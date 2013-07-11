package com.founder.fix.fixflow.core.impl.job;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.founder.fix.fixflow.core.ScheduleService;


public class ConnectorTimeJob extends AbstactTimeJob {

	@Override
	public void executeJob(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		
		
		
		
			ScheduleService scheduleService = processEngine.getScheduleService();
			scheduleService.executeConnectorTimeJob(jobExecutionContext);

	
		
		
	}


}
