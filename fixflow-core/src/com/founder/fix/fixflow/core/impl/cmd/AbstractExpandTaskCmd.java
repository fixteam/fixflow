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

import java.util.Map;

import com.founder.fix.fixflow.core.exception.FixFlowBizException;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.command.AbstractCustomExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessInstanceManager;
import com.founder.fix.fixflow.core.impl.persistence.TaskManager;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
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
	 * 加载流程执行上下文对象
	 */
	protected ExecutionContext loadExecutionContext(CommandContext commandContext) {

		if (this.taskId == null || this.taskId.equals("")) {
			throw new FixFlowException("任务编号为空！");
		}

		// 获取任务管理器
		TaskManager taskManager = commandContext.getTaskManager();
		
		// 获取任务管理器
		ProcessInstanceManager processInstanceManager = commandContext.getProcessInstanceManager();

		// 根据指定id查询出任务的TO 不能做改变操作
		TaskInstance taskInstanceQuery = taskManager.findTaskById(taskId);

		if (taskInstanceQuery==null) {
			throw new FixFlowBizException("未查询到指定的任务");
		}
		
		String processInstanceId = taskInstanceQuery.getProcessInstanceId();
		
		String tokenId= taskInstanceQuery.getTokenId();
		
		ProcessInstanceEntity processInstance =processInstanceManager.findProcessInstanceById(processInstanceId);
		
		
		TokenEntity tokenEntity=processInstance.getTokenMap().get(tokenId);
		
		//执行按钮表达式
		ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(tokenEntity);
		

		return executionContext;
	}
}
