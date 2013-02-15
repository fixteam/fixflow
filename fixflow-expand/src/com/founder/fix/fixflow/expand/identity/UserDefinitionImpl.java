package com.founder.fix.fixflow.expand.identity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.cache.CacheHandler;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
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
			cache.putCacheData("user_findUserByUserId_", userTo);
			
			return userTo;
		}
	}

}
