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
package org.eclipse.bpmn2.modeler.ui.features.activity;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.modeler.core.features.BaseElementFeatureContainer;
import org.eclipse.bpmn2.modeler.core.features.DefaultResizeBPMNShapeFeature;
import org.eclipse.bpmn2.modeler.core.features.MultiUpdateFeature;
import org.eclipse.bpmn2.modeler.core.features.activity.MoveActivityFeature;
import org.eclipse.bpmn2.modeler.core.features.activity.UpdateActivityCompensateMarkerFeature;
import org.eclipse.bpmn2.modeler.core.features.activity.UpdateActivityLoopAndMultiInstanceMarkerFeature;
import org.eclipse.bpmn2.modeler.core.features.event.AbstractBoundaryEventOperation;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.ui.features.AbstractDefaultDeleteFeature;
import org.eclipse.bpmn2.modeler.ui.features.event.AppendEventFeature;
import org.eclipse.bpmn2.modeler.ui.features.gateway.AppendGatewayFeature;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IMoveShapeFeature;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.impl.DeleteContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;

public abstract class AbstractActivityFeatureContainer extends BaseElementFeatureContainer {

	@Override
	public MultiUpdateFeature getUpdateFeature(IFeatureProvider fp) {
		UpdateActivityCompensateMarkerFeature compensateMarkerUpdateFeature = new UpdateActivityCompensateMarkerFeature(
				fp);
		UpdateActivityLoopAndMultiInstanceMarkerFeature loopAndMultiInstanceUpdateFeature = new UpdateActivityLoopAndMultiInstanceMarkerFeature(
				fp);
		MultiUpdateFeature multiUpdate = new MultiUpdateFeature(fp);
		multiUpdate.addUpdateFeature(compensateMarkerUpdateFeature);
		multiUpdate.addUpdateFeature(loopAndMultiInstanceUpdateFeature);
		return multiUpdate;
	}

	@Override
	public IResizeShapeFeature getResizeFeature(IFeatureProvider fp) {
		return new DefaultResizeBPMNShapeFeature(fp);
	}

	@Override
	public IMoveShapeFeature getMoveFeature(IFeatureProvider fp) {
		return new MoveActivityFeature(fp);
	}

	@Override
	public IDeleteFeature getDeleteFeature(IFeatureProvider fp) {
		return new AbstractDefaultDeleteFeature(fp) {
			@Override
			public void delete(final IDeleteContext context) {
				Activity activity = BusinessObjectUtil.getFirstElementOfType(context.getPictogramElement(),
						Activity.class);
				new AbstractBoundaryEventOperation() {
					@Override
					protected void doWorkInternal(ContainerShape container) {
						IDeleteContext delete = new DeleteContext(container);
						getFeatureProvider().getDeleteFeature(delete).delete(delete);
					}
				}.doWork(activity, getDiagram());
				super.delete(context);
			}
		};
	}

	@Override
	public ICustomFeature[] getCustomFeatures(IFeatureProvider fp) {
		return new ICustomFeature[] {
			new AppendActivityFeature(fp),
			new AppendGatewayFeature(fp),
			new AppendEventFeature(fp)
		};
	}
}