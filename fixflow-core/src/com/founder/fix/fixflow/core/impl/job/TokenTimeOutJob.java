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
import com.founder.fix.fixflow.core.runtime.Token;


public class TokenTimeOutJob extends AbstactTimeJob {


	@SuppressWarnings("unchecked")
	@Override
	public void executeJob(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		// TODO Auto-generated method stub

		String tokenId=jobExecutionContext.getJobDetail().getJobDataMap().getString("tokenId");
		String transientVariableId=jobExecutionContext.getJobDetail().getJobDataMap().getString("transientVariableId");
		String processInstanceId=jobExecutionContext.getJobDetail().getJobDataMap().getString("processInstanceId");
		String nodeId=jobExecutionContext.getJobDetail().getJobDataMap().getString("nodeId");
		
		RuntimeService runtimeService = processEngine.getRuntimeService();
		
		Token token=runtimeService.createTokenQuery().tokenId(tokenId).singleResult();
		
		Object transientVariable=runtimeService.getProcessInstanceVariable(processInstanceId, transientVariableId);
		Map<String, Object> transientVariableMap=null;
		if(transientVariable!=null){
			
			transientVariableMap=(HashMap<String, Object>)transientVariable;
			
		}
		
		//删除变量
		
		/*
		VariableQueryEntity variableQueryEntity = new VariableQueryEntity();

		if (processInstanceId != null && !processInstanceId.equals("")) {
			VariableFlowTypeEntity variableFlowTypeEntity = new VariableFlowTypeEntity(VariableFlowType.PROCESSINSTANCE, processInstanceId);
			variableQueryEntity.addVariableFlowType(variableFlowTypeEntity);
		}
		
		variableQueryEntity.addVariableName(transientVariableId);
		
		Context.getCommandContext().getVariableManager().deleteVariable(variableQueryEntity);
        */
		
		//
		
		if(nodeId.equals(token.getNodeId())){

			runtimeService.tokenTimeOut(tokenId, transientVariableMap);

		}
		else{

			runtimeService.tokenTimeOut(tokenId,nodeId, transientVariableMap);
			
		}
	}

}
