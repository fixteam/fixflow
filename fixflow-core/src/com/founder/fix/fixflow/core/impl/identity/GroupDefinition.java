/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 */
package com.founder.fix.fixflow.core.impl.identity;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.founder.fix.bpmn2extensions.coreconfig.GroupInfo;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.internationalization.FixFlowResources;

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
		Boolean booleanTemp = StringUtil.getBoolean(Context.getProcessEngineConfiguration().getInternationalizationConfig().getIsEnable());
		// 用户名称国际化处理
		if (booleanTemp) {
			FixFlowResources fixFlowResources = Context.getProcessEngineConfiguration().getFixFlowResources();
			String nameTemp = fixFlowResources.getResourceName(FixFlowResources.OrganizationResource, "FixFlow_"+id+"_Name");
			if (nameTemp == null || nameTemp.equals("")) {
				return name;
			} else {
				return StringUtil.getString(nameTemp);
			}
		} else {
			return name;
		}

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
	 * @param groupId组编号
	 * @return 组对象
	 */
	public abstract GroupTo findGroupByGroupId(String groupId);

	/**
	 * 通过用户名获取用户所在的组
	 * @param userId用户编号
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
	 * @param groupId组编号
	 * @return
	 */
	public abstract List<UserTo> findUserChildMembersIncludeByGroupId(String groupId);

	/**
	 * 获取组下面的用户(不包含子组的用户)
	 * @param groupId组编号
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
	 * 查询组集合
	 * @param page
	 * @param queryMap
	 * @return
	 */
	public abstract Map<String, Object> findGroups(Page page,Map<String,Object> queryMap);

	/**
	 * 获取数据库操作类
	 * @return
	 */
	public SqlCommand getSqlCommand() {
		String dataBaseId=Context.getProcessEngineConfiguration().getFixFlowConfig().getDesignerOrgConfig().getDataBaseId();
		Connection connection = Context.getDbConnection(dataBaseId);// Context.getDbConnection();
		SqlCommand sqlCommand = new SqlCommand(connection);
		return sqlCommand;
	}
}
