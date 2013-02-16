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

package org.eclipse.bpmn2.modeler.ui.property.data;

import org.eclipse.bpmn2.Assignment;
import org.eclipse.bpmn2.DataObject;
import org.eclipse.bpmn2.DataObjectReference;
import org.eclipse.bpmn2.DataState;
import org.eclipse.bpmn2.Expression;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.HumanPerformer;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.Performer;
import org.eclipse.bpmn2.PotentialOwner;
import org.eclipse.bpmn2.ResourceAssignmentExpression;
import org.eclipse.bpmn2.ResourceParameterBinding;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.ui.property.DefaultPropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.PropertiesCompositeFactory;
import org.eclipse.bpmn2.modeler.ui.property.DefaultPropertiesComposite.AbstractPropertiesProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Bob Brodt
 * From section 10.3.1 of the BPMN 2.0 specification:
 * "The Data Object class is an item-aware parameter. Data Object elements MUST be contained within Process or Sub-
 * Process elements. Data Object elements are visually displayed on a Process diagram. Data Object References are
 * a way to reuse Data Objects in the same diagram. They can specify different states of the same Data Object at
 * different points in a Process. Data Object Reference cannot specify item definitions, and Data Objects cannot
 * specify states. The names of Data Object References are derived by concatenating the name of the referenced Data
 * Data Object the state of the Data Object Reference in square brackets as follows: <Data Object Name> [ <Data
 * Object Reference State> ]."
 */
public class DataObjectPropertySection extends AbstractBpmn2PropertySection {

	static {
		PropertiesCompositeFactory.register(DataObject.class, DataObjectPropertiesComposite.class);
		PropertiesCompositeFactory.register(DataObjectReference.class, DataObjectPropertiesComposite.class);
		PropertiesCompositeFactory.register(Assignment.class, DataAssignmentPropertiesComposite.class);
		PropertiesCompositeFactory.register(Expression.class, ExpressionPropertiesComposite.class);
		PropertiesCompositeFactory.register(FormalExpression.class, ExpressionPropertiesComposite.class);
		PropertiesCompositeFactory.register(ResourceAssignmentExpression.class, ResourceAssignmentExpressionPropertiesComposite.class);
		PropertiesCompositeFactory.register(ResourceParameterBinding.class, ResourceParameterBindingPropertiesComposite.class);
		PropertiesCompositeFactory.register(PotentialOwner.class, ResourceRolePropertiesComposite.class);
		PropertiesCompositeFactory.register(HumanPerformer.class, ResourceRolePropertiesComposite.class);
		PropertiesCompositeFactory.register(Performer.class, ResourceRolePropertiesComposite.class);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection#createSectionRoot()
	 */
	@Override
	protected AbstractBpmn2PropertiesComposite createSectionRoot() {
		return new DataObjectPropertiesComposite(this);
	}

	@Override
	protected EObject getBusinessObjectForPictogramElement(PictogramElement pe) {
		EObject bo = super.getBusinessObjectForPictogramElement(pe);
		if (bo instanceof DataObject) {
			return bo;
		}
		if (bo instanceof DataObjectReference) {
			return ((DataObjectReference)bo).getDataObjectRef();
		}		

		return null;
	}
	
	public class DataObjectPropertiesComposite extends DefaultPropertiesComposite {

		private AbstractPropertiesProvider dataObjectReferencePropertiesProvider;
		private AbstractPropertiesProvider dataStatePropertiesProvider;

		public DataObjectPropertiesComposite(Composite parent, int style) {
			super(parent, style);
		}

		/**
		 * @param section
		 */
		public DataObjectPropertiesComposite(AbstractBpmn2PropertySection section) {
			super(section);
		}

		@Override
		public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
			if (object instanceof DataState) {
				if (dataStatePropertiesProvider == null) {
					dataStatePropertiesProvider = new AbstractPropertiesProvider(object) {
						String[] properties = new String[] { "id", "name" };
						
						@Override
						public String[] getProperties() {
							return properties; 
						}
					};
				}
				return dataStatePropertiesProvider;
			}
			else if (object instanceof DataObject) {
				if (propertiesProvider == null) {
					propertiesProvider = new AbstractPropertiesProvider(object) {
						String[] properties = new String[] { "id", "name", "isCollection", "itemSubjectRef" };
						String[] children = new String[] { "dataState" };
						
						@Override
						public String[] getProperties() {
							return properties;
						}
						
						@Override
						public String[] getChildren(String name) {
							return children;
						}
					};
				}
				return propertiesProvider;
			}
			else if (object instanceof DataObjectReference) {
				if (dataObjectReferencePropertiesProvider == null) {
					dataObjectReferencePropertiesProvider = new AbstractPropertiesProvider(object) {
						String[] properties = new String[] { "id", "name" };
						String[] children = new String[] { "dataObjectRef" , "dataState" };
	
						@Override
						public String[] getProperties() {
							return properties; 
						}
						
						@Override
						public String[] getChildren(String name) {
							return children;
						}
					};
			
				}
				return dataObjectReferencePropertiesProvider;
			}
			return null;
		}
	}
}
