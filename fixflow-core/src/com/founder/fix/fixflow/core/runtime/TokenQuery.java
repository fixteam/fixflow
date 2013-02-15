package com.founder.fix.fixflow.core.runtime;

import com.founder.fix.fixflow.core.query.Query;

public interface TokenQuery extends Query<TokenQuery, Token>{
	
	TokenQuery processInstanceId(String processInstanceId);
	
	TokenQuery tokenId(String tokenId);
	
	TokenQuery tokenIsEnd();
	
	TokenQuery tokenNotEnd();
	
	
	
	
	
	//排序
	
	
	TokenQuery orderByProcessInstanceId();

	TokenQuery orderByTokenId();


}
