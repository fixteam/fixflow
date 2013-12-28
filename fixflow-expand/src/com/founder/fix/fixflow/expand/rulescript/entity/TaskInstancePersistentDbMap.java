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
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.scriptlanguage.BusinessRulesScript;


public class TaskInstancePersistentDbMap implements BusinessRulesScript {

	public Object execute(Object parameter, SqlCommand sqlCommand, ProcessEngineConfigurationImpl processEngineConfiguration) {
		
		TaskInstanceEntity taskInstance=(TaskInstanceEntity)parameter;
		
		
		Map<String, Object> objectParam = new HashMap<String, Object>();
		objectParam.put("TASKINSTANCE_ID", taskInstance.getId());		
		objectParam.put("NAME", taskInstance.getName());		
		objectParam.put("DESCRIPTION", taskInstance.getDescription());
		objectParam.put("PROCESSINSTANCE_ID", taskInstance.getProcessInstanceId());
		objectParam.put("PROCESSDEFINITION_ID", taskInstance.getProcessDefinitionId());
		objectParam.put("PROCESSDEFINITION_KEY", taskInstance.getProcessDefinitionKey());	
		objectParam.put("PROCESSDEFINITION_NAME", taskInstance.getProcessDefinitionName());		
		objectParam.put("VERSION", taskInstance.getVersion());
		objectParam.put("TOKEN_ID", taskInstance.getTokenId());
		objectParam.put("NODE_ID", taskInstance.getNodeId());
		objectParam.put("NODE_NAME", taskInstance.getNodeName());		
		objectParam.put("PARENTTASK_ID",taskInstance.getParentTaskInstanceId());		
		objectParam.put("ASSIGNEE", taskInstance.getAssignee());
		objectParam.put("CLAIM_TIME", taskInstance.getClaimTime());	
		objectParam.put("CREATE_TIME", taskInstance.getCreateTime());
		objectParam.put("START_TIME", taskInstance.getStartTime());		
		objectParam.put("END_TIME", taskInstance.getEndTime());		
		objectParam.put("DUEDATE", taskInstance.getDueDate());		
		objectParam.put("PRIORITY", String.valueOf(taskInstance.getPriority()));		
		objectParam.put("CATEGORY", String.valueOf(taskInstance.getCategory()));		
		objectParam.put("OWNER", taskInstance.getOwner());		
		objectParam.put("DELEGATIONSTATESTRING", StringUtil.getString(taskInstance.getDelegationState()));		
		objectParam.put("BIZKEY", taskInstance.getBizKey());	
		objectParam.put("TASK_COMMENT", taskInstance.getTaskComment());	
		objectParam.put("FORMURI", taskInstance.getFormUri());
		objectParam.put("FORMURIVIEW", taskInstance.getFormUriView());		
		objectParam.put("TASKGROUP", taskInstance.getTaskGroup());		
		objectParam.put("TASKTYPE", taskInstance.getTaskInstanceType().toString());	
		objectParam.put("ISBLOCKING", String.valueOf(taskInstance.isBlocking()));
		objectParam.put("ISCANCELLED", String.valueOf(taskInstance.isCancelled()));		
		objectParam.put("ISSUSPENDED", String.valueOf(taskInstance.isSuspended()));		
		objectParam.put("ISOPEN", String.valueOf(taskInstance.isOpen()));
		objectParam.put("ISDRAFT", String.valueOf(taskInstance.isDraft()));
		objectParam.put("EXPECTED_EXECUTIONTIME", String.valueOf(taskInstance.getExpectedExecutionTime()));
		objectParam.put("AGENT", taskInstance.getAgent());		
		objectParam.put("ADMIN", taskInstance.getAdmin());		
		objectParam.put("CALLACTIVITY_INSTANCE_ID", taskInstance.getCallActivityInstanceId());		
		objectParam.put("PENDINGTASKID", taskInstance.getPendingTaskId());		
		objectParam.put("ARCHIVE_TIME", taskInstance.getArchiveTime());		
		objectParam.put("COMMAND_ID", taskInstance.getCommandId());
		objectParam.put("COMMAND_TYPE", taskInstance.getCommandType());		
		objectParam.put("COMMAND_MESSAGE", taskInstance.getCommandMessage());
		
		Map<String, Object> persistenceExtensionFields=taskInstance.getPersistenceExtensionFields();		
		for (String key : persistenceExtensionFields.keySet()) {
			objectParam.put(key, persistenceExtensionFields.get(key));
		}
		return objectParam;
		

	}

}
