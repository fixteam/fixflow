package com.founder.fix.fixflow.expand.cmd;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.cmd.AbstractExpandTaskCmd;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.expand.command.ReleaseTaskCommand;


public class ReleaseTaskCmd extends AbstractExpandTaskCmd<ReleaseTaskCommand, Void>{

	public ReleaseTaskCmd(ReleaseTaskCommand abstractCustomExpandTaskCommand) {
		super(abstractCustomExpandTaskCommand);
	}

	public Void execute(CommandContext commandContext) {

		if (taskId == null||taskId.equals("")) {
			throw new FixFlowException("任务编号为空！");
		}

		TaskInstanceEntity task = Context.getCommandContext().getTaskManager()
				.findTaskById(taskId);

		if (task == null) {
			throw new FixFlowException("无法找到编号为: " + taskId + " 的任务!");
		}
		if (Authentication.getAuthenticatedUserId() != null) {
			if (task.getAssignee() != null) {
				if (!task.getAssignee().equals(Authentication.getAuthenticatedUserId())) {
					// 当任务已经被另一个不是自己的用户占有，则抛出异常。
					throw new FixFlowException("任务 " + taskId + " 已经被另一个用户领取!您不能做还回操作!");
				}
				else{
					task.setAssignee(null);
					task.setClaimTime(null);
					Context.getCommandContext().getTaskManager()
							.saveTaskInstanceEntity(task);
				}
			} else {
				throw new FixFlowException("任务 " + taskId + " 没有被领取,所以不能做还回操作!");

			}
		} 

		return null;
	}

}
