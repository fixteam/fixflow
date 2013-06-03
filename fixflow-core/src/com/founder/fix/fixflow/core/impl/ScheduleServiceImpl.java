package com.founder.fix.fixflow.core.impl;


import java.util.concurrent.ThreadPoolExecutor;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;

import com.founder.fix.fixflow.core.ScheduleService;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.cmd.GetSchedulerFactoryCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetThreadPoolExecutorCmd;
import com.founder.fix.fixflow.core.impl.cmd.SaveJobCmd;
import com.founder.fix.fixflow.core.impl.job.JobEntity;
import com.founder.fix.fixflow.core.job.Job;

public class ScheduleServiceImpl extends ServiceImpl implements ScheduleService {

	public SchedulerFactory getSchedulerFactory() {
		
		return commandExecutor.execute(new GetSchedulerFactoryCmd());
	}
	
	
	public void schedulerRestart() {
		
		SchedulerFactory schedulerFactory=getSchedulerFactory();
		Scheduler scheduler;
		try {
			scheduler = schedulerFactory.getScheduler();
			if(scheduler.isStarted()){
				scheduler.shutdown();
				scheduler.start();
			}else{
				scheduler.start();
			}

			

		} catch (SchedulerException e) {
			throw new FixFlowException(e.getMessage(),e);
		}
		
	}

	public void schedulerStart() {
		SchedulerFactory schedulerFactory=getSchedulerFactory();
		Scheduler scheduler;
		try {
			scheduler = schedulerFactory.getScheduler();
			if(!scheduler.isStarted()){
				scheduler.start();
			}

			

		} catch (SchedulerException e) {
			throw new FixFlowException(e.getMessage(),e);
		}
	}

	public void schedulerShutdown() {
		
		SchedulerFactory schedulerFactory=getSchedulerFactory();
		Scheduler scheduler;
		try {
			scheduler = schedulerFactory.getScheduler();
			if(scheduler.isStarted()){
				scheduler.shutdown();

			}
			

		} catch (SchedulerException e) {
			throw new FixFlowException(e.getMessage(),e);
		}
		
	}

	public ThreadPoolExecutor getThreadPoolExecutor() {

		return commandExecutor.execute(new GetThreadPoolExecutorCmd(null));
	}

	public ThreadPoolExecutor getThreadPoolExecutor(String threadPoolKey) {
		return commandExecutor.execute(new GetThreadPoolExecutorCmd(threadPoolKey));
	}

	public Job createJob() {

		Job job=new JobEntity();
		return job;
	}

	public void saveJob(Job job) {
		commandExecutor.execute(new SaveJobCmd(job, false));
	}
	
	
	public void saveJob(Job job,boolean isNowPerform) {
		commandExecutor.execute(new SaveJobCmd(job, isNowPerform));
	}

	
	
	
	
	
}
