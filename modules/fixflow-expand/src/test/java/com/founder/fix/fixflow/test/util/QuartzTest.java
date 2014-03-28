/**
 *  Copyright 1996-2013 Founder International Co.,Ltd.
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
 * @author Administrator
 */
package com.founder.fix.fixflow.test.util;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import com.founder.fix.fixflow.core.impl.schedule.SimpleJob;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;

/**
 * @ClassName: QuartzTest
 * @Description: TODO
 * @author Administrator
 * @date 2013-8-20 上午10:31:34
 * 
 */
public class QuartzTest extends AbstractFixFlowTestCase {
	@Override
	protected void setUp() throws Exception {

		// 初始化测试方法
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		// 测试完毕清理方法
		super.tearDown();

	}

	public void testCronTrigger() throws SchedulerException {
		// SchedulerFactory
		// schedulerFactory=scheduleService.getSchedulerFactory();
		// Scheduler scheduler=schedulerFactory.getScheduler();
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		assertNotNull(scheduler);
		scheduler.start();

		JobDetail job = newJob(SimpleJob.class).withIdentity("job1", "group1")
				.build();

		Date nowDate = new Date();
		long startDate = nowDate.getTime() + (24 * 60 * 60 * 1000);
		Date triggerStartTime = new Date(startDate);
		@SuppressWarnings("deprecation")
		int hour = nowDate.getHours();

		String cronExpr = "* * 7/24 * * ?";
		cronExpr = "* * "+hour+"/24 ? * 2-6";
		System.out.println("cronExpr:" + cronExpr);
		CronTrigger trigger = newTrigger().withIdentity("trigger3", "group1")
				.withSchedule(cronSchedule(cronExpr)).startAt(triggerStartTime)
				.forJob("job1", "group1").build();
		scheduler.scheduleJob(job, trigger);
		// trigger.
		Date date = trigger.getNextFireTime();

		// trigger.
		// trigger.get
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		System.out.println("下次执行时间:" + df.format(date));

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, +24);
		System.out.println("Calendar:" + cal.get(Calendar.HOUR_OF_DAY));

		scheduler.shutdown();

		assertTrue(true);
	}

}
