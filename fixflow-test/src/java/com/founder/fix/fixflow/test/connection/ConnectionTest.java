package com.founder.fix.fixflow.test.connection;


import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;


public class ConnectionTest extends AbstractFixFlowTestCase {
	
	

	public void testConnection() throws Exception
	{
		assertNotNull(connection);
		connection.close();
	}
	
	

}
