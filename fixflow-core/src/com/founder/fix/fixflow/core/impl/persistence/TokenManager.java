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
