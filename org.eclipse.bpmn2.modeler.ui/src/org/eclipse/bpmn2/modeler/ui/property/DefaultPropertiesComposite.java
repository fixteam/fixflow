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
 * @author Innar Made
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.ui.property;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.impl.Bpmn2PackageImpl;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.widgets.Composite;

public class DefaultPropertiesComposite extends AbstractBpmn2PropertiesComposite {

	protected final String[] EMPTY_STRING_ARRAY = new String[] {};
	protected AbstractPropertiesProvider propertiesProvider = null;
	
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public DefaultPropertiesComposite(Composite parent, int style) {
		super(parent,style);
	}
	
	public DefaultPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}
	
	public void setPropertiesProvider(AbstractPropertiesProvider provider) {
		propertiesProvider = provider;
	}
	
	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
		if (propertiesProvider==null) {
			final EObject o = object;
			return new AbstractPropertiesProvider(object) {
				public String[] getProperties() {
					List<String> list = new ArrayList<String>();
					for (EStructuralFeature attribute : o.eClass().getEStructuralFeatures()) {
						list.add(attribute.getName());
					}
					// add the anyAttributes
					List<EStructuralFeature> anyAttributes = ModelUtil.getAnyAttributes(o);
					for (EStructuralFeature f : anyAttributes) {
						if (f instanceof EAttribute && !list.contains(f.getName()))
							list.add(f.getName());
					}
					String a[] = new String[list.size()];
					list.toArray(a);
					return a;
				}
			};
		}
		return propertiesProvider;
	}
	
	@Override
	public void createBindings(EObject be) {
		AbstractPropertiesProvider provider = getPropertiesProvider(be); 
		if (provider==null) {
			createMissingPropertiesLabel(be);
			return;
		}
		
		String[] properties = provider.getProperties();
		if (properties!=null) {
			getAttributesParent();
			EStructuralFeature feature;
			for (String a : properties) {
				EClass eItemClass = null;
				if (a.contains(".")) {
					String[] names = a.split("\\.");
					a = names[0];
					eItemClass = (EClass)Bpmn2PackageImpl.eINSTANCE.getEClassifier(names[1]);
					if (eItemClass==null)
						eItemClass = (EClass) getDiagramEditor().getTargetRuntime().getModelDescriptor().getEPackage().getEClassifier(names[1]);
					if (eItemClass!=null) {
						if (!modelEnablement.isEnabled(eItemClass))
							continue;
					}
				}
				feature = getFeature(be,a);
				if (feature!=null) {
					if (modelEnablement.isEnabled(be.eClass(),feature) || eItemClass!=null) {
						if (isAttribute(be,feature)) {
							bindAttribute(getAttributesParent(), be,(EAttribute)feature);
						}
						else if (isList(be,feature)) {
							if (eItemClass==null)
								bindList(be,feature);
							else
								bindList(be,feature, eItemClass);
						}
						else if (isReference(be,feature)) {
							bindReference(getAttributesParent(), be,(EReference)feature);
						}
					}
				}
			}
		}
		
		properties = provider.getChildren(null);
		if (properties!=null) {
			for (String a : properties) {
				bindChild(be,a);
			}
		}
		
		if (getAttributesParent().getChildren().length==0) {
			// yech! ugly hack to hide the Attributes TWISTIE section if it's empty
			attributesComposite.dispose();
			attributesComposite = null;
			attributesSection.dispose();
			attributesSection = null;
		}

		if (getChildren().length==0) {
			createMissingPropertiesLabel(be);
		}
	}
	
	private void createMissingPropertiesLabel(EObject be) {
		if (propertySection!=null) {
			String tab = propertySection.tabbedPropertySheetPage.getSelectedTab().getLabel();
//			createLabel(this,"No "+tab+" Properties for this "+PropertyUtil.getText(be));
		}
	}

	/**
	 * Provider class for the Default Properties sheet tab.
	 * This simply returns a list of properties, containment ELists and references
	 * to be rendered on the Default Properties tab. If the DefaultPropertiesComposite
	 * is subclassed and the client does not specify an item provider, the default
	 * behavior is to render all structural features for the business object.
	 */
	public abstract class AbstractPropertiesProvider {
		
		EObject be;
		
		public AbstractPropertiesProvider(EObject object) {
			be = object;
		}

		public abstract String[] getProperties();

		public String[] getChildren(String name) {
			return null;
		}
	}
}
