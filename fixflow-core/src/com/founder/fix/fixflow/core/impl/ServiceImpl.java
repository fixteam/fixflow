package com.founder.fix.fixflow.core.impl;

import com.founder.fix.fixflow.core.ProcessService;
import com.founder.fix.fixflow.core.impl.interceptor.CommandExecutor;




/**
 * @author kenshin
 * @param <T>
 */
public class ServiceImpl implements ProcessService{

  protected CommandExecutor commandExecutor;
  
  public CommandExecutor getCommandExecutor() {
    return commandExecutor;
  }

  public void setCommandExecutor(CommandExecutor commandExecutor) {
    this.commandExecutor = commandExecutor;

  }
}
