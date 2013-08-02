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

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.cmd.AbstractExpandTaskCmd;
import com.founder.fix.fixflow.core.impl.filter.AbstractCommandFilter;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.ClockUtil;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.task.DelegationState;
import com.founder.fix.fixflow.expand.command.PendingTaskCommand;

public class PendingDifFormTaskCmd extends AbstractExpandTaskCmd<PendingTaskCommand, Void>{

	
	/**
	 * 转办的用户编号
	 */
	protected String pendingTaskId;
	public PendingDifFormTaskCmd(PendingTaskCommand pendingTaskCommand) {
		super(pendingTaskCommand);
		this.pendingTaskId=pendingTaskCommand.getPendingTaskId();
	}

	public Void execute(CommandContext commandContext) {
		if(pendingTaskId== null||pendingTaskId.equals("")){
			throw new FixFlowException("转办的任务不能为空");
		}

		if (taskId == null||taskId.equals("")) {
			throw new FixFlowException("任务编号为空！");
		}

		TaskInstanceEntity task = Context.getCommandContext().getTaskManager()
				.findTaskById(taskId);
		if(AbstractCommandFilter.isAutoClaim()){
			task.setAssigneeWithoutCascade(Authentication.getAuthenticatedUserId());
		}
		
		TaskInstanceEntity pendingTask = Context.getCommandContext().getTaskManager()
				.findTaskById(pendingTaskId);

		if (task == null) {
			throw new FixFlowException("无法找到编号为: " + taskId + " 的任务!");
		}
		if (Authentication.getAuthenticatedUserId() != null) {
			
			
			
			
			
			
			
			
			
			String nodeId = task.getNodeId();
			String processDefinitionId = task.getProcessDefinitionId();

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
			
			
		
			task.customEnd(taskCommand, taskComment, this.agent, this.admin);

			
			
			String assigneeId=task.getAssignee();
			//task.setOwner(assigneeId);
			//task.setAssignee(pendingUserId);
			task.setDelegationState(DelegationState.PENDING);

			
			Context.getCommandContext().getTaskManager().saveTaskInstanceEntity(task);
			task.setPendingTaskId(task.getId());
			task.setIdWithoutCascade(GuidUtil.CreateGuid());
			task.setAssigneeWithoutCascade(pendingTask.getAssignee());
			task.setCreateTimeWithoutCascade(ClockUtil.getCurrentTime());
			task.setOwner(assigneeId);
			task.setDelegationState(DelegationState.RESOLVED);
			task.setEndTimeWithoutCascade(null);
			task.setCommandId(null);
			task.setCommandType(null);
			task.setCommandMessage(null);
			task.setTaskComment(null);
			task.setAgent(null);
			task.setAdmin(null);
			task.setFormUri(pendingTask.getFormUri());
			
			Context.getCommandContext().getTaskManager().saveTaskInstanceEntity(task);
			
		
		}
		else {
			throw new FixFlowException("无法找到当前处理者");
		}

		return null;
	}

}
