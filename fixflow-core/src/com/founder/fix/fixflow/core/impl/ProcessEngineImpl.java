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
package com.founder.fix.fixflow.core.impl;

import java.sql.Connection;
import java.util.Map;


import com.founder.fix.fixflow.core.ConnectionManagement;
import com.founder.fix.fixflow.core.FormService;
import com.founder.fix.fixflow.core.HistoryService;
import com.founder.fix.fixflow.core.IdentityService;
import com.founder.fix.fixflow.core.ManagementService;
import com.founder.fix.fixflow.core.ModelService;
import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.RuntimeService;
import com.founder.fix.fixflow.core.ScheduleService;
import com.founder.fix.fixflow.core.TaskService;
import com.founder.fix.fixflow.core.cache.CacheHandler;
import com.founder.fix.fixflow.core.exception.FixFlowDbException;
import com.founder.fix.fixflow.core.impl.db.FixConnectionResult;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.interceptor.CommandExecutor;
import com.founder.fix.fixflow.core.impl.processversion.FixFlowVersion;
import com.founder.fix.fixflow.core.impl.threadpool.FixThreadPoolExecutor;


public class ProcessEngineImpl implements ProcessEngine {

	protected String name;
	protected ModelService modelService;
	protected RuntimeService runtimeService;
	protected HistoryService historyService;
	protected IdentityService identityService;
	protected TaskService taskService;
	protected FormService formService;
	protected ScheduleService scheduleService;
	protected ManagementService managementService;
	

	

	protected CommandExecutor commandExecutor;
	protected CacheHandler cacheHandler;

	protected ProcessEngineConfigurationImpl processEngineConfiguration;

	public ProcessEngineImpl(ProcessEngineConfigurationImpl processEngineConfiguration) {

		this.processEngineConfiguration = processEngineConfiguration;
		this.name = processEngineConfiguration.getProcessEngineName();
		this.modelService = processEngineConfiguration.getModelService();
		this.runtimeService = processEngineConfiguration.getRuntimeService();
		this.historyService = processEngineConfiguration.getHistoryService();
		this.identityService = processEngineConfiguration.getIdentityService();
		this.taskService = processEngineConfiguration.getTaskService();
		this.formService = processEngineConfiguration.getFormService();
		this.scheduleService = processEngineConfiguration.getScheduleService();
		this.managementService=processEngineConfiguration.getManagementService();
		this.cacheHandler = processEngineConfiguration.getCacheHandler();
		this.commandExecutor = processEngineConfiguration.getCommandExecutor();

		this.processEngineConfiguration = processEngineConfiguration;
		this.name = processEngineConfiguration.getProcessEngineName();

		ProcessEngineManagement.registerProcessEngine(this);

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
	
	public ManagementService getManagementService() {
		
		return managementService;
	}

	public TaskService getTaskService() {

		return taskService;

	}

	public ProcessEngineConfigurationImpl getProcessEngineConfiguration() {
		return processEngineConfiguration;
	}

	public void setExternalContent(ExternalContent externalContent) {

		if( externalContent.getConnectionMap()!=null){
			for (String connKey : externalContent.getConnectionMap().keySet()) {
				Connection connection = externalContent.getConnectionMap().get(connKey);
				if (connection != null) {
					ConnectionManagement.INSTANCE().setFixConnection(connKey, connection);
					// Context.setDbConnection(connection);
				}
			}
		}
		

		String authenticatedUserId = externalContent.getAuthenticatedUserId();
		Authentication.setAuthenticatedUserId(authenticatedUserId);

		String languageType = externalContent.getLanguageType();
		if (languageType == null || languageType.equals("")) {

		} else {
			processEngineConfiguration.getFixFlowResources().setNowLanguage(languageType);
		}

		
		if(externalContent.getConnectionManagement()!=null&&!externalContent.getConnectionManagement().equals("")){
			Context.setConnectionManagementDefault(externalContent.getConnectionManagement());
		}
	}

	public void setLanguageType(String languageType) {

		if (languageType == null || languageType.equals("")) {
			// Context.setLanguageType("defauld");
		} else {
			processEngineConfiguration.getFixFlowResources().setNowLanguage(languageType);
			// Context.setLanguageType(languageType);
		}
	}

	public void contextClose() {

		Context.removeCommandContext();
		Context.removeProcessEngineConfiguration();
		Context.removeDbConnection();
		Context.removeAbstractScriptLanguageMgmt();
		Context.removeLanguageType();
		// Context.closeQuartzConnection();
		// Context.removeQuartzCloseConnection();
		// Context.removeQuartzCommitConnection();
		// Context.removeQuartzRollbackConnection();
	}

	public FixFlowVersion getVersion() {
		return processEngineConfiguration.getFixFlowVersion();
	}

	public void closeEngine() {
		// TODO Auto-generated method stub
		try {
			processEngineConfiguration.getSchedulerFactory().getScheduler().shutdown();

			contextClose();

			Map<String, FixThreadPoolExecutor> threadPoolMap = processEngineConfiguration.getThreadPoolMap();
			for (String mapKey : threadPoolMap.keySet()) {
				threadPoolMap.get(mapKey).shutdown();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProcessEngineManagement.unregister(this);
	}

	public void rollBackConnection() {
		// TODO Auto-generated method stub
		Map<String, FixConnectionResult> connectionMap = Context.getDbConnectionMap();
		if (connectionMap != null) {
			for (String mapKey : connectionMap.keySet()) {
				FixConnectionResult connectionobj = connectionMap.get(mapKey);
				try {
					if (connectionobj != null) {
						connectionobj.rollBackConnection();
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new FixFlowDbException(e.toString(), e);
				}
			}
		}
	}

	public void rollBackConnection(String dataBaseId) {
		// TODO Auto-generated method stub
		Map<String, FixConnectionResult> connectionMap = Context.getDbConnectionMap();
		if (connectionMap != null) {
			for (String mapKey : connectionMap.keySet()) {
				if (mapKey.equals(dataBaseId)) {
					FixConnectionResult connectionobj = connectionMap.get(mapKey);
					try {
						if (connectionobj != null) {
							connectionobj.rollBackConnection();

						}
					} catch (Exception e) {
						e.printStackTrace();
						throw new FixFlowDbException(e.toString(), e);
					}
					break;
				}
			}
		}
	}

	public void commitConnection() {
		// TODO Auto-generated method stub
		Map<String, FixConnectionResult> connectionMap = Context.getDbConnectionMap();
		if (connectionMap != null) {
			for (String mapKey : connectionMap.keySet()) {
				FixConnectionResult connectionobj = connectionMap.get(mapKey);
				try {
					if (connectionobj != null) {
						connectionobj.commitConnection();
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new FixFlowDbException(e.toString(), e);
				}
			}
		}
	}

	public void commitConnection(String dataBaseId) {
		// TODO Auto-generated method stub
		Map<String, FixConnectionResult> connectionMap = Context.getDbConnectionMap();
		if (connectionMap != null) {
			for (String mapKey : connectionMap.keySet()) {
				if (mapKey.equals(dataBaseId)) {
					FixConnectionResult connectionobj = connectionMap.get(mapKey);
					try {
						if (connectionobj != null) {
							connectionobj.commitConnection();

						}
					} catch (Exception e) {
						e.printStackTrace();
						throw new FixFlowDbException(e.toString(), e);
					}
					break;
				}
			}
		}
	}

	public void colseConnection() {
		// TODO Auto-generated method stub
		Map<String, FixConnectionResult> connectionMap = Context.getDbConnectionMap();
		if (connectionMap != null) {
			for (String mapKey : connectionMap.keySet()) {
				FixConnectionResult connectionobj = connectionMap.get(mapKey);
				try {
					if (connectionobj != null) {
						connectionobj.colseConnection();
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new FixFlowDbException(e.toString(), e);
				}
			}
		}
	}

	public void colseConnection(String dataBaseId) {
		// TODO Auto-generated method stub
		Map<String, FixConnectionResult> connectionMap = Context.getDbConnectionMap();
		if (connectionMap != null) {
			for (String mapKey : connectionMap.keySet()) {
				if (mapKey.equals(dataBaseId)) {
					FixConnectionResult connectionobj = connectionMap.get(mapKey);
					try {
						if (connectionobj != null) {
							connectionobj.colseConnection();

						}
					} catch (Exception e) {
						e.printStackTrace();
						throw new FixFlowDbException(e.toString(), e);
					}
					break;
				}
			}
		}
	}

	public void contextClose(boolean threadLocalContext, boolean connection) {
		if (connection) {
			Map<String, FixConnectionResult> connectionMap = Context.getDbConnectionMap();
			if (connectionMap != null) {
				for (String mapKey : connectionMap.keySet()) {
					FixConnectionResult connectionobj = connectionMap.get(mapKey);
					try {
						if (connectionobj != null) {
							connectionobj.colseConnection();
						}
					} catch (Exception e) {
						e.printStackTrace();
						throw new FixFlowDbException(e.toString(), e);
					}
				}
			}
		}
		if (threadLocalContext) {
			Context.removeCommandContext();
			Context.removeProcessEngineConfiguration();
			Context.removeDbConnection();
			Context.removeAbstractScriptLanguageMgmt();
			Context.removeLanguageType();
			Context.removeQuartzTransactionAutoThreadLocal();

			
		}
		
	}

}
