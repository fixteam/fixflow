package com.founder.fix.fixflow.expand.filter;

import com.founder.fix.fixflow.core.impl.filter.AbstractCommandFilter;
import com.founder.fix.fixflow.core.task.TaskInstance;

public class SuspendTaskFilter extends AbstractCommandFilter {

	@Override
	public boolean accept(TaskInstance taskInstance) {
		

		if(taskInstance==null){
			return false;
		}
		
		if(!isProcessTracking()&&!taskInstance.hasEnded()&&taskInstance.getAssignee()!=null&&taskInstance.getDelegationState()==null&&!taskInstance.isSuspended()){
			return true;
		}
		else{
			return false;
		}
	}

}
