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
package com.founder.fix.fixflow.core.impl.context;

import java.util.HashMap;
import java.util.Map;

import com.founder.fix.fixflow.core.context.ContextInstance;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.datavariable.DataVariableEntity;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;

public class ContextInstanceImpl implements ContextInstance {

	ProcessInstance processInstance;

	public ContextInstanceImpl(ProcessInstance processInstance) {
		this.processInstance = processInstance;
	}

	/**
	 * 持久化流程实例变量Map
	 */
	protected Map<String, Object> variableMap = null;

	public void addTransientVariable(String variableKey, Object variableObj) {
		ExpressionMgmt.setVariable(variableKey, variableObj);

	}

	public Object getTransientVariable(String variableKey, ExecutionContext executionContext) {

		if (variableKey == null) {
			return null;
		}
		return ExpressionMgmt.execute(variableKey, executionContext);
	}
	
	protected Map<String, Object> transientVariableMap=new HashMap<String, Object>();

	public Map<String, Object> getTransientVariableMap() {
		return transientVariableMap;
	}

	public void setTransientVariableMap(Map<String, Object> transientVariableMap) {
		if (transientVariableMap == null) {
			return;
		}
		//ExecutionContext executionContextTemp = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(token);
		this.transientVariableMap=transientVariableMap;
			for (String mapKey : transientVariableMap.keySet()) {
				ExpressionMgmt.setVariable(mapKey, transientVariableMap.get(mapKey));
			}

		
		
		/*
		ProcessInstanceImpl processInstanceImpl = (ProcessInstanceImpl) this.processInstance;
		if (transientVariableMap.get("formInfo") != null) {
			for (DataVariableBehavior dataVariableBehavior : processInstanceImpl.getProcessDefinition().getDataVariableMgmtDefinition().getDataVariableBehaviorsByProcess()) {
				DataVariableInstance dataVariableInstance = processInstanceImpl.getDataVariableMgmtInstance().createDataVariableInstance(dataVariableBehavior);
				dataVariableInstance.executeExpression(executionContextTemp);
			}
		}
		*/

	}

	public Object getVariable(String variableKey) {
		if (variableMap == null) {
			return null;
		}
		return variableMap.get(variableKey);
	}

	public Map<String, Object> getVariableMap() {

		if (variableMap == null) {
			variableMap = new HashMap<String, Object>();
		}
		return variableMap;
	}

	public void addVariable(String variableKey, Object variableObj) {
		if (variableMap == null) {
			variableMap = new HashMap<String, Object>();
		}
		variableMap.put(variableKey, variableObj);

	}

	public void setVariableMap(Map<String, Object> variableMap) {
		
		if (variableMap == null||variableMap.size()<=0) {
			return;
		}
		//ExecutionContext executionContextTemp = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(token);
		if (variableMap != null) {
			for (String mapKey : variableMap.keySet()) {
				ExpressionMgmt.setVariable(mapKey, variableMap.get(mapKey));
			}

		}
		String processInstanceId=processInstance.getId();
		
		
		
		

		for (String key : variableMap.keySet()) {
			Object variableData=variableMap.get(key);
			
			DataVariableEntity dataVariableEntity=new DataVariableEntity();
			dataVariableEntity.setVariableKey(key);
			dataVariableEntity.setPersistence(true);
			dataVariableEntity.setProcessInstanceId(processInstanceId);

			dataVariableEntity.setExpressionValue(variableData);
			//dataVariableEntity.setVariableType(this.variableType);
			Context.getCommandContext().getVariableManager().saveVariable(dataVariableEntity);
			
			
		}
	

	
		this.variableMap = variableMap;

	}

	public void addDataVariable(String variableKey, Object variableObj) {
		// TODO 自动生成的方法存根
		ExpressionMgmt.setVariable("fixflowdatavariable_"+variableKey, variableObj);
		ProcessInstanceEntity processInstanceEntity=(ProcessInstanceEntity)this.processInstance;
		ExecutionContext executionContext=ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(processInstanceEntity.getRootToken());
		//ExpressionMgmt.execute("fixflowdatavariable_"+variableKey, executionContext);
		ExpressionMgmt.execute("${"+variableKey+"}=fixflowdatavariable_"+variableKey+";",executionContext);
		
		ExpressionMgmt.execute("${"+variableKey+"}", executionContext);
	}
	
	public void setDataVariable(Map<String, Object> dataVariableMap) {
		// TODO 自动生成的方法存根
		
		
		if (dataVariableMap == null||dataVariableMap.size()<=0) {
			return;
		}
		//ExecutionContext executionContextTemp = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(token);
		if (dataVariableMap != null) {
			for (String mapKey : dataVariableMap.keySet()) {
				addDataVariable(mapKey,dataVariableMap.get(mapKey));
			}

		}
		
		
		
	}

}
