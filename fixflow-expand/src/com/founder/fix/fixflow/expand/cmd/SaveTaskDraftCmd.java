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


import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.RuntimeService;
import com.founder.fix.fixflow.core.TaskService;
import com.founder.fix.fixflow.core.exception.FixFlowBizException;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.cmd.AbstractExpandTaskCmd;
import com.founder.fix.fixflow.core.impl.command.StartProcessInstanceCommand;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;
import com.founder.fix.fixflow.core.impl.persistence.ProcessInstanceManager;
import com.founder.fix.fixflow.core.impl.persistence.TaskManager;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.task.TaskDefinition;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.founder.fix.fixflow.expand.command.SaveTaskDraftCommand;

public class SaveTaskDraftCmd extends AbstractExpandTaskCmd<SaveTaskDraftCommand,Void>  {


	public SaveTaskDraftCmd(SaveTaskDraftCommand saveTaskDraftCommand) {
		
		super(saveTaskDraftCommand);

	}

	

	public Void execute(CommandContext commandContext) {
		
		
		if(Authentication.getAuthenticatedUserId()==null||Authentication.getAuthenticatedUserId().equals("")){
			throw new FixFlowException("登录用户不能!");
		}
		

		if (taskId != null&&!taskId.equals("")) {
			
			TaskManager taskManager = commandContext.getTaskManager();
			TaskInstance taskInstanceQuery = taskManager.findTaskById(taskId);
			
			if(taskInstanceQuery.hasEnded()){
				throw new FixFlowBizException("当前的任务已经结束,无法继续处理!");
			}

			String tokenId = taskInstanceQuery.getTokenId();
			String nodeId = taskInstanceQuery.getNodeId();
			String processDefinitionId = taskInstanceQuery.getProcessDefinitionId();
			ProcessInstanceManager processInstanceManager = commandContext.getProcessInstanceManager();

			String processInstanceId = taskInstanceQuery.getProcessInstanceId();

			ProcessDefinitionManager processDefinitionManager = commandContext.getProcessDefinitionManager();

			ProcessDefinitionBehavior processDefinition = processDefinitionManager.findLatestProcessDefinitionById(processDefinitionId);

			UserTaskBehavior userTask = (UserTaskBehavior) processDefinition.getDefinitions().getElement(nodeId);
			
			TaskCommandInst taskCommand=null;
			
			if(this.admin!=null&&!this.admin.equals("")){
				
				String taskCommandName=commandContext.getProcessEngineConfigurationImpl().getTaskCommandDefMap().get(userCommandId).getName();
				
				taskCommand=new TaskCommandInst(userCommandId, taskCommandName, null, userCommandId, true);
				
				
				
			}
			else{
				taskCommand = userTask.getTaskCommandsMap().get(userCommandId);
			}
			
			


			ProcessInstanceEntity processInstanceImpl = processInstanceManager.findProcessInstanceById(processInstanceId, processDefinition);

			
			
			processInstanceImpl.getContextInstance().addTransientVariable("fixVariable_userCommand", userCommandId);

			processInstanceImpl.getContextInstance().setVariableMap(variables);
			
			//在第一次启动的时候没有bizkey 的时候,在保存草稿的时候去设置bizkey
			if(processInstanceImpl.getBizKey()==null&&this.businessKey!=null){
				processInstanceImpl.setBizKey(this.businessKey);
			}
			
			//processInstanceImpl.setBizKey(this.businessKey);

			TokenEntity token = processInstanceImpl.getTokenMap().get(tokenId);
			
			processInstanceImpl.getContextInstance().setTransientVariableMap(transientVariables);

			ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(token);

			if (taskCommand != null && taskCommand.getExpression() != null) {
				try {
					
					ExpressionMgmt.execute(taskCommand.getExpression(), executionContext);
				} catch (Exception e) {
					throw new FixFlowException("用户命令表达式执行异常!", e);
				}
			}

			List<TaskInstanceEntity> taskInstances = processInstanceImpl.getTaskMgmtInstance().getTaskInstanceEntitys();

			TaskInstanceEntity taskInstanceImpl=null;
			
			for (TaskInstanceEntity taskInstance : taskInstances) {
				if (taskInstance.getId().equals(taskId)) {
				
					
					taskInstanceImpl=taskInstance;
		
				}
			}
			
			
			if(taskInstanceImpl!=null){
				
				if(taskInstanceImpl.getBizKey()==null||taskInstanceImpl.equals("")){
					taskInstanceImpl.setBizKey(this.businessKey);
				}
				
				if(this.agent!=null&&!this.agent.equals("")){
					taskInstanceImpl.setAgent(Authentication.getAuthenticatedUserId());
					taskInstanceImpl.setAssigneeWithoutCascade(this.agent);
				}else{
					taskInstanceImpl.setAssigneeWithoutCascade(Authentication.getAuthenticatedUserId());
					taskInstanceImpl.setAgent(null);
				}
				
				
				//if(AbstractCommandFilter.isAutoClaim()){
					
				//}
				
				taskInstanceImpl.setDraft(true);
				
				TaskDefinition taskDefinition=taskInstanceImpl.getTaskDefinition();
				
				if (taskDefinition != null && taskDefinition.getDescriptionExpression() != null) {

					Object result = ExpressionMgmt.execute(taskDefinition.getDescriptionExpression(), executionContext);
					if (result != null) {
						taskInstanceImpl.setDescription(result.toString());
					} else {
						taskInstanceImpl.setDescription(token.getFlowNode().getName());
					}
				} else {
					
					if (processDefinition.getTaskSubject() != null && processDefinition.getTaskSubject().getExpressionValue() != null) {

						Object result = ExpressionMgmt.execute(token.getProcessInstance().getProcessDefinition().getTaskSubject().getExpressionValue(),
								executionContext);

						if (result != null) {
							taskInstanceImpl.setDescription(result.toString());
						}
					} else {
						taskInstanceImpl.setDescription(token.getFlowNode().getName());
					}
					
					
				}
				
				
				try {
					commandContext.getProcessInstanceManager().saveProcessInstance(processInstanceImpl);
				} catch (Exception e) {
					throw new FixFlowException("任务 "+taskId+" 保存出错！");
				}
				//commandContext.getTaskManager().saveTaskInstanceEntity((TaskInstanceEntity)taskInstanceImpl);
				
			}
			else{
				throw new FixFlowException("任务 "+taskId+" 不存在！");
			}
			
		}
		else{
			
			if(processDefinitionKey==null||processDefinitionKey.equals("")){
				throw new FixFlowException("processDefinitionKey 不能为空!");
			}
			
			
			ProcessEngine processEngine =ProcessEngineManagement.getDefaultProcessEngine();
			RuntimeService runtimeService = processEngine.getRuntimeService();
			// runtimeService.getCommandExecutor().setConnection(dbgr.getConnection());

			StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
			startProcessInstanceCommand.setProcessDefinitionKey(processDefinitionKey);
			startProcessInstanceCommand.setBusinessKey(businessKey);
			startProcessInstanceCommand.setStartAuthor(Authentication.getAuthenticatedUserId());
			startProcessInstanceCommand.setInitiator(Authentication.getAuthenticatedUserId());
			startProcessInstanceCommand.setTransientVariables(transientVariables);
			// startProcessInstanceCommand.setVariables(Variables);
			ProcessInstance processInstanceQueryTo = runtimeService
					.noneStartProcessInstance(startProcessInstanceCommand);

			// 任务第一步提交完还需找到一个待办事宜再执行掉才算真正完成
			String processInstanceId = processInstanceQueryTo.getId();

			TaskService taskService = processEngine.getTaskService();
			TaskQuery taskQuery = taskService.createTaskQuery()
					.processInstanceId(processInstanceId);

			//先去找独占任务没有的话就去找共享任务并完成他
			List<TaskInstance> taskQueryList = taskQuery.taskAssignee(Authentication.getAuthenticatedUserId()).taskNotEnd()
					.list();

			
			if(taskQueryList.size()>0){
				TaskInstanceEntity taskInstance=(TaskInstanceEntity)taskQueryList.get(0);
				taskInstance.setDraft(true);
				if(this.agent!=null&&!this.agent.equals("")){
					taskInstance.setAgent(Authentication.getAuthenticatedUserId());
					taskInstance.setAssigneeWithoutCascade(this.agent);
				}else{
					taskInstance.setAssigneeWithoutCascade(Authentication.getAuthenticatedUserId());
					taskInstance.setAgent(null);
				}
				//taskInstance.setAssignee(Authentication.getAuthenticatedUserId());
				commandContext.getTaskManager().saveTaskInstanceEntity(taskInstance);
			}
			else{
				TaskQuery taskQueryNew=taskService.createTaskQuery().processInstanceId(processInstanceId);
				List<TaskInstance> taskQueryCandidateList = taskQueryNew.taskCandidateUser(Authentication.getAuthenticatedUserId()).taskNotEnd().list();
				if(taskQueryCandidateList.size()>0){
					TaskInstanceEntity taskInstanceCandidate=(TaskInstanceEntity)taskQueryCandidateList.get(0);
					taskInstanceCandidate.setDraft(true);
					if(this.agent!=null&&!this.agent.equals("")){
						taskInstanceCandidate.setAgent(Authentication.getAuthenticatedUserId());
						taskInstanceCandidate.setAssigneeWithoutCascade(this.agent);
					}else{
						taskInstanceCandidate.setAssigneeWithoutCascade(Authentication.getAuthenticatedUserId());
						taskInstanceCandidate.setAgent(null);
					}
					//taskInstanceCandidate.setAssignee(Authentication.getAuthenticatedUserId());
					commandContext.getTaskManager().saveTaskInstanceEntity(taskInstanceCandidate);
				}
			}
			
		
			
		
			
			/*
			TaskInstance newTask = TaskInstanceEntity.create();
			newTask.setId(GuidUtil.CreateGuid());
			newTask.setAssignee(Authentication.getAuthenticatedUserId());
			newTask.setCreateTime(new Date());
			newTask.setProcessDefinitionKey(processDefinitionKey);
			ProcessDefinitionManager processDefinitionManager = commandContext.getProcessDefinitionManager();

			ProcessDefinitionBehavior processDefinition = processDefinitionManager.findLatestProcessDefinitionByKey(this.processDefinitionKey);

			UserTask userTask=null;
			Object flowNodeObject = processDefinition.getSubTask();
			if (flowNodeObject != null && flowNodeObject instanceof UserTask) {
				userTask=(UserTask)flowNodeObject;
				newTask.setNodeId(userTask.getId());
				
			}
			
			newTask.setDraft(true);
			
			newTask.setFormUri(formUri);
			
			newTask.setPriority(50);
			
			newTask.setProcessDefinitionId(processDefinition.getProcessDefinitionId());
	
			newTask.setProcessDefinitionName(processDefinition.getName());
			newTask.setTaskInstanceType(TaskInstanceType.FIXFLOWTASK);
			newTask.setBizKey(this.businessKey);
			
			if(newTask==null||newTask.getId()==null||newTask.getId().equals("")){
				throw new FixFlowException("taskId不能为空!");
			}
			
			commandContext.getTaskManager().saveTaskInstanceEntity((TaskInstanceEntity)newTask);*/
		}

	
		return null;


	}

}
