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



import com.founder.fix.bpmn2extensions.coreconfig.Priority;
import com.founder.fix.bpmn2extensions.coreconfig.PriorityConfig;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class GetPriorityCmd implements Command<Priority>{
	
	protected int priorityValue=50;
	public GetPriorityCmd(int priorityValue){
		this.priorityValue=priorityValue;
	}

	public Priority execute(CommandContext commandContext) {


		
		PriorityConfig priorityConfig=commandContext.getProcessEngineConfigurationImpl().getPriorityConfig();
		
		for (Priority priority : priorityConfig.getPriority()) {
			
			int priorityConfigValue=StringUtil.getInt(priority.getValue());
			if(priorityValue==priorityConfigValue){
				return priority;
			}
		}
		
		
		return null;
	}

}
