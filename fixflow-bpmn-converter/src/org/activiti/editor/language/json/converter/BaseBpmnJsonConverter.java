/* Licensed under the Apache License, Version 2.0 (the "License");
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
 */
package org.activiti.editor.language.json.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.editor.constants.EditorJsonConstants;
import org.activiti.editor.constants.StencilConstants;
import org.activiti.editor.language.json.converter.util.JsonConverterUtil;
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

/**
 * @author Tijs Rademakers
 */
public abstract class BaseBpmnJsonConverter implements EditorJsonConstants, StencilConstants {
  
  protected static final Logger LOGGER = LoggerFactory.getLogger(BaseBpmnJsonConverter.class);
  
  protected ObjectMapper objectMapper = new ObjectMapper();
  protected ActivityProcessor processor;
  protected Definitions model;
  protected ObjectNode flowElementNode;
  protected double subProcessX;
  protected double subProcessY;
  protected ArrayNode shapesArrayNode;

  public void convertToJson(FlowElement flowElement, ActivityProcessor processor, Definitions model,
      ArrayNode shapesArrayNode, double subProcessX, double subProcessY) {
    
    this.model = model;
    this.processor = processor;
    this.subProcessX = subProcessX;
    this.subProcessY = subProcessY;
    this.shapesArrayNode = shapesArrayNode;
   // GraphicInfo graphicInfo = model.getGraphicInfo(flowElement.getId());
    
    BPMNShape bpmnShape=BpmnModelUtil.getBpmnShape(model, flowElement.getId());
    Bounds bounds=bpmnShape.getBounds();
    String stencilId = null;
    /*
    
    if (flowElement instanceof ServiceTask) {
      ServiceTask serviceTask = (ServiceTask) flowElement;
      if ("mail".equalsIgnoreCase(serviceTask.getType())) {
        stencilId = STENCIL_TASK_MAIL;
      } else {
        stencilId = getStencilId(flowElement);
      }
    } else {
      stencilId = getStencilId(flowElement);
    }*/
    
    stencilId = getStencilId(flowElement);
    
    flowElementNode = BpmnJsonConverterUtil.createChildShape(flowElement.getId(), stencilId, 
    		bounds.getX() - subProcessX + bounds.getWidth(), 
    		bounds.getY() - subProcessY + bounds.getHeight(), 
    		bounds.getX() - subProcessX, bounds.getY() - subProcessY);
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
      /*
      if (activity.isAsynchronous()) {
        propertiesNode.put(PROPERTY_ASYNCHRONOUS, PROPERTY_VALUE_YES);
      }
      
      if (activity.isNotExclusive()) {
        propertiesNode.put(PROPERTY_EXCLUSIVE, PROPERTY_VALUE_NO);
      }*/
      
      
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
      
        
//        if (StringUtils.isNotEmpty(loopDef.getLoopCardinality()) || StringUtils.isNotEmpty(loopDef.getInputDataItem()) ||
//            StringUtils.isNotEmpty(loopDef.getCompletionCondition())) {
//          
//          if (loopDef.isSequential() == false) {
//            propertiesNode.put(PROPERTY_MULTIINSTANCE_SEQUENTIAL, PROPERTY_VALUE_NO);
//          }
//          if (StringUtils.isNotEmpty(loopDef.getLoopCardinality())) {
//            propertiesNode.put(PROPERTY_MULTIINSTANCE_CARDINALITY, loopDef.getLoopCardinality());
//          }
//          if (StringUtils.isNotEmpty(loopDef.getInputDataItem())) {
//            propertiesNode.put(PROPERTY_MULTIINSTANCE_COLLECTION, loopDef.getInputDataItem());
//          }
//          if (StringUtils.isNotEmpty(loopDef.getElementVariable())) {
//            propertiesNode.put(PROPERTY_MULTIINSTANCE_VARIABLE, loopDef.getElementVariable());
//          }
//          if (StringUtils.isNotEmpty(loopDef.getCompletionCondition())) {
//            propertiesNode.put(PROPERTY_MULTIINSTANCE_CONDITION, loopDef.getCompletionCondition());
//          }
//        }
      
      /* 
      if (activity instanceof UserTask) {
        addListeners(((UserTask) activity).getTaskListeners(), false,  propertiesNode);
      } else {
        addListeners(activity.getExecutionListeners(), true,  propertiesNode);
      }*/
    }
    
    flowElementNode.put("outgoing", outgoingArrayNode);
  }
  
  public void convertToBpmnModel(JsonNode elementNode, JsonNode modelNode, 
      ActivityProcessor processor, BaseElement parentElement, Map<String, JsonNode> shapeMap) {
    this.processor = processor;
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
    	
    	 /* 这里注释掉内容以后需要恢复过来
      Lane lane = (Lane) parentElement;
      lane.getFlowReferences().add(flowElement.getId());
      lane.getParentProcess().addFlowElement(flowElement);
      */
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
  
  /*
  protected void addFormProperties(List<FormProperty> formProperties, ObjectNode propertiesNode) {
    ObjectNode formPropertiesNode = objectMapper.createObjectNode();
    ArrayNode itemsNode = objectMapper.createArrayNode();
    for (FormProperty property : formProperties) {
      ObjectNode propertyItemNode = objectMapper.createObjectNode();
      propertyItemNode.put(PROPERTY_FORM_ID, property.getId());
      propertyItemNode.put(PROPERTY_FORM_NAME, property.getName());
      propertyItemNode.put(PROPERTY_FORM_TYPE, property.getType());
      if (StringUtils.isNotEmpty(property.getExpression())) {
        propertyItemNode.put(PROPERTY_FORM_EXPRESSION, property.getExpression());
      } else {
        propertyItemNode.putNull(PROPERTY_FORM_EXPRESSION);
      }
      if (StringUtils.isNotEmpty(property.getVariable())) {
        propertyItemNode.put(PROPERTY_FORM_VARIABLE, property.getVariable());
      } else {
        propertyItemNode.putNull(PROPERTY_FORM_VARIABLE);
      }
      
      propertyItemNode.put(PROPERTY_FORM_REQUIRED, property.isRequired() ? PROPERTY_VALUE_YES : PROPERTY_VALUE_NO);
      propertyItemNode.put(PROPERTY_FORM_READABLE, property.isReadable() ? PROPERTY_VALUE_YES : PROPERTY_VALUE_NO);
      propertyItemNode.put(PROPERTY_FORM_WRITEABLE, property.isWriteable() ? PROPERTY_VALUE_YES : PROPERTY_VALUE_NO);
      
      ObjectNode formValueNode = objectMapper.createObjectNode();
      ArrayNode formValueItemNode = objectMapper.createArrayNode();
      
      for (FormValue formValue : property.getFormValues()) {
        ObjectNode propertyFormValueNode = objectMapper.createObjectNode();
        propertyFormValueNode.put(PROPERTY_FORM_FORM_VALUE_ID, formValue.getId());
        propertyFormValueNode.put(PROPERTY_FORM_FORM_VALUE_NAME, formValue.getName());
        formValueItemNode.add(propertyFormValueNode);
      }
      formValueNode.put("totalCount", formValueItemNode.size());
      formValueNode.put(EDITOR_PROPERTIES_GENERAL_ITEMS, formValueItemNode);
      propertyItemNode.put(PROPERTY_FORM_FORM_VALUES, formValueNode.toString());
      
      itemsNode.add(propertyItemNode);
    }
    
    formPropertiesNode.put("totalCount", itemsNode.size());
    formPropertiesNode.put(EDITOR_PROPERTIES_GENERAL_ITEMS, itemsNode);
    propertiesNode.put("formproperties", formPropertiesNode);
  }*/
  
  
  /*
  protected void addListeners(List<ActivitiListener> listeners, boolean isExecutionListener, ObjectNode propertiesNode) {
    
    String propertyName = null;
    String eventType = null;
    String listenerClass = null;
    String listenerExpression = null;
    String listenerDelegateExpression = null;
    
    if (isExecutionListener) {
      propertyName = PROPERTY_EXECUTION_LISTENERS;
      eventType = PROPERTY_EXECUTION_LISTENER_EVENT;
      listenerClass = PROPERTY_EXECUTION_LISTENER_CLASS;
      listenerExpression = PROPERTY_EXECUTION_LISTENER_EXPRESSION;
      listenerDelegateExpression = PROPERTY_EXECUTION_LISTENER_DELEGATEEXPRESSION;
      
    } else {
      propertyName = PROPERTY_TASK_LISTENERS;
      eventType = PROPERTY_TASK_LISTENER_EVENT;
      listenerClass = PROPERTY_TASK_LISTENER_CLASS;
      listenerExpression = PROPERTY_TASK_LISTENER_EXPRESSION;
      listenerDelegateExpression = PROPERTY_TASK_LISTENER_DELEGATEEXPRESSION;
    }
    
    ObjectNode listenersNode = objectMapper.createObjectNode();
    ArrayNode itemsNode = objectMapper.createArrayNode();
    for (ActivitiListener listener : listeners) {
      ObjectNode propertyItemNode = objectMapper.createObjectNode();
      
      propertyItemNode.put(eventType, listener.getEvent());
      
      if (ImplementationType.IMPLEMENTATION_TYPE_CLASS.equals(listener.getImplementationType())) {
        propertyItemNode.put(listenerClass, listener.getImplementation());
      } else if (ImplementationType.IMPLEMENTATION_TYPE_EXPRESSION.equals(listener.getImplementationType())) {
        propertyItemNode.put(listenerExpression, listener.getImplementation());
      } else if (ImplementationType.IMPLEMENTATION_TYPE_DELEGATEEXPRESSION.equals(listener.getImplementationType())) {
        propertyItemNode.put(listenerDelegateExpression, listener.getImplementation());
      }
      
      itemsNode.add(propertyItemNode);
    }
    
    listenersNode.put("totalCount", itemsNode.size());
    listenersNode.put(EDITOR_PROPERTIES_GENERAL_ITEMS, itemsNode);
    propertiesNode.put(propertyName, listenersNode);
  }
  */
  /*
  protected void addFieldExtensions(List<FieldExtension> extensions, ObjectNode propertiesNode) {
    ObjectNode fieldExtensionsNode = objectMapper.createObjectNode();
    ArrayNode itemsNode = objectMapper.createArrayNode();
    for (FieldExtension extension : extensions) {
      ObjectNode propertyItemNode = objectMapper.createObjectNode();
      propertyItemNode.put(PROPERTY_SERVICETASK_FIELD_NAME, extension.getFieldName());
      if (StringUtils.isNotEmpty(extension.getStringValue())) {
        propertyItemNode.put(PROPERTY_SERVICETASK_FIELD_VALUE, extension.getStringValue());
      }
      if (StringUtils.isNotEmpty(extension.getExpression())) {
        propertyItemNode.put(PROPERTY_SERVICETASK_FIELD_EXPRESSION, extension.getExpression());
      }
      itemsNode.add(propertyItemNode);
    }
    
    fieldExtensionsNode.put("totalCount", itemsNode.size());
    fieldExtensionsNode.put(EDITOR_PROPERTIES_GENERAL_ITEMS, itemsNode);
    propertiesNode.put(PROPERTY_SERVICETASK_FIELDS, fieldExtensionsNode);
  }*/
  
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
  
  /*
  protected void convertJsonToFormProperties(JsonNode objectNode, BaseElement element) {
    
    JsonNode formPropertiesNode = getProperty(PROPERTY_FORM_PROPERTIES, objectNode);
    if (formPropertiesNode != null) {
      if (formPropertiesNode.isValueNode() && StringUtils.isNotEmpty(formPropertiesNode.asText())) {
        try {
          formPropertiesNode = objectMapper.readTree(formPropertiesNode.asText());
        } catch (Exception e) {
          LOGGER.info("Form properties node can not be read", e);
        }
      }
      JsonNode itemsArrayNode = formPropertiesNode.get(EDITOR_PROPERTIES_GENERAL_ITEMS);
      String readWriteReqNode = null;
      JsonNode formValuesNode = null;
      JsonNode formValuesArrayNode = null;
      if (itemsArrayNode != null) {
        for (JsonNode formNode : itemsArrayNode) {
          JsonNode formIdNode = formNode.get(PROPERTY_FORM_ID);
          if (formIdNode != null && StringUtils.isNotEmpty(formIdNode.asText())) {
            
            FormProperty formProperty = new FormProperty();
            formProperty.setId(formIdNode.asText());
            formProperty.setName(getValueAsString(PROPERTY_FORM_NAME, formNode));
            formProperty.setType(getValueAsString(PROPERTY_FORM_TYPE, formNode));
            formProperty.setExpression(getValueAsString(PROPERTY_FORM_EXPRESSION, formNode));
            formProperty.setVariable(getValueAsString(PROPERTY_FORM_VARIABLE, formNode));
            readWriteReqNode = getValueAsString(PROPERTY_FORM_REQUIRED, formNode);
            if (PROPERTY_VALUE_YES.equalsIgnoreCase(readWriteReqNode))
              formProperty.setRequired(true);
            readWriteReqNode = getValueAsString(PROPERTY_FORM_READABLE, formNode);
            if (PROPERTY_VALUE_NO.equalsIgnoreCase(readWriteReqNode))
              formProperty.setReadable(false);
            readWriteReqNode = getValueAsString(PROPERTY_FORM_WRITEABLE, formNode);
            if (PROPERTY_VALUE_NO.equalsIgnoreCase(readWriteReqNode))
                formProperty.setWriteable(false);
            
            formValuesNode = formNode.get(PROPERTY_FORM_FORM_VALUES);
            if (formValuesNode != null && StringUtils.isNotEmpty(formValuesNode.asText()) && !("undefined".equals(formValuesNode.asText()))) {
              if (formValuesNode.isValueNode()) {
                try {
                  formValuesNode = objectMapper.readTree(formValuesNode.asText());
                } catch (Exception e) {
                  LOGGER.info("Form properties values node can not be read", e);
                }
              }
              formValuesArrayNode = formValuesNode.get(EDITOR_PROPERTIES_GENERAL_ITEMS);
              List<FormValue> formValues = new ArrayList<FormValue>();
              for (JsonNode valueNode : formValuesArrayNode) {
                JsonNode valueIdNode = valueNode.get(PROPERTY_FORM_FORM_VALUE_ID);
                if (valueIdNode != null && StringUtils.isNotEmpty(valueIdNode.asText())) {
                  FormValue formValue = new FormValue();
                  formValue.setId(valueIdNode.asText());
                  formValue.setName(getValueAsString(PROPERTY_FORM_FORM_VALUE_NAME, valueNode));
                  formValues.add(formValue);
                }
              }
              formProperty.setFormValues(formValues);
            }
            
            if (element instanceof StartEvent) {
              ((StartEvent) element).getFormProperties().add(formProperty);
            } else if (element instanceof UserTask) {
              ((UserTask) element).getFormProperties().add(formProperty);
            }
          }
        }
      }
    }
  }
  */
  
  /*
  protected void convertJsonToListeners(JsonNode objectNode, BaseElement element) {
    JsonNode listenersNode = null;
    
    String propertyName = null;
    String eventType = null;
    String listenerClass = null;
    String listenerExpression = null;
    String listenerDelegateExpression = null;
    String listenerFields = null;
    String listenerFieldName = null;
    String listenerFieldValue = null;
    String listenerFieldExpression = null;
    
    JsonNode listenerFieldsNode = null;
    JsonNode listenerFieldsArrayNode = null;
    
    if (element instanceof UserTask) {
      propertyName = PROPERTY_TASK_LISTENERS;
      eventType = PROPERTY_TASK_LISTENER_EVENT;
      listenerClass = PROPERTY_TASK_LISTENER_CLASS;
      listenerExpression = PROPERTY_TASK_LISTENER_EXPRESSION;
      listenerDelegateExpression = PROPERTY_TASK_LISTENER_DELEGATEEXPRESSION;
      listenerFields = PROPERTY_TASK_LISTENER_FIELDS;
      listenerFieldName = PROPERTY_TASK_LISTENER_FIELD_NAME;
      listenerFieldValue = PROPERTY_TASK_LISTENER_FIELD_VALUE;
      listenerFieldExpression = PROPERTY_TASK_LISTENER_EXPRESSION;
      
    } else {
      propertyName = PROPERTY_EXECUTION_LISTENERS;
      eventType = PROPERTY_EXECUTION_LISTENER_EVENT;
      listenerClass = PROPERTY_EXECUTION_LISTENER_CLASS;
      listenerExpression = PROPERTY_EXECUTION_LISTENER_EXPRESSION;
      listenerDelegateExpression = PROPERTY_EXECUTION_LISTENER_DELEGATEEXPRESSION;
      listenerFields = PROPERTY_EXECUTION_LISTENER_FIELDS;
      listenerFieldName = PROPERTY_EXECUTION_LISTENER_FIELD_NAME;
      listenerFieldValue = PROPERTY_EXECUTION_LISTENER_FIELD_VALUE;
      listenerFieldExpression = PROPERTY_EXECUTION_LISTENER_EXPRESSION;
    }
    
    listenersNode = getProperty(propertyName, objectNode);
    
    if (listenersNode != null) {
    
      if (listenersNode.isValueNode() && StringUtils.isNotEmpty(listenersNode.asText())) {
        try {
          listenersNode = objectMapper.readTree(listenersNode.asText());
        } catch (Exception e) {
          LOGGER.info("Listeners node can not be read", e);
        }
      }
      
      JsonNode itemsArrayNode = listenersNode.get(EDITOR_PROPERTIES_GENERAL_ITEMS);
      if (itemsArrayNode != null) {
        for (JsonNode itemNode : itemsArrayNode) {
          JsonNode typeNode = itemNode.get(eventType);
          if (typeNode != null && StringUtils.isNotEmpty(typeNode.asText())) {
            
            ActivitiListener listener = new ActivitiListener();
            listener.setEvent(typeNode.asText());
            if (StringUtils.isNotEmpty(getValueAsString(listenerClass, itemNode))) {
              listener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_CLASS);
              listener.setImplementation(getValueAsString(listenerClass, itemNode));
            } else if (StringUtils.isNotEmpty(getValueAsString(listenerExpression, itemNode))) {
              listener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_EXPRESSION);
              listener.setImplementation(getValueAsString(listenerExpression, itemNode));
            } else if (StringUtils.isNotEmpty(getValueAsString(listenerDelegateExpression, itemNode))) {
              listener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_DELEGATEEXPRESSION);
              listener.setImplementation(getValueAsString(listenerDelegateExpression, itemNode));
            }
            
            listenerFieldsNode = itemNode.get(listenerFields);
            if (listenerFieldsNode != null && StringUtils.isNotEmpty(listenerFieldsNode.asText()) && !("undefined".equals(listenerFieldsNode.asText()))){
              if(listenerFieldsNode.isValueNode()){
                try{
                  listenerFieldsNode = objectMapper.readTree(listenerFieldsNode.asText());
                } catch(Exception e){
                  LOGGER.info("Listener fields node can not be read", e);
                }
              }
            }
            if (listenerFieldsNode != null) {
              listenerFieldsArrayNode = listenerFieldsNode.get(EDITOR_PROPERTIES_GENERAL_ITEMS);
              List<FieldExtension> fields = new ArrayList<FieldExtension>();
              if (listenerFieldsArrayNode != null) {
                for (JsonNode fieldNode : listenerFieldsArrayNode){
                  JsonNode fieldNameNode = fieldNode.get(listenerFieldName);
                  if (fieldNameNode != null && StringUtils.isNotEmpty(fieldNameNode.asText())){
                    FieldExtension field = new FieldExtension();
                    field.setFieldName(fieldNameNode.asText());
                    field.setStringValue(getValueAsString(listenerFieldValue, fieldNode));
                    field.setExpression(getValueAsString(listenerFieldExpression, fieldNode));
                    fields.add(field);
                  }
                }
              }
              listener.setFieldExtensions(fields);
            }
            
            if (element instanceof Process) {
              ((Process) element).getExecutionListeners().add(listener);
            } else if (element instanceof SequenceFlow) {
              ((SequenceFlow) element).getExecutionListeners().add(listener);
            } else if (element instanceof UserTask) {
              ((UserTask) element).getTaskListeners().add(listener);
            } else if (element instanceof Activity) {
              ((Activity) element).getExecutionListeners().add(listener);
            }
          }
        }
      }
    }
  }
  */
  
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
