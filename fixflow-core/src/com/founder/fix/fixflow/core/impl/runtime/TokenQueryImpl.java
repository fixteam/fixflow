package com.founder.fix.fixflow.core.impl.runtime;

import java.util.List;

import com.founder.fix.fixflow.core.impl.AbstractQuery;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.interceptor.CommandExecutor;
import com.founder.fix.fixflow.core.runtime.Token;
import com.founder.fix.fixflow.core.runtime.TokenQuery;


public class TokenQueryImpl extends AbstractQuery<TokenQuery, Token> implements TokenQuery {

	protected String processInstanceId;
	
	
	protected String tokenId;
	
	

	protected String end;
	
	

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




	
	public long executeCount(CommandContext commandContext) {
		checkQueryOk();
		// ensureVariablesInitialized();
		return commandContext.getTokenManager().findTokenCountByQueryCriteria(this);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Token> executeList(CommandContext commandContext, Page page) {
		checkQueryOk();
		// ensureVariablesInitialized();
		return (List)commandContext.getTokenManager().findTokenByQueryCriteria(this, page);
	}
	
	
	
	
	//排序

	public TokenQuery orderByProcessInstanceId() {
		
		return orderBy(TokenQueryProperty.PROCESSINSTANCE_ID);
	}

	public TokenQuery orderByTokenId() {
		return orderBy(TokenQueryProperty.TOKEN_ID);
	}
	
	
	
	
	
	//get
	
	
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	
	public String getTokenId() {
		return tokenId;
	}
	
	public String getEnd() {
		return end;
	}

	

}
