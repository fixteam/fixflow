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
