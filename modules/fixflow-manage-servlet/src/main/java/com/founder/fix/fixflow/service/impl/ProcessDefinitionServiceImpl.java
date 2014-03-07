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
import com.founder.fix.fixflow.shell.CommonServiceImpl;
import com.founder.fix.fixflow.util.Pagination;
@Scope("prototype")
@Service
public class ProcessDefinitionServiceImpl extends CommonServiceImpl implements ProcessDefinitionService{
	
	@SuppressWarnings("rawtypes")
	public Map<String, Object> getProcessDefitionList(Map<String, Object> params) throws SQLException {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String userId = StringUtil.getString(params.get("userId"));
		ProcessEngine processEngine = getProcessEngine(userId);
		ProcessDefinitionQuery processDefinitionQuery = processEngine.getModelService().createProcessDefinitionQuery();
		try{
			
			String processName = StringUtil.getString(params.get("queryProcessName"));
			if(StringUtil.isNotEmpty(processName)){
				processDefinitionQuery.processDefinitionNameLike(processName);
			}
			String processId = StringUtil.getString(params.get("queryProcessId"));
			if(StringUtil.isNotEmpty(processId)){
				processDefinitionQuery.processDefinitionKeyLike(processId);
			}
			String processCategory = StringUtil.getString(params.get("queryType"));
			if(StringUtil.isNotEmpty(processCategory)){
				processDefinitionQuery.processDefinitionCategoryLike(processCategory);
			}
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
			
//			QueryExpandTo queryExpandTo = new QueryExpandTo();
//			//添加扩展的字段
//			queryExpandTo.setFieldSql("fixflow_def_deployment.deploy_Time");
//			//增加扩展查询的left join语句
//			queryExpandTo.setLeftJoinSql("left join fixflow_def_deployment on PD.deployment_id = fixflow_def_deployment.id");
//			processDefinitionQuery.queryExpandTo(queryExpandTo);
			processDefinitionQuery.orderByDeploymentTime().desc();
			List<ProcessDefinitionBehavior> processDefinitions = processDefinitionQuery.listPagination(pageIndex, rowNum);
			Long count = processDefinitionQuery.count();
			List<Map<String,Object>> processDefinitionList = new ArrayList<Map<String,Object>>();
			Pagination page = new Pagination(pageIndex,rowNum);
			page.setTotal(count.intValue());
			for(ProcessDefinitionBehavior processDefinition:processDefinitions){
				Map<String,Object> processDefinitonMap = processDefinition.getPersistentState();
				processDefinitionList.add(processDefinitonMap);
			}
			resultMap.put("dataList", processDefinitionList);
			resultMap.put("pageInfo", page);
		}finally{
			closeProcessEngine();
		}
		return resultMap;
	}
	
	public void deployByZip(Map<String, Object> params) throws Exception {
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
		} 
		finally{
			closeProcessEngine();
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
			closeProcessEngine();
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
			closeProcessEngine();
		}
		return resultList;
	}
}
