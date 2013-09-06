/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 */
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
	
	

	
	/**
	 * 恢复任务
	 */
	void resumeTask(String taskId);
	
	/**
	 * 暂停任务
	 */
	void suspendTask(String taskId);
	
	
	/* ****************************************    任务干预接口  end  ************************************************************ */

}
