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
 * @author Bob Brodt
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.ui.features.activity.subprocess;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.modeler.core.ModelHandlerLocator;
import org.eclipse.bpmn2.modeler.core.features.DefaultResizeBPMNShapeFeature;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.FeatureSupport;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.impl.ResizeShapeContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;

public class ResizeSubProcessFeature extends DefaultResizeBPMNShapeFeature {
	public final static int MARGIN = 20;
	
	public ResizeSubProcessFeature(IFeatureProvider fp) {
		super(fp);
	}
	
	@Override
	public void resizeShape(IResizeShapeContext context) {

		ResizeShapeContext resizeShapeContext = (ResizeShapeContext)context;

		ContainerShape containerShape = (ContainerShape) context.getPictogramElement();
		Activity activity = BusinessObjectUtil.getFirstElementOfType(containerShape, Activity.class);
		try {
			BPMNShape shape = (BPMNShape) ModelHandlerLocator.getModelHandler(getDiagram().eResource()).findDIElement(activity);
			
			if (shape.isIsExpanded()) {

				// SubProcess is expanded
				
				GraphicsAlgorithm parentGa = containerShape.getGraphicsAlgorithm();
				int newWidth = resizeShapeContext.getWidth();
				int newHeight = resizeShapeContext.getHeight();
				SizeCalculator sizeCalc = new SizeCalculator(containerShape);
				int shiftX = sizeCalc.shiftX;
				int shiftY = sizeCalc.shiftY;
				int minWidth = sizeCalc.minWidth;
				int minHeight = sizeCalc.minHeight;
				
				if (shiftX < 0) {
					for (PictogramElement pe : FeatureSupport.getContainerChildren(containerShape)) {
						GraphicsAlgorithm childGa = pe.getGraphicsAlgorithm();
						if (childGa!=null) {
							int x = childGa.getX() - shiftX + MARGIN;
							childGa.setX(x);
						}
					}
					resizeShapeContext.setX(resizeShapeContext.getX() + shiftX - MARGIN);
					shiftX = MARGIN;
				}
				
				if (shiftY < 0) {
					for (PictogramElement pe : FeatureSupport.getContainerChildren(containerShape)) {
						GraphicsAlgorithm childGa = pe.getGraphicsAlgorithm();
						if (childGa!=null) {
							int y = childGa.getY() - shiftY + MARGIN;
							childGa.setY(y);
						}
					}
					resizeShapeContext.setY(resizeShapeContext.getY() + shiftY - MARGIN);
					shiftX = MARGIN;
				}

				if (shiftX < MARGIN)
					shiftX = MARGIN;
				if (shiftY < MARGIN)
					shiftY = MARGIN;
				minWidth += 2 * MARGIN;
				minHeight += 2 * MARGIN;

				if (newWidth < minWidth) {
					parentGa.setWidth(minWidth);
				}
				if (newWidth < shiftX + minWidth) {
					int shift = shiftX + minWidth - newWidth;
					if (shift>shiftX-MARGIN) {
						shift = shiftX-MARGIN;
					}
					if (shift>0) {
						for (PictogramElement pe : FeatureSupport.getContainerChildren(containerShape)) {
							GraphicsAlgorithm childGa = pe.getGraphicsAlgorithm();
							if (childGa!=null) {
								int x = childGa.getX() - shift;
								childGa.setX(x);
							}
						}
					}
				}
				if (newHeight < minHeight) {
					parentGa.setHeight(minHeight);
				}
				if (newHeight < shiftY + minHeight) {
					int shift = shiftY + minHeight - newHeight;
					if (shift>shiftY-MARGIN) {
						shift = shiftY-MARGIN;
					}
					if (shift>0) {
						for (PictogramElement pe : FeatureSupport.getContainerChildren(containerShape)) {
							GraphicsAlgorithm childGa = pe.getGraphicsAlgorithm();
							if (childGa!=null) {
								int y = childGa.getY() - shift;
								childGa.setY(y);
							}
						}
					}
				}
				
				if (resizeShapeContext.getWidth() < minWidth)
					resizeShapeContext.setWidth(minWidth);
				if (resizeShapeContext.getHeight() < minHeight)
					resizeShapeContext.setHeight(minHeight);
			}
			else {
				
				// SubProcess is collapsed
				
				for (PictogramElement pe : FeatureSupport.getContainerDecorators(containerShape)) {
					GraphicsAlgorithm childGa = pe.getGraphicsAlgorithm();
					if (childGa!=null) {
						childGa.setWidth(GraphicsUtil.getActivitySize(getDiagram()).getWidth());
						childGa.setHeight(GraphicsUtil.getActivitySize(getDiagram()).getHeight());
					}
				}
				
				resizeShapeContext.setWidth(GraphicsUtil.getActivitySize(getDiagram()).getWidth());
				resizeShapeContext.setHeight(GraphicsUtil.getActivitySize(getDiagram()).getHeight());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Graphiti.getPeService().sendToFront(containerShape);
		
		super.resizeShape(context);
	}
	
	public static class SizeCalculator {
		
		int shiftX;
		int shiftY;
		int minWidth;
		int minHeight;
		ContainerShape containerShape;
		
		public SizeCalculator(ContainerShape containerShape) {
			setShape(containerShape);
		}
		
		public void setShape(ContainerShape containerShape) {
			this.containerShape = containerShape;
			calculate();
		}
		
		private void calculate() {
			int minX = Integer.MAX_VALUE;
			int minY = Integer.MAX_VALUE;
			minWidth = 0;
			minHeight = 0;

			for (PictogramElement pe : FeatureSupport.getContainerChildren(containerShape)) {
				GraphicsAlgorithm ga = pe.getGraphicsAlgorithm();
				if (ga!=null) {
					int x = ga.getX();
					int y = ga.getY();
					if (x < minX)
						minX = x;
					if (y < minY)
						minY = y;
				}
			}
			
			shiftX = minX;
			shiftY = minY;
			
			for (PictogramElement pe : FeatureSupport.getContainerChildren(containerShape)) {
				GraphicsAlgorithm ga = pe.getGraphicsAlgorithm();
				if (ga!=null) {
					int w = ga.getX() - minX + ga.getWidth();
					int h = ga.getY() - minY + ga.getHeight();
					if (w > minWidth)
						minWidth = w;
					if (h > minHeight)
						minHeight = h;
				}
			}
			if (minWidth<=0)
				minWidth = GraphicsUtil.TASK_DEFAULT_WIDTH;
			if (minHeight<=0)
				minHeight = GraphicsUtil.TASK_DEFAULT_HEIGHT;
		}

		public int getShiftX() {
			return shiftX;
		}

		public int getShiftY() {
			return shiftY;
		}
		
		public int getWidth() {
			return minWidth;
		}
		
		public int getHeight() {
			return minHeight;
		}
	}
}
