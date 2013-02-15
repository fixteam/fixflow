package com.founder.fix.fixflow.core.impl;

import java.sql.Connection;
/**
 * 外部内容构造器
 * @author kenshin
 *
 */
public class ExternalContent {
	
	Connection connection;
	
	String authenticatedUserId;
	
	String languageType;

	

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
		return connection;
	}

	/**
	 * 设置数据库连接
	 * @param connection
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
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

}
