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
 * @author ych
 */
package com.founder.fix.fixflow.expand.rulescript.entity;

import java.util.HashMap;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;
import com.founder.fix.fixflow.core.impl.datavariable.DataVariableEntity;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.scriptlanguage.BusinessRulesScript;

/**
 * 将DataVariableEntry转换成属性Map
 * @author ych
 */
public class VariableStateMap implements BusinessRulesScript {

	public Object execute(Object parameter, SqlCommand sqlCommand,
			ProcessEngineConfigurationImpl processEngineConfiguration) {
		Map<String,Object> persistentState = new HashMap<String, Object>();
		DataVariableEntity variable = (DataVariableEntity)parameter;
		persistentState.put("processInstanceId", variable.getProcessInstanceId());		
		persistentState.put("variableKey", variable.getVariableKey());		
		persistentState.put("variableValue", variable.getVariableValue());
		persistentState.put("variableClassName", variable.getVariableClassName());
		persistentState.put("taskInstanceId", variable.getTaskInstanceId());
		persistentState.put("tokenId", variable.getTokenId());	
		persistentState.put("nodeId", variable.getNodeId());		
		persistentState.put("variableType", variable.getVariableType());
		persistentState.put("bizData", variable.getBizData());
		persistentState.put("archive_time", variable.getArchiveTime());
		
		Map<String,Object> extensionFields=variable.getExtensionFields();	
			
		for (String key : extensionFields.keySet()) {
			persistentState.put(key, extensionFields.get(key));
		}
		return persistentState;
	}

}
