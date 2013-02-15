package com.founder.fix.fixflow.expand.connector.Oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.action.ConnectorHandler;

public class Oracle implements ConnectorHandler {

	private java.lang.String driver;

	private java.lang.String url;

	private java.lang.String user;

	private java.lang.String password;

	private java.lang.String query;

	public void execute(ExecutionContext executionContext) throws Exception {

		

	}

	public void setDriver(java.lang.String driver) {
		this.driver = driver;
	}

	public void setUrl(java.lang.String url) {
		this.url = url;
	}

	public void setUser(java.lang.String user) {
		this.user = user;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	public void setQuery(java.lang.String query) {
		this.query = query;
	}

	public java.lang.String getRowSet() {
		return null;
	}

}