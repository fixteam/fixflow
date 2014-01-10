package com.founder.fix.fixflow.core.impl.db;

import java.util.List;

public class QueryForValue {

	protected String sqlText;

	public String getSqlText() {
		return sqlText;
	}

	public List<Object> getData() {
		return data;
	}

	protected List<Object> data;

	public QueryForValue(String sqlText) {
		this.sqlText = sqlText;
	}

	public QueryForValue(String sqlText, List<Object> data) {
		this.sqlText = sqlText;
		this.data = data;
	}

}
