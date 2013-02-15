
package com.founder.fix.fixflow.core.pvm;



/**
 * @author kenshin
 */
public interface PvmTransition extends PvmProcessElement {

  PvmActivity getSource();

  PvmActivity getDestination();
  
  
}
