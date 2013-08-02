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
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.variable.VariableFlowTypeEntity;
import com.founder.fix.fixflow.core.impl.variable.VariableTransferEntity;
import com.founder.fix.fixflow.core.variable.VariableFlowType;

public class SaveVariablesCmd implements Command<Void> {

	protected String processInstanceId;
	protected String tokenId;
	protected String taskInstanceId;
	protected String nodeId;

	protected Map<String, ? extends Object> variables;

	public SaveVariablesCmd(SaveVariablesCommand saveVariablesCommand) {

		this.processInstanceId = saveVariablesCommand.getProcessInstanceId();
		this.tokenId = saveVariablesCommand.getTokenId();
		this.taskInstanceId = saveVariablesCommand.getTaskInstanceId();
		this.nodeId = saveVariablesCommand.getNodeId();
		this.variables = saveVariablesCommand.getVariables();

	}

	@SuppressWarnings("unchecked")
	public Void execute(CommandContext commandContext) {
		// TODO Auto-generated method stub

		VariableTransferEntity variableTransferEntity = new VariableTransferEntity();
		variableTransferEntity.setVariableMap((Map<String, Object>) variables);

		if (this.processInstanceId != null && !this.processInstanceId.equals("")) {
			VariableFlowTypeEntity variableFlowTypeEntity = new VariableFlowTypeEntity(VariableFlowType.PROCESSINSTANCE, this.processInstanceId);
			variableTransferEntity.addVariableFlowType(variableFlowTypeEntity);
		}

		if (this.tokenId != null && !this.tokenId.equals("")) {
			VariableFlowTypeEntity variableFlowTypeEntity = new VariableFlowTypeEntity(VariableFlowType.TOKEN, this.tokenId);
			variableTransferEntity.addVariableFlowType(variableFlowTypeEntity);
		}

		if (this.taskInstanceId != null && !this.taskInstanceId.equals("")) {
			VariableFlowTypeEntity variableFlowTypeEntity = new VariableFlowTypeEntity(VariableFlowType.TASKINSTANCE, this.taskInstanceId);
			variableTransferEntity.addVariableFlowType(variableFlowTypeEntity);
		}

		if (this.nodeId != null && !this.nodeId.equals("")) {
			VariableFlowTypeEntity variableFlowTypeEntity = new VariableFlowTypeEntity(VariableFlowType.NODE, this.nodeId);
			variableTransferEntity.addVariableFlowType(variableFlowTypeEntity);
		}

		commandContext.getVariableManager().saveVariable(variableTransferEntity);

		return null;
	}

}
