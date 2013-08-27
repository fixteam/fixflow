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
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.expand.command.TransferTaskCommand;

public class TransferTaskCmd  extends AbstractExpandTaskCmd<TransferTaskCommand, Void>{
	/**
	 * 转发的用户编号
	 */
	protected String transferUserId;
	
	
	public TransferTaskCmd(TransferTaskCommand transferTaskCommand) {
		
		super(transferTaskCommand);
		this.transferUserId=transferTaskCommand.getTransferUserId();

	}

	public Void execute(CommandContext commandContext) {
		
		if (taskId == null||taskId.equals("")) {
			throw new FixFlowException("任务编号为空！");
		}

		TaskInstanceEntity taskInstance = Context.getCommandContext().getTaskManager().findTaskById(taskId);

		if (taskInstance == null) {
			throw new FixFlowException("无法找到编号为: " + taskId + " 的任务!");
		}
		if (transferUserId != null) {
			
			if(AbstractCommandFilter.isAutoClaim()){
				taskInstance.setAssigneeWithoutCascade(Authentication.getAuthenticatedUserId());
			}
			if (taskInstance.getAssignee() == null) {

				
				
				
				
				throw new FixFlowException("任务 " + taskId + " 无代理人!");

			} else {

				String nodeId = taskInstance.getNodeId();
				String processDefinitionId = taskInstance.getProcessDefinitionId();

				ProcessDefinitionManager processDefinitionManager = commandContext.getProcessDefinitionManager();

				ProcessDefinitionBehavior processDefinition = processDefinitionManager.findLatestProcessDefinitionById(processDefinitionId);

				UserTaskBehavior userTask = (UserTaskBehavior) processDefinition.getDefinitions().getElement(nodeId);
				
				TaskCommandInst taskCommand=null;
				
				String taskCommandType = expandTaskCommand.getCommandType();
				
				if (StringUtil.isNotEmpty(this.admin) && StringUtil.isEmpty(this.userCommandId) && StringUtil.isNotEmpty(taskCommandType)) {

					String taskCommandName = commandContext.getProcessEngineConfigurationImpl().getTaskCommandDefMap().get(taskCommandType).getName();

					taskCommand = new TaskCommandInst(taskCommandType, taskCommandName, null, taskCommandType, true);

				} else {
					taskCommand = userTask.getTaskCommandsMap().get(this.userCommandId);
				}
				
				/*
				if(this.agent!=null&&!this.agent.equals("")){
					taskInstance.setAgent(Authentication.getAuthenticatedUserId());
					taskInstance.setAssigneeWithoutCascade(this.agent);
				}else{
					taskInstance.setAssigneeWithoutCascade(Authentication.getAuthenticatedUserId());
					taskInstance.setAgent(null);
				}*/
			
				taskInstance.customEnd(taskCommand, taskComment, this.agent, this.admin);

				//taskInstance.setCommandType(StringUtil.getString(taskCommand.getTaskCommandType()));
				//taskInstance.setCommandMessage(taskCommand.getName());
				
				
				Context.getCommandContext().getTaskManager().saveTaskInstanceEntity(taskInstance);

				taskInstance.setIdWithoutCascade(GuidUtil.CreateGuid());
				taskInstance.setAssigneeWithoutCascade(transferUserId);
				taskInstance.setCreateTimeWithoutCascade(ClockUtil.getCurrentTime());
				taskInstance.setEndTimeWithoutCascade(null);
				taskInstance.setCommandId(null);
				taskInstance.setCommandType(null);
				taskInstance.setCommandMessage(null);
				taskInstance.setTaskComment(null);
				taskInstance.setAgent(null);
				taskInstance.setAdmin(null);
				Context.getCommandContext().getTaskManager().saveTaskInstanceEntity(taskInstance);
			}
		} else {
			throw new FixFlowException("转发人不能为空!");
		}

		return null;
	}

	
}
