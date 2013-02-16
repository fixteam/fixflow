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
package org.eclipse.bpmn2.modeler.ui.property.gateways;

import org.eclipse.bpmn2.ComplexGateway;
import org.eclipse.bpmn2.EventBasedGateway;
import org.eclipse.bpmn2.ExclusiveGateway;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.InclusiveGateway;
import org.eclipse.bpmn2.ParallelGateway;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.ui.property.PropertiesCompositeFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;

public class GatewayPropertySection extends AbstractBpmn2PropertySection implements ITabbedPropertyConstants {
	static {
		PropertiesCompositeFactory.register(Gateway.class, GatewayPropertiesComposite.class);
		PropertiesCompositeFactory.register(ComplexGateway.class, GatewayPropertiesComposite.class);
		PropertiesCompositeFactory.register(EventBasedGateway.class, GatewayPropertiesComposite.class);
		PropertiesCompositeFactory.register(ExclusiveGateway.class, GatewayPropertiesComposite.class);
		PropertiesCompositeFactory.register(InclusiveGateway.class, GatewayPropertiesComposite.class);
		PropertiesCompositeFactory.register(ParallelGateway.class, GatewayPropertiesComposite.class);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection#createSectionRoot()
	 */
	@Override
	protected AbstractBpmn2PropertiesComposite createSectionRoot() {
		return new GatewayPropertiesComposite(this);
	}

	@Override
	protected EObject getBusinessObjectForPictogramElement(PictogramElement pe) {
		EObject be = super.getBusinessObjectForPictogramElement(pe);
		if (be instanceof Gateway)
			return be;
		return null;
	}
}
