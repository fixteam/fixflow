package com.founder.fix.fixflow.expand.rulescript.select;

import java.util.ArrayList;
import java.util.List;

import com.founder.fix.fixflow.core.db.pagination.Pagination;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.runtime.TokenQueryImpl;
import com.founder.fix.fixflow.core.scriptlanguage.SelectRulesScript;

public class TokenQueryCountScript implements SelectRulesScript {

	protected TokenQueryImpl tokenQuery;
	protected Page page;
	protected SqlCommand sqlCommand;
	protected Pagination pagination;
	
	public Object execute(Object parameter, SqlCommand sqlCommand, ProcessEngineConfigurationImpl processEngineConfiguration) {


		this.tokenQuery = (TokenQueryImpl) parameter;

		this.sqlCommand = sqlCommand;
		this.pagination = Context.getProcessEngineConfiguration().getDbConfig().getPagination();

		return selectTokenCountByQueryCriteria(tokenQuery);
	}
	
	/**
	 * 根据查询对象查询结果个数
	 * @param tokenQuery
	 * @return
	 */
	public long selectTokenCountByQueryCriteria(TokenQueryImpl tokenQuery) {
		String sqlString="select count(*) ";
		List<Object> objectParamWhere = new ArrayList<Object>();
		
		TokenQueryScript tokenQueryScript=new TokenQueryScript();
		
		
		sqlString=tokenQueryScript.selectTokenByQueryCriteriaSql(sqlString,tokenQuery,null,objectParamWhere);
		Object returnObj=sqlCommand.queryForValue(sqlString, objectParamWhere);
		return Integer.parseInt(returnObj.toString());
	}

}
