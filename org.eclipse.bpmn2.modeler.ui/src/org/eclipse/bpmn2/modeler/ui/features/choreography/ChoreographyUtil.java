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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.ChoreographyActivity;
import org.eclipse.bpmn2.ChoreographyLoopType;
import org.eclipse.bpmn2.ChoreographyTask;
import org.eclipse.bpmn2.InteractionNode;
import org.eclipse.bpmn2.Message;
import org.eclipse.bpmn2.MessageFlow;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.di.ParticipantBandKind;
import org.eclipse.bpmn2.modeler.core.di.DIUtils;
import org.eclipse.bpmn2.modeler.core.features.choreography.ChoreographyProperties;
import org.eclipse.bpmn2.modeler.core.utils.AnchorUtil;
import org.eclipse.bpmn2.modeler.core.utils.AnchorUtil.AnchorLocation;
import org.eclipse.bpmn2.modeler.core.utils.AnchorUtil.BoundaryAnchor;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil.Envelope;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.bpmn2.modeler.core.utils.Tuple;
import org.eclipse.bpmn2.modeler.ui.util.PropertyUtil;
import org.eclipse.dd.dc.Bounds;
import org.eclipse.dd.di.DiagramElement;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.PropertyContainer;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeService;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.graphiti.util.IColorConstant;

public class ChoreographyUtil implements ChoreographyProperties {

	private static IGaService gaService = Graphiti.getGaService();
	private static IPeService peService = Graphiti.getPeService();

	public static List<ContainerShape> getParticipantBandContainerShapes(
			ContainerShape choreographyActivityContainerShape) {
		List<ContainerShape> containers = new ArrayList<ContainerShape>();
		Collection<Shape> shapes = peService.getAllContainedShapes(choreographyActivityContainerShape);
		for (Shape s : shapes) {
			String property = peService.getPropertyValue(s, ChoreographyProperties.BAND);
			if (property != null && new Boolean(property)) {
				containers.add((ContainerShape) s);
			}
		}
		return containers;
	}

	public static List<BPMNShape> getParicipantBandBpmnShapes(ContainerShape choreographyActivityContainerShape) {
		List<BPMNShape> bpmnShapes = new ArrayList<BPMNShape>();
		List<ContainerShape> containers = getParticipantBandContainerShapes(choreographyActivityContainerShape);
		for (ContainerShape container : containers) {
			BPMNShape bpmnShape = BusinessObjectUtil.getFirstElementOfType(container, BPMNShape.class);
			bpmnShapes.add(bpmnShape);
		}
		return bpmnShapes;
	}

	public static boolean isChoreographyParticipantBand(PictogramElement element) {
		EObject container = element.eContainer();
		if (container instanceof PictogramElement) {
			PictogramElement containerElem = (PictogramElement) container;
			Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(containerElem);
			if (bo instanceof ChoreographyActivity) {
				return true;
			}
		}
		return false;
	}

	public static Tuple<List<ContainerShape>, List<ContainerShape>> getTopAndBottomBands(
			List<ContainerShape> participantBands) {
		List<ContainerShape> top = new ArrayList<ContainerShape>();
		List<ContainerShape> bottom = new ArrayList<ContainerShape>();

		if (participantBands.size() == 1) {
			BPMNShape bpmnShape = BusinessObjectUtil.getFirstElementOfType(participantBands.get(0), BPMNShape.class);
			ParticipantBandKind bandKind = bpmnShape.getParticipantBandKind();
			if (bandKind == ParticipantBandKind.TOP_INITIATING || bandKind == ParticipantBandKind.TOP_NON_INITIATING) {
				top.add(participantBands.get(0));
			} else if (bandKind == ParticipantBandKind.BOTTOM_INITIATING
					|| bandKind == ParticipantBandKind.BOTTOM_NON_INITIATING) {
				bottom.add(participantBands.get(0));
			} else {
				top.add(participantBands.get(0));
			}
		} else {
			Collections.sort(participantBands, getParticipantBandComparator());
			int n = participantBands.size();
			int divider = n / 2;
			top.addAll(participantBands.subList(0, divider));
			bottom.addAll(participantBands.subList(divider, n));
		}
		return new Tuple<List<ContainerShape>, List<ContainerShape>>(top, bottom);
	}

	private static Comparator<ContainerShape> getParticipantBandComparator() {
		return new Comparator<ContainerShape>() {

			@Override
			public int compare(ContainerShape c1, ContainerShape c2) {
				BPMNShape bpmnShape1 = BusinessObjectUtil.getFirstElementOfType(c1, BPMNShape.class);
				Bounds bounds1 = bpmnShape1.getBounds();
				BPMNShape bpmnShape2 = BusinessObjectUtil.getFirstElementOfType(c2, BPMNShape.class);
				Bounds bounds2 = bpmnShape2.getBounds();
				return new Float(bounds1.getY()).compareTo(new Float(bounds2.getY()));
			}

		};
	}

	public static void resizePartipantBandContainerShapes(int w, int h, List<ContainerShape> top,
			List<ContainerShape> bottom, Diagram diagram) {

		int y = 0;
		for (ContainerShape container : top) {
			BPMNShape bpmnShape = BusinessObjectUtil.getFirstElementOfType(container, BPMNShape.class);
			Bounds bounds = bpmnShape.getBounds();
			int hAcc = (int) bounds.getHeight();
			gaService.setLocationAndSize(container.getGraphicsAlgorithm(), 0, y, w, hAcc);
			y += hAcc;
			resizeParticipantBandChildren(container, w);
			DIUtils.updateDIShape(container);
			AnchorUtil.relocateFixPointAnchors(container, w, (int) bounds.getHeight());
			AnchorUtil.reConnect(bpmnShape, diagram);
		}

		Collections.reverse(bottom); // start from bottom towards center
		y = h;
		for (ContainerShape container : bottom) {
			BPMNShape bpmnShape = BusinessObjectUtil.getFirstElementOfType(container, BPMNShape.class);
			Bounds bounds = bpmnShape.getBounds();
			y -= bounds.getHeight();
			gaService.setLocationAndSize(container.getGraphicsAlgorithm(), 0, y, w, (int) bounds.getHeight());
			resizeParticipantBandChildren(container, w);
			DIUtils.updateDIShape(container);
			AnchorUtil.relocateFixPointAnchors(container, w, (int) bounds.getHeight());
			AnchorUtil.reConnect(bpmnShape, diagram);
		}
	}

	private static void resizeParticipantBandChildren(ContainerShape container, int w) {
		for (Shape s : container.getChildren()) {
			GraphicsAlgorithm ga = s.getGraphicsAlgorithm();
			if (ga instanceof Text) {
				gaService.setSize(ga, w, ga.getHeight());
			} else if (ga instanceof Rectangle) {
				gaService.setLocation(ga, (w / 2) - (ga.getWidth() / 2), ga.getY());
			}
		}
	}

	public static String getParticipantRefIds(ChoreographyActivity choreography) {
		if (choreography.getParticipantRefs() == null) {
			return new String();
		}
		Iterator<Participant> iterator = choreography.getParticipantRefs().iterator();
		String delim = ":";
		StringBuilder sb = new StringBuilder();
		while (iterator.hasNext()) {
			Participant participant = iterator.next();
			sb.append(participant.getId());
			if (iterator.hasNext()) {
				sb.append(delim);
			}
		}
		return sb.toString();
	}

	public static String getMessageRefIds(ChoreographyTask choreography) {
		if (choreography.getMessageFlowRef() == null) {
			return new String();
		}
		Iterator<MessageFlow> iterator = choreography.getMessageFlowRef().iterator();
		String delim = ":";
		StringBuilder sb = new StringBuilder();
		while (iterator.hasNext()) {
			MessageFlow message = iterator.next();
			sb.append(message.getId());
			if (iterator.hasNext()) {
				sb.append(delim);
			}
		}
		return sb.toString();
	}

	public static void updateParticipantReferences(ContainerShape choreographyContainer,
			List<ContainerShape> currentParticipantContainers, List<Participant> newParticipants, IFeatureProvider fp,
			boolean showNames) {

		Diagram diagram = peService.getDiagramForShape(choreographyContainer);
		ChoreographyActivity choreography = BusinessObjectUtil.getFirstElementOfType(choreographyContainer,
				ChoreographyActivity.class);

		BPMNDiagram dia = BusinessObjectUtil.getFirstElementOfType(diagram, BPMNDiagram.class);
		List<DiagramElement> diElements = dia.getPlane().getPlaneElement();
		for (int i = 0; i < currentParticipantContainers.size(); i++) {
			ContainerShape container = currentParticipantContainers.get(i);
			for (Connection c : peService.getOutgoingConnections(container)) {
				EObject parent = c.getEnd().eContainer();
				if (parent instanceof PictogramElement) {
					peService.deletePictogramElement((PictogramElement) parent);
				}
			}
			BPMNShape bpmnShape = BusinessObjectUtil.getFirstElementOfType(container, BPMNShape.class);
			diElements.remove(bpmnShape);
			peService.deletePictogramElement(container);
		}

		GraphicsAlgorithm ga = choreographyContainer.getGraphicsAlgorithm();
		IDimension size = gaService.calculateSize(ga);

		List<ContainerShape> newContainers = new ArrayList<ContainerShape>();
		int y = 0;
		boolean first = true;

		List<InteractionNode> sources = new ArrayList<InteractionNode>();
		if (choreography instanceof ChoreographyTask) {
			for (MessageFlow message : ((ChoreographyTask) choreography).getMessageFlowRef()) {
				sources.add(message.getSourceRef());
			}
		}

		Iterator<Participant> iterator = newParticipants.iterator();
		while (iterator.hasNext()) {
			Participant participant = iterator.next();

			ContainerShape bandShape = peService.createContainerShape(choreographyContainer, true);

			ParticipantBandKind bandKind = getNewParticipantBandKind(choreography, participant, first,
					!iterator.hasNext());

			boolean multiple = participant.getParticipantMultiplicity() != null
					&& participant.getParticipantMultiplicity().getMaximum() > 1;

			int w = size.getWidth();
			int h = multiple ? 40 : 20;

			BPMNShape bpmnShape = DIUtils.createDIShape(bandShape, participant, 0, y + h, w, h, fp, diagram);
			bpmnShape.setChoreographyActivityShape(BusinessObjectUtil.getFirstElementOfType(choreographyContainer,
					BPMNShape.class));
			bpmnShape.setIsMarkerVisible(multiple);
			bpmnShape.setParticipantBandKind(bandKind);
			bpmnShape.setIsMessageVisible(sources.contains(participant));
			createParticipantBandContainerShape(bandKind, choreographyContainer, bandShape, bpmnShape, showNames);
			if (multiple) {
				drawMultiplicityMarkers(bandShape);
			}
			newContainers.add(bandShape);

			y += h;
			first = false;
		}

		Tuple<List<ContainerShape>, List<ContainerShape>> topAndBottom = getTopAndBottomBands(newContainers);
		resizePartipantBandContainerShapes(size.getWidth(), size.getHeight(), topAndBottom.getFirst(),
				topAndBottom.getSecond(), diagram);
	}

	private static ContainerShape createTopShape(ContainerShape parent, ContainerShape bandShape, BPMNShape bpmnShape,
			boolean initiating, boolean showNames) {

		if (bandShape == null) {
			bandShape = peService.createContainerShape(parent, true);
		}

		Bounds bounds = bpmnShape.getBounds();
		int w = (int) bounds.getWidth();
		int h = (int) bounds.getHeight();

		Diagram diagram = peService.getDiagramForPictogramElement(parent);
		RoundedRectangle band = gaService.createRoundedRectangle(bandShape, R, R);
		band.setForeground(gaService.manageColor(diagram, StyleUtil.CLASS_FOREGROUND));
		band.setBackground(initiating ? gaService.manageColor(diagram, IColorConstant.WHITE) : gaService.manageColor(
				diagram, IColorConstant.LIGHT_GRAY));
		gaService.setLocationAndSize(band, 0, 0, w, h);

		Participant p = (Participant) bpmnShape.getBpmnElement();
		if (showNames) {
			addBandLabel(bandShape, p.getName(), w, h);
		}
		Graphiti.getPeCreateService().createChopboxAnchor(bandShape);
		AnchorUtil.addFixedPointAnchors(bandShape, band);
		peService.setPropertyValue(bandShape, ChoreographyProperties.BAND, Boolean.toString(true));
		peService.setPropertyValue(bandShape, ChoreographyProperties.MESSAGE_VISIBLE,
				Boolean.toString(bpmnShape.isIsMessageVisible()));
		return bandShape;
	}

	private static ContainerShape createBottomShape(ContainerShape parent, ContainerShape bandShape,
			BPMNShape bpmnShape, boolean initiating, boolean showNames) {

		if (bandShape == null) {
			bandShape = peService.createContainerShape(parent, true);
		}

		Bounds bounds = bpmnShape.getBounds();
		int w = (int) bounds.getWidth();
		int h = (int) bounds.getHeight();

		ILocation parentLoc = peService.getLocationRelativeToDiagram(parent);
		int y = (int) bounds.getY() - parentLoc.getY();

		Diagram diagram = peService.getDiagramForPictogramElement(parent);
		RoundedRectangle band = gaService.createRoundedRectangle(bandShape, R, R);
		band.setForeground(gaService.manageColor(diagram, StyleUtil.CLASS_FOREGROUND));
		band.setBackground(initiating ? gaService.manageColor(diagram, IColorConstant.WHITE) : gaService.manageColor(
				diagram, IColorConstant.LIGHT_GRAY));
		gaService.setLocationAndSize(band, 0, y, w, h);

		Participant p = (Participant) bpmnShape.getBpmnElement();
		if (showNames) {
			addBandLabel(bandShape, p.getName(), w, h);
		}
		Graphiti.getPeCreateService().createChopboxAnchor(bandShape);
		AnchorUtil.addFixedPointAnchors(bandShape, band);
		peService.setPropertyValue(bandShape, ChoreographyProperties.BAND, Boolean.toString(true));
		peService.setPropertyValue(bandShape, ChoreographyProperties.MESSAGE_VISIBLE,
				Boolean.toString(bpmnShape.isIsMessageVisible()));
		return bandShape;
	}

	private static ContainerShape createMiddleShape(ContainerShape parent, ContainerShape bandShape,
			BPMNShape bpmnShape, boolean initiating, boolean showNames) {

		if (bandShape == null) {
			bandShape = peService.createContainerShape(parent, true);
		}

		Bounds bounds = bpmnShape.getBounds();
		int w = (int) bounds.getWidth();
		int h = (int) bounds.getHeight();

		ILocation parentLoc = peService.getLocationRelativeToDiagram(parent);
		int y = (int) bounds.getY() - parentLoc.getY();

		Diagram diagram = peService.getDiagramForPictogramElement(parent);
		Rectangle band = gaService.createRectangle(bandShape);
		band.setForeground(gaService.manageColor(diagram, StyleUtil.CLASS_FOREGROUND));
		band.setBackground(initiating ? gaService.manageColor(diagram, IColorConstant.WHITE) : gaService.manageColor(
				diagram, IColorConstant.LIGHT_GRAY));
		gaService.setLocationAndSize(band, 0, y, w, h);

		Participant p = (Participant) bpmnShape.getBpmnElement();
		if (showNames) {
			addBandLabel(bandShape, p.getName(), w, h);
		}
		Graphiti.getPeCreateService().createChopboxAnchor(bandShape);
		AnchorUtil.addFixedPointAnchors(bandShape, band);
		peService.setPropertyValue(bandShape, ChoreographyProperties.BAND, Boolean.toString(true));
		peService.setPropertyValue(bandShape, ChoreographyProperties.MESSAGE_VISIBLE,
				Boolean.toString(bpmnShape.isIsMessageVisible()));
		return bandShape;
	}

	private static void addBandLabel(ContainerShape container, String name, int w, int h) {
		Diagram diagram = peService.getDiagramForShape(container);
		Shape labelShape = peService.createShape(container, false);
		Text label = gaService.createDefaultText(diagram, labelShape);
		BaseElement be = BusinessObjectUtil.getFirstBaseElement(container);
		label.setValue(name);
		gaService.setLocationAndSize(label, 0, 0, w, h);
		StyleUtil.applyStyle(label, be);
		label.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
		label.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
	}

	private static ParticipantBandKind getNewParticipantBandKind(ChoreographyActivity choreography,
			Participant participant, boolean first, boolean last) {
		boolean initiating = choreography.getInitiatingParticipantRef() != null
				&& choreography.getInitiatingParticipantRef().equals(participant);
		if (first) {
			return initiating ? ParticipantBandKind.TOP_INITIATING : ParticipantBandKind.TOP_NON_INITIATING;
		} else if (last) {
			return initiating ? ParticipantBandKind.BOTTOM_INITIATING : ParticipantBandKind.BOTTOM_NON_INITIATING;
		} else {
			return initiating ? ParticipantBandKind.MIDDLE_INITIATING : ParticipantBandKind.MIDDLE_NON_INITIATING;
		}
	}

	private static ContainerShape drawMessageLink(String name, BoundaryAnchor boundaryAnchor, int x, int y, boolean filled) {
		Diagram diagram = peService.getDiagramForAnchor(boundaryAnchor.anchor);

		FreeFormConnection connection = peService.createFreeFormConnection(diagram);
		Polyline connectionLine = gaService.createPolyline(connection);
		connectionLine.setForeground(gaService.manageColor(diagram, StyleUtil.CLASS_FOREGROUND));
		connectionLine.setLineStyle(LineStyle.DOT);
		connectionLine.setLineWidth(2);

		ContainerShape envelope = peService.createContainerShape(diagram, true);
		Rectangle invisibleRectangle = gaService.createInvisibleRectangle(envelope);
		gaService.setLocation(invisibleRectangle, x, y);
		gaService.setSize(invisibleRectangle, ENV_W + 50, ENV_H);

		Shape envelopeShape = peService.createShape(envelope, false);
		Envelope envelopeGa = GraphicsUtil.createEnvelope(envelopeShape, 0, 0, ENV_W, ENV_H);
		IColorConstant color = filled ? IColorConstant.LIGHT_GRAY : IColorConstant.WHITE;
		envelopeGa.rect.setFilled(true);
		envelopeGa.rect.setBackground(gaService.manageColor(diagram, color));
		envelopeGa.rect.setForeground(gaService.manageColor(diagram, StyleUtil.CLASS_FOREGROUND));
		envelopeGa.line.setForeground(gaService.manageColor(diagram, StyleUtil.CLASS_FOREGROUND));
		AnchorUtil.addFixedPointAnchors(envelope, envelopeGa.rect);

		Shape textShape = peService.createShape(envelope, false);
		Text text = gaService.createDefaultText(diagram, textShape);
		IDimension size = GraphitiUi.getUiLayoutService().calculateTextSize(name, text.getFont());
		gaService.setLocationAndSize(text, ENV_W + 3, 3, size.getWidth(), size.getHeight());
		text.setValue(name);

		gaService.setSize(invisibleRectangle, ENV_W + size.getWidth() + 3, ENV_H);

		AnchorLocation envelopeAnchorLoc = null;
		if (boundaryAnchor.locationType == AnchorLocation.TOP) {
			envelopeAnchorLoc = AnchorLocation.BOTTOM;
		} else {
			envelopeAnchorLoc = AnchorLocation.TOP;
		}

		connection.setStart(boundaryAnchor.anchor);
		connection.setEnd(AnchorUtil.getBoundaryAnchors(envelope).get(envelopeAnchorLoc).anchor);
		peService.setPropertyValue(envelope, MESSAGE_LINK, Boolean.toString(true));
		return envelope;
	}

	public static void drawMultiplicityMarkers(ContainerShape container) {
		Diagram diagram = peService.getDiagramForPictogramElement(container);
		Shape multiplicityShape = peService.createShape(container, false);
		Rectangle rect = gaService.createInvisibleRectangle(multiplicityShape);

		IDimension size = gaService.calculateSize(container.getGraphicsAlgorithm());
		int w = 10;
		int h = 10;
		int x = (size.getWidth() / 2) - (w / 2);
		int y = size.getHeight() - h - 1;
		gaService.setLocationAndSize(rect, x, y, w, h);

		int[][] coorinates = { new int[] { 0, 0, 0, h }, new int[] { 4, 0, 4, h }, new int[] { 8, 0, 8, h } };
		for (int[] xy : coorinates) {
			Polyline line = gaService.createPolyline(rect, xy);
			line.setLineWidth(2);
			line.setForeground(gaService.manageColor(diagram, StyleUtil.CLASS_FOREGROUND));
		}
	}

	public static ContainerShape createParticipantBandContainerShape(ParticipantBandKind bandKind,
			ContainerShape container, ContainerShape bandContainer, BPMNShape bpmnShape, boolean showNames) {

		switch (bandKind) {
		case TOP_INITIATING:
			return createTopShape(container, bandContainer, bpmnShape, true, showNames);
		case TOP_NON_INITIATING:
			return createTopShape(container, bandContainer, bpmnShape, false, showNames);
		case MIDDLE_INITIATING:
			return createMiddleShape(container, bandContainer, bpmnShape, true, showNames);
		case MIDDLE_NON_INITIATING:
			return createMiddleShape(container, bandContainer, bpmnShape, false, showNames);
		case BOTTOM_INITIATING:
			return createBottomShape(container, bandContainer, bpmnShape, true, showNames);
		case BOTTOM_NON_INITIATING:
			return createBottomShape(container, bandContainer, bpmnShape, false, showNames);
		}

		return bandContainer;
	}

	public static ContainerShape createParticipantBandContainerShape(ParticipantBandKind bandKind,
			ContainerShape container, BPMNShape bpmnShape, boolean showNames) {
		return createParticipantBandContainerShape(bandKind, container, null, bpmnShape, showNames);
	}

	public static void drawMessageLinks(IFeatureProvider fp, ContainerShape choreographyContainer) {

		List<MessageFlow> messageFlows = new ArrayList<MessageFlow>();
		ChoreographyTask choreography = BusinessObjectUtil.getFirstElementOfType(choreographyContainer,
				ChoreographyTask.class);
		if (choreography != null) {
			messageFlows.addAll(choreography.getMessageFlowRef());
		}

		List<ContainerShape> bandContainers = getParticipantBandContainerShapes(choreographyContainer);
		Tuple<List<ContainerShape>, List<ContainerShape>> topAndBottom = getTopAndBottomBands(bandContainers);
		List<ContainerShape> shapesWithVisibleMessages = new ArrayList<ContainerShape>();

		Map<AnchorLocation, BoundaryAnchor> boundaryAnchors = AnchorUtil.getBoundaryAnchors(choreographyContainer);
		BoundaryAnchor topBoundaryAnchor = boundaryAnchors.get(AnchorLocation.TOP);
		BoundaryAnchor bottomBoundaryAnchor = boundaryAnchors.get(AnchorLocation.BOTTOM);
		int topConnectionIndex = 0;
		int bottomConnectionIndex = 0;

		boolean hasTopMessage = false;
		EList<Connection> topConnections = topBoundaryAnchor.anchor.getOutgoingConnections();
		for (int i = 0; i < topConnections.size(); i++) {
			Connection connection = topConnections.get(i);
			EObject container = connection.getEnd().eContainer();
			if (container instanceof PropertyContainer) {
				String property = peService.getPropertyValue((PropertyContainer) container, MESSAGE_LINK);
				if (Boolean.parseBoolean(property)) {
					topConnectionIndex = i;
					hasTopMessage = true;
					break;
				}
			}
		}

		boolean hasBottomMessage = false;
		EList<Connection> bottomConnections = bottomBoundaryAnchor.anchor.getOutgoingConnections();
		for (int i = 0; i < bottomConnections.size(); i++) {
			Connection connection = bottomConnections.get(i);
			EObject container = connection.getEnd().eContainer();
			if (container instanceof PropertyContainer) {
				String property = peService.getPropertyValue((PropertyContainer) container, MESSAGE_LINK);
				if (Boolean.parseBoolean(property)) {
					bottomConnectionIndex = i;
					hasBottomMessage = true;
					break;
				}
			}
		}

		Iterator<ContainerShape> iterator = bandContainers.iterator();
		while (iterator.hasNext()) {
			ContainerShape bandContainer = iterator.next();
			BPMNShape bpmnShape = BusinessObjectUtil.getFirstElementOfType(bandContainer, BPMNShape.class);
			if (bpmnShape.isIsMessageVisible()) {
				shapesWithVisibleMessages.add(bandContainer);
			}
		}

		boolean shouldDrawTopMessage = !Collections.disjoint(topAndBottom.getFirst(), shapesWithVisibleMessages);
		boolean shouldDrawBottomMessage = !Collections.disjoint(topAndBottom.getSecond(), shapesWithVisibleMessages);
		ContainerShape envelope;
		
		String topMessageName = null;
		String bottomMessageName = null;
		Message topMessage = null;
		Message bottomMessage = null;

		if (shouldDrawTopMessage) {
			topMessage = getMessage(messageFlows, topAndBottom.getFirst(), false);
			topMessageName = getMessageName(messageFlows, topAndBottom.getFirst());
		}
		if (topMessageName == null) {
			topMessageName = new String();
		}

		if (shouldDrawBottomMessage) {
			bottomMessage = getMessage(messageFlows, topAndBottom.getSecond(), false);
			bottomMessageName = getMessageName(messageFlows, topAndBottom.getSecond());
		}
		if (bottomMessageName == null) {
			bottomMessageName = new String();
		}

		BPMNShape bpmnShape = BusinessObjectUtil.getFirstElementOfType(choreographyContainer, BPMNShape.class);
		Bounds bounds = bpmnShape.getBounds();
		int x = (int) ((bounds.getX() + bounds.getWidth() / 2) - (ENV_W / 2));

		MessageFlow flow = getMessageFlow(messageFlows, topAndBottom.getFirst());
		envelope = null;
		if (!hasTopMessage && shouldDrawTopMessage && flow!=null) {
			int y = (int) (bounds.getY() - ENVELOPE_HEIGHT_MODIFIER - ENV_H);
			envelope = drawMessageLink(topMessageName, topBoundaryAnchor, x, y, isFilled(topAndBottom.getFirst()));
			if (topMessage!=null)
				fp.link(envelope, topMessage);
			peService.setPropertyValue(envelope, MESSAGE_NAME, topMessageName);
		} else if (hasTopMessage && !shouldDrawTopMessage) {
			envelope = (ContainerShape) topConnections.get(topConnectionIndex).getEnd().eContainer();
			peService.deletePictogramElement(topConnections.get(topConnectionIndex));
			peService.deletePictogramElement(envelope);
			envelope = null;
		} else if (hasTopMessage && shouldDrawTopMessage && flow!=null) {
			envelope = (ContainerShape) topConnections.get(topConnectionIndex).getEnd().eContainer();
			setMessageLabel(topMessageName, envelope);
		}
		if (envelope!=null) {
			// link up the message flow
			linkMessageFlow(fp, flow, envelope);
		}

		envelope = null;
		flow = getMessageFlow(messageFlows, topAndBottom.getSecond());
		if (!hasBottomMessage && shouldDrawBottomMessage && flow!=null) {
			int y = (int) (bounds.getY() + bounds.getHeight() + ENVELOPE_HEIGHT_MODIFIER);
			envelope = drawMessageLink(bottomMessageName, bottomBoundaryAnchor, x, y, isFilled(topAndBottom.getSecond()));
			if (bottomMessage!=null)
				fp.link(envelope, bottomMessage);
			peService.setPropertyValue(envelope, MESSAGE_NAME, bottomMessageName);
		} else if (hasBottomMessage && !shouldDrawBottomMessage) {
			envelope = (ContainerShape) bottomConnections.get(bottomConnectionIndex).getEnd()
					.eContainer();
			peService.deletePictogramElement(bottomConnections.get(bottomConnectionIndex));
			peService.deletePictogramElement(envelope);
			envelope = null;
		} else if (hasBottomMessage && shouldDrawBottomMessage && flow!=null) {
			envelope = (ContainerShape) bottomConnections.get(bottomConnectionIndex).getEnd()
					.eContainer();
			setMessageLabel(bottomMessageName, envelope);
		}
		if (envelope!=null) {
			// link up the message flow
			linkMessageFlow(fp, flow, envelope);
		}
		
		return;
	}

	private static void linkMessageFlow(IFeatureProvider fp, MessageFlow flow,ContainerShape envelope) {
		for (Anchor a : envelope.getAnchors()) {
			for (Connection c : a.getIncomingConnections()) {
				fp.link(c, flow);
			}
			for (Connection c : a.getOutgoingConnections()) {
				fp.link(c, flow);
			}
		}
	}
	
	private static boolean isFilled(List<ContainerShape> bands) {
		boolean filled = true;
		for (ContainerShape band : bands) {
			BPMNShape bpmnShape = BusinessObjectUtil.getFirstElementOfType(band, BPMNShape.class);
			if (!bpmnShape.isIsMessageVisible()) {
				continue;
			}
			ParticipantBandKind bandKind = bpmnShape.getParticipantBandKind();
			if (bandKind == ParticipantBandKind.TOP_INITIATING || bandKind == ParticipantBandKind.BOTTOM_INITIATING
					|| bandKind == ParticipantBandKind.MIDDLE_INITIATING) {
				filled = false;
				break;
			}
		}
		return filled;
	}

	private static void setMessageLabel(String label, PictogramElement message) {
		ContainerShape containerShape = (ContainerShape) message;
		Iterator<Shape> iterator = peService.getAllContainedShapes(containerShape).iterator();
		while (iterator.hasNext()) {
			Shape shape = iterator.next();
			if (shape.getGraphicsAlgorithm() instanceof Text) {
				Text text = (Text) shape.getGraphicsAlgorithm();
				text.setValue(label);
				IDimension size = GraphitiUi.getUiLayoutService().calculateTextSize(label, text.getFont());
				gaService.setSize(containerShape.getGraphicsAlgorithm(), ENV_W + size.getWidth() + 3, ENV_H);
				gaService.setSize(text, size.getWidth(), size.getHeight());
				peService.setPropertyValue(containerShape, MESSAGE_NAME, label);
				break;
			}
		}
	}

	private static String getMessageName(List<MessageFlow> messageFlows, List<ContainerShape> bands) {
		for (ContainerShape band : bands) {
			Participant participant = BusinessObjectUtil.getFirstElementOfType(band, Participant.class);
			BPMNShape bpmnShape = BusinessObjectUtil.getFirstElementOfType(band, BPMNShape.class);
			if (bpmnShape.isIsMessageVisible()) {
				for (MessageFlow flow : messageFlows) {
					if (flow.getSourceRef().equals(participant)) {
						return getMessageFlowName(flow);
					}
				}
			}
		}
		return null;
	}

	private static MessageFlow getMessageFlow(List<MessageFlow> messageFlows, List<ContainerShape> bands) {
		for (ContainerShape band : bands) {
			Participant participant = BusinessObjectUtil.getFirstElementOfType(band, Participant.class);
			BPMNShape bpmnShape = BusinessObjectUtil.getFirstElementOfType(band, BPMNShape.class);
			if (bpmnShape.isIsMessageVisible()) {
				for (MessageFlow flow : messageFlows) {
					if (flow.getSourceRef().equals(participant)) {
						return flow;
					}
				}
			}
		}
		return null;
	}

	private static Message getMessage(List<MessageFlow> messageFlows, List<ContainerShape> bands, boolean create) {
		MessageFlow flow = getMessageFlow(messageFlows, bands);
		if (flow!=null) {
			if (flow.getMessageRef()==null && create) {
				Message msg = Bpmn2Factory.eINSTANCE.createMessage();
				ModelUtil.setID(msg, flow.eResource());
				msg.setName("Undefined Message");
				ModelUtil.getDefinitions(flow).getRootElements().add(msg);
				flow.setMessageRef(msg);
			}
			return flow.getMessageRef();
		}
		return null;
	}
	
	public static String getMessageFlowName(MessageFlow flow) {
		if (flow.getMessageRef() == null) {
			return flow.getName();
		} else if (flow.getMessageRef().getItemRef()==null ||
				flow.getMessageRef().getItemRef().getStructureRef()==null) {
			return flow.getMessageRef().getName();
		} else {
			String messageName = flow.getMessageRef().getName();
			String itemDefinitionName = PropertyUtil.getText(flow.getMessageRef().getItemRef());
			String text = itemDefinitionName;
			if (messageName!=null && !messageName.isEmpty())
				text += "/" + messageName;
			return text;
		}
	}
	
	public static String getMessageName(Message mesg) {
		if (mesg.getItemRef()==null ||
				mesg.getItemRef().getStructureRef()==null) {
			return mesg.getName();
		} else {
			return PropertyUtil.getText(mesg.getItemRef());
		}
	}

	public static void moveChoreographyMessageLinks(ContainerShape choreographyContainer) {
		BPMNShape bpmnShape = BusinessObjectUtil.getFirstElementOfType(choreographyContainer, BPMNShape.class);
		Bounds bounds = bpmnShape.getBounds();
		int x = (int) ((bounds.getX() + bounds.getWidth() / 2) - (ENV_W / 2));

		Map<AnchorLocation, BoundaryAnchor> boundaryAnchors = AnchorUtil.getBoundaryAnchors(choreographyContainer);
		BoundaryAnchor topBoundaryAnchor = boundaryAnchors.get(AnchorLocation.TOP);
		BoundaryAnchor bottomBoundaryAnchor = boundaryAnchors.get(AnchorLocation.BOTTOM);

		for (Connection connection : topBoundaryAnchor.anchor.getOutgoingConnections()) {
			EObject container = connection.getEnd().eContainer();
			if (container instanceof PropertyContainer) {
				String property = peService.getPropertyValue((PropertyContainer) container, MESSAGE_LINK);
				if (property != null && new Boolean(property)) {
					int y = (int) (bounds.getY() - ENVELOPE_HEIGHT_MODIFIER - ENV_H);
					gaService.setLocation(((ContainerShape) container).getGraphicsAlgorithm(), x, y);
					break;
				}
			}
		}

		for (Connection connection : bottomBoundaryAnchor.anchor.getOutgoingConnections()) {
			EObject container = connection.getEnd().eContainer();
			if (container instanceof PropertyContainer) {
				String property = peService.getPropertyValue((PropertyContainer) container, MESSAGE_LINK);
				if (property != null && new Boolean(property)) {
					int y = (int) (bounds.getY() + bounds.getHeight() + ENVELOPE_HEIGHT_MODIFIER);
					gaService.setLocation(((ContainerShape) container).getGraphicsAlgorithm(), x, y);
					break;
				}
			}
		}
	}

	public static void drawChoreographyLoopType(ContainerShape markerShape, ChoreographyLoopType type) {
		Collection<Shape> shapes = peService.getAllContainedShapes(markerShape);
		Shape drawingShape = null;

		if (shapes.size() == 1) { // remove previous shape
			Iterator<Shape> iterator = shapes.iterator();
			while (iterator.hasNext()) {
				Shape shape = iterator.next();
				drawingShape = shape;
				break;
			}
		}

		if (drawingShape != null) {
			peService.deletePictogramElement(drawingShape);
		}

		drawingShape = peService.createShape(markerShape, false);
		Rectangle rectangle = gaService.createInvisibleRectangle(drawingShape);
		gaService.setLocationAndSize(rectangle, 0, 0, MARKER_H, MARKER_H);

		if (type == null || type == ChoreographyLoopType.NONE) {
			return;
		}

		switch (type) {
		case STANDARD:
			// TODO implement when property editors supports enums
			break;
		case MULTI_INSTANCE_PARALLEL:
			// TODO implement when property editors supports enums
			break;
		case MULTI_INSTANCE_SEQUENTIAL:
			// TODO implement when property editors supports enums
			break;
		}
	}
}