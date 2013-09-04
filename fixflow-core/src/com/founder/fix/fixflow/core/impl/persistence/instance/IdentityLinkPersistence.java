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
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.db.pagination.Pagination;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.runtime.IdentityLinkQueryImpl;
import com.founder.fix.fixflow.core.impl.task.IdentityLinkEntity;
import com.founder.fix.fixflow.core.objkey.TaskIdentityLinkObjKey;
import com.founder.fix.fixflow.core.objkey.TaskInstanceObjKey;


public class IdentityLinkPersistence {
	
	
	protected Connection connection;
	protected SqlCommand sqlCommand;

	public IdentityLinkPersistence(Connection connection) {
		this.connection = connection;
		sqlCommand = new SqlCommand(connection);
	}
	
	
	
	private String selectIdentityLinkByQueryCriteriaSql(String sqlString,IdentityLinkQueryImpl identityLinkQuery, Page page,List<Object> objectParamWhere){
		
		sqlString = sqlString+" FROM "+TaskIdentityLinkObjKey.getTableName(identityLinkQuery.getQueryLocation())+"  ";

		sqlString = sqlString + " WHERE 1=1";

		if (identityLinkQuery.getId() != null) {
			sqlString = sqlString + " and ID=? ";
			objectParamWhere.add(identityLinkQuery.getId());
		}
		
		if (identityLinkQuery.getType() != null) {
			sqlString = sqlString + " and TYPE=? ";
			objectParamWhere.add(identityLinkQuery.getType().toString());
		}
		if (identityLinkQuery.getUserId() != null) {
			sqlString = sqlString + " and USER_ID=? ";
			objectParamWhere.add(identityLinkQuery.getUserId());
		}
		if (identityLinkQuery.getGroupId() != null) {
			sqlString = sqlString + " and GROUP_ID=? ";
			objectParamWhere.add(identityLinkQuery.getGroupId());
		}
		
		if (identityLinkQuery.getGroupType() != null) {
			sqlString = sqlString + " and GROUP_TYPE=? ";
			objectParamWhere.add(identityLinkQuery.getGroupType());
		}
		
		if (identityLinkQuery.getIncludeExclusion()!= null) {
			sqlString = sqlString + " and INCLUDE_EXCLUSION=? ";
			objectParamWhere.add(identityLinkQuery.getIncludeExclusion().toString());
		}
		
		if (identityLinkQuery.getTaskId() != null) {
			sqlString = sqlString + " and TASKINSTANCE_ID=? ";
			objectParamWhere.add(identityLinkQuery.getTaskId());
		}
		return sqlString;
	}
	
	
	
	
	public List<IdentityLinkEntity> selectIdentityLinksByTask(String taskId)
	{
		String sqlString="SELECT * FROM "+TaskIdentityLinkObjKey.TaskIdentityLinkTableName()+" WHERE TASKINSTANCE_ID=? ORDER BY USER_ID,GROUP_ID";
		List<Object> objectParamWhere = new ArrayList<Object>();
		objectParamWhere.add(taskId);
		List<Map<String, Object>> dataObj = sqlCommand.queryForList(sqlString, objectParamWhere);

		List<IdentityLinkEntity> identityLinkPersistenceToList = new ArrayList<IdentityLinkEntity>();

		for (Map<String, Object> dataMap : dataObj) {
			
			IdentityLinkEntity identityLinkPersistenceTo=new IdentityLinkEntity(dataMap);

			identityLinkPersistenceToList.add(identityLinkPersistenceTo);
		}
		
		return identityLinkPersistenceToList;
	}
	
	public List<IdentityLinkEntity> selectIdentityLinkByQueryCriteria(IdentityLinkQueryImpl identityLinkQuery, Page page) {

		String sqlString="select "+Context.getProcessEngineConfiguration().getDbConfig().getDbSqlMap().get("topOrderBy")+" * ";
		List<Object> objectParamWhere = new ArrayList<Object>();
		sqlString=selectIdentityLinkByQueryCriteriaSql(sqlString,identityLinkQuery,page,objectParamWhere);
		
		
		if (identityLinkQuery.getOrderBy() != null) {

			sqlString = sqlString + " order by "+identityLinkQuery.getOrderBy().toString();
		}
		
		
		String orderByString="";
		
		if (identityLinkQuery.getOrderBy() != null && page != null) {
			String orderBySql=identityLinkQuery.getOrderBy();
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
				orderByString = orderByString + " order by " + identityLinkQuery.getOrderBy().toString().substring(2);
			}
		}
		
		if(page!=null)
		{
			Pagination pagination=Context.getProcessEngineConfiguration().getDbConfig().getPagination();
			sqlString=pagination.getPaginationSql(sqlString, page.getFirstResult(), page.getMaxResults(), "*",orderByString);
		}
	
		
		List<Map<String, Object>> dataObj = sqlCommand.queryForList(sqlString, objectParamWhere);

		List<IdentityLinkEntity> identityLinkPersistenceToList = new ArrayList<IdentityLinkEntity>();

		for (Map<String, Object> dataMap : dataObj) {
			
			IdentityLinkEntity identityLinkPersistenceTo=new IdentityLinkEntity(dataMap);

			identityLinkPersistenceToList.add(identityLinkPersistenceTo);
		}
		
		return identityLinkPersistenceToList;
		//select distinct T.* from
	}

	public long selectIdentityLinkCountByQueryCriteria(IdentityLinkQueryImpl identityLinkQuery) {
		// select count(distinct T.ID_)
		String sqlString="select count(*) ";
		List<Object> objectParamWhere = new ArrayList<Object>();
		sqlString=selectIdentityLinkByQueryCriteriaSql(sqlString,identityLinkQuery,null,objectParamWhere);
		Object returnObj=sqlCommand.queryForValue(sqlString, objectParamWhere);
		return Integer.parseInt(returnObj.toString());
	}

	
	
	
	
	
	
	
	public void deleteIdentityLinkByProcessInstanceId(String processInstanceId){
		
		Object[] objectParamWhere = { processInstanceId };
		
		sqlCommand.delete(TaskIdentityLinkObjKey.TaskIdentityLinkTableName(), " TASKINSTANCE_ID IN (SELECT TASKINSTANCE_ID FROM "+TaskInstanceObjKey.TaskInstanceTableName()+" WHERE PROCESSINSTANCE_ID=?)",objectParamWhere);
		
	}
	
	
	public void insertIdentityLink(IdentityLinkEntity identityLink) {

		
		Map<String, Object> objectParam=identityLink.getPersistentDbMap();
		// 执行插入语句
		sqlCommand.insert(TaskIdentityLinkObjKey.TaskIdentityLinkTableName(), objectParam);

	}

	public void updateIdentityLink(IdentityLinkEntity identityLink) {

		Map<String, Object> objectParam=identityLink.getPersistentDbMap();

		// 构建Where查询参数
		Object[] objectParamWhere = { identityLink.getId() };

		// 执行插入语句
		sqlCommand.update(TaskIdentityLinkObjKey.TaskIdentityLinkTableName(), objectParam, " ID=?", objectParamWhere);

	}
	
	public void saveIdentityLink(IdentityLinkEntity identityLink){
		List<Object> objectParamWhere = new ArrayList<Object>();
		objectParamWhere.add(identityLink.getId());

		// 设置查询字符串
		String sqlText = "SELECT COUNT(1) FROM "+TaskIdentityLinkObjKey.TaskIdentityLinkTableName()+" WHERE ID=?";
		// 执行查询Sql语句,判断身份链接是否存在于数据库中.
		int rowNum = Integer.parseInt(sqlCommand.queryForValue(sqlText, objectParamWhere).toString());

		if (rowNum == 0) {
			
			insertIdentityLink(identityLink);
		} else {
			updateIdentityLink(identityLink);
		}
	}

	

}
