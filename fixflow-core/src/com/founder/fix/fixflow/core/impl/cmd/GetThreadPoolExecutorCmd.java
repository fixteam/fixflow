package com.founder.fix.fixflow.core.impl.cmd;

import java.util.concurrent.ThreadPoolExecutor;

import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.threadpool.FixThreadPoolExecutor;

public class GetThreadPoolExecutorCmd implements Command<ThreadPoolExecutor>{

	protected String threadPoolKey;
	
	public GetThreadPoolExecutorCmd(String threadPoolKey){
		this.threadPoolKey=threadPoolKey;
	}
	
	public ThreadPoolExecutor execute(CommandContext commandContext) {
		if(threadPoolKey==null||threadPoolKey.equals("")){
			threadPoolKey="default";
		}
		ProcessEngineConfigurationImpl processEngineConfigurationImpl=commandContext.getProcessEngineConfigurationImpl();
		FixThreadPoolExecutor fixThreadPoolExecutor= processEngineConfigurationImpl.getThreadPoolMap().get(threadPoolKey);
		return fixThreadPoolExecutor;
	}

}
