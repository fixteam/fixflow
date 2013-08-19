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
 * @author yangchenhui
 */
package com.founder.fix.fixflow.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.apache.commons.fileupload.FileItem;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.persistence.definition.DeploymentEntity;
import com.founder.fix.fixflow.core.impl.persistence.definition.ResourceEntity;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.model.ProcessDefinitionQuery;
import com.founder.fix.fixflow.service.ProcessDefinitionService;
import com.founder.fix.fixflow.shell.FixFlowShellProxy;
import com.founder.fix.fixflow.util.Pagination;
@Scope("prototype")
@Service
public class ProcessDefinitionServiceImpl implements ProcessDefinitionService{

private Connection connection;
	
	public Connection getConnection() {
		return connection;
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	@SuppressWarnings("rawtypes")
	public Map<String, Object> getProcessDefitionList(Map<String, Object> params) throws SQLException {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String userId = StringUtil.getString(params.get("userId"));
		ProcessEngine processEngine = getProcessEngine(userId);
		ProcessDefinitionQuery processDefinitionQuery = processEngine.getModelService().createProcessDefinitionQuery();
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
			List<ProcessDefinitionBehavior> processDefinitions = processDefinitionQuery.listPage(pageIndex, rowNum);
			//取值错误，要更改 为了先测试
			int count = processDefinitions.size();
			List<Map<String,Object>> processDefinitionList = new ArrayList<Map<String,Object>>();
			Pagination page = new Pagination(pageIndex,rowNum);
			page.setTotal(count);
			for(ProcessDefinitionBehavior processDefinition:processDefinitions){
				Map<String,Object> processDefinitonMap = processDefinition.getPersistentState();
				processDefinitionList.add(processDefinitonMap);
			}
			resultMap.put("dataList", processDefinitionList);
			resultMap.put("pageInfo", page);
		}finally{
			FixFlowShellProxy.closeProcessEngine(processEngine, false);
		}
		return resultMap;
	}
	
	public void deployByZip(Map<String, Object> params) {
		String userid = StringUtil.getString(params.get("userId"));
		FileItem file = (FileItem)params.get("ProcessFile");
		ProcessEngine processEngine = null;
		try {
			processEngine = getProcessEngine(userid);
			String deploymentId = StringUtil.getString(params.get("deploymentId"));
			//有deploymentID则为更新，否则为新增
			if(deploymentId != null && !"".equals(deploymentId)){
				processEngine.getModelService().updateDeploymentByZip(new ZipInputStream(file.getInputStream()),deploymentId);
			}else{
				processEngine.getModelService().deploymentByZip(new ZipInputStream(file.getInputStream()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			FixFlowShellProxy.closeProcessEngine(processEngine, false);
		}
	}
	
	public void deleteDeploy(Map<String,Object> params) {
		String userid = StringUtil.getString(params.get("userId"));
		String []deploymentIds = StringUtil.getString(params.get("deploymentId")).split(",");
		ProcessEngine processEngine = null;
		try {
			processEngine = getProcessEngine(userid);
			for(int i = 0;i<deploymentIds.length;i++){
				processEngine.getModelService().deleteDeployment(deploymentIds[i], true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			FixFlowShellProxy.closeProcessEngine(processEngine, false);
		}
	}
	
	public List<Map<String,Object>> getResources(Map<String,Object> params){
		String userid = StringUtil.getString(params.get("userId"));
		String deploymentId = StringUtil.getString(params.get("deploymentId"));
		ProcessEngine processEngine = null;
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			processEngine = getProcessEngine(userid);
			DeploymentEntity deploymentEntity= processEngine.getModelService().getDeploymentEntity(deploymentId);
			Map<String,ResourceEntity> map = deploymentEntity.getResources();
			for(String key :map.keySet()){
				Map<String,Object> resultMap = new HashMap<String,Object>();
				ResourceEntity resourceEntity = map.get(key);
				resultMap.put("FILENAME", resourceEntity.getName());
				resultMap.put("BYTES", resourceEntity.getBytes());
				resultList.add(resultMap);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			FixFlowShellProxy.closeProcessEngine(processEngine, false);
		}
		return resultList;
	}
	
	private ProcessEngine getProcessEngine(Object userId) throws SQLException{
		if(connection!=null){
			return FixFlowShellProxy.createProcessEngine(userId,connection);
		}else{
			return FixFlowShellProxy.createProcessEngine(userId);
		}
	}

}
