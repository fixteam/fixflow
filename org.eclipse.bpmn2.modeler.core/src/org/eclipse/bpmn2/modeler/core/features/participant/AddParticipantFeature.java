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

import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.modeler.core.features.AbstractAddBPMNShapeFeature;
import org.eclipse.bpmn2.modeler.core.utils.AnchorUtil;
import org.eclipse.bpmn2.modeler.core.utils.FeatureSupport;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.services.IPeService;

public class AddParticipantFeature extends AbstractAddBPMNShapeFeature {

	public static final String MULTIPLICITY = "multiplicity";

	public AddParticipantFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		boolean isParticipant = context.getNewObject() instanceof Participant;
		boolean addToDiagram = context.getTargetContainer() instanceof Diagram;
		return isParticipant && addToDiagram;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		Participant participant = (Participant) context.getNewObject();
		IGaService gaService = Graphiti.getGaService();
		IPeService peService = Graphiti.getPeService();

		Diagram targetDiagram = (Diagram) context.getTargetContainer();
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		ContainerShape containerShape = peCreateService.createContainerShape(targetDiagram, true);

		int width = this.getWidth(context);
		int height = this.getHeight(context);

		Rectangle rect = gaService.createRectangle(containerShape);
		StyleUtil.applyStyle(rect, participant);
		gaService.setLocationAndSize(rect, context.getX(), context.getY(), width, height);

		BPMNShape bpmnShape = createDIShape(containerShape, participant);
		boolean horz = bpmnShape.isIsHorizontal();
		FeatureSupport.setHorizontal(containerShape, horz);

		Shape lineShape = peCreateService.createShape(containerShape, false);
		Polyline line;
		if (horz)
			line = gaService.createPolyline(lineShape, new int[] { 30, 0, 30, height });
		else
			line = gaService.createPolyline(lineShape, new int[] { 0, 30, width, 30 });
		StyleUtil.applyStyle(line, participant);

		Shape textShape = peCreateService.createShape(containerShape, false);
		Text text = gaService.createText(textShape, participant.getName());
		StyleUtil.applyStyle(text, participant);
		text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
		text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
		link(textShape, participant);

		peService.setPropertyValue(containerShape, MULTIPLICITY, Boolean.toString(participant.getParticipantMultiplicity()!=null));
		
		peCreateService.createChopboxAnchor(containerShape);
		AnchorUtil.addFixedPointAnchors(containerShape, rect);
		updatePictogramElement(containerShape);
		layoutPictogramElement(containerShape);
		return containerShape;
	}

	@Override
	public int getHeight() {
		return 100;
	}

	@Override
	public int getWidth() {
		return 600;
	}
}
