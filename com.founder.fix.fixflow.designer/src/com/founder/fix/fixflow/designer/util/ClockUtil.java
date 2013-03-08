
package com.founder.fix.fixflow.designer.util;

import java.util.Date;


/**
 * 时间工具类
 * @author kenshin
 *
 */
public class ClockUtil {
  
  private volatile static Date CURRENT_TIME = null;
  
  public static void setCurrentTime(Date currentTime) {
    ClockUtil.CURRENT_TIME = currentTime;
  }
  
  public static void reset() {
    ClockUtil.CURRENT_TIME = null;
  } 
  
  public static Date getCurrentTime() {
    if (CURRENT_TIME != null) {
      return CURRENT_TIME;
    }
    return new Date();
  }

}
