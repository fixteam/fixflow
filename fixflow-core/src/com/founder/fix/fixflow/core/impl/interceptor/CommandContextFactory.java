
package com.founder.fix.fixflow.core.impl.interceptor;

import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;


/**
 * @author kenshin
 */
public class CommandContextFactory {

  protected ProcessEngineConfigurationImpl processEngineConfiguration;
  
  public CommandContext createCommandContext(Command<?> cmd) {
    return new CommandContext(cmd, processEngineConfiguration);
  }
  
  // getters and setters //////////////////////////////////////////////////////
  
  public ProcessEngineConfigurationImpl getProcessEngineConfiguration() {
    return processEngineConfiguration;
  }

  public void setProcessEngineConfiguration(ProcessEngineConfigurationImpl processEngineConfiguration) {
    this.processEngineConfiguration = processEngineConfiguration;
  }
}