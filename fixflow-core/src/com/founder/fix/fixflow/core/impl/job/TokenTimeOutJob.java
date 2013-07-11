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
