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

import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.ProcessInstanceQuery;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.founder.fix.fixflow.service.FlowCenterService;
import com.founder.fix.fixflow.shell.FixFlowShellProxy;

@Service
public class FlowCenterServiceImpl implements FlowCenterService {
	public Map<String,Object> queryMyTaskNotEnd(Map<String, String> filter)
			throws SQLException {
		Map<String,Object> result = new HashMap<String,Object>();
		ProcessEngine engine = FixFlowShellProxy.createProcessEngine(filter
				.get("userId"));
		try {
			TaskQuery tq = engine.getTaskService().createTaskQuery();

			tq.taskAssignee(filter.get("userId"));
			tq.processDefinitionKey(filter.get("pdkey"));
			int pageIndex = Integer.valueOf(filter.get("pageIndex"));
			int rowNum = Integer.valueOf(filter.get("rowNum"));
			
			tq.taskNotEnd();
			List<TaskInstance> lts = tq.listPagination(pageIndex, rowNum);
			long count = tq.count();

			result.put("dataList", lts);
			result.put("pageNumber", count);
			result.put("agentUsers", getAgentUsers(engine,filter.get("userId")));
			result.put("agentToUsers", getAgentToUsers(engine,filter.get("userId")));
		} finally {
			FixFlowShellProxy.closeProcessEngine(engine, false);
		}
		return result;
	}
	
	public Map<String,Object> queryMyTaskEnded(Map<String, String> filter)
			throws SQLException {
		Map<String,Object> result = new HashMap<String,Object>();
		ProcessEngine engine = FixFlowShellProxy.createProcessEngine(filter
				.get("userId"));
		try {
			TaskQuery tq = engine.getTaskService().createTaskQuery();

			tq.taskAssignee(filter.get("userId"));
			tq.processDefinitionKey(filter.get("pdkey"));
			int pageIndex = Integer.valueOf(filter.get("pageIndex"));
			int rowNum = Integer.valueOf(filter.get("rowNum"));
			tq.taskNotEnd();
			List<TaskInstance> lts = tq.listPagination(pageIndex, rowNum);
			long count = tq.count();

			result.put("dataList", lts);
			result.put("pageNumber", count);
			result.put("agentUsers", getAgentUsers(engine,filter.get("userId")));
			result.put("agentToUsers", getAgentToUsers(engine,filter.get("userId")));
		} finally {
			FixFlowShellProxy.closeProcessEngine(engine, false);
		}
		return result;
	}

	public List<Map<String, String>> queryStartProcess(String userId)
			throws SQLException {
		List<Map<String, String>> result = null;
		ProcessEngine engine = FixFlowShellProxy.createProcessEngine(userId);
		try {
			result = engine.getModelService().getStartProcessByUserId(userId);
		} finally {
			FixFlowShellProxy.closeProcessEngine(engine, false);
		}

		return result;
	}

	public List<Map<String, Object>> getAgentUsers(ProcessEngine engine, String targetId) {
		return engine.getTaskService().getAgentUsersAndCount(targetId);
	}

	public List<Map<String, Object>> getAgentToUsers(ProcessEngine engine, String targetId) {
		return engine.getTaskService().getAgentToUsersAndCount(targetId);
	}

	public InputStream queryStartProcessImage(String id) throws SQLException {
		return null;
	}

	public Map<String,Object> queryTaskParticipants(Map<String,String> filter) throws SQLException {
		Map<String,Object> result = new HashMap<String,Object>();
		String userId = (String) filter.get("userId");
		ProcessEngine engine = FixFlowShellProxy.createProcessEngine(userId);
		ProcessInstanceQuery query = engine.getRuntimeService()
				.createProcessInstanceQuery();
		List<ProcessInstance> instances = query.taskParticipants(userId).list();
		result.put("dataList", instances);
		return result;
	}

	public Map<String,Object> queryTaskInitiator(Map<String,String> filter) throws SQLException {
		Map<String,Object> result = new HashMap<String,Object>();
		String userId = (String) filter.get("userId");
		ProcessEngine engine = FixFlowShellProxy.createProcessEngine(userId);
		ProcessInstanceQuery query = engine.getRuntimeService()
				.createProcessInstanceQuery();
		List<ProcessInstance> instances = query.initiator(userId).list();
		result.put("dataList", instances);
		return result;
	}
}
