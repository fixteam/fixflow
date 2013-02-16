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
import java.util.Map;

import org.eclipse.bpmn2.ChoreographyActivity;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.modeler.core.features.DefaultDeleteBPMNShapeFeature;
import org.eclipse.bpmn2.modeler.core.features.PropertyBasedFeatureContainer;
import org.eclipse.bpmn2.modeler.core.features.choreography.ChoreographyProperties;
import org.eclipse.bpmn2.modeler.core.utils.AnchorUtil;
import org.eclipse.bpmn2.modeler.core.utils.AnchorUtil.AnchorLocation;
import org.eclipse.bpmn2.modeler.core.utils.AnchorUtil.BoundaryAnchor;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.Tuple;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IDirectEditingFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IMoveShapeFeature;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.impl.AbstractMoveShapeFeature;
import org.eclipse.graphiti.features.impl.DefaultResizeShapeFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

public class ChoreographyMessageLinkFeatureContainer extends PropertyBasedFeatureContainer {

	@Override
	protected String getPropertyKey() {
		return ChoreographyProperties.MESSAGE_LINK;
	}

	@Override
	protected boolean canApplyToProperty(String value) {
		return new Boolean(value);
	}

	@Override
	public ICreateFeature getCreateFeature(IFeatureProvider fp) {
		return null;
	}

	@Override
	public IAddFeature getAddFeature(IFeatureProvider fp) {
		return null;
	}

	@Override
	public IUpdateFeature getUpdateFeature(IFeatureProvider fp) {
		return null;
	}

	@Override
	public IDirectEditingFeature getDirectEditingFeature(IFeatureProvider fp) {
		return null;
	}

	@Override
	public ILayoutFeature getLayoutFeature(IFeatureProvider fp) {
		return null;
	}

	@Override
	public IMoveShapeFeature getMoveFeature(IFeatureProvider fp) {
		return new AbstractMoveShapeFeature(fp) {

			@Override
			public void moveShape(IMoveShapeContext context) {
			}

			@Override
			public boolean canMoveShape(IMoveShapeContext context) {
				return false;
			}
		};
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
		return new DefaultDeleteBPMNShapeFeature(fp) {

			@Override
			public void delete(IDeleteContext context) {
				ContainerShape envelope = (ContainerShape) context.getPictogramElement();
				Map<AnchorLocation, BoundaryAnchor> boundaryAnchors = AnchorUtil.getBoundaryAnchors(envelope);
				BoundaryAnchor topBoundaryAnchor = boundaryAnchors.get(AnchorLocation.TOP);
				BoundaryAnchor bottomBoundaryAnchor = boundaryAnchors.get(AnchorLocation.BOTTOM);
				modifyAffectedBands(topBoundaryAnchor);
				modifyAffectedBands(bottomBoundaryAnchor);
				super.delete(context);
			}

			private void modifyAffectedBands(BoundaryAnchor anchor) {

				for (Connection connection : anchor.anchor.getIncomingConnections()) {

					EObject start = connection.getStart().eContainer();

					if (!(start instanceof ContainerShape)) {
						continue;
					}

					if (!BusinessObjectUtil.containsElementOfType((PictogramElement) start, ChoreographyActivity.class)) {
						continue;
					}

					List<ContainerShape> bands = ChoreographyUtil
							.getParticipantBandContainerShapes((ContainerShape) start);

					Tuple<List<ContainerShape>, List<ContainerShape>> topAndBottomBands = ChoreographyUtil
							.getTopAndBottomBands(bands);

					List<ContainerShape> affectedBands = anchor.locationType == AnchorLocation.BOTTOM ? topAndBottomBands
							.getFirst() : topAndBottomBands.getSecond();

					for (ContainerShape bottomBand : affectedBands) {
						BPMNShape bpmnShape = BusinessObjectUtil.getFirstElementOfType(bottomBand, BPMNShape.class);
						bpmnShape.setIsMessageVisible(false);
					}

					break;
				}
			}
		};
	}
}