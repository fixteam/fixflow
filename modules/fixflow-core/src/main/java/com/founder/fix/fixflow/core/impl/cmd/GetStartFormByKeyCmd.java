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



import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;


public class GetStartFormByKeyCmd<T> implements Command<String>{

	protected String processDefinitionKey;
	
	public GetStartFormByKeyCmd(String processDefinitionKey)
	{
		this.processDefinitionKey=processDefinitionKey;
	}
	
	
	public String execute(CommandContext commandContext) {
		// TODO Auto-generated method stub
		ProcessDefinitionManager processDefinitionManager =commandContext.getProcessDefinitionManager();

		ProcessDefinitionBehavior processDefinition = processDefinitionManager
				.findLatestProcessDefinitionByKey(processDefinitionKey);
		if(processDefinition==null)
		{
			throw new FixFlowException("Key 为 :"+processDefinitionKey+" 的流程定义不存在!");
		}
		
		
		

		return processDefinition.getStartFormKey();
	}

}
