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
package com.founder.fix.fixflow.core.task;

import java.util.Date;
import java.util.List;

import com.founder.fix.fixflow.core.impl.identity.GroupTo;
import com.founder.fix.fixflow.core.query.Query;



/**
 * 任务查询器
 * 
 * @author kenshin
 */
public interface TaskQuery extends Query<TaskQuery, TaskInstance> {

	/**
	 * 任务是否结束
	 * @return
	 */
	TaskQuery taskIsEnd();
	
	/**
	 * 根据流程分类查询
	 * @param category
	 * @return
	 */
	TaskQuery category(String category);
	
	/**
	 * 是否代理
	 * @param isAgent
	 * @return
	 */
	TaskQuery isAgent(boolean isAgent);
	
	/**
	 * 代理id
	 * @param agentId
	 * @return
	 */
	TaskQuery agentId(String agentId);

	/**
	 * 未结束的任务
	 * @return
	 */
	TaskQuery taskNotEnd();
	
	/**
	 * 是否暂停
	 * @param isSuspended
	 * @return
	 */
	TaskQuery isSuspended(boolean isSuspended);
	
	/**
	 * 根据令牌ID查询
	 * @param tokenId
	 * @return
	 */
	TaskQuery tokenId(String tokenId);
	
	/**
	 * 
	 * @param callActivityInstanceId
	 * @return
	 */
	TaskQuery callActivityInstanceId(String callActivityInstanceId);
	
	/**
	 * 根据任务所有变量查询
	 * @param variableValue 变量值
	 * @param isLike 是否Like
	 * @return
	 */
	TaskQuery variableData(String variableValue,boolean isLike);
	
	/**
	 * 根据任务指定的变量查询
	 * @param variableKey  变量Key
	 * @param variableValue 变量值
	 * @param isLike 是否Like
	 * @return
	 */
	TaskQuery variableData(String variableKey,String variableValue,boolean isLike);
	
	/**
	 * 根据任务的流程实例的所有变量查询
	 * @param variableValue 变量值
	 * @param isLike 是否Like
	 * @return
	 */
	TaskQuery processInstanceVariableData(String variableValue,boolean isLike);
	
	/**
	 * 根据任务的流程实例指定的变量查询
	 * @param variableKey 变量Key
	 * @param variableValue 变量值
	 * @param isLike 是否Like
	 * @return
	 */
	TaskQuery processInstanceVariableData(String variableKey,String variableValue,boolean isLike);

	/**
	 * 根据任务发起人查询
	 * @return
	 */
	TaskQuery initiator(String initiator);
	
	/**
	 * 根据任务发起人查询
	 * @param initiator
	 * @return
	 */
	TaskQuery initiatorLike(String initiator);

	/**
	 * 根据taskId查询
	 * @param taskId
	 * @return
	 */
	TaskQuery taskId(String taskId);

	/**
	 * 根据任务名称查询
	 * @param name
	 * @return
	 */
	TaskQuery taskName(String name);
	
	/**
	 * 根据业务主键查询
	 * @param businessKey
	 * @return
	 */
	TaskQuery businessKey(String businessKey);
	
	/**
	 * 根据业务主键查询
	 * @param businessKey
	 * @return
	 */
	TaskQuery businessKeyLike(String businessKey);
	
	/**
	 * 任务类型
	 * @param taskInstanceType
	 * @return
	 */
	TaskQuery addTaskType(TaskInstanceType taskInstanceType);

	/**
	 * 任务名称like匹配
	 * @param nameLike
	 * @return
	 */
	TaskQuery taskNameLike(String nameLike);

	/**
	 * 任务描述
	 * @param description
	 * @return
	 */
	TaskQuery taskDescription(String description);

	/**
	 * 任务描述like匹配
	 * @param descriptionLike
	 * @return
	 */
	TaskQuery taskDescriptionLike(String descriptionLike);

	/**
	 * 
	 * @param priority
	 * @return
	 */
	TaskQuery taskPriority(Integer priority);

	/**
	 * 
	 * @param minPriority
	 * @return
	 */
	TaskQuery taskMinPriority(Integer minPriority);

	/**
	 * 
	 * @param maxPriority
	 * @return
	 */
	TaskQuery taskMaxPriority(Integer maxPriority);

	/**
	 * 制定用户的独占任务
	 * @param assignee
	 * @return
	 */
	TaskQuery taskAssignee(String assignee);

	/**
	 * 指定用户的任务
	 * @param owner
	 * @return
	 */
	TaskQuery taskOwner(String owner);

	/**
	 * 未被领取的任务
	 * @return
	 */
	TaskQuery taskUnnassigned();

	/**
	 * 指定用户的的共享任务
	 * @param candidateUser
	 * @return
	 */
	TaskQuery taskCandidateUser(String candidateUser);

	/**
	 * 
	 * @param involvedUser
	 * @return
	 */
	TaskQuery taskInvolvedUser(String involvedUser);

	/**
	 * 根据共享用户组查询
	 * @param candidateGroup
	 * @return
	 */
	TaskQuery taskCandidateGroup(GroupTo candidateGroup);

	/**
	 * 根据流程实例编号查询
	 * @param processInstanceId
	 * @return
	 */
	TaskQuery processInstanceId(String processInstanceId);
	
	/**
	 * 含有子流程的任务
	 * @return
	 */
	TaskQuery containsSubProcess();

	/**
	 * 
	 * @param executionId
	 * @return
	 */
	TaskQuery executionId(String executionId);

	/**
	 * 创建时间等于createTime
	 * @param createTime
	 * @return
	 */
	TaskQuery taskCreatedOn(Date createTime);

	/**
	 * 创建时间小于before
	 * @param before
	 * @return
	 */
	TaskQuery taskCreatedBefore(Date before);

	/**
	 * 创建时间大于after
	 * @param after
	 * @return
	 */
	TaskQuery taskCreatedAfter(Date after);

	/**
	 * 
	 * @param key
	 * @return
	 */
	TaskQuery taskDefinitionKey(String key);

	/**
	 * 根据任务定义key like匹配
	 * @param keyLike
	 * @return
	 */
	TaskQuery taskDefinitionKeyLike(String keyLike);

	/**
	 * 根据流程定义key查询
	 * @param processDefinitionKey
	 * @return
	 */
	TaskQuery processDefinitionKey(String processDefinitionKey);
	
	/**
	 * 根据流程定义key查询
	 * @param processDefinitionKeyList
	 * @return
	 */
	TaskQuery processDefinitionKey(List<String> processDefinitionKeyList);
	
	/**
	 * 根据流程定义编号查询
	 * @param processDefinitionId
	 * @return
	 */
	TaskQuery processDefinitionId(String processDefinitionId);

	/**
	 * 根据流程名称查询
	 * @param processDefinitionName
	 * @return
	 */
	TaskQuery processDefinitionName(String processDefinitionName);

	/**
	 * 
	 * @param dueDate
	 * @return
	 */
	TaskQuery dueDate(Date dueDate);

	/**
	 * 
	 * @param dueDate
	 * @return
	 */
	TaskQuery dueBefore(Date dueDate);

	/**
	 * 
	 * @param dueDate
	 * @return
	 */
	TaskQuery dueAfter(Date dueDate);
	
	/**
	 * 独占不为空
	 * @return
	 */
	TaskQuery assigneeNotNull();
	
	/**
	 * 共享分组不为空
	 * @return
	 */
	TaskQuery candidateNotNull();
	
	/**
	 * 根据节点查询
	 * @param nodeId
	 * @return
	 */
	TaskQuery nodeId(String nodeId);
	
	/**
	 * 查询归档数据
	 * @return
	 */
	TaskQuery his();
	
	/**
	 * 查询运行数据
	 * @return
	 */
	TaskQuery run();
	// ordering ////////////////////////////////////////////////////////////

	/**
	 * 根据任务ID排序
	 * @return
	 */
	TaskQuery orderByTaskId();

	/**
	 * 根据任务名称排序
	 * @return
	 */
	TaskQuery orderByTaskName();

	/**
	 * 根据任务描述排序
	 * @return
	 */
	TaskQuery orderByTaskDescription();

	/**
	 * 
	 * @return
	 */
	TaskQuery orderByTaskPriority();

	/**
	 * 根据处理人排序
	 * @return
	 */
	TaskQuery orderByTaskAssignee();

	/**
	 * 根据创建时间排序
	 * @return
	 */
	TaskQuery orderByTaskCreateTime();

	/**
	 * 根据流程实例ID排序
	 * @return
	 */
	TaskQuery orderByProcessInstanceId();

	/**
	 * 
	 * @return
	 */
	TaskQuery orderByExecutionId();

	/**
	 * 
	 * @return
	 */
	TaskQuery orderByDueDate();

	/**
	 * 根据结束时间排序
	 * @return
	 */
	TaskQuery orderByEndTime();

}
