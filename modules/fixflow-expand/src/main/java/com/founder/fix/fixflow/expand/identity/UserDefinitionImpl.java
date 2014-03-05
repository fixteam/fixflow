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

import com.founder.fix.fixflow.core.cache.CacheHandler;
import com.founder.fix.fixflow.core.db.pagination.Pagination;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.identity.GroupDefinition;
import com.founder.fix.fixflow.core.impl.identity.GroupTo;
import com.founder.fix.fixflow.core.impl.identity.UserDefinition;
import com.founder.fix.fixflow.core.impl.identity.UserTo;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class UserDefinitionImpl extends UserDefinition {
	

	@Override
	public UserTo findUserByUserId(String userId) {
	
		if (userId == null || userId.equals("")) {

			throw new FixFlowException("查询的用户编号不能为空");

		}

		CacheHandler cache = Context.getProcessEngineConfiguration().getCacheHandler();

		Object cacheData = cache.getCacheData("user_findUserByUserId_" + userId);
		if (cacheData != null) {
			return (UserTo) cacheData;
		} else {
			
			String userIdField=getUserInfoConfig().getUserIdField();
			
			String userNameField=getUserInfoConfig().getUserNameField();
			
			String sqlText=getUserInfoConfig().getSqlText();
			
			

			SqlCommand sqlCommand = getSqlCommand();

			List<Object> objectParamWhere = new ArrayList<Object>();

			objectParamWhere.add(userId);

			List<Map<String, Object>> dataObj = sqlCommand.queryForList("SELECT USERTABLE.* FROM ("+sqlText+") USERTABLE where USERTABLE."+userIdField+"=?", objectParamWhere);

			if(dataObj.size()==0){
				return null;
			}
			
			if (dataObj.get(0).get(userIdField) == null) {
				return null;
			}
			UserTo userTo = new UserTo(userId, StringUtil.getString(dataObj.get(0).get(userNameField)), dataObj.get(0));
			cache.putCacheData("user_findUserByUserId_"+userId, userTo);
			
			return userTo;
		}
	}

	@Override
	public List<GroupTo> getUserInGroups(String userId) {
		List<GroupDefinition> groupDefinitions = Context.getProcessEngineConfiguration().getGroupDefinitions();
		List<GroupTo> groupTos=new ArrayList<GroupTo>();
		for (GroupDefinition groupDefinition : groupDefinitions) {
			List<GroupTo> groupTosTemp=groupDefinition.findGroupMembersByUser(userId);
			
			if(groupTosTemp!=null&&groupTosTemp.size()>0){
				groupTos.addAll(groupTosTemp);
			}
			
			
		}
		return groupTos;
	}
	
	@Override
	public Map<String,Object> getUserTos(Page page,Map<String,Object> queryMap) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<UserTo> resultList = new ArrayList<UserTo>();
		String userIdField=getUserInfoConfig().getUserIdField();
		String userNameField=getUserInfoConfig().getUserNameField();
		String sqlText=getUserInfoConfig().getSqlText();
		SqlCommand sqlCommand = getSqlCommand();
		String sql = "SELECT USERTABLE.* FROM ("+sqlText+") USERTABLE WHERE 1=1";
		String countSql = "SELECT count(*) FROM ("+sqlText+") USERTABLE WHERE 1=1";
		String whereSql = "";
		if(queryMap != null && queryMap.containsKey("USERID")){
			whereSql += " AND "+userIdField+" LIKE '%"+queryMap.get("USERID")+"%'";
		}
		if(queryMap != null && queryMap.containsKey("USERNAME")){
			whereSql += " AND "+userNameField+" LIKE '%"+queryMap.get("USERNAME")+"%' ";
		}
		sql += whereSql;
		countSql += whereSql;
		
		int count = Integer.parseInt(sqlCommand.queryForValue(countSql).toString());
		if (page != null) {
			Pagination pagination = Context.getProcessEngineConfiguration().getDbConfig().getPagination();
			sql = pagination.getPaginationSql(sql, page.getFirstResult(), page.getMaxResults(), "*",null);
		}
		List<Map<String, Object>> dataObj = sqlCommand.queryForList(sql, null);
		
		for(int i= 0;i<dataObj.size();i++){
			if (dataObj.get(i).get(userIdField) != null) {
				UserTo userTo = new UserTo(StringUtil.getString(dataObj.get(i).get(userIdField)), StringUtil.getString(dataObj.get(i).get(userNameField)), dataObj.get(i));
				resultList.add(userTo);
			}
		}
		resultMap.put("userList", resultList);
		resultMap.put("count", count);
		return resultMap;
	}

}
