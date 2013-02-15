package com.founder.fix.fixflow.core.task;



/**
 * 用户命令类型模枚举
 * @author kenshin
 *
 */
public enum TaskCommandType {
	
	
	/**
	 * 草稿
	 */
	draft,
	/**
	 * 通用类型
	 */
	general,
	/**
	 * 退回类型
	 */
	rollBack,
	/**
	 * 转发类型
	 */
	transfer,
	/**
	 * 代理任务类型
	 */
	delegation,
	/**
	 * 扩展类型
	 */
	expand,
	/**
	 * 提交任务
	 */
	submit;


}
