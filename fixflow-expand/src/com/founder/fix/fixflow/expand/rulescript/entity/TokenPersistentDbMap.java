package com.founder.fix.fixflow.expand.rulescript.entity;

import java.util.HashMap;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.scriptlanguage.BusinessRulesScript;

public class TokenPersistentDbMap implements BusinessRulesScript {

	public Object execute(Object parameter, SqlCommand sqlCommand, ProcessEngineConfigurationImpl processEngineConfiguration) {
		
		TokenEntity token=(TokenEntity)parameter;
		
		Map<String, Object> objectParam = new HashMap<String, Object>();
		objectParam.put("TOKEN_ID", token.getId());
		objectParam.put("NAME", token.getName());
		objectParam.put("PROCESSINSTANCE_ID", token.getProcessInstanceId());
		objectParam.put("NODE_ID", token.getNodeId());
		objectParam.put("PARENT_ID", token.getParentTokenId());
		objectParam.put("PARENT_FREETOKEN_ID", token.getParentFreeTokenId());
		objectParam.put("START_TIME", token.getStartTime());
		objectParam.put("END_TIME", token.getEndTime());
		objectParam.put("NODEENTER_TIME", token.getNodeEnterTime());
		objectParam.put("ARCHIVE_TIME", token.getArchiveTime());
		objectParam.put("TOKEN_LOCK", String.valueOf(token.getlock()));
		objectParam.put("ISSUSPENDED", String.valueOf(token.isSuspended()));
		objectParam.put("ISSUBPROCESSROOTTOKEN", String.valueOf(token.isSubProcessRootToken()));
		objectParam.put("ISABLETOREACTIVATEPARENT", String.valueOf(token.isAbleToReactivateParent()));
		objectParam.put("FREETOKEN", String.valueOf(token.isFreeToken()));
		
		Map<String, Object> persistenceExtensionFields=token.getPersistenceExtensionFields();		
		for (String key : persistenceExtensionFields.keySet()) {
			objectParam.put(key, persistenceExtensionFields.get(key));
		}
		return objectParam;
		
	}

}
