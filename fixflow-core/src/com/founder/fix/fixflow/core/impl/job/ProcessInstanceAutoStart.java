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
import com.founder.fix.fixflow.core.impl.command.StartProcessInstanceCommand;
import com.founder.fix.fixflow.core.impl.identity.Authentication;

public class ProcessInstanceAutoStart implements Job {


	public ProcessInstanceAutoStart() {
		
	}

	public void execute(JobExecutionContext context) throws JobExecutionException {

		
		String processUniqueKey=context.getJobDetail().getJobDataMap().getString("processUniqueKey").toString();
		System.out.println("=====定时流程 "+processUniqueKey+" 启动 " + new Date() + " =====");
		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		startProcessInstanceCommand.setProcessDefinitionId(processUniqueKey);
		startProcessInstanceCommand.setStartAuthor(Authentication.getAdminId());

		Map<String, Object> transientVariableMap = new HashMap<String, Object>();

		startProcessInstanceCommand.setTransientVariables(transientVariableMap);
		
	
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
			
			runtimeService.timeStartProcessInstance(startProcessInstanceCommand);

			
			
			//processEngine.contextClose();
			
			System.out.println("定时流程启动成功");

			System.out.println("=====完成 at" + new Date() + "=====");
			
			connection.commit();
			
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			System.out.print("定时任务启动失败");
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
