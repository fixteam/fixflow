/*******************************************************************************
 * Copyright (c) 2011 Red Hat, Inc.
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 *
 * @author Bob Brodt
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.core.preferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.bpmn2.AdHocSubProcess;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.CallActivity;
import org.eclipse.bpmn2.CallChoreography;
import org.eclipse.bpmn2.ExclusiveGateway;
import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.SubChoreography;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.Transaction;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.modeler.core.Activator;
import org.eclipse.bpmn2.modeler.core.runtime.TargetRuntime;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.core.internal.resources.ProjectPreferences;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.navigator.ResourceNavigator;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.eclipse.emf.common.util.URI;

@SuppressWarnings("restriction")
public class Bpmn2Preferences implements IPreferenceChangeListener, IPropertyChangeListener, IResourceChangeListener {
	public final static String PROJECT_PREFERENCES_ID = "org.eclipse.bpmn2.modeler";
	public final static String PREF_TARGET_RUNTIME = "target.runtime";
	public final static String PREF_TARGET_RUNTIME_LABEL = "Target &Runtime";
	public final static String PREF_SHOW_ADVANCED_PROPERTIES = "show.advanced.properties";
	public final static String PREF_SHOW_ADVANCED_PROPERTIES_LABEL = "Show the &Advanced Properties Tab for BPMN2 Elements";
	public final static String PREF_SHOW_DESCRIPTIONS = "show.descriptions";
	public final static String PREF_SHOW_DESCRIPTIONS_LABEL = "Show &descriptions in Properties Tab for BPMN2 Elements";
	public final static String PREF_EXPAND_PROPERTIES = "expand.properties";
	public final static String PREF_EXPAND_PROPERTIES_LABEL = "E&xpand compound property details instead of showing a selection list";
	public final static String PREF_OVERRIDE_MODEL_ENABLEMENTS = "override.model.enablements";
	public final static String PREF_IS_HORIZONTAL = "is.horizontal";
	public final static String PREF_IS_HORIZONTAL_LABEL = "&Horizontal layout of Pools, Lanes and diagram elements [isHorizontal]";
	
	public final static String PREF_IS_EXPANDED = "is.expanded";
	public final static String PREF_IS_EXPANDED_LABEL = "Expand activity containers (SubProcess, CallActivity, etc.) [isExpanded]";
	public final static String PREF_IS_MESSAGE_VISIBLE = "is.message.visible";
	public final static String PREF_IS_MESSAGE_VISIBLE_LABEL = "Show Participant Band Messages [isMessageVisible]";
	public final static String PREF_IS_MARKER_VISIBLE = "is.marker.visible";
	public final static String PREF_IS_MARKER_VISIBLE_LABEL = "Decorate Exclusive Gateway with \"X\" marker [isMarkerVisible]";
	
	public final static String PREF_WSIL_URL = "wsil.url";
	public final static String PREF_SHAPE_STYLE = "shape.style";

	private static Hashtable<IProject,Bpmn2Preferences> instances = null;
	private static IProject activeProject;

	private IProject project;
	private Preferences projectPreferences;
	private IPreferenceStore globalPreferences;
	private boolean loaded;
	private boolean dirty;
	
	public enum BPMNDIAttributeDefault {
		USE_DI_VALUE,
		DEFAULT_TRUE,
		ALWAYS_TRUE,
		ALWAYS_FALSE
	};
	
	private TargetRuntime targetRuntime;
	private boolean showAdvancedPropertiesTab;
	private boolean overrideModelEnablements;
	private boolean expandProperties;
	private boolean showDescriptions;
	private BPMNDIAttributeDefault isHorizontal;
	private BPMNDIAttributeDefault isExpanded;
	private BPMNDIAttributeDefault isMessageVisible;
	private BPMNDIAttributeDefault isMarkerVisible;
	
	private HashMap<Class, ShapeStyle> shapeStyles = new HashMap<Class, ShapeStyle>();
	
	// TODO: stuff like colors, fonts, etc.

	private Bpmn2Preferences(IProject project) {
		this.project = project;
		IEclipsePreferences rootNode = Platform.getPreferencesService()
				.getRootNode();
		if (project!=null) {
		projectPreferences = rootNode.node(ProjectScope.SCOPE)
				.node(project.getName())
				.node(PROJECT_PREFERENCES_ID);
		if (projectPreferences instanceof ProjectPreferences)
			((ProjectPreferences)projectPreferences).addPreferenceChangeListener(this);
		}		
		globalPreferences = Activator.getDefault().getPreferenceStore();
		globalPreferences.addPropertyChangeListener(this);
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}

	// various preference instance getters
	
	/**
	 * Return the Preferences for the currently active project. This should be used
	 * with caution: the active project is set by the BPMN2Editor, so this should only
	 * be used in a context that is known to have an active editor.
	 * 
	 * @return project preferences
	 */
	public static Bpmn2Preferences getInstance() {
		return getInstance(getActiveProject());
	}
	
	/**
	 * Return the Preferences for the project containing the EMF Resource
	 * 
	 * @param resource
	 * @return project preferences
	 */
	public static Bpmn2Preferences getInstance(Resource resource) {
		return getInstance(resource.getURI());
	}
	
	/**
	 * Return the Preferences for the project containing the EMF Resource specified
	 * by the resource URI. This must be a Platform URI.
	 * 
	 * @param resourceURI
	 * @return project preferences
	 */
	public static Bpmn2Preferences getInstance(URI resourceURI) {
		String filename = resourceURI.toPlatformString(true);
		IProject project = ResourcesPlugin.getWorkspace().getRoot().findMember(filename).getProject();
		return getInstance(project);
	}
	
	/**
	 * Return the Preferences for the given project.
	 * 
	 * @param project
	 * @return project preferences
	 */
	public static Bpmn2Preferences getInstance(IProject project) {
		if (instances==null) {
			instances = new Hashtable<IProject,Bpmn2Preferences>();
		}
		Bpmn2Preferences pref;
		if (project==null)
			pref = new Bpmn2Preferences(null);
		else
			pref = instances.get(project);
		if (pref==null) {
			pref = new Bpmn2Preferences(project);
			instances.put(project, pref);
		}
		return pref;
	}
	
	public IPreferenceStore getGlobalPreferences()
	{
		return globalPreferences;
	}
	
	public Preferences getProjectPreferences()
	{
		return projectPreferences;
	}
	
	public void restoreDefaults(boolean resetProjectPreferences) {
		if (resetProjectPreferences && projectPreferences != null) {
			projectPreferences.remove(PREF_TARGET_RUNTIME);
			projectPreferences.remove(PREF_SHOW_ADVANCED_PROPERTIES);
			projectPreferences.remove(PREF_SHOW_DESCRIPTIONS);
			projectPreferences.remove(PREF_EXPAND_PROPERTIES);
			projectPreferences.remove(PREF_IS_HORIZONTAL);
			projectPreferences.remove(PREF_IS_EXPANDED);
			projectPreferences.remove(PREF_IS_MESSAGE_VISIBLE);
			projectPreferences.remove(PREF_IS_MARKER_VISIBLE);
			for (Class key : shapeStyles.keySet()) {
				projectPreferences.remove(getShapeStyleId(key));
			}
		}
		globalPreferences.setDefault(PREF_TARGET_RUNTIME, TargetRuntime.getFirstNonDefaultId());
		globalPreferences.setDefault(PREF_SHOW_ADVANCED_PROPERTIES, false);
		globalPreferences.setDefault(PREF_SHOW_DESCRIPTIONS, true);
		globalPreferences.setDefault(PREF_EXPAND_PROPERTIES, false);
		globalPreferences.setDefault(PREF_IS_HORIZONTAL, BPMNDIAttributeDefault.DEFAULT_TRUE.name());
		globalPreferences.setDefault(PREF_IS_EXPANDED, BPMNDIAttributeDefault.ALWAYS_TRUE.name());
		globalPreferences.setDefault(PREF_IS_MESSAGE_VISIBLE, BPMNDIAttributeDefault.ALWAYS_TRUE.name());
		globalPreferences.setDefault(PREF_IS_MARKER_VISIBLE, BPMNDIAttributeDefault.DEFAULT_TRUE.name());
		for (Class key : shapeStyles.keySet()) {
			globalPreferences.setDefault(getShapeStyleId(key), IPreferenceStore.STRING_DEFAULT_DEFAULT);
		}

		globalPreferences.setToDefault(PREF_TARGET_RUNTIME);
		globalPreferences.setToDefault(PREF_SHOW_ADVANCED_PROPERTIES);
		globalPreferences.setToDefault(PREF_SHOW_DESCRIPTIONS);
		globalPreferences.setToDefault(PREF_EXPAND_PROPERTIES);
		globalPreferences.setToDefault(PREF_IS_HORIZONTAL);
		globalPreferences.setToDefault(PREF_IS_EXPANDED);
		globalPreferences.setToDefault(PREF_IS_MESSAGE_VISIBLE);
		globalPreferences.setToDefault(PREF_IS_MARKER_VISIBLE);

		List<Class> keys = new ArrayList<Class>();
		keys.addAll(shapeStyles.keySet());
		shapeStyles.clear();
		for (Class key : keys) {
			globalPreferences.setToDefault(getShapeStyleId(key));
			ShapeStyle ss = getShapeStyle(key);
			ss.setDirty(true);
		}

		loaded = false;
		load();
	}
	
	public boolean hasProjectPreference(String key) {
		try {
			String[] keys;
			keys = projectPreferences.keys();
			for (String k : keys) {
				if (k.equals(key))
					return true;
			}
		} catch (Exception e) {
		}
		return false;
	}
	
	public void dispose() {
		if (projectPreferences instanceof ProjectPreferences)
			((ProjectPreferences)projectPreferences).removePreferenceChangeListener(this);
		globalPreferences.removePropertyChangeListener(this);
		instances.remove(project);
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
	}
	
	public synchronized void reload() {
		loaded = false;
		load();
		dirty = false;
	}
	
	public void load() {
		
		if (!loaded) {
			// load all preferences
			if (projectPreferences!=null)
				overrideModelEnablements = projectPreferences.getBoolean(PREF_OVERRIDE_MODEL_ENABLEMENTS, false);

			String id = getString(PREF_TARGET_RUNTIME,TargetRuntime.getFirstNonDefaultId());
			if (id==null || id.isEmpty())
				id = TargetRuntime.getFirstNonDefaultId();
			targetRuntime = TargetRuntime.getRuntime(id);
			showAdvancedPropertiesTab = getBoolean(PREF_SHOW_ADVANCED_PROPERTIES, false);
			showDescriptions = getBoolean(PREF_SHOW_DESCRIPTIONS, false);
			expandProperties = getBoolean(PREF_EXPAND_PROPERTIES, false);
			isHorizontal = getBPMNDIAttributeDefault(PREF_IS_HORIZONTAL, BPMNDIAttributeDefault.USE_DI_VALUE);
			isExpanded = getBPMNDIAttributeDefault(PREF_IS_EXPANDED, BPMNDIAttributeDefault.USE_DI_VALUE);
			isMessageVisible = getBPMNDIAttributeDefault(PREF_IS_MESSAGE_VISIBLE, BPMNDIAttributeDefault.USE_DI_VALUE);
			isMarkerVisible = getBPMNDIAttributeDefault(PREF_IS_MARKER_VISIBLE, BPMNDIAttributeDefault.USE_DI_VALUE);
			
			loaded = true;
		}
	}
	
	public synchronized void save() throws BackingStoreException {
		if (dirty) {
			// this is the only preference that is a project property,
			// and not saved in the preference store for this plugin.
			if (projectPreferences!=null)
				projectPreferences.putBoolean(PREF_OVERRIDE_MODEL_ENABLEMENTS, overrideModelEnablements);

			setString(PREF_TARGET_RUNTIME,targetRuntime.getId());
			setBoolean(PREF_SHOW_ADVANCED_PROPERTIES, showAdvancedPropertiesTab);
			setBoolean(PREF_SHOW_DESCRIPTIONS, showDescriptions);
			setBoolean(PREF_EXPAND_PROPERTIES, expandProperties);
			setBPMNDIAttributeDefault(PREF_IS_HORIZONTAL, isHorizontal);

			setBPMNDIAttributeDefault(PREF_IS_EXPANDED, isExpanded);
			setBPMNDIAttributeDefault(PREF_IS_MESSAGE_VISIBLE, isMessageVisible);
			setBPMNDIAttributeDefault(PREF_IS_MARKER_VISIBLE, isMarkerVisible);
		}
		
		for (Entry<Class, ShapeStyle> entry : shapeStyles.entrySet()) {
			setShapeStyle(entry.getKey(), entry.getValue());
		}
		
		if (projectPreferences!=null)
			projectPreferences.flush();
		dirty = false;
	}
	
	public static String getShapeStyleId(EObject object) {
		try {
			Class clazz = Class.forName(object.eClass().getInstanceClassName());
			return getShapeStyleId(clazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getShapeStyleId(Class clazz) {
		return clazz.getSimpleName() + "." + PREF_SHAPE_STYLE;
	}

	public ShapeStyle getShapeStyle(EObject object) {
		Class clazz;
		try {
			clazz = Class.forName(object.eClass().getInstanceClassName());
			return getShapeStyle(clazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ShapeStyle getShapeStyle(Class clazz) {
		ShapeStyle ss = shapeStyles.get(clazz);
		if (ss==null) {
			String key = getShapeStyleId(clazz);
			String value;
			if (hasProjectPreference(key)) {
				value = projectPreferences.get(key, "");
			}
			else {
				value = globalPreferences.getString(key);
				if (value.isEmpty()) {
					//get from TargetRuntime
					ss = getRuntime().getShapeStyle(clazz);
					if (ss==null) {
						if (!TargetRuntime.DEFAULT_RUNTIME_ID.equals(getRuntime().getId())) {
							// search default runtime
							ss = TargetRuntime.getDefaultRuntime().getShapeStyle(clazz);
						}
						if (ss==null) {
							// give up
							ss = new ShapeStyle();
						}
					}
					// don't cache this because we don't want to save it PreferenceStore
					return ss;
				}
			}
			ss = ShapeStyle.decode(value);
			shapeStyles.put(clazz, ss);
		}
		return ss;
	}
	
	public void setShapeStyle(Class clazz, ShapeStyle style) {
		if (style.isDirty()) {
			String key = getShapeStyleId(clazz);
			String value = ShapeStyle.encode(style);
			if (hasProjectPreference(key))
				projectPreferences.put(key, value);
			else
				globalPreferences.setValue(key, value);
			shapeStyles.put(clazz, style);
			style.setDirty(false);
			dirty = true;
		}
	}
	
	public TargetRuntime getRuntime() {
		load();
		return targetRuntime;
	}

	/**
	 * If the project has not been configured for a specific runtime through the "BPMN2"
	 * project properties page (i.e. the target is "None") then allow the runtime extension
	 * plug-ins an opportunity to identify the given process file contents as their own.
	 * 
	 * If none of the plug-ins respond with "yes, this file is targeted for my runtime",
	 * then use the "None" as the extension. This will configure the BPMN2 Modeler with
	 * generic property sheets and other default behavior.
	 * 
	 * @param file
	 * @return
	 */
	public TargetRuntime getRuntime(IFile file) {
		
		load();
		
		if (targetRuntime == TargetRuntime.getDefaultRuntime()) {
			for (TargetRuntime rt : TargetRuntime.getAllRuntimes()) {
				if (rt.getRuntimeExtension().isContentForRuntime(file)) {
					return rt;
				}
			}
		}
		else
			return targetRuntime;
		
		return TargetRuntime.getDefaultRuntime();
	}
	
	public void setRuntime(TargetRuntime rt) {
		
		assert(rt!=null);
		overrideGlobalString(PREF_TARGET_RUNTIME, rt.getId());
		targetRuntime = rt;
	}
	
	public boolean getShowAdvancedPropertiesTab() {
		load();
		return showAdvancedPropertiesTab;
	}
	
	public void setShowAdvancedPropertiesTab(boolean show) {
		overrideGlobalBoolean(PREF_SHOW_ADVANCED_PROPERTIES, show);
		showAdvancedPropertiesTab = show;
	}
	
	public boolean getShowDescriptions() {
		load();
		return showDescriptions;
	}
	
	public void setShowDescriptions(boolean show) {
		overrideGlobalBoolean(PREF_SHOW_DESCRIPTIONS, show);
		showDescriptions = show;
	}
	
	public boolean getOverrideModelEnablements() {
		load();
		return overrideModelEnablements;
	}
	
	public void setOverrideModelEnablements(boolean override) {
		overrideModelEnablements = override;
		dirty = true;
	}
	
	public boolean getExpandProperties() {
		load();
		return expandProperties;
	}
	
	public void setExpandProperties(boolean expand) {
		overrideGlobalBoolean(PREF_EXPAND_PROPERTIES, expand);
		expandProperties = expand;
	}

	public boolean isHorizontalDefault() {
		load();
		return isHorizontal==BPMNDIAttributeDefault.ALWAYS_TRUE ||
				isHorizontal==BPMNDIAttributeDefault.DEFAULT_TRUE;
	}

	public BPMNDIAttributeDefault getIsHorizontal() {
		return isHorizontal;
	}
	
	public void setIsHorizontal(BPMNDIAttributeDefault value) {
		overrideGlobalBPMNDIAttributeDefault(PREF_IS_HORIZONTAL, value);
		this.isHorizontal = value;
	}

	public boolean isExpandedDefault() {
		load();
		return isExpanded==BPMNDIAttributeDefault.ALWAYS_TRUE ||
				isExpanded==BPMNDIAttributeDefault.DEFAULT_TRUE;
	}

	public BPMNDIAttributeDefault getIsExpanded() {
		load();
		return isExpanded;
	}

	public void setIsExpanded(BPMNDIAttributeDefault value) {
		overrideGlobalBPMNDIAttributeDefault(PREF_IS_EXPANDED, value);
		this.isExpanded = value;
	}

	public BPMNDIAttributeDefault getIsMessageVisible() {
		load();
		return isMessageVisible;
	}

	public void setIsMessageVisible(BPMNDIAttributeDefault value) {
		overrideGlobalBPMNDIAttributeDefault(PREF_IS_MESSAGE_VISIBLE, value);
		this.isMessageVisible = value;
	}

	public BPMNDIAttributeDefault getIsMarkerVisible() {
		load();
		return isMarkerVisible;
	}

	public void setIsMarkerVisible(BPMNDIAttributeDefault value) {
		overrideGlobalBPMNDIAttributeDefault(PREF_IS_MARKER_VISIBLE, value);
		this.isMarkerVisible = value;
	}

	@Override
	public void preferenceChange(PreferenceChangeEvent event) {
		reload();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		reload();
	}

	// preference/property getters and setters
	public boolean getBoolean(String key, boolean defaultValue) {
		if (hasProjectPreference(key))
			return projectPreferences.getBoolean(key, defaultValue);
		if (globalPreferences.contains(key))
			return globalPreferences.getBoolean(key);
		return defaultValue;
	}
	
	public void setBoolean(String key, boolean value) {
		if (hasProjectPreference(key))
			projectPreferences.putBoolean(key, value);
		else
			globalPreferences.setValue(key, value);
	}

	private void overrideGlobalBoolean(String key, boolean value) {
		if (value!=globalPreferences.getBoolean(key)) {
			projectPreferences.putBoolean(key, value);
			dirty = true;
		}
	}
	
	public String getString(String key, String defaultValue) {
		if (hasProjectPreference(key))
			return projectPreferences.get(key, defaultValue);
		if (globalPreferences.contains(key))
			return globalPreferences.getString(key);
		return defaultValue;
	}
	
	public void setString(String key, String value) {
		if (hasProjectPreference(key))
			projectPreferences.put(key, value);
		else
			globalPreferences.setValue(key, value);
	}

	private void overrideGlobalString(String key, String value) {
		if (value!=globalPreferences.getString(key)) {
			projectPreferences.put(key, value);
			dirty = true;
		}
	}

	public BPMNDIAttributeDefault getBPMNDIAttributeDefault(String key, BPMNDIAttributeDefault defaultValue) {
		BPMNDIAttributeDefault value = null;
		if (hasProjectPreference(key))
			value = BPMNDIAttributeDefault.valueOf(projectPreferences.get(key, defaultValue.name()));
		else if (globalPreferences.contains(key))
			value = BPMNDIAttributeDefault.valueOf(globalPreferences.getString(key));
		else
			value = defaultValue;
		return value;
	}
	
	public void setBPMNDIAttributeDefault(String key, BPMNDIAttributeDefault value) {
		if (hasProjectPreference(key))
			projectPreferences.put(key, value.name());
		else
			globalPreferences.setValue(key, value.name());
	}

	private void overrideGlobalBPMNDIAttributeDefault(String key, BPMNDIAttributeDefault value) {
		if (value!=BPMNDIAttributeDefault.valueOf(globalPreferences.getString(key))) {
			projectPreferences.put(key, value.name());
			dirty = true;
		}
	}

	public static String[] getBPMNDIAttributeDefaultChoices() {
		BPMNDIAttributeDefault[] values = BPMNDIAttributeDefault.values();
		String[] choices = new String[values.length];
		int i = 0;
		for (BPMNDIAttributeDefault v : values) {
			String text = "None";
			switch (v) {
			case USE_DI_VALUE:
				text = "False if not set";
				break;
			case DEFAULT_TRUE:
				text = "True if not set";
				break;
			case ALWAYS_TRUE:
				text = "Always true";
				break;
			case ALWAYS_FALSE:
				text = "Always false";
				break;
			}
			choices[i++] = text;
		}
		return choices;
	}
	
	public static String[][] getBPMNDIAttributeDefaultChoicesAndValues() {
		String[] choices = getBPMNDIAttributeDefaultChoices();
		BPMNDIAttributeDefault[] values = BPMNDIAttributeDefault.values();
		String[][] choicesAndValues = new String[choices.length][2];
		int i = 0;
		for (BPMNDIAttributeDefault v : values) {
			choicesAndValues[i][0] = choices[i];
			choicesAndValues[i][1] = v.name();
			++i;
		}
		return choicesAndValues;
	}
	
	/**
	 * Applies preference defaults to a BPMNShape object. The <code>attribs</code> map should contain
	 * only those attributes that are set on the BPMNShape object (as read from the bpmn XML file).
	 * This is used to determine the appropriate default values for certain optional attributes, e.g.
	 * isHorizontal, isExpanded, etc.
	 * 
	 * @param bpmnShape - the BPMNShape object whose attributes are to be set
	 * @param attribs - map of BPMN DI attributes currently set on the BPMNShape object. May be null.
	 * @see getIsHorizontal(), getIsExpanded(), getIsMessageVisible() and getIsMarkerVisible()
	 */
	public void applyBPMNDIDefaults(BPMNShape bpmnShape, Map<String,String>attribs) {
		boolean isHorizontalSet = false;
		boolean isExpandedSet = false;
		boolean isMessageVisibleSet = false;
		boolean isMarkerVisibleSet = false;
		boolean choreographyActivityShapeSet = false;
		
		if (attribs != null) {
			for (Entry<String, String> entry : attribs.entrySet()) {
				String name = entry.getKey();
				if ("isHorizontal".equals(name)) {
					isHorizontalSet = true;
				}
				if ("isExpanded".equals(name)) {
					isExpandedSet = true;
				}
				if ("isMessageVisible".equals(name)) {
					isMessageVisibleSet = true;
				}
				if ("isMarkerVisible".equals(name)) {
					isMarkerVisibleSet = true;
				}
				if ("choreographyActivityShape".equals(name)) {
					choreographyActivityShapeSet = true;
				}
			}
		}
		
		BaseElement be = bpmnShape.getBpmnElement();
		
		// isHorizontal only applies to Pools and Lanes, not Participant bands
		if (!isHorizontalSet) {
			if ((be instanceof Participant && !choreographyActivityShapeSet) || be instanceof Lane) {
				boolean horz = isHorizontalDefault();
				bpmnShape.setIsHorizontal(horz);
			}
		}
		else {
			if ((be instanceof Participant && !choreographyActivityShapeSet) || be instanceof Lane) {
				BPMNDIAttributeDefault df = getIsHorizontal();
				switch(df) {
				case ALWAYS_TRUE:
					bpmnShape.setIsHorizontal(true);
					break;
				case ALWAYS_FALSE:
					bpmnShape.setIsHorizontal(false);
					break;
				}

			}
		}
		
		// isExpanded only applies to activity containers (SubProcess, AdHocSubProcess, etc.)
		if (!isExpandedSet) {
			if (be instanceof  SubProcess ||
					be instanceof AdHocSubProcess ||
					be instanceof Transaction ||
					be instanceof SubChoreography ||
					be instanceof CallActivity ||
					be instanceof CallChoreography) {
				boolean value = false;
				BPMNDIAttributeDefault df = getIsExpanded();
				switch(df) {
				case ALWAYS_TRUE:
				case DEFAULT_TRUE:
					value = true;
					break;
				case ALWAYS_FALSE:
				case USE_DI_VALUE:
					value = false;
				}
				bpmnShape.setIsExpanded(value);
			}
		}
		else {
			if (be instanceof  SubProcess ||
					be instanceof AdHocSubProcess ||
					be instanceof Transaction ||
					be instanceof SubChoreography ||
					be instanceof CallActivity ||
					be instanceof CallChoreography) {
				BPMNDIAttributeDefault df = getIsExpanded();
				switch(df) {
				case ALWAYS_TRUE:
					bpmnShape.setIsExpanded(true);
					break;
				case ALWAYS_FALSE:
					bpmnShape.setIsExpanded(false);
					break;
				}
			}
		}
		
		// isMessageVisible only applies to Participant Bands
		if (!isMessageVisibleSet) {
			if (be instanceof Participant && choreographyActivityShapeSet) {
				boolean value = false;
				BPMNDIAttributeDefault df = getIsMessageVisible();
				switch(df) {
				case ALWAYS_TRUE:
				case DEFAULT_TRUE:
					value = true;
					break;
				case ALWAYS_FALSE:
				case USE_DI_VALUE:
					value = false;
				}
				bpmnShape.setIsMessageVisible(value);
			}
		}
		else {
			if (be instanceof Participant && choreographyActivityShapeSet) {
				BPMNDIAttributeDefault df = getIsMessageVisible();
				switch(df) {
				case ALWAYS_TRUE:
					bpmnShape.setIsMessageVisible(true);
					break;
				case ALWAYS_FALSE:
					bpmnShape.setIsMessageVisible(false);
					break;
				}
			}
		}
		
		// isMarkerVisible only applies to ExclusiveGateway
		if (!isMarkerVisibleSet) {
			if (be instanceof ExclusiveGateway) {
				BPMNDIAttributeDefault df = getIsMarkerVisible();
				switch(df) {
				case ALWAYS_TRUE:
				case DEFAULT_TRUE:
					bpmnShape.setIsMarkerVisible(true);
					break;
				case ALWAYS_FALSE:
				case USE_DI_VALUE:
					bpmnShape.setIsMarkerVisible(false);
					break;
				}
			}
		}
		else {
			if (be instanceof ExclusiveGateway) {
				BPMNDIAttributeDefault df = getIsMarkerVisible();
				switch(df) {
				case ALWAYS_TRUE:
					bpmnShape.setIsMarkerVisible(true);
					break;
				case ALWAYS_FALSE:
					bpmnShape.setIsMarkerVisible(false);
					break;
				}
			}
		}
	}

	// TODO: use CNF for indigo & future - keep ResourceNavigator for backward compatibility
	public static IProject getActiveProject() {
		if (activeProject!=null)
			return activeProject;
		
		IWorkbench workbench = PlatformUI.getWorkbench(); 
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		if (page!=null) {
			IViewPart[] parts = page.getViews();
	
			for (int i = 0; i < parts.length; i++) {
				if (parts[i] instanceof ResourceNavigator) {
					ResourceNavigator navigator = (ResourceNavigator) parts[i];
					StructuredSelection sel = (StructuredSelection) navigator.getTreeViewer().getSelection();
					IResource resource = (IResource) sel.getFirstElement();
					activeProject = resource.getProject();
					break;
				}
			}
		}
		return activeProject;
	}

	public static void setActiveProject(IProject project) {
		activeProject = project;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
	 */
	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		int type = event.getType();
		if (type==IResourceChangeEvent.PRE_CLOSE) {
			try {
				save();
			} catch (Exception e) {
				e.printStackTrace();
			}
			dispose();
		}
		if (type==IResourceChangeEvent.PRE_DELETE)
			dispose();
	}
}
