package com.founder.fix.fixflow.core.impl.bpmn.behavior;

import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.impl.ErrorEventDefinitionImpl;

import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public class ErrorEventDefinitionBehavior extends ErrorEventDefinitionImpl {
	
	
	protected String errorCode;
	
	
	
	
	public boolean execute(ExecutionContext executionContext,Event event) {

		/*
		if(event instanceof CatchEvent){
			//捕获型事件处理	
			if(event instanceof BoundaryEvent){
				//边界事件处理
				
			}
			else{
				//非边界事件处理
				
			}
		
			
		}
		else{
		//抛出型事件处理
			SubProcess subProcess=event.getSubProcess();
			if(subProcess!=null){
				//该对象在子流程中则开始处理
				
				for (BoundaryEvent boundaryEvent : subProcess.getBoundaryEventRefs()) {
					if(boundaryEvent.getEventDefinitions().size()>0){
						ErrorEventDefinitionBehavior errorEventDefinitionBehavior=(ErrorEventDefinitionBehavior)boundaryEvent.getEventDefinitions().get(0);
						
						String errorCodeThrow=EMFExtensionUtil.getAnyAttributeValue(this, "errorCode");
						String errorCodeCatch=EMFExtensionUtil.getAnyAttributeValue(errorEventDefinitionBehavior, "errorCode");
						
						
						if(errorCodeThrow.equals(errorCodeCatch)){
							if(boundaryEvent.isCancelActivity()){
								TokenEntity token=getSubProcessToken(executionContext.getToken(),subProcess);
								 token.terminationChildToken();
								
								 ExecutionContext executionContextNew=ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(token);
								 boundaryEvent.enter(executionContextNew);
								 return true;
							}
							else{
								TokenEntity token=getSubProcessToken(executionContext.getToken(),subProcess);
								
								
								TokenEntity cToken=new TokenEntity(token,token.getFlowNode().getName(),true);
								 ExecutionContext executionContextNew=ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(cToken);
								 boundaryEvent.enter(executionContextNew);
								 return false;
							}
						}
					}
				}
			}
		}
		
		return false;*/
		return false;
	}
	/*
	private TokenEntity getSubProcessToken(TokenEntity token,SubProcess subProcess){
		
		if(token.getNodeId().equals(subProcess.getId())){
			return token;
		}
		else{
			 return getSubProcessToken(token.getParent(),subProcess);
		}
		
		
	}*/

}
