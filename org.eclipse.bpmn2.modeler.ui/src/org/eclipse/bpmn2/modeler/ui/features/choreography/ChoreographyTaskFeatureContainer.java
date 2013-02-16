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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.ChoreographyLoopType;
import org.eclipse.bpmn2.ChoreographyTask;
import org.eclipse.bpmn2.MessageFlow;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.modeler.core.features.AbstractCreateFlowElementFeature;
import org.eclipse.bpmn2.modeler.core.features.MultiUpdateFeature;
import org.eclipse.bpmn2.modeler.core.model.Bpmn2ModelerFactory;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.ui.ImageProvider;
import org.eclipse.bpmn2.modeler.ui.features.AbstractDefaultDeleteFeature;
import org.eclipse.bpmn2.modeler.ui.features.activity.subprocess.CollapseSubProcessFeature;
import org.eclipse.bpmn2.modeler.ui.features.activity.subprocess.ExpandSubProcessFeature;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.impl.DeleteContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;

public class ChoreographyTaskFeatureContainer extends AbstractChoreographyFeatureContainer {

	@Override
	public boolean canApplyTo(Object o) {
		return super.canApplyTo(o) && o instanceof ChoreographyTask;
	}

	@Override
	public ICreateFeature getCreateFeature(IFeatureProvider fp) {
		return new CreateChoreographyTaskFeature(fp);
	}

	@Override
	public IAddFeature getAddFeature(IFeatureProvider fp) {
		return new AddChoreographyTaskFeature(fp);
	}

	@Override
	public MultiUpdateFeature getUpdateFeature(IFeatureProvider fp) {
		MultiUpdateFeature multiUpdate = super.getUpdateFeature(fp);
		multiUpdate.addUpdateFeature(new UpdateChoreographyMessageFlowFeature(fp));
		return multiUpdate;
	}

	@Override
	public IDeleteFeature getDeleteFeature(IFeatureProvider fp) {
		return new AbstractDefaultDeleteFeature(fp) {
			
			@Override
			protected void deletePeEnvironment(PictogramElement pictogramElement){
				if (pictogramElement instanceof ContainerShape) {
					ContainerShape cShape = (ContainerShape) pictogramElement;
					EList<Anchor> anchors = cShape.getAnchors();
					for (Anchor anchor : anchors) {
						deleteConnections(getFeatureProvider(), anchor.getIncomingConnections());
						deleteConnections(getFeatureProvider(), anchor.getOutgoingConnections());
					}
					deleteContainer(getFeatureProvider(), cShape);
				}
			}

			@Override
			protected void deleteConnections(IFeatureProvider fp, EList<Connection> connections) {
				List<Connection> con = new ArrayList<Connection>();
				con.addAll(connections);
				for (Connection connection : con) {
					// if this is a messageflow, delete the message figure as well
					BaseElement be = BusinessObjectUtil.getFirstElementOfType(connection, BaseElement.class);
					if (be instanceof MessageFlow) {
						EObject msg = connection.getEnd().eContainer();
						if (msg instanceof PictogramElement) {
							Graphiti.getPeService().deletePictogramElement((PictogramElement)msg);
						}
					}
					IDeleteContext conDelete = new DeleteContext(connection);
					IDeleteFeature df = fp.getDeleteFeature(conDelete);
					df.delete(conDelete);
				}
			}

			@Override
			protected void deleteContainer(IFeatureProvider fp, ContainerShape cShape) {
				Object[] children = cShape.getChildren().toArray();
				for (Object shape : children) {
					if (shape instanceof ContainerShape) {
						ContainerShape cs = (ContainerShape)shape;
						BaseElement be = BusinessObjectUtil.getFirstElementOfType(cs, BaseElement.class);
						if (be instanceof Participant) {
							BPMNShape bs = BusinessObjectUtil.getFirstElementOfType(cs, BPMNShape.class);
							Graphiti.getPeService().deletePictogramElement(cs);
							if (bs!=null)
								deleteBusinessObject(bs);
						}
					}
				}
			}
		};
	}
	
	@Override
	public ICustomFeature[] getCustomFeatures(IFeatureProvider fp) {
		return new ICustomFeature[] {
			new AddChoreographyParticipantFeature(fp)
		};
	}

	public static class CreateChoreographyTaskFeature extends AbstractCreateFlowElementFeature<ChoreographyTask> {

		public CreateChoreographyTaskFeature(IFeatureProvider fp) {
			super(fp, "Choreography Task", "Represents interactions between two participants");
		}

		@Override
		protected ChoreographyTask createFlowElement(ICreateContext context) {
			ChoreographyTask task = Bpmn2ModelerFactory.create(ChoreographyTask.class);
			task.setName("Choreography Task");
			task.setLoopType(ChoreographyLoopType.NONE);
			return task;
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
			return ChoreographyTask.class;
		}
	}
}