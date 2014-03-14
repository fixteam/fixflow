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

public class FixFlowScheduleException extends FixFlowException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FixFlowScheduleException(String exceptionCode) {
		super(exceptionCode);
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * 构造定时任务异常
	 * @param exceptionCode 异常编码 '{@link com.founder.fix.fixflow.core.exception.ExceptionResourceCore <em>ExceptionResourceCore</em>}'
	 * @param nodeId 节点编号
	 * @param nodeName 节点名称
	 */
	public FixFlowScheduleException(String exceptionCode,String nodeId,String nodeName) {
		super(exceptionCode, new Object[] { nodeId,nodeName });
	}
	
	/**
	 * 构造定时任务异常
	 * @param exceptionCode 异常编码 '{@link com.founder.fix.fixflow.core.exception.ExceptionResourceCore <em>ExceptionResourceCore</em>}'
	 * @param nodeId 节点编号
	 * @param nodeName 节点名称
	 * @param cause 异常
	 */
	public FixFlowScheduleException(String exceptionCode,String nodeId,String nodeName, Throwable cause) {
		super(exceptionCode, new Object[] { nodeId,nodeName },cause);
	}


}
