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
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.command.GeneralTaskCommand;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;
import com.founder.fix.fixflow.core.impl.persistence.ProcessInstanceManager;
import com.founder.fix.fixflow.core.impl.persistence.TaskManager;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.task.TaskInstance;

public class CompleteGeneralTaskSimulationRunCmd implements
		Command<ProcessInstance> {

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

	public CompleteGeneralTaskSimulationRunCmd(
			GeneralTaskCommand generalTaskCommand) {

		String taskId = generalTaskCommand.getTaskId();
		String userCommandId = generalTaskCommand.getUserCommandId();
		Map<String, Object> transientVariables = generalTaskCommand
				.getTransientVariables();
		Map<String, Object> variables = generalTaskCommand.getVariables();
		String taskComment = generalTaskCommand.getTaskComment();
		// String businessKey=generalTaskCommand.getBusinessKey();

		this.taskId = taskId;
		this.userCommandId = userCommandId;
		this.transientVariables = transientVariables;
		this.variables = variables;
		this.taskComment = taskComment;

	}

	public ProcessInstance execute(CommandContext commandContext) {

		TaskManager taskManager = commandContext.getTaskManager();

		TaskInstance taskInstanceQuery = taskManager.findTaskById(taskId);

		String tokenId = taskInstanceQuery.getTokenId();
		String nodeId = taskInstanceQuery.getNodeId();
		String processDefinitionId = taskInstanceQuery.getProcessDefinitionId();
		ProcessInstanceManager processInstanceManager = commandContext
				.getProcessInstanceManager();

		String processInstanceId = taskInstanceQuery.getProcessInstanceId();

		ProcessDefinitionManager processDefinitionManager = commandContext
				.getProcessDefinitionManager();

		ProcessDefinitionBehavior processDefinition = processDefinitionManager
				.findLatestProcessDefinitionById(processDefinitionId);

		UserTaskBehavior userTask = (UserTaskBehavior) processDefinition
				.getDefinitions().getElement(nodeId);

		TaskCommandInst taskCommand = userTask.getTaskCommandsMap().get(
				userCommandId);

		ProcessInstanceEntity processInstanceImpl = processInstanceManager
				.findProcessInstanceById(processInstanceId, processDefinition);

		processInstanceImpl.getContextInstance().addTransientVariable(
				"fixVariable_userCommand", userCommandId);

		processInstanceImpl.getContextInstance().setVariableMap(variables);

		// processInstanceImpl.setBizKey(this.businessKey);

		TokenEntity token = processInstanceImpl.getTokenMap().get(tokenId);

		processInstanceImpl.getContextInstance().setTransientVariableMap(
				transientVariables);

		ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE
				.createExecutionContext(token);

		if (taskCommand != null && taskCommand.getExpression() != null) {
			try {

				ExpressionMgmt.execute(taskCommand.getExpression(),
						executionContext);
			} catch (Exception e) {
				throw new FixFlowException("用户命令表达式执行异常!", e);
			}
		}

		List<TaskInstanceEntity> taskInstances = processInstanceImpl
				.getTaskMgmtInstance().getTaskInstanceEntitys();

		for (TaskInstanceEntity taskInstance : taskInstances) {
			if (taskInstance.getId().equals(taskId)) {

				TaskInstanceEntity taskInstanceImpl = taskInstance;
				taskInstanceImpl.end();
				if (taskCommand != null) {
					taskInstanceImpl.setCommandId(taskCommand.getId());
					taskInstanceImpl.setCommandType(StringUtil
							.getString(taskCommand.getTaskCommandType()));

					taskInstanceImpl.setCommandMessage(taskCommand.getName());
				}

				taskInstanceImpl.setTaskComment(this.taskComment);

			}
		}

		return processInstanceImpl;
	}

}
