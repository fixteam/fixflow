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
package com.founder.fix.fixflow.core.impl.bpmn.behavior;

import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.impl.ErrorEventDefinitionImpl;

import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public class ErrorEventDefinitionBehavior extends ErrorEventDefinitionImpl {
	
	
	protected String errorCode;
	
	
	
	
	public void execute(ExecutionContext executionContext,Event event) {

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
