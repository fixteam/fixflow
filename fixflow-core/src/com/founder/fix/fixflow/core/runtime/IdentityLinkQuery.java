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
	
	IdentityLinkQuery id(String id);
	
	IdentityLinkQuery taskId(String taskId);
	
	IdentityLinkQuery userId(String userId);
	
	IdentityLinkQuery groupId(String groupId);
	
	IdentityLinkQuery groupType(String groupType);
	
	IdentityLinkQuery includeExclusion(IncludeExclusion includeExclusion);
	
	IdentityLinkQuery type(IdentityLinkType type);
}
