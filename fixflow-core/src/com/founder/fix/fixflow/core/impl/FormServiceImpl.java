package com.founder.fix.fixflow.core.impl;



import com.founder.fix.fixflow.core.FormService;
import com.founder.fix.fixflow.core.impl.cmd.GetStartFormByKeyCmd;
import com.founder.fix.fixflow.core.impl.cmd.GetTaskFormByNodeId;



public class FormServiceImpl extends ServiceImpl implements FormService {



	public String getStartFormByKey(String processDefinitionKey) {
		
		return commandExecutor.execute(new GetStartFormByKeyCmd<String>(processDefinitionKey));
	}

	public String getStartFormById(String processDefinitionId) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTaskFormByNodeId(String processDefinitionId, String nodeId) {
		return commandExecutor.execute(new GetTaskFormByNodeId<String>(processDefinitionId,nodeId));
	}
	
	



}
