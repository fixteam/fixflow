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
package org.eclipse.bpmn2.modeler.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.Artifact;
import org.eclipse.bpmn2.Association;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Choreography;
import org.eclipse.bpmn2.ChoreographyTask;
import org.eclipse.bpmn2.Collaboration;
import org.eclipse.bpmn2.ConversationLink;
import org.eclipse.bpmn2.ConversationNode;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.Import;
import org.eclipse.bpmn2.InputOutputSpecification;
import org.eclipse.bpmn2.InteractionNode;
import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.LaneSet;
import org.eclipse.bpmn2.MessageFlow;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.PotentialOwner;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.ResourceAssignmentExpression;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.di.BPMNEdge;
import org.eclipse.bpmn2.di.BPMNPlane;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.di.BpmnDiFactory;
import org.eclipse.bpmn2.di.BpmnDiPackage;
import org.eclipse.bpmn2.di.ParticipantBandKind;
import org.eclipse.bpmn2.modeler.core.adapters.InsertionAdapter;
import org.eclipse.bpmn2.modeler.core.preferences.Bpmn2Preferences;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil.Bpmn2DiagramType;
import org.eclipse.bpmn2.modeler.core.utils.NamespaceUtil;
import org.eclipse.bpmn2.util.Bpmn2Resource;
import org.eclipse.bpmn2.util.Bpmn2ResourceImpl;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.dd.dc.Bounds;
import org.eclipse.dd.dc.DcFactory;
import org.eclipse.dd.dc.Point;
import org.eclipse.dd.di.DiagramElement;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Internal;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.wst.wsdl.Definition;
import org.eclipse.xsd.XSDSchema;


import com.founder.fix.bpmn2extensions.fixflow.AssignPolicyType;
import com.founder.fix.bpmn2extensions.fixflow.Expression;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowFactory;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.fixflow.SkipStrategy;
import com.founder.fix.bpmn2extensions.fixflow.TaskCommand;


public class ModelHandler {

	Bpmn2ResourceImpl resource;
	Bpmn2Preferences prefs;
	
	ModelHandler() {
	}

	void createDefinitionsIfMissing() {
		EList<EObject> contents = resource.getContents();

		if (contents.isEmpty() || !(contents.get(0) instanceof DocumentRoot)) {
			TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(resource);

			if (domain != null) {
				final DocumentRoot docRoot = create(DocumentRoot.class);
				final Definitions definitions = create(Definitions.class);

				domain.getCommandStack().execute(new RecordingCommand(domain) {
					@Override
					protected void doExecute() {
						docRoot.setDefinitions(definitions);
						resource.getContents().add(docRoot);
					}
				});
				return;
			}
		}
	}

	public Bpmn2Preferences getPreferences() {
		if (prefs==null)
			prefs = Bpmn2Preferences.getInstance(resource);
		return prefs;
	}
	
	public BPMNDiagram createDiagramType(final Bpmn2DiagramType diagramType, String targetNamespace) {
		BPMNDiagram diagram = null;
		switch (diagramType) {
		case PROCESS:
			diagram = createProcessDiagram("Default");
			break;
		case COLLABORATION:
			diagram = createCollaborationDiagram("Default");
			break;
		case CHOREOGRAPHY:
			diagram = createChoreographyDiagram("Default");
			break;
		}
		if (diagram!=null)
			getDefinitions().setTargetNamespace(targetNamespace);
		
		return diagram;
	}
	
	public BPMNDiagram createProcessDiagram(final String name) {
		
		EList<EObject> contents = resource.getContents();
		TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(resource);
		final BPMNDiagram bpmnDiagram = BpmnDiFactory.eINSTANCE.createBPMNDiagram();

		if (domain != null) {
			domain.getCommandStack().execute(new RecordingCommand(domain) {
				@Override
				protected void doExecute() {
					BPMNPlane plane = BpmnDiFactory.eINSTANCE.createBPMNPlane();
					ModelUtil.setID(plane,resource);

					Process process = createProcess();
					process.setName(name+" Process");
					// the Process ID should be the same as the resource name
					String filename = resource.getURI().lastSegment();
					if (filename.contains("."))
						filename = filename.split("\\.")[0];
					process.setId( ModelUtil.generateID(process,resource,filename) );

					// create StartEvent
					StartEvent startEvent = create(StartEvent.class);
//					startEvent.setName("Start Event");
					process.getFlowElements().add(startEvent);
					
					// create SequenceFlow
					SequenceFlow flow = create(SequenceFlow.class);
					process.getFlowElements().add(flow);
					
					// create EndEvent
					UserTask endEvent = create(UserTask.class);
					endEvent.setName("提交任务");
//					endEvent.setName("End Event");
					process.getFlowElements().add(endEvent);
					startEvent.setName("启动");
					// hook 'em up
					startEvent.getOutgoing().add(flow);
					endEvent.getIncoming().add(flow);
					flow.setSourceRef(startEvent);
					flow.setTargetRef(endEvent);
					endEvent.getResources().add(createPotentialOwner());
					
					ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE
					        .createExtensionAttributeValue();
					endEvent.getExtensionValues().add(extensionElement);
					FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
					        (org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_COMMAND, createTaskCommand());
					extensionElement.getValue().add(extensionElementEntry);

					
					extensionElementEntry = new SimpleFeatureMapEntry(
					        (org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_COMMAND, createTaskViewCommand());
					extensionElement.getValue().add(extensionElementEntry);
					FeatureMap.Entry extensionElementEntry2 = new SimpleFeatureMapEntry(
					        (org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__ASSIGN_POLICY_TYPE, createAssignPolicyType());
					extensionElement.getValue().add(extensionElementEntry2);
					
					
					FeatureMap.Entry extensionElementEntry3 = new SimpleFeatureMapEntry(
					        (org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__SKIP_STRATEGY, createSkipStrategy());
					extensionElement.getValue().add(extensionElementEntry3);
					
					
					

					// create DI shapes
					BPMNShape shape = BpmnDiFactory.eINSTANCE.createBPMNShape();
					ModelUtil.setID(shape,resource);

					// StartEvent shape
					shape.setBpmnElement(startEvent);
					Bounds bounds = DcFactory.eINSTANCE.createBounds();
					bounds.setX(100);
					bounds.setY(100);
					bounds.setWidth(GraphicsUtil.EVENT_SIZE);
					bounds.setHeight(GraphicsUtil.EVENT_SIZE);
					shape.setBounds(bounds);
					plane.getPlaneElement().add(shape);
					getPreferences().applyBPMNDIDefaults(shape, null);
					
					// SequenceFlow edge
					BPMNEdge edge = BpmnDiFactory.eINSTANCE.createBPMNEdge();
					edge.setBpmnElement(flow);
					edge.setSourceElement(shape);
					
					Point wp = DcFactory.eINSTANCE.createPoint();
					wp.setX(100+GraphicsUtil.EVENT_SIZE);
					wp.setY(100+GraphicsUtil.EVENT_SIZE/2);
					edge.getWaypoint().add(wp);
					
					wp = DcFactory.eINSTANCE.createPoint();
					wp.setX(500);
					wp.setY(100+GraphicsUtil.EVENT_SIZE/2);
					edge.getWaypoint().add(wp);
					
					plane.getPlaneElement().add(edge);

					// EndEvent shape
					shape = BpmnDiFactory.eINSTANCE.createBPMNShape();
					ModelUtil.setID(shape,resource);

					shape.setBpmnElement(endEvent);
					bounds = DcFactory.eINSTANCE.createBounds();
					bounds.setX(240);
					bounds.setY(93);
					bounds.setWidth(GraphicsUtil.TASK_DEFAULT_WIDTH);
					bounds.setHeight(GraphicsUtil.TASK_DEFAULT_HEIGHT);
					shape.setBounds(bounds);
					plane.getPlaneElement().add(shape);
					getPreferences().applyBPMNDIDefaults(shape, null);

					edge.setTargetElement(shape);
					
					// add to BPMNDiagram
					plane.setBpmnElement(process);
					bpmnDiagram.setPlane(plane);
					bpmnDiagram.setName(name+" 流程定义");
					getDefinitions().getDiagrams().add(bpmnDiagram);
				}

				
			});
		}
		return bpmnDiagram;
	}
	
	public SkipStrategy createSkipStrategy() {
		// TODO Auto-generated method stub
		SkipStrategy SkipStrategy=FixFlowFactory.eINSTANCE.createSkipStrategy();
		
		return SkipStrategy;
	}

	
	public PotentialOwner createPotentialOwner(){
		PotentialOwner potentialOwner = Bpmn2Factory.eINSTANCE.createPotentialOwner();
		
//		FeatureMap.Entry extensionAttributeEntry = new SimpleFeatureMapEntry((Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_TYPE, typecombo.getData(typecombo.getSelectionIndex()+"").toString());
		FeatureMap.Entry extensionAttributeEntry = new SimpleFeatureMapEntry((Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_TYPE, "user");
		potentialOwner.getAnyAttribute().add(extensionAttributeEntry);
		FeatureMap.Entry extensionAttributeEntry1 = new SimpleFeatureMapEntry((Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__INCLUDE_EXCLUSION, "INCLUDE");
		potentialOwner.getAnyAttribute().add(extensionAttributeEntry1);
		FeatureMap.Entry extensionAttributeEntry2 = new SimpleFeatureMapEntry((Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__IS_CONTAINS_SUB, "false");
		potentialOwner.getAnyAttribute().add(extensionAttributeEntry2);
		
		ResourceAssignmentExpression resourceAssignmentExpression = Bpmn2Factory.eINSTANCE.createResourceAssignmentExpression();
		/*FormalExpression formalExpression = ModelHandler.FACTORY.createFormalExpression();
		
		formalExpression.setBody(expcombo.getText());
		resourceAssignmentExpression.setExpression(formalExpression);*/
		
			FormalExpression formalExpression = Bpmn2Factory.eINSTANCE.createFormalExpression();
			
			
			formalExpression.setId("所有人");
			formalExpression.setBody("\"fixflow_allusers\"");
			resourceAssignmentExpression.setExpression(formalExpression);
		
	
		
		potentialOwner.setName("所有人");
		potentialOwner.setResourceAssignmentExpression(resourceAssignmentExpression);
		
		return potentialOwner;
	}
	
	public TaskCommand createTaskCommand(){
		
		TaskCommand taskCommand = FixFlowFactory.eINSTANCE.createTaskCommand();
		taskCommand.setId("Advance_1");
		taskCommand.setName("启动提交");
		taskCommand.setCommandType("startandsubmit");
		/*Expression expression = FixFlowFactory.eINSTANCE.createExpression();
		expression.setValue((DataVarTo)comboViewer.getElementAt(comboViewer.getCombo().getSelectionIndex()) == null ? "" : ((DataVarTo)comboViewer.getElementAt(comboViewer.getCombo().getSelectionIndex())).getValue());
		taskCommand.setExpression(expression);*/
		return taskCommand;
		
	}
	
	public TaskCommand createTaskViewCommand(){
		
		TaskCommand taskCommand = FixFlowFactory.eINSTANCE.createTaskCommand();
		taskCommand.setId("Advance_ProcessStatus");
		taskCommand.setName("流程状态");
		taskCommand.setCommandType("processStatus");
		/*Expression expression = FixFlowFactory.eINSTANCE.createExpression();
		expression.setValue((DataVarTo)comboViewer.getElementAt(comboViewer.getCombo().getSelectionIndex()) == null ? "" : ((DataVarTo)comboViewer.getElementAt(comboViewer.getCombo().getSelectionIndex())).getValue());
		taskCommand.setExpression(expression);*/
		return taskCommand;
		
	}
	
	public AssignPolicyType createAssignPolicyType(){
		
		
		AssignPolicyType assignPolicyType=FixFlowFactory.eINSTANCE.createAssignPolicyType();
		assignPolicyType.setId("potentialOwner");
		
	
		return assignPolicyType;
		
	}
	
	
	
	public BPMNDiagram createProcessDiagramW(final String name) {
		
		EList<EObject> contents = resource.getContents();
		TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(resource);
		final BPMNDiagram bpmnDiagram = BpmnDiFactory.eINSTANCE.createBPMNDiagram();

		if (domain != null) {
			domain.getCommandStack().execute(new RecordingCommand(domain) {
				@Override
				protected void doExecute() {
					BPMNPlane plane = BpmnDiFactory.eINSTANCE.createBPMNPlane();
					ModelUtil.setID(plane,resource);

					Process process = createProcess();
					process.setName(name+" Process");
					// the Process ID should be the same as the resource name
					String filename = resource.getURI().lastSegment();
					if (filename.contains("."))
						filename = filename.split("\\.")[0];
					process.setId( ModelUtil.generateID(process,resource,filename) );

					// create StartEvent
					StartEvent startEvent = create(StartEvent.class);
//					startEvent.setName("Start Event");
					process.getFlowElements().add(startEvent);
					
					// create SequenceFlow
					SequenceFlow flow = create(SequenceFlow.class);
					process.getFlowElements().add(flow);
					
					// create EndEvent
					EndEvent endEvent = create(EndEvent.class);
//					endEvent.setName("End Event");
					process.getFlowElements().add(endEvent);
					
					// hook 'em up
					startEvent.getOutgoing().add(flow);
					endEvent.getIncoming().add(flow);
					flow.setSourceRef(startEvent);
					flow.setTargetRef(endEvent);

					// create DI shapes
					BPMNShape shape = BpmnDiFactory.eINSTANCE.createBPMNShape();
					ModelUtil.setID(shape,resource);

					// StartEvent shape
					shape.setBpmnElement(startEvent);
					Bounds bounds = DcFactory.eINSTANCE.createBounds();
					bounds.setX(100);
					bounds.setY(100);
					bounds.setWidth(GraphicsUtil.EVENT_SIZE);
					bounds.setHeight(GraphicsUtil.EVENT_SIZE);
					shape.setBounds(bounds);
					plane.getPlaneElement().add(shape);
					getPreferences().applyBPMNDIDefaults(shape, null);
					
					// SequenceFlow edge
					BPMNEdge edge = BpmnDiFactory.eINSTANCE.createBPMNEdge();
					edge.setBpmnElement(flow);
					edge.setSourceElement(shape);
					
					Point wp = DcFactory.eINSTANCE.createPoint();
					wp.setX(100+GraphicsUtil.EVENT_SIZE);
					wp.setY(100+GraphicsUtil.EVENT_SIZE/2);
					edge.getWaypoint().add(wp);
					
					wp = DcFactory.eINSTANCE.createPoint();
					wp.setX(500);
					wp.setY(100+GraphicsUtil.EVENT_SIZE/2);
					edge.getWaypoint().add(wp);
					
					plane.getPlaneElement().add(edge);

					// EndEvent shape
					shape = BpmnDiFactory.eINSTANCE.createBPMNShape();
					ModelUtil.setID(shape,resource);

					shape.setBpmnElement(endEvent);
					bounds = DcFactory.eINSTANCE.createBounds();
					bounds.setX(500);
					bounds.setY(100);
					bounds.setWidth(GraphicsUtil.EVENT_SIZE);
					bounds.setHeight(GraphicsUtil.EVENT_SIZE);
					shape.setBounds(bounds);
					plane.getPlaneElement().add(shape);
					getPreferences().applyBPMNDIDefaults(shape, null);

					edge.setTargetElement(shape);
					
					// add to BPMNDiagram
					plane.setBpmnElement(process);
					bpmnDiagram.setPlane(plane);
					bpmnDiagram.setName(name+" Process Diagram");
					getDefinitions().getDiagrams().add(bpmnDiagram);
				}
			});
		}
		return bpmnDiagram;
	}

	public BPMNDiagram createCollaborationDiagram(final String name) {
	
		EList<EObject> contents = resource.getContents();
		TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(resource);
		final BPMNDiagram bpmnDiagram = BpmnDiFactory.eINSTANCE.createBPMNDiagram();

		if (domain != null) {
			domain.getCommandStack().execute(new RecordingCommand(domain) {
				@Override
				protected void doExecute() {
					List<BPMNDiagram> diagrams = getAll(BPMNDiagram.class);
					BPMNPlane plane = BpmnDiFactory.eINSTANCE.createBPMNPlane();
					ModelUtil.setID(plane,resource);

					Collaboration collaboration = createCollaboration();
					collaboration.setName(name+" Collaboration");

					//Process initiatingProcess = createProcess();
					//initiatingProcess.setName(name+" Initiating Process");
					
					Process initiatingProcess = createProcess();
					initiatingProcess.setName(name+" Process");
					// the Process ID should be the same as the resource name
					String filename = resource.getURI().lastSegment();
					if (filename.contains("."))
						filename = filename.split("\\.")[0];
					initiatingProcess.setId( ModelUtil.generateID(initiatingProcess,resource,filename) );
					
					// create StartEvent
					StartEvent startEvent = create(StartEvent.class);
					
					// create SequenceFlow
					SequenceFlow flow = create(SequenceFlow.class);
					
					// create EndEvent
					UserTask endEvent = create(UserTask.class);
					endEvent.setName("提交任务");
				
					startEvent.setName("启动");
					startEvent.getOutgoing().add(flow);
					endEvent.getIncoming().add(flow);
					flow.setSourceRef(startEvent);
					flow.setTargetRef(endEvent);
					//添加任务分配
					endEvent.getResources().add(createPotentialOwner());
					//添加处理命令
					ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE
					        .createExtensionAttributeValue();
					endEvent.getExtensionValues().add(extensionElement);
					FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
					        (org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_COMMAND, createTaskCommand());
					extensionElement.getValue().add(extensionElementEntry);
					extensionElementEntry = new SimpleFeatureMapEntry(
					        (org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_COMMAND, createTaskViewCommand());
					extensionElement.getValue().add(extensionElementEntry);
					
					FeatureMap.Entry extensionElementEntry2 = new SimpleFeatureMapEntry(
					        (org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__ASSIGN_POLICY_TYPE, createAssignPolicyType());
					extensionElement.getValue().add(extensionElementEntry2);
					
					FeatureMap.Entry extensionElementEntry3 = new SimpleFeatureMapEntry(
					        (org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__SKIP_STRATEGY, createSkipStrategy());
					extensionElement.getValue().add(extensionElementEntry3);
					
					
					
					
					initiatingProcess.getFlowElements().add(startEvent);
					initiatingProcess.getFlowElements().add(flow);
					initiatingProcess.getFlowElements().add(endEvent);
					
					
					Participant initiatingParticipant = create(Participant.class);
					//initiatingParticipant.setName("Initiating Pool");
					initiatingParticipant.setProcessRef(initiatingProcess);
					initiatingParticipant.setName(name+" Process");
					//Process nonInitiatingProcess = createProcess();
					//nonInitiatingProcess.setName(name+" Non-initiating Process");
					
					//Participant nonInitiatingParticipant = create(Participant.class);
					//nonInitiatingParticipant.setName("Non-initiating Pool");
					//nonInitiatingParticipant.setProcessRef(nonInitiatingProcess);
					
					collaboration.getParticipants().add(initiatingParticipant);
					//collaboration.getParticipants().add(nonInitiatingParticipant);

					// create DI shapes

					boolean horz = getPreferences().isHorizontalDefault();
					// initiating pool
					BPMNShape shape = BpmnDiFactory.eINSTANCE.createBPMNShape();
					ModelUtil.setID(shape,resource);

					shape.setBpmnElement(initiatingParticipant);
					Bounds bounds = DcFactory.eINSTANCE.createBounds();
					if (horz) {
						bounds.setX(20);
						bounds.setY(20);
						bounds.setWidth(740);
						bounds.setHeight(270);
					}
					else {
						bounds.setX(20);
						bounds.setY(20);
						bounds.setWidth(270);
						bounds.setHeight(740);
					}
					shape.setBounds(bounds);
					shape.setIsHorizontal(horz);
					plane.getPlaneElement().add(shape);
					getPreferences().applyBPMNDIDefaults(shape, null);
					
					
					
					
					
					
					// StartEvent shape
					shape = BpmnDiFactory.eINSTANCE.createBPMNShape();
					shape.setBpmnElement(startEvent);
					Bounds boundsstart = DcFactory.eINSTANCE.createBounds();
					boundsstart.setX(100);
					boundsstart.setY(100);
					boundsstart.setWidth(GraphicsUtil.EVENT_SIZE);
					boundsstart.setHeight(GraphicsUtil.EVENT_SIZE);
					shape.setBounds(boundsstart);
					plane.getPlaneElement().add(shape);
					getPreferences().applyBPMNDIDefaults(shape, null);
					
					// SequenceFlow edge
					BPMNEdge edge = BpmnDiFactory.eINSTANCE.createBPMNEdge();
					edge.setBpmnElement(flow);
					edge.setSourceElement(shape);
					
					Point wp = DcFactory.eINSTANCE.createPoint();
					wp.setX(100+GraphicsUtil.EVENT_SIZE);
					wp.setY(100+GraphicsUtil.EVENT_SIZE/2);
					edge.getWaypoint().add(wp);
					
					wp = DcFactory.eINSTANCE.createPoint();
					wp.setX(500);
					wp.setY(100+GraphicsUtil.EVENT_SIZE/2);
					edge.getWaypoint().add(wp);
					
					plane.getPlaneElement().add(edge);

					// EndEvent shape
					shape = BpmnDiFactory.eINSTANCE.createBPMNShape();
					ModelUtil.setID(shape,resource);

					shape.setBpmnElement(endEvent);
					bounds = DcFactory.eINSTANCE.createBounds();
					bounds.setX(240);
					bounds.setY(93);
					bounds.setWidth(GraphicsUtil.TASK_DEFAULT_WIDTH);
					bounds.setHeight(GraphicsUtil.TASK_DEFAULT_HEIGHT);
					shape.setBounds(bounds);
					plane.getPlaneElement().add(shape);
					getPreferences().applyBPMNDIDefaults(shape, null);

					edge.setTargetElement(shape);
					
					
					
					

					// non-initiating pool
					//shape = BpmnDiFactory.eINSTANCE.createBPMNShape();
					//ModelUtil.setID(shape,resource);

					//shape.setBpmnElement(nonInitiatingParticipant);
					//bounds = DcFactory.eINSTANCE.createBounds();
					/*if (horz) {
						bounds.setX(100);
						bounds.setY(400);
						bounds.setWidth(1000);
						bounds.setHeight(200);
					}
					else {
						bounds.setX(400);
						bounds.setY(100);
						bounds.setWidth(200);
						bounds.setHeight(1000);
					}
					shape.setBounds(bounds);
					shape.setIsHorizontal(horz);
					plane.getPlaneElement().add(shape);
					getPreferences().applyBPMNDIDefaults(shape, null);*/

					plane.setBpmnElement(collaboration);
					bpmnDiagram.setPlane(plane);
					bpmnDiagram.setName(name+" Collaboration Diagram");
					getDefinitions().getDiagrams().add(bpmnDiagram);
				}
			});
		}
		return bpmnDiagram;
	}
	

	public BPMNDiagram createChoreographyDiagram(final String name) {
	
		EList<EObject> contents = resource.getContents();
		TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(resource);
		final BPMNDiagram bpmnDiagram = BpmnDiFactory.eINSTANCE.createBPMNDiagram();

		if (domain != null) {
			domain.getCommandStack().execute(new RecordingCommand(domain) {
				@Override
				protected void doExecute() {
					List<BPMNDiagram> diagrams = getAll(BPMNDiagram.class);
					BPMNPlane plane = BpmnDiFactory.eINSTANCE.createBPMNPlane();
					ModelUtil.setID(plane,resource);

					Choreography choreography = createChoreography();
					choreography.setName(name+" Choreography");
					
					Participant initiatingParticipant = create(Participant.class);
					initiatingParticipant.setName(name+" Initiating Participant");

					Process initiatingProcess = createProcess();
					initiatingProcess.setName(name+" Initiating Process");
					initiatingParticipant.setProcessRef(initiatingProcess);
					
					Participant nonInitiatingParticipant = create(Participant.class);
					nonInitiatingParticipant.setName(name+" Non-initiating Participant");

					Process nonInitiatingProcess = createProcess();
					nonInitiatingProcess.setName(name+" Non-initiating Process");
					nonInitiatingParticipant.setProcessRef(nonInitiatingProcess);
					
					choreography.getParticipants().add(initiatingParticipant);
					choreography.getParticipants().add(nonInitiatingParticipant);
					
					ChoreographyTask task = create(ChoreographyTask.class);
					task.setName(name+" Choreography Task");
					task.getParticipantRefs().add(initiatingParticipant);
					task.getParticipantRefs().add(nonInitiatingParticipant);
					task.setInitiatingParticipantRef(initiatingParticipant);
					choreography.getFlowElements().add(task);

					BPMNShape taskShape = BpmnDiFactory.eINSTANCE.createBPMNShape();
					ModelUtil.setID(taskShape,resource);

					taskShape.setBpmnElement(task);
					Bounds bounds = DcFactory.eINSTANCE.createBounds();
					bounds.setX(100);
					bounds.setY(100);
					bounds.setWidth(GraphicsUtil.CHOREOGRAPHY_WIDTH);
					bounds.setHeight(GraphicsUtil.CHOREOGRAPHY_HEIGHT);
					taskShape.setBounds(bounds);
					plane.getPlaneElement().add(taskShape);
					getPreferences().applyBPMNDIDefaults(taskShape, null);

					BPMNShape participantShape = BpmnDiFactory.eINSTANCE.createBPMNShape();
					ModelUtil.setID(participantShape,resource);
					participantShape.setBpmnElement(initiatingParticipant);
					participantShape.setChoreographyActivityShape(taskShape);
					participantShape.setParticipantBandKind(ParticipantBandKind.TOP_INITIATING);
					bounds = DcFactory.eINSTANCE.createBounds();
					bounds.setX(100);
					bounds.setY(100);
					bounds.setWidth(GraphicsUtil.CHOREOGRAPHY_WIDTH);
					bounds.setHeight(GraphicsUtil.PARTICIPANT_BAND_HEIGHT);
					participantShape.setBounds(bounds);
					plane.getPlaneElement().add(participantShape);
					getPreferences().applyBPMNDIDefaults(participantShape, null);

					participantShape = BpmnDiFactory.eINSTANCE.createBPMNShape();
					ModelUtil.setID(participantShape,resource);
					participantShape.setBpmnElement(nonInitiatingParticipant);
					participantShape.setChoreographyActivityShape(taskShape);
					participantShape.setParticipantBandKind(ParticipantBandKind.BOTTOM_NON_INITIATING);
					bounds = DcFactory.eINSTANCE.createBounds();
					bounds.setX(100);
					bounds.setY(100 + GraphicsUtil.CHOREOGRAPHY_HEIGHT - GraphicsUtil.PARTICIPANT_BAND_HEIGHT);
					bounds.setWidth(GraphicsUtil.CHOREOGRAPHY_WIDTH);
					bounds.setHeight(GraphicsUtil.PARTICIPANT_BAND_HEIGHT);
					participantShape.setBounds(bounds);
					plane.getPlaneElement().add(participantShape);
					getPreferences().applyBPMNDIDefaults(participantShape, null);

					plane.setBpmnElement(choreography);
					bpmnDiagram.setPlane(plane);
					getDefinitions().getDiagrams().add(bpmnDiagram);
				}
			});
		}
		return bpmnDiagram;
	}
	
	
	public static ModelHandler getInstance(EObject object) throws IOException {
		return ModelHandlerLocator.getModelHandler(object.eResource());
	}

	public static Import addImport(EObject modelObject, Object importObject) {
		Resource resource = modelObject.eResource();
		return addImport(resource,importObject);
	}
	
	public static Import addImport(Resource resource, Object importObject) {
		Import imp = null;
		if (resource instanceof Bpmn2Resource) {
			final Definitions bpmn2Definitions = (Definitions) resource.getContents().get(0).eContents().get(0);

			if (importObject instanceof Definition) {
				// WSDL Definition
				Definition wsdlDefinition = (Definition)importObject;
	
				imp = Bpmn2Factory.eINSTANCE.createImport();
				imp.setImportType("http://schemas.xmlsoap.org/wsdl/");
				imp.setLocation(wsdlDefinition.getLocation());
				imp.setNamespace(wsdlDefinition.getTargetNamespace());
			}
			else if (importObject instanceof XSDSchema){
				// XSD Schema
				XSDSchema schema = (XSDSchema)importObject;
				
				imp = Bpmn2Factory.eINSTANCE.createImport();
				imp.setImportType("http://www.w3.org/2001/XMLSchema");
				imp.setLocation(schema.getSchemaLocation());
				imp.setNamespace(schema.getTargetNamespace());
			}
			if (imp!=null) {
				// make sure this is a new one!
				for (Import i : bpmn2Definitions.getImports()) {
					String location = i.getLocation();
					if (location!=null && location.equals(imp.getLocation())) {
						imp = null;
						break;
					}
				}
		
				if (imp!=null) {
					TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(resource);
					if (domain != null) {
						final Import i = imp;
						domain.getCommandStack().execute(new RecordingCommand(domain) {
							@Override
							protected void doExecute() {
								bpmn2Definitions.getImports().add(i);
								NamespaceUtil.addNamespace(i.eResource(), i.getNamespace());
							}
						});
					}
				}
			}
		}
		return imp;
	}

	/**
	 * @param <T>
	 * @param target
	 *            object that this element is being added to
	 * @param elem
	 *            flow element to be added
	 * @return
	 */
	public <T extends FlowElement> T addFlowElement(Object target, T elem) {
		FlowElementsContainer container = getFlowElementContainer(target);
		if(container==null){
			return null;
		}
		container.getFlowElements().add(elem);
		return elem;
	}

	/**
	 * @param <A>
	 * @param target
	 *            object that this artifact is being added to
	 * @param artifact
	 *            artifact to be added
	 * @return
	 */
	public <T extends Artifact> T addArtifact(Object target, T artifact) {
		Process process = getOrCreateProcess(getParticipant(target));
		process.getArtifacts().add(artifact);
		return artifact;
	}

	public <T extends RootElement> T addRootElement(T element) {
		getDefinitions().getRootElements().add(element);
		return element;
	}

	public DataOutput addDataOutput(Object target, DataOutput dataOutput) {
		getOrCreateIOSpecification(target).getDataOutputs().add(dataOutput);
		return dataOutput;
	}

	public DataInput addDataInput(Object target, DataInput dataInput) {
		getOrCreateIOSpecification(target).getDataInputs().add(dataInput);
		return dataInput;
	}

	public ConversationNode addConversationNode(BPMNDiagram bpmnDiagram, ConversationNode conversationNode) {
		Collaboration collaboration = getParticipantContainer(bpmnDiagram);
		if (collaboration!=null)
			collaboration.getConversations().add(conversationNode);
		return conversationNode;
	}

	private InputOutputSpecification getOrCreateIOSpecification(Object target) {
		Process process = getOrCreateProcess(getParticipant(target));
		if (process.getIoSpecification() == null) {
			InputOutputSpecification ioSpec = create(InputOutputSpecification.class);
			process.setIoSpecification(ioSpec);
		}
		return process.getIoSpecification();
	}

	public void moveFlowNode(FlowNode node, Object source, Object target) {
		FlowElementsContainer sourceContainer = getFlowElementContainer(source);
		FlowElementsContainer targetContainer = getFlowElementContainer(target);
		sourceContainer.getFlowElements().remove(node);
		targetContainer.getFlowElements().add(node);
		for (SequenceFlow flow : node.getOutgoing()) {
			sourceContainer.getFlowElements().remove(flow);
			targetContainer.getFlowElements().add(flow);
		}
	}

	public Participant addParticipant(BPMNDiagram bpmnDiagram) {
		Participant participant = null;
		Collaboration collaboration = getParticipantContainer(bpmnDiagram);
		if (collaboration!=null) {
			participant = create(Participant.class);
			collaboration.getParticipants().add(participant);
		}
		return participant;
	}

	@Deprecated
	public void moveLane(Lane movedLane, Participant targetParticipant) {
		Participant sourceParticipant = getParticipant(movedLane);
		moveLane(movedLane, sourceParticipant, targetParticipant);
	}

	public void moveLane(Lane movedLane, Participant sourceParticipant, Participant targetParticipant) {
		Process sourceProcess = getOrCreateProcess(sourceParticipant);
		Process targetProcess = getOrCreateProcess(targetParticipant);
		for (FlowNode node : movedLane.getFlowNodeRefs()) {
			moveFlowNode(node, sourceProcess, targetProcess);
		}
		if (movedLane.getChildLaneSet() != null && !movedLane.getChildLaneSet().getLanes().isEmpty()) {
			for (Lane lane : movedLane.getChildLaneSet().getLanes()) {
				moveLane(lane, sourceParticipant, targetParticipant);
			}
		}
	}

	public Process createProcess() {
		Process process = create(Process.class);
		getDefinitions().getRootElements().add(process);
		return process;
	}
	
	public Process getOrCreateProcess(Participant participant) {
		if (participant==null) {
			participant = getInternalParticipant();
		}
		if (participant!=null && participant.getProcessRef()!=null) {
			return participant.getProcessRef();
		}
		Process process = create(Process.class);
		getDefinitions().getRootElements().add(process);
		if (participant!=null) {
			participant.setProcessRef(process);
		}
		return process;
	}

	public Lane createLane(Lane targetLane) {
		Lane lane = create(Lane.class);

		if (targetLane.getChildLaneSet() == null) {
			targetLane.setChildLaneSet(create(LaneSet.class));
		}

		LaneSet targetLaneSet = targetLane.getChildLaneSet();
		targetLaneSet.getLanes().add(lane);

		lane.getFlowNodeRefs().addAll(targetLane.getFlowNodeRefs());
		targetLane.getFlowNodeRefs().clear();

		return lane;
	}

	public Lane createLane(Object target) {
		Lane lane = create(Lane.class);
		FlowElementsContainer container = getFlowElementContainer(target);
		if (container.getLaneSets().isEmpty()) {
			LaneSet laneSet = create(LaneSet.class);
			container.getLaneSets().add(laneSet);
		}
		container.getLaneSets().get(0).getLanes().add(lane);
		return lane;
	}

	public void laneToTop(Lane lane) {
		LaneSet laneSet = create(LaneSet.class);
		laneSet.getLanes().add(lane);
		Process process = getOrCreateProcess(getInternalParticipant());
		process.getLaneSets().add(laneSet);
	}

	public SequenceFlow createSequenceFlow(FlowNode source, FlowNode target) {
		SequenceFlow sequenceFlow = create(SequenceFlow.class);

		addFlowElement(source.eContainer(), sequenceFlow);
		sequenceFlow.setSourceRef(source);
		sequenceFlow.setTargetRef(target);
		return sequenceFlow;
	}

	public MessageFlow createMessageFlow(InteractionNode source, InteractionNode target) {
		MessageFlow messageFlow = null;
		Participant participant = getParticipant(source);
		if (participant!=null) {
			messageFlow = create(MessageFlow.class);
			messageFlow.setSourceRef(source);
			messageFlow.setTargetRef(target);
			if (participant.eContainer() instanceof Collaboration)
				((Collaboration)participant.eContainer()).getMessageFlows().add(messageFlow);
		}
		return messageFlow;
	}

	public ConversationLink createConversationLink(InteractionNode source, InteractionNode target) {
		ConversationLink link = null;
		Participant participant = getParticipant(source);
		if (participant!=null) {
			link = create(ConversationLink.class);
			link.setSourceRef(source);
			link.setTargetRef(target);
			if (participant.eContainer() instanceof Collaboration)
				((Collaboration)participant.eContainer()).getConversationLinks().add(link);
		}
		return link;
	}

	public Association createAssociation(BaseElement source, BaseElement target) {
		BaseElement e = null;
		if (getParticipant(source) != null) {
			e = source;
		} else if (getParticipant(target) != null) {
			e = target;
		} else {
			e = getInternalParticipant();
		}
		Association association = create(Association.class);
		addArtifact(e, association);
		association.setSourceRef(source);
		association.setTargetRef(target);
		return association;
	}

	private Collaboration getCollaboration() {
		final List<RootElement> rootElements = getDefinitions().getRootElements();

		for (RootElement element : rootElements) {
			if (element instanceof Collaboration) {
				return (Collaboration) element;
			}
		}
		return null;
	}
	
	public Collaboration createCollaboration() {
		Collaboration collaboration = create(Collaboration.class);
		getDefinitions().getRootElements().add(collaboration);
		return collaboration;
	}
	
	private Collaboration getOrCreateCollaboration() {
		Collaboration c = getCollaboration();
		if (c!=null)
			return c;
		
		final List<RootElement> rootElements = getDefinitions().getRootElements();
		TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(resource);
		final Collaboration collaboration = create(Collaboration.class);
		if (domain != null) {
			domain.getCommandStack().execute(new RecordingCommand(domain) {

				@Override
				protected void doExecute() {
					addCollaborationToRootElements(rootElements, collaboration);
				}
			});
		}
		return collaboration;
	}
	
	private Collaboration getParticipantContainer(BPMNDiagram bpmnDiagram) {
		if (bpmnDiagram==null) {
			// return the first Collaboration or Choreography in the model hierarchy
			List<RootElement> rootElements = getDefinitions().getRootElements();
			for (RootElement element : rootElements) {
				// yeah, Collaboration and Choreography are both instanceof Collaboration...
				if (element instanceof Collaboration || element instanceof Choreography) {
					return (Collaboration)element;
				}
			}
		}
		else {
			BaseElement be = bpmnDiagram.getPlane().getBpmnElement();
			if (be instanceof Collaboration || be instanceof Choreography) {
				return (Collaboration)be;
			}
		}
		return null;
	}
	
	public Choreography createChoreography() {
		Choreography choreography = create(Choreography.class);
		getDefinitions().getRootElements().add(choreography);
		return choreography;
	}

	private void addCollaborationToRootElements(final List<RootElement> rootElements, final Collaboration collaboration) {
		Participant participant = create(Participant.class);
		for (RootElement element : rootElements) {
			if (element instanceof Process) {
				participant.setProcessRef((Process) element);
				break;
			}
		}
		collaboration.getParticipants().add(participant);
		rootElements.add(collaboration);
	}

	private void addChoreographyToRootElements(final List<RootElement> rootElements, final Choreography choreography) {
		Participant participant = create(Participant.class);
		for (RootElement element : rootElements) {
			if (element instanceof Process) {
				participant.setProcessRef((Process) element);
				break;
			}
		}
		choreography.getParticipants().add(participant);
		rootElements.add(choreography);
	}

	public Bpmn2ResourceImpl getResource() {
		return resource;
	}

	public Definitions getDefinitions() {
		return (Definitions) resource.getContents().get(0).eContents().get(0);
	}

	public void save() {
		TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(resource);
		if (domain != null) {
			domain.getCommandStack().execute(new RecordingCommand(domain) {
				@Override
				protected void doExecute() {
					saveResource();
				}
			});
		} else {
			saveResource();
		}
	}

	private void saveResource() {
		fixZOrder();
		try {
			resource.save(null);
		} catch (IOException e) {
			Activator.logError(e);
		}
	}

	private void fixZOrder() {
		final List<BPMNDiagram> diagrams = getAll(BPMNDiagram.class);
		for (BPMNDiagram bpmnDiagram : diagrams) {
			fixZOrder(bpmnDiagram);
		}

	}

	private void fixZOrder(BPMNDiagram bpmnDiagram) {
		EList<DiagramElement> elements = (EList<DiagramElement>) bpmnDiagram.getPlane().getPlaneElement();
		ECollections.sort(elements, new DIZorderComparator());
	}

	void loadResource() {
		try {
			resource.load(null);
		} catch (IOException e) {
			Activator.logError(e);
		}
	}

	public Participant getInternalParticipant() {
		Collaboration collaboration = getParticipantContainer(null);
		if (collaboration!=null && collaboration.getParticipants().size()>0) {
			return collaboration.getParticipants().get(0);
		}
		return null;
	}

	public FlowElementsContainer getFlowElementContainer(Object o) {
		if (o == null) {
			return getOrCreateProcess(getInternalParticipant());
		}
		if (o instanceof Diagram) {
	        o = BusinessObjectUtil.getFirstElementOfType((Diagram)o, BPMNDiagram.class);
		}
		if (o instanceof BPMNDiagram) {
			BaseElement be = ((BPMNDiagram)o).getPlane().getBpmnElement();
			if (be instanceof FlowElementsContainer)
				return (FlowElementsContainer)be;
		}
		if (o instanceof Participant) {
			return getOrCreateProcess((Participant) o);
		}
		if (o instanceof SubProcess) {
//			EObject container = (SubProcess)o;
//			while (!(container instanceof Process) && container.eContainer()!=null) {
//				container = container.eContainer();
//			}
//			return (FlowElementsContainer) container;
			return (FlowElementsContainer) o;
		}
		return findElementOfType(FlowElementsContainer.class, o);
	}

	public Participant getParticipant(final Object o) {
		if (o == null) {
			return getInternalParticipant();
		}
		
		if (o instanceof Diagram) {
	        BPMNDiagram bpmnDiagram = BusinessObjectUtil.getFirstElementOfType((Diagram)o, BPMNDiagram.class);
	        Collaboration collaboration = getParticipantContainer(bpmnDiagram);
			if (collaboration!=null && collaboration.getParticipants().size()>0) {
				return collaboration.getParticipants().get(0);
			}
			return null;
		}

		Object object = o;
		if (o instanceof Shape) {
			object = BusinessObjectUtil.getFirstElementOfType((PictogramElement) o, BaseElement.class);
		}

		if (object instanceof Participant) {
			return (Participant) object;
		}

		Process process = findElementOfType(Process.class, object);
		
		Collaboration collaboration = getParticipantContainer(null);
		if (collaboration!=null) {
			if (process==null) {
				if (collaboration.getParticipants().size()>0)
					return collaboration.getParticipants().get(0);
			}
			else {
				for (Participant p : collaboration.getParticipants()) {
					if (p.getProcessRef() != null && p.getProcessRef().equals(process)) {
						return p;
					}
				}
			}
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T extends BaseElement> T findElementOfType(Class<T> clazz, Object from) {
		if (!(from instanceof BaseElement)) {
			return null;
		}

		if (clazz.isAssignableFrom(from.getClass())) {
			return (T) from;
		}

		return findElementOfType(clazz, ((BaseElement) from).eContainer());
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getAll(final Class<T> class1) {
		ArrayList<T> l = new ArrayList<T>();
		TreeIterator<EObject> contents = resource.getAllContents();
		for (; contents.hasNext();) {
			Object t = contents.next();
			if (class1.isInstance(t)) {
				l.add((T) t);
			}
		}
		return l;
	}

	public DiagramElement findDIElement(BaseElement element) {
		String id = element.getId();
		if (id==null || id.isEmpty())
			return null;

		List<BPMNDiagram> diagrams = getAll(BPMNDiagram.class);
		for (BPMNDiagram d : diagrams) {
			List<DiagramElement> planeElement = d.getPlane().getPlaneElement();
			
			for (DiagramElement elem : planeElement) {
				if (elem instanceof BPMNShape && ((BPMNShape) elem).getBpmnElement() != null &&
						id.equals(((BPMNShape) elem).getBpmnElement().getId())) {
					return (elem);
				} else if (elem instanceof BPMNEdge &&
						id.equals(((BPMNEdge) elem).getBpmnElement().getId())) {
					return (elem);
				}
			}
		}
		return null;
	}

	public BaseElement findElement(String id) {
		if (id==null || id.isEmpty())
			return null;
		
		List<BaseElement> baseElements = getAll(BaseElement.class);

		for (BaseElement be : baseElements) {
			if (id.equals(be.getId())) {
				return be;
			}
		}

		return null;
	}
	
	/**
	 * General-purpose factory method that sets appropriate default values for new objects.
	 */
	public EObject create(EClass eClass) {
		EObject newObject = null;
		EPackage pkg = eClass.getEPackage();
		EFactory factory = pkg.getEFactoryInstance();
		// make sure we don't try to construct abstract objects here!
		if (eClass == Bpmn2Package.eINSTANCE.getExpression())
			eClass = Bpmn2Package.eINSTANCE.getFormalExpression();
		newObject = factory.create(eClass);
		initialize(newObject);
		return newObject;
	}
	
	public <T extends EObject> T createStandby(EObject object, EStructuralFeature feature, Class<T> clazz) {
		T newObject = create(clazz);
		newObject.eAdapters().add(new InsertionAdapter(resource, object, feature, newObject));
		return newObject;
	}

	public <T extends EObject> T create(Class<T> clazz) {
		EObject newObject = null;
		EClassifier eClassifier = Bpmn2Package.eINSTANCE.getEClassifier(clazz.getSimpleName());
		if (eClassifier instanceof EClass) {
			EClass eClass = (EClass)eClassifier;
			newObject = Bpmn2Factory.eINSTANCE.create(eClass);
		}
		else {
			// maybe it's a DI object type?
			eClassifier = BpmnDiPackage.eINSTANCE.getEClassifier(clazz.getSimpleName());
			if (eClassifier instanceof EClass) {
				EClass eClass = (EClass)eClassifier;
				newObject = BpmnDiFactory.eINSTANCE.create(eClass);
			}
		}
		
		if (newObject!=null) {
			initialize(newObject);
		}

		return (T)newObject;
	}
	
	public void initialize(EObject newObject) {
		if (newObject!=null) {
//			if (newObject.eClass().getEPackage() == Bpmn2Package.eINSTANCE) {
//				// Set appropriate default values for the object features here
//				switch (newObject.eClass().getClassifierID()) {
//				case Bpmn2Package.ITEM_DEFINITION:
//					((ItemDefinition)newObject).setItemKind(ItemKind.INFORMATION);
//				}
//			}
			
			// if the object has an "id", assign it now.
			String id = ModelUtil.setID(newObject,resource);
			// also set a default name
//			EStructuralFeature feature = newObject.eClass().getEStructuralFeature("name");
//			if (feature!=null) {
//				if (id!=null)
//					newObject.eSet(feature, ModelUtil.toDisplayName(id));
//				else
//					newObject.eSet(feature, "New "+ModelUtil.toDisplayName(newObject.eClass().getName()));
//			}
		}
	}
}