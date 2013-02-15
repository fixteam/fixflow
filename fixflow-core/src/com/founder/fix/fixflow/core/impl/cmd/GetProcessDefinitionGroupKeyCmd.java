package com.founder.fix.fixflow.core.impl.cmd;

import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;

public class GetProcessDefinitionGroupKeyCmd  implements Command<List<Map<String, Object>>>  {

	public GetProcessDefinitionGroupKeyCmd(){
		
		
	}
	
	
	public List<Map<String, Object>> execute(CommandContext commandContext) {
		List<Map<String, Object>> listMap=commandContext.getProcessDefinitionManager().selectProcessDefinitionGroupKey();
		return listMap;
	}

}
