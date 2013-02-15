
package com.founder.fix.fixflow.core.impl.interceptor;


/**
 * @author kenshin
 */
public abstract class CommandInterceptor implements CommandExecutor {

  /** will be initialized by the {@link CommandInterceptorChains} */
  protected CommandExecutor next;

  public CommandExecutor getNext() {
    return next;
  }
  
  public void setNext(CommandExecutor next) {
    this.next = next;
  }
}
