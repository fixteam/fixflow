package com.founder.fix.fixflow.core.impl.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.UserTask;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef;
import com.founder.fix.fixflow.core.cache.CacheHandler;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.db.AbstractPersistentObject;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.identity.GroupTo;
import com.founder.fix.fixflow.core.impl.interceptor.CommandExecutor;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.util.ClockUtil;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.internationalization.FixFlowResources;
import com.founder.fix.fixflow.core.objkey.TaskInstanceObjKey;
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

public class TaskInstanceEntity extends AbstractPersistentObject implements TaskInstance, Assignable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1620201951314802718L;

	protected String id;

	protected String owner;

	protected String assignee;

	protected String name;

	protected String description;

	protected int priority = TaskInstance.PRIORITY_NORMAL;

	protected Date createTime;

	protected Date startTime;

	protected Date endTime;

	protected Date dueDate;

	protected Date claimTime;

	protected String formUri;
	
	protected String formUriView;

	

	protected boolean isBlocking = false;

	protected boolean isSuspended = false;

	protected boolean isOpen = true;

	protected boolean isCancelled = false;

	protected TokenEntity token;

	protected String tokenId;

	protected String bizKey;

	protected UserTask node;

	protected String nodeId;

	protected String nodeName;

	protected DelegationState delegationState;

	protected String processDefinitionId;

	protected String processDefinitionKey;

	protected String processInstanceId;

	protected TaskDefinition taskDefinition;

	protected List<IdentityLinkEntity> taskIdentityLinks = new ArrayList<IdentityLinkEntity>();

	protected TaskInstance parentTaskInstance;

	protected String parentTaskInstanceId;

	protected TaskMgmtInstance taskMgmtInstance;

	protected String processDefinitionName;
	
	protected boolean isDraft=false;
	
	protected int expectedExecutionTime=0;
	
	protected String admin;
	
	protected String callActivityInstanceId;

	protected String pendingTaskId;

	protected String commandType;
	
	protected String commandId;
	
	
	

	


	/**
	 * 代理处理者
	 */
	protected String agent;
	
	
	/**
	 * 用户分类
	 */
	protected String category;

	

	

	protected Map<String, Object> extensionFields = new HashMap<String, Object>();
	
	/**
	 * 持久化扩展字段
	 */
	protected Map<String, Object> persistenceExtensionFields = new HashMap<String, Object>();
	
	public void setPersistenceExtensionField(String fieldName,Object value){
		extensionFields.put(fieldName, value);
		persistenceExtensionFields.put(fieldName, value);
	}
	


	/**
	 * 默认为fixflow任务
	 */
	protected TaskInstanceType taskInstanceType = TaskInstanceType.FIXFLOWTASK;

	/**
	 * 会签任务组编号
	 */
	protected String taskGroup;

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

	public String getAssignee() {
		return assignee;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getDescription() {
		return description;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public Date getEndTime() {
		return endTime;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getOwner() {
		return owner;
	}

	public TaskInstance getParentTaskInstance() {
		return parentTaskInstance;
	}

	public int getPriority() {
		return priority;
	}

	public Date getStartTime() {
		return startTime;
	}

	public TaskDefinition getTaskDefinition() {
		return taskDefinition;
	}

	public TokenEntity getToken() {
		return token;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
		// addIdentityLink(assignee, null, null,IncludeExclusion.INCLUDE,
		// IdentityLinkType.assignee);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCallActivityInstanceId() {
		return callActivityInstanceId;
	}

	public void setCallActivityInstanceId(String callActivityInstanceId) {
		this.callActivityInstanceId = callActivityInstanceId;
	}

	public void setOwner(String owner) {
		this.owner = owner;
		// addIdentityLink(owner, null, null,
		// IncludeExclusion.INCLUDE,IdentityLinkType.owner);
	}

	public void setParentTaskInstance(TaskInstance parentTaskInstance) {
		this.parentTaskInstanceId = parentTaskInstance.getId();
		this.parentTaskInstance = parentTaskInstance;

	}

	public void setPriority(int priority) {
		this.priority = priority;

	}
	
	public String getFormUriView() {
		return formUriView;
	}

	public void setFormUriView(String formUriView) {
		this.formUriView = formUriView;
	}

	public void addCandidateGroup(GroupTo groupTo, IncludeExclusion includeExclusion) {
		addIdentityLink(null, groupTo.getGroupId(), groupTo.getGroupType(), includeExclusion, IdentityLinkType.candidate);
	}

	public void addCandidateUser(String userId, IncludeExclusion includeExclusion) {
		addIdentityLink(userId, null, null, includeExclusion, IdentityLinkType.candidate);
	}

	public DelegationState getDelegationState() {
		return delegationState;
	}

	public void setDelegationState(DelegationState delegationState) {
		this.delegationState = delegationState;
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

		// if this task instance is associated with a task...
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
		/*
		 * if ((task != null) && (token != null)) { ExecutionContext
		 * executionContext = new ExecutionContext(token);
		 * executionContext.setTask(task);
		 * executionContext.setTaskInstance(this);
		 * task.fireEvent(Event.EVENTTYPE_TASK_START, executionContext); }
		 */
	}

	public void end() {
		
		this.isDraft=false;

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
		if ((taskDefinition != null) && (token != null)) {

			ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(token);
			executionContext.setTaskDefinition(taskDefinition);
			executionContext.setTaskInstance(this);
			// task.fireEvent(Event.EVENTTYPE_TASK_END, executionContext);
		}

		// log this assignment
		if (token != null) {
			// token.addLog(new TaskEndLog(this));
		}

		token.signal();

		// 用于并行、串行会签的处理.

		/*
		 * if (token.getFlowNode() instanceof UserTask && ((UserTask)
		 * this).getLoopCharacteristics() != null) {
		 * 
		 * UserTask userTask = (UserTask) token.getFlowNode();
		 * LoopCharacteristics loopCharacteristics =
		 * userTask.getLoopCharacteristics();
		 * 
		 * if (loopCharacteristics instanceof MultiInstanceLoopCharacteristics)
		 * { Set<TaskInstance>
		 * taskInstances=token.getProcessInstance().getTaskMgmtInstance
		 * ().getTaskInstances(token);
		 * 
		 * // 并行多实例处理 }else { // 串行处理 }
		 * 
		 * }
		 * 
		 * 
		 * 
		 * for (TaskInstance taskInstance :taskInstances) {
		 * if(taskInstance.getNodeId().equals(this.getNodeId())){
		 * 
		 * } }
		 */

	}
	
	
	public void toFlowNodeEnd(TaskCommandInst taskCommandInst,String taskComment,String agent,String admin,FlowNode flowNode,String rollBackAssignee) {
		
		
		//分支退回处理
		
		
		if(token.getParent()==null){
			//主令牌非分支的处理
			
			customEnd(taskCommandInst,taskComment,agent,admin);
			ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(token);
			executionContext.setToFlowNode(flowNode);
			executionContext.setRollBackAssignee(rollBackAssignee);
			token.signal(executionContext);
			
		}
		else{
			//非主令牌分支令牌的处理
			CommandExecutor commandExecutor=Context.getProcessEngineConfiguration().getCommandExecutor();
			TaskQuery taskQuery=new TaskQueryImpl(commandExecutor);
			Long taskNum=taskQuery.tokenId(token.getId()).nodeId(flowNode.getId()).count();
			if(taskNum!=0){
				//分支令牌经过这个节点则允许正常退回
				customEnd(taskCommandInst,taskComment,agent,admin);
				ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(token);
				executionContext.setToFlowNode(flowNode);
				executionContext.setRollBackAssignee(rollBackAssignee);
				token.signal(executionContext);
			}else{
				
				
				
				//分支令牌经过这个节点则允许正常退回
				customEnd(taskCommandInst,taskComment,agent,admin);
				
				
				boolean isFind=toFlowNodeEnd(taskCommandInst, taskComment, agent, admin, flowNode, rollBackAssignee,token.getParent(),taskQuery);
				if(!isFind){
					throw new FixFlowException("该节点从未到达过不能退回");
				}
			}
			
			
			
			
			
		}
		
		
		
		
		
		
		
	}
	
	private boolean toFlowNodeEnd(TaskCommandInst taskCommandInst,String taskComment,String agent,String admin,FlowNode flowNode,String rollBackAssignee,TokenEntity tokenObj,TaskQuery taskQuery){
		Long taskNum=taskQuery.tokenId(tokenObj.getId()).nodeId(flowNode.getId()).count();
		if(taskNum!=0){
			
			tokenObj.terminationChildToken();
			ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(tokenObj);
			executionContext.setToFlowNode(flowNode);
			executionContext.setRollBackAssignee(rollBackAssignee);
			tokenObj.signal(executionContext);
			return true;
		}else{
			if(tokenObj.getParent()!=null){
				return toFlowNodeEnd( taskCommandInst, taskComment, agent, admin, flowNode, rollBackAssignee, tokenObj.getParent(), taskQuery);
				
			}else{
				return false;
			}
			
		}
		
	}


	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}
	
	//public void customEnd(String taskCommandType,String taskCommandName,FlowNode flowNode ) {
		
		
		
	//}
	

	/**
	 * 这个结束并不会去推动令牌向下。例如用在退回的时候。
	 */
	public void customEnd(TaskCommandInst taskCommandInst,String taskComment,String agent,String admin) {
		
		// this.operationCommand = operationCommand;
		if (this.endTime != null) {
			//throw new FixFlowException("任务已经结束,不能再进行处理.");
		}
		if (this.isSuspended) {
			throw new FixFlowException("任务已经暂停不能再处理");
		}

		this.endTime = new Date();
		
		this.isDraft=false;

		this.isOpen = false;
		
		this.taskComment=taskComment;
		
		if(agent!=null&&!agent.equals("")){
			this.setAgent(Authentication.getAuthenticatedUserId());
		}
		if(admin!=null&&!admin.equals("")){
			this.setAdmin(admin);
		}
		
		
		
		
		if(taskCommandInst!=null&&taskCommandInst.getTaskCommandType()!=null&&!taskCommandInst.getTaskCommandType().equals("")){
			String taskCommandType=taskCommandInst.getTaskCommandType();
			String taskCommandName=taskCommandInst.getName();
			//设置流程自动结束信息 autoEnd
			this.setCommandId(taskCommandInst.getId());
			this.setCommandType(taskCommandType);
			if(taskCommandName==null){
				TaskCommandDef taskCommandDef=Context.getProcessEngineConfiguration().getTaskCommandDefMap().get(taskCommandType);
				if(taskCommandDef!=null){
					this.setCommandMessage(taskCommandDef.getName());
				}
			}
			else{
				this.setCommandMessage(taskCommandName);
				
			}
			
		}
		else{
			
			
			this.setCommandType(TaskCommandType.AUTOEND);
			TaskCommandDef taskCommandDef=Context.getProcessEngineConfiguration().getTaskCommandDefMap().get(TaskCommandType.AUTOEND);
			if(taskCommandDef!=null){
				this.setCommandMessage(taskCommandDef.getName());
			}
		}
		

		
		
		
		

	}
	
	
	public TokenEntity removeTimeOutTask(){
		
		if(this.node.getBoundaryEventRefs().size()>0){
			//List<BoundaryEvent> boundaryEvents = this.getBoundaryEventRefs();
			TokenEntity tokenEntity=this.getToken();
			String parentTokenId=tokenEntity.getParent().getId();
			try {
				Scheduler scheduler=Context.getProcessEngineConfiguration().getSchedulerFactory().getScheduler();
				//Set<JobKey> jobKeys=scheduler.getJobKeys(GroupMatcher.jobGroupContains("Out"));
				Map<String, TokenEntity> tokenBrothers=tokenEntity.getParent().getChildren();

				int colseTokenBrothersNum=0;
				
				for (String tokenBrotherKey : tokenBrothers.keySet()) {
					
					
					if(!tokenBrotherKey.equals(tokenEntity.getId())){
						
						//for (BoundaryEvent boundaryEvent : this.node.getBoundaryEventRefs()) {
							//if(tokenBrothers.get(tokenBrotherKey).getFlowNode().getId().equals(boundaryEvent.getId())){
								//JobDetail JobDetail=scheduler.getJobDetail(JobKey.jobKey(tokenBrothers.get(tokenBrotherKey).getId(),"FixTimeOutTask_"+parentTokenId));
								scheduler.deleteJob(JobKey.jobKey(tokenBrothers.get(tokenBrotherKey).getId(),"FixTimeOutTask_"+parentTokenId));
								tokenBrothers.get(tokenBrotherKey).end();
								colseTokenBrothersNum=colseTokenBrothersNum+1;
								break;
							//}
						//}
					}
				}

				if (colseTokenBrothersNum==(tokenBrothers.keySet().size()-1)) {
					tokenEntity.getParent().terminationChildToken();
					//ExecutionContext executionContextParent=ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(tokenEntity.getParent());
					
					
					//super.leave(executionContextParent, sequenceFlow);
					return tokenEntity.getParent();
				}

			} catch (SchedulerException e) {
				e.printStackTrace();
				throw new FixFlowException("流程在离开节点 "+this.getId()+" 的时候发生错误! 错误信息: "+e.toString(), e);
			}
			
		}
		
		return null;
	}

	public void assign(ExecutionContext executionContext) {

		// 这里会有泳道的处理 暂时没有实现泳道

		TaskMgmtInstance taskMgmtInstance = executionContext.getTaskMgmtInstance();

		taskMgmtInstance.performAssignment(taskDefinition, this, executionContext);

	}

	public IdentityLinkEntity addIdentityLink(String userId, String groupId, String groupType, IncludeExclusion includeExclusion, IdentityLinkType type) {
		IdentityLinkEntity identityLink = new IdentityLinkEntity(GuidUtil.CreateGuid());

		identityLink.setTaskInstance(this);
		identityLink.setUserId(userId);
		identityLink.setGroupId(groupId);
		identityLink.setGroupType(groupType);
		identityLink.setType(type);
		identityLink.setIncludeExclusion(includeExclusion);
		this.taskIdentityLinks.add(identityLink);
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<IdentityLink> getTaskIdentityLinks() {

		return (List) taskIdentityLinks;
	}

	public List<IdentityLinkEntity> getTaskIdentityLinkEntitys() {

		return taskIdentityLinks;
	}

	public boolean hasEnded() {
		return (endTime != null);
	}

	public boolean isBlocking() {
		return isBlocking;
	}

	public void cancel() {
		isCancelled = true;
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public boolean isSuspended() {
		return isSuspended;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAssigneeId(String assigneeId) {
		this.assignee = assigneeId;
	}

	public void setOwnerId(String ownerId) {
		this.owner = ownerId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setBlocking(boolean isBlocking) {
		this.isBlocking = isBlocking;
	}

	public String getProcessInstanceId() {

		return this.processInstanceId;
	}

	public String getTokenId() {
		return this.tokenId;
	}

	public String getProcessDefinitionId() {
		return this.processDefinitionId;
	}

	public String getParentTaskInstanceId() {
		return this.parentTaskInstanceId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public void setParentTaskInstanceId(String parentTaskInstanceId) {
		this.parentTaskInstanceId = parentTaskInstanceId;
	}

	public String getNodeId() {
		// TODO Auto-generated method stub
		return this.nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}


	

	public String getCommandType() {
		return commandType;
	}

	public void setCommandType(String commandType) {
		this.commandType = commandType;
	}

	protected String commandMessage;

	public String getCommandMessage() {
		
		
		
		if(this.getCommandType()==null){
			
			return commandMessage;
			
		}
		Boolean booleanTemp=StringUtil.getBoolean(Context.getProcessEngineConfiguration().getInternationalizationConfig().getIsEnable());
    	
    	
    	if(booleanTemp){
        	String processId=this.getProcessDefinitionId();
        	String cType=Context.getProcessEngineConfiguration().getTaskCommandDefMap().get(this.getCommandType()).getType();
        	String nameTemp=null;
        	if(cType.equals("system")){
        		nameTemp=Context.getProcessEngineConfiguration().getFixFlowResources().getResourceName(FixFlowResources.TaskComandResource, "System_"+commandId);
            	
        	}
        	else{
        		nameTemp=Context.getProcessEngineConfiguration().getFixFlowResources().getResourceName(processId, this.nodeId+"_"+commandId);
            	
        	}

        	if(nameTemp==null){
        		return commandMessage;
        	}
        	return nameTemp;
        	
    	}
    	else{
    		return commandMessage;
    	}
		
	}
	
	public String  getDefaultCommandMessage(){
		return commandMessage;
	}

	public void setCommandMessage(String commandMessage) {
		this.commandMessage = commandMessage;
	}

	protected String taskComment;

	public String getTaskComment() {
		return taskComment;
	}

	public void setTaskComment(String taskComment) {
		this.taskComment = taskComment;
	}

	public String getBizKey() {
		return this.bizKey;
	}

	public void setBizKey(String bizKey) {
		this.bizKey = bizKey;
	}
	
	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	// 持久化的时候用的方法

	public void setAssigneeWithoutCascade(String assignee) {
		this.assignee = assignee;
	}

	public void setOwnerWithoutCascade(String owner) {
		this.owner = owner;
	}

	public void setDueDateWithoutCascade(Date dueDate) {
		this.dueDate = dueDate;
	}

	public void setPriorityWithoutCascade(int priority) {
		this.priority = priority;
	}

	public void setParentTaskIdWithoutCascade(String parentTaskInstanceId) {
		this.parentTaskInstanceId = parentTaskInstanceId;
	}

	public void setNameWithoutCascade(String taskName) {
		this.name = taskName;
	}

	public void setDescriptionWithoutCascade(String description) {
		this.description = description;
	}

	public void setEndTimeWithoutCascade(Date endTime) {
		this.endTime = endTime;
	}

	public Date getClaimTime() {
		return this.claimTime;
	}

	public void setClaimTime(Date claimTime) {
		this.claimTime = claimTime;
	}

	public void setClaimTimeWithoutCascade(Date claimTime) {
		this.claimTime = claimTime;
	}

	public void setIdWithoutCascade(String id) {
		this.id = id;
	}

	public void setCreateTimeWithoutCascade(Date createTime) {
		this.createTime = createTime;
	}

	public void setStartTimeWithoutCascade(Date startTime) {
		this.startTime = startTime;
	}

	public void setProcessDefinitionKeyWithoutCascade(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}
	
	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public void setProcessDefinitionIdWithoutCascade(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getProcessDefinitionKey() {
		return this.processDefinitionKey;
	}
	
	public String getCommandId() {
		return commandId;
	}

	public void setCommandId(String commandId) {
		this.commandId = commandId;
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

	public void setFormUri(String formUri) {
		// TODO Auto-generated method stub
		this.formUri = formUri;
	}

	public String getFormUri() {
		// TODO Auto-generated method stub
		return this.formUri;
	}

	public String getTaskGroup() {
		return taskGroup;
	}

	public void setTaskGroup(String taskGroup) {
		this.taskGroup = taskGroup;
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	public TaskInstanceType getTaskInstanceType() {
		return taskInstanceType;
	}

	public void setTaskInstanceType(TaskInstanceType taskInstanceType) {
		this.taskInstanceType = taskInstanceType;
	}

	public String getProcessDefinitionName() {
		return processDefinitionName;
	}

	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}

	public Object getExtensionField(String fieldName) {
		return extensionFields.get(fieldName);
	}

	public Map<String, Object> getExtensionFields() {
		return extensionFields;
	}

	public void setExtensionFields(Map<String, Object> extensionFields) {
		this.extensionFields = extensionFields;
	}

	public void addExtensionField(String fieldName, Object fieldValue) {
		this.extensionFields.put(fieldName, fieldValue);
	}

	
	public boolean isDraft() {
		return isDraft;
	}

	public void setDraft(boolean isDraft) {
		this.isDraft = isDraft;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getExpectedExecutionTime() {
		return expectedExecutionTime;
	}

	public void setExpectedExecutionTime(int expectedExecutionTime) {
		this.expectedExecutionTime = expectedExecutionTime;
	}
	
	public String getPendingTaskId() {
		return pendingTaskId;
	}

	public void setPendingTaskId(String pendingTaskId) {
		this.pendingTaskId = pendingTaskId;
	}

	
	
	/**
	 * 从数据库读取任务
	 */
	public TaskInstanceEntity(Map<String, Object> entityMap) {

		persistentInit(entityMap);

	}


	public void persistentInit(Map<String, Object> entityMap) {
		for (String dataKey : entityMap.keySet()) {

			if (dataKey.equals(TaskInstanceObjKey.TaskInstanceId().DataBaseKey())) {
				this.setId(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}
			
			if (dataKey.equals(TaskInstanceObjKey.Agent().DataBaseKey())) {
				this.setAgent(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}

			if (dataKey.equals(TaskInstanceObjKey.Name().DataBaseKey())) {
				this.setNameWithoutCascade(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}

			if (dataKey.equals(TaskInstanceObjKey.Description().DataBaseKey())) {
				this.setDescriptionWithoutCascade(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}

			if (dataKey.equals(TaskInstanceObjKey.Priority().DataBaseKey())) {
				this.setPriorityWithoutCascade(StringUtil.getInt(entityMap.get(dataKey)));
				continue;
			}

			if (dataKey.equals(TaskInstanceObjKey.Owner().DataBaseKey())) {
				this.setOwnerWithoutCascade(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}

			if (dataKey.equals(TaskInstanceObjKey.Assignee().DataBaseKey())) {
				this.setAssigneeWithoutCascade(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}

			if (dataKey.equals(TaskInstanceObjKey.NodeId().DataBaseKey())) {
				this.setNodeId(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}

			if (dataKey.equals(TaskInstanceObjKey.ProcessInstanceId().DataBaseKey())) {
				this.setProcessInstanceId(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}

			if (dataKey.equals(TaskInstanceObjKey.ProcessDefinitionId().DataBaseKey())) {
				this.setProcessDefinitionIdWithoutCascade(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}

			if (dataKey.equals(TaskInstanceObjKey.ProcessDefinitionKey().DataBaseKey())) {
				this.setProcessDefinitionKeyWithoutCascade(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}

			if (dataKey.equals(TaskInstanceObjKey.DelegationState().DataBaseKey())) {

				if (entityMap.get(dataKey) != null) {
					this.setDelegationState(DelegationState.valueOf(entityMap.get(dataKey).toString()));
					
				}
				continue;
			}
			if (dataKey.equals(TaskInstanceObjKey.TokenId().DataBaseKey())) {
				this.setTokenId(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}

			if (dataKey.equals(TaskInstanceObjKey.CreateTime().DataBaseKey())) {
				this.setCreateTime(StringUtil.getDate(entityMap.get(dataKey)));
				continue;
			}

			if (dataKey.equals(TaskInstanceObjKey.StartTime().DataBaseKey())) {
				this.setStartTime(StringUtil.getDate(entityMap.get(dataKey)));
				continue;
			}

			if (dataKey.equals(TaskInstanceObjKey.EndTime().DataBaseKey())) {
				this.setEndTime(StringUtil.getDate(entityMap.get(dataKey)));
				continue;
			}

			if (dataKey.equals(TaskInstanceObjKey.DueDate().DataBaseKey())) {
				this.setDueDateWithoutCascade(StringUtil.getDate(entityMap.get(dataKey)));
				continue;
			}
			if (dataKey.equals(TaskInstanceObjKey.ClaimTime().DataBaseKey())) {
				this.setClaimTime(StringUtil.getDate(entityMap.get(dataKey)));
				continue;
			}

			if (dataKey.equals(TaskInstanceObjKey.BizKey().DataBaseKey())) {
				this.setBizKey(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}
			

			if (dataKey.equals(TaskInstanceObjKey.CommandId().DataBaseKey())) {
				this.setCommandId(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}


			if (dataKey.equals(TaskInstanceObjKey.CommandType().DataBaseKey())) {
				this.setCommandType(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}

			if (dataKey.equals(TaskInstanceObjKey.CommandMessage().DataBaseKey())) {
				this.setCommandMessage(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}

			if (dataKey.equals(TaskInstanceObjKey.TaskComment().DataBaseKey())) {
				this.setTaskComment(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}
			if (dataKey.equals(TaskInstanceObjKey.NodeName().DataBaseKey())) {
				this.setNodeName(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}
			if (dataKey.equals(TaskInstanceObjKey.FormUri().DataBaseKey())) {
				this.setFormUri(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}
			if (dataKey.equals(TaskInstanceObjKey.FormUriView().DataBaseKey())) {
				this.setFormUriView(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}
			if (dataKey.equals(TaskInstanceObjKey.TaskGroup().DataBaseKey())) {
				this.setTaskGroup(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}
			if (dataKey.equals(TaskInstanceObjKey.TaskInstanceType().DataBaseKey())) {
				this.setTaskInstanceType(TaskInstanceType.valueOf(StringUtil.getString(entityMap.get(dataKey))));
				continue;
			}
			if (dataKey.equals(TaskInstanceObjKey.ProcessDefinitionName().DataBaseKey())) {
				this.setProcessDefinitionName(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}
			
			if (dataKey.equals(TaskInstanceObjKey.IsDraft().DataBaseKey())) {
				this.setDraft(StringUtil.getBoolean(entityMap.get(dataKey)));
				continue;
			}
			
			if (dataKey.equals(TaskInstanceObjKey.IsOpen().DataBaseKey())) {
				this.isOpen=StringUtil.getBoolean(entityMap.get(dataKey));
				continue;
			}
			if (dataKey.equals(TaskInstanceObjKey.IsSuspended().DataBaseKey())) {
				this.isSuspended=StringUtil.getBoolean(entityMap.get(dataKey));
				continue;
			}
			if (dataKey.equals(TaskInstanceObjKey.IsCancelled().DataBaseKey())) {
				this.isCancelled=StringUtil.getBoolean(entityMap.get(dataKey));
				continue;
			}
			
			if (dataKey.equals(TaskInstanceObjKey.Category().DataBaseKey())) {
				this.setCategory(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}

			if (dataKey.equals(TaskInstanceObjKey.ExpectedExecutionTime().DataBaseKey())) {
				this.setExpectedExecutionTime(StringUtil.getInt(entityMap.get(dataKey)));
				continue;
			}
			
			if (dataKey.equals(TaskInstanceObjKey.Admin().DataBaseKey())) {
				this.setAdmin(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}
			
			if (dataKey.equals(TaskInstanceObjKey.CallActivityInstanceId().DataBaseKey())) {
				this.setCallActivityInstanceId(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}
			
			if (dataKey.equals(TaskInstanceObjKey.PendingTaskId().DataBaseKey())) {
				this.setPendingTaskId(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}
			
			this.addExtensionField(dataKey, entityMap.get(dataKey));
			/*
			 * if (dataKey.equals("PI_INITIATOR")) {
			 * this.addExtensionField("PI_INITIATOR",
			 * StringUtil.getString(entityMap.get(dataKey))); }
			 * 
			 * if (dataKey.equals("PI_START_AUTHOR")) {
			 * this.addExtensionField("PI_START_AUTHOR",
			 * StringUtil.getString(entityMap.get(dataKey))); }
			 * 
			 * if (dataKey.equals("PI_START_TIME")) {
			 * this.addExtensionField("PI_START_TIME",
			 * StringUtil.getDate(entityMap.get(dataKey))); }
			 * 
			 * if (dataKey.equals("PI_SUBJECT")) {
			 * this.addExtensionField("PI_SUBJECT",
			 * StringUtil.getString(entityMap.get(dataKey))); }
			 */
		}
	}

	public Map<String, Object> getPersistentDbMap() {
		Map<String, Object> objectParam = new HashMap<String, Object>();

		objectParam.put(TaskInstanceObjKey.TaskInstanceId().DataBaseKey(), this.getId());

		objectParam.put(TaskInstanceObjKey.ProcessInstanceId().DataBaseKey(), this.getProcessInstanceId());

		objectParam.put(TaskInstanceObjKey.ProcessDefinitionId().DataBaseKey(), this.getProcessDefinitionId());

		objectParam.put(TaskInstanceObjKey.ProcessDefinitionKey().DataBaseKey(), this.getProcessDefinitionKey());

		objectParam.put(TaskInstanceObjKey.TokenId().DataBaseKey(), this.getTokenId());

		objectParam.put(TaskInstanceObjKey.NodeId().DataBaseKey(), this.getNodeId());

		objectParam.put(TaskInstanceObjKey.Description().DataBaseKey(), this.getDescription());

		objectParam.put(TaskInstanceObjKey.ParentTaskInstanceId().DataBaseKey(), this.getParentTaskInstanceId());

		objectParam.put(TaskInstanceObjKey.Assignee().DataBaseKey(), this.getAssignee());

		objectParam.put(TaskInstanceObjKey.Name().DataBaseKey(), this.getName());

		objectParam.put(TaskInstanceObjKey.CreateTime().DataBaseKey(), this.getCreateTime());

		objectParam.put(TaskInstanceObjKey.StartTime().DataBaseKey(), this.getStartTime());

		objectParam.put(TaskInstanceObjKey.EndTime().DataBaseKey(), this.getEndTime());

		objectParam.put(TaskInstanceObjKey.DueDate().DataBaseKey(), this.getDueDate());

		objectParam.put(TaskInstanceObjKey.ClaimTime().DataBaseKey(), this.getClaimTime());

		objectParam.put(TaskInstanceObjKey.Priority().DataBaseKey(), String.valueOf(this.getPriority()));

		objectParam.put(TaskInstanceObjKey.Owner().DataBaseKey(), this.getOwner());

		objectParam.put(TaskInstanceObjKey.BizKey().DataBaseKey(), this.getBizKey());

		objectParam.put(TaskInstanceObjKey.CommandType().DataBaseKey(), this.getCommandType());

		objectParam.put(TaskInstanceObjKey.CommandId().DataBaseKey(), this.getCommandId());
		
		objectParam.put(TaskInstanceObjKey.CommandMessage().DataBaseKey(), this.getCommandMessage());

		objectParam.put(TaskInstanceObjKey.TaskComment().DataBaseKey(), this.getTaskComment());

		objectParam.put(TaskInstanceObjKey.NodeName().DataBaseKey(), this.getNodeName());

		objectParam.put(TaskInstanceObjKey.DelegationState().DataBaseKey(), StringUtil.getString(this.getDelegationState()));

		objectParam.put(TaskInstanceObjKey.FormUri().DataBaseKey(), this.getFormUri());
		
		objectParam.put(TaskInstanceObjKey.FormUriView().DataBaseKey(), this.getFormUriView());

		objectParam.put(TaskInstanceObjKey.TaskGroup().DataBaseKey(), this.getTaskGroup());

		objectParam.put(TaskInstanceObjKey.TaskInstanceType().DataBaseKey(), this.getTaskInstanceType().toString());

		objectParam.put(TaskInstanceObjKey.ProcessDefinitionName().DataBaseKey(), this.getProcessDefinitionName());

		
		objectParam.put(TaskInstanceObjKey.IsSuspended().DataBaseKey(), String.valueOf(this.isSuspended()));
		objectParam.put(TaskInstanceObjKey.IsOpen().DataBaseKey(), String.valueOf(this.isOpen()));
		
		objectParam.put(TaskInstanceObjKey.IsDraft().DataBaseKey(), String.valueOf(this.isDraft()));
		
		objectParam.put(TaskInstanceObjKey.IsCancelled().DataBaseKey(), String.valueOf(this.isCancelled()));
		
		objectParam.put(TaskInstanceObjKey.Category().DataBaseKey(), String.valueOf(this.category));
		
		objectParam.put(TaskInstanceObjKey.ExpectedExecutionTime().DataBaseKey(), String.valueOf(this.expectedExecutionTime));
		objectParam.put(TaskInstanceObjKey.Agent().DataBaseKey(), this.agent);
		objectParam.put(TaskInstanceObjKey.Admin().DataBaseKey(), this.admin);
		
		
		objectParam.put(TaskInstanceObjKey.CallActivityInstanceId().DataBaseKey(), this.callActivityInstanceId);
		objectParam.put(TaskInstanceObjKey.PendingTaskId().DataBaseKey(), this.pendingTaskId);
		
		
		for (String key : persistenceExtensionFields.keySet()) {
			objectParam.put(key, persistenceExtensionFields.get(key));	
		}
		
		return objectParam;
	}


	
	public Map<String, Object> getPersistentState() {

		Map<String, Object> persistentState = new HashMap<String, Object>();
		
		persistentState.put(TaskInstanceObjKey.TaskInstanceId().FullKey(), this.id);
		persistentState.put(TaskInstanceObjKey.Name().FullKey(), this.name);
		persistentState.put(TaskInstanceObjKey.Description().FullKey(), this.description);
		persistentState.put(TaskInstanceObjKey.Priority().FullKey(), this.priority);
		persistentState.put(TaskInstanceObjKey.Owner().FullKey(), this.owner);
		persistentState.put(TaskInstanceObjKey.Assignee().FullKey(), this.assignee);
		persistentState.put(TaskInstanceObjKey.NodeId().FullKey(), this.nodeId);
		persistentState.put(TaskInstanceObjKey.NodeName().FullKey(), this.nodeName);
		persistentState.put(TaskInstanceObjKey.ProcessInstanceId().FullKey(), this.processInstanceId);
		persistentState.put(TaskInstanceObjKey.DelegationState().FullKey(), this.delegationState);
		persistentState.put(TaskInstanceObjKey.TokenId().FullKey(), this.tokenId);
		persistentState.put(TaskInstanceObjKey.CreateTime().FullKey(), this.createTime);
		persistentState.put(TaskInstanceObjKey.StartTime().FullKey(), this.startTime);
		persistentState.put(TaskInstanceObjKey.EndTime().FullKey(), this.endTime);
		persistentState.put(TaskInstanceObjKey.ClaimTime().FullKey(), this.claimTime);
		persistentState.put(TaskInstanceObjKey.ProcessDefinitionId().FullKey(), this.processDefinitionId);
		persistentState.put(TaskInstanceObjKey.ProcessDefinitionKey().FullKey(), this.processDefinitionKey);
		persistentState.put(TaskInstanceObjKey.DueDate().FullKey(), this.dueDate);
		persistentState.put(TaskInstanceObjKey.ParentTaskInstanceId().FullKey(), this.parentTaskInstanceId);
		persistentState.put(TaskInstanceObjKey.IsBlocking().FullKey(), this.isBlocking);
		persistentState.put(TaskInstanceObjKey.IsOpen().FullKey(), this.isOpen);
		persistentState.put(TaskInstanceObjKey.IsCancelled().FullKey(), this.isCancelled);
		persistentState.put(TaskInstanceObjKey.IsSuspended().FullKey(), this.isSuspended);
		persistentState.put(TaskInstanceObjKey.HasEnded().FullKey(), this.endTime != null);
		persistentState.put(TaskInstanceObjKey.BizKey().FullKey(), this.bizKey);
		persistentState.put(TaskInstanceObjKey.CommandId().FullKey(), this.commandId);
		persistentState.put(TaskInstanceObjKey.CommandType().FullKey(), this.commandType);
		persistentState.put(TaskInstanceObjKey.CommandMessage().FullKey(), this.commandMessage);
		persistentState.put(TaskInstanceObjKey.TaskComment().FullKey(), this.taskComment);
		persistentState.put(TaskInstanceObjKey.FormUri().FullKey(), this.formUri);
		persistentState.put(TaskInstanceObjKey.FormUri().FullKey(), this.formUriView);
		persistentState.put(TaskInstanceObjKey.TaskGroup().FullKey(), this.taskGroup);
		persistentState.put(TaskInstanceObjKey.TaskInstanceType().FullKey(), this.taskInstanceType.toString());
		persistentState.put(TaskInstanceObjKey.ProcessDefinitionName().FullKey(), this.getProcessDefinitionName());
		persistentState.put(TaskInstanceObjKey.IsDraft().FullKey(), this.isDraft);
		persistentState.put(TaskInstanceObjKey.Category().FullKey(), this.category);
		persistentState.put(TaskInstanceObjKey.ExpectedExecutionTime().FullKey(), this.expectedExecutionTime);
		persistentState.put(TaskInstanceObjKey.Agent().FullKey(), this.agent);
		persistentState.put(TaskInstanceObjKey.Admin().FullKey(), this.admin);
		persistentState.put(TaskInstanceObjKey.CallActivityInstanceId().FullKey(), this.callActivityInstanceId);
		persistentState.put(TaskInstanceObjKey.PendingTaskId().FullKey(), this.pendingTaskId);
		
		
		for (String key : extensionFields.keySet()) {
			persistentState.put(key, extensionFields.get(key));	
		}
		
		return persistentState;
	}

}
