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
package com.founder.fix.fixflow.core.task;

import java.io.Serializable;

import com.founder.fix.fixflow.core.impl.db.PersistentObject;
import com.founder.fix.fixflow.core.impl.identity.GroupTo;

public interface IdentityLink extends PersistentObject, Serializable{

	
	
String getId();
	
	/**
	 * 获取任务身份类型
	 * See {@link IdentityLinkType}
	 * @return
	 */
	IdentityLinkType getType();
	
	
	/**
	 * 返回用户编号
	 * 如果身份链接涉及到一个用户，那么这将是一个非空的用户ID。
	 * @return
	 */
	String getUserId();
	
	
	
	/**
	 * 返回组
	 * @return
	 */
	GroupTo getGroup();
	
	
	String getGroupId();
	
	String getGroupType();
	

	
	IncludeExclusion getIncludeExclusion();
	
	
	/**
	 * 返回任务实例编号
	 * @return
	 */
	String getTaskId();

	
	
	void setType(IdentityLinkType identityLinkType);

	
	
	void setUserId(String userId);

	
	
	void setGroup(GroupTo group);



	void setIncludeExclusion(IncludeExclusion includeExclusion);
	


	void setGroupId(String groupId);

	

	void setGroupType(String groupType);
	

	void setTaskId(String taskId);

	
	
	
	
}
