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
package org.eclipse.bpmn2.modeler.core.features.choreography;

import static org.eclipse.bpmn2.modeler.core.features.choreography.ChoreographyProperties.TEXT_H;

import org.eclipse.bpmn2.ChoreographyActivity;
import org.eclipse.bpmn2.modeler.core.features.DefaultLayoutBPMNShapeFeature;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeService;

public class LayoutChoreographyFeature extends DefaultLayoutBPMNShapeFeature {

	protected IPeService peService = Graphiti.getPeService();
	protected IGaService gaService = Graphiti.getGaService();

	public LayoutChoreographyFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canLayout(ILayoutContext context) {
		return BusinessObjectUtil.getFirstElementOfType(context.getPictogramElement(), ChoreographyActivity.class) != null;
	}

	@Override
	public boolean layout(ILayoutContext context) {
		ContainerShape choreographyContainer = (ContainerShape) context.getPictogramElement();
		GraphicsAlgorithm parentGa = choreographyContainer.getGraphicsAlgorithm();

		int newWidth = parentGa.getWidth();
		int newHeight = parentGa.getHeight();

		for (Shape s : peService.getAllContainedShapes(choreographyContainer)) {
			String property = peService.getPropertyValue(s, ChoreographyProperties.CHOREOGRAPHY_NAME);
			if (property != null && new Boolean(property)) {
				GraphicsAlgorithm ga = s.getGraphicsAlgorithm();
				setTextLocation(choreographyContainer, (Text) ga, newWidth, newHeight);
			}
			property = peService.getPropertyValue(s, ChoreographyProperties.CALL_CHOREO_BORDER);
			if (property != null && new Boolean(property)) {
				GraphicsAlgorithm ga = s.getGraphicsAlgorithm();
				gaService.setSize(ga, newWidth, newHeight);
				peService.sendToFront(s);
			}
			// use it when property editor supports enums
			// property = peService.getPropertyValue(s, ChoreographyProperties.CHOREOGRAPHY_MARKER_SHAPE);
			// if (property != null && new Boolean(property)) {
			// List<ContainerShape> bands = ChoreographyUtil.getParticipantBandContainerShapes(choreographyContainer);
			// List<ContainerShape> bottomBands = ChoreographyUtil.getTopAndBottomBands(bands).getSecond();
			// int x = (newWidth / 2) - (MARKER_H / 2);
			// int y = newHeight - MARKER_H;
			// if (!bottomBands.isEmpty()) {
			// ContainerShape b = bottomBands.get(0);
			// y = b.getGraphicsAlgorithm().getY() - MARKER_H;
			// }
			// gaService.setLocation(s.getGraphicsAlgorithm(), x, y);
			// }
		}

		return super.layout(context);
	}

	protected void setTextLocation(ContainerShape choreographyContainer, Text text, int w, int h) {
		int y = (h / 2) - (TEXT_H / 2);
		gaService.setLocationAndSize(text, 0, y, w, TEXT_H);
	}
}