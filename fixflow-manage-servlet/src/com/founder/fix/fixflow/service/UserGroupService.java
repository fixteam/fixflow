/**
 *  Copyright 1996-2013 Founder International Co.,Ltd.
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
 * @author yangchenhui
 */
package com.founder.fix.fixflow.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.Page;

/**
 * 用户组管理
 * @author Administrator
 *
 */
public interface UserGroupService {
	
	/**
	 * 用户
	 * @param page
	 * pageIndex 页数
	 * pageSize 页大小
	 * queryUserId 查询userId
	 * queryUserName 查询userName
	 * @return
	 * @throws SQLException 
	 */
	public Map<String, Object> getAllUsers(Map<String,Object> filter) throws SQLException;
	
	/**
	 * 获取组列表
	 * @param filter
	 * pageIndex 页数
	 * pageSize 页大小
	 * groupType 组类型
	 * queryGroupId 组ID
	 * queryGroupName 组名称
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> getAllGroup(Map<String,Object> filter) throws SQLException;
	
	/**
	 * 获取组定义
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getAllGroupDefinition(Map<String,Object> params) throws SQLException;
	
	/**
	 * 获取用户信息
	 * @param params
	 * viewUserId 要查询的用户ID
	 * @return
	 * user 用户信息
	 * groups 用户所在组信息
	 * @throws SQLException
	 */
	public Map<String,Object> getUserInfo(Map<String,Object> params) throws SQLException;
	
	/**
	 * 获取组信息
	 * @param params
	 * viewGroupId 组ID
	 * viewGroupType组类型
	 * @return 
	 * users 组成员
	 * group 组信息
	 * @throws SQLException
	 */
	public Map<String,Object> getGroupInfo(Map<String,Object> params) throws SQLException;
	
}
