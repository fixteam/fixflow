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

import static org.eclipse.bpmn2.modeler.core.features.choreography.ChoreographyProperties.TEXT_H;

import java.util.List;

import org.eclipse.bpmn2.ChoreographyLoopType;
import org.eclipse.bpmn2.SubChoreography;
import org.eclipse.bpmn2.modeler.core.features.activity.AbstractCreateExpandableFlowNodeFeature;
import org.eclipse.bpmn2.modeler.core.features.choreography.LayoutChoreographyFeature;
import org.eclipse.bpmn2.modeler.core.model.Bpmn2ModelerFactory;
import org.eclipse.bpmn2.modeler.core.utils.Tuple;
import org.eclipse.bpmn2.modeler.ui.ImageProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;

public class SubChoreographyFeatureContainer extends AbstractChoreographyFeatureContainer {

	@Override
	public boolean canApplyTo(Object o) {
		return super.canApplyTo(o) && o instanceof SubChoreography;
	}

	@Override
	public ICreateFeature getCreateFeature(IFeatureProvider fp) {
		return new CreateSubChoreographyFeature(fp);
	}

	@Override
	public IAddFeature getAddFeature(IFeatureProvider fp) {
		return new AddSubChoreographyFeature(fp);
	}

	@Override
	public ILayoutFeature getLayoutFeature(IFeatureProvider fp) {
		return new LayoutChoreographyFeature(fp) {
			@Override
			protected void setTextLocation(ContainerShape choreographyContainer, Text text, int w, int h) {
				List<ContainerShape> bandContainers = ChoreographyUtil
						.getParticipantBandContainerShapes(choreographyContainer);
				Tuple<List<ContainerShape>, List<ContainerShape>> topAndBottomBands = ChoreographyUtil
						.getTopAndBottomBands(bandContainers);
				List<ContainerShape> topBands = topAndBottomBands.getFirst();

				int y = 3;
				if (!topBands.isEmpty()) {
					ContainerShape containerShape = topBands.get(topBands.size() - 1);
					GraphicsAlgorithm ga = containerShape.getGraphicsAlgorithm();
					y = ga.getY() + ga.getHeight() + 3;
				}

				gaService.setLocationAndSize(text, 0, y, w, TEXT_H);
			}
		};
	}

	public static class CreateSubChoreographyFeature extends AbstractCreateExpandableFlowNodeFeature<SubChoreography> {

		public CreateSubChoreographyFeature(IFeatureProvider fp) {
			super(fp, "Sub-Choreography", "A compound activity that can contain other activities");
		}

		@Override
		protected SubChoreography createFlowElement(ICreateContext context) {
			SubChoreography subChoreography = Bpmn2ModelerFactory.create(SubChoreography.class);
			subChoreography.setName("Sub-Choreography");
			subChoreography.setLoopType(ChoreographyLoopType.NONE);
			return subChoreography;
		}

		@Override
		public String getStencilImageId() {
			return ImageProvider.IMG_16_CHOREOGRAPHY_TASK;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.bpmn2.modeler.core.features.AbstractCreateFlowElementFeature#getFlowElementClass()
		 */
		@Override
		public Class getBusinessObjectClass() {
			return SubChoreography.class;
		}
	}
}