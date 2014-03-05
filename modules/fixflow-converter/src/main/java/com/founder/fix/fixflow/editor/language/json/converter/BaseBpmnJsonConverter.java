/**
 *  Copyright 1996-2013 Founder International Co.,Ltd.
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
 * @author ych
 * @author kenshin
 */
package com.founder.fix.fixflow.editor.language.json.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.editor.language.json.converter.ActivityProcessor;
import com.founder.fix.fixflow.editor.language.json.converter.BaseBpmnJsonConverter;
import com.founder.fix.fixflow.editor.language.json.converter.BpmnJsonConverterUtil;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.CatchEvent;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.ErrorEventDefinition;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.MessageEventDefinition;
import org.eclipse.bpmn2.MultiInstanceLoopCharacteristics;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.SignalEventDefinition;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.ThrowEvent;
import org.eclipse.bpmn2.TimerEventDefinition;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.impl.ActivityImpl;
import org.eclipse.dd.dc.Bounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.founder.fix.bpmn2extensions.fixflow.Expression;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowFactory;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.fixflow.LoopDataInputCollection;
import com.founder.fix.bpmn2extensions.fixflow.LoopDataOutputCollection;
import com.founder.fix.bpmn2extensions.fixflow.SkipAssignee;
import com.founder.fix.bpmn2extensions.fixflow.SkipComment;
import com.founder.fix.bpmn2extensions.fixflow.SkipStrategy;
import com.founder.fix.fixflow.core.impl.util.BpmnModelUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.editor.constants.EditorJsonConstants;
import com.founder.fix.fixflow.editor.constants.StencilConstants;
import com.founder.fix.fixflow.editor.language.json.converter.util.JsonConverterUtil;


public abstract class BaseBpmnJsonConverter implements EditorJsonConstants, StencilConstants {
  
  protected static final Logger LOGGER = LoggerFactory.getLogger(BaseBpmnJsonConverter.class);
  
  protected ObjectMapper objectMapper = new ObjectMapper();
  protected ActivityProcessor processor;
  protected Definitions model;
  protected ObjectNode flowElementNode;
  protected double subProcessX;
  protected double subProcessY;
  protected ArrayNode shapesArrayNode;
  protected Map<String, List<JsonNode>> sourceAndTargetMap;
  private static final List<String> DI_CIRCLES = new ArrayList<String>();
  private static final List<String> DI_RECTANGLES = new ArrayList<String>();
  private static final List<String> DI_GATEWAY = new ArrayList<String>();
  
  static {
    DI_CIRCLES.add(STENCIL_EVENT_START_ERROR);
    DI_CIRCLES.add(STENCIL_EVENT_START_MESSAGE);
    DI_CIRCLES.add(STENCIL_EVENT_START_NONE);
    DI_CIRCLES.add(STENCIL_EVENT_START_TIMER);
    
    DI_CIRCLES.add(STENCIL_EVENT_BOUNDARY_ERROR);
    DI_CIRCLES.add(STENCIL_EVENT_BOUNDARY_SIGNAL);
    DI_CIRCLES.add(STENCIL_EVENT_BOUNDARY_TIMER);
    
    DI_CIRCLES.add(STENCIL_EVENT_CATCH_MESSAGE);
    DI_CIRCLES.add(STENCIL_EVENT_CATCH_SIGNAL);
    DI_CIRCLES.add(STENCIL_EVENT_CATCH_TIMER);
    
    DI_CIRCLES.add(STENCIL_EVENT_THROW_NONE);
    DI_CIRCLES.add(STENCIL_EVENT_THROW_SIGNAL);
    
    DI_CIRCLES.add(STENCIL_EVENT_END_NONE);
    DI_CIRCLES.add(STENCIL_EVENT_END_ERROR);
    
    DI_RECTANGLES.add(STENCIL_CALL_ACTIVITY);
    DI_RECTANGLES.add(STENCIL_SUB_PROCESS);
    DI_RECTANGLES.add(STENCIL_EVENT_SUB_PROCESS);
    DI_RECTANGLES.add(STENCIL_TASK_BUSINESS_RULE);
    DI_RECTANGLES.add(STENCIL_TASK_MAIL);
    DI_RECTANGLES.add(STENCIL_TASK_MANUAL);
    DI_RECTANGLES.add(STENCIL_TASK_RECEIVE);
    DI_RECTANGLES.add(STENCIL_TASK_SCRIPT);
    DI_RECTANGLES.add(STENCIL_TASK_SERVICE);
    DI_RECTANGLES.add(STENCIL_TASK_USER);
    
    DI_GATEWAY.add(STENCIL_GATEWAY_EVENT);
    DI_GATEWAY.add(STENCIL_GATEWAY_EXCLUSIVE);
    DI_GATEWAY.add(STENCIL_GATEWAY_INCLUSIVE);
    DI_GATEWAY.add(STENCIL_GATEWAY_PARALLEL);
  }

  public void convertToJson(FlowElement flowElement, ActivityProcessor processor, Definitions model,
      ArrayNode shapesArrayNode, double subProcessX, double subProcessY) {
    this.model = model;
    this.processor = processor;
    this.subProcessX = subProcessX;
    this.subProcessY = subProcessY;
    this.shapesArrayNode = shapesArrayNode;
    BPMNShape bpmnShape=BpmnModelUtil.getBpmnShape(model, flowElement.getId());
    Bounds bounds=bpmnShape.getBounds();
    String stencilId = null;
    stencilId = getStencilId(flowElement);
    double upleftX = bounds.getX() - subProcessX;
    double upleftY = bounds.getY() - subProcessY;
    //坐标修正
    if(DI_CIRCLES.contains(stencilId) || DI_GATEWAY.contains(stencilId)){
    	upleftX += REVERSION_X;
    	upleftY += REVERSION_Y;
    }
    flowElementNode = BpmnJsonConverterUtil.createChildShape(flowElement.getId(), stencilId, 
    		bounds.getX() - subProcessX + bounds.getWidth(), 
    		bounds.getY() - subProcessY + bounds.getHeight(), 
    		upleftX, upleftY);
    shapesArrayNode.add(flowElementNode);
    ObjectNode propertiesNode = objectMapper.createObjectNode();
    propertiesNode.put(PROPERTY_OVERRIDE_ID, flowElement.getId());
    if (StringUtils.isNotEmpty(flowElement.getName())) {
      propertiesNode.put(PROPERTY_NAME, flowElement.getName());
    }
    
    if (StringUtils.isNotEmpty(BpmnModelUtil.getDocumentation(flowElement))) {
      propertiesNode.put(PROPERTY_DOCUMENTATION, BpmnModelUtil.getDocumentation(flowElement));
    }
    
    convertElementToJson(propertiesNode, flowElement);
    
    flowElementNode.put(EDITOR_SHAPE_PROPERTIES, propertiesNode);
    ArrayNode outgoingArrayNode = objectMapper.createArrayNode();
    
    if (flowElement instanceof FlowNode) {
      FlowNode flowNode = (FlowNode) flowElement;
      for (SequenceFlow sequenceFlow : flowNode.getOutgoing()) {
        outgoingArrayNode.add(BpmnJsonConverterUtil.createResourceNode(sequenceFlow.getId()));
      }
    }
    
    if (flowElement instanceof Activity) {
      
    	//为什么要改成impl???很多方法都封装在impl里？
    	
      ActivityImpl activity = (ActivityImpl) flowElement;
      for (BoundaryEvent boundaryEvent : activity.getBoundaryEventRefs()) {
        outgoingArrayNode.add(BpmnJsonConverterUtil.createResourceNode(boundaryEvent.getId()));
      }
  
      if (activity.getLoopCharacteristics() != null) {
        MultiInstanceLoopCharacteristics loopDef = (MultiInstanceLoopCharacteristics)activity.getLoopCharacteristics();//.getLoopCharacteristics();
        propertiesNode.put(PROPERTY_MULTIINSTANCE, StringUtil.getString(true));
        	propertiesNode.put(PROPERTY_MULTIINSTANCE_SEQUENTIAL, StringUtil.getString(loopDef.isIsSequential()));
        	if(activity.getLoopDataInputCollectionExpression() != null){
        		propertiesNode.put(PROPERTY_MULTIINSTANCE_INPUT_COLLECTION, activity.getLoopDataInputCollectionExpression());
        	}
        	if(activity.getLoopDataOutputCollectionExpression() !=null){
        		propertiesNode.put(PROPERTY_MULTIINSTANCE_OUTPUT_COLLECTION, activity.getLoopDataOutputCollectionExpression());
        	}
        	
        	if(loopDef.getInputDataItem()!=null){
        		propertiesNode.put(PROPERTY_MULTIINSTANCE_INPUT_ITEM, activity.getInputDataItemExpression());
        	}
        	if(loopDef.getOutputDataItem() !=null){
        		propertiesNode.put(PROPERTY_MULTIINSTANCE_OUTPUT_ITEM, activity.getOutputDataItemExpression());
        	}
        	if(BpmnModelUtil.getExpression(loopDef.getCompletionCondition()) !=null){
        		propertiesNode.put(PROPERTY_MULTIINSTANCE_CONDITION, BpmnModelUtil.getExpression(loopDef.getCompletionCondition()));
        	}
      }
      
    //跳过策略
      SkipStrategy skipStrategy = activity.getSkipStrategy();
      if(skipStrategy !=null){
      	setPropertyValue(PROPERTY_ACTIVITY_SKIPSTRATEGY, StringUtil.getString(skipStrategy.isIsEnable()), propertiesNode);
      	setPropertyValue(PROPERTY_ACTIVITY_IS_CREATE_SKIP_PROCESS, StringUtil.getString(skipStrategy.isIsCreateSkipProcess()), propertiesNode);
      	SkipAssignee skipAssignee = skipStrategy.getSkipAssignee();
      	if(skipAssignee != null){
      		setPropertyValue(PROPERTY_ACTIVITY_SKIPASSIGNEE, skipAssignee.getExpression().getValue(), propertiesNode);
      	}
      	SkipComment skipComment = skipStrategy.getSkipComment();
      	if(skipComment !=null){
      		setPropertyValue(PROPERTY_ACTIVITY_SKIPCOMMENT, skipComment.getExpression().getValue(), propertiesNode);
      	}
      	Expression skipExpression = skipStrategy.getExpression();
      	if(skipExpression !=null){
      		setPropertyValue(PROPERTY_ACTIVITY_SKIPEXPRESSION, skipExpression.getValue(), propertiesNode);
      	}
      } 
    }
    
    flowElementNode.put("outgoing", outgoingArrayNode);
  }
  
  public void convertToBpmnModel(JsonNode elementNode, JsonNode modelNode, 
      ActivityProcessor processor, BaseElement parentElement, Map<String, JsonNode> shapeMap, Map<String, List<JsonNode>> sourceAndTargetMap, Definitions model2) {
    //subProcess使用
	  this.processor = processor;
	  this.model = model2;
	  this.sourceAndTargetMap = sourceAndTargetMap;
    FlowElement flowElement = convertJsonToElement(elementNode, modelNode, shapeMap);
    flowElement.setId(BpmnJsonConverterUtil.getElementId(elementNode));
    flowElement.setName(getPropertyValueAsString(PROPERTY_NAME, elementNode));
    String documentation = getPropertyValueAsString(PROPERTY_DOCUMENTATION, elementNode);
    if(StringUtil.isNotEmpty(documentation)){
    	BpmnModelUtil.setDocumentation(flowElement, documentation);
    }
    // 这里注释掉内容以后需要恢复过来
    if (flowElement instanceof Activity) {
      ActivityImpl activity = (ActivityImpl) flowElement;
      
      boolean isMulti = getPropertyValueAsBoolean(PROPERTY_MULTIINSTANCE, elementNode);
      if(isMulti){
    	  MultiInstanceLoopCharacteristics newLoopCharacteristics = Bpmn2Factory.eINSTANCE.createMultiInstanceLoopCharacteristics();
    	  String inputDataCollection = getPropertyValueAsString(PROPERTY_MULTIINSTANCE_INPUT_COLLECTION, elementNode);
    	  String inputDataItem = getPropertyValueAsString(PROPERTY_MULTIINSTANCE_INPUT_ITEM, elementNode);
    	  String outputDataCollection = getPropertyValueAsString(PROPERTY_MULTIINSTANCE_OUTPUT_COLLECTION, elementNode);
    	  String outputDataItem = getPropertyValueAsString(PROPERTY_MULTIINSTANCE_OUTPUT_ITEM, elementNode);
    	  String completeExpression = getPropertyValueAsString(PROPERTY_MULTIINSTANCE_CONDITION, elementNode);
    	  
    	  //输出变量
    	  DataOutput dataOutput = Bpmn2Factory.eINSTANCE.createDataOutput();
    	  Expression dataOutputExpression = FixFlowFactory.eINSTANCE.createExpression();
    	  dataOutputExpression.setName(outputDataItem);
    	  dataOutputExpression.setValue(outputDataItem);
    	  BpmnModelUtil.addExtensionElement(dataOutput, FixFlowPackage.Literals.RESOURCE_FILTER__EXPRESSION, dataOutputExpression);
    	  newLoopCharacteristics.setOutputDataItem(dataOutput);
    	  
    	  //输入变量
    	  DataInput dataInput = Bpmn2Factory.eINSTANCE.createDataInput();
    	  Expression dataInputExpression = FixFlowFactory.eINSTANCE.createExpression();
    	  dataInputExpression.setName(inputDataItem);
    	  dataInputExpression.setValue(inputDataItem);
    	  BpmnModelUtil.addExtensionElement(dataInput, FixFlowPackage.Literals.RESOURCE_FILTER__EXPRESSION, dataInputExpression);
    	  newLoopCharacteristics.setInputDataItem(dataInput);
    	  
    	  //输入数据集
    	  LoopDataInputCollection loopDataInputCollection = FixFlowFactory.eINSTANCE.createLoopDataInputCollection();
    	  Expression inputCollectionExpression = FixFlowFactory.eINSTANCE.createExpression();
    	  inputCollectionExpression.setName(inputDataCollection);
    	  inputCollectionExpression.setValue(inputDataCollection);
    	  loopDataInputCollection.setExpression(inputCollectionExpression);
    	  BpmnModelUtil.addExtensionElement(newLoopCharacteristics, FixFlowPackage.Literals.DOCUMENT_ROOT__LOOP_DATA_INPUT_COLLECTION, loopDataInputCollection);

    	  //输出数据集
    	  LoopDataOutputCollection loopDataOutputCollection = FixFlowFactory.eINSTANCE.createLoopDataOutputCollection();
    	  Expression outputCollectionExpression = FixFlowFactory.eINSTANCE.createExpression();
    	  outputCollectionExpression.setName(outputDataCollection);
    	  outputCollectionExpression.setValue(outputDataCollection);
    	  loopDataOutputCollection.setExpression(outputCollectionExpression);
    	  BpmnModelUtil.addExtensionElement(newLoopCharacteristics, FixFlowPackage.Literals.DOCUMENT_ROOT__LOOP_DATA_OUTPUT_COLLECTION, loopDataOutputCollection);

    	  //完成表达式
    	  FormalExpression formalExpression= Bpmn2Factory.eINSTANCE.createFormalExpression();
    	  formalExpression.setId(completeExpression);
    	  formalExpression.setBody(completeExpression);
    	  newLoopCharacteristics.setCompletionCondition(formalExpression);
    	  
    	  activity.setLoopCharacteristics(newLoopCharacteristics);
    	  
      }
      
      //跳过策略
      JsonNode skipEnabled = getProperty(PROPERTY_ACTIVITY_SKIPSTRATEGY,elementNode);
      boolean isSkipEnabled = false;
      if(skipEnabled !=null){
    	  isSkipEnabled = getProperty(PROPERTY_ACTIVITY_SKIPSTRATEGY,elementNode).asBoolean();
      }
      if(isSkipEnabled){
    	  SkipStrategy skipStrategy = FixFlowFactory.eINSTANCE.createSkipStrategy();
    	  skipStrategy.setIsEnable(isSkipEnabled);
    	  String skipExpressionValue = getPropertyValueAsString(PROPERTY_ACTIVITY_SKIPEXPRESSION, elementNode);
    	  String skipAssigneeValue = getPropertyValueAsString(PROPERTY_ACTIVITY_SKIPASSIGNEE, elementNode);
    	  String skipCommentValue = getPropertyValueAsString(PROPERTY_ACTIVITY_SKIPCOMMENT, elementNode);
    	  Expression skipExpression = FixFlowFactory.eINSTANCE.createExpression();
    	  skipExpression.setName(skipExpressionValue);
    	  skipExpression.setValue(skipExpressionValue);
    	  skipStrategy.setExpression(skipExpression);
    	  
    	  boolean isCreateSkipProcess = getProperty(PROPERTY_ACTIVITY_IS_CREATE_SKIP_PROCESS, elementNode).asBoolean();
    	  if(isCreateSkipProcess){
    		  skipStrategy.setIsCreateSkipProcess(isCreateSkipProcess);
    	  }
    	  
    	  Expression skipAssigneeExpression = FixFlowFactory.eINSTANCE.createExpression();
    	  skipAssigneeExpression.setName(skipAssigneeValue);
    	  skipAssigneeExpression.setValue(skipAssigneeValue);
    	  SkipAssignee skipAssignee = FixFlowFactory.eINSTANCE.createSkipAssignee();
    	  skipAssignee.setExpression(skipAssigneeExpression);
    	  skipStrategy.setSkipAssignee(skipAssignee);
    	  
    	  Expression skipCommentExpression = FixFlowFactory.eINSTANCE.createExpression();
    	  skipCommentExpression.setName(skipCommentValue);
    	  skipCommentExpression.setValue(skipCommentValue);
    	  SkipComment skipComment = FixFlowFactory.eINSTANCE.createSkipComment();
    	  skipComment.setExpression(skipCommentExpression);
    	  skipStrategy.setSkipComment(skipComment);
    	  
    	  BpmnModelUtil.addExtensionElement(activity, FixFlowPackage.Literals.DOCUMENT_ROOT__SKIP_STRATEGY, skipStrategy);
      }
    }
    if (parentElement instanceof Process) {
      ((Process) parentElement).getFlowElements().add(flowElement);
    } else if (parentElement instanceof SubProcess) {
      ((SubProcess) parentElement).getFlowElements().add(flowElement);
    } else if (parentElement instanceof Lane) {
    	Process process = (Process)this.model.getRootElements().get(0);
    	Lane lane = (Lane) parentElement;
    	process.getFlowElements().add(flowElement);
    	lane.getFlowNodeRefs().add((FlowNode)flowElement);
    }
  }
  
  protected abstract void convertElementToJson(ObjectNode propertiesNode, FlowElement flowElement);
  
  protected abstract FlowElement convertJsonToElement(JsonNode elementNode, JsonNode modelNode, Map<String, JsonNode> shapeMap);
  
  protected abstract String getStencilId(FlowElement flowElement);
  
  protected void setPropertyValue(String name, String value, ObjectNode propertiesNode) {
    if (StringUtils.isNotEmpty(value)) {
    	propertiesNode.put(name, value);
    }
  }
  
  protected void addEventProperties(Event event, ObjectNode propertiesNode) {
	  List<EventDefinition> eventDefinitions=null;
	  if(event instanceof CatchEvent){
		  eventDefinitions=((CatchEvent)event).getEventDefinitions();
	  }
	  
	  if(event instanceof ThrowEvent){
		  eventDefinitions=((ThrowEvent)event).getEventDefinitions();
	  }

    if (eventDefinitions.size() == 1) {
    
      EventDefinition eventDefinition = eventDefinitions.get(0);
      if (eventDefinition instanceof ErrorEventDefinition) {
        ErrorEventDefinition errorDefinition = (ErrorEventDefinition) eventDefinition;
        if (StringUtils.isNotEmpty(errorDefinition.getErrorRef().getErrorCode())) {
          propertiesNode.put(PROPERTY_ERRORREF, errorDefinition.getErrorRef().getErrorCode());
        }
        
      } else if (eventDefinition instanceof SignalEventDefinition) {
        SignalEventDefinition signalDefinition = (SignalEventDefinition) eventDefinition;
        if (StringUtils.isNotEmpty(signalDefinition.getSignalRef().getId())) {
          propertiesNode.put(PROPERTY_SIGNALREF, signalDefinition.getSignalRef().getId());
        }
        
      } else if (eventDefinition instanceof TimerEventDefinition) {
        TimerEventDefinition timerDefinition = (TimerEventDefinition) eventDefinition;
        if (StringUtils.isNotEmpty(BpmnModelUtil.getExpression(timerDefinition.getTimeDuration()))) {
          propertiesNode.put(PROPERTY_TIMER_DURATON, BpmnModelUtil.getExpression(timerDefinition.getTimeDuration()));
        }
        if (StringUtils.isNotEmpty(BpmnModelUtil.getExpression(timerDefinition.getTimeCycle()))) {
          propertiesNode.put(PROPERTY_TIMER_CYCLE, BpmnModelUtil.getExpression(timerDefinition.getTimeCycle()));
        }
        if (StringUtils.isNotEmpty(BpmnModelUtil.getExpression(timerDefinition.getTimeDate()))) {
          propertiesNode.put(PROPERTY_TIMER_DATE,BpmnModelUtil.getExpression( timerDefinition.getTimeDate()));
        }
      }
    }
  }
  
  protected void convertJsonToTimerDefinition(JsonNode objectNode, Event event) {
    
    String timeDate = getPropertyValueAsString(PROPERTY_TIMER_DATE, objectNode);
    String timeCycle = getPropertyValueAsString(PROPERTY_TIMER_CYCLE, objectNode);
    String timeDuration = getPropertyValueAsString(PROPERTY_TIMER_DURATON, objectNode);
    
    if (StringUtils.isNotEmpty(timeDate) || StringUtils.isNotEmpty(timeCycle) || StringUtils.isNotEmpty(timeDuration)) {
    
      TimerEventDefinition eventDefinition = Bpmn2Factory.eINSTANCE.createTimerEventDefinition();// TimerEventDefinition();
      if (StringUtils.isNotEmpty(timeDate)) {
        eventDefinition.setTimeDate(BpmnModelUtil.getExpressionByString(timeDate));
        
      } else if (StringUtils.isNotEmpty(timeCycle)) {
        eventDefinition.setTimeCycle(BpmnModelUtil.getExpressionByString(timeCycle));
        
      } else if (StringUtils.isNotEmpty(timeDuration)) {
        eventDefinition.setTimeDuration(BpmnModelUtil.getExpressionByString(timeDuration));
      }
      
      if(event instanceof CatchEvent){
		  ((CatchEvent)event).getEventDefinitions().add(eventDefinition);
	  }
	  
	  if(event instanceof ThrowEvent){
		  ((ThrowEvent)event).getEventDefinitions().add(eventDefinition);
	  }

      
      //event.getEventDefinitions().add(eventDefinition);
    }
  }
  
  protected void convertJsonToSignalDefinition(JsonNode objectNode, Event event) {
    String signalRef = getPropertyValueAsString(PROPERTY_SIGNALREF, objectNode);
    
    if (StringUtils.isNotEmpty(signalRef)) {
      SignalEventDefinition eventDefinition = Bpmn2Factory.eINSTANCE.createSignalEventDefinition();// SignalEventDefinition();
      //eventDefinition.setSignalRef(signalRef);
      //event.getEventDefinitions().add(eventDefinition);
    }
  }
  
  protected void convertJsonToMessageDefinition(JsonNode objectNode, Event event) {
    String messageRef = getPropertyValueAsString(PROPERTY_MESSAGEREF, objectNode);
    
    if (StringUtils.isNotEmpty(messageRef)) {
      MessageEventDefinition eventDefinition = Bpmn2Factory.eINSTANCE.createMessageEventDefinition();// MessageEventDefinition();
      //这里注释掉内容以后需要恢复过来 eventDefinition.setMessageRef(messageRef);
      //这里注释掉内容以后需要恢复过来 event.getEventDefinitions().add(eventDefinition);
    }
  }
  
  
  protected void convertJsonToErrorDefinition(JsonNode objectNode, Event event) {
    String errorRef = getPropertyValueAsString(PROPERTY_ERRORREF, objectNode);
    
    if (StringUtils.isNotEmpty(errorRef)) {
      ErrorEventDefinition eventDefinition = Bpmn2Factory.eINSTANCE.createErrorEventDefinition();// ErrorEventDefinition();
      //这里注释掉内容以后需要恢复过来 eventDefinition.setErrorCode(errorRef);
      //这里注释掉内容以后需要恢复过来 event.getEventDefinitions().add(eventDefinition);
    }
  }
  
  protected String getValueAsString(String name, JsonNode objectNode) {
    String propertyValue = null;
    JsonNode propertyNode = objectNode.get(name);
    if (propertyNode != null && "null".equalsIgnoreCase(propertyNode.asText()) == false) {
      propertyValue = propertyNode.asText();
    }
    return propertyValue;
  }
  
  protected List<String> getValueAsList(String name, JsonNode objectNode) {
    List<String> resultList = new ArrayList<String>();
    String propertyValue = getValueAsString(name, objectNode);
    if (propertyValue != null) {
      String[] valueList = propertyValue.split(",");
      for (String value : valueList) {
        resultList.add(value.trim());
      }
    }
    return resultList;
  }
  
  protected String getPropertyValueAsString(String name, JsonNode objectNode) {
    return JsonConverterUtil.getPropertyValueAsString(name, objectNode);
  }
  
  protected boolean getPropertyValueAsBoolean(String name, JsonNode objectNode) {
    return JsonConverterUtil.getPropertyValueAsBoolean(name, objectNode);
  }
  
  protected List<String> getPropertyValueAsList(String name, JsonNode objectNode) {
    return JsonConverterUtil.getPropertyValueAsList(name, objectNode);
  }
  
  protected JsonNode getProperty(String name, JsonNode objectNode) {
    return JsonConverterUtil.getProperty(name, objectNode);
  }
  
  protected String convertListToCommaSeparatedString(List<String> stringList) {
    String resultString = null;
    if (stringList  != null && stringList.size() > 0) {
      StringBuilder expressionBuilder = new StringBuilder();
      for (String singleItem : stringList) {
        if (expressionBuilder.length() > 0) {
          expressionBuilder.append(",");
        } 
        expressionBuilder.append(singleItem);
      }
      resultString = expressionBuilder.toString();
    }
    return resultString;
  }
}
