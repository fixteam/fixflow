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
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.founder.fix.fixflow.core.ConnectionManagement;
import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.impl.ExternalContent;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.util.CurrentThread;
import com.founder.fix.fixflow.util.SpringConfigLoadHelper;

public class FixFlowShellProxy {
	
	/**
	  * getConnection
	
	  * @Title: getConnection
	  * @Description: 获取数据库连接，连接来源是来自于spring配置的数据源
	  * @param @param beanId
	  * @param @return
	  * @param @throws SQLException    设定文件
	  * @return Connection    返回类型
	  * @throws
	  */
	public static Connection getConnection(String beanId) throws SQLException{
		Connection connection = null;

		if(isPoolConn()){
			DBConnection conn = getDBConn(beanId);
			if(conn!=null && conn.getConnection()!=null && conn.getConnection().isClosed()!=true){
				connection = conn.getConnection();
			}else{
				DBConnFactory dbcf = (DBConnFactory)SpringConfigLoadHelper.getBean(beanId);
				DBConnection dbconn = dbcf.createDBConnection();
				connection = dbconn.getConnection();
				setDBConn(beanId,dbconn);
			}
		}else{
			DBConnFactory dbcf = (DBConnFactory)SpringConfigLoadHelper.getBean(beanId);
			DBConnection dbconn = dbcf.createDBConnection();
			connection = dbconn.getConnection();
		}
		
		return connection;
	}
	
	public static ProcessEngine createProcessEngine(Object operator) throws SQLException{
		return createProcessEngine(operator,false);
	}
	
	public static ProcessEngine createProcessEngine(Object operator,boolean needTransaction) throws SQLException{
		Connection connection = getConnection(ConnectionManagement.defaultDataBaseId);
		if(needTransaction){
			connection.setAutoCommit(false);
		}
		return createProcessEngine(operator,connection);
	}
	
	public static ProcessEngine createProcessEngine(Object operator,Connection connection){
		Map<String,Connection> tmp = new HashMap<String,Connection>();
		tmp.put("null", connection);
		return createProcessEngine(operator,tmp);
	}
	
	public static ProcessEngine createProcessEngine(Object operator,Map<String,Connection> connections){
		ExternalContent externalContent=new ExternalContent();
		externalContent.setAuthenticatedUserId(StringUtil.getString(operator));
		for(Entry<String,Connection> tmp:connections.entrySet()){
			if(tmp.getKey().equals("null"))
				externalContent.setConnection(tmp.getValue());
			else
				externalContent.setConnection(tmp.getKey(), tmp.getValue());
		}
		
		return createProcessEngine(externalContent);
	}
	
	public static ProcessEngine createProcessEngine(ExternalContent externalContext){
		ProcessEngine processEngine = ProcessEngineManagement.getDefaultProcessEngine();
		processEngine.setExternalContent(externalContext);
		
		return processEngine;
	}
	
	public static void closeProcessEngine(ProcessEngine engine,boolean closeConnection){
		engine.contextClose(true, closeConnection);
	}
	
	private static DBConnection getDBConn(String key){
		return (CurrentThread.getThreadDBPool().get()).get(key);
	}
	
	private static void setDBConn(String key,DBConnection dbconn){
		(CurrentThread.getThreadDBPool().get()).put(key,dbconn);
	}
	
	public static boolean isPoolConn(){
		return CurrentThread.getThreadDBPool().get()!=null;
	}
}
