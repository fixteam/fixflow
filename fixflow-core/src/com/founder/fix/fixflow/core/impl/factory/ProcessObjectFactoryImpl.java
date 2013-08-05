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
package com.founder.fix.fixflow.core.impl.factory;

import java.sql.Connection;
import java.util.List;



import com.founder.fix.bpmn2extensions.coreconfig.ExpandClass;
import com.founder.fix.bpmn2extensions.coreconfig.ExpandClassConfig;
import com.founder.fix.fixflow.core.action.AssignmentHandler;
import com.founder.fix.fixflow.core.action.CommandHandler;
import com.founder.fix.fixflow.core.context.ContextInstance;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.context.ContextInstanceImpl;


import com.founder.fix.fixflow.core.impl.persistence.instance.ProcessInstancePersistence;
import com.founder.fix.fixflow.core.impl.persistence.instance.TaskInstancePersistence;
import com.founder.fix.fixflow.core.impl.runtime.ExecutionContextImpl;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.task.TaskDefinitionImpl;
import com.founder.fix.fixflow.core.impl.util.ReflectUtil;

import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.task.TaskDefinition;
import com.founder.fix.fixflow.core.task.TaskMgmtInstance;


public class ProcessObjectFactoryImpl implements ProcessObjectFactory {

	public static ProcessObjectFactory init() {
		return new ProcessObjectFactoryImpl();
	}

	public ExecutionContext createExecutionContext(TokenEntity token) {
		ExecutionContextImpl executionContextImpl = new ExecutionContextImpl(token);
		return executionContextImpl;
	}

	

	public TaskDefinition createTaskDefinition(UserTaskBehavior userTask) {
		TaskDefinitionImpl taskDefinitionImpl = new TaskDefinitionImpl(userTask);
		return taskDefinitionImpl;
	}


	public AssignmentHandler createAssignmentHandler(String className) {
		AssignmentHandler assignmentHandler = (AssignmentHandler) ReflectUtil.instantiate(className);
		return assignmentHandler;
	}

	public CommandHandler createCommandHandler(String className) {
		CommandHandler commandHandler = (CommandHandler) ReflectUtil.instantiate(className);
		return commandHandler;
	}


	public TaskMgmtInstance createTaskMgmtInstance() {
		
		ExpandClassConfig expandClassConfig=Context.getProcessEngineConfiguration().getExpandClassConfig();
		List<ExpandClass>  expandClasses=expandClassConfig.getExpandClass();
		for (ExpandClass expandClass : expandClasses) {
			if(expandClass.getClassId().equals("TaskMgmtInstance")){
				
				TaskMgmtInstance taskMgmtInstance =(TaskMgmtInstance) ReflectUtil.instantiate(expandClass.getClassImpl());
				return taskMgmtInstance;
			}
		}
		throw new FixFlowException("流程引擎扩展配置里的TaskMgmtInstance实现类指定错误");
		
	}
	
	/**
	 * 创建任务实例持久化对象
	 */
	public TaskInstancePersistence createTaskInstancePersistence(Connection connection) {
		
		ExpandClassConfig expandClassConfig=Context.getProcessEngineConfiguration().getExpandClassConfig();
		List<ExpandClass>  expandClasses=expandClassConfig.getExpandClass();
		for (ExpandClass expandClass : expandClasses) {
			if(expandClass.getClassId().equals("TaskInstancePersistence")){
				Object[] objTemp = new Object[] {connection};  
				TaskInstancePersistence taskInstancePersistence =(TaskInstancePersistence) ReflectUtil.instantiate(expandClass.getClassImpl(),objTemp);
				return taskInstancePersistence;
			}
		}
		throw new FixFlowException("流程引擎扩展配置里的TaskInstancePersistence实现类指定错误");
		
	}
	
	/**
	 * 创建流程实例持久化对象
	 */
	public ProcessInstancePersistence createProcessInstancePersistence(
			Connection connection) {
		ExpandClassConfig expandClassConfig=Context.getProcessEngineConfiguration().getExpandClassConfig();
		List<ExpandClass>  expandClasses=expandClassConfig.getExpandClass();
		for (ExpandClass expandClass : expandClasses) {
			if(expandClass.getClassId().equals("ProcessInstancePersistence")){
				Object[] objTemp = new Object[] {connection};  
				ProcessInstancePersistence processInstancePersistence =(ProcessInstancePersistence) ReflectUtil.instantiate(expandClass.getClassImpl(),objTemp);
				return processInstancePersistence;
			}
		}
		throw new FixFlowException("流程引擎扩展配置里的ProcessInstancePersistence实现类指定错误");
	}


	public ContextInstance createContextInstance(ProcessInstance processInstance) {
		ContextInstanceImpl contextInstanceImpl = new ContextInstanceImpl(processInstance);
		return contextInstanceImpl;
	}
	
	







}
