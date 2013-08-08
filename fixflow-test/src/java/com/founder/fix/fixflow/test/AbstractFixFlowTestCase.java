package com.founder.fix.fixflow.test;

import java.lang.reflect.Method;


import com.founder.fix.fixflow.core.FormService;
import com.founder.fix.fixflow.core.HistoryService;
import com.founder.fix.fixflow.core.IdentityService;
import com.founder.fix.fixflow.core.ModelService;
import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.RuntimeService;
import com.founder.fix.fixflow.core.ScheduleService;
import com.founder.fix.fixflow.core.TaskService;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.ExternalContent;
import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;
import com.founder.fix.fixflow.core.impl.ProcessEngineImpl;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.model.DeploymentBuilder;

import junit.framework.TestCase;

public abstract class AbstractFixFlowTestCase extends TestCase {

	public static ProcessEngine processEngine=ProcessEngineManagement.getDefaultProcessEngine();
	protected String deploymentId;

	protected ProcessEngineConfigurationImpl processEngineConfiguration;
	protected ModelService modelService;
	protected RuntimeService runtimeService;
	protected TaskService taskService;
	protected FormService formService;
	protected HistoryService historyService;
	protected IdentityService identityService;
	protected ScheduleService scheduleService;
	
	
	
	

	protected void initializeServices() {
		processEngineConfiguration = ((ProcessEngineImpl) processEngine).getProcessEngineConfiguration();
		

		modelService = processEngine.getModelService();
		runtimeService = processEngine.getRuntimeService();
		taskService = processEngine.getTaskService();
		formService = processEngine.getFormService();
		historyService = processEngine.getHistoryService();
		identityService = processEngine.getIdentityService();
		scheduleService = processEngine.getScheduleService();
	}



	protected void initializeProcessEngine() {


		ExternalContent externalContent=new ExternalContent();
		externalContent.setAuthenticatedUserId(Authentication.getSystemId());
		externalContent.setConnectionManagement("General");
		processEngine.setExternalContent(externalContent);
		//processEngine.getProcessEngineConfiguration().setConnectionManagement("General");
	}

	public void runBare() throws Throwable {

		if (modelService == null) {
			initializeProcessEngine();
			initializeServices();
		}

		try {
			deploymentId = annotationDeploymentSetUp(processEngine, getClass(), getName());
			super.runBare();
		} finally {
			cleanDb();
		}

	}

	protected void cleanDb() {

		processEngine.rollBackConnection();

		processEngine.contextClose(true, true);

	}


	public String annotationDeploymentSetUp(ProcessEngine processEngine, Class<?> testClass, String methodName) throws Exception {
		String deploymentId = null;
		Method method = null;
		try {
			method = testClass.getDeclaredMethod(methodName, (Class<?>[]) null);
		} catch (Exception e) {
			throw new FixFlowException("获取方法失败!", e);
		}
		Deployment deploymentAnnotation = method.getAnnotation(Deployment.class);
		if (deploymentAnnotation != null) {
			String[] resources = deploymentAnnotation.resources();
			if (resources.length == 0) {
				return null;
			}


			DeploymentBuilder deploymentBuilder = processEngine.getModelService().createDeployment().name("测试名称");

			for (String resource : resources) {
				deploymentBuilder.addClasspathResource(resource);
			}

			deploymentId = deploymentBuilder.deploy().getId();

			// connection.close();
			// processEngine.contextClose();
		}
		return deploymentId;

	}

}
