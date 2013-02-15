
package com.founder.fix.fixflow.core.model;

import com.founder.fix.fixflow.core.query.Query;



/**
 * 定义部署查询器
 * @author kenshin
 *
 */
public interface DeploymentQuery extends Query<DeploymentQuery, Deployment>{
  

  DeploymentQuery deploymentId(String deploymentId);
  

  DeploymentQuery deploymentName(String name);
  

  DeploymentQuery deploymentNameLike(String nameLike);

  

  DeploymentQuery orderByDeploymentId();
  

  DeploymentQuery orderByDeploymentName();
  

  DeploymentQuery orderByDeploymenTime();
}
