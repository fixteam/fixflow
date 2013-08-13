package com.founder.fix.fixflow.core.impl.persistence.instance;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.objkey.ProcessInstanceObjKey;

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
		String processInstanceSql = "insert into "+ProcessInstanceObjKey.ProcessInstanceHisTableName()+
				"(PROCESSINSTANCE_ID,PROCESSDEFINITION_ID,SUBJECT,START_TIME,END_TIME,DEFINITION_ID,ROOTTOKEN_ID,BIZ_KEY,INITIATOR,ISSUSPENDED,PROCESSDEFINITION_KEY,PARENT_INSTANCE_ID,PARENT_INSTANCE_TOKEN_ID,UPDATE_TIME,START_AUTHOR,PROCESSLOCATION,ISPIGEONHOLE,INSTANCE_STATUS,ARCHIVE_TIME)"+
				"select PROCESSINSTANCE_ID,PROCESSDEFINITION_ID,SUBJECT,START_TIME,END_TIME,DEFINITION_ID,ROOTTOKEN_ID,BIZ_KEY,INITIATOR,ISSUSPENDED,PROCESSDEFINITION_KEY,PARENT_INSTANCE_ID,PARENT_INSTANCE_TOKEN_ID,UPDATE_TIME,START_AUTHOR,PROCESSLOCATION,ISPIGEONHOLE,INSTANCE_STATUS,sysdate " +
				" from "+ProcessInstanceObjKey.ProcessInstanceTableName() +
				" E WHERE E.processinstance_id  in (select processinstance_id from " +ProcessInstanceObjKey.ProcessInstanceTableName() +
				" where " +whereSql +")";
		sqlCommand.execute(processInstanceSql, whereObject.toArray());
		
		String processInstanceDeleteSql = "delete from "+ProcessInstanceObjKey.ProcessInstanceTableName()+" where "+whereSql;
		sqlCommand.execute(processInstanceDeleteSql, whereObject.toArray());
		
		return true;
	}
	
	public String getWhereSql(Map<String,Object> paraMap,List<Object> whereObj){
		String whereSql = " 1=1 ";
		if(paraMap.containsKey("PROCESSINSTANCEID")){
			whereSql += " and processinstance_id = ?";
			whereObj.add(paraMap.get("PROCESSINSTANCEID").toString());
		}
		
		return whereSql;
	}

}
