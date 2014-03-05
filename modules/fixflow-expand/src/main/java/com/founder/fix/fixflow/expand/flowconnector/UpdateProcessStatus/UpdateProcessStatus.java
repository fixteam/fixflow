package com.founder.fix.fixflow.expand.flowconnector.UpdateProcessStatus;


import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.action.ConnectorHandler;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class UpdateProcessStatus implements ConnectorHandler {

	private java.lang.String statusValue;

	public void execute(ExecutionContext executionContext) throws Exception {
		SqlCommand sqlCommand=new SqlCommand(Context.getDbConnection());
		String Fix_BizName=StringUtil.getString(Context.getAbstractScriptLanguageMgmt().execute("${Fix_BizName}", executionContext));
		String Fix_BizKeyFile=StringUtil.getString(Context.getAbstractScriptLanguageMgmt().execute("${Fix_BizKeyFile}", executionContext));
		if(Fix_BizName==null||Fix_BizName.equals("")){
			throw new FixFlowException("数据变量${Fix_BizName}为空");
		}
		if(Fix_BizKeyFile==null||Fix_BizKeyFile.equals("")){
			throw new FixFlowException("数据变量${Fix_BizKeyFile}为空");
		}
		String sqlText="UPDATE "+Fix_BizName+" SET  FIX_PROCESSSTATE='"+statusValue+"' WHERE "+Fix_BizKeyFile+"='"+executionContext.getBizKey()+"'";
		sqlCommand.execute(sqlText);
	}

	public void  setStatusValue(java.lang.String statusValue){
		this.statusValue = statusValue;
	}

}