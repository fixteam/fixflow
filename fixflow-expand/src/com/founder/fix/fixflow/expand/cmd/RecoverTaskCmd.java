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
package com.founder.fix.fixflow.expand.cmd;


import java.util.List;

import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.RuntimeService;
import com.founder.fix.fixflow.core.TaskService;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.cmd.AbstractExpandTaskCmd;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;
import com.founder.fix.fixflow.core.impl.persistence.ProcessInstanceManager;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.runtime.Token;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.expand.command.RecoverTaskCommand;

public class RecoverTaskCmd extends AbstractExpandTaskCmd<RecoverTaskCommand,Void>{


	protected String recoverNodeId;
	
	//protected String processInstanceId;
	
	public RecoverTaskCmd(RecoverTaskCommand recoverTaskCommand) {
		super(recoverTaskCommand);
		// TODO 自动生成的构造函数存根

		this.recoverNodeId=recoverTaskCommand.getRecoverNodeId();
		//this.processInstanceId=recoverTaskCommand.getProcessInstanceId();
	}

	public Void execute(CommandContext commandContext) {
		// TODO 自动生成的方法存根
		
		
		
		TaskService taskService=ProcessEngineManagement.getDefaultProcessEngine().getTaskService();
		RuntimeService runtimeService=ProcessEngineManagement.getDefaultProcessEngine().getRuntimeService();
		
		TaskInstance taskInstanceTemp=taskService.createTaskQuery().taskId(taskId).singleResult();
		
		String userId= Authentication.getAuthenticatedUserId();
		List<TaskInstance> endTaskInstances=taskService.createTaskQuery().processInstanceId(taskInstanceTemp.getProcessInstanceId()).taskAssignee(userId).taskIsEnd().nodeId(recoverNodeId).list();
		
		
		TaskInstance recoverToTask=endTaskInstances.get(0);
		
		String tokenId=recoverToTask.getTokenId();
		Token recoverToToken=runtimeService.createTokenQuery().tokenId(tokenId).singleResult();
		if(recoverToToken.getEndTime()==null){
			
		
			String processDefinitionId = recoverToTask.getProcessDefinitionId();
			ProcessInstanceManager processInstanceManager = commandContext.getProcessInstanceManager();

			String processInstanceId = recoverToTask.getProcessInstanceId();

			ProcessDefinitionManager processDefinitionManager = commandContext.getProcessDefinitionManager();

			ProcessDefinitionBehavior processDefinition = processDefinitionManager.findLatestProcessDefinitionById(processDefinitionId);

			UserTaskBehavior userTask = (UserTaskBehavior) processDefinition.getDefinitions().getElement(recoverNodeId);

			
			UserTaskBehavior userTaskNow=(UserTaskBehavior) processDefinition.getDefinitions().getElement(taskInstanceTemp.getNodeId());
			
			TaskCommandInst taskCommand = userTaskNow.getTaskCommandsMap().get(userCommandId);

			ProcessInstanceEntity processInstanceImpl = processInstanceManager.findProcessInstanceById(processInstanceId, processDefinition);
			
			TokenEntity tokenEntity=processInstanceImpl.getTokenMap().get(tokenId);
			
			
			
			tokenEntity.terminationChildTokenWithTask(taskCommand.getTaskCommandType(), taskCommand.getName(),this.taskComment,userId,this.agent,this.admin);
			
			
			
		
			processInstanceImpl.getContextInstance().setVariableMap(variables);

			TokenEntity token = processInstanceImpl.getTokenMap().get(tokenId);

			processInstanceImpl.getContextInstance().setTransientVariableMap(transientVariables);
			processInstanceImpl.getContextInstance().setVariableMap(variables);
			

			ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(token);

			
			List<TaskInstanceEntity> taskInstances=processInstanceImpl.getTaskMgmtInstance().getTaskInstanceEntitys();
			
			
			for (TaskInstanceEntity taskInstanceEntity : taskInstances) {
				if(taskInstanceEntity.getId().equals(taskId)){
					taskInstanceEntity.setAssigneeWithoutCascade(userId);
					taskInstanceEntity.customEnd(taskCommand, taskComment, this.agent, this.admin);
				}
			}
			
			
			token.removeTaskInstanceSynchronization(taskCommand.getTaskCommandType(), taskCommand.getName(),this.taskComment, userId,this.agent,this.admin);
			
			
			
			
			
			if (recoverToTask.getTaskGroup()!=null) {

				if (taskCommand != null) {
					
							
							
					userTask.enter(executionContext);
		


				}

				try {
					processInstanceManager.saveProcessInstance(processInstanceImpl);
				} catch (Exception e) {
					throw new FixFlowException("流程实例持久化失败!", e);
				}
				return null;

			} else {

				if (taskCommand != null) {

							
					executionContext.setRollBackAssignee(recoverToTask.getAssignee());
					userTask.enter(executionContext);
	
				}

				try {
					processInstanceManager.saveProcessInstance(processInstanceImpl);
				} catch (Exception e) {
					throw new FixFlowException("流程实例持久化失败!", e);
				}
				return null;

			}
			
			

			
		}
		else{
			
			
			
			
			
			
			
			
		}
		
		
		
		return null;
		
		/*
		
		
		TaskManager taskManager = commandContext.getTaskManager();

		TaskInstance recoverTask = taskManager.findTaskById(this.taskId);
		
		TaskInstance recoverToTask = taskManager.findTaskById(this.recoverTaskToTaskId);
		
		
		String recoverTaskTokenId=recoverTask.getTokenId();
		String recoverToTaskTokenId=recoverToTask.getTokenId();
		
		
		//令牌退回算法   
		//被追回的任务的令牌号 等于 追回到的任务的令牌号 的时候，采用直接将当前任务结束然后将令牌扔回 追回到的节点
		//
		//两个令牌相等 
		 * 
		 */
		
		
		
			
		/*

			
			String tokenId = recoverTask.getTokenId();
			String nodeId = recoverTask.getNodeId();
			String processDefinitionId = recoverTask.getProcessDefinitionId();
			ProcessInstanceManager processInstanceManager = commandContext.getProcessInstanceManager();

			String processInstanceId = recoverTask.getProcessInstanceId();

			ProcessDefinitionManager processDefinitionManager = commandContext.getProcessDefinitionManager();

			ProcessDefinitionBehavior processDefinition = processDefinitionManager.findLatestProcessDefinitionById(processDefinitionId);

			UserTaskBehavior userTask = (UserTaskBehavior) processDefinition.getDefinitions().getElement(nodeId);

			TaskCommand taskCommand = userTask.getTaskCommandsMap().get(userCommandId);

			
			
			
			
		
	 
		String tokenId = taskInstanceQueryRollBack.getTokenId();
		String nodeId = taskInstanceQueryRollBack.getNodeId();
		String processDefinitionId = taskInstanceQueryRollBack.getProcessDefinitionId();
		ProcessInstanceManager processInstanceManager = commandContext.getProcessInstanceManager();

		String processInstanceId = taskInstanceQueryRollBack.getProcessInstanceId();

		ProcessDefinitionManager processDefinitionManager = commandContext.getProcessDefinitionManager();

		ProcessDefinitionBehavior processDefinition = processDefinitionManager.findLatestProcessDefinitionById(processDefinitionId);

		UserTaskBehavior userTask = (UserTaskBehavior) processDefinition.getDefinitions().getElement(nodeId);

		TaskCommand taskCommand = userTask.getTaskCommandsMap().get(userCommandId);

		ProcessInstanceEntity processInstanceImpl = processInstanceManager.findProcessInstanceById(processInstanceId, processDefinition);
		
		TokenEntity tokenEntity=processInstanceImpl.getTokenMap().get(tokenId);
		tokenEntity.terminationChildTokenWithTask(taskCommand.getTaskCommandType(), taskCommand.getName(),Authentication.getAuthenticatedUserId());
		

		return null;
		
		*/
		
	}

}
