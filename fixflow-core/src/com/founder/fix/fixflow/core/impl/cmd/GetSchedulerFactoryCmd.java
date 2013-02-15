package com.founder.fix.fixflow.core.impl.cmd;


import org.quartz.SchedulerFactory;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;


public class GetSchedulerFactoryCmd implements Command<SchedulerFactory>{

	
	public GetSchedulerFactoryCmd(){
		
	}
	
	public SchedulerFactory execute(CommandContext commandContext) {
		// TODO Auto-generated method stub
		
		return Context.getProcessEngineConfiguration().getSchedulerFactory();
	}

}
