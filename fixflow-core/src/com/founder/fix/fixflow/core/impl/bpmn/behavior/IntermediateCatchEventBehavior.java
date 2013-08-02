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
package com.founder.fix.fixflow.core.impl.bpmn.behavior;

import java.util.Date;
import java.util.List;

import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.impl.IntermediateCatchEventImpl;

import com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef;
import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.task.TaskCommandType;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.ClockUtil;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskInstanceType;

public class IntermediateCatchEventBehavior extends IntermediateCatchEventImpl {
	
	
	
	@Override
	public void execute(ExecutionContext executionContext) {
		
		
		List<EventDefinition> eventDefinitionList = this.getEventDefinitions();
		if (eventDefinitionList != null) {
			for (EventDefinition eventDefinition : eventDefinitionList) {
				eventDefinition.execute(executionContext, this);
			}
		}
		
		

		createEventTask(executionContext);
	}
	
	
	private void createEventTask(ExecutionContext executionContext) {

		// 构造创建任务所需的数据
		String newTaskId = GuidUtil.CreateGuid();
		String newTaskProcessInstanceId = executionContext.getProcessInstance().getId();
		String newTaskProcessDefinitionId = executionContext.getProcessDefinition().getProcessDefinitionId();
		String newTaskTokenId = executionContext.getToken().getId();
		String newTaskNodeId = executionContext.getToken().getNodeId();
		String newTaskNodeName = executionContext.getToken().getFlowNode().getName();
		String newTaskDescription = newTaskNodeName;
		Date newTaskCreateTime = ClockUtil.getCurrentTime();
		int newTaskPriority = TaskInstance.PRIORITY_NORMAL;
		String newTaskProcessDefinitionKey = executionContext.getProcessDefinition().getProcessDefinitionKey();
		TaskInstanceType newTaskTaskInstanceType = TaskInstanceType.INTERMEDIATECATCHEVENT;
		String newTaskProcessDefinitionName = executionContext.getProcessDefinition().getName();
		boolean isDraft = false;

		// 创建任务
		TaskInstance taskInstance = new TaskInstanceEntity();
		taskInstance.setId(newTaskId);
		taskInstance.setNodeName(newTaskNodeName);
		taskInstance.setProcessInstanceId(newTaskProcessInstanceId);
		taskInstance.setProcessDefinitionId(newTaskProcessDefinitionId);
		taskInstance.setTokenId(newTaskTokenId);
		taskInstance.setNodeId(newTaskNodeId);
		taskInstance.setName(newTaskNodeName);
		taskInstance.setDescription(newTaskDescription);
		taskInstance.setCreateTime(newTaskCreateTime);
		taskInstance.setPriority(newTaskPriority);
		taskInstance.setProcessDefinitionKey(newTaskProcessDefinitionKey);
		taskInstance.setTaskInstanceType(newTaskTaskInstanceType);
		taskInstance.setProcessDefinitionName(newTaskProcessDefinitionName);
		taskInstance.setDraft(isDraft);
		//taskInstance.setCallActivityInstanceId(subProcessInstanceId);
		Context.getCommandContext().getTaskManager().saveTaskInstanceEntity((TaskInstanceEntity) taskInstance);

	}

	@Override
	public void leaveClearData(ExecutionContext executionContext){

		ProcessEngine processEngine=ProcessEngineManagement.getDefaultProcessEngine();
		TaskInstanceEntity taskInstance=(TaskInstanceEntity)processEngine.getTaskService().createTaskQuery().tokenId(executionContext.getToken().getId()).addTaskType(TaskInstanceType.INTERMEDIATECATCHEVENT).taskNotEnd().singleResult();
		//当发现老任务,并没有创建等待接收任务的时候,不需要清理
		if(taskInstance==null){
			return;
		}
		
		
		Date newTaskEndTime=ClockUtil.getCurrentTime();
		//taskInstance.setAssigneeId("1200119390");
		taskInstance.setEndTime(newTaskEndTime);
		taskInstance.setCommandId(TaskCommandType.RECEIVEEND);
		taskInstance.setCommandType(TaskCommandType.RECEIVEEND);
		TaskCommandDef taskCommandDef=Context.getProcessEngineConfiguration().getTaskCommandDefMap().get(TaskCommandType.RECEIVEEND);
		if(taskCommandDef!=null){
			taskInstance.setCommandMessage(taskCommandDef.getName());
		}

		Context.getCommandContext().getTaskManager().saveTaskInstanceEntity(taskInstance);
		

	}

}
