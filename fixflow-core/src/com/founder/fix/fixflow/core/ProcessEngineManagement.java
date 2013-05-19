package com.founder.fix.fixflow.core;

import java.util.HashMap;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.db.DbType;

public abstract class ProcessEngineManagement {

	public static final String NAME_DEFAULT = "default";
	
	public static final String NAME_DESIGNER = "designer";

	protected static boolean isInitialized = false;
	
	public static DbType dbType;

	protected static Map<String, ProcessEngine> processEngines = new HashMap<String, ProcessEngine>();

	/**
	 * 注册一个流程引擎 获取一系列包含工作流方法Services。
	 * ProcessEngine和服务对象都是线程安全的，因此你可以在整个服务器中保留对它们任何一个的引用。
	 * 
	 * @param processEngine
	 */
	public static void registerProcessEngine(ProcessEngine processEngine) {
		processEngines.put(processEngine.getName(), processEngine);
	}

	/**
	 * 注销的流程引擎。
	 * 
	 * @param processEngine
	 *            流程引擎实例
	 */
	public static void unregister(ProcessEngine processEngine) {
		processEngines.remove(processEngine.getName());
	}

	/**
	 * 获取默认的流程引擎
	 * 
	 * @return 流程引擎实例
	 */
	public static ProcessEngine getDefaultProcessEngine() {
		return getProcessEngine(NAME_DEFAULT);
	}

	/**
	 * 初始化流程引擎管理器
	 */
	public synchronized static void init(String processEngineName) {
		if (!isInitialized) {
			if (processEngines == null) {

				processEngines = new HashMap<String, ProcessEngine>();
			}

			ProcessEngineConfiguration.createProcessEngineConfiguration(processEngineName)
			.setProcessEngineName(NAME_DEFAULT).buildProcessEngine();
			
			isInitialized = true;
		} else {
			// 记录日志
		}
	}

	/**
	 * 获取流程引擎
	 * 
	 * @param processEngineName
	 *            流程引擎名称
	 * @return 流程引擎实例
	 */
	public static ProcessEngine getProcessEngine(String processEngineName) {
		if (!isInitialized) {
			init(processEngineName);
		}

		return processEngines.get(processEngineName);
	}

	/**
	 * 关闭所有的流程引擎。
	 */
	public synchronized static void destroy() {
		if (isInitialized) {
			Map<String, ProcessEngine> engines = new HashMap<String, ProcessEngine>(processEngines);
			processEngines = new HashMap<String, ProcessEngine>();

			for (String processEngineName : engines.keySet()) {
				ProcessEngine processEngine = engines.get(processEngineName);
				try {
					processEngine.close();
				} catch (Exception e) {
					// 抛出异常
				}
			}

			isInitialized = false;
		}
	}

}
