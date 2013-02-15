package com.founder.fix.fixflow.core.impl.cmd;

import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;

public class ProcessPerformanceInterface4Cmd implements Command<List<Map<String, Object>>> {
	private String[] processKey;
	private String startTime;
	private String endTime;

	public ProcessPerformanceInterface4Cmd(String[] processKey, String startTime, String endTime) {
		// TODO Auto-generated constructor stub
		this.processKey = processKey;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public List<Map<String, Object>> execute(CommandContext commandContext) {
		// TODO Auto-generated method stub
		return commandContext.getProcessInstanceManager().getProcessPerformance(processKey, startTime, endTime);
	}

}
