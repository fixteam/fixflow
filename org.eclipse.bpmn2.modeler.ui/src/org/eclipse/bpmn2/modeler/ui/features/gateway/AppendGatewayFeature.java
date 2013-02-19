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

package org.eclipse.bpmn2.modeler.ui.features.gateway;

import org.eclipse.bpmn2.Gateway;
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
public class AppendGatewayFeature extends AbstractAppendNodeNodeFeature<Gateway> {

	/**
	 * @param fp
	 */
	public AppendGatewayFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public String getName() {
		/*return "Append Gateway";*/
		return "添加网关";
	}

	@Override
	public String getDescription() {
		/*return "Create a new Gateway and connect it to this item";*/
		return "创建一个新网关并连到此元素";
	}

	@Override
	public String getImageId() {
		return ImageProvider.IMG_16_EXCLUSIVE_GATEWAY;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.ui.features.AbstractAppendNodeNodeFeature#getBusinessObjectClass()
	 */
	@Override
	public Class getBusinessObjectClass() {
		return Gateway.class;
	}
}
