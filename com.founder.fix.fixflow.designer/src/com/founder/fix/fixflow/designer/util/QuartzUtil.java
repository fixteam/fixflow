package com.founder.fix.fixflow.designer.util;

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
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

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
