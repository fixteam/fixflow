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

import java.util.Iterator;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.AdHocSubProcess;
import org.eclipse.bpmn2.modeler.core.features.activity.AbstractCreateExpandableFlowNodeFeature;
import org.eclipse.bpmn2.modeler.core.model.Bpmn2ModelerFactory;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.bpmn2.modeler.ui.ImageProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IPeService;

public class AdHocSubProcessFeatureContainer extends AbstractSubProcessFeatureContainer {

	@Override
	public boolean canApplyTo(Object o) {
		return super.canApplyTo(o) && o instanceof AdHocSubProcess;
	}

	@Override
	public ICreateFeature getCreateFeature(IFeatureProvider fp) {
		return new CreateAdHocSubProcessFeature(fp);
	}

	@Override
	public IAddFeature getAddFeature(IFeatureProvider fp) {
		return new AddExpandedSubProcessFeature(fp) {
			@Override
			protected void hook(Activity activity, ContainerShape container, IAddContext context, int width, int height) {
				super.hook(activity, container, context, width, height);
				IPeService peService = Graphiti.getPeService();
				Iterator<Shape> iterator = peService.getAllContainedShapes(container).iterator();
				while (iterator.hasNext()) {
					Shape shape = iterator.next();
					String property = peService.getPropertyValue(shape, GraphicsUtil.ACTIVITY_MARKER_CONTAINER);
					if (property != null && new Boolean(property)) {
						Polyline tilde = GraphicsUtil.createActivityMarkerAdHoc((ContainerShape) shape);
						tilde.setForeground(manageColor(StyleUtil.CLASS_FOREGROUND));
					}
				}
			}
		};
	}

	public static class CreateAdHocSubProcessFeature extends AbstractCreateExpandableFlowNodeFeature<AdHocSubProcess> {

		public CreateAdHocSubProcessFeature(IFeatureProvider fp) {
			super(fp, "自由子流程",
					"A specialized description of Sub-Process that is a group of Activities that have no REQUIRED sequence relationships");
		}

		@Override
		protected AdHocSubProcess createFlowElement(ICreateContext context) {
			AdHocSubProcess adHocSubProcess = Bpmn2ModelerFactory.create(AdHocSubProcess.class);
			adHocSubProcess.setName("Ad-Hoc SubProcess");
			return adHocSubProcess;
		}

		@Override
		protected String getStencilImageId() {
			return ImageProvider.IMG_16_AD_HOC_SUB_PROCESS;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.bpmn2.modeler.core.features.AbstractCreateFlowElementFeature#getFlowElementClass()
		 */
		@Override
		public Class getBusinessObjectClass() {
			return AdHocSubProcess.class;
		}
	}
}