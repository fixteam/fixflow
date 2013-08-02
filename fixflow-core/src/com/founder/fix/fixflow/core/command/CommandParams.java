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
