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

import java.util.Map;

import com.founder.fix.bpmn2extensions.coreconfig.ExpandCmd;
import com.founder.fix.bpmn2extensions.coreconfig.ExpandCmdConfig;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.util.ReflectUtil;

public class ExpandCommonCmd<T> implements Command<T> {
	
	protected String cmdId;
	
	protected Map<String, Object> parameterMap;

	public ExpandCommonCmd(String cmdId,Map<String, Object> parameterMap){
		this.cmdId=cmdId;
		this.parameterMap=parameterMap;
	}
	
	@SuppressWarnings("unchecked")
	public T execute(CommandContext commandContext) {
		// TODO 自动生成的方法存根
		
		
		
		
		//Object[] obj = new Object[] {parameterMap};  
		ExpandCmd expandCmdObj=null;
		
		ExpandCmdConfig expandCmdConfig= commandContext.getProcessEngineConfigurationImpl().getExpandCmdConfig();
		for (ExpandCmd expandCmd : expandCmdConfig.getExpandCmd()) {
			if(expandCmd.getId().equals(cmdId)){
				expandCmdObj=expandCmd;
				break;
			}
			
		}
		if(expandCmdObj!=null){
			String classNameString=expandCmdObj.getCmd();
			if(classNameString==null||classNameString.equals("")){
				throw new FixFlowException("配置文件中ID为 "+cmdId + " 的扩展配置cmd属性不能为空!");
			}
			
			
			//commandClassNameString
			Object[] objTemp = new Object[] {parameterMap};  
			AbstractCommand<T> abstractCommand=(AbstractCommand<T>)ReflectUtil.instantiate(classNameString, objTemp);
			
		
			return (T) abstractCommand.execute(commandContext);
			
		}
		else{
			throw new FixFlowException("配置文件中不存在ID为 "+cmdId + " 的扩展配置.");
		}
		
	
	}

}
