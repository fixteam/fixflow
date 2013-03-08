/**
 * 
 */
package com.founder.fix.fixflow.designer.modeler.ui.property.connectors.dialog;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.founder.fix.bpmn2extensions.connector.Connector;
import com.founder.fix.bpmn2extensions.connector.OutputParameter;
import com.founder.fix.bpmn2extensions.connector.Page;
import com.founder.fix.fixflow.designer.modeler.ui.property.connectors.tree.ITreeElement;
import com.founder.fix.fixflow.designer.modeler.ui.property.connectors.tree.TreeViewerContentProvider;
import com.founder.fix.fixflow.designer.modeler.ui.property.connectors.tree.TreeViewerFactory;
import com.founder.fix.fixflow.designer.modeler.ui.property.connectors.tree.TreeViewerLabelProvider;

/**
 * @author wangzhiwei
 *
 */
public class SelectConnectorWizardPage extends WizardPage {
	
	private ConnectorFilter filter;
	
	private TreeViewer viewer;
	
	private AddConnectorWizard wizard;
	
	private CommonConnectorWizardPage[] commonConnectotWizardPages;
	
	private CommonConnectorWizardPage commonConnectotWizardPage;
	
	private OutputConnectorWizardPage outputConnectorWizardPage;
	
	private Connector connector;

	/**
	 * @param pageName
	 */
	public SelectConnectorWizardPage(String pageName) {
		super(pageName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param pageName
	 * @param title
	 * @param titleImage
	 */
	public SelectConnectorWizardPage(String pageName, String title,
			ImageDescriptor titleImage, AddConnectorWizard wizard) {
		super(pageName, title, titleImage);
		this.wizard = wizard;
		
		//title信息
		setTitle(title);
		
		//消息信息
		setMessage("选择您想加入的连接器", INFORMATION);
		
		//设置图片
		setImageDescriptor(titleImage);
		
		filter = new ConnectorFilter();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		//创建一个底层的Composite，并使用GridLayout布局
		Composite newComposite = new Composite(parent, SWT.NULL);
		newComposite.setLayout(new FillLayout());

		Composite client = new Composite(newComposite, SWT.NULL);
		GridLayout layoutClient = new GridLayout();
		layoutClient.numColumns = 2; //grid分为2列
		client.setLayout(layoutClient);

		Label searchLabel = new Label(client, SWT.NONE);
		searchLabel.setText("搜索: ");
		
		final Text searchText = new Text(client, SWT.BORDER | SWT.SEARCH);
		searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		
		searchText.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				filter.setSearchText(searchText.getText());
				
				viewer.refresh();
				//展现第二层
				viewer.expandToLevel(2);
				
				if(viewer.getSelection().isEmpty()) {
					setPageComplete(false); 
				}
			}
		});
		
		//占位
		new Label(client, SWT.NONE);

		//创建TreeViewer，将其放入composite中
		viewer = new TreeViewer(client, SWT.BORDER);
		viewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		viewer.addFilter(filter);
		
		//tableViewer获取数据
		List<ITreeElement> elements = (List<ITreeElement>) TreeViewerFactory.reloadTree();
		
		if(elements != null && elements.size() > 0) {
			//设置内容提供器
			viewer.setContentProvider(new TreeViewerContentProvider());
			
			//设置标签提供器
			viewer.setLabelProvider(new TreeViewerLabelProvider());
			
			//设置内容
			viewer.setInput(elements);
		}
		
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				//根据所选的连接器的id动态的加载向导页所需要的页面
				IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
				ITreeElement selectElement = (ITreeElement) selection.getFirstElement();
				
				if (viewer.getExpandedState(selectElement)) {
					// 如果展开就收起
					viewer.collapseToLevel(selectElement, 1);
				} else {
					// 展现下一层文件夹
					viewer.expandToLevel(selectElement, 1);
				}
			}
		});
		
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				//根据所选的连接器的id动态的加载向导页所需要的页面
				IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
				ITreeElement element = (ITreeElement) selection.getFirstElement();
				
				//改变显示规则
				if(element != null && !element.getRealPath().equals("")) {
					setPageComplete(true); 
					
					//先将之前加入的删除掉
					if(wizard.getPageCount() > 2) {
						for (int i = 2; i < wizard.getPageCount();) {
							wizard.removePage(i);							
						}
					}
					
					//获取该connetor
					connector = TreeViewerFactory.getConnector(element.getId());
					
					if(connector != null) {
						//获取所有的page
						EList<Page> pages = connector.getPages().getPage();
						if(pages != null && pages.size() > 0) {
							commonConnectotWizardPages = new CommonConnectorWizardPage[pages.size()];
							
							for (int i = 0; i < pages.size(); i++) {
								Page page = pages.get(i);
								commonConnectotWizardPage = new CommonConnectorWizardPage("commonConnectotWizardPage", 
										page.getPageTitle(), page.getPageNote(), null, page);
								wizard.addPage(commonConnectotWizardPage);
								
								commonConnectotWizardPages[i] = commonConnectotWizardPage;
							}
						} 
						
						//获取所有的输出参数
						if(connector.getOutputs() != null) {
							EList<OutputParameter> outputs = connector.getOutputs().getOutputParameter();
							outputConnectorWizardPage = new OutputConnectorWizardPage("outputConnectorWizardPage",
									"输出配置", "映射连接器的输出到流程变量中", null, outputs);
							wizard.addPage(outputConnectorWizardPage);
						}
					}
				} else {
					setPageComplete(false); 
				}
//				MessageDialog.openInformation(getShell(), null, wizard.getPages().length + "");
			}
		});
		
		//初始化的时候先使‘完成’按钮不可用
		setPageComplete(false); 
		
		//必须要的，将新的Composite放入
		setControl(newComposite);
	}

	public CommonConnectorWizardPage getCommonConnectotWizardPage() {
		return commonConnectotWizardPage;
	}

	public void setCommonConnectotWizardPage(
			CommonConnectorWizardPage commonConnectotWizardPage) {
		this.commonConnectotWizardPage = commonConnectotWizardPage;
	}

	public OutputConnectorWizardPage getOutputConnectorWizardPage() {
		return outputConnectorWizardPage;
	}

	public void setOutputConnectorWizardPage(
			OutputConnectorWizardPage outputConnectorWizardPage) {
		this.outputConnectorWizardPage = outputConnectorWizardPage;
	}

	public CommonConnectorWizardPage[] getCommonConnectotWizardPages() {
		return commonConnectotWizardPages;
	}

	public void setCommonConnectotWizardPages(
			CommonConnectorWizardPage[] commonConnectotWizardPages) {
		this.commonConnectotWizardPages = commonConnectotWizardPages;
	}

	public Connector getConnector() {
		return connector;
	}

	public void setConnector(Connector connector) {
		this.connector = connector;
	}

	@Override
	public IWizardPage getPreviousPage() {
		// TODO Auto-generated method stub
		return null;
	}
}
