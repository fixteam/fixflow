package com.founder.fix.fixflow.core.impl.identity;

import java.sql.Connection;

import com.founder.fix.bpmn2extensions.coreconfig.AllUserInfo;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;

public abstract class UserDefinition {
	
	protected AllUserInfo userInfoConfig;
	
	
	public AllUserInfo getUserInfoConfig() {
		return userInfoConfig;
	}


	public void setUserInfoConfig(AllUserInfo userInfoConfig) {
		this.userInfoConfig = userInfoConfig;
	}
	
	/**
	 * 获取数据库操作类
	 * @return
	 */
	public SqlCommand getSqlCommand(){
		
		Connection connection = Context.getDbConnection();

		SqlCommand sqlCommand = new SqlCommand(connection);
		
		return sqlCommand;
		
	}


	public abstract UserTo findUserByUserId(String userId);

}
