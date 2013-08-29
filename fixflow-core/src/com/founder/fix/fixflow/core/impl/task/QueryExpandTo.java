/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 */
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
