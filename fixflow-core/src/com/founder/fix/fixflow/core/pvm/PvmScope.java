
package com.founder.fix.fixflow.core.pvm;

import java.util.List;


/**
 * @author kenshin
 */
public interface PvmScope extends PvmProcessElement {

  List<? extends PvmActivity> getActivities();
  
  PvmActivity findActivity(String activityId);

}
