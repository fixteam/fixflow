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

//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.ObjectInput;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutput;
//import java.io.ObjectOutputStream;
//import java.sql.Connection;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.founder.fix.fixflow.core.impl.command.QueryVariablesCommand;
//import com.founder.fix.fixflow.core.impl.datavariable.DataVariableEntity;
//import com.founder.fix.fixflow.core.impl.db.PersistentObject;
//import com.founder.fix.fixflow.core.impl.db.SqlCommand;
//import com.founder.fix.fixflow.core.impl.util.StringUtil;
//import com.founder.fix.fixflow.core.objkey.VariableObjKey;

public class VariablePersistence {

//	protected Connection connection;
//	protected SqlCommand sqlCommand;
//
//	public VariablePersistence(Connection connection) {
//		this.connection = connection;
//		sqlCommand = new SqlCommand(connection);
//	}
//
//	public Map<String, Object> queryVariable(QueryVariablesCommand queryVariablesCommand) {
//
//		Map<String, Object> returnValue = new HashMap<String, Object>();
//
//		if (queryVariablesCommand == null) {
//			return returnValue;
//		}
//		List<String> variableNames = queryVariablesCommand.getVariableNames();
//
//		String sqlWhereQueryString = "";
//		// 构建查询参数
//		List<Object> objectParamWhere = new ArrayList<Object>();
//
//		String processInstanceId = queryVariablesCommand.getProcessInstanceId();
//		String tokenId = queryVariablesCommand.getTokenId();
//		String taskInstanceId = queryVariablesCommand.getTaskInstanceId();
//		String nodeId = queryVariablesCommand.getNodeId();
//
//		if (processInstanceId == null) {
//			sqlWhereQueryString = sqlWhereQueryString + " PROCESSINSTANCE_ID IS NULL AND ";
//		} else {
//			sqlWhereQueryString = sqlWhereQueryString + " PROCESSINSTANCE_ID=? AND ";
//			objectParamWhere.add(processInstanceId);
//		}
//
//		if (tokenId == null) {
//			sqlWhereQueryString = sqlWhereQueryString + " TOKEN_ID IS NULL AND ";
//		} else {
//			sqlWhereQueryString = sqlWhereQueryString + " TOKEN_ID=? AND";
//			objectParamWhere.add(tokenId);
//		}
//
//		if (taskInstanceId == null) {
//			sqlWhereQueryString = sqlWhereQueryString + " TASKINSTANCE_ID IS NULL AND ";
//		} else {
//			sqlWhereQueryString = sqlWhereQueryString + " TASKINSTANCE_ID=? AND ";
//			objectParamWhere.add(taskInstanceId);
//		}
//
//		if (nodeId == null) {
//			sqlWhereQueryString = sqlWhereQueryString + " NODE_ID IS NULL ";
//		} else {
//			sqlWhereQueryString = sqlWhereQueryString + " NODE_ID=? ";
//			objectParamWhere.add(nodeId);
//		}
//
//		if (variableNames != null) {
//			if (variableNames.size() > 0) {
//				sqlWhereQueryString = sqlWhereQueryString + " AND (";
//			}
//
//			for (int i = 0; i < variableNames.size(); i++) {
//
//				String variableName = variableNames.get(i);
//
//				if (i == variableNames.size() - 1) {
//					sqlWhereQueryString = sqlWhereQueryString + " VARIABLE_KEY=? ";
//				} else {
//					sqlWhereQueryString = sqlWhereQueryString + " VARIABLE_KEY=? OR ";
//				}
//
//				objectParamWhere.add(variableName);
//			}
//
//			if (variableNames.size() > 0) {
//				sqlWhereQueryString = sqlWhereQueryString + " )";
//			}
//		}
//
//		// 构建Where查询参数
//		Object[] objectParamObj = new Object[objectParamWhere.size()];
//		for (int i = 0; i < objectParamWhere.size(); i++) {
//			objectParamObj[i] = objectParamWhere.get(i);
//		}
//
//		// 设置查询字符串
//		String sqlText = "SELECT VARIABLE_KEY,VARIABLE_VALUE FROM " + VariableObjKey.VariableTableName() + "  WHERE " + sqlWhereQueryString;
//
//		List<Map<String, Object>> listMaps = sqlCommand.queryForList(sqlText, objectParamWhere);
//
//		for (Map<String, Object> mapObj : listMaps) {
//			String keyObj = StringUtil.getString(mapObj.get("VARIABLE_KEY"));
//
//			byte[] bytes = (byte[]) mapObj.get("VARIABLE_VALUE");
//			Object valueObj = bytesToObject(bytes);
//			returnValue.put(keyObj, valueObj);
//		}
//		return returnValue;
//	}
//
//	public void saveVariable(PersistentObject persistentObject) {
//
//		DataVariableEntity dataVariableEntity = (DataVariableEntity) persistentObject;
//
//		if (dataVariableEntity == null) {
//			return;
//		}
//		String sqlWhereQueryString = "";
//		// 构建查询参数
//		List<Object> objectParamWhere = new ArrayList<Object>();
//		String variableKey = dataVariableEntity.getId();
//		String processInstanceId = dataVariableEntity.getProcessInstanceId();
//		String tokenId = dataVariableEntity.getTokenId();
//		String taskInstanceId = dataVariableEntity.getTaskInstanceId();
//		String nodeId = dataVariableEntity.getNodeId();
//		String variableType = dataVariableEntity.getVariableType();
//
//		if (processInstanceId == null) {
//			sqlWhereQueryString = sqlWhereQueryString + " PROCESSINSTANCE_ID IS NULL AND ";
//		} else {
//			sqlWhereQueryString = sqlWhereQueryString + " PROCESSINSTANCE_ID=? AND ";
//			objectParamWhere.add(processInstanceId);
//		}
//
//		if (tokenId == null) {
//			sqlWhereQueryString = sqlWhereQueryString + " TOKEN_ID IS NULL AND ";
//		} else {
//			sqlWhereQueryString = sqlWhereQueryString + " TOKEN_ID=? AND";
//			objectParamWhere.add(tokenId);
//		}
//
//		if (taskInstanceId == null) {
//			sqlWhereQueryString = sqlWhereQueryString + " TASKINSTANCE_ID IS NULL AND ";
//		} else {
//			sqlWhereQueryString = sqlWhereQueryString + " TASKINSTANCE_ID=? AND ";
//			objectParamWhere.add(taskInstanceId);
//		}
//
//		if (nodeId == null) {
//			sqlWhereQueryString = sqlWhereQueryString + " NODE_ID IS NULL ";
//		} else {
//			sqlWhereQueryString = sqlWhereQueryString + " NODE_ID=? ";
//			objectParamWhere.add(nodeId);
//		}
//
//		sqlWhereQueryString = sqlWhereQueryString + " AND VARIABLE_KEY=?";
//
//		objectParamWhere.add(variableKey);
//
//		// 设置查询字符串
//		String sqlText = "SELECT count(1) FROM " + VariableObjKey.VariableTableName() + "  WHERE " + sqlWhereQueryString;
//
//		// 执行查询流程是Sql语句,判断流程实例是否存在于数据库中.
//		int rowNum = Integer.parseInt(sqlCommand.queryForValue(sqlText, objectParamWhere).toString());
//
//		if (rowNum == 0) {
//			// 数据库不存在这个变量,则执行创建新变量的方法.
//
//			insertVariable(processInstanceId, tokenId, taskInstanceId, nodeId, variableKey, dataVariableEntity.getExpressionValue(), variableType);
//
//		} else {
//			// 当数据库中已经存在这个变量
//
//			updateVariable(processInstanceId, tokenId, taskInstanceId, nodeId, variableKey, dataVariableEntity.getExpressionValue(), sqlWhereQueryString,
//					objectParamWhere, variableType);
//
//		}
//
//	}
//
//	private void insertVariable(String processInstanceId, String tokenId, String taskInstanceId, String nodeId, String variableKey, Object variableValue,
//			String variableType) {
//
//		String processInstanceIdDb = processInstanceId;
//		String tokenIdDb = tokenId;
//		String taskInstanceIdDb = taskInstanceId;
//		String nodeIdDb = nodeId;
//
//		String variableKeyDb = variableKey;
//		byte[] variableValueDb = ObjectToBytes(variableValue);
//		String variableClassName = null;
//		if (variableValue != null) {
//			variableClassName = variableValue.getClass().getCanonicalName();
//		}
//
//		// 构建查询参数
//		Map<String, Object> objectParam = new HashMap<String, Object>();
//
//		objectParam.put(VariableObjKey.ProcessInstanceId().DataBaseKey(), processInstanceIdDb);
//
//		objectParam.put(VariableObjKey.VariableKey().DataBaseKey(), variableKeyDb);
//
//		objectParam.put(VariableObjKey.VariableValue().DataBaseKey(), variableValueDb);
//
//		objectParam.put(VariableObjKey.VariableClassName().DataBaseKey(), variableClassName);
//
//		objectParam.put(VariableObjKey.TaskInstanceId().DataBaseKey(), taskInstanceIdDb);
//
//		objectParam.put(VariableObjKey.TokenId().DataBaseKey(), tokenIdDb);
//
//		objectParam.put(VariableObjKey.NodeId().DataBaseKey(), nodeIdDb);
//
//		objectParam.put(VariableObjKey.VariableType().DataBaseKey(), variableType);
//
//		if (variableType != null && variableType.equals(DataVariableEntity.QUERY_DATA_KEY)) {
//			objectParam.put(VariableObjKey.BizData().DataBaseKey(), StringUtil.getString(variableValue));
//		}
//
//		// 执行插入语句
//		sqlCommand.insert(VariableObjKey.VariableTableName(), objectParam);
//
//	}
//
//	private void updateVariable(String processInstanceId, String tokenId, String taskInstanceId, String nodeId, String variableKey, Object variableValue,
//			String updateWhereSql, List<Object> objectParamWhere, String variableType) {
//
//		String variableKeyDb = variableKey;
//		byte[] variableValueDb = ObjectToBytes(variableValue);
//		String variableClassName = null;
//		if (variableValue != null) {
//			variableClassName = variableValue.getClass().getCanonicalName();
//		}
//
//		// 构建查询参数
//		Map<String, Object> objectParam = new HashMap<String, Object>();
//
//		objectParam.put(VariableObjKey.VariableKey().DataBaseKey(), variableKeyDb);
//
//		objectParam.put(VariableObjKey.VariableValue().DataBaseKey(), variableValueDb);
//
//		objectParam.put(VariableObjKey.VariableClassName().DataBaseKey(), variableClassName);
//
//		objectParam.put(VariableObjKey.VariableType().DataBaseKey(), variableType);
//
//		if (variableType != null && variableType.equals(DataVariableEntity.QUERY_DATA_KEY)) {
//			objectParam.put(VariableObjKey.BizData().DataBaseKey(), StringUtil.getString(variableValue));
//		}
//
//		// 构建Where查询参数
//		Object[] objectParamObj = new Object[objectParamWhere.size()];
//		for (int i = 0; i < objectParamWhere.size(); i++) {
//			objectParamObj[i] = objectParamWhere.get(i);
//		}
//
//		// 执行插入语句
//		sqlCommand.update(VariableObjKey.VariableTableName(), objectParam, updateWhereSql, objectParamObj);
//
//	}
//
//	public void deleteVariable(QueryVariablesCommand queryVariablesCommand) {
//
//		if (queryVariablesCommand == null) {
//			return;
//		}
//		List<String> variableNames = queryVariablesCommand.getVariableNames();
//
//		String sqlWhereQueryString = "";
//		// 构建查询参数
//		List<Object> objectParamWhere = new ArrayList<Object>();
//
//		String processInstanceId = queryVariablesCommand.getProcessInstanceId();
//		String tokenId = queryVariablesCommand.getTokenId();
//		String taskInstanceId = queryVariablesCommand.getTaskInstanceId();
//		String nodeId = queryVariablesCommand.getNodeId();
//
//		if (processInstanceId == null) {
//			sqlWhereQueryString = sqlWhereQueryString + " PROCESSINSTANCE_ID IS NULL AND ";
//		} else {
//			sqlWhereQueryString = sqlWhereQueryString + " PROCESSINSTANCE_ID=? AND ";
//			objectParamWhere.add(processInstanceId);
//		}
//
//		if (tokenId == null) {
//			sqlWhereQueryString = sqlWhereQueryString + " TOKEN_ID IS NULL AND ";
//		} else {
//			sqlWhereQueryString = sqlWhereQueryString + " TOKEN_ID=? AND";
//			objectParamWhere.add(tokenId);
//		}
//
//		if (taskInstanceId == null) {
//			sqlWhereQueryString = sqlWhereQueryString + " TASKINSTANCE_ID IS NULL AND ";
//		} else {
//			sqlWhereQueryString = sqlWhereQueryString + " TASKINSTANCE_ID=? AND ";
//			objectParamWhere.add(taskInstanceId);
//		}
//
//		if (nodeId == null) {
//			sqlWhereQueryString = sqlWhereQueryString + " NODE_ID IS NULL ";
//		} else {
//			sqlWhereQueryString = sqlWhereQueryString + " NODE_ID=? ";
//			objectParamWhere.add(nodeId);
//		}
//
//		/*
//		 * sqlWhereQueryString = sqlWhereQueryString + " AND VARIABLE_KEY=?";
//		 * 
//		 * 
//		 * List<VariableFlowTypeEntity> variableFlowTypeEntities =
//		 * variableQueryEntity.getVariableFlowTypeEntities(); if
//		 * (variableFlowTypeEntities.size() == 0) { return; }
//		 */
//
//		if (variableNames != null) {
//			if (variableNames.size() > 0) {
//				sqlWhereQueryString = sqlWhereQueryString + " AND (";
//			}
//
//			for (int i = 0; i < variableNames.size(); i++) {
//
//				String variableName = variableNames.get(i);
//
//				if (i == variableNames.size() - 1) {
//					sqlWhereQueryString = sqlWhereQueryString + " VARIABLE_KEY=? ";
//				} else {
//					sqlWhereQueryString = sqlWhereQueryString + " VARIABLE_KEY=? OR ";
//				}
//
//				objectParamWhere.add(variableName);
//			}
//
//			if (variableNames.size() > 0) {
//				sqlWhereQueryString = sqlWhereQueryString + " )";
//			}
//		}
//
//		// 构建Where查询参数
//		Object[] objectParamObj = new Object[objectParamWhere.size()];
//		for (int i = 0; i < objectParamWhere.size(); i++) {
//			objectParamObj[i] = objectParamWhere.get(i);
//		}
//		sqlCommand.delete(VariableObjKey.VariableTableName(), sqlWhereQueryString, objectParamObj);
//
//	}
//
//	public static byte[] ObjectToBytes(Object obj) {
//
//		ObjectOutput out = null;
//		try {
//			ByteArrayOutputStream byteout = new ByteArrayOutputStream();
//			out = new ObjectOutputStream(byteout);
//			out.writeObject(obj);
//			byte[] buf = byteout.toByteArray();
//
//			return buf;
//		} catch (IOException e) {
//			return null;
//		} finally {
//			if (out != null) {
//				try {
//					out.close();
//				} catch (IOException e) {
//
//				}
//			}
//		}
//	}
//
//	/**
//	 * byte[] to long
//	 * 
//	 * @param b
//	 * @return
//	 */
//	public static Object bytesToObject(byte[] b) {
//
//		if (b.length > 0) {
//			ObjectInput in = null;
//			try {
//				ByteArrayInputStream byteIn = new ByteArrayInputStream(b);
//				in = new ObjectInputStream(byteIn);
//				Object obj = in.readObject();
//
//				if (obj != null) {
//					return obj;
//				}
//
//			} catch (IOException e) {
//
//			} catch (ClassNotFoundException e) {
//
//			} finally {
//				if (in != null) {
//					try {
//						in.close();
//					} catch (IOException e) {
//
//					}
//				}
//			}
//
//			return null;
//		} else {
//
//			return null;
//		}
//	}

}
