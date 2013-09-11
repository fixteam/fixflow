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
import com.founder.fix.fixflow.core.task.DelegationState;
import com.founder.fix.fixflow.expand.command.PendingTaskCommand;

public class PendingTaskCmd extends AbstractExpandTaskCmd<PendingTaskCommand, Void> {

	/**
	 * 转办的用户编号
	 */
	protected String pendingUserId;

	public PendingTaskCmd(PendingTaskCommand pendingTaskCommand) {
		super(pendingTaskCommand);
		this.pendingUserId = pendingTaskCommand.getPendingUserId();
	}

	public Void execute(CommandContext commandContext) {

		if (pendingUserId == null || pendingUserId.equals("")) {
			throw new FixFlowException("选择用户不能为空！");
		}

		// 初始化任务命令执行所需要的常用对象
		loadProcessParameter(commandContext);

		// 将外部变量注册到流程实例运行环境中
		addVariable();

		// 执行处理命令中的开发人员设置的表达式
		runCommandExpression();

		// 获取当前正在操作的任务对象
		TaskInstanceEntity taskInstance = getTaskInstanceEntity();

		// 获取当前正在操作的任务命令
		TaskCommandInst taskCommand = getTaskCommandInst();

		// 结束当前任务,但是不驱动令牌继续向下
		taskInstance.customEnd(taskCommand, taskComment);

		// 获取当前任务的处理者
		String assigneeId = taskInstance.getAssignee();

		// 将当前任务的设置为转办状态
		taskInstance.setDelegationState(DelegationState.PENDING);

		// 拷贝出一个新的任务
		TaskInstanceEntity taskInstanceNew = taskInstance.clone();
		// 设置新任务的GUID
		taskInstanceNew.setIdWithoutCascade(GuidUtil.CreateGuid());
		// 将新任务的处理者设置为需要转办的人
		taskInstanceNew.setAssigneeWithoutCascade(pendingUserId);
		// 重置任务的创建时间
		taskInstanceNew.setCreateTimeWithoutCascade(ClockUtil.getCurrentTime());
		// 设置任务的原始拥有者,以便在还回的时候找到原始处理者
		taskInstanceNew.setOwner(assigneeId);
		// 将任务设置为还回状态
		taskInstanceNew.setDelegationState(DelegationState.RESOLVED);
		// 将新任务的结束时间设置为空
		taskInstanceNew.setEndTimeWithoutCascade(null);
		// 将新任务的任务命令的编号设置为空
		taskInstanceNew.setCommandId(null);
		// 将新任务的任务命令类型设置为空
		taskInstanceNew.setCommandType(null);
		// 将新任务的任务命令文本设置为空
		taskInstanceNew.setCommandMessage(null);
		// 将新任务的意见设置为空
		taskInstanceNew.setTaskComment(null);
		// 将新任务的代理人设置为空
		taskInstanceNew.setAgent(null);
		// 将新任务的管理员设置为空
		taskInstanceNew.setAdmin(null);

		// 获取当前正在操作的流程实例对象
		ProcessInstanceEntity processInstance = getProcessInstance();
		// 将新创建的出的任务插入任务管理器中
		processInstance.getTaskMgmtInstance().addTaskInstanceEntity(taskInstanceNew);
		// 获取流程上下文
		ExecutionContext executionContext = getExecutionContext();
		// 触发节点的任务分配事件
		((FlowNodeImpl) executionContext.getToken().getFlowNode()).fireEvent(BaseElementEvent.EVENTTYPE_TASK_ASSIGN, executionContext, taskInstanceNew);

		
		saveProcessInstance(commandContext);
		
		return null;
	}

}
