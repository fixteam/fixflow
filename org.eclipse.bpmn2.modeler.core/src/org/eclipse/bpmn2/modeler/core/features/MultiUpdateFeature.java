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
 * @author Ivar Meikas
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.core.features;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;

public class MultiUpdateFeature extends AbstractUpdateFeature {

	protected List<IUpdateFeature> features = new ArrayList<IUpdateFeature>();

	public MultiUpdateFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		for (IUpdateFeature p : features) {
			if (p.canUpdate(context)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		for (IUpdateFeature p : features) {
			if (p.updateNeeded(context).toBoolean()) {
				return Reason.createTrueReason();
			}
		}
		return Reason.createFalseReason();
	}

	@Override
	public boolean update(IUpdateContext context) {
		boolean updated = false;

		for (IUpdateFeature p : features) {
			if (p.updateNeeded(context).toBoolean() && p.update(context)) {
				updated = true;
			}
		}

		return updated;
	}

	public void addUpdateFeature(IUpdateFeature feature) {
		if (feature != null) {
			features.add(feature);
		}
	}
}