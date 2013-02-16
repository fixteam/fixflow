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
 * @author Bob Brodt
 ******************************************************************************/

package org.eclipse.bpmn2.modeler.ui.features.choreography;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.Choreography;
import org.eclipse.bpmn2.ChoreographyTask;
import org.eclipse.bpmn2.Message;
import org.eclipse.bpmn2.MessageFlow;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.modeler.core.ModelHandler;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.ui.Activator;
import org.eclipse.bpmn2.modeler.ui.ImageProvider;
import org.eclipse.bpmn2.modeler.ui.util.PropertyUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.internal.util.ui.PopupMenu;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * @author Bob Brodt
 *
 */
public class AddChoreographyMessageFeature extends AbstractCustomFeature {
	
	private static ILabelProvider labelProvider = new ILabelProvider() {

		public void removeListener(ILabelProviderListener listener) {
		}

		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		public void dispose() {

		}

		public void addListener(ILabelProviderListener listener) {

		}

		public String getText(Object element) {
			return ChoreographyUtil.getMessageName((Message)element);
		}

		public Image getImage(Object element) {
			return null;
		}

	};

	/**
	 * @param fp
	 */
	public AddChoreographyMessageFeature(IFeatureProvider fp) {
		super(fp);
	}
	
	@Override
	public String getName() {
	    return "Add Message";
	}
	
	@Override
	public String getDescription() {
	    return "Add a new Message to this Choreography Task";
	}

	@Override
	public String getImageId() {
		return ImageProvider.IMG_16_ADD_MESSAGE;
	}

	@Override
	public boolean isAvailable(IContext context) {
		return true;
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length == 1) {
			PictogramElement pe = pes[0];
			Object bo = getBusinessObjectForPictogramElement(pe);
			if (pe instanceof ContainerShape && bo instanceof Participant) {
				Participant participant = (Participant)bo;
				
				Object parent = getBusinessObjectForPictogramElement(((ContainerShape)pe).getContainer());
				if (parent instanceof ChoreographyTask) {
					
					// Check if choreography task already associated with MessageFlow
					// with this participant as the source
					ChoreographyTask ct=(ChoreographyTask)parent;
					
					if (ct.getParticipantRefs().size() == 2) {
						boolean canAdd=true;
						
						for (MessageFlow mf : ct.getMessageFlowRef()) {
							if (mf.getSourceRef() != null &&
									mf.getSourceRef().equals(participant)) {
								canAdd = false;
								break;
							}
						}
						
						return (canAdd);
					}
				}
			}
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.features.custom.ICustomFeature#execute(org.eclipse.graphiti.features.context.ICustomContext)
	 */
	@Override
	public void execute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length == 1) {
			PictogramElement pe = pes[0];
			Object bo = getBusinessObjectForPictogramElement(pe);
			if (pe instanceof ContainerShape && bo instanceof Participant) {
				try {
					ModelHandler mh = ModelHandler.getInstance(getDiagram());

					ContainerShape containerShape = (ContainerShape)pe;
					Participant participant = (Participant)bo;
					
					Object parent = getBusinessObjectForPictogramElement(containerShape.getContainer());
					if (parent instanceof ChoreographyTask) {
						ChoreographyTask ct=(ChoreographyTask)parent;
												
						Message message = null;
						List<Message> messageList = new ArrayList<Message>();
						message = mh.create(Message.class);
						message.setName(message.getId());
						
						messageList.add(message);
						for (RootElement re : mh.getDefinitions().getRootElements()) {
							if (re instanceof Message) {
								messageList.add((Message)re);
							}
						}

						Message result = message;
		
						boolean doit = true;
						if (messageList.size()>1) {
							PopupMenu popupMenu = new PopupMenu(messageList, labelProvider);
							doit = popupMenu.show(Display.getCurrent().getActiveShell());
							if (doit) {
								result = (Message) popupMenu.getResult();
							}
						}
						if (doit) {
							if (result==message) { // the new one
								message.setName( PropertyUtil.getText(message)); // ModelUtil.toDisplayName(message.getId()) );
								
								mh.getDefinitions().getRootElements().add(result);
							}
							
							java.util.List<Participant> parts=new java.util.ArrayList<Participant>(
													ct.getParticipantRefs());
							parts.remove(participant);
							
							if (parts.size() == 1) {
								MessageFlow mf=mh.createMessageFlow(participant, parts.get(0));
								mf.setName(ModelUtil.toDisplayName(mf.getId()));
								
								Choreography choreography = (Choreography)ct.eContainer();
								choreography.getMessageFlows().add(mf);

								mf.setMessageRef(result);
								ct.getMessageFlowRef().add(mf);
							} else {
								// REPORT ERROR??
							}
						}
					}
				} catch (IOException e) {
					Activator.logError(e);
				}
			}
		}
	}

}
