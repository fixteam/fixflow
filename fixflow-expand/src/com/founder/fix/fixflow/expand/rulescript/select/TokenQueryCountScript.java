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
