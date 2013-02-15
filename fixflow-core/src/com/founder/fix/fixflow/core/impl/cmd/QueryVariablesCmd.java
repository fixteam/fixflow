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
