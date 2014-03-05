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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.editor.language.json.converter.BaseBpmnJsonConverter;
import com.founder.fix.fixflow.editor.language.json.converter.UserTaskJsonConverter;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.PotentialOwner;
import org.eclipse.bpmn2.ResourceAssignmentExpression;
import org.eclipse.bpmn2.ResourceRole;
import org.eclipse.bpmn2.UserTask;

import com.founder.fix.bpmn2extensions.fixflow.AssignPolicyType;
import com.founder.fix.bpmn2extensions.fixflow.Expression;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowFactory;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.fixflow.FormUri;
import com.founder.fix.bpmn2extensions.fixflow.FormUriView;
import com.founder.fix.bpmn2extensions.fixflow.TaskCommand;
import com.founder.fix.bpmn2extensions.fixflow.TaskPriority;
import com.founder.fix.bpmn2extensions.fixflow.TaskSubject;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.util.BpmnModelUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.editor.language.json.converter.util.JsonConverterUtil;


public class UserTaskJsonConverter extends BaseBpmnJsonConverter {
	  public static void fillTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap,
	      Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
		  fillJsonTypes(convertersToBpmnMap);
		  fillBpmnTypes(convertersToJsonMap);
	  }
	  
	  public static void fillJsonTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap) {
		  convertersToBpmnMap.put(STENCIL_TASK_USER, UserTaskJsonConverter.class);
	  }
	  
	  public static void fillBpmnTypes(Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
		  convertersToJsonMap.put(UserTaskBehavior.class, UserTaskJsonConverter.class);
	  }
	  
	  @Override
	  protected String getStencilId(FlowElement flowElement) {
		  return STENCIL_TASK_USER;
	  }
	  
	  @Override
	  protected void convertElementToJson(ObjectNode propertiesNode, FlowElement flowElement) {
		  UserTaskBehavior userTask = (UserTaskBehavior) flowElement;
		  List<ResourceRole> resources = userTask.getResources();
		  //任务分配
		  setPropertyValue(PROPERTY_USERTASK_POLICYTYPE, userTask.getAssignPolicyType().getId(), propertiesNode);
		  Expression assigneeExpression = userTask.getAssignPolicyType().getExpression();
		  if(assigneeExpression != null){
			  setPropertyValue(PROPERTY_USERTASK_ASSIGNEXPRESSION, assigneeExpression.getValue(), propertiesNode);
		  }
		  if(resources != null){
			  ObjectNode assignmentNode = objectMapper.createObjectNode();
			  ArrayNode itemsNode = objectMapper.createArrayNode();
			  for(ResourceRole resource :resources){
				  if(resource != null){
					  String resourceType = StringUtil.getString(resource.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_TYPE, true));
					  String isContainsSub = StringUtil.getString(resource.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__IS_CONTAINS_SUB, true));
					  String resourceExpression = BpmnModelUtil.getExpression(resource.getResourceAssignmentExpression().getExpression());
					  String resourceName = resource.getName();
					  ObjectNode assignmentItemNode = objectMapper.createObjectNode();
					  assignmentItemNode.put(PROPERTY_USERTASK_RESOURCE_TYPE, resourceType);
					  assignmentItemNode.put(PROPERTY_USERTASK_RESOURCE_IS_CONTAINSSUB, isContainsSub);
					  assignmentItemNode.put(PROPERTY_USERTASK_RESOURCE_EXPRESSION, resourceExpression);
					  assignmentItemNode.put(PROPERTY_USERTASK_RESOURCE_NAME, resourceName);
					  itemsNode.add(assignmentItemNode);
				  }
			  }
			  assignmentNode.put("totalCount", itemsNode.size());
			  assignmentNode.put(EDITOR_PROPERTIES_GENERAL_ITEMS, itemsNode);
			  propertiesNode.put(PROPERTY_USERTASK_ASSIGNMENT, assignmentNode);
	    }
	    //处理命令
	    List<TaskCommandInst> commandList =  userTask.getTaskCommands();
	    if(commandList != null){
	    	ObjectNode commandNode = objectMapper.createObjectNode();
	    	ArrayNode itemsNode = objectMapper.createArrayNode();
	    	for(TaskCommandInst command :commandList){
	    		String commandId = command.getId();
	    		String commandType = command.getTaskCommandType();
	    		String commandName = command.getName();
	    		String commandExpression = command.getExpression();
	    		String isSaveData = StringUtil.getString(command.isSaveData());
	    		String isVerification = StringUtil.getString(command.isVerification());
	    		String isSimulationRun = StringUtil.getString(command.isSimulationRun());
	    		String parameterExpression = command.getExpressionParam();
	    		
	    		ObjectNode taskCommandNode = objectMapper.createObjectNode();
	    		taskCommandNode.put(PROPERTY_TASKCOMMAND_ID, commandId);
	    		taskCommandNode.put(PROPERTY_TASKCOMMAND_NAME, commandName);
	    		taskCommandNode.put(PROPERTY_TASKCOMMAND_TYPE, commandType);
	    		taskCommandNode.put(PROPERTY_TASKCOMMAND_EXPRESSION, commandExpression);
	    		taskCommandNode.put(PROPERTY_TASKCOMMAND_IS_SAVEDATA, isSaveData);
	    		taskCommandNode.put(PROPERTY_TASKCOMMAND_IS_SIMULATION_RUN, isSimulationRun);
	    		taskCommandNode.put(PROPERTY_TASKCOMMAND_IS_VERIFICATION, isVerification);
	    		taskCommandNode.put(PROPERTY_TASKCOMMAND_PARA_EXPRESSION, parameterExpression);
	    		itemsNode.add(taskCommandNode);
	    	}
	    	commandNode.put("totalCount", itemsNode.size());
	    	commandNode.put(EDITOR_PROPERTIES_GENERAL_ITEMS, itemsNode);
	        propertiesNode.put(PROPERTY_TASKCOMMAND, commandNode);
	    }
	    //任务主题
	    if(userTask.getTaskSubject() !=null){
	    	setPropertyValue(PROPERTY_USERTASK_SUBJECT, userTask.getTaskSubject().getExpressionValue(), propertiesNode);
	    }
	    //任务类型
	    if(userTask.getTaskInstanceType() != null){
	    	setPropertyValue(PROPERTY_USERTASK_TASKTYPE, StringUtil.getString(userTask.getTaskInstanceType()), propertiesNode);
	    }
	    //任务优先级
	    if (userTask.getTaskPriority() != null) {
	    	setPropertyValue(PROPERTY_PRIORITY, userTask.getTaskPriority(), propertiesNode);
	    }
	    //操作表单
	    setPropertyValue(PROPERTY_FORMURI, userTask.getFormUri(), propertiesNode);
	    //浏览表单
	    setPropertyValue(PROPERTY_FORMURI_VIEW, userTask.getFormUriView(), propertiesNode);
	  }
  
	  @Override
	  protected FlowElement convertJsonToElement(JsonNode elementNode, JsonNode modelNode, Map<String, JsonNode> shapeMap) {
		  UserTask task = Bpmn2Factory.eINSTANCE.createUserTask();// UserTask();
		  //任务主题
		  JsonNode processSubject = JsonConverterUtil.getProperty(PROPERTY_USERTASK_SUBJECT, elementNode);
		  if(processSubject != null && StringUtils.isNotEmpty(processSubject.asText())) {
			  TaskSubject taskSubject = FixFlowFactory.eINSTANCE.createTaskSubject();
			  Expression subjectExpression = FixFlowFactory.eINSTANCE.createExpression();
			  subjectExpression.setName(processSubject.asText());
			  subjectExpression.setValue(processSubject.asText());
			  taskSubject.setExpression(subjectExpression);
			  taskSubject.setId("TaskSubject_1");
			  BpmnModelUtil.addExtensionElement(task, FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_SUBJECT, taskSubject);
		  }
	    
		  //任务类型
		  JsonNode taskTypeNode = getProperty(PROPERTY_USERTASK_TASKTYPE, elementNode);
		  if(taskTypeNode != null){
			  BpmnModelUtil.addExtensionAttribute(task, FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_TYPE, taskTypeNode.asText());
		  }
		  
		  //任务优先级
		  JsonNode taskPriorityNode = getProperty(PROPERTY_PRIORITY, elementNode);
		  if(taskPriorityNode != null){
			  TaskPriority taskPriority = FixFlowFactory.eINSTANCE.createTaskPriority();
			  Expression taskPriorityExpression = FixFlowFactory.eINSTANCE.createExpression();
			  taskPriorityExpression.setName(taskPriorityNode.asText());
			  taskPriorityExpression.setValue(taskPriorityNode.asText());
			  taskPriority.setExpression(taskPriorityExpression);
			  BpmnModelUtil.addExtensionElement(task, FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_PRIORITY, taskPriority);
		  }
		  
		  //任务命令
		  JsonNode taskCommandNode = getProperty(PROPERTY_TASKCOMMAND, elementNode);
		  if(taskCommandNode != null){
			  JsonNode itemsNode = taskCommandNode.get(EDITOR_PROPERTIES_GENERAL_ITEMS);
			  if(itemsNode !=null){
				  Iterator<JsonNode> taskcommandIterator = itemsNode.getElements();
				  while(taskcommandIterator.hasNext()) {
					  JsonNode taskCommandItemNode = taskcommandIterator.next();
					  TaskCommand taskCommand = FixFlowFactory.eINSTANCE.createTaskCommand();
					  String commandName = taskCommandItemNode.get(PROPERTY_TASKCOMMAND_NAME).asText();
					  String commandId = taskCommandItemNode.get(PROPERTY_TASKCOMMAND_ID).asText();
					  String commandType = taskCommandItemNode.get(PROPERTY_TASKCOMMAND_TYPE).asText();
					  String isSaveData = taskCommandItemNode.get(PROPERTY_TASKCOMMAND_IS_SAVEDATA).asText();
					  String isSimulation =  taskCommandItemNode.get(PROPERTY_TASKCOMMAND_IS_SIMULATION_RUN).asText();
					  String isVerify = taskCommandItemNode.get(PROPERTY_TASKCOMMAND_IS_VERIFICATION).asText();
					  String paraExpression = taskCommandItemNode.get(PROPERTY_TASKCOMMAND_PARA_EXPRESSION).asText();
					  String expression = taskCommandItemNode.get(PROPERTY_TASKCOMMAND_EXPRESSION).asText();
		        	
					  taskCommand.setCommandType(commandType);
					  taskCommand.setId(commandId);
					  taskCommand.setName(commandName);
					  taskCommand.setIsSimulationRun(isSimulation);
					  taskCommand.setIsSaveData(isSaveData);
					  taskCommand.setIsVerification(isVerify);
					  //执行表达式
					  Expression expression2 = FixFlowFactory.eINSTANCE.createExpression();
					  expression2.setName(expression);
					  expression2.setValue(expression);
					  taskCommand.setExpression(expression2);
					  //参数表达式
					  Expression expression3 = FixFlowFactory.eINSTANCE.createExpression();
					  expression3.setName(paraExpression);
					  expression3.setValue(paraExpression);
					  taskCommand.setParameterExpression(expression3);
					  BpmnModelUtil.addExtensionElement(task, FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_COMMAND, taskCommand);
				  }
			  }
		  }
	    
		  //默认表单
		  JsonNode usertaskFormUri = JsonConverterUtil.getProperty(PROPERTY_FORMURI, elementNode);
		  if(usertaskFormUri != null && StringUtil.isNotEmpty(usertaskFormUri.asText())){
			  FormUri formUri = FixFlowFactory.eINSTANCE.createFormUri();
			  Expression expression = FixFlowFactory.eINSTANCE.createExpression();
			  expression.setName(usertaskFormUri.asText());
			  expression.setValue(usertaskFormUri.asText());
			  formUri.setExpression(expression);
			  BpmnModelUtil.addExtensionElement(task, FixFlowPackage.Literals.DOCUMENT_ROOT__FORM_URI, formUri);
		  }
		  //浏览表单
		  JsonNode usertaskFormUriView = JsonConverterUtil.getProperty(PROPERTY_FORMURI_VIEW, elementNode);
		  if(usertaskFormUriView != null && StringUtil.isNotEmpty(usertaskFormUriView.asText())){
			  FormUriView formUri = FixFlowFactory.eINSTANCE.createFormUriView();
			  Expression expression = FixFlowFactory.eINSTANCE.createExpression();
			  expression.setName(usertaskFormUriView.asText());
			  expression.setValue(usertaskFormUriView.asText());
			  formUri.setExpression(expression);
			  BpmnModelUtil.addExtensionElement(task, FixFlowPackage.Literals.DOCUMENT_ROOT__FORM_URI_VIEW, formUri);
		  }
		  //分配策略
		  JsonNode policyTypeNode = getProperty(PROPERTY_USERTASK_POLICYTYPE, elementNode);
		  if(policyTypeNode != null){
			  AssignPolicyType assignPolicyType = FixFlowFactory.eINSTANCE.createAssignPolicyType();
			  assignPolicyType.setId(policyTypeNode.asText());
			  String expressionBody = getPropertyValueAsString(PROPERTY_USERTASK_ASSIGNEXPRESSION, elementNode);
			  if(StringUtil.isNotEmpty(expressionBody)){
				  Expression policyExpression = FixFlowFactory.eINSTANCE.createExpression();
				  policyExpression.setValue(expressionBody);
				  policyExpression.setName(expressionBody);
				  assignPolicyType.setExpression(policyExpression);
			  }
			  BpmnModelUtil.addExtensionElement(task, FixFlowPackage.Literals.DOCUMENT_ROOT__ASSIGN_POLICY_TYPE, assignPolicyType);
		  }
		  //转换处理人
		  JsonNode assignmentNode = getProperty(PROPERTY_USERTASK_ASSIGNMENT, elementNode);
		  if(assignmentNode != null) {
			  JsonNode itemsNode = assignmentNode.get(EDITOR_PROPERTIES_GENERAL_ITEMS);
			  if(itemsNode != null) {
				  Iterator<JsonNode> assignmentIterator = itemsNode.getElements();
				  while (assignmentIterator.hasNext()) {
					  JsonNode assignmentItemNode = assignmentIterator.next();
					  PotentialOwner potentialOwner = Bpmn2Factory.eINSTANCE.createPotentialOwner();
					  String resourceType = assignmentItemNode.get(PROPERTY_USERTASK_RESOURCE_TYPE).asText();
					  String includeExclusion = "INCLUDE";
					  String isContainSub = assignmentItemNode.get(PROPERTY_USERTASK_RESOURCE_IS_CONTAINSSUB).asText();
					  String resourceName = assignmentItemNode.get(PROPERTY_USERTASK_RESOURCE_NAME).asText();
					  String expressionBody = assignmentItemNode.get(PROPERTY_USERTASK_RESOURCE_EXPRESSION).asText();
					  
					  BpmnModelUtil.addExtensionAttribute(potentialOwner, FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_TYPE, resourceType);
					  BpmnModelUtil.addExtensionAttribute(potentialOwner, FixFlowPackage.Literals.DOCUMENT_ROOT__INCLUDE_EXCLUSION, includeExclusion);
					  BpmnModelUtil.addExtensionAttribute(potentialOwner, FixFlowPackage.Literals.DOCUMENT_ROOT__IS_CONTAINS_SUB, isContainSub);
					  ResourceAssignmentExpression resourceAssignmentExpression = Bpmn2Factory.eINSTANCE.createResourceAssignmentExpression();
					  potentialOwner.setName(resourceName);
					  FormalExpression formalExpression = Bpmn2Factory.eINSTANCE.createFormalExpression();
					  formalExpression.setBody(expressionBody);
					  formalExpression.setId(expressionBody);
					  resourceAssignmentExpression.setExpression(formalExpression);
					  potentialOwner.setResourceAssignmentExpression(resourceAssignmentExpression);
					  task.getResources().add(potentialOwner);
				  }
			  }
		  }
		  return task;
	  }
}
