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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.impl.ExclusiveGatewayImpl;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public class ExclusiveGatewayBehavior extends ExclusiveGatewayImpl {
	
	
	
private static final Logger LOG = Logger.getLogger(ExclusiveGatewayImpl.class.getName());;
	
	public void leave(ExecutionContext executionContext){

		List<SequenceFlow> sequenceFlowList = new ArrayList<SequenceFlow>();

		for (SequenceFlow sequenceFlow : getOutgoing()) {
			
			if(sequenceFlow.isContinue(executionContext))
			{
				sequenceFlowList.add(sequenceFlow);
			}
			
			
		}
		// 节点后面没有线的处理
		if (sequenceFlowList.size()==0) {
			if(getOutgoing().size()==0){
				throw new FixFlowException(this.getName()+"("+this.getId()+") 节点后面没有处理线条！");
			}
			else{
				throw new FixFlowException(this.getName()+"("+this.getId()+") 节点后面的条件都不满足导致节点后面没有处理线条,请检查后续线条条件！");
			}
			
			
		}

		// calculationTransitionsExp(executionContext);

		// 节点后面就一条线的处理
		if (sequenceFlowList.size() == 1) {
			leave(executionContext, sequenceFlowList.get(0));
			return;
		}
		// 节点后面大于一条线的处理
		if (sequenceFlowList.size() > 1) {
			
			LOG.info("唯一网关后面的链接线条满足条件的大于一条,默认选择第一条满足的线条. 默认离开线条为: 编号:"+sequenceFlowList.get(0).getId()+"  名称:"+sequenceFlowList.get(0).getName());
			leave(executionContext, sequenceFlowList.get(0));
		}
	}
	

}
