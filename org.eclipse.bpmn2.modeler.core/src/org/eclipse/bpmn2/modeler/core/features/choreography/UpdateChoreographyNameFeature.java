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
package org.eclipse.bpmn2.modeler.core.features.choreography;

import java.util.Iterator;

import org.eclipse.bpmn2.ChoreographyActivity;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IPeService;

public class UpdateChoreographyNameFeature extends AbstractUpdateFeature {

	private static final IPeService peService = Graphiti.getPeService();

	public UpdateChoreographyNameFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		return BusinessObjectUtil.containsElementOfType(context.getPictogramElement(), ChoreographyActivity.class);
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		ChoreographyActivity activity = (ChoreographyActivity) BusinessObjectUtil.getFirstElementOfType(
				context.getPictogramElement(), ChoreographyActivity.class);
		if (activity.getName().equals(getBodyText(context).getValue())) {
			return Reason.createFalseReason();
		} else {
			return Reason.createTrueReason("Name is out of date");
		}
	}

	@Override
	public boolean update(IUpdateContext context) {
		ChoreographyActivity task = (ChoreographyActivity) BusinessObjectUtil.getFirstElementOfType(
				context.getPictogramElement(), ChoreographyActivity.class);
		getBodyText(context).setValue(task.getName());
		return true;
	}

	private Text getBodyText(IUpdateContext context) {
		Iterator<Shape> iterator = peService.getAllContainedShapes((ContainerShape) context.getPictogramElement())
				.iterator();
		while (iterator.hasNext()) {
			Shape shape = (Shape) iterator.next();
			String property = peService.getPropertyValue(shape, ChoreographyProperties.CHOREOGRAPHY_NAME);
			if (property != null && Boolean.parseBoolean(property)) {
				return (Text) shape.getGraphicsAlgorithm();
			}
		}
		return null;
	}
}