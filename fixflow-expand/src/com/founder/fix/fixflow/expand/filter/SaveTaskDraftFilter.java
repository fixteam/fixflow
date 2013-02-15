package com.founder.fix.fixflow.expand.filter;

import com.founder.fix.fixflow.core.impl.filter.AbstractCommandFilter;
import com.founder.fix.fixflow.core.task.TaskInstance;

public class SaveTaskDraftFilter extends AbstractCommandFilter {

	@Override
	public boolean accept(TaskInstance taskInstance) {

		if(taskInstance==null){
			return true;
		}
		// TODO 自动生成的方法存根
		if(!taskInstance.hasEnded()&&taskInstance.getAssignee()!=null&&!taskInstance.isSuspended()){
			return true;
		}
		else{
			return false;
		}
	}

}
