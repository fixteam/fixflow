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
package com.founder.fix.fixflow.core.impl.cmd;

import java.util.Map;


import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.command.StartProcessInstanceCommand;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.Token;


public class NoneStartProcessInstanceCmd<T> implements Command<ProcessInstance> {

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
	
	/**
	 * 父流程实例
	 */
	protected ProcessInstanceEntity parentProcessInstance;
	
	protected Token parentProcessInstanceToken;
	

	/**
	 * 流程实例启动操作
	 * @param processDefinitionKey
	 * @param processDefinitionId
	 * @param businessKey
	 * @param startAuthor
	 * @param transientVariables
	 * @param variables
	 */
	public NoneStartProcessInstanceCmd(StartProcessInstanceCommand startProcessInstanceCommand) {
		
		
		String processDefinitionKey=startProcessInstanceCommand.getProcessDefinitionKey();
		String processDefinitionId=startProcessInstanceCommand.getProcessDefinitionId();
		String businessKey=startProcessInstanceCommand.getBusinessKey();
		String startAuthor=startProcessInstanceCommand.getStartAuthor();
		Map<String, Object> transientVariables= startProcessInstanceCommand.getTransientVariables();
		Map<String, Object> variables=startProcessInstanceCommand.getVariables();	
		
		
		this.processDefinitionKey = processDefinitionKey;
		this.processDefinitionId = processDefinitionId;
		this.businessKey = businessKey;
		this.startAuthor = startAuthor;
		this.transientVariables = transientVariables;
		this.variables = variables;
		this.parentProcessInstance=startProcessInstanceCommand.getParentProcessInstance();
		this.parentProcessInstanceToken=startProcessInstanceCommand.getParentProcessInstanceToken();
		
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
			/*if(parentProcessInstance!=null){
				
				if(parentProcessInstanceToken!=null){
					processInstanceEntity=new ProcessInstanceEntity(processDefinition, businessKey, parentProcessInstance, (TokenEntity) parentProcessInstanceToken);
					
				}
				else{
					throw new FixFlowException("子流程启动失败");
				}
				
			}
			else{
				processInstanceEntity =  processDefinition.createProcessInstance(businessKey);

			}*/
			processInstanceEntity =  processDefinition.createProcessInstance(businessKey);
			
			processInstanceEntity.setStartAuthor(startAuthor);
			processInstanceEntity.getContextInstance().setTransientVariableMap(transientVariables);
			processInstanceEntity.getContextInstance().setVariableMap(variables);
	

			processInstanceEntity.noneStart();
			
			
			commandContext.getProcessInstanceManager().saveProcessInstance(processInstanceEntity);

		} catch (Exception e) {
			throw new FixFlowException("流程实例启动异常! "+ e.getMessage(), e);
		}

		return processInstanceEntity;
	}

}
