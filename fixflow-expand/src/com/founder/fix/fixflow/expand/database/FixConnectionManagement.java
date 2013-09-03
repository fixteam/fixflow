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
package com.founder.fix.fixflow.expand.database;

import java.sql.Connection;

import com.founder.fix.fixflow.core.ConnectionManagement;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.db.FixConnectionResult;

public class FixConnectionManagement extends ConnectionManagement {


	@Override
	public FixConnectionResult getFixConnectionResult() {

		String defaultId=ConnectionManagement.defaultDataBaseId;

		
		return getFixConnectionResult(defaultId);
	}

	@Override
	public FixConnectionResult getFixConnectionResult(String dbId) {
		
		
		FixConnectionResult fixConnectionResult=new FixConnectionResultImpl(dbId);
		
		return fixConnectionResult;

	}

	@Override
	public void setFixConnectionResult(String dbId, FixConnectionResult connection) {
		Context.setFixConnectionResult(dbId, connection);
	}

	@Override
	public void setFixConnection(String dbId, Connection connection) {
		FixConnectionResult fixConnectionResult=new FixConnectionResultImpl(dbId,connection);
		Context.setFixConnectionResult(dbId, fixConnectionResult);
		
	}

}
