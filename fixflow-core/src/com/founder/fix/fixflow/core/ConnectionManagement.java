package com.founder.fix.fixflow.core;


import java.sql.Connection;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.db.FixConnectionResult;

/**
 * FixFlow数据库管理中心
 * @author kenshin
 *
 */
public abstract class ConnectionManagement {
	
	
	private static String FIXDATABASEID="DB_FIX_BIZ_BASE";
	
	public static String defaultDataBaseId=FIXDATABASEID;
	
	
	public static ConnectionManagement INSTANCE(){
		
		ProcessEngine processEngine =ProcessEngineManagement.getDefaultProcessEngine();
		String cmId=Context.getConnectionManagementDefault();
		if(cmId==null){
			return processEngine.getProcessEngineConfiguration().getConnectionManagementDefault();
		}else{
			return processEngine.getProcessEngineConfiguration().getConnectionManagement(cmId);
		}
		
		
	}
	
	/**
	 * 获取默认的数据库连接
	 * @return
	 */
	public abstract FixConnectionResult getFixConnectionResult();
	
	/**
	 * 获取指定ID的数据库链接
	 * @param dbId 数据库配置ID
	 * @return
	 */
	public abstract FixConnectionResult getFixConnectionResult(String dbId);
	
	/**
	 * 设置数据库连接,默认会放置到流程的线程副本中
	 * @param dbId 数据库配置ID
	 * @param connection 数据库连接
	 */
	public abstract void setFixConnectionResult(String dbId,FixConnectionResult connection);
	
	/**
	 * 设置数据库连接,默认会放置到流程的线程副本中
	 * @param dbId 数据库配置ID
	 * @param connection 数据库连接
	 */
	public abstract void setFixConnection(String dbId,Connection connection);
	

}
