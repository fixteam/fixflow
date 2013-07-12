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

import com.founder.fix.bpmn2extensions.coreconfig.DBType;
import com.founder.fix.bpmn2extensions.coreconfig.DataBase;
import com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig;
import com.founder.fix.bpmn2extensions.coreconfig.QuartzConfig;

public class QuartzUtil {
	
	private static Properties getQuartzProps() {
		QuartzConfig quartzConfig = FixFlowConfigUtil.getFixFlowConfig().getQuartzConfig();
		FixFlowConfig fixFlowConfig = FixFlowConfigUtil.getFixFlowConfig();

		String DBDRIVER = "";
		String DBURL = "";
		String DBUSER = "";
		String DBPASSWORD = "";
		String driverDelegateClass = "";

		if (quartzConfig.getIsDefaultConfig().equals("true")) {
			DBDRIVER = FixFlowConfigUtil.getSelectedDataBase().getDriverClassName();
			DBURL = FixFlowConfigUtil.getSelectedDataBase().getUrl();
			DBUSER = FixFlowConfigUtil.getSelectedDataBase().getUsername();
			DBPASSWORD = FixFlowConfigUtil.getSelectedDataBase().getPassword();
			if (FixFlowConfigUtil.getSelectedDataBase().getDbtype().equals(DBType.ORACLE)) {
				driverDelegateClass = "org.quartz.impl.jdbcjobstore.oracle.OracleDelegate";
			} else {
				
				if(FixFlowConfigUtil.getSelectedDataBase().getDbtype().equals(DBType.SQLSERVER))
				{
					driverDelegateClass = "org.quartz.impl.jdbcjobstore.MSSQLDelegate";
				}
				else{
					driverDelegateClass = "org.quartz.impl.jdbcjobstore.StdJDBCDelegate";
				}
			}
		} else {
			for (DataBase dataBase : fixFlowConfig.getDataBaseConfig().getDataBase()) {
				if (dataBase.getId().equals(quartzConfig.getDataBaseId())) {
					DBDRIVER = dataBase.getDriverClassName();
					DBURL = dataBase.getUrl();
					DBUSER = dataBase.getUsername();
					DBPASSWORD = dataBase.getPassword();
					
					if (dataBase.getDbtype().equals(DBType.ORACLE)) {
						driverDelegateClass = "org.quartz.impl.jdbcjobstore.oracle.OracleDelegate";
						// driverDelegateClass =
						// "org.quartz.impl.jdbcjobstore.oracle.OracleDelegate";//org.quartz.impl.jdbcjobstore.StdJDBCDelegate
					} else {
						
						if(dataBase.getDbtype().equals(DBType.SQLSERVER))
						{
							driverDelegateClass = "org.quartz.impl.jdbcjobstore.MSSQLDelegate";
						}
						else{
							driverDelegateClass = "org.quartz.impl.jdbcjobstore.StdJDBCDelegate";
						}
						
					}
				}
			}
		}

		Properties props = new Properties();
		props.put("org.quartz.scheduler.instanceName", "FixFlowQuartzScheduler");
		props.put("org.quartz.scheduler.instanceId", "AUTO");
		props.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
		props.put("org.quartz.threadPool.threadCount", "3");
		props.put("org.quartz.threadPool.threadPriority", "5");
		props.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
		props.put("org.quartz.jobStore.driverDelegateClass", driverDelegateClass);
		props.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
		props.put("org.quartz.jobStore.dataSource", "fixDS");
		props.put("org.quartz.jobStore.isClustered", "false");
		props.put("org.quartz.dataSource.fixDS.driver", DBDRIVER);
		props.put("org.quartz.dataSource.fixDS.URL", DBURL);
		props.put("org.quartz.dataSource.fixDS.user", DBUSER);
		props.put("org.quartz.dataSource.fixDS.password", DBPASSWORD);
		props.put("org.quartz.dataSource.fixDS.maxConnections", "5");

		return props;
	}
	
	public static Scheduler schedulerInstant;
	
	public static Scheduler getScheduler() {
		if(schedulerInstant==null){
			try {
				schedulerInstant=createSchedulerFactory(getQuartzProps()).getScheduler();
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return schedulerInstant;
	}
	
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
