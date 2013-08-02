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

import java.util.Collection;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.command.QueryVariablesCommand;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.variable.VariableFlowTypeEntity;
import com.founder.fix.fixflow.core.impl.variable.VariableQueryEntity;
import com.founder.fix.fixflow.core.variable.VariableFlowType;

public class QueryVariablesCmd<T> implements Command<Map<String, Object>> {

	protected String processInstanceId;
	protected String tokenId;
	protected String taskInstanceId;
	protected String nodeId;

	Collection<String> variableNames;

	public QueryVariablesCmd(QueryVariablesCommand queryVariablesCommand) {

		this.processInstanceId = queryVariablesCommand.getProcessInstanceId();
		this.tokenId = queryVariablesCommand.getTokenId();
		this.taskInstanceId = queryVariablesCommand.getTaskInstanceId();
		this.nodeId = queryVariablesCommand.getNodeId();
		this.variableNames = queryVariablesCommand.getVariableNames();

	}

	public Map<String, Object> execute(CommandContext commandContext) {
		// TODO Auto-generated method stub

		VariableQueryEntity variableQueryEntity = new VariableQueryEntity();
		if(variableNames!=null){
			variableQueryEntity.setVariableNames(variableNames);
		}
		

		if (this.processInstanceId != null && !this.processInstanceId.equals("")) {
			VariableFlowTypeEntity variableFlowTypeEntity = new VariableFlowTypeEntity(VariableFlowType.PROCESSINSTANCE, this.processInstanceId);
			variableQueryEntity.addVariableFlowType(variableFlowTypeEntity);
		}

		if (this.tokenId != null && !this.tokenId.equals("")) {
			VariableFlowTypeEntity variableFlowTypeEntity = new VariableFlowTypeEntity(VariableFlowType.TOKEN, this.tokenId);
			variableQueryEntity.addVariableFlowType(variableFlowTypeEntity);
		}

		if (this.taskInstanceId != null && !this.taskInstanceId.equals("")) {
			VariableFlowTypeEntity variableFlowTypeEntity = new VariableFlowTypeEntity(VariableFlowType.TASKINSTANCE, this.taskInstanceId);
			variableQueryEntity.addVariableFlowType(variableFlowTypeEntity);
		}

		if (this.nodeId != null && !this.nodeId.equals("")) {
			VariableFlowTypeEntity variableFlowTypeEntity = new VariableFlowTypeEntity(VariableFlowType.NODE, this.nodeId);
			variableQueryEntity.addVariableFlowType(variableFlowTypeEntity);
		}

		return commandContext.getVariableManager().queryVariable(variableQueryEntity);

	}

}
