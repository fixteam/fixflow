package com.founder.fix.fixflow.test.engine.api.schedule;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;

import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;

public class ScheduleServiceTest extends AbstractFixFlowTestCase {
	
	
	public void testGetSchedulerFactory() throws SchedulerException{
		SchedulerFactory schedulerFactory=scheduleService.getSchedulerFactory();
		Scheduler scheduler=schedulerFactory.getScheduler();
		//获得到 scheduler 之后就能对定时任务框架进行操作 具体操作方式请参见 quartz 使用手册
		assertNotNull(scheduler);
	}

}
