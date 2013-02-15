package com.founder.fix.fixflow.core.impl.jms;

import javax.jms.Destination;
import javax.jms.Session;

public interface IMessageProcess {
	public void process(Session session,Destination engine)throws Exception ;
}
