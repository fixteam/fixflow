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
package org.eclipse.bpmn2.modeler.core.features.activity;

import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.modeler.core.ModelHandlerLocator;
import org.eclipse.bpmn2.modeler.core.features.AbstractCreateFlowElementFeature;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;

public abstract class AbstractCreateExpandableFlowNodeFeature<T extends FlowNode> extends
		AbstractCreateFlowElementFeature<T> {

	public AbstractCreateExpandableFlowNodeFeature(IFeatureProvider fp, String name,
			String description) {
		super(fp, name, description);
	}

	@Override
	public Object[] create(ICreateContext context) {
		Object[] elems = super.create(context);
		try {
			
			BPMNShape shape = (BPMNShape) ModelHandlerLocator.getModelHandler(getDiagram().eResource()).findDIElement((T)elems[0]);
			
			// if the Activity is expandable, set "isExpanded" to true because
			// this feature will always create an expanded BPMNShape.
//			EStructuralFeature feature = ((EObject)shape).eClass().getEStructuralFeature("isExpanded");
//			if (feature!=null)
//				shape.eSet(feature, Boolean.TRUE);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return elems;
	}

}
