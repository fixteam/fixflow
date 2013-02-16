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

package org.eclipse.bpmn2.modeler.ui.features.participant;

import org.eclipse.bpmn2.modeler.ui.features.AbstractRotateContainerFeature;
import org.eclipse.graphiti.features.IFeatureProvider;

/**
 * @author Bob Brodt
 *
 */
public class RotatePoolFeature extends AbstractRotateContainerFeature {

	/**
	 * @param fp
	 */
	public RotatePoolFeature(IFeatureProvider fp) {
		super(fp);
	}
	
	@Override
	public String getName() {
	    return "Change Pool Orientation";
	}
	
	@Override
	public String getDescription() {
	    return "Switch the orientation of this Pool between horizontal and vertical";
	}
}
