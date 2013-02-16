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
package org.eclipse.bpmn2.modeler.ui.features.activity.subprocess;

import static org.eclipse.bpmn2.modeler.ui.features.activity.subprocess.SubProcessFeatureContainer.TRIGGERED_BY_EVENT;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.modeler.core.features.activity.AbstractAddActivityFeature;
import org.eclipse.bpmn2.modeler.core.preferences.Bpmn2Preferences;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil.Expand;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.internal.services.impl.GaServiceImpl;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeService;

public class AddExpandedSubProcessFeature extends AbstractAddActivityFeature {

	public AddExpandedSubProcessFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	protected void hook(Activity activity, ContainerShape container, IAddContext context, int width, int height) {
		super.hook(activity, container, context, width, height);
		IPeService peService = Graphiti.getPeService();
		IGaService gaService = Graphiti.getGaService();

		boolean isTriggeredByEvent = false;
		if (activity instanceof SubProcess) {
			SubProcess subprocess = (SubProcess) activity;
			isTriggeredByEvent = subprocess.isTriggeredByEvent();
		}
		peService.setPropertyValue(container, TRIGGERED_BY_EVENT, Boolean.toString(isTriggeredByEvent));

		Shape textShape = peService.createShape(container, false);
		Text text = gaService.createDefaultText(getDiagram(), textShape, activity.getName());
		gaService.setLocationAndSize(text, 5, 5, width - 10, 15);
		StyleUtil.applyStyle(text, activity);
		text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
		text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
//		text.setFont(gaService.manageFont(getDiagram(), GaServiceImpl.DEFAULT_FONT, 8, false, true));
		link(textShape, activity);

		// TODO: should this go into the UpdateFeature?
		// see UpdateCallActivityFeature for implementation
//		ContainerShape markerContainer = (ContainerShape) GraphicsUtil.getShapeForProperty(container,
//				GraphicsUtil.ACTIVITY_MARKER_CONTAINER);
//		
//		Expand expand = GraphicsUtil.createActivityMarkerExpand(markerContainer);
//		expand.rect.setForeground(manageColor(StyleUtil.CLASS_FOREGROUND));
//		expand.horizontal.setForeground(manageColor(StyleUtil.CLASS_FOREGROUND));
//		expand.vertical.setForeground(manageColor(StyleUtil.CLASS_FOREGROUND));
	}

	@Override
	public int getWidth() {
		if (Bpmn2Preferences.getInstance().isExpandedDefault())
			return GraphicsUtil.SUB_PROCEESS_DEFAULT_WIDTH;
		return GraphicsUtil.TASK_DEFAULT_WIDTH;
	}

	@Override
	public int getHeight() {
		if (Bpmn2Preferences.getInstance().isExpandedDefault())
			return GraphicsUtil.SUB_PROCESS_DEFAULT_HEIGHT;
		return GraphicsUtil.TASK_DEFAULT_HEIGHT;
	}
}