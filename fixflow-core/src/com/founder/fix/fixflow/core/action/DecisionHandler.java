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
package com.founder.fix.fixflow.core.action;

import com.founder.fix.fixflow.core.runtime.ExecutionContext;


public interface DecisionHandler {
	
	/**
	 * 线条判定方法,返回 true 则允许通过线条,返回 false 则不允许通过线条.
	 * @param executionContext 上下文执行内容对象
	 * @return 是否允许通过
	 * @throws Exception
	 */
	boolean decide(ExecutionContext executionContext);

}
