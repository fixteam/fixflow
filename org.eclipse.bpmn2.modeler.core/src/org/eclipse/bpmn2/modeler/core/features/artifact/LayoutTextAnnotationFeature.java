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
package org.eclipse.bpmn2.modeler.core.features.artifact;

import java.util.Iterator;

import org.eclipse.bpmn2.TextAnnotation;
import org.eclipse.bpmn2.modeler.core.di.DIUtils;
import org.eclipse.bpmn2.modeler.core.features.DefaultLayoutBPMNShapeFeature;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.MultiText;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;

public class LayoutTextAnnotationFeature extends DefaultLayoutBPMNShapeFeature {

	public LayoutTextAnnotationFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canLayout(ILayoutContext context) {
		PictogramElement pictoElem = context.getPictogramElement();
		if (!(pictoElem instanceof ContainerShape)) {
			return false;
		}
		Object bo = getBusinessObjectForPictogramElement(pictoElem);
		return bo instanceof TextAnnotation;
	}

	@Override
	public boolean layout(ILayoutContext context) {
		boolean changed = false;

		ContainerShape containerShape = (ContainerShape) context.getPictogramElement();
		GraphicsAlgorithm ga = containerShape.getGraphicsAlgorithm();
		IGaService gaService = Graphiti.getGaService();

		if (ga.getWidth() < 100) {
			ga.setWidth(100);
			changed = true;
		}

		if (ga.getHeight() < 50) {
			ga.setHeight(50);
			changed = true;
		}

		int containerWidth = ga.getWidth();
		int containerHeight = ga.getHeight();

		DIUtils.updateDIShape(containerShape);

		Iterator<Shape> iterator = containerShape.getChildren().iterator();
		while (iterator.hasNext()) {
			Shape shape = iterator.next();
			GraphicsAlgorithm shapeGa = shape.getGraphicsAlgorithm();
			IDimension size = gaService.calculateSize(shapeGa);
			if (containerWidth != size.getWidth() && shapeGa instanceof MultiText) {
				gaService.setWidth(shapeGa, containerWidth - 5);
				changed = true;
			}
			if (containerHeight != size.getHeight()) {
				if (shapeGa instanceof Polyline) {
					Polyline line = (Polyline) shapeGa;
					line.getPoints().set(2, getNewPoint(line, 2, containerHeight, gaService));
					line.getPoints().set(3, getNewPoint(line, 3, containerHeight, gaService));
					changed = true;
				} else if (shapeGa instanceof MultiText) {
					gaService.setHeight(shapeGa, containerHeight - 5);
					changed = true;
				}
			}
		}

		return changed;
	}

	private Point getNewPoint(Polyline line, int pointIndex, int height, IGaService gaService) {
		Point p = line.getPoints().get(pointIndex);
		return gaService.createPoint(p.getX(), height);
	}
}
