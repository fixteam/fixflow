package com.founder.fix.fixflow.expand.cmd;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.cmd.AbstractExpandTaskCmd;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.expand.command.SuspendProcessInstanceCommand;


/**
 * @author kenshin
 */
public class SuspendProcessInstanceCmd extends AbstractExpandTaskCmd<SuspendProcessInstanceCommand, Void> {

	public SuspendProcessInstanceCmd(SuspendProcessInstanceCommand suspendProcessInstanceCommand) {
		super(suspendProcessInstanceCommand);

	}

	public Void execute(CommandContext commandContext) {
		if (taskId == null || taskId.equals("")) {
			throw new FixFlowException("任务编号为空！");
		}

		TaskInstanceEntity task = Context.getCommandContext().getTaskManager().findTaskById(taskId);
		String processInstanceId=task.getProcessInstanceId();
		ProcessInstanceEntity  processInstance =Context.getCommandContext().getProcessInstanceManager().findProcessInstanceById(processInstanceId);
		processInstance.suspend();
		try {
			Context.getCommandContext().getProcessInstanceManager().saveProcessInstance(processInstance);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new FixFlowException("暂停流程时出现错误!", e);
			
		}
		return null;
	}

}
