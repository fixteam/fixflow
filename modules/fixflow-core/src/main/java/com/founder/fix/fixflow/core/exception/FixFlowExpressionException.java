/**
 * Copyright 1995-2014 Wisedu Co.,Ltd.
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
package com.founder.fix.fixflow.core.exception;

import com.founder.fix.fixflow.core.internationalization.ExceptionCode;

/**
 * 表达式异常
 * @author kenshin
 *
 */
public class FixFlowExpressionException extends FixFlowException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 发生异常的表达式内容
	 */
	private String expressionText;

	/**
	 * 构造表达式异常
	 * @param expressionText 执行的表达式脚本
	 */
	public FixFlowExpressionException(String expressionText) {
		super(ExceptionCode.CLASSLOAD_EXCEPTION_DEFAULT, new Object[] { expressionText });
		this.expressionText = expressionText;
	}


	/**
	 * 构造表达式异常
	 * @param exceptionCode 异常编码 '{@link com.founder.fix.fixflow.core.exception.ExceptionResourceCore <em>ExceptionResourceCore</em>}'
	 * @param expressionText 执行的表达式脚本
	 */
	public FixFlowExpressionException(String exceptionCode, String expressionText) {
		super(exceptionCode, new Object[] { expressionText });
		this.expressionText = expressionText;
	}


	/**
	 * 构造表达式异常
	 * @param expressionText 执行的表达式脚本
	 * @param cause 异常
	 */
	public FixFlowExpressionException(String expressionText, Throwable cause) {
		super(ExceptionCode.CLASSLOAD_EXCEPTION_DEFAULT, new Object[] { expressionText }, cause);
		this.expressionText = expressionText;
	}

	/**
	 * 构造表达式异常 
	 * @param exceptionCode 异常编码 '{@link com.founder.fix.fixflow.core.exception.ExceptionResourceCore <em>ExceptionResourceCore</em>}'
	 * @param expressionText 表达式内容
	 * @param nodeId 节点编号
	 * @param nodeName 节点名称
	 */
	public FixFlowExpressionException(String exceptionCode, String expressionText,String nodeId,String nodeName) {
		super(exceptionCode, new Object[] { expressionText,nodeId,nodeName });
		this.expressionText = expressionText;
	}

	

	/**
	 * 构造表达式异常 
	 * @param exceptionCode 异常编码 '{@link com.founder.fix.fixflow.core.exception.ExceptionResourceCore <em>ExceptionResourceCore</em>}'
	 * @param expressionText 表达式内容
	 * @param nodeId 节点编号
	 * @param nodeName 节点名称
	 * @param cause 异常
	 */
	public FixFlowExpressionException(String exceptionCode, String expressionText,String nodeId,String nodeName, Throwable cause) {
		super(exceptionCode, new Object[] { expressionText,nodeId,nodeName }, cause);
		this.expressionText = expressionText;
	}

	/**
	 * 获取发生异常的表达式内容
	 * @return 表达式文本
	 */
	public String getExpressionText() {
		return expressionText;
	}

}
