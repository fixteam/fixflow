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

import org.eclipse.bpmn2.modeler.core.utils.Tuple;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;

public class AddSubChoreographyFeature extends AddChoreographyFeature {

	public AddSubChoreographyFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	protected void setTextLocation(ContainerShape choreographyContainer, Text text, int w, int h) {
		List<ContainerShape> bandContainers = ChoreographyUtil.getParticipantBandContainerShapes(choreographyContainer);
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

}