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

import org.eclipse.bpmn2.ChoreographyActivity;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.FeatureSupport;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeService;

public class UpdateParticipantMultiplicityFeature extends AbstractUpdateFeature {

	public static final String MULTIPLICITY_MARKER = "multiplicity.marker";

	public UpdateParticipantMultiplicityFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		EObject container = context.getPictogramElement().eContainer();
		if (container instanceof PictogramElement) {
			PictogramElement containerElem = (PictogramElement) container;
			if (BusinessObjectUtil.containsElementOfType(containerElem, ChoreographyActivity.class)) {
				return false;
			}
		}
		return BusinessObjectUtil.containsElementOfType(context.getPictogramElement(), Participant.class)
				&& context.getPictogramElement() instanceof ContainerShape;
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		EObject container = context.getPictogramElement().eContainer();
		if (container instanceof PictogramElement) {
			PictogramElement containerElem = (PictogramElement) container;
			if (BusinessObjectUtil.containsElementOfType(containerElem, ChoreographyActivity.class)) {
				return Reason.createFalseReason();
			}
		}
		if (!(context.getPictogramElement() instanceof ContainerShape)) {
			return Reason.createFalseReason();
		}
		IPeService peService = Graphiti.getPeService();
		Participant participant = (Participant) BusinessObjectUtil.getFirstElementOfType(context.getPictogramElement(),
				Participant.class);
		ContainerShape containerShape = (ContainerShape) context.getPictogramElement();

		boolean multiplicityProperty = new Boolean(peService.getPropertyValue(containerShape,
				AddParticipantFeature.MULTIPLICITY));
		boolean hasMultiplicity = participant.getParticipantMultiplicity() != null;

		return multiplicityProperty != hasMultiplicity ? Reason.createTrueReason() : Reason.createFalseReason();
	}

	@Override
	public boolean update(IUpdateContext context) {
		IPeService peService = Graphiti.getPeService();
		IGaService gaService = Graphiti.getGaService();

		Participant participant = (Participant) BusinessObjectUtil.getFirstElementOfType(context.getPictogramElement(),
				Participant.class);
		ContainerShape containerShape = (ContainerShape) context.getPictogramElement();

		if (participant.getParticipantMultiplicity() != null) {
			Shape shape = peService.createShape(containerShape, false);
			peService.setPropertyValue(shape, MULTIPLICITY_MARKER, Boolean.toString(true));
			Rectangle invisibleRectangle = gaService.createInvisibleRectangle(shape);
			GraphicsAlgorithm parentGa = containerShape.getGraphicsAlgorithm();
			int x = (parentGa.getWidth() / 2) - 10;
			int y = parentGa.getHeight() - 20;
			gaService.setLocationAndSize(invisibleRectangle, x, y, 20, 20);

			Polyline line1 = gaService.createPolyline(invisibleRectangle, new int[] { 0, 0, 0, 20 });
			line1.setLineWidth(2);
			line1.setForeground(manageColor(StyleUtil.CLASS_FOREGROUND));
			Polyline line2 = gaService.createPolyline(invisibleRectangle, new int[] { 9, 0, 9, 20 });
			line2.setForeground(manageColor(StyleUtil.CLASS_FOREGROUND));
			line2.setLineWidth(2);
			Polyline line3 = gaService.createPolyline(invisibleRectangle, new int[] { 18, 0, 18, 20 });
			line3.setForeground(manageColor(StyleUtil.CLASS_FOREGROUND));
			line3.setLineWidth(2);
		} else {
			Shape shape = FeatureSupport.getShape(containerShape, MULTIPLICITY_MARKER, Boolean.toString(true));
			if (shape != null) {
				peService.deletePictogramElement(shape);
			}
		}

		peService.setPropertyValue(containerShape, AddParticipantFeature.MULTIPLICITY,
				participant.getParticipantMultiplicity() != null ? Boolean.toString(true) : Boolean.toString(false));
		return true;
	}
}