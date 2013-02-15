package com.founder.fix.fixflow.core.impl.job;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.founder.fix.bpmn2extensions.coreconfig.DataBase;
import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.RuntimeService;
import com.founder.fix.fixflow.core.impl.ExternalContent;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.runtime.Token;


public class TokenTimeOutJob implements Job {

	@SuppressWarnings("unchecked")
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		System.out.println("=====定时任务启动 " + new Date() + " =====");

		
		String tokenId=context.getJobDetail().getJobDataMap().getString("tokenId");
		String transientVariableId=context.getJobDetail().getJobDataMap().getString("transientVariableId");
		String processInstanceId=context.getJobDetail().getJobDataMap().getString("processInstanceId");
		String nodeId=context.getJobDetail().getJobDataMap().getString("nodeId");
		
		
		
	
		
		ProcessEngine processEngine = ProcessEngineManagement.getDefaultProcessEngine();
		
		
		DataBase dataBase = processEngine.getProcessEngineConfiguration().getSelectedDatabase();
		
		Connection connection=null;
		
		if (connection == null) {
			try {
				Class.forName(dataBase.getDriverClassName());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
			String url = dataBase.getUrl();
			String user = dataBase.getUsername();
			String password = dataBase.getPassword();
			try {
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
			} catch (SQLException e) {

			}

		}
		try {
			
			
			
			
			ExternalContent externalContent = new ExternalContent();
			
			externalContent.setAuthenticatedUserId(Authentication.getAdminId());
			externalContent.setConnection(connection);
			processEngine.setExternalContent(externalContent);
			


			RuntimeService runtimeService = processEngine.getRuntimeService();
			
			Token token=runtimeService.createTokenQuery().tokenId(tokenId).singleResult();
			
			Object transientVariable=runtimeService.getProcessInstanceVariable(processInstanceId, transientVariableId);
			Map<String, Object> transientVariableMap=null;
			if(transientVariable!=null){
				
				transientVariableMap=(HashMap<String, Object>)transientVariable;
				
			}
			
			//删除变量
			
			/*
			VariableQueryEntity variableQueryEntity = new VariableQueryEntity();
	
			if (processInstanceId != null && !processInstanceId.equals("")) {
				VariableFlowTypeEntity variableFlowTypeEntity = new VariableFlowTypeEntity(VariableFlowType.PROCESSINSTANCE, processInstanceId);
				variableQueryEntity.addVariableFlowType(variableFlowTypeEntity);
			}
			
			variableQueryEntity.addVariableName(transientVariableId);
			
			Context.getCommandContext().getVariableManager().deleteVariable(variableQueryEntity);
	        */
			
			//
			
			if(nodeId.equals(token.getNodeId())){

				runtimeService.tokenTimeOut(tokenId, transientVariableMap);

			}
			else{

				runtimeService.tokenTimeOut(tokenId,nodeId, transientVariableMap);
				
			}
			
			
			System.out.println("超时处理启动! "+new Date());

			connection.commit();
			
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally{
			if(connection!=null){
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}

}
