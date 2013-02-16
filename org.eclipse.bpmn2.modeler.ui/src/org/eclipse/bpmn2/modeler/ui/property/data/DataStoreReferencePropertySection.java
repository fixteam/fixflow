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

import org.eclipse.bpmn2.DataStoreReference;
import org.eclipse.bpmn2.DataState;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.ui.property.DefaultPropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.PropertiesCompositeFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Bob Brodt
 *
 */
public class DataStoreReferencePropertySection extends AbstractBpmn2PropertySection {
	static {
		PropertiesCompositeFactory.register(DataStoreReference.class, DataStoreReferencePropertiesComposite.class);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection#createSectionRoot()
	 */
	@Override
	protected AbstractBpmn2PropertiesComposite createSectionRoot() {
		return new DataStoreReferencePropertiesComposite(this);
	}

	@Override
	protected EObject getBusinessObjectForPictogramElement(PictogramElement pe) {
		EObject bo = super.getBusinessObjectForPictogramElement(pe);
		if (bo instanceof DataStoreReference) {
			return bo;
		}		
		return null;
	}
	
	public class DataStoreReferencePropertiesComposite extends DefaultPropertiesComposite {

		private AbstractPropertiesProvider dataStoreReferencePropertiesProvider;
		private AbstractPropertiesProvider dataStatePropertiesProvider;

		/**
		 * @param section
		 */
		public DataStoreReferencePropertiesComposite(AbstractBpmn2PropertySection section) {
			super(section);
		}

		public DataStoreReferencePropertiesComposite(Composite parent, int style) {
			super(parent, style);
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
			else if (object instanceof DataStoreReference) {
				if (dataStoreReferencePropertiesProvider == null) {
					dataStoreReferencePropertiesProvider = new AbstractPropertiesProvider(object) {
						String[] properties = new String[] { "id", "name" };
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
				return dataStoreReferencePropertiesProvider;
			}
			return null;
		}
		
	}

}
