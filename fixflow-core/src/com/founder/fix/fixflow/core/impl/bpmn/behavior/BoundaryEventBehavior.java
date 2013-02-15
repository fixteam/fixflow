package com.founder.fix.fixflow.core.impl.bpmn.behavior;

import java.util.List;

import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.impl.BoundaryEventImpl;

import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public class BoundaryEventBehavior extends BoundaryEventImpl {

	
	@Override
	/**
	 * 边界事件节点进入事件
	 */
	public void execute(ExecutionContext executionContext) {

		List<EventDefinition> eventDefinitions=this.getEventDefinitions();
		
		if (eventDefinitions != null) {
			for (EventDefinition eventDefinition : eventDefinitions) {
				eventDefinition.execute(executionContext, this);
			}
		}

	
		
		// 节点离开
		//leave(executionContext);

	}
	
	

	
}
