/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author ych
 */
package com.founder.fix.fixflow.core.impl.cmd;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;

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
			throw new FixFlowException("归档参数异常，请检查");
		}
		return commandContext.getHistoryManager().archiveByProcessInstanceId(paraMap);
	}

}
