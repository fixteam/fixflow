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

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

public abstract class BaseElementFeatureContainer implements FeatureContainer {

	@Override
	public Object getApplyObject(IContext context) {
		if (context instanceof IAddContext) {
			return ((IAddContext) context).getNewObject();
// TODO: why is this here? we have already expanded LabelFeatureContainer to handle all of the
// BPMN elements that have label figures and this test is done there - I believe this was an
// interim hack before LabelFeatureContainer was added...
//		} else if (context instanceof IUpdateContext) {
//			IUpdateContext updateContext = (IUpdateContext) context;
//			BaseElement o = BusinessObjectUtil.getFirstElementOfType(updateContext.getPictogramElement(), BaseElement.class);
//			if (o instanceof Gateway || o instanceof Event) {
//				if (updateContext.getPictogramElement() instanceof ContainerShape) {
//					ContainerShape container = (ContainerShape) updateContext.getPictogramElement();
//					if (container.getChildren().size() == 1) {
//						Shape shape = container.getChildren().get(0);
//						if (shape.getGraphicsAlgorithm() instanceof AbstractText) {
//							return null;
//						}
//					}
//				}
//			}
		}
		if (context instanceof IPictogramElementContext) {
			return BusinessObjectUtil.getFirstElementOfType(
					(((IPictogramElementContext) context).getPictogramElement()), BaseElement.class);
		}
		if (context instanceof ICustomContext) {
			PictogramElement[] pes = ((ICustomContext) context).getPictogramElements();
			if (pes.length==1)
				return BusinessObjectUtil.getFirstElementOfType(pes[0], BaseElement.class);
		}
		return null;
	}

	@Override
	public boolean canApplyTo(Object o) {
		return o instanceof BaseElement;
	}

	@Override
	public IRemoveFeature getRemoveFeature(IFeatureProvider fp) {
		return null;
	}
	
	@Override
	public ICustomFeature[] getCustomFeatures(IFeatureProvider fp) {
		return null;
	}
}