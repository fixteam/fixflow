package com.founder.fix.fixflow.core.impl.cmd;

import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;

public class ProcessPerformanceCmd implements Command<List<Map<String, Object>>> {
	private String processKey;
	private String startTime;
	private String endTime;
	private Page page;
	
	public ProcessPerformanceCmd(String processKey, String startTime, String endTime, int firstPage, int maxSize ) {
		this.processKey = processKey;
		this.startTime = startTime;
		this.endTime = endTime;
		this.page = new Page(firstPage, maxSize);
	}

	public List<Map<String, Object>> execute(CommandContext commandContext) {
		// TODO Auto-generated method stub
		return commandContext.getProcessInstanceManager().getProcessPerformance(startTime, endTime, processKey, page);
	}
}
