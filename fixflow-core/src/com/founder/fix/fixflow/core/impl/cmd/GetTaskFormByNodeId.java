package com.founder.fix.fixflow.core.impl.cmd;


import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessDefinitionManager;

public class GetTaskFormByNodeId<T> implements Command<String> {
	
	protected String processDefinitionId;
	protected String nodeId;

	public GetTaskFormByNodeId(String processDefinitionId, String nodeId) {
		this.processDefinitionId = processDefinitionId;
		this.nodeId = nodeId;
	}

	public String execute(CommandContext commandContext) {

		ProcessDefinitionManager processDefinitionManager = commandContext.getProcessDefinitionManager();

		ProcessDefinitionBehavior processDefinition = processDefinitionManager
				.findLatestProcessDefinitionById(processDefinitionId);
		UserTaskBehavior userTask = (UserTaskBehavior) processDefinition.getDefinitions().getElement(nodeId);
		
		Object returnFormUri=ExpressionMgmt.execute(userTask.getFormUri(),processDefinition);
		if(returnFormUri!=null && !returnFormUri.equals("")){
			return returnFormUri.toString();
		}
		else{
			
			String defaultFormUri=processDefinition.getDefaultFormUri();
			if(defaultFormUri != null && !defaultFormUri.equals("")){
				
					return defaultFormUri.toString();
				
			}
			
	}
		
		return null;
	}
}
