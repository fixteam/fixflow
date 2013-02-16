package com.founder.fix.fixflow.designer.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;

public class FormUtil {
	/**
	 * 通过业务对象id查找模块名称
	 * @param bizobjId
	 * @return
	 */
	public static String getModelNameFromBizobjId(String bizobjId, String bizobjRealPath) {
		String modelName = "";
		
		try {
			bizobjRealPath = bizobjRealPath.replace('\\', '/');
			//处理字符串
			for (int i = 0; i < 3; i++) {
				bizobjRealPath = bizobjRealPath.substring(0, bizobjRealPath.lastIndexOf("/"));
			}
			modelName = bizobjRealPath.substring(bizobjRealPath.lastIndexOf("/") + 1, 
					bizobjRealPath.length());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return modelName;
	}
	
	/**
	 * 根据工程绝对路径判断这个工程是否是web工程
	 * @param projectRealPath
	 * @return
	 */
	public static boolean isWebProject(String projectRealPath) {
		boolean isWebPrject = false;
		try {
			File file = new File(projectRealPath);
			if(file.exists()) {
				File webroot = new File(projectRealPath + "/WebRoot");
				if(webroot.exists()) {
					isWebPrject = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isWebPrject;
	}
	
	/**
	 * 根据logic工程的名称获取其引用它的web工程全路径
	 * @param logicProjectName
	 * @return
	 */
	public static String getWebProjectRealPathFromLogicProjectName(String logicProjectName) {
		String webProjectRealPath = "";
		//找到workspace下所有的web工程
		File files = new File(getWorkspaceRealPath());
		if(files.exists()) {
			File[] workspaceFiles = files.listFiles();
			if(workspaceFiles != null && workspaceFiles.length > 0) {
				for (int i = 0; i < workspaceFiles.length; i++) {
					File file = workspaceFiles[i];
					if(file.exists() && file.isDirectory() && isWebProject(file.getAbsolutePath())) {
						//读取其.classpath看是否引用的logic工程
						try {
							Document document = XmlUtil.read(file.getAbsolutePath() + "/.classpath");
							Element root = document.getRootElement();
							List<Element> elements = root.elements("classpathentry");
							for (Iterator iterator = elements.iterator(); iterator.hasNext();) {
								Element element = (Element) iterator.next();
								if(element.attributeValue("combineaccessrules") != null 
										&& !element.attributeValue("combineaccessrules").equals("")
										&& element.attributeValue("path").equals("/" + logicProjectName)) {
									webProjectRealPath = file.getAbsolutePath();
									break;
								}
							}
						} catch (DocumentException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return webProjectRealPath.replace('\\', '/');
	}
	
	/**
	 * 获取eclipse的workspace真实目录
	 */
	public static String getWorkspaceRealPath() {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		// root.getFullPath();
		return root.getLocation().toString() + "/";
		// root.getLocationURI();
	}
	
	/**
	 * 根据bizobjId找到其对应的web工程真实路径
	 * @param bizobjId
	 * @return
	 */
	public static String getWebProjectRealPathFromBizobjId(String bizobjId, String bizobjRealPath) {
		String webProjectRealPath = "";
		
		if(!bizobjRealPath.equals("")) {
			bizobjRealPath = bizobjRealPath.replace('\\', '/');
			//处理路径获取到web工程目录
			bizobjRealPath = bizobjRealPath.substring(getWorkspaceRealPath().length());
			bizobjRealPath = bizobjRealPath.substring(0, bizobjRealPath.indexOf("/"));
			if(!isWebProject(getWorkspaceRealPath() + bizobjRealPath)) {
				webProjectRealPath = getWebProjectRealPathFromLogicProjectName(bizobjRealPath);
			} else {
				webProjectRealPath = getWorkspaceRealPath() + bizobjRealPath;
			}
		}
		return webProjectRealPath;
	}
	
	/**
	 * 通过业务对象Id找到其对应的web工程下所建立的表单的目录
	 * @param bizobjId
	 * @return
	 */
	public static String getHtmlDirectoryRealPathFromBizobjId(String bizobjId, String bizobjRealPath) {
		return getWebProjectRealPathFromBizobjId(bizobjId, bizobjRealPath) + "/WebRoot/forms/" + getModelNameFromBizobjId(bizobjId, bizobjRealPath);
	}
	
	/**
	 * 通过业务对象Id找到其对应的web工程下所建立的所有表单相对路径
	 * @param bizobjId
	 * @return
	 */
	public static List<String> getHtmlFilesRealPathFromBizobjId(String bizobjId, String bizobjRealPath) {
		String filterRealPath = getHtmlDirectoryRealPathFromBizobjId(bizobjId, bizobjRealPath) + "/" + bizobjId;
		String webRootPath = getWebProjectRealPathFromBizobjId(bizobjId, bizobjRealPath) + "/WebRoot/";
		
		List<String> list = new ArrayList<String>();
		String[] array = null;

		File tmpFile = new File(filterRealPath);
		if (tmpFile.exists()) {
			File[] files = tmpFile.listFiles();
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					File file = files[i];
					if (file.getName().indexOf(".svn") == -1) {
						list.add(file.getAbsolutePath()
								.substring(webRootPath.length())
								.replace('\\', '/'));
					}
				}
			}
		}

		/*array = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}*/

		return list;
	}
	
	public static String getFormNameByFormPath(String formPath){
		return formPath.substring(formPath.lastIndexOf("/") + 1, formPath.length());
	}
}
