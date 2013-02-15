package com.founder.fix.fixflow.core.impl.cmd;

import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;

public class ProcessPerformanceInterface5Cmd implements Command<List<Map<String, Object>>> {
	private String startTime;
	private String endTime;
	private String pid;

	public ProcessPerformanceInterface5Cmd(String startTime, String endTime, String pid) {
		// TODO Auto-generated constructor stub
		this.startTime = startTime;
		this.endTime = endTime;
		this.pid = pid;
	}

	public List<Map<String, Object>> execute(CommandContext commandContext) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = commandContext.getProcessInstanceManager().getProcessPerformance(startTime, endTime, pid);
		
		for (Map<String, Object> map : list) {
			String name = commandContext.getProcessEngineConfigurationImpl().getTaskCommandDefMap().get(map.get("COMMAND_TYPE").toString()).getName();
			map.put("COMMAND_TYPE", name);
		}
		
		
		return list;
	}

}
