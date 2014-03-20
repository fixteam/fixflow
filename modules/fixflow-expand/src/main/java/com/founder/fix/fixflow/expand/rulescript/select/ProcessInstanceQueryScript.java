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
package com.founder.fix.fixflow.expand.rulescript.select;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.db.pagination.Pagination;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;
import com.founder.fix.fixflow.core.impl.db.ListQueryParameterObject;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceQueryImpl;
import com.founder.fix.fixflow.core.impl.util.QueryTableUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.scriptlanguage.SelectRulesScript;

public class ProcessInstanceQueryScript implements SelectRulesScript {

	Pagination pagination = Context.getProcessEngineConfiguration().getDbConfig().getPagination();

	public Object execute(Object parameter, SqlCommand sqlCommand, ProcessEngineConfigurationImpl processEngineConfiguration) {
		ListQueryParameterObject listQueryParameterObject = (ListQueryParameterObject) parameter;
		ProcessInstanceQueryImpl processInstanceQueryImpl = (ProcessInstanceQueryImpl) listQueryParameterObject.getParameter();
		Page page = listQueryParameterObject.getPage();
		

		return selectProcessInstanceByQueryCriteria(processInstanceQueryImpl,page,sqlCommand);
	}
	
	
	
	/**
	 * 查询流程实例
	 * @param processInstanceQuery  query对象
	 * @param page 页数
	 * @return 
	 */
	public List<Map<String, Object>> selectProcessInstanceByQueryCriteria(ProcessInstanceQueryImpl processInstanceQuery, Page page,SqlCommand sqlCommand) {
		String sqlString = " select " + Context.getProcessEngineConfiguration().getDbConfig().getDbSqlMap().get("topOrderBy") + " E.* ";
		if(processInstanceQuery.getQueryExpandTo()!=null && processInstanceQuery.getQueryExpandTo().getFieldSql()!=null&&!processInstanceQuery.getQueryExpandTo().getFieldSql().equals("")){
			sqlString=sqlString+" , "+processInstanceQuery.getQueryExpandTo().getFieldSql();
		}
		List<Object> objectParamWhere = new ArrayList<Object>();
		sqlString = selectProcessInstanceByQueryCriteriaSql(sqlString, processInstanceQuery, objectParamWhere,sqlCommand);
		//if (processInstanceQuery.getOrderBy() != null) {
		//	sqlString = sqlString + " order by " + processInstanceQuery.getOrderBy().toString();
		//}
		
		
		String orderByString="";
		
		if (processInstanceQuery.getOrderBy() != null && page != null) {
			String orderBySql=processInstanceQuery.getOrderBy();
			String orderBySqlFin="";
			if(orderBySql.indexOf(",")>=0){
				String[] orderBySqlTemp=orderBySql.split(",");
				for (String orderByObj : orderBySqlTemp) {
					if(orderBySqlFin.equals("")){
						orderBySqlFin=orderBySqlFin+orderByObj.substring(orderByObj.indexOf(".")+1,orderByObj.length());
					}
					else{
						orderBySqlFin=orderBySqlFin+","+orderByObj.substring(orderByObj.indexOf(".")+1,orderByObj.length());
					}
				}
				orderByString = orderByString + " order by " + orderBySqlFin;
				
			}else{
				orderByString = orderByString + " order by " + processInstanceQuery.getOrderBy().toString().substring(2);
			}
		}
		
		
		if (page != null) {
			Pagination pagination = Context.getProcessEngineConfiguration().getDbConfig().getPagination();
			sqlString = pagination.getPaginationSql(sqlString, page.getFirstResult(), page.getMaxResults(), "*",orderByString);
		}
		else{
			if (processInstanceQuery.getOrderBy() != null) {
				sqlString = sqlString + " order by " + processInstanceQuery.getOrderBy().toString();
				}
		}
		/*
		if (processInstanceQuery.getOrderBy() != null&&page != null) {
			String orderBySql=processInstanceQuery.getOrderBy();
			String orderBySqlFin="";
			if(orderBySql.indexOf(",")>=0){
				String[] orderBySqlTemp=orderBySql.split(",");
				for (String orderByObj : orderBySqlTemp) {
					if(orderBySqlFin.equals("")){
						orderBySqlFin=orderBySqlFin+orderByObj.substring(orderByObj.indexOf(".")+1,orderByObj.length());
					}else{
						orderBySqlFin=orderBySqlFin+","+orderByObj.substring(orderByObj.indexOf(".")+1,orderByObj.length());
					}
				}
				sqlString = sqlString + " order by " + orderBySqlFin;
			}else{
				sqlString = sqlString + " order by " + processInstanceQuery.getOrderBy().toString().substring(2);
			}
		}*/
		List<Map<String, Object>> dataObj = sqlCommand.queryForList(sqlString, objectParamWhere);
		
		
		
		return dataObj;
		
	}
	
	
	/**
	 * 构造查询sql语句
	 * @param sqlString
	 * @param processInstanceQuery query对象
	 * @param page 页数
	 * @param objectParamWhere 需要构造where参数
	 * @return
	 */
	public String selectProcessInstanceByQueryCriteriaSql(String sqlString, ProcessInstanceQueryImpl processInstanceQuery,List<Object> objectParamWhere,SqlCommand sqlCommand) {
		
		
		
		
		sqlString = sqlString + " FROM "+QueryTableUtil.getTableSelect("fixflow_run_processinstance", processInstanceQuery.getQueryLocation())+" E ";
		//自定义扩展查询
		if(processInstanceQuery.getQueryExpandTo()!=null&&processInstanceQuery.getQueryExpandTo().getLeftJoinSql()!=null&&!processInstanceQuery.getQueryExpandTo().getLeftJoinSql().equals("")){
			sqlString=sqlString+processInstanceQuery.getQueryExpandTo().getLeftJoinSql();
		}
		if((processInstanceQuery.getProcessDefinitionName() !=null && !"".equals(processInstanceQuery.getProcessDefinitionName()))||(processInstanceQuery.getProcessDefinitionNameLike() !=null && !"".equals(processInstanceQuery.getProcessDefinitionNameLike()))){
			sqlString += "left join FIXFLOW_DEF_PROCESSDEFINITION PD on pd.process_id = E.processdefinition_id";
		}
		sqlString = sqlString + " WHERE 1=1";
		//自定义扩展查询
		if(processInstanceQuery.getQueryExpandTo()!=null&&processInstanceQuery.getQueryExpandTo().getWhereSql()!=null&&!processInstanceQuery.getQueryExpandTo().getWhereSql().equals("")){
			sqlString=sqlString+" and "+processInstanceQuery.getQueryExpandTo().getWhereSql();
			if(processInstanceQuery.getQueryExpandTo().getWhereSqlObj()!=null&&processInstanceQuery.getQueryExpandTo().getWhereSqlObj().size()>0){
				objectParamWhere.addAll(processInstanceQuery.getQueryExpandTo().getWhereSqlObj());
			}
		}
		if(processInstanceQuery.getProcessDefinitionNameLike() !=null && !"".equals(processInstanceQuery.getProcessDefinitionNameLike())){
			sqlString += " and PD.process_name like ?";
			objectParamWhere.add("%"+processInstanceQuery.getProcessDefinitionNameLike()+"%");
		}
		if(processInstanceQuery.getProcessDefinitionName() !=null && !"".equals(processInstanceQuery.getProcessDefinitionName())){
			sqlString += " and PD.process_name = ?";
			objectParamWhere.add(processInstanceQuery.getProcessDefinitionName());
		}
		if (processInstanceQuery.getBusinessKey() != null) {
			sqlString = sqlString + " and E.BIZ_KEY=? ";
			objectParamWhere.add(processInstanceQuery.getBusinessKey());
		}
		
		if (processInstanceQuery.getBusinessKeyLike() != null) {
			sqlString = sqlString + " and E.BIZ_KEY LIKE '%"+processInstanceQuery.getBusinessKeyLike()+"%'";
			//objectParamWhere.add(processInstanceQuery.getBusinessKeyLike());
		}
		
		if (processInstanceQuery.getIsPigeonhole() != null) {
			sqlString = sqlString + " and (E.ISPIGEONHOLE=? or E.ISPIGEONHOLE IS NULL) ";
			objectParamWhere.add(processInstanceQuery.getIsPigeonhole());
		}
		
		if (processInstanceQuery.getIsEnd() != null) {
			if(StringUtil.getBoolean(processInstanceQuery.getIsEnd())){
				sqlString = sqlString + " AND E.END_TIME IS NOT NULL ";
			}else{
				sqlString = sqlString + " AND E.END_TIME IS NULL ";
			}
		}
		if (processInstanceQuery.getProcessInstanceId() != null) {
			sqlString = sqlString + " and E.PROCESSINSTANCE_ID=? ";
			objectParamWhere.add(processInstanceQuery.getProcessInstanceId());
		}
		if (processInstanceQuery.getProcessInstanceId() != null) {
			if(processInstanceQuery.isContainsSubProcess()){
				//这个地方需要用到递归去寻找所有的子流程
				List<Object> dataList=new ArrayList<Object>();
				dataList.add(processInstanceQuery.getProcessInstanceId());
				StringBuffer  processInstanceIdList=new StringBuffer();
				List<Map<String, Object>> dataListMaps=sqlCommand.queryForList("SELECT * FROM "+QueryTableUtil.getTableSelect("fixflow_run_processinstance", processInstanceQuery.getQueryLocation())+" WHERE PARENT_INSTANCE_ID=?", dataList);
				processInstanceIdList.append("'"+processInstanceQuery.getProcessInstanceId()+"'");
				if(dataListMaps.size()>0){
					getSubProcessId(processInstanceQuery.getProcessInstanceId(),processInstanceIdList,sqlCommand,processInstanceQuery);
				}
				sqlString = sqlString + " and E.PROCESSINSTANCE_ID in ("+processInstanceIdList.toString()+") ";
			}
			else{
				sqlString = sqlString + " and E.PROCESSINSTANCE_ID=? ";
					objectParamWhere.add(processInstanceQuery.getProcessInstanceId());
			}
		}
		if (processInstanceQuery.getProcessDefinitionId() != null) {
			sqlString = sqlString + " and E.PROCESSDEFINITION_ID=? ";
			objectParamWhere.add(processInstanceQuery.getProcessDefinitionId());
		}
		if (processInstanceQuery.getProcessDefinitionKey() != null) {
			sqlString = sqlString + " and E.PROCESSDEFINITION_KEY=? ";
			objectParamWhere.add(processInstanceQuery.getProcessDefinitionKey());
		}
		if(processInstanceQuery.getProcessDefinitionKeyList().size()>0){
			List<String> processDefinitionKeyList = processInstanceQuery.getProcessDefinitionKeyList();
			sqlString = sqlString + " and E.PROCESSDEFINITION_KEY IN (";
			for (int i = 0; i < processDefinitionKeyList.size(); i++) {
				if (i == processDefinitionKeyList.size() - 1) {
					String processDefinitionKey = processDefinitionKeyList.get(i);
					sqlString = sqlString + " ? ) ";
					objectParamWhere.add(processDefinitionKey.toString());
				} else {
					String processDefinitionKey = processDefinitionKeyList.get(i);
					sqlString = sqlString + " ?, ";
					objectParamWhere.add(processDefinitionKey.toString());
				}
			}
		}
		if (processInstanceQuery.getInitiator() != null) {
			sqlString = sqlString + " and E.INITIATOR=? ";
			objectParamWhere.add(processInstanceQuery.getInitiator());
		}
		if (processInstanceQuery.getSubject() != null) {
			sqlString = sqlString + " and E.SUBJECT=?";
			objectParamWhere.add(processInstanceQuery.getSubject());
		}
		if (processInstanceQuery.getSubjectLike() != null) {
			sqlString = sqlString + " and E.SUBJECT LIKE '%"+processInstanceQuery.getSubjectLike()+"%' ";
		}
		
		if (processInstanceQuery.getInitiatorLike() != null) {
			sqlString = sqlString + " and E.INITIATOR LIKE '%"+processInstanceQuery.getInitiatorLike()+"%' ";
		}
		if (processInstanceQuery.getStartTime() != null) {
			sqlString = sqlString + " and  E.START_TIME=? ";
			objectParamWhere.add(processInstanceQuery.getStartTime());
		}
		if (processInstanceQuery.getIsSuspended() != null) {
			sqlString = sqlString + " and  E.ISSUSPENDED=? ";
			objectParamWhere.add(processInstanceQuery.getIsSuspended());
		}
		if (processInstanceQuery.getStartTimeBefore() != null) {
			sqlString = sqlString + " and  E.START_TIME>=? ";
			objectParamWhere.add(processInstanceQuery.getStartTimeBefore());
		}
		if (processInstanceQuery.getStartTimeAfter() != null) {
			sqlString = sqlString + " and  E.START_TIME<=? ";
			objectParamWhere.add(processInstanceQuery.getStartTimeAfter());
		}
		if (processInstanceQuery.getEndTime() != null) {
			sqlString = sqlString + " and  E.END_TIME =?";
			objectParamWhere.add(processInstanceQuery.getEndTime());
		}
		if (processInstanceQuery.getEndTimeAfter() != null) {
			sqlString = sqlString + " and  E.END_TIME<=?";
			objectParamWhere.add(processInstanceQuery.getEndTimeAfter());
		}
		if (processInstanceQuery.getEndTimeBefore() != null) {
			sqlString = sqlString + " and E.END_TIME>=?";
			objectParamWhere.add(processInstanceQuery.getEndTimeBefore());
		}
		
		if (processInstanceQuery.getArchiveTime() != null) {
			sqlString = sqlString + " and  E.ARCHIVE_TIME =?";
			objectParamWhere.add(processInstanceQuery.getArchiveTime());
		}
		if (processInstanceQuery.getArchiveTimeAfter() != null) {
			sqlString = sqlString + " and  E.ARCHIVE_TIME<=?";
			objectParamWhere.add(processInstanceQuery.getArchiveTimeAfter());
		}
		if (processInstanceQuery.getArchiveTimeBefore() != null) {
			sqlString = sqlString + " and E.ARCHIVE_TIME>=?";
			objectParamWhere.add(processInstanceQuery.getArchiveTimeBefore());
		}
		if(processInstanceQuery.getTaskParticipants() !=null ){
			sqlString = sqlString + " and E.PROCESSINSTANCE_ID in (SELECT distinct(F.PROCESSINSTANCE_ID) FROM "+QueryTableUtil.getTableSelect("fixflow_run_taskinstance", processInstanceQuery.getQueryLocation())+" F WHERE F.ASSIGNEE=? and F.END_TIME is not null) ";
			objectParamWhere.add(processInstanceQuery.getTaskParticipants());
		}
		if(processInstanceQuery.getProcessInstanceVariableValue()!=null&&!processInstanceQuery.getProcessInstanceVariableValue().equals("")){
			sqlString = sqlString + " and E.PROCESSINSTANCE_ID in ( SELECT PROCESSINSTANCE_ID FROM "+QueryTableUtil.getTableSelect("fixflow_run_variable", processInstanceQuery.getQueryLocation())+
					" WHERE PROCESSINSTANCE_ID IS NOT NULL AND VARIABLE_TYPE='queryBizVariable' ";
			if(processInstanceQuery.getProcessInstanceVariableKey()!=null&&!processInstanceQuery.getProcessInstanceVariableKey().equals("")){
				sqlString = sqlString +"AND VARIABLE_KEY = ? ";
				objectParamWhere.add(processInstanceQuery.getProcessInstanceVariableKey());
			}
			if(processInstanceQuery.isProcessInstanceVariableValueIsLike()){
				sqlString = sqlString +"AND BIZ_DATA LIKE '%"+processInstanceQuery.getProcessInstanceVariableValue()+"%') ";
			}else{
				sqlString = sqlString +"AND BIZ_DATA=?) ";
				objectParamWhere.add(processInstanceQuery.getProcessInstanceVariableValue());
			}
		}
		if(processInstanceQuery.getStatus() !=null){
			sqlString += " AND INSTANCE_STATUS = ? ";
			objectParamWhere.add(processInstanceQuery.getStatus().toString());
		}
		return sqlString;
	}
	
	/**
	 * 查询子流程流程id，逗号隔开
	 * @param processInstanceId 主流程id
	 * @param processInstanceIdList 构造好的子流程流程号字符串，通过引用返回
	 */
	private void getSubProcessId(String processInstanceId,StringBuffer processInstanceIdList,SqlCommand sqlCommand, ProcessInstanceQueryImpl processInstanceQuery){
		//这个地方需要用到递归去寻找所有的子流程
		List<Object> dataList=new ArrayList<Object>();
		dataList.add(processInstanceId);
		List<Map<String, Object>> dataListMaps=sqlCommand.queryForList("SELECT * FROM "+QueryTableUtil.getTableSelect("fixflow_run_processinstance", processInstanceQuery.getQueryLocation())+" WHERE PARENT_INSTANCE_ID=?", dataList);
		if(dataListMaps.size()>0){
			for (Map<String, Object> map : dataListMaps) {
				processInstanceIdList.append(",'"+StringUtil.getString(map.get("PROCESSINSTANCE_ID"))+"'");
				getSubProcessId(StringUtil.getString(map.get("PROCESSINSTANCE_ID")),processInstanceIdList,sqlCommand,processInstanceQuery);
			}
		}
	}
	

}
