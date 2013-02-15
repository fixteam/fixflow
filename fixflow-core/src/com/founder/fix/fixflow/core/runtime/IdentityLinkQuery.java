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
