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
