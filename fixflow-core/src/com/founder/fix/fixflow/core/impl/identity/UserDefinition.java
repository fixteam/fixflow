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

import com.founder.fix.bpmn2extensions.coreconfig.AllUserInfo;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;

public abstract class UserDefinition {
	
	protected AllUserInfo userInfoConfig;
	
	/**
	 * 获取流程引擎中配置的用户信息
	 * @return
	 */
	public AllUserInfo getUserInfoConfig() {
		return userInfoConfig;
	}

	public void setUserInfoConfig(AllUserInfo userInfoConfig) {
		this.userInfoConfig = userInfoConfig;
	}
	
	/**
	 * 获取数据库操作类
	 * @return
	 */
	public SqlCommand getSqlCommand(){
		String dataBaseId=Context.getProcessEngineConfiguration().getFixFlowConfig().getDesignerOrgConfig().getDataBaseId();
		Connection connection = Context.getDbConnection(dataBaseId);// Context.getDbConnection();
		SqlCommand sqlCommand = new SqlCommand(connection);
		return sqlCommand;
		
	}

	/**
	 * 根据分页和查询条件获取用户
	 * @param page
	 * @param queryMap key有 count,UserList
	 * @return
	 */
	public abstract Map<String, Object> getUserTos(Page page,Map<String,Object> queryMap);
	
	/**
	 * 根据userid获取用户
	 * @param userId
	 * @return
	 */
	public abstract UserTo findUserByUserId(String userId);
	
	/**
	 * 获取用户所在的所有组
	 * @param userId用户编号
	 * @return
	 */
	public abstract List<GroupTo> getUserInGroups(String userId);

}
