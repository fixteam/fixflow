package com.founder.fix.fixflow.expand.cmd;


import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.cmd.AbstractExpandTaskCmd;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessInstanceManager;
import com.founder.fix.fixflow.core.impl.persistence.TaskManager;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.expand.command.DeleteProcessInstanceCommand;

public class DeleteProcessInstanceCmd extends AbstractExpandTaskCmd<DeleteProcessInstanceCommand, Void>{
	
	public DeleteProcessInstanceCmd(DeleteProcessInstanceCommand deleteProcessInstanceCommand) {
		super(deleteProcessInstanceCommand);
	}

	public Void execute(CommandContext commandContext) {

		
		if (taskId == null||taskId.equals("")) {
			throw new FixFlowException("任务编号为空！");
		}
		if(this.userCommandId==null||this.userCommandId.equals("")){
			throw new FixFlowException("未点击任务处理按钮,任务处理失败。");
		}
		TaskManager taskManager = commandContext.getTaskManager();
		
		ProcessInstanceManager processInstanceManager=commandContext.getProcessInstanceManager();

		TaskInstance taskInstanceQuery = taskManager.findTaskById(taskId);

		String processInstanceId=taskInstanceQuery.getProcessInstanceId();
		
		processInstanceManager.deleteProcessInstance(processInstanceId, true);
		
		

		
		
		
		
		throw new FixFlowException("未在删除流程实例(DeleteProcessInstanceCmd)方法内设置业务数据的清空方式!请检查该类,如不需要删除业务数据,请注释掉该句话。");
		
		//return null;
		
	}

}
