package com.founder.fix.fixflow.core.task;

public enum TaskInstanceType {
	
	/**
	 * fixflow流程引擎产生的任务
	 */
	FIXFLOWTASK,
	
	/**
	 * fixflow产生的通知型任务
	 */
	FIXNOTICETASK,
	
	/**
	 *fixflow自己产生的业务任务
	 */
	FIXBPMTASK,
	
	/**
	 * 调用节点状态记录
	 */
	FIXCALLACTIVITYTASK,
	
	/**
	 * 启动任务
	 */
	FIXSTARTEVENT,
	
	
	/**
	 * 结束任务
	 */
	FIXENDEVENT,
	
	/**
	 * 等待任务
	 */
	FIXRECEIVETASK,
	
	
	/**
	 * 其他流程引擎产生的任务
	 */
	OTHERFLOWTASK,
	
	
	/**
	 * 其他系统产生的通知型任务
	 */
	OTHERNOTICETASK,
	
	
	
	/**
	 * 其他系统产生的业务任务
	 */
	OTHERBPMTASK
	
	
}
