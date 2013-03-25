package com.founder.fix.fixflow.core.impl.task;

/**
 * 任务处理命令系统类型
 * @author kenshin
 *
 */
public class TaskCommandType {

	/**
	 * 自动结束
	 */
	public static String AUTOEND="autoEnd";
	/**
	 * 自动跳过
	 */
	public static String SKIPNODE="skipNode";
	/**
	 * 子流程结束
	 */
	public static String SUBPROCESSEND="subProcessEnd";
	/**
	 * 流程开始
	 */
	public static String STARTEVENT="startEvent";
	/**
	 * 流程结束
	 */
	public static String ENDEVENT="endEvent";
	
	/**
	 * 等待结束
	 */
	public static String RECEIVEEND="receiveEnd";

}
