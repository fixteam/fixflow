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
import com.founder.fix.fixflow.core.impl.persistence.definition.DeploymentPersistence;
import com.founder.fix.fixflow.core.impl.persistence.definition.ProcessDefinitionPersistence;
import com.founder.fix.fixflow.core.impl.persistence.definition.ResourcePersistence;
import com.founder.fix.fixflow.core.impl.persistence.instance.IdentityLinkPersistence;
import com.founder.fix.fixflow.core.impl.persistence.instance.JobPersistence;
import com.founder.fix.fixflow.core.impl.persistence.instance.ProcessInstancePersistence;
import com.founder.fix.fixflow.core.impl.persistence.instance.TaskInstancePersistence;
import com.founder.fix.fixflow.core.impl.persistence.instance.TokenPersistence;
import com.founder.fix.fixflow.core.impl.persistence.instance.VariablePersistence;
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
	/**
	 * 创建任务实例持久化对象
	 * @param connection 可用的数据库连接
	 * @return
	 */
	TaskInstancePersistence createTaskInstancePersistence(Connection connection);
	
	/**
	 * 创建流程实例持久化对象
	 * @param connection 可用的数据库连接
	 * @return
	 */
	ProcessInstancePersistence createProcessInstancePersistence(Connection connection);
	
	/**
	 * 创建任务候选人持久化
	 * @param connection 可用的数据库连接
	 * @return
	 */
	IdentityLinkPersistence createIdentityLinkPersistence(Connection connection);
	
	/**
	 * 创建定时任务持久化
	 * @param connection 可用的数据库连接
	 * @return
	 */
	JobPersistence createJobPersistence(Connection connection);
	
	/**
	 * 创建令牌持久化
	 * @param connection 可用的数据库连接
	 * @return
	 */
	TokenPersistence createTokenPersistence(Connection connection);
	
	/**
	 * 创建流程变量持久化
	 * @param connection 可用的数据库连接
	 * @return
	 */
	VariablePersistence createVariablePersistence(Connection connection);
	
	/**
	 * 创建流程发布持久化
	 * @param connection 可用的数据库连接
	 * @return
	 */
	DeploymentPersistence createDeploymentPersistence(Connection connection);
	
	/**
	 * 创建资源持久化
	 * @param connection 可用的数据库连接
	 * @return
	 */
	ResourcePersistence createResourcePersistence(Connection connection);
	
	/**
	 * 创建流程定义持久化
	 * @param connection 可用的数据库连接
	 * @return
	 */
	ProcessDefinitionPersistence createProcessDefinitionPersistence(Connection connection);
	
}
