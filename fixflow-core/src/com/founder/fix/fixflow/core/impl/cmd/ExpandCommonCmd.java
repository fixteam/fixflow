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
