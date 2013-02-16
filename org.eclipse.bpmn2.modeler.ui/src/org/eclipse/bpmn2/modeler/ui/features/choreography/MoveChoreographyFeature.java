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
package org.eclipse.bpmn2.modeler.ui.features.choreography;

import java.util.Collection;

import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.modeler.core.features.MoveFlowNodeFeature;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.dd.dc.Bounds;
import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IPeService;

public class MoveChoreographyFeature extends MoveFlowNodeFeature {

	public MoveChoreographyFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	protected void postMoveShape(final IMoveShapeContext context) {
		super.postMoveShape(context);
		IPeService peService = Graphiti.getPeService();
		Collection<Shape> shapes = peService.getAllContainedShapes((ContainerShape) context.getShape());
		for (Shape s : shapes) {
			Participant participant = BusinessObjectUtil.getFirstElementOfType(s, Participant.class);
			if (participant != null) {
				ContainerShape container = (ContainerShape) s;
				GraphicsAlgorithm ga = container.getGraphicsAlgorithm();

				BPMNShape bpmnShape = BusinessObjectUtil.getFirstElementOfType(s, BPMNShape.class);
				ILocation loc = Graphiti.getLayoutService().getLocationRelativeToDiagram(context.getShape());

				Bounds bounds = bpmnShape.getBounds();
				bounds.setX(loc.getX() + ga.getX());
				bounds.setY(loc.getY() + ga.getY());
			}
		}
		ChoreographyUtil.moveChoreographyMessageLinks((ContainerShape) context.getPictogramElement());
	}
}