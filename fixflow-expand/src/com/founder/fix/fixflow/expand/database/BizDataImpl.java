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
package com.founder.fix.fixflow.expand.database;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.variable.BizData;



public class BizDataImpl implements BizData {

	public List<Map<String, Object>> getDataValueAll(String dataBaseId, String bizkey, String field, ExecutionContext executionContext) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(bizkey);
		String bizObjName = StringUtil.getString(ExpressionMgmt.execute("Fix_BizName", executionContext));
		String bizField = StringUtil.getString(ExpressionMgmt.execute("Fix_BizKeyFile", executionContext));
		SqlCommand sqlCommand = new SqlCommand(Context.getDbConnection(dataBaseId));
		List<Map<String, Object>> dataObjList = sqlCommand.queryForList("SELECT " + field + " FROM " + bizObjName + " WHERE " + bizField + "=?", paramList);

		return dataObjList;

	}

	public Object getDataValue(String dataBaseId, String bizkey, String field, ExecutionContext executionContext) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(bizkey);
		String bizObjName = StringUtil.getString(ExpressionMgmt.execute("Fix_BizName", executionContext));
		String bizField = StringUtil.getString(ExpressionMgmt.execute("Fix_BizKeyFile", executionContext));
		SqlCommand sqlCommand = new SqlCommand(Context.getDbConnection(dataBaseId));
		List<Map<String, Object>> dataObjList = sqlCommand.queryForList("SELECT " + field + " FROM " + bizObjName + " WHERE " + bizField + "=?", paramList);
		if (dataObjList.size() == 0) {
			return null;
		} else {
			return dataObjList.get(0).get(field);
		}
	}

	
}
