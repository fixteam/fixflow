package com.founder.fix.fixflow.service.impl;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.founder.fix.fixflow.core.IdentityService;
import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.identity.GroupDefinition;
import com.founder.fix.fixflow.core.impl.identity.GroupTo;
import com.founder.fix.fixflow.core.impl.identity.UserTo;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.service.UserGroupService;
import com.founder.fix.fixflow.shell.CommonServiceImpl;
import com.founder.fix.fixflow.util.FileUtil;
import com.founder.fix.fixflow.util.JSONUtil;
import com.founder.fix.fixflow.util.Pagination;
@Scope("prototype")
@Service
public class UserGroupServiceImpl extends CommonServiceImpl implements UserGroupService {

	@Override
	public Map<String, Object> getAllUsers(Map<String, Object> params) throws SQLException {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String userId = StringUtil.getString(params.get("userId"));
		ProcessEngine processEngine = getProcessEngine(userId);
		IdentityService identityService = processEngine.getIdentityService();
		try{
			String pageI = StringUtil.getString(params.get("pageIndex"));
			String rowI = StringUtil.getString(params.get("pageSize"));
			int pageIndex=1;
			int rowNum   =15;
			if(StringUtil.isNotEmpty(pageI)){
				pageIndex = Integer.valueOf(pageI);
			}
			if(StringUtil.isNotEmpty(rowI)){
				rowNum = Integer.valueOf(rowI);
			}
			int firstResult = rowNum*(pageIndex-1)+1;//起始行
			int maxResults = pageIndex*rowNum;//结束行
			Map<String,Object> queryMap = new HashMap<String,Object>();
			String queryUserId = StringUtil.getString(params.get("queryUserId"));
			if(StringUtil.isNotEmpty(queryUserId)){
				queryMap.put("USERID", queryUserId);
			}
			String queryUserName = StringUtil.getString(params.get("queryUserName"));
			if(StringUtil.isNotEmpty(queryUserName)){
				queryMap.put("USERNAME", queryUserName);
			}
			Map<String,Object> userListMap = identityService.getUserTos(new Page(firstResult,maxResults), queryMap);
			List<UserTo> userTos = (List<UserTo>)userListMap.get("userList");
			int count = (Integer)userListMap.get("count");
			List<Map<String,Object>> userList = new ArrayList<Map<String,Object>>();
			Pagination page = new Pagination(pageIndex,rowNum);
			page.setTotal(count);
			if(userTos!=null){
				for(UserTo user:userTos){
					Map<String,Object> userMap = user.getPropertyMap();
					userList.add(userMap);
				}
			}
			resultMap.put("dataList", userList);
			resultMap.put("pageInfo", page);
		}finally{
			closeProcessEngine();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> getAllGroup(Map<String, Object> params) throws SQLException {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String groupType = StringUtil.getString(params.get("groupType"));
		if(StringUtil.isEmpty(groupType)){
			return resultMap;
		}
		String userId = StringUtil.getString(params.get("userId"));
		ProcessEngine processEngine = getProcessEngine(userId);
		IdentityService identityService = processEngine.getIdentityService();
		try{
			String pageI = StringUtil.getString(params.get("pageIndex"));
			String rowI = StringUtil.getString(params.get("pageSize"));
			int pageIndex=1;
			int rowNum   =15;
			if(StringUtil.isNotEmpty(pageI)){
				pageIndex = Integer.valueOf(pageI);
			}
			if(StringUtil.isNotEmpty(rowI)){
				rowNum = Integer.valueOf(rowI);
			}
			int firstResult = rowNum*(pageIndex-1)+1;//起始行
			int maxResults = pageIndex*rowNum;//结束行
			Map<String,Object> queryMap = new HashMap<String,Object>();
			String queryGroupId = StringUtil.getString(params.get("queryGroupId"));
			if(StringUtil.isNotEmpty(queryGroupId)){
				queryMap.put("GROUPID", queryGroupId);
			}
			String queryGroupName = StringUtil.getString(params.get("queryGroupName"));
			if(StringUtil.isNotEmpty(queryGroupName)){
				queryMap.put("GROUPNAME", queryGroupName);
			}
			String supId = StringUtil.getString(params.get("supId"));
			if(StringUtil.isNotEmpty(supId)){
				queryMap.put("SUPID", supId);
			}
			
			GroupDefinition groupDefinition = identityService.getGroupDefinition(groupType);
			Map<String,Object> map = groupDefinition.findGroups(new Page(firstResult,maxResults), queryMap);
			List<GroupTo> groupTos = (List<GroupTo>)map.get("groupList");
			List<Map<String,Object>> groupList = new ArrayList<Map<String,Object>>();
			int count = (Integer)map.get("count");
			Pagination page = new Pagination(pageIndex,rowNum);
			page.setTotal(count);
			for(GroupTo group : groupTos){
				
				Map<String,Object> groupMap = new HashMap<String,Object>();
				groupMap.put("groupId",group.getGroupId());
				groupMap.put("groupName", group.getGroupName());
				groupMap.putAll(group.getPropertyMap());
				groupList.add(groupMap);
			}
			resultMap.put("dataList", groupList);
			resultMap.put("pageInfo", page);
		}
		finally{
			closeProcessEngine();
		}
		return resultMap;
	}
	
	public List<Map<String, Object>> getAllGroupDefinition(Map<String,Object> params) throws SQLException {
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		String userId = StringUtil.getString(params.get("userId"));
		ProcessEngine processEngine = getProcessEngine(userId);
		IdentityService identityService = processEngine.getIdentityService();
		try{
			List<GroupDefinition> list = identityService.getAllGroupDefinitions();
			for(GroupDefinition group: list){
				Map<String,Object> groupMap = new HashMap<String,Object>();
				groupMap.put("typeId", group.getId());
				groupMap.put("typeName", group.getName());
				if(!group.getGroupInfo().getSupGroupIdField().equals(group.getGroupInfo().getGroupIdField())){
					groupMap.put("isTree", true);
					List<GroupTo> GroupTolist = (List<GroupTo>)group.findGroups(null, null).get("groupList");
					List<Map<String, Object>> resultJsonList = new ArrayList<Map<String,Object>>();
					for(GroupTo tmpGroupTo:GroupTolist){
						Map<String,Object> tmpResult = new HashMap<String,Object>();
						if(tmpGroupTo.getGroupId().equals(tmpGroupTo.getPropertyValue(group.getGroupInfo().getSupGroupIdField()))){
							tmpResult.put("pId", "0");
						}else{
							tmpResult.put("pId", group.getId()+"__"+tmpGroupTo.getPropertyValue(group.getGroupInfo().getSupGroupIdField()));
						}
						tmpResult.put("id", group.getId()+"__"+tmpGroupTo.getGroupId());
						tmpResult.put("name", tmpGroupTo.getGroupName());
						tmpResult.put("open", true);
						resultJsonList.add(tmpResult);
					}
					groupMap.put("groupJson", JSONUtil.parseObject2JSON(resultJsonList));
				}
				resultList.add(groupMap);
			}
		}finally{
			closeProcessEngine();
		}
		return resultList;
	}
	
	public Map<String, Object> getUserInfo(Map<String, Object> params) throws SQLException {
		Map<String,Object> result= new HashMap<String,Object>();
		UserTo user = null;
		String userId = (String) params.get("viewUserId");
		ProcessEngine engine = getProcessEngine(userId);
		
		String path = StringUtil.getString(params.get("path"));
		path = path+"/icon/";
		FileUtil.makeParent(new File(path+"ss.ss"));
		result.put("icon", "icon/"+userId+".png");
		try{
			user = engine.getIdentityService().getUserTo(userId);
			List<GroupTo> groups = engine.getIdentityService().getUserInGroups(userId);
			result.put("user", user);
			result.put("groups", groups);
		}finally{
			closeProcessEngine();
		}
		return result;
	}
	
	@Override
	public Map<String, Object> getGroupInfo(Map<String, Object> params) throws SQLException {
		Map<String,Object> result= new HashMap<String,Object>();
		String userId = StringUtil.getString(params.get("userId"));
		GroupTo group = null;
		String groupId = StringUtil.getString(params.get("viewGroupId"));
		String groupType = StringUtil.getString(params.get("viewGroupType"));
		ProcessEngine engine = getProcessEngine(userId);
		try{
			group = engine.getIdentityService().getGroup(groupId, groupType);
			List<UserTo> users = engine.getIdentityService().getUserInGroupChildMembersInclude(groupId, groupType);
			result.put("users", users);
			result.put("group", group);
		}finally{
			closeProcessEngine();
		}
		return result;
	}
}
