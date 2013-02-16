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

import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.modeler.core.ModelHandlerLocator;
import org.eclipse.bpmn2.modeler.ui.ImageProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.impl.ResizeShapeContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

// NOT USED YET
public class ExpandSubProcessFeature extends AbstractCustomFeature {

	public ExpandSubProcessFeature(IFeatureProvider fp) {
	    super(fp);
    }
	
	@Override
	public String getName() {
//	    return "Expand";
		return "展开";
	}
	
	@Override
	public String getDescription() {
//	    return "Expand the Sub-Process and show contents";
		return "展开子流程并显示内容";
	}

	@Override
	public String getImageId() {
		return ImageProvider.IMG_16_EXPAND;
	}

	@Override
	public boolean isAvailable(IContext context) {
		return true;
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		boolean ret = false;
		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length == 1) {
			Object bo = getBusinessObjectForPictogramElement(pes[0]);
			if (bo instanceof SubProcess) {
				try {
					BPMNShape bpmnShape = (BPMNShape) ModelHandlerLocator.getModelHandler(getDiagram().eResource()).findDIElement((SubProcess)bo);
					if (!bpmnShape.isIsExpanded())
						ret = true;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return ret;
	}

	@Override
	public void execute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length == 1) {
			PictogramElement pe0 = pes[0];
			Object bo = getBusinessObjectForPictogramElement(pe0);
			if (pe0 instanceof ContainerShape && bo instanceof SubProcess) {
				ContainerShape containerShape = (ContainerShape)pe0;
				SubProcess subProcess = (SubProcess)bo;
				try {
					BPMNShape bpmnShape = (BPMNShape) ModelHandlerLocator.getModelHandler(getDiagram().eResource()).findDIElement(subProcess);
					if (!bpmnShape.isIsExpanded()) {
						
						// SubProcess is collapsed - resize to minimum size such that all children are visible
						// NOTE: children tasks will be set visible in LayoutSubProcessFeature

						bpmnShape.setIsExpanded(true);
						
						GraphicsAlgorithm ga = containerShape.getGraphicsAlgorithm();
						ResizeShapeContext resizeContext = new ResizeShapeContext(containerShape);
						IResizeShapeFeature resizeFeature = getFeatureProvider().getResizeShapeFeature(resizeContext);
						int oldWidth = ga.getWidth();
						int oldHeight = ga.getHeight();
						ResizeSubProcessFeature.SizeCalculator newSize = new ResizeSubProcessFeature.SizeCalculator(containerShape);
						int newWidth = newSize.getWidth();
						int newHeight = newSize.getHeight();
						resizeContext.setX(ga.getX()/* + oldWidth/2 - newWidth/2*/);
						resizeContext.setY(ga.getY()/* + oldHeight/2 - newHeight/2*/);
						resizeContext.setWidth(newWidth);
						resizeContext.setHeight(newHeight);
						resizeFeature.resizeShape(resizeContext);
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}