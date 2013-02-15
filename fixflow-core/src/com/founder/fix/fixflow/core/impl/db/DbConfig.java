package com.founder.fix.fixflow.core.impl.db;

import java.util.Map;

import com.founder.fix.fixflow.core.db.pagination.Pagination;

public class DbConfig {
	
	protected DbType dbType;
	
	protected Pagination pagination;
	
	protected String keyword;
	
	protected Map<String, String> dbSqlMap;

	public DbType getDbType() {
		return dbType;
	}

	public void setDbType(DbType dbType) {
		this.dbType = dbType;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Map<String, String> getDbSqlMap() {
		return dbSqlMap;
	}

	public void setDbSqlMap(Map<String, String> dbSqlMap) {
		this.dbSqlMap = dbSqlMap;
	}

}
