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

import java.util.Collection;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;

public class DeleteTaskCmd implements Command<Void> {

	protected Collection<String> taskIds;
	protected String taskId;
	protected boolean cascade;

	public DeleteTaskCmd(String taskId, boolean cascade) {
		this.taskId = taskId;
		this.cascade = cascade;
	}

	public DeleteTaskCmd(Collection<String> taskIds, boolean cascade) {
		this.taskIds = taskIds;
		this.cascade = cascade;
	}

	public Void execute(CommandContext commandContext) {

		if (taskId != null) {
			deleteTask(taskId);
		} else if (taskIds != null) {
			for (String taskId : taskIds) {
				deleteTask(taskId);
			}
		} else {
			throw new FixFlowException("taskId 和 taskIds 不能都为空");
		}

		return null;

	}

	protected void deleteTask(String taskId) {
		Context.getCommandContext().getTaskManager().deleteTaskById(taskId, cascade);
	}

}
