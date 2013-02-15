package com.founder.fix.fixflow.core.impl.cmd;



import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;

public class DeleteProcessInstanceByInstanceIdAndDefKeyCmd implements Command<Boolean> {

	
	
	protected String processDefinitionKey;
	protected String businessKey;
	protected boolean cascade;
	
	
	
	
	public DeleteProcessInstanceByInstanceIdAndDefKeyCmd(String processDefinitionKey,String businessKey, boolean cascade){
		
		this.processDefinitionKey=processDefinitionKey;
		this.businessKey=businessKey;
		this.cascade=cascade;
		
	}
	
	
	public Boolean execute(CommandContext commandContext) {
		// TODO Auto-generated method stub
		
		ProcessInstanceEntity processInstanceQueryTo=commandContext.getProcessInstanceManager().findProcessInstanceByDefKeyAndBusinessKey(processDefinitionKey, businessKey);
		if(processInstanceQueryTo==null){
			return false;
		}
		String  processInstanceId=processInstanceQueryTo.getId();
		
		commandContext.getProcessInstanceManager().deleteProcessInstance(processInstanceId, cascade);
		
		return true;
	}
	
	
	

}
