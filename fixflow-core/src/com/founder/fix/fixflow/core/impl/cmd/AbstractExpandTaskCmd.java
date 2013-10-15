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

import com.founder.fix.fixflow.core.exception.FixFlowBizException;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.command.AbstractCustomExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.filter.AbstractCommandFilter;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessInstanceManager;
import com.founder.fix.fixflow.core.impl.persistence.TaskManager;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.task.TaskInstance;

public abstract class AbstractExpandTaskCmd<P extends AbstractCustomExpandTaskCommand, T> implements Command<T> {

	/**
	 * 任务编号
	 */
	protected String taskId;

	/**
	 * 任务命令类型
	 */
	protected String commandType;

	/**
	 * 用户命令编号
	 */
	protected String userCommandId;

	/**
	 * 任务意见
	 */
	protected String taskComment;

	/**
	 * 瞬态流程实例变量Map
	 */
	protected Map<String, Object> transientVariables = null;

	/**
	 * 持久化流程实例变量Map
	 */
	protected Map<String, Object> variables = null;

	protected ExpandTaskCommand expandTaskCommand = null;

	protected String businessKey;

	protected String initiator;

	protected String processDefinitionKey;

	protected String agent;

	protected String admin;

	public AbstractExpandTaskCmd(P abstractCustomExpandTaskCommand) {

		this.taskId = abstractCustomExpandTaskCommand.getExpandTaskCommand().getTaskId();
		this.commandType = abstractCustomExpandTaskCommand.getExpandTaskCommand().getCommandType();
		this.userCommandId = abstractCustomExpandTaskCommand.getExpandTaskCommand().getUserCommandId();
		this.taskComment = abstractCustomExpandTaskCommand.getExpandTaskCommand().getTaskComment();
		this.transientVariables = abstractCustomExpandTaskCommand.getExpandTaskCommand().getTransientVariables();
		this.variables = abstractCustomExpandTaskCommand.getExpandTaskCommand().getVariables();
		this.expandTaskCommand = abstractCustomExpandTaskCommand.getExpandTaskCommand();

		this.initiator = abstractCustomExpandTaskCommand.getExpandTaskCommand().getInitiator();
		this.processDefinitionKey = abstractCustomExpandTaskCommand.getExpandTaskCommand().getProcessDefinitionKey();
		this.businessKey = abstractCustomExpandTaskCommand.getExpandTaskCommand().getBusinessKey();
		this.agent = abstractCustomExpandTaskCommand.getExpandTaskCommand().getAgent();
		this.admin = abstractCustomExpandTaskCommand.getExpandTaskCommand().getAdmin();
	}

	/**
	 * 流程执行上下文对象
	 */
	protected ExecutionContext executionContextAbstract;

	/**
	 * 任务处理命名
	 */
	protected TaskCommandInst taskCommandInstAbstract;

	/**
	 * 正在操作的任务的实体
	 */
	protected TaskInstanceEntity taskInstanceEntityAbstract;

	/**
	 * 流程实例
	 */
	protected ProcessInstanceEntity processInstanceAbstract;

	/**
	 * 加载流程相关参数
	 */
	protected void loadProcessParameter(CommandContext commandContext) {

		if (this.taskId == null || this.taskId.equals("")) {
			throw new FixFlowException("任务编号为空！");
		}

		// 获取任务管理器
		TaskManager taskManager = commandContext.getTaskManager();

		// 获取任务管理器
		ProcessInstanceManager processInstanceManager = commandContext.getProcessInstanceManager();

		// 根据指定id查询出任务的TO 不能做改变操作
		TaskInstance taskInstanceQuery = taskManager.findTaskById(taskId);

		if (taskInstanceQuery == null) {
			throw new FixFlowBizException("未查询到指定的任务");
		}

		String processInstanceId = taskInstanceQuery.getProcessInstanceId();

		String tokenId = taskInstanceQuery.getTokenId();

		String nodeId = taskInstanceQuery.getNodeId();

		this.processInstanceAbstract = processInstanceManager.findProcessInstanceById(processInstanceId);

		TokenEntity tokenEntity = this.processInstanceAbstract.getTokenMap().get(tokenId);

		this.executionContextAbstract = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(tokenEntity);

		ProcessDefinitionBehavior processDefinition = this.processInstanceAbstract.getProcessDefinition();

		// 获取任务所在节点对象
		UserTaskBehavior userTask = (UserTaskBehavior) processDefinition.getDefinitions().getElement(nodeId);

		String taskCommandTypeString = expandTaskCommand.getCommandType();

		if (StringUtil.isNotEmpty(this.admin) && StringUtil.isEmpty(this.userCommandId) && StringUtil.isNotEmpty(taskCommandTypeString)) {

			String taskCommandName = commandContext.getProcessEngineConfigurationImpl().getTaskCommandDefMap().get(taskCommandTypeString).getName();

			this.taskCommandInstAbstract = new TaskCommandInst(taskCommandTypeString, taskCommandName, null, taskCommandTypeString, true);

		} else {
			this.taskCommandInstAbstract = userTask.getTaskCommandsMap().get(this.userCommandId);
		}

		// 获取任务管理器
		List<TaskInstanceEntity> taskInstances = this.processInstanceAbstract.getTaskMgmtInstance().getTaskInstanceEntitys();

		for (TaskInstanceEntity taskInstance : taskInstances) {
			if (taskInstance.getId().equals(this.taskId)) {

				this.taskInstanceEntityAbstract = taskInstance;

				break;
			}
		}

		if (AbstractCommandFilter.isAutoClaim()) {
			this.taskInstanceEntityAbstract.setAssigneeWithoutCascade(Authentication.getAuthenticatedUserId());
		}
		if (this.taskInstanceEntityAbstract.getAssignee() == null) {

			if (StringUtil.isNotEmpty(this.admin)) {
				this.taskInstanceEntityAbstract.setAssigneeWithoutCascade(this.admin);
			} else {

				if(this.taskCommandInstAbstract!=null&&this.taskCommandInstAbstract.getTaskCommandDefType()!=null){
					if(!this.taskCommandInstAbstract.getTaskCommandDefType().equals("processInstanceInfo")){
						throw new FixFlowException("任务 " + taskId + " 无代理人!");
					}
				}else{
					throw new FixFlowException("任务 " + taskId + " 无代理人!");
				}
				
			}

		}

		if (StringUtil.isNotEmpty(agent)) {
			this.taskInstanceEntityAbstract.setAgent(Authentication.getAuthenticatedUserId());
			if (StringUtil.isEmpty(this.taskInstanceEntityAbstract.getAssignee())) {
				this.taskInstanceEntityAbstract.setAssigneeWithoutCascade(this.agent);
			}

		} else {
			if (StringUtil.isEmpty(this.taskInstanceEntityAbstract.getAssignee())) {
				this.taskInstanceEntityAbstract.setAssigneeWithoutCascade(Authentication.getAuthenticatedUserId());
			}
			this.taskInstanceEntityAbstract.setAgent(null);
		}

		if (StringUtil.isNotEmpty(admin)) {
			this.taskInstanceEntityAbstract.setAdmin(admin);
		}

		this.executionContextAbstract.setTaskInstance(this.taskInstanceEntityAbstract);

	}

	protected void addVariable() {
		// 放置当前点击的按钮ID
		this.processInstanceAbstract.getContextInstance().addTransientVariable("fixVariable_userCommand", this.userCommandId);
		// 放置持久化变量
		this.processInstanceAbstract.getContextInstance().setVariableMap(this.variables);
		// 放置瞬态变量
		this.processInstanceAbstract.getContextInstance().setTransientVariableMap(this.transientVariables);
	}

	protected void runCommandExpression() {

		this.executionContextAbstract.setTaskInstance(this.taskInstanceEntityAbstract);
		if (this.taskCommandInstAbstract != null && this.taskCommandInstAbstract.getExpression() != null) {
			try {

				ExpressionMgmt.execute(this.taskCommandInstAbstract.getExpression(), this.executionContextAbstract);
			} catch (Exception e) {
				throw new FixFlowException("用户命令表达式执行异常!", e);
			}
		}
	}

	protected void saveProcessInstance(CommandContext commandContext) {
		try {
			commandContext.getProcessInstanceManager().saveProcessInstance(getProcessInstance());
		} catch (Exception e) {
			throw new FixFlowException("流程实例持久化失败!", e);
		}
	}

	public ExecutionContext getExecutionContext() {
		return executionContextAbstract;
	}

	public TaskCommandInst getTaskCommandInst() {
		return taskCommandInstAbstract;
	}

	public TaskInstanceEntity getTaskInstanceEntity() {
		return taskInstanceEntityAbstract;
	}

	public ProcessInstanceEntity getProcessInstance() {
		return processInstanceAbstract;
	}

}
