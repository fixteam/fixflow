

package com.founder.fix.fixflow.core.pvm;

import java.util.List;


/**
 * @author kenshin
 */
public interface PvmActivity extends PvmScope {

  PvmScope getParent();

  List<PvmTransition> getIncomingTransitions();

  List<PvmTransition> getOutgoingTransitions();
  
  PvmTransition findOutgoingTransition(String transitionId);
}
