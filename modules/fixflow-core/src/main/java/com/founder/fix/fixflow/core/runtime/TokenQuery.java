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
package com.founder.fix.fixflow.core.runtime;

import com.founder.fix.fixflow.core.query.Query;

public interface TokenQuery extends Query<TokenQuery, Token>{
	
	/**
	 * 根据流程实例ID查询
	 * @param processInstanceId
	 * @return
	 */
	TokenQuery processInstanceId(String processInstanceId);
	
	/**
	 * 根据令牌ID查询
	 * @param tokenId
	 * @return
	 */
	TokenQuery tokenId(String tokenId);
	
	/**
	 * 查询已结束的令牌
	 * @return
	 */
	TokenQuery tokenIsEnd();
	
	/**
	 * 查询未结束的令牌
	 * @return
	 */
	TokenQuery tokenNotEnd();
	
	/**
	 * 查询历史数据
	 * @return
	 */
	TokenQuery his();
	
	/**
	 * 查询运行数据
	 * @return
	 */
	TokenQuery run();
	
	/**
	 * 根据流程实例ID排序
	 * @return
	 */
	TokenQuery orderByProcessInstanceId();

	/**
	 * 根据令牌ID排序
	 * @return
	 */
	TokenQuery orderByTokenId();


}
