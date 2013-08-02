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

import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.ModelServiceImpl;
import com.founder.fix.fixflow.core.impl.persistence.definition.DeploymentEntity;
import com.founder.fix.fixflow.core.impl.persistence.definition.ResourceEntity;
import com.founder.fix.fixflow.core.impl.util.IoUtil;
import com.founder.fix.fixflow.core.impl.util.ReflectUtil;
import com.founder.fix.fixflow.core.model.Deployment;
import com.founder.fix.fixflow.core.model.DeploymentBuilder;

/**
 * @author kenshin
 */
public class DeploymentBuilderImpl implements DeploymentBuilder {


	protected ModelServiceImpl modelService;
	protected DeploymentEntity deployment = new DeploymentEntity();
	protected boolean isDuplicateFilterEnabled = false;

	public DeploymentBuilderImpl(ModelServiceImpl modelService) {
		this.modelService = modelService;
	}

	public DeploymentBuilder addInputStream(String resourceName, InputStream inputStream) {
		if (inputStream == null) {
			throw new FixFlowException("inputStream for resource '" + resourceName + "' is null");
		}
		byte[] bytes = IoUtil.readInputStream(inputStream, resourceName);
		ResourceEntity resource = new ResourceEntity();
		resource.setName(resourceName);
		resource.setBytes(bytes);
		deployment.addResource(resource);
		return this;
	}

	public DeploymentBuilder addClasspathResource(String resource) {
		InputStream inputStream = ReflectUtil.getResourceAsStream(resource);
		if (inputStream == null) {
			throw new FixFlowException("resource '" + resource + "' not found");
		}
		return addInputStream(resource, inputStream);
	}

	public DeploymentBuilder addString(String resourceName, String text) {
		if (text == null) {
			throw new FixFlowException("text is null");
		}
		ResourceEntity resource = new ResourceEntity();
		resource.setName(resourceName);
		resource.setBytes(text.getBytes());
		deployment.addResource(resource);
		return this;
	}

	public DeploymentBuilder addZipInputStream(ZipInputStream zipInputStream) {
		try {
			ZipEntry entry = zipInputStream.getNextEntry();
			while (entry != null) {
				if (!entry.isDirectory()) {
					String entryName = entry.getName();
					byte[] bytes = IoUtil.readInputStream(zipInputStream, entryName);
					ResourceEntity resource = new ResourceEntity();
					resource.setName(entryName);
					resource.setBytes(bytes);
					deployment.addResource(resource);
				}
				entry = zipInputStream.getNextEntry();
			}
		} catch (Exception e) {
			throw new FixFlowException("problem reading zip input stream", e);
		}
		return this;
	}

	public DeploymentBuilder name(String name) {
		deployment.setName(name);
		return this;
	}

	public DeploymentBuilder enableDuplicateFiltering() {
		isDuplicateFilterEnabled = true;
		return this;
	}

	public Deployment deploy() {
		return modelService.deploy(this);
	}

	// getters and setters
	// //////////////////////////////////////////////////////

	public DeploymentEntity getDeployment() {
		return deployment;
	}

	public boolean isDuplicateFilterEnabled() {
		return isDuplicateFilterEnabled;
	}

	public DeploymentBuilder updateResource(String resourceId,
			InputStream inputStream) {
		throw new FixFlowException("该方法暂未实现,请调用modelService里的更新方法");
	}
}
