package com.founder.fix.fixflow.core.impl.datavariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.DataVariableBehavior;
import com.founder.fix.fixflow.core.impl.command.QueryVariablesCommand;
import com.founder.fix.fixflow.core.impl.db.AbstractPersistentObject;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;


public class DataVariableEntity extends AbstractPersistentObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3906878781132663222L;
	
	public static String QUERY_DATA_KEY="queryBizVariable";



	
	protected String processInstanceId;

	protected String variableKey;

	protected byte[] variableValue;

	protected String variableClassName;

	protected String taskInstanceId;

	protected String tokenId;

	protected String nodeId;

	protected String variableType;

	protected String bizData;
	
	protected boolean isPersistence;
	
	
	protected DataVariableBehavior dataVariableBehavior;

	protected DataVariableMgmtInstance dataVariableMgmtInstance;
	
	public DataVariableEntity () {
	
	}


	public DataVariableEntity (DataVariableBehavior dataVariableBehavior, DataVariableMgmtInstance dataVariableMgmtInstance) {
	
		
		
		
		this.dataVariableBehavior = dataVariableBehavior;
		this.isPersistence=dataVariableBehavior.isPersistence();
		this.variableKey=dataVariableBehavior.getId();
		this.dataVariableMgmtInstance = dataVariableMgmtInstance;

	}
	
	/**
	 * 是否为持久化变量
	 * @return
	 */
	public boolean isPersistence() {
		return this.isPersistence;
	}
	public void setPersistence(boolean isPersistence) {
		this.isPersistence = isPersistence;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getVariableKey() {
		return variableKey;
	}

	public void setVariableKey(String variableKey) {
		this.variableKey = variableKey;
	}

	public byte[] getVariableValue() {
		return variableValue;
	}

	public void setVariableValue(byte[] variableValue) {
		this.variableValue = variableValue;
	}

	public String getVariableClassName() {
		return variableClassName;
	}

	public void setVariableClassName(String variableClassName) {
		this.variableClassName = variableClassName;
	}

	public String getTaskInstanceId() {
		return taskInstanceId;
	}

	public void setTaskInstanceId(String taskInstanceId) {
		this.taskInstanceId = taskInstanceId;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getVariableType() {
		return variableType;
	}

	public void setVariableType(String variableType) {
		this.variableType = variableType;
	}

	public String getBizData() {
		return bizData;
	}

	public void setBizData(String bizData) {
		this.bizData = bizData;
	}
	
	

	public Object getExpressionValue() {
		Object object = ExpressionMgmt.getVariable(getId());
		return object;
	}

	public void setExpressionValue(Object expressionValue) {
		ExpressionMgmt.setVariable(getId(), expressionValue);
	}

	public Map<String, Object> getPersistentState() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("PROCESSINSTANCE_ID", this.dataVariableMgmtInstance.getProcessInstance().getId());
		map.put("VARIABLE_KEY", this.dataVariableBehavior.getId());
		map.put("VARIABLE_VALUE", getExpressionValue());
		map.put("VARIABLE_CLASSNAME", this.dataVariableBehavior.getDataType());
		map.put("VARIABLE_CLASSNAME", this.dataVariableBehavior.getDataType());
		map.put("VARIABLE_TYPE", this.dataVariableBehavior.getDataType());
		return map;
	}
	

	/**
	 * 获取变量编号
	 */
	public String getId() {

		return this.variableKey;
	}

	@Override
	public void persistentInit(Map<String, Object> entityMap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> getPersistentDbMap() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void executeExpression(ExecutionContext executionContext) {

		// 对于需要持久化的数据变量的处理
		if (this.isPersistence()) {

			String processInstanceId = this.dataVariableMgmtInstance.getProcessInstance().getId();
			List<String> variableNames = new ArrayList<String>();
			String variableName = this.dataVariableBehavior.getId();
			variableNames.add(variableName);
			
			QueryVariablesCommand queryVariablesCommand=new QueryVariablesCommand();
			queryVariablesCommand.setVariableNames(variableNames);
			queryVariablesCommand.setProcessInstanceId(processInstanceId);

		

			Map<String, Object> returnMap = Context.getCommandContext().getVariableManager().queryVariable(queryVariablesCommand);
			if (returnMap != null && returnMap.containsKey(variableName)) {
				ExpressionMgmt.setVariable(getId(), returnMap.get(variableName));

			} else {
				Object object = null;
				if (dataVariableBehavior.getExpression() != null) {
					object = ExpressionMgmt.execute(dataVariableBehavior.getExpression(), executionContext);
				}

				ExpressionMgmt.setVariable(getId(), object);
			}

		} else {
			// 不需要持久化的数据变量的处理
			Object object = null;
			if (dataVariableBehavior.getExpression() != null) {
				object = ExpressionMgmt.execute(dataVariableBehavior.getExpression(), executionContext);
			}

			ExpressionMgmt.setVariable(getId(), object);

		}

	}
	

}
