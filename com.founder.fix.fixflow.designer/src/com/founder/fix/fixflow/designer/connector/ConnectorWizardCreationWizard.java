package com.founder.fix.fixflow.designer.connector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.founder.fix.bpmn2extensions.connector.Connector;
import com.founder.fix.bpmn2extensions.connectormenu.ConnectormenuFactory;
import com.founder.fix.bpmn2extensions.connectormenu.Menu;
import com.founder.fix.bpmn2extensions.connectormenu.MenuConnector;
import com.founder.fix.bpmn2extensions.connectormenu.Node;
import com.founder.fix.fixflow.designer.modeler.ui.property.connectors.CreateConnectorJava;
import com.founder.fix.fixflow.designer.util.ConnectorUtil;

public class ConnectorWizardCreationWizard extends Wizard {
	private ConfigureConnectorWizardPage ccwd;
	private Connector connector;
	
	/**
	 * 添加时构造函数
	 */
	public ConnectorWizardCreationWizard() {
		
	}
	
	/**
	 * 编辑时构造函数
	 * @param connector
	 */
	public ConnectorWizardCreationWizard(Connector connector) {
		this.connector = connector;
	}

	@Override
	public void addPages() {
		// TODO Auto-generated method stub
		if(connector == null) {
			ccwd = new ConfigureConnectorWizardPage(true, "编辑连接器");
		}else{
			ccwd = new ConfigureConnectorWizardPage(true, "编辑连接器", connector);
		}
		addPage(ccwd);
	}

	@Override
	public boolean performFinish() {
		if(ccwd.getOpenType().equals("edit")) {
			boolean b = MessageDialog.openConfirm(getShell(), "提示", "使用此编辑方式会导致连接器java文件重写，确定要这么做吗？");
			if(!b)
				return false;
		}
		
		// TODO Auto-generated method stub
		

		// As of here we preparing to save the model content

		// Register the XMI resource factory for the .website extension

		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("xml", new XMIResourceFactoryImpl());
		
		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();
		
		// Create a resource
		XMIResource resource = (XMIResource) resSet.createResource(URI.createFileURI(ConnectorUtil.getConnectorXMLPath(ccwd.getConnector().getConnectorId())));
		resource.setEncoding("UTF-8");
		
		//创建Menu的XML
//		XMIResource menuresource = (XMIResource) resSet.createResource(URI.createFileURI(Platform.getInstallLocation().getURL().getPath() +  "temp/fixflow/connector/FixConnectorMenu.xml"));
		XMIResource menuresource = (XMIResource) new ResourceSetImpl().getResource(URI.createFileURI(ConnectorUtil.getMenuConnectorPath()), true);
		menuresource.setEncoding("UTF-8");
		
		// Get the first model element and cast it to the right type, in my
		// example everything is hierarchical included in this first node

		Menu root = (Menu) menuresource.getContents().get(0);
		
		Connector connector = ccwd.getConnector();
		
		Node node = ccwd.getNode();
		
		MenuConnector menuConnector = ConnectormenuFactory.eINSTANCE.createMenuConnector();
		menuConnector.setId(connector.getConnectorId());
		menuConnector.setName(connector.getConnectorName());
		menuConnector.setNote(connector.getConnectorNote());
		
		for(Node nod : root.getNode()){
			if(nod.getName().equals(node.getName())){
				node = nod;
				break;
			}
		}
		
		//如果是创建连接器
		if(ccwd.getOpenType().equals("create")) {
			/**
			 * 验证连接器ID唯一
			 */
			Iterator<Node> iter = root.getNode().iterator();
			while(iter.hasNext()){
				Node nd = iter.next();
				for(MenuConnector mc : nd.getMenuConnector()){
					if(mc.getId() != null){
						if(mc.getId().equals(connector.getConnectorId())){
							MessageDialog.openWarning(null, "提示", "已存在此ID的连接器，请更换ID!");
							return false;
						}
					}
				}
			}
			
			node.getMenuConnector().add(menuConnector);
			root.getNode().add(node);
		}
		
		
		//如果是编辑连接器
		if(ccwd.getOpenType().equals("edit")) {
			/*File file = new File(ConnectorUtil.getConnectorPathById(ccwd.getConnector().getConnectorId()));
			deleteFile(file);*/
			int idx =0;
			int idx1 =0;
			List<MenuConnector> menuConnectors = new ArrayList<MenuConnector>();
			for(MenuConnector menuConnector2 : node.getMenuConnector()) {
				if(menuConnector2.getId().equals(menuConnector.getId())) {
					menuConnectors.add(menuConnector2);
					idx = node.getMenuConnector().indexOf(menuConnector2);
				}
			}
			node.getMenuConnector().removeAll(menuConnectors);
			node.getMenuConnector().add(idx, menuConnector);
			
			List<Node> nodes = new ArrayList<Node>();
			for(Node node2 : root.getNode()) {
				if(node2.getName().equals(node.getName())) {
					idx1 = root.getNode().indexOf(node2);
					nodes.add(node2);
				}
			}
			root.getNode().removeAll(nodes);
			root.getNode().add(idx1, node);
		}
		
		menuresource.getContents().add(root);
		resource.getContents().add(connector);
		// Now save the content.
		try {
			resource.save(Collections.EMPTY_MAP);
			menuresource.save(Collections.EMPTY_MAP);
			
			 //一次读取1024个字节，当byteread为-1时表示文件已经读完
			int byteread = 0;//读取的位数
		    byte[] buffer = new byte[1024];
		    
			//写入图标文件
			if(null != ccwd.getIconPath()){
				//打开原文件（connector图标）
			    FileInputStream fis = new FileInputStream(ccwd.getIconPath());
			    //打开连接到目标文件的输出流
				File outfile = new File(ConnectorUtil.getConnectorPathById(ccwd.getConnector().getConnectorId()) + "/" + ccwd.getConnector().getIcon());
				FileOutputStream outStream = new FileOutputStream(outfile);
				
				 while ((byteread = fis.read(buffer)) != -1) {
					    //将读取的字节写入输出流
					    outStream.write(buffer, 0, byteread);
				    }
				 outStream.close();
			}
			
			if(null != ccwd.getCustomCategoryIconPath()){
				 //打开原文件（Menu图标）
				    FileInputStream menufis = new FileInputStream(ccwd.getCustomCategoryIconPath());
					 //打开连接到目标文件的输出流
					File menuoutfile = new File(ConnectorUtil.getMenuConnectorIconPath() + node.getIco());
					FileOutputStream menuoutStream = new FileOutputStream(menuoutfile);
					
				    while ((byteread = menufis.read(buffer)) != -1) {
					    //将读取的字节写入输出流
				    	menuoutStream.write(buffer, 0, byteread);
				    }
				    menuoutStream.close();
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(ccwd.getOpenType().equals("create"))
			MessageDialog.openInformation(null, "提示", "连接器创建成功");
		if(ccwd.getOpenType().equals("edit"))
			MessageDialog.openInformation(null, "提示", "连接器修改成功");
		//生成java代码
		InputStream is = CreateConnectorJava.CreateConnectorJavaClassReturnInputStream(connector);
		File file = new File(ConnectorUtil.getConnectorPathById(ccwd.getConnector().getConnectorId()) + "/" + ccwd.getConnector().getConnectorId() + ".java");
		FileOutputStream javafileOutputStream = null;
		try {
			javafileOutputStream = new FileOutputStream(file);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		 //一次读取1024个字节，当byteread为-1时表示文件已经读完
		int byteread = 0;//读取的位数
	    byte[] buffer = new byte[1024];
		
		 try {
			while ((byteread = is.read(buffer)) != -1) {
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
		
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject("fixflow-expand");
		String relativePath = file.toString().substring(project.getLocation().toString().length() + 1);
		IFile ifile = project.getFile(relativePath);
		
		try {
			// 打开编辑器
			ConnectorUtil.refreshProject("fixflow-expand");
			IDE.openEditor(page, ifile);
		} catch (PartInitException e) {
		}
		return true;
	}
	
	/**
	 * 刷新文件夹
	 * 
	 * @param projectName
	 * @param folderName
	 */
/*	public static void refreshFolder(String projectName, String folderName) {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IProject project = root.getProject(projectName);
		try {
			project.open(null);
			// 刷新文件夹
			IFolder folder = project.getFolder(folderName);
			folder.refreshLocal(IResource.FOLDER, null);

			// 刷新文件
			IFile file = project.getFile(folderName);
			file.refreshLocal(IResource.FILE, null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}*/
}
