
package com.founder.fix.fixflow.core.impl.persistence.deployer;

import com.founder.fix.fixflow.core.impl.persistence.definition.DeploymentEntity;


/**
 * @author kenshin
 */
public interface Deployer {

  void deploy(DeploymentEntity deployment);
}
