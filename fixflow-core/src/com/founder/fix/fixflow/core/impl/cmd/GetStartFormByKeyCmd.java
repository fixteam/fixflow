package com.founder.fix.fixflow.core.impl.cmd;



import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;


public class GetStartFormByKeyCmd<T> implements Command<String>{

	protected String processDefinitionKey;
	
	public GetStartFormByKeyCmd(String processDefinitionKey)
	{
		this.processDefinitionKey=processDefinitionKey;
	}
	
	
	public String execute(CommandContext commandContext) {
		// TODO Auto-generated method stub
		ProcessDefinitionManager processDefinitionManager =commandContext.getProcessDefinitionManager();

		ProcessDefinitionBehavior processDefinition = processDefinitionManager
				.findLatestProcessDefinitionByKey(processDefinitionKey);
		if(processDefinition==null)
		{
			throw new FixFlowException("Key 为 :"+processDefinitionKey+" 的流程定义不存在!");
		}
		
		
		

		return processDefinition.getStartFormKey();
	}

}
