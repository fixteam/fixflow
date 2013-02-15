package com.founder.fix.fixflow.core.impl;


import org.quartz.SchedulerFactory;

import com.founder.fix.fixflow.core.ScheduleService;
import com.founder.fix.fixflow.core.impl.cmd.GetSchedulerFactoryCmd;

public class ScheduleServiceImpl extends ServiceImpl implements ScheduleService {

	public SchedulerFactory getSchedulerFactory() {
		// TODO Auto-generated method stub
		return commandExecutor.execute(new GetSchedulerFactoryCmd());
	}
	
	
	
	
}
