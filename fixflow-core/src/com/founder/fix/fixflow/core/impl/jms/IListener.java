package com.founder.fix.fixflow.core.impl.jms;

import javax.jms.Message;

public interface IListener {
	public enum ChainType{
		cuntinue,
		end
	}
	
	public ChainType doJob(Message message) throws Exception ;
	
	public int getMessageTime();
}
