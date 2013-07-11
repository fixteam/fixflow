package com.founder.fix.fixflow.core.impl.identity;

import java.util.ArrayList;

import java.util.List;

import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.impl.Context;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * @author kenshin
 */
public abstract class Authentication {

	static ThreadLocal<String> authenticatedUserIdThreadLocal = new ThreadLocal<String>();

	
	
	
	
	public static void setAuthenticatedUserId(String authenticatedUserId) {

		authenticatedUserIdThreadLocal.set(authenticatedUserId);
	}

	public static String getAdminId() {
		
		
		
		return ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getAuthenticationInstance().getAdminId();
	}
	
	/**
	 * 获取系统自动处理的账号
	 * @return
	 */
	public static String getSystemId(){
		return ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getAuthenticationInstance().getSystemId();
	}

	public static String getAuthenticatedUserId() {
		
		if(authenticatedUserIdThreadLocal.get()==null){
			String userIdString=ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getAuthenticationInstance().getAuthenticatedUserId();
			if(userIdString!=null){
				setAuthenticatedUserId(userIdString);
			}
		}
		return authenticatedUserIdThreadLocal.get();
	}

	public static List<GroupTo> findGroupsByUser(String userId) {

		List<GroupTo> groupTos = new ArrayList<GroupTo>();

		List<GroupDefinition> groupDefinitions = Context.getProcessEngineConfiguration().getGroupDefinitions();
		for (GroupDefinition groupDefinition : groupDefinitions) {
			List<GroupTo> tempGroupTos = groupDefinition.findGroupMembersByUser(userId);
			if (tempGroupTos != null && tempGroupTos.size() > 0) {
				groupTos.addAll(tempGroupTos);
			}
		}
		return groupTos;

	}

	public static GroupTo findGroupByGroupIdAndType(String groupId, String groupType) {

		GroupTo groupTo = null;

		List<GroupDefinition> groupDefinitions = Context.getProcessEngineConfiguration().getGroupDefinitions();
		for (GroupDefinition groupDefinition : groupDefinitions) {
			if (groupDefinition.getId().equals(groupType)) {
				groupTo = groupDefinition.findGroupByGroupId(groupId);
			}
		}

		return groupTo;

	}

	public static GroupDefinition groupDefinition(String groupType) {
		List<GroupDefinition> groupDefinitions = Context.getProcessEngineConfiguration().getGroupDefinitions();
		for (GroupDefinition groupDefinition : groupDefinitions) {
			if (groupDefinition.getId().equals(groupType)) {
				return groupDefinition;
			}
		}
		return null;
	}

	/**
	 * 通过组编号获取下面的子组(包含父组)
	 * 
	 * @param groupId
	 *            组编号
	 * @return 子组的集合(包含父组)
	 */
	public static List<GroupTo> findGroupChildMembersIncludeByGroupId(String groupId, String groupType) {
		List<GroupTo> groupTo = null;

		List<GroupDefinition> groupDefinitions = Context.getProcessEngineConfiguration().getGroupDefinitions();
		for (GroupDefinition groupDefinition : groupDefinitions) {
			if (groupDefinition.getId().equals(groupType)) {
				groupTo = groupDefinition.findGroupChildMembersIncludeByGroupId(groupId);
			}
		}

		return groupTo;
	}

	public static UserTo findUserInfoByUserId(String userId) {

		UserDefinition userDefinition = Context.getProcessEngineConfiguration().getUserDefinition();
		UserTo userTo = userDefinition.findUserByUserId(userId);
		return userTo;
	}

	public static List<String> findUserDeptAndRole(String deptId, String roleId) {

		GroupTo groupDetp = new GroupTo(deptId, "dept");

		GroupTo groupRole = new GroupTo(roleId, "role");

		List<GroupTo> groupTos = new ArrayList<GroupTo>();
		groupTos.add(groupRole);
		groupTos.add(groupDetp);

		List<UserTo> userTos = findUserCondition(groupTos);
		List<String> userList = new ArrayList<String>();
		for (UserTo userTo : userTos) {
			userList.add(userTo.getUserId());
		}
		return userList;

	}

	public static List<UserTo> findUserCondition(List<GroupTo> jGroups) {

		return findUserCondition(jGroups, null, null, null);

	}

	public static List<UserTo> findUserCondition(List<GroupTo> jGroups, List<GroupTo> bGroups, List<UserTo> excludeUsers, List<GroupTo> excludeGroups) {

		List<UserTo> userTosObj = new ArrayList<UserTo>();

		List<List<String>> userListString = new ArrayList<List<String>>();

		for (GroupTo groupTo : jGroups) {
			GroupDefinition groupDefinition = groupDefinition(groupTo.getGroupType());
			List<UserTo> userTos = groupDefinition.findUserChildMembersIncludeByGroupId(groupTo.getGroupId());
			List<String> userListObj = new ArrayList<String>();
			for (UserTo userTo : userTos) {
				userListObj.add(userTo.getUserId());
			}
			userListString.add(userListObj);
		}

		for (int i = 0; i < userListString.size() - 1; i++) {
			userListString.get(0).retainAll(userListString.get(i + 1));
		}

		for (String list : userListString.get(0)) {
			UserTo userTo = new UserTo(list, null, null);
			userTosObj.add(userTo);
		}

		return userTosObj;
	}

	public static GroupTo findParentGroupByGroupId(String groupId, String groupType) {

		GroupDefinition groupDefinition = groupDefinition(groupType);

		GroupTo groupTo = groupDefinition.findParentGroupByGroupId(groupId);

		return groupTo;

	}

	/**
	 * 获用户所在的最大级别组
	 * 
	 * @param userId
	 * @param groupType
	 * @return
	 */
	public static GroupTo findMaxLevelGroupByUserId(String userId, String groupType) {

		GroupDefinition groupDefinition = groupDefinition(groupType);

		List<GroupTo> groupTos = groupDefinition.findGroupMembersByUser(userId);

		ComparatorGroupTo comparatorGroupTo = new ComparatorGroupTo();

		Collections.sort(groupTos, comparatorGroupTo);

		return groupTos.get(0);

	}

	public static List<GroupTo> findGroupsByUser(String userId, String groupType) {

		List<GroupTo> groupTos = new ArrayList<GroupTo>();

		List<GroupDefinition> groupDefinitions = Context.getProcessEngineConfiguration().getGroupDefinitions();
		for (GroupDefinition groupDefinition : groupDefinitions) {
			if (groupDefinition.getId().equals(groupType)) {
				List<GroupTo> tempGroupTos = groupDefinition.findGroupMembersByUser(userId);
				if (tempGroupTos != null && tempGroupTos.size() > 0) {
					groupTos.addAll(tempGroupTos);
				}
			}
		}
		return groupTos;

	}

}
