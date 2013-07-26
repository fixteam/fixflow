package com.founder.fix.fixflow.core.impl.bpmn.behavior;


import org.eclipse.bpmn2.impl.SendTaskImpl;



import com.founder.fix.bpmn2extensions.fixflow.MessageObj;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.subscription.EventSubscriptionType;



public class SendTaskbehavior extends SendTaskImpl {

	@Override
	public void execute(ExecutionContext executionContext) {
		// TODO Auto-generated method stub

		
		/*
		FlowMessage flowMessage =null;
		List<FeatureMap.Entry> entryList = EMFExtensionUtil.getExtensionElements(this, "messageObj");
		if (entryList.size() > 0) {
			flowMessage= new FlowMessage();
			String id = EMFExtensionUtil.getExtensionElementAttributeValue(entryList.get(0), "id");
			String name = EMFExtensionUtil.getExtensionElementAttributeValue(entryList.get(0), "name");
			String targetProcess = EMFExtensionUtil.getExtensionElementAttributeValue(entryList.get(0), "targetProcess");
			String targetNode = EMFExtensionUtil.getExtensionElementAttributeValue(entryList.get(0), "targetNode");
			String messageType = EMFExtensionUtil.getExtensionElementAttributeValue(entryList.get(0), "messageType");
			flowMessage.setId(id);
			flowMessage.setName(name);
			flowMessage.setMessageType(EventSubscriptionType.valueOf(messageType));
			flowMessage.setTargetProcess(targetProcess);
			flowMessage.setTargetNode(targetNode);
			
			Map<String, Object> map=new HashMap<String, Object>();
			
	
			
			//List<FeatureMap.Entry> documentation = EMFExtensionUtil.getExtensionElementsInEntry(entryList.get(0), "documentation");
			
			//String docid = EMFExtensionUtil.getExtensionElementAttributeValue(documentation.get(0), "id");
			//String docvalue = EMFExtensionUtil.getExtensionElementValue(documentation.get(0));
			
		
			List<FeatureMap.Entry> dataVariable = EMFExtensionUtil.getExtensionElementsInEntry(entryList.get(0), "dataVariable");
			for (Entry entry : dataVariable) {
				String datavarid = EMFExtensionUtil.getExtensionElementAttributeValue(entry, "id");
				List<Entry> entries=EMFExtensionUtil.getExtensionElementsInEntry(entry,"expression");
				String valueString=EMFExtensionUtil.getExtensionElementValue(entries.get(0));
				
				Object valueOjb=ExpressionMgmt.execute(valueString, executionContext);
				
				map.put(datavarid, valueOjb);
			
				
			}
			
			
			flowMessage.setDataVariableMap(map);
			
			
			
			List<FeatureMap.Entry> processInstanceVariable = EMFExtensionUtil.getExtensionElementsInEntry(entryList.get(0), "processInstanceVariable");
			
			String proexpid = "";
			String proexpname = "";
			String proexpvalue = "";
			
			if(processInstanceVariable.size()>0) {
				List<FeatureMap.Entry> processInstanceVariableexp = EMFExtensionUtil.getExtensionElementsInEntry(processInstanceVariable.get(0), "expression");
				proexpid = EMFExtensionUtil.getExtensionElementAttributeValue(processInstanceVariableexp.get(0), "id");
				proexpname = EMFExtensionUtil.getExtensionElementAttributeValue(processInstanceVariableexp.get(0), "name");
				proexpvalue = EMFExtensionUtil.getExtensionElementValue(processInstanceVariableexp.get(0));
			}
			
			List<FeatureMap.Entry> tokenVariable = EMFExtensionUtil.getExtensionElementsInEntry(entryList.get(0), "tokenVariable");
			
			String tokenvarid = "";
			String tokenvarname = "";
			String tokenvarvalue = "";
			
			if(tokenVariable.size()>0) {
				List<FeatureMap.Entry> tokenVariableexp = EMFExtensionUtil.getExtensionElementsInEntry(tokenVariable.get(0), "expression");
				tokenvarid = EMFExtensionUtil.getExtensionElementAttributeValue(tokenVariableexp.get(0), "id");
				tokenvarname = EMFExtensionUtil.getExtensionElementAttributeValue(tokenVariableexp.get(0), "name");
				tokenvarvalue = EMFExtensionUtil.getExtensionElementValue(tokenVariableexp.get(0));
			}

			
			Object valueOjb=ExpressionMgmt.execute(tokenvarvalue, executionContext);
			
			flowMessage.setProcessInstanceId(proexpvalue);
			flowMessage.setTokenId(StringUtil.getString(valueOjb));
			
			try{
				MessageSender messageReciver = new MessageSender("tcp://"+ Context.getProcessEngineConfiguration().getEventSubscriptionConfig().getServerAddress() +":"+Context.getProcessEngineConfiguration().getEventSubscriptionConfig().getServerPort(),Session.CLIENT_ACKNOWLEDGE);
				messageReciver.sendTopicObjectMessage(Context.getProcessEngineConfiguration().getEventSubscriptionConfig().getMessageInfo(), flowMessage);
				System.out.println("消息发送成功");
				messageReciver.close();
				System.out.println("消息发送器关闭成功");
			}catch(Exception e){
				e.printStackTrace();
				throw new FixFlowException("消息发送失败!", e);
			}
			
		} */
		
		super.execute(executionContext);
		
	}
	

	
	public EventSubscriptionType getEventSubscriptionType(MessageObj messageObj) {
		if(messageObj.getMessageType().equals(EventSubscriptionType.MessageStartEvent.toString())) {
			return EventSubscriptionType.MessageStartEvent;
		}else if(messageObj.getMessageType().equals(EventSubscriptionType.MessageToken.toString())) {
			return EventSubscriptionType.MessageToken;
		}else if(messageObj.getMessageType().equals(EventSubscriptionType.SignalStartEvent.toString())) {
			return EventSubscriptionType.SignalStartEvent;
		}else if(messageObj.getMessageType().equals(EventSubscriptionType.SignalToken.toString())) {
			return EventSubscriptionType.SignalToken;
		}else{
			return null;
		}
	}
}
