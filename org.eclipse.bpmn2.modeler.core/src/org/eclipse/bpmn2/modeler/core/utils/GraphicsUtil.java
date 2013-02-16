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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.modeler.core.features.ContextConstants;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.ILayoutService;
import org.eclipse.graphiti.services.IPeService;
import org.eclipse.graphiti.ui.services.GraphitiUi;

public class GraphicsUtil {

	private static final IGaService gaService = Graphiti.getGaService();
	static final IPeService peService = Graphiti.getPeService();
	private static Map<Diagram, SizeTemplate> diagramSizeMap;

	// TODO move all size properties to separate interface
	public static int DATA_WIDTH = 36;
	public static int DATA_HEIGHT = 50;

	public static int CHOREOGRAPHY_WIDTH = 150;
	public static int CHOREOGRAPHY_HEIGHT = 150;
	public static int PARTICIPANT_BAND_HEIGHT = 20;

	public static final int SHAPE_PADDING = 6;
	public static final int TEXT_PADDING = 5;
	public static final String LABEL_PROPERTY = "label";
	
	// TODO: Determine all cases to make a line break! The following implementation are the easy once.
	private static final String LINE_BREAK = "\n";
	
	public static class SizeTemplate{
		
		private Size eventSize = new Size(GraphicsUtil.EVENT_SIZE, GraphicsUtil.EVENT_SIZE);
		private Size gatewaySize = new Size(GraphicsUtil.GATEWAY_RADIUS*2, GraphicsUtil.GATEWAY_RADIUS*2);
		private Size activitySize = new Size(GraphicsUtil.TASK_DEFAULT_WIDTH, GraphicsUtil.TASK_DEFAULT_HEIGHT);
		
		public Size getEventSize() {
			return eventSize;
		}
		public void setEventSize(Size eventSize) {
			this.eventSize = eventSize;
		}
		public Size getGatewaySize() {
			return gatewaySize;
		}
		public void setGatewaySize(Size gatewaySize) {
			this.gatewaySize = gatewaySize;
		}
		public Size getActivitySize() {
			return this.activitySize;
		}
		public void setActivitySize(Size activitySize) {
			this.activitySize = activitySize;
		}
	}
	
	public static class Size {
		private int width;
		private int height;
		
		public Size(int width, int height) {
			this.width = width;
			this.height = height;
		}
		
		public int getWidth() {
			return this.width;
		}
		
		public int getHeight() {
			return this.height;
		}
	}

	public static class Envelope {
		public Rectangle rect;
		public Polyline line;
	}

	public static class Asterisk {
		public Polyline horizontal;
		public Polyline vertical;
		public Polyline diagonalDesc;
		public Polyline diagonalAsc;
	}

	public static class Compensation {
		public Polygon arrow1;
		public Polygon arrow2;
	}

	public static class Cross {
		public Polyline vertical;
		public Polyline horizontal;
	}

	public static class DiagonalCross {
		public Polyline diagonalAsc;
		public Polyline diagonalDesc;
	}

	public static class MultiInstance {
		public Polyline line1;
		public Polyline line2;
		public Polyline line3;
	}

	public static class Loop {
		public Polyline circle;
		public Polyline arrow;
	}

	public static class Expand {
		public Rectangle rect;
		public Polyline horizontal;
		public Polyline vertical;
	}

	/* GATEWAY */

	private static final String DELETABLE_PROPERTY = "deletable";

	public static final int GATEWAY_RADIUS = 25;
	public static final int GATEWAY_TEXT_AREA = 15;

	private static int generateRatioPointValue(float originalPointValue, float ratioValue) {
		return Math.round(Float.valueOf(originalPointValue * ratioValue));
	}
	
	// TODO: Think about line break in the ui...
	public static int getLabelHeight(AbstractText text) {
		if (text.getValue() != null && !text.getValue().isEmpty()) {
			String[] strings = text.getValue().split(LINE_BREAK);
			return strings.length * 14;
		}
		return 0;
	}

	// TODO: Think about a maximum-width...
	public static int getLabelWidth(AbstractText text) {
		if (text.getValue() != null && !text.getValue().isEmpty()) {
			String[] strings = text.getValue().split(LINE_BREAK);
			int result = 0;
			for (String string : strings) {
				IDimension dim = GraphitiUi.getUiLayoutService().calculateTextSize(string, text.getFont());
				if (dim.getWidth() > result) {
					result = dim.getWidth();
				}
			}
			return result;
		}
		return 0;
	}
	
	public static void alignWithShape(AbstractText text, ContainerShape labelContainer, 
			int width,
			int height,
			int shapeX,
			int shapeY, 
			int preShapeX, 
			int preShapeY){
		final int textHeight = getLabelHeight(text);
		final int textWidth = getLabelWidth(text);
		
		int currentLabelX = labelContainer.getGraphicsAlgorithm().getX();
		int currentLabelY = labelContainer.getGraphicsAlgorithm().getY();
		
		int newShapeX = shapeX - ((textWidth + SHAPE_PADDING) / 2) + width / 2;
		int newShapeY = shapeY + height + 2;

		if (currentLabelX > 0 && preShapeX > 0){
			newShapeX = currentLabelX + (shapeX - preShapeX);
			newShapeY = currentLabelY + (shapeY - preShapeY);
		}
		
		IGaService gaService = Graphiti.getGaService();
		
		gaService.setLocationAndSize(labelContainer.getGraphicsAlgorithm(), 
				newShapeX , newShapeY ,
				textWidth + SHAPE_PADDING, textHeight + SHAPE_PADDING);
		gaService.setLocationAndSize(text, 
				0, 0,
				textWidth + TEXT_PADDING, textHeight + TEXT_PADDING);
	}

	public static void prepareAddContext(IAddContext context, int width, int height) {
		context.putProperty(ContextConstants.LABEL_CONTEXT, true);
		context.putProperty(ContextConstants.WIDTH, width);
		context.putProperty(ContextConstants.HEIGHT, height);
		context.putProperty(ContextConstants.BASE_ELEMENT, context.getNewObject());
	}
	
	private static float calculateRatio(float x, float y) {
		return x / y;
	}
	
	private static int getShapeHeight(Shape shape) {
		return shape.getGraphicsAlgorithm().getHeight();
	}
	
	private static int getShapeWidth(Shape shape) {
		return shape.getGraphicsAlgorithm().getWidth();
	}

//	private static final int[] GATEWAY = { 0, GATEWAY_RADIUS, GATEWAY_RADIUS, 0, 2 * GATEWAY_RADIUS, GATEWAY_RADIUS,
//	        GATEWAY_RADIUS, 2 * GATEWAY_RADIUS };

	public static Polygon createGateway(Shape container, final int width, final int height) {
		final int widthRadius = width / 2;
		final int heightRadius = height / 2;
		final int[] gateWayPoints = {0, heightRadius, widthRadius, 0, 2 * widthRadius, heightRadius, widthRadius, 2 * heightRadius};
		return gaService.createPolygon(container, gateWayPoints);
	}

	public static Polygon createGatewayPentagon(ContainerShape container) {
		Shape pentagonShape = peService.createShape(container, false);
		
		final int gatewayHeight = getShapeHeight(container);
		final int gatewayWidth = getShapeWidth(container);
		
		final float heightRatio = calculateRatio(gatewayHeight, Float.valueOf(GATEWAY_RADIUS * 2));
		final float widthRatio = calculateRatio(gatewayWidth, Float.valueOf(GATEWAY_RADIUS * 2));
		
		
//		Polygon pentagon = gaService.createPolygon(pentagonShape,
//				new int[] { GATEWAY_RADIUS, 18,
//						GATEWAY_RADIUS + 8, GATEWAY_RADIUS - 2,
//						GATEWAY_RADIUS + 5, GATEWAY_RADIUS + 7,
//						GATEWAY_RADIUS - 5, GATEWAY_RADIUS + 7,
//						GATEWAY_RADIUS - 8, GATEWAY_RADIUS - 2 });
		Polygon pentagon = gaService.createPolygon(pentagonShape,
				new int[] { gatewayWidth / 2, generateRatioPointValue(18, heightRatio),
							gatewayWidth / 2 + generateRatioPointValue(8, widthRatio), gatewayHeight / 2 - generateRatioPointValue(2, heightRatio),
							gatewayWidth / 2 + generateRatioPointValue(5, widthRatio), gatewayHeight / 2 + generateRatioPointValue(7, heightRatio),
							gatewayWidth / 2 - generateRatioPointValue(5, widthRatio), gatewayHeight / 2 + generateRatioPointValue(7, heightRatio),
							gatewayWidth / 2 - generateRatioPointValue(8, widthRatio), gatewayHeight / 2 - generateRatioPointValue(2, heightRatio) });
							
		peService.setPropertyValue(pentagonShape, DELETABLE_PROPERTY, "true");
		return pentagon;
	}

	public static Ellipse createGatewayInnerCircle(Ellipse outer) {
		final int gatewayHeight = outer.getHeight();
		final int gatewayWidth = outer.getWidth();
		
		final float heightRatio = calculateRatio(gatewayHeight, Float.valueOf(GATEWAY_RADIUS * 2));
		final float widthRatio = calculateRatio(gatewayWidth, Float.valueOf(GATEWAY_RADIUS * 2));
		
		Float x = (5 * widthRatio) * new Float(0.8);
		Float y = (5 * heightRatio) * new Float(0.8);

		Float width = gatewayHeight * new Float(0.8);
		Float height = gatewayWidth * new Float(0.8);
		
//		gaService.setLocationAndSize(ellipse, 14, 14, 23, 23);
		Ellipse ellipse = gaService.createEllipse(outer);
		gaService.setLocationAndSize(ellipse,
				 Math.round(x), Math.round(y),
				 Math.round(width), Math.round(height));
		ellipse.setFilled(false);
		ellipse.setLineWidth(1);
		peService.setPropertyValue(ellipse, DELETABLE_PROPERTY, "true");
		return ellipse;
	}

	public static Ellipse createGatewayOuterCircle(ContainerShape container) {
		Shape ellipseShape = peService.createShape(container, false);
		Ellipse ellipse = gaService.createEllipse(ellipseShape);
		
		final int gatewayHeight = getShapeHeight(container);
		final int gatewayWidth = getShapeWidth(container);
		
		final float heightRatio = calculateRatio(gatewayHeight, Float.valueOf(GATEWAY_RADIUS * 2));
		final float widthRatio = calculateRatio(gatewayWidth, Float.valueOf(GATEWAY_RADIUS * 2));
//		gaService.setLocationAndSize(ellipse, 12, 12, 27, 27);
		gaService.setLocationAndSize(ellipse,
				generateRatioPointValue(12, widthRatio),
				generateRatioPointValue(12, heightRatio),
				generateRatioPointValue(27, widthRatio),
				generateRatioPointValue(27, heightRatio));
		ellipse.setFilled(false);
		ellipse.setLineWidth(1);
		peService.setPropertyValue(ellipseShape, DELETABLE_PROPERTY, "true");
		return ellipse;
	}

	public static Cross createGatewayCross(ContainerShape container) {
		Shape verticalShape = peService.createShape(container, false);
		
		final int gatewayHeight = getShapeHeight(container);
		final int gatewayWidth = getShapeWidth(container);
		
		final float heightRatio = calculateRatio(gatewayHeight, Float.valueOf(GATEWAY_RADIUS * 2));
		final float widthRatio = calculateRatio(gatewayWidth, Float.valueOf(GATEWAY_RADIUS * 2));
		
//		Polyline verticalLine = gaService.createPolyline(verticalShape, new int[] { 24, 7, 24, 43 });
		Polyline verticalLine = gaService.createPolyline(verticalShape,
				new int[] { generateRatioPointValue(24, widthRatio), generateRatioPointValue(7, heightRatio),
							generateRatioPointValue(24, widthRatio), generateRatioPointValue(43, heightRatio) });
		verticalLine.setLineWidth(3);
		peService.setPropertyValue(verticalShape, DELETABLE_PROPERTY, "false");

		Shape horizontalShape = peService.createShape(container, false);
		
//		Polyline horizontalLine = gaService.createPolyline(horizontalShape, new int[] { 7, 24, 43, 24 });
		
		Polyline horizontalLine = gaService.createPolyline(horizontalShape,
				new int[] { generateRatioPointValue(7, widthRatio), generateRatioPointValue(24, heightRatio),
							generateRatioPointValue(43, widthRatio), generateRatioPointValue(24, heightRatio) });
		horizontalLine.setLineWidth(3);
		peService.setPropertyValue(horizontalShape, DELETABLE_PROPERTY, "false");

		Cross cross = new Cross();
		cross.vertical = verticalLine;
		cross.horizontal = horizontalLine;
		return cross;
	}

	public static DiagonalCross createGatewayDiagonalCross(ContainerShape container) {
		IPeService service = Graphiti.getPeService();

		final int gatewayHeight = getShapeHeight(container);
		final int gatewayWidth = getShapeWidth(container);
		
		final float heightRatio = calculateRatio(gatewayHeight, Float.valueOf(GATEWAY_RADIUS * 2));
		final float widthRatio = calculateRatio(gatewayWidth, Float.valueOf(GATEWAY_RADIUS * 2));

		Shape diagonalDescShape = service.createShape(container, false);
//		Polyline diagonalDesc = gaService.createPolyline(diagonalDescShape, new int[] { 13, 14, 37, 37 });
		Polyline diagonalDesc = gaService.createPolyline(diagonalDescShape,
				new int[] { generateRatioPointValue(14, widthRatio), generateRatioPointValue(14, heightRatio),
							generateRatioPointValue(37, widthRatio), generateRatioPointValue(37, heightRatio) });
		diagonalDesc.setLineWidth(3);
		peService.setPropertyValue(diagonalDescShape, DELETABLE_PROPERTY, "true");

		Shape diagonalAscShape = service.createShape(container, false);
		
//		Polyline diagonalAsc = gaService.createPolyline(diagonalAscShape, new int[] { 37, 14, 13, 37 });
		Polyline diagonalAsc = gaService.createPolyline(diagonalAscShape,
				new int[] { generateRatioPointValue(37, widthRatio), generateRatioPointValue(14, heightRatio),
							generateRatioPointValue(14, widthRatio), generateRatioPointValue(37, heightRatio) });
		diagonalAsc.setLineWidth(3);
		peService.setPropertyValue(diagonalAscShape, DELETABLE_PROPERTY, "true");

		DiagonalCross diagonalCross = new DiagonalCross();
		diagonalCross.diagonalDesc = diagonalDesc;
		diagonalCross.diagonalAsc = diagonalAsc;
		return diagonalCross;
	}

	public static Polygon createEventGatewayParallelCross(ContainerShape container) {
		Shape crossShape = peService.createShape(container, false);
		
		final int gatewayHeight = getShapeHeight(container);
		final int gatewayWidth = getShapeWidth(container);
		
		final float heightRatio = calculateRatio(gatewayHeight, Float.valueOf(GATEWAY_RADIUS * 2));
		final float widthRatio = calculateRatio(gatewayWidth, Float.valueOf(GATEWAY_RADIUS * 2));
		
		int n1x = generateRatioPointValue(14, widthRatio);
		int n1y = generateRatioPointValue(14, heightRatio);
		int n2x = generateRatioPointValue(22, widthRatio);
		int n2y = generateRatioPointValue(22, heightRatio);
		int n3x = generateRatioPointValue(28, widthRatio);
		int n3y = generateRatioPointValue(28, heightRatio);
		int n4x = generateRatioPointValue(36, widthRatio);
		int n4y = generateRatioPointValue(36, heightRatio);
		
		Collection<Point> points = new ArrayList<Point>();
		points.add(gaService.createPoint(n1x, n2y));
		points.add(gaService.createPoint(n2x, n2y));
		points.add(gaService.createPoint(n2x, n1y));
		points.add(gaService.createPoint(n3x, n1y));
		points.add(gaService.createPoint(n3x, n2y));
		points.add(gaService.createPoint(n4x, n2y));
		points.add(gaService.createPoint(n4x, n3y));
		points.add(gaService.createPoint(n3x, n3y));
		points.add(gaService.createPoint(n3x, n4y));
		points.add(gaService.createPoint(n2x, n4y));
		points.add(gaService.createPoint(n2x, n3y));
		points.add(gaService.createPoint(n1x, n3y));
		Polygon cross = gaService.createPolygon(crossShape, points);
		cross.setFilled(false);
		cross.setLineWidth(1);
		peService.setPropertyValue(crossShape, DELETABLE_PROPERTY, "true");
		return cross;
	}

	public static Asterisk createGatewayAsterisk(ContainerShape container) {
		IPeService service = Graphiti.getPeService();

		final int gatewayHeight = getShapeHeight(container);
		final int gatewayWidth = getShapeWidth(container);
		
		final float heightRatio = calculateRatio(gatewayHeight, Float.valueOf(GATEWAY_RADIUS * 2));
		final float widthRatio = calculateRatio(gatewayWidth, Float.valueOf(GATEWAY_RADIUS * 2));

		Shape verticalShape = service.createShape(container, false);
//		Polyline vertical = gaService.createPolyline(verticalShape, new int[] { 23, 8, 23, 42 });
		Polyline vertical = gaService.createPolyline(verticalShape,
				new int[] { generateRatioPointValue(24, widthRatio), generateRatioPointValue(7, heightRatio),
							generateRatioPointValue(24, widthRatio), generateRatioPointValue(43, heightRatio) });
		vertical.setLineWidth(3);
		peService.setPropertyValue(verticalShape, DELETABLE_PROPERTY, "true");

		Shape horizontalShape = service.createShape(container, false);
//		Polyline horizontal = gaService.createPolyline(horizontalShape, new int[] { 8, 24, 42, 24 });
		Polyline horizontal = gaService.createPolyline(horizontalShape,
				new int[] { generateRatioPointValue(7, widthRatio), generateRatioPointValue(24, heightRatio),
							generateRatioPointValue(43, widthRatio), generateRatioPointValue(24, heightRatio) });
		horizontal.setLineWidth(3);
		peService.setPropertyValue(horizontalShape, DELETABLE_PROPERTY, "true");

		Shape diagonalDescShape = service.createShape(container, false);
//		Polyline diagonalDesc = gaService.createPolyline(diagonalDescShape, new int[] { 13, 14, 37, 37 });
		Polyline diagonalDesc = gaService.createPolyline(diagonalDescShape,
				new int[] { generateRatioPointValue(14, widthRatio), generateRatioPointValue(14, heightRatio),
							generateRatioPointValue(37, widthRatio), generateRatioPointValue(37, heightRatio) });
		diagonalDesc.setLineWidth(3);
		peService.setPropertyValue(diagonalDescShape, DELETABLE_PROPERTY, "true");

		Shape diagonalAscShape = service.createShape(container, false);
//		Polyline diagonalAsc = gaService.createPolyline(diagonalAscShape, new int[] { 37, 14, 13, 37 });
		Polyline diagonalAsc = gaService.createPolyline(diagonalAscShape,
				new int[] { generateRatioPointValue(37, widthRatio), generateRatioPointValue(14, heightRatio),
							generateRatioPointValue(14, widthRatio), generateRatioPointValue(37, heightRatio) });
		diagonalAsc.setLineWidth(3);
		peService.setPropertyValue(diagonalAscShape, DELETABLE_PROPERTY, "true");

		Asterisk a = new Asterisk();
		a.horizontal = horizontal;
		a.vertical = vertical;
		a.diagonalDesc = diagonalDesc;
		a.diagonalAsc = diagonalAsc;
		return a;
	}

	public static void clearGateway(PictogramElement element) {
		Iterator<PictogramElement> iterator = peService.getAllContainedPictogramElements(element).iterator();
		while (iterator.hasNext()) {
			PictogramElement childElement = iterator.next();
			boolean deletable = Boolean.parseBoolean(peService.getPropertyValue(childElement, DELETABLE_PROPERTY));
			if (deletable) {
				peService.deletePictogramElement(childElement);
			}
		}
	}

	/* EVENT */

	public static final int EVENT_SIZE = 36;
//	public static final int EVENT_TEXT_AREA = 15;

	public static Ellipse createEventShape(Shape container, final int width, final int height) {
		Ellipse ellipse = gaService.createEllipse(container);
		gaService.setLocationAndSize(ellipse, 0, 0, width, height);
		return ellipse;
	}

	public static Envelope createEventEnvelope(Shape shape) {
		final int eventHeight = shape.getContainer().getGraphicsAlgorithm().getHeight();
		final int eventWidth = shape.getContainer().getGraphicsAlgorithm().getWidth();
		
		final float heightRatio = calculateRatio(eventHeight, Float.valueOf(EVENT_SIZE));
		final float widthRatio = calculateRatio(eventWidth, Float.valueOf(EVENT_SIZE));
		
//		return createEnvelope(shape, 9, 9, 18, 18);
		return createEnvelope(shape,
				generateRatioPointValue(9, widthRatio),
				generateRatioPointValue(12, heightRatio),
				generateRatioPointValue(18, widthRatio),
				generateRatioPointValue(14, heightRatio));
		
	}

	public static Polygon createEventPentagon(Shape shape) {
		final int eventHeight = shape.getContainer().getGraphicsAlgorithm().getHeight();
		final int eventWidth = shape.getContainer().getGraphicsAlgorithm().getWidth();
		
		final float heightRatio = calculateRatio(eventHeight, Float.valueOf(EVENT_SIZE));
		final float widthRatio = calculateRatio(eventWidth, Float.valueOf(EVENT_SIZE));
		
//		return gaService.createPolygon(shape, new int[] { r, 7, r + 10, r - 4, r + 7, r + 10, r - 7, r + 10, r - 10,
//		        r - 4 });
		return gaService.createPolygon(shape,
				new int[] { eventWidth / 2, generateRatioPointValue(7, heightRatio),
				eventWidth / 2 + generateRatioPointValue(10, widthRatio), eventHeight / 2 - generateRatioPointValue(4, heightRatio),
				eventWidth / 2 + generateRatioPointValue(7, widthRatio), eventHeight / 2 + generateRatioPointValue(10, heightRatio),
				eventWidth / 2 - generateRatioPointValue(7, widthRatio), eventHeight / 2 + generateRatioPointValue(10, heightRatio),
				eventWidth / 2 - generateRatioPointValue(10, widthRatio), eventHeight / 2 - generateRatioPointValue(4, heightRatio) });
	}

	public static Ellipse createIntermediateEventCircle(Ellipse ellipse) {
		final int eventHeight = ellipse.getHeight();
		final int eventWidth = ellipse.getWidth();
		
		final float heightRatio = calculateRatio(eventHeight, Float.valueOf(EVENT_SIZE));
		final float widthRatio = calculateRatio(eventWidth, Float.valueOf(EVENT_SIZE));
		
		Float x = (5 * widthRatio) * new Float(0.8);
		Float y = (5 * heightRatio) * new Float(0.8);

		Float width = eventWidth * new Float(0.8);
		Float height = eventHeight * new Float(0.8);
		
		Ellipse circle = gaService.createEllipse(ellipse);
//		gaService.setLocationAndSize(circle, 
//				generateRatioPointValue(4, widthRatio), generateRatioPointValue(4, heightRatio),
//				eventWidth - generateRatioPointValue(8, widthRatio), eventWidth - generateRatioPointValue(8, heightRatio));
		gaService.setLocationAndSize(circle,
				 Math.round(x), Math.round(y),
				 width.intValue(), height.intValue());
		circle.setLineWidth(1);
		circle.setFilled(false);
		return circle;
	}

	public static Image createEventImage(Shape shape, String imageId) {
		final int eventHeight = shape.getContainer().getGraphicsAlgorithm().getHeight();
		final int eventWidth = shape.getContainer().getGraphicsAlgorithm().getWidth();
		
		final float heightRatio = calculateRatio(eventHeight, Float.valueOf(EVENT_SIZE));
		final float widthRatio = calculateRatio(eventWidth, Float.valueOf(EVENT_SIZE));
		
		Image image = gaService.createImage(shape, imageId);
		gaService.setLocationAndSize(image, 
				generateRatioPointValue(8, widthRatio), generateRatioPointValue(8, heightRatio),
				generateRatioPointValue(20, widthRatio), generateRatioPointValue(20, heightRatio));
		return image;
	}

	public static Polygon createEventSignal(Shape shape) {
		final int eventHeight = shape.getContainer().getGraphicsAlgorithm().getHeight();
		final int eventWidth = shape.getContainer().getGraphicsAlgorithm().getWidth();
		
		final float heightRatio = calculateRatio(eventHeight, Float.valueOf(EVENT_SIZE));
		final float widthRatio = calculateRatio(eventWidth, Float.valueOf(EVENT_SIZE));
		
		Polygon polygon = gaService.createPolygon(shape, 
				new int[] { generateRatioPointValue(16, widthRatio), generateRatioPointValue(4, heightRatio),
							generateRatioPointValue(28, widthRatio), generateRatioPointValue(26, heightRatio),
							generateRatioPointValue(7, widthRatio), generateRatioPointValue(26, heightRatio) });
		polygon.setLineWidth(1);
		return polygon;
	}

	public static Polygon createEventEscalation(Shape shape) {
		final int eventHeight = shape.getContainer().getGraphicsAlgorithm().getHeight();
		final int eventWidth = shape.getContainer().getGraphicsAlgorithm().getWidth();
		
		final float heightRatio = calculateRatio(eventHeight, Float.valueOf(EVENT_SIZE));
		final float widthRatio = calculateRatio(eventWidth, Float.valueOf(EVENT_SIZE));
		
		int heightRadius = eventHeight / 2;
		int widthRadius = eventWidth / 2;
		
		int[] points = { widthRadius, generateRatioPointValue(8, heightRatio),
						 widthRadius + generateRatioPointValue(8, widthRatio), heightRadius + generateRatioPointValue(9, heightRatio),
						 widthRadius, heightRadius + generateRatioPointValue(2, heightRatio),
						 widthRadius - generateRatioPointValue(8, widthRatio), heightRadius + generateRatioPointValue(9, heightRatio) };
		Polygon polygon = gaService.createPolygon(shape, points);
		polygon.setLineWidth(1);
		return polygon;
	}

	public static Compensation createEventCompensation(Shape shape) {
		final int eventHeight = shape.getContainer().getGraphicsAlgorithm().getHeight();
		final int eventWidth = shape.getContainer().getGraphicsAlgorithm().getWidth();
		
		final float heightRatio = calculateRatio(eventHeight, Float.valueOf(EVENT_SIZE));
		final float widthRatio = calculateRatio(eventWidth, Float.valueOf(EVENT_SIZE));
		
		Rectangle rect = gaService.createInvisibleRectangle(shape);

		int w = generateRatioPointValue(22, widthRatio);
		int h = generateRatioPointValue(18, heightRatio);
		gaService.setLocationAndSize(rect, 
				generateRatioPointValue(5, widthRatio), generateRatioPointValue(9, heightRatio), w, h);

		int _w = w / 2;
		int _h = h / 2;
		int[] pontsArrow1 = { _w, 0, _w, h, 0, _h };
		Polygon arrow1 = gaService.createPolygon(rect, pontsArrow1);

		int[] pontsArrow2 = { w, 0, w, h, w / 2, _h };
		Polygon arrow2 = gaService.createPolygon(rect, pontsArrow2);

		Compensation compensation = new Compensation();
		compensation.arrow1 = arrow1;
		compensation.arrow2 = arrow2;
		return compensation;
	}

	public static Polygon createEventLink(Shape shape) {
		final int eventHeight = shape.getContainer().getGraphicsAlgorithm().getHeight();
		final int eventWidth = shape.getContainer().getGraphicsAlgorithm().getWidth();
		
		final float heightRatio = calculateRatio(eventHeight, Float.valueOf(EVENT_SIZE));
		final float widthRatio = calculateRatio(eventWidth, Float.valueOf(EVENT_SIZE));
		
		int heightRadius = eventHeight / 2;

		int[] points = { 
				generateRatioPointValue(32, widthRatio), heightRadius,
				generateRatioPointValue(23, widthRatio), heightRadius + generateRatioPointValue(11, heightRatio),
				generateRatioPointValue(23, widthRatio), heightRadius + generateRatioPointValue(6, heightRatio),
				generateRatioPointValue(5, widthRatio), heightRadius + generateRatioPointValue(6, heightRatio),
				generateRatioPointValue(5, widthRatio), heightRadius - generateRatioPointValue(6, heightRatio),
				generateRatioPointValue(23, widthRatio), heightRadius - generateRatioPointValue(6, heightRatio),
				generateRatioPointValue(23, widthRatio), heightRadius - generateRatioPointValue(11, heightRatio)};
		Polygon polygon = gaService.createPolygon(shape, points);
		polygon.setLineWidth(1);
		return polygon;
	}

	public static Polygon createEventError(Shape shape) {
		final int eventHeight = shape.getContainer().getGraphicsAlgorithm().getHeight();
		final int eventWidth = shape.getContainer().getGraphicsAlgorithm().getWidth();
		
		final float heightRatio = calculateRatio(eventHeight, Float.valueOf(EVENT_SIZE));
		final float widthRatio = calculateRatio(eventWidth, Float.valueOf(EVENT_SIZE));
		
		int heightRadius = eventHeight / 2;
		int widthRadius = eventWidth / 2;
		
		int[] points = { 
				widthRadius + generateRatioPointValue(4, widthRatio), heightRadius,
				widthRadius + generateRatioPointValue(10, widthRatio), heightRadius - generateRatioPointValue(10, heightRatio),
				widthRadius + generateRatioPointValue(7, widthRatio), heightRadius + generateRatioPointValue(10, heightRatio),
				widthRadius - generateRatioPointValue(4, widthRatio), heightRadius,
				widthRadius - generateRatioPointValue(10, widthRatio), heightRadius + generateRatioPointValue(10, heightRatio),
				widthRadius - generateRatioPointValue(7, widthRatio), heightRadius - generateRatioPointValue(10, heightRatio)};
		Polygon polygon = gaService.createPolygon(shape, points);
		polygon.setLineWidth(1);
		return polygon;
	}

	public static Polygon createEventCancel(Shape shape) {
		final int eventHeight = shape.getContainer().getGraphicsAlgorithm().getHeight();
		final int eventWidth = shape.getContainer().getGraphicsAlgorithm().getWidth();
		
		final float heightRatio = calculateRatio(eventHeight, Float.valueOf(EVENT_SIZE));
		final float widthRatio = calculateRatio(eventWidth, Float.valueOf(EVENT_SIZE));
		
		int heightRadius = eventHeight / 2;
		int widthRadius = eventWidth / 2;
		
		int a1 = generateRatioPointValue(9, widthRatio);
		int a2 = generateRatioPointValue(9, heightRatio);
		int b1 = generateRatioPointValue(12, widthRatio);
		int b2 = generateRatioPointValue(12, heightRatio);
		int c1 = generateRatioPointValue(4, widthRatio);
		int c2 = generateRatioPointValue(4, heightRatio);
		int[] points = { widthRadius, heightRadius - c2,
						 widthRadius + a1, heightRadius - b2,
						 widthRadius + b1, heightRadius - a2,
						 widthRadius + c1, heightRadius,
						 widthRadius + b1, heightRadius + a2,
						 widthRadius + a1, heightRadius + b2,
						 widthRadius, heightRadius + c2,
						 widthRadius - a1, heightRadius + b2,
						 widthRadius - b1, heightRadius + a2,
						 widthRadius - c1, heightRadius,
						 widthRadius - b1, heightRadius - a2,
						 widthRadius - a1, heightRadius - b2 };
		Polygon polygon = gaService.createPolygon(shape, points);
		polygon.setLineWidth(1);
		return polygon;
	}

	public static Ellipse createEventTerminate(Shape terminateShape) {
		final int eventHeight = terminateShape.getContainer().getGraphicsAlgorithm().getHeight();
		final int eventWidth = terminateShape.getContainer().getGraphicsAlgorithm().getWidth();
		
		final float heightRatio = calculateRatio(eventHeight, Float.valueOf(EVENT_SIZE));
		final float widthRatio = calculateRatio(eventWidth, Float.valueOf(EVENT_SIZE));
		
		Ellipse ellipse = gaService.createEllipse(terminateShape);
		gaService.setLocationAndSize(ellipse,
				generateRatioPointValue(6, widthRatio), generateRatioPointValue(6, heightRatio),
				eventWidth - generateRatioPointValue(12, widthRatio), eventHeight - generateRatioPointValue(12, heightRatio));
		ellipse.setLineWidth(1);
		ellipse.setFilled(true);
		return ellipse;
	}

	public static Polygon createEventParallelMultiple(Shape shape) {
		int r = EVENT_SIZE / 2;
		int a = 3;
		int b = 11;
		int[] points = { r - a, r - b, r + a, r - b, r + a, r - a, r + b, r - a, r + b, r + a, r + a, r + a, r + a,
		        r + b, r - a, r + b, r - a, r + a, r - b, r + a, r - b, r - a, r - a, r - a };
		Polygon cross = gaService.createPolygon(shape, points);
		cross.setFilled(false);
		cross.setLineWidth(1);
		return cross;
	}

	public static void deleteEventShape(ContainerShape containerShape) {
		for (PictogramElement shape : containerShape.getChildren()) {
			if (shape.getLink() != null) {
				EList<EObject> objects = shape.getLink().getBusinessObjects();
				if (objects.size()>0 && objects.get(0) instanceof EventDefinition) {
					peService.deletePictogramElement(shape);
					break;
				}
			}
		}
	}

	/* OTHER */

	public static Envelope createEnvelope(GraphicsAlgorithmContainer gaContainer, int x, int y, int w, int h) {
		Rectangle rect = gaService.createRectangle(gaContainer);
		gaService.setLocationAndSize(rect, x, y, w, h);
		rect.setFilled(false);

		Polyline line = gaService.createPolyline(rect, new int[] { 0, 0, w / 2, h / 2, w, 0 });

		Envelope envelope = new Envelope();
		envelope.rect = rect;
		envelope.line = line;

		return envelope;
	}

	public static Polygon createDataArrow(Polygon p) {
		int[] points = { 4, 8, 14, 8, 14, 4, 18, 10, 14, 16, 14, 12, 4, 12 };
		Polygon arrow = gaService.createPolygon(p, points);
		arrow.setLineWidth(1);
		return arrow;
	}

	// ACTIVITY

	public static final int TASK_DEFAULT_WIDTH = 110;
	public static final int TASK_DEFAULT_HEIGHT = 50;
	public static final int TASK_IMAGE_SIZE = 16;

	public static final int SUB_PROCEESS_DEFAULT_WIDTH = 300;
	public static final int SUB_PROCESS_DEFAULT_HEIGHT = 300;

	public static final int MARKER_WIDTH = 10;
	public static final int MARKER_HEIGHT = 10;

	public static final String ACTIVITY_MARKER_CONTAINER = "activity.marker.container";
	public static final String ACTIVITY_MARKER_COMPENSATE = "activity.marker.compensate";
	public static final String ACTIVITY_MARKER_LOOP_CHARACTERISTIC = "activity.marker.loop.characteristic";
	public static final String ACTIVITY_MARKER_AD_HOC = "activity.marker.adhoc";
	public static final String ACTIVITY_MARKER_EXPAND = "activity.marker.expand";
	public static final String EVENT_MARKER_CONTAINER = "event.marker.container";

	public static Compensation createActivityMarkerCompensate(ContainerShape markerContainer) {
		GraphicsAlgorithmContainer algorithmContainer = createActivityMarkerGaContainer(markerContainer,
		        ACTIVITY_MARKER_COMPENSATE);
		return createCompensation(algorithmContainer, MARKER_WIDTH, MARKER_HEIGHT);
	}

	public static Loop createActivityMarkerStandardLoop(ContainerShape markerContainer) {
		GraphicsAlgorithmContainer algorithmContainer = createActivityMarkerGaContainer(markerContainer,
		        ACTIVITY_MARKER_LOOP_CHARACTERISTIC);

		int[] xy = { 8, 10, 10, 5, 5, 0, 0, 5, 3, 10 };
		int[] bend = { 0, 0, 3, 4, 4, 4, 4, 3, 3, 0 };
		Polyline circle = gaService.createPolyline(algorithmContainer, xy, bend);

		Loop loop = new Loop();
		loop.circle = circle;
		loop.arrow = gaService.createPolyline(algorithmContainer, new int[] { 5, 5, 5, 10, 0, 10 });
		return loop;
	}

	public static MultiInstance createActivityMarkerMultiParallel(ContainerShape markerContainer) {
		GraphicsAlgorithmContainer algorithmContainer = createActivityMarkerGaContainer(markerContainer,
		        ACTIVITY_MARKER_LOOP_CHARACTERISTIC);
		MultiInstance multiInstance = new MultiInstance();
		multiInstance.line1 = gaService.createPolyline(algorithmContainer, new int[] { 2, 0, 2, MARKER_HEIGHT });
		multiInstance.line2 = gaService.createPolyline(algorithmContainer, new int[] { 5, 0, 5, MARKER_HEIGHT });
		multiInstance.line3 = gaService.createPolyline(algorithmContainer, new int[] { 8, 0, 8, MARKER_HEIGHT });
		return multiInstance;
	}

	public static MultiInstance createActivityMarkerMultiSequential(ContainerShape markerContainer) {
		GraphicsAlgorithmContainer algorithmContainer = createActivityMarkerGaContainer(markerContainer,
		        ACTIVITY_MARKER_LOOP_CHARACTERISTIC);
		MultiInstance multiInstance = new MultiInstance();
		multiInstance.line1 = gaService.createPolyline(algorithmContainer, new int[] { 0, 2, MARKER_WIDTH, 2 });
		multiInstance.line2 = gaService.createPolyline(algorithmContainer, new int[] { 0, 5, MARKER_WIDTH, 5 });
		multiInstance.line3 = gaService.createPolyline(algorithmContainer, new int[] { 0, 8, MARKER_WIDTH, 8 });
		return multiInstance;
	}

	public static Polyline createActivityMarkerAdHoc(ContainerShape markerContainer) {
		GraphicsAlgorithmContainer algorithmContainer = createActivityMarkerGaContainer(markerContainer,
		        ACTIVITY_MARKER_AD_HOC);
		int[] xy = { 0, 8, 3, 2, 7, 8, 10, 2 };
		int[] bend = { 0, 3, 3, 3, 3, 3, 3, 0 };
		return gaService.createPolyline(algorithmContainer, xy, bend);
	}

	public static Expand createActivityMarkerExpand(ContainerShape markerContainer) {
		GraphicsAlgorithmContainer algorithmContainer = createActivityMarkerGaContainer(markerContainer,
		        ACTIVITY_MARKER_EXPAND);

		Rectangle rect = gaService.createRectangle(algorithmContainer);
		rect.setFilled(false);
		gaService.setLocationAndSize(rect, 0, 0, 10, 10);

		Expand expand = new Expand();
		expand.rect = rect;
		expand.horizontal = gaService.createPolyline(algorithmContainer, new int[] { 0, 5, 10, 5 });
		expand.vertical = gaService.createPolyline(algorithmContainer, new int[] { 5, 0, 5, 10 });
		return expand;
	}

	public static void clearActivityMarker(ContainerShape markerContainer, String property) {

		int totalWidth = 0;
		int parentW = ((ContainerShape) markerContainer.eContainer()).getGraphicsAlgorithm().getWidth();
		int lastX = -1;

		Iterator<Shape> iterator = peService.getAllContainedShapes(markerContainer).iterator();
		while (iterator.hasNext()) {
			Shape shape = iterator.next();
			String value = peService.getPropertyValue(shape, property);
			GraphicsAlgorithm ga = shape.getGraphicsAlgorithm();
			if (value != null && new Boolean(value)) {
				lastX = ga.getX();
				peService.deletePictogramElement(shape);
			} else {
				totalWidth += ga.getWidth();
				if (lastX != -1) {
					gaService.setLocation(ga, lastX, ga.getY(), true);
					lastX = ga.getX() + ga.getWidth();
				}
			}
		}

		totalWidth = totalWidth == 0 ? 10 : totalWidth;
		GraphicsAlgorithm ga = markerContainer.getGraphicsAlgorithm();
		gaService.setLocationAndSize(ga, (parentW / 2) - (totalWidth / 2), ga.getY(), totalWidth, MARKER_HEIGHT);
	}

	private static GraphicsAlgorithmContainer createActivityMarkerGaContainer(ContainerShape markerContainer,
	        String property) {
		GraphicsAlgorithm ga = markerContainer.getGraphicsAlgorithm();

		int totalWidth = MARKER_WIDTH;
		int parentW = ((ContainerShape) markerContainer.eContainer()).getGraphicsAlgorithm().getWidth();
		int lastX = 0;

		Iterator<Shape> iterator = peService.getAllContainedShapes(markerContainer).iterator();
		while (iterator.hasNext()) {
			Shape containedShape = (Shape) iterator.next();
			GraphicsAlgorithm containedGa = containedShape.getGraphicsAlgorithm();
			totalWidth += containedGa.getWidth();
			lastX = containedGa.getX() + containedGa.getWidth();
		}

		gaService.setLocationAndSize(ga, (parentW / 2) - (totalWidth / 2), ga.getY(), totalWidth, MARKER_HEIGHT);

		Shape shape = peService.createShape(markerContainer, false);
		peService.setPropertyValue(shape, property, Boolean.toString(true));
		Rectangle invisibleRect = gaService.createInvisibleRectangle(shape);
		gaService.setLocationAndSize(invisibleRect, lastX, 0, MARKER_WIDTH, MARKER_HEIGHT);

		return invisibleRect;
	}

	private static Compensation createCompensation(GraphicsAlgorithmContainer container, int w, int h) {
		int[] xy = { 0, h / 2, w / 2, 0, w / 2, h };
		Polygon arrow1 = gaService.createPolygon(container, xy);
		arrow1.setFilled(false);

		xy = new int[] { w / 2, h / 2, w, 0, w, h };
		Polygon arrow2 = gaService.createPolygon(container, xy);
		arrow2.setFilled(false);

		Compensation compensation = new Compensation();
		compensation.arrow1 = arrow1;
		compensation.arrow2 = arrow2;

		return compensation;
	}

	/**
	 * Check if the given Point is with a given distance of the given Location.
	 * 
	 * @param p - the Point to check
	 * @param loc - the target Location
	 * @param dist - the maximum distance horizontally and vertically from the given Location
	 * @return true if the point lies within the rectangular area of the Location.
	 */
	public static boolean isPointNear(Point p, ILocation loc, int dist) {
		int x = p.getX();
		int y = p.getY();
		int lx = loc.getX();
		int ly = loc.getY();
		return lx-dist <= x && x <= lx+dist && ly-dist <= y && y <= ly+dist;
	}

	public static void setEventSize(int width, int height, Diagram diagram) {
		if (diagramSizeMap == null) {
			diagramSizeMap = new HashMap<Diagram, GraphicsUtil.SizeTemplate>();
			SizeTemplate temp = new SizeTemplate();
			temp.setEventSize(new Size(EVENT_SIZE, EVENT_SIZE));
			temp.setGatewaySize(new Size(GATEWAY_RADIUS*2, GATEWAY_RADIUS*2));
		}
		
		SizeTemplate sizeTemplate = diagramSizeMap.get(diagram);
		if (sizeTemplate == null) {
			sizeTemplate = new SizeTemplate();
			diagramSizeMap.put(diagram, sizeTemplate);
		}
		sizeTemplate.setEventSize(new Size(width, height));
	}

	public static void setGatewaySize(int width, int height, Diagram diagram) {
		if (diagramSizeMap == null) {
			diagramSizeMap = new HashMap<Diagram, GraphicsUtil.SizeTemplate>();
		}
		
		SizeTemplate sizeTemplate = diagramSizeMap.get(diagram);
		if (sizeTemplate == null) {
			sizeTemplate = new SizeTemplate();
			diagramSizeMap.put(diagram, sizeTemplate);
		}
		sizeTemplate.setGatewaySize(new Size(width, height));
	}

	public static void setActivitySize(int width, int height, Diagram diagram) {
		if (diagramSizeMap == null) {
			diagramSizeMap = new HashMap<Diagram, GraphicsUtil.SizeTemplate>();
		}
		
		SizeTemplate sizeTemplate = diagramSizeMap.get(diagram);
		if (sizeTemplate == null) {
			sizeTemplate = new SizeTemplate();
			diagramSizeMap.put(diagram, sizeTemplate);
		}
		sizeTemplate.setActivitySize(new Size(width, height));
	}
	
	public static Size getEventSize(Diagram diagram) {
		if (diagramSizeMap != null) {
			SizeTemplate temp = diagramSizeMap.get(diagram);
			if (temp != null) {
				return temp.getEventSize();
			}
		}
		return new Size(EVENT_SIZE, EVENT_SIZE);
	}
	
	public static Size getGatewaySize(Diagram diagram) {
		if (diagramSizeMap != null) {
			SizeTemplate temp = diagramSizeMap.get(diagram);
			if (temp != null) {
				return temp.getGatewaySize();
			}
		}
		return new Size(GATEWAY_RADIUS*2, GATEWAY_RADIUS*2);
	}
	
	public static Size getActivitySize(Diagram diagram) {
		if (diagramSizeMap != null) {
			SizeTemplate temp = diagramSizeMap.get(diagram);
			if (temp != null) {
				return temp.getActivitySize();
			}
		}
		return new Size(TASK_DEFAULT_WIDTH, TASK_DEFAULT_HEIGHT);
	}
	
	public static boolean intersects(Shape shape1, Shape shape2) {
		ILayoutService layoutService = Graphiti.getLayoutService();
		ILocation loc2 = layoutService.getLocationRelativeToDiagram(shape2);
		int x2 = loc2.getX();
		int y2 = loc2.getY();
		int w2 = getShapeWidth(shape2);
		int h2 = getShapeHeight(shape2);
		return intersects(shape1, x2, y2, w2, h2);
	}
	
	public static boolean intersects(Shape shape1, int x2, int y2, int w2, int h2) {
		ILayoutService layoutService = Graphiti.getLayoutService();
		ILocation loc1 = layoutService.getLocationRelativeToDiagram(shape1);
		int x1 = loc1.getX();
		int y1 = loc1.getY();
		int w1 = getShapeWidth(shape1);
		int h1 = getShapeHeight(shape1);
		return intersects(x1, y1, w1, h1, x2, y2, w2, h2);
	}

	public static boolean intersects(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
		if(x2<=x1 || y1<=y2) {  
			int t1, t2, t3, t4;
			t1 = x1; x1 = x2; x2 = t1;  
			t2 = y1; y1 = y2; y2 = t2;  
			t3 = w1; w1 = w2; w2 = t3;  
			t4 = h1; h1 = h2; h2 = t4;  
		}  
		if( y2 + h2 < y1 || y1 + h1 < y2 ||  x2 + w2 < x1 || x1 + w1 < x2 ) {
			return false;
		}
		return true;
	}

	public static Shape getShapeForProperty(ContainerShape container, String propertyKey) {
		IPeService peService = Graphiti.getPeService();
		Iterator<Shape> iterator = peService.getAllContainedShapes(container).iterator();
		while (iterator.hasNext()) {
			Shape shape = iterator.next();
			String property = peService.getPropertyValue(shape, propertyKey);
			if (property != null && new Boolean(property)) {
				return shape;
			}
		}
		return null;
	}
}