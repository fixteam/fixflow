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
package org.eclipse.bpmn2.modeler.ui.diagram;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.modeler.core.Activator;
import org.eclipse.bpmn2.modeler.core.features.activity.ActivitySelectionBehavior;
import org.eclipse.bpmn2.modeler.core.features.event.EventSelectionBehavior;
import org.eclipse.bpmn2.modeler.core.runtime.CustomTaskDescriptor;
import org.eclipse.bpmn2.modeler.core.runtime.ModelEnablementDescriptor;
import org.eclipse.bpmn2.modeler.core.runtime.TargetRuntime;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.bpmn2.modeler.ui.FeatureMap;
import org.eclipse.bpmn2.modeler.ui.ImageProvider;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.bpmn2.modeler.ui.features.activity.task.CustomTaskFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.choreography.ChoreographySelectionBehavior;
import org.eclipse.bpmn2.modeler.ui.features.choreography.ChoreographyUtil;
import org.eclipse.bpmn2.modeler.ui.features.participant.RotatePoolFeature;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.FeatureCheckerAdapter;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IFeatureChecker;
import org.eclipse.graphiti.features.IFeatureCheckerHolder;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IDoubleClickContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.impl.CreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.palette.IPaletteCompartmentEntry;
import org.eclipse.graphiti.palette.impl.ConnectionCreationToolEntry;
import org.eclipse.graphiti.palette.impl.ObjectCreationToolEntry;
import org.eclipse.graphiti.palette.impl.PaletteCompartmentEntry;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.tb.ContextButtonEntry;
import org.eclipse.graphiti.tb.ContextEntryHelper;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonEntry;
import org.eclipse.graphiti.tb.IContextButtonPadData;

public class BpmnToolBehaviourFeature extends DefaultToolBehaviorProvider implements IFeatureCheckerHolder {

	BPMNFeatureProvider featureProvider;
	ModelEnablementDescriptor modelEnablements;
	
	public BpmnToolBehaviourFeature(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}

	@Override
	public IPaletteCompartmentEntry[] getPalette() {

		EList<Resource> resources = getDiagramTypeProvider().getDiagram().eResource().getResourceSet().getResources();
		IProject project = null;
		for (Resource resource : resources) {
			if (resource.getURI().segmentCount() > 1) {
				String projectName = resource.getURI().segment(1);
				project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
				if (project != null) {
					break;
				}
			}
		}

		BPMN2Editor editor = (BPMN2Editor)getDiagramTypeProvider().getDiagramEditor();
		Diagram diagram = getDiagramTypeProvider().getDiagram();
		Object object = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(diagram);
		modelEnablements = editor.getTargetRuntime().getModelEnablements((EObject)object);
		featureProvider = (BPMNFeatureProvider)getFeatureProvider();
		
		List<IPaletteCompartmentEntry> palette = new ArrayList<IPaletteCompartmentEntry>();

		// add compartments from super class
		createConnectors(palette);
		createTasksCompartments(palette);
		createGatewaysCompartments(palette);
		createEventsCompartments(palette);
		createEventDefinitionsCompartments(palette);
		createDataCompartments(palette);
		createOtherCompartments(palette);
		createCustomTasks(palette);

		return palette.toArray(new IPaletteCompartmentEntry[palette.size()]);
	}

	private void createEventsCompartments(List<IPaletteCompartmentEntry> palette) {
		PaletteCompartmentEntry compartmentEntry = new PaletteCompartmentEntry("事件", null);

		createEntries(FeatureMap.EVENTS, compartmentEntry);

		if (compartmentEntry.getToolEntries().size()>0)
			palette.add(compartmentEntry);
	}

	private void createOtherCompartments(List<IPaletteCompartmentEntry> palette) {
		PaletteCompartmentEntry compartmentEntry = new PaletteCompartmentEntry("其他", null);
		compartmentEntry.setInitiallyOpen(false);

		createEntries(FeatureMap.OTHER, compartmentEntry);

		if (compartmentEntry.getToolEntries().size()>0)
			palette.add(compartmentEntry);
	}

	private void createDataCompartments(List<IPaletteCompartmentEntry> palette) {
		PaletteCompartmentEntry compartmentEntry = new PaletteCompartmentEntry("数据对象", null);
		compartmentEntry.setInitiallyOpen(false);

		createEntries(FeatureMap.DATA, compartmentEntry);

		if (compartmentEntry.getToolEntries().size()>0)
			palette.add(compartmentEntry);
	}

	private void createEventDefinitionsCompartments(List<IPaletteCompartmentEntry> palette) {
		PaletteCompartmentEntry compartmentEntry = new PaletteCompartmentEntry("事件定义", null);
		compartmentEntry.setInitiallyOpen(false);

		createEntries(FeatureMap.EVENT_DEFINITIONS, compartmentEntry);

		if (compartmentEntry.getToolEntries().size()>0)
			palette.add(compartmentEntry);
	}

	private void createGatewaysCompartments(List<IPaletteCompartmentEntry> palette) {
		PaletteCompartmentEntry compartmentEntry = new PaletteCompartmentEntry("网关", null);

		createEntries(FeatureMap.GATEWAYS, compartmentEntry);

		if (compartmentEntry.getToolEntries().size()>0)
			palette.add(compartmentEntry);
	}

	private void createTasksCompartments(List<IPaletteCompartmentEntry> palette) {
		PaletteCompartmentEntry compartmentEntry = new PaletteCompartmentEntry("任务", null);

		createEntries(FeatureMap.TASKS, compartmentEntry);

		if (compartmentEntry.getToolEntries().size()>0)
			palette.add(compartmentEntry);
	}

	private void createConnectors(List<IPaletteCompartmentEntry> palette) {
		PaletteCompartmentEntry compartmentEntry = new PaletteCompartmentEntry("连接", null);

		createEntries(FeatureMap.CONNECTORS, compartmentEntry);

		if (compartmentEntry.getToolEntries().size()>0)
			palette.add(compartmentEntry);
	}

	private void createEntries(List<Class> neededEntries, PaletteCompartmentEntry compartmentEntry) {
		for (Class c : neededEntries) {
			if (modelEnablements.isEnabled(c.getSimpleName())) {
				IFeature feature = featureProvider.getCreateFeatureForBusinessObject(c);
				if (feature instanceof ICreateFeature) {
					ICreateFeature cf = (ICreateFeature)feature;
					ObjectCreationToolEntry objectCreationToolEntry = new ObjectCreationToolEntry(cf.getCreateName(),
						cf.getCreateDescription(), cf.getCreateImageId(), cf.getCreateLargeImageId(), cf);
					compartmentEntry.addToolEntry(objectCreationToolEntry);
				}
				else if (feature instanceof ICreateConnectionFeature) {
					ICreateConnectionFeature cf = (ICreateConnectionFeature)feature;
					ConnectionCreationToolEntry connectionCreationToolEntry = new ConnectionCreationToolEntry(
							cf.getCreateName(), cf.getCreateDescription(), cf.getCreateImageId(),
							cf.getCreateLargeImageId());
					connectionCreationToolEntry.addCreateConnectionFeature(cf);
					compartmentEntry.addToolEntry(connectionCreationToolEntry);
				}
			}
		}
	}

	private void createCustomTasks(List<IPaletteCompartmentEntry> ret) {
		PaletteCompartmentEntry compartmentEntry = null;
		BPMN2Editor editor = (BPMN2Editor) getDiagramTypeProvider().getDiagramEditor();
		TargetRuntime rt = editor.getTargetRuntime();
		
		try {
			for (CustomTaskDescriptor tc : rt.getCustomTasks()) {
				if (true) {//modelEnablements.isEnabled(tc.getId())) {
					CustomTaskFeatureContainer container = (CustomTaskFeatureContainer)tc.getFeatureContainer();
	
					container.setId(featureProvider, tc.getId());
					ICreateFeature cf = container.getCreateFeature(featureProvider);
					ObjectCreationToolEntry objectCreationToolEntry = new ObjectCreationToolEntry(tc.getName(),
							cf.getCreateDescription(), cf.getCreateImageId(), cf.getCreateLargeImageId(), cf);
					
					if (compartmentEntry==null) {
						compartmentEntry = new PaletteCompartmentEntry("Custom Task", null);
						compartmentEntry.setInitiallyOpen(false);
						ret.add(compartmentEntry);
					}
					
					compartmentEntry.addToolEntry(objectCreationToolEntry);
				}
			}
		} catch (Exception ex) {
			Activator.logError(ex);
		}
	}

	@Override
	public IFeatureChecker getFeatureChecker() {
		return new FeatureCheckerAdapter(false) {
			@Override
			public boolean allowAdd(IContext context) {
				return super.allowAdd(context);
			}

			@Override
			public boolean allowCreate() {
				return super.allowCreate();
			}
		};
	}

	@Override
	public GraphicsAlgorithm[] getClickArea(PictogramElement pe) {
		if (ActivitySelectionBehavior.canApplyTo(pe)) {
			return ActivitySelectionBehavior.getClickArea(pe);
		} else if (EventSelectionBehavior.canApplyTo(pe)) {
			return EventSelectionBehavior.getClickArea(pe);
		} else if (ChoreographySelectionBehavior.canApplyTo(pe)) {
			return ChoreographySelectionBehavior.getClickArea(pe);
		}
		return super.getClickArea(pe);
	}

	@Override
	public GraphicsAlgorithm getSelectionBorder(PictogramElement pe) {
		if (ActivitySelectionBehavior.canApplyTo(pe)) {
			return ActivitySelectionBehavior.getSelectionBorder(pe);
		} else if (EventSelectionBehavior.canApplyTo(pe)) {
			return EventSelectionBehavior.getSelectionBorder(pe);
		} else if (ChoreographySelectionBehavior.canApplyTo(pe)) {
			return ChoreographySelectionBehavior.getSelectionBorder(pe);
		}
		else if (pe instanceof ContainerShape) {
			if (((ContainerShape)pe).getChildren().size()>0) {
				GraphicsAlgorithm ga = ((ContainerShape)pe).getChildren().get(0).getGraphicsAlgorithm();
				if (!(ga instanceof Text) && !(ga instanceof Polyline))
					return ga;
				ga = ((ContainerShape)pe).getGraphicsAlgorithm();
				if (ga.getGraphicsAlgorithmChildren().size()>0)
					return ga.getGraphicsAlgorithmChildren().get(0);
				return ga;
			}
		}
		else if (pe instanceof Shape) {
			return ((Shape)pe).getGraphicsAlgorithm();
		}
		return super.getSelectionBorder(pe);
	}

	@Override
	public IContextButtonPadData getContextButtonPad(IPictogramElementContext context) {
		IContextButtonPadData data = super.getContextButtonPad(context);
		PictogramElement pe = context.getPictogramElement();
		IFeatureProvider fp = getFeatureProvider();

		String labelProperty = Graphiti.getPeService().getPropertyValue(pe, GraphicsUtil.LABEL_PROPERTY);
		if (Boolean.parseBoolean(labelProperty)) {
			// labels don't have a buttonpad
			setGenericContextButtons(data, pe, 0);
			return data;
		}

		if( pe.getGraphicsAlgorithm()!= null && pe.getGraphicsAlgorithm().getWidth() < 40 ){
		    ILocation origin = getAbsoluteLocation(pe.getGraphicsAlgorithm());
		    data.getPadLocation().setRectangle(origin.getX(), origin.getY(), 40, 40);
		}
		
		// 1. set the generic context buttons
		// Participant bands can only be removed from the choreograpy task
		int genericButtons = CONTEXT_BUTTON_DELETE;
		if (ChoreographyUtil.isChoreographyParticipantBand(pe)) {
			genericButtons |= CONTEXT_BUTTON_REMOVE;
		}
		
		setGenericContextButtons(data, pe, genericButtons);

		// 2. set the expand & collapse buttons
		CustomContext cc = new CustomContext(new PictogramElement[] { pe });
		ICustomFeature[] cf = fp.getCustomFeatures(cc);
		for (int i = 0; i < cf.length; i++) {
			ICustomFeature iCustomFeature = cf[i];
			if (iCustomFeature.canExecute(cc)) {
				ContextButtonEntry button = new ContextButtonEntry(iCustomFeature, cc);
				//汉化
				if(iCustomFeature.getName().equals("Change Pool Orientation")) {
					button.setText("更改池方向");
				}else {
					button.setText(iCustomFeature.getName()); //$NON-NLS-1$
				}
				
				if(iCustomFeature.getName().equals("Change Lane Orientation")) {
					button.setText("更改道方向");
				}else {
					button.setText(iCustomFeature.getName()); //$NON-NLS-1$
				}
				
				if(iCustomFeature.getDescription().equals("Switch the orientation of this Pool between horizontal and vertical")) {
					button.setDescription("切换这个池横向和纵向的方向");
				}else {
					button.setDescription(iCustomFeature.getDescription());
				}
				
				if(iCustomFeature.getDescription().equals("Switch the orientation of this Lane between horizontal and vertical")) {
					button.setDescription("切换这个道横向和纵向的方向");
				}else {
					button.setDescription(iCustomFeature.getDescription());
				}
				
				button.setIconId(iCustomFeature.getImageId());
				
				data.getDomainSpecificContextButtons().add(button);
			}
		}

		// 3. add one domain specific context-button, which offers all
		// available connection-features as drag&drop features...

		// 3.a. create new CreateConnectionContext
		CreateConnectionContext ccc = new CreateConnectionContext();
		ccc.setSourcePictogramElement(pe);
		Anchor anchor = null;
		if (pe instanceof Anchor) {
			anchor = (Anchor) pe;
		} else if (pe instanceof AnchorContainer) {
			// assume, that our shapes always have chopbox anchors
			anchor = Graphiti.getPeService().getChopboxAnchor((AnchorContainer) pe);
		}
		ccc.setSourceAnchor(anchor);

		// 3.b. create context button and add "Create Connections" feature
		ICreateConnectionFeature[] features = fp.getCreateConnectionFeatures();
		ContextButtonEntry button = new ContextButtonEntry(null, context);
		button.setText(/*"Create Connection"*/"创建连接"); //$NON-NLS-1$
		String description = null;
		ArrayList<String> names = new ArrayList<String>();
		button.setIconId(ImageProvider.IMG_16_SEQUENCE_FLOW);
		for (ICreateConnectionFeature feature : features) {
			if (feature.isAvailable(ccc) && feature.canStartConnection(ccc)) {
				button.addDragAndDropFeature(feature);
				names.add(feature.getCreateName());
			}
		}
		
		// 3.c. build a reasonable description for the context button action 
		for (int i=0; i<names.size(); ++i) {
			if (description==null)
				description = /*"Click and drag to create a\n"*/"点击并拖拽来创建一个\n";
			description += names.get(i);
			if (i+2 == names.size())
				description += /*" or "*/" 或 ";
			else if (i+1 < names.size())
				description += ", ";
		}
		button.setDescription(description);

		// 3.d. add context button, button only if it contains at least one feature
		if (button.getDragAndDropFeatures().size() > 0) {
			data.getDomainSpecificContextButtons().add(button);
		}

		return data;
	}

	@Override
	public ICustomFeature getDoubleClickFeature(IDoubleClickContext context) {
		ICustomFeature[] cf = getFeatureProvider().getCustomFeatures(context);
		for (int i = 0; i < cf.length; i++) {
			ICustomFeature iCustomFeature = cf[i];
			if (iCustomFeature.canExecute(context)) {
				if(iCustomFeature instanceof RotatePoolFeature) {
					return null;
				}
				return iCustomFeature;
			}
		}
		return null;
	}

	@Override
	public GraphicsAlgorithm getChopboxAnchorArea(PictogramElement pe) {
		return super.getChopboxAnchorArea(pe);
	}

	@Override
	protected void setGenericContextButtons(IContextButtonPadData data,
			PictogramElement pe, int identifiers) {
		// TODO Auto-generated method stub
		data.getGenericContextButtons().clear();

		// update button
		if ((identifiers & CONTEXT_BUTTON_UPDATE) != 0) {
			IContextButtonEntry updateButton = ContextEntryHelper.createDefaultUpdateContextButton(getFeatureProvider(), pe);
			if (updateButton != null) {
				updateButton.setText("更新");
				updateButton.setDescription("更新图形");
				data.getGenericContextButtons().add(updateButton);
			}
		}

		// remove button
		if ((identifiers & CONTEXT_BUTTON_REMOVE) != 0) {
			IContextButtonEntry removeButton = ContextEntryHelper.createDefaultRemoveContextButton(getFeatureProvider(), pe);
			if (removeButton != null) {
				removeButton.setText("移除");
				removeButton.setDescription("从图形中移除该图形");
				data.getGenericContextButtons().add(removeButton);
			}
		}

		// delete button
		if ((identifiers & CONTEXT_BUTTON_DELETE) != 0) {
			IContextButtonEntry deleteButton = ContextEntryHelper.createDefaultDeleteContextButton(getFeatureProvider(), pe);
			if (deleteButton != null) {
				deleteButton.setText("删除");
				deleteButton.setDescription("从模型中删除");
				data.getGenericContextButtons().add(deleteButton);
			}
		}
	}

}