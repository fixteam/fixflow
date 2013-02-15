package com.founder.fix.fixflow.core.subscription;

public enum EventSubscriptionType {
	
	/**
	 * 消息启动
	 */
	MessageStartEvent,
	
	/**
	 * 信号广播启动
	 */
	SignalStartEvent,
	
	/**
	 * 消息令牌
	 */
	MessageToken,
	
	/**
	 * 信号令牌
	 */
	SignalToken

}
