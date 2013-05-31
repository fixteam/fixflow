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
	protected boolean isProcessTracking;
	public GetTaskCommandByTaskIdCmd(String taskId,boolean isProcessTracking){
		this.taskId=taskId;
		this.isProcessTracking=isProcessTracking;
	}
	
	
	public List<TaskCommandInst> execute(CommandContext commandContext) {
		// TODO 自动生成的方法存根
		List<TaskCommandInst> taskCommandInsts=new ArrayList<TaskCommandInst>();
		
		TaskInstanceEntity taskInstance=commandContext.getTaskManager().findTaskById(taskId);
		
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
