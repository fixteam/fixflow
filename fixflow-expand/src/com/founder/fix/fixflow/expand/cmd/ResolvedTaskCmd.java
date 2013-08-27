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

import org.eclipse.bpmn2.impl.FlowNodeImpl;

import com.founder.fix.fixflow.core.event.BaseElementEvent;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.cmd.AbstractExpandTaskCmd;
import com.founder.fix.fixflow.core.impl.filter.AbstractCommandFilter;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;
import com.founder.fix.fixflow.core.impl.persistence.ProcessInstanceManager;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.ClockUtil;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.task.DelegationState;
import com.founder.fix.fixflow.expand.command.ResolvedTaskCommand;

public class ResolvedTaskCmd extends AbstractExpandTaskCmd<ResolvedTaskCommand, Void> {

	public ResolvedTaskCmd(ResolvedTaskCommand abstractCustomExpandTaskCommand) {
		super(abstractCustomExpandTaskCommand);
	}

	public Void execute(CommandContext commandContext) {

		if (taskId == null || taskId.equals("")) {
			throw new FixFlowException("任务编号为空！");
		}

		TaskInstanceEntity task = Context.getCommandContext().getTaskManager().findTaskById(taskId);
		if(AbstractCommandFilter.isAutoClaim()){
			task.setAssigneeWithoutCascade(Authentication.getAuthenticatedUserId());
		}
		if (task == null) {
			throw new FixFlowException("无法找到编号为: " + taskId + " 的任务!");
		}
		if (Authentication.getAuthenticatedUserId() != null) {
			String owner = task.getOwner();

			String nodeId = task.getNodeId();
			String processDefinitionId = task.getProcessDefinitionId();

			ProcessDefinitionManager processDefinitionManager = commandContext.getProcessDefinitionManager();

			ProcessDefinitionBehavior processDefinition = processDefinitionManager.findLatestProcessDefinitionById(processDefinitionId);

			UserTaskBehavior userTask = (UserTaskBehavior) processDefinition.getDefinitions().getElement(nodeId);

			TaskCommandInst taskCommand = null;

			String taskCommandType = expandTaskCommand.getCommandType();
			
			if (StringUtil.isNotEmpty(this.admin) && StringUtil.isEmpty(this.userCommandId) && StringUtil.isNotEmpty(taskCommandType)) {

				String taskCommandName = commandContext.getProcessEngineConfigurationImpl().getTaskCommandDefMap().get(taskCommandType).getName();

				taskCommand = new TaskCommandInst(taskCommandType, taskCommandName, null, taskCommandType, true);

			} else {
				taskCommand = userTask.getTaskCommandsMap().get(this.userCommandId);
			}
			
			if(taskCommand==null){
				throw new FixFlowException("未点击任务处理按钮,任务处理失败。");
			}

			
			if(this.agent!=null&&!this.agent.equals("")){
				task.setAgent(Authentication.getAuthenticatedUserId());
				task.setAssigneeWithoutCascade(this.agent);
			}else{
				task.setAssigneeWithoutCascade(Authentication.getAuthenticatedUserId());
				task.setAgent(null);
			}
			
			task.customEnd(taskCommand, taskComment, this.agent, this.admin);

			

			task.setDelegationState(DelegationState.RESOLVED);

			Context.getCommandContext().getTaskManager().saveTaskInstanceEntity(task);

			task.setIdWithoutCascade(GuidUtil.CreateGuid());
			task.setAssigneeWithoutCascade(owner);
			task.setCreateTimeWithoutCascade(ClockUtil.getCurrentTime());

			task.setEndTimeWithoutCascade(null);
			task.setCommandId(null);
			task.setCommandType(null);
			task.setCommandMessage(null);
			task.setTaskComment(null);
			task.setAgent(null);
			task.setAdmin(null);
			task.setDelegationState(null);
			
			if(task.getPendingTaskId()==null){
				
			}
			else{
				TaskInstanceEntity resolvedTask = Context.getCommandContext().getTaskManager().findTaskById(task.getPendingTaskId());

				task.setFormUri(resolvedTask.getFormUri());
			}
			
			
			
			
			Context.getCommandContext().getTaskManager().saveTaskInstanceEntity(task);
			
			
			
			
			
			//转办通知
			//任务分配事件

			
			ProcessInstanceManager processInstanceManager = commandContext.getProcessInstanceManager();

			String processInstanceId = task.getProcessInstanceId();

			ProcessInstanceEntity processInstanceImpl = processInstanceManager.findProcessInstanceById(processInstanceId, processDefinition);

			TokenEntity token=processInstanceImpl.getTokenMap().get(task.getTokenId());

			
			ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(token);
			
			((FlowNodeImpl) token.getFlowNode()).fireEvent(BaseElementEvent.EVENTTYPE_TASK_ASSIGN, executionContext, task);


		} else {
			throw new FixFlowException("无法找到当前处理者");
		}

		return null;
	}

}
