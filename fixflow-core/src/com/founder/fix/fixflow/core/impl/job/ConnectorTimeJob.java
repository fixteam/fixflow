package com.founder.fix.fixflow.core.impl.job;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


import com.founder.fix.bpmn2extensions.coreconfig.DataBase;
import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.ScheduleService;


public class ConnectorTimeJob extends AbstactConnectorTimeJob {

	@Override
	public void executeJob(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		
		System.out.println("=====定时任务启动 " + new Date() + " =====");
		
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

			


			ScheduleService scheduleService = processEngine.getScheduleService();
			scheduleService.executeConnectorTimeJob(jobExecutionContext);


			connection.commit();
			System.out.println("定时任务启动成功! "+new Date());
			
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			System.out.println("定时任务启动失败! "+new Date());
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
