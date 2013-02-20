package com.founder.fix.fixflow.core.impl;


import java.util.concurrent.ThreadPoolExecutor;

import org.quartz.SchedulerFactory;

import com.founder.fix.fixflow.core.ScheduleService;
import com.founder.fix.fixflow.core.impl.cmd.GetSchedulerFactoryCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetThreadPoolExecutorCmd;

public class ScheduleServiceImpl extends ServiceImpl implements ScheduleService {

	public SchedulerFactory getSchedulerFactory() {
		
		return commandExecutor.execute(new GetSchedulerFactoryCmd());
	}

	public ThreadPoolExecutor getThreadPoolExecutor() {

		return commandExecutor.execute(new GetThreadPoolExecutorCmd(null));
	}

	public ThreadPoolExecutor getThreadPoolExecutor(String threadPoolKey) {
		return commandExecutor.execute(new GetThreadPoolExecutorCmd(threadPoolKey));
	}
	
	
	
	
}
