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

import java.io.InputStream;

import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.persistence.definition.ResourceEntity;
import com.founder.fix.fixflow.core.impl.util.IoUtil;

public class UpdateResourceCmd implements Command<Void> {

	protected String resourceId;
	protected InputStream inputStream;

	public UpdateResourceCmd(String resourceId, InputStream inputStream) {
		this.resourceId = resourceId;
		this.inputStream = inputStream;
	}

	public Void execute(CommandContext commandContext) {
		// TODO Auto-generated method stub

		ResourceEntity resourceEntity = new ResourceEntity();
		resourceEntity.setId(resourceId);
		resourceEntity
				.setBytes(IoUtil.readInputStream(inputStream, resourceId));

		commandContext.getResourceManager().updateResource(resourceEntity);
		return null;

	}

}
