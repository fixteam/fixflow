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

import org.eclipse.bpmn2.UserTask;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.cmd.AbstractExpandTaskCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetRollBackTaskCmd;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.expand.command.RollBackTaskByExpressionCommand;

public class RollBackTaskByExpressionCmd extends AbstractExpandTaskCmd<RollBackTaskByExpressionCommand, Void> {

	public RollBackTaskByExpressionCmd(RollBackTaskByExpressionCommand rollBackTaskByTaskIdCommand) {
		super(rollBackTaskByTaskIdCommand);
		// TODO Auto-generated constructor stub
	}

	public Void execute(CommandContext commandContext) {

		// 初始化任务命令执行所需要的常用对象
		loadProcessParameter(commandContext);

		// 将外部变量注册到流程实例运行环境中
		addVariable();

		// 执行处理命令中的开发人员设置的表达式
		//runCommandExpression();

		// 获取正在操作的任务实例对象
		TaskInstanceEntity taskInstance = getTaskInstanceEntity();

		// 获取正在操作的任务命令对象实例
		TaskCommandInst taskCommand = getTaskCommandInst();
		

		List<TaskInstance> taskInstanceQueryTos = (new GetRollBackTaskCmd(this.taskId)).execute(commandContext);
		
		UserTask userTask=(UserTask)taskInstance.getToken().getFlowNode();
		

		
		ExecutionContext executionContext = getExecutionContext();

		Object expressionValue = null;
		if (taskCommand != null && taskCommand.getExpression() != null) {
			try {

				expressionValue = ExpressionMgmt.execute(taskCommand.getExpression(), executionContext);

			} catch (Exception e) {
				throw new FixFlowException(taskInstance.getToken().getFlowNode().getName()+ " 节点,用户命令表达式执行异常!", e);
			}
		}
		String rollBackNodeId = null;
		if (expressionValue != null && !expressionValue.equals("")) {
			rollBackNodeId = expressionValue.toString();
		} else {
			throw new FixFlowException("没有在表达式中指定退回节点!");
		}

		TaskInstance taskInstanceQueryRollBack = null;

		for (TaskInstance taskInstanceQueryTo : taskInstanceQueryTos) {
			if (taskInstanceQueryTo.getNodeId().equals(rollBackNodeId)) {
				taskInstanceQueryRollBack = taskInstanceQueryTo;
				break;
			}
		}
		if (taskInstanceQueryRollBack == null) {
			throw new FixFlowException(userTask.getId() + " 节点的退回命令上退回节点设置错误!");
		}

		String taskId = this.taskId;
		String taskComment = this.taskComment;

		
		if (taskInstanceQueryRollBack.getTaskGroup() != null) {

			try {

				UserTaskBehavior backNodeUserTask = (UserTaskBehavior) executionContext.getProcessDefinition().getDefinitions().getElement(rollBackNodeId);

				taskInstance.toFlowNodeEnd(taskCommand, taskComment, backNodeUserTask, null);

			} catch (Exception e) {

				throw new FixFlowException("任务: " + taskId + " 退回失败!", e);
			}

			try {
				saveProcessInstance(commandContext);
			} catch (Exception e) {
				throw new FixFlowException("流程实例持久化失败!", e);
			}
			return null;

		} else {

			try {
				UserTaskBehavior backNodeUserTask = (UserTaskBehavior) executionContext.getProcessDefinition().getDefinitions().getElement(rollBackNodeId);

				taskInstance.toFlowNodeEnd(taskCommand, taskComment, backNodeUserTask, taskInstanceQueryRollBack.getAssignee());

			} catch (Exception e) {

				throw new FixFlowException("任务: " + taskId + " 退回失败!", e);
			}

			try {
				saveProcessInstance(commandContext);
			} catch (Exception e) {
				throw new FixFlowException("流程实例持久化失败!", e);
			}
			return null;

		}

	}

}
