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
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.StreamMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageSender{
	
	public static final String COMMAND = "COMMAND";
	
	public static final String STREAM_KEY="STREAM_KEY";
	
	private ActiveMQConnectionFactory activeFactory;
	
	private Connection connection;
	
	private Session session;
	
	public MessageSender(String ip,int sessionType) throws JMSException{
		activeFactory = new ActiveMQConnectionFactory();
		activeFactory.setBrokerURL(ip);
		connection = activeFactory.createConnection();
        connection.start();
        session = connection.createSession(false, sessionType);
	}
	
	public void close() throws JMSException{
		session.close();
		connection.close();
	}
	
    public void sendQuneneObjectMessage(String messageId,Serializable object) throws Exception {
    	ObjectProcesser processer = new ObjectProcesser(object);
    	sendQueueMessage(messageId,processer);
    }
    
    public void sendTopicObjectMessage(String topic,Serializable object) throws Exception {
    	ObjectProcesser processer = new ObjectProcesser(object);
    	sendTopicMessage(topic,processer);
    }

    public void sendQuneneStreamMessage(String messageId,InputStream ins) throws Exception {
    	StreamProcesser sp = new StreamProcesser(ins);
    	sendQueueMessage(messageId,sp);
    }
    
    public void sendTopicStreamMessage(String topic,InputStream ins) throws Exception {
    	StreamProcesser sp = new StreamProcesser(ins);
    	sendTopicMessage(topic,sp);
    }
  
    
    public void sendTopicMessage(String topic,IMessageProcess process) throws Exception{
    	Destination topicEngine = session.createTopic(topic);
    	process.process(session, topicEngine);
    }
    
    public void sendQueueMessage(String messageId,IMessageProcess process) throws Exception{
    	Destination queueEngine = session.createQueue(messageId);
    	process.process(session, queueEngine);
    }
    
    public void sendMessage(Destination quneneEngine,IMessageProcess process) throws Exception{
    	process.process(session, quneneEngine);
    }
    
    class ObjectProcesser implements IMessageProcess{

    	private Serializable object;
    	
    	public ObjectProcesser(Serializable object){
    		this.object = object;
    	}
    	
		public void process(Session session, Destination engine)
				throws Exception {
			MessageProducer producer = session.createProducer(engine);
			ObjectMessage message = session.createObjectMessage(object);
			producer.send(message);
		}
    	
    }
    
    class StreamProcesser implements IMessageProcess{
    	
    	private InputStream ins;
    	
    	public StreamProcesser(InputStream ins){
    		this.ins = ins;
    	}

		public void process(Session session,Destination engine) throws Exception {
	        MessageProducer producer = session.createProducer(engine);
	        //通知客户端开始接受文件
	        StreamMessage message = session.createStreamMessage();
	        
	        //开始发送文件
	        byte[] content = new byte[4096];
	        BufferedInputStream bins = new BufferedInputStream(ins);
	        while (bins.read(content) > 0) {
	            message = session.createStreamMessage();
//	            message.clearBody();
	            message.writeBytes(content);
	            producer.send(message);
	        }
	        bins.close();
	        ins.close();
		}
    	
    }
}