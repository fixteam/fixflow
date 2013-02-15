package com.founder.fix.fixflow.core.impl.message;


import java.sql.Connection;
import java.util.Map;

import javax.jms.Message;
import javax.jms.ObjectMessage;

import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.RuntimeService;
import com.founder.fix.fixflow.core.impl.ExternalContent;
import com.founder.fix.fixflow.core.impl.command.MessageStartProcessInstanceCommand;
import com.founder.fix.fixflow.core.impl.jms.IListener;
import com.founder.fix.fixflow.core.impl.log.LogFactory;
import com.founder.fix.fixflow.core.subscription.EventSubscriptionType;

public class FlowMessageListener implements IListener {

	FlowMessage flowMessage=new FlowMessage();
	private static com.founder.fix.fixflow.core.impl.log.DebugLog debugLog = LogFactory.getDebugLog(FlowMessageListener.class);

	public ChainType doJob(Message message) throws Exception {
		
		
		Connection connection=null;
		
		try {
			
			
			ProcessEngine processEngine=ProcessEngineManagement.getDefaultProcessEngine();
			
			connection=processEngine.getProcessEngineConfiguration().createConnection();
			connection.setAutoCommit(false);
			ExternalContent externalContent=new ExternalContent();
			externalContent.setConnection(connection);
			externalContent.setAuthenticatedUserId("1200119390");
			processEngine.setExternalContent(externalContent);
			
			
			ObjectMessage objMessage = (ObjectMessage)message;
			
			flowMessage = (FlowMessage)objMessage.getObject();
			
			
			
			String messageId=flowMessage.getId();
			
			
			String processDefinitionId=flowMessage.getTargetProcess();
			
			
			EventSubscriptionType eventSubscriptionType=flowMessage.getMessageType();
			String nodeId=flowMessage.getTargetNode();
			String tokenId=flowMessage.getTokenId();
			Map<String,Object> dataVariableMap=flowMessage.getDataVariableMap();
			
			RuntimeService runtimeService=processEngine.getRuntimeService();
			
			if(eventSubscriptionType==EventSubscriptionType.MessageStartEvent){
				MessageStartProcessInstanceCommand messageStartProcessInstanceCommand=new MessageStartProcessInstanceCommand();
				messageStartProcessInstanceCommand.setProcessDefinitionId(processDefinitionId);
				messageStartProcessInstanceCommand.setNodeId(nodeId);
				messageStartProcessInstanceCommand.setMessageId(messageId);
				//messageStartProcessInstanceCommand.setBusinessKey(businessKey)
				messageStartProcessInstanceCommand.setTransientVariables(dataVariableMap);
				
				
				runtimeService.startProcessInstanceByMessage(messageStartProcessInstanceCommand);
				debugLog.debug("流程：" +processDefinitionId+" 接收到消息 "+messageId+" 开始启动!"); 
			}
			else{
				if(eventSubscriptionType==EventSubscriptionType.MessageToken){
					runtimeService.tokenSignal(tokenId, dataVariableMap);
					debugLog.debug("流程：" +processDefinitionId+" 接收到消息 "+messageId+" 开始驱动令牌!"); 
				}
			}
			
			
		} catch (Exception e) {
			if(connection!=null){
				connection.rollback();
			}
			e.printStackTrace();
			debugLog.debug("消息接收处理失败！"); 
			
			
		}finally{
			if(connection!=null){
				connection.close();
			}
			
		}
		
		
			
		
		return ChainType.cuntinue;
	}

	public int getMessageTime() {
		return 3000;
	}

}
