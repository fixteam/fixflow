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
//
//import java.sql.Connection;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import com.founder.fix.fixflow.core.db.pagination.Pagination;
//import com.founder.fix.fixflow.core.impl.Context;
//import com.founder.fix.fixflow.core.impl.Page;
//import com.founder.fix.fixflow.core.impl.db.SqlCommand;
//import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
//import com.founder.fix.fixflow.core.impl.runtime.TokenQueryImpl;
//import com.founder.fix.fixflow.core.objkey.TokenObjKey;

/**
 * 流程令牌信息持久化
 * @author kenshin
 *
 */
public class TokenPersistence {
	
	
//	protected Connection connection;
//	protected SqlCommand sqlCommand;
//
//	public TokenPersistence(Connection connection) {
//		this.connection = connection;
//		sqlCommand = new SqlCommand(connection);
//	}
//	
//	/**
//	 * 根据查询对象生成查询语句
//	 * @param sqlString sql语句
//	 * @param tokenQuery 查询对象
//	 * @param page 页数信息
//	 * @param objectParamWhere 生成where参数
//	 * @return
//	 */
//	private String selectTokenByQueryCriteriaSql(String sqlString,TokenQueryImpl tokenQuery, Page page,List<Object> objectParamWhere){
//		sqlString = sqlString+" FROM "+TokenObjKey.getTableName(tokenQuery.getQueryLocation())+" tokentable ";
//		sqlString = sqlString + " WHERE 1=1";
//		if (tokenQuery.getTokenId() != null) {
//			sqlString = sqlString + " and TOKEN_ID=? ";
//			objectParamWhere.add(tokenQuery.getTokenId());
//		}
//		if (tokenQuery.getProcessInstanceId() != null) {
//			sqlString = sqlString + " and PROCESSINSTANCE_ID=? ";
//			objectParamWhere.add(tokenQuery.getProcessInstanceId());
//		}
//		if (tokenQuery.getEnd() != null) {
//			sqlString = sqlString + " and END_TIME "+tokenQuery.getEnd() ;
//		}
//		return sqlString;
//	}
//	
//	/**
//	 * 查询令牌实体
//	 * @param tokenQuery 令牌查询对象
//	 * @param page 页数信息
//	 * @return
//	 */
//	public List<TokenEntity> selectTokenByQueryCriteria(TokenQueryImpl tokenQuery, Page page) {
//		String sqlString="select "+Context.getProcessEngineConfiguration().getDbConfig().getDbSqlMap().get("topOrderBy")+" tokentable.* ";
//		List<Object> objectParamWhere = new ArrayList<Object>();
//		sqlString=selectTokenByQueryCriteriaSql(sqlString,tokenQuery,page,objectParamWhere);
//		if (tokenQuery.getOrderBy() != null) {
//			sqlString = sqlString + " order by "+tokenQuery.getOrderBy().toString();
//		}
//		
//		
//		
//		String orderByString="";
//		
//		if (tokenQuery.getOrderBy() != null && page != null) {
//			String orderBySql=tokenQuery.getOrderBy();
//			String orderBySqlFin="";
//			if(orderBySql.indexOf(",")>=0){
//				String[] orderBySqlTemp=orderBySql.split(",");
//				for (String orderByObj : orderBySqlTemp) {
//					if(orderBySqlFin.equals("")){
//						orderBySqlFin=orderBySqlFin+orderByObj.substring(orderByObj.indexOf(".")+1,orderByObj.length());
//					}
//					else{
//						orderBySqlFin=orderBySqlFin+","+orderByObj.substring(orderByObj.indexOf(".")+1,orderByObj.length());
//					}
//				}
//				orderByString = orderByString + " order by " + orderBySqlFin;
//				
//			}else{
//				orderByString = orderByString + " order by " + tokenQuery.getOrderBy().toString().substring(2);
//			}
//		}
//		
//		if(page!=null){
//			Pagination pagination=Context.getProcessEngineConfiguration().getDbConfig().getPagination();
//			sqlString=pagination.getPaginationSql(sqlString, page.getFirstResult(), page.getMaxResults(), "*",orderByString);
//		}
//		List<Map<String, Object>> dataObj = sqlCommand.queryForList(sqlString, objectParamWhere);
//		List<TokenEntity> tokenPersistenceToList = new ArrayList<TokenEntity>();
//		for (Map<String, Object> dataMap : dataObj) {
//			TokenEntity tokenPersistenceTo=new TokenEntity(dataMap);
//			tokenPersistenceToList.add(tokenPersistenceTo);
//		}
//		return tokenPersistenceToList;
//	}
//
//	/**
//	 * 根据查询对象查询结果个数
//	 * @param tokenQuery
//	 * @return
//	 */
//	public long selectTokenCountByQueryCriteria(TokenQueryImpl tokenQuery) {
//		String sqlString="select count(*) ";
//		List<Object> objectParamWhere = new ArrayList<Object>();
//		sqlString=selectTokenByQueryCriteriaSql(sqlString,tokenQuery,null,objectParamWhere);
//		Object returnObj=sqlCommand.queryForValue(sqlString, objectParamWhere);
//		return Integer.parseInt(returnObj.toString());
//	}
//	
//	/**
//	 * 根据流程实例ID删除令牌信息
//	 * @param processInstanceId 流程实例ID
//	 */
//	public void deleteTokenByProcessInstanceId(String processInstanceId){
//		
//		Object[] objectParamWhere = { processInstanceId };
//		sqlCommand.delete(TokenObjKey.TokenTableName(), " PROCESSINSTANCE_ID=?",objectParamWhere);
//		sqlCommand.delete(TokenObjKey.TokenHisTableName(), " PROCESSINSTANCE_ID=?",objectParamWhere);
//	}
}
