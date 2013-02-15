

package com.founder.fix.fixflow.core.pvm;

import java.io.Serializable;


/**
 * @author kenshin
 */
public interface PvmProcessElement extends Serializable {

  String getId();
  
  PvmProcessDefinition getProcessDefinition();
  
  Object getProperty(String name);

}
