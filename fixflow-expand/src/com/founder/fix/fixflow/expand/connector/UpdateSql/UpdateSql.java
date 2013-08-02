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
package com.founder.fix.fixflow.expand.connector.UpdateSql;


import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.action.ConnectorHandler;

public class UpdateSql implements ConnectorHandler {

	private java.lang.String tableName;

	private java.util.HashMap objectParam;

	private java.lang.String whereSql;

	private java.util.List objectParamWhere;

	public void execute(ExecutionContext executionContext) throws Exception {
		if(objectParamWhere!=null){
			Object[] objectParamWhereNew = objectParamWhere.toArray();
			executionContext.getSqlCommand().update(tableName, objectParam, whereSql, objectParamWhereNew);
		}

	}

	public void  setTableName(java.lang.String tableName){
		this.tableName = tableName;
	}

	public void  setObjectParam(java.util.HashMap objectParam){
		this.objectParam = objectParam;
	}

	public void  setWhereSql(java.lang.String whereSql){
		this.whereSql = whereSql;
	}

	public void  setObjectParamWhere(java.util.List objectParamWhere){
		this.objectParamWhere = objectParamWhere;
	}

}