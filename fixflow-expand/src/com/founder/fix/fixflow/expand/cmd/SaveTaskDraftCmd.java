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
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.cmd.AbstractExpandTaskCmd;
import com.founder.fix.fixflow.core.impl.command.StartProcessInstanceCommand;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
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
			


			// 初始化任务命令执行所需要的常用对象
			loadProcessParameter(commandContext);

			// 将外部变量注册到流程实例运行环境中
			addVariable();

			// 执行处理命令中的开发人员设置的表达式
			runCommandExpression();

			// 获取正在操作的任务实例对象
			TaskInstanceEntity taskInstance = getTaskInstanceEntity();
			
			ProcessInstanceEntity processInstanceImpl=getProcessInstance();
			
			
			
			//在第一次启动的时候没有bizkey 的时候,在保存草稿的时候去设置bizkey
			if(processInstanceImpl.getBizKey()==null&&this.businessKey!=null){
				processInstanceImpl.setBizKey(this.businessKey);
			}
			
			ProcessDefinitionBehavior processDefinition=processInstanceImpl.getProcessDefinition();
			
			
			if(taskInstance!=null){
				
				if(taskInstance.getBizKey()==null||taskInstance.equals("")){
					taskInstance.setBizKey(this.businessKey);
				}
				
				
				taskInstance.setDraft(true);
				
				TaskDefinition taskDefinition=taskInstance.getTaskDefinition();
				
				if (taskDefinition != null && taskDefinition.getDescriptionExpression() != null) {

					Object result = ExpressionMgmt.execute(taskDefinition.getDescriptionExpression(), getExecutionContext());
					if (result != null) {
						taskInstance.setDescription(result.toString());
					} else {
						taskInstance.setDescription(taskInstance.getToken().getFlowNode().getName());
					}
				} else {
					
					if (processDefinition.getTaskSubject() != null && processDefinition.getTaskSubject().getExpressionValue() != null) {

						Object result = ExpressionMgmt.execute(processInstanceImpl.getProcessDefinition().getTaskSubject().getExpressionValue(),
								getExecutionContext());

						if (result != null) {
							taskInstance.setDescription(result.toString());
						}
					} else {
						taskInstance.setDescription(taskInstance.getToken().getFlowNode().getName());
					}
					
					
				}
				
				
				try {
					saveProcessInstance(commandContext);
				} catch (Exception e) {
					throw new FixFlowException("任务 "+taskId+" 保存出错！");
				}
				
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

			TaskInstanceEntity taskInstanceNew=null;
			if(taskQueryList.size()>0){
				taskInstanceNew=(TaskInstanceEntity)taskQueryList.get(0);
				taskInstanceNew.setDraft(true);
				if(this.agent!=null&&!this.agent.equals("")){
					taskInstanceNew.setAgent(Authentication.getAuthenticatedUserId());
					taskInstanceNew.setAssigneeWithoutCascade(this.agent);
				}else{
					taskInstanceNew.setAssigneeWithoutCascade(Authentication.getAuthenticatedUserId());
					taskInstanceNew.setAgent(null);
				}
				//taskInstance.setAssignee(Authentication.getAuthenticatedUserId());
				commandContext.getTaskManager().saveTaskInstanceEntity(taskInstanceNew);
			}
			else{
				TaskQuery taskQueryNew=taskService.createTaskQuery().processInstanceId(processInstanceId);
				List<TaskInstance> taskQueryCandidateList = taskQueryNew.taskCandidateUser(Authentication.getAuthenticatedUserId()).taskNotEnd().list();
				if(taskQueryCandidateList.size()>0){
					taskInstanceNew=(TaskInstanceEntity)taskQueryCandidateList.get(0);
					taskInstanceNew.setDraft(true);
					if(this.agent!=null&&!this.agent.equals("")){
						taskInstanceNew.setAgent(Authentication.getAuthenticatedUserId());
						taskInstanceNew.setAssigneeWithoutCascade(this.agent);
					}else{
						taskInstanceNew.setAssigneeWithoutCascade(Authentication.getAuthenticatedUserId());
						taskInstanceNew.setAgent(null);
					}
					//taskInstanceCandidate.setAssignee(Authentication.getAuthenticatedUserId());
					commandContext.getTaskManager().saveTaskInstanceEntity(taskInstanceNew);
				}
			}
			
			
			
			if(taskInstanceNew!=null){
				this.taskId=taskInstanceNew.getId();
				
				//初始化任务命令执行所需要的常用对象
				loadProcessParameter(commandContext);
				
				//将外部变量注册到流程实例运行环境中
				addVariable();
				
				//执行处理命令中的开发人员设置的表达式
				runCommandExpression();
				
				//持久化流程实例
				saveProcessInstance(commandContext);
				
			}
		
		}

	
		return null;


	}

}
