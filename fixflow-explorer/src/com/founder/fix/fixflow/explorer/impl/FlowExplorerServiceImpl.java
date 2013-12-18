package com.founder.fix.fixflow.explorer.impl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.founder.fix.bpmn2extensions.coreconfig.DataBase;
import com.founder.fix.fixflow.core.ModelService;
import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.impl.ExternalContent;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.model.ProcessDefinitionQuery;
import com.founder.fix.fixflow.explorer.service.FlowExplorerService;

public class FlowExplorerServiceImpl implements FlowExplorerService {

	public String getProcessVersionInfo(String fileName, String userId)throws Exception {
		String processKey = fileName.substring(0, fileName.lastIndexOf("."));
    	List<Map<String, Object>> resultMaps = new ArrayList<Map<String,Object>>();
    	ProcessEngine processEngine = getProcessEngine(userId);
    	if(processEngine != null){
    		ModelService modelService = processEngine.getModelService();
    		ProcessDefinitionQuery processDefinitionQuery = modelService.createProcessDefinitionQuery();
    		processDefinitionQuery.processDefinitionKey(processKey);
    		List<ProcessDefinitionBehavior> processDefinitionBehaviors = processDefinitionQuery.list();
    		for(ProcessDefinitionBehavior processDefinitionBehavior :processDefinitionBehaviors){
    			resultMaps.add(processDefinitionBehavior.getPersistentState());
    		}
    	}
    	ObjectMapper objectMapper = new ObjectMapper();
    	String result = objectMapper.writeValueAsString(resultMaps);
	    return result;	
	}

	public void deploy(Map<String, InputStream> fileInputStream,String deploymentId, String userId) throws Exception {
		ProcessEngine processEngine = null;
		try {
			processEngine = getProcessEngine(userId);
			if (processEngine != null) {
				ModelService modelService = processEngine.getModelService();
				if (deploymentId != null && !"".equals(deploymentId)) {
					modelService.updateDeploymentByStream(fileInputStream,deploymentId);
				} else {
					modelService.deploymentByStream(fileInputStream);
				}
			}
		}finally {
			if (processEngine != null) {
				processEngine.contextClose(true, true);
			}
		}
	}

	private ProcessEngine getProcessEngine(String userId) throws Exception{
    	Connection connection = null;
    	ProcessEngine processEngine=ProcessEngineManagement.getDefaultProcessEngine();
		//获取流程当前配置的数据库信息
		DataBase dataBase=processEngine.getProcessEngineConfiguration().getSelectedDatabase();
		String driver = dataBase.getDriverClassName();
		String url = dataBase.getUrl();
		String user = dataBase.getUsername();
		String password = dataBase.getPassword();
		//创建Connection
		Class.forName(driver);
		connection = DriverManager.getConnection(url, user, password);
		connection.setAutoCommit(false);
		ExternalContent externalContent =new ExternalContent();
		externalContent.setAuthenticatedUserId(userId);
		externalContent.setConnection(connection);
		processEngine.setExternalContent(externalContent);
    	
    	return processEngine;
    }

}
