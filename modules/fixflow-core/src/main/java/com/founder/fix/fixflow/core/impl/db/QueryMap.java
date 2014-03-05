package com.founder.fix.fixflow.core.impl.db;

import java.util.List;

public class QueryMap {
	
	protected String sqlText;
	
	public String getSqlText() {
		return sqlText;
	}


	public List<Object> getData() {
		return data;
	}


	protected List<Object> data;

	public QueryMap(String sqlText) {
		this.sqlText=sqlText;
	}

	
	public QueryMap(String sqlText,List<Object> data) {
		this.sqlText=sqlText;
		this.data=data;
	}

}
