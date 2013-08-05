package com.founder.fix.fixflow.shell;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

public class DBConnection {
	private DataSource dataSource;
	
	private Connection connection;
	
	public void close(){
		if (connection != null) DataSourceUtils.releaseConnection(connection, dataSource);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	
}
