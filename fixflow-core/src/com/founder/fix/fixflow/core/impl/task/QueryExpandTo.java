package com.founder.fix.fixflow.core.impl.task;

import java.util.ArrayList;
import java.util.List;


public class QueryExpandTo {

	protected String fieldSql;
	
	protected String leftJoinSql;

	protected String whereSql;
	
	protected List<Object> whereSqlObj=new ArrayList<Object>();
	
	
	public String getFieldSql() {
		return fieldSql;
	}

	public void setFieldSql(String fieldSql) {
		this.fieldSql = fieldSql;
	}

	public String getLeftJoinSql() {
		return leftJoinSql;
	}

	public void setLeftJoinSql(String leftJoinSql) {
		this.leftJoinSql = leftJoinSql;
	}

	public String getWhereSql() {
		return whereSql;
	}

	public void setWhereSql(String whereSql) {
		this.whereSql = whereSql;
	}

	public List<Object> getWhereSqlObj() {
		return whereSqlObj;
	}

	public void setWhereSqlObj(List<Object> whereSqlObj) {
		this.whereSqlObj = whereSqlObj;
	}
	
	public void addWhereSqlObj(Object obj){
		this.whereSqlObj.add(obj);
	}
}
