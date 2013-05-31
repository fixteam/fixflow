package com.founder.fix.fixflow.core.database;

public interface FlowConnection {
	
	
	/**
	 * 将任务释放到连接池中
	 */
	void close();
	
	/**
	 * 回滚connection事务
	 */
	void rollback();
	
	/**
	 * 提交connection事务
	 */
	void commit();
	
	
	

}
