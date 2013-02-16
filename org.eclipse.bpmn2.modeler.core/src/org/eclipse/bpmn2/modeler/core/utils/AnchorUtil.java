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
package org.eclipse.bpmn2.modeler.core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.di.BPMNEdge;
import org.eclipse.bpmn2.modeler.core.Activator;
import org.eclipse.bpmn2.modeler.core.ModelHandler;
import org.eclipse.dd.di.DiagramElement;
import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.features.IAddBendpointFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IRemoveBendpointFeature;
import org.eclipse.graphiti.features.context.IAddBendpointContext;
import org.eclipse.graphiti.features.context.IRemoveBendpointContext;
import org.eclipse.graphiti.features.context.impl.AddBendpointContext;
import org.eclipse.graphiti.features.context.impl.RemoveBendpointContext;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ICreateService;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.ILayoutService;
import org.eclipse.graphiti.services.IPeService;

public class AnchorUtil {

	public static final String BOUNDARY_FIXPOINT_ANCHOR = "boundary.fixpoint.anchor";

	// values for connection points
	public static final String CONNECTION_POINT = "connection.point"; //$NON-NLS-1$
	public static final String CONNECTION_POINT_KEY = "connection.point.key"; //$NON-NLS-1$
	public static final int CONNECTION_POINT_SIZE = 4;

	private static final IPeService peService = Graphiti.getPeService();
	private static final IGaService gaService = Graphiti.getGaService();
	private static final ICreateService createService = Graphiti.getCreateService();
	private static final ILayoutService layoutService = Graphiti.getLayoutService();
	
	public enum AnchorLocation {
		TOP("anchor.top"), BOTTOM("anchor.bottom"), LEFT("anchor.left"), RIGHT("anchor.right");

		private final String key;

		private AnchorLocation(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		public static AnchorLocation getLocation(String key) {
			for (AnchorLocation l : values()) {
				if (l.getKey().equals(key)) {
					return l;
				}
			}
			return null;
		}
	}

	public static class AnchorTuple {
		public FixPointAnchor sourceAnchor;
		public FixPointAnchor targetAnchor;
	}

	public static class BoundaryAnchor {
		public FixPointAnchor anchor;
		public AnchorLocation locationType;
		public ILocation location;
	}

	public static FixPointAnchor createAnchor(AnchorContainer ac, AnchorLocation loc, int x, int y) {
		IGaService gaService = Graphiti.getGaService();
		IPeService peService = Graphiti.getPeService();

		FixPointAnchor anchor = peService.createFixPointAnchor(ac);
		peService.setPropertyValue(anchor, BOUNDARY_FIXPOINT_ANCHOR, loc.getKey());
		anchor.setLocation(gaService.createPoint(x, y));
		gaService.createInvisibleRectangle(anchor);

		return anchor;
	}

	public static Map<AnchorLocation, BoundaryAnchor> getConnectionBoundaryAnchors(Shape connectionPointShape) {
		Map<AnchorLocation, BoundaryAnchor> map = new HashMap<AnchorLocation, BoundaryAnchor>(4);
		BoundaryAnchor a = new BoundaryAnchor();
		a.anchor = getConnectionPointAnchor(connectionPointShape);
		for (AnchorLocation al : AnchorLocation.values() ) {
			a.locationType = al;
			a.location = getConnectionPointLocation(connectionPointShape);
			map.put(a.locationType, a);
		}
		return map;
	}
	
	public static Map<AnchorLocation, BoundaryAnchor> getBoundaryAnchors(AnchorContainer ac) {
		Map<AnchorLocation, BoundaryAnchor> map = new HashMap<AnchorLocation, BoundaryAnchor>(4);
		
		if (ac instanceof FreeFormConnection) {
			// the anchor container is a Connection which does not have any predefined BoundaryAnchors
			// so we have to synthesize these by looking for connection point shapes owned by the connection
			for (Shape connectionPointShape : getConnectionPoints((FreeFormConnection)ac)) {
				// TODO: if there are multiple connection points, figure out which one to use
				return getConnectionBoundaryAnchors(connectionPointShape);
			}
		}
		else if (AnchorUtil.isConnectionPoint(ac)) {
			return getConnectionBoundaryAnchors((Shape)ac);
		}
		else {
			// anchor container is a ContainerShape - these already have predefined BoundaryAnchors
			Iterator<Anchor> iterator = ac.getAnchors().iterator();
			while (iterator.hasNext()) {
				Anchor anchor = iterator.next();
				String property = Graphiti.getPeService().getPropertyValue(anchor, BOUNDARY_FIXPOINT_ANCHOR);
				if (property != null && anchor instanceof FixPointAnchor) {
					BoundaryAnchor a = new BoundaryAnchor();
					a.anchor = (FixPointAnchor) anchor;
					a.locationType = AnchorLocation.getLocation(property);
					a.location = peService.getLocationRelativeToDiagram(anchor);
					map.put(a.locationType, a);
				}
			}
		}
		return map;
	}

	public static Point getCenterPoint(Shape s) {
		GraphicsAlgorithm ga = s.getGraphicsAlgorithm();
		ILocation loc = peService.getLocationRelativeToDiagram(s);
		return gaService.createPoint(loc.getX() + (ga.getWidth() / 2), loc.getY() + (ga.getHeight() / 2));
	}

	@SuppressWarnings("restriction")
	public static Tuple<FixPointAnchor, FixPointAnchor> getSourceAndTargetBoundaryAnchors(AnchorContainer source, AnchorContainer target,
			Connection connection) {
		Map<AnchorLocation, BoundaryAnchor> sourceBoundaryAnchors = getBoundaryAnchors(source);
		Map<AnchorLocation, BoundaryAnchor> targetBoundaryAnchors = getBoundaryAnchors(target);

		if (connection instanceof FreeFormConnection) {
			EList<Point> bendpoints = ((FreeFormConnection) connection).getBendpoints();
			if (bendpoints.size() > 0) {
				FixPointAnchor sourceAnchor = getCorrectAnchor(sourceBoundaryAnchors, bendpoints.get(0));
				FixPointAnchor targetAnchor = getCorrectAnchor(targetBoundaryAnchors,
						bendpoints.get(bendpoints.size() - 1));
				return new Tuple<FixPointAnchor, FixPointAnchor>(sourceAnchor, targetAnchor);
			}
		}

		BoundaryAnchor sourceTop = sourceBoundaryAnchors.get(AnchorLocation.TOP);
		BoundaryAnchor sourceBottom = sourceBoundaryAnchors.get(AnchorLocation.BOTTOM);
		BoundaryAnchor sourceLeft = sourceBoundaryAnchors.get(AnchorLocation.LEFT);
		BoundaryAnchor sourceRight = sourceBoundaryAnchors.get(AnchorLocation.RIGHT);
		BoundaryAnchor targetBottom = targetBoundaryAnchors.get(AnchorLocation.BOTTOM);
		BoundaryAnchor targetTop = targetBoundaryAnchors.get(AnchorLocation.TOP);
		BoundaryAnchor targetRight = targetBoundaryAnchors.get(AnchorLocation.RIGHT);
		BoundaryAnchor targetLeft = targetBoundaryAnchors.get(AnchorLocation.LEFT);

		boolean sLower = sourceTop.location.getY() > targetBottom.location.getY();
		boolean sHigher = sourceBottom.location.getY() < targetTop.location.getY();
		boolean sRight = sourceLeft.location.getX() > targetRight.location.getX();
		boolean sLeft = sourceRight.location.getX() < targetLeft.location.getX();

		if (sLower) {
			if (!sLeft && !sRight) {
				return new Tuple<FixPointAnchor, FixPointAnchor>(sourceTop.anchor, targetBottom.anchor);
			} else if (sLeft) {
				FixPointAnchor fromTopAnchor = getCorrectAnchor(targetBoundaryAnchors,
						peService.getLocationRelativeToDiagram(sourceTop.anchor));
				FixPointAnchor fromRightAnchor = getCorrectAnchor(targetBoundaryAnchors,
						peService.getLocationRelativeToDiagram(sourceRight.anchor));

				double topLength = getLength(peService.getLocationRelativeToDiagram(fromTopAnchor),
						peService.getLocationRelativeToDiagram(sourceTop.anchor));
				double rightLength = getLength(peService.getLocationRelativeToDiagram(fromRightAnchor),
						peService.getLocationRelativeToDiagram(sourceRight.anchor));

				if (topLength < rightLength) {
					return new Tuple<FixPointAnchor, FixPointAnchor>(sourceTop.anchor, fromTopAnchor);
				} else {
					return new Tuple<FixPointAnchor, FixPointAnchor>(sourceRight.anchor, fromRightAnchor);
				}
			} else {
				FixPointAnchor fromTopAnchor = getCorrectAnchor(targetBoundaryAnchors,
						peService.getLocationRelativeToDiagram(sourceTop.anchor));
				FixPointAnchor fromLeftAnchor = getCorrectAnchor(targetBoundaryAnchors,
						peService.getLocationRelativeToDiagram(sourceLeft.anchor));

				double topLength = getLength(peService.getLocationRelativeToDiagram(fromTopAnchor),
						peService.getLocationRelativeToDiagram(sourceTop.anchor));
				double leftLength = getLength(peService.getLocationRelativeToDiagram(fromLeftAnchor),
						peService.getLocationRelativeToDiagram(sourceLeft.anchor));
				if (topLength < leftLength) {
					return new Tuple<FixPointAnchor, FixPointAnchor>(sourceTop.anchor, fromTopAnchor);
				} else {
					return new Tuple<FixPointAnchor, FixPointAnchor>(sourceLeft.anchor, fromLeftAnchor);
				}
			}

		}

		if (sHigher) {
			if (!sLeft && !sRight) {
				return new Tuple<FixPointAnchor, FixPointAnchor>(sourceBottom.anchor, targetTop.anchor);
			} else if (sLeft) {
				FixPointAnchor fromBottomAnchor = getCorrectAnchor(targetBoundaryAnchors,
						peService.getLocationRelativeToDiagram(sourceBottom.anchor));
				FixPointAnchor fromRightAnchor = getCorrectAnchor(targetBoundaryAnchors,
						peService.getLocationRelativeToDiagram(sourceRight.anchor));

				double bottomLength = getLength(peService.getLocationRelativeToDiagram(fromBottomAnchor),
						peService.getLocationRelativeToDiagram(sourceBottom.anchor));
				double rightLength = getLength(peService.getLocationRelativeToDiagram(fromRightAnchor),
						peService.getLocationRelativeToDiagram(sourceRight.anchor));

				if (bottomLength < rightLength) {
					return new Tuple<FixPointAnchor, FixPointAnchor>(sourceBottom.anchor, fromBottomAnchor);
				} else {
					return new Tuple<FixPointAnchor, FixPointAnchor>(sourceRight.anchor, fromRightAnchor);
				}
			} else {
				FixPointAnchor fromBottomAnchor = getCorrectAnchor(targetBoundaryAnchors,
						peService.getLocationRelativeToDiagram(sourceBottom.anchor));
				FixPointAnchor fromLeftAnchor = getCorrectAnchor(targetBoundaryAnchors,
						peService.getLocationRelativeToDiagram(sourceLeft.anchor));

				double bottomLength = getLength(peService.getLocationRelativeToDiagram(fromBottomAnchor),
						peService.getLocationRelativeToDiagram(sourceBottom.anchor));
				double leftLength = getLength(peService.getLocationRelativeToDiagram(fromLeftAnchor),
						peService.getLocationRelativeToDiagram(sourceLeft.anchor));
				if (bottomLength < leftLength) {
					return new Tuple<FixPointAnchor, FixPointAnchor>(sourceBottom.anchor, fromBottomAnchor);
				} else {
					return new Tuple<FixPointAnchor, FixPointAnchor>(sourceLeft.anchor, fromLeftAnchor);
				}
			}
		}

		// if source left is further than target right then use source left and target right
		if (sRight) {
			return new Tuple<FixPointAnchor, FixPointAnchor>(sourceLeft.anchor, targetRight.anchor);
		}

		// if source right is smaller than target left then use source right and target left
		if (sLeft) {
			return new Tuple<FixPointAnchor, FixPointAnchor>(sourceRight.anchor, targetLeft.anchor);
		}

		return new Tuple<FixPointAnchor, FixPointAnchor>(sourceTop.anchor, targetTop.anchor);
	}

	private static FixPointAnchor getCorrectAnchor(Map<AnchorLocation, BoundaryAnchor> targetBoundaryAnchors,
			ILocation loc) {
		return getCorrectAnchor(targetBoundaryAnchors, gaService.createPoint(loc.getX(), loc.getY()));
	}

	private static double getLength(ILocation start, ILocation end) {
		return Math.sqrt(Math.pow(start.getX() - end.getX(), 2) + Math.pow(start.getY() - end.getY(), 2));
	}

	private static FixPointAnchor getCorrectAnchor(Map<AnchorLocation, BoundaryAnchor> boundaryAnchors, Point point) {

		BoundaryAnchor bottom = boundaryAnchors.get(AnchorLocation.BOTTOM);
		BoundaryAnchor top = boundaryAnchors.get(AnchorLocation.TOP);
		BoundaryAnchor right = boundaryAnchors.get(AnchorLocation.RIGHT);
		BoundaryAnchor left = boundaryAnchors.get(AnchorLocation.LEFT);

		boolean pointLower = point.getY() > bottom.location.getY();
		boolean pointHigher = point.getY() < top.location.getY();
		boolean pointRight = point.getX() > right.location.getX();
		boolean pointLeft = point.getX() < left.location.getX();

		// Find the best connector.
		if (pointLower) {
			if (!pointLeft && !pointRight) {
				// bendpoint is straight below the shape
				return bottom.anchor;
			} else if (pointLeft) {

				int deltaX = left.location.getX() - point.getX();
				int deltaY = point.getY() - bottom.location.getY();
				if (deltaX > deltaY) {
					return left.anchor;
				} else {
					return bottom.anchor;
				}
			} else {
				int deltaX = point.getX() - right.location.getX();
				int deltaY = point.getY() - bottom.location.getY();
				if (deltaX > deltaY) {
					return right.anchor;
				} else {
					return bottom.anchor;
				}
			}
		}

		if (pointHigher) {
			if (!pointLeft && !pointRight) {
				// bendpoint is straight above the shape
				return top.anchor;
			} else if (pointLeft) {
				int deltaX = left.location.getX() - point.getX();
				int deltaY = top.location.getY() - point.getY();
				if (deltaX > deltaY) {
					return left.anchor;
				} else {
					return top.anchor;
				}
			} else {
				int deltaX = point.getX() - right.location.getX();
				int deltaY = top.location.getY() - point.getY();
				if (deltaX > deltaY) {
					return right.anchor;
				} else {
					return top.anchor;
				}
			}

		}

		// if we reach here, then the point is neither above or below the shape and we only need to determine if we need
		// to connect to the left or right part of the shape
		if (pointRight) {
			return right.anchor;
		}

		if (pointLeft) {
			return left.anchor;
		}

		return top.anchor;
	}

	public static void reConnect(DiagramElement element, Diagram diagram) {
		try {
			ModelHandler handler = ModelHandler.getInstance(diagram);
			for (BPMNEdge bpmnEdge : handler.getAll(BPMNEdge.class)) {
				DiagramElement sourceElement = bpmnEdge.getSourceElement();
				DiagramElement targetElement = bpmnEdge.getTargetElement();
				if (sourceElement != null && targetElement != null) {
					boolean sourceMatches = sourceElement.getId().equals(element.getId());
					boolean targetMatches = targetElement.getId().equals(element.getId());
					if (sourceMatches || targetMatches) {
						updateEdge(bpmnEdge, diagram);
					}
				}
			}
		} catch (Exception e) {
			Activator.logError(e);
		}
	}

	private static void updateEdge(BPMNEdge edge, Diagram diagram) {
		List<PictogramElement> elements;
		elements =  Graphiti.getLinkService().getPictogramElements(diagram, edge.getSourceElement());
		if (elements.size()==0 || !(elements.get(0) instanceof AnchorContainer))
			return;
		AnchorContainer source = (AnchorContainer) elements.get(0);
		
		elements =  Graphiti.getLinkService().getPictogramElements(diagram, edge.getTargetElement());
		if (elements.size()==0 || !(elements.get(0) instanceof AnchorContainer))
			return;
		AnchorContainer target = (AnchorContainer) elements.get(0);
		
		elements = Graphiti.getLinkService().getPictogramElements(diagram, edge);
		if (elements.size()==0)
			return;
		Connection connection = (Connection) elements.get(0);
		Tuple<FixPointAnchor, FixPointAnchor> anchors = getSourceAndTargetBoundaryAnchors(source, target, connection);

		ILocation loc = peService.getLocationRelativeToDiagram(anchors.getFirst());
		org.eclipse.dd.dc.Point p = edge.getWaypoint().get(0);
		p.setX(loc.getX());
		p.setY(loc.getY());

		loc = peService.getLocationRelativeToDiagram(anchors.getSecond());
		p = edge.getWaypoint().get(edge.getWaypoint().size() - 1);
		p.setX(loc.getX());
		p.setY(loc.getY());

		relocateConnection(source.getAnchors(), anchors, target);
		deleteEmptyAdHocAnchors(source);
		deleteEmptyAdHocAnchors(target);
	}

	private static void relocateConnection(EList<Anchor> anchors, Tuple<FixPointAnchor, FixPointAnchor> newAnchors,
			AnchorContainer target) {

		List<Connection> connectionsToBeUpdated = new ArrayList<Connection>();

		for (Anchor anchor : anchors) {
			if (!(anchor instanceof FixPointAnchor)) {
				continue;
			}

			for (Connection connection : anchor.getOutgoingConnections()) {
				if (connection.getEnd().eContainer().equals(target)) {
					connectionsToBeUpdated.add(connection);
				}
			}
		}

		for (Connection c : connectionsToBeUpdated) {
			c.setStart(newAnchors.getFirst());
			c.setEnd(newAnchors.getSecond());
		}
	}

	private static void deleteEmptyAdHocAnchors(AnchorContainer target) {
		List<Integer> indexes = new ArrayList<Integer>();

		for (int i = target.getAnchors().size()-1; i>=0; --i) {
			Anchor a = target.getAnchors().get(i);
			if (!(a instanceof FixPointAnchor)) {
				continue;
			}

			if (peService.getProperty(a, BOUNDARY_FIXPOINT_ANCHOR) == null && a.getIncomingConnections().isEmpty()
					&& a.getOutgoingConnections().isEmpty()) {
				indexes.add(i);
			}
		}

		for (int i : indexes) {
			peService.deletePictogramElement(target.getAnchors().get(i));
		}
	}

	public static void addFixedPointAnchors(Shape shape, GraphicsAlgorithm ga) {
		IDimension size = gaService.calculateSize(ga);
		int w = size.getWidth();
		int h = size.getHeight();
		createAnchor(shape, AnchorLocation.TOP, w / 2, 0);
		createAnchor(shape, AnchorLocation.RIGHT, w, h / 2);
		createAnchor(shape, AnchorLocation.BOTTOM, w / 2, h);
		createAnchor(shape, AnchorLocation.LEFT, 0, h / 2);
	}

	public static void relocateFixPointAnchors(Shape shape, int w, int h) {
		Map<AnchorLocation, BoundaryAnchor> anchors = getBoundaryAnchors(shape);

		FixPointAnchor anchor = anchors.get(AnchorLocation.TOP).anchor;
		anchor.setLocation(gaService.createPoint(w / 2, 0));

		anchor = anchors.get(AnchorLocation.RIGHT).anchor;
		anchor.setLocation(gaService.createPoint(w, h / 2));

		anchor = anchors.get(AnchorLocation.BOTTOM).anchor;
		anchor.setLocation(gaService.createPoint(w / 2, h));

		anchor = anchors.get(AnchorLocation.LEFT).anchor;
		anchor.setLocation(gaService.createPoint(0, h / 2));
	}

	// Connection points allow creation of anchors on FreeFormConnections
	
	public static Shape createConnectionPoint(IFeatureProvider fp,
			FreeFormConnection connection, ILocation location) {

		Shape connectionPointShape = null;

		Point bendPoint = null;
		Diagram diagram = fp.getDiagramTypeProvider().getDiagram();

		// TODO: fix this
		for (Point p : connection.getBendpoints()) {
			int px = p.getX();
			int py = p.getY();
			if (GraphicsUtil.isPointNear(p, location, 20)) {
				bendPoint = p;
				location.setX(px);
				location.setY(py);
			}

			for (Shape s : diagram.getChildren()) {
				if (isConnectionPointNear(s, location, 0)) {
					// this is the connection point on the target connection line
					// reuse this connection point if it's "close enough" to
					// target location otherwise create a new connection point
					if (isConnectionPointNear(s, location, 20)) {
						bendPoint = p;
						connectionPointShape = s;
						location.setX(px);
						location.setY(py);
					}
					break;
				}
			}
		}

		if (connectionPointShape == null) {
			connectionPointShape = createConnectionPoint(location, diagram);
			fp.link(connectionPointShape, connection);
			connection.getLink().getBusinessObjects().add(connectionPointShape);

			if (bendPoint == null) {
				bendPoint = createService.createPoint(location.getX(),
						location.getY());

				IAddBendpointContext addBpContext = new AddBendpointContext(connection, bendPoint.getX(), bendPoint.getY(), 0);
				IAddBendpointFeature addBpFeature = fp.getAddBendpointFeature(addBpContext);
				addBpFeature.addBendpoint(addBpContext);
			}
		}
		return connectionPointShape;
	}

	public static Shape createConnectionPoint(ILocation location, ContainerShape cs) {
		
		// create a circle for the connection point shape
		Shape connectionPointShape = createService.createShape(cs, true);
		peService.setPropertyValue(connectionPointShape, CONNECTION_POINT_KEY, CONNECTION_POINT);
		Ellipse ellipse = createService.createEllipse(connectionPointShape);
		int x = 0, y = 0;
		if (location != null) {
			x = location.getX();
			y = location.getY();
		}
		ellipse.setFilled(true);
		Diagram diagram = peService.getDiagramForPictogramElement(connectionPointShape);
		ellipse.setForeground(Graphiti.getGaService().manageColor(diagram, StyleUtil.CLASS_FOREGROUND));
		
		// create the anchor
		getConnectionPointAnchor(connectionPointShape);
		
		// set the location
		setConnectionPointLocation(connectionPointShape, x, y);
	
		return connectionPointShape;
	}

	public static boolean deleteConnectionPointIfPossible(IFeatureProvider fp,Shape connectionPointShape) {
		if (isConnectionPoint(connectionPointShape)) {
			Anchor anchor = getConnectionPointAnchor(connectionPointShape);
			List<Connection> allConnections = Graphiti.getPeService().getAllConnections(anchor);
			if (allConnections.size()==0) {
				// remove the bendpoint from target connection if there are no other connections going to it
				FreeFormConnection oldTargetConnection = (FreeFormConnection) connectionPointShape.getLink().getBusinessObjects().get(0);
				
				Point bp = null;
				for (Point p : oldTargetConnection.getBendpoints()) {
					if (AnchorUtil.isConnectionPointNear(connectionPointShape, p, 0)) {
						bp = p;
						break;
					}
				}
				
				if (bp!=null) {
					IRemoveBendpointContext removeBpContext = new RemoveBendpointContext(oldTargetConnection, bp);
					IRemoveBendpointFeature removeBpFeature = fp.getRemoveBendpointFeature(removeBpContext);
					removeBpFeature.removeBendpoint(removeBpContext);
				}
				
				RemoveContext ctx = new RemoveContext(connectionPointShape);
				fp.getRemoveFeature(ctx).remove(ctx);
			}
		}
		return false;
	}
	
	public static FixPointAnchor getConnectionPointAnchor(Shape connectionPointShape) {
		if (connectionPointShape.getAnchors().size()==0) {
			FixPointAnchor anchor = createService.createFixPointAnchor(connectionPointShape);
			peService.setPropertyValue(anchor, CONNECTION_POINT_KEY, CONNECTION_POINT);
			
			// if the anchor doesn't have a GraphicsAlgorithm, GEF will throw a fit
			// so create an invisible rectangle for it
			createService.createInvisibleRectangle(anchor);
		}		
		return (FixPointAnchor)connectionPointShape.getAnchors().get(0);
	}

	public static ILocation getConnectionPointLocation(Shape connectionPointShape) {
		ILocation location = GraphicsUtil.peService.getLocationRelativeToDiagram(connectionPointShape);
		int x = location.getX() + CONNECTION_POINT_SIZE / 2;
		int y = location.getY() + CONNECTION_POINT_SIZE / 2;
		location.setX(x);
		location.setY(y);
		return location;
	}
	
	public static void setConnectionPointLocation(Shape connectionPointShape, int x, int y) {
		
		if (connectionPointShape.getAnchors().size()==0) {
			// anchor has not been created yet - need to set both location AND size
			layoutService.setLocationAndSize(
					connectionPointShape.getGraphicsAlgorithm(),
					x - CONNECTION_POINT_SIZE / 2, y - CONNECTION_POINT_SIZE / 2,
					CONNECTION_POINT_SIZE, CONNECTION_POINT_SIZE);
		}
		else {
			// already created - just set the location
			layoutService.setLocation(
					connectionPointShape.getGraphicsAlgorithm(),
					x - CONNECTION_POINT_SIZE / 2, y - CONNECTION_POINT_SIZE / 2);
		}
		
		FixPointAnchor anchor = getConnectionPointAnchor(connectionPointShape);
		anchor.setLocation( Graphiti.getCreateService().createPoint(CONNECTION_POINT_SIZE / 2,CONNECTION_POINT_SIZE / 2) );
		layoutService.setLocation(
				anchor.getGraphicsAlgorithm(), 
				CONNECTION_POINT_SIZE / 2,CONNECTION_POINT_SIZE / 2);
	}
	
	public static List<Shape> getConnectionPoints(FreeFormConnection connection) {
		ArrayList<Shape> list = new ArrayList<Shape>();
		
		for (Object o : connection.getLink().getBusinessObjects()) {
			if ( o instanceof AnchorContainer ) {
				AnchorContainer c = (AnchorContainer)o;
				if (AnchorUtil.isConnectionPoint(c)) {
					list.add((Shape)c);
				}
			}
		}
		
		return list;
	}
	
	public static Shape getConnectionPointAt(FreeFormConnection connection, Point point) {
		for (Shape connectionPointShape : getConnectionPoints(connection)) {
			if (AnchorUtil.isConnectionPointNear(connectionPointShape, point, 0)) {
				return connectionPointShape;
			}
		}
		return null;
	}


	public static boolean isConnectionPoint(PictogramElement pe) {
		String value = Graphiti.getPeService().getPropertyValue(pe, CONNECTION_POINT_KEY);
		return CONNECTION_POINT.equals(value);
	}

	public static boolean isConnectionPointNear(PictogramElement pe, ILocation loc, int dist) {
		if (isConnectionPoint(pe)) {
			int x = pe.getGraphicsAlgorithm().getX() + CONNECTION_POINT_SIZE / 2;
			int y = pe.getGraphicsAlgorithm().getY() + CONNECTION_POINT_SIZE / 2;
			int lx = loc.getX();
			int ly = loc.getY();
			return lx-dist <= x && x <= lx+dist && ly-dist <= y && y <= ly+dist;
		}
		return false;
	}

	public static boolean isConnectionPointNear(PictogramElement pe, Point loc, int dist) {
		if (isConnectionPoint(pe)) {
			int x = pe.getGraphicsAlgorithm().getX() + CONNECTION_POINT_SIZE / 2;
			int y = pe.getGraphicsAlgorithm().getY() + CONNECTION_POINT_SIZE / 2;
			int lx = loc.getX();
			int ly = loc.getY();
			return lx-dist <= x && x <= lx+dist && ly-dist <= y && y <= ly+dist;
		}
		return false;
	}
	
	public static FreeFormConnection getConnectionPointOwner(Shape connectionPointShape) {
		if (isConnectionPoint(connectionPointShape)) {
			return (FreeFormConnection)connectionPointShape.getLink().getBusinessObjects().get(0); 
		}
		return null;
	}
}