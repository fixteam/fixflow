package com.founder.fix.fixflow.core.impl.cmd;

import java.util.Map;


import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.command.TransferTaskCommand;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.ClockUtil;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class TransferTaskCmd implements Command<Void> {

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

	/**
	 * 转发的用户编号
	 */
	protected String transferUserId;

	protected TransferTaskCommand transferTaskCommand;

	public TransferTaskCmd(TransferTaskCommand transferTaskCommand) {
		String taskId = transferTaskCommand.getTaskId();
		String userCommandId = transferTaskCommand.getUserCommandId();
		Map<String, Object> transientVariables = transferTaskCommand.getTransientVariables();
		Map<String, Object> variables = transferTaskCommand.getVariables();
		String taskComment = transferTaskCommand.getTaskComment();
		String transferUserId = transferTaskCommand.getTransferUserId();

		this.taskId = taskId;
		this.userCommandId = userCommandId;
		this.transientVariables = transientVariables;
		this.variables = variables;
		this.taskComment = taskComment;
		this.transferUserId = transferUserId;
		this.transferTaskCommand = transferTaskCommand;
	}

	public Void execute(CommandContext commandContext) {

		if (taskId == null) {
			throw new FixFlowException("任务编号为空！");
		}

		TaskInstanceEntity taskInstance = Context.getCommandContext().getTaskManager().findTaskById(taskId);

		if (taskInstance == null) {
			throw new FixFlowException("无法找到编号为: " + taskId + " 的任务!");
		}
		if (transferUserId != null) {
			if (taskInstance.getAssignee() == null) {

				throw new FixFlowException("任务 " + taskId + " 无代理人!");

			} else {

				String nodeId = taskInstance.getNodeId();
				String processDefinitionId = taskInstance.getProcessDefinitionId();

				ProcessDefinitionManager processDefinitionManager = commandContext.getProcessDefinitionManager();

				ProcessDefinitionBehavior processDefinition = processDefinitionManager.findLatestProcessDefinitionById(processDefinitionId);

				UserTaskBehavior userTask = (UserTaskBehavior) processDefinition.getDefinitions().getElement(nodeId);

				TaskCommandInst taskCommand = userTask.getTaskCommandsMap().get(userCommandId);

				taskInstance.setEndTimeWithoutCascade(ClockUtil.getCurrentTime());
				taskInstance.setCommandType(StringUtil.getString(taskCommand.getTaskCommandType()));
				taskInstance.setCommandMessage(taskCommand.getName());
				taskInstance.setTaskComment(taskComment);

				Context.getCommandContext().getTaskManager().saveTaskInstanceEntity(taskInstance);

				taskInstance.setIdWithoutCascade(GuidUtil.CreateGuid());
				taskInstance.setAssigneeWithoutCascade(transferUserId);
				taskInstance.setCreateTimeWithoutCascade(ClockUtil.getCurrentTime());
				taskInstance.setEndTimeWithoutCascade(null);
				taskInstance.setCommandType(null);
				taskInstance.setCommandMessage(null);
				taskInstance.setTaskComment(null);
				Context.getCommandContext().getTaskManager().saveTaskInstanceEntity(taskInstance);
			}
		} else {
			throw new FixFlowException("转发人不能为空!");
		}

		return null;

	}

}
