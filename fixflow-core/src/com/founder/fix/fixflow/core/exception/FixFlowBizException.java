package com.founder.fix.fixflow.core.exception;


public class FixFlowBizException extends FixFlowException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1974264253264503442L;

	public FixFlowBizException(String message) {
		
		super(message);
		//super((ResourcesUtil.getExpressionAll(FixFlowResources.ExceptionResource,message)));
	}

	
	public FixFlowBizException(String message, Throwable cause) {
		
		super(message, cause);
		//super(ResourcesUtil.getExpressionAll(FixFlowResources.ExceptionResource, message), cause);
	}


}
