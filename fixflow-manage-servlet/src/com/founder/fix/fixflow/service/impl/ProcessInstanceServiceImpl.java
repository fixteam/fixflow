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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.founder.fix.fixflow.core.HistoryService;
import com.founder.fix.fixflow.core.IdentityService;
import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.RuntimeService;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.identity.UserTo;
import com.founder.fix.fixflow.core.impl.task.QueryExpandTo;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.ProcessInstanceQuery;
import com.founder.fix.fixflow.core.runtime.ProcessInstanceType;
import com.founder.fix.fixflow.core.runtime.Token;
import com.founder.fix.fixflow.core.runtime.TokenQuery;
import com.founder.fix.fixflow.service.ProcessInstanceService;
import com.founder.fix.fixflow.shell.CommonServiceImpl;
import com.founder.fix.fixflow.shell.FlowUtilServiceImpl;
import com.founder.fix.fixflow.util.Pagination;

/**
 * @ClassName: ProcessInstanceServiceImpl
 * @Description: 
 * @author shao
 *
 */
@Scope("prototype")
@Service
public class ProcessInstanceServiceImpl extends CommonServiceImpl implements ProcessInstanceService {

	/*
	 * <p>Title: getProcessInstances</p>
	 * <p>Description: </p>
	 * @param param
	 * @return
	 * @throws SQLException
	 * @see com.founder.fix.fixflow.service.ProcessInstanceService#getProcessInstances(java.util.Map)
	 */
	public Map<String,Object> getProcessInstances(Map<String,Object> params) throws SQLException{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String userId = StringUtil.getString(params.get("userId"));
		ProcessEngine engine = getProcessEngine(userId);
		RuntimeService runtimeService = engine.getRuntimeService();
		IdentityService identityService = engine.getIdentityService();
		FlowUtilServiceImpl flowUtil = new FlowUtilServiceImpl();
		String processName = StringUtil.getString(params.get("processName"));
		String processInstanceId    = StringUtil.getString(params.get("processInstanceId"));
		String subject				= StringUtil.getString(params.get("subject"));
		String bizKey				= StringUtil.getString(params.get("bizKey"));
		String initor				= StringUtil.getString(params.get("initor"));
		String status				= StringUtil.getString(params.get("status"));
		ProcessInstanceType processInstanceStatus = FlowUtilServiceImpl.getInstanceStaus(status);
		try{
			
			String pageI = StringUtil.getString(params.get("pageIndex"));
			String rowI = StringUtil.getString(params.get("pageSize"));
			int pageIndex=1;
			int rowNum   =10;
			if(StringUtil.isNotEmpty(pageI)){
				pageIndex = Integer.valueOf(pageI);
			}
			if(StringUtil.isNotEmpty(rowI)){
				rowNum = Integer.valueOf(rowI);
			}
			ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
			if(StringUtil.isNotEmpty(processName)){
				QueryExpandTo queryExpandTo = new QueryExpandTo();
				//增加扩展查询的left join语句
				List<Object> paraObjs = new ArrayList<Object>();
				paraObjs.add("%"+processName+"%");
				queryExpandTo.setLeftJoinSql("left join fixflow_def_processdefinition pd on PD.process_id = E.processdefinition_id");
				queryExpandTo.setWhereSql(" PD.process_name like ? ");
				queryExpandTo.setWhereSqlObj(paraObjs);
				processInstanceQuery.queryExpandTo(queryExpandTo);
				
			}
			if(StringUtil.isNotEmpty(processInstanceId))
				processInstanceQuery.processInstanceId(processInstanceId);
			if(StringUtil.isNotEmpty(subject))
				processInstanceQuery.subjectLike(subject);
			if(StringUtil.isNotEmpty(bizKey))
				processInstanceQuery.processInstanceBusinessKeyLike(bizKey);
			if(StringUtil.isNotEmpty(initor))
				processInstanceQuery.initiatorLike(initor);
			if(processInstanceStatus !=null){
				processInstanceQuery.processInstanceStatus(processInstanceStatus);
			}
			processInstanceQuery.orderByUpdateTime().desc();
			List<ProcessInstance> processInstances = processInstanceQuery.listPagination(pageIndex, rowNum);
			
			List<Map<String,Object>> instanceMaps = new ArrayList<Map<String,Object>>();
			for(ProcessInstance tmp: processInstances){
				Map<String, Object> persistentState = tmp.getPersistentState();
				String processDefinitionId = tmp.getProcessDefinitionId();
				ProcessDefinitionBehavior processDefinitionBehavior = engine.getModelService().getProcessDefinition(processDefinitionId);
				String processDefinitionName = processDefinitionBehavior.getName();
				persistentState.put("processDefinitionName", processDefinitionName);
				String nowNodeInfo = flowUtil.getShareTaskNowNodeInfo(tmp.getId()); 
				persistentState.put("nowNodeInfo", nowNodeInfo);
				UserTo user = identityService.getUserTo(tmp.getStartAuthor());
				if(user !=null){
					persistentState.put("startAuthorName", user.getUserName());
				}else{
					persistentState.put("startAuthorName", tmp.getStartAuthor());
				}
				instanceMaps.add(persistentState);
			}
			Long count = processInstanceQuery.count();
			Pagination page = new Pagination(pageIndex,rowNum);
			page.setTotal(count.intValue());
			resultMap.put("dataList", instanceMaps);
			resultMap.put("pageInfo", page);
		}finally{
			closeProcessEngine();
		}
		return resultMap;
	}
	
	
	public Map<String,Object> getProcessTokens(Map<String,Object> params) throws SQLException{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String userId = StringUtil.getString(params.get("userId"));
		ProcessEngine engine =  getProcessEngine(userId);
		try{
			RuntimeService runtimeService = engine.getRuntimeService();
			TokenQuery tokenQuery = runtimeService.createTokenQuery();
			
			String processInstanceId = StringUtil.getString(params.get("processInstanceId"));
			if(StringUtil.isNotEmpty(processInstanceId))
				tokenQuery.processInstanceId(processInstanceId);
			
			List<Token> tokenList = tokenQuery.list();
			List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
			for(Token tmp : tokenList){
				result.add(tmp.getPersistentState());
			}
			
			resultMap.put("dataList", result);
		}finally{
			closeProcessEngine();
		}
		return resultMap;
	}
	
	public Map<String,Object> getProcessVariables(Map<String,Object> params) throws SQLException{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String userId = StringUtil.getString(params.get("userId"));
		String processInstanceId = StringUtil.getString(params.get("processInstanceId"));
		ProcessEngine engine = getProcessEngine(userId);
		try{
			RuntimeService runtimeService = engine.getRuntimeService();
			Map<String, Object> dataList = runtimeService.getProcessInstanceVariables(processInstanceId);
			
			
			resultMap.put("dataList", dataList);
		}finally{
			closeProcessEngine();
		}
		return resultMap;
	}
	

	public void saveProcessVariables(Map<String,Object> params) throws SQLException{
		String userId = StringUtil.getString(params.get("userId"));
		String deleteIndex  = StringUtil.getString(params.get("deleteIndex"));
		String processInstanceId  = StringUtil.getString(params.get("processInstanceId"));
		Map<String, Object> infos  = (Map<String, Object>)params.get("insertAndUpdate");
		ProcessEngine engine = getTransactionProcessEngine(userId);
		try{
			RuntimeService runtimeService = engine.getRuntimeService();
			if(StringUtil.isNotEmpty(deleteIndex)){
				String[] keys  = deleteIndex.split(",");
				for(String tmp:keys){
					runtimeService.deleteProcessInstanceVariable(processInstanceId, tmp);
				}
			}
			if(infos!=null){
				runtimeService.setProcessInstanceVariables(processInstanceId, infos);
			}
		}finally{
			closeProcessEngine();
		}
		
	}
	
	public void suspendProcessInstance(Map<String,Object> params) throws SQLException{
		String userId = StringUtil.getString(params.get("userId"));
		String processInstanceId = StringUtil.getString(params.get("operProcessInstanceId"));
		String[] pids = processInstanceId.split(",");
		ProcessEngine engine = getTransactionProcessEngine(userId);
		try{
			RuntimeService runtimeService = engine.getRuntimeService();
			for(String tmp:pids){
				runtimeService.suspendProcessInstance(tmp);
			}
		}finally{
			closeProcessEngine();
		}
		
	}
	
	public void continueProcessInstance(Map<String,Object> params) throws SQLException{
		String userId = StringUtil.getString(params.get("userId"));
		String processInstanceId = StringUtil.getString(params.get("operProcessInstanceId"));
		String[] pids = processInstanceId.split(",");
		ProcessEngine engine = getTransactionProcessEngine(userId);
		try{
			RuntimeService runtimeService = engine.getRuntimeService();
			for(String tmp:pids){
				runtimeService.continueProcessInstance(tmp);
			}
		}finally{
			closeProcessEngine();
		}
	}
	
	public void terminatProcessInstance(Map<String,Object> params) throws SQLException{
		String userId = StringUtil.getString(params.get("userId"));
		String processInstanceId = StringUtil.getString(params.get("operProcessInstanceId"));
		String[] pids = processInstanceId.split(",");
		ProcessEngine engine = getTransactionProcessEngine(userId);
		try{
			RuntimeService runtimeService = engine.getRuntimeService();
			for(String tmp:pids){
				runtimeService.terminatProcessInstance(tmp);
			}
		}finally{
			closeProcessEngine();
		}
	}
	
	public void deleteProcessInstance(Map<String,Object> params) throws SQLException{
		String userId = StringUtil.getString(params.get("userId"));
		String processInstanceId = StringUtil.getString(params.get("operProcessInstanceId"));
		String[] pids = processInstanceId.split(",");
		ProcessEngine engine = getTransactionProcessEngine(userId);
		try{
			RuntimeService runtimeService = engine.getRuntimeService();
			for(String tmp:pids){
				runtimeService.deleteProcessInstance(tmp,true);
			}
		}finally{
			closeProcessEngine();
		}
	}
	
	public void setHistory(Map<String, Object> params) throws SQLException {
		String userId = StringUtil.getString(params.get("userId"));
		String processInstanceId = StringUtil.getString(params.get("operProcessInstanceId"));
		String[] pids = processInstanceId.split(",");
		ProcessEngine engine = getProcessEngine(userId);
		try{
			HistoryService historyService = engine.getHistoryService();
			List<String> processInstanceIds = new ArrayList<String>();
			for(String tmp:pids){
				processInstanceIds.add(tmp);
			}
			historyService.archiveByProcessInstanceIds(processInstanceIds);
		}finally{
			closeProcessEngine();
		}
	}
	
}
