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

import org.eclipse.bpmn2.modeler.core.utils.FeatureSupport;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

public abstract class AbstractUpdateBaseElementFeature extends AbstractUpdateFeature {

	public AbstractUpdateBaseElementFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		PictogramElement pe = context.getPictogramElement();
		if (pe instanceof ContainerShape) {
			String shapeValue = FeatureSupport.getShapeValue(context);
			if (shapeValue==null)
				shapeValue = "";
			String businessValue = FeatureSupport.getBusinessValue(context);
			if (businessValue==null)
				businessValue = "";
	
			boolean updateNeeded = !shapeValue.equals(businessValue);
			
			if (updateNeeded) {
				return Reason.createTrueReason("Name out of date");
			}
		}
		
		return Reason.createFalseReason();
	}

	@Override
	public boolean update(IUpdateContext context) {
		// TODO here it should get an updated picture from the element controller

		PictogramElement pe = context.getPictogramElement();
		if (pe instanceof ContainerShape) {
			ContainerShape cs = (ContainerShape) pe;
			for (Shape shape : cs.getChildren()) {
				if (shape.getGraphicsAlgorithm() instanceof AbstractText) {
					AbstractText text = (AbstractText) shape.getGraphicsAlgorithm();
					String value = FeatureSupport.getBusinessValue(context);
					if (value == null) {
						value = "";
					}
					text.setValue(value);
					return true;
				}
			}
		}
		return false;
	}

}
