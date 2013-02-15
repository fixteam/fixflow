package com.founder.fix.fixflow.core.impl.cmd;



import com.founder.fix.bpmn2extensions.coreconfig.Priority;
import com.founder.fix.bpmn2extensions.coreconfig.PriorityConfig;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class GetPriorityCmd implements Command<Priority>{
	
	protected int priorityValue=50;
	public GetPriorityCmd(int priorityValue){
		this.priorityValue=priorityValue;
	}

	public Priority execute(CommandContext commandContext) {


		
		PriorityConfig priorityConfig=commandContext.getProcessEngineConfigurationImpl().getPriorityConfig();
		
		for (Priority priority : priorityConfig.getPriority()) {
			
			int priorityConfigValue=StringUtil.getInt(priority.getValue());
			if(priorityValue==priorityConfigValue){
				return priority;
			}
		}
		
		
		return null;
	}

}
