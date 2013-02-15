package com.founder.fix.fixflow.core.impl.jms;
import javax.jms.Message;
import javax.jms.ObjectMessage;

public class TestListener implements IListener {

	public ChainType doJob(Message message) throws Exception {
		ObjectMessage objMessage = (ObjectMessage)message;
		
		System.out.println("I got a message:"+objMessage.getObject());
		return ChainType.cuntinue;
	}
	
	public int getMessageTime(){
		return 2000;
	}
	


}
