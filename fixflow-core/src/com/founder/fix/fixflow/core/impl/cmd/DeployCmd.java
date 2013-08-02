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
package com.founder.fix.fixflow.core.impl.cmd;

import java.util.Arrays;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.model.DeploymentBuilderImpl;
import com.founder.fix.fixflow.core.impl.persistence.definition.DeploymentEntity;
import com.founder.fix.fixflow.core.impl.persistence.definition.ResourceEntity;
import com.founder.fix.fixflow.core.impl.util.ClockUtil;
import com.founder.fix.fixflow.core.model.Deployment;

/**
 * 
 * @author kenshin
 *
 * @param <T>
 */
public class DeployCmd<T> implements Command<Deployment> {

	protected DeploymentBuilderImpl deploymentBuilder;

	public DeployCmd(DeploymentBuilderImpl deploymentBuilder) {
		this.deploymentBuilder = deploymentBuilder;
	}

	public Deployment execute(CommandContext commandContext) {
		DeploymentEntity deployment = deploymentBuilder.getDeployment();

		deployment.setDeploymentTime(ClockUtil.getCurrentTime());

		if (deploymentBuilder.isDuplicateFilterEnabled()) {
			DeploymentEntity existingDeployment = Context.getCommandContext().getDeploymentManager()
					.findLatestDeploymentByName(deployment.getName());

			if ((existingDeployment != null) && !deploymentsDiffer(deployment, existingDeployment)) {
				return existingDeployment;
			}
		}

		deployment.setNew(true);

		Context.getCommandContext().getDeploymentManager().insertDeployment(deployment);

		return deployment;
	}

	protected boolean deploymentsDiffer(DeploymentEntity deployment, DeploymentEntity saved) {
		Map<String, ResourceEntity> resources = deployment.getResources();
		Map<String, ResourceEntity> savedResources = saved.getResources();
		if (!resources.keySet().equals(savedResources.keySet())) {
			return true;
		}
		for (String resourceName : resources.keySet()) {
			ResourceEntity resource = resources.get(resourceName);
			byte[] bytes = resource.getBytes();
			ResourceEntity savedResource = savedResources.get(resourceName);
			byte[] savedBytes = savedResource.getBytes();
			if (!Arrays.equals(bytes, savedBytes)) {
				return true;
			}
		}
		return false;
	}

}
