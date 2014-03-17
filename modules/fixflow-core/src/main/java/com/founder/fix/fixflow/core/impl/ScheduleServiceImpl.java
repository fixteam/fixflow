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


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;

import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.ScheduleService;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.exception.FixFlowScheduleException;
import com.founder.fix.fixflow.core.impl.cmd.ExecuteConnectorTimeJobCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetSchedulerFactoryCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetThreadPoolExecutorCmd;
import com.founder.fix.fixflow.core.impl.cmd.SaveJobCmd;
import com.founder.fix.fixflow.core.impl.job.JobEntity;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.internationalization.ExceptionCode;
import com.founder.fix.fixflow.core.job.Job;

public class ScheduleServiceImpl extends ServiceImpl implements ScheduleService {
	
	public SchedulerFactory getSchedulerFactory() {
		return commandExecutor.execute(new GetSchedulerFactoryCmd());
	}
	
	public boolean getIsEnabled(){
		return StringUtil.getBoolean(ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getQuartzConfig().getIsEnable());
	}
	
	public Scheduler getScheduler() {
		return ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getScheduler();
	}
	
	public void schedulerRestart() {
		if(!getIsEnabled()){
			throw new FixFlowScheduleException(ExceptionCode.QUARZTEXCEPTION_ISENABLE);
		}
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
		if(!getIsEnabled()){
			throw new FixFlowScheduleException(ExceptionCode.QUARZTEXCEPTION_ISENABLE);
		}
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
		if(!getIsEnabled()){
			throw new FixFlowScheduleException(ExceptionCode.QUARZTEXCEPTION_ISENABLE);
		}
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
	
	public List<JobDetail> getJobList(String queryId){
		if(!getIsEnabled()){
			throw new FixFlowScheduleException(ExceptionCode.QUARZTEXCEPTION_ISENABLE);
		}
		Scheduler scheduler = getScheduler();
		List<JobDetail> jobList = new ArrayList<JobDetail>();
		Set<JobKey> set = new HashSet<JobKey>();
		try {
			//如果queryId不为空，则返回queryId对应的job,否则返回所有job
			if(StringUtil.isNotEmpty(queryId)){
				set = scheduler.getJobKeys(GroupMatcher.jobGroupContains(queryId));
			}else{
				List<String> groupNames = scheduler.getJobGroupNames();
				for(String groupName:groupNames){
					set.addAll(scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName)));
				}
			}
			for(JobKey key :set){
				JobDetail job = scheduler.getJobDetail(key); 
				jobList.add(job);
			}
		}catch (SchedulerException e) {
				throw new FixFlowException(e.getMessage(),e);
			}
		return jobList;
	}
	
	public void suspendJob(String name, String group) {
		if(!getIsEnabled()){
			throw new FixFlowScheduleException(ExceptionCode.QUARZTEXCEPTION_ISENABLE);
		}
		Scheduler scheduler = getScheduler();
		try {
			scheduler.pauseJob(new JobKey(name,group));
		} catch (SchedulerException e) {
			throw new FixFlowException(e.getMessage(),e);
		}
	}
	
	public void continueJob(String name, String group) {
		if(!getIsEnabled()){
			throw new FixFlowScheduleException(ExceptionCode.QUARZTEXCEPTION_ISENABLE);
		}
		Scheduler scheduler = getScheduler();
		try {
			scheduler.resumeJob(new JobKey(name,group));
		} catch (SchedulerException e) {
			throw new FixFlowException(e.getMessage(),e);
		}
	}
	
	public List<Trigger> getTriggerList(String jobName, String jobGroup) {
		if(!getIsEnabled()){
			throw new FixFlowScheduleException(ExceptionCode.QUARZTEXCEPTION_ISENABLE);
		}
		Scheduler scheduler = getScheduler();
		try{
			@SuppressWarnings("unchecked")
			List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(new JobKey(jobName,jobGroup));
			return triggers;
		}catch(Exception e){
			throw new FixFlowException(e.getMessage(),e);
		}
	}
	
	public void suspendTrigger(String triggerName, String triggerGroup) {
		if(!getIsEnabled()){
			throw new FixFlowScheduleException(ExceptionCode.QUARZTEXCEPTION_ISENABLE);
		}
		Scheduler scheduler = getScheduler();
		TriggerKey tKey = new TriggerKey(triggerName,triggerGroup);
		try {
			scheduler.pauseTrigger(tKey);
		} catch (SchedulerException e) {
			throw new FixFlowException(e.getMessage(),e);
		}
	}
	
	public void continueTrigger(String triggerName, String triggerGroup) {
		if(!getIsEnabled()){
			throw new FixFlowScheduleException(ExceptionCode.QUARZTEXCEPTION_ISENABLE);
		}
		Scheduler scheduler = getScheduler();
		TriggerKey tKey = new TriggerKey(triggerName,triggerGroup);
		try {
			scheduler.resumeTrigger(tKey);
		} catch (SchedulerException e) {
			throw new FixFlowException(e.getMessage(),e);
		}
	}
	
}
