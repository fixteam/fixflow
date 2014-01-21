package com.founder.fix.fixflow.core.impl.bpmn.behavior;

import java.util.List;

import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.impl.IntermediateThrowEventImpl;

import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public class IntermediateThrowEventBehavior extends IntermediateThrowEventImpl {
	
	@Override
	public void execute(ExecutionContext executionContext) {
		
		
		List<EventDefinition> eventDefinitionList = this.getEventDefinitions();
		if (eventDefinitionList != null) {
			for (EventDefinition eventDefinition : eventDefinitionList) {
				eventDefinition.execute(executionContext, this);
			}
		}
		
		

		//createEventTask(executionContext);
	}

}
