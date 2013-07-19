package com.founder.fix.fixflow.test.connection;


import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;


public class ConnectionTest extends AbstractFixFlowTestCase {
	
	
	/**
	 * 测试获取数据库连接
	 * @throws Exception
	 */
	public void testConnection() throws Exception
	{
		assertNotNull(Context.getDbConnection());
	}
	
	

}
