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

import java.util.Comparator;

import org.eclipse.bpmn2.SequenceFlow;

public class ComparatorSequence implements Comparator<SequenceFlow> {


	public int compare(SequenceFlow o1, SequenceFlow o2) {

		
		SequenceFlowBehavior sequenceFlowBehavior1=(SequenceFlowBehavior)o1;
		SequenceFlowBehavior sequenceFlowBehavior2=(SequenceFlowBehavior)o2;


		   //首先比较年龄，如果年龄相同，则比较名字
		int orderId1=sequenceFlowBehavior1.getOrderId();
		int orderId2=sequenceFlowBehavior2.getOrderId();
		
		
		if(orderId1>orderId2){
			return 1;
		}
		else{
			if(orderId1==orderId2){
				return 0;
			}else{
				return -1;
			}
			
		}

		
	}

}
