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
package org.eclipse.bpmn2.modeler.core.di;

import java.io.IOException;
import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.di.BPMNEdge;
import org.eclipse.bpmn2.di.BPMNPlane;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.di.BpmnDiFactory;
import org.eclipse.bpmn2.modeler.core.Activator;
import org.eclipse.bpmn2.modeler.core.ModelHandler;
import org.eclipse.bpmn2.modeler.core.ModelHandlerLocator;
import org.eclipse.bpmn2.modeler.core.preferences.Bpmn2Preferences;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.dd.dc.Bounds;
import org.eclipse.dd.dc.DcFactory;
import org.eclipse.dd.dc.Point;
import org.eclipse.dd.di.DiagramElement;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ILayoutService;

public class DIUtils {

	public static void updateDIShape(PictogramElement element) {

		PictogramLink link = element.getLink();
		if (link == null) {
			return;
		}

		BPMNShape bpmnShape = BusinessObjectUtil.getFirstElementOfType(element, BPMNShape.class);
		if (bpmnShape == null) {
			return;
		}

		ILocation loc = Graphiti.getLayoutService().getLocationRelativeToDiagram((Shape) element);
		Bounds bounds = bpmnShape.getBounds();

		bounds.setX(loc.getX());
		bounds.setY(loc.getY());

		GraphicsAlgorithm graphicsAlgorithm = element.getGraphicsAlgorithm();
		IDimension size = Graphiti.getGaService().calculateSize(graphicsAlgorithm);
		bounds.setHeight(size.getHeight());
		bounds.setWidth(size.getWidth());

		if (element instanceof ContainerShape) {
			EList<Shape> children = ((ContainerShape) element).getChildren();
			for (Shape shape : children) {
				if (shape instanceof ContainerShape) {
					updateDIShape(shape);
				}
			}
		}
	}

	public static void updateDIEdge(Connection connection) {
		try {
			ILayoutService layoutService = Graphiti.getLayoutService();
			ModelHandler modelHandler = ModelHandlerLocator.getModelHandler(connection.getLink().getBusinessObjects()
					.get(0).eResource());

			EObject be = BusinessObjectUtil.getFirstElementOfType(connection, BaseElement.class);
			BPMNEdge edge = (BPMNEdge) modelHandler.findDIElement((BaseElement) be);
			Point point = DcFactory.eINSTANCE.createPoint();

			List<Point> waypoint = edge.getWaypoint();
			waypoint.clear();

			ILocation loc;
			loc = layoutService.getLocationRelativeToDiagram(connection.getStart());
			point.setX(loc.getX());
			point.setY(loc.getY());
			waypoint.add(point);

			if (connection instanceof FreeFormConnection) {
				FreeFormConnection freeForm = (FreeFormConnection) connection;
				EList<org.eclipse.graphiti.mm.algorithms.styles.Point> bendpoints = freeForm.getBendpoints();
				for (org.eclipse.graphiti.mm.algorithms.styles.Point bp : bendpoints) {
					point = DcFactory.eINSTANCE.createPoint();
					point.setX(bp.getX());
					point.setY(bp.getY());
					waypoint.add(point);
				}
			}

			point = DcFactory.eINSTANCE.createPoint();
			loc = layoutService.getLocationRelativeToDiagram(connection.getEnd());
			point.setX(loc.getX());
			point.setY(loc.getY());
			waypoint.add(point);

		} catch (IOException e) {
			Activator.logError(e);
		}
	}

	static void addBendPoint(FreeFormConnection freeForm, Point point) {
		freeForm.getBendpoints().add(Graphiti.getGaService().createPoint((int) point.getX(), (int) point.getY()));
	}

	public static BPMNShape createDIShape(Shape shape, BaseElement elem, int x, int y, int w, int h,
			IFeatureProvider fp, Diagram diagram) {

		EList<EObject> businessObjects = Graphiti.getLinkService().getLinkForPictogramElement(diagram)
				.getBusinessObjects();
		BPMNShape bpmnShape = null;

		for (EObject eObject : businessObjects) {
			if (eObject instanceof BPMNDiagram) {
				BPMNDiagram bpmnDiagram = (BPMNDiagram) eObject;

				bpmnShape = BpmnDiFactory.eINSTANCE.createBPMNShape();
				bpmnShape.setBpmnElement(elem);
				Bounds bounds = DcFactory.eINSTANCE.createBounds();
				bounds.setX(x);
				bounds.setY(y);
				bounds.setWidth(w);
				bounds.setHeight(h);
				bpmnShape.setBounds(bounds);

				Bpmn2Preferences.getInstance(bpmnDiagram.eResource()).applyBPMNDIDefaults(bpmnShape, null);

				addShape(bpmnShape,bpmnDiagram);
				ModelUtil.setID(bpmnShape);

				fp.link(shape, new Object[] { elem, bpmnShape });
				break;
			}
		}

		return bpmnShape;
	}

	public static void addShape(DiagramElement elem, BPMNDiagram bpmnDiagram) {
		List<DiagramElement> elements = bpmnDiagram.getPlane().getPlaneElement();
		elements.add(elem);
	}
	
	public static DiagramElement findDiagramElement(List<BPMNDiagram> diagrams, BaseElement bpmnElement) {
		for (BPMNDiagram d : diagrams) {
			BPMNPlane plane = d.getPlane();
			List<DiagramElement> planeElements = plane.getPlaneElement();
			for (DiagramElement de : planeElements) {
				if (de instanceof BPMNShape) {
					if (bpmnElement == ((BPMNShape)de).getBpmnElement())
						return de;
				}
				if (de instanceof BPMNEdge) {
					if (bpmnElement == ((BPMNEdge)de).getBpmnElement())
						return de;
				}
			}
		}
		return null;
	}
}
