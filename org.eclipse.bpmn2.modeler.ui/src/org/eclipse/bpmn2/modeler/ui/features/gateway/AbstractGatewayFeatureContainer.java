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
package org.eclipse.bpmn2.modeler.ui.features.gateway;

import org.eclipse.bpmn2.modeler.core.features.BaseElementFeatureContainer;
import org.eclipse.bpmn2.modeler.core.features.ContextConstants;
import org.eclipse.bpmn2.modeler.core.features.MoveFlowNodeFeature;
import org.eclipse.bpmn2.modeler.core.features.UpdateBaseElementNameFeature;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.bpmn2.modeler.ui.features.AbstractDefaultDeleteFeature;
import org.eclipse.bpmn2.modeler.ui.features.LayoutBaseElementTextFeature;
import org.eclipse.bpmn2.modeler.ui.features.activity.AppendActivityFeature;
import org.eclipse.bpmn2.modeler.ui.features.choreography.AddChoreographyMessageFeature;
import org.eclipse.bpmn2.modeler.ui.features.event.AppendEventFeature;
import org.eclipse.bpmn2.modeler.ui.features.participant.RotatePoolFeature;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IDirectEditingFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IMoveShapeFeature;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.features.impl.DefaultResizeShapeFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;

public abstract class AbstractGatewayFeatureContainer extends BaseElementFeatureContainer {

	@Override
	public Object getApplyObject(IContext context) {
		if (context.getProperty(ContextConstants.LABEL_CONTEXT) == null
				|| !((Boolean) context.getProperty(ContextConstants.LABEL_CONTEXT))) {
			return super.getApplyObject(context);
		}
		return null;
	}

	@Override
	public IUpdateFeature getUpdateFeature(IFeatureProvider fp) {
		return new UpdateAbstractGatewayFeature(fp);
	}

	@Override
	public IDirectEditingFeature getDirectEditingFeature(IFeatureProvider fp) {
		return null; // TODO
	}

	@Override
	public ILayoutFeature getLayoutFeature(IFeatureProvider fp) {
		return new LayoutBaseElementTextFeature(fp) {

			@Override
			public int getMinimumWidth() {
				return 2 * GraphicsUtil.GATEWAY_RADIUS;
			}
		};
	}

	@Override
	public IMoveShapeFeature getMoveFeature(IFeatureProvider fp) {
		return new MoveFlowNodeFeature(fp);
	}

	@Override
	public IResizeShapeFeature getResizeFeature(IFeatureProvider fp) {
		return new DefaultResizeShapeFeature(fp) {
			@Override
			public boolean canResizeShape(IResizeShapeContext context) {
				return false;
			}
		};
	}

	@Override
	public IDeleteFeature getDeleteFeature(IFeatureProvider fp) {
		return new AbstractDefaultDeleteFeature(fp);
	}

	@Override
	public ICustomFeature[] getCustomFeatures(IFeatureProvider fp) {
		return new ICustomFeature[] {
			new AppendActivityFeature(fp),
			new AppendGatewayFeature(fp),
			new AppendEventFeature(fp)
		};
	}

	private class UpdateAbstractGatewayFeature extends UpdateBaseElementNameFeature {

		public UpdateAbstractGatewayFeature(IFeatureProvider fp) {
			super(fp);
			// TODO Auto-generated constructor stub
		}

		@Override
		public boolean canUpdate(IUpdateContext context) {
			IFeatureProvider featureProvider = getDiagramEditor().getDiagramTypeProvider().getFeatureProvider();

			Shape gatewayShape = (Shape) context.getPictogramElement();
			for (Anchor anchor : gatewayShape.getAnchors()) {
				for (Connection connection : anchor.getIncomingConnections() ) {
					IUpdateContext updateCtx = new UpdateContext(connection);
					IUpdateFeature updateFeature = featureProvider.getUpdateFeature(updateCtx);
					if (updateFeature != null) {
						boolean ret = updateFeature.canUpdate(updateCtx);
						if (ret)
							return ret;
					}
				}
				for (Connection connection : anchor.getOutgoingConnections() ) {
					IUpdateContext updateCtx = new UpdateContext(connection);
					IUpdateFeature updateFeature = featureProvider.getUpdateFeature(updateCtx);
					if (updateFeature != null) {
						boolean ret = updateFeature.canUpdate(updateCtx);
						if (ret)
							return ret;
					}
				}
			}
			
			return super.canUpdate(context);
		}

		@Override
		public IReason updateNeeded(IUpdateContext context) {
			IFeatureProvider featureProvider = getDiagramEditor().getDiagramTypeProvider().getFeatureProvider();

			Shape gatewayShape = (Shape) context.getPictogramElement();
			for (Anchor anchor : gatewayShape.getAnchors()) {
				for (Connection connection : anchor.getIncomingConnections() ) {
					IUpdateContext updateCtx = new UpdateContext(connection);
					IUpdateFeature updateFeature = featureProvider.getUpdateFeature(updateCtx);
					if (updateFeature != null) {
						IReason ret = updateFeature.updateNeeded(updateCtx);
						if (ret.toBoolean())
							return ret;
					}
				}
				for (Connection connection : anchor.getOutgoingConnections() ) {
					IUpdateContext updateCtx = new UpdateContext(connection);
					IUpdateFeature updateFeature = featureProvider.getUpdateFeature(updateCtx);
					if (updateFeature != null) {
						IReason ret = updateFeature.updateNeeded(updateCtx);
						if (ret.toBoolean())
							return ret;
					}
				}
			}
			
			return super.updateNeeded(context);
		}

		@Override
		public boolean update(IUpdateContext context) {
			IFeatureProvider featureProvider = getDiagramEditor().getDiagramTypeProvider().getFeatureProvider();

			ContainerShape gatewayShape = (ContainerShape) context.getPictogramElement();
			for (Anchor anchor : gatewayShape.getAnchors()) {
				for (Connection connection : anchor.getIncomingConnections() ) {
					IUpdateContext updateCtx = new UpdateContext(connection);
					IUpdateFeature updateFeature = featureProvider.getUpdateFeature(updateCtx);
					if (updateFeature != null) {
						updateFeature.update(updateCtx);
					}
				}
				for (Connection connection : anchor.getOutgoingConnections() ) {
					IUpdateContext updateCtx = new UpdateContext(connection);
					IUpdateFeature updateFeature = featureProvider.getUpdateFeature(updateCtx);
					if (updateFeature != null) {
						updateFeature.update(updateCtx);
					}
				}
			}
			return super.update(context);
		}
	}
}