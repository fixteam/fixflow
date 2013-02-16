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

import static org.eclipse.bpmn2.modeler.core.utils.FeatureSupport.getChildElementOfType;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;

public class UpdateBaseElementNameFeature extends AbstractUpdateFeature {

	public static final String TEXT_ELEMENT = "baseelement.text";

	public UpdateBaseElementNameFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		BaseElement element = (BaseElement) BusinessObjectUtil.getFirstElementOfType(context.getPictogramElement(),
		        BaseElement.class);
		if (element == null) {
			return false;
		}
		return ModelUtil.hasName(element);
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		PictogramElement container = context.getPictogramElement();

		BaseElement element = (BaseElement) BusinessObjectUtil.getFirstElementOfType(container,
		        BaseElement.class);

		Shape textShape = getChildElementOfType(container, TEXT_ELEMENT, Boolean.toString(true), Shape.class);
		if (textShape!=null) {
			String oldLabel = ModelUtil.getName(element);
			if (oldLabel==null || oldLabel.isEmpty())
				oldLabel = "";
			String newLabel = "";
			if (textShape.getGraphicsAlgorithm() instanceof AbstractText) {
				AbstractText text = (AbstractText) textShape.getGraphicsAlgorithm();
				newLabel = text.getValue();
			}
			if (newLabel==null || newLabel.isEmpty())
				newLabel = "";
			
			return oldLabel.equals(newLabel) ? Reason.createFalseReason() : Reason.createTrueReason();
		}
		return Reason.createFalseReason();
	}

	@Override
	public boolean update(IUpdateContext context) {
		PictogramElement pe = (PictogramElement) context.getPictogramElement();
		BaseElement element = (BaseElement) BusinessObjectUtil.getFirstElementOfType(context.getPictogramElement(),
		        BaseElement.class);
		Shape textShape = getChildElementOfType(pe, TEXT_ELEMENT, Boolean.toString(true), Shape.class);
		if (textShape!=null) {
			AbstractText text = (AbstractText) textShape.getGraphicsAlgorithm();
			String name = ModelUtil.getName(element);
			if (name == null) {
				name = "";
			}
			text.setValue(name);
			layoutPictogramElement(context.getPictogramElement());
		}

		if (pe instanceof ContainerShape) {
			IGaService gaService = Graphiti.getGaService();
				ContainerShape container = (ContainerShape)pe;
			
			Shape shape = container.getChildren().get(0); // Otherwise, this would never be reached!
			if (!(shape.getGraphicsAlgorithm() instanceof AbstractText))
				return true;
			
			GraphicsAlgorithm textGA = container.getGraphicsAlgorithm();
			AbstractText text = (AbstractText) shape.getGraphicsAlgorithm();
			
			int oldWidth = textGA.getWidth() - GraphicsUtil.SHAPE_PADDING;
			int x = textGA.getX() + ((oldWidth + GraphicsUtil.SHAPE_PADDING) / 2);
			int y = textGA.getY();
			
			BaseElement o = BusinessObjectUtil.getFirstElementOfType(context.getPictogramElement(), BaseElement.class);
			String name = ModelUtil.getName(o);
			
			if (name == null) {
				gaService.setLocationAndSize(textGA, x, y, 0, 0);
				gaService.setLocationAndSize(text, 0, 0, 0, 0);
				container.setVisible(false);
			} else {
				int newWidth = GraphicsUtil.getLabelWidth(text);
				int newHeight = GraphicsUtil.getLabelHeight(text);
				x = x - ((newWidth + GraphicsUtil.SHAPE_PADDING) / 2);
				gaService.setLocationAndSize(textGA, x, y, newWidth + GraphicsUtil.SHAPE_PADDING, newHeight + GraphicsUtil.SHAPE_PADDING);
				gaService.setLocationAndSize(text, 0, 0, newWidth + GraphicsUtil.TEXT_PADDING, newHeight + GraphicsUtil.TEXT_PADDING);
				container.setVisible(true);
			}
		}
		
		return true;
	}
}