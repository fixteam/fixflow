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
package com.founder.fix.fixflow.core.impl.persistence.instance;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.db.PersistentObject;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.task.CommentImpl;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class CommentPersistence {

	public Connection connection;
	protected SqlCommand sqlCommand;

	public CommentPersistence(Connection connection) {
		this.connection = connection;
		// 初始化数据库操作类
		sqlCommand = new SqlCommand(connection);
	}

	public void insertComment(PersistentObject persistentObject) {

		Map<String, Object> commentContent = persistentObject.getPersistentState();

		// 构建查询参数
		Map<String, Object> objectParam = new HashMap<String, Object>();
		// 意见编号 String
		
		objectParam.put("ID", commentContent.get("id"));
		objectParam.put("TYPE", commentContent.get("type"));
		objectParam.put("TIME", commentContent.get("time"));
		objectParam.put("USER_ID", commentContent.get("userId"));
		objectParam.put("TASKINSTANCE_ID", commentContent.get("taskId"));
		objectParam.put("PROCESSINSTANCE_ID", commentContent.get("processInstanceId"));
		objectParam.put("ACTION", commentContent.get("action"));
		objectParam.put("MESSAGE", commentContent.get("message"));
		objectParam.put("FULL_MSG", (commentContent.get("fullMessage")!=null ? commentContent.get("fullMessage").toString().getBytes() : null));
		
		

		// 执行插入语句
		sqlCommand.insert("FIXFLOW_RUN_COMMENT", objectParam);

	}

	public List<CommentImpl> selectCommentsByProcessInstanceId(Object parameter) {

		List<Object> objectParamWhere = new ArrayList<Object>();

		String selectSql = "SELECT * FROM FIXFLOW_RUN_COMMENT WHERE PROCESSINSTANCE_ID = ? ORDER BY TIME DESC";

		List<Map<String, Object>> dataObj = sqlCommand.queryForList(selectSql, objectParamWhere);

		objectParamWhere.add(parameter.toString());

		List<CommentImpl> commentImplList = new ArrayList<CommentImpl>();

		for (Map<String, Object> dataMap : dataObj) {
			CommentImpl commentImpl = new CommentImpl();
			for (String dataKey : dataMap.keySet()) {

				if (dataKey.equals("ID")) {
					commentImpl.setId(StringUtil.getString(dataMap.get(dataKey)));
				}

				if (dataKey.equals("TYPE")) {
					commentImpl.setType(StringUtil.getString(dataMap.get(dataKey)));
				}

				if (dataKey.equals("TIME")) {
					commentImpl.setTime(StringUtil.getDate(dataMap.get(dataKey)));
				}

				if (dataKey.equals("USER_ID")) {
					commentImpl.setUserId(StringUtil.getString(dataMap.get(dataKey)));
				}

				if (dataKey.equals("TASKINSTANCE_ID")) {
					commentImpl.setTaskId(StringUtil.getString(dataMap.get(dataKey)));
				}

				if (dataKey.equals("PROCESSINSTANCE_ID")) {
					commentImpl.setProcessInstanceId(StringUtil.getString(dataMap.get(dataKey)));
				}

				if (dataKey.equals("ACTION")) {
					commentImpl.setAction(StringUtil.getString(dataMap.get(dataKey)));
				}

				if (dataKey.equals("MESSAGE")) {
					commentImpl.setMessage(StringUtil.getString(dataMap.get(dataKey)));
				}

				if (dataKey.equals("FULL_MSG")) {
					commentImpl.setFullMessage(StringUtil.getString(dataMap.get(dataKey)));
				}

			}
			commentImplList.add(commentImpl);
		}

		return commentImplList;
	}

}
