package com.founder.fix.fixflow.core.task;

import com.founder.fix.fixflow.core.impl.db.PersistentObject;



public interface UserCommandQueryTo extends PersistentObject {



	/**
	 * 处理命令名称
	 * @return
	 */
	String getName();
	
	/**
	 * 处理命令类型
	 * @return
	 */
	String getTaskCommandType();
	
	

}
