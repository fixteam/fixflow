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
