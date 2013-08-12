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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Service;

import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.TaskService;
import com.founder.fix.fixflow.core.impl.TaskServiceImpl;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.identity.GroupTo;
import com.founder.fix.fixflow.core.impl.identity.UserTo;
import com.founder.fix.fixflow.core.impl.util.DateUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.ProcessInstanceQuery;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.founder.fix.fixflow.service.FlowCenterService;
import com.founder.fix.fixflow.shell.FixFlowShellProxy;
import com.founder.fix.fixflow.util.FileUtil;

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
			result.put("pageIndex", pageI);
			result.put("rowNum", rowI);
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
			for(Map<String,String> tmp:result){
				String pdkey = tmp.get("processDefinitionKey");
				String formUrl = engine.getFormService().getStartFormByKey(pdkey);
				
				tmp.put("formUrl", formUrl);
			}
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
			result.put("pageIndex", pageI);
			result.put("rowNum", rowI);
		}finally{
			FixFlowShellProxy.closeProcessEngine(engine, false);
		}
		return result;
	}
	
	public Map<String,Object> getTaskDetailInfo(Map<String,Object> filter) throws SQLException{
		String processInstanceId = StringUtil.getString(filter.get("processInstanceId"));
		
		Map<String,Object> result = new HashMap<String,Object>();
		if(StringUtil.isNotEmpty(processInstanceId)){
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
	
	public Map<String,Object> getUserInfo(Map<String,Object> filter) throws SQLException, IOException{
		Map<String,Object> result= new HashMap<String,Object>();
		UserTo user = null;
		String userId = (String) filter.get("userId");
		ProcessEngine engine = FixFlowShellProxy.createProcessEngine(userId);
		
		String path = StringUtil.getString(filter.get("path"));
		path = path+"/icon/";
		File newFile = new File(path);
		FileUtil.makeParent(new File(path+"ss.ss"));
		
		String[] icons = newFile.list();
		for(String tmp:icons){
			if(tmp.startsWith(userId)){
				result.put("icon", "icon/"+tmp);
			}
		}
		
		try{
			user = engine.getIdentityService().getUserTo(userId);
			List<GroupTo> groups = engine.getIdentityService().getUserInGroups(userId);
			result.put("user", user);
			result.put("groups", groups);
		}finally{
			FixFlowShellProxy.closeProcessEngine(engine, false);
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
	

	@Override
	public List<Map<String,Object>> GetTaskCommand(Map<String,Object> filter) throws SQLException {
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		ProcessEngine engine = FixFlowShellProxy.createProcessEngine(filter.get("userId"));
		String taskId = (String)filter.get("taskId");
		String processDefinitionKey = (String)filter.get("processDefinitionKey");
		
		List<TaskCommandInst> list = null;
		if(StringUtil.isNotEmpty(processDefinitionKey)){
			list = engine.getTaskService().getSubTaskTaskCommandByKey(processDefinitionKey);
		}else{
			list = engine.getTaskService().GetTaskCommandByTaskId(taskId, false);
		}
		for(TaskCommandInst tmp:list){
			result.add(tmp.getPersistentState());
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
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		

		//命令类型，可以从流程引擎配置中查询   启动并提交为startandsubmit
		expandTaskCommand.setCommandType(commandType);
		//设置提交人
		expandTaskCommand.setInitiator(userId);
		//设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
		expandTaskCommand.setUserCommandId(commandId);
		
		if(StringUtil.isNotEmpty(taskId)){
			expandTaskCommand.setTaskId(taskId);
		}else{
			expandTaskCommand.setProcessDefinitionKey(processDefinitionKey);
			//设置流程的业务关联键
			expandTaskCommand.setBusinessKey(businessKey);
		}

		ProcessEngine engine = FixFlowShellProxy.createProcessEngine(userId);
		ProcessInstance processInstance = engine.getTaskService().expandTaskComplete(expandTaskCommand, null);
		
		return processInstance;
	}
}
