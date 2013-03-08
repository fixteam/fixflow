package com.founder.fix.fixflow.core.exception;

/**
 * 运行时异常，这是所有FixFlow异常的基类
 * 
 * @author kenshin
 */
public class FixFlowException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FixFlowException(String message, Throwable cause) {
		super(message, cause);
	}

	public FixFlowException(String message) {
		super(message);
	}


}
