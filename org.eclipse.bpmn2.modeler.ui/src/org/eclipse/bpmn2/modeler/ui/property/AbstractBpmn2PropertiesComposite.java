/******************************************************************************* 
 * Copyright (c) 2011 Red Hat, Inc. 
 *  All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 * IBM Corporation - http://dev.eclipse.org/viewcvs/viewvc.cgi/org.eclipse.swt.snippets/src/org/eclipse/swt/snippets/Snippet19.java
 *
 * @author Innar Made
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.ui.property;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.modeler.core.ModelHandler;
import org.eclipse.bpmn2.modeler.core.ModelHandlerLocator;
import org.eclipse.bpmn2.modeler.core.adapters.InsertionAdapter;
import org.eclipse.bpmn2.modeler.core.runtime.ModelEnablementDescriptor;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.ui.Activator;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.bpmn2.modeler.ui.property.editors.BooleanObjectEditor;
import org.eclipse.bpmn2.modeler.ui.property.editors.ComboObjectEditor;
import org.eclipse.bpmn2.modeler.ui.property.editors.FeatureListObjectEditor;
import org.eclipse.bpmn2.modeler.ui.property.editors.IntObjectEditor;
import org.eclipse.bpmn2.modeler.ui.property.editors.ObjectEditor;
import org.eclipse.bpmn2.modeler.ui.property.editors.TextObjectEditor;
import org.eclipse.bpmn2.modeler.ui.util.PropertyUtil;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.IExpansionListener;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;


/**
 * This is a base class for all Property Sheet Sections. The Composite is used to render
 * the "structural features" for an EObject, presumably a subclass of BaseElement or some
 * other BPMN2 metamodel object.
 * 
 * The Composite uses a GridLayout with 3 columns: the leftmost column is designated for a
 * label; the two rightmost columns are designated for input or editing widgets, depending
 * on the type of structural feature being rendered.
 * 
 * EAttribute and EReference type of structural features are collected and rendered within
 * a non-collapsible FormSection given the title "Attributes". List features are rendered in
 * collapsible AbstractBpmn2TableComposite table widgets. 
 * 
 * Subclasses must implement the abstract createBindings() method to construct their editing
 * widgets. These widgets are torn down and reconstructed when the editor selection changes.
 */
public abstract class AbstractBpmn2PropertiesComposite extends Composite implements ResourceSetListener {

	public final static Bpmn2Package PACKAGE = Bpmn2Package.eINSTANCE;
	public final static Bpmn2Factory FACTORY = Bpmn2Factory.eINSTANCE;

	protected AbstractBpmn2PropertySection propertySection;
	protected EObject be;
	protected FormToolkit toolkit;
	protected ModelEnablementDescriptor modelEnablement;
	protected ModelHandler modelHandler;
	protected TransactionalEditingDomainImpl domain;
	protected IPreferenceStore preferenceStore ;

	protected Section attributesSection = null;
	protected Composite attributesComposite = null;
	protected Font descriptionFont = null;
	
	protected ChildObjectStack objectStack = new ChildObjectStack();

	/**
	 * Constructor for embedding this composite in an AbstractBpmn2PropertySection.
	 * This is the "normal" method of creating this composite.
	 * 
	 * @param section
	 */
	public AbstractBpmn2PropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section.getParent(), SWT.NONE);
		propertySection = section;
		toolkit = propertySection.getWidgetFactory();
		initialize();
	}
	
	/**
	 * Constructor for embedding this composite in a nested property section,
	 * e.g. the AdvancedPropertySection uses this.
	 * 
	 * @param parent
	 * @param style
	 */
	public AbstractBpmn2PropertiesComposite(Composite parent, int style) {
		super(parent,style);
		toolkit = new FormToolkit(Display.getCurrent());
		initialize();
	}

	protected void initialize() {
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		setLayout(new GridLayout(3, false));
		// set a default Layout Data if the parent is using a GridLayout
		if (getParent().getLayout() instanceof GridLayout) {
			GridLayout layout = (GridLayout) getParent().getLayout();
			setLayoutData(new GridData(SWT.FILL,SWT.TOP,true,false,layout.numColumns,1));
		}
		addDomainListener();
	}
	
	private void addDomainListener() {
		try {
			if (domain==null) {
				domain = (TransactionalEditingDomainImpl)BPMN2Editor.getActiveEditor().getEditingDomain();
				domain.addResourceSetListener(this);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	private void removeDomainListener() {
		if (domain!=null) {
			domain.removeResourceSetListener(this);
		}
	}
	
	@Override
	public void dispose() {
		removeDomainListener();
		PropertyUtil.disposeChildWidgets(AbstractBpmn2PropertiesComposite.this);
		super.dispose();
	}

	public void setPropertySection(AbstractBpmn2PropertySection section) {
		propertySection = section;
	}
	
	/**
	 * This method is called by the when the property sheet tab to update the UI
	 * after a new selection is made. Updating consists of a full teardown of the
	 * widget tree and then rebuilding it for the newly selected EObject. Since the
	 * same composite MAY be used for different EObject types, the widgets may be
	 * completely different, hence the need for teardown and setup for each new selection.
	 * 
	 * @param bpmn2Editor
	 * @param object
	 */
	public void setEObject(BPMN2Editor bpmn2Editor, final EObject object) {
		modelEnablement = bpmn2Editor.getTargetRuntime().getModelEnablements(object);
		try {
			modelHandler = ModelHandlerLocator.getModelHandler(bpmn2Editor.getDiagramTypeProvider().getDiagram()
					.eResource());
		} catch (IOException e1) {
			Activator.showErrorWithLogging(e1);
		}
		
		setEObject(object);
	}
	
	public final EObject getEObject() {
		return be;
	}

	protected final void setEObject(final EObject object) {
		cleanBindings();
		be = object;
		if (be != null) {
			createBindings(be);
			PropertyUtil.recursivelayout(getParent());
		}
	}
	
	/**
	 * Querries the owning AbstractBpmn2PropertySection for its owning BPMN2Editor.
	 * If this composite is not owned by a AbstractBpmn2PropertySection, then return
	 * the currently active editor.
	 * 
	 * @return the BPMN2Editor that owns this property section.
	 */
	public BPMN2Editor getDiagramEditor() {
		if (propertySection!=null)
			return (BPMN2Editor)propertySection.getDiagramEditor();
		return BPMN2Editor.getActiveEditor();
	}

	/**
	 * Tear down all child widgets
	 */
	protected void cleanBindings() {
		PropertyUtil.disposeChildWidgets(this);
	}

	public FormToolkit getToolkit() {
		return toolkit;
	}
	
	/**
	 * Returns the composite that is used to contain all EAttributes for the
	 * current selection. The default behavior is to construct a non-collapsible
	 * Form Section and create a Composite within that section.
	 * 
	 * @return the Composite root for the current selection's EAttributes
	 */
	public Composite getAttributesParent() {
		if (attributesSection==null || attributesSection.isDisposed()) {

			if (objectStack.peek()==be)
				attributesSection = createSection(objectStack.getAttributesParent(), "Attributes");
			else
				attributesSection = createSubSection(objectStack.getAttributesParent(),
						ModelUtil.getLabel(objectStack.peek()) + " Attributes");
			
			attributesSection.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true, 3, 1));
			attributesComposite = toolkit.createComposite(attributesSection);
			attributesSection.setClient(attributesComposite);
			attributesComposite.setLayout(new GridLayout(3,false));
			attributesSection.setExpanded(true);
		}
		return attributesComposite;
	}
	
	/**
	 * Set the title of the Attributes section
	 * @param title
	 */
	public void setTitle(String title) {
		getAttributesParent();
		if (attributesSection!=null)
			attributesSection.setText(title);
	}

	/**
	 * This method is called when setEObject is called and this should recreate
	 *  all bindings and widgets for the current selection.
	 *  
	 * @param be the business object linked to the currently selected EditPart
	 * through the Graphiti DiagramEditor framework.
	 */
	public abstract void createBindings(EObject be);
	
	/**
	 * Convenience method to look up an EObject's feature by name.
	 * 
	 * @param object the EObject
	 * @param name the feature name string
	 * @return the EStructuralFeature or null if the feature does not exist for this object
	 */
	protected EStructuralFeature getFeature(EObject object, String name) {
		EStructuralFeature feature = ((EObject)object).eClass().getEStructuralFeature(name);
		if (feature==null) {
			// maybe it's an anyAttribute?
			List<EStructuralFeature> anyAttributes = ModelUtil.getAnyAttributes(object);
			for (EStructuralFeature f : anyAttributes) {
				if (f.getName().equals(name))
					return f;
			}
		}
		return feature;
	}

	/**
	 * Convenience method to check if a feature is an EAttribute
	 * @param feature
	 * @return
	 */
	protected boolean isAttribute(EObject object, EStructuralFeature feature) {
		// maybe it's an anyAttribute?
		if (feature instanceof EAttribute)
			return true;

		List<EStructuralFeature> anyAttributes = ModelUtil.getAnyAttributes(object);
		for (EStructuralFeature f : anyAttributes) {
			if (f.getName().equals(feature.getName()))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Convenience method to check if a feature is an EList
	 * @param object
	 * @param feature
	 * @return
	 */
	protected boolean isList(EObject object, EStructuralFeature feature) {
		if (feature!=null) {
			Object list = object.eGet(feature);
			return (list instanceof EObjectContainmentEList);
		}
		return false;
	}

	/**
	 * Convenience method to check if a feature is an EReference
	 * @param feature
	 * @return
	 */
	protected boolean isReference(EObject object, EStructuralFeature feature) {
		if (feature!=null) {
			Object list = object.eGet(feature);
			if (list instanceof EList && !(list instanceof EObjectContainmentEList))
				return true;
		}
		return (feature instanceof EReference);
	}

	protected Label createLabel(Composite parent, String name) {
		Label label = toolkit.createLabel(parent, name);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		return label;
	}

	public Font getDescriptionFont() {
		if (descriptionFont==null) {
			Display display = Display.getCurrent();
		    FontData data = display.getSystemFont().getFontData()[0];
		    descriptionFont = new Font(display, data.getName(), data.getHeight() + 1, SWT.NONE);
		}
		return descriptionFont;
	}

	protected StyledText createDescription(Composite parent, String description) {
		Display display = Display.getCurrent();
		final StyledText styledText = new StyledText(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.WRAP | SWT.READ_ONLY);
		styledText.setText(description);

	    styledText.setFont(getDescriptionFont());
		
		styledText.setBackground(display.getSystemColor(SWT.COLOR_INFO_BACKGROUND));
		styledText.setForeground(display.getSystemColor(SWT.COLOR_INFO_FOREGROUND));
		
		GridData d = new GridData(SWT.FILL, SWT.FILL, false, false, 3, 1);
		d.horizontalIndent = 4;
		d.verticalIndent = 4;
		d.heightHint = (int)(5.5 * getDescriptionFont().getFontData()[0].getHeight());
		d.widthHint = 100;
		styledText.setLayoutData(d);

		return styledText;
	}

	protected Section createSection(Composite parent, final String title) {
		Section section = toolkit.createSection(parent,
				ExpandableComposite.TWISTIE |
				ExpandableComposite.EXPANDED |
				ExpandableComposite.TITLE_BAR);
		section.setText(title);
		
		if (getEObject()!=null) {
			final String prefKey = "section."+getEObject().eClass().getName()+title+"."+".expanded";
			preferenceStore= Activator.getDefault().getPreferenceStore();
			boolean expanded = preferenceStore.getBoolean(prefKey);
			section.setExpanded(expanded);
		}
		
		section.addExpansionListener(new IExpansionListener() {
			@Override
			public void expansionStateChanging(ExpansionEvent e) {
			}

			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				if (getEObject()!=null) {
					final String prefKey = "section."+getEObject().eClass().getName()+title+"."+".expanded";
					preferenceStore= Activator.getDefault().getPreferenceStore();
					preferenceStore.setValue(prefKey, e.getState());
				}
			}
		});
		return section;
	}

	protected Section createSubSection(Composite parent, String title) {
		Section section = toolkit.createSection(parent,
				ExpandableComposite.EXPANDED |
				ExpandableComposite.TITLE_BAR);
		section.setText(title);
		section.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true, 3,1));
		return section;
	}

	// TODO: clean this mess up! Too many variations of bindAttribute()
	
	protected void bindAttribute(EObject object, String name) {
		bindAttribute(null,object,name, null);
	}

	protected void bindAttribute(EObject object, String name, String label) {
		bindAttribute(null,object,name,label);
	}
	
	protected void bindAttribute(Composite parent, EObject object, String name) {
		bindAttribute(parent,object,name,null);
	}
	
	protected void bindAttribute(Composite parent, EObject object, String name, String label) {
		EStructuralFeature feature = getFeature(object,name);
		if (isAttribute(object,feature)) {
			bindAttribute(object,(EAttribute)feature,label);
		}
	}

	protected void bindAttribute(EObject object, EAttribute attribute) {
		bindAttribute(null,object,attribute,null);
	}
	
	protected void bindAttribute(EObject object, EAttribute attribute, String label) {
		bindAttribute(null,object,attribute,label);
	}
	
	protected void bindAttribute(Composite parent, EObject object, EAttribute attribute) {
		bindAttribute(parent,object,attribute,null);
	}
	
	protected void bindAttribute(Composite parent, EObject object, EAttribute attribute, String label) {

		if (modelEnablement.isEnabled(object.eClass(), attribute)) {

			if (parent==null)
				parent = getAttributesParent();
			
			if (label==null)
				label = PropertyUtil.getLabel(object, attribute);
			
			Hashtable<String,Object> choiceOfValues = PropertyUtil.getChoiceOfValues(object, attribute);
			
			Class eTypeClass = attribute.getEType().getInstanceClass();
			if (choiceOfValues != null) {
				ObjectEditor editor = new ComboObjectEditor(this,object,attribute);
				editor.createControl(parent,label);
			}
			else if (String.class.equals(eTypeClass)) {
				int style = SWT.NONE;
				if (PropertyUtil.getIsMultiLine(object,attribute))
					style |= SWT.MULTI;
				ObjectEditor editor = new TextObjectEditor(this,object,attribute);
				editor.createControl(parent,label,style);
			} else if (boolean.class.equals(eTypeClass)) {
				ObjectEditor editor = new BooleanObjectEditor(this,object,attribute);
				editor.createControl(parent,label);
			} else if (int.class.equals(eTypeClass) ||
					Integer.class.equals(eTypeClass) ||
					BigInteger.class.equals(eTypeClass) ) {
				ObjectEditor editor = new IntObjectEditor(this,object,attribute);
				editor.createControl(parent,label);
			} else if ("anyAttribute".equals(attribute.getName()) ||
					object.eGet(attribute) instanceof FeatureMap) {
				List<Entry> basicList = ((BasicFeatureMap) object.eGet(attribute)).basicList();
				for (Entry entry : basicList) {
					EStructuralFeature feature = entry.getEStructuralFeature();
					if (isAttribute(object, feature))
						bindAttribute(parent,object,(EAttribute)feature);
					else if (isReference(object, feature))
						bindReference(parent,object,(EReference)feature);
					else if (isList(object,feature))
						bindList(object,feature);
				}
			}
		}
	}
	
	protected void bindReference(Composite parent, EObject object, String name) {
		EStructuralFeature feature = getFeature(object,name);
		if (isReference(object,feature)) {
			bindReference(parent, object,(EReference)feature);
		}
	}

	protected void bindReference(EObject object, String name) {
		EStructuralFeature feature = getFeature(object,name);
		if (isReference(object,feature)) {
			bindReference(object,(EReference)feature);
		}
	}
	
	protected void bindReference(EObject object, EReference reference) {
		bindReference(null, object, reference);
	}
	
	protected void bindReference(Composite parent, EObject object, EReference reference) {
		if (modelEnablement.isEnabled(object.eClass(), reference)) {
			if (parent==null)
				parent = getAttributesParent();
			
			String displayName = PropertyUtil.getLabel(object, reference);

			if (getDiagramEditor().getPreferences().getExpandProperties()) {
				AbstractBpmn2PropertiesComposite composite = null;
				if (propertySection!=null) {
					composite = PropertiesCompositeFactory.createComposite(
						reference.getEReferenceType().getInstanceClass(), propertySection, true);
				}
				else {
					composite = PropertiesCompositeFactory.createComposite(
						reference.getEReferenceType().getInstanceClass(), parent, SWT.NONE, true);
				}
				
				if (composite!=null) {
					EObject value = (EObject)object.eGet(reference);
					if (value==null) {
						value = modelHandler.create((EClass)reference.getEType());
						InsertionAdapter.add(object, reference, value);
					}
					composite.setEObject(getDiagramEditor(), value);
					composite.setTitle( PropertyUtil.getLabel(object,reference) + " Details");
				}
				else {
					Label label = createLabel(
							parent,
							"Internal error: cannot display properties for "+
							displayName+
							" because the property page does not exist.");
					label.setLayoutData(new GridData(SWT.FILL,SWT.TOP,true,false,3,1));
				}
			}
			else
			{
				ObjectEditor editor;
				if (PropertyUtil.isMultiChoice(object, reference)) {
					if (reference.isMany()) {
						editor = new FeatureListObjectEditor(this,object,reference);
					} else {
						editor = new ComboObjectEditor(this,object,reference);
					}
				}
				else {
					editor = new TextObjectEditor(this,object,reference);
				}
				editor.createControl(parent,displayName);
			}
		}
	}
	
	protected void bindChild(final EObject object, String name) {
		final EStructuralFeature feature = ((EObject)object).eClass().getEStructuralFeature(name);
		if (feature instanceof EReference) {
			Object value = object.eGet(feature);
			if (value==null) {
				domain.getCommandStack().execute(new RecordingCommand(domain) {
					@Override
					protected void doExecute() {
						Object newValue = FACTORY.create(((EReference) feature).getEReferenceType());
						object.eSet(feature, newValue);
						ModelUtil.setID((EObject)newValue);
					}
				});
				value = object.eGet(feature);
			}
			if (value instanceof EObject) {
				objectStack.push((EObject)value);
				createBindings((EObject)value);
				objectStack.pop();
			}
		}
	}

	protected void bindList(EObject object, String name) {
		EStructuralFeature feature = getFeature(object,name);
		if (isList(object,feature)) {
			bindList(object,feature);
		}
	}
	
	protected void bindList(EObject object, EStructuralFeature feature) {
		bindList(object,feature,null);
	}
	
	protected void bindList(EObject object, EStructuralFeature feature, EClass listItemClass) {

		if (modelEnablement.isEnabled(object.eClass(), feature) || modelEnablement.isEnabled(listItemClass)) {

			AbstractBpmn2TableComposite tableComposite = null;
			if (propertySection!=null)
				tableComposite = new AbstractBpmn2TableComposite(propertySection, AbstractBpmn2TableComposite.DEFAULT_STYLE);
			else
				tableComposite = new AbstractBpmn2TableComposite(this, AbstractBpmn2TableComposite.DEFAULT_STYLE);
			tableComposite.setListItemClass(listItemClass);
			tableComposite.bindList(object, feature);
		}
	}
	
	public class ChildObjectStack {
		private Stack<EObject> objectStack = new Stack<EObject>();
		private Stack<Composite> attributesCompositeStack = new Stack<Composite>();
		private Stack<Section> attributesSectionStack = new Stack<Section>();
		
		public void push(EObject object) {
			attributesCompositeStack.push(AbstractBpmn2PropertiesComposite.this.getAttributesParent());
			attributesComposite = null;
			attributesSectionStack.push(attributesSection);
			attributesSection = null;
			objectStack.push(object);
		}
		
		public EObject pop() {
			if (objectStack.size()>0) {
				attributesComposite = attributesCompositeStack.pop();
				
				return objectStack.pop();
			}
			return null;
		}
		
		public EObject peek() {
			if (objectStack.size()>0) {
				return objectStack.peek();
			}
			return AbstractBpmn2PropertiesComposite.this.be;
		}
		
		public Composite getAttributesParent() {
			if (objectStack.size()>0) {
				return attributesCompositeStack.peek();
			}
			return AbstractBpmn2PropertiesComposite.this;
		}
		
		public EObject get(int i) {
			return objectStack.get(i);
		}
		
		public int size() {
			return objectStack.size();
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.transaction.ResourceSetListener#resourceSetChanged(org.eclipse.emf.transaction.ResourceSetChangeEvent)
	 */
	@Override
	public void resourceSetChanged(ResourceSetChangeEvent event) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.transaction.ResourceSetListener#getFilter()
	 */
	@Override
	public NotificationFilter getFilter() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.transaction.ResourceSetListener#transactionAboutToCommit(org.eclipse.emf.transaction.ResourceSetChangeEvent)
	 */
	@Override
	public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.transaction.ResourceSetListener#isAggregatePrecommitListener()
	 */
	@Override
	public boolean isAggregatePrecommitListener() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.transaction.ResourceSetListener#isPrecommitOnly()
	 */
	@Override
	public boolean isPrecommitOnly() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.transaction.ResourceSetListener#isPostcommitOnly()
	 */
	@Override
	public boolean isPostcommitOnly() {
		return false;
	}
}