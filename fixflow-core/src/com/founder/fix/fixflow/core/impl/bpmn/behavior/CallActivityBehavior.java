package com.founder.fix.fixflow.core.impl.bpmn.behavior;

import java.util.Date;
import org.eclipse.bpmn2.impl.CallActivityImpl;

import com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef;
import com.founder.fix.bpmn2extensions.fixflow.DataSourceToSubProcessMapping;
import com.founder.fix.bpmn2extensions.fixflow.DataVariableMapping;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.fixflow.SubProcessToDataSourceMapping;
import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.persistence.ProcessInstanceManager;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.task.TaskCommandType;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.ClockUtil;
import com.founder.fix.fixflow.core.impl.util.EMFUtil;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskInstanceType;

public class CallActivityBehavior extends CallActivityImpl {

	


	protected boolean isAsync = false;

	protected String callableElementId;
	
	protected String callableElementVersion;
	
	protected String callableElementBizKey;
	
	
	
	protected DataSourceToSubProcessMapping dataSourceToSubProcessMapping;
	protected SubProcessToDataSourceMapping subProcessToDataSourceMapping;

	public boolean isAsync() {

		this.isAsync = StringUtil.getBoolean(this.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__IS_ASYNC));
		return this.isAsync;
	}

	public String getCallableElementId() {

		if (this.callableElementId == null) {
			this.callableElementId = StringUtil.getString(this.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_ID));
		}

		return this.callableElementId;
	}

	
	public String getCallableElementVersion() {
		
		

		if (this.callableElementVersion == null) {
			this.callableElementVersion = StringUtil.getString(this.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_VERSION));
		}

		return this.callableElementVersion;

	}
	
	
	public String getCallableElementBizKey() {
		
		if (this.callableElementBizKey == null) {
			this.callableElementBizKey = StringUtil.getString(this.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_BIZ_KEY));
		}

		return this.callableElementBizKey;

	}

	

	public DataSourceToSubProcessMapping getDataSourceToSubProcessMapping() {
		
		if(this.dataSourceToSubProcessMapping==null){
			
				this.dataSourceToSubProcessMapping=EMFUtil.getExtensionElementOne(DataSourceToSubProcessMapping.class,this,FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_SOURCE_TO_SUB_PROCESS_MAPPING);
			
			
			
		}
		return this.dataSourceToSubProcessMapping;
		
		
		
		
	}

	public SubProcessToDataSourceMapping getSubProcessToDataSourceMapping() {
		
		if(this.subProcessToDataSourceMapping==null){
			
			
			this.subProcessToDataSourceMapping=EMFUtil.getExtensionElementOne(SubProcessToDataSourceMapping.class,this,FixFlowPackage.Literals.DOCUMENT_ROOT__SUB_PROCESS_TO_DATA_SOURCE_MAPPING);
			
			
		}
		return this.subProcessToDataSourceMapping;

	}

	
	public void execute(ExecutionContext executionContext) {
		// 创建子流程
		String supProcessInstanceId = createSubProcess(executionContext);
		// 如果为异步子流程则创建子流程完毕后直接
		// 执行离开事件
		if (isAsync()) {
			endSubTask(supProcessInstanceId);
			super.execute(executionContext);
			// subProcessInstanceId=null;

		}

	}

	private void createSubTask(ExecutionContext executionContext, String subProcessInstanceId) {

		// 构造创建人物所需的数据
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
		TaskInstanceType newTaskTaskInstanceType = TaskInstanceType.FIXCALLACTIVITYTASK;
		String newTaskProcessDefinitionName = executionContext.getProcessDefinition().getName();
		boolean isDraft = false;

		// 创建任务
		TaskInstance taskInstance = new TaskInstanceEntity();
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
		taskInstance.setCallActivityInstanceId(subProcessInstanceId);

		Context.getCommandContext().getTaskManager().saveTaskInstanceEntity((TaskInstanceEntity) taskInstance);

	}

	public void endSubTask(String supProcessInstanceId) {

		ProcessEngine processEngine = ProcessEngineManagement.getDefaultProcessEngine();
		TaskInstanceEntity taskInstance = (TaskInstanceEntity) processEngine.getTaskService().createTaskQuery()
				.callActivityInstanceId(supProcessInstanceId).taskNotEnd().singleResult();
		Date newTaskEndTime = ClockUtil.getCurrentTime();
		// taskInstance.setAssigneeId("1200119390");
		taskInstance.setEndTime(newTaskEndTime);
		taskInstance.setCommandId(TaskCommandType.SUBPROCESSEND);
		taskInstance.setCommandType(TaskCommandType.SUBPROCESSEND);
		TaskCommandDef taskCommandDef = Context.getProcessEngineConfiguration().getTaskCommandDefMap().get(TaskCommandType.SUBPROCESSEND);
		if (taskCommandDef != null) {
			taskInstance.setCommandMessage(taskCommandDef.getName());
		}

		Context.getCommandContext().getTaskManager().saveTaskInstanceEntity(taskInstance);

	}

	private String createSubProcess(ExecutionContext executionContext) {
		String flowId = StringUtil.getString(ExpressionMgmt.execute(getCallableElementId(), executionContext));

		String flowVersion = StringUtil.getString(ExpressionMgmt.execute(
				getCallableElementVersion(), executionContext));
		int version = StringUtil.getInt(flowVersion);

		

		String bizKey = StringUtil.getString(ExpressionMgmt.execute(getCallableElementBizKey(), executionContext));

		

		ProcessDefinitionBehavior processDefinitionBehavior = Context.getCommandContext().getProcessDefinitionManager()
				.findLatestProcessDefinitionByKeyAndVersion(flowId, version);

		ProcessInstanceEntity subProcessInstance = new ProcessInstanceEntity(processDefinitionBehavior, bizKey,
				executionContext.getProcessInstance(), executionContext.getToken());

		subProcessInstance.setStartAuthor(Authentication.getAuthenticatedUserId());

		DataSourceToSubProcessMapping dataSourceToSubProcessMapping=getDataSourceToSubProcessMapping();
		if(dataSourceToSubProcessMapping!=null){
			for (DataVariableMapping dataVariableMapping :dataSourceToSubProcessMapping.getDataVariableMapping()) {

				String dataSourceId = "${" + dataVariableMapping.getDataSourceId() + "}";

				subProcessInstance.getContextInstance().addDataVariable(dataVariableMapping.getSubProcesId(),
						ExpressionMgmt.execute(dataSourceId, executionContext));

			}

		}
		
		
		try {

			subProcessInstance.noneStart();

			ProcessInstanceManager processInstanceManager = Context.getCommandContext().getProcessInstanceManager();

			processInstanceManager.saveProcessInstance(subProcessInstance);

		} catch (Exception e) {

			throw new FixFlowException("子流程 " + this.getName() + " 启动异常!", e);

		}

		createSubTask(executionContext, subProcessInstance.getId());

		return subProcessInstance.getId();

	}

}
