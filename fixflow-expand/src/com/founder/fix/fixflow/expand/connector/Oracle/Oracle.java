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
package com.founder.fix.fixflow.expand.connector.Oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.action.ConnectorHandler;

public class Oracle implements ConnectorHandler {

	private java.lang.String driver;

	private java.lang.String url;

	private java.lang.String user;

	private java.lang.String password;

	private java.lang.String query;

	public void execute(ExecutionContext executionContext) throws Exception {

		

	}

	public void setDriver(java.lang.String driver) {
		this.driver = driver;
	}

	public void setUrl(java.lang.String url) {
		this.url = url;
	}

	public void setUser(java.lang.String user) {
		this.user = user;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	public void setQuery(java.lang.String query) {
		this.query = query;
	}

	public java.lang.String getRowSet() {
		return null;
	}

}