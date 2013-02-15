package com.founder.fix.fixflow.core.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.jms.JMSException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
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
import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;
import com.founder.fix.bpmn2extensions.coreconfig.DBType;
import com.founder.fix.bpmn2extensions.coreconfig.DataBase;
import com.founder.fix.bpmn2extensions.coreconfig.EventSubscriptionConfig;
import com.founder.fix.bpmn2extensions.coreconfig.ExpandClass;
import com.founder.fix.bpmn2extensions.coreconfig.ExpandClassConfig;
import com.founder.fix.bpmn2extensions.coreconfig.ExpandCmdConfig;
import com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig;
import com.founder.fix.bpmn2extensions.coreconfig.GroupInfo;
import com.founder.fix.bpmn2extensions.coreconfig.InternationalizationConfig;
import com.founder.fix.bpmn2extensions.coreconfig.PigeonholeConfig;
import com.founder.fix.bpmn2extensions.coreconfig.Priority;
import com.founder.fix.bpmn2extensions.coreconfig.PriorityConfig;
import com.founder.fix.bpmn2extensions.coreconfig.QuartzConfig;
import com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguageConfig;
import com.founder.fix.bpmn2extensions.coreconfig.SysMailConfig;
import com.founder.fix.bpmn2extensions.coreconfig.TaskCommandConfig;
import com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef;
import com.founder.fix.fixflow.core.FormService;
import com.founder.fix.fixflow.core.HistoryService;
import com.founder.fix.fixflow.core.IdentityService;
import com.founder.fix.fixflow.core.ModelService;
import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineConfiguration;
import com.founder.fix.fixflow.core.RuntimeService;
import com.founder.fix.fixflow.core.ScheduleService;
import com.founder.fix.fixflow.core.TaskService;
import com.founder.fix.fixflow.core.cache.CacheHandler;
import com.founder.fix.fixflow.core.db.pagination.Pagination;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.cache.CacheImpl;
import com.founder.fix.fixflow.core.impl.db.DbConfig;
import com.founder.fix.fixflow.core.impl.db.DbType;
import com.founder.fix.fixflow.core.impl.filter.AbstractCommandFilter;
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
	protected FixFlowConfig fixFlowConfig;
	protected DataBase selectedDatabase;
	protected SysMailConfig sysMailConfig;
	protected EventSubscriptionConfig eventSubscriptionConfig;
	
	protected ScriptLanguageConfig scriptLanguageConfig;
	
	protected InternationalizationConfig internationalizationConfig;
	
	protected FixFlowResources fixFlowResources;
	
	protected PigeonholeConfig pigeonholeConfig;
	
	
	protected ExpandCmdConfig expandCmdConfig;

	protected TaskCommandConfig taskCommandConfig;

	protected Map<String,TaskCommandDef> taskCommandDefMap;

	
	protected Map<String,AbstractCommandFilter> abstractCommandFilterMap;
	

	List<GroupInfo> groupInfos;

	protected BizData bizData;

	
	
	protected PriorityConfig priorityConfig;
	


	


	protected ExpandClassConfig expandClassConfig;
	
	
	

	protected SchedulerFactory schedulerFactory;
	
	
	
	protected AssignPolicyConfig assignPolicyConfig;



	public ProcessEngine buildProcessEngine() {

		loadEmfFile();
		init();
		return new ProcessEngineImpl(this);
	}
	
	
	private void loadEmfFile(){
		
		ResourceSet resourceSet = new ResourceSetImpl();

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new XMIResourceFactoryImpl());
		
		String filePath=this.getClass().getClassLoader().getResource("com/founder/fix/fixflow/expand/config/fixflowconfig.xml").toString();
		Resource resource = null;
		try {
			if(!filePath.startsWith("jar")){
				filePath= java.net.URLDecoder.decode(ReflectUtil.getResource("com/founder/fix/fixflow/expand/config/fixflowconfig.xml").getFile(),"utf-8");
				resource = resourceSet.createResource(URI.createFileURI(filePath));
			}else{
				resource = resourceSet.createResource(URI.createURI(filePath));
			}
				
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
			throw new FixFlowException("流程配置文件加载失败！",e2);
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

	}

	protected void init() {

		initCommandContextFactory();
		initCommandExecutors();
		initServices();
		initCache();
		initDeployers();
		initGroupDefinitions();
		initDbConfig();// dbType
		// 任务命令配置加载
		initTaskCommandConfig();
		initConnection();
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
		
		
	}

	//加载任务分配策略配置
	private void initAssignPolicyConfig() {
		 		
		 assignPolicyConfig=fixFlowConfig.getAssignPolicyConfig();
		
	}


	private void initPriorityConfig() {
		// TODO 自动生成的方法存根
		priorityConfig=fixFlowConfig.getPriorityConfig();
	}


	private void initBizData() {

		ExpandClassConfig expandClassConfig=getExpandClassConfig();
		List<ExpandClass>  expandClasses=expandClassConfig.getExpandClass();
		for (ExpandClass expandClass : expandClasses) {
			if(expandClass.getClassId().equals("BizData")){
				bizData =(BizData) ReflectUtil.instantiate(expandClass.getClassImpl());
				break;
			}
		}

	}


	private void initAbstractCommandFilter() {
		
		abstractCommandFilterMap=new HashMap<String, AbstractCommandFilter>();
		List<TaskCommandDef> taskCommandDefs=fixFlowConfig.getTaskCommandConfig().getTaskCommandDef();
		for (TaskCommandDef taskCommandDef : taskCommandDefs) {
			if(StringUtil.getBoolean(taskCommandDef.getIsEnabled())&&taskCommandDef.getFilter()!=null&&!taskCommandDef.getFilter().equals("")){
				AbstractCommandFilter abstractCommandFilter =(AbstractCommandFilter) ReflectUtil.instantiate(taskCommandDef.getFilter());
				abstractCommandFilterMap.put(taskCommandDef.getId(), abstractCommandFilter);
			}
			
		}
		
	}


	private void initExpandCmdConfig() {

		this.expandCmdConfig=fixFlowConfig.getExpandCmdConfig();
	}


	private void initPigeonholeConfig() {
		// TODO 自动生成的方法存根
		pigeonholeConfig=fixFlowConfig.getPigeonholeConfig();
	}


	private void intiFixFlowResources() {
		
		if(!StringUtil.getBoolean(internationalizationConfig.getIsEnable())){
			return;
		}
	
			List<ExpandClass>  expandClasses=expandClassConfig.getExpandClass();
			for (ExpandClass expandClass : expandClasses) {
				if(expandClass.getClassId().equals("FixFlowResources")){
					
					fixFlowResources =(FixFlowResources) ReflectUtil.instantiate(expandClass.getClassImpl());
					
					break;
				}
			}
			if(fixFlowResources==null){
				throw new FixFlowException("流程国际化处理文件加载失败!");
			}
			
			
			Connection connection= createConnection();
			try {
				connection.setAutoCommit(false);
			} catch (SQLException e) {
				throw new FixFlowException("流程国际化处理文件加载失败!",e);
			}
			Context.setDbConnection(connection);
			
			try {
				fixFlowResources.systemInit();
				connection.commit();
			} catch (Exception e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					throw new FixFlowException("流程国际化处理文件加载失败!",e1);
				}
				throw new FixFlowException("流程国际化处理文件加载失败!",e);
			}
			finally{
				try {
					connection.close();
					Context.removeDbConnection();
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
					throw new FixFlowException("流程国际化处理文件加载失败!",e);
				}
				
			}
			

	}


	private void initInternationalizationConfig() {
		// TODO 自动生成的方法存根
		
		internationalizationConfig=fixFlowConfig.getInternationalizationConfig();
		
	}


	private void initScriptLanguageConfig() {

		this.scriptLanguageConfig=fixFlowConfig.getScriptLanguageConfig();
	}


	private void initMessageSubscription() {
		
		if(StringUtil.getBoolean(this.eventSubscriptionConfig.getIsEnable())){
			String serverAddress=this.eventSubscriptionConfig.getServerAddress();
			
			String ServerPort=this.eventSubscriptionConfig.getServerPort();
			
			String messageInfo=this.eventSubscriptionConfig.getMessageInfo();
			
			MessageReceiver reciver=null;
			try {
				reciver = new MessageReceiver("tcp://"+serverAddress+":"+ServerPort+"");
				FlowMessageListener listener = new FlowMessageListener();
				reciver.addTopicListener(messageInfo,listener);
				System.out.print("Message监听启动成功!\n");
			} catch (JMSException e) {

				System.out.print("Message监听启动失败!\n");
				e.printStackTrace();
				
			}

			
		}
		
		
		
	}
	
	public Connection createConnection(){
		DataBase dataBase = this.selectedDatabase;
		Connection connection=null;
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


	private void initEventSubscriptionConfig() {
		// TODO Auto-generated method stub
		this.eventSubscriptionConfig=fixFlowConfig.getEventSubscriptionConfig();
	}


	private void initExpandClassConfig() {
		this.expandClassConfig=fixFlowConfig.getExpandClassConfig();
	}


	private void initSysMailConfig() {
		this.sysMailConfig=fixFlowConfig.getSysMailConfig();
	}

	private void initUserDefinition() {
		
		UserDefinition userDefinitionObj = (UserDefinition) ReflectUtil.instantiate("com.founder.fix.fixflow.expand.identity.UserDefinitionImpl");
		AllUserInfo allUserInfo=fixFlowConfig.getDesignerOrgConfig().getAllUserInfo();
		userDefinitionObj.setUserInfoConfig(allUserInfo);
		this.userDefinition=userDefinitionObj;
		
	}

	private void initConnection() {
		String selectedDB = fixFlowConfig.getDataBaseConfig().getSelected();
		for (DataBase dataBase : fixFlowConfig.getDataBaseConfig().getDataBase()) {
			if (dataBase.getId().equals(selectedDB)) {
				selectedDatabase = dataBase;
			}
		}

	}

	private void initQuartz() {
	/*	SchedulerFactory schedulerFactory = QuartzUtil.createSchedulerFactory();
		Scheduler scheduler = null;*/
		
		QuartzConfig quartzConfig=fixFlowConfig.getQuartzConfig();
		
		if(!StringUtil.getBoolean(quartzConfig.getIsEnable())){
			return;
		}
		
		String driverClassName="";
		String url="";
		String username="";
		String driverDelegateClass = "";
		String password="";
		DataBase quartzDataBase=null;
		
		if(StringUtil.getBoolean(quartzConfig.getIsDefaultConfig())){
			quartzDataBase=selectedDatabase;
		}
		else{
			
			String selectedDB =quartzConfig.getDataBaseId();
			for (DataBase dataBase : fixFlowConfig.getDataBaseConfig().getDataBase()) {
				if (dataBase.getId().equals(selectedDB)) {
					quartzDataBase = dataBase;
				}
			}
			
		}
		
		if(quartzDataBase==null){
			throw new FixFlowException("定时任务框架启动失败！未找到指定的数据库");
		}
		
	
		driverClassName=quartzDataBase.getDriverClassName();
		url=quartzDataBase.getUrl();
		username=quartzDataBase.getUsername();
		password=quartzDataBase.getPassword();
		
		if(quartzDataBase.getDbtype().equals(DBType.ORACLE)){
			driverDelegateClass = "org.quartz.impl.jdbcjobstore.oracle.OracleDelegate";//org.quartz.impl.jdbcjobstore.StdJDBCDelegate
		}
		else{
			driverDelegateClass = "org.quartz.impl.jdbcjobstore.MSSQLDelegate";
		}

		/*
		if(quartzDataBase.getId().toLowerCase().equals("sqlserver")) {
			driverDelegateClass = "org.quartz.impl.jdbcjobstore.MSSQLDelegate";
		}
		else {
			driverDelegateClass = "org.quartz.impl.jdbcjobstore.oracle.OracleDelegate";//org.quartz.impl.jdbcjobstore.StdJDBCDelegate
		}
		*/
		
		
		
		Properties props = new Properties();
		props.put("org.quartz.scheduler.instanceName", "FixFlowQuartzScheduler");
		props.put("org.quartz.scheduler.instanceId", "AUTO");
		props.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
		props.put("org.quartz.threadPool.threadCount", "3");
		props.put("org.quartz.threadPool.threadPriority", "5");
		props.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
		props.put("org.quartz.jobStore.driverDelegateClass", driverDelegateClass);
		props.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
		props.put("org.quartz.jobStore.dataSource", "fixDS");
		props.put("org.quartz.jobStore.isClustered", "false");
		props.put("org.quartz.dataSource.fixDS.driver", driverClassName);
		props.put("org.quartz.dataSource.fixDS.URL", url);
		props.put("org.quartz.dataSource.fixDS.user", username);
		props.put("org.quartz.dataSource.fixDS.password", password);
		props.put("org.quartz.dataSource.fixDS.maxConnections", "5");
		
		schedulerFactory = null;
		Scheduler scheduler = null;
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

	

	private void initTaskCommandConfig() {
		
		
		this.taskCommandConfig=fixFlowConfig.getTaskCommandConfig();
		
		
		// TODO Auto-generated method stub
		taskCommandDefMap = new HashMap<String, TaskCommandDef>();

		
		for (TaskCommandDef taskCommandDef :taskCommandConfig.getTaskCommandDef()) {
			
			String id = taskCommandDef.getId();
			

			taskCommandDefMap.put(id, taskCommandDef);
	
			

		}
		

	}
	


	protected void initDbConfig() {
		Element dataBaseConfigEle = getDataBaseConfigEle(getFixFlowConfigDoc());
		if (dataBaseConfigEle.attributeValue("selected") != null) {
			if (dataBaseConfigEle.attributeValue("selected").toLowerCase().equals(DbType.SQLSERVER.toString().toLowerCase())) {
				initSqlServerDbConfig();
				return;
			}
			if (dataBaseConfigEle.attributeValue("selected").toLowerCase().equals(DbType.ORACLE.toString().toLowerCase())) {
				initOracleDbConfig();
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
		// OraclePaginationImpl
		Pagination pagination = (Pagination) ReflectUtil.instantiate(getDataBaseEle(getDataBaseConfigEle(getFixFlowConfigDoc())).attributeValue("paginationImpl"));
		DbConfig dbConfig = new DbConfig();
		dbConfig.setDbType(DbType.SQLSERVER);
		dbConfig.setPagination(pagination);
		dbConfig.setKeyword("?");

		Map<String, String> map = new HashMap<String, String>();
		map.put("topOrderBy", " top 100 percent ");

		dbConfig.setDbSqlMap(map);

		this.dbConfig = dbConfig;
	}

	protected void initOracleDbConfig() {
		// OraclePaginationImpl
		Pagination pagination = (Pagination) ReflectUtil.instantiate(getDataBaseEle(getDataBaseConfigEle(getFixFlowConfigDoc())).attributeValue("paginationImpl"));
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
		
		
		groupInfos=fixFlowConfig.getDesignerOrgConfig().getGroupInfo();
		
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


	public void setInternationalizationConfig(
			InternationalizationConfig internationalizationConfig) {
		this.internationalizationConfig = internationalizationConfig;
	}

	/**
	 * 拿到fixflowconfig.xml的doc
	 * 
	 * @return
	 */
	private Document getFixFlowConfigDoc() {
		Document document = null;
		try {
			document = XmlUtil.read(ReflectUtil.getResourceAsStream("com/founder/fix/fixflow/expand/config/fixflowconfig.xml"));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FixFlowException("读取fixflow配置出错", e);
		}
		return document;
	}

	public Map<String, TaskCommandDef> getTaskCommandDefMap() {
		return taskCommandDefMap;
	}

	/**
	 * 拿到数据库配置元素
	 * 
	 * @return
	 */
	private Element getDataBaseConfigEle(Document document) {
		Element dataBaseConfigEle = null;
		dataBaseConfigEle = document.getRootElement().element("dataBaseConfig");
		return dataBaseConfigEle;
	}

	/**
	 * 拿到组定义配置元素
	 * 
	 * @return
	 */
	/*
	 * private Element getGroupDefinitionEle(Document document) { Element
	 * groupDefinitionEle = null; Element groupDefinitionConfigEle =
	 * document.getRootElement().element("groupDefinitionConfig"); for(Object
	 * obj : groupDefinitionConfigEle.elements("groupDefinition")) { if() }
	 * 
	 * return groupDefinitionEle; }
	 */

	/**
	 * 取得对应ID的数据库元素
	 * 
	 * @return
	 */
	private Element getDataBaseEle(Element dataBaseConfigEle) {
		Element dataBaseEle = null;
		for (Object ele : dataBaseConfigEle.elements("dataBase")) {
			dataBaseEle = (Element) ele;
			if (dataBaseEle.attributeValue("id").equals(dataBaseConfigEle.attributeValue("selected"))) {
				return dataBaseEle;
			}
		}
		return dataBaseEle;
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
	 * @return
	 */
	public SysMailConfig getSysMailConfig() {
		return sysMailConfig;
	}
	
	/**
	 * 获取调度工厂对象
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
	
	
	public Priority getPriority(int priorityValue){
		
		
		for (Priority priority : priorityConfig.getPriority()) {
			if(priority.getValue().equals(StringUtil.getString(priorityValue))){
				return priority;
			}
		}
		
		
		
		
		return priorityConfig.getPriority().get(1);
		
	}

	public AssignPolicyConfig getAssignPolicyConfig() {
		return assignPolicyConfig;
	}

}
