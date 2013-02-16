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
package org.eclipse.bpmn2.modeler.ui.features.activity.subprocess;

import static org.eclipse.bpmn2.modeler.ui.features.activity.subprocess.SubProcessFeatureContainer.TRIGGERED_BY_EVENT;

import org.eclipse.bpmn2.SubProcess;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.services.Graphiti;

public class UpdateSubProcessFeature extends AbstractUpdateFeature {

	public UpdateSubProcessFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		Object bo = getBusinessObjectForPictogramElement(context.getPictogramElement());
		return bo != null && bo instanceof SubProcess;
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		Property triggerProperty = Graphiti.getPeService().getProperty(context.getPictogramElement(),
		        TRIGGERED_BY_EVENT);
		if (triggerProperty == null)
			return Reason.createFalseReason();
		SubProcess process = (SubProcess) getBusinessObjectForPictogramElement(context.getPictogramElement());
		boolean changed = Boolean.parseBoolean(triggerProperty.getValue()) != process.isTriggeredByEvent();
		IReason reason = changed ? Reason.createTrueReason("Trigger property changed") : Reason.createFalseReason();
		return reason;
	}

	@Override
	public boolean update(IUpdateContext context) {
		SubProcess process = (SubProcess) getBusinessObjectForPictogramElement(context.getPictogramElement());

		Graphiti.getPeService().setPropertyValue(context.getPictogramElement(), TRIGGERED_BY_EVENT,
		        Boolean.toString(process.isTriggeredByEvent()));

		RoundedRectangle rectangle = (RoundedRectangle) Graphiti.getPeService()
		        .getAllContainedPictogramElements(context.getPictogramElement()).iterator().next()
		        .getGraphicsAlgorithm();
		LineStyle lineStyle = process.isTriggeredByEvent() ? LineStyle.DOT : LineStyle.SOLID;
		rectangle.setLineStyle(lineStyle);

		return true;
	}
}