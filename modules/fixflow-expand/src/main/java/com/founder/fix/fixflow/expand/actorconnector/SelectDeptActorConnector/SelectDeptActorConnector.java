package com.founder.fix.fixflow.expand.actorconnector.SelectDeptActorConnector;


import java.util.*;

import com.founder.fix.fixflow.core.connector.ActorConnectorHandler;
import com.founder.fix.fixflow.core.impl.identity.GroupTo;
import com.founder.fix.fixflow.core.impl.identity.UserTo;
import com.founder.fix.fixflow.core.impl.util.AssigneeUtil;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public class SelectDeptActorConnector implements ActorConnectorHandler {

	private java.lang.Object deptId;

	/**
	* 获取用户类型处理者
	* @param executionContext 流程上下文
	* @return
	*/
	public List<UserTo> UserExecute(ExecutionContext executionContext) {
		List<UserTo> userTos = new ArrayList<UserTo>();
		//加入UserTo UserTo userTo = new UserTo("用户编号");
		return userTos;
	}

	/**
	* 获取组类型处理者
	* @param executionContext 流程上下文
	* @return
	*/
	public List<GroupTo> GroupExecute(ExecutionContext executionContext) {
		List<GroupTo> groupTos = new ArrayList<GroupTo>();
		List<String> deptList=AssigneeUtil.executionExpressionObj(deptId, executionContext);
		
		
		for (String deptId : deptList) {
			GroupTo userTo = new GroupTo(deptId,"dept");
			groupTos.add(userTo);
		}
		return groupTos;
	}

	public void  setDeptId(java.lang.Object deptId){
		this.deptId = deptId;
	}

}