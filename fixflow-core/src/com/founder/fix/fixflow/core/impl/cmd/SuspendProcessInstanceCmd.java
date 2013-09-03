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
package com.founder.fix.fixflow.core.impl.cmd;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.ProcessInstanceManager;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;

public class SuspendProcessInstanceCmd  implements Command<Void>{

	protected String processInstanceId;
	
	
	public SuspendProcessInstanceCmd(String processInstanceId){
		this.processInstanceId=processInstanceId;
	}
	
	public Void execute(CommandContext commandContext) {
		if (processInstanceId == null || processInstanceId.equals("")) {
			throw new FixFlowException("流程实例编号为空！");
		}

		// 创建流程实例管理器
		ProcessInstanceManager processInstanceManager = commandContext.getProcessInstanceManager();

		// 获取流程实例
		ProcessInstanceEntity processInstanceImpl = processInstanceManager.findProcessInstanceById(processInstanceId);

		
		if(processInstanceImpl.isSuspended()){
			throw new FixFlowException("流程实例已经暂停,不能再次暂停");
		}
		
		//暂停流程实例
		processInstanceImpl.suspend();
	
		try {
			// 持久化实例
			processInstanceManager.saveProcessInstance(processInstanceImpl);
		} catch (Exception e) {
			throw new FixFlowException("流程实例持久化失败!", e);
		}
		return null;
	}
	
	
	
	

}
