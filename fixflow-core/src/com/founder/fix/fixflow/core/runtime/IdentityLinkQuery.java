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
package com.founder.fix.fixflow.core.runtime;

import com.founder.fix.fixflow.core.query.Query;
import com.founder.fix.fixflow.core.task.IdentityLink;
import com.founder.fix.fixflow.core.task.IdentityLinkType;
import com.founder.fix.fixflow.core.task.IncludeExclusion;

public interface IdentityLinkQuery extends Query<IdentityLinkQuery, IdentityLink>{
	
	/**
	 * 根据任务候选id查询
	 * @param id
	 * @return
	 */
	IdentityLinkQuery id(String id);
	
	/**
	 * 根据任务号查询
	 * @param taskId
	 * @return
	 */
	IdentityLinkQuery taskId(String taskId);
	
	/**
	 * 根据用户号查询
	 * @param userId
	 * @return
	 */
	IdentityLinkQuery userId(String userId);
	
	/**
	 * 根据分组号查询
	 * @param groupId
	 * @return
	 */
	IdentityLinkQuery groupId(String groupId);
	
	/**
	 * 根据分组类型查询
	 * @param groupType
	 * @return
	 */
	IdentityLinkQuery groupType(String groupType);
	
	/**
	 * 包含（排除）
	 * @param includeExclusion
	 * @return
	 */
	IdentityLinkQuery includeExclusion(IncludeExclusion includeExclusion);
	
	/**
	 * 候选人类型
	 * @param type
	 * @return
	 */
	IdentityLinkQuery type(IdentityLinkType type);
	
	/**
	 * 查询历史数据
	 * @return
	 */
	IdentityLinkQuery his();
	
	/**
	 * 查询运行数据
	 * @return
	 */
	IdentityLinkQuery run();
}
