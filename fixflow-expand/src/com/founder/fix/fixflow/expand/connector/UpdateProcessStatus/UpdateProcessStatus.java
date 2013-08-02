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
package com.founder.fix.fixflow.expand.connector.UpdateProcessStatus;


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