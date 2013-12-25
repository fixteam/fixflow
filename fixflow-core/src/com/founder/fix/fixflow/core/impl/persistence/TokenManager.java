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

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.runtime.TokenQueryImpl;
import com.founder.fix.fixflow.core.runtime.Token;

public class TokenManager extends AbstractManager {
	
	
	@SuppressWarnings("unchecked")
	public List<TokenEntity> findTokenByQueryCriteria(TokenQueryImpl tokenQuery, Page page) {

		String query = "selectTokenByQueryCriteria";
		return getMappingSqlSession().selectList(query, tokenQuery, page);

	}
	
	
	
	
	public long findTokenCountByQueryCriteria(TokenQueryImpl tokenQuery)
	{
		return (Long)getMappingSqlSession().selectOne("selectTokenCountByQueryCriteria", tokenQuery);
	}
	
	
	
	
	
	
	/**新增方法*/
	
	
	/**
	 * 非递归保存令牌
	 * @param tokenEntity
	 */
	public void saveToken(Token token){
		TokenEntity tmpTokenEntity = findTokenById(token.getId());
		if(tmpTokenEntity == null){
			insert("insertToken", token);
		}else{
			update("updateToken", token);
		}
	}
	/**
	 * 递归保存令牌实例
	 * @param tokenEntity
	 */
	public void saveTokenAndChildren(Token token){
		
		TokenEntity tmpTokenEntity = findTokenById(token.getId());
		if(tmpTokenEntity == null){
			insert("insertToken", token);
		}else{
			update("updateToken", token);
		}
		if (token.getChildren() != null) {
			for (String tokenKey : token.getChildren().keySet()) {
				TokenEntity tokenChildren = token.getChildren().get(tokenKey);
				saveToken(tokenChildren);
			}
		}
	}
	
	/**
	 * 根据ID获取令牌实例
	 * @param tokenId
	 * @return
	 */
	public TokenEntity findTokenById(String id){
		
		if (id == null) {
			throw new FixFlowException("令牌编号不能为空!");
		}
		return (TokenEntity)getMappingSqlSession().selectOne("findTokenById", id);
	}


	@SuppressWarnings("unchecked")
	public List<TokenEntity> findTokensByProcessInstanceId(String id) {

		String query = "findTokensByProcessInstanceId";
		return (List<TokenEntity>)getMappingSqlSession().selectList(query, id);

	}
	
	@SuppressWarnings("unchecked")
	public List<TokenEntity> findTokensByProcessInstanceIdNotEnd(String id) {

		String query = "findTokensByProcessInstanceIdNotEnd";
		return (List<TokenEntity>)getMappingSqlSession().selectList(query, id);

	}


	@SuppressWarnings("unchecked")
	public List<TokenEntity> findTokenByParentId(String id) {
		String query = "findTokenByParentId";
		return (List<TokenEntity>)getDbSqlSession().selectList(query, id);
	}
	
	public void deleteTokenByProcessInstanceId(String processInstanceId){
		delete("deleteTokenByProcessInstanceId", processInstanceId);
	}

}
