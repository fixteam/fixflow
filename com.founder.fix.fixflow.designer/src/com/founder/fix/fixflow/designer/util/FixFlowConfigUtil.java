package com.founder.fix.fixflow.designer.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.dialogs.MessageDialog;

import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigFactory;
import com.founder.fix.bpmn2extensions.coreconfig.DataBase;
import com.founder.fix.bpmn2extensions.coreconfig.DataBaseConfig;
import com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig;
import com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef;

public class FixFlowConfigUtil {
	public static FixFlowConfig fixFlowConfig;

	/**
	 * 得到FixFlow配置文件所存放的路径
	 * 
	 * @return
	 */
	public static String getFixFlowConfigPath() {
		return ResourcesPlugin.getWorkspace().getRoot()
				.getProject("fixflow-expand").getLocation().toString()
				+ "/src/com/founder/fix/fixflow/expand/config/";
	}

	/**
	 * 得到FixFlowXML所存放的路径
	 * 
	 * @return
	 */
	public static String getFixFlowConfigXMLPath() {
		return getFixFlowConfigPath() + "fixflowconfig.xml";
	}

	/**
	 * 加载所有的fixflowconfig配置信息
	 * 
	 * @return
	 */
	public static FixFlowConfig getFixFlowConfig() {
		if (fixFlowConfig == null) {
			

			fixFlowConfig = EMFUtil.getFixFlowConfig(getFixFlowConfigXMLPath());
		}
		return fixFlowConfig;
		/*
		 * 
		 * // 读取Menu的xml if (fixFlowConfig == null) { XMIResource resource =
		 * (XMIResource) new
		 * ResourceSetImpl().getResource(URI.createFileURI(getFixFlowConfigXMLPath
		 * ()), true); fixFlowConfig = (FixFlowConfig)
		 * resource.getContents().get(0); } return fixFlowConfig;
		 */
	}

	/**
	 * 设置流程引擎所使用的数据库
	 * 
	 * @param id
	 *            编号("FixBPMCS")
	 * @param name
	 *            名称
	 * @param driverClassName
	 *            驱动名称
	 * @param url
	 *            地址
	 * @param userName
	 *            用户名
	 * @param passWord
	 *            密码
	 * @param maxActive
	 *            最大活动连接数
	 * @param maxIdle
	 *            最大闲置连接数
	 * @param maxWait
	 *            最大等待时间
	 * @param dbType
	 *            数据库类型(oracle、sqlserver、mysql、db2)
	 */
	public static void modifyFixFlowDataBaseConfig(String id, String name,
			String driverClassName, String url, String userName,
			String passWord, String maxActive, String maxIdle, String maxWait,
			String dbType) {

		FixFlowConfig fixFlowConfig = getFixFlowConfig();
		fixFlowConfig.getDataBaseConfig().getDataBase();
		for (DataBase dataBase : fixFlowConfig.getDataBaseConfig()
				.getDataBase()) {
			if (dataBase.getId().trim().equals(dbType.trim())) {

				// dataBase.setId(dataBase.getId().trim());
				dataBase.setName(name);
				dataBase.setDriverClassName(driverClassName);
				dataBase.setUrl(url);
				dataBase.setUsername(userName);
				dataBase.setPassword(passWord);
				dataBase.setMaxActive(maxActive);
				dataBase.setMaxIdle(maxIdle);
				dataBase.setMaxWait(maxWait);

				if (dbType.trim().equals("oracle")) {
					dataBase.setPaginationImpl("com.founder.fix.fixflow.expand.database.pagination.OraclePaginationImpl");
				} else {
					dataBase.setPaginationImpl("com.founder.fix.fixflow.expand.database.pagination.SqlServerPaginationImpl");
				}
				fixFlowConfig.getDataBaseConfig().setSelected(dataBase.getId());

				// TODO Auto-generated method stub
				Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
				Map<String, Object> m = reg.getExtensionToFactoryMap();
				m.put("xml", new XMIResourceFactoryImpl());

				// Obtain a new resource set
				ResourceSet resSet = new ResourceSetImpl();

				// Create a resource
				XMIResource resource = (XMIResource) resSet.createResource(URI
						.createFileURI(FixFlowConfigUtil
								.getFixFlowConfigXMLPath()));
				resource.setEncoding("UTF-8");

				resource.getContents().add(fixFlowConfig);

				try {
					resource.save(Collections.EMPTY_MAP);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 刷新工程
				ConnectorUtil.refreshProject("fixflow-expand");

				// 清空缓存
				FixFlowConfigUtil.fixFlowConfig = null;

				return;
			}
		}

		DataBase dataBase = CoreconfigFactory.eINSTANCE.createDataBase();
		dataBase.setId(dbType.trim());
		dataBase.setName(name);
		dataBase.setDriverClassName(driverClassName);
		dataBase.setUrl(url);
		dataBase.setUsername(userName);
		dataBase.setPassword(passWord);
		dataBase.setMaxActive(maxActive);
		dataBase.setMaxIdle(maxIdle);
		dataBase.setMaxWait(maxWait);

		if (dbType.trim().equals("oracle")) {
			dataBase.setPaginationImpl("com.founder.fix.fixflow.expand.database.pagination.OraclePaginationImpl");
		} else {
			dataBase.setPaginationImpl("com.founder.fix.fixflow.expand.database.pagination.SqlServerPaginationImpl");
		}
		fixFlowConfig.getDataBaseConfig().getDataBase().add(dataBase);
		fixFlowConfig.getDataBaseConfig().setSelected(dbType.trim());

		// TODO Auto-generated method stub
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("xml", new XMIResourceFactoryImpl());

		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();

		// Create a resource
		XMIResource resource = (XMIResource) resSet.createResource(URI
				.createFileURI(FixFlowConfigUtil.getFixFlowConfigXMLPath()));
		resource.setEncoding("UTF-8");

		resource.getContents().add(fixFlowConfig);

		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 刷新工程
		ConnectorUtil.refreshProject("fixflow-expand");

		// 清空缓存
		FixFlowConfigUtil.fixFlowConfig = null;

	}

	/**
	 * 拿到所有启用为true的TaskCommand对象
	 * 
	 * @return
	 */
	public static List<TaskCommandDef> getTaskCommandNames(
			FixFlowConfig fixFlowConfig) {
		List<TaskCommandDef> taskCommandNames = new ArrayList<TaskCommandDef>();
		for (TaskCommandDef taskCommand : fixFlowConfig.getTaskCommandConfig()
				.getTaskCommandDef()) {
			if (taskCommand.getIsEnabled().equals("true")&&!taskCommand.getType().equals("system")) {
				taskCommandNames.add(taskCommand);
			}
		}
		return taskCommandNames;
	}

	/**
	 * 拿到配置里选中的数据库
	 * 
	 * @return
	 */
	public static DataBase getSelectedDataBase() {
		DataBaseConfig dataBaseConfig = FixFlowConfigUtil.getFixFlowConfig()
				.getDataBaseConfig();
		List<DataBase> dataBases = dataBaseConfig.getDataBase();

		DataBase dataBase = null;

		for (DataBase db : dataBases) {
			if (db.getId().equals(dataBaseConfig.getSelected())) {
				dataBase = db;
			}
		}
		return dataBase;
	}

	/**
	 * 创建数据库连接(不起事务)
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection createConnection() {
		Connection connection = null;

		DataBase dataBase = getSelectedDataBase();

		String driver = dataBase.getDriverClassName();
		String url = dataBase.getUrl();
		String user = dataBase.getUsername();
		String password = dataBase.getPassword();

		/*
		 * Class.forName("oracle.jdbc.driver.OracleDriver"); String url =
		 * "jdbc:oracle:thin:@172.29.128.91:1521:orcl"; String user = "idbase";
		 * String password = "idbase"; connection =
		 * DriverManager.getConnection(url, user, password);
		 */

		try {
			Class.forName(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//MessageDialog.openInformation(null, "错误",
					//"数据库连接创建失败,原因是 " + e.toString());
		}// com.mysql.jdbc.Driver

		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			MessageDialog.openInformation(null, "错误",
					"数据库连接创建失败,原因是 " + e.toString());
		}

		return connection;
	}
	
	/**
	 * 创建数据库连接(起事务)
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection createConnectionWithCommit() {
		Connection connection = null;

		DataBase dataBase = getSelectedDataBase();

		String driver = dataBase.getDriverClassName();
		String url = dataBase.getUrl();
		String user = dataBase.getUsername();
		String password = dataBase.getPassword();

		/*
		 * Class.forName("oracle.jdbc.driver.OracleDriver"); String url =
		 * "jdbc:oracle:thin:@172.29.128.91:1521:orcl"; String user = "idbase";
		 * String password = "idbase"; connection =
		 * DriverManager.getConnection(url, user, password);
		 */

		try {
			Class.forName(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//MessageDialog.openInformation(null, "错误",
					//"数据库连接创建失败,原因是 " + e.toString());
		}// com.mysql.jdbc.Driver

		try {
			connection = DriverManager.getConnection(url, user, password);
			connection.setAutoCommit(false);
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			MessageDialog.openInformation(null, "错误",
					"数据库连接创建失败,原因是 " + e.toString());
		}

		return connection;
	}
}
