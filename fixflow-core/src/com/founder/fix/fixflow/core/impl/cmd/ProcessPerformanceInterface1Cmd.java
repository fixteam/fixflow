package com.founder.fix.fixflow.core.impl.cmd;

import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;

public class ProcessPerformanceInterface1Cmd implements Command<List<Map<String, Object>>> {
	private String startTime;
	private String endTime;
	
	public ProcessPerformanceInterface1Cmd(String startTime, String endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public List<Map<String, Object>> execute(CommandContext commandContext) {
		// TODO Auto-generated method stub
		return commandContext.getProcessInstanceManager().getProcessPerformance(startTime, endTime);
	}

}
