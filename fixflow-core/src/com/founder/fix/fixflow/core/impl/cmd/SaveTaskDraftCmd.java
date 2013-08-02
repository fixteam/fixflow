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
package com.founder.fix.fixflow.core.impl.cmd;

import java.util.List;
import java.util.Map;


import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.command.SaveTaskDraftCommand;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;
import com.founder.fix.fixflow.core.impl.persistence.ProcessInstanceManager;
import com.founder.fix.fixflow.core.impl.persistence.TaskManager;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.task.TaskInstance;

public class SaveTaskDraftCmd implements Command<Void> {
 
	/**
	 * 任务编号
	 */
	protected String taskId;

	/**
	 * 用户命令编号
	 */
	protected String userCommandId;

	/**
	 * 瞬态流程实例变量Map
	 */
	protected Map<String, Object> transientVariables = null;

	/**
	 * 持久化流程实例变量Map
	 */
	protected Map<String, Object> variables = null;

	/**
	 * 任务意见
	 */
	protected String taskComment;



	public SaveTaskDraftCmd(SaveTaskDraftCommand saveTaskDraftCommand) {
		String taskId = saveTaskDraftCommand.getTaskId();
		String userCommandId = saveTaskDraftCommand.getUserCommandId();
		Map<String, Object> transientVariables = saveTaskDraftCommand
				.getTransientVariables();
		Map<String, Object> variables = saveTaskDraftCommand.getVariables();
		String taskComment = saveTaskDraftCommand.getTaskComment();

		this.taskId = taskId;
		this.userCommandId = userCommandId;
		this.transientVariables = transientVariables;
		this.variables = variables;
		this.taskComment = taskComment;

	}

	public Void execute(CommandContext commandContext) {

		if (taskId == null) {
			throw new FixFlowException("任务编号为空！");
		}

			
			
			TaskManager taskManager = commandContext.getTaskManager();

			TaskInstance taskInstanceQuery = taskManager.findTaskById(taskId);

			
			String processDefinitionId = taskInstanceQuery.getProcessDefinitionId();
			ProcessInstanceManager processInstanceManager = commandContext.getProcessInstanceManager();

			String processInstanceId = taskInstanceQuery.getProcessInstanceId();

			ProcessDefinitionManager processDefinitionManager = commandContext.getProcessDefinitionManager();

			ProcessDefinitionBehavior processDefinition = processDefinitionManager.findLatestProcessDefinitionById(processDefinitionId);

			
			
			ProcessInstanceEntity processInstanceImpl = processInstanceManager.findProcessInstanceById(processInstanceId, processDefinition);

		

			List<TaskInstanceEntity> taskInstances = processInstanceImpl.getTaskMgmtInstance().getTaskInstanceEntitys();

			for (TaskInstanceEntity taskInstance : taskInstances) {
				if (taskInstance.getId().equals(taskId)) {
				
					
					TaskInstanceEntity taskInstanceImpl= taskInstance;
						taskInstanceImpl.setTaskComment(this.taskComment);
						
				}
			}

			try {
				processInstanceManager.saveProcessInstance(processInstanceImpl);
			} catch (Exception e) {
				throw new FixFlowException("流程实例持久化失败!", e);
			}
			return null;


	}

}
