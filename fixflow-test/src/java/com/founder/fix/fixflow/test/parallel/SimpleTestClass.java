package com.founder.fix.fixflow.test.parallel;

import java.sql.Connection;
import java.sql.SQLException;

import org.punit.runner.ConcurrentRunner;

import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.impl.ExternalContent;
import com.founder.fix.fixflow.core.impl.command.StartProcessInstanceCommand;
import com.founder.fix.fixflow.core.impl.db.DbType;


public class SimpleTestClass {

	public static MiniConnectionPoolManager poolMgr;
	public static void main(String[] args) {

		
		oracle.jdbc.pool.OracleConnectionPoolDataSource dataSource=null;
		try {
			dataSource = new oracle.jdbc.pool.OracleConnectionPoolDataSource();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataSource.setDriverType ("thin");
		dataSource.setServerName ("172.29.128.91");
		dataSource.setPortNumber (1521);
		dataSource.setServiceName ("orcl");
		dataSource.setUser ("idbase");
		dataSource.setPassword ("idbase");
		poolMgr = new MiniConnectionPoolManager(dataSource, 40);

	
		
		new ConcurrentRunner(50).run(SimpleTestClass.class);
		try {
			poolMgr.dispose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void testA() {
		
		
		Connection connection=null;
		try {
			connection = poolMgr.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ProcessEngineManagement.dbType = DbType.ORACLE;
		ProcessEngine processEngine = ProcessEngineManagement.getDefaultProcessEngine();

		ExternalContent externalContent = new ExternalContent();
		externalContent.setAuthenticatedUserId("1200119390");

		externalContent.setConnection(connection);
		processEngine.setExternalContent(externalContent);

		//RuntimeService runtimeService = processEngine.getRuntimeService();

		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		startProcessInstanceCommand.setProcessDefinitionKey("Process_101");
		startProcessInstanceCommand.setBusinessKey("1234567890");
		startProcessInstanceCommand.setStartAuthor("1200119390");
		//ProcessInstanceQueryTo processInstance = runtimeService.startProcessInstanceByKey(startProcessInstanceCommand);

		
		
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
