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
package com.founder.fix.fixflow.core.impl;


import java.util.concurrent.ThreadPoolExecutor;

import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;

import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.ScheduleService;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.cmd.ExecuteConnectorTimeJobCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetSchedulerFactoryCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetThreadPoolExecutorCmd;
import com.founder.fix.fixflow.core.impl.cmd.SaveJobCmd;
import com.founder.fix.fixflow.core.impl.job.JobEntity;
import com.founder.fix.fixflow.core.job.Job;

public class ScheduleServiceImpl extends ServiceImpl implements ScheduleService {

	public SchedulerFactory getSchedulerFactory() {
		
		return commandExecutor.execute(new GetSchedulerFactoryCmd());
	}
	
	public Scheduler getScheduler() {
		return ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getScheduler();
	}
	
	
	public void schedulerRestart() {
		
		Scheduler scheduler;
		try {
			scheduler = getScheduler();
			if(scheduler.isInStandbyMode()){
				
				
				scheduler.start();
			}else{
				scheduler.standby();
				scheduler.start();
			}

			

		} catch (SchedulerException e) {
			throw new FixFlowException(e.getMessage(),e);
		}
		
	}

	public void schedulerStart() {
		Scheduler scheduler;
		try {
			scheduler = getScheduler();
			if(scheduler.isInStandbyMode()){
				scheduler.start();
			}

			

		} catch (SchedulerException e) {
			throw new FixFlowException(e.getMessage(),e);
		}
	}

	public void schedulerShutdown() {
		
		Scheduler scheduler;
		try {
			scheduler = getScheduler();
			if(!scheduler.isInStandbyMode()){
				scheduler.standby();

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

	public void executeConnectorTimeJob(JobExecutionContext jobExecutionContext) {
		
		commandExecutor.execute(new ExecuteConnectorTimeJobCmd(jobExecutionContext));
		
	}

	
}
