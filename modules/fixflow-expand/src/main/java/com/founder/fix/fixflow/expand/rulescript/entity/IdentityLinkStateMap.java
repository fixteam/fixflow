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
import com.founder.fix.fixflow.core.impl.task.IdentityLinkEntity;
import com.founder.fix.fixflow.core.scriptlanguage.BusinessRulesScript;
/**
 * 将IdentityLinkEntry转换成属性Map
 * @author ych
 *
 */
public class IdentityLinkStateMap implements BusinessRulesScript {

	public Object execute(Object parameter, SqlCommand sqlCommand,
			ProcessEngineConfigurationImpl processEngineConfiguration) {
		IdentityLinkEntity identityLinkEntity = (IdentityLinkEntity)parameter;
		Map<String,Object> objectParam = new HashMap<String, Object>();
		objectParam.put("id", identityLinkEntity.getId());
		objectParam.put("type", identityLinkEntity.getType().toString());
		objectParam.put("userId", identityLinkEntity.getUserId());
		objectParam.put("groupId", identityLinkEntity.getGroupId());
		objectParam.put("groupType", identityLinkEntity.getGroupType());
		objectParam.put("includeExclusion", identityLinkEntity.getIncludeExclusion().toString());
		objectParam.put("taskId", identityLinkEntity.getTaskId());
		objectParam.put("archiveTime", identityLinkEntity.getArchiveTime());
		Map<String,Object> persistenceExtensionFields=identityLinkEntity.getPersistenceExtensionFields();	
		for (String key : persistenceExtensionFields.keySet()) {
			objectParam.put(key, persistenceExtensionFields.get(key));	
		}
		return objectParam;
	}

}
