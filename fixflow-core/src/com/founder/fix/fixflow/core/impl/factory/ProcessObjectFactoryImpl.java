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


	public ContextInstance createContextInstance(ProcessInstance processInstance) {
		ContextInstanceImpl contextInstanceImpl = new ContextInstanceImpl(processInstance);
		return contextInstanceImpl;
	}
	
	







}
