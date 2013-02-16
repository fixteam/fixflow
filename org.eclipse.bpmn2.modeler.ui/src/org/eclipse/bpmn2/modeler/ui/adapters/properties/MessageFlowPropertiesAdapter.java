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

package org.eclipse.bpmn2.modeler.ui.adapters.properties;

import org.eclipse.bpmn2.MessageFlow;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.core.adapters.ObjectDescriptor;
import org.eclipse.bpmn2.modeler.ui.features.choreography.ChoreographyUtil;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Gary Brown
 *
 */
public class MessageFlowPropertiesAdapter extends ExtendedPropertiesAdapter {

	/**
	 * @param adapterFactory
	 * @param object
	 */
	public MessageFlowPropertiesAdapter(AdapterFactory adapterFactory, EObject object) {
		super(adapterFactory, object);
		
    	setObjectDescriptor(new ObjectDescriptor(adapterFactory, object) {
			@Override
			public String getText(Object context) {
				final MessageFlow mf = context instanceof MessageFlow ?
						(MessageFlow)context :
						(MessageFlow)this.object;
				String text = "";
				if (mf.getMessageRef()!=null) {
					text += ChoreographyUtil.getMessageFlowName(mf);
				}
				
				if (mf.getSourceRef() instanceof Participant) {
					text += " "+((Participant)mf.getSourceRef()).getName()+"->";
					
					if (mf.getTargetRef() instanceof Participant) {
						text += ((Participant)mf.getTargetRef()).getName();
					}
				}
				return text;
			}
    	});
	}

}
