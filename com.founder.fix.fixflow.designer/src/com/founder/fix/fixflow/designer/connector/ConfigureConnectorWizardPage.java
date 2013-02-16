package com.founder.fix.fixflow.designer.connector;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.ui.wizards.NewTypeWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.ui.PlatformUI;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerCell;

import com.founder.fix.bpmn2extensions.connector.Connector;
import com.founder.fix.bpmn2extensions.connector.ConnectorFactory;
import com.founder.fix.bpmn2extensions.connector.ConnectorPackage;
import com.founder.fix.bpmn2extensions.connector.InputParameter;
import com.founder.fix.bpmn2extensions.connector.OutputParameter;
import com.founder.fix.bpmn2extensions.connector.Outputs;
import com.founder.fix.bpmn2extensions.connector.Page;
import com.founder.fix.bpmn2extensions.connector.Pages;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.emf.databinding.EMFObservables;
import com.founder.fix.bpmn2extensions.connector.ConnectorPackage.Literals;
import com.founder.fix.bpmn2extensions.connectormenu.ConnectormenuFactory;
import com.founder.fix.bpmn2extensions.connectormenu.Menu;
import com.founder.fix.bpmn2extensions.connectormenu.Node;
import com.founder.fix.bpmn2extensions.connectormenu.impl.NodeImpl;
import com.founder.fix.bpmn2extensions.variableconfig.DataTypeDef;
import com.founder.fix.fixflow.designer.util.ConnectorUtil;
import com.founder.fix.fixflow.designer.util.DataVarUtil;
import com.founder.fix.fixflow.designer.util.EMFUtil;

import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.LabelProvider;

public class ConfigureConnectorWizardPage extends NewTypeWizardPage {
	private DataBindingContext m_bindingContext;
	private Text connectorclassnametext;
	private Text connectorpackagenametext;
	private Table pagetable;
	private Table outputtable;
	private TableViewer pagetableViewer;
	private TableViewer outputtableViewer;
	private Text connectoridtext;
	private Text connectordesctext;
	private Combo connectortypecombo;
	private ComboViewer comboViewer;
	private Button connectoriconButton;
	private String iconPath;
	private Connector connector;
	// Custom category
	private String customCategoryIconPath;
	private String customCategoryName;
	private String categoryId;
	
	private Node node;
	private Composite composite;
	private Composite customCategoryComposite;
	private Text connectornametext;
	private List<Node> nodelist;
	private Button pagecreateButton;
	private Button pageeditButton;
	private Button pageupButton;
	private Button pagedownButton;
	private Button pagedeleteButton;
	private Button outputcreateButton;
	private Button outputupButton;
	private Button outputdownButton;
	private Button outputdeleteButton;
	private Text categoryNameText;
	private boolean hassamecat = false;
	private ConnectorFactory factory;
	private List<Page> pages;
	private String openType;
	private FileInputStream is;

	/**
	 * 构造函数
	 * @param isClass
	 * @param pageName
	 * @wbp.parser.constructor
	 */
	public ConfigureConnectorWizardPage(boolean isClass, String pageName) {
		super(isClass, pageName);
		// TODO Auto-generated constructor stub
		setDescription("设置连接器的描述信息");
		setTitle("创建连接器");
		// Initialize the model
		ConnectorPackage.eINSTANCE.eClass();
		// Retrieve the default factory singleton
		ConnectorFactory factory = ConnectorFactory.eINSTANCE;
		Connector connector = factory.createConnector();
		Pages pages = factory.createPages();
		Outputs outputs = factory.createOutputs();
		connector.setPages(pages);
		connector.setOutputs(outputs);
		this.connector = connector;
		this.factory = factory;
		
		ConnectormenuFactory menufactory = ConnectormenuFactory.eINSTANCE;
		Node node = menufactory.createNode();
		this.node = node;
		this.node.setName("");
		
		this.pages = new ArrayList<Page>();
		
		//读取Menu的xml
	/*	XMIResource menuresource = (XMIResource) new ResourceSetImpl().getResource(URI.createFileURI(ConnectorUtil.getMenuConnectorPath()), true);
		Menu menu = (Menu) menuresource.getContents().get(0);*/
		Menu menu = EMFUtil.getConnectorMenuConfig(ConnectorUtil.getMenuConnectorPath());
		nodelist = new ArrayList<Node>();
		for(Node nd : menu.getNode()){
			if(nd.getName() != null)
			nodelist.add(nd);
		}
		
		this.openType = "create";
	}
	
	/**
	 * 修改时构造函数
	 * @param isClass
	 * @param pageName
	 * @param connector
	 */
	public ConfigureConnectorWizardPage(boolean isClass, String pageName, Connector connector) {
		super(isClass, pageName);
		// TODO Auto-generated constructor stub
		setDescription("设置连接器的描述信息");
		setTitle("编辑连接器");
		// Initialize the model
		ConnectorPackage.eINSTANCE.eClass();
		// Retrieve the default factory singleton
		ConnectorFactory factory = ConnectorFactory.eINSTANCE;
		this.connector = connector;
		this.factory = factory;
		this.pages = connector.getPages().getPage();
		
		//读取Menu的xml
	/*	XMIResource menuresource = (XMIResource) new ResourceSetImpl().getResource(URI.createFileURI(ConnectorUtil.getMenuConnectorPath()), true);
		Menu menu = (Menu) menuresource.getContents().get(0);*/
		Menu menu = EMFUtil.getConnectorMenuConfig(ConnectorUtil.getMenuConnectorPath());
		nodelist = new ArrayList<Node>();
		for(Node nd : menu.getNode()){
			if(nd.getName() != null)
			nodelist.add(nd);
			if(nd.getName().equals(connector.getCategory())) {
				this.node = nd;
			}
		}
		
		this.openType = "edit";
	}

	@Override
	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		Composite mainComposite = new Composite(parent, SWT.NONE);
		mainComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		composite = new Composite(mainComposite, SWT.NONE);
		GridLayout gl_composite = new GridLayout(4, false);
		gl_composite.marginRight = 10;
		gl_composite.marginHeight = 10;
		gl_composite.marginLeft = 10;
		composite.setLayout(gl_composite);
		
		Label connectoridLabel = new Label(composite, SWT.NONE);
		connectoridLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		connectoridLabel.setText("连接器ID");
		
		connectoridtext = new Text(composite, SWT.BORDER);
		connectoridtext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
		
		connectoridtext.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				connectorclassnametext.setText(connectoridtext.getText());
				connectorpackagenametext.setText("com.founder.fix.fixflow.expand.connector" + "." + connectoridtext.getText());
				setPageComplete(isPageComplete());
			}
		});
		
		Label connectornameLabel = new Label(composite, SWT.NONE);
		connectornameLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		connectornameLabel.setText("名称");
		
		connectornametext = new Text(composite, SWT.BORDER);
		connectornametext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
		
		connectornametext.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				setPageComplete(isPageComplete());
			}
		});
		
		Label connectordescLabel = new Label(composite, SWT.NONE);
		connectordescLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		connectordescLabel.setText("描述");
		
		connectordesctext = new Text(composite, SWT.BORDER);
		connectordesctext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
		
		connectordesctext.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				setPageComplete(isPageComplete());
			}
		});
		
		Label connectortypeLabel = new Label(composite, SWT.NONE);
		connectortypeLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		connectortypeLabel.setText("分类");
		
		comboViewer = new ComboViewer(composite, SWT.READ_ONLY);
		connectortypecombo = comboViewer.getCombo();
		GridData gd_connectortypecombo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_connectortypecombo.widthHint = 88;
		connectortypecombo.setLayoutData(gd_connectortypecombo);
		comboViewer.setLabelProvider(new ViewerLabelProvider());
		comboViewer.setContentProvider(new ContentProvider());
		comboViewer.setInput(nodelist);
		
		customCategoryComposite = createCustomCategoryComposite(composite);
		new Label(composite, SWT.NONE);
		
		if(connector.getCategory() == null) {
			//默认选择创建
			Node createnode = nodelist.get(nodelist.size()-1);
			comboViewer.setSelection(new StructuredSelection(createnode));
		}else{
			for(Node node : nodelist) {
				if(node.getName().equals(connector.getCategory())) {
					comboViewer.setSelection(new StructuredSelection(node));
					customCategoryComposite.setVisible(false);
				}
			}
		}
		
		Label connectoriconLabel = new Label(composite, SWT.NONE);
		connectoriconLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		connectoriconLabel.setText("图标");
		
		connectoriconButton = new Button(composite, SWT.NONE);
		GridData gd_connectoriconButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1);
		gd_connectoriconButton.widthHint = 43;
		connectoriconButton.setLayoutData(gd_connectoriconButton);
		connectoriconButton.setText("...");
		connectoriconButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				FileDialog dialog = new FileDialog(getShell(), SWT.OPEN | SWT.SINGLE);
				dialog.setFilterExtensions(new String[] { "*.jpg;*.jpeg;*.gif;*.png;*.bmp;*.ico" });
				dialog.setFilterPath(System.getProperty("user.home"));
				String res = dialog.open();
				if (res != null) {
					ConfigureConnectorWizardPage.this.iconPath = res;
					try {
						connectoriconButton.setText("");
						is = new FileInputStream(res);
						if (connectoriconButton.getImage() != null && !connectoriconButton.getImage().isDisposed()) {
							connectoriconButton.getImage().dispose();
						}
						connectoriconButton.setImage(new Image(PlatformUI.getWorkbench().getDisplay(), new ImageData(is).scaledTo(16, 16)));
						is.close();
					} catch (Exception ex) {
						connectoriconButton.setImage(null);
						connectoriconButton.setText("...");
					}
					connector.setIcon(ConnectorUtil.getConnectorMenuIconName(res));
				}
			}
		});
		
		//编辑时显示图片
		if(connector.getIcon() != null) {
			try {
				connectoriconButton.setText("");
				is = new FileInputStream(ConnectorUtil.getConnectorIconPathByIconName(connector.getConnectorId(), connector.getIcon()));
				if (connectoriconButton.getImage() != null && !connectoriconButton.getImage().isDisposed()) {
					connectoriconButton.getImage().dispose();
				}
				connectoriconButton.setImage(new Image(PlatformUI.getWorkbench().getDisplay(), new ImageData(is).scaledTo(16, 16)));
				is.close();
			} catch (Exception ex) {
				connectoriconButton.setImage(null);
				connectoriconButton.setText("...");
			}
		}
		
		comboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
		public void selectionChanged(SelectionChangedEvent event) {
			if(comboViewer != null
					&& comboViewer.getSelection() != null
					&& ((IStructuredSelection)comboViewer.getSelection()).getFirstElement() != null) {
				ConfigureConnectorWizardPage.this.categoryId = ((NodeImpl)((IStructuredSelection)comboViewer.getSelection()).getFirstElement()).getName();
				ConfigureConnectorWizardPage.this.node.setName(ConfigureConnectorWizardPage.this.categoryId);
				customCategoryComposite.setVisible(ConfigureConnectorWizardPage.this.categoryId.equals("创建..."));
			}
			connector.setCategory(categoryId);
			setPageComplete(isPageComplete());
		}
	});
		
		Label connectorclassnameLabel = new Label(composite, SWT.NONE);
		connectorclassnameLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		connectorclassnameLabel.setText("类名");
		
		connectorclassnametext = new Text(composite, SWT.BORDER);
		connectorclassnametext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
		
		//如果是编辑时，不允许更改ID，防止保存时验证ID唯一时麻烦
		if(openType.equals("edit")) {
			connectoridtext.setEnabled(false);
			connectorclassnametext.setEnabled(false);
			comboViewer.getCombo().setEnabled(false);
		}
		
		Label connectorpackagenameLabel = new Label(composite, SWT.NONE);
		connectorpackagenameLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		connectorpackagenameLabel.setText("包名");
		
		connectorpackagenametext = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		connectorpackagenametext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		
		Button connectorpakageButton = new Button(composite, SWT.NONE);
		connectorpakageButton.setEnabled(false);
		connectorpakageButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		connectorpakageButton.setText("浏览");
		connectorpakageButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				IPackageFragment selectedPackage = ConfigureConnectorWizardPage.this.choosePackage();
				if (selectedPackage != null) {
					connectorpackagenametext.setText(selectedPackage.getElementName());
				}
			}
		});
		new Label(composite, SWT.NONE);
		
		Label connectorpagesLabel = new Label(composite, SWT.NONE);
		connectorpagesLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		connectorpagesLabel.setText("指定连接器的页数");
		
		Label connectorpageLabel = new Label(composite, SWT.NONE);
		connectorpageLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		connectorpageLabel.setText("页");
		
		pagetableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		pagetableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				updateButtons();
			}
		});
		
		pagetable = pagetableViewer.getTable();
		pagetable.setLinesVisible(true);
		pagetable.setHeaderVisible(true);
		pagetable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 2, 1));
		
		pagetable.addListener(SWT.MeasureItem, new Listener() {
			public void handleEvent(Event event) {
				// 设置行高度
				event.height = (int) Math.floor(event.gc.getFontMetrics().getHeight() * 1.5);
			}
		});
		
		TableViewerColumn idcolumn = new TableViewerColumn(pagetableViewer, SWT.NONE);
		idcolumn.getColumn().setWidth(100);
		idcolumn.getColumn().setText("页面ID");
		idcolumn.getColumn().setMoveable(true);
		idcolumn.setLabelProvider(new CellLabelProvider() {
			
			@Override
			public void update(ViewerCell cell) {
				// TODO Auto-generated method stub
				cell.setText(((Page)cell.getElement()).getPageId());
			}
		});
		
		TableViewerColumn titlecolumn = new TableViewerColumn(pagetableViewer, SWT.NONE);
		titlecolumn.getColumn().setWidth(200);
		titlecolumn.getColumn().setText("页面标题");
		titlecolumn.getColumn().setMoveable(true);
		titlecolumn.setLabelProvider(new CellLabelProvider() {
			
			@Override
			public void update(ViewerCell cell) {
				// TODO Auto-generated method stub
				cell.setText(((Page)cell.getElement()).getPageTitle());
			}
		});
		
		TableViewerColumn numcolumn = new TableViewerColumn(pagetableViewer, SWT.NONE);
		numcolumn.getColumn().setWidth(100);
		numcolumn.getColumn().setText("输入的个数");
		numcolumn.getColumn().setMoveable(true);
		numcolumn.setLabelProvider(new CellLabelProvider() {
			
			@Override
			public void update(ViewerCell cell) {
				// TODO Auto-generated method stub
				cell.setText(((Page)cell.getElement()).getInputParameter().size() + "");
			}
		});
		
		pagetableViewer.setContentProvider(new ArrayContentProvider());
		pagetableViewer.setInput(pages);
		
		Composite pagecomposite = new Composite(composite, SWT.NONE);
		GridLayout gl_pagecomposite = new GridLayout(1, false);
		gl_pagecomposite.verticalSpacing = 0;
		gl_pagecomposite.horizontalSpacing = 0;
		gl_pagecomposite.marginWidth = 0;
		gl_pagecomposite.marginHeight = 0;
		pagecomposite.setLayout(gl_pagecomposite);
		pagecomposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		pagecreateButton = new Button(pagecomposite, SWT.NONE);
		pagecreateButton.setText("创建");
		pagecreateButton.addListener(SWT.Selection, new Listener() {
			@SuppressWarnings("unchecked")
			public void handleEvent(Event event) {
				CreatePageDialog cpd = new CreatePageDialog(getShell(), connector.getOutputs());
				cpd.setBlockOnOpen(true);
				if(cpd != null && cpd.open() == InputDialog.OK){
					connector.getPages().getPage().add(cpd.getPage());
					((List<Page>)pagetableViewer.getInput()).add(cpd.getPage());
					pagetableViewer.refresh();
					setPageComplete(isPageComplete());
					updateButtons();
				}
			}
		});
		
		pageeditButton = new Button(pagecomposite, SWT.NONE);
		pageeditButton.setEnabled(false);
		pageeditButton.setText("编辑");
		pageeditButton.addListener(SWT.Selection, new Listener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if(!pagetableViewer.getSelection().isEmpty()){
					IStructuredSelection selection = (IStructuredSelection) pagetableViewer.getSelection();
					Page page  = (Page) selection.getFirstElement();
					CreatePageDialog cpd = new CreatePageDialog(getShell(), page, connector.getOutputs());
					cpd.setBlockOnOpen(true);
					if(cpd != null && cpd.open() == InputDialog.OK){
						connector.getPages().getPage().remove(page);
						connector.getPages().getPage().add(cpd.getPage());
						int index = ((List<Page>)pagetableViewer.getInput()).indexOf(page);
						((List<Page>)pagetableViewer.getInput()).remove(page);
						((List<Page>)pagetableViewer.getInput()).add(index, cpd.getPage());
						pagetableViewer.refresh();
						setPageComplete(isPageComplete());
					}
				}
			}
		});
		
		pageupButton = new Button(pagecomposite, SWT.NONE);
		pageupButton.setText("向上");
		pageupButton.addListener(SWT.Selection, new Listener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				IStructuredSelection selection = (IStructuredSelection) pagetableViewer.getSelection();
				Page page = (Page) selection.getFirstElement();
				int idx = ((List<Page>)pagetableViewer.getInput()).indexOf(page);
				if (idx != 0) {
					((List<Page>)pagetableViewer.getInput()).remove(page);
					((List<Page>)pagetableViewer.getInput()).add(idx - 1, page);
				}
				pagetableViewer.refresh();
				if(selection != null)
					pagetableViewer.setSelection(selection);
				updateButtons();
			}
		});
		
		pagedownButton = new Button(pagecomposite, SWT.NONE);
		pagedownButton.setText("向下");
		pagedownButton.addListener(SWT.Selection, new Listener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				IStructuredSelection selection = (IStructuredSelection) pagetableViewer.getSelection();
				Page pageTo = (Page) selection.getFirstElement();
				int idx = ((List<Page>)pagetableViewer.getInput()).indexOf(pageTo);
				if (idx != ((List<Page>)pagetableViewer.getInput()).size() - 1) {
					((List<Page>)pagetableViewer.getInput()).remove(pageTo);
					((List<Page>)pagetableViewer.getInput()).add(idx + 1, pageTo);
				}
				pagetableViewer.refresh();
				if(selection != null)
					pagetableViewer.setSelection(selection);
				updateButtons();
			}
		});
		
		pagedeleteButton = new Button(pagecomposite, SWT.NONE);
		pagedeleteButton.setText("移除");
		pagedeleteButton.addListener(SWT.Selection, new Listener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				/*if(!pagetableViewer.getSelection().isEmpty()){
					IStructuredSelection selection = (IStructuredSelection) pagetableViewer.getSelection();
					Page page  = (Page) selection.getFirstElement();
					connector.getPages().getPage().remove(page);
					((List<Page>)pagetableViewer.getInput()).remove(page);
					pagetableViewer.refresh();
					setPageComplete(isPageComplete());
					updateButtons();
				}*/
				ISelection sel = pagetableViewer.getSelection();
				if (sel == null)
					return;
				Object[] objs = ((IStructuredSelection) sel).toArray();
				if (objs == null || objs.length == 0)
					return;
				boolean b = MessageDialog.openConfirm(null, "警告", "你确认要删除吗？");
				if (!b)
					return;

				for (int i = 0; i < objs.length; i++) {
					Page col = (Page) objs[i];
					((List<Page>)pagetableViewer.getInput()).remove(col);
					connector.getPages().getPage().remove(col);
				}
				pagetableViewer.refresh();
				setPageComplete(isPageComplete());
				updateButtons();
			}
		});
		
		Label connectoroutputLabel = new Label(composite, SWT.NONE);
		connectoroutputLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		connectoroutputLabel.setText("输出");
		
		outputtableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		outputtableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				updateButtons();
			}
		});
		
		outputtable = outputtableViewer.getTable();
		outputtable.setLinesVisible(true);
		outputtable.setHeaderVisible(true);
		GridData gd_outputtable = new GridData(SWT.FILL, SWT.FILL, false, true, 2, 1);
		gd_outputtable.heightHint = 90;
		outputtable.setLayoutData(gd_outputtable);
		
		outputtable.addListener(SWT.MeasureItem, new Listener() {
			public void handleEvent(Event event) {
				// 设置行高度
				event.height = (int) Math.floor(event.gc.getFontMetrics().getHeight() * 1.5);
			}
		});
		
		TableColumn fieldcolumn = new TableColumn(outputtable, SWT.NONE);
		fieldcolumn.setWidth(100);
		fieldcolumn.setText("字段名");
		fieldcolumn.setMoveable(true);
		
		TableColumn displaycolumn = new TableColumn(outputtable, SWT.NONE);
		displaycolumn.setWidth(200);
		displaycolumn.setText("显示名");
		displaycolumn.setMoveable(true);
		
		TableColumn datatypecolumn = new TableColumn(outputtable, SWT.NONE);
		datatypecolumn.setWidth(100);
		datatypecolumn.setText("数据类型");
		datatypecolumn.setMoveable(true);
		
		createCellModifierOutput();
		
		Composite outputcomposite = new Composite(composite, SWT.NONE);
		GridLayout gl_outputcomposite = new GridLayout(1, false);
		gl_outputcomposite.verticalSpacing = 0;
		gl_outputcomposite.horizontalSpacing = 0;
		gl_outputcomposite.marginHeight = 0;
		gl_outputcomposite.marginWidth = 0;
		outputcomposite.setLayout(gl_outputcomposite);
		outputcomposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		outputcreateButton = new Button(outputcomposite, SWT.NONE);
		outputcreateButton.setText("创建");
		outputcreateButton.addListener(SWT.Selection, new Listener() {
			
			@SuppressWarnings("rawtypes")
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				
				// Initialize the model
				ConnectorPackage.eINSTANCE.eClass();
				// Retrieve the default factory singleton
				ConnectorFactory factory = ConnectorFactory.eINSTANCE;
				// Create the content of the model via this program
				OutputParameter outputParameter = factory.createOutputParameter();
				
				if(connector.getOutputs() == null){
					Outputs outputs = factory.createOutputs();
					connector.setOutputs(outputs);
				}
				
				outputParameter.setId("outputfield" + ((List)outputtableViewer.getInput()).size());
				outputParameter.setName("name" + ((List)outputtableViewer.getInput()).size());
				outputParameter.setDataType("java.lang.String");
			
				connector.getOutputs().getOutputParameter().add(outputParameter);
				
				outputtableViewer.refresh();
				outputtableViewer.editElement(outputParameter, 0);
				setPageComplete(isPageComplete());
				updateButtons();
			}
		});
		
		outputupButton = new Button(outputcomposite, SWT.NONE);
		outputupButton.setEnabled(false);
		outputupButton.setText("向上");
		outputupButton.addListener(SWT.Selection, new Listener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				IStructuredSelection selection = (IStructuredSelection) outputtableViewer.getSelection();
				OutputParameter outputParameter = (OutputParameter) selection.getFirstElement();
				int idx = ((List<OutputParameter>)outputtableViewer.getInput()).indexOf(outputParameter);
				if (idx != 0) {
					((List<OutputParameter>)outputtableViewer.getInput()).remove(outputParameter);
					((List<OutputParameter>)outputtableViewer.getInput()).add(idx - 1, outputParameter);
				}
				outputtableViewer.refresh();
				if(selection != null)
					outputtableViewer.setSelection(selection);
				updateButtons();
			}
		});
		
		outputdownButton = new Button(outputcomposite, SWT.NONE);
		outputdownButton.setEnabled(false);
		outputdownButton.setText("向下");
		outputdownButton.addListener(SWT.Selection, new Listener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				IStructuredSelection selection = (IStructuredSelection) outputtableViewer.getSelection();
				OutputParameter outputParameter = (OutputParameter) selection.getFirstElement();
				int idx = ((List<OutputParameter>)outputtableViewer.getInput()).indexOf(outputParameter);
				if (idx != ((List<OutputParameter>)outputtableViewer.getInput()).size() - 1) {
					((List<OutputParameter>)outputtableViewer.getInput()).remove(outputParameter);
					((List<OutputParameter>)outputtableViewer.getInput()).add(idx + 1, outputParameter);
				}
				outputtableViewer.refresh();
				if(selection != null)
				outputtableViewer.setSelection(selection);
				updateButtons();
			}
		});
		
		outputdeleteButton = new Button(outputcomposite, SWT.NONE);
		outputdeleteButton.setEnabled(false);
		outputdeleteButton.setText("移除");
		outputdeleteButton.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				/*if(!outputtableViewer.getSelection().isEmpty()){
					IStructuredSelection selection = (IStructuredSelection) outputtableViewer.getSelection();
					OutputParameter outputParameter  = (OutputParameter) selection.getFirstElement();
					connector.getOutputs().getOutputParameter().remove(outputParameter);
					outputtableViewer.refresh();
					setPageComplete(isPageComplete());
					updateButtons();
				}*/
				ISelection sel = outputtableViewer.getSelection();
				if (sel == null)
					return;
				Object[] objs = ((IStructuredSelection) sel).toArray();
				if (objs == null || objs.length == 0)
					return;
				boolean b = MessageDialog.openConfirm(null, "警告", "你确认要删除吗？");
				if (!b)
					return;

				for (int i = 0; i < objs.length; i++) {
					OutputParameter col = (OutputParameter) objs[i];
					connector.getOutputs().getOutputParameter().remove(col);
				}
				outputtableViewer.refresh();
				setPageComplete(isPageComplete());
				updateButtons();
			}
		});
		
		updateButtons();
		setControl(mainComposite);
		m_bindingContext = initDataBindings();
	}
	
	
	private void createCellModifierOutput() {
		final String[] sources = new String[] { "java.lang.String" };

		final CellEditor[] cellEditor = new CellEditor[outputtable.getColumnCount()];
		cellEditor[0] = new TextCellEditor(outputtable);
		cellEditor[1] = new TextCellEditor(outputtable);
//		cellEditor[2] = new ComboBoxCellEditor(outputtable, sources, SWT.READ_ONLY);
		cellEditor[2] = new ComboBoxViewerCellEditor(outputtable, SWT.READ_ONLY);
		((ComboBoxViewerCellEditor)cellEditor[2]).setContenProvider(new ArrayContentProvider());
		((ComboBoxViewerCellEditor)cellEditor[2]).setLabelProvider(new ViewerLabelProvider1());
		((ComboBoxViewerCellEditor)cellEditor[2]).setInput(DataVarUtil.getFixFlowDataVariableConfig().getDataVariableDataType().getDataTypeDef());
		
		outputtableViewer.setColumnProperties(new String[]{"PARAID", "PARANAME", "PARATYPE"});
		outputtableViewer.setCellEditors(cellEditor);
		outputtableViewer.setCellModifier(new ICellModifier() {

			public void modify(Object element, String property, Object value) {
				// TODO Auto-generated method stub
				TableItem tableitem = (TableItem) element;
				OutputParameter outputparameter = (OutputParameter) tableitem.getData();
				if (property.equals("PARAID")) {
					outputparameter.setId((String) value);
				}
				if (property.equals("PARANAME")) {
					outputparameter.setName((String) value);
				}
				if (property.equals("PARATYPE")) {
					if(value == null){
						outputparameter.setDataType("");
					}else{
						outputparameter.setDataType(((DataTypeDef)value).getTypeValue());
					}
//					outputparameter.setDataType(sources[((Integer)value).intValue()]);
				}
				setPageComplete(isPageComplete());
				outputtableViewer.refresh();
			}

			public Object getValue(Object element, String property) {
				// TODO Auto-generated method stub
				OutputParameter outputparameter = (OutputParameter) element;

				if (property.equals("PARAID")) {
					return outputparameter.getId();
				}
				if (property.equals("PARANAME")) {
					return outputparameter.getName();
				}
				if (property.equals("PARATYPE")) {
					return DataVarUtil.getDataTypeDefByDataVariableDataType(outputparameter.getDataType());
				}
				return null;
			}

			public boolean canModify(Object element, String property) {
				// TODO Auto-generated method stub
				return element instanceof OutputParameter;
			}
		});
	}

	public Connector getConnector() {
		return connector;
	}
	
	private Composite createCustomCategoryComposite(Composite parent) {
		Composite res = new Composite(parent, SWT.NONE);
		res.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		GridLayout layout = new GridLayout(4, false);
		res.setLayout(layout);
		layout.marginTop = layout.marginHeight = layout.marginBottom = 0;
		Label categoryNameLabel = new Label(res, SWT.NONE);
		categoryNameLabel.setText("名称");
		categoryNameText = new Text(res, SWT.BORDER);
		categoryNameText.setLayoutData(new GridData(140, SWT.DEFAULT));
		if(customCategoryName != null){
			categoryNameText.setText(customCategoryName);
		}
		categoryNameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				ConfigureConnectorWizardPage.this.customCategoryName = categoryNameText.getText();
				/*XMIResource menuresource = (XMIResource) new ResourceSetImpl().getResource(URI.createFileURI(ConnectorUtil.getMenuConnectorPath()), true);
				Menu menu = (Menu) menuresource.getContents().get(0);*/
				Menu menu = EMFUtil.getConnectorMenuConfig(ConnectorUtil.getMenuConnectorPath());
				if(menu.getNode().size()>0){
					for(Node node : menu.getNode()){
						if(customCategoryName.equals(node.getName())){
							hassamecat = true;
							break;
						}
					}
				}
				if(node.getName().equals("创建...")){
					ConfigureConnectorWizardPage.this.node.setName(customCategoryName);
				}else{
					ConfigureConnectorWizardPage.this.node.setId(java.util.UUID.randomUUID().toString());
					ConfigureConnectorWizardPage.this.node.setName(customCategoryName);
					hassamecat = false;
				}
				
				connector.setCategory(customCategoryName);
				setPageComplete(isPageComplete());
			}
		});
		Label customCategoryIconLabel = new Label(res, SWT.NONE);
		customCategoryIconLabel.setText("图标");
		final Button customCategoryIconButton = new Button(res, SWT.FLAT);
		customCategoryIconButton.setText("...");
		customCategoryIconButton.setLayoutData(new GridData(40, SWT.DEFAULT));
		customCategoryIconButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				FileDialog dialog = new FileDialog(getShell(), SWT.OPEN | SWT.SINGLE);
				dialog.setFilterExtensions(new String[] { "*.jpg;*.jpeg;*.gif;*.png;*.bmp;*.ico" });
				dialog.setFilterPath(System.getProperty("user.home"));
				String res = dialog.open();
				if (res != null) {
					ConfigureConnectorWizardPage.this.customCategoryIconPath = res;
					try {
						customCategoryIconButton.setText("");
						FileInputStream is = new FileInputStream(res);
						if (customCategoryIconButton.getImage() != null && !customCategoryIconButton.getImage().isDisposed()) {
							customCategoryIconButton.getImage().dispose();
						}
						customCategoryIconButton.setImage(new Image(PlatformUI.getWorkbench().getDisplay(), new ImageData(is).scaledTo(16, 16)));
						is.close();
					} catch (Exception ex) {
						customCategoryIconButton.setImage(null);
						customCategoryIconButton.setText("...");
						ex.printStackTrace();
					}
					node.setIco(ConnectorUtil.getConnectorMenuIconName(res));
				}
			}
		});

		return res;
	}

	private static class ContentProvider implements IStructuredContentProvider {
		@SuppressWarnings("unchecked")
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof List) {
				@SuppressWarnings("rawtypes")
				List list = (List) inputElement;
				Node node = ConnectormenuFactory.eINSTANCE.createNode();
				node.setName("创建...");
				if(!list.contains(node))
				list.add(node);
				return list.toArray();
			} else {
				return new Object[0];
			}
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}
	
	private static class ViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			Node treeElement = (Node) element;
			return treeElement.getName();
		}
	}
	
	private class ViewerLabelProvider1 extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}

		public String getText(Object element) {
			DataTypeDef dataTypeDef = (DataTypeDef) element;
			return dataTypeDef.getName();
		}
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}
	

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public String getCustomCategoryIconPath() {
		return customCategoryIconPath;
	}

	public void setCustomCategoryIconPath(String customCategoryIconPath) {
		this.customCategoryIconPath = customCategoryIconPath;
	}

	/**
	 * 拿到所有的输入参数
	 * @return
	 */
	public List<InputParameter> getinputParameters(){
		List<InputParameter> inputParameters = new ArrayList<InputParameter>();
		for(Page page : connector.getPages().getPage()){
			inputParameters.addAll(page.getInputParameter());
		}
		return inputParameters;
	}
	
	@Override
	public boolean isPageComplete() {
		boolean OutputNamesAreUnique = allParaNamesAreUnique(getinputParameters(), connector.getOutputs());
		boolean PageIdAreUnique = allPageIdUnique();
		
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		if(connectoridtext.getText() == null || connectoridtext.getText().equals(""))
			sb.append("连接器ID为空");
		if(connectoridtext.getText() != null && !(connectoridtext.getText().equals(""))) {
			Pattern pattern = Pattern.compile("^[A-Z][A-Za-z0-9]+$");
			Pattern pattern1 = Pattern.compile("^[A-Z]$");
			Matcher matcher = null;
			
			if(connectoridtext.getText().length()>1) {
				matcher = pattern.matcher(connectoridtext.getText());
			}else{
				matcher = pattern1.matcher(connectoridtext.getText());
			}
			
			if(!matcher.matches()){
				if(sb.length()>0)
					sb.append(",");
				sb.append("连接器ID的名称应遵循java类的命名规范\n");
			}
		}
		if(connectornametext.getText() == null || connectornametext.getText().equals("")){
			if(sb.length()>0)
				sb.append(",");
			sb.append("连接器名称为空");
		}
		if(connectorclassnametext.getText() == null || connectorclassnametext.getText().equals("")){
			if(sb.length()>0)
				sb.append(",");
			sb.append("连接器类名为空");
		}
		if(connectordesctext.getText() == null || connectordesctext.getText().equals("")){
			if(sb.length()>0)
				sb.append(",");
			sb.append("连接器描述为空");
		}
		if(customCategoryComposite.getVisible() && (categoryNameText.getText() == null || categoryNameText.getText().equals(""))){
			if(sb.length()>0)
				sb.append(",");
			sb.append("分类目录为空");
		}
		if(customCategoryComposite.getVisible() && hassamecat == true){
			if(sb.length()>0)
				sb.append(",");
			sb.append("已存在该分类名称");
		}
		if(!OutputNamesAreUnique){
			if(sb.length()>0)
				sb.append(",");
			sb.append("存在相同的字段名");
		}
		if(!PageIdAreUnique){
			if(sb.length()>0)
				sb.append(",");
			sb.append("存在相同的页面名");
		}
		
		if(sb.length()>0){
			ConfigureConnectorWizardPage.this.setErrorMessage(sb.toString());
			return false;
		}else{
			ConfigureConnectorWizardPage.this.setErrorMessage(null);
			return true;
		}
	}
	
	/**
	 * 验证页ID唯一
	 * @return
	 */
	private boolean allPageIdUnique() {
		List<Page> pageList = connector.getPages().getPage();
		Set<String> pageIds = new HashSet<String>();
		for (Page page : pageList) {
			if (pageIds.contains(page.getPageId())) {
				return false;
			} else {
				pageIds.add(page.getPageId());
			}
		}
		return true;
	}
	
	/**
	 * 验证字段唯一
	 * @return
	 */
	private boolean allParaNamesAreUnique(List<InputParameter> inputParameters, Outputs outputs) {
		Set<String> parameterNames = new HashSet<String>();
		for (InputParameter inputParameter : inputParameters) {
			if (parameterNames.contains(inputParameter.getId())) {
				return false;
			} else {
				parameterNames.add(inputParameter.getId());
			}
		}
		for (OutputParameter outputParameter : outputs.getOutputParameter()) {
			if (parameterNames.contains(outputParameter.getId())) {
				return false;
			} else {
				parameterNames.add(outputParameter.getId());
			}
		}
		return true;
	}
	
	/**
	 * 设置按钮可用性
	 */
	@SuppressWarnings("unchecked")
	public void updateButtons(){
		Object[] objs = null;
		ISelection sel = pagetableViewer.getSelection();
		if(sel != null) {
			objs = ((IStructuredSelection) sel).toArray();
		}
		Object selectedPage = ((IStructuredSelection)pagetableViewer.getSelection()).getFirstElement();
		int index = 0;
		while (selectedPage != null && pagetableViewer.getElementAt(index) != null && ! selectedPage.equals(pagetableViewer.getElementAt(index))) {
			index++;
		}
		pagedeleteButton.setEnabled(selectedPage != null);
		pageeditButton.setEnabled(selectedPage != null && objs != null && objs.length<2);
		pageupButton.setEnabled(selectedPage != null && index != 0 && objs != null && objs.length<2);
		pagedownButton.setEnabled(selectedPage != null && index != pagetableViewer.getTable().getItemCount() - 1 && objs != null && objs.length<2);
		
		if(outputtableViewer != null){
			Object[] objs1 = null;
			ISelection sel1 = outputtableViewer.getSelection();
			if(sel1 != null) {
				objs1 = ((IStructuredSelection) sel1).toArray();
			}
			Object selectedPage1 = ((IStructuredSelection)outputtableViewer.getSelection()).getFirstElement();
			index = 0;
			while (selectedPage != null && selectedPage1 != null && outputtableViewer.getElementAt(index) != null && ! selectedPage1.equals(outputtableViewer.getElementAt(index))) {
				index++;
			}
			outputdownButton.setEnabled(selectedPage1 != null && index != outputtableViewer.getTable().getItemCount() - 1 && objs1 != null && objs1.length<2) ;
			outputupButton.setEnabled(selectedPage1 != null && index != 0 && objs1 != null && objs1.length<2) ;
		}
		
		Object[] objs1 = null;
		ISelection sel1 = outputtableViewer.getSelection();
		if(sel1 != null) {
			objs1 = ((IStructuredSelection) sel1).toArray();
		}
		OutputParameter outputparameter = (OutputParameter) ((IStructuredSelection)outputtableViewer.getSelection()).getFirstElement();
		index = -1;
		if(outputparameter!=null) {
			index = ((List<OutputParameter>)outputtableViewer.getInput()).indexOf(outputparameter);
		}
		if (outputdeleteButton != null) {
			outputdeleteButton.setEnabled(outputparameter != null);
		}
		if(outputdownButton != null){
			outputdownButton.setEnabled(outputparameter!= null && index < ((List<OutputParameter>)outputtableViewer.getInput()).size()-1 && objs1 != null && objs1.length<2);
		}
		if(outputupButton != null){
			outputupButton.setEnabled(outputparameter!= null && index > 0 && objs1 != null && objs1.length<2);
		}
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider_1 = new ObservableListContentProvider();
		outputtableViewer.setContentProvider(listContentProvider_1);
		//
		IObservableMap[] observeMaps_1 = EMFObservables.observeMaps(listContentProvider_1.getKnownElements(), new EStructuralFeature[]{Literals.OUTPUT_PARAMETER__ID, Literals.OUTPUT_PARAMETER__NAME, Literals.OUTPUT_PARAMETER__DATA_TYPE});
		outputtableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps_1));
		//
		IObservableList connectorOutputParameterObserveList = EMFProperties.list(FeaturePath.fromList(Literals.CONNECTOR__OUTPUTS, Literals.OUTPUTS__OUTPUT_PARAMETER)).observe(connector);
		outputtableViewer.setInput(connectorOutputParameterObserveList);
		//
		IObservableValue connectoridtextObserveTextObserveWidget = SWTObservables.observeText(connectoridtext, SWT.Modify);
		IObservableValue connectorConnectorIdObserveValue = EMFObservables.observeValue(connector, Literals.CONNECTOR__CONNECTOR_ID);
		bindingContext.bindValue(connectoridtextObserveTextObserveWidget, connectorConnectorIdObserveValue, null, null);
		//
		IObservableValue connectordesctextObserveTextObserveWidget = SWTObservables.observeText(connectordesctext, SWT.Modify);
		IObservableValue connectorConnectorNoteObserveValue = EMFObservables.observeValue(connector, Literals.CONNECTOR__CONNECTOR_NOTE);
		bindingContext.bindValue(connectordesctextObserveTextObserveWidget, connectorConnectorNoteObserveValue, null, null);
		//
		IObservableValue connectorclassnametextObserveTextObserveWidget = SWTObservables.observeText(connectorclassnametext, SWT.Modify);
		IObservableValue connectorClassNameObserveValue = EMFObservables.observeValue(connector, Literals.CONNECTOR__CLASS_NAME);
		bindingContext.bindValue(connectorclassnametextObserveTextObserveWidget, connectorClassNameObserveValue, null, null);
		//
		IObservableValue connectorpackagenametextObserveTextObserveWidget = SWTObservables.observeText(connectorpackagenametext, SWT.Modify);
		IObservableValue connectorPackageNameObserveValue = EMFObservables.observeValue(connector, Literals.CONNECTOR__PACKAGE_NAME);
		bindingContext.bindValue(connectorpackagenametextObserveTextObserveWidget, connectorPackageNameObserveValue, null, null);
		//
		IObservableValue connectornametextObserveTextObserveWidget = SWTObservables.observeText(connectornametext, SWT.Modify);
		IObservableValue connectorConnectorNameObserveValue = EMFObservables.observeValue(connector, Literals.CONNECTOR__CONNECTOR_NAME);
		bindingContext.bindValue(connectornametextObserveTextObserveWidget, connectorConnectorNameObserveValue, null, null);
		//
		return bindingContext;
	}

	public String getOpenType() {
		return openType;
	}

	public FileInputStream getIs() {
		return is;
	}
}
