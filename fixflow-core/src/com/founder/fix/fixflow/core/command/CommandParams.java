package com.founder.fix.fixflow.core.command;

import java.util.HashMap;
import java.util.Map;

public abstract class CommandParams {

	/**
	 * 瞬态流程实例变量Map
	 */
	protected Map<String, Object> transientVariables = null;

	/**
	 * 持久化流程实例变量Map
	 */
	protected Map<String, Object> variables = null;

	public void addTransientVariable(String variableKey, Object variableObj) {
		if (transientVariables == null) {
			transientVariables = new HashMap<String, Object>();
		}
		transientVariables.put(variableKey, variableObj);

	}

	public void addVariable(String variableKey, Object variableObj) {
		if (variables == null) {
			variables = new HashMap<String, Object>();
		}
		variables.put(variableKey, variableObj);

	}

	public Object getTransientVariable(String variableKey) {
		if (transientVariables == null) {
			return null;
		}
		return transientVariables.get(variableKey);
	}

	public Map<String, Object> getTransientVariables() {
		return transientVariables;
	}

	public Object getVariable(String variableKey) {
		if (variables == null) {
			return null;
		}
		return variables.get(variableKey);
	}

	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setTransientVariables(Map<String, Object> transientVariables) {
		this.transientVariables = transientVariables;

	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;

	}

}
