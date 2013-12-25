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
package com.founder.fix.fixflow.core.impl.runtime;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.LoopCharacteristics;
import org.eclipse.bpmn2.MultiInstanceLoopCharacteristics;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.db.AbstractPersistentObject;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.runtime.Token;
import com.founder.fix.fixflow.core.task.TaskMgmtInstance;

public class TokenEntity extends AbstractPersistentObject<TokenEntity> implements Token {

	private static final long serialVersionUID = 1L;
	public static final String RULE_GET_TOKEN_PERSISTENT_STATE = "getTokenPersistentState";
	public static final String RULE_GET_TOKEN_PERSISTENT_DBMAP = "getTokenPersistentDbMap";
	public static final String RULE_GET_TOKEN_CLONE = "tokenClone";

	// 持久化字段
	protected String id;
	protected String name;
	protected String processInstanceId;
	protected String nodeId;
	protected String parentTokenId;
	protected String parentFreeTokenId;
	protected Date startTime;
	protected Date endTime;
	protected Date nodeEnterTime;
	protected Date archiveTime;
	protected boolean isLocked = false;
	protected boolean isSuspended = false;
	protected boolean isSubProcessRootToken = false;
	protected boolean isAbleToReactivateParent = true;
	protected boolean freeToken;

	// 构造函数 //////////////////////

	// 构造函数
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
		setProcessInstance(parent.getProcessInstance());
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

	// /定义对象
	/**
	 * 令牌所在流程实例
	 */
	protected ProcessInstanceEntity processInstance;

	/**
	 * 令牌所在的节点
	 */
	protected FlowNode flowNode;

	/**
	 * 父令牌
	 */
	protected TokenEntity parent;

	/**
	 * 父令牌(如果有则这个令牌为自由令牌)
	 */
	protected TokenEntity parentFreeToken;

	/**
	 * 子令牌集合
	 */
	protected Map<String, TokenEntity> children;

	

	// //get和set方法

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessInstanceId() {
		return this.processInstanceId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeId() {
		return this.nodeId;
	}

	public void setParentTokenId(String parentTokenId) {
		this.parentTokenId = parentTokenId;
	}

	public String getParentTokenId() {
		return this.parentTokenId;
	}

	public void setParentFreeTokenId(String parentFreeTokenId) {
		this.parentFreeTokenId = parentFreeTokenId;
	}

	public String getParentFreeTokenId() {
		return parentFreeTokenId;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setStartTimeWithoutCascade(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setEndTimeWithoutCascade(Date endTime) {
		this.endTime = endTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setNodeEnterTime(Date date) {
		this.nodeEnterTime = date;
	}

	public Date getNodeEnterTime() {
		return nodeEnterTime;
	}

	public void setArchiveTime(Date archiveTime) {
		this.archiveTime = archiveTime;
	}

	public Date getArchiveTime() {
		return archiveTime;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public void setLockedString(String isLocked) {
		if (StringUtil.isNotEmpty(isLocked)) {
			this.isLocked = StringUtil.getBoolean(isLocked);
		}
	}

	public boolean getlock() {
		return this.isLocked;
	}

	public void setSuspended(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}

	public void setSuspendedString(String isSuspended) {
		if (StringUtil.isNotEmpty(isSuspended)) {
			this.isSuspended = StringUtil.getBoolean(isSuspended);
		}
	}

	public boolean isSuspended() {
		return isSuspended;
	}

	public void setSubProcessRootToken(boolean isSubProcessRootToken) {
		this.isSubProcessRootToken = isSubProcessRootToken;
	}

	public void setSubProcessRootTokenString(String isSubProcessRootToken) {
		if (StringUtil.isNotEmpty(isSubProcessRootToken)) {
			this.isSubProcessRootToken = StringUtil.getBoolean(isSubProcessRootToken);
		}
	}

	public boolean isSubProcessRootToken() {
		return isSubProcessRootToken;
	}

	public void setAbleToReactivateParent(boolean isAbleToReactivateParent) {
		this.isAbleToReactivateParent = isAbleToReactivateParent;
	}

	public void setAbleToReactivateParentString(String isAbleToReactivateParent) {
		if (StringUtil.isNotEmpty(isAbleToReactivateParent)) {
			this.isAbleToReactivateParent = StringUtil.getBoolean(isAbleToReactivateParent);
		}
	}

	public boolean isAbleToReactivateParent() {
		return isAbleToReactivateParent;
	}

	public void setFreeToken(boolean freeToken) {
		this.freeToken = freeToken;
	}

	public void setFreeTokenString(String freeToken) {
		if (StringUtil.isNotEmpty(freeToken)) {
			this.freeToken = StringUtil.getBoolean(freeToken);
		}
	}

	public boolean isFreeToken() {
		return freeToken;
	}

	// 对象get set方法
	public void setFlowNode(FlowNode flowNode) {
		this.flowNode = flowNode;
		if (flowNode == null) {
			this.nodeId = null;
		} else {
			this.nodeId = flowNode.getId();
		}
	}

	public FlowNode getFlowNode() {
		if (this.flowNode == null) {

			if (StringUtil.isNotEmpty(this.nodeId)) {
				ProcessInstanceEntity processInstanceEntity = getProcessInstance();
				if (processInstanceEntity == null) {
					return null;
				}
				ProcessDefinitionBehavior processDefinitionBehavior = getProcessInstance().getProcessDefinition();
				if (processDefinitionBehavior == null) {
					return null;
				}
				this.flowNode = (FlowNode) processDefinitionBehavior.getDefinitions().getElement(this.nodeId);
				return this.flowNode;
			}

			return null;

		} else {
			return flowNode;
		}

	}

	public void setProcessInstance(ProcessInstanceEntity processInstance) {
		this.processInstance = processInstance;
		if (processInstance == null) {
			this.processInstanceId = null;
		} else {
			this.processInstanceId = processInstance.getId();
		}
	}

	public ProcessInstanceEntity getProcessInstance() {

		if (this.processInstance == null) {

			if (StringUtil.isNotEmpty(this.processInstanceId)) {

				this.processInstance = Context.getCommandContext().getProcessInstanceManager().findProcessInstanceById(this.processInstanceId);
				
				if(this.processInstance!=null){
					
					if(StringUtil.isNotEmpty(this.parentTokenId)&&!this.freeToken){
						this.processInstance.setRootToken(this);
					}					
					return this.processInstance;
				}
				
				
			}
			return null;

		}

		return this.processInstance;
	}

	public void setParent(TokenEntity parent) {
		this.parent = parent;
		if (parent == null) {
			this.parentTokenId = null;
		} else {
			this.parentTokenId = parent.getId();
		}
	}

	public TokenEntity getParent() {

		if (this.parent == null) {

			if (StringUtil.isNotEmpty(this.parentTokenId)) {

				this.parent = Context.getCommandContext().getTokenManager().findTokenById(this.parentTokenId);
				return this.parent;

			}
			return null;

		}

		return this.parent;
	}

	public void setParentFreeToken(TokenEntity parentFreeToken) {
		this.parentFreeToken = parentFreeToken;
		if (parentFreeToken != null) {
			this.parentFreeTokenId = parentFreeToken.getId();
		}

	}

	public Token getParentFreeToken() {

		if (this.parentFreeToken == null) {

			if (StringUtil.isNotEmpty(this.parentFreeTokenId)) {

				this.parentFreeToken = Context.getCommandContext().getTokenManager().findTokenById(this.parentFreeTokenId);
				return this.parentFreeToken;

			}
			return null;

		}

		return this.parentFreeToken;

	}

	

	// 令牌流转
	// ////////////////////////////////////////////////////////

	public void signal() {
		ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(this);
		signal(executionContext);
	}

	public void signalKillChildMoveParentToken(FlowNode flowNode, FlowNode timeOutNode) {

		TokenEntity tokenParent = this.getParent();
		if (tokenParent == null) {
			tokenParent = this;
		}
		tokenParent.terminationChildToken();
		ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(tokenParent);
		if (timeOutNode != null) {
			executionContext.setTimeOutNode(timeOutNode);
		}
		flowNode.leave(executionContext);
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
			FlowNode signalNode = this.getFlowNode();
	
			signalNode.leave(executionContext);

		} finally {

		}
	}
	
	public Map<String, TokenEntity> getChildren() {
		
		if(this.children==null){
			this.children= new HashMap<String, TokenEntity>();
			
			List<TokenEntity> tokenEntities=Context.getCommandContext().getTokenManager().findTokenByParentId(this.getId());
			if(tokenEntities!=null){
				for (TokenEntity tokenEntity : tokenEntities) {
					
					tokenEntity.setProcessInstance(getProcessInstance());
					
					this.children.put(tokenEntity.getId(),tokenEntity);
					
				}
				return this.children;
			}
			
		}
		return children;
	}



	public void addChild(TokenEntity token) {
		
		if(token!=null){
			getChildren().put(token.getId(), token);
		}
		
		

	}

	public boolean hasParent() {
		return (StringUtil.isNotEmpty(this.parentTokenId));
	}

	public boolean hasChild(String name) {
		return (getChildren() != null ? getChildren().containsKey(name) : false);
	}

	public boolean hasChildRecursive(String tokenId) {
		boolean hasChildRecursive = false;
		if (getChildren() != null) {
			if (!getChildren().containsKey(tokenId)) {
				for (String tokenChildrenKey : getChildren().keySet()) {
					hasChildRecursive = getChildren().get(tokenChildrenKey).hasChildRecursive(tokenId);
					if (hasChildRecursive) {
						return true;
					}
				}
			} else {
				return true;
			}
		}

		return hasChildRecursive;
	}

	public Token getChild(String id) {
		
		return getChildren().get(id);
	}

	public void end() {
		end(true);
	}

	public void end(boolean verifyParentTermination) {
		// 如果令牌已经有结束时间则不执行令牌结束方法
		if (endTime == null) {
			// 结束令牌.使他不能再启动父令牌
			isAbleToReactivateParent = false;
			// 结束日期的标志，表明此令牌已经结束。
			this.endTime = new Date();
			// 结束所有子令牌
			if (getChildren() != null) {
				for (String tokenKey : getChildren().keySet()) {
					TokenEntity child = getChildren().get(tokenKey);
					if (!child.hasEnded()) {
						child.end();
					}
				}
			}
			// 记录日志用
			if (getParent() != null) {
				// 添加日志
				// parent.addLog(new TokenEndLog(this));
			}
			// 移除这个将要结束的令牌上的所有未完成的任务
			TaskMgmtInstance taskMgmtInstance = (TaskMgmtInstance) this.getProcessInstance().getTaskMgmtInstance();
			for (TaskInstanceEntity taskInstance : taskMgmtInstance.getTaskInstanceEntitys(this)) {
				if (!taskInstance.hasEnded()) {
					taskInstance.customEnd(null, null);
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
			if (getChildren() != null) {

				for (String tokenKey : getChildren().keySet()) {
					TokenEntity child = getChildren().get(tokenKey);
					if (!child.hasEnded()) {
						// 结束令牌.使他不能再启动父令牌
						child.setAbleToReactivateParent(false);
						// 移除这个将要结束的令牌上的所有未完成的任务
						removeTaskInstanceSynchronization(child, null, null, null, null, null, null);
						child.terminationChildToken();
						// 设置结束日起
						// 结束日期的标志，表明此令牌已经结束。
						child.setEndTime(new Date());
					}
				}
			}
		}
	}

	public void terminationChildTokenWithTask(String taskommandType, String taskommandName, String taskComment, String assigneeId, String agent,
			String admin) {
		// 如果令牌已经有结束时间则不执行令牌结束方法
		if (endTime == null) {
			// 结束所有子令牌
			if (getChildren() != null) {
				for (String tokenKey : getChildren().keySet()) {
					TokenEntity child = getChildren().get(tokenKey);
					if (!child.hasEnded()) {
						// 结束令牌.使他不能再启动父令牌
						child.setAbleToReactivateParent(false);
						// 移除这个将要结束的令牌上的所有未完成的任务
						removeTaskInstanceSynchronization(child, taskommandType, taskommandName, taskComment, assigneeId, agent, admin);
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

	private void removeTaskInstanceSynchronization(TokenEntity token, String taskCommandType, String taskCommandName, String taskComment,
			String assigneeId, String agent, String admin) {
		String assigneeIdObj = assigneeId;
		String agentObj = null;
		String adminObj = null;
		if (agent != null) {
			assigneeIdObj = agent;
			agentObj = Authentication.getAuthenticatedUserId();
		}
		if (admin != null) {
			assigneeIdObj = admin;
			adminObj = admin;
		}
		TaskMgmtInstance tmi = getTaskMgmtInstance(token);
		for (TaskInstanceEntity taskInstance : tmi.getTaskInstanceEntitys(token)) {
			if (!taskInstance.hasEnded()) {
				taskInstance.customEnd(null, null);
				if (assigneeId != null) {
					taskInstance.setAssignee(assigneeIdObj);
				}
				taskInstance.setAgent(agentObj);
				taskInstance.setAdmin(adminObj);
				// taskInstance.setDraft(false);
				taskInstance.setTaskComment(taskComment);// taskComment
			}
		}
	}

	public void removeTaskInstanceSynchronization(String taskCommandType, String taskCommandName, String taskComment, String assigneeId,
			String agent, String admin) {
		removeTaskInstanceSynchronization(this, taskCommandType, taskCommandName, taskComment, assigneeId, agent, admin);
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
			getProcessInstance().end();
		} else {
			if (getParent() != null) {
				// 下面这句话是用来当存在分支时 一个分支走到结束另一个分支没结束的时候,不能结束他的父令牌
				// 只有当到达结束节点的令牌的同级分支都结束才能结束父令牌
				if (!getParent().hasActiveChildren()) {
					// 推动父令牌向后执行
					if (this.isSubProcessRootToken) {
						if (!getParent().hasEnded()) {
							getParent().signal();
						}
					} else {
						getParent().end();
					}
				} else {
					// 子流程多实例的时候 每个子流程结束的时候去触发验证完成条件
					if (getParent().getFlowNode() instanceof Activity) {
						Activity activity = (Activity) getParent().getFlowNode();
						LoopCharacteristics loopCharacteristics = activity.getLoopCharacteristics();
						if (loopCharacteristics instanceof MultiInstanceLoopCharacteristics) {
							if (this.isSubProcessRootToken) {
								if (!getParent().hasEnded()) {
									getParent().signal();
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
						getProcessInstance().end();
					}
				}
			}
		}
	}

	public boolean hasActiveChildren() {
		boolean foundActiveChildToken = false;
		// 发现至少有一个子令牌,仍然活跃（没有结束.
		if (getChildren() != null) {

			for (String tokenKey : getChildren().keySet()) {
				if (!foundActiveChildToken) {
					TokenEntity child = getChildren().get(tokenKey);
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
		if (getChildren() != null) {
			for (String key : getChildren().keySet()) {
				TokenEntity child = getChildren().get(key);
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
		if (getChildren() != null) {
			for (String key : getChildren().keySet()) {
				TokenEntity child = getChildren().get(key);
				child.suspend();
			}
		}
	}

	void suspendTaskInstances() {
		TaskMgmtInstance taskMgmtInstance = (getProcessInstance() != null ? getProcessInstance().getTaskMgmtInstance() : null);
		if (taskMgmtInstance != null) {
			taskMgmtInstance.suspend(this);
		}
	}

	void resumeTaskInstances() {
		TaskMgmtInstance taskMgmtInstance = (getProcessInstance() != null ? getProcessInstance().getTaskMgmtInstance() : null);
		if (taskMgmtInstance != null) {
			taskMgmtInstance.resume(this);
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

	public boolean hasEnded() {
		return (endTime != null);
	}

	public String toString() {
		return "Token(" + getFullName() + ")";
	}

	public boolean isRoot() {
		return (getParent() == null && !isFreeToken());
	}

	public String getFullName() {
		if (getParent() == null)
			return "/";
		if (getParent() .getParent() == null)
			return "/" + name;
		return getParent() .getFullName() + "/" + name;
	}

	@Override
	public String getCloneRuleId() {
		return RULE_GET_TOKEN_CLONE;
	}

	@Override
	public String getPersistentDbMapRuleId() {
		return RULE_GET_TOKEN_PERSISTENT_DBMAP;
	}

	@Override
	public String getPersistentStateRuleId() {
		return RULE_GET_TOKEN_PERSISTENT_STATE;
	}

	// 过时方法
	public void setIdWithoutCascade(String id) {
		this.id = id;
	}

	public void setNameWithoutCascade(String name) {
		this.name = name;
	}

	public void setProcessInstanceIdWithoutCascade(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public void setNodeIdWithoutCascade(String nodeId) {
		this.nodeId = nodeId;
	}

	public void setParentTokenIdWithoutCascade(String parentTokenId) {
		this.parentTokenId = parentTokenId;
	}

	public void setNodeEnterTimeWithoutCascade(Date nodeEnterTime) {
		this.nodeEnterTime = nodeEnterTime;
	}

	public void setlockWithoutCascade(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public void setSuspendedWithoutCascade(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}

	public void setSubProcessRootTokenWithoutCascade(boolean isSubProcessRootToken) {
		this.isSubProcessRootToken = isSubProcessRootToken;
	}

	public void setAbleToReactivateParentWithoutCascade(boolean isAbleToReactivateParent) {
		this.isAbleToReactivateParent = isAbleToReactivateParent;
	}
	
	// 作废方法
	
	/**
	 * 自由子令牌集合
	 */
	protected Map<String, TokenEntity> freeChildren = new HashMap<String, TokenEntity>();
	
	public Map<String, TokenEntity> getFreeChildren() {

		return freeChildren;
	}

	public void setFreeChildren(Map<String, TokenEntity> freeChildren) {
		this.freeChildren = freeChildren;
	}
}
