package com.founder.fix.fixflow.expand.actorconnector.StartAuthorActorConnector;


import java.util.*;
import com.founder.fix.fixflow.core.connector.ActorConnectorHandler;
import com.founder.fix.fixflow.core.impl.identity.GroupTo;
import com.founder.fix.fixflow.core.impl.identity.UserTo;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public class StartAuthorActorConnector implements ActorConnectorHandler {

	/**
	* 获取用户类型处理者
	* @param executionContext 流程上下文
	* @return
	*/
	public List<UserTo> UserExecute(ExecutionContext executionContext){
		List<UserTo> userTos = new ArrayList<UserTo>();
		UserTo userTo = new UserTo(executionContext.getStartAuthor());
		userTos.add(userTo);
		return userTos;
	}

	/**
	* 获取组类型处理者
	* @param executionContext 流程上下文
	* @return
	*/
	public List<GroupTo> GroupExecute(ExecutionContext executionContext){
		List<GroupTo> groupTos = new ArrayList<GroupTo>();
		//加入Group 	GroupTo groupTo = new GroupTo("组编号", "组类型");
		return groupTos;
	}

}