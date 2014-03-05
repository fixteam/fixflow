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
