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

import org.eclipse.bpmn2.UserTask;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;

public class GetUserCommandCmd<T> implements Command<List<T>> {

	
	String processDefinitionId;
	
	String nodeId;
	
	
	public GetUserCommandCmd(String processDefinitionId,String nodeId)
	{
		this.processDefinitionId=processDefinitionId;
		this.nodeId=nodeId;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> execute(CommandContext commandContext) {

		
		ProcessDefinitionManager processDefinitionManager = commandContext.getProcessDefinitionManager();

		ProcessDefinitionBehavior processDefinition = processDefinitionManager.findLatestProcessDefinitionById(processDefinitionId);

		List<T> userCommandQueryList = new ArrayList<T>();
		Object flowNodeObject = processDefinition.getDefinitions().getElement(nodeId);
		if (flowNodeObject != null && flowNodeObject instanceof UserTask) {
			userCommandQueryList = (List<T>)((UserTaskBehavior) flowNodeObject).getTaskCommands();
		}

		return userCommandQueryList;
	}

}
