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
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.scriptlanguage.BusinessRulesScript;
/**
 * 将TokenEntry转换成属性Map
 * @author ych
 *
 */
public class TokenStateMap implements BusinessRulesScript {

	public Object execute(Object parameter, SqlCommand sqlCommand,
			ProcessEngineConfigurationImpl processEngineConfiguration) {
		// TODO Auto-generated method stub
		Map<String,Object> objectParam = new HashMap<String, Object>();
		TokenEntity token = (TokenEntity)parameter;
		objectParam.put("tokenId", token.getId());
		objectParam.put("name", token.getName());
		objectParam.put("startTime", token.getStartTime());
		objectParam.put("endTime", token.getEndTime());
		objectParam.put("nodeEnterTime", token.getNodeEnterTime());
		objectParam.put("isAbleToReactivateParent", String.valueOf(token.isAbleToReactivateParent()));
		objectParam.put("isSubProcessRootToken", String.valueOf(token.isSubProcessRootToken()));
		objectParam.put("isSuspended", String.valueOf(token.isSuspended()));
		objectParam.put("isLocked", String.valueOf(token.getlock()));
		objectParam.put("nodeId", token.getNodeId());
		objectParam.put("processInstanceId", token.getProcessInstanceId());
		objectParam.put("parentTokenId", token.getParentTokenId());
		objectParam.put("archiveTime", token.getArchiveTime());
		objectParam.put("freeToken", String.valueOf(token.isFreeToken()));
		objectParam.put("parentFreeTokenId", token.getParentFreeTokenId());
		
		Map<String,Object> persistenceExtensionFields=token.getPersistenceExtensionFields();	
		for (String key : persistenceExtensionFields.keySet()) {
			objectParam.put(key, persistenceExtensionFields.get(key));	
		}
		return objectParam;
	}

}
