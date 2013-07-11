package com.founder.fix.fixflow.core.impl.identity;

public abstract class AbstractAuthentication {
	
	/**
	 * 获取当前操作人的编号
	 * @return
	 */
	public abstract String getAuthenticatedUserId();
	
	/**
	 * 获取系统管理员的编号
	 * @return
	 */
	public abstract String getAdminId();
	
	/**
	 * 获取系统自动处理的账号
	 * @return
	 */
	public abstract String getSystemId();

}
