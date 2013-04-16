package com.founder.fix.fixflow.core.impl.cmd;

import java.util.ArrayList;
import java.util.List;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.util.CoreUtil;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskInstanceType;

public class GetTaskCommandByTaskInstanceCmd  implements Command<List<TaskCommandInst>>{
	
	protected TaskInstance taskInstance;
	
	public GetTaskCommandByTaskInstanceCmd(TaskInstance taskInstance){
		this.taskInstance=taskInstance;
	}
	
	
	public List<TaskCommandInst> execute(CommandContext commandContext) {
		// TODO 自动生成的方法存根
		List<TaskCommandInst> taskCommandInsts=new ArrayList<TaskCommandInst>();
		
		
		
		if(taskInstance!=null){
			if(taskInstance.getTaskInstanceType()==TaskInstanceType.FIXFLOWTASK||taskInstance.getTaskInstanceType()==TaskInstanceType.FIXNOTICETASK){
				taskCommandInsts= CoreUtil.getTaskCommandInst(taskInstance);
			}
		}
		
	
		
		return taskCommandInsts;
	}
}
