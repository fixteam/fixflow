package com.founder.fix.fixflow.core;

import java.util.List;

import com.founder.fix.fixflow.core.impl.identity.GroupDefinition;
import com.founder.fix.fixflow.core.impl.identity.GroupTo;
import com.founder.fix.fixflow.core.impl.identity.UserDefinition;
import com.founder.fix.fixflow.core.impl.identity.UserTo;


public interface IdentityService extends ProcessService{
	

	/**
	 * 获取组定义根据组类型
	 * @param groupType 组的类型
	 * @return
	 */
	GroupDefinition getGroupDefinition(String groupType);

	/**
	 * 获取组对象
	 * @param groupId 组对象编号
	 * @param groupType 组对象类型
	 * @return
	 */
	GroupTo getGroup(String groupId,String groupType);
	
	/**
	 * 获取用户操作定义
	 * @return
	 */
	UserDefinition getUserDefinition();
	
	/**
	 * 获取用户对象
	 * @param userId 用户编号
	 * @return 用户对象
	 */
	UserTo getUserTo(String userId);
	
	
	boolean verificationStartUserByKey(String userId,String processDefinitionKey);

	boolean verificationStartUserById(String userId,String processDefinitionId);
	
	/**
	 * 获取组下面的用户(包含子集)
	 * @param groupId 组编号
	 * @param groupType 类型
	 * @return
	 */
	List<UserTo> getUserInGroupChildMembersInclude(String groupId,String groupType);
	
	/**
	 * 获取组下面的用户(不包含子集)
	 * @param groupId 组编号
	 * @param groupType 类型
	 * @return
	 */
	List<UserTo> getUserInGroup(String groupId,String groupType);
	
   
}
