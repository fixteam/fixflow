package com.founder.fix.fixflow.core.exception;

import com.founder.fix.fixflow.core.internationalization.FixFlowResources;
import com.founder.fix.fixflow.core.internationalization.ResourcesUtil;

public class FixFlowBizException extends FixFlowException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1974264253264503442L;

	public FixFlowBizException(String message) {
		super((ResourcesUtil.getExpressionAll(FixFlowResources.ExceptionResource,message)));
	}

	
	public FixFlowBizException(String message, Throwable cause) {
		super(ResourcesUtil.getExpressionAll(FixFlowResources.ExceptionResource, message), cause);
	}


}
