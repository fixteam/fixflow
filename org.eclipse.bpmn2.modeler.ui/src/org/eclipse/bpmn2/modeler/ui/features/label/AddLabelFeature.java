package org.eclipse.bpmn2.modeler.ui.features.label;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.modeler.core.features.ContextConstants;
import org.eclipse.bpmn2.modeler.core.features.UpdateBaseElementNameFeature;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.FeatureSupport;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.bpmn2.modeler.ui.util.PropertyUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.MultiText;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeService;

public class AddLabelFeature extends AbstractAddShapeFeature {

	public AddLabelFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		boolean intoDiagram = context.getTargetContainer().equals(getDiagram());
		boolean intoLane = FeatureSupport.isTargetLane(context) && FeatureSupport.isTargetLaneOnTop(context);
		boolean intoParticipant = FeatureSupport.isTargetParticipant(context);
		boolean intoFlowELementContainer = BusinessObjectUtil.containsElementOfType(context.getTargetContainer(),
		        FlowElementsContainer.class);
		return intoDiagram || intoLane || intoParticipant || intoFlowELementContainer;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		IGaService gaService = Graphiti.getGaService();
		IPeService peService = Graphiti.getPeService();
		
		int width = (Integer) context.getProperty(ContextConstants.WIDTH);
		int height = (Integer) context.getProperty(ContextConstants.HEIGHT);
		BaseElement baseElement = (BaseElement) context.getProperty(ContextConstants.BASE_ELEMENT);
		
		final ContainerShape textContainerShape = peService.createContainerShape(context.getTargetContainer(), true);
		gaService.createInvisibleRectangle(textContainerShape);
		
		Shape textShape = peService.createShape(textContainerShape, false);
		peService.setPropertyValue(textShape, UpdateBaseElementNameFeature.TEXT_ELEMENT, Boolean.toString(true));
		String name = PropertyUtil.getText(baseElement);
		MultiText text = gaService.createDefaultMultiText(getDiagram(), textShape, name);
		StyleUtil.applyStyle(text, baseElement);
		text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
		text.setVerticalAlignment(Orientation.ALIGNMENT_TOP);
		
		GraphicsUtil.alignWithShape(text, textContainerShape, width, height, context.getX(), context.getY(), 0, 0);
		
		this.link(textContainerShape, baseElement);
		Graphiti.getPeService().setPropertyValue(textContainerShape, GraphicsUtil.LABEL_PROPERTY, "true");
		
		updatePictogramElement(textContainerShape);
		layoutPictogramElement(textContainerShape);
		return textContainerShape;
	}

}
