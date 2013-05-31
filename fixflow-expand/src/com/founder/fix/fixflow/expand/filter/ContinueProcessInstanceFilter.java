package com.founder.fix.fixflow.expand.filter;

import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.impl.filter.AbstractCommandFilter;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.task.TaskInstance;

public class ContinueProcessInstanceFilter extends AbstractCommandFilter {

	@Override
	public boolean accept(TaskInstance taskInstance) {
		

		if(taskInstance==null){
			return false;
		}
		
		if(!isProcessTracking()&&!taskInstance.hasEnded()&&taskInstance.getAssignee()!=null&&taskInstance.getDelegationState()==null&&taskInstance.isSuspended()){
			
			
			ProcessEngine processEngine=ProcessEngineManagement.getDefaultProcessEngine();
			ProcessInstance processInstance=processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(taskInstance.getProcessInstanceId()).singleResult();
			if(processInstance.isSuspended()){
				return true;
			}
			
			return true;
		}
		else{
			return false;
		}
	}

}
