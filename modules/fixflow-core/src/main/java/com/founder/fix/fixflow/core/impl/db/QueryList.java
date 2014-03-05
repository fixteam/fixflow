package com.founder.fix.fixflow.core.impl.db;

import java.util.List;

public class QueryList {
	

	protected String sqlText;
	
	public String getSqlText() {
		return sqlText;
	}


	public List<Object> getData() {
		return data;
	}


	protected List<Object> data;

	public QueryList(String sqlText) {
		this.sqlText=sqlText;
	}

	
	public QueryList(String sqlText,List<Object> data) {
		this.sqlText=sqlText;
		this.data=data;
	}

}
