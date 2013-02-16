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
package org.eclipse.bpmn2.modeler.ui.features.event;

import org.eclipse.bpmn2.IntermediateThrowEvent;
import org.eclipse.bpmn2.modeler.core.features.MultiUpdateFeature;
import org.eclipse.bpmn2.modeler.core.features.event.AbstractCreateEventFeature;
import org.eclipse.bpmn2.modeler.core.features.event.AbstractUpdateEventFeature;
import org.eclipse.bpmn2.modeler.core.features.event.AddEventFeature;
import org.eclipse.bpmn2.modeler.core.model.Bpmn2ModelerFactory;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.bpmn2.modeler.ui.ImageProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IPeService;

public class IntermediateThrowEventFeatureContainer extends AbstractEventFeatureContainer {

	@Override
	public boolean canApplyTo(Object o) {
		return super.canApplyTo(o) && o instanceof IntermediateThrowEvent;
	}

	@Override
	public ICreateFeature getCreateFeature(IFeatureProvider fp) {
		return new CreateIntermediateThrowEventFeature(fp);
	}

	@Override
	public IUpdateFeature getUpdateFeature(IFeatureProvider fp) {
		MultiUpdateFeature multiUpdate = new MultiUpdateFeature(fp);
		multiUpdate.addUpdateFeature(super.getUpdateFeature(fp));
		multiUpdate.addUpdateFeature(new UpdateIntermediateThrowEventFeature(fp));
		return multiUpdate;
	}

	@Override
	public IAddFeature getAddFeature(IFeatureProvider fp) {
		return new AddEventFeature(fp) {
			@Override
			protected void decorateEllipse(Ellipse e) {
				Ellipse circle = GraphicsUtil.createIntermediateEventCircle(e);
				circle.setForeground(manageColor(StyleUtil.CLASS_FOREGROUND));
			}

			@Override
			protected void hook(ContainerShape container) {
				IPeService peService = Graphiti.getPeService();
				IntermediateThrowEvent event = BusinessObjectUtil.getFirstElementOfType(container, IntermediateThrowEvent.class);
				peService.setPropertyValue(container,
						UpdateIntermediateThrowEventFeature.INTERMEDIATE_THROW_EVENT_MARKER,
						AbstractUpdateEventFeature.getEventDefinitionsValue(event));
			}
		};
	}

	public static class CreateIntermediateThrowEventFeature extends AbstractCreateEventFeature {

		public CreateIntermediateThrowEventFeature(IFeatureProvider fp) {
			super(fp, "抛出事件", "Throws the event trigger and the event immediately occurs");
		}

		@Override
		protected IntermediateThrowEvent createFlowElement(ICreateContext context) {
			IntermediateThrowEvent event = Bpmn2ModelerFactory.create(IntermediateThrowEvent.class);
			event.setName("Throw");
			return event;
		}

		@Override
		public String getStencilImageId() {
			return ImageProvider.IMG_16_INTERMEDIATE_THORW_EVENT;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.bpmn2.modeler.core.features.AbstractCreateFlowElementFeature#getFlowElementClass()
		 */
		@Override
		public Class getBusinessObjectClass() {
			return IntermediateThrowEvent.class;
		}
	}
	
	public static class UpdateIntermediateThrowEventFeature extends AbstractUpdateEventFeature {

		public static String INTERMEDIATE_THROW_EVENT_MARKER = "marker.intermediate.throw.event";

		/**
		 * @param fp
		 */
		public UpdateIntermediateThrowEventFeature(IFeatureProvider fp) {
			super(fp);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.bpmn2.modeler.core.features.activity.AbstractUpdateMarkerFeature#getPropertyKey()
		 */
		@Override
		protected String getPropertyKey() {
			return INTERMEDIATE_THROW_EVENT_MARKER;
		}
	}
}