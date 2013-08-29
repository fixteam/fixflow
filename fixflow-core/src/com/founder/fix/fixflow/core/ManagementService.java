package com.founder.fix.fixflow.core;

import java.util.Map;

public interface ManagementService extends ProcessService{
	

	/* ****************************************    任务干预接口  begin  ************************************************************ */
	
	
	
	/**
	 * 完成任务
	 * @param taskId 任务编号
	 * @param taskComment 处理意见
	 * @param transientVariables 瞬态变量
	 */
	void complete(String taskId,String taskComment,Map<String, Object> transientVariables);
	

	/**
	 * 接收任务
	 * @param taskId 任务编号
	 */
	void claim(String taskId);
	
	
	/**
	 * 接收任务
	 * @param taskId 任务编号
	 * @param claimUserId 接收(不传的话将会去线程副本中获取)
	 */
	void claim(String taskId, String claimUserId);

	/**
	 * 释放任务 (和领取对应,领取过了之后可以释放)
	 * @param taskId 任务编号
	 */
	void release(String taskId);
	
	

	
	/**
	 * 转发任务
	 * @param taskId 操作的任务号
	 * @param transferUserId 转发给的用户编号
	 * @param taskComment 处理意见
	 * @param transientVariables 瞬态变量
	 */
	void transfer(String taskId,String transferUserId,String taskComment,Map<String, Object> transientVariables);
	


	/**
	 * 退回任务
	 * @param taskId 操作的任务号
	 * @param rollBackNodeId 退回的节点号
	 * @param taskComment 处理意见
	 * @param transientVariables 瞬态变量
	 */
	void rollBack(String taskId,String rollBackNodeId,String taskComment,Map<String, Object> transientVariables);
	
	

	
	
	
	
	/* ****************************************    任务干预接口  end  ************************************************************ */

}
