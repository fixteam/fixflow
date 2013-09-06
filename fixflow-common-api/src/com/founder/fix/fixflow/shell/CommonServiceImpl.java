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
import java.util.Map;

import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.query.Query;

/**
 * @ClassName: CommonServiceImpl
 * @Description: TODO
 * @author shao
 *
 */
public abstract class CommonServiceImpl {
	
	protected Connection connection;
	
	protected ProcessEngine getProcessEngine(Object userId) throws SQLException{
		if(connection!=null){
			return FixFlowShellProxy.createProcessEngine(userId,connection);
		}else{
			return FixFlowShellProxy.createProcessEngine(userId);
		}
	}
	
	protected ProcessEngine getTransactionProcessEngine(Object userId) throws SQLException{
		if(connection!=null){
			return FixFlowShellProxy.createProcessEngine(userId,connection);
		}else{
			return FixFlowShellProxy.createProcessEngine(userId,true);
		}
	}
	
	
}
