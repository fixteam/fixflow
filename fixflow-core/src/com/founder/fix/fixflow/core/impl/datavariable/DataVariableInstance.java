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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.DataVariableBehavior;
import com.founder.fix.fixflow.core.impl.db.PersistentObject;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.variable.VariableFlowTypeEntity;
import com.founder.fix.fixflow.core.impl.variable.VariableQueryEntity;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.variable.VariableFlowType;

public class DataVariableInstance implements Serializable, PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 501716446297454941L;

	protected DataVariableBehavior dataVariableBehavior;

	protected DataVariableMgmtInstance dataVariableMgmtInstance;

	public boolean isPersistence() {
		return dataVariableBehavior.isPersistence();
	}

	public DataVariableInstance(DataVariableBehavior dataVariableBehavior, DataVariableMgmtInstance dataVariableMgmtInstance) {
		this.dataVariableBehavior = dataVariableBehavior;
		this.dataVariableMgmtInstance = dataVariableMgmtInstance;

	}

	public void executeExpression(ExecutionContext executionContext) {

		// 对于需要持久化的数据变量的处理
		if (this.dataVariableBehavior.isPersistence()) {

			String processInstanceId = this.dataVariableMgmtInstance.getProcessInstance().getId();
			List<String> variableNames = new ArrayList<String>();
			String variableName = this.dataVariableBehavior.getId();
			variableNames.add(variableName);

			VariableQueryEntity variableQueryEntity = new VariableQueryEntity();
			if (variableNames != null) {
				variableQueryEntity.setVariableNames(variableNames);
			}

			if (processInstanceId != null && !processInstanceId.equals("")) {
				VariableFlowTypeEntity variableFlowTypeEntity = new VariableFlowTypeEntity(VariableFlowType.PROCESSINSTANCE, processInstanceId);
				variableQueryEntity.addVariableFlowType(variableFlowTypeEntity);
			}

			Map<String, Object> returnMap = Context.getCommandContext().getVariableManager().queryVariable(variableQueryEntity);
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

		return map;
	}

	public String getId() {

		return this.dataVariableBehavior.getId();
	}

}
