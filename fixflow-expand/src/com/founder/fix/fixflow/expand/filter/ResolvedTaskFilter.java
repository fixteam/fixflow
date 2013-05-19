package com.founder.fix.fixflow.expand.filter;

import com.founder.fix.fixflow.core.impl.filter.AbstractCommandFilter;
import com.founder.fix.fixflow.core.task.DelegationState;
import com.founder.fix.fixflow.core.task.TaskInstance;

public class ResolvedTaskFilter extends AbstractCommandFilter {

	@Override
	public boolean accept(TaskInstance taskInstance) {




		if(taskInstance==null){
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
			
			
			if(taskInstance.getDelegationState()!=null&&taskInstance.getDelegationState().equals(DelegationState.RESOLVED)){
				return true;
			}
			else {
				return false;
			}
			
			
			
		}
		else{
			if(isAutoClaim()){
				if(taskInstance.getDelegationState()!=null&&taskInstance.getDelegationState().equals(DelegationState.RESOLVED)){
					return true;
				}
				else {
					return false;
				}
			}else{
				return false;
			}
		}
		
		
		
		
	}

}
