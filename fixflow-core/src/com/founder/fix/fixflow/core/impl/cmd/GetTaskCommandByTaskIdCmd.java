/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 */
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
