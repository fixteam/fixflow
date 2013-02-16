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
package org.eclipse.bpmn2.modeler.core.features.participant;

import java.util.Iterator;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.modeler.core.di.DIUtils;
import org.eclipse.bpmn2.modeler.core.features.DefaultLayoutBPMNShapeFeature;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.FeatureSupport;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeService;

public class LayoutParticipantFeature extends DefaultLayoutBPMNShapeFeature {

	public LayoutParticipantFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canLayout(ILayoutContext context) {
		Object bo = BusinessObjectUtil.getFirstElementOfType(context.getPictogramElement(), BaseElement.class);
		return bo != null && bo instanceof Participant;
	}

	@Override
	public boolean layout(ILayoutContext context) {
		ContainerShape containerShape = (ContainerShape) context.getPictogramElement();

		GraphicsAlgorithm containerGa = containerShape.getGraphicsAlgorithm();
		IGaService gaService = Graphiti.getGaService();

		boolean horz = FeatureSupport.isHorizontal(containerShape);
		
		int containerHeight = containerGa.getHeight();
		int containerWidth = containerGa.getWidth();
		Iterator<Shape> iterator = containerShape.getChildren().iterator();
		while (iterator.hasNext()) {
			Shape shape = iterator.next();
			GraphicsAlgorithm ga = shape.getGraphicsAlgorithm();
			IDimension size = gaService.calculateSize(ga);
			
			if (ga instanceof Polyline) {
				Polyline line = (Polyline) ga;
				Point p0 = line.getPoints().get(0);
				Point p1 = line.getPoints().get(1);
				if (horz) {
					p0.setX(30); p0.setY(0);
					p1.setX(30); p1.setY(containerHeight);
				}
				else {
					p0.setX(0); p0.setY(30);
					p1.setX(containerWidth); p1.setY(30);
				}
			} else if (ga instanceof Text) {
				if (horz) {
					Text text = (Text)ga;
					text.setAngle(-90);
					gaService.setLocationAndSize(ga, 5, 0, 15, containerHeight);
				}
				else {
					Text text = (Text)ga;
					text.setAngle(0);
					gaService.setLocationAndSize(ga, 0, 5, containerWidth, 15);
				}
			}
		}

		Shape shape = FeatureSupport.getShape(containerShape, UpdateParticipantMultiplicityFeature.MULTIPLICITY_MARKER,
				Boolean.toString(true));
		if (shape != null) {
			GraphicsAlgorithm ga = shape.getGraphicsAlgorithm();
			int x = (containerGa.getWidth() / 2) - 10;
			int y = containerGa.getHeight() - 20;
			gaService.setLocation(ga, x, y);
		}

		DIUtils.updateDIShape(containerShape);
		return super.layout(context);
	}
}