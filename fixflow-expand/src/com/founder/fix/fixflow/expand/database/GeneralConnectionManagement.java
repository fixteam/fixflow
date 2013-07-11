package com.founder.fix.fixflow.expand.database;

import java.sql.Connection;

import com.founder.fix.fixflow.core.ConnectionManagement;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.db.FixConnectionResult;


public class GeneralConnectionManagement extends ConnectionManagement {

	@Override
	public FixConnectionResult getFixConnectionResult() {
		
		String defaultId=ConnectionManagement.defaultDataBaseId;

		
		return getFixConnectionResult(defaultId);	
	}

	@Override
	public FixConnectionResult getFixConnectionResult(String dbId) {
		FixConnectionResult fixConnectionResult=new GeneralConnectionResultImpl(dbId);
		
		return fixConnectionResult;
	}

	@Override
	public void setFixConnectionResult(String dbId, FixConnectionResult fixConnectionResult) {
		Context.setFixConnectionResult(dbId,fixConnectionResult);
	}

	@Override
	public void setFixConnection(String dbId, Connection connection) {
		FixConnectionResult fixConnectionResult=new GeneralConnectionResultImpl(dbId,connection);
		Context.setFixConnectionResult(dbId,fixConnectionResult);
	}

}
