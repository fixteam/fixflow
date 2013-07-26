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
	
	protected boolean isProcessTracking;
	
	public GetTaskCommandByTaskInstanceCmd(TaskInstance taskInstance,boolean isProcessTracking){
		this.taskInstance=taskInstance;
		this.isProcessTracking=isProcessTracking;
	}
	
	
	public List<TaskCommandInst> execute(CommandContext commandContext) {

		List<TaskCommandInst> taskCommandInsts=new ArrayList<TaskCommandInst>();
		
		
		
		if(isProcessTracking){
			//流程追踪查询
			if(taskInstance!=null){
				if(taskInstance.getTaskInstanceType()==TaskInstanceType.FIXFLOWTASK){
					taskCommandInsts= CoreUtil.getTaskCommandInst(taskInstance,this.isProcessTracking);
				}
				
			}
		}
		else{
			

			
			//非流程追踪查询
			if(taskInstance!=null){
				if(taskInstance.getTaskInstanceType()==TaskInstanceType.FIXFLOWTASK||taskInstance.getTaskInstanceType()==TaskInstanceType.FIXNOTICETASK){
					taskCommandInsts= CoreUtil.getTaskCommandInst(taskInstance,this.isProcessTracking);
				}
				
			}
		}
		
		
		

	
		
		return taskCommandInsts;
	}
}
