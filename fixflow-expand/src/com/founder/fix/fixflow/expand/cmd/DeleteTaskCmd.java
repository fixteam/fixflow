package com.founder.fix.fixflow.expand.cmd;


import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.cmd.AbstractExpandTaskCmd;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.TaskManager;
import com.founder.fix.fixflow.expand.command.DeleteTaskCommand;

public class DeleteTaskCmd extends AbstractExpandTaskCmd<DeleteTaskCommand, Void>{
	
	public DeleteTaskCmd(DeleteTaskCommand deleteTaskCommand) {
		super(deleteTaskCommand);
	}

	public Void execute(CommandContext commandContext) {

		
		if (taskId == null||taskId.equals("")) {
			throw new FixFlowException("任务编号为空！");
		}

		TaskManager taskManager = commandContext.getTaskManager();
		
		
		taskManager.deleteTaskInstanceByTaskInstanceId(taskId, true);


		return null;
		
	}

}
