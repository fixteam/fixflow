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

import com.founder.fix.fixflow.util.CurrentThread;
import com.founder.fix.fixflow.util.SpringConfigLoadHelper;

public class FixFlowShellFactory {
	private Connection connection;
	
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
	public Connection getConnection(String beanId) throws SQLException{
		if(connection==null){
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
				setDBConn(beanId,dbconn);
			}
		}
		return connection;
	}
	
	public void setConnection(Connection connection){
		this.connection=connection;
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
