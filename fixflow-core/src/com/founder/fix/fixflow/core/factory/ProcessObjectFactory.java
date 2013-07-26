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
