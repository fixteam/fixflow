package com.founder.fix.fixflow.core.objkey;

public interface ObjKeyInterface {
	/**
	 * 查询中拼接开始时间
	 */
	public static String BEGIN_TIME_KEY="begin_";
	
	/**
	 * 查询中拼接结束时间
	 */
	public static String AFTER_TIME_KEY="after_";
	
	/**
	 * 获取用于流程内部流转的实例key
	 * @return 实例key
	 */
	String EntityKey();
	

	String FullKey();
	
	/**
	 * 获取用户数据库持久化存储的字段Key
	 * @return 数据库字段Key
	 */
	String DataBaseKey();
	
	
	/**
	 * key 的显示名称
	 * @return 显示名称
	 */
	String KeyName();


}
