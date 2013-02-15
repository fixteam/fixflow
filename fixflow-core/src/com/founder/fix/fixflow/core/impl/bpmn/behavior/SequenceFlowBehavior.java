package com.founder.fix.fixflow.core.impl.bpmn.behavior;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.MultiInstanceLoopCharacteristics;
import org.eclipse.bpmn2.impl.SequenceFlowImpl;

import com.founder.fix.fixflow.core.action.DecisionHandler;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.util.EMFExtensionUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public class SequenceFlowBehavior extends SequenceFlowImpl {
	
	

	public SequenceFlowBehavior(){
		
		
		
	}
	
	public int getOrderId() {
		
	
		Object orderIdObj =EMFExtensionUtil.getAnyAttributeValue(this, "orderId");
		if(orderIdObj==null||orderIdObj.toString().equals("")){
			return 0;
		}
		else{
			return StringUtil.getInt(orderIdObj);
		}
		
	}


	
	/**
	 * 线条条件处理适配器
	 */
	protected DecisionHandler decideAction = null;

	// Function　方法
	// //////////////////////////////////////////////////////

	/**
	 * 线条条件处理适配器执行方法
	 * @param executionContext 执行内容对象
	 * @throws Exception
	 */
	public boolean isContinue(ExecutionContext executionContext){

		// 如果decideAction不为空则直接调用action去直接表达式解释
		if (decideAction != null) {

			// 在上下文执行变量中 放入 被判定的节点 方便 action里边去取到当前判断的线条
			// executionContext.setDecideTransition(true);
			// 默认操作执行默认action
			// String
			// expressionString=getConditionExpression().getElementText();
			// decideAction.inPutExpression(expressionString);
			return decideAction.decide(executionContext);

		} else {
			// 如果action为空则直接调用表达式解释返回结果

			if (this.conditionExpression != null) {

				try {
						
						
						
						
					Object returnValue=ExpressionMgmt.execute(((FormalExpression)conditionExpression).getBody(), executionContext);
					
					if(returnValue!=null)
					{
						boolean returnCondition=Boolean.parseBoolean(returnValue.toString());
						return returnCondition;
					}
					else
					{
						throw new FixFlowException("线条表达式值为空!");
					}
					
					
				} catch (Exception e) {
					throw new FixFlowException("线条表达式类型转换出错,请检查线条条件!",e);
				}
			}
			else
			{
				return true;
			}

		}
	}

	/**
	 * 线条执行事件
	 * 
	 * @param executionContext
	 *            执行内容对象
	 * @throws Exception
	 */
	public void take(ExecutionContext executionContext){
		// 从执行内容对象中获取令牌 并将令牌指向的当前节点设置为空
		executionContext.getToken().setFlowNode(null);

		try {

			// 执行令牌进入条线事件
			// fireEvent(Event.EVENTTYPE_TRANSITION, executionContext);
			
		} finally {

		}

		// 执行下一个节点的进入事件
		
		if(targetRef instanceof Activity)
		{
			Activity activity=(Activity)targetRef;
			if(activity.getLoopCharacteristics()!=null&&activity.getLoopCharacteristics() instanceof MultiInstanceLoopCharacteristics)
			{
				targetRef.enter(executionContext);
			}
			else
			{
				targetRef.enter(executionContext);
			}
			
		}
		else
		{
			targetRef.enter(executionContext);
		}
		
		
		
	}

}
