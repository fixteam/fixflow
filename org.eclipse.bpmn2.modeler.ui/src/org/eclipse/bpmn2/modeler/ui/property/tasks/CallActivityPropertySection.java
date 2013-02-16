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

package org.eclipse.bpmn2.modeler.ui.property.tasks;

import org.eclipse.bpmn2.CallActivity;
import org.eclipse.bpmn2.GlobalBusinessRuleTask;
import org.eclipse.bpmn2.GlobalManualTask;
import org.eclipse.bpmn2.GlobalScriptTask;
import org.eclipse.bpmn2.GlobalTask;
import org.eclipse.bpmn2.GlobalUserTask;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.ui.property.DefaultPropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.PropertiesCompositeFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;

/**
 * @author Bob Brodt
 *
 */
public class CallActivityPropertySection extends AbstractBpmn2PropertySection implements ITabbedPropertyConstants {
	static {
		PropertiesCompositeFactory.register(CallActivity.class, CallActivityPropertiesComposite.class);
		PropertiesCompositeFactory.register(GlobalTask.class, GlobalTaskPropertiesComposite.class);
		PropertiesCompositeFactory.register(GlobalBusinessRuleTask.class, GlobalTaskPropertiesComposite.class);
		PropertiesCompositeFactory.register(GlobalManualTask.class, GlobalTaskPropertiesComposite.class);
		PropertiesCompositeFactory.register(GlobalScriptTask.class, GlobalTaskPropertiesComposite.class);
		PropertiesCompositeFactory.register(GlobalUserTask.class, GlobalTaskPropertiesComposite.class);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection#createSectionRoot()
	 */
	@Override
	protected AbstractBpmn2PropertiesComposite createSectionRoot() {
		return new CallActivityPropertiesComposite(this);
	}

	@Override
	protected EObject getBusinessObjectForPictogramElement(PictogramElement pe) {
		EObject be = super.getBusinessObjectForPictogramElement(pe);
		if (be instanceof CallActivity)
			return be;
		return null;
	}
	
	public class GlobalTaskPropertiesComposite extends DefaultPropertiesComposite {

		public GlobalTaskPropertiesComposite(Composite parent, int style) {
			super(parent, style);
		}

		public GlobalTaskPropertiesComposite(AbstractBpmn2PropertySection section) {
			super(section);
		}

		@Override
		public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
			if (propertiesProvider==null) {
				propertiesProvider = new AbstractPropertiesProvider(object) {
					String[] properties = new String[] {
							"id", // BaseElement
							"name", // CallableElement
							"ioBinding", // CallableElement
							"ioSpecification",// CallableElement
							"supportedInterfaceRefs",// CallableElement
							"resources", // GlobalTask
							"implementation", // GlobalBusinessRuleTask & GlobalUserTask
							"script", // GlobalScriptTask
							"scriptLanguage", // GlobalScriptTask
							"renderings", // GlobalUserTask
					};
					
					@Override
					public String[] getProperties() {
						return properties; 
					}
				};
			}
			return propertiesProvider;
		}

	}
}
