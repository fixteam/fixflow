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

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.objkey.ProcessInstanceObjKey;
import com.founder.fix.fixflow.core.objkey.TaskIdentityLinkObjKey;
import com.founder.fix.fixflow.core.objkey.TaskInstanceObjKey;
import com.founder.fix.fixflow.core.objkey.TokenObjKey;
import com.founder.fix.fixflow.core.objkey.VariableObjKey;

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
		String whereSql = getWhereSql(paraMap,whereObject);
		//流程实例表的归档
		String processInstanceSql = "insert into "+ProcessInstanceObjKey.ProcessInstanceHisTableName()+
				"(PROCESSINSTANCE_ID,PROCESSDEFINITION_ID,SUBJECT,START_TIME,END_TIME,DEFINITION_ID,ROOTTOKEN_ID,BIZ_KEY,INITIATOR,ISSUSPENDED,PROCESSDEFINITION_KEY,PARENT_INSTANCE_ID,PARENT_INSTANCE_TOKEN_ID,UPDATE_TIME,START_AUTHOR,PROCESSLOCATION,ISPIGEONHOLE,INSTANCE_STATUS,ARCHIVE_TIME)"+
				"select PROCESSINSTANCE_ID,PROCESSDEFINITION_ID,SUBJECT,START_TIME,END_TIME,DEFINITION_ID,ROOTTOKEN_ID,BIZ_KEY,INITIATOR,ISSUSPENDED,PROCESSDEFINITION_KEY,PARENT_INSTANCE_ID,PARENT_INSTANCE_TOKEN_ID,UPDATE_TIME,START_AUTHOR,PROCESSLOCATION,ISPIGEONHOLE,INSTANCE_STATUS,sysdate " +
				" from "+ProcessInstanceObjKey.ProcessInstanceTableName() +
				" E WHERE E.processinstance_id  in (" +whereSql +")";
		sqlCommand.execute(processInstanceSql, whereObject.toArray());
		//流程实例表删除  语句
		String processInstanceDeleteSql = "delete from "+ProcessInstanceObjKey.ProcessInstanceTableName()+" where processinstance_id  in ("+whereSql+")";
		
		//任务实例表的归档
		String taskInstanceSql = "insert into "+TaskInstanceObjKey.TaskInstanceHisTableName()+
				"(TASKINSTANCE_ID,PROCESSINSTANCE_ID,PROCESSDEFINITION_ID,VERSION,TOKEN_ID,NODE_ID,DESCRIPTION,PARENTTASK_ID,ASSIGNEE,CLAIM_TIME,NAME,CREATE_TIME,START_TIME,ISBLOCKING,END_TIME,DUEDATE," +
				"PRIORITY,CATEGORY,OWNER,DELEGATIONSTATESTRING,BIZKEY,COMMAND_TYPE,COMMAND_MESSAGE,TASK_COMMENT,NODE_NAME,PROCESSDEFINITION_KEY,FORMURI,TASKGROUP,TASKTYPE,PROCESSDEFINITION_NAME,ISCANCELLED,ISSUSPENDED,ISOPEN,ISDRAFT," +
				"EXPECTED_EXECUTIONTIME,AGENT,ADMIN,FORMURIVIEW,CALLACTIVITY_INSTANCE_ID,COMMAND_ID,PENDINGTASKID,ARCHIVE_TIME)"+
				"select " +
				"TASKINSTANCE_ID,PROCESSINSTANCE_ID,PROCESSDEFINITION_ID,VERSION,TOKEN_ID,NODE_ID,DESCRIPTION,PARENTTASK_ID,ASSIGNEE,CLAIM_TIME,NAME,CREATE_TIME,START_TIME,ISBLOCKING,END_TIME,DUEDATE," +
				"PRIORITY,CATEGORY,OWNER,DELEGATIONSTATESTRING,BIZKEY,COMMAND_TYPE,COMMAND_MESSAGE,TASK_COMMENT,NODE_NAME,PROCESSDEFINITION_KEY,FORMURI,TASKGROUP,TASKTYPE,PROCESSDEFINITION_NAME,ISCANCELLED,ISSUSPENDED,ISOPEN,ISDRAFT," +
				"EXPECTED_EXECUTIONTIME,AGENT,ADMIN,FORMURIVIEW,CALLACTIVITY_INSTANCE_ID,COMMAND_ID,PENDINGTASKID,sysdate"+
				" from "+TaskInstanceObjKey.TaskInstanceTableName() +
				" E WHERE E.processinstance_id  in (" + whereSql + ")";
		sqlCommand.execute(taskInstanceSql, whereObject.toArray());
		//任务实例表删除语句
		String taskInstanceDeleteSql = "delete from "+TaskInstanceObjKey.TaskInstanceTableName()+" E WHERE E.processinstance_id  in ("+whereSql+")";
		
		//流程令牌表的归档
		String tokenSql = "insert into "+TokenObjKey.TokenHisTableName()+
				"(TOKEN_ID,NAME,START_TIME,END_TIME,NODEENTER_TIME,ISABLETOREACTIVATEPARENT,ISSUSPENDED,TOKEN_LOCK,NODE_ID,PROCESSINSTANCE_ID,PARENT_ID,ISSUBPROCESSROOTTOKEN,FREETOKEN,PARENT_FREETOKEN_ID,ARCHIVE_TIME)"+
				"select " +
				"TOKEN_ID,NAME,START_TIME,END_TIME,NODEENTER_TIME,ISABLETOREACTIVATEPARENT,ISSUSPENDED,TOKEN_LOCK,NODE_ID,PROCESSINSTANCE_ID,PARENT_ID,ISSUBPROCESSROOTTOKEN,FREETOKEN,PARENT_FREETOKEN_ID,sysdate"+
				" from "+TokenObjKey.TokenTableName() +
				" E WHERE E.processinstance_id  in (" + whereSql + ")";
		sqlCommand.execute(tokenSql, whereObject.toArray());
		//流程令牌表删除语句
		String tokenDeleteSql = "delete from "+TokenObjKey.TokenTableName()+" E WHERE E.processinstance_id  in ("+whereSql+")";
		
		//流程变量表的归档
		String variableSql = "insert into "+VariableObjKey.VariableHisTableName()+
				"(PROCESSINSTANCE_ID,VARIABLE_KEY,VARIABLE_VALUE,VARIABLE_CLASSNAME,TASKINSTANCE_ID,TOKEN_ID,NODE_ID,ARCHIVE_TIME,VARIABLE_TYPE,BIZ_DATA)"+
				"select " +
				"PROCESSINSTANCE_ID,VARIABLE_KEY,VARIABLE_VALUE,VARIABLE_CLASSNAME,TASKINSTANCE_ID,TOKEN_ID,NODE_ID,sysdate,VARIABLE_TYPE,BIZ_DATA"+
				" from "+VariableObjKey.VariableTableName() +
				" E WHERE E.processinstance_id  in (" + whereSql + ")";
		sqlCommand.execute(variableSql, whereObject.toArray());
		//流程变量表删除语句
		String variableDeleteSql = "delete from "+VariableObjKey.VariableTableName()+" E WHERE E.processinstance_id  in ("+whereSql+")";
		
		//任务候选人的归档
		String taskidentitylinkSql = "insert into "+TaskIdentityLinkObjKey.TaskIdentityLinkHisTableName()+
				"(ID,TYPE,USER_ID,GROUP_ID,GROUP_TYPE,TASKINSTANCE_ID,INCLUDE_EXCLUSION,ARCHIVE_TIME)"+
				"select " +
				"ID,TYPE,USER_ID,GROUP_ID,GROUP_TYPE,TASKINSTANCE_ID,INCLUDE_EXCLUSION,sysdate"+
				" from "+TaskIdentityLinkObjKey.TaskIdentityLinkTableName() +
				" E WHERE E.taskinstance_id  in (select taskinstance_id from "+TaskInstanceObjKey.TaskInstanceTableName()+" T WHERE T.PROCESSINSTANCE_ID IN (" + whereSql + "))";
		sqlCommand.execute(taskidentitylinkSql, whereObject.toArray());
		//任务候选人表删除语句
		String taskidentitylinkDeleteSql = "delete from "+TaskIdentityLinkObjKey.TaskIdentityLinkTableName() +" E WHERE E.taskinstance_id  in (select taskinstance_id from "+TaskInstanceObjKey.TaskInstanceTableName()+" T WHERE T.PROCESSINSTANCE_ID IN (" + whereSql + "))";
		
		sqlCommand.execute(tokenDeleteSql, whereObject.toArray());
		sqlCommand.execute(variableDeleteSql, whereObject.toArray());
		sqlCommand.execute(taskidentitylinkDeleteSql, whereObject.toArray());
		sqlCommand.execute(taskInstanceDeleteSql, whereObject.toArray());
		sqlCommand.execute(processInstanceDeleteSql, whereObject.toArray());
		return true;
	}
	
	/**
	 * 构造whereSql
	 * @param paraMap
	 * @param whereObj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getWhereSql(Map<String,Object> paraMap,List<Object> whereObj){
		String whereSql = "select processinstance_id from " +ProcessInstanceObjKey.ProcessInstanceTableName() +" where 1=1 ";
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
