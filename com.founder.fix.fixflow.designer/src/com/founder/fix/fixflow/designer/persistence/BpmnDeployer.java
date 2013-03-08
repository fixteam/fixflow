package com.founder.fix.fixflow.designer.persistence;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.net.aso.g;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;

import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.fixflow.designer.database.SqlCommand;
import com.founder.fix.fixflow.designer.exception.FixFlowException;
import com.founder.fix.fixflow.designer.internationalization.ResourcesUtil;
import com.founder.fix.fixflow.designer.modeler.ui.property.SectionBpmnElement;
import com.founder.fix.fixflow.designer.util.ClockUtil;
import com.founder.fix.fixflow.designer.util.ConnectorUtil;
import com.founder.fix.fixflow.designer.util.EMFExtensionUtil;
import com.founder.fix.fixflow.designer.util.EMFUtil;
import com.founder.fix.fixflow.designer.util.FixFlowConfigUtil;
import com.founder.fix.fixflow.designer.util.GuidUtil;
import com.founder.fix.fixflow.designer.util.IoUtil;
import com.founder.fix.fixflow.designer.util.StringUtil;

/**
 * @author kenshins
 */
public class BpmnDeployer {

	public static final String BPMN_RESOURCE_SUFFIX = "bpmn";
	public static final String[] DIAGRAM_SUFFIXES = new String[] { "png", "jpg", "gif", "svg" };

	public String deploy(IFile ifile, String deploymentName, Definitions definitions,Text dbidtext,BPMN2Editor bpmn2Editor) throws Exception {

		Connection connection = null;

		// MessageDialog.openInformation(null, "流程定义发布信息",
		// "流程发布出错,原因是数据库链接配置错误。请在设计器的属性配置里检查数据库链接。");

		String processIdString = null;

		try {
			
			connection = FixFlowConfigUtil.createConnection();
			connection.setAutoCommit(false);
			
			org.eclipse.bpmn2.Process process = SectionBpmnElement.process;

			/*
			for (RootElement rootElement : definitions.getRootElements()) {
				if (rootElement instanceof org.eclipse.bpmn2.Process) {
					process = (org.eclipse.bpmn2.Process) rootElement;
					break;
				}
			}*/
			String sqlText = "select * " + "from FIXFLOW_DEF_PROCESSDEFINITION " + "where PROCESS_KEY = ? and "
					+ "VERSION = (select max(VERSION) from FIXFLOW_DEF_PROCESSDEFINITION where PROCESS_KEY = ?)";

			// 构建查询参数

			List<Object> objectParamWhere = new ArrayList<Object>();
			objectParamWhere.add(process.getId());
			objectParamWhere.add(process.getId());
			SqlCommand sqlCommand = new SqlCommand(connection);
			List<Map<String, Object>> dataObj = null;
			try {
				dataObj = sqlCommand.queryForList(sqlText, objectParamWhere);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int version = 1;

			if (dataObj == null || dataObj.size() == 0) {
				version = 1;
			} else {
				version = StringUtil.getInt(dataObj.get(0).get("VERSION")) + 1;
			}

			
			
			
			
			String guid= GuidUtil.CreateGuid();
			processIdString = process.getId() + ":" + version + ":" + guid;
			
			// 蛋疼的bug这里
			dbidtext.setText(processIdString);
			bpmn2Editor.doSave(null);
			
			

			

			String pathString = ifile.getFullPath().toString();
			String fileName = ifile.getName();
			InputStream inputStream = null;
			try {
				inputStream = ifile.getContents();
			} catch (CoreException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			

			Map<String, Object> deploymentMap = new HashMap<String, Object>();
			deploymentMap.put("id", GuidUtil.CreateGuid());
			deploymentMap.put("name", deploymentName);
			deploymentMap.put("deploymentTime", ClockUtil.getCurrentTime());

			DeploymentPersistence DeploymentPersistence = new DeploymentPersistence(connection);
			DeploymentPersistence.insertDeployment(deploymentMap);

			if (inputStream == null) {
				throw new FixFlowException("resource '" + pathString + "' not found");
			}

			byte[] bytes = IoUtil.readInputStream(inputStream, pathString);

			Map<String, Object> resourceMap = new HashMap<String, Object>();

			resourceMap.put("id", GuidUtil.CreateGuid());
			resourceMap.put("name", fileName);
			resourceMap.put("bytes", bytes);
			resourceMap.put("deploymentId", deploymentMap.get("id"));

			ResourcePersistence resourcePersistence = new ResourcePersistence(connection);
			resourcePersistence.insertResource(resourceMap);

			Map<String, Object> processMap = new HashMap<String, Object>();

		
			String processCategory = EMFExtensionUtil.getAnyAttributeValue(process, "category");

			processMap.put("processDefinitionId", processIdString);
			processMap.put("processDefinitionName", process.getName());
			processMap.put("processDefinitionKey", process.getId());
			processMap.put("category", processCategory);
			processMap.put("version", version);
			processMap.put("resourceName", fileName);
			processMap.put("resourceId", resourceMap.get("id"));
			processMap.put("deploymentId", deploymentMap.get("id"));
			// processMap.put("startForm", formUri);
			// processMap.put("subTaskId", subTask);

			ProcessDefinitionPersistence processDefinitionPersistence = new ProcessDefinitionPersistence(connection);

			processDefinitionPersistence.insertProcessDefinition(processMap);

			
			String isEnable=FixFlowConfigUtil.getFixFlowConfig().getInternationalizationConfig().getIsEnable();
			String folderPath=FixFlowConfigUtil.getFixFlowConfig().getInternationalizationConfig().getFolderPath();
			if(StringUtil.getBoolean(isEnable)){
				//生成国际化资源文件
				ResourcesUtil.createResourcesFile(process.getId(),StringUtil.getString(version), guid, folderPath, definitions.eResource());
				
			}
			
			
			
			connection.commit();
			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new Exception(e);

		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return processIdString;

	}

	public void deleteDeploy(String processKey, int processVersion, Connection connection) {

		SqlCommand sqlCommand = new SqlCommand(connection);

		try {
			connection.setAutoCommit(false);

			// 构建查询参数
			List<Object> objectParamWhere = new ArrayList<Object>();
			objectParamWhere.add(processKey);
			objectParamWhere.add(processVersion);
			// 设置查询字符串
			String sqlText = "SELECT DEPLOYMENT_ID,PROCESS_ID FROM FIXFLOW_DEF_PROCESSDEFINITION WHERE PROCESS_KEY=? AND VERSION=?";
			// 执行查询流程是Sql语句,判断流程实例是否存在于数据库中.
			String deploymentId = null;

			List<Map<String, Object>> mapList = sqlCommand.queryForList(sqlText, objectParamWhere);

			deploymentId = StringUtil.getString(mapList.get(0).get("DEPLOYMENT_ID"));
			String processId = StringUtil.getString(mapList.get(0).get("PROCESS_ID"));
			// String sqlTextCount =
			// "SELECT DEPLOYMENT_ID,PROCESS_ID FROM FIXFLOW_DEF_PROCESSDEFINITION WHERE PROCESS_KEY=? AND VERSION=?";

			// 构建查询参数
			/*List<Object> objectParamWhereCount = new ArrayList<Object>();
			objectParamWhereCount.add(processId);
			Object count = sqlCommand.queryForValue("SELECT count(1) FROM FIXFLOW_RUN_PROCESSINSTANECE WHERE PROCESSDEFINITION_ID=? AND END_TIME IS NULL", objectParamWhereCount);
			int countTemp = 0;
			if (count != null) {
				countTemp = Integer.parseInt(count.toString());
			}

			if (countTemp > 0) {
				if (!MessageDialog.openConfirm(null, "流程定义删除警告", "当前删除的流程还有未完成流程实例,您确认删除吗?")) {
					return;
				}

			}*/

			deleteDeployment(deploymentId, sqlCommand);
			deleteProcessDefinitionsByDeploymentId(deploymentId, sqlCommand);
			deleteResourcesByDeploymentId(deploymentId, sqlCommand);

			String sqlTextSl = "SELECT PROCESSINSTANCE_ID FROM FIXFLOW_RUN_PROCESSINSTANECE WHERE PROCESSDEFINITION_ID=?";
			// 执行查询流程是Sql语句,判断流程实例是否存在于数据库中.
			List<Object> objectParamWhereSl = new ArrayList<Object>();
			objectParamWhereSl.add(processId);
			List<Map<String, Object>> mapListSl = sqlCommand.queryForList(sqlTextSl, objectParamWhereSl);

			for (Map<String, Object> map : mapListSl) {
				deleteIdentityLinkByProcessInstanceId(map.get("PROCESSINSTANCE_ID").toString(), sqlCommand);
				deleteProcessInstanceByProcessInstanceId(map.get("PROCESSINSTANCE_ID").toString(), sqlCommand);
				deleteTaskInstanceByProcessInstanceId(map.get("PROCESSINSTANCE_ID").toString(), sqlCommand);
				deleteTokenByProcessInstanceId(map.get("PROCESSINSTANCE_ID").toString(), sqlCommand);
				deleteVariableByProcessInstanceId(map.get("PROCESSINSTANCE_ID").toString(), sqlCommand);
			}

			connection.commit();

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void deleteDeployment(String persistentObjectId, SqlCommand sqlCommand) {
		// 构建Where查询参数
		Object[] objectParamWhere = { persistentObjectId };
		try {
			sqlCommand.delete("FIXFLOW_DEF_DEPLOYMENT", " ID=?", objectParamWhere);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MessageDialog.openInformation(null, "流程定义删除信息", "流程删除出错。");
		}

	}

	private void deleteProcessDefinitionsByDeploymentId(String deploymentId, SqlCommand sqlCommand) {
		// 构建Where查询参数
		Object[] objectParamWhere = { deploymentId };
		try {
			sqlCommand.delete("FIXFLOW_DEF_PROCESSDEFINITION", " DEPLOYMENT_ID=?", objectParamWhere);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MessageDialog.openInformation(null, "流程定义删除信息", "流程删除出错。");
		}
	}

	private void deleteResourcesByDeploymentId(String deploymentId, SqlCommand sqlCommand) {
		// 构建Where查询参数
		Object[] objectParamWhere = { deploymentId };
		try {
			sqlCommand.delete("FIXFLOW_DEF_BYTEARRAY", " ID=?", objectParamWhere);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MessageDialog.openInformation(null, "流程定义删除信息", "流程删除出错。");
		}
	}

	public void updateDeploy(BPMN2Editor bpmn2Editor, final EObject be, final String newProcessGuid,final String oldProcessGuid,String typeString,String nameString) {

		
		
		Connection connection = null;

		try {

			
			@SuppressWarnings("restriction")
			TransactionalEditingDomain editingDomain = bpmn2Editor.getEditingDomain();
			editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {

					SectionBpmnElement.process.eSet(FixFlowPackage.Literals.DOCUMENT_ROOT__DBID,newProcessGuid);

				}
			});
			
			bpmn2Editor.doSave(null);
			IFile ifile=bpmn2Editor.getModelFile();
			
			
			
			connection = FixFlowConfigUtil.createConnection();
			connection.setAutoCommit(false);

			String pathString = ifile.getFullPath().toString();
			String fileName = ifile.getName();
			InputStream inputStream = null;
			try {
				inputStream = ifile.getContents();
			} catch (CoreException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String sqlText = "select DEPLOYMENT_ID,RESOURCE_ID,VERSION,PROCESS_KEY FROM FIXFLOW_DEF_PROCESSDEFINITION " + "where PROCESS_ID = ? ";

			// 构建查询参数

			List<Object> objectParamWhere = new ArrayList<Object>();
			objectParamWhere.add(newProcessGuid);
			SqlCommand sqlCommand = new SqlCommand(connection);
			List<Map<String, Object>> resMap = null;
			try {
				resMap = sqlCommand.queryForList(sqlText, objectParamWhere);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String resId = resMap.get(0).get("RESOURCE_ID").toString();
			String deployId = resMap.get(0).get("DEPLOYMENT_ID").toString();
			String version=resMap.get(0).get("VERSION").toString();
			String processKey=resMap.get(0).get("PROCESS_KEY").toString();
			
			Map<String, Object> deploymentMap = new HashMap<String, Object>();
			deploymentMap.put("ID", deployId);
			deploymentMap.put("DEPLOY_TIME", ClockUtil.getCurrentTime());

			DeploymentPersistence deploymentPersistence = new DeploymentPersistence(connection);
			deploymentPersistence.updateDeployment(deploymentMap);

			// BYTES

			Map<String, Object> resourceMap = new HashMap<String, Object>();
			resourceMap.put("ID", resId);
			byte[] bytes = IoUtil.readInputStream(inputStream, pathString);

			resourceMap.put("BYTES", bytes);
			resourceMap.put("NAME", fileName);

			ResourcePersistence resourcePersistence = new ResourcePersistence(connection);

			resourcePersistence.updateResource(resourceMap);
			
			
			
			Map<String, Object> resourceMapDef = new HashMap<String, Object>();
			resourceMapDef.put("processDefinitionName", nameString);
			resourceMapDef.put("category", typeString);
			resourceMapDef.put("processDefinitionId", newProcessGuid);
			
			ProcessDefinitionPersistence persistence=new ProcessDefinitionPersistence(connection);
			persistence.updateProcessDefinition(resourceMapDef);
			
			
			String isEnable=FixFlowConfigUtil.getFixFlowConfig().getInternationalizationConfig().getIsEnable();
			String folderPath=FixFlowConfigUtil.getFixFlowConfig().getInternationalizationConfig().getFolderPath();
			if(StringUtil.getBoolean(isEnable)){
				
				String guid=newProcessGuid.substring(newProcessGuid.lastIndexOf(":")+1, newProcessGuid.length());

				ResourcesUtil.createResourcesFile(processKey,version, guid, folderPath, be.eResource());
				
			}
			
			

			connection.commit();
			
			
			
			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			@SuppressWarnings("restriction")
			TransactionalEditingDomain editingDomain = bpmn2Editor.getEditingDomain();
			editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {

					SectionBpmnElement.process.eSet(FixFlowPackage.Literals.DOCUMENT_ROOT__DBID,oldProcessGuid);

				}
			});
			
			bpmn2Editor.doSave(null);
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			MessageDialog.openInformation(null, "流程定义更新信息", "流程更新出错,原因是 " + e.toString());

		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		MessageDialog.openInformation(null, "流程定义更新信息", "流程更新成功!");

	}

	public Definitions getResources(BPMN2Editor bpmn2Editor, String processGuid) {

		Definitions definitionsNew = null;
		Connection connection = null;

		try {

			connection = FixFlowConfigUtil.createConnection();
			String sqlText = "select DEPLOYMENT_ID,RESOURCE_ID FROM FIXFLOW_DEF_PROCESSDEFINITION " + "where PROCESS_ID = ? ";

			// 构建查询参数

			List<Object> objectParamWhere = new ArrayList<Object>();
			objectParamWhere.add(processGuid);
			SqlCommand sqlCommand = new SqlCommand(connection);
			List<Map<String, Object>> resMap = null;
			try {
				resMap = sqlCommand.queryForList(sqlText, objectParamWhere);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String resId = resMap.get(0).get("RESOURCE_ID").toString();
			// String deployId=resMap.get(0).get("DEPLOYMENT_ID").toString();

			List<Object> objectParamWhereRes = new ArrayList<Object>();

			String selectSql = "SELECT * FROM FIXFLOW_DEF_BYTEARRAY WHERE ID = ?";

			objectParamWhereRes.add(resId);

			List<Map<String, Object>> dataObj = sqlCommand.queryForList(selectSql, objectParamWhereRes);

			byte[] bytes = null;
			Object bytesObject = dataObj.get(0).get("BYTES");
			if (bytesObject != null) {
				bytes = (byte[]) bytesObject;
			}
			
			ByteArrayInputStream bais=new ByteArrayInputStream(bytes);

//			Bpmn2ResourceFactoryImpl ddd = new Bpmn2ResourceFactoryImpl();
//			Resource ddddResource = ddd.createResource(URI.createFileURI(ReflectUtil.getResource("com/founder/fix/fixflow/designer/persistence/fixflowfile.bpmn").getFile()));
			
			if(MessageDialog.openConfirm(null, "提示", "确定不发布当前修改的流程吗？(该操作会丢失当前正在进行的修改操作)")) {
				//写文件
				
				
				//File file = new File(bpmn2Editor.getModelFile().getRawLocationURI().toString().substring(6));
				
				FileOutputStream javafileOutputStream = null;
				try {
					javafileOutputStream = new FileOutputStream(bpmn2Editor.getModelFile().getLocationURI().getPath());
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				 //一次读取1024个字节，当byteread为-1时表示文件已经读完
				int byteread = 0;//读取的位数
			    byte[] buffer = new byte[1024];
				
				 try {
					while ((byteread = bais.read(buffer)) != -1) {
						    //将读取的字节写入输出流
						 javafileOutputStream.write(buffer, 0, byteread);
					    }
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 try {
					javafileOutputStream.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String projectName = "";
				if(bpmn2Editor.getModelFile().toString().split("/").length>1) {
					projectName = bpmn2Editor.getModelFile().toString().split("/")[1];
				}
				
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
				String relativePath = bpmn2Editor.getModelFile().getLocationURI().getPath();
				IFile ifile = project.getFile(relativePath.substring(relativePath.indexOf(projectName)
						+ projectName.length(),	relativePath.length()));
				IEditorPart editor = page.getActiveEditor();
				page.closeEditor(editor, false);
				
				// 打开编辑器
				ConnectorUtil.refreshProject(projectName);
				IDE.openEditor(page, ifile, BPMN2Editor.EDITOR_ID);
			}
			
			
			/*Bpmn2ResourceFactoryImpl ddd = new Bpmn2ResourceFactoryImpl();
			
			Resource ddddResource = ddd.createResource(URI.createFileURI(file.toString()));

			definitionsNew = (Definitions) ddddResource.getContents().get(0).eContents().get(0);
			
			try {
				ddddResource.load(null);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				throw new FixFlowException("定义文件加载失败!", e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new FixFlowException("定义文件加载失败!", e);
			}*/
			
//			definitionsNew = (Definitions) ddddResource.getContents().get(0).eContents().get(0);


		} catch (Exception e) {
			
			MessageDialog.openInformation(null, "流程定义更新信息", "流程更新出错,原因是 " + e.toString());

		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return definitionsNew;

		// MessageDialog.openInformation(null, "流程定义更新信息", "流程更新成功!");

	}

	public void deleteIdentityLinkByProcessInstanceId(String processInstanceId, SqlCommand sqlCommand) throws Exception {

		Object[] objectParamWhere = { processInstanceId };
		// String
		// sqlString="DELETE FROM FIXFLOW_RUN_TOKEN WHERE PROCESSINSTANCE_ID=?";

		sqlCommand.delete("FIXFLOW_RUN_TASKIDENTITYLINK", " TASKINSTANCE_ID IN (SELECT TASKINSTANCE_ID FROM FIXFLOW_RUN_TAKSINSTANECE WHERE PROCESSINSTANCE_ID=?)", objectParamWhere);

	}

	public void deleteProcessInstanceByProcessInstanceId(String processInstanceId, SqlCommand sqlCommand) throws Exception {

		Object[] objectParamWhere = { processInstanceId };
		// String
		// sqlString="DELETE FROM FIXFLOW_RUN_TOKEN WHERE PROCESSINSTANCE_ID=?";

		sqlCommand.delete("FIXFLOW_RUN_PROCESSINSTANECE", " PROCESSINSTANCE_ID=?", objectParamWhere);

	}

	public void deleteTaskInstanceByProcessInstanceId(String processInstanceId, SqlCommand sqlCommand) throws Exception {

		Object[] objectParamWhere = { processInstanceId };
		// String
		// sqlString="DELETE FROM FIXFLOW_RUN_TOKEN WHERE PROCESSINSTANCE_ID=?";

		sqlCommand.delete("FIXFLOW_RUN_TAKSINSTANECE", " PROCESSINSTANCE_ID=?", objectParamWhere);

	}

	public void deleteTokenByProcessInstanceId(String processInstanceId, SqlCommand sqlCommand) throws Exception {

		Object[] objectParamWhere = { processInstanceId };
		// String
		// sqlString="DELETE FROM FIXFLOW_RUN_TOKEN WHERE PROCESSINSTANCE_ID=?";

		sqlCommand.delete("FIXFLOW_RUN_TOKEN", " PROCESSINSTANCE_ID=?", objectParamWhere);

	}

	public void deleteVariableByProcessInstanceId(String processInstanceId, SqlCommand sqlCommand) throws Exception {

		Object[] objectParamWhere = { processInstanceId };
		// String
		// sqlString="DELETE FROM FIXFLOW_RUN_TOKEN WHERE PROCESSINSTANCE_ID=?";

		sqlCommand.delete("FIXFLOW_RUN_VARIABLE", " PROCESSINSTANCE_ID=?", objectParamWhere);

	}
	
	
	/**
	 * 通过文件全路径找到当前打开的编辑器
	 * 
	 * @param fileRealPath
	 */
	public static IEditorPart getEditorPartFromFileRealPath(String fileRealPath) {
		fileRealPath = fileRealPath.replace('\\', '/');
		IEditorPart editor = null;
		// 找到打开的并隶属于该文件的编辑器并关闭
		IWorkbenchPage workbenchPage = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		IEditorPart[] editorParts = workbenchPage.getEditors();
		if (editorParts != null && editorParts.length != 0) {
			for (int i = 0; i < editorParts.length; i++) {
				IEditorPart part = editorParts[i];
				if (part.getEditorInput() instanceof FileEditorInput) {
					String editerFileRealPath = ((FileEditorInput) part
							.getEditorInput()).getPath().toFile()
							.getAbsolutePath();
					editerFileRealPath = editerFileRealPath.replace('\\', '/');
					// 找到这个文件对应的编辑器
					if (fileRealPath.equals(editerFileRealPath)) {
						editor = part;
						break;
					}
				}
				if (part.getEditorInput() instanceof DiagramEditorInput) {
					// 获取uriString的范例：'platform:/resource/fixweb/.bpmn2/webapp/modules/system/bpmn/fixflow.bpmn2d#/'
					String uriString = ((DiagramEditorInput) part
							.getEditorInput()).getUriString();
					uriString = uriString.substring(
							"platform:/resource/".length(), uriString.length());
					String projectName = uriString.substring(0,
							uriString.indexOf("/")); // 截取工程名称**web或者**logic
					uriString = uriString.substring(projectName.length()
							+ ".bpmn2".length() + 1,
							uriString.lastIndexOf("2d#/"));
					
					IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
					
					uriString = root.getLocation().toString() + "/" + projectName + "/" 
							+ uriString;
					// 找到这个文件对应的编辑器
					if (fileRealPath.toString().equals(uriString)) {
						editor = part;
						break;
					}
				}
			}
		}
		return editor;
	}

}
