package com.founder.fix.fixflow.designer.internationalization;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.bpmn2.CallableElement;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.LaneSet;
import org.eclipse.bpmn2.TextAnnotation;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.bpmn2.modeler.core.ModelHandler;
import org.eclipse.bpmn2.modeler.core.ModelHandlerLocator;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.jface.dialogs.MessageDialog;

import com.founder.fix.apputil.util.FixResourceUtil;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.fixflow.TaskCommand;

public class ResourcesUtil {
	
	
	

	public static List<Map<String, String>> getResourceList(Resource resource) throws Exception {

		ModelHandler modelHandler = null;
		try {
			modelHandler = ModelHandlerLocator.getModelHandler(resource);
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
		
		List<CallableElement> callableElements = modelHandler.getAll(CallableElement.class);
		for (CallableElement callableElement : callableElements) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("key", callableElement.getId());
			map.put("value", callableElement.getName());
			listMap.add(map);
		}

		List<FlowElement> flowElements = modelHandler.getAll(FlowElement.class);
		for (FlowElement flowElement : flowElements) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("key", flowElement.getId());
			map.put("value", flowElement.getName());
			listMap.add(map);
		}
		
		
		List<Lane> laneList = modelHandler.getAll(Lane.class);
		for (Lane lane : laneList) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("key", lane.getId());
			map.put("value", lane.getName());
			listMap.add(map);
		}
		
		List<LaneSet> laneSetList = modelHandler.getAll(LaneSet.class);
		for (LaneSet laneSet : laneSetList) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("key", laneSet.getId());
			map.put("value", laneSet.getName());
			listMap.add(map);
		}
		
		List<TextAnnotation> textAnnotationList = modelHandler.getAll(TextAnnotation.class);
		for (TextAnnotation textAnnotation : textAnnotationList) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("key", textAnnotation.getId());
			map.put("value", textAnnotation.getText());
			listMap.add(map);
		}
		
		
		
		List<UserTask> userTasks = modelHandler.getAll(UserTask.class);
		for (UserTask userTask : userTasks) {
			
		
			
			for(ExtensionAttributeValue extensionAttributeValue : userTask.getExtensionValues()){
				FeatureMap extensionElements = extensionAttributeValue.getValue();
				Object objectElement = extensionElements.get(FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_COMMAND, true);
				if(objectElement != null){
					
					List<TaskCommand> taskCommandList=(List<TaskCommand>) objectElement;
					for (TaskCommand taskCommand : taskCommandList) {
						Map<String, String> map = new HashMap<String, String>();
						
						map.put("key", userTask.getId()+"_"+taskCommand.getId());
						map.put("value", taskCommand.getName());
						listMap.add(map);
						
					}

				}
			}

		}

		

		return listMap;

	}
	
	public static void createResourcesFile(String flowKey, String flowVersion,
			String flowGuid, String folderPath,
			Resource resource) throws Exception {
		createResourcesFile(flowKey,flowVersion,flowGuid,folderPath,getResourceList(resource));
	}

	public static boolean createResourcesFile(String flowKey, String flowVersion,
			String flowGuid, String folderPath,
			List<Map<String, String>> resourceList) throws Exception {
		folderPath = getWorkspaceRealPath() + folderPath + "/resource/defauld/";
		
		//流程国际化资源文件夹
		File resourceFolder = new File(folderPath);
		if(!resourceFolder.exists()) {
			resourceFolder.mkdirs();
		}
		
		//流程国际化配置文件夹(以flowKey值为名称)
		File flowFoler = new File(folderPath + "/" + flowKey);
		if(!flowFoler.exists()) {
			flowFoler.mkdirs();
		} 
		
		File _flowResource = new File(flowFoler.getAbsolutePath() 
				+ "/" + flowKey + "_" + flowVersion + "_" + flowGuid + ".properties");
		if(!_flowResource.exists()) {
			try {
				_flowResource.createNewFile();	//不存在国际化文件需要创建
			} catch (IOException e) {
				e.printStackTrace();
				MessageDialog.openError(null, "错误", e.toString());
				throw e;
			}
		}
		
		File flowResource = new File(flowFoler.getAbsolutePath() 
				+ "/" + flowKey + "_" + flowVersion + "_" + flowGuid + "_tmp.properties");
		if(!flowResource.exists()) {
			try {
				flowResource.createNewFile();	//不存在国际化文件需要创建
			} catch (IOException e) {
				e.printStackTrace();
				MessageDialog.openError(null, "错误", e.toString());
				throw e;
			}
		}
		
		//写入property文件
		try {
			String path = flowResource.getAbsolutePath();
			Properties properties = FixResourceUtil.load(path);
			properties.clear();
			
			for (Iterator iterator = resourceList.iterator(); iterator
					.hasNext();) {
				Map<String, String> map = (Map<String, String>) iterator.next();
				properties.setProperty(map.get("key"), 
						map.get("value") == null ? "" : map.get("value"));
			}
			FixResourceUtil.write(properties, path);
			
			//转换字符编码为中文
			javaConsoleCommand("native2ascii -reverse -encoding utf-8 \"" + path
					+ "\" \"" + _flowResource + "\"");
			refreshTruthFileFromFileFullPath(path);
			refreshTruthFileFromFileFullPath(_flowResource.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
			MessageDialog.openError(null, "错误", e.toString());
			throw e;
		}
		
		return true;
	}

	/**
	 * java执行windows命令行
	 * @param command
	 * @throws Exception 
	 */
	public static synchronized void javaConsoleCommand(String command) throws Exception {
		try {
			System.out.println("正在进行转码...命令行：" + command);
//			Process process = Runtime.getRuntime().exec(command);
			Runtime.getRuntime().exec(command);
			System.out.println("转码成功...");
			
//			InputStream inputStream = process.getInputStream();
//            byte[] bs = new byte[1024];
//            inputStream.read(bs);
//            inputStream.close();
//            
//            System.out.println(new String(bs));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	
	/**
	 * 通过文件绝对路径刷新workspace下的该文件
	 * @param fileFullPath
	 * @throws Exception 
	 */
	public static void refreshTruthFileFromFileFullPath(String fileFullPath) throws Exception {
		String projectName = getProjectPath(fileFullPath);
		String fileName = getFilePath(fileFullPath);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IProject project = root.getProject(projectName);
		try {
			project.open(null);
			// 刷新文件
			IFile file = project.getFile(fileName);
			file.refreshLocal(IResource.FILE, null);
		} catch (CoreException e) {
			MessageDialog.openError(null, "错误", e.toString());
			throw new Exception(e);
		}
	}
	
	/**
	 * 根据文件名获取工程名称
	 * 
	 * @param fileFullName
	 * @return
	 */
	public static String getProjectPath(String fileFullName) {
		fileFullName = fileFullName.replace('\\', '/'); // 替换符号
		String workSpacePath = getWorkspaceRealPath();
		if(workSpacePath.startsWith("/") && isWindows()) {
			workSpacePath = workSpacePath.substring(1);
		}
		String projectAndFilePath = null;
		int index = fileFullName.indexOf(workSpacePath);
		if (index > -1) {
			projectAndFilePath = fileFullName.substring(workSpacePath.length(),
					fileFullName.length());
		}
		return projectAndFilePath.substring(0, (projectAndFilePath.indexOf("/") == -1) ? 
				projectAndFilePath.length() : projectAndFilePath.indexOf("/"));
	}
	
	/**
	 * 获取文件相对路径
	 * 
	 * @param fileFullName
	 * @return
	 */
	public static String getFilePath(String fileFullName) {
		fileFullName = fileFullName.replace('\\', '/'); // 替换符号
		String workSpacePath = getWorkspaceRealPath();
		if(workSpacePath.startsWith("/") && isWindows()) {
			workSpacePath = workSpacePath.substring(1);
		}
		String projectAndFilePath = null;
		int index = fileFullName.indexOf(workSpacePath);
		if (index > -1) {
			projectAndFilePath = fileFullName.substring(workSpacePath.length(),
					fileFullName.length());
		}
		return projectAndFilePath.substring(
				projectAndFilePath.indexOf("/") + 1,
				projectAndFilePath.length());
	}
	
	/**
	 * 获取eclipse的workspace真实目录
	 */
	public static String getWorkspaceRealPath() {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		return root.getLocation().toString() + "/";
	}
	
	/**
	 * 判断运行系统是否是windows
	 * @return
	 */
	public static boolean isWindows() {
		String os = System.getProperty("os.name");
		if (os.startsWith("win") || os.startsWith("Win")) {
			return true;
		}
		return false;
	}
	
}
