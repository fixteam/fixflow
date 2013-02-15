
package com.founder.fix.fixflow.core.impl.interceptor;


/**
 * @author kenshin
 */
public interface Session {
  
  void flush();

  void close();
}
