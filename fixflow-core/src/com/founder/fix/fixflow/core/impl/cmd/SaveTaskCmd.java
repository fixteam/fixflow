package com.founder.fix.fixflow.core.impl.cmd;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.task.TaskInstance;

public class SaveTaskCmd implements Command<Void>{

	protected TaskInstanceEntity taskInstanceEntity;
	
	public SaveTaskCmd(TaskInstance taskInstanceEntity){
		this.taskInstanceEntity=(TaskInstanceEntity)taskInstanceEntity;
	}
	
	
	
	public Void execute(CommandContext commandContext) {
		// TODO Auto-generated method stub
		
		if(taskInstanceEntity==null||taskInstanceEntity.getId()==null||taskInstanceEntity.getId().equals("")){
			throw new FixFlowException("taskId不能为空!");
		}
		
		commandContext.getTaskManager().saveTaskInstanceEntity(taskInstanceEntity);
		
		
		return null;
	}

}
