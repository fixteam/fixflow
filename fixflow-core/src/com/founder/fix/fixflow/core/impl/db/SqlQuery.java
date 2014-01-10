package com.founder.fix.fixflow.core.impl.db;

import java.util.List;

public class SqlQuery {
	
	protected QueryList queryList;
	

	

	
	protected QueryMap queryMap;
	
	protected QueryForValue queryForValue;
	
	public SqlQuery() {
		
	}
	
	public SqlQuery queryList(String sqlText) {
		queryList=new QueryList(sqlText);
		return this;
	}
	
	public SqlQuery queryList(String sqlText,List<Object> data) {
		queryList=new QueryList(sqlText,data);
		return this;
	}
	
	public SqlQuery queryMap(String sqlText) {
		queryMap=new QueryMap(sqlText);
		return this;
	}
	
	public SqlQuery queryMap(String sqlText,List<Object> data) {
		queryMap=new QueryMap(sqlText,data);
		return this;
	}
	
	public SqlQuery queryForValue(String sqlText) {
		queryForValue=new QueryForValue(sqlText);
		return this;
	}
	
	public SqlQuery queryForValue(String sqlText,List<Object> data) {
		queryForValue=new QueryForValue(sqlText,data);
		return this;
	}
	
	public QueryList getQueryList() {
		return queryList;
	}

	public QueryMap getQueryMap() {
		return queryMap;
	}

	public QueryForValue getQueryForValue() {
		return queryForValue;
	}

	

}
