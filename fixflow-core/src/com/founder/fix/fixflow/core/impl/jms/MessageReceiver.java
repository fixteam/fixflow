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
package com.founder.fix.fixflow.core.impl.jms;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.founder.fix.fixflow.core.impl.jms.IListener.ChainType;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;



public class MessageReceiver{

	private ActiveMQConnectionFactory activeFactory;
	
	private Connection connection;
	
	private Session session;
	
	private Map<String,Thread> threads = new HashMap<String,Thread>();
    
    /**
      * 新建需要传入要连接的地址. 
      * @param url
      * @throws JMSException
      */
    public MessageReceiver(String url) throws JMSException{
    	activeFactory = new ActiveMQConnectionFactory(url);
        connection = activeFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }
    
    /**
      * 放如信息id，订阅者将会根据这个id订阅消息同时根据后一个listener来处理收到消息后所做的事。
      *
      * @Title: addQueueListemer
      * @Description: TODO
      * @param @param messageId
      * @param @param listener
      * @param @throws JMSException    设定文件
      * @return void    返回类型
      * @throws
      */
    public void addQueueListener(String messageId,IListener listener) throws JMSException{
    	Destination quneneEngine = session.createQueue(messageId);
    	addListener(quneneEngine,listener);
    }
    
    /**
      *
      * @Title: addTopicListemer
      * @Description: TODO
      * @param @param topic
      * @param @param listener
      * @param @throws JMSException    设定文件
      * @return void    返回类型
      * @throws
      */
    public void addTopicListener(String topic,IListener listener) throws JMSException{
    	Destination quneneEngine = session.createTopic(topic);
    	addListener(quneneEngine,listener);
    }
    
	/**
	  *
	  * @Title: addListener
	  * @Description: TODO
	  * @param @param destination
	  * @param @param listener
	  * @param @return
	  * @param @throws JMSException    设定文件
	  * @return String    返回类型
	  * @throws
	  */
	public String addListener(Destination destination, IListener listener)
			throws JMSException {
		MessageConsumer consumer = session.createConsumer(destination);
		String guid = GuidUtil.CreateGuid();
		MessageListener res = new MessageListener(consumer,listener);
    	Thread thread1 = new Thread(res);
		thread1.start();
		threads.put(guid, thread1);
		return guid;
	}
	
	/**
	  * 停止一个监听器。
	  * @Title: stop
	  * @Description: TODO
	  * @param @param guid    设定文件
	  * @return void    返回类型
	  * @throws
	  */
	public void stop(String guid){
		Thread thread = threads.get(guid);
		if(thread!=null){
			thread.interrupt();
		}
	}
	
	/**
	  * stopAll(停止全部的监听器)
	  *
	  * @Title: stopAll
	  * @Description: TODO
	  * @param     设定文件
	  * @return void    返回类型
	  * @throws
	  */
	public void stopAll(){
		Set<Entry<String,Thread>> ths =  threads.entrySet();
		
		for(Entry<String,Thread> t:ths){
			t.getValue().interrupt();
		}
	}

	class MessageListener implements Runnable{
		private MessageConsumer consumer;
		
		private IListener listener;
		
		public MessageListener(MessageConsumer customer,IListener listener){
			this.consumer = customer;
			this.listener = listener;
		}
		
		public void run() {
			try{
				while (true) {
					Message message = consumer.receive(listener.getMessageTime());
					if (message == null) {
						continue;
					}
					
					ChainType type = listener.doJob(message);
					if(type.equals(ChainType.end)){
						return;
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
			
	}
	
	public static void main(String[] args){
		try{
			MessageReceiver reciver = new MessageReceiver("tcp://foundersr:61616");
			TestListener listener = new TestListener();
			
			reciver.addQueueListener("info",listener);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}