package com.founder.fix.fixflow.expand.database;

import java.sql.Connection;

import com.founder.fix.dbcore.DBGetResult;
import com.founder.fix.fixflow.core.ConnectionManagement;
import com.founder.fix.fixflow.core.exception.FixFlowDbException;
import com.founder.fix.fixflow.core.impl.Context;

public class FixConnectionManagement extends ConnectionManagement {

	@Override
	public Connection getConnection() {

		String defaultId=ConnectionManagement.defaultDataBaseId;

		
		return getConnection(defaultId);
	}

	@Override
	public Connection getConnection(String dbId) {
		
		
		Connection connection=Context.getDbConnection(dbId);
		if(connection==null){
			DBGetResult dbGetResult=new DBGetResult();
			try {
				dbGetResult.openConn(dbId);
				connection=dbGetResult.getConnection();
				
				if(connection!=null){
					setConnection(dbId, connection);
					return  connection;
				}else{
					throw new FixFlowDbException("ID为"+dbId+"的数据库连接创建失败!");
				}
				
				
				
			} catch (Exception e) {
				
				throw new FixFlowDbException(e.getMessage(), e);
			}
		}
		else{
			return connection;
		}

	}

	@Override
	public void setConnection(String dbId, Connection connection) {
		Context.setDbConnection(dbId, connection);
	}

}
