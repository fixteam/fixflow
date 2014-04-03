/**
 *  Copyright 1996-2013 Founder International Co.,Ltd.
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
 * @author shao
 */
package com.founder.fix.fixflow.shell;

import java.sql.Connection;
import java.sql.SQLException;

import com.founder.fix.fixflow.core.ProcessEngine;

/**
 * @ClassName: CommonServiceImpl
 * @Description: TODO
 * @author shao
 *
 */
public abstract class CommonServiceImpl {
	
	protected Connection connection=null;
	
	protected ProcessEngine processEngine=null;
	
	protected ProcessEngine getProcessEngine(Object userId) throws SQLException{
		ProcessEngine result = null;
		if(processEngine==null){
			if(connection!=null){
				processEngine = FixFlowShellProxy.createProcessEngine(userId,connection);
			}else{
				processEngine = FixFlowShellProxy.createProcessEngine(userId);
			}
		}
		
		result = processEngine;
		return result;
	}
	
	protected ProcessEngine getTransactionProcessEngine(Object userId) throws SQLException{
		ProcessEngine result = null;
		if(processEngine==null){
			if(connection!=null){
				processEngine = FixFlowShellProxy.createProcessEngine(userId,connection);
			}else{
				processEngine = FixFlowShellProxy.createProcessEngine(userId,true);
			}
		}
		
		result = processEngine;
		return result;
	}
	
	
	protected void rollbackProcessEngine(){
		if(processEngine!=null){
			FixFlowShellProxy.rollbackProcessEngine(processEngine);
		}
	}
	
	protected void closeProcessEngine(){
		if(processEngine!=null){
			boolean closeConnection = true;
			if(connection!=null){
				closeConnection = false;
			}
			FixFlowShellProxy.closeProcessEngine(processEngine,closeConnection);
			processEngine = null;
			connection = null;
		}
	}
	
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
}
