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
