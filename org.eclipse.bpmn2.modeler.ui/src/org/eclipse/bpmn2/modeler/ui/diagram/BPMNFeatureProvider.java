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
import java.util.HashMap;
import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.ChoreographyTask;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.Group;
import org.eclipse.bpmn2.IntermediateCatchEvent;
import org.eclipse.bpmn2.IntermediateThrowEvent;
import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.modeler.core.features.AbstractBpmn2CreateConnectionFeature;
import org.eclipse.bpmn2.modeler.core.features.AbstractBpmn2CreateFeature;
import org.eclipse.bpmn2.modeler.core.features.ConnectionFeatureContainer;
import org.eclipse.bpmn2.modeler.core.features.CopyBaseElementFeature;
import org.eclipse.bpmn2.modeler.core.features.DefaultDeleteBPMNShapeFeature;
import org.eclipse.bpmn2.modeler.core.features.DefaultRemoveBPMNShapeFeature;
import org.eclipse.bpmn2.modeler.core.features.FeatureContainer;
import org.eclipse.bpmn2.modeler.core.features.PasteBaseElementFeature;
import org.eclipse.bpmn2.modeler.core.features.activity.task.ICustomTaskFeature;
import org.eclipse.bpmn2.modeler.core.features.bendpoint.AddBendpointFeature;
import org.eclipse.bpmn2.modeler.core.features.bendpoint.MoveBendpointFeature;
import org.eclipse.bpmn2.modeler.core.features.bendpoint.RemoveBendpointFeature;
import org.eclipse.bpmn2.modeler.core.runtime.CustomTaskDescriptor;
import org.eclipse.bpmn2.modeler.core.runtime.FeatureContainerDescriptor;
import org.eclipse.bpmn2.modeler.core.runtime.TargetRuntime;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.bpmn2.modeler.ui.features.activity.AppendActivityFeature;
import org.eclipse.bpmn2.modeler.ui.features.activity.subprocess.AdHocSubProcessFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.activity.subprocess.CallActivityFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.activity.subprocess.CollapseSubProcessFeature;
import org.eclipse.bpmn2.modeler.ui.features.activity.subprocess.ExpandSubProcessFeature;
import org.eclipse.bpmn2.modeler.ui.features.activity.subprocess.SubProcessFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.activity.subprocess.TransactionFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.activity.task.BusinessRuleTaskFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.activity.task.CustomTaskFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.activity.task.CustomTaskFeatureContainer.CreateCustomTaskFeature;
import org.eclipse.bpmn2.modeler.ui.features.activity.task.ManualTaskFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.activity.task.ReceiveTaskFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.activity.task.ScriptTaskFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.activity.task.SendTaskFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.activity.task.ServiceTaskFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.activity.task.TaskFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.activity.task.UserTaskFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.artifact.GroupFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.artifact.TextAnnotationFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.choreography.AddChoreographyMessageFeature;
import org.eclipse.bpmn2.modeler.ui.features.choreography.AddChoreographyParticipantFeature;
import org.eclipse.bpmn2.modeler.ui.features.choreography.CallChoreographyFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.choreography.ChoreographyMessageLinkFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.choreography.ChoreographyTaskFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.choreography.SubChoreographyFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.conversation.ConversationFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.conversation.ConversationLinkFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.data.DataInputFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.data.DataObjectFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.data.DataObjectReferenceFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.data.DataOutputFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.data.DataStoreReferenceFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.data.MessageFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.event.AppendEventFeature;
import org.eclipse.bpmn2.modeler.ui.features.event.BoundaryEventFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.event.EndEventFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.event.IntermediateCatchEventFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.event.IntermediateThrowEventFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.event.StartEventFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.event.definitions.CancelEventDefinitionContainer;
import org.eclipse.bpmn2.modeler.ui.features.event.definitions.CompensateEventDefinitionContainer;
import org.eclipse.bpmn2.modeler.ui.features.event.definitions.ConditionalEventDefinitionContainer;
import org.eclipse.bpmn2.modeler.ui.features.event.definitions.ErrorEventDefinitionContainer;
import org.eclipse.bpmn2.modeler.ui.features.event.definitions.EscalationEventDefinitionContainer;
import org.eclipse.bpmn2.modeler.ui.features.event.definitions.LinkEventDefinitionContainer;
import org.eclipse.bpmn2.modeler.ui.features.event.definitions.MessageEventDefinitionContainer;
import org.eclipse.bpmn2.modeler.ui.features.event.definitions.SignalEventDefinitionContainer;
import org.eclipse.bpmn2.modeler.ui.features.event.definitions.TerminateEventDefinitionFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.event.definitions.TimerEventDefinitionContainer;
import org.eclipse.bpmn2.modeler.ui.features.flow.AssociationFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.flow.DataInputAssociationFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.flow.DataOutputAssociationFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.flow.MessageFlowFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.flow.SequenceFlowFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.gateway.AppendGatewayFeature;
import org.eclipse.bpmn2.modeler.ui.features.gateway.ComplexGatewayFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.gateway.EventBasedGatewayFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.gateway.ExclusiveGatewayFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.gateway.InclusiveGatewayFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.gateway.ParallelGatewayFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.label.LabelFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.lane.LaneFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.lane.RotateLaneFeature;
import org.eclipse.bpmn2.modeler.ui.features.participant.ParticipantFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.participant.RotatePoolFeature;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddBendpointFeature;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICopyFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IDirectEditingFeature;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IMoveBendpointFeature;
import org.eclipse.graphiti.features.IMoveShapeFeature;
import org.eclipse.graphiti.features.IPasteFeature;
import org.eclipse.graphiti.features.IReconnectionFeature;
import org.eclipse.graphiti.features.IRemoveBendpointFeature;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddBendpointContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICopyContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.IDoubleClickContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IMoveBendpointContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.context.IRemoveBendpointContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ILinkService;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;

/**
 * Determines what kinds of business objects can be added to a diagram.
 * 
 * @author imeikas
 * 
 */
public class BPMNFeatureProvider extends DefaultFeatureProvider {

	private List<FeatureContainer> containers;

	private ICreateFeature[] createFeatures;

	private ICreateConnectionFeature[] createConnectionFeatures;

	public BPMNFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);

		containers = new ArrayList<FeatureContainer>();
		containers.add(new LabelFeatureContainer());
		containers.add(new GroupFeatureContainer());
		containers.add(new DataObjectFeatureContainer());
		//containers.add(new DataObjectReferenceFeatureContainer());
		//containers.add(new DataStoreReferenceFeatureContainer());
		containers.add(new DataInputFeatureContainer());
		containers.add(new DataOutputFeatureContainer());
		containers.add(new MessageFeatureContainer());
		containers.add(new StartEventFeatureContainer());
		containers.add(new EndEventFeatureContainer());
		containers.add(new IntermediateCatchEventFeatureContainer());
		containers.add(new IntermediateThrowEventFeatureContainer());
		containers.add(new BoundaryEventFeatureContainer());
		containers.add(new TaskFeatureContainer());
		containers.add(new ScriptTaskFeatureContainer());
		containers.add(new ServiceTaskFeatureContainer());
		containers.add(new UserTaskFeatureContainer());
		containers.add(new ManualTaskFeatureContainer());
		containers.add(new BusinessRuleTaskFeatureContainer());
		containers.add(new SendTaskFeatureContainer());
		containers.add(new ReceiveTaskFeatureContainer());
		//containers.add(new ChoreographyTaskFeatureContainer());
		containers.add(new ExclusiveGatewayFeatureContainer());
		containers.add(new InclusiveGatewayFeatureContainer());
		containers.add(new ParallelGatewayFeatureContainer());
		containers.add(new EventBasedGatewayFeatureContainer());
		containers.add(new ComplexGatewayFeatureContainer());
		containers.add(new AdHocSubProcessFeatureContainer());
		containers.add(new CallActivityFeatureContainer());
		containers.add(new TransactionFeatureContainer());
		containers.add(new SubProcessFeatureContainer());
		containers.add(new ConditionalEventDefinitionContainer());
		containers.add(new MessageEventDefinitionContainer());
		containers.add(new TimerEventDefinitionContainer());
		containers.add(new SignalEventDefinitionContainer());
		containers.add(new EscalationEventDefinitionContainer());
		containers.add(new CompensateEventDefinitionContainer());
		containers.add(new LinkEventDefinitionContainer());
		containers.add(new ErrorEventDefinitionContainer());
		containers.add(new CancelEventDefinitionContainer());
		containers.add(new TerminateEventDefinitionFeatureContainer());
		containers.add(new SequenceFlowFeatureContainer());
		containers.add(new MessageFlowFeatureContainer());
		containers.add(new AssociationFeatureContainer());
		//containers.add(new ConversationFeatureContainer());
		////containers.add(new ConversationLinkFeatureContainer());
		containers.add(new DataInputAssociationFeatureContainer());
		containers.add(new DataOutputAssociationFeatureContainer());
		//containers.add(new SubChoreographyFeatureContainer());
		//containers.add(new CallChoreographyFeatureContainer());
		containers.add(new ParticipantFeatureContainer());
		containers.add(new LaneFeatureContainer());
		containers.add(new TextAnnotationFeatureContainer());
		//containers.add(new ChoreographyMessageLinkFeatureContainer());

		updateFeatureLists();
	}
	
	HashMap<Class,IFeature> mapBusinessObjectClassToCreateFeature = new HashMap<Class,IFeature>();

	public void addFeatureContainer(FeatureContainer fc) throws Exception {
		
		boolean canAdd = true;
		
		if (fc instanceof CustomTaskFeatureContainer) {
			ICustomTaskFeature ctfc = (ICustomTaskFeature)fc;
			for (FeatureContainer container : containers) {
				if (container instanceof CustomTaskFeatureContainer) {
					// don't add duplicates
					String oldId = ((ICustomTaskFeature)container).getId();
					String newId = ctfc.getId();
					if (oldId!=null && newId!=null) {
						if (oldId.equals(newId)) {
							if (container==fc)
								return;
							canAdd = false;
							break;
						}
					}
					else if (oldId==newId) {
						canAdd = false;
						break;
					}
				}
			}
		}
		else {
			for (FeatureContainer container : containers) {
				if (container.getClass().isInstance(fc)) {
					canAdd = false;
					break;
				}
			}
		}
		if (canAdd) {
			containers.add(fc);
			updateFeatureLists();
		}
		else
			throw new Exception("Attempt to add a Custom Feature with a duplicate ID "+fc.getClass().getName());
	}
	
	private void updateFeatureLists() {
		
		List<ICreateFeature> createFeaturesList = new ArrayList<ICreateFeature>();

		for (FeatureContainer container : containers) {
			ICreateFeature createFeature = container.getCreateFeature(this);
			if (createFeature != null) {
				createFeaturesList.add(createFeature);
			}
		}

		BPMN2Editor editor = BPMN2Editor.getActiveEditor(); //(BPMN2Editor)getDiagramTypeProvider().getDiagramEditor();;
		TargetRuntime rt = editor.getTargetRuntime();
		for (FeatureContainerDescriptor fcd : rt.getFeatureContainers()) {
			FeatureContainer container = fcd.getFeatureContainer();
			ICreateFeature createFeature = container.getCreateFeature(this);
			if (createFeature != null) {
				createFeaturesList.add(createFeature);
			}
		}

		createFeatures = createFeaturesList.toArray(new ICreateFeature[createFeaturesList.size()]);

		List<ICreateConnectionFeature> createConnectionFeatureList = new ArrayList<ICreateConnectionFeature>();

		for (FeatureContainer c : containers) {
			if (c instanceof ConnectionFeatureContainer) {
				ConnectionFeatureContainer connectionFeatureContainer = (ConnectionFeatureContainer) c;
				ICreateConnectionFeature createConnectionFeature = connectionFeatureContainer
						.getCreateConnectionFeature(this);
				if (createConnectionFeature == null) {
					continue;
				}
				createConnectionFeatureList.add(createConnectionFeature);
			}
		}

		createConnectionFeatures = createConnectionFeatureList
				.toArray(new ICreateConnectionFeature[createConnectionFeatureList.size()]);
		
		mapBusinessObjectClassToCreateFeature.clear();
		for (IFeature cf : createFeatures) {
			if (cf instanceof AbstractBpmn2CreateFeature) {
				if (cf instanceof CreateCustomTaskFeature) {
					continue;
				}
				AbstractBpmn2CreateFeature acf = (AbstractBpmn2CreateFeature)cf;
				mapBusinessObjectClassToCreateFeature.put(acf.getBusinessObjectClass(), cf);
			}
		}
		for (IFeature cf : createConnectionFeatures) {
			if (cf instanceof AbstractBpmn2CreateConnectionFeature) {
				AbstractBpmn2CreateConnectionFeature acf = (AbstractBpmn2CreateConnectionFeature)cf;
				mapBusinessObjectClassToCreateFeature.put(acf.getBusinessObjectClass(), cf);
			}
		}
	}
	
	private EObject getApplyObject(IContext context) {
		if (context instanceof IAddContext) {
			Object object = ((IAddContext) context).getNewObject();
			if (object instanceof EObject)
				return (EObject)object;
		} else if (context instanceof IPictogramElementContext) {
			return BusinessObjectUtil.getFirstElementOfType(
					(((IPictogramElementContext) context).getPictogramElement()), EObject.class);
		}
		return null;
	}
	public FeatureContainer getFeatureContainer(IContext context) {
		
		EObject object = getApplyObject(context);
		if (object!=null) {
			BPMN2Editor editor = (BPMN2Editor)getDiagramTypeProvider().getDiagramEditor();;
			TargetRuntime rt = editor.getTargetRuntime();
			FeatureContainerDescriptor fcd = rt.getFeatureContainer(object.eClass());
			if (fcd!=null)
				return fcd.getFeatureContainer();
		}
		
		Object id = CustomTaskFeatureContainer.getId(context); 
		for (FeatureContainer container : containers) {
			if (id!=null && !(container instanceof CustomTaskFeatureContainer))
				continue;
			Object o = container.getApplyObject(context);
			if (o != null && container.canApplyTo(o)) {
				return container;
			}
		}
		return null;
	}

	@Override
	public IAddFeature getAddFeature(IAddContext context) {
		// only here do we need to search all of the Custom Task extensions to check if
		// the newObject (in AddContext) is a Custom Task. This is because of a chicken/egg
		// problem during DIImport: the Custom Task feature containers are not added to
		// the toolpalette until AFTER the file is loaded (in DIImport) and getAddFeature()
		// is called during file loading.
		Object id = CustomTaskFeatureContainer.getId(context); 
		if (id!=null) {
			BPMN2Editor editor = (BPMN2Editor)getDiagramTypeProvider().getDiagramEditor();;
			TargetRuntime rt = editor.getTargetRuntime();
			for (CustomTaskDescriptor ct : rt.getCustomTasks()) {
				if (id.equals(ct.getId())) {
					CustomTaskFeatureContainer container = (CustomTaskFeatureContainer)ct.getFeatureContainer();
					return container.getAddFeature(this);
				}
			}
		}
		
		FeatureContainer container = getFeatureContainer(context);
		if (container!=null) {
			IAddFeature feature = container.getAddFeature(this);
			if (feature != null)
				return feature;
		}
		return super.getAddFeature(context);
	}

	@Override
	public ICreateFeature[] getCreateFeatures() {
		return createFeatures;
	}

	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		FeatureContainer container = getFeatureContainer(context);
		if (container!=null) {
			IUpdateFeature feature = container.getUpdateFeature(this);
			if (feature != null)
				return feature;
		}
		return super.getUpdateFeature(context);
	}

	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return createConnectionFeatures;
	}

	@Override
	public IDirectEditingFeature getDirectEditingFeature(IDirectEditingContext context) {
		FeatureContainer container = getFeatureContainer(context);
		if (container!=null) {
			IDirectEditingFeature feature = container.getDirectEditingFeature(this);
			if (feature != null)
				return feature;
		}
		return super.getDirectEditingFeature(context);
	}

	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		FeatureContainer container = getFeatureContainer(context);
		if (container!=null) {
			ILayoutFeature feature = container.getLayoutFeature(this);
			if (feature != null)
				return feature;
		}
		return super.getLayoutFeature(context);
	}

	@Override
	public IMoveShapeFeature getMoveShapeFeature(IMoveShapeContext context) {
		FeatureContainer container = getFeatureContainer(context);
		if (container!=null) {
			IMoveShapeFeature feature = container.getMoveFeature(this);
			if (feature != null)
				return feature;
		}
		return super.getMoveShapeFeature(context);
	}

	@Override
	public IResizeShapeFeature getResizeShapeFeature(IResizeShapeContext context) {
		FeatureContainer container = getFeatureContainer(context);
		if (container!=null) {
			IResizeShapeFeature feature = container.getResizeFeature(this);
			if (feature != null)
				return feature;
		}
		return super.getResizeShapeFeature(context);
	}

	@Override
	public IAddBendpointFeature getAddBendpointFeature(IAddBendpointContext context) {
		return new AddBendpointFeature(this);
	}

	@Override
	public IMoveBendpointFeature getMoveBendpointFeature(IMoveBendpointContext context) {
		return new MoveBendpointFeature(this);
	}

	@Override
	public IRemoveBendpointFeature getRemoveBendpointFeature(IRemoveBendpointContext context) {
		return new RemoveBendpointFeature(this);
	}

	@Override
	public IReconnectionFeature getReconnectionFeature(IReconnectionContext context) {
		for (FeatureContainer container : containers) {
			Object o = container.getApplyObject(context);
			if (o != null && container.canApplyTo(o) && container instanceof ConnectionFeatureContainer) {
				IReconnectionFeature feature = ((ConnectionFeatureContainer)container).getReconnectionFeature(this);
				if (feature == null) {
					break;
				}
				return feature;
			}
		}
		return super.getReconnectionFeature(context);
	}

	@Override
	public IDeleteFeature getDeleteFeature(IDeleteContext context) {
		FeatureContainer container = getFeatureContainer(context);
		if (container!=null) {
			IDeleteFeature feature = container.getDeleteFeature(this);
			if (feature != null)
				return feature;
		}
		return new DefaultDeleteBPMNShapeFeature(this);
	}

	@Override
	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		FeatureContainer container = getFeatureContainer(context);
		if (container!=null) {
			IRemoveFeature feature = container.getRemoveFeature(this);
			if (feature != null)
				return feature;
		}
		return new DefaultRemoveBPMNShapeFeature(this);
	}

	@Override
	public ICustomFeature[] getCustomFeatures(ICustomContext context) {
		ILinkService ls = Graphiti.getLinkService();
		List<ICustomFeature> list = new ArrayList<ICustomFeature>();

		BPMN2Editor editor = (BPMN2Editor)getDiagramTypeProvider().getDiagramEditor();;
		TargetRuntime rt = editor.getTargetRuntime();
		for (CustomTaskDescriptor ct : rt.getCustomTasks()) {
			ICustomTaskFeature ctf = ct.getFeatureContainer();
			if (ctf!=null) {
				ICustomFeature[] cfa = ctf.getCustomFeatures(this);
				if (cfa!=null) {
					for (ICustomFeature cf : cfa) {
						if (cf.isAvailable(context))
							list.add(cf);
					}
				}
			}
		}
		for (FeatureContainer fc : containers) {
			Object o = fc.getApplyObject(context);
			if (o!=null && fc.canApplyTo(o)) {
				ICustomFeature[] cfa = fc.getCustomFeatures(this);
				if (cfa!=null) {
					for (ICustomFeature cf : cfa) {
						list.add(cf);
					}
				}
			}
		}
		
		return list.toArray(new ICustomFeature[list.size()]);
	}

	// TODO: move this to the adapter registry
	public IFeature getCreateFeatureForPictogramElement(PictogramElement pe) {
		if (pe!=null) {
			String id = Graphiti.getPeService().getPropertyValue(pe,ICustomTaskFeature.CUSTOM_TASK_ID);
			if (id!=null) {
				for (FeatureContainer container : containers) {
					if (container instanceof CustomTaskFeatureContainer) {
						CustomTaskFeatureContainer ctf = (CustomTaskFeatureContainer)container;
						if (id.equals(ctf.getId())) {
							return ctf.getCreateFeature(this);
						}
					}
				}
			}

			EObject be = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
			return getCreateFeatureForBusinessObject(be);
		}
		return null;
	}
	
	public IFeature getCreateFeatureForBusinessObject(Object be) {
		IFeature feature = null;
		if (be!=null) {
			Class[] ifs = be.getClass().getInterfaces();
			for (int i=0; i<ifs.length && feature==null; ++i) {
				feature = mapBusinessObjectClassToCreateFeature.get(ifs[i]);
			}
		}
		return feature;
	}
	
	public IFeature getCreateFeatureForBusinessObject(Class clazz) {
		return mapBusinessObjectClassToCreateFeature.get(clazz);
	}

	@Override
	public ICopyFeature getCopyFeature(ICopyContext context) {
		// TODO Auto-generated method stub
		return new CopyBaseElementFeature(this);
	}

	@Override
	public IPasteFeature getPasteFeature(IPasteContext context) {
		// TODO Auto-generated method stub
		return new PasteBaseElementFeature(this);
	}
}