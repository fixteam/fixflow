package com.founder.fix.fixflow.core.impl.cmd;

import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;

public class GetTaskStatusByByProcessInstanceIdCmd  implements Command<List<Map<String, Object>>>{

	protected List<String> processInstanceIdList;
	
	public GetTaskStatusByByProcessInstanceIdCmd(List<String> processInstanceIdList){
		this.processInstanceIdList=processInstanceIdList;
	}
	
	public List<Map<String, Object>> execute(CommandContext commandContext) {
		if(processInstanceIdList==null||processInstanceIdList.size()==0){
			return null;
		}
		return commandContext.getTaskManager().getTaskStatusByByProcessInstanceIdList(processInstanceIdList);
	}

}
