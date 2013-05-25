package com.founder.fix.fixflow.core.impl.cmd;

import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class ProcessPerformanceInterface1Cmd implements Command<List<Map<String, Object>>> {
	private String startTime;
	private String endTime;
	
	public ProcessPerformanceInterface1Cmd(String startTime, String endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public List<Map<String, Object>> execute(CommandContext commandContext) {
		// TODO Auto-generated method stub
		
		ProcessDefinitionManager processDefinitionManager=commandContext.getProcessDefinitionManager();
		
		List<Map<String, Object>> returnData=commandContext.getProcessInstanceManager().getProcessPerformance(startTime, endTime);
		
		
		for (Map<String, Object> map : returnData) {
			ProcessDefinitionBehavior processDefinitionBehavior=processDefinitionManager.findLatestProcessDefinitionByKey(StringUtil.getString(map.get("PROCESS_KEY")));
			if(processDefinitionBehavior!=null){
				map.put("PROCESS_NAME", processDefinitionBehavior.getName());
			}
		}
		
		return returnData;
	}

}
