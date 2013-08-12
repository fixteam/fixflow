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
package com.founder.fix.fixflow.core.impl.task;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.AbstractQuery;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.identity.GroupTo;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.interceptor.CommandExecutor;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskInstanceType;
import com.founder.fix.fixflow.core.task.TaskQuery;


/**
 * @author kenshin
 */
public class TaskQueryImpl extends AbstractQuery<TaskQuery, TaskInstance> implements TaskQuery {
	
	protected String taskId;
	protected String name;
	protected String nameLike;
	protected String description;
	protected String descriptionLike;
	protected Integer priority;
	protected Integer minPriority;
	protected Integer maxPriority;
	protected String assignee;
	protected String involvedUser;
	protected String owner;
	protected boolean unassigned = false;
	protected String candidateUser;
	protected GroupTo candidateGroup;
	protected String end;
	protected String businessKey;
	protected boolean isAgent=false;
	
	protected String nodeId;

	

	protected String processInstanceId;
	protected String executionId;
	protected Date createTime;
	protected Date createTimeBefore;
	protected Date createTimeAfter;
	protected String key;
	protected String keyLike;
	protected String processDefinitionKey;
	protected List<String> processDefinitionKeyList=new ArrayList<String>();
	
	protected String processDefinitionId;
	protected String processDefinitionName;

	protected Date dueDate;
	protected Date dueBefore;
	protected Date dueAfter;
	
	protected String initiator;
	protected String initiatorLike;
	protected boolean assigneeNotNull= false;
	protected boolean candidateNotNull= false;
	
	protected String category;
	
	
	protected String callActivityInstanceId;
	

	protected String isSuspended;
	
	protected String tokenId;

	protected boolean isContainsSubProcess=false;

	

	

	

	

	
	protected List<TaskInstanceType> taskTypeList=new ArrayList<TaskInstanceType>();

	
	public TaskQueryImpl() {
	}

	public TaskQueryImpl(CommandContext commandContext) {
		super(commandContext);
	}

	public TaskQueryImpl(CommandExecutor commandExecutor) {
		super(commandExecutor);
	}

	public TaskQueryImpl taskId(String taskId) {
		if (taskId == null) {
			throw new FixFlowException("Task id is null");
		}
		this.taskId = taskId;
		return this;
	}
	
	
	
	public TaskQueryImpl callActivityInstanceId(String callActivityInstanceId) {
		if (callActivityInstanceId == null) {
			throw new FixFlowException("callActivityInstanceId  is null");
		}
		this.callActivityInstanceId = callActivityInstanceId;
		return this;
	}
	
	public TaskQuery nodeId(String nodeId) {
		this.nodeId = nodeId;
		return this;
	}

	public TaskQueryImpl taskName(String name) {
		this.name = name;
		return this;
	}

	public TaskQueryImpl taskNameLike(String nameLike) {
		if (nameLike == null) {
			throw new FixFlowException("Task namelike is null");
		}
		this.nameLike = nameLike;
		return this;
	}
	
	//isAgent
	
	public TaskQueryImpl isAgent(boolean isAgent){
		this.isAgent=isAgent;
		return this;
	}
	
	protected String agentId;
	
	

	public TaskQuery agentId(String agentId) {
		this.agentId=agentId;
		return this;
	}
	
	public TaskQuery isSuspended(boolean isSuspended) {
		this.isSuspended=String.valueOf(isSuspended);
		return this;
	}
	
	public TaskQuery tokenId(String tokenId) {
		this.tokenId=tokenId;
		return this;
	}


	
	public TaskQueryImpl businessKey(String businessKey) {
		if (businessKey == null) {
			throw new FixFlowException("业务关联键不能为空!");
		}
		this.businessKey = businessKey;
		return this;
	}
	
	public TaskQueryImpl addTaskType(TaskInstanceType taskInstanceType) {
		
		if (taskInstanceType == null) {
			throw new FixFlowException("任务类型不能为空");
		}
		for (TaskInstanceType taskInstanceTypeObj : taskTypeList) {
			if(taskInstanceType.toString().equals(taskInstanceTypeObj.toString())){
				throw new FixFlowException("查询时添加了重复的taskInstanceType!");
			}
		}
		
		
		this.taskTypeList.add(taskInstanceType);
		return this;
	}

	public TaskQueryImpl taskDescription(String description) {
		if (description == null) {
			throw new FixFlowException("Description is null");
		}
		this.description = description;
		return this;
	}

	public TaskQuery taskDescriptionLike(String descriptionLike) {
		if (descriptionLike == null) {
			throw new FixFlowException("Task descriptionlike is null");
		}
		this.descriptionLike = descriptionLike;
		return this;
	}

	public TaskQuery taskPriority(Integer priority) {
		if (priority == null) {
			throw new FixFlowException("Priority is null");
		}
		this.priority = priority;
		return this;
	}

	public TaskQuery taskMinPriority(Integer minPriority) {
		if (minPriority == null) {
			throw new FixFlowException("Min Priority is null");
		}
		this.minPriority = minPriority;
		return this;
	}

	public TaskQuery taskMaxPriority(Integer maxPriority) {
		if (maxPriority == null) {
			throw new FixFlowException("Max Priority is null");
		}
		this.maxPriority = maxPriority;
		return this;
	}

	public TaskQueryImpl taskAssignee(String assignee) {
		if (assignee == null) {
			throw new FixFlowException("Assignee is null");
		}
		this.assignee = assignee;
		return this;
	}
	
	public TaskQuery category(String category) {
		if (category == null) {
			throw new FixFlowException("category is null");
		}
		this.category = category;
		return this;
	}

	public TaskQueryImpl taskOwner(String owner) {
		if (owner == null) {
			throw new FixFlowException("Owner is null");
		}
		this.owner = owner;
		return this;
	}

	public TaskQuery taskUnnassigned() {
		this.unassigned = true;
		return this;
	}

	public TaskQueryImpl taskCandidateUser(String candidateUser) {
		
		if (candidateUser == null) {
			throw new FixFlowException("候选用户不能为空!");
		}
		if (candidateGroup != null) {
			throw new FixFlowException("无效的查询使用：不能同时设置 candidateUser 和 candidateGroup ");
		}
		
		
		this.candidateUser = candidateUser;
		return this;
	}

	public TaskQueryImpl taskInvolvedUser(String involvedUser) {
		if (involvedUser == null) {
			throw new FixFlowException("Involved user is null");
		}
		this.involvedUser = involvedUser;
		return this;
	}

	public TaskQueryImpl taskCandidateGroup(GroupTo candidateGroup) {

		if (candidateGroup == null) {
			throw new FixFlowException("候选组不能为空!");
		}

		if (this.candidateUser != null) {
			throw new FixFlowException("无效的查询使用：不能同时设置 候选用户 和 候选组 ");
		}
		this.candidateGroup = candidateGroup;
		return this;

	}


	public TaskQueryImpl processInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
		return this;
	}
	
	
	public TaskQueryImpl containsSubProcess() {
		this.isContainsSubProcess=true;
		return this;
	}

	public TaskQueryImpl executionId(String executionId) {
		this.executionId = executionId;
		return this;
	}

	public TaskQueryImpl taskCreatedOn(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public TaskQuery taskCreatedBefore(Date before) {
		this.createTimeBefore = before;
		return this;
	}

	public TaskQuery taskCreatedAfter(Date after) {
		this.createTimeAfter = after;
		return this;
	}

	public TaskQuery taskDefinitionKey(String key) {
		this.key = key;
		return this;
	}
	
	

	public TaskQuery taskDefinitionKeyLike(String keyLike) {
		this.keyLike = keyLike;
		return this;
	}

	public TaskQuery processDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
		return this;
	}
	public TaskQuery processDefinitionKey(List<String> processDefinitionKeyList) {
		if(processDefinitionKeyList!=null&&processDefinitionKeyList.size()>0){
			this.processDefinitionKeyList=processDefinitionKeyList;
		}
		else{
			throw new FixFlowException("processDefinitionKeyList 不能为空!");
		}
		return this;
	}

	public TaskQuery processDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
		return this;
	}

	public TaskQuery processDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
		return this;
	}

	public TaskQuery dueDate(Date dueDate) {
		this.dueDate = dueDate;
		return this;
	}

	public TaskQuery dueBefore(Date dueBefore) {
		this.dueBefore = dueBefore;
		return this;
	}

	public TaskQuery dueAfter(Date dueAfter) {
		this.dueAfter = dueAfter;
		return this;
	}
	
	public TaskQuery assigneeNotNull() {
		this.assigneeNotNull = true;
		return this;
	}
	
	public TaskQuery candidateNotNull() {
		this.candidateNotNull = true;
		return this;
	}
	
	public List<GroupTo> getCandidateGroups() {
		if (candidateGroup != null) {
			return Collections.singletonList(candidateGroup);
		} else if (candidateUser != null) {
			return getGroupsForCandidateUser(candidateUser);
		}
		return null;
	}


	protected List<GroupTo> getGroupsForCandidateUser(String candidateUser) {
		

		return Authentication.findGroupsByUser(candidateUser);
	}


	// ordering ////////////////////////////////////////////////////////////////

	public TaskQuery orderByTaskId() {
		return orderBy(TaskQueryProperty.TASK_ID);
	}

	public TaskQuery orderByTaskName() {
		return orderBy(TaskQueryProperty.NAME);
	}

	public TaskQuery orderByTaskDescription() {
		return orderBy(TaskQueryProperty.DESCRIPTION);
	}

	public TaskQuery orderByTaskPriority() {
		return orderBy(TaskQueryProperty.PRIORITY);
	}

	public TaskQuery orderByProcessInstanceId() {
		return orderBy(TaskQueryProperty.PROCESS_INSTANCE_ID);
	}

	public TaskQuery orderByExecutionId() {
		return orderBy(TaskQueryProperty.EXECUTION_ID);
	}

	public TaskQuery orderByTaskAssignee() {
		return orderBy(TaskQueryProperty.ASSIGNEE);
	}

	public TaskQuery orderByTaskCreateTime() {
		return orderBy(TaskQueryProperty.CREATE_TIME);
	}

	public TaskQuery orderByDueDate() {
		return orderBy(TaskQueryProperty.DUE_DATE);
	}

	public TaskQuery orderByEndTime() {
		return orderBy(TaskQueryProperty.END_TIME);
	}

	// results ////////////////////////////////////////////////////////////////

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<TaskInstance> executeList(CommandContext commandContext, Page page) {
		// ensureVariablesInitialized();
		checkQueryOk();
		return (List)commandContext.getTaskManager().findTasksByQueryCriteria(this, page);
	}

	public long executeCount(CommandContext commandContext) {
		// ensureVariablesInitialized();
		checkQueryOk();
		return commandContext.getTaskManager().findTaskCountByQueryCriteria(this);
	}

	// getters ////////////////////////////////////////////////////////////////

	public String getName() {
		return name;
	}

	public String getNameLike() {
		return nameLike;
	}

	public String getAssignee() {
		return assignee;
	}
	
	public String getBusinessKey() {
		return businessKey;
	}
	


	public boolean getUnassigned() {
		return unassigned;
	}

	public String getCandidateUser() {
		return candidateUser;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public String getExecutionId() {
		return executionId;
	}

	public String getTaskId() {
		return taskId;
	}

	public String getDescription() {
		return description;
	}

	public String getDescriptionLike() {
		return descriptionLike;
	}

	public Integer getPriority() {
		return priority;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getCreateTimeBefore() {
		return createTimeBefore;
	}

	public Date getCreateTimeAfter() {
		return createTimeAfter;
	}

	public String getKey() {
		return key;
	}

	public String getKeyLike() {
		return keyLike;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public String getProcessDefinitionName() {
		return processDefinitionName;
	}

	public  GroupTo getCandidateGroup() {
		return candidateGroup;
	}

	public List<TaskInstanceType> getTaskTypeList() {
		return taskTypeList;
	}

	

	public String getEnd() {
		return end;
	}

	public TaskQuery taskIsEnd() {
		this.end = " is not null ";
		return this;
	}

	public TaskQuery taskNotEnd() {
		this.end = " is null ";
		return this;
	}

	public TaskQuery initiator(String initiator) {
		this.initiator=initiator;
		return this;
	}
	
	public String getInitiator() {
		return initiator;
	}

	public TaskQuery initiatorLike(String initiator) {
		this.initiatorLike=initiator;
		return this;
	}
	
	public String getInitiatorLike() {
		return initiatorLike;
	}

	public boolean isAssigneeNotNull() {
		return assigneeNotNull;
	}
	
	public boolean isCandidateNotNull() {
		return candidateNotNull;
	}
	
	public String getCategory() {
		return category;
	}

	public boolean getIsAgent() {
		return isAgent;
	}

	public String getAgentId() {
		return agentId;
	}

	

	public String getCallActivityInstanceId() {
		return callActivityInstanceId;
	}

	public String getTokenId() {
		return tokenId;
	}

	public String getNodeId() {
		return nodeId;
	}
	public String getIsSuspended() {
		return isSuspended;
	}

	public List<String> getProcessDefinitionKeyList() {
		return processDefinitionKeyList;
	}

	
	public QueryExpandTo getQueryExpandTo() {
		return queryExpandTo;
	}


	public boolean isContainsSubProcess() {
		return isContainsSubProcess;
	}
	
	
	
	
	
	/* 变量查询 */
	protected String taskVariableKey;
	protected String taskVariableValue;
	protected boolean taskVariableValueIsLike;
	
	protected String processInstanceVariableKey;
	protected String processInstanceVariableValue;
	protected boolean processInstanceVariableValueIsLike;

	public TaskQuery variableData(String variableValue, boolean isLike) {
		this.taskVariableValue=variableValue;
		this.taskVariableValueIsLike=isLike;
		return this;
	}

	public TaskQuery variableData(String variableKey, String variableValue, boolean isLike) {
		this.taskVariableValue=variableValue;
		this.taskVariableValueIsLike=isLike;
		this.taskVariableKey=variableKey;
		
		return this;
	}

	public TaskQuery processInstanceVariableData(String variableValue, boolean isLike) {
		this.processInstanceVariableValue=variableValue;
		this.processInstanceVariableValueIsLike=isLike;
		return this;
	}

	public TaskQuery processInstanceVariableData(String variableKey, String variableValue, boolean isLike) {
		this.processInstanceVariableValue=variableValue;
		this.processInstanceVariableValueIsLike=isLike;
		this.processInstanceVariableKey=variableKey;
		return this;
	}
	
	public String getTaskVariableKey() {
		return taskVariableKey;
	}

	public String getTaskVariableValue() {
		return taskVariableValue;
	}

	public boolean isTaskVariableValueIsLike() {
		return taskVariableValueIsLike;
	}

	public String getProcessInstanceVariableKey() {
		return processInstanceVariableKey;
	}

	public String getProcessInstanceVariableValue() {
		return processInstanceVariableValue;
	}

	public boolean isProcessInstanceVariableValueIsLike() {
		return processInstanceVariableValueIsLike;
	}

}
