package org.eclipse.bpmn2.modeler.ui.features.label;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.modeler.core.ModelHandlerLocator;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.FeatureSupport;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.impl.ResizeShapeContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

public class LayoutLabelFeature extends AbstractLayoutFeature {

	public LayoutLabelFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canLayout(ILayoutContext context) {
//		Object bo = BusinessObjectUtil.getFirstElementOfType(context.getPictogramElement(), BaseElement.class);
//		return bo != null && bo instanceof Activity;
		// TODO Auto-generated method stub
//		return false;
		return true;
	}

	@Override
	public boolean layout(ILayoutContext context) {
		ContainerShape containerShape = (ContainerShape) context.getPictogramElement();
		SubProcess subProcess = BusinessObjectUtil.getFirstElementOfType(containerShape, SubProcess.class);
		try {
			BPMNShape shape = (BPMNShape) ModelHandlerLocator.getModelHandler(getDiagram().eResource()).findDIElement(subProcess);
			
			if (shape.isIsExpanded()) {
				
				// SubProcess is expanded
				
				boolean needResize = false;
				GraphicsAlgorithm parentGa = containerShape.getGraphicsAlgorithm();
				
				for (PictogramElement pe : FeatureSupport.getContainerChildren(containerShape)) {
					GraphicsAlgorithm ga = pe.getGraphicsAlgorithm();
					if (ga!=null) {
						if (ga.getX() < 0 || ga.getY() < 0) {
							needResize = true;
							break;
						}
						if (ga.getX() + ga.getWidth() > parentGa.getWidth()) {
							needResize = true;
							break;
						}
						if (ga.getY() + ga.getHeight() > parentGa.getHeight()) {
							needResize = true;
							break;
						}
					}
				}
				if (needResize) {
					ResizeShapeContext resizeContext = new ResizeShapeContext(containerShape);
					resizeContext.setX(parentGa.getX());
					resizeContext.setY(parentGa.getY());
					resizeContext.setWidth(parentGa.getWidth());
					resizeContext.setHeight(parentGa.getHeight());
					IResizeShapeFeature resizeFeature = getFeatureProvider().getResizeShapeFeature(resizeContext);
					resizeFeature.resizeShape(resizeContext);
				}
				
				FeatureSupport.setContainerChildrenVisible(containerShape, true);
			}
			else {
				
				// SubProcess is collapsed
				
				FeatureSupport.setContainerChildrenVisible(containerShape, false);
			}
			
		} catch (Exception e) {
			// It's OK, I've played a programmer before...
			// e.printStackTrace();
		}
		
		return true;
	}

}
