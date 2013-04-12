package com.founder.fix.fixflow.core.impl.runtime;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.LoopCharacteristics;
import org.eclipse.bpmn2.MultiInstanceLoopCharacteristics;


import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;

import com.founder.fix.fixflow.core.impl.db.AbstractPersistentObject;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.objkey.TokenObjKey;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.runtime.Token;
import com.founder.fix.fixflow.core.task.TaskMgmtInstance;

public class TokenEntity extends AbstractPersistentObject implements Token {

	private static final long serialVersionUID = 1L;

	/**
	 * 令牌编号
	 */
	protected String id;

	/**
	 * 令牌名称
	 */
	protected String name;

	/**
	 * 令牌启动时间
	 */
	protected Date startTime;

	/**
	 * 结束日期的标志，表明此令牌已经结束。
	 */
	protected Date endTime;

	/**
	 * 令牌是否被锁定(用于在执行用户自己的Action的时候某些情况需要将令牌锁定)
	 */
	boolean isLocked = false;

	/**
	 * 令牌所在的节点
	 */
	protected FlowNode flowNode;

	/**
	 * 令牌进入节点的时间
	 */
	protected Date nodeEnterTime;

	/**
	 * 是否是子流程根令牌
	 */
	protected boolean isSubProcessRootToken = false;

	/**
	 * 流程实例
	 */
	protected ProcessInstanceEntity processInstance;

	/**
	 * 父令牌
	 */
	protected TokenEntity parent;

	protected String nodeId;

	/**
	 * 是否为自由令牌
	 */
	protected boolean freeToken;

	/**
	 * 父令牌号(如果有则这个令牌为自由令牌)
	 */
	protected String parentFreeTokenId;

	/**
	 * 父令牌(如果有则这个令牌为自由令牌)
	 */
	protected TokenEntity parentFreeToken;

	protected Map<String, Object> extensionFields = new HashMap<String, Object>();

	/**
	 * 子令牌集合
	 */
	protected Map<String, TokenEntity> children=new HashMap<String, TokenEntity>();

	/**
	 * 自由子令牌集合
	 */
	protected Map<String, TokenEntity> freeChildren = new HashMap<String, TokenEntity>();

	/**
	 * isAbleToReactivateParent在创建分支令牌为true,当子令牌到达join是会被设为false.
	 */
	boolean isAbleToReactivateParent = true;

	/**
	 * 是否停止
	 */
	boolean isSuspended = false;

	protected String processInstanceId;
	protected String parentTokenId;

	public TokenEntity() {

	}

	/**
	 * 创建令牌
	 * 
	 * @param fixflowInstance
	 *            流程实例
	 * @throws Exception
	 */
	public TokenEntity(ProcessInstanceEntity processInstance) {

		// 给令牌编号赋值(guid)
		this.id = GuidUtil.CreateGuid();
		// 设置令牌的创建时间
		this.startTime = new Date();
		// 设置令牌的流程实例
		setProcessInstance(processInstance);
		// 将新生成的令牌放入流程实例令牌列表中
		processInstance.addTokenList(this);

	}

	/**
	 * 创建一个子令牌
	 * 
	 * @param parent
	 *            父令牌
	 * @param name
	 *            令牌名称
	 */
	public TokenEntity(TokenEntity parent, String name) {
		this(parent, name, false);
	}

	/**
	 * 创建一个子令牌
	 * 
	 * @param parent
	 *            父令牌
	 * @param name
	 *            令牌名称
	 */
	public TokenEntity(TokenEntity parent, String name, boolean freeToken) {
		// 设置令牌的编号
		this.id = GuidUtil.CreateGuid();
		// 设置令牌的开始时间
		this.startTime = new Date();
		// 设置令牌的流程实例对象
		setProcessInstance( parent.getProcessInstance());
		
		// 设置令牌的名称
		this.name = name;
		// 设置令牌所在的节点
		this.flowNode = parent.getFlowNode();

		// 把这个新生成的令牌放到他爸爸的儿子集合里～
		if (freeToken) {
			this.freeToken = true;
			this.parentFreeTokenId = parent.getId();
			this.parentFreeToken = parent;
		} else {
			// 设置令牌的爸爸囧～
			setParent(parent);
			parent.addChild(this);
		}

		// 将生成的新节点放入流程实例令牌列表中
		this.getProcessInstance().addTokenList(this);
	}

	// 令牌流转
	// ////////////////////////////////////////////////////////

	public void signal() {
		ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(this);
		signal(executionContext);
	}
	
	public void signalKillChildMoveParentToken(FlowNode flowNode) {
		
		TokenEntity tokenParent=this.parent;
		if(tokenParent==null){
			tokenParent=this;
		}
		tokenParent.terminationChildToken();
		ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(tokenParent);
		flowNode.leave(executionContext);
		
		//throw new FixFlowException("没有实现");
	}

	public void signal(ExecutionContext executionContext) {

		if (executionContext == null) {
			throw new FixFlowException("执行内容不能为空！");

		}
		if (isSuspended) {
			throw new FixFlowException("令牌已经停止！");

		}
		if (isLocked) {
			throw new FixFlowException("令牌已经锁定！");

		}

		try {

			FlowNode signalNode = flowNode;
			// signalNode.fireEvent(Event.EVENTTYPE_BEFORE_SIGNAL,executionContext);

			signalNode.leave(executionContext);

			// signalNode.fireEvent(Event.EVENTTYPE_AFTER_SIGNAL,
			// executionContext);

		} finally {

		}
	}

	/*
	 * public ProcessInstance createSubProcessInstance(ProcessDefinition
	 * subProcessDefinition) { // 创建一个子流程实例 subProcessInstance =
	 * ProcessObjectFactory
	 * .FACTORYINSTANCE.createProcessInstance(subProcessDefinition,
	 * this.getProcessInstance().getBizKey()); // bind the subprocess to the
	 * super-process-token setSubProcessInstance(subProcessInstance);
	 * subProcessInstance.setSuperProcessToken(this); // make sure the process
	 * gets saved during super process save
	 * //processInstance.addCascadeProcessInstance(subProcessInstance); return
	 * subProcessInstance; }
	 */

	public void addChild(TokenEntity token) {
		if (children == null) {
			children = new HashMap<String, TokenEntity>();
		}
		children.put(token.getId(), token);
	}

	public TokenEntity getParent() {
		return parent;
	}

	public boolean hasParent() {
		return (parent != null);
	}

	public boolean hasChild(String name) {
		return (children != null ? children.containsKey(name) : false);
	}

	public Token getChild(String id) {
		Token child = null;
		if (children != null) {
			child = (Token) children.get(id);
		}
		return child;
	}

	public ProcessInstanceEntity getProcessInstance() {

		return processInstance;

	}

	public void setProcessInstance(ProcessInstanceEntity processInstance) {
		this.processInstance = processInstance;
		if (processInstance == null) {
			this.processInstanceId = null;
		} else {
			this.processInstanceId = processInstance.getId();
		}

	}

	public void setFlowNode(FlowNode flowNode) {
		this.flowNode = flowNode;
		if (flowNode == null) {
			this.nodeId = null;
		} else {
			this.nodeId = flowNode.getId();
		}

	}

	public void setNodeEnterTime(Date date) {
		this.nodeEnterTime = date;

	}

	public FlowNode getFlowNode() {
		// TODO Auto-generated method stub
		return flowNode;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public void end() {
		end(true);
	}

	public void end(boolean verifyParentTermination) {

		// 如果令牌已经有结束时间则不执行令牌结束方法
		if (endTime == null) {

			// 结束令牌.使他不能再启动父令牌
			isAbleToReactivateParent = false;

			// 设置结束日起
			// 结束日期的标志，表明此令牌已经结束。
			this.endTime = new Date();

			// 结束所有子令牌
			if (children != null) {

				for (String tokenKey : children.keySet()) {
					TokenEntity child = children.get(tokenKey);
					if (!child.hasEnded()) {
						child.end();
					}
				}
			}

			// 记录日志用
			if (parent != null) {
				// 添加日志
				// parent.addLog(new TokenEndLog(this));
			}

			// 移除这个将要结束的令牌上的所有未完成的任务
			TaskMgmtInstance taskMgmtInstance = (TaskMgmtInstance) this.getProcessInstance().getTaskMgmtInstance();
			for (TaskInstanceEntity taskInstance : taskMgmtInstance.getTaskInstanceEntitys(this)) {
				if (!taskInstance.hasEnded()) {
					
					taskInstance.customEnd(null,null,null,null);
				}
			}

			if (verifyParentTermination) {
				// 如果这是根令牌,则需要结束流程实例.
				notifyParentOfTokenEnd();
			}

		}
	}

	public void terminationChildToken() {
		// 如果令牌已经有结束时间则不执行令牌结束方法
		if (endTime == null) {

			// 结束所有子令牌
			if (children != null) {

				for (String tokenKey : children.keySet()) {
					TokenEntity child = children.get(tokenKey);
					if (!child.hasEnded()) {
						// 结束令牌.使他不能再启动父令牌
						child.setAbleToReactivateParent(false);

						// 移除这个将要结束的令牌上的所有未完成的任务
						removeTaskInstanceSynchronization(child,null,null,null,null,null,null);
						child.terminationChildToken();
						// 设置结束日起
						// 结束日期的标志，表明此令牌已经结束。
						child.setEndTime(new Date());

					}
				}
			}

		}
	}
	
	
	public void terminationChildTokenWithTask(String taskommandType,String taskommandName,String taskComment,String assigneeId,String agent,String admin) {
		// 如果令牌已经有结束时间则不执行令牌结束方法
		if (endTime == null) {

			// 结束所有子令牌
			if (children != null) {

				for (String tokenKey : children.keySet()) {
					TokenEntity child = children.get(tokenKey);
					if (!child.hasEnded()) {
						// 结束令牌.使他不能再启动父令牌
						child.setAbleToReactivateParent(false);

						// 移除这个将要结束的令牌上的所有未完成的任务
						removeTaskInstanceSynchronization(child,taskommandType,taskommandName,taskComment,assigneeId,agent,admin);
						child.terminationChildToken();
						// 设置结束日起
						// 结束日期的标志，表明此令牌已经结束。
						child.setEndTime(new Date());

					}
				}
			}

		}
	}
	
	private TaskMgmtInstance getTaskMgmtInstance(TokenEntity token) {
		return (TaskMgmtInstance) token.getProcessInstance().getTaskMgmtInstance();
	}
	
	private void removeTaskInstanceSynchronization(TokenEntity token,String taskCommandType,String taskCommandName,String taskComment,String assigneeId,String agent,String admin) {
		// TODO Auto-generated method stub
		
		
		
		String assigneeIdObj=assigneeId;
		 String agentObj=null;
		 String adminObj=null;
		
		if(agent!=null){
			
			assigneeIdObj=agent;
			agentObj=Authentication.getAuthenticatedUserId();
			
		}
		
		
		
		if(admin!=null){
			assigneeIdObj=admin;
			adminObj=admin;
		}
		
		
		TaskMgmtInstance tmi = getTaskMgmtInstance(token);
		for (TaskInstanceEntity taskInstance : tmi.getTaskInstanceEntitys(token)) {
			if (!taskInstance.hasEnded()) {
				taskInstance.customEnd(null,null,null,null);
				if(assigneeId!=null){
					taskInstance.setAssignee(assigneeIdObj);
					
				}
				taskInstance.setAgent(agentObj);
				taskInstance.setAdmin(adminObj);
				//taskInstance.setDraft(false);
				taskInstance.setTaskComment(taskComment);//taskComment
			}
		}
	}
	
	public void removeTaskInstanceSynchronization(String taskCommandType,String taskCommandName,String taskComment,String assigneeId,String agent,String admin) {
		removeTaskInstanceSynchronization(this,taskCommandType,taskCommandName,taskComment,assigneeId,agent,admin);
	}
	


	void notifyParentOfTokenEnd() {
		// 判断是否为根令牌
		if (isRoot()) {
			// 结束流程实例

			for (TokenEntity tokenObj : this.getProcessInstance().getTokenList()) {
				if (tokenObj.isFreeToken() && !tokenObj.hasEnded()) {
					return;
				}
			}

			processInstance.end();
		} else {
			if (parent != null) {
				//下面这句话是用来当存在分支时 一个分支走到结束另一个分支没结束的时候,不能结束他的父令牌
				//只有当到达结束节点的令牌的同级分支都结束才能结束父令牌
				if (!parent.hasActiveChildren()) {
					// 推动父令牌向后执行
					if (this.isSubProcessRootToken) {
						if (!parent.hasEnded()) {
							parent.signal();
						}

					} else {
						parent.end();
					}

				}
				else{
					//子流程多实例的时候 每个子流程结束的时候去触发验证完成条件
					if(parent.getFlowNode() instanceof Activity){
					
						Activity activity = (Activity) parent.getFlowNode();
						LoopCharacteristics loopCharacteristics = activity.getLoopCharacteristics();

						if (loopCharacteristics instanceof MultiInstanceLoopCharacteristics) {
							if (this.isSubProcessRootToken) {
								if (!parent.hasEnded()) {
									parent.signal();
								}

							}
						}
						
					}
					
					
					
				}
			} else {
				if (this.isFreeToken()) {
					boolean rootTokenObj = false;
					for (TokenEntity tokenObj : this.getProcessInstance().getTokenList()) {
						if (tokenObj.isRoot()) {
							rootTokenObj = true;
						}
						if (tokenObj.isFreeToken() && !tokenObj.hasEnded()) {
							return;
						}
					}
					if (!rootTokenObj || this.getProcessInstance().getRootToken().hasEnded()) {
						processInstance.end();
					}

				}
			}

		}
	}
	
	
	

	public boolean hasActiveChildren() {
		boolean foundActiveChildToken = false;
		// 发现至少有一个子令牌,仍然活跃（没有结束.
		if (children != null) {

			for (String tokenKey : children.keySet()) {
				if (!foundActiveChildToken) {
					TokenEntity child = children.get(tokenKey);
					if (!child.hasEnded()) {
						foundActiveChildToken = true;
					}
				}
			}
		}
		return foundActiveChildToken;
	}

	public void resume() {
		isSuspended = false;

		// resumeTimers();
		// resumeMessages();
		resumeTaskInstances();

		// 恢复子令牌
		if (children != null) {
			for (String key : children.keySet()) {
				TokenEntity child = children.get(key);
				child.resume();
			}
		}
	}

	public void suspend() {
		isSuspended = true;

		// suspendTimers();
		// suspendMessages();
		suspendTaskInstances();

		// 暂停子令牌
		if (children != null) {
			for (String key : children.keySet()) {
				TokenEntity child = children.get(key);
				child.suspend();
			}
		}
	}

	void suspendTaskInstances() {
		TaskMgmtInstance taskMgmtInstance = (processInstance != null ? processInstance.getTaskMgmtInstance() : null);
		if (taskMgmtInstance != null) {
			taskMgmtInstance.suspend(this);
		}
	}

	void resumeTaskInstances() {
		TaskMgmtInstance taskMgmtInstance = (processInstance != null ? processInstance.getTaskMgmtInstance() : null);
		if (taskMgmtInstance != null) {
			taskMgmtInstance.resume(this);
		}
	}

	public Map<String, TokenEntity> getChildren() {
		return children;
	}

	public void setParent(TokenEntity parent) {
		this.parent = parent;
		if (parent == null) {
			this.parentTokenId = null;
		} else {
			this.parentTokenId = parent.getId();
		}

	}

	public void lock() {
		isLocked = true;
	}

	/**
	 * @see #lock()
	 */
	public void unlock() {
		isLocked = false;
	}

	public boolean getlock() {
		return this.isLocked;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getName() {
		return name;
	}

	public boolean hasEnded() {
		return (endTime != null);
	}

	public String toString() {
		return "Token(" + getFullName() + ")";
	}

	public boolean isRoot() {
		return (parent == null && !isFreeToken());
	}

	public String getFullName() {
		if (parent == null)
			return "/";
		if (parent.getParent() == null)
			return "/" + name;
		return parent.getFullName() + "/" + name;
	}

	public boolean isSubProcessRootToken() {
		return isSubProcessRootToken;
	}

	public void setSubProcessRootToken(boolean isSubProcessRootToken) {
		this.isSubProcessRootToken = isSubProcessRootToken;
	}

	public boolean isAbleToReactivateParent() {
		return isAbleToReactivateParent;
	}

	public void setAbleToReactivateParent(boolean isAbleToReactivateParent) {
		this.isAbleToReactivateParent = isAbleToReactivateParent;
	}

	public boolean isSuspended() {
		return isSuspended;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public Date getNodeEnterTime() {
		return nodeEnterTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setSuspended(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Map<String, Object> getPersistentState() {

		Map<String, Object> objectParam = new HashMap<String, Object>();
		// 令牌编号 String
		objectParam.put(TokenObjKey.TokenId().FullKey(), this.getId());
		// 令牌名称 String
		objectParam.put(TokenObjKey.Name().FullKey(), this.getName());
		// 令牌开始时间 Date
		objectParam.put(TokenObjKey.StartTime().FullKey(), this.getStartTime());
		// 令牌结束时间 Date
		objectParam.put(TokenObjKey.EndTime().FullKey(), this.getEndTime());
		// 令牌进入节点时间 Date
		objectParam.put(TokenObjKey.NodeEnterTime().FullKey(), this.getNodeEnterTime());
		// 是否可以激活父令牌标示 String
		objectParam.put(TokenObjKey.IsAbleToReactivateParent().FullKey(), String.valueOf(this.isAbleToReactivateParent()));

		// 是否是子流程根令牌
		objectParam.put(TokenObjKey.IsSubProcessRootToken().FullKey(), String.valueOf(this.isSubProcessRootToken()));

		// 令牌是否暂停 String
		objectParam.put(TokenObjKey.IsSuspended().FullKey(), String.valueOf(this.isSuspended()));
		// 令牌是否锁定 String
		objectParam.put(TokenObjKey.IsLocked().FullKey(), String.valueOf(this.getlock()));
		// 令牌所在节点编号 String
		objectParam.put(TokenObjKey.NodeId().FullKey(), this.getNodeId());
		// 流程实例编号 String
		objectParam.put(TokenObjKey.ProcessInstanceId().FullKey(), this.getProcessInstanceId());
		// 父令牌编号 String

		objectParam.put(TokenObjKey.ParentTokenId().FullKey(), this.getParentTokenId());

		// 流程实例编号 String
		objectParam.put(TokenObjKey.FreeToken().FullKey(), String.valueOf(this.isFreeToken()));
		objectParam.put(TokenObjKey.ParentFreeTokenId().FullKey(), this.getParentFreeTokenId());
		for (String key : extensionFields.keySet()) {
			objectParam.put(key, extensionFields.get(key));	
		}
		return objectParam;
	}

	public String getNodeId() {
		return this.nodeId;
	}

	public void setNodeIdWithoutCascade(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getProcessInstanceId() {
		return this.processInstanceId;
	}

	public void setProcessInstanceIdWithoutCascade(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public void setParentTokenIdWithoutCascade(String parentTokenId) {
		this.parentTokenId = parentTokenId;
	}

	public String getParentTokenId() {
		return this.parentTokenId;
	}

	public void setIdWithoutCascade(String id) {
		this.id = id;
	}

	public void setNameWithoutCascade(String name) {
		this.name = name;
	}

	public void setStartTimeWithoutCascade(Date startTime) {
		this.startTime = startTime;
	}

	public void setEndTimeWithoutCascade(Date endTime) {
		this.endTime = endTime;
	}

	public void setNodeEnterTimeWithoutCascade(Date nodeEnterTime) {
		this.nodeEnterTime = nodeEnterTime;
	}

	public void setSuspendedWithoutCascade(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}

	public void setlockWithoutCascade(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public void setAbleToReactivateParentWithoutCascade(boolean isAbleToReactivateParent) {
		this.isAbleToReactivateParent = isAbleToReactivateParent;
	}

	public void setSubProcessRootTokenWithoutCascade(boolean isSubProcessRootToken) {
		this.isSubProcessRootToken = isSubProcessRootToken;
	}

	public boolean isFreeToken() {
		return freeToken;
	}

	public void setFreeToken(boolean freeToken) {
		this.freeToken = freeToken;
	}

	public Token getParentFreeToken() {
		return parentFreeToken;
	}

	public void setParentFreeToken(TokenEntity parentFreeToken) {
		this.parentFreeTokenId = parentFreeToken.getId();
		this.parentFreeToken = parentFreeToken;

	}

	public String getParentFreeTokenId() {
		return parentFreeTokenId;
	}

	public void setParentFreeTokenId(String parentFreeTokenId) {
		this.parentFreeTokenId = parentFreeTokenId;
	}

	public Map<String, TokenEntity> getFreeChildren() {
		return freeChildren;
	}

	public void setFreeChildren(Map<String, TokenEntity> freeChildren) {
		this.freeChildren = freeChildren;
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

	public TokenEntity(Map<String, Object> entityMap){
		persistentInit(entityMap);
	}


	@Override
	public void persistentInit(Map<String, Object> entityMap) {
		for (String dataKey : entityMap.keySet()) {

			if (dataKey.equals(TokenObjKey.TokenId().DataBaseKey())) {
				this.id = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(TokenObjKey.Name().DataBaseKey())) {
				this.name = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(TokenObjKey.StartTime().DataBaseKey())) {
				this.startTime = StringUtil.getDate(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(TokenObjKey.IsSubProcessRootToken().DataBaseKey())) {
				this.isSubProcessRootToken = StringUtil.getBoolean(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(TokenObjKey.EndTime().DataBaseKey())) {
				this.endTime = StringUtil.getDate(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(TokenObjKey.NodeEnterTime().DataBaseKey())) {
				this.nodeEnterTime = StringUtil.getDate(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(TokenObjKey.IsAbleToReactivateParent().DataBaseKey())) {
				this.isAbleToReactivateParent = StringUtil.getBoolean(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(TokenObjKey.IsSuspended().DataBaseKey())) {
				this.isSuspended = StringUtil.getBoolean(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(TokenObjKey.IsLocked().DataBaseKey())) {
				this.isLocked = StringUtil.getBoolean(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(TokenObjKey.NodeId().DataBaseKey())) {
				this.nodeId = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(TokenObjKey.ProcessInstanceId().DataBaseKey())) {

				this.processInstanceId = StringUtil.getString(entityMap.get(dataKey));
				continue;

			}
			if (dataKey.equals(TokenObjKey.ParentTokenId().DataBaseKey())) {
				this.parentTokenId = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(TokenObjKey.FreeToken().DataBaseKey())) {
				this.freeToken = StringUtil.getBoolean(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(TokenObjKey.ParentFreeTokenId().DataBaseKey())) {
				this.parentFreeTokenId = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			this.addExtensionField(dataKey, entityMap.get(dataKey));

		}

	}

	@Override
	public Map<String, Object> getPersistentDbMap() {
		// 构建查询参数
				Map<String, Object> objectParam = new HashMap<String, Object>();
				// 令牌编号 String
				objectParam.put("TOKEN_ID", this.getId());
				// 令牌名称 String
				objectParam.put("NAME", this.getName());
				// 令牌开始时间 Date
				objectParam.put("START_TIME", this.getStartTime());
				// 令牌结束时间 Date
				objectParam.put("END_TIME", this.getEndTime());
				// 令牌进入节点时间 Date
				objectParam.put("NODEENTER_TIME", this.getNodeEnterTime());
				// 是否可以激活父令牌标示 String
				objectParam.put("ISABLETOREACTIVATEPARENT", String.valueOf(this.isAbleToReactivateParent()));

				// 是否是子流程根令牌
				objectParam.put("ISSUBPROCESSROOTTOKEN", String.valueOf(this.isSubProcessRootToken()));

				// 令牌是否暂停 String
				objectParam.put("ISSUSPENDED", String.valueOf(this.isSuspended()));
				// 令牌是否锁定 String
				objectParam.put("TOKEN_LOCK", String.valueOf(this.getlock()));
				// 令牌所在节点编号 String
				objectParam.put("NODE_ID", this.getFlowNode().getId());
				// 流程实例编号 String
				objectParam.put("PROCESSINSTANCE_ID", this.getProcessInstance().getId());
				// 父令牌编号 String

				if (this.getParent() != null) {
					objectParam.put("PARENT_ID", this.getParent().getId());
				}
				// 流程实例编号 String
				objectParam.put("FREETOKEN", String.valueOf(this.isFreeToken()));
				objectParam.put("PARENT_FREETOKEN_ID", this.getParentFreeTokenId());

				return objectParam;
	}
}
