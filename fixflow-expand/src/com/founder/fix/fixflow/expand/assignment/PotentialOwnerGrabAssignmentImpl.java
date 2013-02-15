package com.founder.fix.fixflow.expand.assignment;

import com.founder.fix.fixflow.core.action.AssignmentHandler;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.task.Assignable;

/**
 * 共享   (无需领取抢占式完成任务)
 * @author kenshin
 *
 */
public class PotentialOwnerGrabAssignmentImpl implements AssignmentHandler {

	public void assign(Assignable assignable, ExecutionContext executionContext) {
		// TODO 自动生成的方法存根
		throw new FixFlowException("任务的分配策略没有实现类,请检查!");
	}

}
