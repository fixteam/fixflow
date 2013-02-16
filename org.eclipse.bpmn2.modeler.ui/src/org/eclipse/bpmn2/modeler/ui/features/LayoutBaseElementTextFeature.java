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
package org.eclipse.bpmn2.modeler.ui.features;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.modeler.core.features.DefaultLayoutBPMNShapeFeature;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;

public abstract class LayoutBaseElementTextFeature extends DefaultLayoutBPMNShapeFeature {

//	private static IGaService gaService = Graphiti.getGaService();

	public LayoutBaseElementTextFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canLayout(ILayoutContext context) {
		PictogramElement pictoElem = context.getPictogramElement();
		if (!(pictoElem instanceof ContainerShape)) {
			return false;
		}
		return BusinessObjectUtil.containsElementOfType(pictoElem, BaseElement.class);
	}

	@Override
	public boolean layout(ILayoutContext context) {
		ContainerShape container = (ContainerShape) context.getPictogramElement();

//		Shape textShape = getShape(container, UpdateBaseElementNameFeature.TEXT_ELEMENT, Boolean.toString(true));
//		Text textGa = (Text) textShape.getGraphicsAlgorithm();
//		String text = textGa.getValue() == null ? "" : textGa.getValue();
//		IDimension size = GraphitiUi.getUiLayoutService().calculateTextSize(text, textGa.getFont());

//		GraphicsAlgorithm parentGa = container.getGraphicsAlgorithm();

//		if (size.getWidth() > getMinimumWidth()) {
//			gaService.setSize(parentGa, parentGa.getWidth(), parentGa.getHeight());
//		} else {
//			gaService.setSize(parentGa, getMinimumWidth(), parentGa.getHeight());
//		}

//		gaService.setSize(textGa, size.getWidth() + 3, textGa.getHeight());
		
		return super.layout(context);
	}

	public abstract int getMinimumWidth();
}