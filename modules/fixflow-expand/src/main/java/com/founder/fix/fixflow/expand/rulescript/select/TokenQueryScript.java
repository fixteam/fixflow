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
import com.founder.fix.fixflow.core.impl.runtime.TokenQueryImpl;
import com.founder.fix.fixflow.core.impl.util.QueryTableUtil;
import com.founder.fix.fixflow.core.scriptlanguage.SelectRulesScript;

public class TokenQueryScript implements SelectRulesScript {

	Pagination pagination = Context.getProcessEngineConfiguration().getDbConfig().getPagination();

	public Object execute(Object parameter, SqlCommand sqlCommand, ProcessEngineConfigurationImpl processEngineConfiguration) {
		ListQueryParameterObject listQueryParameterObject = (ListQueryParameterObject) parameter;
		TokenQueryImpl tokenQuery = (TokenQueryImpl) listQueryParameterObject.getParameter();
		Page page = listQueryParameterObject.getPage();
		

		return selectTokenByQueryCriteria(tokenQuery,page,sqlCommand);
	}
	
	
	/**
	 * 查询令牌实体
	 * @param tokenQuery 令牌查询对象
	 * @param page 页数信息
	 * @return
	 */
	public List<Map<String, Object>> selectTokenByQueryCriteria(TokenQueryImpl tokenQuery, Page page,SqlCommand sqlCommand) {
		String sqlString="select "+Context.getProcessEngineConfiguration().getDbConfig().getDbSqlMap().get("topOrderBy")+" tokentable.* ";
		List<Object> objectParamWhere = new ArrayList<Object>();
		sqlString=selectTokenByQueryCriteriaSql(sqlString,tokenQuery,page,objectParamWhere);
		//if (tokenQuery.getOrderBy() != null) {
		//	sqlString = sqlString + " order by "+tokenQuery.getOrderBy().toString();
		//}
		
		
		
		String orderByString="";
		
		if (tokenQuery.getOrderBy() != null ) {
			String orderBySql=tokenQuery.getOrderBy();
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
				orderByString = orderByString + " order by " + tokenQuery.getOrderBy().toString().substring(2);
			}
		}
		
		if(page!=null){
			Pagination pagination=Context.getProcessEngineConfiguration().getDbConfig().getPagination();
			sqlString=pagination.getPaginationSql(sqlString, page.getFirstResult(), page.getMaxResults(), "*",orderByString);
		}else{
			if (tokenQuery.getOrderBy() != null) {
				sqlString = sqlString + orderByString;
				}
		}
		List<Map<String, Object>> dataObj = sqlCommand.queryForList(sqlString, objectParamWhere);
		
		return dataObj;
	}
	
	
	/**
	 * 根据查询对象生成查询语句
	 * @param sqlString sql语句
	 * @param tokenQuery 查询对象
	 * @param page 页数信息
	 * @param objectParamWhere 生成where参数
	 * @return
	 */
	public String selectTokenByQueryCriteriaSql(String sqlString,TokenQueryImpl tokenQuery, Page page,List<Object> objectParamWhere){
		sqlString = sqlString+" FROM "+QueryTableUtil.getTableSelect("fixflow_run_token", tokenQuery.getQueryLocation())+" tokentable ";
		sqlString = sqlString + " WHERE 1=1";
		if (tokenQuery.getTokenId() != null) {
			sqlString = sqlString + " and TOKEN_ID=? ";
			objectParamWhere.add(tokenQuery.getTokenId());
		}
		if (tokenQuery.getProcessInstanceId() != null) {
			sqlString = sqlString + " and PROCESSINSTANCE_ID=? ";
			objectParamWhere.add(tokenQuery.getProcessInstanceId());
		}
		if (tokenQuery.getEnd() != null) {
			sqlString = sqlString + " and END_TIME "+tokenQuery.getEnd() ;
		}
		return sqlString;
	}
	
	

}
