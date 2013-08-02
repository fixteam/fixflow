/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 */
package com.founder.fix.fixflow.core.impl.model;

import java.util.List;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.AbstractQuery;
import com.founder.fix.fixflow.core.impl.Page;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.interceptor.CommandExecutor;
import com.founder.fix.fixflow.core.model.Deployment;
import com.founder.fix.fixflow.core.model.DeploymentQuery;




/**
 * @author kenshin
 */
public class DeploymentQueryImpl extends AbstractQuery<DeploymentQuery, Deployment> implements DeploymentQuery {

  protected String deploymentId;
  protected String name;
  protected String nameLike;

  public DeploymentQueryImpl() {
  }

  public DeploymentQueryImpl(CommandContext commandContext) {
    super(commandContext);
  }

  public DeploymentQueryImpl(CommandExecutor commandExecutor) {
    super(commandExecutor);
  }

  public DeploymentQueryImpl deploymentId(String deploymentId) {
    if (deploymentId == null) {
      throw new FixFlowException("Deployment id is null");
    }
    this.deploymentId = deploymentId;
    return this;
  }
  
  public DeploymentQueryImpl deploymentName(String deploymentName) {
    if (deploymentName == null) {
      throw new FixFlowException("deploymentName is null");
    }
    this.name = deploymentName;
    return this;
  }

  public DeploymentQueryImpl deploymentNameLike(String nameLike) {
    if (nameLike == null) {
      throw new FixFlowException("deploymentNameLike is null");
    }
    this.nameLike = nameLike;
    return this;
  }
  
  //sorting ////////////////////////////////////////////////////////
  
  public DeploymentQuery orderByDeploymentId() {
    return orderBy(DeploymentQueryProperty.DEPLOYMENT_ID);
  }
  
  public DeploymentQuery orderByDeploymenTime() {
    return orderBy(DeploymentQueryProperty.DEPLOY_TIME);
  }
  
  public DeploymentQuery orderByDeploymentName() {
    return orderBy(DeploymentQueryProperty.DEPLOYMENT_NAME);
  }
  
  //results ////////////////////////////////////////////////////////
  

  public long executeCount(CommandContext commandContext) {
    checkQueryOk();
    return commandContext
      .getDeploymentManager()
      .findDeploymentCountByQueryCriteria(this);
  }

 
  public List<Deployment> executeList(CommandContext commandContext, Page page) {
    checkQueryOk();
    return commandContext
      .getDeploymentManager()
      .findDeploymentsByQueryCriteria(this, page);
  }
  
  //getters ////////////////////////////////////////////////////////
  
  public String getDeploymentId() {
    return deploymentId;
  }
  
  public String getName() {
    return name;
  }

  public String getNameLike() {
    return nameLike;
  }
}
