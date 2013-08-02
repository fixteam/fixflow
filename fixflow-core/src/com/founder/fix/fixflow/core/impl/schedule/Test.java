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
