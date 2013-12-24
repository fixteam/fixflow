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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.CallActivity;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.StartEvent;

import com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef;
import com.founder.fix.bpmn2extensions.fixflow.DataVariableMapping;
import com.founder.fix.bpmn2extensions.fixflow.SubProcessToDataSourceMapping;
import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.context.ContextInstance;
import com.founder.fix.fixflow.core.event.BaseElementEvent;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.CallActivityBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.StartEventBehavior;
import com.founder.fix.fixflow.core.impl.command.QueryVariablesCommand;
import com.founder.fix.fixflow.core.impl.context.ContextInstanceImpl;
import com.founder.fix.fixflow.core.impl.datavariable.DataVariableMgmtInstance;
import com.founder.fix.fixflow.core.impl.db.AbstractPersistentObject;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.persistence.ProcessInstanceManager;
import com.founder.fix.fixflow.core.impl.task.TaskCommandType;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.ClockUtil;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.ProcessInstanceType;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskInstanceType;
import com.founder.fix.fixflow.core.task.TaskMgmtInstance;

public class ProcessInstanceEntity extends AbstractPersistentObject<ProcessInstanceEntity> implements ProcessInstance {

	// Field　字段
	// //////////////////////////////////////////////////////

	/**
	 * 
	 */
	private static final long serialVersionUID = -7192069864977069735L;

	/**
	 * 该值缓存 '{@link #getId() <em>Id</em>}' 引用. <!-- 开始-用户-文档 -->
	 * <p>
	 * 流程实例的编号 在创建新实例的时候赋予(GUID) 在从持久化层读取出来的时候赋值
	 * </p>
	 * <!-- 结束-用户-文档 -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */

	// 持久化字段
	protected String id = null;

	protected String subject;

	protected String processDefinitionId;

	protected String processDefinitionKey;

	protected String definitionId;

	protected String rootTokenId;

	protected String parentProcessInstanceTokenId;

	protected String parentProcessInstanceId;

	protected String startAuthor;

	protected String initiator;

	protected String bizKey;

	protected Date startTime;

	protected Date endTime;

	protected Date updateTime;

	protected Date archiveTime;

	protected ProcessInstanceType instanceType;

	protected String processLocation;

	protected boolean isSuspended = false;

	// set和get方法
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getProcessDefinitionId() {
		return this.processDefinitionId;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public String getProcessDefinitionKey() {
		return this.processDefinitionKey;
	}

	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}

	public String getDefinitionId() {
		return this.definitionId;
	}

	public void setRootTokenId(String rootTokenId) {
		this.rootTokenId = rootTokenId;
	}

	public String getRootTokenId() {
		return this.rootTokenId;
	}

	public void setParentProcessInstanceId(String parentProcessInstanceId) {
		this.parentProcessInstanceId = parentProcessInstanceId;
	}

	public String getParentProcessInstanceId() {
		return parentProcessInstanceId;
	}

	public void setParentProcessInstanceTokenId(String parentProcessInstanceTokenId) {
		this.parentProcessInstanceTokenId = parentProcessInstanceTokenId;
	}

	public String getParentProcessInstanceTokenId() {
		return parentProcessInstanceTokenId;
	}

	public String getStartAuthor() {
		return startAuthor;
	}

	public void setStartAuthor(String startAuthor) {
		this.startAuthor = startAuthor;
	}

	public String getInitiator() {
		return initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	public String getBizKey() {
		return bizKey;
	}

	public void setBizKey(String bizKey) {
		this.bizKey = bizKey;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getArchiveTime() {
		return archiveTime;
	}

	public void setArchiveTime(Date archiveTime) {
		this.archiveTime = archiveTime;
	}

	public ProcessInstanceType getInstanceType() {
		return instanceType;
	}

	public void setInstanceType(ProcessInstanceType instanceType) {
		this.instanceType = instanceType;
	}

	public String getProcessLocation() {
		return processLocation;
	}

	public void setProcessLocation(String processLocation) {
		this.processLocation = processLocation;
	}

	public void setSuspended(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}

	public boolean getSuspended() {
		return this.isSuspended;
	}

	// 构造函数 ///////////////////////////////
	
	public ProcessInstanceEntity() {

		this.taskMgmtInstance = ProcessObjectFactory.FACTORYINSTANCE.createTaskMgmtInstance();
		
		this.taskMgmtInstance.setProcessInstance(this);

		this.dataVariableMgmtInstance = new DataVariableMgmtInstance(this);

		this.contextInstance = new ContextInstanceImpl(this);
		

	}

	// Constructor 构造函数
	// /////////////////////////////////////////////////////
	public ProcessInstanceEntity(ProcessDefinitionBehavior processDefinition, String businessKey) {
		this();
		
		if (processDefinition == null) {
			throw new FixFlowException("你不能通过一个空的流程定义对象来创建流程实例");
		}
		
		// 设置业务关联键
		this.bizKey = businessKey;
		// 将外部传入的流程定义放置到流程实例里
		this.processDefinition = processDefinition;
		// 设置流程实例的根令牌

		this.rootToken = new TokenEntity(this);
		this.rootTokenId = this.rootToken.getId();
		this.processDefinitionId = processDefinition.getProcessDefinitionId();
		this.processDefinitionKey = processDefinition.getProcessDefinitionKey();
		this.definitionId = processDefinition.getDefinitions().getId();
		// 设置流程实例的编号,通过静态方法获得Guid
		this.id = GuidUtil.CreateGuid();

		
		
	}
	
	/**
	 * 子流程使用构造函数
	 * 
	 * @param processDefinition
	 * @param businessKey
	 * @param parentProcessInstance
	 * @param parentProcessInstanceToken
	 */
	public ProcessInstanceEntity(ProcessDefinitionBehavior processDefinition, String businessKey, ProcessInstanceEntity parentProcessInstance,
			TokenEntity parentProcessInstanceToken) {
		this(processDefinition, businessKey);
		this.parentProcessInstance = parentProcessInstance;
		this.parentProcessInstanceId = parentProcessInstance.getId();
		this.parentProcessInstanceToken = parentProcessInstanceToken;
		this.parentProcessInstanceTokenId = parentProcessInstanceToken.getId();
		
		
	}

	// 对象字段 /////////////////////

	// 流程定义
	protected ProcessDefinitionBehavior processDefinition;

	// 任务管理器
	protected TaskMgmtInstance taskMgmtInstance;

	// 变量管理器
	protected DataVariableMgmtInstance dataVariableMgmtInstance;

	// 实例内容管理器
	protected ContextInstance contextInstance;

	// 父流程
	protected ProcessInstanceEntity parentProcessInstance;

	// 根令牌
	protected TokenEntity rootToken;

	// 父流程实例令牌
	protected TokenEntity parentProcessInstanceToken;

	// 流程实例令牌集合
	protected List<TokenEntity> tokenList;

	// 对象字段 get set //////////////////////

	/**
	 * 获取流程定义
	 */
	public ProcessDefinitionBehavior getProcessDefinition() {
		if (this.processDefinition == null) {
			this.processDefinition = Context.getCommandContext().getProcessDefinitionManager()
					.findLatestProcessDefinitionById(this.processDefinitionId);
		}
		return this.processDefinition;
	}

	/**
	 * 设置流程实例所使用的流程定义
	 * 
	 * @param processDefinition
	 */
	public void setProcessDefinition(ProcessDefinitionBehavior processDefinition) {
		this.processDefinitionId = processDefinition.getProcessDefinitionId();
		this.processDefinitionKey = processDefinition.getProcessDefinitionKey();
		this.processDefinition = processDefinition;
	}

	/**
	 * 获取任务管理器
	 * 
	 * @return
	 */
	public TaskMgmtInstance getTaskMgmtInstance() {
		return this.taskMgmtInstance;
	}

	/**
	 * 获取变量管理器
	 * 
	 * @return
	 */
	public DataVariableMgmtInstance getDataVariableMgmtInstance() {
		return this.dataVariableMgmtInstance;
	}

	/**
	 * 获取实例内容管理器
	 * 
	 * @return
	 */
	public ContextInstance getContextInstance() {
		return this.contextInstance;
	}

	/**
	 * 获取父流程实例
	 * 
	 * @return
	 */
	public ProcessInstanceEntity getParentProcessInstance() {
		if (this.parentProcessInstance == null) {

			if (StringUtil.isNotEmpty(this.parentProcessInstanceId)) {
				ProcessInstanceManager processInstanceManager = Context.getCommandContext().getProcessInstanceManager();
				ProcessInstanceEntity processInstanceImpl = processInstanceManager.findProcessInstanceById(this.parentProcessInstanceId, null);
				if (processInstanceImpl != null) {
					this.parentProcessInstance = processInstanceImpl;
				}
				return processInstanceImpl;
			} else {
				return null;
			}

		}
		return this.parentProcessInstance;
	}

	/**
	 * 获取根令牌
	 * 
	 * @return
	 */
	public TokenEntity getRootToken() {

		if (this.rootToken == null) {
			if (StringUtil.isNotEmpty(this.rootTokenId)) {
				TokenEntity rootTokenEntityObj = Context.getCommandContext().getTokenManager().findTokenById(this.rootTokenId);
				this.rootToken = rootTokenEntityObj;
				return this.rootToken;
			}
			return null;
		}
		return rootToken;
	}

	/**
	 * 获取父流程实例令牌对象
	 * 
	 * @return
	 */
	public TokenEntity getParentProcessInstanceToken() {

		if (this.parentProcessInstanceToken == null) {

			if (StringUtil.isNotEmpty(this.parentProcessInstanceTokenId)) {

				TokenEntity parentProcessInstanceTokenObj = Context.getCommandContext().getTokenManager()
						.findTokenById(this.parentProcessInstanceTokenId);
				this.parentProcessInstanceToken = parentProcessInstanceTokenObj;
				return this.parentProcessInstanceToken;
			}
			return null;

		}
		return this.parentProcessInstanceToken;
	}

	/**
	 * 获取流程实例的令牌集合
	 * 
	 * @return
	 */
	public List<TokenEntity> getTokenList() {

		if (this.tokenList == null) {

			List<TokenEntity> tokenEntities = Context.getCommandContext().getTokenManager().findTokensByProcessInstanceId(this.id);
			this.tokenList = tokenEntities;
			return tokenEntities;
		}

		return tokenList;
	}

	/**
	 * 获取流程实例的令牌编号集合
	 */
	public List<String> getTokenIdList() {
		List<String> tokenIdList = new ArrayList<String>();
		List<TokenEntity> tokenEntities = getTokenList();
		if (tokenEntities != null) {

			for (TokenEntity tokenEntity : tokenEntities) {
				tokenIdList.add(tokenEntity.getId());
			}

		}
		return tokenIdList;
	}

	/**
	 * 获取流程实例令牌Map
	 * 
	 * @return
	 */
	public Map<String, TokenEntity> getTokenMap() {
		Map<String, TokenEntity> tokenMap = new HashMap<String, TokenEntity>();

		List<TokenEntity> tokenEntities = getTokenList();
		if (tokenEntities != null) {
			for (TokenEntity tokenEntity : tokenEntities) {
				tokenMap.put(tokenEntity.getId(), tokenEntity);
			}
		}

		return tokenMap;
	}

	
	
	// 遗留方法 ////////////////////
	

	public void start() {
		if (this.getRootToken().getFlowNode() == null) {
			// 设置流程实例的开始时间
			this.startTime = new Date();

			ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(this.getRootToken());

			processDefinition.getTaskSubject().getExpressionValue();
			String processInstanceSubjectExpression = processDefinition.getTaskSubject().getExpressionValue();

			try {
				Object result = ExpressionMgmt.execute(processInstanceSubjectExpression, executionContext);
				if (result != null) {

					this.setSubject(StringUtil.getString(result));

				} else {
					this.setSubject(processDefinition.getName());
				}
			} catch (Exception e) {
				e.printStackTrace();
				this.setSubject(processDefinition.getName());
			}

			processDefinition.getStartElement().enter(executionContext);
		} else {
			throw new FixFlowException("流程实例已经启动!");
		}

	}

	public void start(FlowNode flowNode) {
		if (this.getRootToken().getFlowNode() == null) {
			// 设置流程实例的开始时间
			this.startTime = new Date();

			ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(this.getRootToken());

			processDefinition.getTaskSubject().getExpressionValue();
			String processInstanceSubjectExpression = processDefinition.getTaskSubject().getExpressionValue();

			try {
				Object result = ExpressionMgmt.execute(processInstanceSubjectExpression, executionContext);
				if (result != null) {

					this.setSubject(StringUtil.getString(result));

				} else {
					this.setSubject(processDefinition.getName());
				}
			} catch (Exception e) {
				e.printStackTrace();
				this.setSubject(processDefinition.getName());
			}

			// 触发流程启动事件
			// fixflowDefinition.fireEvent(Event.EVENTTYPE_PROCESS_START,
			// executionContext);
			// 将令牌设置到开始节点
			flowNode.enter(executionContext);
		} else {
			throw new FixFlowException("流程实例已经启动!");
		}
	}

	public void noneStart() throws Exception {
		if (this.getRootToken().getFlowNode() == null) {
			// 设置流程实例的开始时间
			this.startTime = new Date();

			ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(this.getRootToken());
			processDefinition.getTaskSubject().getExpressionValue();
			String processInstanceSubjectExpression = processDefinition.getTaskSubject().getExpressionValue();

			try {
				Object result = ExpressionMgmt.execute(processInstanceSubjectExpression, executionContext);
				if (result != null) {

					this.setSubject(StringUtil.getString(result));

				} else {
					this.setSubject(processDefinition.getName());
				}
			} catch (Exception e) {
				e.printStackTrace();
				this.setSubject(processDefinition.getName());
			}

			// 触发流程启动事件
			// fixflowDefinition.fireEvent(Event.EVENTTYPE_PROCESS_START,
			// executionContext);
			// 将令牌设置到开始节点
			processDefinition.getNoneStartEvent().enter(executionContext);
		} else {
			throw new FixFlowException("流程实例已经启动!");
		}

	}

	public void timeStart(String nodeId) throws Exception {
		if (this.getRootToken().getFlowNode() == null) {
			// 设置流程实例的开始时间
			this.startTime = new Date();

			ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(this.getRootToken());

			String processInstanceSubjectExpression = "";
			if (processDefinition.getTaskSubject() != null) {
				processInstanceSubjectExpression = processDefinition.getTaskSubject().getExpressionValue();
			}

			try {
				Object result = ExpressionMgmt.execute(processInstanceSubjectExpression, executionContext);
				if (result != null) {

					this.setSubject(StringUtil.getString(result));

				} else {
					this.setSubject(processDefinition.getName());
				}
			} catch (Exception e) {
				e.printStackTrace();
				this.setSubject(processDefinition.getName());
			}

			// 触发流程启动事件
			// fixflowDefinition.fireEvent(Event.EVENTTYPE_PROCESS_START,
			// executionContext);
			// 将令牌设置到开始节点
			processDefinition.getTimeStartEvent(nodeId).enter(executionContext);
		} else {
			throw new FixFlowException("流程实例已经启动!");
		}

	}

	public void signal() throws Exception {
		if (hasEnded()) {
			throw new FixFlowException("根令牌已经结束!");
		}
		rootToken.signal();
	}

	public void addTokenList(TokenEntity token) {
		if (tokenList == null) {
			tokenList = new ArrayList<TokenEntity>();
		}
		tokenList.add(token);

	}

	public void end() {
		rootToken.end();
		if (endTime == null) {
			// 设置流程结束时间
			endTime = new Date();
			ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(rootToken);
			// 插入流程结束任务
			if (this.getProcessDefinition().getStartElement() != null && this.getProcessDefinition().getStartElement() instanceof StartEvent) {
				// 插入流程结束记录
				StartEventBehavior startEventBehavior = (StartEventBehavior) this.getProcessDefinition().getStartElement();
				if (startEventBehavior.isPersistence()) {
					createEndEventTask(executionContext);
				}
			}
			// createEndEventTask(executionContext);
			if (this.getParentProcessInstanceTokenId() != null && this.getParentProcessInstanceToken() != null) {
				FlowNode flowNode = this.getParentProcessInstanceToken().getFlowNode();
				if (flowNode instanceof CallActivity) {
					CallActivityBehavior callActivityBehavior = (CallActivityBehavior) flowNode;
					startParentProcessInstance(this.getParentProcessInstanceToken(), callActivityBehavior);
				}
			}
			// 更新实例状态为正常完成
			this.instanceType = ProcessInstanceType.COMPLETE;
			// 强制结束没有结束的子流程
			endSubProcess();
		}
	}

	/**
	 * 终止所有子流程
	 */
	public void terminationSubProcess() {
		// 判断子流程是否都结束，没结束都强制结束子流程。
		List<ProcessInstanceEntity> processInstanceEntities = this.getSubProcessInstanceList();
		if (processInstanceEntities.size() > 0) {
			for (ProcessInstanceEntity subProcessInstanceEntity : processInstanceEntities) {
				subProcessInstanceEntity.termination();
				try {
					Context.getCommandContext().getProcessInstanceManager().saveProcessInstance(subProcessInstanceEntity);
				} catch (Exception e) {
					e.printStackTrace();
					throw new FixFlowException("子流程强制终止的时候出现错误", e);
				}
			}
		}
	}

	/**
	 * 终止所有子流程
	 */
	public void terminationSubProcess(String tokenId) {
		// 判断子流程是否都结束，没结束都强制结束子流程。
		List<ProcessInstanceEntity> processInstanceEntities = this.getSubProcessInstanceList(tokenId);
		if (processInstanceEntities.size() > 0) {
			for (ProcessInstanceEntity subProcessInstanceEntity : processInstanceEntities) {
				subProcessInstanceEntity.termination();
				try {
					Context.getCommandContext().getProcessInstanceManager().saveProcessInstance(subProcessInstanceEntity);
				} catch (Exception e) {
					e.printStackTrace();
					throw new FixFlowException("子流程强制终止的时候出现错误", e);
				}
			}
		}

	}

	/**
	 * 结束所有子流程
	 */
	public void endSubProcess() {

		List<ProcessInstanceEntity> processInstanceEntities = this.getSubProcessInstanceList();
		if (processInstanceEntities.size() > 0) {
			for (ProcessInstanceEntity subProcessInstanceEntity : processInstanceEntities) {
				subProcessInstanceEntity.termination();
				try {
					Context.getCommandContext().getProcessInstanceManager().saveProcessInstance(subProcessInstanceEntity);
				} catch (Exception e) {
					e.printStackTrace();
					throw new FixFlowException("子流程强制结束的时候出现错误", e);
				}
			}
		}
	}

	/**
	 * 终止流程实例
	 */
	public void termination() {
		rootToken.end(false);
		if (endTime == null) {
			// 设置流程结束时间
			endTime = new Date();
			// 更新实例状态为终止
			this.setInstanceType(ProcessInstanceType.TERMINATION);
			ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(getRootToken());
			// 触发流程实例终止事件
			getProcessDefinition().fireEvent(BaseElementEvent.EVENTTYPE_PROCESS_ABORT, executionContext);
			// 强制终止没有结束的子流程
			terminationSubProcess();
		}
	}

	private void createEndEventTask(ExecutionContext executionContext) {
		// 构造创建任务所需的数据
		String newTaskId = GuidUtil.CreateGuid();
		String newTaskProcessInstanceId = executionContext.getProcessInstance().getId();
		String newTaskProcessDefinitionId = executionContext.getProcessDefinition().getProcessDefinitionId();
		String newTaskTokenId = executionContext.getToken().getId();
		String newTaskNodeId = executionContext.getToken().getNodeId();
		String newTaskNodeName = executionContext.getToken().getFlowNode().getName();
		String newTaskDescription = newTaskNodeName;
		Date newTaskCreateTime = ClockUtil.getCurrentTime();
		int newTaskPriority = TaskInstance.PRIORITY_NORMAL;
		String newTaskProcessDefinitionKey = executionContext.getProcessDefinition().getProcessDefinitionKey();
		TaskInstanceType newTaskTaskInstanceType = TaskInstanceType.FIXENDEVENT;
		String newTaskProcessDefinitionName = executionContext.getProcessDefinition().getName();
		boolean isDraft = false;
		// 创建任务
		TaskInstanceEntity taskInstance = new TaskInstanceEntity();
		taskInstance.setId(newTaskId);
		taskInstance.setNodeName(newTaskNodeName);
		taskInstance.setProcessInstanceId(newTaskProcessInstanceId);
		taskInstance.setProcessDefinitionId(newTaskProcessDefinitionId);
		taskInstance.setTokenId(newTaskTokenId);
		taskInstance.setNodeId(newTaskNodeId);
		taskInstance.setName(newTaskNodeName);
		taskInstance.setDescription(newTaskDescription);
		taskInstance.setCreateTime(newTaskCreateTime);
		taskInstance.setPriority(newTaskPriority);
		taskInstance.setProcessDefinitionKey(newTaskProcessDefinitionKey);
		taskInstance.setTaskInstanceType(newTaskTaskInstanceType);
		taskInstance.setProcessDefinitionName(newTaskProcessDefinitionName);
		taskInstance.setDraft(isDraft);

		// taskInstance.setAssigneeId(Authentication.getAuthenticatedUserId());
		// taskInstance.setEndTime(newTaskEndTime);
		taskInstance.setCommandId(TaskCommandType.ENDEVENT);
		taskInstance.setCommandType(TaskCommandType.ENDEVENT);

		TaskCommandDef taskCommandDef = Context.getProcessEngineConfiguration().getTaskCommandDefMap().get(TaskCommandType.ENDEVENT);
		if (taskCommandDef != null) {
			taskInstance.setCommandMessage(taskCommandDef.getName());
		}
		taskInstance.setEndTime(ClockUtil.getCurrentTime());
		// taskInstance.setCallActivityInstanceId(subProcessInstanceId);
		Context.getCommandContext().getTaskManager().saveTaskInstanceEntity((TaskInstanceEntity) taskInstance);
	}

	private void startParentProcessInstance(TokenEntity parentToken, CallActivityBehavior callActivityBehavior) {

		// 如果父流程的调用节点为异步调用则不再回启父流程直接结束
		if (callActivityBehavior.isAsync()) {
			return;
		}
		// 结束创建的那条子流程状态记录。
		callActivityBehavior.endSubTask(this.getId());
		ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(this.rootToken);
		Map<String, Object> dataVarMap = new HashMap<String, Object>();
		SubProcessToDataSourceMapping subProcessToDataSourceMapping = callActivityBehavior.getSubProcessToDataSourceMapping();
		if (subProcessToDataSourceMapping != null) {
			for (DataVariableMapping dataVariableMapping : subProcessToDataSourceMapping.getDataVariableMapping()) {
				String subProcesId = "${" + dataVariableMapping.getSubProcesId() + "}";
				dataVarMap.put(dataVariableMapping.getDataSourceId(), ExpressionMgmt.execute(subProcesId, executionContext));
			}
		}
		ProcessEngine processEngine = ProcessEngineManagement.getDefaultProcessEngine();
		processEngine.getRuntimeService().tokenSignal(this.getParentProcessInstanceTokenId(), null, dataVarMap);
	}

	public Map<String, Object> getDataVariable() {
		QueryVariablesCommand queryVariablesCommand = new QueryVariablesCommand();
		queryVariablesCommand.setProcessInstanceId(this.id);
		return Context.getCommandContext().getVariableManager().queryVariable(queryVariablesCommand);
	}

	public List<ProcessInstanceEntity> getSubProcessInstanceList() {
		ProcessInstanceManager processInstanceManager = Context.getCommandContext().getProcessInstanceManager();
		List<ProcessInstanceEntity> processInstanceEntities = processInstanceManager.findSubProcessInstanceById(this.getId());
		return processInstanceEntities;
	}

	public List<ProcessInstanceEntity> getSubProcessInstanceList(String tokenId) {
		ProcessInstanceManager processInstanceManager = Context.getCommandContext().getProcessInstanceManager();
		List<ProcessInstanceEntity> processInstanceEntities = processInstanceManager.findSubProcessInstanceByIdAndToken(this.getId(), tokenId);
		return processInstanceEntities;
	}

	/**
	 * 判断流程实例是否结束
	 * 
	 * @return
	 */
	public boolean hasEnded() {
		return (endTime != null);
	}

	public void resume() {
		isSuspended = false;
		this.instanceType = ProcessInstanceType.RUNNING;
		rootToken.resume();
	}

	public void suspend() {
		isSuspended = true;
		this.instanceType = ProcessInstanceType.SUSPEND;
		rootToken.suspend();
	}

	public int getVersion() {
		return getProcessDefinition().getVersion();
	}

	public boolean isSuspended() {
		return isSuspended;
	}

	// 过时方法
	public void setBizKeyWithoutCascade(String bizKey) {
		this.bizKey = bizKey;
	}

	public void setInitiatorWithoutCascade(String initiator) {
		this.initiator = initiator;
	}

	public void setIdWithoutCascade(String id) {
		this.id = id;
	}

	public void setSuspendedWithoutCascade(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}

	public void setDefinitionIdWithoutCascade(String definitionId) {
		this.definitionId = definitionId;
	}

	public void setProcessDefinitionIdWithoutCascade(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public void setProcessDefinitionKeyWithoutCascade(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public void setStartTimeWithoutCascade(Date startTime) {
		this.startTime = startTime;
	}

	public void setEndTimeWithoutCascade(Date endTime) {
		this.endTime = endTime;
	}

	public void setRootTokenIdWithoutCascade(String rootTokenId) {
		this.rootTokenId = rootTokenId;
	}

	@Override
	public String getCloneRuleId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPersistentDbMapRuleId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPersistentStateRuleId() {
		// TODO Auto-generated method stub
		return null;
	}
}
