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
package org.eclipse.bpmn2.modeler.core.features.conversation;

import org.eclipse.bpmn2.Conversation;
import org.eclipse.bpmn2.modeler.core.features.AbstractAddBPMNShapeFeature;
import org.eclipse.bpmn2.modeler.core.utils.AnchorUtil;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeService;

public class AddConversationFeature extends AbstractAddBPMNShapeFeature {

	public AddConversationFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		return context.getTargetContainer().equals(getDiagram());
	}

	@Override
	public PictogramElement add(IAddContext context) {
		IGaService gaService = Graphiti.getGaService();
		IPeService peService = Graphiti.getPeService();
		Conversation c = (Conversation) context.getNewObject();

		int w = this.getWidth(context);
		int h = this.getHeight(context);

		ContainerShape containerShape = peService.createContainerShape(context.getTargetContainer(), true);
		Rectangle rect = gaService.createInvisibleRectangle(containerShape);
		gaService.setLocationAndSize(rect, context.getX(), context.getY(), w, h);

		int w_5th = w / 5;
		int[] xy = { w_5th, 0, w_5th * 4, 0, w, h / 2, w_5th * 4, h, w_5th, h, 0, h / 2 };
		Polygon hexagon = gaService.createPolygon(rect, xy);

		StyleUtil.applyStyle(hexagon, c);

		peService.createChopboxAnchor(containerShape);
		AnchorUtil.addFixedPointAnchors(containerShape, rect);

		link(containerShape, c);
		createDIShape(containerShape, c);
		return containerShape;
	}

	@Override
	public int getHeight() {
		return 30;
	}

	@Override
	public int getWidth() {
		return 30;
	}
}