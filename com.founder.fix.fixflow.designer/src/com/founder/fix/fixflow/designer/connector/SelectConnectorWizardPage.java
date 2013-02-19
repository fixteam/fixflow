package com.founder.fix.fixflow.designer.connector;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

import com.founder.fix.bpmn2extensions.connector.Connector;
import com.founder.fix.bpmn2extensions.connectormenu.Menu;
import com.founder.fix.bpmn2extensions.connectormenu.MenuConnector;
import com.founder.fix.bpmn2extensions.connectormenu.Node;
import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage;
import com.founder.fix.fixflow.designer.modeler.ui.property.connectors.dialog.ConnectorFilter;
import com.founder.fix.fixflow.designer.modeler.ui.property.connectors.tree.ITreeElement;
import com.founder.fix.fixflow.designer.modeler.ui.property.connectors.tree.TreeViewerContentProvider;
import com.founder.fix.fixflow.designer.modeler.ui.property.connectors.tree.TreeViewerFactory;
import com.founder.fix.fixflow.designer.modeler.ui.property.connectors.tree.TreeViewerLabelProvider;
import com.founder.fix.fixflow.designer.util.ConnectorUtil;
import com.founder.fix.fixflow.designer.util.EMFUtil;

import org.eclipse.swt.widgets.Button;

public class SelectConnectorWizardPage extends WizardPage {
	private Text searchtext;
	private ConnectorFilter filter;
	private TreeViewer treeViewer;
	private Connector connector;
	private EditConnectorWizard editConnectorWizard;
	private List<ITreeElement> elements;
	private Button deleteButton;
	private String MenuNodeId;

	/**
	 * Create the wizard.
	 */
	public SelectConnectorWizardPage(EditConnectorWizard editConnectorWizard) {
		super("wizardPage");
		setTitle("选择连接器");
		setDescription("选择你想编辑的连接器");
		filter = new ConnectorFilter();
		this.editConnectorWizard = editConnectorWizard;
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		GridLayout gl_container = new GridLayout(1, false);
		gl_container.verticalSpacing = 0;
		gl_container.marginHeight = 0;
		gl_container.marginWidth = 0;
		gl_container.horizontalSpacing = 0;
		container.setLayout(gl_container);
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite.setBounds(0, 0, 64, 64);
		composite.setLayout(new GridLayout(2, false));
		
		Label searchLabel = new Label(composite, SWT.NONE);
		searchLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		searchLabel.setText("搜索:");
		
		searchtext = new Text(composite, SWT.BORDER | SWT.SEARCH);
		searchtext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		searchtext.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				filter.setSearchText(searchtext.getText());
				
				treeViewer.refresh();
				//展现第二层
				treeViewer.expandToLevel(2);
				
				if(treeViewer.getSelection().isEmpty()) {
					setPageComplete(false); 
				}
			}
		});
		
		new Label(composite, SWT.NONE);
		
		treeViewer = new TreeViewer(composite, SWT.BORDER);
		Tree tree = treeViewer.getTree();
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		new Label(composite, SWT.NONE);
		
		deleteButton = new Button(composite, SWT.NONE);
		deleteButton.setText("删除连接器");
		deleteButton.setEnabled(false);
		deleteButton.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if(connector != null) {
					boolean b = MessageDialog.openConfirm(null, "警告", "你确认要删除吗？");
					if (!b)
						return;
					
					//删除目录
					File file = new File(ConnectorUtil.getConnectorPathById(connector.getConnectorId()));
					deleteFile(file);
					
					//读取Menu的XML
					XMIResource menuresource = (XMIResource) new ResourceSetImpl().getResource(URI.createFileURI(ConnectorUtil.getMenuConnectorPath()), true);
					menuresource.setEncoding("UTF-8");
					Menu root = (Menu) menuresource.getContents().get(0);
					List<MenuConnector> menuConnectors = new ArrayList<MenuConnector>();
					for(Node node : root.getNode()) {
						if(node.getName().equals(connector.getCategory())) {
							for(MenuConnector menuConnector : node.getMenuConnector()) {
								if(menuConnector.getId().equals(connector.getConnectorId())) {
									menuConnectors.add(menuConnector);
								}
							}
							node.getMenuConnector().removeAll(menuConnectors);
						}
					}
					
					//保存XML
					try {
						menuresource.save(Collections.EMPTY_MAP);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					MessageDialog.openInformation(null, "提示", "连接器删除成功");
					
					//刷新树
					elements = (List<ITreeElement>) TreeViewerFactory.reloadTree();
					treeViewer.setInput(elements);
					treeViewer.refresh();
					
					//刷新工程
					ConnectorUtil.refreshProject("fixflow-expand");
				}else {
					List<Node> nodes = new ArrayList<Node>();
					boolean b = MessageDialog.openConfirm(null, "警告", "你确认要删除此目录及此目录下的所有连接器吗？");
					if (!b)
						return;
					
					
					//读取Menu的XML
					/*XMIResource menuresource = (XMIResource) new ResourceSetImpl().getResource(URI.createFileURI(ConnectorUtil.getMenuConnectorPath()), true);
					menuresource.setEncoding("UTF-8");
					Menu root = (Menu) menuresource.getContents().get(0);*/
//					Menu root = EMFUtil.getConnectorMenuConfig(ConnectorUtil.getMenuConnectorPath());
					
					ResourceSet resourceSet = new ResourceSetImpl();
					resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
							.put("xml", new XMIResourceFactoryImpl());
					Resource resource = resourceSet.getResource(
							URI.createFileURI(ConnectorUtil.getMenuConnectorPath()), true);
					// register package in local resource registry
					resourceSet.getPackageRegistry().put(
							CoreconfigPackage.eINSTANCE.getNsURI(),
							CoreconfigPackage.eINSTANCE);
					// load resource
					try {
						resource.load(null);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					Menu root = (Menu) resource.getContents()
							.get(0);
					
					for(Node node : root.getNode()) {
						if(node.getId().equals(MenuNodeId)) {
							for(MenuConnector menuConnector : node.getMenuConnector()) {
								//删除目录
								File file = new File(ConnectorUtil.getConnectorPathById(menuConnector.getId()));
								deleteFile(file);
							}
							nodes.add(node);
						}
					}
					root.getNode().removeAll(nodes);
					
					
					//保存XML
					try {
						resource.save(Collections.EMPTY_MAP);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					MessageDialog.openInformation(null, "提示", "删除成功");
					
					//刷新树
					elements = (List<ITreeElement>) TreeViewerFactory.reloadTree();
					treeViewer.setInput(elements);
					treeViewer.refresh();
					
					//刷新工程
					ConnectorUtil.refreshProject("fixflow-expand");
				}
			}
		});
		
		
		treeViewer.addFilter(filter);
		//tableViewer获取数据
		elements = (List<ITreeElement>) TreeViewerFactory.reloadTree();
		
		if(elements != null && elements.size() > 0) {
			//设置内容提供器
			treeViewer.setContentProvider(new TreeViewerContentProvider());
			
			//设置标签提供器
			treeViewer.setLabelProvider(new TreeViewerLabelProvider());
			
			//设置内容
			treeViewer.setInput(elements);
		}
		
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				//根据所选的连接器的id动态的加载向导页所需要的页面
				IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
				ITreeElement selectElement = (ITreeElement) selection.getFirstElement();
				
				if (treeViewer.getExpandedState(selectElement)) {
					// 如果展开就收起
					treeViewer.collapseToLevel(selectElement, 1);
				} else {
					// 展现下一层文件夹
					treeViewer.expandToLevel(selectElement, 1);
				}
			}
		});
		
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				//根据所选的连接器的id动态的加载向导页所需要的页面
				IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
				ITreeElement element = (ITreeElement) selection.getFirstElement();
				
				//改变显示规则
				if(element != null && !element.getRealPath().equals("")) {
					deleteButton.setEnabled(true);
					
					setPageComplete(true); 
					
					//先将之前加入的删除掉
					if(editConnectorWizard.getPageCount() > 2) {
						for (int i = 2; i < editConnectorWizard.getPageCount();) {
							editConnectorWizard.removePage(i);							
						}
					}
					
					//获取该connetor
					connector = TreeViewerFactory.getConnector(element.getId());
					
					editConnectorWizard.getChooseConnectorFileToEditWizardPage().setConnector(connector);
					
					editConnectorWizard.getChooseConnectorFileToEditWizardPage().getCheckboxTreeViewer().setInput(editConnectorWizard.getChooseConnectorFileToEditWizardPage().getFilesInConnectorPath());
					
				}
				else if(element != null && element.getRealPath().equals("")) {
					connector = null;
					MenuNodeId = element.getId();
					deleteButton.setEnabled(true);
					setPageComplete(false); 
				}
				else {
					deleteButton.setEnabled(false);
					setPageComplete(false); 
				}
			}
		});
		
		//初始化的时候先使‘完成’按钮不可用
		setPageComplete(false); 
		
		//必须要的，将新的Composite放入
		setControl(container);
	}

	/**
	 * 删除文件夹
	 * @param file
	 */
	private void deleteFile(File file){ 
		   if(file.exists()){                    		//判断文件是否存在
		    if(file.isFile()){                    		//判断是否是文件
		     file.delete();                       		//delete()方法 你应该知道 是删除的意思;
		    }else if(file.isDirectory()){               //否则如果它是一个目录
		     File files[] = file.listFiles();           //声明目录下所有的文件 files[];
		     for(int i=0;i<files.length;i++){           //遍历目录下所有的文件
		      this.deleteFile(files[i]);             	//把每个文件 用这个方法进行迭代
		     } 
		    } 
		    file.delete(); 
		   }else{ 
		    System.out.println("所删除的文件不存在！"+'\n'); 
		   } 
		} 
	
	public Connector getConnector() {
		return connector;
	}

	public void setConnector(Connector connector) {
		this.connector = connector;
	}
}
