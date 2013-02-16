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
 * @author Ivar Meikas
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.core.preferences;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.modeler.core.runtime.ModelEnablementDescriptor;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

public class ToolEnablementPreferences {

	private final Preferences prefs;

	private static HashSet<EClass> elementSet = new HashSet<EClass>();


	static {
		Bpmn2Package i = Bpmn2Package.eINSTANCE;
		final List<EClass> items = new ArrayList<EClass>();
		for (EClassifier eclassifier : i.getEClassifiers() ) {
			if (eclassifier instanceof EClass) {
				items.add((EClass)eclassifier);
			}
		}
		elementSet.addAll(items);
//		elementSet.addAll(getSubClasses(i.getFlowElement()));
//		elementSet.addAll(getSubClasses(i.getItemAwareElement()));
//		elementSet.addAll(getSubClasses(i.getDataAssociation()));
//		elementSet.addAll(getSubClasses(i.getRootElement()));
//		elementSet.addAll(getSubClasses(i.getEventDefinition()));
//		elementSet.addAll(getSubClasses(i.getLoopCharacteristics()));
//		elementSet.addAll(getSubClasses(i.getExpression()));
//		elementSet.add(i.getDefinitions());
//		elementSet.add(i.getOperation());
//		elementSet.add(i.getLane());
//		elementSet.add(i.getEscalation());
//		elementSet.add(i.getPotentialOwner());
//		elementSet.add(i.getResourceAssignmentExpression());
//		elementSet.add(i.getInputSet());
//		elementSet.add(i.getOutputSet());
//		elementSet.add(i.getAssignment());
//		elementSet.add(i.getAssociation());
//		elementSet.add(i.getTextAnnotation());
//		elementSet.add(i.getMessageFlow());
//		elementSet.add(i.getConversationLink());
//		elementSet.add(i.getGroup());
//		elementSet.add(i.getConversation());
	}

	private ToolEnablementPreferences(Preferences prefs) {
		this.prefs = prefs;
	}

	public static ToolEnablementPreferences getPreferences(IProject project) {
		IEclipsePreferences rootNode = Platform.getPreferencesService().getRootNode();
		Preferences prefs = rootNode.node(ProjectScope.SCOPE).node(project.getName())
				.node("org.eclipse.bpmn2.modeler.tools");
		return new ToolEnablementPreferences(prefs);
	}

	public void setEnablements(ModelEnablementDescriptor md) {
		setEnabledAll(false);
		
		Collection<String> enablements = md.getAllEnabled();
		for (String s : enablements) {
			String className = null;
			String featureName = null;
			if (s.contains(".")) {
				String[] a = s.split("\\.");
				className = a[0];
				featureName = a[1];
			}
			else
				className = s;
			for (EClass e : elementSet) {
				if (e.getName().equals(className)) {
					prefs.putBoolean(className, true);
					if (featureName!=null)
						prefs.putBoolean(className+"."+featureName, true);
					break;
				}
			}
		}
	}
	
	public List<ToolEnablement> getAllElements() {
		ArrayList<ToolEnablement> ret = new ArrayList<ToolEnablement>();

		for (EClass e : elementSet) {

			ToolEnablement tool = new ToolEnablement();
			tool.setTool(e);
			tool.setEnabled(isEnabled(e));
			ret.add(tool);

			HashSet<EStructuralFeature> possibleFeatures = new HashSet<EStructuralFeature>();

			ArrayList<ToolEnablement> children = new ArrayList<ToolEnablement>();

			for (EAttribute a : e.getEAllAttributes()) {
				possibleFeatures.add(a);
			}

			for (EReference a : e.getEAllContainments()) {
				possibleFeatures.add(a);
			}

			for (EReference a : e.getEAllReferences()) {
				possibleFeatures.add(a);
			}

			for (EStructuralFeature feature : possibleFeatures) {
				ToolEnablement toolEnablement = new ToolEnablement(feature, tool);
				toolEnablement.setEnabled(isEnabled(e, feature));
				children.add(toolEnablement);
			}
			sortTools(children);
			tool.setChildren(children);
		}
		sortTools(ret);
		return ret;
	}
	
	private void setEnabledAll(boolean enabled) {
		for (EClass e : elementSet) {
			prefs.putBoolean(e.getName(), enabled);

			for (EAttribute a : e.getEAllAttributes()) {
				prefs.putBoolean(e.getName()+"."+a.getName(), enabled);
			}

			for (EReference a : e.getEAllContainments()) {
				prefs.putBoolean(e.getName()+"."+a.getName(), enabled);
			}

			for (EReference a : e.getEAllReferences()) {
				prefs.putBoolean(e.getName()+"."+a.getName(), enabled);
			}
		}
	}

	private void sortTools(ArrayList<ToolEnablement> ret) {
		Collections.sort(ret, new Comparator<ToolEnablement>() {

			@Override
			public int compare(ToolEnablement o1, ToolEnablement o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}

		});
	}

	public boolean isEnabled(EClass element) {
		return prefs.getBoolean(element.getName(), true);
	}

	public boolean isEnabled(String name) {
		return prefs.getBoolean(name, true);
	}

	public boolean isEnabled(String name, boolean b) {
		return prefs.getBoolean(name, b);
	}

	public boolean isEnabled(EClass c, ENamedElement element) {
		return prefs.getBoolean(c.getName() + "." + element.getName(), true);
	}

	public void setEnabled(ToolEnablement tool, boolean enabled) {
		prefs.putBoolean(tool.getPreferenceName(), enabled);
	}

	public boolean isEnabled(ToolEnablement tool) {
		return prefs.getBoolean(tool.getPreferenceName(), true);
	}

	public void flush() throws BackingStoreException {
		prefs.flush();
	}

	public static List<EClass> getSubClasses(EClass parentClass) {

		List<EClass> classList = new ArrayList<EClass>();
		EList<EClassifier> classifiers = Bpmn2Package.eINSTANCE.getEClassifiers();

		for (EClassifier classifier : classifiers) {
			if (classifier instanceof EClass) {
				EClass clazz = (EClass) classifier;

				clazz.getEAllSuperTypes().contains(parentClass);
				if (parentClass.isSuperTypeOf(clazz) && !clazz.isAbstract()) {
					classList.add(clazz);
				}
			}
		}
		return classList;
	}

	public void importPreferences(String path) throws FileNotFoundException, IOException, BackingStoreException {
		Properties p = new Properties();
		p.load(new FileInputStream(path));

		for (Object k : p.keySet()) {
			Object object = p.get(k);
			if (k instanceof String && object instanceof String) {
				prefs.putBoolean((String) k, Boolean.parseBoolean((String) object));
			}
		}
		prefs.flush();
	}

	public void export(String path) throws BackingStoreException, FileNotFoundException, IOException {
		FileWriter fw = new FileWriter(path);

		List<String> keys = Arrays.asList(prefs.keys());
		Collections.sort(keys);
		for (String k : keys) {
			fw.write(k + "=" + prefs.getBoolean(k, true) + "\r\n");
		}
		fw.flush();
		fw.close();
	}

	public static ArrayList<EStructuralFeature> getAttributes(EClass eClass) {
		ArrayList<EStructuralFeature> ret = new ArrayList<EStructuralFeature>();

//		if (Bpmn2Package.eINSTANCE.getTask().equals(eClass)) {
//			ret.add(taskName);
//		} else if (Bpmn2Package.eINSTANCE.getCallActivity().equals(eClass)) {
//			ret.add(waitFor);
//			ret.add(independent);
//		} else if (Bpmn2Package.eINSTANCE.getBusinessRuleTask().equals(eClass)) {
//			ret.add(ruleFlowGroup);
//		} else if (Bpmn2Package.eINSTANCE.getProcess().equals(eClass)) {
//			ret.add(packageName);
//		}

		return ret;
	}

}
