package com.founder.fix.fixflow.expand.rulescript;

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
import com.founder.fix.fixflow.core.objkey.TokenObjKey;
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
		if (tokenQuery.getOrderBy() != null) {
			sqlString = sqlString + " order by "+tokenQuery.getOrderBy().toString();
		}
		
		
		
		String orderByString="";
		
		if (tokenQuery.getOrderBy() != null && page != null) {
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
		sqlString = sqlString+" FROM "+TokenObjKey.getTableName(tokenQuery.getQueryLocation())+" tokentable ";
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
