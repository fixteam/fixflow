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

import java.util.Map;

import com.founder.fix.fixflow.core.impl.command.SaveVariablesCommand;
import com.founder.fix.fixflow.core.impl.datavariable.DataVariableEntity;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;

public class SaveVariablesCmd implements Command<Void> {

	protected String processInstanceId;
	protected String tokenId;
	protected String taskInstanceId;
	protected String nodeId;
	protected String variableType;

	protected Map<String, ? extends Object> variables;

	public SaveVariablesCmd(SaveVariablesCommand saveVariablesCommand) {


		this.processInstanceId = saveVariablesCommand.getProcessInstanceId();
		this.tokenId = saveVariablesCommand.getTokenId();
		this.taskInstanceId = saveVariablesCommand.getTaskInstanceId();
		this.nodeId = saveVariablesCommand.getNodeId();
		this.variables = saveVariablesCommand.getVariables();
		this.variableType=saveVariablesCommand.getVariableType();

	}


	public Void execute(CommandContext commandContext) {
		
		
		for (String key : variables.keySet()) {
			Object variableData=variables.get(key);
			
			DataVariableEntity dataVariableEntity=new DataVariableEntity();
			dataVariableEntity.setVariableKey(key);
			dataVariableEntity.setPersistence(true);
			dataVariableEntity.setProcessInstanceId(this.processInstanceId);
			dataVariableEntity.setTaskInstanceId(this.taskInstanceId);
			dataVariableEntity.setNodeId(this.nodeId);
			dataVariableEntity.setTokenId(this.tokenId);
			dataVariableEntity.setExpressionValue(variableData);
			dataVariableEntity.setVariableType(this.variableType);
			commandContext.getVariableManager().saveVariable(dataVariableEntity);
			
			
		}
		
		

		return null;
	}

}
