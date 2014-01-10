package com.founder.fix.fixflow.expand.flowconnector.FixRunDataBase;


import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.action.ConnectorHandler;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;

public class FixRunDataBase implements ConnectorHandler {

	private java.lang.String sqlText;

	public void execute(ExecutionContext executionContext) throws Exception {
		

		if(sqlText!=null){
			SqlCommand sqlCommand=new SqlCommand(Context.getDbConnection());
			sqlCommand.execute(sqlText.toString());
		}
		else {
			throw new FixFlowException("执行数据库语句不正确!");
		}
		
	}

	public void  setSqlText(java.lang.String sqlText){
		this.sqlText = sqlText;
	}

}