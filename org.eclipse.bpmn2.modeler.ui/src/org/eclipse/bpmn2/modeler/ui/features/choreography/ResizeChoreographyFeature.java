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

import java.util.List;

import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.modeler.core.Activator;
import org.eclipse.bpmn2.modeler.core.features.DefaultResizeBPMNShapeFeature;
import org.eclipse.bpmn2.modeler.core.features.choreography.ChoreographyProperties;
import org.eclipse.bpmn2.modeler.core.utils.Tuple;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;

public class ResizeChoreographyFeature extends DefaultResizeBPMNShapeFeature {

	public ResizeChoreographyFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canResizeShape(IResizeShapeContext context) {
		try {
			List<BPMNShape> bands = ChoreographyUtil.getParicipantBandBpmnShapes((ContainerShape) context
					.getPictogramElement());
			int h = ChoreographyProperties.TEXT_H; // + ChoreographyProperties.MARKER_H;

			for (BPMNShape shape : bands) {
				h += shape.getBounds().getHeight();
			}

			return context.getHeight() > 0 ? context.getHeight() > h : true;
		} catch (Exception e) {
			Activator.logError(e);
			return true;
		}
	}

	@Override
	public void resizeShape(IResizeShapeContext context) {
		try {
			List<ContainerShape> bands = ChoreographyUtil.getParticipantBandContainerShapes((ContainerShape) context
					.getPictogramElement());
			Tuple<List<ContainerShape>, List<ContainerShape>> topAndBottom = ChoreographyUtil
					.getTopAndBottomBands(bands);
			ChoreographyUtil.resizePartipantBandContainerShapes(context.getWidth(), context.getHeight(),
					topAndBottom.getFirst(), topAndBottom.getSecond(), getDiagram());
		} catch (Exception e) {
			Activator.logError(e);
		}
		super.resizeShape(context);
		ChoreographyUtil.moveChoreographyMessageLinks((ContainerShape) context.getPictogramElement());
	}

}