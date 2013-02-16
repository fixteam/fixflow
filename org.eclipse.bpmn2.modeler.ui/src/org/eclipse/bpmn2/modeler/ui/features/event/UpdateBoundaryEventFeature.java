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
package org.eclipse.bpmn2.modeler.ui.features.event;

import static org.eclipse.bpmn2.modeler.ui.features.event.BoundaryEventFeatureContainer.BOUNDARY_EVENT_CANCEL;

import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.modeler.core.features.event.AbstractUpdateEventFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.services.Graphiti;

public class UpdateBoundaryEventFeature extends AbstractUpdateEventFeature {

	public static String BOUNDARY_EVENT_MARKER = "marker.boundary.event";

	public UpdateBoundaryEventFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		if (super.updateNeeded(context).toBoolean())
			return Reason.createTrueReason();
		
		String cancelProperty = Graphiti.getPeService().getPropertyValue(context.getPictogramElement(),
		        BOUNDARY_EVENT_CANCEL);
		BoundaryEvent event = (BoundaryEvent) getBusinessObjectForPictogramElement(context.getPictogramElement());
		boolean changed = Boolean.parseBoolean(cancelProperty) != event.isCancelActivity();
		IReason reason = changed ? Reason.createTrueReason("Boundary description changed") : Reason.createFalseReason();
		return reason;
	}

	@Override
	public boolean update(IUpdateContext context) {
		super.update(context);
		
		BoundaryEvent event = (BoundaryEvent) getBusinessObjectForPictogramElement(context.getPictogramElement());

		Graphiti.getPeService().setPropertyValue(context.getPictogramElement(), BOUNDARY_EVENT_CANCEL,
		        Boolean.toString(event.isCancelActivity()));

		Ellipse ellipse = (Ellipse) context.getPictogramElement().getGraphicsAlgorithm();
		Ellipse innerEllipse = (Ellipse) ellipse.getGraphicsAlgorithmChildren().get(0);
		LineStyle lineStyle = event.isCancelActivity() ? LineStyle.SOLID : LineStyle.DASH;

		ellipse.setLineStyle(lineStyle);
		innerEllipse.setLineStyle(lineStyle);

		return true;
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		return getBusinessObjectForPictogramElement(context.getPictogramElement()) instanceof BoundaryEvent;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.core.features.activity.AbstractUpdateMarkerFeature#getPropertyKey()
	 */
	@Override
	protected String getPropertyKey() {
		return BOUNDARY_EVENT_MARKER;
	}
}