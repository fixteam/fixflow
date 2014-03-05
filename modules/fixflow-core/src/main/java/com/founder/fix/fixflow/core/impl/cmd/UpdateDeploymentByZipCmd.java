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
 * @author yangchenhui
 */
package com.founder.fix.fixflow.core.impl.cmd;

import java.util.zip.ZipInputStream;

import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.model.DeploymentBuilder;

public class UpdateDeploymentByZipCmd implements Command<String> {

	protected ZipInputStream zipInputStream;
	protected DeploymentBuilder deploymentBuilder;
	protected String deploymentId;
	public UpdateDeploymentByZipCmd(DeploymentBuilder deploymentBuilder,ZipInputStream zipInputStream,String deploymentId){
		this.zipInputStream = zipInputStream;
		this.deploymentBuilder = deploymentBuilder;
		this.deploymentId = deploymentId;
	}
	public String execute(CommandContext commandContext) {
		//发布流程定义
		deploymentBuilder.addZipInputStream(zipInputStream);
		//设置需要更新的发布号
		deploymentBuilder.updateDeploymentId(deploymentId);
		//更新流程定义
		return deploymentBuilder.deploy().getId();
	}

}
