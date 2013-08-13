package com.founder.fix.fixflow.core.impl.cmd;

import java.util.HashMap;
import java.util.Map;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;

public class ArchiveByProcessInstanceIdCmd implements Command<Boolean> {

	protected String  processInstanceId = null;
	public ArchiveByProcessInstanceIdCmd(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public Boolean execute(CommandContext commandContext) {
		ProcessInstanceEntity processInstanceQueryTo=commandContext.getProcessInstanceManager().findProcessInstanceById(processInstanceId);
		if(!processInstanceQueryTo.hasEnded()){
			throw new FixFlowException("未结束的流程实例不能被归档", null);
		}
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("PROCESSINSTANCEID", processInstanceId);
		return commandContext.getHistoryManager().archiveByProcessInstanceId(paraMap);
	}

}
