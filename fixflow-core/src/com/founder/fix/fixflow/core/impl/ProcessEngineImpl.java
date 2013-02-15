package com.founder.fix.fixflow.core.impl;

import java.sql.Connection;

import org.quartz.SchedulerException;


import com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguage;
import com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguageConfig;
import com.founder.fix.fixflow.core.FormService;
import com.founder.fix.fixflow.core.HistoryService;
import com.founder.fix.fixflow.core.IdentityService;
import com.founder.fix.fixflow.core.ModelService;
import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.RuntimeService;
import com.founder.fix.fixflow.core.ScheduleService;
import com.founder.fix.fixflow.core.TaskService;
import com.founder.fix.fixflow.core.cache.CacheHandler;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.interceptor.CommandExecutor;
import com.founder.fix.fixflow.core.impl.util.ReflectUtil;
import com.founder.fix.fixflow.core.scriptlanguage.AbstractScriptLanguageMgmt;
import com.founder.fix.fl.core.FixResourceCore;

public class ProcessEngineImpl implements ProcessEngine {

	protected String name;
	protected ModelService modelService;
	protected RuntimeService runtimeService;
	protected HistoryService historyService;
	protected IdentityService identityService;
	protected TaskService taskService;
	protected FormService formService;
	protected ScheduleService scheduleService;

	protected CommandExecutor commandExecutor;
	protected CacheHandler cacheHandler;
	

	protected ProcessEngineConfigurationImpl processEngineConfiguration;

	public ProcessEngineImpl(
			ProcessEngineConfigurationImpl processEngineConfiguration) {

		this.processEngineConfiguration = processEngineConfiguration;
		this.name = processEngineConfiguration.getProcessEngineName();
		this.modelService = processEngineConfiguration.getModelService();
		this.runtimeService = processEngineConfiguration.getRuntimeService();
		this.historyService = processEngineConfiguration.getHistoryService();
		this.identityService = processEngineConfiguration.getIdentityService();
		this.taskService = processEngineConfiguration.getTaskService();
		this.formService = processEngineConfiguration.getFormService();
		this.scheduleService = processEngineConfiguration.getScheduleService();
		this.cacheHandler = processEngineConfiguration.getCacheHandler();
		this.commandExecutor = processEngineConfiguration.getCommandExecutor();

		this.processEngineConfiguration = processEngineConfiguration;
		this.name = processEngineConfiguration.getProcessEngineName();

		ProcessEngineManagement.registerProcessEngine(this);

	}
	
	

	public void close() {
		try {
			processEngineConfiguration.getSchedulerFactory().getScheduler()
					.shutdown();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProcessEngineManagement.unregister(this);
	}

	public String getName() {
		return name;
	}

	public FormService getFormService() {

		return formService;
	}

	public HistoryService getHistoryService() {

		return historyService;

	}

	public IdentityService getIdentityService() {

		return identityService;

	}

	public ModelService getModelService() {

		return modelService;

	}

	public RuntimeService getRuntimeService() {

		return runtimeService;

	}

	public ScheduleService getScheduleService() {

		return scheduleService;

	}

	public TaskService getTaskService() {

		return taskService;

	}

	public ProcessEngineConfigurationImpl getProcessEngineConfiguration() {
		return processEngineConfiguration;
	}

	public void setExternalContent(ExternalContent externalContent) {
		Connection connection = externalContent.getConnection();
		// CacheObject cacheObject = new CacheObject(cacheHandler);
		// Context.setCacheObject(cacheObject);

		/*
		 * if(connection==null){ Context.setBshInterpreter(new Interpreter());
		 * 
		 * 
		 * DataBase dataBase =
		 * this.processEngineConfiguration.getSelectedDatabase();
		 * 
		 * 
		 * if (connection == null) { try {
		 * Class.forName(dataBase.getDriverClassName()); } catch
		 * (ClassNotFoundException e) { // TODO Auto-generated catch block //
		 * e.printStackTrace(); } String url = dataBase.getUrl(); String user =
		 * dataBase.getUsername(); String password = dataBase.getPassword(); try
		 * { connection = DriverManager.getConnection(url, user, password); }
		 * catch (SQLException e) { // TODO Auto-generated catch block //
		 * e.printStackTrace(); }
		 * 
		 * }
		 * 
		 * Context.setDbConnection(connection);
		 * 
		 * 
		 * String authenticatedUserId=externalContent.getAuthenticatedUserId();
		 * Authentication.setAuthenticatedUserId(authenticatedUserId); } else{
		 */
		
		//ContextInstanceImpl contextInstanceImpl = new ContextInstanceImpl(processInstance);
		AbstractScriptLanguageMgmt abstractScriptLanguageMgmt=null;
		ScriptLanguageConfig scriptLanguageConfig=processEngineConfiguration.getScriptLanguageConfig();
		for (ScriptLanguage scriptLanguage : scriptLanguageConfig.getScriptLanguage()) {
			if(scriptLanguage.getId().equals(scriptLanguageConfig.getSelected())){
				abstractScriptLanguageMgmt= (AbstractScriptLanguageMgmt)ReflectUtil.instantiate(scriptLanguage.getClassImpl());
				break;
			}
		}
		

		

		Context.setAbstractScriptLanguageMgmt(abstractScriptLanguageMgmt.init());
		Context.setDbConnection(connection);
		String authenticatedUserId = externalContent.getAuthenticatedUserId();
		Authentication.setAuthenticatedUserId(authenticatedUserId);
		
		String languageType=externalContent.getLanguageType();
		if(languageType==null||languageType.equals("")){
			//Context.setLanguageType("defauld");
		}
		else{
			FixResourceCore.setNowLanguage(languageType);}
		
		// }

	}
	
	public void setLanguageType(String languageType){
		
		if(languageType==null||languageType.equals("")){
			//Context.setLanguageType("defauld");
		}
		else{
			FixResourceCore.setNowLanguage(languageType);
			//Context.setLanguageType(languageType);
		}
	}
	
	

	public void contextClose() {

		Context.removeCommandContext();
		Context.removeProcessEngineConfiguration();
		Context.removeDbConnection();
		Context.removeAbstractScriptLanguageMgmt();
		Context.removeLanguageType();
	}

	public void contextNoConnectionClose() {

		Context.removeCommandContext();
		Context.removeProcessEngineConfiguration();
		Context.removeAbstractScriptLanguageMgmt();
		Context.removeLanguageType();
	}

}
