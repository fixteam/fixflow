
package com.founder.fix.fixflow.core.impl.interceptor;




/**
 * @author Kenshin
 */
public interface Command <T> {
  


  T execute(CommandContext commandContext);
  
}
