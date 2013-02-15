package com.founder.fix.fixflow.core.impl.cmd;

import java.util.Map;

import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;
import com.founder.fix.fixflow.core.impl.persistence.ProcessInstanceManager;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.Token;

public class TokenSignalCmd implements Command<Void> {

	protected String tokenId;
	
	/**
	 * 瞬态流程实例变量Map
	 */
	protected Map<String, Object> transientVariables = null;
	
	protected Map<String, Object> dataVariables = null;
	

	public TokenSignalCmd(String tokenId,Map<String, Object> transientVariables,Map<String, Object> dataVariables) {
		this.tokenId = tokenId;
		this.transientVariables=transientVariables;
		this.dataVariables=dataVariables;
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
		if(transientVariables!=null&&transientVariables.keySet().size()>0){
			processInstanceImpl.getContextInstance().setTransientVariableMap(transientVariables);
		
		}
		if(dataVariables!=null&&dataVariables.keySet().size()>0){
			processInstanceImpl.getContextInstance().setDataVariable(dataVariables);
		}
		
		tokenEntity.signal();

		try {
			processInstanceManager.saveProcessInstance(processInstanceImpl);
		} catch (Exception e) {
			throw new FixFlowException("流程实例持久化失败!", e);
		}
		return null;
		
		

	}

}
