package com.founder.fix.fixflow.core.impl.cmd;

import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.job.JobEntity;
import com.founder.fix.fixflow.core.impl.persistence.JobManager;
import com.founder.fix.fixflow.core.job.Job;

public class SaveJobCmd implements Command<Void>{

	protected Job job;
	protected boolean isNowPerform=false;
	
	public SaveJobCmd(Job job,boolean isNowPerform){
		this.job=job;
		this.isNowPerform=isNowPerform;
	}
	
	
	public Void execute(CommandContext commandContext) {


		JobManager jobManager=commandContext.getJobManager();
		jobManager.saveJob((JobEntity)this.job);
		
		return null;
	}

}
