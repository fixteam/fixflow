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
package com.founder.fix.fixflow.core.impl.util;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;
import java.util.Properties;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public class QuartzUtil {
	/**
	 * 创建定时任务工厂
	 * @return
	 */
	public static SchedulerFactory createSchedulerFactory() {
		SchedulerFactory sf = new StdSchedulerFactory();
		return sf;
	}
	
	/**
	 * 根据传入的属性文件创建定时任务工厂
	 * @param props
	 * @return
	 */
	public static SchedulerFactory createSchedulerFactory(Properties props) {
		SchedulerFactory sf = null;
		try {
			sf = new StdSchedulerFactory(props);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sf;
	}
	
	/**
	 * 根据任务工厂拿到定时任务
	 * @param schedulerFactory 任务工厂
	 * @return
	 */
	public static Scheduler getScheduler(SchedulerFactory schedulerFactory) {
		Scheduler scheduler = null;
		try {
			scheduler = schedulerFactory.getScheduler();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scheduler;
	}
	
	/**
	 * 创建作业
	 * @param jobClass 实现Job接口的类
	 * @param jobName 作业名称
	 * @param groupName 组名称
	 * @return
	 */
	public static JobDetail createJobDetail(Class<? extends Job> jobClass, String jobName, String groupName) {
		JobDetail job = newJob(jobClass)
        .withIdentity(jobName, groupName)
        .build();
		return job;
	}
		
	/**
	 * 创建简单触发器
	 * @param jobName 作业名
	 * @param groupName 组名
	 * @param DataTime 启动时间
	 * @return
	 */
	public static Trigger createSimpleTrigger(String jobName, String groupName, Date DataTime) {
		 Trigger trigger = newTrigger()
         .withIdentity(jobName, groupName)
         .startAt(DataTime)
         .build();
		return trigger;
	}
	
	/**
	 * 创建复杂触发器
	 * @param jobName 作业名
	 * @param groupName 组名
	 * @param cronExpression cron表达式
	 * @return
	 */
	public static Trigger createCronTrigger(String jobName, String groupName, String cronExpression) {
		 CronTrigger trigger = newTrigger()
         .withIdentity(jobName, groupName)
         .withSchedule(cronSchedule(cronExpression))
         .build();
		return trigger;
	}
	
	/**
	 * 创建简单触发器
	 * @param jobName 作业名
	 * @param groupName 组名
	 * @param DataTime 启动时间
	 * @return
	 */
	public static Trigger createSimpleTrigger(ExecutionContext executionContext, Date DataTime) {
		 Trigger trigger = newTrigger()
         .withIdentity(GuidUtil.CreateGuid(), executionContext.getProcessDefinition().getId())
         .startAt(DataTime)
         .build();
		return trigger;
	}
	
	
	/**
	 * 创建复杂触发器
	 * @param jobName 作业名
	 * @param groupName 组名
	 * @param cronExpression cron表达式
	 * @return
	 */
	public static Trigger createCronTrigger(ExecutionContext executionContext, String cronExpression) {
		 CronTrigger trigger = newTrigger()
         .withIdentity(GuidUtil.CreateGuid(), executionContext.getProcessDefinition().getId())
         .withSchedule(cronSchedule(cronExpression))
         .build();
		return trigger;
	}
	
	/**
	 * 创建复杂触发器
	 * @param jobName 作业名
	 * @param groupName 组名
	 * @param cronExpression cron表达式
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	public static Trigger createCronTrigger(ExecutionContext executionContext, String cronExpression,Date startTime,Date endTime) {
		
		TriggerBuilder<CronTrigger> triggerBuilder=newTrigger()
		         .withIdentity(GuidUtil.CreateGuid(), executionContext.getProcessDefinition().getId())
		         .withSchedule(cronSchedule(cronExpression));
		
		if(startTime!=null){
			triggerBuilder.startAt(startTime);
		}
		if(endTime!=null){
			triggerBuilder.endAt(endTime);
		}
		
		 CronTrigger trigger = triggerBuilder
         .build();
		return trigger;
	}
	
	
	
	/**
	 * 根据定时任务和作业名称得到作业
	 * @param scheduler 定时任务
	 * @param jobKey 作业名称
	 * @return
	 */
	public static JobDetail getJobDetail(Scheduler scheduler, String jobKey) {
		JobDetail jobDetail = null;
		try {
			jobDetail = scheduler.getJobDetail(new JobKey(jobKey));
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jobDetail;
	}
	
	/**
	 * 根据定时任务和触发器名称得到触发器
	 * @param scheduler 定时任务
	 * @param triggerKey 触发器名称
	 * @return
	 */
	public static Trigger getTrigger(Scheduler scheduler, String triggerKey) {
		Trigger trigger = null;
		try {
			trigger = scheduler.getTrigger(new TriggerKey(triggerKey));
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trigger;
	}
	
	 
}
