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
 * @author kenshin
 */
package com.founder.fix.fixflow.core.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.founder.fix.fixflow.core.HistoryService;
import com.founder.fix.fixflow.core.impl.cmd.ArchiveCmd;

public class HistoryServiceImpl extends ServiceImpl implements HistoryService {

	public boolean archiveByProcessInstanceId(String processinstanceId) {
		List<String> processInstanceIds = new ArrayList<String>();
		processInstanceIds.add(processinstanceId);
		return commandExecutor.execute(new ArchiveCmd(processInstanceIds, null, null, null, false));
	}
	
	public boolean archiveByProcessInstanceIds(List<String> processInstanceIds){
		return commandExecutor.execute(new ArchiveCmd(processInstanceIds, null, null, null, false));
	}
	
	public boolean archiveByProcessDefinitionKey(String processDefinitionKey) {
		return commandExecutor.execute(new ArchiveCmd(null, processDefinitionKey, null, null, false));
	}
	
	public boolean archiveBetweenTime(Date begin, Date end) {
		return commandExecutor.execute(new ArchiveCmd(null, null, begin, end, false));
	}
	
	public boolean archiveAll() {
		return commandExecutor.execute(new ArchiveCmd(null, null, null, null, true));
	}

}
