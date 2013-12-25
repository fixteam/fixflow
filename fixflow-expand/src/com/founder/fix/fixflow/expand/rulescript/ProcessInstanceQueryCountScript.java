package com.founder.fix.fixflow.expand.rulescript;

import java.util.ArrayList;
import java.util.List;

import com.founder.fix.fixflow.core.db.pagination.Pagination;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;
import com.founder.fix.fixflow.core.impl.db.ListQueryParameterObject;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceQueryImpl;
import com.founder.fix.fixflow.core.scriptlanguage.SelectRulesScript;

public class ProcessInstanceQueryCountScript implements SelectRulesScript {

	protected ProcessInstanceQueryImpl processInstanceQueryImpl;
	protected Page page;
	protected SqlCommand sqlCommand;
	protected Pagination pagination;
	
	public Object execute(Object parameter, SqlCommand sqlCommand, ProcessEngineConfigurationImpl processEngineConfiguration) {

		ListQueryParameterObject listQueryParameterObject = (ListQueryParameterObject) parameter;
		this.processInstanceQueryImpl = (ProcessInstanceQueryImpl) listQueryParameterObject.getParameter();
		this.page = listQueryParameterObject.getPage();
		this.sqlCommand = sqlCommand;
		this.pagination = Context.getProcessEngineConfiguration().getDbConfig().getPagination();

		return selectProcessInstanceCountByQueryCriteria(processInstanceQueryImpl);
	}
	
	/* 
	 * @param processInstanceQuery
	 * @return
	 */
	public long selectProcessInstanceCountByQueryCriteria(ProcessInstanceQueryImpl processInstanceQuery) {
		String sqlString = " select count(*) ";
		List<Object> objectParamWhere = new ArrayList<Object>();
		
		ProcessInstanceQueryScript processInstanceQueryScript=new ProcessInstanceQueryScript();
		
		sqlString = processInstanceQueryScript.selectProcessInstanceByQueryCriteriaSql(sqlString, processInstanceQuery, objectParamWhere,sqlCommand);
		Object returnObj = sqlCommand.queryForValue(sqlString, objectParamWhere);
		return Integer.parseInt(returnObj.toString());
	}

}
