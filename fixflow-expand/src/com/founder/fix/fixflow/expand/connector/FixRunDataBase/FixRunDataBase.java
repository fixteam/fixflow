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
package com.founder.fix.fixflow.expand.connector.FixRunDataBase;


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