package com.founder.fix.fixflow.core.impl.db;

import java.io.Serializable;
import java.util.Map;

/**
 * @author kenshin
 */
public interface PersistentObject extends Serializable{
	
	/**
	 * 获取对象编号
	 * @return
	 */
	String getId();
	
	

	/**
	 * 获取对象数据
	 * @return
	 */
	Map<String, Object> getPersistentState();
	
}
