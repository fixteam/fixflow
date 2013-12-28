package com.founder.fix.fixflow.expand.rulescript.entity;

import java.util.HashMap;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.task.IdentityLinkEntity;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.scriptlanguage.BusinessRulesScript;


public class IdentityLinkPersistentDbMap implements BusinessRulesScript {

	public Object execute(Object parameter, SqlCommand sqlCommand, ProcessEngineConfigurationImpl processEngineConfiguration) {
		
		IdentityLinkEntity identityLink=(IdentityLinkEntity)parameter;
		
		Map<String, Object> objectParam = new HashMap<String, Object>();
		objectParam.put("ID", identityLink.getId());
		objectParam.put("TYPE", StringUtil.getString(identityLink.getType()));
		objectParam.put("USER_ID", identityLink.getUserId());
		objectParam.put("GROUP_ID", identityLink.getGroupId());
		objectParam.put("GROUP_TYPE", identityLink.getGroupType());
		objectParam.put("INCLUDE_EXCLUSION", StringUtil.getString(identityLink.getIncludeExclusion()));
		objectParam.put("TASKINSTANCE_ID", identityLink.getTaskId());
		objectParam.put("ARCHIVE_TIME", identityLink.getArchiveTime());
		
		
		Map<String, Object> persistenceExtensionFields=identityLink.getPersistenceExtensionFields();		
		for (String key : persistenceExtensionFields.keySet()) {
			objectParam.put(key, persistenceExtensionFields.get(key));
		}
		return objectParam;
	}

}
