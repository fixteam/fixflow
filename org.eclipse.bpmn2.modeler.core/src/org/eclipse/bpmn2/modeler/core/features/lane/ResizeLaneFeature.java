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
package org.eclipse.bpmn2.modeler.core.features.lane;

import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.modeler.core.features.DefaultResizeBPMNShapeFeature;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.FeatureSupport;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;

public class ResizeLaneFeature extends DefaultResizeBPMNShapeFeature {

	public ResizeLaneFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canResizeShape(IResizeShapeContext context) {
		boolean isLane = FeatureSupport.isLane(context.getPictogramElement());
		if (!isLane) {
			return false;
		}

		boolean isParentLane = FeatureSupport.isLane(((ContainerShape) context.getPictogramElement()).getContainer());
		if (!isParentLane) {
			return true;
		}

		if (context.getHeight() == -1 && context.getWidth() == -1) {
			return true;
		}

		GraphicsAlgorithm ga = ((ContainerShape) context.getPictogramElement()).getGraphicsAlgorithm();

		int i = compare(ga.getHeight(), ga.getWidth(), context.getHeight(), context.getWidth());

		Lane lane = (Lane) BusinessObjectUtil.getFirstElementOfType(context.getPictogramElement(), Lane.class);

		if (i < 0 && lane.getFlowNodeRefs().size() == 0) {
			return true;
		}

		if (i > 0) {
			return true;
		}

		return true;
	}

	private int compare(int heightBefore, int widthBefore, int heightAfter, int widthAfter) {
		if (heightAfter > heightBefore || widthAfter > widthBefore) {
			return 1;
		}
		if (heightAfter < heightBefore || widthAfter < widthBefore) {
			return -1;
		}
		return 0;
	}
}
