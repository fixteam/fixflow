package com.founder.fix.fixflow.expand.cmd;

import java.util.Map;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.FlowNode;

import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.cmd.AbstractCommand;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public class ProcessResurgenceCmd extends AbstractCommand<Void> {

	private String processInstanceId;
	private String nodeId;
	
	
	public ProcessResurgenceCmd(Map<String, Object> parameterMap) {
		super(parameterMap);
		this.processInstanceId=StringUtil.getString(parameterMap.get("processInstanceId"));
		this.nodeId=StringUtil.getString(parameterMap.get("nodeId"));
		
	}

	
	public Void execute(CommandContext commandContext) {
		
		//删除所有之前任务
		commandContext.getTaskManager().deleteTaskByProcessInstanceId(processInstanceId);
		
		//获取流程实例对象
		ProcessInstanceEntity processInstance=commandContext.getProcessInstanceManager().findProcessInstanceById(processInstanceId);

		
		//救活它。。
		processInstance.setEndTime(null);
		//获取根令牌
		TokenEntity token=processInstance.getRootToken();
		//救活它。。
		token.setEndTime(null);
		//获取流程定义
		ProcessDefinitionBehavior processDefinition=processInstance.getProcessDefinition();
		//获取指定的节点
		BaseElement baseElement=processDefinition.getDefinitions().getElement(nodeId);
		if(baseElement instanceof FlowNode){
			
			FlowNode flowNode= (FlowNode)baseElement;
			ExecutionContext executionContext=ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(token);
			//让令牌进入节点开始走流程
			flowNode.enter(executionContext);
			
		}else{
			
		}
		commandContext.getProcessInstanceManager().saveProcessInstance(processInstance);
		
		return null;
	}

}
