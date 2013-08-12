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

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.founder.fix.bpmn2extensions.coreconfig.Priority;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.command.GeneralTaskCommand;
import com.founder.fix.fixflow.core.impl.identity.UserTo;
import com.founder.fix.fixflow.core.runtime.IdentityLinkQuery;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.task.CommentQueryTo;
import com.founder.fix.fixflow.core.task.IdentityLink;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.founder.fix.fixflow.core.task.UserCommandQueryTo;

public interface TaskService extends ProcessService {

	/**
	 * 创建一个新的任务
	 * 
	 * @return
	 */
	TaskInstance newTask();

	/**
	 * 按照指定的编号创建一个新的任务
	 * 
	 * @param taskId
	 *            任务编号
	 * @return
	 */
	TaskInstance newTask(String taskId);

	/**
	 * 存储一个任务实例
	 * 
	 * @param task
	 *            任务实例
	 */
	void saveTask(TaskInstance task);

	/**
	 * 删除一个任务(非级联)
	 * 
	 * @param taskId
	 *            任务号
	 */
	void deleteTask(String taskId);

	/**
	 * 删除一组任务(非级联)
	 * 
	 * @param taskIds
	 *            任务号集合
	 */
	void deleteTasks(Collection<String> taskIds);

	/**
	 * 删除一个任务
	 * 
	 * @param taskId
	 *            任务号
	 * @param cascade
	 *            是否级联
	 */
	void deleteTask(String taskId, boolean cascade);

	/**
	 * 删除一组任务
	 * 
	 * @param taskIds
	 *            任务实例号集合
	 * @param cascade
	 *            是否级联
	 */
	void deleteTasks(Collection<String> taskIds, boolean cascade);

	// 任务常用处理

	/**
	 * 接收任务
	 * 
	 * @param taskId
	 *            任务编号
	 * @param claimUserId
	 *            接收(不传的话将会去线程副本中获取)
	 */
	void claim(String taskId, String claimUserId);

	/**
	 * 释放任务 (和领取对应,领取过了之后可以释放)
	 * 
	 * @param taskId
	 *            任务编号
	 */
	void release(String taskId);

	/**
	 * 自定义扩展方式完成任务的处理命令调用的方法
	 * 
	 * @param expandTaskCommand
	 * @param classReturn
	 * @return
	 */
	<T> T expandTaskComplete(ExpandTaskCommand expandTaskCommand, T classReturn);

	/**
	 * 返回一个新 {@link TaskQuery}，可用于动态查询的任务。
	 */
	TaskQuery createTaskQuery();

	IdentityLinkQuery createIdentityLinkQuery();

	/**
	 * 获取用户自定义命令 {@link TaskCommand} 列表
	 * 
	 * @param processDefinitionId
	 *            流程定义id，唯一编号,不能为空。(数据库中的 id)
	 * @param nodeId
	 *            任务节点编号
	 * @return {@link TaskCommand} 列表
	 */
	List<TaskCommandInst> getTaskCommandById(String processDefinitionId, String nodeId);

	

	/**
	 * 获取系统运维管理中,能够对指定类型任务操作的所有任务命令
	 * 
	 * @param commandType
	 *            命令类型 toDoTasks sharedTasks processInstanceInfo all
	 * @return {@link TaskCommandInst} 列表
	 */
	List<TaskCommandInst> getSystemTaskCommand(String commandType);

	/**
	 * 设置任务代理人
	 * 
	 * @param taskId
	 *            任务编号
	 * @param userId
	 *            用户编号
	 */
	void setAssignee(String taskId, String userId);

	/**
	 * 设置任务拥有者
	 * 
	 * @param taskId
	 *            任务编号
	 * @param userId
	 *            用户编号
	 */
	void setOwner(String taskId, String userId);

	/** 获取任务相关的意见(处理过程) */
	List<CommentQueryTo> getTaskComments(String taskId);

	/** 获取流程实例相关的意见(处理过程) */
	List<CommentQueryTo> getProcessInstanceComments(String processInstanceId);

	/** 添加一个评论(处理过程)到 任务和流程实例上 */
	void addComment(String taskId, String processInstanceId, String message, String fullMessage);

	/**
	 * 获取可退回的任务
	 * 
	 * @param taskId
	 *            任务编号
	 * @return 可退回的任务
	 */
	List<TaskInstance> getRollBackTask(String taskId);

	/**
	 * 获取可退回的任务(只包含前面的节点的任务,且剔除掉重复数据)
	 * 
	 * @param taskId
	 *            任务编号
	 * @return 可退回的任务
	 */
	List<TaskInstance> getRollBackScreeningTask(String taskId);

	/**
	 * 获取可退回的节点
	 * 
	 * @param taskId
	 *            任务编号
	 * @return 可退回的节点对象
	 */
	List<UserTaskBehavior> getRollBackNode(String taskId);

	/**
	 * 模拟提交任务
	 * 
	 * @param generalTaskCommand
	 * @return 流程实例对象
	 */
	ProcessInstance commandCompleteSimulationRun(GeneralTaskCommand generalTaskCommand);

	/**
	 * 根据流程实例号获取当前流程任务状态
	 * 
	 * @param processInstanceIdList
	 * @return
	 */
	List<Map<String, Object>> getTaskStatusByByProcessInstanceId(List<String> processInstanceIdList);

	/**
	 * 获取任务的上一步骤任务对象(非会签情况下上一步骤只有一个任务,会签情况下可能会有多个任务)
	 * 
	 * @param taskId
	 *            任务编号
	 * @return 上一步骤任务对象
	 */
	List<TaskInstance> getPreviousStepTaskByTaskId(String taskId);

	/**
	 * 获取任务的可以退回的所有任务对象
	 * 
	 * @param taskId
	 *            任务编号
	 * @return 所有任务对象
	 */
	List<TaskInstance> getRollBackTaskByTaskId(String taskId);

	/**
	 * 设置任务的持久变量
	 * 
	 * @param taskId
	 *            任务编号
	 * @param variableName
	 *            变量key
	 * @param value
	 *            变量值
	 */
	void setVariable(String taskId, String variableName, Object value);

	/**
	 * 设置任务的持久变量
	 * 
	 * @param taskId
	 *            任务编号
	 * @param variables
	 *            变量Map
	 */
	void setVariables(String taskId, Map<String, ? extends Object> variables);

	/**
	 * 获取任务的持久变量
	 * 
	 * @param taskId
	 *            任务编号
	 * @param variableName
	 *            变量key
	 * @return 变量值
	 */
	Object getVariable(String taskId, String variableName);

	/**
	 * 获取任务的所有持久化变量
	 * 
	 * @param taskId
	 *            任务编号
	 * @return 变量Map
	 */
	Map<String, Object> getVariables(String taskId);

	/**
	 * 获取任务的指定的持久化变量
	 * 
	 * @param taskId
	 *            任务编号
	 * @param variableNames
	 *            变量key集合
	 * @return 变量Map
	 */
	Map<String, Object> getVariables(String taskId, List<String> variableNames);

	/**
	 * 创建一个新的任务候选身份
	 * 
	 * @return
	 */
	IdentityLink newIdentityLink();

	/**
	 * 创建一个新的任务候选身份
	 * 
	 * @param linkId
	 *            身份编号
	 * @return
	 */
	IdentityLink newIdentityLink(String linkId);

	/**
	 * 存储 任务候选身份 对象
	 * 
	 * @param identityLink
	 */
	void saveIdentityLink(IdentityLink identityLink);

	/**
	 * 删除 任务候选身份 对象
	 * 
	 * @param linkId
	 *            任务候选身份编号
	 */
	void deleteIdentityLink(String linkId);

	/**
	 * 删除 任务候选身份 对象
	 * 
	 * @param linkIds
	 *            任务候选身份编号集合
	 */
	void deleteIdentityLink(Collection<String> linkIds);

	List<Map<String, Object>> processPerformance(String[] pid, String startTime, String endTime, String type);

	/**
	 * 获取将任务代理给当前用户的人员列表
	 * 
	 * @param userId
	 *            用户编号
	 * @return
	 */
	List<UserTo> getAgentUsers(String userId);

	/**
	 * 获取将任务代理给当前用户的人员列表
	 * 
	 * @param userId
	 *            用户编号
	 * @return map的key{userid,username}
	 */
	List<Map<String, Object>> getAgentUsersAndCount(String userId);

	/**
	 * 获取当前用户设置的代理用户
	 * 
	 * @param userId
	 * @return
	 */
	List<UserTo> getAgentToUsers(String userId);

	/**
	 * 获取当前用户设置的代理用户
	 * 
	 * @param userId
	 * @return map的key{userid,username}
	 */
	List<Map<String, Object>> getAgentToUsersAndCount(String userId);

	List<UserTaskBehavior> getUserEndTaskNodesInProcessInstance(String processInstanceId);

	
	/**
	 * 获取提交节点用户自定义命令 {@link TaskCommand} 列表
	 * 
	 * @param processDefinitionKey
	 *            流程定义编号
	 * @return {@link TaskCommand} 列表
	 */
	List<TaskCommandInst> getSubTaskTaskCommandByKey(String processDefinitionKey);
	
	
	/**
	 * 获取任务的处理命令
	 * 
	 * @param taskId
	 *            任务编号
	 * @param isProcessTracking
	 *            是否为流程追踪查询
	 * @return
	 */
	List<TaskCommandInst> GetTaskCommandByTaskId(String taskId, boolean isProcessTracking);

	/**
	 * 获取任务的处理命令
	 * @param taskInstance 任务实例
	 * @param isProcessTracking 是否为流程追踪查询
	 * @return
	 */
	List<TaskCommandInst> GetTaskCommandByTaskInstance(TaskInstance taskInstance, boolean isProcessTracking);

	List<UserTo> getTaskUsersByTaskId(String taskId);

	/**
	 * 根据优先级的值获取所处的优先级定义
	 * 
	 * @param priorityValue
	 *            优先级值
	 * @return
	 */
	Priority getPriority(int priorityValue);

	/**
	 * 获取模拟运行下一步产生的任务
	 * 
	 * @param taskId
	 *            任务编号
	 * @param processInstanceId
	 *            流程实例号
	 * @return
	 */
	List<TaskInstance> getNextTask(String taskId, String processInstanceId);

	/**
	 * 获取可以追回到的任务
	 * 
	 * @param taskId
	 *            当前任务号
	 * @param taskCommandId
	 *            点击处理命令编号
	 * @return 可以追回的任务
	 */
	List<TaskInstance> GetRecoverTask(String taskId, String taskCommandId);
	
	
	
	/** * * * * * * * * * * * * * * *   已经过期的方法 * * * * * * * * * * * * * * * * * * *  */

	@Deprecated
	/**
	 * 获取用户自定义命令 {@link UserCommandQuery} 列表
	 * 
	 * @param processDefinitionId
	 *            流程定义id，唯一编号,不能为空。(数据库中的 id)
	 * @param nodeId
	 *            任务节点编号
	 * @return {@link UserCommandQuery} 列表
	 */
	List<UserCommandQueryTo> getUserCommandById(String processDefinitionId, String nodeId);

	@Deprecated
	/**
	 * 获取提交节点用户自定义命令 {@link UserCommandQuery} 列表
	 * 
	 * @param processDefinitionKey
	 *            流程定义编号
	 * @return {@link UserCommandQuery} 列表
	 */
	List<UserCommandQueryTo> getSubTaskUserCommandByKey(String processDefinitionKey);

}
