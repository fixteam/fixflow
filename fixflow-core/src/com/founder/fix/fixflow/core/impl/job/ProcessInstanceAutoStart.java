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
