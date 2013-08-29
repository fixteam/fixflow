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
package com.founder.fix.fixflow.expand.connector.CreateSubpProcessData;


import java.util.HashMap;

import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.action.ConnectorHandler;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;

public class CreateSubpProcessData implements ConnectorHandler {

	private java.lang.String table;

	private java.util.HashMap param;

	public void execute(ExecutionContext executionContext) throws Exception {

		
		SqlCommand sqlCommand=executionContext.getSqlCommand();

		// 构建查询参数
		HashMap<String, Object> objectParam = (HashMap<String, Object>)param;

				
				// 执行插入语句
				sqlCommand.insert(table, objectParam);
		
		
	}

	public void  setTable(java.lang.String table){
		this.table = table;
	}

	public void  setParam(java.util.HashMap param){
		this.param = param;
	}

}