package com.founder.fix.fixflow.expand.filter;

import com.founder.fix.fixflow.core.impl.filter.AbstractCommandFilter;
import com.founder.fix.fixflow.core.task.TaskInstance;

public class ClaimTaskFilter extends AbstractCommandFilter {

	@Override
	public boolean accept(TaskInstance taskInstance) {
		// TODO 自动生成的方法存根
		
		if(taskInstance==null){
			return false;
		}
		
		if(!taskInstance.hasEnded()&&taskInstance.getAssignee()==null&&taskInstance.getDelegationState()==null&&!taskInstance.isSuspended()){
			return true;
		}
		else{
			return false;
		}
		
	}

}
