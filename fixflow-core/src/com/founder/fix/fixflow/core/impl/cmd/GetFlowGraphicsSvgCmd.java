/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 */
package com.founder.fix.fixflow.core.impl.cmd;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Association;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.BusinessRuleTask;
import org.eclipse.bpmn2.CallActivity;
import org.eclipse.bpmn2.ComplexGateway;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataObject;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.DataStoreReference;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.ErrorEventDefinition;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.ExclusiveGateway;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.Group;
import org.eclipse.bpmn2.InclusiveGateway;
import org.eclipse.bpmn2.IntermediateCatchEvent;
import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.ManualTask;
import org.eclipse.bpmn2.Message;
import org.eclipse.bpmn2.MessageFlow;
import org.eclipse.bpmn2.MultiInstanceLoopCharacteristics;
import org.eclipse.bpmn2.ParallelGateway;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.ReceiveTask;
import org.eclipse.bpmn2.ScriptTask;
import org.eclipse.bpmn2.SendTask;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.ServiceTask;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.TerminateEventDefinition;
import org.eclipse.bpmn2.TextAnnotation;
import org.eclipse.bpmn2.TimerEventDefinition;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.di.BPMNEdge;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.dd.dc.Point;
import org.eclipse.dd.di.DiagramElement;
import org.eclipse.emf.ecore.impl.BasicEObjectImpl;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.DefinitionsBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.IntermediateCatchEventBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.FlowSvgUtil;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.SvgBench;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.LoopType;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgAnnotationTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgAssocationTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgCallActivityTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgComplexGatewayTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgDataInputTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgDataObjectTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgDataOutputTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgDataStoreTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgEndErrorEventTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgEndTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgExclusiveGatewayTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgGroupTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgInclusiveGatewayTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgIntermediateErrorEventCancelTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgIntermediateErrorEventTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgIntermediateEventTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgIntermediateTimeEventCancelTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgIntermediateTimerEventTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgLaneTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgLineBaseTo.SvgPoint;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgLineTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgLineTo.LineType;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgMessageFlowTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgMessageTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgParallelGatewayTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgShapeBaseTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgStartTimerEventTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgStartTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgSubProcessTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgTaskTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgTaskTo.TaskType;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgTerminateEndEventTo;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;

public class GetFlowGraphicsSvgCmd implements Command<String> {

	/**
	 * 流程定义编号
	 */
	protected String processDefinitionId;
	
	
	protected DefinitionsBehavior definitions;

	public GetFlowGraphicsSvgCmd(String processDefinitionId) {
		
		
		this.processDefinitionId = processDefinitionId;
		
		
	}

	public String execute(CommandContext commandContext) {

		ProcessDefinitionManager processDefinitionManager = commandContext.getProcessDefinitionManager();
		
		ProcessDefinitionBehavior processDefinition = processDefinitionManager.findLatestProcessDefinitionById(processDefinitionId);
		
		definitions = processDefinition.getDefinitions();
		
		List<BPMNDiagram> BPMNDiagramList = definitions.getDiagrams();

		SvgBench svg = new SvgBench();
		float maxX=0;
		float maxY=0;
		float minY=0;
		float minX=0;
		for (BPMNDiagram bpmnDiagram : BPMNDiagramList) {
			
			
			//for (int i = bpmnDiagram.getPlane().getPlaneElement().size()-1; i >= 0; i--) {
			//	DiagramElement diagramElement = bpmnDiagram.getPlane().getPlaneElement().get(i);
				
			//}
			
			for (DiagramElement diagramElement : bpmnDiagram.getPlane().getPlaneElement()) {

				if (diagramElement instanceof BPMNShape) {
					BPMNShape bpmnShape = (BPMNShape) diagramElement;
					if(bpmnShape.getBounds().getX()+bpmnShape.getBounds().getWidth()>maxX)
					{
						maxX=bpmnShape.getBounds().getX()+bpmnShape.getBounds().getWidth();
			
					}
					if(bpmnShape.getBounds().getY()+bpmnShape.getBounds().getHeight()>maxY)
					{
						maxY=bpmnShape.getBounds().getY()+bpmnShape.getBounds().getHeight();
						
					}
					
				
					if(minY==0){
						minY=bpmnShape.getBounds().getY();
					}else{
						if(bpmnShape.getBounds().getY()<minY)
						{
							minY=bpmnShape.getBounds().getY();
						}
					}
					
					if(minX==0){
						minX=bpmnShape.getBounds().getX();
					}else{
						if(bpmnShape.getBounds().getX()<minX)
						{
							minX=bpmnShape.getBounds().getX();
						
						}
					}
					
					BaseElement bpmnElement=getBaseElement(bpmnShape.getBpmnElement());
				
					
					
					if(bpmnElement==null){
						continue;
					}
					
				
					
					if (bpmnElement instanceof StartEvent) {
						
						String startEventSVG = startEventToSVG(bpmnShape);
						svg.addChildren(startEventSVG);

					}
					if (bpmnElement instanceof EndEvent) {
						String endEventSVG = endEventToSVG(bpmnShape);
						svg.addChildren(endEventSVG);

					}
					
					if (bpmnElement instanceof IntermediateCatchEventBehavior) {
						String intermediateTimerEventSVG = intermediateTimerEventToSVG(bpmnShape);
						svg.addChildren(intermediateTimerEventSVG);
						
					}
					

					if (bpmnElement instanceof Task) {

						String taskSVG = taskToSVG(bpmnShape);
						svg.addChildren(taskSVG);

					}
					
					if (bpmnElement instanceof CallActivity) {

						String taskSVG = callActivityToSVG(bpmnShape);
						svg.addChildren(taskSVG);

					}

					if (bpmnElement instanceof Gateway) {
						String gatewaySVG = gatewayToSVG(bpmnShape);
						svg.addChildren(gatewaySVG);
					}
					
					if(bpmnElement instanceof Lane)
					{
						String laneSVG = laneToSVG(bpmnShape);
						svg.addChildren(laneSVG);
					}
					
					if(bpmnElement instanceof Participant)
					{
						String laneSVG = participantToSVG(bpmnShape);
						svg.addChildren(laneSVG);
					}
					
					
					
					if(bpmnElement instanceof SubProcess)
					{
						String subProcessSVG = subProcessToSVG(bpmnShape);
						svg.addChildren(subProcessSVG);
					}
					if(bpmnElement instanceof Group)
					{
						String subProcessSVG = groupToSVG(bpmnShape,bpmnElement);
						svg.addChildren(subProcessSVG);
					}
					if(bpmnElement instanceof DataObject)
					{
						String dataObjectSVG= dataObjectToSVG(bpmnShape,bpmnElement);
						svg.addChildren(dataObjectSVG);
					}
					//DataStoreReference  //DataInput  //DataOutput  //Message
					if(bpmnElement instanceof DataStoreReference)
					{
						String dataStoreReferenceSVG= dataStoreReferenceToSVG(bpmnShape,bpmnElement);
						svg.addChildren(dataStoreReferenceSVG);
					}
					if(bpmnElement instanceof DataInput)
					{
						String dataInputSVG= dataInputToSVG(bpmnShape,bpmnElement);
						svg.addChildren(dataInputSVG);
					}
					if(bpmnElement instanceof DataOutput)
					{
						String dataOutputSVG= dataOutputToSVG(bpmnShape,bpmnElement);
						svg.addChildren(dataOutputSVG);
					}
					if(bpmnElement instanceof Message)
					{
						String messageSVG= messageToSVG(bpmnShape,bpmnElement);
						svg.addChildren(messageSVG);
					}
					
					
					if(bpmnElement instanceof TextAnnotation)
					{
						String messageSVG= textAnnotationToSVG(bpmnShape,bpmnElement);
						svg.addChildren(messageSVG);
					}
					
					
					if(bpmnElement instanceof BoundaryEvent)
					{
						String messageSVG= boundaryEventToSVG(bpmnShape,bpmnElement);
						svg.addChildren(messageSVG);
					}
					
					
					
					
				}
				if (diagramElement instanceof BPMNEdge) {
					BPMNEdge bpmnEdge = (BPMNEdge) diagramElement;
					
					List<Point> pointList = bpmnEdge.getWaypoint();
					for (Point point : pointList) {
						
						
						if(point.getX()>maxX)
						{
							maxX=point.getX();
						
						}
						if(point.getY()>maxY)
						{
							maxY=point.getY();
						}
	
					}
					BaseElement bpmnElement=getBaseElement(bpmnEdge.getBpmnElement());
					if (bpmnElement instanceof SequenceFlow) {
						String sequenceFlowSVG = sequenceFlowToSVG(bpmnEdge);
						svg.addEdge(sequenceFlowSVG);
					}
					if (bpmnElement instanceof Association) {
						String associationSVG = associationToSVG(bpmnEdge);
						svg.addEdge(associationSVG);
					}
					if (bpmnElement instanceof MessageFlow) {
						String messageFlowSVG = messageFlowToSVG(bpmnEdge);
						svg.addChildren(messageFlowSVG);

					}
					
				}

			}
		}
		svg.setWidth(maxX+30);
		svg.setHight(maxY+70);
		svg.setMhight(minY);
		svg.setMwidth(minX);
		return svg.release();

	}
	
	

	private String messageFlowToSVG(BPMNEdge bpmnEdge) {
		List<Point> pointList = bpmnEdge.getWaypoint();

		String id = getBaseElement(bpmnEdge.getBpmnElement()).getId();
		//String label = ((FlowElement) getBaseElement(bpmnEdge.getBpmnElement())).getName();
		// line.setLineType(LineType.ConditionalFlow);

		SvgMessageFlowTo svgMessageFlowTo = new SvgMessageFlowTo();

		

		svgMessageFlowTo.setId(id);
		//svgMessageFlowTo.setLabel(label);
		List<SvgPoint> svgPointList = new ArrayList<SvgPoint>();
		for (Point point : pointList) {
			SvgPoint svgPoint = svgMessageFlowTo.new SvgPoint();
			svgPoint.setX(point.getX());
			svgPoint.setY(point.getY());
			svgPointList.add(svgPoint);
		}
		svgMessageFlowTo.setSvgPointList(svgPointList);

		String lines = FlowSvgUtil.getSvgComponent(svgMessageFlowTo);

		return lines;
	}

	private String boundaryEventToSVG(BPMNShape bpmnShape, BaseElement bpmnElement) {
	
			BoundaryEvent boundaryEvent=(BoundaryEvent)getBaseElement(bpmnShape.getBpmnElement());
			
			for (EventDefinition eventDefinition : boundaryEvent.getEventDefinitions()) {
				if(eventDefinition instanceof TimerEventDefinition){
					if(boundaryEvent.isCancelActivity()){
						return CommonNodeToSVG(bpmnShape, new SvgIntermediateTimerEventTo());
					}
					else{
						return CommonNodeToSVG(bpmnShape, new SvgIntermediateTimeEventCancelTo());
					}
					
				}
				if(eventDefinition instanceof ErrorEventDefinition){
					if(boundaryEvent.isCancelActivity()){
						return CommonNodeToSVG(bpmnShape, new SvgIntermediateErrorEventTo());
					}
					else{
						return CommonNodeToSVG(bpmnShape, new SvgIntermediateErrorEventCancelTo());
					}
					
				}
			}
			
			return CommonNodeToSVG(bpmnShape, new SvgIntermediateEventTo());
			
		
	}

	private String textAnnotationToSVG(BPMNShape bpmnShape, BaseElement bpmnElement) {
		// TODO Auto-generated method stub
		return textAnnotationToSVG(bpmnShape, new SvgAnnotationTo());
	}
	
	private String textAnnotationToSVG(BPMNShape bpmnShape, SvgShapeBaseTo svgShapeBaseTo) {
		float height = bpmnShape.getBounds().getHeight();
		float width = bpmnShape.getBounds().getWidth();
		float x = bpmnShape.getBounds().getX();
		float y = bpmnShape.getBounds().getY();
		String id = getBaseElement(bpmnShape.getBpmnElement()).getId();
		
	
		String label = ((TextAnnotation)getBaseElement(bpmnShape.getBpmnElement())).getText();

		svgShapeBaseTo.setHeight(height);
		svgShapeBaseTo.setWidth(width);
		svgShapeBaseTo.setId(id);
		svgShapeBaseTo.setLabel(label);
		svgShapeBaseTo.setX(x);
		svgShapeBaseTo.setY(y);
		String child = FlowSvgUtil.getSvgComponent(svgShapeBaseTo);
		return child;
	}

	private String messageToSVG(BPMNShape bpmnShape, BaseElement bpmnElement) {
		// TODO Auto-generated method stub
		return messageToSVG(bpmnShape, new SvgMessageTo());
	}

	private String dataOutputToSVG(BPMNShape bpmnShape, BaseElement bpmnElement) {
		// TODO Auto-generated method stub
		return dataOutputToSVG(bpmnShape, new SvgDataOutputTo());
	}

	private String dataInputToSVG(BPMNShape bpmnShape, BaseElement bpmnElement) {
		// TODO Auto-generated method stub
		return dataInputToSVG(bpmnShape, new SvgDataInputTo());
	}

	private String dataStoreReferenceToSVG(BPMNShape bpmnShape, BaseElement bpmnElement) {
		// TODO Auto-generated method stub
		return CommonNodeToSVG(bpmnShape, new SvgDataStoreTo());
	}

	private String dataObjectToSVG(BPMNShape bpmnShape, BaseElement bpmnElement) {
		// TODO Auto-generated method stub
		return CommonNodeToSVG(bpmnShape, new SvgDataObjectTo());
	}

	private String groupToSVG(BPMNShape bpmnShape,BaseElement baseElement) {
		// TODO Auto-generated method stub
		
		return artifactNodeToSVG(bpmnShape,new SvgGroupTo(),baseElement);
	}

	private String callActivityToSVG(BPMNShape bpmnShape) {
		// TODO Auto-generated method stub
		SvgCallActivityTo svgCallActivityTo=new SvgCallActivityTo();
		
		float height = bpmnShape.getBounds().getHeight();
		float width = bpmnShape.getBounds().getWidth();
		float x = bpmnShape.getBounds().getX();
		float y = bpmnShape.getBounds().getY();
		String id = getBaseElement(bpmnShape.getBpmnElement()).getId();
		
	
		String label = "";
		
		if(getBaseElement(bpmnShape.getBpmnElement()) instanceof BoundaryEvent){
			
		}
		else{
			label= ((FlowElement)getBaseElement(bpmnShape.getBpmnElement())).getName();
		}
		if(label==null){
			label="";
		}
		
		svgCallActivityTo.setHeight(height);
		svgCallActivityTo.setWidth(width);
		svgCallActivityTo.setId(id);
		CallActivity callActivity=(CallActivity)getBaseElement(bpmnShape.getBpmnElement());
		if(callActivity.getLoopCharacteristics() instanceof MultiInstanceLoopCharacteristics ){
			svgCallActivityTo.setLoopType(LoopType.MultiInstanceLoopParallel);
		}
		
		svgCallActivityTo.setLabel(label);
		
		
		svgCallActivityTo.setX(x);
		svgCallActivityTo.setY(y);
		String child = FlowSvgUtil.getSvgComponent(svgCallActivityTo);
		return child;
		
		//return CommonNodeToSVG(bpmnShape, new SvgCallActivityTo());
	}
	
	
	
	

	private  BaseElement getBaseElement(BaseElement baseElement)
	{
		
		
		
		if(baseElement==null){
			return null;
		}
		
		if(baseElement.getId()==null){
			BasicEObjectImpl basicEObjectImpl=(BasicEObjectImpl)baseElement;
			if(basicEObjectImpl!=null&&basicEObjectImpl.eProxyURI()!=null){
				String elementId=basicEObjectImpl.eProxyURI().fragment();
				BaseElement bpmnElement=definitions.getElement(elementId);
				return bpmnElement;
			}
			else{
				return null;
			}
		}else{
			return baseElement;
		}
		
		
		
		
		
	}
	
	
	

	private String subProcessToSVG(BPMNShape bpmnShape) {
		return CommonNodeToSVG(bpmnShape, new SvgSubProcessTo ());
	}

	private String laneToSVG(BPMNShape bpmnShape) {
		return laneToSVG(bpmnShape, new SvgLaneTo());
	}
	private String participantToSVG(BPMNShape bpmnShape) {
		return participantToSVG(bpmnShape, new SvgLaneTo());
	}
	
	private String dataOutputToSVG(BPMNShape bpmnShape, SvgShapeBaseTo svgShapeBaseTo) {
		float height = bpmnShape.getBounds().getHeight();
		float width = bpmnShape.getBounds().getWidth();
		float x = bpmnShape.getBounds().getX();
		float y = bpmnShape.getBounds().getY();
		String id = getBaseElement(bpmnShape.getBpmnElement()).getId();
		
	
		String label = ((DataOutput)getBaseElement(bpmnShape.getBpmnElement())).getName();

		svgShapeBaseTo.setHeight(height);
		svgShapeBaseTo.setWidth(width);
		svgShapeBaseTo.setId(id);
		svgShapeBaseTo.setLabel(label);
		svgShapeBaseTo.setX(x);
		svgShapeBaseTo.setY(y);
		String child = FlowSvgUtil.getSvgComponent(svgShapeBaseTo);
		return child;
	}
	
	private String messageToSVG(BPMNShape bpmnShape, SvgShapeBaseTo svgShapeBaseTo) {
		float height = bpmnShape.getBounds().getHeight();
		float width = bpmnShape.getBounds().getWidth();
		float x = bpmnShape.getBounds().getX();
		float y = bpmnShape.getBounds().getY();
		String id = getBaseElement(bpmnShape.getBpmnElement()).getId();
		
	
		String label = ((org.eclipse.bpmn2.Message)getBaseElement(bpmnShape.getBpmnElement())).getName();

		svgShapeBaseTo.setHeight(height);
		svgShapeBaseTo.setWidth(width);
		svgShapeBaseTo.setId(id);
		svgShapeBaseTo.setLabel(label);
		svgShapeBaseTo.setX(x);
		svgShapeBaseTo.setY(y);
		String child = FlowSvgUtil.getSvgComponent(svgShapeBaseTo);
		return child;
	}
	
	private String dataInputToSVG(BPMNShape bpmnShape, SvgShapeBaseTo svgShapeBaseTo) {
		float height = bpmnShape.getBounds().getHeight();
		float width = bpmnShape.getBounds().getWidth();
		float x = bpmnShape.getBounds().getX();
		float y = bpmnShape.getBounds().getY();
		String id = getBaseElement(bpmnShape.getBpmnElement()).getId();
		
	
		String label = ((DataInput)getBaseElement(bpmnShape.getBpmnElement())).getName();

		svgShapeBaseTo.setHeight(height);
		svgShapeBaseTo.setWidth(width);
		svgShapeBaseTo.setId(id);
		svgShapeBaseTo.setLabel(label);
		svgShapeBaseTo.setX(x);
		svgShapeBaseTo.setY(y);
		String child = FlowSvgUtil.getSvgComponent(svgShapeBaseTo);
		return child;
	}
	
	private String artifactNodeToSVG(BPMNShape bpmnShape, SvgShapeBaseTo svgShapeBaseTo,BaseElement baseElement) {
		float height = bpmnShape.getBounds().getHeight();
		float width = bpmnShape.getBounds().getWidth();
		float x = bpmnShape.getBounds().getX();
		float y = bpmnShape.getBounds().getY();
		String id = baseElement.getId();
		

		svgShapeBaseTo.setHeight(height);
		svgShapeBaseTo.setWidth(width);
		svgShapeBaseTo.setId(id);
		svgShapeBaseTo.setX(x);
		svgShapeBaseTo.setY(y);
		String child = FlowSvgUtil.getSvgComponent(svgShapeBaseTo);
		return child;
	}

	private String CommonNodeToSVG(BPMNShape bpmnShape, SvgShapeBaseTo svgShapeBaseTo) {
		float height = bpmnShape.getBounds().getHeight();
		float width = bpmnShape.getBounds().getWidth();
		float x = bpmnShape.getBounds().getX();
		float y = bpmnShape.getBounds().getY();
		String id = getBaseElement(bpmnShape.getBpmnElement()).getId();
		
	
		String label = "";
		
		if(getBaseElement(bpmnShape.getBpmnElement()) instanceof BoundaryEvent){
			
		}
		else{
			label= ((FlowElement)getBaseElement(bpmnShape.getBpmnElement())).getName();
		}
		if(label==null){
			label="";
		}
		
		svgShapeBaseTo.setHeight(height);
		svgShapeBaseTo.setWidth(width);
		svgShapeBaseTo.setId(id);
		
		
		svgShapeBaseTo.setLabel(label);
		
		
		svgShapeBaseTo.setX(x);
		svgShapeBaseTo.setY(y);
		String child = FlowSvgUtil.getSvgComponent(svgShapeBaseTo);
		return child;
	}
	
	
	private String participantToSVG(BPMNShape bpmnShape, SvgShapeBaseTo svgShapeBaseTo) {
		float height = bpmnShape.getBounds().getHeight();
		float width = bpmnShape.getBounds().getWidth();
		float x = bpmnShape.getBounds().getX();
		float y = bpmnShape.getBounds().getY();
		String id = getBaseElement(bpmnShape.getBpmnElement()).getId();
		String label = ((Participant) getBaseElement(bpmnShape.getBpmnElement())).getName();

		svgShapeBaseTo.setHeight(height);
		svgShapeBaseTo.setWidth(width);
		svgShapeBaseTo.setId(id);
		svgShapeBaseTo.setLabel(label);
		svgShapeBaseTo.setX(x);
		svgShapeBaseTo.setY(y);
		String child = FlowSvgUtil.getSvgComponent(svgShapeBaseTo);
		return child;
	}

	private String laneToSVG(BPMNShape bpmnShape, SvgLaneTo svgShapeBaseTo) {
		
		boolean isHorizontal =bpmnShape.isIsHorizontal();
		
		
		float height = bpmnShape.getBounds().getHeight();
		float width = bpmnShape.getBounds().getWidth();
		float x = bpmnShape.getBounds().getX();
		float y = bpmnShape.getBounds().getY();
		String id = getBaseElement(bpmnShape.getBpmnElement()).getId();
		String label = ((Lane) getBaseElement(bpmnShape.getBpmnElement())).getName();

		svgShapeBaseTo.setHeight(height);
		svgShapeBaseTo.setWidth(width);
		svgShapeBaseTo.setId(id);
		svgShapeBaseTo.setLabel(label);
		svgShapeBaseTo.setX(x);
		svgShapeBaseTo.setY(y);
		svgShapeBaseTo.setHorizontal(isHorizontal);
		String child = FlowSvgUtil.getSvgComponent(svgShapeBaseTo);
		return child;
	}
	
	private String startEventToSVG(BPMNShape bpmnShape) {
		
		
		
		if (getBaseElement(bpmnShape.getBpmnElement()) instanceof StartEvent) {
			StartEvent startEvent=(StartEvent)getBaseElement(bpmnShape.getBpmnElement());
			
			for (EventDefinition eventDefinition : startEvent.getEventDefinitions()) {
				if(eventDefinition instanceof TimerEventDefinition){
					return CommonNodeToSVG(bpmnShape, new SvgStartTimerEventTo());
				}
			}
			
			return CommonNodeToSVG(bpmnShape, new SvgStartTo());
			
		}
		
		return CommonNodeToSVG(bpmnShape, new SvgStartTo());
	}

	private String endEventToSVG(BPMNShape bpmnShape) {
		

		if (getBaseElement(bpmnShape.getBpmnElement()) instanceof EndEvent) {
			EndEvent endEvent=(EndEvent)getBaseElement(bpmnShape.getBpmnElement());
			
			for (EventDefinition eventDefinition : endEvent.getEventDefinitions()) {
				if(eventDefinition instanceof TerminateEventDefinition){
					return CommonNodeToSVG(bpmnShape, new SvgTerminateEndEventTo());
				}
				if(eventDefinition instanceof ErrorEventDefinition){
					return CommonNodeToSVG(bpmnShape, new SvgEndErrorEventTo());
				}
			}
			
			return CommonNodeToSVG(bpmnShape, new SvgEndTo());
			
		}
		
		return CommonNodeToSVG(bpmnShape, new SvgEndTo());
	}
	
	
	private String intermediateTimerEventToSVG(BPMNShape bpmnShape) {
		
//SvgIntermediateTimerEventTo
		if (getBaseElement(bpmnShape.getBpmnElement()) instanceof IntermediateCatchEvent) {
			IntermediateCatchEvent intermediateCatchEvent=(IntermediateCatchEvent)getBaseElement(bpmnShape.getBpmnElement());
			
			for (EventDefinition eventDefinition : intermediateCatchEvent.getEventDefinitions()) {
				if(eventDefinition instanceof TimerEventDefinition){
					return CommonNodeToSVG(bpmnShape, new SvgIntermediateTimerEventTo());
				}
				if(eventDefinition instanceof ErrorEventDefinition){
					return CommonNodeToSVG(bpmnShape, new SvgIntermediateErrorEventTo());
				}
			}
			
			return CommonNodeToSVG(bpmnShape, new SvgIntermediateEventTo());
			
		}
		
		return CommonNodeToSVG(bpmnShape, new SvgIntermediateEventTo());
	}

	private String gatewayToSVG(BPMNShape bpmnShape) {

		if (getBaseElement(bpmnShape.getBpmnElement()) instanceof ParallelGateway) {
			return CommonNodeToSVG(bpmnShape, new SvgParallelGatewayTo());
		}
		if (getBaseElement(bpmnShape.getBpmnElement()) instanceof ComplexGateway) {
			return CommonNodeToSVG(bpmnShape, new SvgComplexGatewayTo());
		}
		if (getBaseElement(bpmnShape.getBpmnElement()) instanceof ExclusiveGateway) {
			return CommonNodeToSVG(bpmnShape, new SvgExclusiveGatewayTo());
		}
		if (getBaseElement(bpmnShape.getBpmnElement()) instanceof InclusiveGateway) {
			return CommonNodeToSVG(bpmnShape, new SvgInclusiveGatewayTo());
		}

		return "";
	}
	
	
	private String associationToSVG(BPMNEdge bpmnEdge) {

		List<Point> pointList = bpmnEdge.getWaypoint();

		String id = getBaseElement(bpmnEdge.getBpmnElement()).getId();
		
		SvgAssocationTo svgAssocationTo=new SvgAssocationTo();
		


		svgAssocationTo.setId(id);
	
		List<SvgPoint> svgPointList = new ArrayList<SvgPoint>();
		for (Point point : pointList) {
			SvgPoint svgPoint = svgAssocationTo.new SvgPoint();
			svgPoint.setX(point.getX());
			svgPoint.setY(point.getY());
			svgPointList.add(svgPoint);
		}
		svgAssocationTo.setSvgPointList(svgPointList);

		String lines = FlowSvgUtil.getSvgComponent(svgAssocationTo);

		return lines;
	}

	private String sequenceFlowToSVG(BPMNEdge bpmnEdge) {

		List<Point> pointList = bpmnEdge.getWaypoint();

		String id = getBaseElement(bpmnEdge.getBpmnElement()).getId();
		String label = ((FlowElement) getBaseElement(bpmnEdge.getBpmnElement())).getName();
		// line.setLineType(LineType.ConditionalFlow);

		SvgLineTo line = new SvgLineTo();

		SequenceFlow sequenceFlow = (SequenceFlow) getBaseElement(bpmnEdge.getBpmnElement());

		line.setLineType(LineType.SequenceFlow);

		if (sequenceFlow.getConditionExpression() != null) {
			line.setLineType(LineType.ConditionalFlow);
		}

		if (sequenceFlow.getTargetRef() instanceof Activity) {
			Activity activity = (Activity) sequenceFlow.getTargetRef();
			if (activity.getDefault() != null && activity.getDefault().equals(sequenceFlow)) {
				line.setLineType(LineType.DefaultFlow);
			}
		}

		if (sequenceFlow.getTargetRef() instanceof Gateway) {
			if (sequenceFlow.getTargetRef() instanceof ComplexGateway) {
				ComplexGateway complexGateway = (ComplexGateway) sequenceFlow.getTargetRef();
				if (complexGateway.getDefault() != null && complexGateway.getDefault().equals(sequenceFlow)) {
					line.setLineType(LineType.DefaultFlow);
				}
				// ExclusiveGateway //InclusiveGateway
			}
			if (sequenceFlow.getTargetRef() instanceof ExclusiveGateway) {
				ExclusiveGateway exclusiveGateway = (ExclusiveGateway) sequenceFlow.getTargetRef();
				if (exclusiveGateway.getDefault() != null && exclusiveGateway.getDefault().equals(sequenceFlow)) {
					line.setLineType(LineType.DefaultFlow);
				}
				// ExclusiveGateway //InclusiveGateway
			}
			if (sequenceFlow.getTargetRef() instanceof InclusiveGateway) {
				InclusiveGateway inclusiveGateway = (InclusiveGateway) sequenceFlow.getTargetRef();
				if (inclusiveGateway.getDefault() != null && inclusiveGateway.getDefault().equals(sequenceFlow)) {
					line.setLineType(LineType.DefaultFlow);
				}
				// ExclusiveGateway //InclusiveGateway
			}
		}

		line.setId(id);
		line.setLabel(label);
		List<SvgPoint> svgPointList = new ArrayList<SvgPoint>();
		for (Point point : pointList) {
			SvgPoint svgPoint = line.new SvgPoint();
			svgPoint.setX(point.getX());
			svgPoint.setY(point.getY());
			svgPointList.add(svgPoint);
		}
		line.setSvgPointList(svgPointList);

		String lines = FlowSvgUtil.getSvgComponent(line);

		return lines;
	}

	private String taskToSVG(BPMNShape bpmnShape) {
		float height = bpmnShape.getBounds().getHeight();
		float width = bpmnShape.getBounds().getWidth();
		float x = bpmnShape.getBounds().getX();
		float y = bpmnShape.getBounds().getY();
		String id = getBaseElement(bpmnShape.getBpmnElement()).getId();
		String label = ((FlowElement) getBaseElement(bpmnShape.getBpmnElement())).getName();

		SvgTaskTo task = new SvgTaskTo();

		task.setTaskType(TaskType.Task);

		if (getBaseElement(bpmnShape.getBpmnElement()) instanceof UserTask) {

			task.setTaskType(TaskType.UserTask);

		}

		if (getBaseElement(bpmnShape.getBpmnElement()) instanceof ServiceTask) {

			task.setTaskType(TaskType.ServiceTask);

		}
		
		if (getBaseElement(bpmnShape.getBpmnElement()) instanceof ReceiveTask) {

			task.setTaskType(TaskType.ReceiveTask);

		}

		if (getBaseElement(bpmnShape.getBpmnElement()) instanceof ScriptTask) {

			task.setTaskType(TaskType.ScriptTask);

		}

		if (getBaseElement(bpmnShape.getBpmnElement()) instanceof BusinessRuleTask) {

			task.setTaskType(TaskType.BusinessRuleTask);

		}
		
		if (getBaseElement(bpmnShape.getBpmnElement()) instanceof ManualTask) {

			task.setTaskType(TaskType.ManualTask);

		}
		
		if (getBaseElement(bpmnShape.getBpmnElement()) instanceof SendTask) {

			task.setTaskType(TaskType.SendTask);

		}
		
		Task taskBpmn=(Task)getBaseElement(bpmnShape.getBpmnElement());
		if(taskBpmn.getLoopCharacteristics() instanceof MultiInstanceLoopCharacteristics ){
			task.setLoopType(LoopType.MultiInstanceLoopParallel);
		}

		task.setHeight(height);
		task.setWidth(width);
		task.setId(id);
		task.setLabel(label);
		task.setX(x);
		task.setY(y);
		// task.setTaskType(TaskType.ScriptTask);
		// task.setLoopType(LoopType.MultiInstanceLoopParallel);
		String child = FlowSvgUtil.getSvgComponent(task);
		return child;
	}

}
