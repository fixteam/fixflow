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
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.UserTask;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.cache.CacheHandler;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.db.AbstractPersistentObject;
import com.founder.fix.fixflow.core.impl.identity.GroupTo;
import com.founder.fix.fixflow.core.impl.interceptor.CommandExecutor;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.util.ClockUtil;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.internationalization.FixFlowResources;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.task.Assignable;
import com.founder.fix.fixflow.core.task.DelegationState;
import com.founder.fix.fixflow.core.task.IdentityLink;
import com.founder.fix.fixflow.core.task.IdentityLinkType;
import com.founder.fix.fixflow.core.task.IncludeExclusion;
import com.founder.fix.fixflow.core.task.TaskInstanceType;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.founder.fix.fixflow.core.task.TaskDefinition;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskMgmtInstance;

public class TaskInstanceEntity extends AbstractPersistentObject<TaskInstanceEntity> implements TaskInstance, Assignable, Cloneable {

	private static final long serialVersionUID = 2262140765605817383L;

	// 静态字段 //////////////////////////////////////////////////////////////

	public static final String RULE_GET_TASKINSTANCE_PERSISTENT_STATE = "getTaskInstancePersistentState";

	public static final String RULE_GET_TASKINSTANCE_PERSISTENT_DBMAP = "getTaskInstancePersistentDbMap";

	public static final String RULE_TASK_INSTANCE_CLONE = "taskInstanceClone";

	// 需要持久化的字段 //////////////////////////////////////////////////////////

	protected String id;

	protected String name;

	protected String description;

	protected String processInstanceId;

	protected String processDefinitionId;

	protected String processDefinitionKey;

	protected String processDefinitionName;

	protected int version;

	protected String tokenId;

	protected String nodeId;

	protected String nodeName;

	protected String parentTaskInstanceId;

	protected String assignee;

	protected Date claimTime;

	protected Date createTime;

	protected Date startTime;

	protected Date endTime;

	protected Date dueDate;

	protected boolean isBlocking = false;

	protected int priority = TaskInstance.PRIORITY_NORMAL;

	protected String category;

	protected String owner;

	protected DelegationState delegationState;

	protected String bizKey;

	protected String taskComment;

	protected String formUri;

	protected String formUriView;

	protected String taskGroup;

	protected TaskInstanceType taskInstanceType = TaskInstanceType.FIXFLOWTASK;

	protected boolean isCancelled = false;

	protected boolean isSuspended = false;

	protected boolean isOpen = true;

	protected boolean isDraft = false;

	protected int expectedExecutionTime = 0;

	protected String agent;

	protected String admin;

	protected String callActivityInstanceId;

	protected String pendingTaskId;

	protected Date archiveTime;

	protected String commandId;

	protected String commandType;

	protected String commandMessage;

	// get set //////////////////////////////////////////////////////////

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProcessInstanceId() {
		return this.processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessDefinitionId() {
		return this.processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public String getProcessDefinitionKey() {
		return this.processDefinitionKey;
	}

	public String getProcessDefinitionName() {
		return processDefinitionName;
	}

	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getTokenId() {
		return this.tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getParentTaskInstanceId() {
		return this.parentTaskInstanceId;
	}

	public void setParentTaskInstanceId(String parentTaskInstanceId) {
		this.parentTaskInstanceId = parentTaskInstanceId;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public Date getClaimTime() {
		return this.claimTime;
	}

	public void setClaimTime(Date claimTime) {
		this.claimTime = claimTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	// 有问题的
	public DelegationState getDelegationState() {
		return delegationState;
	}

	public void setDelegationState(DelegationState delegationState) {
		this.delegationState = delegationState;
	}

	public void setDelegationStateString(String delegationState) {
		if (StringUtil.isNotEmpty(delegationState)) {
			this.delegationState = DelegationState.valueOf(delegationState);
		}
	}

	public String getBizKey() {
		return this.bizKey;
	}

	public void setBizKey(String bizKey) {
		this.bizKey = bizKey;
	}

	public String getTaskComment() {
		return taskComment;
	}

	public void setTaskComment(String taskComment) {
		this.taskComment = taskComment;
	}

	public void setFormUri(String formUri) {
		this.formUri = formUri;
	}

	public String getFormUri() {
		return this.formUri;
	}

	public String getFormUriView() {
		return formUriView;
	}

	public void setFormUriView(String formUriView) {
		this.formUriView = formUriView;
	}

	public String getTaskGroup() {
		return taskGroup;
	}

	public void setTaskGroup(String taskGroup) {
		this.taskGroup = taskGroup;
	}

	public TaskInstanceType getTaskInstanceType() {
		return taskInstanceType;
	}

	public void setTaskInstanceType(TaskInstanceType taskInstanceType) {
		this.taskInstanceType = taskInstanceType;
	}

	public void setTaskInstanceTypeString(String taskInstanceType) {
		if (StringUtil.isNotEmpty(taskInstanceType)) {
			this.taskInstanceType = TaskInstanceType.valueOf(taskInstanceType);
		}
	}

	// 有问题的
	public boolean isBlocking() {
		return isBlocking;
	}

	public void setBlockingString(String isBlocking) {
		if (StringUtil.isNotEmpty(isBlocking)) {
			this.isBlocking = StringUtil.getBoolean(isBlocking);
		}

	}

	public void setBlocking(boolean isBlocking) {
		this.isBlocking = isBlocking;
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	// 有问题的
	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	// 有问题的
	public void setCancelledString(String isCancelled) {
		if (StringUtil.isNotEmpty(isCancelled)) {
			this.isCancelled = StringUtil.getBoolean(isCancelled);
		}
	}

	public boolean isSuspended() {
		return isSuspended;
	}

	// 有问题的
	public void setSuspended(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}

	public void setSuspendedString(String isSuspended) {
		if (StringUtil.isNotEmpty(isSuspended)) {
			this.isSuspended = StringUtil.getBoolean(isSuspended);
		}
	}

	public boolean isOpen() {
		return isOpen;
	}

	// 有问题的
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	// 有问题的
	public void setOpenString(String isOpen) {
		if (StringUtil.isNotEmpty(isOpen)) {
			this.isOpen = StringUtil.getBoolean(isOpen);
		}
	}

	public boolean isDraft() {
		return isDraft;
	}

	public void setDraft(boolean isDraft) {
		this.isDraft = isDraft;
	}

	public void setDraftString(String isDraft) {
		if (StringUtil.isNotEmpty(isDraft)) {
			this.isDraft = StringUtil.getBoolean(isDraft);
		}
	}

	public int getExpectedExecutionTime() {
		return expectedExecutionTime;
	}

	public void setExpectedExecutionTime(int expectedExecutionTime) {
		this.expectedExecutionTime = expectedExecutionTime;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getCallActivityInstanceId() {
		return callActivityInstanceId;
	}

	public void setCallActivityInstanceId(String callActivityInstanceId) {
		this.callActivityInstanceId = callActivityInstanceId;
	}

	public String getPendingTaskId() {
		return pendingTaskId;
	}

	public void setPendingTaskId(String pendingTaskId) {
		this.pendingTaskId = pendingTaskId;
	}

	public Date getArchiveTime() {
		return archiveTime;
	}

	public void setArchiveTime(Date archiveTime) {
		this.archiveTime = archiveTime;
	}

	public String getCommandId() {
		return commandId;
	}

	public void setCommandId(String commandId) {
		this.commandId = commandId;
	}

	public String getCommandType() {
		return commandType;
	}

	public void setCommandType(String commandType) {
		this.commandType = commandType;
	}

	public String getCommandMessage() {

		if (this.getCommandType() == null) {

			return commandMessage;

		}

		// 国际化处理
		ProcessEngineConfigurationImpl processEngineConfiguration = ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration();
		Boolean booleanTemp = StringUtil.getBoolean(processEngineConfiguration.getInternationalizationConfig().getIsEnable());

		if (booleanTemp) {
			String processId = this.getProcessDefinitionId();
			String cType = processEngineConfiguration.getTaskCommandDefMap().get(this.getCommandType()).getType();
			String nameTemp = null;
			if (cType.equals("system")) {
				nameTemp = processEngineConfiguration.getFixFlowResources().getResourceName(FixFlowResources.TaskComandResource,
						"System_" + commandId);

			} else {
				nameTemp = processEngineConfiguration.getFixFlowResources().getResourceName(processId, this.nodeId + "_" + commandId);

			}

			if (nameTemp == null) {
				return commandMessage;
			}
			return nameTemp;

		} else {
			return commandMessage;
		}

	}

	public String getDefaultCommandMessage() {
		return commandMessage;
	}

	public void setCommandMessage(String commandMessage) {
		this.commandMessage = commandMessage;
	}

	// 对象化元素 //////////////////////////////////////////////////////////

	protected TokenEntity token;

	protected UserTask node;

	

	protected TaskDefinition taskDefinition;

	protected List<IdentityLinkEntity> taskIdentityLinks;

	protected TaskInstance parentTaskInstance;

	protected TaskMgmtInstance taskMgmtInstance;

	/**
	 * 创建任务
	 */
	public TaskInstanceEntity() {

	}

	/**
	 * 创建任务
	 * 
	 * @param taskInstanceId
	 *            任务编号
	 */
	public TaskInstanceEntity(String taskInstanceId) {
		this.id = taskInstanceId;
	}

	public static TaskInstanceEntity create() {
		TaskInstanceEntity task = new TaskInstanceEntity();
		task.createTime = ClockUtil.getCurrentTime();
		return task;
	}

	public TaskInstance getParentTaskInstance() {
		
		if(this.parentTaskInstance==null){
			
			if(StringUtil.isNotEmpty(this.parentTaskInstanceId)){
				this.parentTaskInstance=Context.getCommandContext().getTaskManager().findTaskById(this.parentTaskInstanceId);
				return this.parentTaskInstance;
			}
			
		}
		
		return parentTaskInstance;
	}

	public TaskDefinition getTaskDefinition() {
		return taskDefinition;
	}

	public TokenEntity getToken() {
		return token;
	}

	public void setParentTaskInstance(TaskInstance parentTaskInstance) {
		this.parentTaskInstance = parentTaskInstance;
		if(parentTaskInstance!=null){
			this.parentTaskInstanceId = parentTaskInstance.getId();
		}
		
		

	}

	public void addCandidateGroup(GroupTo groupTo, IncludeExclusion includeExclusion) {
		addIdentityLink(null, groupTo.getGroupId(), groupTo.getGroupType(), includeExclusion, IdentityLinkType.candidate);
	}

	public void addCandidateUser(String userId, IncludeExclusion includeExclusion) {
		addIdentityLink(userId, null, null, includeExclusion, IdentityLinkType.candidate);
	}

	public void setTaskMgmtInstance(TaskMgmtInstance taskMgmtInstance) {
		this.taskMgmtInstance = taskMgmtInstance;
	}

	public void setTaskDefinition(TaskDefinition taskDefinition) {
		this.taskDefinition = taskDefinition;
	}

	public void setToken(TokenEntity token) {

		this.processInstanceId = token.getProcessInstance().getId();
		this.processDefinitionId = token.getProcessInstance().getProcessDefinition().getProcessDefinitionId();
		this.node = (UserTask) token.getFlowNode();
		this.nodeId = this.node.getId();
		this.nodeName = this.node.getName();
		this.tokenId = token.getId();
		this.token = token;
		this.bizKey = token.getProcessInstance().getBizKey();
		this.processDefinitionKey = token.getProcessInstance().getProcessDefinition().getProcessDefinitionKey();
		this.name = this.node.getName();
		this.processDefinitionName = token.getProcessInstance().getProcessDefinition().getName();

	}

	public void create(ExecutionContext executionContext) {
		if (createTime != null) {
			throw new FixFlowException("任务实例 '" + id + "' 已经被创建!", new Error());
		}
		createTime = new Date();

		if ((taskDefinition != null) && (executionContext != null)) {
			//
			executionContext.setTaskInstance(this);
			executionContext.setTaskDefinition(taskDefinition);
			// task.fireEvent(Event.EVENTTYPE_TASK_CREATE, executionContext);
		}
	}

	public void start() {
		if (startTime != null) {
			throw new FixFlowException("任务实例 '" + id + "' 已经被标示为开始！");
		}

		startTime = new Date();

	}

	public void end(TaskCommandInst taskCommandInst, String taskComment) {

		// 设置任务上点击的处理命令
		this.setCommandId(taskCommandInst.getId());
		// 设置任务上点击的处理命令类型
		this.setCommandType(taskCommandInst.getTaskCommandType());
		// 设置任务上点击的处理命令文本
		this.setCommandMessage(taskCommandInst.getName());
		// 处理意见
		this.setTaskComment(taskComment);

		// 调用任务的完成方法
		end();
	}

	public void end() {

		// 设置是否为草稿
		this.isDraft = false;

		// this.operationCommand = operationCommand;
		if (this.endTime != null) {
			throw new FixFlowException("任务已经结束,不能再进行处理.");
		}
		if (this.isSuspended) {
			throw new FixFlowException("任务已经暂停不能再处理");
		}

		this.endTime = new Date();

		this.isOpen = false;

		// fire the task instance end event
		if ((taskDefinition != null) && (getToken() != null)) {

			ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(getToken());
			executionContext.setTaskDefinition(taskDefinition);
			executionContext.setTaskInstance(this);
			// task.fireEvent(Event.EVENTTYPE_TASK_END, executionContext);
		}

		//
		if (getToken() != null) {
			// token.addLog(new TaskEndLog(this));
		}

		getToken().signal();

	}

	public void toFlowNodeEnd(TaskCommandInst taskCommandInst, String taskComment, FlowNode flowNode, String rollBackAssignee) {

		// 分支退回处理

		if (getToken().getParent() == null) {
			// 主令牌非分支的处理

			customEnd(taskCommandInst, taskComment);
			ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(getToken());
			executionContext.setToFlowNode(flowNode);
			executionContext.setRollBackAssignee(rollBackAssignee);
			getToken().signal(executionContext);

		} else {
			// 非主令牌分支令牌的处理
			CommandExecutor commandExecutor = Context.getProcessEngineConfiguration().getCommandExecutor();
			TaskQuery taskQuery = new TaskQueryImpl(commandExecutor);
			Long taskNum = taskQuery.tokenId(getToken().getId()).nodeId(flowNode.getId()).count();
			if (taskNum != 0) {
				// 分支令牌经过这个节点则允许正常退回
				customEnd(taskCommandInst, taskComment);
				ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(getToken());
				executionContext.setToFlowNode(flowNode);
				executionContext.setRollBackAssignee(rollBackAssignee);
				getToken().signal(executionContext);
			} else {

				// 分支令牌经过这个节点则允许正常退回
				customEnd(taskCommandInst, taskComment);

				boolean isFind = toFlowNodeEnd(taskCommandInst, taskComment, flowNode, rollBackAssignee, getToken().getParent(), taskQuery);
				if (!isFind) {
					throw new FixFlowException("该节点从未到达过不能退回");
				}
			}

		}

	}

	private boolean toFlowNodeEnd(TaskCommandInst taskCommandInst, String taskComment, FlowNode flowNode, String rollBackAssignee,
			TokenEntity tokenObj, TaskQuery taskQuery) {
		Long taskNum = taskQuery.tokenId(tokenObj.getId()).nodeId(flowNode.getId()).count();
		if (taskNum != 0) {

			tokenObj.terminationChildToken();
			ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(tokenObj);
			executionContext.setToFlowNode(flowNode);
			executionContext.setRollBackAssignee(rollBackAssignee);
			tokenObj.signal(executionContext);
			return true;
		} else {
			if (tokenObj.getParent() != null) {
				return toFlowNodeEnd(taskCommandInst, taskComment, flowNode, rollBackAssignee, tokenObj.getParent(), taskQuery);

			} else {
				return false;
			}

		}

	}

	/**
	 * 这个结束并不会去推动令牌向下。例如用在退回的时候。
	 */
	public void customEnd(TaskCommandInst taskCommandInst, String taskComment) {

		// this.operationCommand = operationCommand;
		if (this.endTime != null) {
			// throw new FixFlowException("任务已经结束,不能再进行处理.");
		}
		if (this.isSuspended) {
			throw new FixFlowException("任务已经暂停不能再处理");
		}

		this.endTime = new Date();

		this.isDraft = false;

		this.isOpen = false;

		this.taskComment = taskComment;

		if (taskCommandInst != null && taskCommandInst.getTaskCommandType() != null && !taskCommandInst.getTaskCommandType().equals("")) {
			String taskCommandType = taskCommandInst.getTaskCommandType();
			String taskCommandName = taskCommandInst.getName();
			// 设置流程自动结束信息 autoEnd
			this.setCommandId(taskCommandInst.getId());
			this.setCommandType(taskCommandType);
			if (taskCommandName == null) {
				TaskCommandDef taskCommandDef = Context.getProcessEngineConfiguration().getTaskCommandDefMap().get(taskCommandType);
				if (taskCommandDef != null) {
					this.setCommandMessage(taskCommandDef.getName());
				}
			} else {
				this.setCommandMessage(taskCommandName);

			}

		} else {

			this.setCommandId(TaskCommandType.AUTOEND);
			this.setCommandType(TaskCommandType.AUTOEND);
			TaskCommandDef taskCommandDef = Context.getProcessEngineConfiguration().getTaskCommandDefMap().get(TaskCommandType.AUTOEND);
			if (taskCommandDef != null) {
				this.setCommandMessage(taskCommandDef.getName());
			}
		}

	}
	
	
	public UserTask getNode() {
		
		if(this.node==null){
			if(StringUtil.isNotEmpty(this.nodeId)){
				this.node=(UserTask)getProcessDefinition().getDefinitions().getElement(this.nodeId);
				return this.node;
			}
		}
		
		return node;
	}

	public TokenEntity removeTimeOutTask() {

		if (getNode().getBoundaryEventRefs().size() > 0) {

			TokenEntity tokenEntity = this.getToken();
			String parentTokenId = tokenEntity.getParent().getId();
			try {
				Scheduler scheduler = Context.getProcessEngineConfiguration().getSchedulerFactory().getScheduler();

				Map<String, TokenEntity> tokenBrothers = tokenEntity.getParent().getChildren();

				int colseTokenBrothersNum = 0;

				for (String tokenBrotherKey : tokenBrothers.keySet()) {

					if (!tokenBrotherKey.equals(tokenEntity.getId())) {

						scheduler.deleteJob(JobKey.jobKey(tokenBrothers.get(tokenBrotherKey).getId(), "FixTimeOutTask_" + parentTokenId));
						tokenBrothers.get(tokenBrotherKey).end();
						colseTokenBrothersNum = colseTokenBrothersNum + 1;
						break;

					}
				}

				if (colseTokenBrothersNum == (tokenBrothers.keySet().size() - 1)) {
					tokenEntity.getParent().terminationChildToken();
					return tokenEntity.getParent();
				}

			} catch (SchedulerException e) {
				e.printStackTrace();
				throw new FixFlowException("流程在离开节点 " + this.getId() + " 的时候发生错误! 错误信息: " + e.toString(), e);
			}

		}

		return null;
	}

	public void assign(ExecutionContext executionContext) {

		// 这里会有泳道的处理 暂时没有实现泳道

		TaskMgmtInstance taskMgmtInstance = executionContext.getTaskMgmtInstance();

		taskMgmtInstance.performAssignment(taskDefinition, this, executionContext);

	}

	public IdentityLinkEntity addIdentityLink(String userId, String groupId, String groupType, IncludeExclusion includeExclusion,
			IdentityLinkType type) {
		IdentityLinkEntity identityLink = new IdentityLinkEntity(GuidUtil.CreateGuid());

		identityLink.setTaskInstance(this);
		identityLink.setUserId(userId);
		identityLink.setGroupId(groupId);
		identityLink.setGroupType(groupType);
		identityLink.setType(type);
		identityLink.setIncludeExclusion(includeExclusion);
		getTaskIdentityLinkEntitys().add(identityLink);
		return identityLink;
	}

	/**
	 * 暂停任务
	 */
	public void suspend() {
		isSuspended = true;
		isOpen = false;
	}

	/**
	 * 恢复任务
	 */
	public void resume() {
		isSuspended = false;
		isOpen = true;
	}

	public List<IdentityLink> getTaskIdentityLinks() {

		return getIdentityLinkQueryToListNoCache();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<IdentityLinkEntity> getTaskIdentityLinkEntitys() {

		return (List)getIdentityLinkQueryToListNoCache();
	}

	public boolean hasEnded() {
		return (endTime != null);
	}

	public void cancel() {
		isCancelled = true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<IdentityLink> getIdentityLinkQueryToListNoCache() {

		if (this.taskIdentityLinks==null) {
			
			this.taskIdentityLinks= new ArrayList<IdentityLinkEntity>();

			List valueObjectTemp = (List) Context.getCommandContext().getIdentityLinkManager().findIdentityLinksByTaskId(this.id);
			if (valueObjectTemp.size() > 0) {

				this.taskIdentityLinks = (List<IdentityLinkEntity>) valueObjectTemp;
				return (List) this.taskIdentityLinks;
			} else {
				return (List) this.taskIdentityLinks;
			}

		} else {
			return (List) this.taskIdentityLinks;
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<IdentityLink> getIdentityLinkQueryToList() {

		if (this.taskIdentityLinks.size() == 0) {

			CacheHandler cacheHandler = Context.getProcessEngineConfiguration().getCacheHandler();

			Object valueObject = cacheHandler.getCacheData("IdentityLink_" + this.id);
			if (valueObject != null) {
				this.taskIdentityLinks = (List<IdentityLinkEntity>) valueObject;
				return (List) this.taskIdentityLinks;
			} else {
				List valueObjectTemp = (List) Context.getCommandContext().getIdentityLinkManager().findIdentityLinksByTaskId(this.id);
				if (valueObjectTemp.size() > 0) {
					cacheHandler.putCacheData("IdentityLink_" + this.id, valueObjectTemp);
					this.taskIdentityLinks = (List<IdentityLinkEntity>) valueObjectTemp;
					return (List) this.taskIdentityLinks;
				} else {
					return (List) this.taskIdentityLinks;
				}
			}
		} else {
			return (List) this.taskIdentityLinks;
		}

	}

	public ProcessDefinitionBehavior getProcessDefinition() {

		if (this.processDefinitionId == null || this.processDefinitionId.equals("")) {
			throw new FixFlowException("processDefinitionId 不能为空!");
		}
		return Context.getCommandContext().getProcessDefinitionManager().findLatestProcessDefinitionById(this.processDefinitionId);
	}

	@Override
	public String getCloneRuleId() {
		return RULE_TASK_INSTANCE_CLONE;
	}

	@Override
	public String getPersistentDbMapRuleId() {
		return RULE_GET_TASKINSTANCE_PERSISTENT_DBMAP;
	}

	@Override
	public String getPersistentStateRuleId() {
		return RULE_GET_TASKINSTANCE_PERSISTENT_STATE;
	}

}
