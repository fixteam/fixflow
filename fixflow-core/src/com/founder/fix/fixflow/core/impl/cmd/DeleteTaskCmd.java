package com.founder.fix.fixflow.core.impl.cmd;

import java.util.Collection;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;

public class DeleteTaskCmd implements Command<Void> {

	protected Collection<String> taskIds;
	protected String taskId;
	protected boolean cascade;

	public DeleteTaskCmd(String taskId, boolean cascade) {
		this.taskId = taskId;
		this.cascade = cascade;
	}

	public DeleteTaskCmd(Collection<String> taskIds, boolean cascade) {
		this.taskIds = taskIds;
		this.cascade = cascade;
	}

	public Void execute(CommandContext commandContext) {

		if (taskId != null) {
			deleteTask(taskId);
		} else if (taskIds != null) {
			for (String taskId : taskIds) {
				deleteTask(taskId);
			}
		} else {
			throw new FixFlowException("taskId 和 taskIds 不能都为空");
		}

		return null;

	}

	protected void deleteTask(String taskId) {
		Context.getCommandContext().getTaskManager().deleteTaskInstanceByTaskInstanceId(taskId, cascade);
	}

}
