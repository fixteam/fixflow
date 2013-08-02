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
