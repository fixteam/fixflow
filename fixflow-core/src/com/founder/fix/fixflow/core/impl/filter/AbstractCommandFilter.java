package com.founder.fix.fixflow.core.impl.filter;

import com.founder.fix.fixflow.core.task.TaskInstance;

public abstract class AbstractCommandFilter {
	

	public abstract boolean accept(TaskInstance taskInstance);
	

}
