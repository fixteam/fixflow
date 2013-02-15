package com.founder.fix.fixflow.core.impl.cmd;

import java.util.ArrayList;
import java.util.List;


import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.CoreUtil;
import com.founder.fix.fixflow.core.task.TaskInstanceType;

public class GetTaskCommandByTaskIdCmd implements Command<List<TaskCommandInst>>{

	protected String taskId;
	
	public GetTaskCommandByTaskIdCmd(String taskId){
		this.taskId=taskId;
	}
	
	
	public List<TaskCommandInst> execute(CommandContext commandContext) {
		// TODO 自动生成的方法存根
		List<TaskCommandInst> taskCommandInsts=new ArrayList<TaskCommandInst>();
		
		
		TaskInstanceEntity taskInstance=commandContext.getTaskManager().findTaskById(taskId);
		if(taskInstance!=null){
			if(taskInstance.getTaskInstanceType()==TaskInstanceType.FIXFLOWTASK){
				taskCommandInsts= CoreUtil.getTaskCommandInst(taskInstance);
			}
			
		}
		
	
		
		return taskCommandInsts;
	}

}
