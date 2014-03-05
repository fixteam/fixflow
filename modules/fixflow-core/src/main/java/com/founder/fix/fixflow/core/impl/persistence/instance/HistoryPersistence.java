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
 * @author yangchenhui
 */
package com.founder.fix.fixflow.core.impl.persistence.instance;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.db.pagination.Pagination;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.QueryTableUtil;

/**
 * 归档操作持久化
 * @author yangchenhui
 *
 */
public class HistoryPersistence {
	
	Connection connection;
	ProcessDefinitionBehavior processDefinition;
	ProcessInstanceEntity processInstance;
	SqlCommand sqlCommand = null;
	java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public HistoryPersistence(Connection connection) {
		this.connection = connection;
		// 初始化数据库操作类
		sqlCommand = new SqlCommand(connection);
	}
	
	public boolean archive(Map<String,Object> paraMap){
		List<Object> whereObject = new ArrayList<Object>();
		String tmpWhereSql = getWhereSql(paraMap,whereObject);
		String processInstSql="select processinstance_id from " +QueryTableUtil.getDefaultTableName("fixflow_run_processinstance") +" where 1=1 ";
		String whereSql = processInstSql + tmpWhereSql;
		
		//流程实例表的归档
		String commonWhereSql =  " E.processInstance_id in("+whereSql+")";
		String processInstanceInsertSql = getInsertSqlString("fixflow_run_processinstance", commonWhereSql);
		sqlCommand.execute(processInstanceInsertSql, whereObject.toArray());
		//流程实例表删除  语句
		String processInstanceDeleteSql = getDeleteSqlString("fixflow_run_processinstance", "1=1 " + tmpWhereSql);
		
		//任务实例表的归档
		String taskInstanceSql = getInsertSqlString("fixflow_run_taskinstance", commonWhereSql);
		sqlCommand.execute(taskInstanceSql, whereObject.toArray());
		//任务实例表删除语句
		String taskInstanceDeleteSql = getDeleteSqlString("fixflow_run_taskinstance", commonWhereSql);
		
		//流程令牌表的归档
		String tokenSql = getInsertSqlString("fixflow_run_token", commonWhereSql);
		sqlCommand.execute(tokenSql, whereObject.toArray());
		//流程令牌表删除语句
		String tokenDeleteSql = getDeleteSqlString("fixflow_run_token", commonWhereSql);
		//流程变量表的归档
		String variableSql = getInsertSqlString("fixflow_run_variable", commonWhereSql);
		sqlCommand.execute(variableSql, whereObject.toArray());
		//流程变量表删除语句
		String variableDeleteSql = getDeleteSqlString("fixflow_run_variable", commonWhereSql);;
		
		//任务候选人的归档
		String tmpTaskidentitylinkSql = "E.taskinstance_id  in (select taskinstance_id from FIXFLOW_RUN_TASKINSTANCE T WHERE T.PROCESSINSTANCE_ID IN ("+ whereSql + "))";
		String taskidentitylinkSql = getInsertSqlString("fixflow_run_taskidentitylink", tmpTaskidentitylinkSql);;
		sqlCommand.execute(taskidentitylinkSql, whereObject.toArray());
		//任务候选人表删除语句
		String tmpTaskidentitylinkDeleteSql = " E.taskinstance_id  in (select taskinstance_id from FIXFLOW_RUN_TASKINSTANCE T WHERE T.PROCESSINSTANCE_ID IN (" + whereSql + "))";
		String taskidentitylinkDeleteSql = getDeleteSqlString("fixflow_run_taskidentitylink", tmpTaskidentitylinkDeleteSql);
		
		sqlCommand.execute(tokenDeleteSql, whereObject.toArray());
		sqlCommand.execute(variableDeleteSql, whereObject.toArray());
		sqlCommand.execute(taskidentitylinkDeleteSql, whereObject.toArray());
		sqlCommand.execute(taskInstanceDeleteSql, whereObject.toArray());
		sqlCommand.execute(processInstanceDeleteSql, whereObject.toArray());
		return true;
	}
	
	private String getInsertSqlString(String tableId,String whereSql){
		
		Pagination pagination = Context.getProcessEngineConfiguration().getDbConfig().getPagination();
		String tableName = QueryTableUtil.getDefaultTableName(tableId);
		String archiveTableName = QueryTableUtil.getArchiveTableName(tableId);
		String columnString = QueryTableUtil.getColumnStringWithOutArchiveTime(tableId);
		String insertSqlString = "insert into "+archiveTableName+" (" + columnString+",ARCHIVE_TIME) select "+columnString+","+pagination.getCurrentDateSql()+" from "+tableName +" E where " + whereSql;
		return insertSqlString;
	}
	
	private String getDeleteSqlString(String tableId,String whereSql){
		String tableName = QueryTableUtil.getDefaultTableName(tableId);
		String deleteSqlString  = "delete from "+tableName+" E where " + whereSql;
		return deleteSqlString;
	}

	/**
	 * 构造whereSql
	 * @param paraMap
	 * @param whereObj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getWhereSql(Map<String,Object> paraMap,List<Object> whereObj){
		String whereSql = "";
		
		//标志是否参数都不满足要求
		boolean flag = false;
		if(paraMap.containsKey("PROCESSINSTANCE_ID")){
			List<String> processInstanceIds = (List<String>)paraMap.get("PROCESSINSTANCE_ID");
			if(processInstanceIds != null && processInstanceIds.size() > 0){
				whereSql += " and processinstance_id in(";
				for(int i = 0;i<processInstanceIds.size() ; i++){
					if(i == 0){
						whereSql += "? ";
					}else{
						whereSql += ",? ";
					}
					whereObj.add(processInstanceIds.get(i));
				}
				whereSql += ")";
				flag = true;
			}
		}
		if(paraMap.containsKey("PROCESSDEFINITION_KEY")){
			whereSql += " and PROCESSDEFINITION_KEY = ?";
			whereObj.add(paraMap.get("PROCESSDEFINITION_KEY").toString());
			flag = true;
		}
		if(paraMap.containsKey("BEGIN")){
			whereSql += " and end_time >= ?";
			whereObj.add((Date)paraMap.get("BEGIN"));
			flag = true;
		}
		if(paraMap.containsKey("END")){
			whereSql += " and end_time <= ?";
			whereObj.add((Date)paraMap.get("END"));
			flag = true;
		}
		if(paraMap.containsKey("ALL")){
			flag = true;
		}
		if(!flag){
			throw new FixFlowException("归档参数异常，请检查",null);
		}
		whereSql += " and end_time is not null";
		return whereSql;
	}

}
