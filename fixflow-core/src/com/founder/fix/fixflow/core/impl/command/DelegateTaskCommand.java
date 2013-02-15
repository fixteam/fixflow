package com.founder.fix.fixflow.core.impl.command;

public class DelegateTaskCommand extends BaseTaskCommand {

	/**
	 * 代理交给的用户
	 */
	protected String delegateUserId;

	public String getDelegateUserId() {
		return delegateUserId;
	}

	public void setDelegateUserId(String delegateUserId) {
		this.delegateUserId = delegateUserId;
	}
}
