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


import com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class GetSystemTaskCommandCmd implements Command<List<TaskCommandInst>> {

	
	protected String commandType;
	
	public GetSystemTaskCommandCmd(String commandType){
		this.commandType=commandType;
	}
	
	public List<TaskCommandInst> execute(CommandContext commandContext) {
		// TODO 自动生成的方法存根
		
		
		List<TaskCommandInst> taskCommands=new ArrayList<TaskCommandInst>();
		List<TaskCommandDef> taskCommandDefs=commandContext.getProcessEngineConfigurationImpl().getTaskCommandConfig().getTaskCommandDef();
		

		
		for (TaskCommandDef taskCommandDef : taskCommandDefs) {
			
			
			
		
			if((taskCommandDef.getType().equals(commandType)||taskCommandDef.getType().equals("all"))&&StringUtil.getBoolean(taskCommandDef.getIsEnabled())){
				TaskCommandInst taskCommand=new TaskCommandInst(taskCommandDef.getId(),taskCommandDef.getName(), null, taskCommandDef.getId(),true);
				taskCommands.add(taskCommand);
			}
			
			
		}
		
		
		
		return taskCommands;
	}

}
