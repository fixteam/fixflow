/**
 *  Copyright 1996-2013 Founder International Co.,Ltd.
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
 * @author shao
 */
package com.founder.fix.fixflow.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.founder.fix.fixflow.pojo.PageResultTo;
import com.founder.fix.fixflow.service.FlowCenterService;
import com.founder.fix.fixflow.shell.FixFlowShellProxy;

@Service
public class FlowCenterServiceImpl implements FlowCenterService {
	public PageResultTo queryMyTask(Map<String,String> filter) throws SQLException{
		PageResultTo result = new PageResultTo();
		ProcessEngine engine = FixFlowShellProxy.createProcessEngine(filter.get("userId"));
		try{
			TaskQuery tq = engine.getTaskService().createTaskQuery();
			
			tq.taskAssignee(filter.get("userId"));
			tq.processDefinitionKey(filter.get("pdkey"));
			tq.taskNotEnd();
			List<TaskInstance> lts = tq.list();
			long count = tq.count();
			
			result.setDataList(lts);
			result.setPageNumber(count);
		}finally{
			FixFlowShellProxy.closeProcessEngine(engine, false);
		}
		return result;
	}
	
	public List<Map<String,String>> queryStartProcess(String userId) throws SQLException{
		List<Map<String, String>> result = null;
		ProcessEngine engine = FixFlowShellProxy.createProcessEngine(userId);
		try{
			result =  engine.getModelService().getStartProcessByUserId(userId);
		}finally{
			FixFlowShellProxy.closeProcessEngine(engine, false);
		}
		
		return result;
	}
}
