package com.founder.fix.fixflow.designer.util;

import java.io.File;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

import com.founder.fix.bpmn2extensions.connector.Connector;
import com.founder.fix.fixflow.designer.Activator;
import com.founder.fix.fixflow.designer.preferences.PreferenceConstants;

public class ConnectorUtil {

	/**
	 * 得到连接器所存放的路径
	 * 
	 * @return
	 */
	/*
	 * public static String getConnectorPath(){ return
	 * Platform.getInstallLocation().getURL().getPath() +
	 * "temp/fixflow/connector/"; }
	 */

	/**
	 * 得到连接器所存放的路径
	 * 
	 * @return
	 */
	public static String getConnectorPath() {
		return ResourcesPlugin.getWorkspace().getRoot()
				.getProject("fixflow-expand").getLocation().toString()
				+ "/src/com/founder/fix/fixflow/expand"
				+ Activator.getDefault().getPreferenceStore()
						.getString(PreferenceConstants.CONNECTORPATH) + "/";
	}

	/**
	 * 得到连接器Menu文件路径
	 * 
	 * @return
	 */
	public static String getMenuConnectorPath() {
		return getConnectorPath() + "FixConnectorMenu.xml";
	}

	/**
	 * 得到连接器Menu图标所存放的路径
	 * 
	 * @return
	 */
	public static String getMenuConnectorIconPath() {
		return getConnectorPath() + "ico/";
	}

	/**
	 * 根据图标的名称得到连接器Menu图标所存放的路径
	 * 
	 * @param iconName
	 * @return
	 */
	public static String getMenuConnectorIconPathByIconName(String iconName) {
		return getMenuConnectorIconPath() + iconName;
	}

	/**
	 * 根据图标的名称得到连接器图标所存放的路径
	 * 
	 * @param connectorId
	 * @param iconName
	 * @return
	 */
	public static String getConnectorIconPathByIconName(String connectorId,
			String iconName) {
		return getConnectorPathById(connectorId) + "/" + iconName;
	}

	/**
	 * 根据连接器ID得到对应连接器文件存放路径
	 * 
	 * @param connectorId
	 * @return
	 */
	public static String getConnectorPathById(String connectorId) {
		return getConnectorPath() + connectorId;
	}

	/**
	 * 根据连接器ID得到对应连接器xml文件路径
	 * 
	 * @param connectorId
	 * @return
	 */
	public static String getConnectorXMLPath(String connectorId) {
		return getConnectorPath() + connectorId + "/FixConnector.xml";
	}

	/**
	 * 根据Menu上的连接器ID找到对应的连接器对象
	 * 
	 * @param connectorId
	 * @return
	 */
	public static Connector getConnectorByMenuConnectorId(String connectorId) {

		Connector connector = EMFUtil
				.getConnectorConfig(getConnectorXMLPath(connectorId));
		if (connector.getConnectorId().equals(connectorId)) {
			return connector;
		}

		return null;
	}

	/**
	 * 得到连接器图标名称方法
	 * 
	 * @param fullpath
	 * @return
	 */
	/*
	 * public static String getConnectorMenuIconName(String fullpath){ return
	 * fullpath.substring(fullpath.lastIndexOf("\\") + 1); }
	 */

	/**
	 * 得到连接器图标名称方法(兼容Mac)
	 * 
	 * @param fullpath
	 * @return
	 */
	public static String getConnectorMenuIconName(String fullpath) {
		File file = new File(fullpath);
		return file.getName();
	}

	/**
	 * 刷新工程
	 */
	public static void refreshProject(String projectName) {
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IProject myProject = workspaceRoot.getProject(projectName);

		try {
			myProject.refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
}
