package com.founder.fix.fixflow.core.impl.db;

import java.sql.Connection;

public interface FixConnectionResult {
	
	Connection getConnection();
	
	void openConnection();
	
	void colseConnection();
	
	void commitConnection();
	
	void rollBackConnection();
	
	String getDataBaseId();
	

}
