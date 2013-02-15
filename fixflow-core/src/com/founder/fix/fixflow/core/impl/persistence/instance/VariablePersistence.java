package com.founder.fix.fixflow.core.impl.persistence.instance;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.db.PersistentObject;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.impl.variable.VariableFlowTypeEntity;
import com.founder.fix.fixflow.core.impl.variable.VariableQueryEntity;
import com.founder.fix.fixflow.core.impl.variable.VariableTransferEntity;
import com.founder.fix.fixflow.core.objkey.VariableObjKey;

import com.founder.fix.fixflow.core.variable.VariableFlowType;

public class VariablePersistence {

	protected Connection connection;
	protected SqlCommand sqlCommand;

	public VariablePersistence(Connection connection) {
		this.connection = connection;
		sqlCommand = new SqlCommand(connection);
	}
	
	
	public Map<String, Object> queryVariable(Object persistentObject){
		
		
		VariableQueryEntity variableQueryEntity=(VariableQueryEntity)persistentObject;
		
		Map<String, Object> returnValue=new HashMap<String, Object>();
		List<VariableFlowTypeEntity> variableFlowTypeEntities=variableQueryEntity.getVariableFlowTypeEntities();
		List<String> variableNames=variableQueryEntity.getVariableNames();
		if (variableFlowTypeEntities.size() == 0) {
			return returnValue;
		}
		String sqlWhereQueryString = "";
		// 构建查询参数
		List<Object> objectParamWhere = new ArrayList<Object>();


		for (int i = 0; i < variableFlowTypeEntities.size(); i++) {

			VariableFlowTypeEntity variableFlowTypeEntity = variableFlowTypeEntities.get(i);
			VariableFlowType variableFlowType = variableFlowTypeEntity.getVariableFlowType();
			String dbTypeColumnName = variableFlowType + "_ID";
			String dbTypeValue = variableFlowTypeEntity.getTypeValue();
			

			if (i == variableFlowTypeEntities.size() - 1) {
				sqlWhereQueryString = sqlWhereQueryString + " " + dbTypeColumnName + "=? ";
			} else {
				sqlWhereQueryString = sqlWhereQueryString + " " + dbTypeColumnName + "=? AND ";
			}

			objectParamWhere.add(dbTypeValue);

		}
		
		if(variableNames.size()>0){
			sqlWhereQueryString=sqlWhereQueryString+" AND (";
		}
		
		for (int i = 0; i < variableNames.size(); i++) {
			
			String variableName=variableNames.get(i);
			
			if (i == variableNames.size() - 1) {
				sqlWhereQueryString = sqlWhereQueryString + " VARIABLE_KEY=? ";
			} else {
				sqlWhereQueryString = sqlWhereQueryString + " VARIABLE_KEY=? OR ";
			}

			objectParamWhere.add(variableName);
		}
		
		if(variableNames.size()>0){
			sqlWhereQueryString=sqlWhereQueryString+" )";
		}

		// 设置查询字符串
		String sqlText = "SELECT VARIABLE_KEY,VARIABLE_VALUE FROM FIXFLOW_RUN_VARIABLE  WHERE " + sqlWhereQueryString;
		

		List<Map<String, Object>> listMaps=sqlCommand.queryForList(sqlText,objectParamWhere);
		
		for (Map<String, Object> mapObj : listMaps) {
			String keyObj=StringUtil.getString(mapObj.get("VARIABLE_KEY"));
			
			byte[] bytes = (byte[]) mapObj.get("VARIABLE_VALUE");
			Object valueObj=bytesToObject(bytes);
			returnValue.put(keyObj, valueObj);
		}
		return  returnValue;
	}

	public void saveVariable(PersistentObject persistentObject) {
		
		VariableTransferEntity variableTransferEntity=(VariableTransferEntity)persistentObject;
		
		
		Map<String, Object> variableMap = variableTransferEntity.getVariableMap();
		List<VariableFlowTypeEntity> variableFlowTypeEntities = variableTransferEntity.getVariableFlowTypeEntities();
		for (String variableKey : variableMap.keySet()) {

			saveVariable(variableFlowTypeEntities, variableKey, variableMap.get(variableKey));

		}
	}

	private void saveVariable(List<VariableFlowTypeEntity> variableFlowTypeEntities, String variableKey, Object variableValue) {
		if (variableFlowTypeEntities.size() == 0) {
			return;
		}
		String sqlWhereQueryString = "";
		// 构建查询参数
		List<Object> objectParamWhere = new ArrayList<Object>();

		String processInstanceId = null;
		String tokenId = null;
		String taskInstanceId = null;
		String nodeId = null;

		for (int i = 0; i < variableFlowTypeEntities.size(); i++) {

			VariableFlowTypeEntity variableFlowTypeEntity = variableFlowTypeEntities.get(i);
			VariableFlowType variableFlowType = variableFlowTypeEntity.getVariableFlowType();
			String dbTypeColumnName = variableFlowType + "_ID";
			String dbTypeValue = variableFlowTypeEntity.getTypeValue();
			if (variableFlowType.equals(VariableFlowType.PROCESSINSTANCE)) {
				processInstanceId = dbTypeValue;
			}
			if (variableFlowType.equals(VariableFlowType.TASKINSTANCE)) {
				taskInstanceId = dbTypeValue;
			}
			if (variableFlowType.equals(VariableFlowType.NODE)) {
				nodeId = dbTypeValue;
			}
			if (variableFlowType.equals(VariableFlowType.TOKEN)) {
				tokenId = dbTypeValue;
			}

			if (i == variableFlowTypeEntities.size() - 1) {
				sqlWhereQueryString = sqlWhereQueryString + " " + dbTypeColumnName + "=? ";
			} else {
				sqlWhereQueryString = sqlWhereQueryString + " " + dbTypeColumnName + "=? AND ";
			}

			objectParamWhere.add(dbTypeValue);

		}
		       sqlWhereQueryString=sqlWhereQueryString+" AND VARIABLE_KEY=?";
		// 设置查询字符串
		String sqlText = "SELECT count(1) FROM FIXFLOW_RUN_VARIABLE  WHERE " + sqlWhereQueryString ;
		objectParamWhere.add(variableKey);
		// 执行查询流程是Sql语句,判断流程实例是否存在于数据库中.
		int rowNum = Integer.parseInt(sqlCommand.queryForValue(sqlText, objectParamWhere).toString());

		if (rowNum == 0) {
			// 数据库不存在这条流程实例,则执行创建新实例的方法.

			insertVariable(processInstanceId, tokenId, taskInstanceId, nodeId, variableKey, variableValue);

		} else {
			// 当数据库中已经存在这个流程实例

			updateVariable(processInstanceId, tokenId, taskInstanceId, nodeId, variableKey, variableValue,sqlWhereQueryString,objectParamWhere);

		}

	}

	private void insertVariable(String processInstanceId, String tokenId, String taskInstanceId, String nodeId, String variableKey, Object variableValue) {

		String processInstanceIdDb = processInstanceId;
		String tokenIdDb = tokenId;
		String taskInstanceIdDb = taskInstanceId;
		String nodeIdDb = nodeId;

		String variableKeyDb = variableKey;
		byte[] variableValueDb = ObjectToBytes(variableValue);
		String variableClassName = variableValue.getClass().getCanonicalName();

		// 构建查询参数
		Map<String, Object> objectParam = new HashMap<String, Object>();

		objectParam.put(VariableObjKey.ProcessInstanceId().DataBaseKey(), processInstanceIdDb);

		objectParam.put(VariableObjKey.VariableKey().DataBaseKey(), variableKeyDb);

		objectParam.put(VariableObjKey.VariableValue().DataBaseKey(), variableValueDb);

		objectParam.put(VariableObjKey.VariableClassName().DataBaseKey(), variableClassName);
		
		objectParam.put(VariableObjKey.TaskInstanceId().DataBaseKey(), taskInstanceIdDb);

		objectParam.put(VariableObjKey.TokenId().DataBaseKey(), tokenIdDb);
		
		objectParam.put(VariableObjKey.NodeId().DataBaseKey(), nodeIdDb);

		// 执行插入语句
		sqlCommand.insert("FIXFLOW_RUN_VARIABLE", objectParam);

	}

	private void updateVariable(String processInstanceId, String tokenId, String taskInstanceId, String nodeId, String variableKey, Object variableValue,String updateWhereSql,List<Object> objectParamWhere) {



		String variableKeyDb = variableKey;
		byte[] variableValueDb = ObjectToBytes(variableValue);
		String variableClassName = variableValue.getClass().getCanonicalName();

		// 构建查询参数
		Map<String, Object> objectParam = new HashMap<String, Object>();

		objectParam.put(VariableObjKey.VariableKey().DataBaseKey(), variableKeyDb);

		objectParam.put(VariableObjKey.VariableValue().DataBaseKey(), variableValueDb);

		objectParam.put(VariableObjKey.VariableClassName().DataBaseKey(), variableClassName);




		// 构建Where查询参数
		Object[] objectParamObj=new Object[objectParamWhere.size()];
		for (int i = 0; i < objectParamWhere.size(); i++) {
			objectParamObj[i]=objectParamWhere.get(i);
		}

		// 执行插入语句
		sqlCommand.update("FIXFLOW_RUN_VARIABLE", objectParam, updateWhereSql,objectParamObj);

	}
	
	
	public void deleteVariable(VariableQueryEntity variableQueryEntity){
		List<VariableFlowTypeEntity> variableFlowTypeEntities=variableQueryEntity.getVariableFlowTypeEntities();
		if (variableFlowTypeEntities.size() == 0) {
			return;
		}
		
		List<String> variableNames=variableQueryEntity.getVariableNames();
		
		String sqlWhereQueryString = "";
		// 构建查询参数
		List<Object> objectParamWhere = new ArrayList<Object>();


		for (int i = 0; i < variableFlowTypeEntities.size(); i++) {

			VariableFlowTypeEntity variableFlowTypeEntity = variableFlowTypeEntities.get(i);
			VariableFlowType variableFlowType = variableFlowTypeEntity.getVariableFlowType();
			String dbTypeColumnName = variableFlowType + "_ID";
			String dbTypeValue = variableFlowTypeEntity.getTypeValue();
			

			if (i == variableFlowTypeEntities.size() - 1) {
				sqlWhereQueryString = sqlWhereQueryString + " " + dbTypeColumnName + "=? ";
			} else {
				sqlWhereQueryString = sqlWhereQueryString + " " + dbTypeColumnName + "=? AND ";
			}

			objectParamWhere.add(dbTypeValue);

		}
		
		if(variableNames.size()>0){
			sqlWhereQueryString=sqlWhereQueryString+" AND (";
		}
		
		for (int i = 0; i < variableNames.size(); i++) {
			
			String variableName=variableNames.get(i);
			
			if (i == variableNames.size() - 1) {
				sqlWhereQueryString = sqlWhereQueryString + " VARIABLE_KEY=? ";
			} else {
				sqlWhereQueryString = sqlWhereQueryString + " VARIABLE_KEY=? OR ";
			}

			objectParamWhere.add(variableName);
		}
		
		if(variableNames.size()>0){
			sqlWhereQueryString=sqlWhereQueryString+" )";
		}

		
		
		// 构建Where查询参数
				Object[] objectParamObj=new Object[objectParamWhere.size()];
				for (int i = 0; i < objectParamWhere.size(); i++) {
					objectParamObj[i]=objectParamWhere.get(i);
				}
		//String sqlString="DELETE FROM FIXFLOW_RUN_TOKEN WHERE PROCESSINSTANCE_ID=?";
		
		sqlCommand.delete("FIXFLOW_RUN_VARIABLE",  sqlWhereQueryString,objectParamObj);
		
	}

	public static byte[] ObjectToBytes(Object obj) {

		ObjectOutput out = null;
		try {
			ByteArrayOutputStream byteout = new ByteArrayOutputStream();
			out = new ObjectOutputStream(byteout);
			out.writeObject(obj);
			byte[] buf = byteout.toByteArray();

			return buf;
		} catch (IOException e) {
			return null;
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {

				}
			}
		}
	}

	/**
	 * byte[] to long
	 * 
	 * @param b
	 * @return
	 */
	public static Object bytesToObject(byte[] b) {

		if (b.length > 0) {
			ObjectInput in = null;
			try {
				ByteArrayInputStream byteIn = new ByteArrayInputStream(b);
				in = new ObjectInputStream(byteIn);
				Object obj = in.readObject();

				if (obj != null) {
					return obj;
				}

			} catch (IOException e) {

			} catch (ClassNotFoundException e) {

			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {

					}
				}
			}

			return null;
		} else {

			return null;
		}
	}

}
