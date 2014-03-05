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

import java.util.Date;
import java.util.List;

import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.impl.EndEventImpl;

import com.founder.fix.fixflow.core.event.BaseElementEvent;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public class EndEventBehavior extends EndEventImpl {

	public void enter(ExecutionContext executionContext) {
		TokenEntity token = executionContext.getToken();

		// 把令牌的所在节点设置为当前节点
		token.setFlowNode(this);

		// 设置令牌进入节点的时间
		token.setNodeEnterTime(new Date());

		// 移除执行内容对象的线条关联
		executionContext.setSequenceFlow(null);

		executionContext.setSequenceFlowSource(null);

		execute(executionContext);
		// //token.getProcessInstance().getProcessDefinition().getDefinitions();
		// 创建结束任务
		// createEndEventTask(executionContext);
	}

	public void execute(ExecutionContext executionContext) {

		// 遍历所有的事件定义并依次执行
		List<EventDefinition> eventDefinitionList = this.getEventDefinitions();
		if (eventDefinitionList != null) {
			for (EventDefinition eventDefinition : eventDefinitionList) {
				eventDefinition.execute(executionContext, this);
			}
		}

		// 结束当前的令牌实例,如果当前令牌是根令牌则整个流程结束.
		executionContext.getToken().end();

		if (executionContext.getToken().getProcessInstance().hasEnded()) {
			// 发现流程实例已经结束则触发流程结束事件
			executionContext.getToken().getProcessInstance().getProcessDefinition()
					.fireEvent(BaseElementEvent.EVENTTYPE_PROCESS_END, executionContext);
		}

	}

}
