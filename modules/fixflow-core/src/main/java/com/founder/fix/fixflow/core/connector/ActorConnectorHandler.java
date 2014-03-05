package com.founder.fix.fixflow.core.connector;

import java.util.List;

import com.founder.fix.fixflow.core.impl.identity.GroupTo;
import com.founder.fix.fixflow.core.impl.identity.UserTo;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public interface ActorConnectorHandler {
	
	
	/**
	 * 获取用户类型处理者
	 * @param executionContext 流程上下文
	 * @return
	 */
	List<UserTo> UserExecute(ExecutionContext executionContext);
	
	/**
	 * 获取组类型处理者
	 * @param executionContext 流程上下文
	 * @return
	 */
	List<GroupTo> GroupExecute(ExecutionContext executionContext);
	
	
	
}
