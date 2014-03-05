package com.founder.fix.fixflow.expand.flowconnector.RunSqlSelectOneObject;


import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.action.ConnectorHandler;

public class RunSqlSelectOneObject implements ConnectorHandler {

	private java.lang.String sqlText;
	private java.util.List<java.util.Map<String, Object>> outputfield;

	public void execute(ExecutionContext executionContext) throws Exception {
		//Object sqltextTemp=ExpressionMgmt.execute(sqlText, executionContext);
		List<Map<String, Object>> mapList= executionContext.getSqlCommand().queryForList(sqlText);
	
		outputfield=mapList;
	}

	public void  setSqlText(java.lang.String sqlText){
		this.sqlText = sqlText;
	}

	public java.util.List<java.util.Map<String, Object>>  getOutputfield(){
		return outputfield ;
	}
}