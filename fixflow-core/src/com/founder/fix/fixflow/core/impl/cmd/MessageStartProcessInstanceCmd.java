package com.founder.fix.fixflow.core.impl.cmd;

import java.util.Map;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.FlowNode;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.command.MessageStartProcessInstanceCommand;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;

public class MessageStartProcessInstanceCmd <T> implements Command<ProcessInstance>{
	
	/**
	 * 流程定义id，唯一编号,不能为空。(数据库中的 id)
	 */
	protected String processDefinitionId;

	/**
	 * 流程定义key(xml定义里的 process id,数据库中的 key)
	 */
	protected String processDefinitionKey;

	/**
	 * 业务关联键
	 */
	protected String businessKey;

	/**
	 * 启动
	 */
	protected String startAuthor;

	/**
	 * 瞬态流程实例变量Map
	 */
	protected Map<String, Object> transientVariables = null;

	/**
	 * 持久化流程实例变量Map
	 */
	protected Map<String, Object> variables = null;
	
	protected String messageId;
	
	protected String nodeId;

	/**
	 * 流程实例启动操作
	 * @param processDefinitionKey
	 * @param processDefinitionId
	 * @param businessKey
	 * @param startAuthor
	 * @param transientVariables
	 * @param variables
	 */
	public MessageStartProcessInstanceCmd(MessageStartProcessInstanceCommand messageStartProcessInstanceCommand) {
		
		
		String processDefinitionKey=messageStartProcessInstanceCommand.getProcessDefinitionKey();
		String processDefinitionId=messageStartProcessInstanceCommand.getProcessDefinitionId();
		String businessKey=messageStartProcessInstanceCommand.getBusinessKey();
		String startAuthor=messageStartProcessInstanceCommand.getStartAuthor();
		Map<String, Object> transientVariables= messageStartProcessInstanceCommand.getTransientVariables();
		Map<String, Object> variables=messageStartProcessInstanceCommand.getVariables();	
		this.messageId=messageStartProcessInstanceCommand.getMessageId();
		this.nodeId=messageStartProcessInstanceCommand.getNodeId();
		this.processDefinitionKey = processDefinitionKey;
		this.processDefinitionId = processDefinitionId;
		this.businessKey = businessKey;
		this.startAuthor = startAuthor;
		this.transientVariables = transientVariables;
		this.variables = variables;
		
		
		
	}

	public ProcessInstance execute(CommandContext commandContext) {

		ProcessDefinitionManager processDefinitionManager = commandContext.getProcessDefinitionManager();

		ProcessDefinitionBehavior processDefinition = null;
		if (processDefinitionId != null) {
			processDefinition = processDefinitionManager.findLatestProcessDefinitionById(processDefinitionId);
			if (processDefinition == null) {
				throw new FixFlowException("通过 processDefinitionId 没有找到指定流程 = '" + processDefinitionId + "'");
			}
		} else if (processDefinitionKey != null) {
			processDefinition = processDefinitionManager.findLatestProcessDefinitionByKey(processDefinitionKey);
			if (processDefinition == null) {
				throw new FixFlowException("通过 processDefinitionKey 没有找到指定流程 '" + processDefinitionKey + "'");
			}
		} else {
			throw new FixFlowException("processDefinitionKey 和 processDefinitionId 不能都为空");
		}

		ProcessInstanceEntity processInstanceEntity = null;
		try {
			
			processInstanceEntity =  processDefinition.createProcessInstance(businessKey);

			processInstanceEntity.setStartAuthor(startAuthor);
			processInstanceEntity.getContextInstance().setTransientVariableMap(transientVariables);
			processInstanceEntity.getContextInstance().setVariableMap(variables);
	
			BaseElement baseElement=processDefinition.getDefinitions().getElement(nodeId);
			
			if(baseElement !=null){
				processInstanceEntity.start((FlowNode)baseElement);
			}
			else{
				throw new FixFlowException("没有找到messageId为 "+ messageId+" 的消息启动节点");
			}
			
			

			
			
			
			commandContext.getProcessInstanceManager().saveProcessInstance(processInstanceEntity);

		} catch (Exception e) {
			throw new FixFlowException("流程实例启动异常! "+ e.getMessage(), e);
		}

		return processInstanceEntity;
	}


}
