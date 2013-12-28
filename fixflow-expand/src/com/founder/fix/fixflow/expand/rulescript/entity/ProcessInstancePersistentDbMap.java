package com.founder.fix.fixflow.expand.rulescript.entity;

import java.util.HashMap;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.scriptlanguage.BusinessRulesScript;

public class ProcessInstancePersistentDbMap implements BusinessRulesScript {

	public Object execute(Object parameter, SqlCommand sqlCommand, ProcessEngineConfigurationImpl processEngineConfiguration) {
		
		ProcessInstanceEntity processInstance=(ProcessInstanceEntity)parameter;
		
		Map<String, Object> objectParam = new HashMap<String, Object>();
		objectParam.put("PROCESSINSTANCE_ID", processInstance.getId());
		objectParam.put("SUBJECT", processInstance.getSubject());
		objectParam.put("PROCESSDEFINITION_KEY", processInstance.getProcessDefinitionKey());
		objectParam.put("PROCESSDEFINITION_ID", processInstance.getProcessDefinitionId());
		//objectParam.put("DEFINITION_ID", parameter.getProcessDefinition().getDefinitions().getId());
		objectParam.put("ROOTTOKEN_ID", processInstance.getRootTokenId());
		objectParam.put("PARENT_INSTANCE_ID", processInstance.getParentProcessInstanceId());
		objectParam.put("PARENT_INSTANCE_TOKEN_ID", processInstance.getParentProcessInstanceTokenId());
		objectParam.put("BIZ_KEY", processInstance.getBizKey());
		objectParam.put("INITIATOR", processInstance.getInitiator());
		objectParam.put("START_AUTHOR", processInstance.getStartAuthor());
		objectParam.put("START_TIME", processInstance.getStartTime());
		objectParam.put("END_TIME", processInstance.getEndTime());
		objectParam.put("UPDATE_TIME", processInstance.getUpdateTime());
		objectParam.put("ARCHIVE_TIME", processInstance.getArchiveTime());
		objectParam.put("ISSUSPENDED", String.valueOf(processInstance.isSuspended()));
		objectParam.put("PROCESSLOCATION", processInstance.getProcessLocation());
		objectParam.put("INSTANCE_STATUS", processInstance.getInstanceType().toString());
		
		Map<String, Object> persistenceExtensionFields=processInstance.getPersistenceExtensionFields();		
		for (String key : persistenceExtensionFields.keySet()) {
			objectParam.put(key, persistenceExtensionFields.get(key));
		}
		return objectParam;

	}

}
