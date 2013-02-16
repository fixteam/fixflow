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

import static org.eclipse.bpmn2.modeler.core.features.choreography.ChoreographyProperties.MESSAGE_REF_IDS;
import static org.eclipse.bpmn2.modeler.core.features.choreography.ChoreographyProperties.MESSAGE_VISIBLE;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.ChoreographyTask;
import org.eclipse.bpmn2.InteractionNode;
import org.eclipse.bpmn2.Message;
import org.eclipse.bpmn2.MessageFlow;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.modeler.core.adapters.AdapterUtil;
import org.eclipse.bpmn2.modeler.core.features.choreography.ChoreographyProperties;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.util.Bpmn2OppositeReferenceAdapter;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ILinkService;
import org.eclipse.graphiti.services.IPeService;

public class UpdateChoreographyMessageFlowFeature extends AbstractUpdateFeature {

	private final IPeService peService = Graphiti.getPeService();

	public UpdateChoreographyMessageFlowFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		return BusinessObjectUtil.containsElementOfType(context.getPictogramElement(), ChoreographyTask.class);
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		PictogramElement pe = context.getPictogramElement();
		if (isLinkedMessage(pe)) {
			Message message = BusinessObjectUtil.getFirstElementOfType(pe, Message.class);
			TreeIterator<EObject> iter = message.eContainer().eAllContents();
			while (iter.hasNext()) {
				EObject eo = iter.next();
				if (eo instanceof ChoreographyTask) {
					ChoreographyTask choreographyTask = (ChoreographyTask)eo;
					for (MessageFlow mf : choreographyTask.getMessageFlowRef()) {
						if (mf.getMessageRef()==message) {
							String oldLabel = peService.getPropertyValue(pe, ChoreographyProperties.MESSAGE_NAME);
							if (oldLabel==null || oldLabel.isEmpty())
								oldLabel = "";
							String newLabel = ChoreographyUtil.getMessageFlowName(mf);
							if (newLabel==null || newLabel.isEmpty())
								newLabel = "";
							if (!newLabel.equals(oldLabel))
								return Reason.createTrueReason();
						}
					}
				}
			}
			return Reason.createFalseReason();
		}
		
		if (!BusinessObjectUtil.containsElementOfType(pe, ChoreographyTask.class)) {
			return Reason.createFalseReason();
		}

		ChoreographyTask choreography = BusinessObjectUtil.getFirstElementOfType(pe,
				ChoreographyTask.class);

		String ids = peService.getPropertyValue(pe, MESSAGE_REF_IDS);
		String choreoIds = ChoreographyUtil.getMessageRefIds(choreography);

		if (ids.equals(choreoIds)) {
			return Reason.createFalseReason();
		}

		return Reason.createTrueReason();
	}

	private boolean isLinkedMessage(PictogramElement pe) {
		BaseElement be = BusinessObjectUtil.getFirstElementOfType(pe, BaseElement.class);
		return be instanceof Message &&
			Boolean.parseBoolean(peService.getPropertyValue(pe, ChoreographyProperties.MESSAGE_LINK));
	}
	
	@Override
	public boolean update(IUpdateContext context) {
		PictogramElement pe = context.getPictogramElement();
		BaseElement be = BusinessObjectUtil.getFirstElementOfType(pe,BaseElement.class);
		if (be instanceof ChoreographyTask) {
			return update((ContainerShape)pe, (ChoreographyTask)be);
		}
		else if (isLinkedMessage(pe)) {
			ILinkService linkService = Graphiti.getLinkService();
			int result = 0;
			TreeIterator<EObject> iter = be.eContainer().eAllContents();
			while (iter.hasNext()) {
				EObject eo = iter.next();
				if (eo instanceof ChoreographyTask) {
					ChoreographyTask choreographyTask = (ChoreographyTask)eo;
					for (MessageFlow mf : choreographyTask.getMessageFlowRef()) {
						if (mf.getMessageRef()==be) {
							for (PictogramElement cs : linkService.getPictogramElements(getDiagram(), choreographyTask)) {
								if (cs instanceof ContainerShape) {
									if (update((ContainerShape)cs, choreographyTask))
										++result;
								}
							}
						}
					}
				}
			}
			return result>0;
		}
		return false;
	}
	
	public boolean update(ContainerShape choreographyContainer, ChoreographyTask choreographyTask) {
		List<InteractionNode> sources = new ArrayList<InteractionNode>();
		for (MessageFlow message : choreographyTask.getMessageFlowRef()) {
			sources.add(message.getSourceRef());
		}

		for (ContainerShape band : ChoreographyUtil.getParticipantBandContainerShapes(choreographyContainer)) {
			Participant participant = BusinessObjectUtil.getFirstElementOfType(band, Participant.class);
			BPMNShape bpmnShape = BusinessObjectUtil.getFirstElementOfType(band, BPMNShape.class);
			if (!sources.contains(participant) && bpmnShape.isIsMessageVisible()) {
				bpmnShape.setIsMessageVisible(false);
				peService.setPropertyValue(choreographyContainer, MESSAGE_VISIBLE, Boolean.toString(false));
			} else if (sources.contains(participant) && !bpmnShape.isIsMessageVisible()) {
				bpmnShape.setIsMessageVisible(true);
				peService.setPropertyValue(choreographyContainer, MESSAGE_VISIBLE, Boolean.toString(true));
			}
		}

		ChoreographyUtil.drawMessageLinks(getFeatureProvider(),choreographyContainer);

		String choreoIds = ChoreographyUtil.getMessageRefIds(choreographyTask);
		peService.setPropertyValue(choreographyContainer, MESSAGE_REF_IDS, choreoIds);
		return false;
	}
}