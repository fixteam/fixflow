
package com.founder.fix.fixflow.core.impl.interceptor;


import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;



/**
 * @author kenshin
 */
public class CommandContextInterceptor extends CommandInterceptor {

  protected CommandContextFactory commandContextFactory;
  protected ProcessEngineConfigurationImpl processEngineConfiguration;

  public CommandContextInterceptor() {
	  
  }

  public CommandContextInterceptor(CommandContextFactory commandContextFactory, ProcessEngineConfigurationImpl processEngineConfiguration) {
    this.commandContextFactory = commandContextFactory;
    this.processEngineConfiguration = processEngineConfiguration;
  }

  public <T> T execute(Command<T> command) {
    CommandContext context = commandContextFactory.createCommandContext(command);

   // try {
      Context.setCommandContext(context);
      Context.setProcessEngineConfiguration(processEngineConfiguration);
      //ConnectionManagement.INSTANCE().getConnection();
      Context.setQuartzTransactionAuto(false);
      return next.execute(command);
       
    //} catch (Exception e) {
      //context.exception(e);
    //  throw new FixFlowException("流程执行器出现异常! 错误信息: "+e.getMessage(), e);
    //} finally {
    //  try {
        //context.close();
    //  } finally {
       // Context.removeCommandContext();
        //Context.removeProcessEngineConfiguration();
     // }
   // }
  }
  
  public CommandContextFactory getCommandContextFactory() {
    return commandContextFactory;
  }
  
  public void setCommandContextFactory(CommandContextFactory commandContextFactory) {
    this.commandContextFactory = commandContextFactory;
  }

  public ProcessEngineConfigurationImpl getProcessEngineConfiguration() {
    return processEngineConfiguration;
  }

  public void setProcessEngineContext(ProcessEngineConfigurationImpl processEngineContext) {
    this.processEngineConfiguration = processEngineContext;
  }
}
