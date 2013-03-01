package com.founder.fix.fixflow.core.impl.bpmn.behavior;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.bpmn2.impl.CallActivityImpl;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;

import com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef;
import com.founder.fix.bpmn2extensions.fixflow.DataVariableMapping;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowFactory;
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
import com.founder.fix.fixflow.core.impl.util.EMFExtensionUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskInstanceType;

public class CallActivityBehavior extends CallActivityImpl {

	//protected boolean isAsync = false;

	public boolean isAsync() {
		
		
		return StringUtil.getBoolean(EMFExtensionUtil.getAnyAttributeValue(this, "isAsync"));
		//return isAsync;
	}

	//protected String subProcessInstanceId;

	public void execute(ExecutionContext executionContext) {
		//创建子流程
		String supProcessInstanceId=createSubProcess(executionContext);
		//如果为异步子流程则创建子流程完毕后直接
		//执行离开事件
		if(isAsync()){
			endSubTask(supProcessInstanceId);
			super.execute(executionContext);
			//subProcessInstanceId=null;
			
		}
		
		
	}
	
	
	
	private void createSubTask(ExecutionContext executionContext,String subProcessInstanceId){
		
		//构造创建人物所需的数据
		String newTaskId=subProcessInstanceId;
		String newTaskProcessInstanceId=executionContext.getProcessInstance().getId();		
		String newTaskProcessDefinitionId=executionContext.getProcessDefinition().getProcessDefinitionId();		
		String newTaskTokenId=executionContext.getToken().getId();		
		String newTaskNodeId=executionContext.getToken().getNodeId();		
		String newTaskNodeName=executionContext.getToken().getFlowNode().getName();		
		String newTaskDescription=newTaskNodeName;	
		Date newTaskCreateTime=ClockUtil.getCurrentTime();
		int newTaskPriority= TaskInstance.PRIORITY_NORMAL;
		String newTaskProcessDefinitionKey=executionContext.getProcessDefinition().getProcessDefinitionKey();
		TaskInstanceType newTaskTaskInstanceType=TaskInstanceType.FIXCALLACTIVITYTASK;	
		String newTaskProcessDefinitionName=executionContext.getProcessDefinition().getName();
		boolean isDraft=false;
				
		
				
		//创建任务
		TaskInstance taskInstance=new TaskInstanceEntity();
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
				
		Context.getCommandContext().getTaskManager().saveTaskInstanceEntity((TaskInstanceEntity)taskInstance);
		
	}
	
	public void endSubTask(String supProcessInstanceId) {
		
		
		
		ProcessEngine processEngine=ProcessEngineManagement.getDefaultProcessEngine();
		TaskInstanceEntity taskInstance=(TaskInstanceEntity)processEngine.getTaskService().createTaskQuery().callActivityInstanceId(supProcessInstanceId).singleResult();
		Date newTaskEndTime=ClockUtil.getCurrentTime();
		//taskInstance.setAssigneeId("1200119390");
		taskInstance.setEndTime(newTaskEndTime);
		taskInstance.setCommandId(TaskCommandType.SUBPROCESSEND);
		taskInstance.setCommandType(TaskCommandType.SUBPROCESSEND);
		TaskCommandDef taskCommandDef=Context.getProcessEngineConfiguration().getTaskCommandDefMap().get(TaskCommandType.SUBPROCESSEND);
		if(taskCommandDef!=null){
			taskInstance.setCommandMessage(taskCommandDef.getName());
		}


		Context.getCommandContext().getTaskManager().saveTaskInstanceEntity(taskInstance);
		

		
		
	}
	
	private String createSubProcess(ExecutionContext executionContext){
		String flowId = StringUtil.getString(ExpressionMgmt.execute(EMFExtensionUtil.getAnyAttributeValue(this, "callableElementId"),
				executionContext));

		String flowVersion = StringUtil.getString(ExpressionMgmt.execute(EMFExtensionUtil.getAnyAttributeValue(this, "callableElementVersion"),
				executionContext));
		int version = StringUtil.getInt(flowVersion);

		String bizKeyTemp = EMFExtensionUtil.getAnyAttributeValue(this, "callableElementBizKey");

		String bizKey = StringUtil.getString(ExpressionMgmt.execute(bizKeyTemp, executionContext));

		List<DataVariableMapping> dDataVariableMapping = new ArrayList<DataVariableMapping>();
		List<DataVariableMapping> sDataVariableMapping = new ArrayList<DataVariableMapping>();
		// SubProcessToDataSourceMapping
		List<Entry> entries = EMFExtensionUtil.getExtensionElements(this, "DataSourceToSubProcessMapping");
		for (Entry entry : entries) {

			List<Entry> cEntries = EMFExtensionUtil.getExtensionElementsInEntry(entry, "DataVariableMapping");
			for (Entry entry2 : cEntries) {
				String dataSourceId = EMFExtensionUtil.getExtensionElementAttributeValue(entry2, "dataSourceId");
				String subProcesId = EMFExtensionUtil.getExtensionElementAttributeValue(entry2, "subProcesId");
				DataVariableMapping dataVariableMapping = FixFlowFactory.eINSTANCE.createDataVariableMapping();
				dataVariableMapping.setDataSourceId(dataSourceId);
				dataVariableMapping.setSubProcesId(subProcesId);
				dDataVariableMapping.add(dataVariableMapping);

			}

		}

		entries = EMFExtensionUtil.getExtensionElements(this, "SubProcessToDataSourceMapping");
		for (Entry entry : entries) {

			List<Entry> cEntries = EMFExtensionUtil.getExtensionElementsInEntry(entry, "DataVariableMapping");
			for (Entry entry2 : cEntries) {
				String dataSourceId = EMFExtensionUtil.getExtensionElementAttributeValue(entry2, "dataSourceId");
				String subProcesId = EMFExtensionUtil.getExtensionElementAttributeValue(entry2, "subProcesId");
				DataVariableMapping dataVariableMapping = FixFlowFactory.eINSTANCE.createDataVariableMapping();
				dataVariableMapping.setDataSourceId(dataSourceId);
				dataVariableMapping.setSubProcesId(subProcesId);
				sDataVariableMapping.add(dataVariableMapping);

			}

		}

		ProcessDefinitionBehavior processDefinitionBehavior = Context.getCommandContext().getProcessDefinitionManager()
				.findLatestProcessDefinitionByKeyAndVersion(flowId, version);

		ProcessInstanceEntity subProcessInstance = new ProcessInstanceEntity(processDefinitionBehavior, bizKey,
				executionContext.getProcessInstance(), executionContext.getToken());

		subProcessInstance.setStartAuthor(Authentication.getAuthenticatedUserId());

		for (DataVariableMapping dataVariableMapping : dDataVariableMapping) {

			String dataSourceId = "${" + dataVariableMapping.getDataSourceId() + "}";

			subProcessInstance.getContextInstance().addDataVariable(dataVariableMapping.getSubProcesId(),
					ExpressionMgmt.execute(dataSourceId, executionContext));

		}

		try {

			subProcessInstance.noneStart();

			ProcessInstanceManager processInstanceManager = Context.getCommandContext().getProcessInstanceManager();

			processInstanceManager.saveProcessInstance(subProcessInstance);

		} catch (Exception e) {

			throw new FixFlowException("子流程 " + this.getName() + " 启动异常!", e);

		}
		
		
		createSubTask(executionContext,subProcessInstance.getId());
		
		return subProcessInstance.getId();
		
	}
	
}
