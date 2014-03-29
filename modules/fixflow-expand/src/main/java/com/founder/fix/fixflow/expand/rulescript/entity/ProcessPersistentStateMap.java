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
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.scriptlanguage.BusinessRulesScript;
/**
 * 将ProcessEntry转换成属性Map
 * @author ych
 *
 */
public class ProcessPersistentStateMap  implements BusinessRulesScript{
	public Object execute(Object parameter, SqlCommand sqlCommand,ProcessEngineConfigurationImpl processEngineConfiguration) {
		
		Map<String,Object> mapPersistentState = new HashMap<String, Object>();
		ProcessInstanceEntity processInstance = (ProcessInstanceEntity)parameter;
		mapPersistentState.put("processInstanceId", processInstance.getId());
		mapPersistentState.put("subject", processInstance.getSubject());
		mapPersistentState.put("processDefinitionKey", processInstance.getProcessDefinitionKey());
		mapPersistentState.put("processDefinitionId", processInstance.getProcessDefinitionId());
		mapPersistentState.put("rootTokenId", processInstance.getRootTokenId());
		mapPersistentState.put("definitionId", processInstance.getDefinitionId());
		mapPersistentState.put("parentProcessInstanceId", processInstance.getParentProcessInstanceId());
		mapPersistentState.put("parentProcessInstanceTokenId", processInstance.getParentProcessInstanceTokenId());
		mapPersistentState.put("initiator", processInstance.getInitiator());
		mapPersistentState.put("startAuthor", processInstance.getStartAuthor());
		mapPersistentState.put("bizKey", processInstance.getBizKey());
		mapPersistentState.put("startTime", processInstance.getStartTime());
		mapPersistentState.put("endTime", processInstance.getEndTime());
		mapPersistentState.put("updateTime", processInstance.getUpdateTime());
		mapPersistentState.put("archiveTime", processInstance.getArchiveTime());
		mapPersistentState.put("isSuspended", processInstance.isSuspended());
		mapPersistentState.put("processLocation", processInstance.getProcessLocation());
		mapPersistentState.put("instanceStatus", processInstance.getInstanceType());
		Map<String,Object> persistenceExtensionFields=processInstance.getPersistenceExtensionFields();	
		for (String key : persistenceExtensionFields.keySet()) {
			mapPersistentState.put(key, persistenceExtensionFields.get(key));	
		}
		return mapPersistentState;
	}
}
