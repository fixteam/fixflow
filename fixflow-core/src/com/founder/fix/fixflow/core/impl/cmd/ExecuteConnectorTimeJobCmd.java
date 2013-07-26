package com.founder.fix.fixflow.core.impl.cmd;

import java.util.List;

import org.eclipse.bpmn2.impl.FlowNodeImpl;
import org.quartz.JobExecutionContext;

import com.founder.fix.fixflow.core.event.BaseElementEvent;
import com.founder.fix.fixflow.core.exception.FixFlowBizException;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.connector.ConnectorInstanceBehavior;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public class ExecuteConnectorTimeJobCmd implements Command<Void> {

	protected JobExecutionContext jobExecutionContext;
	
	protected String tokenId;
	protected String processInstanceId;
	protected String nodeId;
	protected String processKey;
	protected String processId;
	protected String processName;
	protected String bizKey;
	protected String jobType;
	protected String connectorId;
	protected String connectorInstanceId;
	protected String connectorInstanceName;
	protected String eventType;
	protected String taskId;
	
	public ExecuteConnectorTimeJobCmd(JobExecutionContext jobExecutionContext){
		this.jobExecutionContext=jobExecutionContext;
		this.tokenId=jobExecutionContext.getJobDetail().getJobDataMap().getString("tokenId");
		this.processInstanceId=jobExecutionContext.getJobDetail().getJobDataMap().getString("processInstanceId");
		this.nodeId=jobExecutionContext.getJobDetail().getJobDataMap().getString("nodeId");
		this.processKey=jobExecutionContext.getJobDetail().getJobDataMap().getString("processKey");
		this.processId=jobExecutionContext.getJobDetail().getJobDataMap().getString("processId");
		this.processName=jobExecutionContext.getJobDetail().getJobDataMap().getString("processName");
		this.bizKey=jobExecutionContext.getJobDetail().getJobDataMap().getString("bizKey");
		this.jobType=jobExecutionContext.getJobDetail().getJobDataMap().getString("jobType");
		this.connectorId=jobExecutionContext.getJobDetail().getJobDataMap().getString("connectorId");
		this.connectorInstanceId=jobExecutionContext.getJobDetail().getJobDataMap().getString("connectorInstanceId");
		this.connectorInstanceName=jobExecutionContext.getJobDetail().getJobDataMap().getString("connectorInstanceName");
		this.eventType=jobExecutionContext.getJobDetail().getJobDataMap().getString("eventType");
		this.taskId=jobExecutionContext.getJobDetail().getJobDataMap().getString("taskId");
	}
	
	public Void execute(CommandContext commandContext) {
		
		ProcessInstanceEntity processInstance=commandContext.getProcessInstanceManager().findProcessInstanceById(this.processInstanceId);
		
		TokenEntity token=processInstance.getTokenMap().get(this.tokenId);
		
		ExecutionContext executionContext=ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(token);
		
		
		if(this.taskId!=null&&!this.taskId.equals("")){
			TaskInstanceEntity taskInstanceEntity=processInstance.getTaskMgmtInstance().getTaskInstanceEntitys(taskId);
			executionContext.setTaskInstance(taskInstanceEntity);
		}
		
		if(this.connectorId!=null&&!this.connectorId.equals("")){
			
			FlowNodeImpl flowNode=(FlowNodeImpl)processInstance.getProcessDefinition().getDefinitions().getElement(this.nodeId);
			BaseElementEvent baseElementEvent=flowNode.getEvent(this.eventType);
			List<ConnectorInstanceBehavior> connectors=baseElementEvent.getConnectors();
			ConnectorInstanceBehavior connectorDefinition=null;
			for (ConnectorInstanceBehavior connectorDefinitionObj : connectors) {
				if(connectorDefinitionObj.getConnectorInstanceId().equals(this.connectorInstanceId)){
					connectorDefinition=connectorDefinitionObj;
					break;
				}
			}
			if(connectorDefinition!=null){
				//connectorDefinition.setSkipExpression(null);
				connectorDefinition.execute(executionContext);
			}
			
			try {
				commandContext.getProcessInstanceManager().saveProcessInstance(processInstance);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new FixFlowException(e.toString(), e);
			}
			
			
		}else{
			
			
			
			throw new FixFlowBizException("定时任务启动失败");
			
		}
		
		return null;
		
		
	}

}
