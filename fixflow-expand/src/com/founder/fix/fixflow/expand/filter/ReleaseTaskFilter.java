package com.founder.fix.fixflow.expand.filter;

import com.founder.fix.fixflow.core.impl.filter.AbstractCommandFilter;
import com.founder.fix.fixflow.core.task.TaskInstance;

public class ReleaseTaskFilter extends AbstractCommandFilter {

	@Override
	public boolean accept(TaskInstance taskInstance) {

		if(taskInstance==null){
			return false;
		}
		
		if(isAutoClaim()){
			return false;
		}
		
		
		
		
		
	
		if(taskInstance.isSuspended()){
			return false;
		}
		
		if(taskInstance.hasEnded()){
			return false;
		}
		
		if(isProcessTracking()){
			return false;
		}
		
		if(taskInstance.getDelegationState()!=null){
			return false;
		}

		if(taskInstance.getAssignee()!=null){
			
			return true;
			
		}
		else{
			
			return false;
			
		}
		
		
	
		
		
	}

}
