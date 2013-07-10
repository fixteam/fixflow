package com.founder.fix.fixflow.core;

import java.util.concurrent.ThreadPoolExecutor;

import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;

import com.founder.fix.fixflow.core.job.Job;

public interface ScheduleService extends ProcessService{
	
	
	/**
	 * 获取quartz引擎调度工厂对象(不推荐直接拿SchedulerFactory而是直接拿Scheduler)
	 * SchedulerFactory在系统启动的时候被创建出来并且已经Start。
	 * @return 调度工厂对象
	 */
	SchedulerFactory getSchedulerFactory();
	
	Scheduler getScheduler();
	
	/**
	 * 定时任务调度器重启
	 */
	void schedulerRestart();
	
	/**
	 * 定时任务调度器启动
	 */
	void schedulerStart();
	
	/**
	 * 定时任务调度器关闭
	 */
	void schedulerShutdown();
	
	/**
	 * 执行定时连接器
	 * @param jobExecutionContext job内容
	 */
	void executeConnectorTimeJob(JobExecutionContext jobExecutionContext);

	
	/**
	 * 获取默认的线程池
	 * @return
	 */
	ThreadPoolExecutor getThreadPoolExecutor();
	
	/**
	 * 获取指定的线程池
	 * @param threadPoolKey 线程池key
	 * @return
	 */
	ThreadPoolExecutor getThreadPoolExecutor(String threadPoolKey);
	
	
	/**
	 * 创建一个定时任务
	 * @return
	 */
	Job createJob();
	
	/**
	 * 保存一个任务
	 * @param job
	 */
	void saveJob(Job job);
	
	/**
	 * 保存一个任务
	 * @param job
	 * @param isNowPerform 是否立刻注册
	 */
    void saveJob(Job job,boolean isNowPerform);
	
}
