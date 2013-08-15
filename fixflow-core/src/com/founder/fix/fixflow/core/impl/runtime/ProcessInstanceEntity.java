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
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.CallActivityBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.StartEventBehavior;
import com.founder.fix.fixflow.core.impl.command.QueryVariablesCommand;
import com.founder.fix.fixflow.core.impl.datavariable.DataVariableMgmtInstance;
import com.founder.fix.fixflow.core.impl.db.AbstractPersistentObject;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.persistence.ProcessInstanceManager;
import com.founder.fix.fixflow.core.impl.task.TaskCommandType;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.ClockUtil;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.objkey.ProcessInstanceObjKey;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.ProcessInstanceType;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskInstanceType;
import com.founder.fix.fixflow.core.task.TaskMgmtInstance;

public class ProcessInstanceEntity extends AbstractPersistentObject implements ProcessInstance {

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
	protected String id = null;

	/**
	 * 提交人(业务流、工作流都有提交人信息)
	 */
	protected String initiator;

	/**
	 * 业务关联键
	 */
	protected String bizKey;

	/**
	 * 流程实例的开始时间
	 */
	protected Date startTime;

	/**
	 * 
	 * 流程实例的结束时间
	 */
	protected Date endTime;

	/**
	 * 流程定义对象,在创建流程实例的时候赋予流程实例。
	 */
	protected ProcessDefinitionBehavior processDefinition;

	/**
	 * 是否已经暂停默认为否(false) --暂时未实现
	 */
	protected boolean isSuspended = false;

	/**
	 * 流程实例的根令牌(最初创建的那个令牌老大)
	 */
	protected TokenEntity rootToken;

	/**
	 * 流程实例令牌集合
	 */
	protected List<TokenEntity> tokenList;

	/**
	 * 流程实例令牌集合
	 */
	protected HashMap<String, TokenEntity> tokenMap;

	protected TaskMgmtInstance taskMgmtInstance;

	protected DataVariableMgmtInstance dataVariableMgmtInstance;

	protected ContextInstance contextInstance;

	/**
	 * 子流程实例列表
	 */
	protected List<ProcessInstanceEntity> subProcessInstanceList;

	/**
	 * 父流程实例列表
	 */
	protected ProcessInstanceEntity parentProcessInstance;

	/**
	 * 父流程实例编号
	 */
	protected String parentProcessInstanceId;

	/**
	 * 父流程实例令牌
	 */
	protected TokenEntity parentProcessInstanceToken;

	/**
	 * 父流程实例令牌编号
	 */
	protected String parentProcessInstanceTokenId;

	/**
	 * 流程实例主题
	 */
	protected String subject;

	/**
	 * 流程的启动人
	 */
	protected String startAuthor;
	
	/**
	 * 更新时间
	 */
	protected Date updateTime;
	
	/**
	 * 归档时间
	 */
	protected Date archiveTime;
	
	/**
	 * 实例状态
	 */
	protected ProcessInstanceType instanceType;
	


	protected Map<String, Object> extensionFields = new HashMap<String, Object>();
	

	/**
	 * 流程位置
	 */
	protected String processLocation;
	

	

	public ProcessInstanceEntity() {

	}

	// Constructor 构造函数
	// /////////////////////////////////////////////////////
	public ProcessInstanceEntity(ProcessDefinitionBehavior processDefinition, String businessKey) {
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
		// 设置提交人
		// this.initiator=startProcessInstanceCommand.getInitiator();
		// 设置瞬态变量
		// this.getContextInstance().setTransientVariableMap(startProcessInstanceCommand.getTransientVariables());
		// 设置持久化变量
		// this.getContextInstance().setVariableMap(startProcessInstanceCommand.getVariables());
		contextInstance = ProcessObjectFactory.FACTORYINSTANCE.createContextInstance(this);
		DataVariableMgmtInstance dataVariableMgmtInstance = new DataVariableMgmtInstance();
		dataVariableMgmtInstance.setProcessInstance(this);
	}

	/**
	 * 子流程使用
	 * 
	 * @param processDefinition
	 * @param businessKey
	 * @param parentProcessInstance
	 * @param parentProcessInstanceToken
	 */
	public ProcessInstanceEntity(ProcessDefinitionBehavior processDefinition, String businessKey, ProcessInstanceEntity parentProcessInstance, TokenEntity parentProcessInstanceToken) {
		this(processDefinition, businessKey);
		this.parentProcessInstance = parentProcessInstance;
		this.parentProcessInstanceId = parentProcessInstance.getId();
		this.parentProcessInstanceToken = parentProcessInstanceToken;
		this.parentProcessInstanceTokenId = parentProcessInstanceToken.getId();
	}

	public void start() throws Exception {
		if (this.getRootToken().getFlowNode() == null) {
			// 设置流程实例的开始时间
			this.startTime = new Date();

			ExecutionContext executionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(this.getRootToken());

		
			processDefinition.getTaskSubject().getExpressionValue();
			String processInstanceSubjectExpression=processDefinition.getTaskSubject().getExpressionValue();
			
			try {
				Object result = ExpressionMgmt.execute(processInstanceSubjectExpression,executionContext);
				if (result != null) {
					
					this.setSubject(StringUtil.getString(result));
					
				}
				else{
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
			String processInstanceSubjectExpression=processDefinition.getTaskSubject().getExpressionValue();
			
			try {
				Object result = ExpressionMgmt.execute(processInstanceSubjectExpression,executionContext);
				if (result != null) {
					
					this.setSubject(StringUtil.getString(result));
					
				}
				else{
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
			String processInstanceSubjectExpression=processDefinition.getTaskSubject().getExpressionValue();
			
			try {
				Object result = ExpressionMgmt.execute(processInstanceSubjectExpression,executionContext);
				if (result != null) {
					
					this.setSubject(StringUtil.getString(result));
					
				}
				else{
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

			String processInstanceSubjectExpression="";
			if(processDefinition.getTaskSubject()!=null){
				processInstanceSubjectExpression=processDefinition.getTaskSubject().getExpressionValue();
			}
			
			
			try {
				Object result = ExpressionMgmt.execute(processInstanceSubjectExpression,executionContext);
				if (result != null) {
					
					this.setSubject(StringUtil.getString(result));
					
				}
				else{
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
		// end the main path of execution
		rootToken.end();

		if (endTime == null) {
			// 设置流程结束时间

			endTime = new Date();

			/*
			 * for (Token freeToken : this.getTokenList()) {
			 * if(freeToken.isFreeToken()&&!freeToken.hasEnded()){
			 * freeToken.end(); } }
			 */

			// 发生流程结束事件
			// ExecutionContext executionContext = new
			// ExecutionContext(rootToken);
			// processDefinition.fireEvent(Event.EVENTTYPE_PROCESS_END,
			// executionContext);
			
			ExecutionContext  executionContext=ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(rootToken);
			//插入流程结束任务
			
			
			if(this.getProcessDefinition().getStartElement()!=null&&this.getProcessDefinition().getStartElement() instanceof StartEvent){
				
				//插入流程启动记录
				StartEventBehavior startEventBehavior=(StartEventBehavior)this.getProcessDefinition().getStartElement();
				if(startEventBehavior.isPersistence()){
					createEndEventTask(executionContext);
				}
				
			}
			
			//createEndEventTask(executionContext);

			if (this.getParentProcessInstanceTokenId() != null && this.getParentProcessInstanceToken() != null) {
				
				FlowNode flowNode=this.getParentProcessInstanceToken().getFlowNode();
				if(flowNode instanceof CallActivity){
					CallActivityBehavior callActivityBehavior=(CallActivityBehavior)flowNode;
					startParentProcessInstance(this.getParentProcessInstanceToken(),callActivityBehavior);
					
				}
				

			}
			//更新实例状态为正常完成
			this.instanceType = ProcessInstanceType.COMPLETE;
		}
		

	}
	
	private void createEndEventTask(ExecutionContext executionContext){
		
		//构造创建任务所需的数据
		String newTaskId=GuidUtil.CreateGuid();
		String newTaskProcessInstanceId=executionContext.getProcessInstance().getId();		
		String newTaskProcessDefinitionId=executionContext.getProcessDefinition().getProcessDefinitionId();		
		String newTaskTokenId=executionContext.getToken().getId();		
		String newTaskNodeId=executionContext.getToken().getNodeId();		
		String newTaskNodeName=executionContext.getToken().getFlowNode().getName();		
		String newTaskDescription=newTaskNodeName;	
		Date newTaskCreateTime=ClockUtil.getCurrentTime();
		int newTaskPriority= TaskInstance.PRIORITY_NORMAL;
		String newTaskProcessDefinitionKey=executionContext.getProcessDefinition().getProcessDefinitionKey();
		TaskInstanceType newTaskTaskInstanceType=TaskInstanceType.FIXENDEVENT;
		String newTaskProcessDefinitionName=executionContext.getProcessDefinition().getName();
		boolean isDraft=false;
				
		
				
		//创建任务
		TaskInstanceEntity taskInstance=new TaskInstanceEntity();
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
		
	
		//taskInstance.setAssigneeId(Authentication.getAuthenticatedUserId());
		//taskInstance.setEndTime(newTaskEndTime);
		taskInstance.setCommandId(TaskCommandType.ENDEVENT);
		taskInstance.setCommandType(TaskCommandType.ENDEVENT);
		
		TaskCommandDef taskCommandDef=Context.getProcessEngineConfiguration().getTaskCommandDefMap().get(TaskCommandType.ENDEVENT);
		if(taskCommandDef!=null){
			taskInstance.setCommandMessage(taskCommandDef.getName());
		}
		
		
		taskInstance.setEndTime(ClockUtil.getCurrentTime());
		//taskInstance.setCallActivityInstanceId(subProcessInstanceId);
				
		Context.getCommandContext().getTaskManager().saveTaskInstanceEntity((TaskInstanceEntity)taskInstance);
		
	}
	
	private void startParentProcessInstance(TokenEntity parentToken,CallActivityBehavior callActivityBehavior){
		
		
		
		
		//如果父流程的调用节点为异步调用则不再回启父流程直接结束
		if(callActivityBehavior.isAsync()){
			return;
		}
		
		
		//结束创建的那条子流程状态记录。
		callActivityBehavior.endSubTask(this.getId());
		
		ExecutionContext executionContext=ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(this.rootToken);
		Map<String, Object> dataVarMap=new HashMap<String, Object>();
		
		SubProcessToDataSourceMapping subProcessToDataSourceMapping=callActivityBehavior.getSubProcessToDataSourceMapping();
		if(subProcessToDataSourceMapping!=null){
			for (DataVariableMapping dataVariableMapping : subProcessToDataSourceMapping.getDataVariableMapping()) {
				 String subProcesId="${"+dataVariableMapping.getSubProcesId()+"}";
				 dataVarMap.put(dataVariableMapping.getDataSourceId(),  ExpressionMgmt.execute(subProcesId,executionContext));
			}
		}
		ProcessEngine processEngine=ProcessEngineManagement.getDefaultProcessEngine();
		processEngine.getRuntimeService().tokenSignal(this.getParentProcessInstanceTokenId(), null,dataVarMap);
	}

	public Map<String, Object> getDataVariable() {
		QueryVariablesCommand queryVariablesCommand=new QueryVariablesCommand();
		queryVariablesCommand.setProcessInstanceId(this.id);
		return Context.getCommandContext().getVariableManager().queryVariable(queryVariablesCommand);
	}

	/**
	 * 获取父流程实例编号
	 * 
	 * @return
	 */
	public String getParentProcessInstanceId() {
		return parentProcessInstanceId;
	}

	/**
	 * 获取父流程实例
	 * 
	 * @return 父流程实例
	 */
	public ProcessInstanceEntity getParentProcessInstance() {
		if (this.parentProcessInstanceId != null) {
			if (this.parentProcessInstance != null) {
				return this.parentProcessInstance;
			}
			ProcessInstanceManager processInstanceManager = Context.getCommandContext().getProcessInstanceManager();
			ProcessInstanceEntity processInstanceImpl = processInstanceManager.findProcessInstanceById(this.parentProcessInstanceId, null);
			if (processInstanceImpl != null) {
				this.parentProcessInstance = processInstanceImpl;
			}
			return processInstanceImpl;
		}

		return parentProcessInstance;
	}

	/**
	 * 获取父流程实例令牌编号
	 * 
	 * @return 父流程实例令牌编号
	 */
	public String getParentProcessInstanceTokenId() {
		return parentProcessInstanceTokenId;
	}

	/**
	 * 设置父流程实例编号
	 * 
	 * @param parentProcessInstanceId
	 *            父流程实例编号
	 */
	public void setParentProcessInstanceId(String parentProcessInstanceId) {
		this.parentProcessInstanceId = parentProcessInstanceId;
	}

	/**
	 * 设置父流程实例令牌编号
	 * 
	 * @param parentProcessInstanceTokenId
	 *            父流程实例令牌编号
	 */
	public void setParentProcessInstanceTokenId(String parentProcessInstanceTokenId) {
		this.parentProcessInstanceTokenId = parentProcessInstanceTokenId;
	}

	/**
	 * 获取父流程实例令牌
	 * 
	 * @return
	 */
	public TokenEntity getParentProcessInstanceToken() {

		if (this.parentProcessInstanceToken != null) {
			return this.parentProcessInstanceToken;
		}

		TokenEntity tempToken = getParentProcessInstance().getTokenMap().get(this.getParentProcessInstanceTokenId());
		if (tempToken != null) {
			this.parentProcessInstanceToken = tempToken;
		}
		return parentProcessInstanceToken;
	}

	public Date getEndTime() {
		return endTime;
	}

	public String getId() {
		return id;
	}

	public void setProcessDefinition(ProcessDefinitionBehavior processDefinition) {

		this.processDefinitionId = processDefinition.getProcessDefinitionId();
		this.processDefinitionKey = processDefinition.getProcessDefinitionKey();
		this.processDefinition = processDefinition;
	}

	public ProcessDefinitionBehavior getProcessDefinition() {

		if (processDefinition == null) {
			processDefinition = Context.getCommandContext().getProcessDefinitionManager().findLatestProcessDefinitionById(processDefinitionId);
		}
		return processDefinition;
	}

	public TokenEntity getRootToken() {
		return rootToken;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStartTime() {
		return startTime;
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

	public TaskMgmtInstance getTaskMgmtInstance() {

		if (taskMgmtInstance == null) {
			taskMgmtInstance = ProcessObjectFactory.FACTORYINSTANCE.createTaskMgmtInstance();
			taskMgmtInstance.setProcessInstance(this);
		}
		return taskMgmtInstance;

	}

	public void setTaskMgmtInstance(TaskMgmtInstance taskMgmtInstance) {
		this.taskMgmtInstance = taskMgmtInstance;
	}

	public List<TokenEntity> getTokenList() {
		return tokenList;
	}

	public Map<String, TokenEntity> getTokenMap() {
		if ((tokenMap == null) && (tokenList != null)) {

			tokenMap = new HashMap<String, TokenEntity>();

			for (TokenEntity token : tokenList) {
				tokenMap.put(token.getId(), token);
			}
		}
		return tokenMap;
	}

	/**
	 * 判断流程实例是否结束
	 * 
	 * @return
	 */
	public boolean hasEnded() {
		return (endTime != null);
	}

	public void resume(){
		isSuspended = false;
		rootToken.resume();

	}

	public void setId(String id) {
		this.id = id;

	}

	public void setTokenList(List<TokenEntity> tokenList) {
		this.tokenList = tokenList;
	}

	public void suspend(){
		isSuspended = true;
		rootToken.suspend();
	}

	public ContextInstance getContextInstance() {
		if (contextInstance == null) {
			contextInstance = ProcessObjectFactory.FACTORYINSTANCE.createContextInstance(this);
		}
		return contextInstance;
	}

	public DataVariableMgmtInstance getDataVariableMgmtInstance() {
		return dataVariableMgmtInstance;
	}

	public void setDataVariableMgmtInstance(DataVariableMgmtInstance dataVariableMgmtInstance) {
		this.dataVariableMgmtInstance = dataVariableMgmtInstance;
	}

	public void setContextInstance(ContextInstance contextInstance) {
		this.contextInstance = contextInstance;
	}

	public String getBizKey() {
		return bizKey;
	}

	public void setBizKey(String bizKey) {
		this.bizKey = bizKey;
	}

	public String getInitiator() {
		return initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	public int getVersion() {
		return getProcessDefinition().getVersion();
	}

	public boolean isSuspended() {
		return isSuspended;
	}

	public void setRootToken(TokenEntity rootToken) {
		this.rootToken = rootToken;
	}

	/* 持久化变量 */

	protected String processDefinitionId;

	protected String processDefinitionKey;

	protected String rootTokenId;

	protected List<String> tokenIdList;

	protected String definitionId;

	public Map<String, Object> getPersistentState() {

		Map<String, Object> mapPersistentState = new HashMap<String, Object>();

		mapPersistentState.put(ProcessInstanceObjKey.ProcessInstanceId().FullKey(), this.id);

		mapPersistentState.put(ProcessInstanceObjKey.Initiator().FullKey(), this.initiator);

		mapPersistentState.put(ProcessInstanceObjKey.StartAuthor().FullKey(), this.startAuthor);

		mapPersistentState.put(ProcessInstanceObjKey.BizKey().DataBaseKey(), this.bizKey);

		mapPersistentState.put(ProcessInstanceObjKey.StartTime().FullKey(), this.startTime);

		mapPersistentState.put(ProcessInstanceObjKey.EndTime().FullKey(), this.endTime);

		mapPersistentState.put(ProcessInstanceObjKey.ProcessDefinitionId().FullKey(), this.processDefinitionId);

		mapPersistentState.put(ProcessInstanceObjKey.IsSuspended().FullKey(), this.isSuspended);

		mapPersistentState.put(ProcessInstanceObjKey.RootTokenId().FullKey(), this.rootTokenId);

		mapPersistentState.put(ProcessInstanceObjKey.Subject().FullKey(), this.subject);

		mapPersistentState.put(ProcessInstanceObjKey.DefinitionId().FullKey(), this.definitionId);

		mapPersistentState.put(ProcessInstanceObjKey.ProcessDefinitionKey().FullKey(), this.processDefinitionKey);

		mapPersistentState.put(ProcessInstanceObjKey.ParentProcessInstanceId().FullKey(), this.parentProcessInstanceId);

		mapPersistentState.put(ProcessInstanceObjKey.ParentProcessInstanceTokenId().FullKey(), this.parentProcessInstanceTokenId);

		mapPersistentState.put(ProcessInstanceObjKey.ProcessLocation().FullKey(), this.processLocation);
		
		mapPersistentState.put(ProcessInstanceObjKey.UpdateTime().FullKey(), this.updateTime);
		
		mapPersistentState.put(ProcessInstanceObjKey.InstanceStatus().FullKey(), this.instanceType);
		
		mapPersistentState.put(ProcessInstanceObjKey.ArchiveTime().FullKey(), this.archiveTime);

		for (String key : extensionFields.keySet()) {
			mapPersistentState.put(key, extensionFields.get(key));	
		}
		
		return mapPersistentState;
	}

	public String getProcessDefinitionId() {
		return this.processDefinitionId;
	}

	public String getProcessDefinitionKey() {
		return this.processDefinitionKey;
	}

	public String getRootTokenId() {
		return this.rootTokenId;
	}

	public List<String> getTokenIdList() {
		return this.tokenIdList;
	}

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

	public void setTokenIdList(List<String> tokenIdList) {
		this.tokenIdList = tokenIdList;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getStartAuthor() {
		return startAuthor;
	}

	public void setStartAuthor(String startAuthor) {
		this.startAuthor = startAuthor;
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
	
	
	public String getProcessLocation() {
		return processLocation;
	}

	public void setProcessLocation(String processLocation) {
		this.processLocation = processLocation;
	}
	
	
	public ProcessInstanceEntity(Map<String, Object> entityMap){
		
		persistentInit(entityMap);
	}

	@Override
	public void persistentInit(Map<String, Object> entityMap) {
		// TODO 自动生成的方法存根
		for (String dataKey : entityMap.keySet()) {

			if (dataKey.equals(ProcessInstanceObjKey.ProcessInstanceId().DataBaseKey())) {
				this.id = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(ProcessInstanceObjKey.ProcessDefinitionId().DataBaseKey())) {
				this.processDefinitionId = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(ProcessInstanceObjKey.ProcessDefinitionKey().DataBaseKey())) {
				this.processDefinitionKey = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(ProcessInstanceObjKey.StartTime().DataBaseKey())) {
				this.startTime = StringUtil.getDate(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(ProcessInstanceObjKey.EndTime().DataBaseKey())) {
				this.endTime = StringUtil.getDate(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(ProcessInstanceObjKey.DefinitionId().DataBaseKey())) {
				this.definitionId = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(ProcessInstanceObjKey.RootTokenId().DataBaseKey())) {
				this.rootTokenId = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(ProcessInstanceObjKey.BizKey().DataBaseKey())) {
				this.bizKey = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(ProcessInstanceObjKey.StartAuthor().DataBaseKey())) {
				this.startAuthor = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(ProcessInstanceObjKey.Initiator().DataBaseKey())) {
				this.initiator = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(ProcessInstanceObjKey.IsSuspended().DataBaseKey())) {
				this.isSuspended = StringUtil.getBoolean(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}

			if (dataKey.equals(ProcessInstanceObjKey.Subject().DataBaseKey())) {
				this.subject = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}
			
			
			if (dataKey.equals(ProcessInstanceObjKey.ParentProcessInstanceId().DataBaseKey())) {
				this.parentProcessInstanceId = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}
			
			if (dataKey.equals(ProcessInstanceObjKey.ParentProcessInstanceTokenId().DataBaseKey())) {
				this.parentProcessInstanceTokenId = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}
			
			if (dataKey.equals(ProcessInstanceObjKey.ProcessLocation().DataBaseKey())) {
				this.processLocation = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}
			
			if (dataKey.equals(ProcessInstanceObjKey.UpdateTime().DataBaseKey())) {
				this.updateTime =  StringUtil.getDate(entityMap.get(dataKey));
				continue;
			}
			
			if (dataKey.equals(ProcessInstanceObjKey.InstanceStatus().DataBaseKey())) {
				//为了兼容老系统
				if(entityMap.get(dataKey) == null){
					if(entityMap.get(ProcessInstanceObjKey.EndTime().DataBaseKey()) == null){
						this.instanceType = ProcessInstanceType.valueOf("RUNNING");
					}else{
						this.instanceType = ProcessInstanceType.valueOf("COMPLETE");
					}
				}else{
					this.instanceType = ProcessInstanceType.valueOf(StringUtil.getString(entityMap.get(dataKey)));
				}
				continue;
			}
			
			if (dataKey.equals(ProcessInstanceObjKey.ArchiveTime().DataBaseKey())) {
				this.archiveTime =  StringUtil.getDate(entityMap.get(dataKey));
				continue;
			}

			this.addExtensionField(dataKey, entityMap.get(dataKey));

		}

	}

	@Override
	public Map<String, Object> getPersistentDbMap() {
		// 构建查询参数
				Map<String, Object> objectParam = new HashMap<String, Object>();
				// 流程实例编号 String
				objectParam.put(ProcessInstanceObjKey.ProcessInstanceId().DataBaseKey(), this.id);

				objectParam.put(ProcessInstanceObjKey.ProcessDefinitionKey().DataBaseKey(), this.getProcessDefinitionKey());

				// 流程定义编号 String
				objectParam.put(ProcessInstanceObjKey.ProcessDefinitionId().DataBaseKey(), this.processDefinitionId);
				// 任务开始时间(可以理解为已读) Date
				objectParam.put(ProcessInstanceObjKey.StartTime().DataBaseKey(), this.startTime);
				// 任务结束时间 Date
				objectParam.put(ProcessInstanceObjKey.EndTime().DataBaseKey(), this.endTime);
				// 业务定义编号 String
				objectParam.put(ProcessInstanceObjKey.DefinitionId().DataBaseKey(), this.getProcessDefinition().getDefinitions().getId());
				// 根令牌编号 String
				objectParam.put(ProcessInstanceObjKey.RootTokenId().DataBaseKey(), this.getRootToken().getId());
				// 流程实例的业务关联值 String
				objectParam.put(ProcessInstanceObjKey.BizKey().DataBaseKey(), this.getBizKey());
				// 流程的发起人 String
				objectParam.put(ProcessInstanceObjKey.Initiator().DataBaseKey(), this.getInitiator());

				// 流程的启动人 String
				objectParam.put(ProcessInstanceObjKey.StartAuthor().DataBaseKey(), this.getStartAuthor());

				// 是否暂停
				objectParam.put(ProcessInstanceObjKey.IsSuspended().DataBaseKey(), String.valueOf(this.isSuspended()));
				// 父流程实例号
				objectParam.put(ProcessInstanceObjKey.ParentProcessInstanceId().DataBaseKey(), this.getParentProcessInstanceId());

				// 父流程令牌号
				objectParam.put(ProcessInstanceObjKey.ParentProcessInstanceTokenId().DataBaseKey(), this.getParentProcessInstanceTokenId());

				// 父流程实例号
				objectParam.put(ProcessInstanceObjKey.Subject().DataBaseKey(), this.getSubject());
				
				// 父流程实例号
				objectParam.put(ProcessInstanceObjKey.ProcessLocation().DataBaseKey(), this.getProcessLocation());
				
				// 实例状态
				objectParam.put(ProcessInstanceObjKey.InstanceStatus().DataBaseKey(), this.getInstanceType().toString());
				
				// 更新时间
				objectParam.put(ProcessInstanceObjKey.UpdateTime().DataBaseKey(), this.getUpdateTime());
				
				//归档时间
				// 更新时间
				objectParam.put(ProcessInstanceObjKey.ArchiveTime().DataBaseKey(), this.getArchiveTime());
				
				for (String key : persistenceExtensionFields.keySet()) {
					objectParam.put(key, persistenceExtensionFields.get(key));	
				}
				
				return objectParam;
	}
	
	/**
	 * 持久化扩展字段
	 */
	protected Map<String, Object> persistenceExtensionFields = new HashMap<String, Object>();
	
	public void setPersistenceExtensionField(String fieldName,Object value){
		extensionFields.put(fieldName, value);
		persistenceExtensionFields.put(fieldName, value);
	}
	

}
