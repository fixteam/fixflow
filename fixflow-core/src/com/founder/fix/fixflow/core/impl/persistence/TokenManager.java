package com.founder.fix.fixflow.core.impl.persistence;

import java.util.List;

import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.runtime.TokenQueryImpl;

public class TokenManager extends AbstractManager {
	
	
	
	
	public long findTokenCountByQueryCriteria(TokenQueryImpl tokenQuery)
	{
		return (Long)getDbSqlSession().selectOne("selectTokenCountByQueryCriteria", tokenQuery);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TokenEntity> findTokenByQueryCriteria(TokenQueryImpl tokenQuery, Page page) {

		String query = "selectTokenByQueryCriteria";
		return getDbSqlSession().selectList(query, tokenQuery, page);

	}

}
