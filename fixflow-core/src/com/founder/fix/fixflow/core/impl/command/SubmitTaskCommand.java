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
package com.founder.fix.fixflow.core.impl.command;

public class SubmitTaskCommand extends BaseTaskCommand{
	
	
	
	

	public SubmitTaskCommand(){};
	
	public SubmitTaskCommand(BaseTaskCommand taskCommand) {
		// TODO Auto-generated constructor stub
		super(taskCommand);
	}
	/**
	 * 业务关联键
	 */
	protected String businessKey;
	/**
	 * 获取业务关联键
	 * @return 业务关联键
	 */
	public String getBusinessKey() {
		return businessKey;
	}

	/**
	 * 设置业务关联键
	 * @param businessKey 业务关联键
	 */
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	
	
	/**
	 * 提交人
	 */
	protected String initiator;
	
	
	
	/**
	 * 提交人
	 */
	public String getInitiator() {
		return initiator;
	}
	/**
	 * 提交人
	 */
	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}
	
	
	
	
	

}
