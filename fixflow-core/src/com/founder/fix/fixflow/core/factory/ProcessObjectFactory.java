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
package com.founder.fix.fixflow.core.factory;



import java.sql.Connection;

import com.founder.fix.fixflow.core.action.AssignmentHandler;
import com.founder.fix.fixflow.core.action.CommandHandler;
import com.founder.fix.fixflow.core.context.ContextInstance;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.factory.ProcessObjectFactoryImpl;
import com.founder.fix.fixflow.core.impl.persistence.instance.TaskInstancePersistence;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;


import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.task.TaskDefinition;
import com.founder.fix.fixflow.core.task.TaskMgmtInstance;


public interface ProcessObjectFactory {
	
	
	

	ProcessObjectFactory FACTORYINSTANCE = ProcessObjectFactoryImpl.init();

	
	ExecutionContext createExecutionContext(TokenEntity token);
	
	
	TaskMgmtInstance createTaskMgmtInstance();
	

	
	TaskDefinition createTaskDefinition(UserTaskBehavior userTask);
	
	AssignmentHandler createAssignmentHandler(String className);
	
	CommandHandler createCommandHandler(String className);
	
	ContextInstance createContextInstance(ProcessInstance processInstance);
	
	TaskInstancePersistence createTaskInstancePersistence(Connection connection);
	
}
