package com.founder.fix.fixflow.expand.database;

import java.sql.Connection;

import com.founder.fix.fixflow.core.ConnectionManagement;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.db.FixConnectionResult;

public class FixConnectionManagement extends ConnectionManagement {


	@Override
	public FixConnectionResult getFixConnectionResult() {

		String defaultId=ConnectionManagement.defaultDataBaseId;

		
		return getFixConnectionResult(defaultId);
	}

	@Override
	public FixConnectionResult getFixConnectionResult(String dbId) {
		
		
		FixConnectionResult fixConnectionResult=new FixConnectionResultImpl(dbId);
		
		return fixConnectionResult;

	}

	@Override
	public void setFixConnectionResult(String dbId, FixConnectionResult connection) {
		Context.setFixConnectionResult(dbId, connection);
	}

	@Override
	public void setFixConnection(String dbId, Connection connection) {
		FixConnectionResult fixConnectionResult=new FixConnectionResultImpl(dbId,connection);
		Context.setFixConnectionResult(dbId, fixConnectionResult);
		
	}

}
