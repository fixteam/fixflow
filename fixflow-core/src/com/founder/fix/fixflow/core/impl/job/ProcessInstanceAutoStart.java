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
package com.founder.fix.fixflow.core.impl.job;

import java.util.HashMap;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


import com.founder.fix.fixflow.core.RuntimeService;
import com.founder.fix.fixflow.core.impl.command.StartProcessInstanceCommand;
import com.founder.fix.fixflow.core.impl.identity.Authentication;

public class ProcessInstanceAutoStart extends AbstactTimeJob {


	public ProcessInstanceAutoStart() {
		
	}

	
	@Override
	public void executeJob(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		
		String processId=jobExecutionContext.getJobDetail().getJobDataMap().getString("processId");
		String nodeId=jobExecutionContext.getJobDetail().getJobDataMap().getString("nodeId");
		if(processId==null||processId.equals("")){
			processId=jobExecutionContext.getJobDetail().getJobDataMap().getString("processUniqueKey");
		}
		
		
		
		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		startProcessInstanceCommand.setProcessDefinitionId(processId);
		startProcessInstanceCommand.setStartAuthor(Authentication.getAdminId());
		startProcessInstanceCommand.setNodeId(nodeId);

		Map<String, Object> transientVariableMap = new HashMap<String, Object>();

		startProcessInstanceCommand.setTransientVariables(transientVariableMap);
		
		RuntimeService runtimeService = processEngine.getRuntimeService();
		
		runtimeService.timeStartProcessInstance(startProcessInstanceCommand);

	}

}
