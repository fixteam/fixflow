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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.PotentialOwner;
import org.eclipse.bpmn2.ResourceRole;
import org.eclipse.bpmn2.UserTask;

import com.founder.fix.bpmn2extensions.fixflow.Expression;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.fixflow.SkipAssignee;
import com.founder.fix.bpmn2extensions.fixflow.SkipComment;
import com.founder.fix.bpmn2extensions.fixflow.SkipStrategy;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.util.BpmnModelUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

/**
 * @author Tijs Rademakers
 */
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
    if(userTask.getTaskSubject() !=null){
    	setPropertyValue(PROPERTY_USERTASK_SUBJECT, userTask.getTaskSubject().getExpressionValue(), propertiesNode);
    }
    if(userTask.getTaskInstanceType() != null){
    	setPropertyValue(PROPERTY_USERTASK_TASKTYPE, StringUtil.getString(userTask.getTaskInstanceType()), propertiesNode);
    }
    if (userTask.getTaskPriority() != null) {
      setPropertyValue(PROPERTY_PRIORITY, userTask.getTaskPriority(), propertiesNode);
    }
    setPropertyValue(PROPERTY_FORMURI, userTask.getFormUri(), propertiesNode);
    setPropertyValue(PROPERTY_FORMURI_VIEW, userTask.getFormUriView(), propertiesNode);
  }
  
  @Override
  protected FlowElement convertJsonToElement(JsonNode elementNode, JsonNode modelNode, Map<String, JsonNode> shapeMap) {
    UserTask task = Bpmn2Factory.eINSTANCE.createUserTask();// UserTask();
   // task.setPriority(getPropertyValueAsString(PROPERTY_PRIORITY, elementNode));
    //task.setFormKey(getPropertyValueAsString(PROPERTY_FORMKEY, elementNode));
    //task.setDueDate(getPropertyValueAsString(PROPERTY_DUEDATE, elementNode));
    
    JsonNode assignmentNode = getProperty(PROPERTY_USERTASK_ASSIGNMENT, elementNode);
    if (assignmentNode != null) {
      JsonNode itemsNode = assignmentNode.get(EDITOR_PROPERTIES_GENERAL_ITEMS);
      if (itemsNode != null) {
        Iterator<JsonNode> assignmentIterator = itemsNode.getElements();
        while (assignmentIterator.hasNext()) {
          JsonNode assignmentItemNode = assignmentIterator.next();
          if (assignmentItemNode.get(PROPERTY_USERTASK_ASSIGNMENT_TYPE) != null && 
              assignmentItemNode.get(PROPERTY_USERTASK_ASSIGNMENT_EXPRESSION) != null) {
            
            String assignmentType = assignmentItemNode.get(PROPERTY_USERTASK_ASSIGNMENT_TYPE).asText();
            if (PROPERTY_USERTASK_ASSIGNEE.equals(assignmentType)) {
              //ask.setAssignee(assignmentItemNode.get(PROPERTY_USERTASK_ASSIGNMENT_EXPRESSION).asText());
            } else if (PROPERTY_USERTASK_CANDIDATE_USERS.equals(assignmentType)) {
              //task.setCandidateUsers(getValueAsList(PROPERTY_USERTASK_ASSIGNMENT_EXPRESSION, assignmentItemNode));
            } else if (PROPERTY_USERTASK_CANDIDATE_GROUPS.equals(assignmentType)) {
             // task.setCandidateGroups(getValueAsList(PROPERTY_USERTASK_ASSIGNMENT_EXPRESSION, assignmentItemNode));
            }
          }
        }
      }
    }
//    convertJsonToFormProperties(elementNode, task);
    return task;
  }
}
