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
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.cmd.AbstractExpandTaskCmd;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.ClockUtil;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.expand.command.TransferTaskCommand;

public class TransferTaskCmd extends AbstractExpandTaskCmd<TransferTaskCommand, Void> {
	/**
	 * 转发的用户编号
	 */
	protected String transferUserId;

	public TransferTaskCmd(TransferTaskCommand transferTaskCommand) {

		super(transferTaskCommand);
		this.transferUserId = transferTaskCommand.getTransferUserId();

	}

	public Void execute(CommandContext commandContext) {

		if (transferUserId != null) {

			// 初始化任务命令执行所需要的常用对象
			loadProcessParameter(commandContext);

			// 将外部变量注册到流程实例运行环境中
			addVariable();

			// 执行处理命令中的开发人员设置的表达式
			runCommandExpression();

			// 获取正在操作的任务实例对象
			TaskInstanceEntity taskInstance = getTaskInstanceEntity();

			// 获取正在操作的任务命令对象实例
			TaskCommandInst taskCommand = getTaskCommandInst();

			taskInstance.customEnd(taskCommand, taskComment);

			// 拷贝出一个新的任务
			TaskInstanceEntity taskInstanceNew = taskInstance.clone();

			taskInstanceNew.setIdWithoutCascade(GuidUtil.CreateGuid());
			taskInstanceNew.setAssigneeWithoutCascade(transferUserId);
			taskInstanceNew.setCreateTimeWithoutCascade(ClockUtil.getCurrentTime());
			taskInstanceNew.setEndTimeWithoutCascade(null);
			taskInstanceNew.setCommandId(null);
			taskInstanceNew.setCommandType(null);
			taskInstanceNew.setCommandMessage(null);
			taskInstanceNew.setTaskComment(null);
			taskInstanceNew.setAgent(null);
			taskInstanceNew.setAdmin(null);
			taskInstanceNew.setDraft(false);

			// 获取当前正在操作的流程实例对象
			ProcessInstanceEntity processInstance = getProcessInstance();
			// 将新创建的出的任务插入任务管理器中
			processInstance.getTaskMgmtInstance().addTaskInstanceEntity(taskInstanceNew);
			// 获取流程上下文
			ExecutionContext executionContext = getExecutionContext();
			// 触发节点的任务分配事件
			((FlowNodeImpl) executionContext.getToken().getFlowNode()).fireEvent(BaseElementEvent.EVENTTYPE_TASK_ASSIGN, executionContext, taskInstanceNew);

			saveProcessInstance(commandContext);

		} else {
			throw new FixFlowException("转发人不能为空!");
		}

		return null;
	}

}
