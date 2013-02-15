package com.founder.fix.fixflow.expand.cmd;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.cmd.AbstractExpandTaskCmd;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.expand.command.SuspendTaskCommand;

/**
 * @author kenshin
 */
public class SuspendTaskCmd extends AbstractExpandTaskCmd<SuspendTaskCommand, Void> {

	public SuspendTaskCmd(SuspendTaskCommand suspendTaskCommand) {
		super(suspendTaskCommand);

	}

	public Void execute(CommandContext commandContext) {
		if (taskId == null || taskId.equals("")) {
			throw new FixFlowException("任务编号为空！");
		}

		TaskInstanceEntity task = Context.getCommandContext().getTaskManager().findTaskById(taskId);
		
	

		
		

		if (task == null) {
			throw new FixFlowException("无法找到编号为: " + taskId + " 的任务!");
		}
		
		String processInstanceId=task.getProcessInstanceId();
		ProcessInstanceEntity  processInstance =Context.getCommandContext().getProcessInstanceManager().findProcessInstanceById(processInstanceId);
		
		String tokenId=task.getTokenId();
		TokenEntity token=processInstance.getTokenMap().get(tokenId);
		token.suspend();
		try {
			Context.getCommandContext().getProcessInstanceManager().saveProcessInstance(processInstance);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new FixFlowException("暂停任务时出现错误!", e);
			
		}
		return null;
	}

}
