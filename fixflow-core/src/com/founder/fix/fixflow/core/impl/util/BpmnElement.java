package com.founder.fix.fixflow.core.impl.util;

import com.founder.fix.fixflow.core.ProcessEngineManagement;

public class  BpmnElement {

	//	Event
	public static String StartEvent="StartEvent";
	public static String EndEvent="EndEvent";	
	public static String TimerEventDefinition="TimerEventDefinition";
	public static String TerminateEventDefinition="TerminateEventDefinition";
	public static String IntermediateCatchEvent="IntermediateCatchEvent";
	public static String ErrorEventDefinition="ErrorEventDefinition";
	public static String BoundaryEvent="BoundaryEvent";
	public static String MessageEventDefinition="MessageEventDefinition";
	public static String IntermediateThrowEvent="IntermediateThrowEvent";
	

	
	
	
	//	Gateway
	public static String ParallelGateway="ParallelGateway";	
	public static String InclusiveGateway="InclusiveGateway";	
	public static String ExclusiveGateway="ExclusiveGateway";	
	public static String EventBasedGateway="EventBasedGateway";
	public static String ComplexGateway="ComplexGateway";
	
	
	//	Activity
	public static String UserTask="UserTask";
	public static String SubProcess="SubProcess";
	public static String SendTask="SendTask";
	public static String ScriptTask="ScriptTask";
	public static String ReceiveTask="ReceiveTask";
	public static String CallActivity="CallActivity";
	
	//	Other
	public static String SequenceFlow="SequenceFlow";
	public static String ProcessDefinition="ProcessDefinition";
	public static String Definitions="Definitions";
	

	@SuppressWarnings("unchecked")
	public static <T> T getInstance(String elementId){
		Class<?> classObj=ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getExpandClassMap().get(elementId);
		
		try {
			return (T)classObj.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
