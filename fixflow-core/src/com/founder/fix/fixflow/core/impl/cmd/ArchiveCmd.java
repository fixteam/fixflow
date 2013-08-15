package com.founder.fix.fixflow.core.impl.cmd;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;

public class ArchiveCmd implements Command<Boolean> {

	protected List<String>  processInstanceIds = null;
	protected String processDefinitionKey = null;
	protected boolean isAll = false;
	protected Date begin;
	protected Date end;
	public ArchiveCmd(List<String> processInstanceIds,String processDefinitionKey,Date begin,Date end,boolean isAll) {
		this.processInstanceIds = processInstanceIds;
		this.processDefinitionKey = processDefinitionKey;
		this.begin = begin;
		this.end = end;
		this.isAll = isAll;
	}
	
	public Boolean execute(CommandContext commandContext) {
		Map<String,Object> paraMap = new HashMap<String,Object>();
		if(this.isAll){
			paraMap.put("ALL", true);
			return commandContext.getHistoryManager().archiveByProcessInstanceId(paraMap);
		}
		if(processInstanceIds != null){
			paraMap.put("PROCESSINSTANCE_ID", processInstanceIds);
			return commandContext.getHistoryManager().archiveByProcessInstanceId(paraMap);
		}
		if(processDefinitionKey != null){
			paraMap.put("PROCESSDEFINITION_KEY", processDefinitionKey);
		}
		if(begin !=null){
			paraMap.put("BEGIN", begin);
		}
		if(end != null){
			paraMap.put("END", end);
		}
		
		if(paraMap.keySet().size() == 0){
			throw new FixFlowException("归档参数异常，请检查", null);
		}
		return commandContext.getHistoryManager().archiveByProcessInstanceId(paraMap);
	}

}
