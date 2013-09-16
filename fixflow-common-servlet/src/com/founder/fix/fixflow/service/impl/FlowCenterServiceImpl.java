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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.founder.fix.bpmn2extensions.coreconfig.AllUserInfo;
import com.founder.fix.fixflow.core.IdentityService;
import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.RuntimeService;
import com.founder.fix.fixflow.core.TaskService;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.identity.GroupDefinition;
import com.founder.fix.fixflow.core.impl.identity.GroupTo;
import com.founder.fix.fixflow.core.impl.identity.UserTo;
import com.founder.fix.fixflow.core.impl.task.QueryExpandTo;
import com.founder.fix.fixflow.core.impl.util.DateUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.ProcessInstanceQuery;
import com.founder.fix.fixflow.core.runtime.ProcessInstanceType;
import com.founder.fix.fixflow.core.task.IdentityLink;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.founder.fix.fixflow.service.FlowCenterService;
import com.founder.fix.fixflow.shell.CommonServiceImpl;
import com.founder.fix.fixflow.shell.FixFlowShellProxy;
import com.founder.fix.fixflow.shell.FlowUtilServiceImpl;
import com.founder.fix.fixflow.util.FileUtil;
import com.founder.fix.fixflow.util.ImageCutUtil;
import com.founder.fix.fixflow.util.JSONUtil;
import com.founder.fix.fixflow.util.Pagination;
@Scope("prototype")
@Service
public class FlowCenterServiceImpl extends CommonServiceImpl implements FlowCenterService {

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
		ProcessEngine engine = getProcessEngine(filter
				.get("userId"));
		
		String whereSql = " 1=1 ";
		String leftJoinStr = "";

		QueryExpandTo queryExpandTo = new QueryExpandTo();
		
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
				tq.initiator(initor);
			
			//发起人模糊匹配
			String initorName = StringUtil.getString(filter.get("initorName"));
			if (StringUtil.isNotEmpty(initorName)) {
				initorName = initorName.replace("'", "");
				ProcessEngine processEngine = ProcessEngineManagement
						.getDefaultProcessEngine();
				AllUserInfo userInfoConfig = processEngine
						.getProcessEngineConfiguration().getUserDefinition()
						.getUserInfoConfig();
				leftJoinStr += " LEFT JOIN (" + userInfoConfig.getSqlText()
						+ ") UT on UT." + userInfoConfig.getUserIdField()
						+ " = P.INITIATOR ";
				whereSql += " and (UT." + userInfoConfig.getUserNameField()
						+ " LIKE '%" + initorName + "%'  or UT."
						+ userInfoConfig.getUserIdField() + " = '"
						+ initorName + "')";
			}

			
			String bizKey	   = StringUtil.getString(filter.get("bizKey"));
			if(StringUtil.isNotEmpty(bizKey))
				tq.businessKeyLike(bizKey);
			
			Date dates = null;
			Date datee = null;
			String dss = StringUtil.getString(filter.get("arrivalTimeS"));
			String dse = StringUtil.getString(filter.get("arrivalTimeE"));
			if(StringUtil.isNotEmpty(dss)){
				dates = DateUtil.stringToDate(dss,"yyyy-MM-dd");
			}
			if(StringUtil.isNotEmpty(dse)){
				String endTime = "235959999";
				dse += endTime;
				datee = DateUtil.stringToDate(dse,"yyyy-MM-ddHHmmssSSS");
			}
			if(dates!=null)
				tq.taskCreatedAfter(datee);
			
			if(datee!=null)
				tq.taskCreatedBefore(dates);
			
			String pageI = StringUtil.getString(filter.get("pageIndex"));
			String rowI = StringUtil.getString(filter.get("pageSize"));
			
			int pageIndex=1;
			int rowNum   =10;
			if(StringUtil.isNotEmpty(pageI)){
				pageIndex = Integer.valueOf(pageI);
			}
			if(StringUtil.isNotEmpty(rowI)){
				rowNum = Integer.valueOf(rowI);
			}
			
			if(filter.get("ended")==null)
				tq.taskNotEnd();
			
			if(StringUtil.isNotEmpty(StringUtil.getString(filter.get("agentUserId")))){
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
			
			
			if (StringUtil.isNotEmpty(leftJoinStr)) {
				queryExpandTo.setLeftJoinSql(leftJoinStr);
			}
			queryExpandTo.setWhereSql(whereSql);
			tq.queryExpandTo(queryExpandTo);
			
			List<TaskInstance> lts = tq.orderByTaskCreateTime().desc().listPagination(pageIndex, rowNum);
			Long count = tq.count();
			List<Map<String,Object>> instanceMaps = new ArrayList<Map<String,Object>>();
			
			Pagination page = new Pagination(pageIndex,rowNum);
			page.setTotal(count.intValue());
			IdentityService identsvz = engine.getIdentityService();
			
			for(TaskInstance tmp:lts){ 
				Map<String,Object> instances = tmp.getPersistentState();
//				String path = StringUtil.getString(filter.get("path"));
//				path = path+"/icon/";
//				File newFile = new File(path);
//				FileUtil.makeParent(new File(path+"ss.ss"));
//				
//				String[] icons = newFile.list();
				String userId = StringUtil.getString(instances.get("PI_START_AUTHOR"));
//				for(String tmp2:icons){
//					if(tmp2.startsWith(userId)){
//						instances.put("icon", "icon/"+tmp2);
//					}
//				}
				
//				instances.put("icon", "icon/"+userId+"_small.png");
				if(StringUtil.isNotEmpty(userId)){
					UserTo user = identsvz.getUserTo(userId);
					if(user!=null){
						instances.put("userName", user.getUserName());
					}else{
						instances.put("userName", userId+"(未知用户)");
					}
				}else{
					instances.put("userName", "(空用户名)");
				}
				instanceMaps.add(instances);
			}
			result.put("dataList", instanceMaps);
			result.put("pageInfo", page);
			
			result.put("agentUsers", getAgentUsers(engine,StringUtil.getString(filter.get("userId"))));
			result.put("agentToUsers", getAgentToUsers(engine,StringUtil.getString(filter.get("userId"))));
		} finally {
			closeProcessEngine();
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
		ProcessEngine engine = getProcessEngine(userId);
		try {
			result = engine.getModelService().getStartProcessByUserId(userId);
			for(Map<String,String> tmp:result){
				String formUrl = tmp.get("startFormKey");
				//String formUrl = engine.getFormService().getStartFormByKey(pdkey);
				
				tmp.put("formUrl", formUrl);
			}
		} finally {
			closeProcessEngine();
		}

		return result;
	}
	
	public List<Map<String, String>> queryLastestProcess(String userid) throws SQLException {
		List<Map<String, String>> result = null;
		ProcessEngine engine = getProcessEngine(userid);
		try {
			result = engine.getModelService().getUserSubmitProcess(userid,5);
			for(Map<String,String> tmp:result){
				String formUrl = tmp.get("startFormKey");
				tmp.put("formUrl", formUrl);
			}
		} finally {
			closeProcessEngine();
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
		
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String userId = StringUtil.getString(filter.get("userId"));
		ProcessEngine engine = getProcessEngine(userId);
		RuntimeService runtimeService = engine.getRuntimeService();
		IdentityService identityService = engine.getIdentityService();
		FlowUtilServiceImpl flowUtil = new FlowUtilServiceImpl();
		String processDefinitionKey = StringUtil.getString(filter.get("processDefinitionKey"));
		String processInstanceId    = StringUtil.getString(filter.get("processInstanceId"));
		String title				= StringUtil.getString(filter.get("title"));
//		String subject				= StringUtil.getString(filter.get("subject"));
		String bizKey				= StringUtil.getString(filter.get("bizKey"));
		String initor				= StringUtil.getString(filter.get("initor"));
		String status				= StringUtil.getString(filter.get("status"));
		String processType 			= StringUtil.getString(filter.get("processType"));
		ProcessInstanceType processInstanceStatus = FlowUtilServiceImpl.getInstanceStaus(status);
		try{
			
			String pageI = StringUtil.getString(filter.get("pageIndex"));
			String rowI = StringUtil.getString(filter.get("pageSize"));
			int pageIndex=1;
			int rowNum   =10;
			if(StringUtil.isNotEmpty(pageI)){
				pageIndex = Integer.valueOf(pageI);
			}
			if(StringUtil.isNotEmpty(rowI)){
				rowNum = Integer.valueOf(rowI);
			}
			ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
			if(StringUtil.isNotEmpty(processDefinitionKey))
				processInstanceQuery.processDefinitionKey(processDefinitionKey);
			if(StringUtil.isNotEmpty(processInstanceId))
				processInstanceQuery.processInstanceId(processInstanceId);
			if(StringUtil.isNotEmpty(title))
				processInstanceQuery.subjectLike(title);
			if(StringUtil.isNotEmpty(bizKey))
				processInstanceQuery.processInstanceBusinessKeyLike(bizKey);
			if(processInstanceStatus !=null){
				processInstanceQuery.processInstanceStatus(processInstanceStatus);
			}
			if(StringUtil.isNotEmpty(initor))
				processInstanceQuery.initiator(initor);
			
			
			if(StringUtil.isNotEmpty(processType)){
				if(processType.equals("initor"))
					processInstanceQuery.initiator(userId);
				else
					processInstanceQuery.taskParticipants(userId);
			}
			
			processInstanceQuery.orderByUpdateTime().desc();
			Date dates = null;
			Date datee = null;
			String dss = StringUtil.getString(filter.get("startTimeS"));
			String dse = StringUtil.getString(filter.get("startTimeE"));
			if(StringUtil.isNotEmpty(dss)){
				dates = DateUtil.stringToDate(dss,"yyyy-MM-dd");
			}
			if(StringUtil.isNotEmpty(dse)){
				String endTime = "235959999";
				dse += endTime;
				datee = DateUtil.stringToDate(dse,"yyyy-MM-ddHHmmssSSS");
			}
			if(dates!=null)
				processInstanceQuery.startTimeBefore(dates);
			
			if(datee!=null)
				processInstanceQuery.startTimeAfter(datee);
			
			
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
	
	/*
	 * 归档任务
	 * */
	public Map<String,Object> queryPlaceOnFile(Map<String,Object> filter) throws SQLException {
		Map<String,Object> result = new HashMap<String,Object>();
		String userId = (String) filter.get("userId");
		String processType = StringUtil.getString(filter.get("processType"));
		
		ProcessEngine engine = getProcessEngine(userId);
		try{
			ProcessInstanceQuery tq = engine.getRuntimeService()
					.createProcessInstanceQuery();
			
			String bizKey = StringUtil.getString(filter.get("BIZ_KEY"));  //业务数据
			if(StringUtil.isNotEmpty(bizKey))
				tq.processInstanceBusinessKey(bizKey);
			
			String initor = StringUtil.getString(filter.get("initor"));	//发起人
			if(StringUtil.isNotEmpty(initor))
				tq.initiator(initor);
			
			Date dates = null;
			Date datee = null;
			String dss = StringUtil.getString(filter.get("arrivalTimeS"));
			String dse = StringUtil.getString(filter.get("arrivalTimeE"));
			if(StringUtil.isNotEmpty(dss)){
				dates = DateUtil.stringToDate(dss,"yyyy-MM-dd");
			}
			if(StringUtil.isNotEmpty(dse)){
				String endTime = "235959999";
				dse += endTime;
				datee = DateUtil.stringToDate(dse,"yyyy-MM-ddHHmmssSSS");
			}
			if(dates!=null)
				tq.archiveTimeAfter(dates);
			
			if(datee!=null)
				tq.archiveTimeBefore(datee);
			
			String processDefinitionKey = StringUtil.getString(filter.get("processDefinitionKey"));  //流程定义
			if(StringUtil.isNotEmpty(processDefinitionKey))
				tq.processDefinitionKey(processDefinitionKey);
			
			String processInstanceId = StringUtil.getString(filter.get("processInstanceId"));	//流程实例号
			if(StringUtil.isNotEmpty(processInstanceId))
				tq.processInstanceId(processInstanceId);
			
			String subject = StringUtil.getString(filter.get("subject"));	//主题
			if(StringUtil.isNotEmpty(subject))
				tq.subjectLike(subject);
			
			String pageI = StringUtil.getString(filter.get("pageIndex"));
			String rowI = StringUtil.getString(filter.get("pageSize"));
			
			int pageIndex=1;
			int rowNum   =20;
			if(StringUtil.isNotEmpty(pageI)){
				pageIndex = Integer.valueOf(pageI);
			}
			if(StringUtil.isNotEmpty(rowI)){
				rowNum = Integer.valueOf(rowI);
			}
			
			if(filter.get("ended")!=null)
				tq.isEnd();
			
			List<ProcessInstance> instances = null;
			if(StringUtil.isNotEmpty(processType)){
				if(processType.equals("initor"))
					tq.initiator(userId);
				else
					tq.taskParticipants(userId);
			}
			tq.his();
			instances = tq.listPagination(pageIndex, rowNum);

			Long count = tq.count();
			List<Map<String,Object>> instanceMaps = new ArrayList<Map<String,Object>>();
			Pagination page = new Pagination(pageIndex,rowNum);
			page.setTotal(count.intValue());
			IdentityService identityService = engine.getIdentityService();
			for(ProcessInstance tmp:instances){
				Map<String, Object> persistentState = tmp.getPersistentState();
				ProcessEngine processEngine = ProcessEngineManagement.getDefaultProcessEngine();
				String processDefinitionId = tmp.getProcessDefinitionId();
				ProcessDefinitionBehavior processDefinitionBehavior = processEngine.getModelService().getProcessDefinition(processDefinitionId);
				String processDefinitionName = processDefinitionBehavior.getName();
				persistentState.put("processDefinitionName", processDefinitionName);
				
				
				
				UserTo user = identityService.getUserTo(tmp.getStartAuthor());
				if(user !=null){
					persistentState.put("startAuthorName", user.getUserName());
				}else{
					persistentState.put("startAuthorName", tmp.getStartAuthor());
				}
				instanceMaps.add(persistentState);
			}
			result.put("dataList", instanceMaps);
			result.put("pageInfo", page);
		}finally{
			closeProcessEngine();
		}
		return result;
	}
	
	public Map<String,Object> getTaskDetailInfo(Map<String,Object> filter) throws SQLException{
		String processInstanceId = StringUtil.getString(filter.get("processInstanceId"));
		
		Map<String,Object> result = new HashMap<String,Object>();
		if(StringUtil.isNotEmpty(processInstanceId)){
			String userId = (String) filter.get("userId");
			ProcessEngine engine = getProcessEngine(userId);
			ProcessInstance processInstance = engine.getRuntimeService().getProcessInstance(processInstanceId);
			String processName = processInstance.getProcessDefinition().getName();
			try{
				TaskQuery tq = engine.getTaskService().createTaskQuery();
				IdentityService identityService = engine.getIdentityService();
				tq.processInstanceId(processInstanceId);
				tq.taskIsEnd().orderByEndTime().asc();
				List<TaskInstance> instances = tq.list();
				List<Map<String,Object>> instanceMaps = new ArrayList<Map<String,Object>>();
				for(TaskInstance tmp:instances){
					Map<String,Object> instanceMap = tmp.getPersistentState();
					String assigneeUserId = tmp.getAssignee();
					if(StringUtil.isNotEmpty(assigneeUserId)){
						UserTo tmpUser = identityService.getUserTo(assigneeUserId);
						if(tmpUser!=null){
							instanceMap.put("assgneeUserName", tmpUser.getUserName());
						}
					}else{
						instanceMap.put("assgneeUserName", "(空用户名)");
					}
					instanceMaps.add(instanceMap);
				}
				tq.taskNotEnd().orderByTaskCreateTime().asc();
				List<TaskInstance> instancesNotEnd = tq.list();
				
				List<Map<String,Object>> notEndInstanceMaps = new ArrayList<Map<String,Object>>();
				for(TaskInstance tmp:instancesNotEnd){
					Map<String,Object> instanceMap = tmp.getPersistentState();
					String assigneeUserId = tmp.getAssignee();
					if(StringUtil.isNotEmpty(assigneeUserId)){
						UserTo tmpUser = identityService.getUserTo(assigneeUserId);
						if(tmpUser!=null){
							instanceMap.put("assgneeUserName", tmpUser.getUserName());
						}
					}else{
						instanceMap.put("assgneeUserName", "(空用户名)");
					}
					notEndInstanceMaps.add(instanceMap);
				}
				Map<String,Map<String,Object>> postionMap = engine.getModelService().GetFlowGraphicsElementPosition(processInstance.getProcessDefinitionId());
				result.put("notEnddataList", notEndInstanceMaps);
				result.put("dataList", instanceMaps);
				result.put("positionInfo", JSONUtil.parseObject2JSON(postionMap));
				result.put("taskEndedJson", JSONUtil.parseObject2JSON(instanceMaps));
				result.put("taskNotEndJson", JSONUtil.parseObject2JSON(instancesNotEnd));
				result.put("processName", processName);
				
			}finally{
				closeProcessEngine();
			}
		}
		return result;
	}
	
	
	public InputStream getFlowGraph(Map<String,Object> filter) throws SQLException{
		String processInstanceId = StringUtil.getString(filter.get("processDefinitionId"));
		String processDefinitionKey = StringUtil.getString(filter.get("processDefinitionKey"));
		InputStream result = null;
		
		String userId = (String) filter.get("userId");
		ProcessEngine engine = getProcessEngine(userId);
		
		try{
			if(StringUtil.isNotEmpty(processInstanceId))
				result = engine.getModelService().GetFlowGraphicsImgStreamByDefId(processInstanceId);
			else
				result = engine.getModelService().GetFlowGraphicsImgStreamByDefKey(processDefinitionKey);
		}finally{
			closeProcessEngine();
		}
		
		return result;
	}
	
	public Map<String,Object> getUserInfo(Map<String,Object> filter) throws SQLException, IOException{
		Map<String,Object> result= new HashMap<String,Object>();
		UserTo user = null;
		String userId = (String) filter.get("userId");
		ProcessEngine engine = getProcessEngine(userId);
		
		String path = StringUtil.getString(filter.get("path"));
		path = path+"/icon/";
		String tuserId = (String)filter.get("targetUserId");
		if(StringUtil.isNotEmpty(tuserId)){
			userId = tuserId;
		}
		
		FileUtil.makeParent(new File(path+"ss.ss"));
		result.put("icon", "icon/"+userId+".png");
		
		try{
			user = engine.getIdentityService().getUserTo(userId);
			List<GroupTo> groups = engine.getIdentityService().getUserInGroups(userId);
			result.put("user", user);
			result.put("groups", groups);
		}finally{
			closeProcessEngine();
		}
		return result;
	}
	
	public void saveUserIcon(Map<String,Object> filter) throws IOException{
		FileItem is = (FileItem)filter.get("icon");
		String userId = (String) filter.get("userId");
		String path = StringUtil.getString(filter.get("path"));
		String ex = FileUtil.getFileEx(is.getName());
		path = path+"/icon/"+userId+"."+ex;
		
		File newFile = new File(path);
		FileUtil.makeFile(newFile);
	    BufferedInputStream bis = null;   
	    FileOutputStream fos = null;
	    int BUFFER_SIZE = 4096;
	    byte[] buf = new byte[BUFFER_SIZE];   
	    int size = 0;   
	    InputStream file = is.getInputStream();
	    bis = new BufferedInputStream(file);   
	    fos = new FileOutputStream(newFile);

	    try{
		    while ( (size = bis.read(buf)) != -1)   { 
		      fos.write(buf, 0, size);
		      fos.flush();
		    }
	    }finally{
	    	file.close();
		    fos.close();
		    bis.close();
	    }
	}
	

	public Map<String,Object> GetFlowRefInfo(Map<String,Object> filter) throws SQLException {
		Map<String,Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> tmpres = new ArrayList<Map<String,Object>>();
		ProcessEngine engine = getProcessEngine(filter.get("userId"));
		try{
			String taskId = (String)filter.get("taskId");
			String processDefinitionKey = (String)filter.get("processDefinitionKey");
	//		taskId.replaceAll(regex, replacement)
			List<TaskCommandInst> list = null;
			if(StringUtil.isNotEmpty(taskId)){
				list = engine.getTaskService().GetTaskCommandByTaskId(taskId, false);
			}else{
				list = engine.getTaskService().getSubTaskTaskCommandByKey(processDefinitionKey);
			}
			for(TaskCommandInst tmp:list){
				tmpres.add(tmp.getPersistentState());
			}
			result.put("commandList", tmpres);
		}finally{
			closeProcessEngine();
		}
		return result;
	}
	
	public ProcessInstance completeTask(Map<String,Object> params) throws SQLException{
		String taskId = StringUtil.getString(params.get("taskId"));
		String commandType = StringUtil.getString(params.get("commandType"));
		String commandId = StringUtil.getString(params.get("commandId"));
		String processDefinitionKey = StringUtil.getString(params.get("processDefinitionKey"));
		String businessKey = StringUtil.getString(params.get("businessKey"));
		String userId = StringUtil.getString(params.get("userId"));
		String taskComment = StringUtil.getString(params.get("_taskComment"));
		Map<String,Object> taskParams = null;
		Object tmpParams = params.get("taskParams");
		if(tmpParams instanceof Map)
			taskParams = (Map<String,Object>)tmpParams;
		else
			taskParams = new HashMap<String,Object>();
		
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		

		//命令类型，可以从流程引擎配置中查询   启动并提交为startandsubmit
		expandTaskCommand.setCommandType(commandType);
		//设置提交人
		expandTaskCommand.setInitiator(userId);
		//设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
		expandTaskCommand.setUserCommandId(commandId);
		expandTaskCommand.setTaskComment(taskComment);
		if(StringUtil.isNotEmpty(taskId)){
			expandTaskCommand.setTaskId(taskId);
		}else{
			expandTaskCommand.setProcessDefinitionKey(processDefinitionKey);
			//设置流程的业务关联键
			expandTaskCommand.setBusinessKey(businessKey);
		}
		expandTaskCommand.setParamMap(taskParams);

		ProcessEngine engine = getProcessEngine(userId);
		ProcessInstance processInstance = null;
		try{
			processInstance = (ProcessInstance)engine.getTaskService().expandTaskComplete(expandTaskCommand, null);
		}finally{
			closeProcessEngine();
		}
		return processInstance;
	}
	
	
	public void cutUserIcon(Map<String,Object> params) throws IOException{
		String userId = (String) params.get("userId");
		String path = StringUtil.getString(params.get("path"));
		String scaled = StringUtil.getString(params.get("scaled"));
		Map<String,Object> map = JSONUtil.parseJSON2Map(scaled);
		int x = Integer.valueOf(StringUtil.getString(map.get("x")));
		int y = Integer.valueOf(StringUtil.getString(map.get("y")));
		int w = Integer.valueOf(StringUtil.getString(map.get("w")));
		int h = Integer.valueOf(StringUtil.getString(map.get("h")));
		path = path+"/icon/";
		File newFile = new File(path);
		FileUtil.makeParent(new File(path+"ss.ss"));
		
		String[] icons = newFile.list();
		for(String tmp:icons){
			if(tmp.startsWith(userId)){
				path +=tmp;
			}
		}
		
		ImageCutUtil icu = new ImageCutUtil(path,x,y,w,h);
		icu.setSubpath(path);
		icu.cut();
	}
	
	public Map<String, Object> getAllUsers(Map<String, Object> params) throws SQLException {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String userId = StringUtil.getString(params.get("userId"));
		String queryInfo = StringUtil.getString(params.get("queryInfo"));
		ProcessEngine processEngine = getProcessEngine(userId);
		IdentityService identityService = processEngine.getIdentityService();
		try{
			String pageI = StringUtil.getString(params.get("pageIndex"));
			String rowI = StringUtil.getString(params.get("pageSize"));
			int pageIndex=1;
			int rowNum   =15;
			if(StringUtil.isNotEmpty(pageI)){
				pageIndex = Integer.valueOf(pageI);
			}
			if(StringUtil.isNotEmpty(rowI)){
				rowNum = Integer.valueOf(rowI);
			}
			
			Map<String,Object> queryMap = new HashMap<String,Object>();
			String queryUserId = StringUtil.getString(params.get("queryUserId"));
			if(StringUtil.isNotEmpty(queryUserId)){
				queryMap.put("USERID", queryUserId);
			}
			String queryUserName = StringUtil.getString(params.get("queryUserName"));
			if(StringUtil.isNotEmpty(queryUserName)){
				queryMap.put("USERNAME", queryUserName);
			}
			if(StringUtil.isNotEmpty(queryInfo)){
				queryMap.put("USERID", queryInfo);
				queryMap.put("USERNAME", queryInfo);
			}
			int firstResult = rowNum*(pageIndex-1)+1;//起始行
			int maxResults = pageIndex*rowNum;//结束行
			Map<String,Object> userListMap = identityService.getUserTos(new Page(firstResult,maxResults), queryMap);
			List<UserTo> userTos = (List<UserTo>)userListMap.get("userList");
			int count = (Integer)userListMap.get("count");
			List<Map<String,Object>> userList = new ArrayList<Map<String,Object>>();
			Pagination page = new Pagination(pageIndex,rowNum);
			page.setTotal(count);
			if(userTos!=null){
				for(UserTo user:userTos){
					Map<String,Object> userMap = user.getPropertyMap();
					userList.add(userMap);
				}
			}
			resultMap.put("dataList", userList);
			resultMap.put("pageInfo", page);
		}finally{
			closeProcessEngine();
		}
		return resultMap;
	}
	
	public Map<String, Object> getRollbackNode(Map<String,Object> params) throws SQLException{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String userId = StringUtil.getString(params.get("userId"));
		String taskId = StringUtil.getString(params.get("taskId"));
		ProcessEngine processEngine = getProcessEngine(userId);
		TaskService taskService = processEngine.getTaskService();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try{
			List<UserTaskBehavior> userTaskBehaviors = taskService.getRollBackNode(taskId);
			for(UserTaskBehavior node :userTaskBehaviors){
				Map<String,Object> nodeMap = new HashMap<String,Object>();
				nodeMap.put("nodeId", node.getId());
				nodeMap.put("nodeName", node.getName());
				resultList.add(nodeMap);
			}
		}finally{
			closeProcessEngine();
		}
		resultMap.put("dataList", resultList);
		return resultMap;
	}
	
	public Map<String, Object> getRollbackTask(Map<String, Object> params) throws SQLException {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String userId = StringUtil.getString(params.get("userId"));
		String taskId = StringUtil.getString(params.get("taskId"));
		ProcessEngine processEngine = getProcessEngine(userId);
		TaskService taskService = processEngine.getTaskService();
		IdentityService identityService = processEngine.getIdentityService();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try{
			List<TaskInstance> taskInstances = taskService.getRollBackTask(taskId);
			for(TaskInstance task :taskInstances){
				Map<String,Object> taskMap = new HashMap<String,Object>();
				taskMap.put("taskId", task.getId());
				taskMap.put("taskName", task.getName());
				taskMap.put("startTime", task.getCreateTime());
				taskMap.put("endTime", task.getEndTime());
				UserTo user = identityService.getUserTo(task.getAssignee());
				taskMap.put("assignee", task.getAssignee());
				if(user !=null){
					taskMap.put("assigneeUserName", user.getUserName());
				}
				resultList.add(taskMap);
			}
		}finally{
			FixFlowShellProxy.closeProcessEngine(processEngine, true);
		}
		resultMap.put("dataList", resultList);
		return resultMap;
	}
	
	
}
