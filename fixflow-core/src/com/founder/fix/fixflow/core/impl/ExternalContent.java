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
package com.founder.fix.fixflow.core.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.founder.fix.fixflow.core.ConnectionManagement;
/**
 * 外部内容构造器
 * @author kenshin
 *
 */
public class ExternalContent {

	
	
	protected String authenticatedUserId;
	
	protected String languageType;

	
	protected boolean isQuartzTransactionAuto=true;
	

	
	/**
	 * 获取当前登陆用户
	 * @return
	 */
	public String getAuthenticatedUserId() {
		return authenticatedUserId;
	}

	/**
	 * 设置当前登陆用户
	 * @param authenticatedUserId 用户编号
	 */
	public void setAuthenticatedUserId(String authenticatedUserId) {
		this.authenticatedUserId = authenticatedUserId;
	}
	
	

	/**
	 * 获取数据库连接
	 * @return
	 */
	public Connection getConnection() {
		return getConnection(ConnectionManagement.defaultDataBaseId);
	}
	

	/**
	 * 获取数据库连接
	 * @return
	 */
	public Connection getConnection(String dbId) {
		return connectionMap.get(dbId);
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 */
	public Map<String, Connection> getConnectionMap() {
		return connectionMap;
	}

	/**
	 * 设置数据库连接
	 * @param connection
	 */
	public void setConnection(Connection connection) {
		setConnection(ConnectionManagement.defaultDataBaseId,connection);
	}
	

	Map<String, Connection> connectionMap=new HashMap<String, Connection>();
	
	
	/**
	 * 设置数据库链接
	 * @param connectionKey
	 * @param connection
	 */
	public void setConnection(String connectionKey,Connection connection) {
		this.connectionMap.put(connectionKey, connection);
	}
	
	public void cleanConnection(){
		connectionMap.clear();
	}
	
	/**
	 * 获取语言类型
	 * @return
	 */
	public String getLanguageType() {
		return languageType;
	}

	/**
	 * 设置语言类型
	 * @param languageType 语言类型
	 */
	public void setLanguageType(String languageType) {
		this.languageType = languageType;
	}

	/**
	 * 获取定时任务框架的事务控制类型
	 * @return
	 */
	public boolean isQuartzTransactionAuto() {
		return isQuartzTransactionAuto;
	}

	/**
	 * 设置定时任务框架的事务控制类型
	 * @param isQuartzTransactionAuto true则框架自己控制事务 false则交由流程引擎控制
	 */
	public void setQuartzTransactionAuto(boolean isQuartzTransactionAuto) {
		this.isQuartzTransactionAuto = isQuartzTransactionAuto;
	}
	
	protected String cmId;
	/**
	 * 设置数据库连接管理器
	 * @param cmId 管理器编号
	 */
	public void setConnectionManagement(String cmId) {
		this.cmId = cmId;
	}
	

	/**
	 * 获取数据库连接管理器
	 * @return 管理器编号
	 */
	public String getConnectionManagement() {
		return this.cmId;
	}

}
