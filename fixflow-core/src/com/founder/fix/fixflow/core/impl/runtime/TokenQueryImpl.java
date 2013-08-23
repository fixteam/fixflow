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
package com.founder.fix.fixflow.core.impl.runtime;

import java.util.List;

import com.founder.fix.fixflow.core.impl.AbstractQuery;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.interceptor.CommandExecutor;
import com.founder.fix.fixflow.core.impl.task.QueryExpandTo;
import com.founder.fix.fixflow.core.runtime.QueryLocation;
import com.founder.fix.fixflow.core.runtime.Token;
import com.founder.fix.fixflow.core.runtime.TokenQuery;

public class TokenQueryImpl extends AbstractQuery<TokenQuery, Token> implements TokenQuery {

	//流程实例ID
	protected String processInstanceId;
	//令牌ID
	protected String tokenId;
	//是否结束
	protected String end;
	//查询位置 run表或his表
	protected QueryLocation queryLocation = null;

	public TokenQueryImpl() {
		
	}

	public TokenQueryImpl(CommandContext commandContext) {
		super(commandContext);
	}

	public TokenQueryImpl(CommandExecutor commandExecutor) {
		super(commandExecutor);
	}
	
	public TokenQuery processInstanceId(String processInstanceId) {
		this.processInstanceId=processInstanceId;
		return this;
	}

	public TokenQuery tokenId(String tokenId) {
		this.tokenId=tokenId;
		return this;
	}
	
	public TokenQuery tokenIsEnd() {
		this.end = " is not null ";
		return this;
	}

	public TokenQuery tokenNotEnd() {
		this.end = " is null ";
		return this;
	}
	
	public TokenQuery his() {
		if(this.queryLocation != null){
			this.queryLocation = QueryLocation.RUN_HIS;
		}else{
			this.queryLocation = QueryLocation.HIS;
		}
		return this;
	}
	
	public TokenQuery run() {
		if(this.queryLocation != null){
			this.queryLocation = QueryLocation.RUN_HIS;
		}else{
			this.queryLocation = QueryLocation.RUN;
		}
		return this;
	}

	public long executeCount(CommandContext commandContext) {
		checkQueryOk();
		return commandContext.getTokenManager().findTokenCountByQueryCriteria(this);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Token> executeList(CommandContext commandContext, Page page) {
		checkQueryOk();
		return (List)commandContext.getTokenManager().findTokenByQueryCriteria(this, page);
	}

	public TokenQuery orderByProcessInstanceId() {
		return orderBy(TokenQueryProperty.PROCESSINSTANCE_ID);
	}

	public TokenQuery orderByTokenId() {
		return orderBy(TokenQueryProperty.TOKEN_ID);
	}
	
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public QueryLocation getQueryLocation() {
		return queryLocation;
	}

	public String getTokenId() {
		return tokenId;
	}
	
	public String getEnd() {
		return end;
	}

	public QueryExpandTo getQueryExpandTo() {
		return queryExpandTo;
	}

}
