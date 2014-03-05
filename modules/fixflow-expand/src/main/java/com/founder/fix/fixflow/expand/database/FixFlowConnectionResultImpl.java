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
import java.sql.SQLException;

import com.founder.fix.fixflow.core.exception.FixFlowDbException;
import com.founder.fix.fixflow.core.impl.db.FixConnectionResult;
//import com.founder.fix.fixflow.shell.FixFlowShellProxy;



public class FixFlowConnectionResultImpl implements FixConnectionResult {


	protected String dbId;

	protected Connection connection;

	public FixFlowConnectionResultImpl(String dbId) {
		this.dbId = dbId;
	}

	public FixFlowConnectionResultImpl(String dbId, Connection connection) {
		this.dbId = dbId;
		this.connection = connection;
	}

	public FixFlowConnectionResultImpl(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		try {
			if (this.connection == null) {
				//this.connection = FixFlowShellProxy.getConnection(dbId);

				return this.connection;
			} else {
				return this.connection;

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new FixFlowDbException(e.getMessage(), e);
		}
	}

	public void colseConnection() {
		try {
			if (!this.connection.isClosed()) {
				if(this.connection.getAutoCommit()==false){
					commitConnection();
				}
				this.connection.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new FixFlowDbException(e.getMessage(), e);
		}
	}

	public void commitConnection() {
		try {
			this.connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FixFlowDbException(e.getMessage(), e);
		}
	}

	public void rollBackConnection() {
		try {
			this.connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FixFlowDbException(e.getMessage(), e);
		}
	}

	public void openConnection() {

	}

	public String getDataBaseId() {
		return this.dbId;
	}
}
