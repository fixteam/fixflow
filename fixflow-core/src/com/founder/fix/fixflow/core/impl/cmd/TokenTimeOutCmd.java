package com.founder.fix.fixflow.core.impl.cmd;

import java.util.Map;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.CatchEvent;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.impl.FlowNodeImpl;

import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;
import com.founder.fix.fixflow.core.impl.persistence.ProcessInstanceManager;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.Token;

public class TokenTimeOutCmd implements Command<Void> {

	protected String tokenId;
	/**
	 * 瞬态流程实例变量Map
	 */
	protected Map<String, Object> transientVariables = null;
	
	protected String nodeId;
	 
	
	public TokenTimeOutCmd(String tokenId,String nodeId,Map<String, Object> transientVariables){
		this.tokenId = tokenId;
		this.transientVariables=transientVariables;
		this.nodeId=nodeId;
		
	}

	public Void execute(CommandContext commandContext) {
		
		ProcessEngine processEngine=ProcessEngineManagement.getDefaultProcessEngine();
		Token token=processEngine.getRuntimeService().createTokenQuery().tokenId(tokenId).singleResult();
		String processInstanceId = token.getProcessInstanceId();

		ProcessInstance processInstance=processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		
		
		ProcessInstanceManager processInstanceManager = commandContext.getProcessInstanceManager();

	
		ProcessDefinitionManager processDefinitionManager = commandContext.getProcessDefinitionManager();

		ProcessDefinitionBehavior processDefinition = processDefinitionManager.findLatestProcessDefinitionById(processInstance.getProcessDefinitionId());

		

		ProcessInstanceEntity processInstanceImpl = processInstanceManager.findProcessInstanceById(processInstanceId, processDefinition);
		TokenEntity tokenEntity = processInstanceImpl.getTokenMap().get(tokenId);
		processInstanceImpl.getContextInstance().setTransientVariableMap(transientVariables);
		
		if(nodeId==null){
			Event event=(Event)tokenEntity.getFlowNode();
			if(event instanceof BoundaryEvent){
				BoundaryEvent boundaryEvent=(BoundaryEvent)event;
				Activity activity =boundaryEvent.getAttachedToRef();
				boolean isCancelActivity=boundaryEvent.isCancelActivity();
				if(isCancelActivity){
					//如果是终止事件 则结束进入节点的时候的散发的所有子令牌 然后将父令牌 移动到超时节点往下进行
					tokenEntity.signalKillChildMoveParentToken(boundaryEvent,activity);
				}
				else{
					//如果不是终止事件 则默认方法驱动令牌
					tokenEntity.signal();
				}
				
			}
			if(event instanceof CatchEvent){
				tokenEntity.signal();
			}
			
		}
		else{
			BaseElement baseElement=processDefinition.getDefinitions().getElement(nodeId);
			if(baseElement instanceof BoundaryEvent){
				BoundaryEvent boundaryEvent=(BoundaryEvent)baseElement;
				Activity activity =boundaryEvent.getAttachedToRef();
				
				//String nodeTokenId = this.getId();
				// 创建分支令牌并添加到集合中
				boolean isCancelActivity=boundaryEvent.isCancelActivity();
				if(isCancelActivity){
					
					//设置超时节点
					//nodeChildExecutionContext.setTimeOutNode(nodeToken.getFlowNode());
					//ExecutionContext executionContext=ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(tokenEntity);
					tokenEntity.signalKillChildMoveParentToken(boundaryEvent,activity);
					//boundaryEvent.leave(executionContext);
				}
				else{
					TokenEntity nodeToken=((FlowNodeImpl)tokenEntity.getFlowNode()).createForkedToken(tokenEntity, boundaryEvent.getId()).token;
					
					ExecutionContext nodeChildExecutionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(nodeToken);
					//设置超时节点
					nodeChildExecutionContext.setTimeOutNode(activity);
					boundaryEvent.leave(nodeChildExecutionContext);
					//this.forkedTokenEnter(nodeChildExecutionContext);
				}
				
				
			}
		}
		
		try {
			processInstanceManager.saveProcessInstance(processInstanceImpl);
		} catch (Exception e) {
			throw new FixFlowException("流程实例持久化失败!", e);
		}
		return null;
	}
}
