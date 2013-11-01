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
package com.founder.fix.fixflow.expand.identity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.bpmn2extensions.coreconfig.UserInfo;
import com.founder.fix.fixflow.core.cache.CacheHandler;
import com.founder.fix.fixflow.core.db.pagination.Pagination;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.identity.GroupDefinition;
import com.founder.fix.fixflow.core.impl.identity.GroupTo;
import com.founder.fix.fixflow.core.impl.identity.UserTo;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class GroupDefinitionImpl extends GroupDefinition {

	@Override
	public GroupTo findGroupByGroupId(String groupId) {
		// TODO Auto-generated method stub
		if (groupId == null || groupId.equals("")) {
			return null;
		}
		CacheHandler cache = Context.getProcessEngineConfiguration().getCacheHandler();
		Object cacheData = cache.getCacheData(getId() + "_findGroupByGroupId_" + groupId);
		if (cacheData != null) {
			return (GroupTo) cacheData;
		} else {
			try {
				String groupIdField = getGroupInfo().getGroupIdField();
				String groupNameField = getGroupInfo().getGroupNameField();
				String sqlText = getGroupInfo().getSqlText();
				SqlCommand sqlCommand = getSqlCommand();
				List<Object> objectParamWhere = new ArrayList<Object>();
				objectParamWhere.add(groupId);
				List<Map<String, Object>> dataObj = sqlCommand.queryForList("SELECT USERTABLE.* FROM (" + sqlText + ") USERTABLE where USERTABLE."
						+ groupIdField + "=?", objectParamWhere);
				if (dataObj.size() == 0) {
					return null;
				}
				if (dataObj.get(0).get(groupIdField) == null) {
					return null;
				}
				GroupTo groupTo = new GroupTo(groupId, StringUtil.getString(dataObj.get(0).get(groupNameField)), getId(), dataObj.get(0));
				cache.putCacheData(getId() + "_findGroupByGroupId_" + groupId, groupTo);
				return groupTo;
			} catch (Exception e) {
				e.printStackTrace();
				throw new FixFlowException("获取" + getGroupInfo().getGroupName() + "信息出错!", e);
			}
		}
	}
	
	public Map<String,Object> findGroups(Page page, Map<String, Object> queryMap) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<GroupTo> resultList = new ArrayList<GroupTo>();
		try {
			String groupIdField = getGroupInfo().getGroupIdField();
			String groupNameField = getGroupInfo().getGroupNameField();
			String supIdField = getGroupInfo().getSupGroupIdField();
			String sqlText = getGroupInfo().getSqlText();
			SqlCommand sqlCommand = getSqlCommand();
			String sql = "SELECT USERTABLE.* FROM (" + sqlText + ") USERTABLE where 1=1";
			String countSql = "SELECT count(*) FROM (" + sqlText + ") USERTABLE where 1=1";
			String whereSql = "";
			if(queryMap!= null && queryMap.containsKey("GROUPID")){
				whereSql += " and " + groupIdField +" like '%"+queryMap.get("GROUPID")+"%'";
			}
			if(queryMap!= null && queryMap.containsKey("GROUPNAME")){
				whereSql += " and " + groupNameField +" like '%"+queryMap.get("GROUPNAME")+"%'";
			}
			if(queryMap!= null && queryMap.containsKey("SUPID")){
				whereSql += " and (" + supIdField +" = '"+queryMap.get("SUPID")+"' or "+groupIdField+"='"+queryMap.get("SUPID")+"')";
			}
			sql += whereSql;
			countSql += whereSql;
			
			if (page != null) {
				Pagination pagination = Context.getProcessEngineConfiguration().getDbConfig().getPagination();
				sql = pagination.getPaginationSql(sql, page.getFirstResult(), page.getMaxResults(), "*",null);
			}
			List<Map<String, Object>> dataObj = sqlCommand.queryForList(sql, null);
			int count = Integer.parseInt(sqlCommand.queryForValue(countSql).toString());
			for(int i = 0;i<dataObj.size();i++){
				if (dataObj.get(0).get(groupIdField) != null) {
					GroupTo groupTo = new GroupTo(StringUtil.getString(dataObj.get(i).get(groupIdField)), StringUtil.getString(dataObj.get(i).get(groupNameField)), getId(), dataObj.get(i));
					resultList.add(groupTo);
				}
			}
			resultMap.put("groupList", resultList);
			resultMap.put("count", count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FixFlowException("获取" + getGroupInfo().getGroupName() + "信息出错!", e);
		}
		
		return resultMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GroupTo> findGroupMembersByUser(String userId) {
		if (userId == null || userId.equals("")) {
			throw new FixFlowException("查询的用户编号不能为空");
		}
		CacheHandler cache = Context.getProcessEngineConfiguration().getCacheHandler();
		Object cacheData = cache.getCacheData(getId() + "_findGroupMembersByUser_" + userId);
		if (cacheData != null) {
			return (List<GroupTo>) cacheData;
		} else {
			try {
				List<GroupTo> groupTos = new ArrayList<GroupTo>();
				SqlCommand sqlCommand = getSqlCommand();
				List<Object> objectParamWhere = new ArrayList<Object>();
				objectParamWhere.add(userId);
				UserInfo userInfo = getGroupInfo().getUserInfo();
				String userIdField = userInfo.getUserIdField();
				@SuppressWarnings("unused")
				String userNameField = userInfo.getUserNameField();
				String groupIdField = userInfo.getGroupIdField();
				String sqlText = userInfo.getSqlText();
				List<Map<String, Object>> dataObj = sqlCommand.queryForList("SELECT USERTABLE.* FROM (" + sqlText + ") USERTABLE where USERTABLE."
						+ userIdField + "=? AND USERTABLE."+groupIdField+" IS NOT NULL", objectParamWhere);
				if(dataObj.size()==0){
					return groupTos;
				}
				for (Map<String, Object> roleTo : dataObj) {
					GroupTo groupTo = new GroupTo(roleTo.get(groupIdField).toString(), StringUtil.getString(roleTo.get(this.getGroupInfo().getGroupNameField())), getId(),
							roleTo);
					groupTos.add(groupTo);
				}
				cache.putCacheData(getId() + "_findGroupMembersByUser_" + userId, groupTos);
				return groupTos;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new FixFlowException("人员获取" + getGroupInfo().getGroupName() + "出错!", e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GroupTo> findGroupChildMembersByGroupId(String groupId) {
		if (groupId == null || groupId.equals("")) {
			throw new FixFlowException("查询的组编号不能为空");
		}
		CacheHandler cache = Context.getProcessEngineConfiguration().getCacheHandler();
		Object cacheData = cache.getCacheData(getId() + "_findGroupChildMembersByGroupId_" + groupId);
		if (cacheData != null) {
			return (List<GroupTo>) cacheData;
		} else {
			List<GroupTo> groupTos = getGroupChild(groupId, false);
			cache.putCacheData(getId() + "_findGroupChildMembersByGroupId_" + groupId, groupTos);
			return groupTos;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GroupTo> findGroupChildMembersIncludeByGroupId(String groupId) {
		if (groupId == null || groupId.equals("")) {
			throw new FixFlowException("查询的组编号不能为空");
		}
		CacheHandler cache = Context.getProcessEngineConfiguration().getCacheHandler();
		Object cacheData = cache.getCacheData(getId() + "_findGroupChildMembersIncludeByGroupId_" + groupId);
		if (cacheData != null) {
			return (List<GroupTo>) cacheData;
		} else {
			List<GroupTo> groupTos = getGroupChild(groupId, true);
			cache.putCacheData(getId() + "_findGroupChildMembersIncludeByGroupId_" + groupId, groupTos);
			return groupTos;
		}
	}

	private List<GroupTo> getGroupChild(String groupId, boolean include) {
		List<GroupTo> groupTos = new ArrayList<GroupTo>();
		try {
			String groupIdField = getGroupInfo().getGroupIdField();
			String groupNameField = getGroupInfo().getGroupNameField();
			String supGroupIdField = getGroupInfo().getSupGroupIdField();
			String sqlText = getGroupInfo().getSqlText();
			SqlCommand sqlCommand = getSqlCommand();
			// 部门编号,父级编号，部门名称
			// 获取这个部门的所有子部门
			List<Map<String, Object>> listMap = sqlCommand.queryForList("SELECT USERTABLE.* FROM (" + sqlText + ") USERTABLE");
			for (Map<String, Object> map : listMap) {
				// 部门编号
				String groupDbId = StringUtil.getString(StringUtil.getString(map.get(groupIdField)));
				// 部门名称
				String groupDbName = StringUtil.getString(StringUtil.getString(map.get(groupNameField)));
				String supGroupDbId = StringUtil.getString(StringUtil.getString(map.get(supGroupIdField)));
				// 部门编号不能为空
				if (StringUtil.isNotEmpty(groupDbId)) {
					// 这里查询出来的结果集会带有父节点本身,父节点本身不需要再递归了。
					if (groupDbId.equals(groupId) && include) {
						// 父节点处理方式，不递归。
						GroupTo groupTo = new GroupTo(groupDbId, groupDbName, getId(), map);
						groupTos.add(groupTo);
					} else {
						if (StringUtil.isNotEmpty(supGroupDbId)) {
							if (supGroupDbId.equals(groupId)) {
								// 子节点处理方式,递归。
								GroupTo groupTo = new GroupTo(groupDbId, groupDbName, getId(), map);
								groupTos.add(groupTo);
								// 递归查找子部门
								findGroupSub(listMap, groupDbId, groupTos);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new FixFlowException("获取子" + getGroupInfo().getGroupName() + "信息出错!", e);
		}
		return groupTos;
	}

	/**
	 * 递归查找子组
	 * @param dt 数据集合
	 * @param id 父亲编号
	 * @param result 返回结果
	 */
	private void findGroupSub(List<Map<String, Object>> listMap, String id, List<GroupTo> groupTos) {
		String groupIdField = getGroupInfo().getGroupIdField();
		String groupNameField = getGroupInfo().getGroupNameField();
		String supGroupIdField = getGroupInfo().getSupGroupIdField();
		for (Map<String, Object> map : listMap) {
			String tmpsupid = StringUtil.getString(map.get(supGroupIdField));
			String tmpid = StringUtil.getString(map.get(groupIdField));
			String tmpName = StringUtil.getString(map.get(groupNameField));
			if (tmpid != null && !tmpid.equals("")) {
				if (tmpsupid != null && tmpsupid.equals(id)) {
					GroupTo groupTo = new GroupTo(tmpid, tmpName, getId(), map);
					groupTos.add(groupTo);
					findGroupSub(listMap, tmpid, groupTos);
				}
			}

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserTo> findUserChildMembersIncludeByGroupId(String groupId) {
		if (groupId == null || groupId.equals("")) {
			throw new FixFlowException("查询的组编号不能为空");
		}
		CacheHandler cache = Context.getProcessEngineConfiguration().getCacheHandler();
		Object cacheData = cache.getCacheData(getId() + "_findUserChildMembersIncludeByGroupId_" + groupId);
		if (cacheData != null) {
			return (List<UserTo>) cacheData;
		} else {
			try {
				SqlCommand sqlCommand = getSqlCommand();
				List<GroupTo> groupTos = getGroupChild(groupId, true);
				List<Object> objectParamWhere = new ArrayList<Object>();
				String sqlInString = "";
				for (GroupTo groupTo : groupTos) {
					if (sqlInString.equals("")) {
						sqlInString = "?";
						objectParamWhere.add(groupTo.getGroupId());
					} else {
						sqlInString = sqlInString + ",?";
						objectParamWhere.add(groupTo.getGroupId());
					}
				}
				UserInfo userInfo = getGroupInfo().getUserInfo();
				String userIdField = userInfo.getUserIdField();
				String userNameField = userInfo.getUserNameField();
				String groupIdField = userInfo.getGroupIdField();
				String sqlText = userInfo.getSqlText();
				// 这里可能会出现重复记录人名的问题
				List<Map<String, Object>> dataObj = sqlCommand.queryForList("select USERTABLE.* FROM (" + sqlText + ") USERTABLE WHERE USERTABLE."
						+ groupIdField + " in (" + sqlInString + ")", objectParamWhere);
				List<UserTo> userTos = new ArrayList<UserTo>();
				for (Map<String, Object> map : dataObj) {
					UserTo userTo = new UserTo(StringUtil.getString(map.get(userIdField)), StringUtil.getString(map.get(userNameField)), map);
					userTos.add(userTo);
				}
				cache.putCacheData(getId() + "_findUserChildMembersIncludeByGroupId_" + groupId, userTos);
				return userTos;
			} catch (Exception e) {
				e.printStackTrace();
				throw new FixFlowException("从" + getGroupInfo().getGroupName() + "获取用户信息出错!", e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserTo> findUserByGroupId(String groupId) {
		if (groupId == null || groupId.equals("")) {
			throw new FixFlowException("查询的组编号不能为空");
		}
		CacheHandler cache = Context.getProcessEngineConfiguration().getCacheHandler();
		Object cacheData = cache.getCacheData(getId() + "_findUserByGroupId_" + groupId);
		if (cacheData != null) {
			return (List<UserTo>) cacheData;
		} else {
			try {
				SqlCommand sqlCommand = getSqlCommand();
				List<Object> objectParamWhere = new ArrayList<Object>();
				objectParamWhere.add(groupId);
				UserInfo userInfo = getGroupInfo().getUserInfo();
				String userIdField = userInfo.getUserIdField();
				String userNameField = userInfo.getUserNameField();
				String groupIdField = userInfo.getGroupIdField();
				String sqlText = userInfo.getSqlText();
				List<Map<String, Object>> dataObj = sqlCommand.queryForList("SELECT USERTABLE.* FROM (" + sqlText + ") USERTABLE where USERTABLE."
						+ groupIdField + "=?", objectParamWhere);
				List<UserTo> userTos = new ArrayList<UserTo>();
				for (Map<String, Object> map : dataObj) {
					UserTo userTo = new UserTo(StringUtil.getString(map.get(userIdField)), StringUtil.getString(map.get(userNameField)), map);
					userTos.add(userTo);
				}
				cache.putCacheData(getId() + "_findUserByGroupId_" + groupId, userTos);
				return userTos;
			} catch (Exception e) {
				e.printStackTrace();
				throw new FixFlowException("从" + getGroupInfo().getGroupName() + "获取用户信息出错!", e);
			}
		}
	}

	@Override
	public GroupTo findParentGroupByGroupId(String groupId) {
		if (groupId == null || groupId.equals("")) {
			return null;
		}
		CacheHandler cache = Context.getProcessEngineConfiguration().getCacheHandler();
		Object cacheData = cache.getCacheData(getId() + "_findParentGroupByGroupId_" + groupId);
		if (cacheData != null) {
			return (GroupTo) cacheData;
		} else {
			try {
				String groupIdField = getGroupInfo().getGroupIdField();
				String groupNameField = getGroupInfo().getGroupNameField();
				String supGroupIdField = getGroupInfo().getSupGroupIdField();
				String sqlText = getGroupInfo().getSqlText();
				SqlCommand sqlCommand = getSqlCommand();
				List<Object> objectParamWhere = new ArrayList<Object>();
				objectParamWhere.add(groupId);
				List<Map<String, Object>> dataObj = sqlCommand.queryForList(" SELECT USERTABLENEW.* FROM (" + sqlText
						+ ") USERTABLENEW WHERE USERTABLENEW." + groupIdField + " IN (SELECT USERTABLE." + supGroupIdField + " FROM (" + sqlText
						+ ") USERTABLE where USERTABLE." + groupIdField + "=?)", objectParamWhere);
				if (dataObj.size() == 0) {
					return null;
				}
				if (dataObj.get(0).get(groupIdField) == null) {
					return null;
				}
				GroupTo groupTo = new GroupTo(groupId, StringUtil.getString(dataObj.get(0).get(groupNameField)), getId(), dataObj.get(0));
				cache.putCacheData(getId() + "_findParentGroupByGroupId_" + groupId, groupTo);
				return groupTo;
			} catch (Exception e) {
				e.printStackTrace();
				throw new FixFlowException("获取" + getGroupInfo().getGroupName() + "信息出错!", e);
			}
		}
	}

}
