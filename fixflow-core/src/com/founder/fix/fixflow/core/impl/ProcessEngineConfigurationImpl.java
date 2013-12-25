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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;

import org.dom4j.Document;
import org.dom4j.Element;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;

import com.founder.fix.bpmn2extensions.coreconfig.AllUserInfo;
import com.founder.fix.bpmn2extensions.coreconfig.AssignPolicyConfig;
import com.founder.fix.bpmn2extensions.coreconfig.ConnectionManagementInstanceConfig;
import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;
import com.founder.fix.bpmn2extensions.coreconfig.DBType;
import com.founder.fix.bpmn2extensions.coreconfig.DataBase;
import com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig;
import com.founder.fix.bpmn2extensions.coreconfig.ExpandClass;
import com.founder.fix.bpmn2extensions.coreconfig.ExpandClassConfig;
import com.founder.fix.bpmn2extensions.coreconfig.ExpandCmdConfig;
import com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig;
import com.founder.fix.bpmn2extensions.coreconfig.GroupInfo;
import com.founder.fix.bpmn2extensions.coreconfig.ImportDataVariableConfig;
import com.founder.fix.bpmn2extensions.coreconfig.InternationalizationConfig;
import com.founder.fix.bpmn2extensions.coreconfig.PigeonholeConfig;
import com.founder.fix.bpmn2extensions.coreconfig.Priority;
import com.founder.fix.bpmn2extensions.coreconfig.PriorityConfig;
import com.founder.fix.bpmn2extensions.coreconfig.QuartzConfig;
import com.founder.fix.bpmn2extensions.coreconfig.ResourcePath;
import com.founder.fix.bpmn2extensions.coreconfig.ResourcePathConfig;
import com.founder.fix.bpmn2extensions.coreconfig.RulesResource;
import com.founder.fix.bpmn2extensions.coreconfig.RulesResourceConfig;
import com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguageConfig;
import com.founder.fix.bpmn2extensions.coreconfig.SysMailConfig;
import com.founder.fix.bpmn2extensions.coreconfig.TaskCommandConfig;
import com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef;
import com.founder.fix.bpmn2extensions.sqlmappingconfig.BusinessRules;
import com.founder.fix.bpmn2extensions.sqlmappingconfig.Column;
import com.founder.fix.bpmn2extensions.sqlmappingconfig.DataBaseTable;
import com.founder.fix.bpmn2extensions.sqlmappingconfig.Delete;
import com.founder.fix.bpmn2extensions.sqlmappingconfig.Insert;
import com.founder.fix.bpmn2extensions.sqlmappingconfig.Result;
import com.founder.fix.bpmn2extensions.sqlmappingconfig.ResultMap;
import com.founder.fix.bpmn2extensions.sqlmappingconfig.Rule;
import com.founder.fix.bpmn2extensions.sqlmappingconfig.Select;
import com.founder.fix.bpmn2extensions.sqlmappingconfig.SqlmappingconfigFactory;
import com.founder.fix.bpmn2extensions.sqlmappingconfig.Update;
import com.founder.fix.bpmn2extensions.variableconfig.DataVariableConfig;
import com.founder.fix.bpmn2extensions.variableconfig.VariableconfigPackage;
import com.founder.fix.fixflow.core.ConnectionManagement;
import com.founder.fix.fixflow.core.FormService;
import com.founder.fix.fixflow.core.HistoryService;
import com.founder.fix.fixflow.core.IdentityService;
import com.founder.fix.fixflow.core.ManagementService;
import com.founder.fix.fixflow.core.ModelService;
import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineConfiguration;
import com.founder.fix.fixflow.core.RuntimeService;
import com.founder.fix.fixflow.core.ScheduleService;
import com.founder.fix.fixflow.core.TaskService;
import com.founder.fix.fixflow.core.cache.CacheHandler;
import com.founder.fix.fixflow.core.database.DataBaseTableEnum;
import com.founder.fix.fixflow.core.db.pagination.Pagination;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.cache.CacheImpl;
import com.founder.fix.fixflow.core.impl.db.DbConfig;
import com.founder.fix.fixflow.core.impl.db.DbType;
import com.founder.fix.fixflow.core.impl.filter.AbstractCommandFilter;
import com.founder.fix.fixflow.core.impl.identity.AbstractAuthentication;
import com.founder.fix.fixflow.core.impl.identity.GroupDefinition;
import com.founder.fix.fixflow.core.impl.identity.UserDefinition;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContextFactory;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContextInterceptor;
import com.founder.fix.fixflow.core.impl.interceptor.CommandExecutor;
import com.founder.fix.fixflow.core.impl.interceptor.CommandExecutorImpl;
import com.founder.fix.fixflow.core.impl.jms.MessageReceiver;
import com.founder.fix.fixflow.core.impl.message.FlowMessageListener;
import com.founder.fix.fixflow.core.impl.persistence.deployer.BpmnDeployer;
import com.founder.fix.fixflow.core.impl.persistence.deployer.Deployer;
import com.founder.fix.fixflow.core.impl.persistence.deployer.DeploymentCache;
import com.founder.fix.fixflow.core.impl.processversion.FixFlowVersion;
import com.founder.fix.fixflow.core.impl.threadpool.FixThreadPoolExecutor;
import com.founder.fix.fixflow.core.impl.util.QuartzUtil;
import com.founder.fix.fixflow.core.impl.util.ReflectUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.impl.util.XmlUtil;
import com.founder.fix.fixflow.core.internationalization.FixFlowResources;
import com.founder.fix.fixflow.core.variable.BizData;

public class ProcessEngineConfigurationImpl extends ProcessEngineConfiguration {

	protected CommandExecutor commandExecutor;
	protected CommandContextFactory commandContextFactory;
	protected CacheHandler cacheHandler;
	protected DeploymentCache deploymentCache;
	protected List<GroupDefinition> groupDefinitions;
	protected UserDefinition userDefinition;

	protected DbConfig dbConfig;

	protected ModelService modelService = new ModelServiceImpl();
	protected RuntimeService runtimeService = new RuntimeServiceImpl();
	protected HistoryService historyService = new HistoryServiceImpl();
	protected IdentityService identityService = new IdentityServiceImpl();
	protected TaskService taskService = new TaskServiceImpl();
	protected FormService formService = new FormServiceImpl();
	protected ScheduleService scheduleService = new ScheduleServiceImpl();
	protected ManagementService managementService = new ManagementServiceImpl();
	protected FixFlowConfig fixFlowConfig;

	protected FixFlowVersion fixFlowVersion;

	public AbstractAuthentication authenticationInstance;

	protected DataBase selectedDatabase;
	protected SysMailConfig sysMailConfig;
	protected EventSubscriptionConfig eventSubscriptionConfig;

	protected ScriptLanguageConfig scriptLanguageConfig;

	protected InternationalizationConfig internationalizationConfig;

	protected FixFlowResources fixFlowResources;

	protected PigeonholeConfig pigeonholeConfig;

	protected ExpandCmdConfig expandCmdConfig;

	protected TaskCommandConfig taskCommandConfig;

	protected Map<String, TaskCommandDef> taskCommandDefMap;

	protected Map<String, AbstractCommandFilter> abstractCommandFilterMap;

	List<GroupInfo> groupInfos;

	protected BizData bizData;

	protected PriorityConfig priorityConfig;

	protected ExpandClassConfig expandClassConfig;

	protected SchedulerFactory schedulerFactory;

	protected AssignPolicyConfig assignPolicyConfig;

	protected ImportDataVariableConfig importDataVariableConfig;

	protected QuartzConfig quartzConfig;

	protected ConnectionManagementInstanceConfig connectionManagementInstanceConfigDefault;

	protected List<ConnectionManagementInstanceConfig> connectionManagementInstanceConfigs;

	protected DataVariableConfig dataVariableConfig;

	protected Map<String, DataBaseTable> dataBaseTables = new HashMap<String, DataBaseTable>();
	
	protected Map<String, ResultMap> resultMaps = new HashMap<String, ResultMap>();
	

	
	protected Map<String, Column> columnMap = new HashMap<String, Column>();

	protected Map<String, Rule> ruleMap = new HashMap<String, Rule>();
	
	protected Map<String, Class<?>> ruleClassMap=new HashMap<String, Class<?>>();

	


	/**
	 * 线程池
	 */
	protected Map<String, FixThreadPoolExecutor> threadPoolMap;

	public ProcessEngine buildProcessEngine() {

		init();
		return new ProcessEngineImpl(this);
	}

	protected void init() {
		initEmfFile();
		initRulesConfig();
		// initSqlMappingFile();
		initDataVariableConfig();
		initCommandContextFactory();
		initResourcePathConfig();
		initCommandExecutors();
		initConnectionManagementConfig();
		initServices();
		initConnection();
		// initDataBaseTable();
		initCache();
		initDeployers();
		initGroupDefinitions();
		initDbConfig();// dbType
		// 任务命令配置加载
		initTaskCommandConfig();

		initImportDataVariableConfig();

		initQuartz();
		initUserDefinition();
		initSysMailConfig();
		initExpandClassConfig();
		initEventSubscriptionConfig();
		initMessageSubscription();
		initScriptLanguageConfig();
		initInternationalizationConfig();
		intiFixFlowResources();
		initPigeonholeConfig();
		initExpandCmdConfig();
		initAbstractCommandFilter();
		initBizData();
		initPriorityConfig();
		initAssignPolicyConfig();
		initThreadPool();

	}

	private void initRulesConfig() {
		// TODO Auto-generated method stub

		// this.rulesConfigs=new ArrayList<RulesConfig>();
		ruleMap.clear();

		RulesResourceConfig rulesResourceConfig = getFixFlowConfig().getRulesResourceConfig();
		List<RulesResource> rulesResources = rulesResourceConfig.getRulesResource();

		for (RulesResource rulesResource : rulesResources) {

			String classPath = rulesResource.getSrc();

			String filePath = this.getClass().getClassLoader().getResource(classPath).toString();
			Document document = null;
			try {

				if (!filePath.startsWith("jar")) {
					filePath = java.net.URLDecoder.decode(ReflectUtil.getResource(classPath).getFile(), "utf-8");

					InputStream in = new FileInputStream(filePath);
					document = XmlUtil.read(in);
				} else {
					InputStream in = new FileInputStream(filePath);
					document = XmlUtil.read(in);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new FixFlowException("读取fixflow配置出错", e);
			}

			for (Object ele : document.getRootElement().elements("dataBaseTable")) {
				Element element = (Element) ele;

				DataBaseTable dataBaseTable = SqlmappingconfigFactory.eINSTANCE.createDataBaseTable();
				dataBaseTable.setTableId(element.attributeValue("tableId"));
				dataBaseTable.setTableName(element.attributeValue("tableName"));
				dataBaseTable.setTableValue(element.attributeValue("tableValue"));
				//dataBaseTable.setMappingType(element.attributeValue("mappingType"));

				for (Object eleNew : element.elements("column")) {
					Element columnElement = (Element) eleNew;
					Column column = SqlmappingconfigFactory.eINSTANCE.createColumn();
					column.setColumn(columnElement.attributeValue("column"));
					column.setName(columnElement.attributeValue("name"));
					column.setJdbcType(columnElement.attributeValue("jdbcType"));
					//column.setProperty(columnElement.attributeValue("property"));
					//column.setSimpleKey(columnElement.attributeValue("property"));

					dataBaseTable.getColumn().add(column);
					columnMap.put(dataBaseTable.getTableId()+"_"+column.getColumn(), column);
				}

				dataBaseTables.put(dataBaseTable.getTableId(), dataBaseTable);

			}
			

			for (Object ele : document.getRootElement().elements("resultMap")) {
				Element element = (Element) ele;

				ResultMap resultMap = SqlmappingconfigFactory.eINSTANCE.createResultMap();
				resultMap.setId(element.attributeValue("id"));
				resultMap.setName(element.attributeValue("name"));
				resultMap.setType(element.attributeValue("type"));
				
				for (Object eleNew : element.elements("result")) {
					Element resultMappingElement = (Element) eleNew;
					Result result = SqlmappingconfigFactory.eINSTANCE.createResult();
					result.setColumn(resultMappingElement.attributeValue("column"));
					result.setName(resultMappingElement.attributeValue("name"));
					result.setJdbcType(resultMappingElement.attributeValue("jdbcType"));
					result.setProperty(resultMappingElement.attributeValue("property"));
					//result.setSimpleKey(columnMappingElement.attributeValue("property"));

					resultMap.getResult().add(result);
					//columnMappingMap.put(dataBaseTable.getTableId()+"_"+columnMapping.getColumn(), columnMapping);
				}

				resultMaps.put(resultMap.getId(), resultMap);

			}

			for (Object ele : document.getRootElement().elements("insert")) {
				Element element = (Element) ele;

				Insert insertObj = SqlmappingconfigFactory.eINSTANCE.createInsert();
				insertObj.setId(element.attributeValue("id"));
				insertObj.setParameterType(element.attributeValue("parameterType"));
				insertObj.setRemark(element.attributeValue("remark"));
				insertObj.setSqlValue(element.getText());
				
				String classPathString=element.attributeValue("classPath");

				if(StringUtil.isNotEmpty(classPathString)){
					Class<?> classObj=ReflectUtil.loadClass(classPathString);
					if(classObj!=null){
						insertObj.setClassPath(classPathString);
						ruleClassMap.put(element.attributeValue("id"), classObj);
					}
				}

			
				ruleMap.put(insertObj.getId(), insertObj);

			}

			for (Object ele : document.getRootElement().elements("delete")) {
				Element element = (Element) ele;

				Delete deleteObj = SqlmappingconfigFactory.eINSTANCE.createDelete();
				deleteObj.setId(element.attributeValue("id"));
				deleteObj.setParameterType(element.attributeValue("parameterType"));
				deleteObj.setRemark(element.attributeValue("remark"));
				deleteObj.setSqlValue(element.getText());
				
				String classPathString=element.attributeValue("classPath");

				if(StringUtil.isNotEmpty(classPathString)){
					Class<?> classObj=ReflectUtil.loadClass(classPathString);
					if(classObj!=null){
						deleteObj.setClassPath(classPathString);
						ruleClassMap.put(element.attributeValue("id"), classObj);
					}
				}

				ruleMap.put(deleteObj.getId(), deleteObj);

			}

			for (Object ele : document.getRootElement().elements("update")) {
				Element element = (Element) ele;

				Update updateObj = SqlmappingconfigFactory.eINSTANCE.createUpdate();
				updateObj.setId(element.attributeValue("id"));
				updateObj.setParameterType(element.attributeValue("parameterType"));
				updateObj.setRemark(element.attributeValue("remark"));
				updateObj.setSqlValue(element.getText());
				
				String classPathString=element.attributeValue("classPath");

				if(StringUtil.isNotEmpty(classPathString)){
					Class<?> classObj=ReflectUtil.loadClass(classPathString);
					if(classObj!=null){
						updateObj.setClassPath(classPathString);
						ruleClassMap.put(element.attributeValue("id"), classObj);
					}
				}

				ruleMap.put(updateObj.getId(), updateObj);

			}

			for (Object ele : document.getRootElement().elements("select")) {
				Element element = (Element) ele;

				Select selectObj = SqlmappingconfigFactory.eINSTANCE.createSelect();
				selectObj.setId(element.attributeValue("id"));
				selectObj.setParameterType(element.attributeValue("parameterType"));
				selectObj.setRemark(element.attributeValue("remark"));
				selectObj.setSqlValue(element.getText());
				selectObj.setResultMap(element.attributeValue("resultMap"));
				
				String classPathString=element.attributeValue("classPath");

				if(StringUtil.isNotEmpty(classPathString)){
					Class<?> classObj=ReflectUtil.loadClass(classPathString);
					if(classObj!=null){
						selectObj.setClassPath(classPathString);
						ruleClassMap.put(element.attributeValue("id"), classObj);
					}
				}

				ruleMap.put(selectObj.getId(), selectObj);

			}

			for (Object ele : document.getRootElement().elements("businessRules")) {
				Element element = (Element) ele;

				BusinessRules businessRules = SqlmappingconfigFactory.eINSTANCE.createBusinessRules();
				businessRules.setId(element.attributeValue("id"));
				businessRules.setParameterType(element.attributeValue("parameterType"));
				businessRules.setRemark(element.attributeValue("remark"));
				businessRules.setSqlValue(element.getText());
				businessRules.setResultType(element.attributeValue("resultType"));
				
				String classPathString=element.attributeValue("classPath");

				if(StringUtil.isNotEmpty(classPathString)){
					Class<?> classObj=ReflectUtil.loadClass(classPathString);
					if(classObj!=null){
						businessRules.setClassPath(classPathString);
						ruleClassMap.put(element.attributeValue("id"), classObj);
					}
				}
				
				ruleMap.put(businessRules.getId(), businessRules);

			}


		}

	}

	protected ResourcePathConfig resourcePathConfig;

	private void initResourcePathConfig() {
		// TODO Auto-generated method stub
		this.resourcePathConfig = getFixFlowConfig().getResourcePathConfig();
	}

	private void initDataVariableConfig() {
		// TODO Auto-generated method stub
		ResourceSet resourceSet = new ResourceSetImpl();

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new XMIResourceFactoryImpl());

		InputStream inputStream = null;
		String classPath = "com/founder/fix/fixflow/expand/config/datavariableconfig.xml";
		inputStream = ReflectUtil.getResourceAsStream("datavariableconfig.xml");
		if (inputStream != null) {
			classPath = "datavariableconfig.xml";
		}

		String filePath = this.getClass().getClassLoader().getResource(classPath).toString();
		Resource resource = null;
		try {
			if (!filePath.startsWith("jar")) {
				filePath = java.net.URLDecoder.decode(ReflectUtil.getResource(classPath).getFile(), "utf-8");
				resource = resourceSet.createResource(URI.createFileURI(filePath));
			} else {
				resource = resourceSet.createResource(URI.createURI(filePath));
			}

		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
			throw new FixFlowException("流程配置文件加载失败！", e2);
		}

		// register package in local resource registry
		resourceSet.getPackageRegistry().put(VariableconfigPackage.eINSTANCE.getNsURI(), VariableconfigPackage.eINSTANCE);
		// load resource
		try {
			resource.load(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FixFlowException("流程配置文件加载失败", e);
		}

		dataVariableConfig = (DataVariableConfig) resource.getContents().get(0);
	}

	private void initImportDataVariableConfig() {
		this.importDataVariableConfig = this.fixFlowConfig.getImportDataVariableConfig();
	}

	protected ConnectionManagement connectionManagementDefault;
	protected Map<String, ConnectionManagement> connectionManagementMap;

	private void initConnectionManagementConfig() {
		// TODO 自动生成的方法存根

		connectionManagementMap = new HashMap<String, ConnectionManagement>();
		connectionManagementInstanceConfigs = this.fixFlowConfig.getConnectionManagementConfig().getConnectionManagementInstanceConfig();
		String selectId = this.fixFlowConfig.getConnectionManagementConfig().getSelected();
		for (ConnectionManagementInstanceConfig connectionManagementInstanceConfigTemp : connectionManagementInstanceConfigs) {
			if (connectionManagementInstanceConfigTemp.getId().equals(selectId)) {
				this.connectionManagementInstanceConfigDefault = connectionManagementInstanceConfigTemp;
				connectionManagementDefault = (ConnectionManagement) ReflectUtil.instantiate(this.connectionManagementInstanceConfigDefault
						.getClassImpl());
				if (this.connectionManagementDefault == null) {
					throw new FixFlowException("加载 ConnectionManagementInstanceConfig 失败");
				}
				connectionManagementMap.put(connectionManagementInstanceConfigTemp.getId(), connectionManagementDefault);

			} else {
				ConnectionManagement connectionManagementOther = (ConnectionManagement) ReflectUtil
						.instantiate(connectionManagementInstanceConfigTemp.getClassImpl());
				connectionManagementMap.put(connectionManagementInstanceConfigTemp.getId(), connectionManagementOther);
			}
		}

	}

	protected void initEmfFile() {

		ResourceSet resourceSet = new ResourceSetImpl();

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new XMIResourceFactoryImpl());

		InputStream inputStream = null;
		String classPath = "com/founder/fix/fixflow/expand/config/fixflowconfig.xml";
		inputStream = ReflectUtil.getResourceAsStream("fixflowconfig.xml");
		if (inputStream != null) {
			classPath = "fixflowconfig.xml";
		}

		String filePath = this.getClass().getClassLoader().getResource(classPath).toString();
		Resource resource = null;
		try {
			if (!filePath.startsWith("jar")) {
				filePath = java.net.URLDecoder.decode(ReflectUtil.getResource(classPath).getFile(), "utf-8");
				resource = resourceSet.createResource(URI.createFileURI(filePath));
			} else {
				resource = resourceSet.createResource(URI.createURI(filePath));
			}

		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
			throw new FixFlowException("流程配置文件加载失败！", e2);
		}

		// register package in local resource registry
		resourceSet.getPackageRegistry().put(CoreconfigPackage.eINSTANCE.getNsURI(), CoreconfigPackage.eINSTANCE);
		// load resource
		try {
			resource.load(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FixFlowException("流程配置文件加载失败", e);
		}

		fixFlowConfig = (FixFlowConfig) resource.getContents().get(0);

		String versionString = fixFlowConfig.getVersion();

		this.fixFlowVersion = new FixFlowVersion(versionString);
	}

	protected void initThreadPool() {

		// 这里以后要从配置文件读取现在是写死的
		threadPoolMap = new HashMap<String, FixThreadPoolExecutor>();
		BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
		FixThreadPoolExecutor executor = new FixThreadPoolExecutor("default", "默认线程池", 1, 1, 1, TimeUnit.DAYS, queue);
		threadPoolMap.put(executor.getThreadPoolKey(), executor);

	}

	// 加载任务分配策略配置
	protected void initAssignPolicyConfig() {

		assignPolicyConfig = fixFlowConfig.getAssignPolicyConfig();

	}

	protected void initPriorityConfig() {
		// TODO 自动生成的方法存根
		priorityConfig = fixFlowConfig.getPriorityConfig();
	}

	protected void initBizData() {

		ExpandClassConfig expandClassConfig = getExpandClassConfig();
		List<ExpandClass> expandClasses = expandClassConfig.getExpandClass();
		for (ExpandClass expandClass : expandClasses) {
			if (expandClass.getClassId().equals("BizData")) {
				bizData = (BizData) ReflectUtil.instantiate(expandClass.getClassImpl());
				break;
			}
		}

	}

	protected void initAbstractCommandFilter() {

		abstractCommandFilterMap = new HashMap<String, AbstractCommandFilter>();
		List<TaskCommandDef> taskCommandDefs = fixFlowConfig.getTaskCommandConfig().getTaskCommandDef();
		for (TaskCommandDef taskCommandDef : taskCommandDefs) {
			if (StringUtil.getBoolean(taskCommandDef.getIsEnabled()) && taskCommandDef.getFilter() != null && !taskCommandDef.getFilter().equals("")) {
				AbstractCommandFilter abstractCommandFilter = (AbstractCommandFilter) ReflectUtil.instantiate(taskCommandDef.getFilter());
				abstractCommandFilterMap.put(taskCommandDef.getId(), abstractCommandFilter);
			}

		}

	}

	protected void initExpandCmdConfig() {

		this.expandCmdConfig = fixFlowConfig.getExpandCmdConfig();
	}

	protected void initPigeonholeConfig() {
		// TODO 自动生成的方法存根
		pigeonholeConfig = fixFlowConfig.getPigeonholeConfig();
	}

	protected void intiFixFlowResources() {

		if (!StringUtil.getBoolean(internationalizationConfig.getIsEnable())) {
			return;
		}

		List<ExpandClass> expandClasses = expandClassConfig.getExpandClass();
		for (ExpandClass expandClass : expandClasses) {
			if (expandClass.getClassId().equals("FixFlowResources")) {

				fixFlowResources = (FixFlowResources) ReflectUtil.instantiate(expandClass.getClassImpl());

				break;
			}
		}
		if (fixFlowResources == null) {
			throw new FixFlowException("流程国际化处理文件加载失败!");
		}

		Connection connection = createConnection();

		try {

			fixFlowResources.systemInit(connection);

		} catch (Exception e) {

			throw new FixFlowException("流程国际化处理文件加载失败!", e);

		} finally {
			try {
				connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
				throw new FixFlowException("流程国际化处理文件加载失败!", e);
			}

		}

	}

	protected void initInternationalizationConfig() {
		// TODO 自动生成的方法存根

		internationalizationConfig = fixFlowConfig.getInternationalizationConfig();

	}

	protected void initScriptLanguageConfig() {

		this.scriptLanguageConfig = fixFlowConfig.getScriptLanguageConfig();
	}

	protected void initMessageSubscription() {

		if (StringUtil.getBoolean(this.eventSubscriptionConfig.getIsEnable())) {
			String serverAddress = this.eventSubscriptionConfig.getServerAddress();

			String ServerPort = this.eventSubscriptionConfig.getServerPort();

			String messageInfo = this.eventSubscriptionConfig.getMessageInfo();

			MessageReceiver reciver = null;
			try {
				reciver = new MessageReceiver("tcp://" + serverAddress + ":" + ServerPort + "");
				FlowMessageListener listener = new FlowMessageListener();
				reciver.addTopicListener(messageInfo, listener);
				System.out.print("Message监听启动成功!\n");
			} catch (JMSException e) {

				System.out.print("Message监听启动失败!\n");
				e.printStackTrace();

			}

		}

	}

	public Connection createConnection() {
		DataBase dataBase = this.selectedDatabase;
		Connection connection = null;
		String driver = dataBase.getDriverClassName();
		String url = dataBase.getUrl();
		String user = dataBase.getUsername();
		String password = dataBase.getPassword();

		try {
			Class.forName(driver);
		} catch (Exception e) {
			throw new FixFlowException("数据库链接创建失败!", e);
		}// com.mysql.jdbc.Driver

		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {

		}

		return connection;

	}

	protected void initEventSubscriptionConfig() {
		// TODO Auto-generated method stub
		this.eventSubscriptionConfig = fixFlowConfig.getEventSubscriptionConfig();
	}

	protected void initExpandClassConfig() {
		this.expandClassConfig = fixFlowConfig.getExpandClassConfig();
		for (ExpandClass expandClass : expandClassConfig.getExpandClass()) {
			if (expandClass.getClassId().equals("Authentication")) {
				this.authenticationInstance = (AbstractAuthentication) ReflectUtil.instantiate(expandClass.getClassImpl());
			}
		}

	}

	protected void initSysMailConfig() {
		this.sysMailConfig = fixFlowConfig.getSysMailConfig();
	}

	protected void initUserDefinition() {

		UserDefinition userDefinitionObj = (UserDefinition) ReflectUtil.instantiate("com.founder.fix.fixflow.expand.identity.UserDefinitionImpl");
		AllUserInfo allUserInfo = fixFlowConfig.getDesignerOrgConfig().getAllUserInfo();
		userDefinitionObj.setUserInfoConfig(allUserInfo);
		this.userDefinition = userDefinitionObj;

	}

	protected void initConnection() {
		String selectedDB = fixFlowConfig.getDataBaseConfig().getSelected();
		for (DataBase dataBase : fixFlowConfig.getDataBaseConfig().getDataBase()) {
			if (dataBase.getId().equals(selectedDB)) {
				selectedDatabase = dataBase;
				ConnectionManagement.defaultDataBaseId = selectedDatabase.getId();
				break;
			}
		}

	}

	protected Scheduler scheduler;

	public Scheduler getScheduler() {
		return scheduler;
	}

	protected void initQuartz() {
		/*
		 * SchedulerFactory schedulerFactory =
		 * QuartzUtil.createSchedulerFactory(); Scheduler scheduler = null;
		 */

		this.quartzConfig = fixFlowConfig.getQuartzConfig();

		if (!StringUtil.getBoolean(quartzConfig.getIsEnable())) {
			return;
		}

		String driverClassName = "";
		String url = "";
		String username = "";
		String driverDelegateClass = "";
		String password = "";
		DataBase quartzDataBase = null;

		if (StringUtil.getBoolean(quartzConfig.getIsDefaultConfig())) {
			quartzDataBase = selectedDatabase;
		} else {

			String selectedDB = quartzConfig.getDataBaseId();
			for (DataBase dataBase : fixFlowConfig.getDataBaseConfig().getDataBase()) {
				if (dataBase.getId().equals(selectedDB)) {
					quartzDataBase = dataBase;
				}
			}

		}

		if (quartzDataBase == null) {
			throw new FixFlowException("定时任务框架启动失败！未找到指定的数据库");
		}

		driverClassName = quartzDataBase.getDriverClassName();
		url = quartzDataBase.getUrl();
		username = quartzDataBase.getUsername();
		password = quartzDataBase.getPassword();

		if (quartzDataBase.getDbtype().equals(DBType.ORACLE)) {
			driverDelegateClass = "com.founder.fix.fixflow.expand.quartz.jdbcjobstore.oracle.OracleDelegate";
			// driverDelegateClass =
			// "org.quartz.impl.jdbcjobstore.oracle.OracleDelegate";//org.quartz.impl.jdbcjobstore.StdJDBCDelegate
		} else {

			if (quartzDataBase.getDbtype().equals(DBType.SQLSERVER)) {
				driverDelegateClass = "org.quartz.impl.jdbcjobstore.MSSQLDelegate";
			} else {
				driverDelegateClass = "org.quartz.impl.jdbcjobstore.StdJDBCDelegate";
			}

		}

		/*
		 * if(quartzDataBase.getId().toLowerCase().equals("sqlserver")) {
		 * driverDelegateClass = "org.quartz.impl.jdbcjobstore.MSSQLDelegate"; }
		 * else { driverDelegateClass =
		 * "org.quartz.impl.jdbcjobstore.oracle.OracleDelegate"
		 * ;//org.quartz.impl.jdbcjobstore.StdJDBCDelegate }
		 */

		Properties props = new Properties();
		props.put("org.quartz.scheduler.instanceName", "FixFlowQuartzScheduler");
		props.put("org.quartz.scheduler.instanceId", "AUTO");
		props.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
		props.put("org.quartz.threadPool.threadCount", "15");
		props.put("org.quartz.threadPool.threadPriority", "5");

		props.put("org.quartz.jobStore.misfireThreshold", "60000");

		// props.put("org.quartz.scheduler.jmx.export", "true");

		// JobStoreTX
		props.put("org.quartz.jobStore.class", "com.founder.fix.fixflow.expand.quartz.jdbcjobstore.JobStoreFix");
		// props.put("org.quartz.jobStore.class",
		// "org.quartz.impl.jdbcjobstore.JobStoreTX");
		props.put("org.quartz.jobStore.driverDelegateClass", driverDelegateClass);
		props.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
		props.put("org.quartz.jobStore.dataSource", "fixDS");
		props.put("org.quartz.jobStore.isClustered", "false");
		props.put("org.quartz.dataSource.fixDS.driver", driverClassName);
		props.put("org.quartz.dataSource.fixDS.URL", url);
		props.put("org.quartz.dataSource.fixDS.user", username);
		props.put("org.quartz.dataSource.fixDS.password", password);
		props.put("org.quartz.dataSource.fixDS.maxConnections", "5");

		Properties prop = new Properties();
		InputStream inputStream = null;

		try {
			inputStream = ReflectUtil.getResourceAsStream("quartz.properties");
			if (inputStream != null) {
				prop.load(inputStream);
				inputStream.close();
				Set<Object> objects = prop.keySet();
				for (Object object : objects) {
					props.put(StringUtil.getString(object), prop.getProperty(StringUtil.getString(object)));
				}

			}

		} catch (IOException e) {
			System.out.println("Scr根目录未找到quartz.properties文件");
		}

		if (inputStream == null) {
			try {
				inputStream = ReflectUtil.getResourceAsStream("com/founder/fix/fixflow/expand/config/quartz.properties");
				if (inputStream != null) {
					prop.load(inputStream);
					inputStream.close();
					Set<Object> objects = prop.keySet();
					for (Object object : objects) {
						props.put(StringUtil.getString(object), prop.getProperty(StringUtil.getString(object)));
					}

				}
			} catch (IOException e) {
				System.out.println("com/founder/fix/fixflow/expand/config/根目录未找到quartz.properties文件");
			}
		}

		props.putAll(prop);

		schedulerFactory = null;
		schedulerFactory = QuartzUtil.createSchedulerFactory(props);
		try {
			scheduler = schedulerFactory.getScheduler();
			scheduler.start();

			System.out.println("定时框架启动成功");
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FixFlowException("定时任务框架启动失败！", e);
		}
	}

	protected void initTaskCommandConfig() {

		this.taskCommandConfig = fixFlowConfig.getTaskCommandConfig();

		// TODO Auto-generated method stub
		taskCommandDefMap = new HashMap<String, TaskCommandDef>();

		for (TaskCommandDef taskCommandDef : taskCommandConfig.getTaskCommandDef()) {

			String id = taskCommandDef.getId();

			taskCommandDefMap.put(id, taskCommandDef);

		}

	}

	protected void initDbConfig() {
		// Element dataBaseConfigEle =
		// getDataBaseConfigEle(getFixFlowConfigDoc());

		DataBase dataBase = this.selectedDatabase;
		if (dataBase != null) {
			if (dataBase.getDbtype().toString().toLowerCase().equals(DbType.SQLSERVER.toString().toLowerCase())) {
				initSqlServerDbConfig();
				return;
			}
			if (dataBase.getDbtype().toString().toLowerCase().equals(DbType.ORACLE.toString().toLowerCase())) {
				initOracleDbConfig();
				return;
			}
			if (dataBase.getDbtype().toString().toLowerCase().equals(DbType.MYSQL.toString().toLowerCase())) {
				initMySqlDbConfig();
				return;
			}
			if (dataBase.getDbtype().toString().toLowerCase().equals(DbType.DB2.toString().toLowerCase())) {
				initDB2DbConfig();
				return;
			}
			initOracleDbConfig();
		} else {
			initOracleDbConfig();
			return;
		}

		/*
		 * if (ProcessEngineManagement.dbType != null) { if
		 * (ProcessEngineManagement.dbType == DbType.SQLSERVER) {
		 * initSqlServerDbConfig(); return; } if (ProcessEngineManagement.dbType
		 * == DbType.ORACLE) { initOracleDbConfig(); return; }
		 * initOracleDbConfig(); } else { initOracleDbConfig(); return; }
		 */
	}

	protected void initSqlServerDbConfig() {

		DataBase dataBase = this.selectedDatabase;

		// OraclePaginationImpl
		Pagination pagination = (Pagination) ReflectUtil.instantiate(dataBase.getPaginationImpl());
		DbConfig dbConfig = new DbConfig();
		dbConfig.setDbType(DbType.SQLSERVER);
		dbConfig.setPagination(pagination);
		dbConfig.setKeyword("?");

		Map<String, String> map = new HashMap<String, String>();
		map.put("topOrderBy", " top 100 percent ");

		dbConfig.setDbSqlMap(map);

		this.dbConfig = dbConfig;
	}

	protected void initMySqlDbConfig() {

		DataBase dataBase = this.selectedDatabase;
		// OraclePaginationImpl
		Pagination pagination = (Pagination) ReflectUtil.instantiate(dataBase.getPaginationImpl());
		DbConfig dbConfig = new DbConfig();
		dbConfig.setDbType(DbType.ORACLE);
		dbConfig.setPagination(pagination);
		dbConfig.setKeyword("?");

		Map<String, String> map = new HashMap<String, String>();
		map.put("topOrderBy", " ");

		dbConfig.setDbSqlMap(map);

		this.dbConfig = dbConfig;
	}

	protected void initDB2DbConfig() {
		DataBase dataBase = this.selectedDatabase;
		// OraclePaginationImpl
		Pagination pagination = (Pagination) ReflectUtil.instantiate(dataBase.getPaginationImpl());
		DbConfig dbConfig = new DbConfig();
		dbConfig.setDbType(DbType.ORACLE);
		dbConfig.setPagination(pagination);
		dbConfig.setKeyword("?");

		Map<String, String> map = new HashMap<String, String>();
		map.put("topOrderBy", " ");

		dbConfig.setDbSqlMap(map);

		this.dbConfig = dbConfig;
	}

	protected void initOracleDbConfig() {
		DataBase dataBase = this.selectedDatabase;
		// OraclePaginationImpl
		Pagination pagination = (Pagination) ReflectUtil.instantiate(dataBase.getPaginationImpl());
		DbConfig dbConfig = new DbConfig();
		dbConfig.setDbType(DbType.ORACLE);
		dbConfig.setPagination(pagination);
		dbConfig.setKeyword("?");

		Map<String, String> map = new HashMap<String, String>();
		map.put("topOrderBy", " ");

		dbConfig.setDbSqlMap(map);

		this.dbConfig = dbConfig;
	}

	protected void initGroupDefinitions() {
		groupDefinitions = new ArrayList<GroupDefinition>();

		groupInfos = fixFlowConfig.getDesignerOrgConfig().getGroupInfo();

		for (GroupInfo groupInfo : groupInfos) {

			GroupDefinition groupDefinition = (GroupDefinition) ReflectUtil.instantiate(groupInfo.getGroupDefinitionImpl());
			groupDefinition.setId(groupInfo.getGroupId());
			groupDefinition.setName(groupInfo.getGroupName());
			groupDefinition.setGroupInfo(groupInfo);
			groupDefinitions.add(groupDefinition);

		}

	}

	protected void initServices() {
		initService(modelService);
		initService(runtimeService);
		initService(historyService);
		initService(identityService);
		initService(taskService);
		initService(managementService);
		initService(formService);
		initService(scheduleService);
	}

	protected void initService(Object service) {
		if (service instanceof ServiceImpl) {
			((ServiceImpl) service).setCommandExecutor(commandExecutor);
		}
	}

	protected void initCache() {
		// 这里以后要改到从conf文件中读取！！！
		this.cacheHandler = new CacheImpl();

	}

	protected void initCommandContextFactory() {
		if (commandContextFactory == null) {
			commandContextFactory = new CommandContextFactory();
			commandContextFactory.setProcessEngineConfiguration(this);
		}
	}

	protected void initCommandExecutors() {

		CommandContextInterceptor commandContextInterceptor = new CommandContextInterceptor(commandContextFactory, this);
		commandContextInterceptor.setNext(new CommandExecutorImpl());
		this.commandExecutor = commandContextInterceptor;
	}

	protected void initDeployers() {

		if (deploymentCache == null) {
			List<Deployer> deployers = new ArrayList<Deployer>();

			deployers.add(new BpmnDeployer());
			deploymentCache = new DeploymentCache();
			deploymentCache.setDeployers(deployers);
		}
	}

	public DeploymentCache getDeploymentCache() {
		return deploymentCache;
	}

	public void setDeploymentCache(DeploymentCache deploymentCache) {
		this.deploymentCache = deploymentCache;
	}

	public CommandExecutor getCommandExecutor() {
		return this.commandExecutor;
	}

	public ProcessEngineConfigurationImpl setCommandExecutor(CommandExecutor commandExecutor) {
		this.commandExecutor = commandExecutor;
		return this;
	}

	public DbConfig getDbConfig() {
		return dbConfig;
	}

	public ModelService getModelService() {
		return modelService;
	}

	public ProcessEngineConfigurationImpl setModelService(ModelService modelService) {
		this.modelService = modelService;
		return this;
	}

	public RuntimeService getRuntimeService() {
		return runtimeService;
	}

	public ProcessEngineConfigurationImpl setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
		return this;
	}

	public HistoryService getHistoryService() {
		return historyService;
	}

	public ProcessEngineConfigurationImpl setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
		return this;
	}

	public IdentityService getIdentityService() {
		return identityService;
	}

	public ProcessEngineConfigurationImpl setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
		return this;
	}

	public ManagementService getManagementService() {
		return managementService;
	}

	public ProcessEngineConfigurationImpl setManagementService(ManagementService managementService) {
		this.managementService = managementService;
		return this;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public List<GroupDefinition> getGroupDefinitions() {
		return groupDefinitions;
	}

	public TaskCommandConfig getTaskCommandConfig() {
		return taskCommandConfig;
	}

	public ProcessEngineConfigurationImpl setTaskService(TaskService taskService) {
		this.taskService = taskService;
		return this;
	}

	public FormService getFormService() {
		return formService;
	}

	public ProcessEngineConfigurationImpl setFormService(FormService formService) {
		this.formService = formService;
		return this;
	}

	public ScheduleService getScheduleService() {
		return scheduleService;
	}

	public ProcessEngineConfigurationImpl setScheduleService(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
		return this;
	}

	public CacheHandler getCacheHandler() {
		return cacheHandler;
	}

	public ProcessEngineConfigurationImpl setCacheHandler(CacheHandler cacheHandler) {
		this.cacheHandler = cacheHandler;
		return this;
	}

	public InternationalizationConfig getInternationalizationConfig() {
		return internationalizationConfig;
	}

	public void setInternationalizationConfig(InternationalizationConfig internationalizationConfig) {
		this.internationalizationConfig = internationalizationConfig;
	}

	public Map<String, TaskCommandDef> getTaskCommandDefMap() {
		return taskCommandDefMap;
	}

	public Map<String, FixThreadPoolExecutor> getThreadPoolMap() {
		return threadPoolMap;
	}

	public Map<String, AbstractCommandFilter> getAbstractCommandFilterMap() {
		return abstractCommandFilterMap;
	}

	public DataBase getSelectedDatabase() {
		return selectedDatabase;
	}

	public UserDefinition getUserDefinition() {
		return userDefinition;
	}

	public void setUserDefinition(UserDefinition userDefinition) {
		this.userDefinition = userDefinition;
	}

	/**
	 * 获取系统配置邮件
	 * 
	 * @return
	 */
	public SysMailConfig getSysMailConfig() {
		return sysMailConfig;
	}

	/**
	 * 获取调度工厂对象
	 * 
	 * @return 调度工厂对象
	 */
	public SchedulerFactory getSchedulerFactory() {
		return schedulerFactory;
	}

	public ExpandClassConfig getExpandClassConfig() {
		return expandClassConfig;
	}

	public EventSubscriptionConfig getEventSubscriptionConfig() {
		return eventSubscriptionConfig;
	}

	public ScriptLanguageConfig getScriptLanguageConfig() {
		return scriptLanguageConfig;
	}

	public FixFlowResources getFixFlowResources() {
		return fixFlowResources;
	}

	public void setFixFlowResources(FixFlowResources fixFlowResources) {
		this.fixFlowResources = fixFlowResources;
	}

	public PigeonholeConfig getPigeonholeConfig() {
		return pigeonholeConfig;
	}

	public ExpandCmdConfig getExpandCmdConfig() {

		return expandCmdConfig;
	}

	public BizData getBizData() {
		return bizData;
	}

	public PriorityConfig getPriorityConfig() {

		return priorityConfig;
	}

	public Priority getPriority(int priorityValue) {

		for (Priority priority : priorityConfig.getPriority()) {
			if (priority.getValue().equals(StringUtil.getString(priorityValue))) {
				return priority;
			}
		}

		return priorityConfig.getPriority().get(1);

	}

	public AssignPolicyConfig getAssignPolicyConfig() {
		return assignPolicyConfig;
	}

	public ImportDataVariableConfig getImportDataVariableConfig() {
		return importDataVariableConfig;
	}

	public ConnectionManagementInstanceConfig getConnectionManagementInstanceConfigDefault() {
		return connectionManagementInstanceConfigDefault;
	}

	public ConnectionManagement getConnectionManagementDefault() {
		return connectionManagementDefault;
	}

	public ConnectionManagement getConnectionManagement(String cmId) {
		return connectionManagementMap.get(cmId);
	}

	public FixFlowConfig getFixFlowConfig() {
		return fixFlowConfig;
	}

	public FixFlowVersion getFixFlowVersion() {
		return fixFlowVersion;
	}

	public QuartzConfig getQuartzConfig() {
		return quartzConfig;
	}

	public AbstractAuthentication getAuthenticationInstance() {
		return authenticationInstance;
	}

	public DataVariableConfig getDataVariableConfig() {
		return dataVariableConfig;
	}

	public DataBaseTable getDataBaseTableConfig(DataBaseTableEnum dataBaseTableEnum) {

		return this.dataBaseTables.get(dataBaseTableEnum.toString().toLowerCase());

	}

	public ResourcePathConfig getResourcePathConfig() {
		return resourcePathConfig;
	}

	public ResourcePath getResourcePath(String resourceId) {

		List<ResourcePath> resourcePaths = this.resourcePathConfig.getResourcePath();
		for (ResourcePath resourcePath : resourcePaths) {
			if (resourcePath.getId().equals(resourceId)) {
				return resourcePath;
			}
		}
		return null;
	}

	public DataBaseTable getDataBaseTable(String tableId) {

		return this.dataBaseTables.get(tableId);

	}

	public Rule getRule(String ruleId) {

		return this.ruleMap.get(ruleId);

	}
	
	public Map<String, Rule> getRuleMap() {
		return ruleMap;
	}
	
	
	
	public Map<String, DataBaseTable> getDataBaseTables() {
		return dataBaseTables;
	}
	
	public Column getColumn(String tableId,String columnId) {
		
		
		return columnMap.get(tableId+"_"+columnId);
		
	}
	
	public Map<String,Column> getColumnMap(String tableId) {
		Map<String, Column> columnMapObj=new HashMap<String, Column>();
		DataBaseTable dataBaseTable=dataBaseTables.get(tableId);
		if(dataBaseTable!=null){
			for (Column column : dataBaseTable.getColumn()) {
				columnMapObj.put(column.getColumn(), column);
			}
		}
		
		return columnMapObj;
		
	}
	public Map<String, ResultMap> getResultMaps() {
		return resultMaps;
	}
	
	public ResultMap getResultMap(String id) {
		return resultMaps.get(id);
	}
	
	public Map<String, Class<?>> getRuleClassMap() {
		return ruleClassMap;
	}
	
	public Class<?> getRuleClass(String id) {
		return ruleClassMap.get(id);
	}



}
