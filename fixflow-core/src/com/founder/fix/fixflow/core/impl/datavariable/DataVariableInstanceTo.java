package com.founder.fix.fixflow.core.impl.datavariable;

import java.io.Serializable;


public class DataVariableInstanceTo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7130902229049188077L;

	protected String processInstanceId;

	protected String variableKey;

	protected Object variableValue;

	protected String variableClassName;

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

	public Object getVariableValue() {
		return variableValue;
	}

	public void setVariableValue(Object variableValue) {
		this.variableValue = variableValue;
	}

	public String getVariableClassName() {
		return variableClassName;
	}

	public void setVariableClassName(String variableClassName) {
		this.variableClassName = variableClassName;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return variableKey;
	}



	

}
