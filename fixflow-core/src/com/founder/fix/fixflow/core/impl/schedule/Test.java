package com.founder.fix.fixflow.core.impl.schedule;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

import com.founder.fix.fixflow.core.impl.util.QuartzUtil;

public class Test {
	public void task() throws SchedulerException {
		SchedulerFactory schedulerFactory = QuartzUtil.createSchedulerFactory();
		Scheduler scheduler = QuartzUtil.getScheduler(schedulerFactory);
		Trigger tg = QuartzUtil.getTrigger(scheduler, "mytrigger");
		((SimpleTrigger)tg).getRepeatCount();
        // reschedule the job
        scheduler.rescheduleJob(new TriggerKey("mytrigger"), tg);
	}

	public static void main(String[] args) throws Exception {
		Test test = new Test();
		test.task();
	}
}
