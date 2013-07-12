package com.founder.fix.fixflow.test.message;

import java.util.Date;

import javax.jms.Session;

import com.founder.fix.fixflow.core.impl.jms.MessageSender;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.task.TaskInstance;

public class SendMessage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TaskInstance taskInstance=new TaskInstanceEntity();
		taskInstance.setId("王狐狸");
		taskInstance.setAssignee("姜楠");
		
		taskInstance.setCreateTime(new Date());
		
		try{
			MessageSender messageReciver = new MessageSender("tcp://172.29.128.91:61616",Session.CLIENT_ACKNOWLEDGE);
			messageReciver.sendQuneneObjectMessage("info", "1");
			messageReciver.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		

	}

}
