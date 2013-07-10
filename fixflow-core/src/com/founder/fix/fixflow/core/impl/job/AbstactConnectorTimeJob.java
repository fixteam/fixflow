package com.founder.fix.fixflow.core.impl.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public abstract class AbstactConnectorTimeJob implements Job {
	
	
	



	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		executeJob(jobExecutionContext);
	}
	

	public abstract void executeJob(JobExecutionContext jobExecutionContext)  throws JobExecutionException;
	


}
