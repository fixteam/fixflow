package com.founder.fix.fixflow.core.impl.identity;

import java.sql.Connection;
import java.util.List;

import com.founder.fix.bpmn2extensions.coreconfig.GroupInfo;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;


public abstract class GroupDefinition {
	
	/**
	 * 组定义的编号
	 */
	protected String id;
	
	

	
	/**
	 * 组定义的类型
	 */
	protected String name;
	
	
	/**
	 * 组配置信息
	 */
	protected GroupInfo groupInfo;

	

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public GroupInfo getGroupInfo() {
		return groupInfo;
	}

	public void setGroupInfo(GroupInfo groupInfo) {
		this.groupInfo = groupInfo;
	}
	
	/**
	 * 通过组对象
	 * @param groupId 组编号
	 * @return 组对象
	 */
	public abstract GroupTo findGroupByGroupId(String groupId);

	/**
	 * 通过用户名获取用户所在的组
	 * @param userId 用户编号
	 * @return 用户所在的组集合
	 */
	public abstract List<GroupTo> findGroupMembersByUser(String userId);
	
	/**
	 * 通过组编号获取下面的子组(不包含父组)
	 * @param groupId 组编号
	 * @return 子组的集合(不包含父组)
	 */
	public abstract List<GroupTo> findGroupChildMembersByGroupId(String groupId);
	
	/**
	 * 通过组编号获取下面的子组(包含父组)
	 * @param groupId 组编号
	 * @return 子组的集合(包含父组)
	 */
	public abstract List<GroupTo> findGroupChildMembersIncludeByGroupId(String groupId);
	
	
	
	
	/**
	 * 获取组下面的用户(包含子组的用户)
	 * @param groupId 组编号
	 * @return
	 */
	public abstract List<UserTo> findUserChildMembersIncludeByGroupId(String groupId);
	
	/**
	 * 获取组下面的用户(不包含子组的用户)
	 * @param groupId 组编号
	 * @return
	 */
	public abstract List<UserTo> findUserByGroupId(String groupId);
	
	/**
	 * 获取父组
	 * @param groupId 组编号
	 * @return
	 */
	public abstract GroupTo findParentGroupByGroupId(String groupId);
	
	/**
	 * 获取数据库操作类
	 * @return
	 */
	public SqlCommand getSqlCommand(){
		
		Connection connection = Context.getDbConnection();

		SqlCommand sqlCommand = new SqlCommand(connection);
		
		return sqlCommand;
		
	}
	
}
