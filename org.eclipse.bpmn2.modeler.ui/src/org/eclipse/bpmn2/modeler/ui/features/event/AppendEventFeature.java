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

package org.eclipse.bpmn2.modeler.ui.features.event;

import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.modeler.ui.ImageProvider;
import org.eclipse.bpmn2.modeler.ui.features.AbstractAppendNodeNodeFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;

/**
 * @author Bob Brodt
 *
 */
public class AppendEventFeature extends AbstractAppendNodeNodeFeature<Event> {

	/**
	 * @param fp
	 */
	public AppendEventFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public String getName() {
		/*return "Append Event";*/
		return "添加事件";
	}

	@Override
	public String getDescription() {
		/*return "Create a new Event and connect it to this item";*/
		return "创建一个新事件并连到此元素";
	}

	@Override
	public String getImageId() {
		return ImageProvider.IMG_16_END_EVENT;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.ui.features.AbstractAppendNodeNodeFeature#getBusinessObjectClass()
	 */
	@Override
	public Class getBusinessObjectClass() {
		return Event.class;
	}
}
