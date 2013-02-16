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
package org.eclipse.bpmn2.modeler.ui.features.conversation;

import java.io.IOException;

import org.eclipse.bpmn2.Conversation;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.modeler.core.Activator;
import org.eclipse.bpmn2.modeler.core.ModelHandler;
import org.eclipse.bpmn2.modeler.core.features.AbstractBpmn2CreateFeature;
import org.eclipse.bpmn2.modeler.core.model.Bpmn2ModelerFactory;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.ui.ImageProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;

public class CreateConversationFeature extends AbstractBpmn2CreateFeature {

	public CreateConversationFeature(IFeatureProvider fp) {
		super(fp, "Conversation", "A logical grouping of Message exchanges");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer().equals(getDiagram());
	}

	@Override
	public Object[] create(ICreateContext context) {
		Conversation c = null;
		try {
			ModelHandler handler = ModelHandler.getInstance(getDiagram());
			c = Bpmn2ModelerFactory.create(Conversation.class);
//			c.setId(EcoreUtil.generateUUID());
			c.setName("Conversation");
	        BPMNDiagram bpmnDiagram = BusinessObjectUtil.getFirstElementOfType(context.getTargetContainer(), BPMNDiagram.class);
			handler.addConversationNode(bpmnDiagram,c);
			ModelUtil.setID(c);
		} catch (IOException e) {
			Activator.logError(e);
		}
		addGraphicalRepresentation(context, c);
		return new Object[] { c };
	}

	@Override
	public String getCreateImageId() {
		return ImageProvider.IMG_16_CONVERSATION;
	}

	@Override
	public String getCreateLargeImageId() {
		return ImageProvider.IMG_16_CONVERSATION;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.core.features.AbstractBpmn2CreateFeature#getBusinessObjectClass()
	 */
	@Override
	public Class getBusinessObjectClass() {
		return Conversation.class;
	}
}