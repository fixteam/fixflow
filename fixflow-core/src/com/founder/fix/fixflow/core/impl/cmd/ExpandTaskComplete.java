package com.founder.fix.fixflow.core.impl.cmd;



import com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.command.AbstractCustomExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.util.ReflectUtil;

public class ExpandTaskComplete<A extends AbstractCustomExpandTaskCommand,T> implements Command<T>{

	protected ExpandTaskCommand expandTaskCommand;
	
	public ExpandTaskComplete (ExpandTaskCommand expandTaskCommand){
		this.expandTaskCommand=expandTaskCommand;
	}
	
	

	@SuppressWarnings("unchecked")
	public T execute(CommandContext commandContext) {
		// TODO Auto-generated method stub
		Object[] obj = new Object[] {expandTaskCommand};  
		
		
		TaskCommandDef taskCommandDef= commandContext.getProcessEngineConfigurationImpl().getTaskCommandDefMap().get(this.expandTaskCommand.getCommandType());
		if(taskCommandDef!=null){
			String classNameString=taskCommandDef.getCmd();
			if(classNameString==null||classNameString.equals("")){
				throw new FixFlowException("配置文件中ID为 "+this.expandTaskCommand.getCommandType() + " 的扩展配置cmd属性不能为空!");
			}
			
			String commandClassNameString=taskCommandDef.getCommand();
			if(commandClassNameString==null||commandClassNameString.equals("")){
				throw new FixFlowException("配置文件中ID为 "+this.expandTaskCommand.getCommandType() + " 的扩展配置command属性不能为空!");
			}
			
			//commandClassNameString
			AbstractCustomExpandTaskCommand abstractCustomExpandTaskCommand=(AbstractCustomExpandTaskCommand)ReflectUtil.instantiate(commandClassNameString, obj);
			
			Object[] objTemp = new Object[] {abstractCustomExpandTaskCommand};  
			AbstractExpandTaskCmd<A,T> abstractExpandTaskCmd=(AbstractExpandTaskCmd<A,T>)ReflectUtil.instantiate(classNameString, objTemp);
			return (T) abstractExpandTaskCmd.execute(commandContext);
			
		}
		else{
			throw new FixFlowException("配置文件中不存在ID为 "+this.expandTaskCommand.getCommandType() + " 的扩展配置.");
		}
	

	}


}
