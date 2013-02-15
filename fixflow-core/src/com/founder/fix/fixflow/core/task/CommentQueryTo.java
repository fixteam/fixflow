package com.founder.fix.fixflow.core.task;

import java.util.Date;

import com.founder.fix.fixflow.core.impl.db.PersistentObject;

/**
 * 意见查询对象
 * @author kenshin
 *
 */
public interface CommentQueryTo extends PersistentObject {

	/** 提出意见的用户 */
	String getUserId();

	/** 提出意见的时间 */
	Date getTime();

	/** 任务编号 */
	String getTaskId();

	/** 流程实例编号 */
	String getProcessInstanceId();
	
	/**
	 * 任务用户命令
	 * @return
	 */
	String getMessage();

	/**
	 * 获取意见的内容
	 * 
	 * @see TaskService#getTaskComments(String)
	 */
	String getFullMessage();
}
