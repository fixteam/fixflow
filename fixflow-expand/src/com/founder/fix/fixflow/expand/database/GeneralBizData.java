package com.founder.fix.fixflow.expand.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public class GeneralBizData {

	public static List<Map<String, Object>> getDataValueAll(String dataBaseId, String bizkey, String field, ExecutionContext executionContext) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(bizkey);
		String bizObjName = StringUtil.getString(ExpressionMgmt.execute("Fix_BizName", executionContext));
		String bizField = StringUtil.getString(ExpressionMgmt.execute("Fix_BizKeyFile", executionContext));
		SqlCommand sqlCommand = new SqlCommand(Context.getDbConnection(dataBaseId));
		List<Map<String, Object>> dataObjList = sqlCommand.queryForList("SELECT " + field + " FROM " + bizObjName + " WHERE " + bizField + "=?", paramList);

		return dataObjList;

	}

	public static Object getDataValue(String dataBaseId, String bizkey, String field, ExecutionContext executionContext) {
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
