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
package com.founder.fix.fixflow.core;

import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.Page;
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
	 * 获取用户所在的所有组
	 * @param userId用户编号
	 * @return
	 */
	List<GroupTo> getUserInGroups(String userId);
	
	/**
	 * 获取用户对象
	 * @param userId 用户编号
	 * @return 用户对象
	 */
	UserTo getUserTo(String userId);
	
	/**
	 * 获取用户对象集合
	 * @param page
	 * @param queryMap key:USERID,USERNAME  (LIKE匹配)
	 * @return
	 */
	List<UserTo> getUserTos(Page page,Map<String,Object> queryMap);
	
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
