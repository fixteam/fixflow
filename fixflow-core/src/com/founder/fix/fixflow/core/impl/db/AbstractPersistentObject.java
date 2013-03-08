package com.founder.fix.fixflow.core.impl.db;

import java.util.Map;


public abstract class AbstractPersistentObject implements PersistentObject {

	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1542067996535178080L;

	
	/**
	 * 从数据库初始化对象
	 * @param entityMap 字段Map
	 * @return
	 */
	public abstract void persistentInit(Map<String, Object> entityMap);
	
	/**
	 * 获取能持久化到数据的Map
	 * @return 对应到数据库字段的Map
	 */
	public abstract Map<String, Object> getPersistentDbMap();
	


}
