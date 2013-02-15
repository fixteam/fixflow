package com.founder.fix.fixflow.core.model;

import java.util.Date;


/**
 * 
 * @author kenshin
 *
 */
public interface Deployment {

  String getId();
  
  String getName();
  
  Date getDeploymentTime();
  
}
