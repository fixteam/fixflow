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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.impl.util.DateUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.ProcessInstanceQuery;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.founder.fix.fixflow.service.FlowCenterService;
import com.founder.fix.fixflow.shell.FixFlowShellProxy;

@Service
public class FlowCenterServiceImpl implements FlowCenterService {
	/*
	  * <p>Title: queryMyTaskNotEnd</p>
	  * <p>Description: </p>
	  * @param filter
	  * @return
	  * @throws SQLException
	  * @see com.founder.fix.fixflow.service.FlowCenterService#queryMyTaskNotEnd(java.util.Map)
	  */
	public Map<String,Object> queryMyTaskNotEnd(Map<String, Object> filter)
			throws SQLException {
		Map<String,Object> result = new HashMap<String,Object>();
		ProcessEngine engine = FixFlowShellProxy.createProcessEngine(filter
				.get("userId"));
		try {
			TaskQuery tq = engine.getTaskService().createTaskQuery();
			
			tq.taskAssignee(StringUtil.getString(filter.get("userId")));
			tq.taskCandidateUser(StringUtil.getString(filter.get("userId")));
//			tq.processDefinitionKey(StringUtil.getString(filter.get("pdkey")));
			
			String descritpion = StringUtil.getString(filter.get("title"));
			if(StringUtil.isNotEmpty(descritpion))
				tq.taskDescriptionLike(descritpion);
			
			String initor	   = StringUtil.getString(filter.get("initor"));
			if(StringUtil.isNotEmpty(initor))
				tq.initiatorLike(initor);
			Date dates = null;
			Date datee = null;
			String dss = StringUtil.getString(filter.get("arrivalTimeS"));
			String dse = StringUtil.getString(filter.get("arrivalTimeE"));
			if(StringUtil.isNotEmpty(dss)){
				dates = DateUtil.stringToDate(dss,"yyyy-MM-dd");
			}
			if(StringUtil.isNotEmpty(dse)){
				datee = DateUtil.stringToDate(dse,"yyyy-MM-dd");
			}
			if(dates!=null)
				tq.taskCreatedAfter(dates);
			
			if(datee!=null)
				tq.taskCreatedBefore(datee);
			
			String pageI = StringUtil.getString(filter.get("pageIndex"));
			String rowI = StringUtil.getString(filter.get("rowNum"));
			
			int pageIndex=1;
			int rowNum   =5;
			if(StringUtil.isNotEmpty(pageI)){
				pageIndex = Integer.valueOf(pageIndex);
			}
			if(StringUtil.isNotEmpty(rowI)){
				rowNum = Integer.valueOf(rowI);
			}
			
			if(filter.get("ended")==null)
				tq.taskNotEnd();
			
			if(filter.get("agentUserId")!=null){
				tq.isAgent(true);
				if(filter.get("agentType").equals("1")){
					tq.taskAssignee(StringUtil.getString(filter.get("userId")));
					tq.taskCandidateUser(StringUtil.getString(filter.get("userId")));
					tq.agentId(StringUtil.getString(filter.get("agentUserId")));
				}else{
					tq.taskAssignee(StringUtil.getString(filter.get("agentUserId")));
					tq.taskCandidateUser(StringUtil.getString(filter.get("agentUserId")));
					tq.agentId(StringUtil.getString(filter.get("userId")));
				}
			}else{
				tq.taskAssignee(StringUtil.getString(filter.get("userId")));
				tq.taskCandidateUser(StringUtil.getString(filter.get("userId")));
			}
			
			List<TaskInstance> lts = tq.listPagination(pageIndex, rowNum);
			long count = tq.count();
			List<Map<String,Object>> instanceMaps = new ArrayList<Map<String,Object>>();
			for(TaskInstance tmp:lts){
				instanceMaps.add(tmp.getPersistentState());
			}
			result.put("dataList", instanceMaps);
			result.put("pageNumber", count);
			result.put("agentUsers", getAgentUsers(engine,StringUtil.getString(filter.get("userId"))));
			result.put("agentToUsers", getAgentToUsers(engine,StringUtil.getString(filter.get("userId"))));
		} finally {
			FixFlowShellProxy.closeProcessEngine(engine, false);
		}
		return result;
	}
	
	public Map<String,Object> queryMyTaskEnded(Map<String, Object> filter)
			throws SQLException {
		filter.put("ended", "ended");
		return queryMyTaskNotEnd(filter);
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

	public Map<String,Object> queryTaskParticipants(Map<String,Object> filter) throws SQLException {
		filter.put("queryPart", "queryPart");
		return queryTaskInitiator(filter);
	}

	public Map<String,Object> queryTaskInitiator(Map<String,Object> filter) throws SQLException {
		Map<String,Object> result = new HashMap<String,Object>();
		String userId = (String) filter.get("userId");
		ProcessEngine engine = FixFlowShellProxy.createProcessEngine(userId);
		try{
			ProcessInstanceQuery tq = engine.getRuntimeService()
					.createProcessInstanceQuery();
			
			String descritpion = StringUtil.getString(filter.get("title"));
			if(StringUtil.isNotEmpty(descritpion))
				tq.subjectLike(descritpion);
			
			String initor	   = StringUtil.getString(filter.get("initor"));
			if(StringUtil.isNotEmpty(initor))
				tq.initiatorLike(initor);
			Date dates = null;
			Date datee = null;
			String dss = StringUtil.getString(filter.get("arrivalTimeS"));
			String dse = StringUtil.getString(filter.get("arrivalTimeE"));
			if(StringUtil.isNotEmpty(dss)){
				dates = DateUtil.stringToDate(dss,"yyyy-MM-dd");
			}
			if(StringUtil.isNotEmpty(dse)){
				datee = DateUtil.stringToDate(dse,"yyyy-MM-dd");
			}
			if(dates!=null)
				tq.startTimeAfter(dates);
			
			if(datee!=null)
				tq.startTimeBefore(datee);
			
			String pageI = StringUtil.getString(filter.get("pageIndex"));
			String rowI = StringUtil.getString(filter.get("rowNum"));
			
			int pageIndex=1;
			int rowNum   =5;
			if(StringUtil.isNotEmpty(pageI)){
				pageIndex = Integer.valueOf(pageIndex);
			}
			if(StringUtil.isNotEmpty(rowI)){
				rowNum = Integer.valueOf(rowI);
			}
			
			if(filter.get("ended")!=null)
				tq.isEnd();
			
			List<ProcessInstance> instances = null;
			if(filter.get("queryPart")==null)
				instances = tq.initiator(userId).listPagination(pageIndex, rowNum);
			else
				instances = tq.taskParticipants(userId).listPagination(pageIndex, rowNum);
			long count = tq.count();
			List<Map<String,Object>> instanceMaps = new ArrayList<Map<String,Object>>();
			for(ProcessInstance tmp:instances){
				instanceMaps.add(tmp.getPersistentState());
			}
			result.put("dataList", instanceMaps);
			result.put("pageNumber", count);
		}finally{
			FixFlowShellProxy.closeProcessEngine(engine, false);
		}
		return result;
	}
	
	public Map<String,Object> getTaskDetailInfo(Map<String,Object> filter) throws SQLException{
		String processInstanceId = StringUtil.getString(filter.get("processInstanceId"));
//		String processId		 = StringUtil.getString(filter.get(""));
		
		Map<String,Object> result = new HashMap<String,Object>();
		String userId = (String) filter.get("userId");
		ProcessEngine engine = FixFlowShellProxy.createProcessEngine(userId);
		try{
			TaskQuery tq = engine.getTaskService().createTaskQuery();
			
			tq.processInstanceId(processInstanceId);
			tq.taskIsEnd().orderByEndTime().asc().orderByTaskCreateTime().asc();
			List<TaskInstance> instances = tq.list();
			List<Map<String,Object>> instanceMaps = new ArrayList<Map<String,Object>>();
			for(TaskInstance tmp:instances){
				instanceMaps.add(tmp.getPersistentState());
			}
			
			result.put("dataList", instanceMaps);
		}finally{
			FixFlowShellProxy.closeProcessEngine(engine, false);
		}
		return result;
	}
	
	
	public InputStream getFlowGraph(Map<String,Object> filter) throws SQLException{
		String processInstanceId = StringUtil.getString(filter.get("processDefinitionId"));
		String processDefinitionKey = StringUtil.getString(filter.get("processDefinitionKey"));
		InputStream result = null;
		
		String userId = (String) filter.get("userId");
		ProcessEngine engine = FixFlowShellProxy.createProcessEngine(userId);
		
		try{
			if(StringUtil.isNotEmpty(processInstanceId))
				result = engine.getModelService().GetFlowGraphicsImgStreamByDefId(processInstanceId);
			else
				result = engine.getModelService().GetFlowGraphicsImgStreamByDefKey(processDefinitionKey);
		}finally{
			FixFlowShellProxy.closeProcessEngine(engine, false);
		}
		
		return result;
	}
	
}
