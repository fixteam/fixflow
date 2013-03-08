package com.founder.fix.fixflow.designer.fixflowconfig;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.founder.fix.bpmn2extensions.coreconfig.AssignPolicy;
import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigFactory;
import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage.Literals;
import com.founder.fix.bpmn2extensions.coreconfig.DBType;
import com.founder.fix.bpmn2extensions.coreconfig.DataBase;
import com.founder.fix.bpmn2extensions.coreconfig.ExpandClass;
import com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig;
import com.founder.fix.bpmn2extensions.coreconfig.GroupDefinition;
import com.founder.fix.bpmn2extensions.coreconfig.GroupInfo;
import com.founder.fix.bpmn2extensions.coreconfig.MailInfo;
import com.founder.fix.bpmn2extensions.coreconfig.Priority;
import com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef;
import com.founder.fix.fixflow.designer.modeler.ui.common.FixFlowInputCellEditor;
import com.founder.fix.fixflow.designer.util.ConnectorUtil;
import com.founder.fix.fixflow.designer.util.EMFUtil;
import com.founder.fix.fixflow.designer.util.FixFlowConfigUtil;
import org.eclipse.swt.widgets.Text;
import org.eclipse.core.databinding.beans.PojoObservables;
import com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguage;

import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.custom.StackLayout;
import com.founder.fix.bpmn2extensions.coreconfig.ExpandCmd;

public class FixFlowConfigDialog extends TitleAreaDialog {
	private DataBindingContext m_bindingContext;
	private FixFlowConfig fixFlowConfig;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Combo combo;
	private Combo combo_1;
	private Combo combo_2;
	private Table table;
	private Table table_1;
	private Table table_2;
	private Table table_3;
	private Table table_4;
	private Table table_5;
	private TableViewer tableViewer;
	private TableViewer tableViewer_1;
	private TableViewer tableViewer_2;
	private TableViewer tableViewer_3;
	private TableViewer tableViewer_4;
	private TableViewer tableViewer_5;
	private TableViewer tableViewer_6;
	private Button button;
	private Button button_1;
	private Button button_2;
	private Button button_3;
	private Button button_4;
	private Button button_5;
	private Button button_6;
	private Button button_7;
	private Button button_9;
	private Button button_8;
	private Button button_10;
	private Button button_11;
	private Button button_12;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Button btnCheckButton;
	private Button button_13;
	private Combo combo_3;
	private Button button_14;
	private Button button_15;
	private Table table_6;
	private Combo combo_4;
	private Button i18nCheckButton;
	private Text text_7;
	private Text serverPathTxt;
	private Text pdfPathTxt;
	private Text timeTxt;
	private Text dayTxt;
	private Composite weekComposite;
	private Composite dayComposite;
	private Composite parentComposite;
	private StackLayout stackLayout;
	private Button pEnabelBtn;
	private Button dayRadioBtn;
	private Button weekRadioBtn;
	private Button monthRadioBtn;
	private Button weekOneBtn;
	private Button weekTwoBtn;
	private Button weekThreeBtn;
	private Button weekFourBtn;
	private Button weekFiveBtn;
	private Button weekSixBtn;
	private Button weekSevenBtn;
	private Composite nullComposite;
	private Composite excuterbuttoncomposite;
	private TableViewer excutertableViewer;
	private Button excuterDeleteButton;
	private Table excutertable;
	private Table table_7;
	private TableViewer prioritytableViewer;
	private Button btnNewButton_2;
	private TableViewer assignPolicyConfigtableViewer;
	private Button btnNewButton_4;
	private Button btnNewButton_3;
	private Table assignPolicytable;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public FixFlowConfigDialog(Shell parentShell) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(SWT.CLOSE | SWT.RESIZE | SWT.TITLE | SWT.PRIMARY_MODAL);
		// 读取Menu的xml
	
		//XMIResource resource = (XMIResource) new ResourceSetImpl().getResource(URI.createFileURI(FixFlowConfigUtil.getFixFlowConfigXMLPath()), true);
		fixFlowConfig = 	EMFUtil.getFixFlowConfig(FixFlowConfigUtil.getFixFlowConfigXMLPath());
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("FixFlow配置");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		GridLayout gl_container = new GridLayout(1, false);
		gl_container.verticalSpacing = 0;
		gl_container.marginWidth = 0;
		gl_container.horizontalSpacing = 0;
		gl_container.marginHeight = 0;
		container.setLayout(gl_container);
		GridData gd_container = new GridData(GridData.FILL_BOTH);
		gd_container.widthHint = 523;
		gd_container.grabExcessHorizontalSpace = false;
		container.setLayoutData(gd_container);

		CTabFolder tabFolder = new CTabFolder(container, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		CTabItem dbconfigItem = new CTabItem(tabFolder, SWT.NONE);
		dbconfigItem.setText("数据库");

		Composite dbconfigcomposite = new Composite(tabFolder, SWT.NONE);
		dbconfigItem.setControl(dbconfigcomposite);
		dbconfigcomposite.setLayout(new GridLayout(2, false));

		Label label = new Label(dbconfigcomposite, SWT.NONE);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		label.setText("数据库配置");

		Composite composite = new Composite(dbconfigcomposite, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.verticalSpacing = 0;
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		gl_composite.horizontalSpacing = 0;
		composite.setLayout(gl_composite);

		combo = new Combo(composite, SWT.NONE);
		GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_combo.widthHint = 150;
		combo.setLayoutData(gd_combo);
		if (combo.getItemCount() >= 0) {
			combo.select(0);
		}
		
		combo.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				String oldValue = combo.getText().trim();
				//读取表格中的数据库ID
				List<DataBase> databases = (List<DataBase>) tableViewer.getInput();
				if(databases != null && databases.size() > 0) {
					combo.setItems(new String[0]);
					combo.add("");
					for (Iterator iterator = databases.iterator(); iterator
							.hasNext();) {
						DataBase database = (DataBase) iterator.next();
						combo.add(database.getId());
					}
				}
				if(oldValue != null) {
					combo.setText(oldValue);
				}
			}
		});
		combo.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				e.doit = false;
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				e.doit = false;
			}
		});

		combo_1 = new Combo(composite, SWT.NONE);
		combo_1.setItems(new String[] { "internal", "external" });
		GridData gd_combo_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_combo_1.widthHint = 150;
		combo_1.setLayoutData(gd_combo_1);
		combo_1.select(0);

		Label label_1 = new Label(dbconfigcomposite, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		label_1.setText("数据库维护");

		tableViewer = new TableViewer(dbconfigcomposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				updateButtons();
			}
		});
		table = tableViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		table.addListener(SWT.MeasureItem, new Listener() {
			public void handleEvent(Event event) {
				// 设置行高度
				event.height = (int) Math.floor(event.gc.getFontMetrics().getHeight() * 1.5);
			}
		});

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(150);
		tableColumn.setText("数据库ID");
		tableColumn.setMoveable(true);

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(270);
		tableColumn_1.setText("数据库名称");
		
		TableColumn tableColumn_1_2 = new TableColumn(table, SWT.NONE);
		tableColumn_1_2.setWidth(100);
		tableColumn_1_2.setText("数据库类型");

		Composite composite_1 = new Composite(dbconfigcomposite, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		GridLayout gl_composite_1 = new GridLayout(1, false);
		gl_composite_1.verticalSpacing = 0;
		gl_composite_1.marginWidth = 0;
		gl_composite_1.marginHeight = 0;
		gl_composite_1.horizontalSpacing = 0;
		composite_1.setLayout(gl_composite_1);

		button = new Button(composite_1, SWT.NONE);
		button.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		button.setText("添加");
		button.addListener(SWT.Selection, new Listener() {

			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				
				DataBase dataBase = CoreconfigFactory.eINSTANCE.createDataBase();

				dataBase.setId("id" + ((List<DataBase>) tableViewer.getInput()).size());
				dataBase.setName("name" + ((List<DataBase>) tableViewer.getInput()).size());
				dataBase.setDriverClassName("driver" + ((List<DataBase>) tableViewer.getInput()).size());
				dataBase.setUrl("url" + ((List<DataBase>) tableViewer.getInput()).size());
				dataBase.setUsername("username" + ((List<DataBase>) tableViewer.getInput()).size());
				dataBase.setPassword("password" + ((List<DataBase>) tableViewer.getInput()).size());
				dataBase.setMaxActive("MaxActive" + ((List<DataBase>) tableViewer.getInput()).size());
				dataBase.setMaxIdle("MaxIdle" + ((List<DataBase>) tableViewer.getInput()).size());
				dataBase.setMaxWait("MaxWait" + ((List<DataBase>) tableViewer.getInput()).size());
				dataBase.setPaginationImpl("PaginationImpl" + ((List<DataBase>) tableViewer.getInput()).size());

				((List<DataBase>) tableViewer.getInput()).add(dataBase);
				tableViewer.refresh();
			}
		});

		button_1 = new Button(composite_1, SWT.NONE);
		button_1.setText("编辑");
		button_1.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				
				IStructuredSelection iStructuredSelection = (IStructuredSelection) tableViewer.getSelection();

				if (iStructuredSelection != null) {
					DataBase dataBase = (DataBase) iStructuredSelection.getFirstElement();

					DataBaseConfigDialog dbcd = new DataBaseConfigDialog(getShell(), dataBase);
					dbcd.setBlockOnOpen(true);
					if (dbcd != null && dbcd.open() == InputDialog.OK) {
						int index = fixFlowConfig.getDataBaseConfig().getDataBase().indexOf(dataBase);
						fixFlowConfig.getDataBaseConfig().getDataBase().remove(dataBase);
						fixFlowConfig.getDataBaseConfig().getDataBase().add(index, dbcd.getDataBase());
					}
				}
			}
		});

		button_2 = new Button(composite_1, SWT.NONE);
		button_2.setText("移除");
		button_2.addListener(SWT.Selection, new Listener() {

			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				
				ISelection sel = tableViewer.getSelection();
				if (sel == null)
					return;
				Object[] objs = ((IStructuredSelection) sel).toArray();
				if (objs == null || objs.length == 0)
					return;
				boolean b = MessageDialog.openConfirm(null, "警告", "你确认要删除吗？");
				if (!b)
					return;

				for (int i = 0; i < objs.length; i++) {
					DataBase col = (DataBase) objs[i];
					((List<DataBase>) tableViewer.getInput()).remove(col);
				}
				tableViewer.refresh();
			}
		});

		//CTabItem groupdefItem = new CTabItem(tabFolder, SWT.NONE);
		//groupdefItem.setText("组定义");
		//groupdefItem.dispose()

		Composite groupcomposite = new Composite(tabFolder, SWT.NONE);
		//groupdefItem.setControl(groupcomposite);
		groupcomposite.setLayout(new GridLayout(2, false));
		

		Label label_2 = new Label(groupcomposite, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		label_2.setText("组定义配置");
		label_2.setLocation(0, 0);

		tableViewer_1 = new TableViewer(groupcomposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		tableViewer_1.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				updateButtons();
			}
		});
		table_1 = tableViewer_1.getTable();
		table_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setBounds(0, 0, 200, 50);
		table_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		table_1.addListener(SWT.MeasureItem, new Listener() {
			public void handleEvent(Event event) {
				// 设置行高度
				event.height = (int) Math.floor(event.gc.getFontMetrics().getHeight() * 1.5);
			}
		});

		TableColumn tableColumn_2 = new TableColumn(table_1, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("ID");
		tableColumn_2.setMoveable(true);

		TableColumn tableColumn_3 = new TableColumn(table_1, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("名称");
		tableColumn_3.setMoveable(true);

		TableColumn tableColumn_4 = new TableColumn(table_1, SWT.NONE);
		tableColumn_4.setWidth(300);
		tableColumn_4.setText("实现类");
		tableColumn_4.setMoveable(true);

		Composite composite_2 = new Composite(groupcomposite, SWT.NONE);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, true, 1, 1));
		composite_2.setBounds(0, 0, 64, 64);
		GridLayout gl_composite_2 = new GridLayout(1, false);
		gl_composite_2.verticalSpacing = 0;
		gl_composite_2.marginWidth = 0;
		gl_composite_2.marginHeight = 0;
		gl_composite_2.horizontalSpacing = 0;
		composite_2.setLayout(gl_composite_2);

		button_3 = new Button(composite_2, SWT.NONE);
		button_3.setText("添加");
		button_3.addListener(SWT.Selection, new Listener() {

			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				
				GroupDefinition groupDefinition = CoreconfigFactory.eINSTANCE.createGroupDefinition();
				groupDefinition.setId("id" + ((List<GroupDefinition>) tableViewer_1.getInput()).size());
				groupDefinition.setName("类型名称" + ((List<GroupDefinition>) tableViewer_1.getInput()).size());
				((List<GroupDefinition>) tableViewer_1.getInput()).add(groupDefinition);
				tableViewer_1.refresh();
			}
		});

		button_4 = new Button(composite_2, SWT.NONE);
		button_4.setText("移除");
		button_4.addListener(SWT.Selection, new Listener() {

			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				
				ISelection sel = tableViewer_1.getSelection();
				if (sel == null)
					return;
				Object[] objs = ((IStructuredSelection) sel).toArray();
				if (objs == null || objs.length == 0)
					return;
				boolean b = MessageDialog.openConfirm(null, "警告", "你确认要删除吗？");
				if (!b)
					return;

				for (int i = 0; i < objs.length; i++) {
					GroupDefinition col = (GroupDefinition) objs[i];
					((List<GroupDefinition>) tableViewer_1.getInput()).remove(col);
					fixFlowConfig.getGroupDefinitionConfig().getGroupDefinition().remove(col);
				}
				tableViewer_1.refresh();
			}
		});

		CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
		tabItem.setText("组织结构");

		Composite orgconfigcomposite = new Composite(tabFolder, SWT.NONE);
		tabItem.setControl(orgconfigcomposite);
		orgconfigcomposite.setLayout(new GridLayout(2, false));

		Composite composite_4 = new Composite(orgconfigcomposite, SWT.NONE);
		composite_4.setLayout(new GridLayout(2, false));
		composite_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));

		Label lblNewLabel = new Label(composite_4, SWT.NONE);
		lblNewLabel.setText("所有用户");

		button_7 = new Button(composite_4, SWT.CHECK);
		button_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		button_7.setText("是否启用");
		button_7.setBounds(0, 0, 12, 22);
		button_7.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if (button_7.getSelection()) {
					fixFlowConfig.getDesignerOrgConfig().getAllUserInfo().setIsEnabled("true");
				} else {
					fixFlowConfig.getDesignerOrgConfig().getAllUserInfo().setIsEnabled("false");
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				

			}
		});

		Label lblNewLabel_1 = new Label(composite_4, SWT.NONE);
		lblNewLabel_1.setText("编号字段");

		text_1 = new Text(composite_4, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNewLabel_2 = new Label(composite_4, SWT.NONE);
		lblNewLabel_2.setText("名称字段");

		text_2 = new Text(composite_4, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNewLabel_3 = new Label(composite_4, SWT.NONE);
		lblNewLabel_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_3.setText("查询语句");

		text = new Text(composite_4, SWT.BORDER | SWT.WRAP);
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text.heightHint = 65;
		text.setLayoutData(gd_text);

		Label label_4 = new Label(orgconfigcomposite, SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		label_4.setText("组织结构配置");

		tableViewer_3 = new TableViewer(orgconfigcomposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table_3 = tableViewer_3.getTable();
		table_3.setLinesVisible(true);
		table_3.setHeaderVisible(true);
		table_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table_3.addListener(SWT.MeasureItem, new Listener() {
			public void handleEvent(Event event) {
				// 设置行高度
				event.height = (int) Math.floor(event.gc.getFontMetrics().getHeight() * 1.5);
			}
		});

		tableViewer_3.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				
				updateButtons();
			}
		});

		TableColumn tableColumn_10 = new TableColumn(table_3, SWT.NONE);
		tableColumn_10.setWidth(100);
		tableColumn_10.setText("组编号");
		tableColumn_10.setMoveable(true);

		TableColumn tableColumn_11 = new TableColumn(table_3, SWT.NONE);
		tableColumn_11.setWidth(100);
		tableColumn_11.setText("组名称");
		tableColumn_11.setMoveable(true);

		TableColumn tableColumn_12 = new TableColumn(table_3, SWT.NONE);
		tableColumn_12.setWidth(100);
		tableColumn_12.setText("是否启用");
		tableColumn_12.setMoveable(true);

		TableColumn tableColumn_13 = new TableColumn(table_3, SWT.NONE);
		tableColumn_13.setWidth(100);
		tableColumn_13.setText("是否显示用户");
		tableColumn_13.setMoveable(true);

		TableColumn tableColumn_14 = new TableColumn(table_3, SWT.NONE);
		tableColumn_14.setWidth(100);
		tableColumn_14.setText("组编号字段");
		tableColumn_14.setMoveable(true);

		TableColumn tableColumn_17 = new TableColumn(table_3, SWT.NONE);
		tableColumn_17.setWidth(100);
		tableColumn_17.setText("组名称字段");
		tableColumn_17.setMoveable(true);

		TableColumn tableColumn_16 = new TableColumn(table_3, SWT.NONE);
		tableColumn_16.setWidth(100);
		tableColumn_16.setText("上级组编号字段");
		tableColumn_16.setMoveable(true);

		TableColumn tblclmnSql = new TableColumn(table_3, SWT.NONE);
		tblclmnSql.setWidth(100);
		tblclmnSql.setText("查询语句");
		tblclmnSql.setMoveable(true);
		
		TableColumn tableColumnClassImpl = new TableColumn(table_3, SWT.NONE);
		tableColumnClassImpl.setWidth(300);
		tableColumnClassImpl.setText("实现类");
		tableColumnClassImpl.setMoveable(true);
		
	

		Composite composite_5 = new Composite(orgconfigcomposite, SWT.NONE);
		GridLayout gl_composite_5 = new GridLayout(1, false);
		gl_composite_5.verticalSpacing = 0;
		gl_composite_5.marginWidth = 0;
		gl_composite_5.marginHeight = 0;
		gl_composite_5.horizontalSpacing = 0;
		composite_5.setLayout(gl_composite_5);

		Button button_7_1 = new Button(composite_5, SWT.NONE);
		button_7_1.setText("添加");
		button_7_1.addListener(SWT.Selection, new Listener() {

			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				
				GroupInfo groupInfo = CoreconfigFactory.eINSTANCE.createGroupInfo();
				groupInfo.setGroupId("id" + ((List<GroupInfo>) tableViewer_3.getInput()).size());
				groupInfo.setGroupName("name" + ((List<GroupInfo>) tableViewer_3.getInput()).size());
				groupInfo.setIsEnabled("true");
				groupInfo.setIsDisplayUser("false");
				groupInfo.setGroupIdField("idfield" + ((List<GroupInfo>) tableViewer_3.getInput()).size());
				groupInfo.setGroupNameField("namefield" + ((List<GroupInfo>) tableViewer_3.getInput()).size());
				groupInfo.setSupGroupIdField("supidfield" + ((List<GroupInfo>) tableViewer_3.getInput()).size());
				groupInfo.setSqlText("select * from");
				//groupInfo.setGroupDefinitionImpl("");
				((List<GroupInfo>) tableViewer_3.getInput()).add(groupInfo);
				tableViewer_3.refresh();
			}
		});

		button_9 = new Button(composite_5, SWT.NONE);
		button_9.setText("编辑");
		button_9.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				
				IStructuredSelection iStructuredSelection = (IStructuredSelection) tableViewer_3.getSelection();
				if (iStructuredSelection != null) {
					GroupInfo groupInfo = (GroupInfo) iStructuredSelection.getFirstElement();
					UserInfoConfigDialog uicd = new UserInfoConfigDialog(getShell(), groupInfo);
					uicd.setBlockOnOpen(true);
					if (uicd != null && uicd.open() == InputDialog.OK) {
						int index = fixFlowConfig.getDesignerOrgConfig().getGroupInfo().indexOf(groupInfo);
						fixFlowConfig.getDesignerOrgConfig().getGroupInfo().remove(groupInfo);
						fixFlowConfig.getDesignerOrgConfig().getGroupInfo().add(index, uicd.getGroupInfo());
					}
				}
			}
		});

		button_8 = new Button(composite_5, SWT.NONE);
		button_8.setText("移除");
		button_8.setEnabled(false);
		button_8.addListener(SWT.Selection, new Listener() {

			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				
				ISelection sel = tableViewer_3.getSelection();
				if (sel == null)
					return;
				Object[] objs = ((IStructuredSelection) sel).toArray();
				if (objs == null || objs.length == 0)
					return;
				boolean b = MessageDialog.openConfirm(null, "警告", "你确认要删除吗？");
				if (!b)
					return;

				for (int i = 0; i < objs.length; i++) {
					GroupInfo col = (GroupInfo) objs[i];
					((List<GroupInfo>) tableViewer_3.getInput()).remove(col);
				}
				tableViewer_3.refresh();
			}
		});

		CTabItem taskcommandconfigItem = new CTabItem(tabFolder, SWT.NONE);
		taskcommandconfigItem.setText("任务命令");

		Composite taskcommandconfigcomposite = new Composite(tabFolder, SWT.NONE);
		taskcommandconfigItem.setControl(taskcommandconfigcomposite);
		taskcommandconfigcomposite.setLayout(new GridLayout(2, false));

		Label label_3 = new Label(taskcommandconfigcomposite, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		label_3.setText("任务命令配置");
		label_3.setLocation(0, 0);

		tableViewer_2 = new TableViewer(taskcommandconfigcomposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		tableViewer_2.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				updateButtons();
			}
		});
		table_2 = tableViewer_2.getTable();
		table_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table_2.setLinesVisible(true);
		table_2.setHeaderVisible(true);
		table_2.setBounds(0, 0, 200, 50);
		table_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		table_2.addListener(SWT.MeasureItem, new Listener() {
			public void handleEvent(Event event) {
				// 设置行高度
				event.height = (int) Math.floor(event.gc.getFontMetrics().getHeight() * 1.5);
			}
		});

		TableColumn tableColumn_5 = new TableColumn(table_2, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("编号");
		tableColumn_5.setMoveable(true);

		TableColumn tableColumn_6 = new TableColumn(table_2, SWT.NONE);
		tableColumn_6.setWidth(100);
		tableColumn_6.setText("名称");
		tableColumn_6.setMoveable(true);
		
		TableColumn tableColumn_type = new TableColumn(table_2, SWT.NONE);
		tableColumn_type.setWidth(80);
		tableColumn_type.setText("命令类型");
		tableColumn_type.setMoveable(true);

		TableColumn tableColumn_9 = new TableColumn(table_2, SWT.NONE);
		tableColumn_9.setWidth(80);
		tableColumn_9.setText("是否启用");
		tableColumn_9.setMoveable(true);
		
		TableColumn tableColumn_15 = new TableColumn(table_2, SWT.NONE);
		tableColumn_15.setWidth(80);
		tableColumn_15.setText("是否验证");
		tableColumn_15.setMoveable(true);
		
		TableColumn tableColumn_24 = new TableColumn(table_2, SWT.NONE);
		tableColumn_24.setWidth(110);
		tableColumn_24.setText("是否保存数据");
		tableColumn_24.setMoveable(true);
		
		TableColumn tableColumn_25 = new TableColumn(table_2, SWT.NONE);
		tableColumn_25.setWidth(110);
		tableColumn_25.setText("是否虚拟运行");
		tableColumn_25.setMoveable(true);

		TableColumn tableColumn_7 = new TableColumn(table_2, SWT.NONE);
		tableColumn_7.setWidth(150);
		tableColumn_7.setText("参数对象");
		tableColumn_7.setMoveable(true);

		TableColumn tableColumn_8 = new TableColumn(table_2, SWT.NONE);
		tableColumn_8.setWidth(150);
		tableColumn_8.setText("执行器");
		tableColumn_8.setMoveable(true);
		
		TableColumn tableColumn_gl = new TableColumn(table_2, SWT.NONE);
		tableColumn_gl.setWidth(150);
		tableColumn_gl.setText("过滤器");
		tableColumn_gl.setMoveable(true);

		Composite composite_3 = new Composite(taskcommandconfigcomposite, SWT.NONE);
		composite_3.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		composite_3.setBounds(0, 0, 64, 64);
		GridLayout gl_composite_3 = new GridLayout(1, false);
		gl_composite_3.verticalSpacing = 0;
		gl_composite_3.marginWidth = 0;
		gl_composite_3.marginHeight = 0;
		gl_composite_3.horizontalSpacing = 0;
		composite_3.setLayout(gl_composite_3);

		button_5 = new Button(composite_3, SWT.NONE);
		button_5.setText("添加");
		button_5.addListener(SWT.Selection, new Listener() {

			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				
				TaskCommandDef taskCommand = CoreconfigFactory.eINSTANCE.createTaskCommandDef();
				taskCommand.setId("id" + ((List<TaskCommandDef>) tableViewer_2.getInput()).size());
				taskCommand.setName("类型名称" + ((List<TaskCommandDef>) tableViewer_2.getInput()).size());
				taskCommand.setIsEnabled("true");
				taskCommand.setIsVerification("true");
				taskCommand.setIsSaveData("true");
				taskCommand.setIsSimulationRun("false");
				
				((List<TaskCommandDef>) tableViewer_2.getInput()).add(taskCommand);
				tableViewer_2.refresh();
			}
		});

		button_6 = new Button(composite_3, SWT.NONE);
		button_6.setText("移除");
		
		CTabItem assignPolicyConfig = new CTabItem(tabFolder, SWT.NONE);
		assignPolicyConfig.setText("分配策略");
		
		Composite assignPolicyConfigcomposite = new Composite(tabFolder, SWT.NONE);
		assignPolicyConfig.setControl(assignPolicyConfigcomposite);
		assignPolicyConfigcomposite.setLayout(new GridLayout(2, false));
		
		Label lblNewLabel_20 = new Label(assignPolicyConfigcomposite, SWT.NONE);
		lblNewLabel_20.setText("分配策略配置");
		new Label(assignPolicyConfigcomposite, SWT.NONE);
		
		assignPolicyConfigtableViewer = new TableViewer(assignPolicyConfigcomposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		assignPolicyConfigtableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				
				updateButtons();
			}
		});
		assignPolicytable = assignPolicyConfigtableViewer.getTable();
		assignPolicytable.setLinesVisible(true);
		assignPolicytable.setHeaderVisible(true);
		assignPolicytable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TableColumn tblclmnNewColumn_8 = new TableColumn(assignPolicytable, SWT.NONE);
		tblclmnNewColumn_8.setWidth(150);
		tblclmnNewColumn_8.setText("策略编号");
		
		TableColumn tblclmnNewColumn_9 = new TableColumn(assignPolicytable, SWT.NONE);
		tblclmnNewColumn_9.setWidth(150);
		tblclmnNewColumn_9.setText("策略名称");
		
		TableColumn tblclmnNewColumn_10 = new TableColumn(assignPolicytable, SWT.NONE);
		tblclmnNewColumn_10.setWidth(150);
		tblclmnNewColumn_10.setText("实现类");
		
		TableColumn tblclmnNewColumn_11 = new TableColumn(assignPolicytable, SWT.NONE);
		tblclmnNewColumn_11.setWidth(140);
		tblclmnNewColumn_11.setText("备注");
		
		Composite composite_12 = new Composite(assignPolicyConfigcomposite, SWT.NONE);
		GridLayout gl_composite_12 = new GridLayout(1, false);
		gl_composite_12.verticalSpacing = 1;
		gl_composite_12.marginWidth = 0;
		gl_composite_12.marginHeight = 0;
		gl_composite_12.horizontalSpacing = 0;
		composite_12.setLayout(gl_composite_12);
		
		btnNewButton_3 = new Button(composite_12, SWT.NONE);
		btnNewButton_3.setBounds(0, 0, 98, 30);
		btnNewButton_3.setText("添加");
		btnNewButton_3.addListener(SWT.Selection, new Listener() {

			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				AssignPolicy assignPolicy = CoreconfigFactory.eINSTANCE.createAssignPolicy();
				assignPolicy.setId("id" + ((List<AssignPolicy>) assignPolicyConfigtableViewer.getInput()).size());
				assignPolicy.setName("名称" + ((List<AssignPolicy>) assignPolicyConfigtableViewer.getInput()).size());
				assignPolicy.setRemarks("");
				((List<AssignPolicy>)assignPolicyConfigtableViewer.getInput()).add(assignPolicy);
				assignPolicyConfigtableViewer.refresh();
			}
		});
		
		btnNewButton_4 = new Button(composite_12, SWT.NONE);
		btnNewButton_4.setBounds(0, 0, 98, 30);
		btnNewButton_4.setText("删除");
		btnNewButton_4.addListener(SWT.Selection, new Listener() {

			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				
				ISelection sel = assignPolicyConfigtableViewer.getSelection();
				if (sel == null)
					return;
				Object[] objs = ((IStructuredSelection) sel).toArray();
				if (objs == null || objs.length == 0)
					return;
				boolean b = MessageDialog.openConfirm(null, "警告", "你确认要删除吗？");
				if (!b)
					return;

				for (int i = 0; i < objs.length; i++) {
					AssignPolicy col = (AssignPolicy) objs[i];
					((List<AssignPolicy>) assignPolicyConfigtableViewer.getInput()).remove(col);
					fixFlowConfig.getTaskCommandConfig().getTaskCommandDef().remove(col);
				}
				assignPolicyConfigtableViewer.refresh();
			}
		});
		
		CTabItem excuterconfig = new CTabItem(tabFolder, SWT.NONE);
		excuterconfig.setText("执行器");
		
		Composite excutercomposite = new Composite(tabFolder, SWT.NONE);
		excuterconfig.setControl(excutercomposite);
		excutercomposite.setLayout(new GridLayout(2, false));
		
		Label excuterLabel = new Label(excutercomposite, SWT.NONE);
		excuterLabel.setText("执行器配置");
		new Label(excutercomposite, SWT.NONE);
		
		excutertableViewer = new TableViewer(excutercomposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		excutertableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				
				updateButtons();
			}
		});
		excutertable = excutertableViewer.getTable();
		excutertable.setLinesVisible(true);
		excutertable.setHeaderVisible(true);
		excutertable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TableColumn tblclmnNewColumn = new TableColumn(excutertable, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("编号");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(excutertable, SWT.NONE);
		tblclmnNewColumn_2.setWidth(150);
		tblclmnNewColumn_2.setText("名称");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(excutertable, SWT.NONE);
		tblclmnNewColumn_1.setWidth(300);
		tblclmnNewColumn_1.setText("执行器");
		
		excuterbuttoncomposite = new Composite(excutercomposite, SWT.NONE);
		excuterbuttoncomposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		GridLayout gl_excuterbuttoncomposite = new GridLayout(1, false);
		gl_excuterbuttoncomposite.verticalSpacing = 0;
		gl_excuterbuttoncomposite.marginWidth = 0;
		gl_excuterbuttoncomposite.marginHeight = 0;
		gl_excuterbuttoncomposite.horizontalSpacing = 0;
		excuterbuttoncomposite.setLayout(gl_excuterbuttoncomposite);
		
		Button btnNewButton = new Button(excuterbuttoncomposite, SWT.NONE);
		btnNewButton.setBounds(0, 0, 80, 27);
		btnNewButton.setText("添加");
		btnNewButton.addListener(SWT.Selection, new Listener() {

			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				
				ExpandCmd expandCmd = CoreconfigFactory.eINSTANCE.createExpandCmd();
				expandCmd.setId("id" + ((List<ExpandCmd>) excutertableViewer.getInput()).size());
				expandCmd.setName("名称" + ((List<ExpandCmd>) excutertableViewer.getInput()).size());
				expandCmd.setCmd("");
				((List<ExpandCmd>) excutertableViewer.getInput()).add(expandCmd);
				excutertableViewer.refresh();
			}
		});
		
		excuterDeleteButton = new Button(excuterbuttoncomposite, SWT.NONE);
		excuterDeleteButton.setText("删除");
		excuterDeleteButton.addListener(SWT.Selection, new Listener() {

			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				
				ISelection sel = excutertableViewer.getSelection();
				if (sel == null)
					return;
				Object[] objs = ((IStructuredSelection) sel).toArray();
				if (objs == null || objs.length == 0)
					return;
				boolean b = MessageDialog.openConfirm(null, "警告", "你确认要删除吗？");
				if (!b)
					return;

				for (int i = 0; i < objs.length; i++) {
					ExpandCmd col = (ExpandCmd) objs[i];
					((List<ExpandCmd>) excutertableViewer.getInput()).remove(col);
					fixFlowConfig.getExpandCmdConfig().getExpandCmd().remove(col);
				}
				excutertableViewer.refresh();
			}
		});

		CTabItem mailconfigItem = new CTabItem(tabFolder, SWT.NONE);
		mailconfigItem.setText("邮件配置");

		Composite mailcomposite = new Composite(tabFolder, SWT.NONE);
		mailconfigItem.setControl(mailcomposite);
		mailcomposite.setLayout(new GridLayout(2, false));

		Label label_5 = new Label(mailcomposite, SWT.NONE);
		label_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		label_5.setText("邮件配置");

		Composite composite_7 = new Composite(mailcomposite, SWT.NONE);
		composite_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		GridLayout gl_composite_7 = new GridLayout(1, false);
		gl_composite_7.verticalSpacing = 0;
		gl_composite_7.marginWidth = 0;
		gl_composite_7.marginHeight = 0;
		gl_composite_7.horizontalSpacing = 0;
		composite_7.setLayout(gl_composite_7);
		
		//如果没有这个元素则自动创建
		if(fixFlowConfig.getSysMailConfig()==null)
		{
			fixFlowConfig.setSysMailConfig(CoreconfigFactory.eINSTANCE.createSysMailConfig());
		}

		combo_2 = new Combo(composite_7, SWT.NONE);
		GridData gd_combo_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_combo_2.widthHint = 150;
		combo_2.setLayoutData(gd_combo_2);
		if (combo_2.getItemCount() >= 0) {
			combo_2.select(0);
		}
		combo_2.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				String oldValue = combo_2.getText().trim();
				//读取表格中的名称
				List<MailInfo> mailInfos = (List<MailInfo>) tableViewer_4.getInput();
				if(mailInfos != null && mailInfos.size() > 0) {
					combo_2.setItems(new String[0]);
					combo_2.add("");
					for (Iterator iterator = mailInfos.iterator(); iterator
							.hasNext();) {
						MailInfo mailInfo = (MailInfo) iterator.next();
						combo_2.add(mailInfo.getMailName());
					}
				}
				if(oldValue != null) {
					combo_2.setText(oldValue);
				}
			}
		});
		combo_2.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				e.doit = false;
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				e.doit = false;
			}
		});
		
		Label label_6 = new Label(mailcomposite, SWT.NONE);
		label_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		label_6.setText("数据库维护");

		tableViewer_4 = new TableViewer(mailcomposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		tableViewer_4.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				updateButtons();
			}
		});
		table_4 = tableViewer_4.getTable();
		table_4.setLinesVisible(true);
		table_4.setHeaderVisible(true);
		table_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		TableColumn tableColumn_18 = new TableColumn(table_4, SWT.NONE);
		tableColumn_18.setMoveable(true);
		tableColumn_18.setWidth(150);
		tableColumn_18.setText("邮件名称");

		TableColumn mailaddColumn = new TableColumn(table_4, SWT.NONE);
		mailaddColumn.setWidth(350);
		mailaddColumn.setText("邮件地址");
		mailaddColumn.setMoveable(true);

		Composite composite_8 = new Composite(mailcomposite, SWT.NONE);
		GridLayout gl_composite_8 = new GridLayout(1, false);
		gl_composite_8.verticalSpacing = 0;
		gl_composite_8.marginWidth = 0;
		gl_composite_8.marginHeight = 0;
		gl_composite_8.horizontalSpacing = 0;
		composite_8.setLayout(gl_composite_8);

		button_10 = new Button(composite_8, SWT.NONE);
		button_10.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		button_10.setText("添加");
		button_10.addListener(SWT.Selection, new Listener() {

			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				
				
				MailInfo mailInfo = CoreconfigFactory.eINSTANCE.createMailInfo();

				mailInfo.setMailName("邮件名称" + ((List<MailInfo>) tableViewer_4.getInput()).size());
				mailInfo.setMailAddress("邮件地址" + ((List<MailInfo>) tableViewer_4.getInput()).size());
				mailInfo.setSmtpHost("");
				mailInfo.setSmtpPort("25");
				mailInfo.setUserName("");
				mailInfo.setPassWord("");

				((List<MailInfo>) tableViewer_4.getInput()).add(mailInfo);
				tableViewer_4.refresh();
			}
		});

		button_11 = new Button(composite_8, SWT.NONE);
		button_11.setText("编辑");
		button_11.setEnabled(false);
		button_11.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				
				IStructuredSelection iStructuredSelection = (IStructuredSelection) tableViewer_4.getSelection();

				if (iStructuredSelection != null) {
					MailInfo mailInfo = (MailInfo) iStructuredSelection.getFirstElement();

					MailConfigDialog mcd = new MailConfigDialog(getShell(), mailInfo);
					mcd.setBlockOnOpen(true);
					if (mcd != null && mcd.open() == InputDialog.OK) {
						int index = fixFlowConfig.getSysMailConfig().getMailInfo().indexOf(mailInfo);
						fixFlowConfig.getSysMailConfig().getMailInfo().remove(mailInfo);
						fixFlowConfig.getSysMailConfig().getMailInfo().add(index, mcd.getMailInfo());
					}
				}
			}
		});

		button_12 = new Button(composite_8, SWT.NONE);
		button_12.setText("移除");
		button_12.setEnabled(false);
		button_12.addListener(SWT.Selection, new Listener() {

			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				
				ISelection sel = tableViewer_4.getSelection();
				if (sel == null)
					return;
				Object[] objs = ((IStructuredSelection) sel).toArray();
				if (objs == null || objs.length == 0)
					return;
				boolean b = MessageDialog.openConfirm(null, "警告", "你确认要删除吗？");
				if (!b)
					return;

				for (int i = 0; i < objs.length; i++) {
					MailInfo col = (MailInfo) objs[i];
					((List<MailInfo>) tableViewer_4.getInput()).remove(col);
				}
				tableViewer_4.refresh();
			}
		});

		button_6.addListener(SWT.Selection, new Listener() {

			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				
				ISelection sel = tableViewer_2.getSelection();
				if (sel == null)
					return;
				Object[] objs = ((IStructuredSelection) sel).toArray();
				if (objs == null || objs.length == 0)
					return;
				boolean b = MessageDialog.openConfirm(null, "警告", "你确认要删除吗？");
				if (!b)
					return;

				for (int i = 0; i < objs.length; i++) {
					TaskCommandDef col = (TaskCommandDef) objs[i];
					((List<TaskCommandDef>) tableViewer_2.getInput()).remove(col);
					fixFlowConfig.getTaskCommandConfig().getTaskCommandDef().remove(col);
				}
				tableViewer_2.refresh();
			}
		});

		
		CTabItem engineextension = new CTabItem(tabFolder, SWT.NONE);
		engineextension.setText("引擎扩展");
		
		Composite engineextensioncomposite = new Composite(tabFolder, SWT.NONE);
		engineextension.setControl(engineextensioncomposite);
		engineextensioncomposite.setLayout(new GridLayout(2, false));

		Label label_7 = new Label(engineextensioncomposite, SWT.NONE);
		label_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		label_7.setText("引擎扩展配置");
		label_7.setLocation(0, 0);

		tableViewer_5 = new TableViewer(engineextensioncomposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table_5 = tableViewer_5.getTable();
		table_5.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table_5.setLinesVisible(true);
		table_5.setHeaderVisible(true);
		table_5.setBounds(0, 0, 200, 50);
		table_5.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		table_5.addListener(SWT.MeasureItem, new Listener() {
			public void handleEvent(Event event) {
				// 设置行高度
				event.height = (int) Math.floor(event.gc.getFontMetrics().getHeight() * 1.5);
			}
		});

		TableColumn tableColumn_23 = new TableColumn(table_5, SWT.NONE);
		tableColumn_23.setWidth(150);
		tableColumn_23.setText("类编号");
		tableColumn_23.setMoveable(true);
		
		TableColumn tableColumn_19 = new TableColumn(table_5, SWT.NONE);
		tableColumn_19.setWidth(100);
		tableColumn_19.setText("类名称");
		tableColumn_19.setMoveable(true);
		
		TableColumn tableColumn_20 = new TableColumn(table_5, SWT.NONE);
		tableColumn_20.setWidth(100);
		tableColumn_20.setText("接口名称");
		tableColumn_20.setMoveable(true);
		
		TableColumn tableColumn_21 = new TableColumn(table_5, SWT.NONE);
		tableColumn_21.setWidth(100);
		tableColumn_21.setText("实现名称");
		tableColumn_21.setMoveable(true);
		
		TableColumn tableColumn_22 = new TableColumn(table_5, SWT.NONE);
		tableColumn_22.setWidth(150);
		tableColumn_22.setText("备注");
		tableColumn_22.setMoveable(true);
		
		CTabItem listenerconfig = new CTabItem(tabFolder, SWT.NONE);
		listenerconfig.setText("监听配置");
		
		Composite listenerconfigcomposite = new Composite(tabFolder, SWT.NONE);
		listenerconfig.setControl(listenerconfigcomposite);
		listenerconfigcomposite.setLayout(new GridLayout(2, false));
		
		Label lblNewLabel_4 = new Label(listenerconfigcomposite, SWT.NONE);
		lblNewLabel_4.setText("监听配置");
		
		btnCheckButton = new Button(listenerconfigcomposite, SWT.CHECK);
		btnCheckButton.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, false, 1, 1));
		btnCheckButton.setText("是否启用");
		btnCheckButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if (btnCheckButton.getSelection()) {
					fixFlowConfig.getEventSubscriptionConfig().setIsEnable("true");
					validate();
				} else {
					fixFlowConfig.getEventSubscriptionConfig().setIsEnable("false");
					FixFlowConfigDialog.this.setErrorMessage(null);
					button_13.setEnabled(true);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				

			}
		});
		
		Label lblNewLabel_5 = new Label(listenerconfigcomposite, SWT.NONE);
		lblNewLabel_5.setText("服务器地址");
		
		text_3 = new Text(listenerconfigcomposite, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_3.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				
				if(btnCheckButton.getSelection()) {
					validate();
				}
			}
		});
		
		Label lblNewLabel_6 = new Label(listenerconfigcomposite, SWT.NONE);
		lblNewLabel_6.setText("端口号");
		
		text_4 = new Text(listenerconfigcomposite, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_4.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				
				if(btnCheckButton.getSelection()) {
					validate();
				}
			}
		});
		
		Label lblNewLabel_7 = new Label(listenerconfigcomposite, SWT.NONE);
		lblNewLabel_7.setText("消息名称");
		
		text_5 = new Text(listenerconfigcomposite, SWT.BORDER);
		text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_5.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				
				if(btnCheckButton.getSelection()) {
					validate();
				}
			}
		});
		
		Label lblNewLabel_8 = new Label(listenerconfigcomposite, SWT.NONE);
		lblNewLabel_8.setText("信号名称");
		
		text_6 = new Text(listenerconfigcomposite, SWT.BORDER);
		text_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		CTabItem jobconfig = new CTabItem(tabFolder, SWT.NONE);
		jobconfig.setText("定时任务");
		
		Composite jobcomposite = new Composite(tabFolder, SWT.NONE);
		jobconfig.setControl(jobcomposite);
		jobcomposite.setLayout(new GridLayout(1, false));
		
		Composite composite_10 = new Composite(jobcomposite, SWT.NONE);
		composite_10.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		composite_10.setBounds(0, 0, 64, 64);
		GridLayout gl_composite_10 = new GridLayout(2, false);
		gl_composite_10.verticalSpacing = 0;
		gl_composite_10.marginWidth = 0;
		gl_composite_10.marginHeight = 0;
		gl_composite_10.horizontalSpacing = 0;
		composite_10.setLayout(gl_composite_10);
		
		button_14 = new Button(composite_10, SWT.CHECK);
		button_14.setText("是否启用");
		button_14.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if (button_14.getSelection()) {
					fixFlowConfig.getQuartzConfig().setIsEnable("true");
				} else {
					fixFlowConfig.getQuartzConfig().setIsEnable("false");
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				

			}
		});
		
		button_15 = new Button(composite_10, SWT.CHECK);
		button_15.setText("是否跟随平台数据库配置");
		button_15.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if (button_15.getSelection()) {
					fixFlowConfig.getQuartzConfig().setIsDefaultConfig("true");
					combo_3.setEnabled(false);
				} else {
					fixFlowConfig.getQuartzConfig().setIsDefaultConfig("false");
					combo_3.setEnabled(true);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				

			}
		});
		
		Label label_8 = new Label(jobcomposite, SWT.NONE);
		label_8.setText("数据库配置");
		
		combo_3 = new Combo(jobcomposite, SWT.NONE);
		GridData gd_combo_3 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_combo_3.widthHint = 200;
		combo_3.setLayoutData(gd_combo_3);
		
		CTabItem scriptconfig = new CTabItem(tabFolder, SWT.NONE);
		scriptconfig.setText("脚本语言");
		
		Composite scriptcomposite = new Composite(tabFolder, SWT.NONE);
		scriptconfig.setControl(scriptcomposite);
		GridLayout gl_scriptcomposite = new GridLayout(1, false);
		scriptcomposite.setLayout(gl_scriptcomposite);
		
		Label lblNewLabel_9 = new Label(scriptcomposite, SWT.NONE);
		lblNewLabel_9.setText("脚本语言配置");
		
		combo_4 = new Combo(scriptcomposite, SWT.NONE);
		GridData gd_combo_4 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_combo_4.widthHint = 200;
		combo_4.setLayoutData(gd_combo_4);
		combo_4.setBounds(0, 0, 88, 25);
		
		if (combo_4.getItemCount() >= 0) {
			combo_4.select(0);
		}
		
		combo_4.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				String oldValue = combo_4.getText().trim();
				
				List<ScriptLanguage> scriptLanguages = (List<ScriptLanguage>) tableViewer_6.getInput();
				if(scriptLanguages != null && scriptLanguages.size() > 0) {
					/*combo_4.setItems(new String[0]);
					combo_4.add("");*/
					for (Iterator iterator = scriptLanguages.iterator(); iterator
							.hasNext();) {
						ScriptLanguage scriptLanguage = (ScriptLanguage) iterator.next();
						combo_4.add(scriptLanguage.getId());
					}
				}
				if(oldValue != null) {
					combo_4.setText(oldValue);
				}
			}
		});
		combo_4.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				e.doit = false;
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				e.doit = false;
			}
		});
		
		Label lblNewLabel_10 = new Label(scriptcomposite, SWT.NONE);
		lblNewLabel_10.setText("脚本语言");
		
		tableViewer_6 = new TableViewer(scriptcomposite, SWT.BORDER | SWT.FULL_SELECTION);
		tableViewer_6.setColumnProperties(new String[] {"SCRIPTID", "SCRIPTCLASSIMPL"});
		table_6 = tableViewer_6.getTable();
		table_6.setLinesVisible(true);
		table_6.setHeaderVisible(true);
		table_6.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TableColumn tblclmnId = new TableColumn(table_6, SWT.NONE);
		tblclmnId.setMoveable(true);
		tblclmnId.setWidth(200);
		tblclmnId.setText("编号");
		
		TableColumn tblclmnClassimpl = new TableColumn(table_6, SWT.NONE);
		tblclmnClassimpl.setMoveable(true);
		tblclmnClassimpl.setWidth(350);
		tblclmnClassimpl.setText("实现类");
		
		if (combo_3.getItemCount() >= 0) {
			combo_3.select(0);
		}
		
		combo_3.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				String oldValue = combo_3.getText().trim();
				//读取表格中的数据库ID
				List<DataBase> databases = (List<DataBase>) tableViewer.getInput();
				if(databases != null && databases.size() > 0) {
					combo_3.setItems(new String[0]);
					combo_3.add("");
					for (Iterator iterator = databases.iterator(); iterator
							.hasNext();) {
						DataBase database = (DataBase) iterator.next();
						combo_3.add(database.getId());
					}
				}
				if(oldValue != null) {
					combo_3.setText(oldValue);
				}
			}
		});
		combo_3.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				e.doit = false;
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				e.doit = false;
			}
		});
		
		text_6.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				
				if(btnCheckButton.getSelection()) {
					validate();
				}
			}
		});
		
		CTabItem i18nconfig = new CTabItem(tabFolder, SWT.NONE);
		i18nconfig.setText("国际化");
		
		Composite i18ncomposite = new Composite(tabFolder, SWT.NONE);
		i18nconfig.setControl(i18ncomposite);
		i18ncomposite.setLayout(new GridLayout(2, false));
		
		i18nCheckButton = new Button(i18ncomposite, SWT.CHECK);
		i18nCheckButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		i18nCheckButton.setText("是否启用");
		i18nCheckButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if (i18nCheckButton.getSelection()) {
					fixFlowConfig.getInternationalizationConfig().setIsEnable("true");
				} else {
					fixFlowConfig.getInternationalizationConfig().setIsEnable("false");
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				

			}
		});
		
		Label label_10 = new Label(i18ncomposite, SWT.NONE);
		label_10.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_10.setText("国际化文件路径");
		
		text_7 = new Text(i18ncomposite, SWT.BORDER);
		text_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		CTabItem priorityConfig = new CTabItem(tabFolder, SWT.NONE);
		priorityConfig.setText("优先级");
		
		Composite prioritycomposite = new Composite(tabFolder, SWT.NONE);
		priorityConfig.setControl(prioritycomposite);
		prioritycomposite.setLayout(new GridLayout(2, false));
		
		Label lblNewLabel_19 = new Label(prioritycomposite, SWT.NONE);
		lblNewLabel_19.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNewLabel_19.setText("优先级配置");
		
		prioritytableViewer = new TableViewer(prioritycomposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		prioritytableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				updateButtons();
			}
		});
		table_7 = prioritytableViewer.getTable();
		table_7.setLinesVisible(true);
		table_7.setHeaderVisible(true);
		table_7.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table_7.addListener(SWT.MeasureItem, new Listener() {
			public void handleEvent(Event event) {
				// 设置行高度
				event.height = (int) Math.floor(event.gc.getFontMetrics().getHeight() * 1.5);
			}
		});
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table_7, SWT.NONE);
		tblclmnNewColumn_3.setWidth(90);
		tblclmnNewColumn_3.setText("编号");
		
		TableColumn tblclmnNewColumn_7 = new TableColumn(table_7, SWT.NONE);
		tblclmnNewColumn_7.setWidth(150);
		tblclmnNewColumn_7.setText("名称");
		
		TableColumn tblclmnNewColumn_6 = new TableColumn(table_7, SWT.NONE);
		tblclmnNewColumn_6.setWidth(100);
		tblclmnNewColumn_6.setText("级别值");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table_7, SWT.NONE);
		tblclmnNewColumn_5.setWidth(100);
		tblclmnNewColumn_5.setText("颜色");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table_7, SWT.NONE);
		tblclmnNewColumn_4.setWidth(150);
		tblclmnNewColumn_4.setText("备注");
		
		Composite composite_11 = new Composite(prioritycomposite, SWT.NONE);
		GridLayout gl_composite_11 = new GridLayout(1, false);
		gl_composite_11.verticalSpacing = 1;
		gl_composite_11.marginWidth = 0;
		gl_composite_11.marginHeight = 0;
		gl_composite_11.horizontalSpacing = 0;
		composite_11.setLayout(gl_composite_11);
		
		Button btnNewButton_1 = new Button(composite_11, SWT.NONE);
		btnNewButton_1.setBounds(0, 0, 98, 30);
		btnNewButton_1.setText("添加");
		btnNewButton_1.addListener(SWT.Selection, new Listener() {

			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				
				Priority priority = CoreconfigFactory.eINSTANCE.createPriority();
				priority.setId("id" + ((List<Priority>) prioritytableViewer.getInput()).size());
				priority.setName("名称" + ((List<Priority>) prioritytableViewer.getInput()).size());
				priority.setColor("#000000");
				priority.setValue("0");
				priority.setRemark("");
				((List<Priority>) prioritytableViewer.getInput()).add(priority);
				prioritytableViewer.refresh();
			}
		});
		
		btnNewButton_2 = new Button(composite_11, SWT.NONE);
		btnNewButton_2.setBounds(0, 0, 98, 30);
		btnNewButton_2.setText("移除");
		btnNewButton_2.addListener(SWT.Selection, new Listener() {

			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				
				ISelection sel = prioritytableViewer.getSelection();
				if (sel == null)
					return;
				Object[] objs = ((IStructuredSelection) sel).toArray();
				if (objs == null || objs.length == 0)
					return;
				boolean b = MessageDialog.openConfirm(null, "警告", "你确认要删除吗？");
				if (!b)
					return;

				for (int i = 0; i < objs.length; i++) {
					Priority col = (Priority) objs[i];
					((List<Priority>) prioritytableViewer.getInput()).remove(col);
					fixFlowConfig.getPriorityConfig().getPriority().remove(col);
				}
				prioritytableViewer.refresh();
			}
		});
		
		CTabItem GDconfig = new CTabItem(tabFolder, SWT.NONE);
		GDconfig.setText("归档");
		
		Composite GDcomposite = new Composite(tabFolder, SWT.NONE);
		GDconfig.setControl(GDcomposite);
		GDcomposite.setLayout(new GridLayout(2, false));
		
		Label lblNewLabel_11 = new Label(GDcomposite, SWT.NONE);
		lblNewLabel_11.setText("归档配置");
		
		pEnabelBtn = new Button(GDcomposite, SWT.CHECK);
		pEnabelBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		pEnabelBtn.setText("是否启用");
		pEnabelBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(pEnabelBtn.getSelection()) {
					fixFlowConfig.getPigeonholeConfig().setIsEnable("true");
				} else {
					fixFlowConfig.getPigeonholeConfig().setIsEnable("false");
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		Label lblNewLabel_12 = new Label(GDcomposite, SWT.NONE);
		lblNewLabel_12.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_12.setText("服务器地址");
		
		serverPathTxt = new Text(GDcomposite, SWT.BORDER);
		serverPathTxt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_13 = new Label(GDcomposite, SWT.NONE);
		lblNewLabel_13.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_13.setText("PDF存放目录");
		
		pdfPathTxt = new Text(GDcomposite, SWT.BORDER);
		pdfPathTxt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_14 = new Label(GDcomposite, SWT.NONE);
		lblNewLabel_14.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_14.setText("发生频率");
		
		Composite composite_6 = new Composite(GDcomposite, SWT.NONE);
		composite_6.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		composite_6.setLayout(new GridLayout(3, false));
		
		dayRadioBtn = new Button(composite_6, SWT.RADIO);
		dayRadioBtn.setText("每天");
		dayRadioBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl = nullComposite;
				parentComposite.layout();
				fixFlowConfig.getPigeonholeConfig().setFrequency("day");
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		weekRadioBtn = new Button(composite_6, SWT.RADIO);
		weekRadioBtn.setText("每周");
		weekRadioBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl = weekComposite;
				parentComposite.layout();
				fixFlowConfig.getPigeonholeConfig().setFrequency("week");
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		monthRadioBtn = new Button(composite_6, SWT.RADIO);
		monthRadioBtn.setText("每月");
		monthRadioBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl = dayComposite;
				parentComposite.layout();
				fixFlowConfig.getPigeonholeConfig().setFrequency("month");
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		Label lblNewLabel_15 = new Label(GDcomposite, SWT.NONE);
		lblNewLabel_15.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_15.setText("备份时间");
		
		Composite composite_9 = new Composite(GDcomposite, SWT.NONE);
		composite_9.setLayout(new GridLayout(2, false));
		composite_9.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		
		timeTxt = new Text(composite_9, SWT.BORDER);
		timeTxt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_16 = new Label(composite_9, SWT.NONE);
		lblNewLabel_16.setText("（注：格式12:0:0）");
		new Label(GDcomposite, SWT.NONE);
		
		parentComposite = new Composite(GDcomposite, SWT.NONE);
		stackLayout = new StackLayout();
		parentComposite.setLayout(stackLayout);
		parentComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		
		weekComposite = new Composite(parentComposite, SWT.NONE);
		weekComposite.setLayout(new GridLayout(7, false));
		//		weekComposite.setVisible(false);
		
		weekSevenBtn = new Button(weekComposite, SWT.RADIO);
		weekSevenBtn.setText("星期日");
		weekSevenBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(weekSevenBtn.getSelection()) {
					fixFlowConfig.getPigeonholeConfig().setWeek("1");
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		weekOneBtn = new Button(weekComposite, SWT.RADIO);
		weekOneBtn.setText("星期一");
		weekOneBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(weekOneBtn.getSelection()) {
					fixFlowConfig.getPigeonholeConfig().setWeek("2");
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		weekTwoBtn = new Button(weekComposite, SWT.RADIO);
		weekTwoBtn.setText("星期二");
		weekTwoBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(weekTwoBtn.getSelection()) {
					fixFlowConfig.getPigeonholeConfig().setWeek("3");
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		weekThreeBtn = new Button(weekComposite, SWT.RADIO);
		weekThreeBtn.setText("星期三");
		weekThreeBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(weekThreeBtn.getSelection()) {
					fixFlowConfig.getPigeonholeConfig().setWeek("4");
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		weekFourBtn = new Button(weekComposite, SWT.RADIO);
		weekFourBtn.setText("星期四");
		weekFourBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(weekFourBtn.getSelection()) {
					fixFlowConfig.getPigeonholeConfig().setWeek("5");
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		weekFiveBtn = new Button(weekComposite, SWT.RADIO);
		weekFiveBtn.setText("星期五");
		weekFiveBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(weekFiveBtn.getSelection()) {
					fixFlowConfig.getPigeonholeConfig().setWeek("6");
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		weekSixBtn = new Button(weekComposite, SWT.RADIO);
		weekSixBtn.setText("星期六");
		weekSixBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(weekSixBtn.getSelection()) {
					fixFlowConfig.getPigeonholeConfig().setWeek("7");
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		dayComposite = new Composite(parentComposite, SWT.NONE);
		dayComposite.setLayout(new GridLayout(3, false));
		//		dayComposite.setVisible(false);
				
		Label lblNewLabel_17 = new Label(dayComposite, SWT.NONE);
		lblNewLabel_17.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_17.setText("第");
		
		dayTxt = new Text(dayComposite, SWT.BORDER);
		
		Label lblNewLabel_18 = new Label(dayComposite, SWT.NONE);
		lblNewLabel_18.setText("天");
		
		nullComposite = new Composite(parentComposite, SWT.NONE);
		
		if (combo.getItemCount() >= 0) {
			combo.select(0);
		}
		
		if (combo_2.getItemCount() >= 0) {
			combo_2.select(0);
		}
		
		if (combo_3.getItemCount() >= 0) {
			combo_3.select(0);
		}

		createCellModifier1();

		createCellModifier2();

		createCellModifier3();

		createCellModifier4();

		createCellModifier5();
		
		createCellModifier6();
		
		createCellModifier7();
		
		createCellModifier8();
		
		createCellModifierPriority();
		
		createCellModifierAssignPolicy();

		setMessage("配置FixFlow", IMessageProvider.INFORMATION);

		updateButtons();

		init();
		
		return area;
	}

	private void createCellModifier1() {

		CellEditor[] cellEditor = new CellEditor[table.getColumnCount()];
		cellEditor[0] = new TextCellEditor(table, SWT.READ_ONLY);
		cellEditor[1] = new TextCellEditor(table, SWT.READ_ONLY);
		cellEditor[2] = new TextCellEditor(table, SWT.READ_ONLY);

		tableViewer.setColumnProperties(new String[] { "DATABASEID", "DATABASENAME", "DBTYPE" });
		tableViewer.setCellEditors(cellEditor);
		tableViewer.setCellModifier(new ICellModifier() {

			public void modify(Object element, String property, Object value) {
				
				TableItem tableitem = (TableItem) element;
				DataBase dataBase = (DataBase) tableitem.getData();
				
				Object oldValue = getValue(dataBase, property);
				if (String.valueOf(value).equals(String.valueOf(oldValue)))
					return;
				
				if (property.equals("DATABASEID")) {
					dataBase.setId((String) value);
				}
				if (property.equals("DATABASENAME")) {
					dataBase.setName((String) value);
				}
				if (property.equals("DBTYPE")) {
					dataBase.setDbtype(DBType.values()[(Integer) value]);
				}
				tableViewer.refresh();
				/*
				 * //清空缓存 DataVarUtil.dataVariableConfig = null;
				 */
				updateButtons();
			}

			public Object getValue(Object element, String property) {
				
				DataBase dataBase = (DataBase) element;

				if (property.equals("DATABASEID")) {
					return dataBase.getId();
				}
				if (property.equals("DATABASENAME")) {
					return dataBase.getName();
				}
				if (property.equals("DBTYPE")) {
					return dataBase.getDbtype();
				}
				return null;
			}

			public boolean canModify(Object element, String property) {
				
				return element instanceof DataBase;
			}
		});
	}

	private void createCellModifier2() {
		CellEditor[] cellEditor = new CellEditor[table_1.getColumnCount()];
		cellEditor[0] = new TextCellEditor(table_1);
		cellEditor[1] = new TextCellEditor(table_1);
		cellEditor[2] = new GroupDefinitionCellEditor(table_1, getShell());

		tableViewer_1.setColumnProperties(new String[] { "GROUPDEFINITIONID", "GROUPDEFINITIONNAME", "GROUPDEFINITIONIMPL" });
		tableViewer_1.setCellEditors(cellEditor);
		tableViewer_1.setCellModifier(new ICellModifier() {

			public void modify(Object element, String property, Object value) {
				
				TableItem tableitem = (TableItem) element;
				GroupDefinition groupDefinition = (GroupDefinition) tableitem.getData();
				if (property.equals("GROUPDEFINITIONID")) {
					groupDefinition.setId((String) value);
				}
				if (property.equals("GROUPDEFINITIONNAME")) {
					groupDefinition.setName((String) value);
				}
				if (property.equals("GROUPDEFINITIONIMPL")) {
					groupDefinition.setGroupDefinitionImpl((String) value);
				}
				tableViewer_1.refresh();
				updateButtons();
			}

			public Object getValue(Object element, String property) {
				
				GroupDefinition groupDefinition = (GroupDefinition) element;

				if (property.equals("GROUPDEFINITIONID")) {
					return groupDefinition.getId();
				}
				if (property.equals("GROUPDEFINITIONNAME")) {
					return groupDefinition.getName();
				}
				if (property.equals("GROUPDEFINITIONIMPL")) {
					return groupDefinition.getGroupDefinitionImpl();
				}
				return null;
			}

			public boolean canModify(Object element, String property) {
				
				return element instanceof GroupDefinition;
			}
		});
	}

	private void createCellModifier3() {
		final String[] strs = new String[]{"toDoTasks", "sharedTasks", "processInstanceInfo", "finishTasks", "all","system"};
		
		final Map<String, Integer> TASKCOMMANDTYPE_MAP = new HashMap<String, Integer>();
		 {
			 TASKCOMMANDTYPE_MAP.put("toDoTasks", 0);
			 TASKCOMMANDTYPE_MAP.put("sharedTasks", 1);
			 TASKCOMMANDTYPE_MAP.put("processInstanceInfo", 2);
			 TASKCOMMANDTYPE_MAP.put("finishTasks", 3);
			 TASKCOMMANDTYPE_MAP.put("all", 4);
			 TASKCOMMANDTYPE_MAP.put("system", 5);
			 
			 
		}
		
		CellEditor[] cellEditor = new CellEditor[table_2.getColumnCount()];
		cellEditor[0] = new TextCellEditor(table_2);
		cellEditor[1] = new TextCellEditor(table_2);
		cellEditor[2] = new ComboBoxCellEditor(table_2, strs, SWT.READ_ONLY);
		cellEditor[3] = new CheckboxCellEditor(table_2);
		cellEditor[4] = new CheckboxCellEditor(table_2);
		cellEditor[5] = new CheckboxCellEditor(table_2);
		cellEditor[6] = new CheckboxCellEditor(table_2);
		cellEditor[7] = new TaskCommandCellEditor(table_2, getShell());
		cellEditor[8] = new TaskCommandCellEditor(table_2, getShell());
		cellEditor[9] = new TaskCommandCellEditor(table_2, getShell());
		
		tableViewer_2.setColumnProperties(new String[] { "TASKCOMMANDID", "TASKCOMMANDNAME","TASKCOMMANDTYPE", "TASKCOMMANDENABLE","TASKCOMMANDVERIFY","TASKCOMMANDSAVEDATA","TASKCOMMANDSIMULATION", "TASKCOMMANDCOMMAND", "TASKCOMMANDCMD","TASKCOMMANDFILTER" });
		tableViewer_2.setCellEditors(cellEditor);
		tableViewer_2.setCellModifier(new ICellModifier() {

			public void modify(Object element, String property, Object value) {
				
				TableItem tableitem = (TableItem) element;
				TaskCommandDef taskCommand = (TaskCommandDef) tableitem.getData();
				if (property.equals("TASKCOMMANDID")) {
					taskCommand.setId((String) value);
				}
				if (property.equals("TASKCOMMANDNAME")) {
					taskCommand.setName((String) value);
				}
				if (property.equals("TASKCOMMANDTYPE")) {
					taskCommand.setType(strs[((Integer)value).intValue()]);
				}
				if (property.equals("TASKCOMMANDENABLE")) {
					taskCommand.setIsEnabled(((Boolean) value).booleanValue() ? "true" : "false");
				}
				if (property.equals("TASKCOMMANDVERIFY")) {
					taskCommand.setIsVerification(((Boolean) value).booleanValue() ? "true" : "false");
				}
				if (property.equals("TASKCOMMANDSAVEDATA")) {
					taskCommand.setIsSaveData(((Boolean) value).booleanValue() ? "true" : "false");
				}
				if (property.equals("TASKCOMMANDSIMULATION")) {
					taskCommand.setIsSimulationRun(((Boolean) value).booleanValue() ? "true" : "false");
				}
				if (property.equals("TASKCOMMANDCOMMAND")) {
					taskCommand.setCommand((String) value);
				}
				if (property.equals("TASKCOMMANDCMD")) {
					taskCommand.setCmd((String) value);
				}
				if (property.equals("TASKCOMMANDFILTER")) {
					taskCommand.setFilter((String) value);
				}
				tableViewer_2.refresh();
				updateButtons();
			}

			public Object getValue(Object element, String property) {
				
				TaskCommandDef taskCommand = (TaskCommandDef) element;

				if (property.equals("TASKCOMMANDID")) {
					return taskCommand.getId();
				}
				if (property.equals("TASKCOMMANDNAME")) {
					return taskCommand.getName();
				}
				if (property.equals("TASKCOMMANDTYPE")) {
					return TASKCOMMANDTYPE_MAP.get(taskCommand.getType());
				}
				if (property.equals("TASKCOMMANDENABLE")) {
					if (taskCommand.getIsEnabled().equals("true"))
						return true;
					if (taskCommand.getIsEnabled().equals("false"))
						return false;
				}
				if (property.equals("TASKCOMMANDVERIFY")) {
					if (taskCommand.getIsVerification().equals("true"))
						return true;
					if (taskCommand.getIsVerification().equals("false"))
						return false;
				}
				if (property.equals("TASKCOMMANDSAVEDATA")) {
					if (taskCommand.getIsSaveData().equals("true"))
						return true;
					if (taskCommand.getIsSaveData().equals("false"))
						return false;
				}
				if (property.equals("TASKCOMMANDSIMULATION")) {
					if (taskCommand.getIsSimulationRun().equals("true"))
						return true;
					if (taskCommand.getIsSimulationRun().equals("false"))
						return false;
				}
				if (property.equals("TASKCOMMANDCOMMAND")) {
					return taskCommand.getCommand();
				}
				if (property.equals("TASKCOMMANDCMD")) {
					return taskCommand.getCmd();
				}
				if (property.equals("TASKCOMMANDFILTER")) {
					return taskCommand.getFilter();
				}
				return null;
			}

			public boolean canModify(Object element, String property) {
				
				return element instanceof TaskCommandDef;
			}
		});
	}

	private void createCellModifier4() {
		final CellEditor[] cellEditor = new CellEditor[table_3.getColumnCount()];
		cellEditor[0] = new TextCellEditor(table_3);
		cellEditor[1] = new TextCellEditor(table_3);
		cellEditor[2] = new CheckboxCellEditor(table_3);
		cellEditor[3] = new CheckboxCellEditor(table_3);
		cellEditor[4] = new TextCellEditor(table_3);
		cellEditor[5] = new TextCellEditor(table_3);
		cellEditor[6] = new TextCellEditor(table_3);
		cellEditor[7] = new FixFlowInputCellEditor(table_3, getShell());
		//cellEditor[8] = new TextCellEditor(table_3);
		
		cellEditor[8] = new GroupDefinitionCellEditor(table_3, getShell());


		tableViewer_3.setColumnProperties(new String[] { "GROUPID", "GROUPNAME", "ISENABLED", "ISDISPLAYUSER", "GROUPIDFIELD", "GROUPNAMEFIELD", "SUPGROUPIDFIELD", "SQLTEXT","GROUPDEFINITIONIMPL" });
		tableViewer_3.setCellEditors(cellEditor);
		tableViewer_3.setCellModifier(new ICellModifier() {

			public void modify(Object element, String property, Object value) {
				
				TableItem tableitem = (TableItem) element;
				GroupInfo groupInfo = (GroupInfo) tableitem.getData();
				if (property.equals("GROUPID")) {
					groupInfo.setGroupId((String) value);
				}
				if (property.equals("GROUPNAME")) {
					groupInfo.setGroupName((String) value);
				}
				if (property.equals("ISENABLED")) {
					groupInfo.setIsEnabled(((Boolean) value).booleanValue() ? "true" : "false");
				}
				if (property.equals("ISDISPLAYUSER")) {
					groupInfo.setIsDisplayUser(((Boolean) value).booleanValue() ? "true" : "false");
				}
				if (property.equals("GROUPIDFIELD")) {
					groupInfo.setGroupIdField((String) value);
				}
				if (property.equals("GROUPNAMEFIELD")) {
					groupInfo.setGroupNameField((String) value);
				}
				if (property.equals("SUPGROUPIDFIELD")) {
					groupInfo.setSupGroupIdField((String) value);
				}
				if (property.equals("SQLTEXT")) {
					groupInfo.setSqlText((String) value);
				}
				if (property.equals("GROUPDEFINITIONIMPL")) {
					groupInfo.setGroupDefinitionImpl((String) value);
				}
				

				tableViewer_3.refresh();
				updateButtons();
			}

			public Object getValue(Object element, String property) {
				
				GroupInfo groupInfo = (GroupInfo) element;

				if (property.equals("GROUPID")) {
					return groupInfo.getGroupId();
				}
				if (property.equals("GROUPNAME")) {
					return groupInfo.getGroupName();
				}
				if (property.equals("ISENABLED")) {
					if (groupInfo.getIsEnabled().equals("true"))
						return true;
					if (groupInfo.getIsEnabled().equals("false"))
						return false;
				}
				if (property.equals("ISDISPLAYUSER")) {
					if (groupInfo.getIsDisplayUser().equals("true"))
						return true;
					if (groupInfo.getIsDisplayUser().equals("false"))
						return false;
				}
				if (property.equals("GROUPIDFIELD")) {
					return groupInfo.getGroupIdField();
				}
				if (property.equals("GROUPNAMEFIELD")) {
					return groupInfo.getGroupNameField();
				}
				if (property.equals("SUPGROUPIDFIELD")) {
					return groupInfo.getSupGroupIdField();
				}
				if (property.equals("SQLTEXT")) {
					((FixFlowInputCellEditor) cellEditor[7]).setText(groupInfo.getSqlText());
					return groupInfo.getSqlText();
				}
				if (property.equals("GROUPDEFINITIONIMPL")) {
					return groupInfo.getGroupDefinitionImpl();
				}
				
				
				
				return null;
			}

			public boolean canModify(Object element, String property) {
				
				return element instanceof GroupInfo;
			}
		});
	}

	private void createCellModifier5() {

		CellEditor[] cellEditor = new CellEditor[table_4.getColumnCount()];
		cellEditor[0] = new TextCellEditor(table_4);
		cellEditor[1] = new TextCellEditor(table_4);

		tableViewer_4.setColumnProperties(new String[] { "MAILNAME", "MAILADDRESS" });
		tableViewer_4.setCellEditors(cellEditor);
		tableViewer_4.setCellModifier(new ICellModifier() {

			public void modify(Object element, String property, Object value) {
				TableItem tableitem = (TableItem) element;
				MailInfo mailInfo = (MailInfo) tableitem.getData();
				
				Object oldValue = getValue(mailInfo, property);
				if (String.valueOf(value).equals(String.valueOf(oldValue)))
					return;
				
				if (property.equals("MAILNAME")) {
					mailInfo.setMailName((String) value);
					combo_2.setText("");
				}
				if (property.equals("MAILADDRESS")) {
					mailInfo.setMailAddress((String) value);
				}
				tableViewer.refresh();
				/*
				 * //清空缓存 DataVarUtil.dataVariableConfig = null;
				 */
				updateButtons();
			}

			public Object getValue(Object element, String property) {
				
				MailInfo mailInfo = (MailInfo) element;

				if (property.equals("MAILNAME")) {
					return mailInfo.getMailName();
				}
				if (property.equals("MAILADDRESS")) {
					return mailInfo.getMailAddress();
				}
				return null;
			}

			public boolean canModify(Object element, String property) {
				
				return element instanceof MailInfo;
			}
		});
	}

	
	private void createCellModifier6() {
		final CellEditor[] cellEditor = new CellEditor[table_5.getColumnCount()];
		cellEditor[0] = null;
		cellEditor[1] = null;
		cellEditor[2] = null;
		cellEditor[3] = new ExpandClassCellEditor(table_5, getShell());
		cellEditor[4] = new FixFlowInputCellEditor(table_5, getShell());

		tableViewer_5.setColumnProperties(new String[] { "CLASSID", "CLASSNAME", "CLASSINTERFACE", "CLASSIMPL", "REMARKS" });
		tableViewer_5.setCellEditors(cellEditor);
		tableViewer_5.setCellModifier(new ICellModifier() {

			public void modify(Object element, String property, Object value) {
				
				TableItem tableitem = (TableItem) element;
				ExpandClass expandClass = (ExpandClass) tableitem.getData();
				if (property.equals("CLASSID")) {
					expandClass.setClassId((String) value);
				}
				if (property.equals("CLASSNAME")) {
					expandClass.setClassName((String) value);
				}
				if (property.equals("CLASSINTERFACE")) {
					expandClass.setClassInterface((String) value);
				}
				if (property.equals("CLASSIMPL")) {
					expandClass.setClassImpl((String) value);
				}
				if (property.equals("REMARKS")) {
					expandClass.setRemarks((String) value);
				}
				tableViewer_5.refresh();
			}

			public Object getValue(Object element, String property) {
				
				ExpandClass expandClass = (ExpandClass) element;

				if (property.equals("CLASSID")) {
					return expandClass.getClassId();
				}
				if (property.equals("CLASSNAME")) {
					return expandClass.getClassName();
				}
				if (property.equals("CLASSINTERFACE")) {
					return expandClass.getClassInterface();
				}
				if (property.equals("CLASSIMPL")) {
					return expandClass.getClassImpl();
				}
				if (property.equals("REMARKS")) {
					((FixFlowInputCellEditor)cellEditor[4]).setText(expandClass.getRemarks());
					return expandClass.getRemarks();
				}
				return null;
			}

			public boolean canModify(Object element, String property) {
				
				return element instanceof ExpandClass;
			}
		});
	}
	
	private void createCellModifier7() {
		CellEditor[] cellEditor = new CellEditor[table_6.getColumnCount()];
		cellEditor[0] = null;
		cellEditor[1] = new GroupDefinitionCellEditor(table_6, getShell());

		tableViewer_6.setCellEditors(cellEditor);
		tableViewer_6.setCellModifier(new ICellModifier() {

			public void modify(Object element, String property, Object value) {
				
				TableItem tableitem = (TableItem) element;
				ScriptLanguage scriptLanguage = (ScriptLanguage) tableitem.getData();
				if (property.equals("SCRIPTID")) {
					scriptLanguage.setId((String) value);
				}
				if (property.equals("SCRIPTCLASSIMPL")) {
					scriptLanguage.setClassImpl((String) value);
				}
				tableViewer_6.refresh();
			}

			public Object getValue(Object element, String property) {
				
				ScriptLanguage scriptLanguage = (ScriptLanguage) element;

				if (property.equals("SCRIPTID")) {
					return scriptLanguage.getId();
				}
				if (property.equals("SCRIPTCLASSIMPL")) {
					return scriptLanguage.getClassImpl();
				}
				return null;
			}

			public boolean canModify(Object element, String property) {
				
				return element instanceof ScriptLanguage;
			}
		});
	}
	
	private void createCellModifier8() {
		CellEditor[] cellEditor = new CellEditor[excutertable.getColumnCount()];
		cellEditor[0] = new TextCellEditor(excutertable);
		cellEditor[1] = new TextCellEditor(excutertable);
		cellEditor[2] = new TaskCommandCellEditor(excutertable, getShell());

		excutertableViewer.setColumnProperties(new String[] { "EXCUTERID", "EXCUTERNAME","EXCUTER" });
		excutertableViewer.setCellEditors(cellEditor);
		excutertableViewer.setCellModifier(new ICellModifier() {

			public void modify(Object element, String property, Object value) {
				
				TableItem tableitem = (TableItem) element;
				ExpandCmd expandCmd = (ExpandCmd) tableitem.getData();
				if (property.equals("EXCUTERID")) {
					expandCmd.setId((String) value);
				}
				if (property.equals("EXCUTERNAME")) {
					expandCmd.setName((String) value);
				}
				if (property.equals("EXCUTER")) {
					expandCmd.setCmd((String) value);
				}
				excutertableViewer.refresh();
				updateButtons();
			}

			public Object getValue(Object element, String property) {
				
				ExpandCmd expandCmd = (ExpandCmd) element;

				if (property.equals("EXCUTERID")) {
					return expandCmd.getId();
				}
				if (property.equals("EXCUTERNAME")) {
					return expandCmd.getName();
				}
				if (property.equals("EXCUTER")) {
					return expandCmd.getCmd();
				}
				return null;
			}

			public boolean canModify(Object element, String property) {
				
				return element instanceof ExpandCmd;
			}
		});
	}
	
	private void createCellModifierPriority() {
		CellEditor[] cellEditor = new CellEditor[table_7.getColumnCount()];
		cellEditor[0] = new TextCellEditor(table_7);
		cellEditor[1] = new TextCellEditor(table_7);
		cellEditor[2] = new TextCellEditor(table_7);
		cellEditor[3] = new TextCellEditor(table_7);
		cellEditor[4] = new TextCellEditor(table_7);

		prioritytableViewer.setColumnProperties(new String[] { "PRIORITYID", "PRIORITYNAME", "PRIORITYVALUE", "PRIORITYCOLOR", "PRIORITYREMARK" });
		prioritytableViewer.setCellEditors(cellEditor);
		prioritytableViewer.setCellModifier(new ICellModifier() {

			public void modify(Object element, String property, Object value) {
				
				TableItem tableitem = (TableItem) element;
				Priority priority = (Priority) tableitem.getData();
				if (property.equals("PRIORITYID")) {
					priority.setId((String) value);
				}
				if (property.equals("PRIORITYNAME")) {
					priority.setName((String) value);
				}
				if (property.equals("PRIORITYVALUE")) {
					Pattern pattern = Pattern.compile("^[0-9]*$");
					Matcher matcher = null;
					matcher = pattern.matcher((String) value);
					if(!matcher.matches() || Integer.valueOf((String) value)<0||Integer.valueOf((String) value)>100) {
						MessageDialog.openInformation(null, "提示", "输入的值需要在0-100的范围内的数字");
						return;
					}
					priority.setValue((String) value);
				}
				if (property.equals("PRIORITYCOLOR")) {
					priority.setColor((String) value);
				}
				if (property.equals("PRIORITYREMARK")) {
					priority.setRemark((String) value);
				}
				prioritytableViewer.refresh();
				updateButtons();
			}

			public Object getValue(Object element, String property) {
				
				Priority priority = (Priority) element;

				if (property.equals("PRIORITYID")) {
					return priority.getId();
				}
				if (property.equals("PRIORITYNAME")) {
					return priority.getName();
				}
				if (property.equals("PRIORITYVALUE")) {
					return priority.getValue();
				}
				if (property.equals("PRIORITYCOLOR")) {
					return priority.getColor();
				}
				if (property.equals("PRIORITYREMARK")) {
					return priority.getRemark();
				}
				return null;
			}

			public boolean canModify(Object element, String property) {
				
				return element instanceof Priority;
			}
		});
	}
	
	private void createCellModifierAssignPolicy() {
		CellEditor[] cellEditor = new CellEditor[assignPolicytable.getColumnCount()];
		cellEditor[0] = new TextCellEditor(assignPolicytable);
		cellEditor[1] = new TextCellEditor(assignPolicytable);
		cellEditor[2] = new TaskCommandCellEditor(assignPolicytable, getShell());
		cellEditor[3] = new FixFlowInputCellEditor(assignPolicytable, getShell());

		assignPolicyConfigtableViewer.setColumnProperties(new String[] { "ASSIGNPOLICYID", "ASSIGNPOLICYIDNAME","ASSIGNPOLICYIDIMPL","ASSIGNPOLICYREMARKS" });
		assignPolicyConfigtableViewer.setCellEditors(cellEditor);
		assignPolicyConfigtableViewer.setCellModifier(new ICellModifier() {

			public void modify(Object element, String property, Object value) {
				
				TableItem tableitem = (TableItem) element;
				AssignPolicy assignPolicy = (AssignPolicy) tableitem.getData();
				if (property.equals("ASSIGNPOLICYID")) {
					assignPolicy.setId((String) value);
				}
				if (property.equals("ASSIGNPOLICYIDNAME")) {
					assignPolicy.setName((String) value);
				}
				if (property.equals("ASSIGNPOLICYIDIMPL")) {
					assignPolicy.setClassImpl((String) value);
				}
				if (property.equals("ASSIGNPOLICYREMARKS")) {
					assignPolicy.setRemarks((String) value);
				}
				assignPolicyConfigtableViewer.refresh();
				updateButtons();
			}

			public Object getValue(Object element, String property) {
				
				AssignPolicy assignPolicy = (AssignPolicy) element;

				if (property.equals("ASSIGNPOLICYID")) {
					return assignPolicy.getId();
				}
				if (property.equals("ASSIGNPOLICYIDNAME")) {
					return assignPolicy.getName();
				}
				if (property.equals("ASSIGNPOLICYIDIMPL")) {
					return assignPolicy.getClassImpl();
				}
				if (property.equals("ASSIGNPOLICYREMARKS")) {
					return assignPolicy.getRemarks();
				}
				return null;
			}

			public boolean canModify(Object element, String property) {
				
				return element instanceof AssignPolicy;
			}
		});
	}

	
	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		button_13 = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		button_13.setText("确定");
		Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
		button_1.setText("取消");
		m_bindingContext = initDataBindings();
		getDatabaseID(tableViewer);
		getMailName(tableViewer_4);
		
		if(btnCheckButton.getSelection()) {
			validate();
		}else{
			FixFlowConfigDialog.this.setErrorMessage(null);
			button_13.setEnabled(true);
		}
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(680, 650);
	}

	/**
	 * 设置按钮可用性
	 */
	public void updateButtons() {
		Object selectedPage = ((IStructuredSelection) tableViewer.getSelection()).getFirstElement();
		Object selectedPage1 = ((IStructuredSelection) tableViewer_1.getSelection()).getFirstElement();
		Object selectedPage2 = ((IStructuredSelection) tableViewer_2.getSelection()).getFirstElement();
		Object selectedPage3 = ((IStructuredSelection) tableViewer_3.getSelection()).getFirstElement();
		Object selectedPage4 = ((IStructuredSelection) tableViewer_4.getSelection()).getFirstElement();
		Object selectedPage5 = ((IStructuredSelection) excutertableViewer.getSelection()).getFirstElement();
		Object selectedPage6 = ((IStructuredSelection) prioritytableViewer.getSelection()).getFirstElement();
		Object selectedPage7 = ((IStructuredSelection) assignPolicyConfigtableViewer.getSelection()).getFirstElement();
		button_1.setEnabled(selectedPage != null);
		button_2.setEnabled(selectedPage != null);
		button_4.setEnabled(selectedPage1 != null);
		button_6.setEnabled(selectedPage2 != null);
		button_8.setEnabled(selectedPage3 != null);
		button_9.setEnabled(selectedPage3 != null && ((GroupInfo) selectedPage3).getIsEnabled().equals("true"));
		button_11.setEnabled(selectedPage4 != null);
		button_12.setEnabled(selectedPage4 != null);
		btnNewButton_2.setEnabled(selectedPage6 !=null);
		btnNewButton_4.setEnabled(selectedPage7 !=null);
		excuterDeleteButton.setEnabled(selectedPage5 !=null );
	}
	
	/**
	 * 校验文本框是否为空
	 */
	public void validate() {
		StringBuffer sb = new StringBuffer();
		if(text_3.getText() == null || text_3.getText().equals("")) {
			sb.append("服务器地址为空");
		}
		if(text_4.getText() == null || text_4.getText().equals("")){
			if(sb.length()>0)
				sb.append(",");
			sb.append("端口号为空");
		}
		if(text_5.getText() == null || text_5.getText().equals("")){
			if(sb.length()>0)
				sb.append(",");
			sb.append("消息名称为空");
		}
		if(text_6.getText() == null || text_6.getText().equals("")){
			if(sb.length()>0)
				sb.append(",");
			sb.append("信号名称为空");
		}
		
		if(sb.length()>0){
			FixFlowConfigDialog.this.setErrorMessage(sb.toString());
			button_13.setEnabled(false);
		}else{
			FixFlowConfigDialog.this.setErrorMessage(null);
			button_13.setEnabled(true);
		}
	}

	@Override
	protected void okPressed() {
		//记录归档周期的quazte表达式
		convertQzCode();
		
		
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("xml", new XMIResourceFactoryImpl());

		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();

		// Create a resource
		XMIResource resource = (XMIResource) resSet.createResource(URI.createFileURI(FixFlowConfigUtil.getFixFlowConfigXMLPath()));
		resource.setEncoding("UTF-8");

		resource.getContents().add(fixFlowConfig);

		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 刷新工程
		ConnectorUtil.refreshProject("fixflow-expand");

		// 清空缓存
		FixFlowConfigUtil.fixFlowConfig = null;

		super.okPressed();
	}
	
	/**
	  * convertQzCode(记录归档周期的quazte表达式)
	  * @Title: convertQzCode
	  * @Description: TODO
	  * @param     设定文件
	  * @return void    返回类型
	  * @throws
	 */
	private void convertQzCode() {
		if(timeTxt.getText().trim().equals("")) {
			return;
		}
		String[] timeArray = timeTxt.getText().trim().split(":");
		String expCode = timeArray[2] + " " + timeArray[1] + " " + timeArray[0];
		
		if(dayRadioBtn.getSelection()) {	//选择每天
			expCode += " * * ?";
		} 
		else if(weekRadioBtn.getSelection()) {	//选择每周	
			expCode += " ? * " + fixFlowConfig.getPigeonholeConfig().getWeek();
		} 
		else {	//选择每月
			expCode += dayTxt.getText().trim() + " * ?";
		}
		fixFlowConfig.getPigeonholeConfig().setCode(expCode);
	}

	/**
	 * 往combo里面加入数据库ID
	 * 
	 * @param tableViewer
	 */
	@SuppressWarnings("unchecked")
	public void getDatabaseID(TableViewer tableViewer) {
		for (DataBase dataBase : ((List<DataBase>) tableViewer.getInput())) {
			combo.add(dataBase.getId());
		}
	}
	
	/**
	 * 往combo里面加入邮件名称
	 * 
	 * @param tableViewer
	 */
	@SuppressWarnings("unchecked")
	public void getMailName(TableViewer tableViewer) {
		for (MailInfo mailInfo : ((List<MailInfo>) tableViewer_4.getInput())) {
			combo_2.add(mailInfo.getMailName());
		}
	}

	public void init() {
		if (fixFlowConfig.getDesignerOrgConfig().getAllUserInfo().getIsEnabled() != null && fixFlowConfig.getDesignerOrgConfig().getAllUserInfo().getIsEnabled().equals("true")) {
			button_7.setSelection(true);
		}
		if (fixFlowConfig.getEventSubscriptionConfig().getIsEnable() != null && fixFlowConfig.getEventSubscriptionConfig().getIsEnable().equals("true")) {
			btnCheckButton.setSelection(true);
		}
		if (fixFlowConfig.getInternationalizationConfig().getIsEnable() != null && fixFlowConfig.getInternationalizationConfig().getIsEnable().equals("true")) {
			i18nCheckButton.setSelection(true);
		}
		if (fixFlowConfig.getQuartzConfig().getIsEnable() != null && fixFlowConfig.getQuartzConfig().getIsEnable().equals("true")) {
			button_14.setSelection(true);
		}
		if (fixFlowConfig.getQuartzConfig().getIsDefaultConfig() != null && fixFlowConfig.getQuartzConfig().getIsDefaultConfig().equals("true")) {
			button_15.setSelection(true);
			combo_3.setEnabled(false);
		}
		if (fixFlowConfig.getPigeonholeConfig().getServerPath() == null
				|| (fixFlowConfig.getPigeonholeConfig().getServerPath() != null 
				&& fixFlowConfig.getPigeonholeConfig().getServerPath().equals(""))) {
			fixFlowConfig.getPigeonholeConfig().setServerPath("http://<127.0.0.1>:<8080>/<xxweb>");
		}
		if (fixFlowConfig.getPigeonholeConfig().getPdfPath() == null
				|| (fixFlowConfig.getPigeonholeConfig().getPdfPath() != null 
				&& fixFlowConfig.getPigeonholeConfig().getPdfPath().equals(""))) {
			fixFlowConfig.getPigeonholeConfig().setPdfPath("<C:>/<xxfolder>/<xxfolder>");
		}
		if (fixFlowConfig.getPigeonholeConfig().getIsEnable() != null 
				&& fixFlowConfig.getPigeonholeConfig().getIsEnable().equals("true")) {
			pEnabelBtn.setSelection(true);
		}
		if (fixFlowConfig.getPigeonholeConfig().getFrequency() != null) {
			if(fixFlowConfig.getPigeonholeConfig().getFrequency().equals("day")) {
				dayRadioBtn.setSelection(true);
				
				stackLayout.topControl = nullComposite;
				parentComposite.layout();
			} else if(fixFlowConfig.getPigeonholeConfig().getFrequency().equals("week")) {
				weekRadioBtn.setSelection(true);
				
				stackLayout.topControl = weekComposite;
				parentComposite.layout();
				
				if(fixFlowConfig.getPigeonholeConfig().getWeek() != null) {
					switch (Integer.parseInt(fixFlowConfig.getPigeonholeConfig().getWeek())) {
					case 1:
						weekOneBtn.setSelection(true);
						break;
					case 2:
						weekTwoBtn.setSelection(true);
						break;
					case 3:
						weekThreeBtn.setSelection(true);
						break;
					case 4:
						weekFourBtn.setSelection(true);
						break;
					case 5:
						weekFiveBtn.setSelection(true);
						break;
					case 6:
						weekSixBtn.setSelection(true);
						break;
					case 7:
						weekSevenBtn.setSelection(true);
						break;
					default:
						weekOneBtn.setSelection(true);
						break;
					}
				}
			} else {
				monthRadioBtn.setSelection(true);
				
				stackLayout.topControl = dayComposite;
				parentComposite.layout();
			}
		}
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = EMFObservables.observeMaps(listContentProvider.getKnownElements(), new EStructuralFeature[]{Literals.DATA_BASE__ID, Literals.DATA_BASE__NAME, Literals.DATA_BASE__DBTYPE});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableList fixFlowConfigDataBaseObserveList = EMFProperties.list(FeaturePath.fromList(Literals.FIX_FLOW_CONFIG__DATA_BASE_CONFIG, Literals.DATA_BASE_CONFIG__DATA_BASE)).observe(fixFlowConfig);
		tableViewer.setInput(fixFlowConfigDataBaseObserveList);
		//
		ObservableListContentProvider listContentProvider_1 = new ObservableListContentProvider();
		IObservableMap[] observeMaps_1 = EMFObservables.observeMaps(listContentProvider_1.getKnownElements(), new EStructuralFeature[]{Literals.GROUP_DEFINITION__ID, Literals.GROUP_DEFINITION__NAME, Literals.GROUP_DEFINITION__GROUP_DEFINITION_IMPL});
		tableViewer_1.setLabelProvider(new ObservableMapLabelProvider(observeMaps_1));
		tableViewer_1.setContentProvider(listContentProvider_1);
		//
		IObservableList fixFlowConfigGroupDefinitionObserveList = EMFProperties.list(FeaturePath.fromList(Literals.FIX_FLOW_CONFIG__GROUP_DEFINITION_CONFIG, Literals.GROUP_DEFINITION_CONFIG__GROUP_DEFINITION)).observe(fixFlowConfig);
		tableViewer_1.setInput(fixFlowConfigGroupDefinitionObserveList);
		//
		ObservableListContentProvider listContentProvider_2 = new ObservableListContentProvider();
		IObservableMap[] observeMaps_2 = EMFObservables.observeMaps(listContentProvider_2.getKnownElements(), new EStructuralFeature[]{Literals.TASK_COMMAND_DEF__ID, Literals.TASK_COMMAND_DEF__NAME, Literals.TASK_COMMAND_DEF__TYPE, Literals.TASK_COMMAND_DEF__IS_ENABLED, Literals.TASK_COMMAND_DEF__IS_VERIFICATION, Literals.TASK_COMMAND_DEF__IS_SAVE_DATA, Literals.TASK_COMMAND_DEF__IS_SIMULATION_RUN, Literals.TASK_COMMAND_DEF__COMMAND, Literals.TASK_COMMAND_DEF__CMD, Literals.TASK_COMMAND_DEF__FILTER});
		tableViewer_2.setLabelProvider(new ObservableMapLabelProvider(observeMaps_2));
		tableViewer_2.setContentProvider(listContentProvider_2);
		//
		IObservableList fixFlowConfigTaskCommandObserveList = EMFProperties.list(FeaturePath.fromList(Literals.FIX_FLOW_CONFIG__TASK_COMMAND_CONFIG, Literals.TASK_COMMAND_CONFIG__TASK_COMMAND_DEF)).observe(fixFlowConfig);
		tableViewer_2.setInput(fixFlowConfigTaskCommandObserveList);
		//
		IObservableValue comboObserveTextObserveWidget = SWTObservables.observeText(combo);
		IObservableValue fixFlowConfigSelectedObserveValue = EMFProperties.value(FeaturePath.fromList(Literals.FIX_FLOW_CONFIG__DATA_BASE_CONFIG, Literals.DATA_BASE_CONFIG__SELECTED)).observe(fixFlowConfig);
		bindingContext.bindValue(comboObserveTextObserveWidget, fixFlowConfigSelectedObserveValue, null, null);
		//
		IObservableValue combo_1ObserveTextObserveWidget = SWTObservables.observeText(combo_1);
		IObservableValue fixFlowConfigModeObserveValue = EMFProperties.value(FeaturePath.fromList(Literals.FIX_FLOW_CONFIG__DATA_BASE_CONFIG, Literals.DATA_BASE_CONFIG__MODE)).observe(fixFlowConfig);
		bindingContext.bindValue(combo_1ObserveTextObserveWidget, fixFlowConfigModeObserveValue, null, null);
		//
		ObservableListContentProvider listContentProvider_3 = new ObservableListContentProvider();
		IObservableMap[] observeMaps_3 = EMFObservables.observeMaps(listContentProvider_3.getKnownElements(), new EStructuralFeature[]{Literals.GROUP_INFO__GROUP_ID, Literals.GROUP_INFO__GROUP_NAME, Literals.GROUP_INFO__IS_ENABLED, Literals.GROUP_INFO__IS_DISPLAY_USER, Literals.GROUP_INFO__GROUP_ID_FIELD, Literals.GROUP_INFO__GROUP_NAME_FIELD, Literals.GROUP_INFO__SUP_GROUP_ID_FIELD, Literals.GROUP_INFO__SQL_TEXT, Literals.GROUP_INFO__GROUP_DEFINITION_IMPL});
		tableViewer_3.setLabelProvider(new ObservableMapLabelProvider(observeMaps_3));
		tableViewer_3.setContentProvider(listContentProvider_3);
		//
		IObservableList fixFlowConfigGroupInfoObserveList = EMFProperties.list(FeaturePath.fromList(Literals.FIX_FLOW_CONFIG__DESIGNER_ORG_CONFIG, Literals.DESIGNER_ORG_CONFIG__GROUP_INFO)).observe(fixFlowConfig);
		tableViewer_3.setInput(fixFlowConfigGroupInfoObserveList);
		//
		IObservableValue text_1ObserveTextObserveWidget = SWTObservables.observeText(text_1, SWT.Modify);
		IObservableValue fixFlowConfigUserIdFieldObserveValue = EMFProperties.value(FeaturePath.fromList(Literals.FIX_FLOW_CONFIG__DESIGNER_ORG_CONFIG, Literals.DESIGNER_ORG_CONFIG__ALL_USER_INFO, Literals.ALL_USER_INFO__USER_ID_FIELD)).observe(fixFlowConfig);
		bindingContext.bindValue(text_1ObserveTextObserveWidget, fixFlowConfigUserIdFieldObserveValue, null, null);
		//
		IObservableValue text_2ObserveTextObserveWidget = SWTObservables.observeText(text_2, SWT.Modify);
		IObservableValue fixFlowConfigUserNameFieldObserveValue = EMFProperties.value(FeaturePath.fromList(Literals.FIX_FLOW_CONFIG__DESIGNER_ORG_CONFIG, Literals.DESIGNER_ORG_CONFIG__ALL_USER_INFO, Literals.ALL_USER_INFO__USER_NAME_FIELD)).observe(fixFlowConfig);
		bindingContext.bindValue(text_2ObserveTextObserveWidget, fixFlowConfigUserNameFieldObserveValue, null, null);
		//
		IObservableValue textObserveTextObserveWidget = SWTObservables.observeText(text, SWT.Modify);
		IObservableValue fixFlowConfigSqlTextObserveValue = EMFProperties.value(FeaturePath.fromList(Literals.FIX_FLOW_CONFIG__DESIGNER_ORG_CONFIG, Literals.DESIGNER_ORG_CONFIG__ALL_USER_INFO, Literals.ALL_USER_INFO__SQL_TEXT)).observe(fixFlowConfig);
		bindingContext.bindValue(textObserveTextObserveWidget, fixFlowConfigSqlTextObserveValue, null, null);
		//
		ObservableListContentProvider listContentProvider_4 = new ObservableListContentProvider();
		IObservableMap[] observeMaps_4 = EMFObservables.observeMaps(listContentProvider_4.getKnownElements(), new EStructuralFeature[]{Literals.MAIL_INFO__MAIL_NAME, Literals.MAIL_INFO__MAIL_ADDRESS});
		tableViewer_4.setLabelProvider(new ObservableMapLabelProvider(observeMaps_4));
		tableViewer_4.setContentProvider(listContentProvider_4);
		//
		IObservableList fixFlowConfigMailInfoObserveList = EMFProperties.list(FeaturePath.fromList(Literals.FIX_FLOW_CONFIG__SYS_MAIL_CONFIG, Literals.SYS_MAIL_CONFIG__MAIL_INFO)).observe(fixFlowConfig);
		tableViewer_4.setInput(fixFlowConfigMailInfoObserveList);
		//
		IObservableValue combo_2ObserveTextObserveWidget = SWTObservables.observeText(combo_2);
		IObservableValue fixFlowConfigSelectedObserveValue_1 = EMFProperties.value(FeaturePath.fromList(Literals.FIX_FLOW_CONFIG__SYS_MAIL_CONFIG, Literals.SYS_MAIL_CONFIG__SELECTED)).observe(fixFlowConfig);
		bindingContext.bindValue(combo_2ObserveTextObserveWidget, fixFlowConfigSelectedObserveValue_1, null, null);
		//
		ObservableListContentProvider listContentProvider_5 = new ObservableListContentProvider();
		IObservableMap[] observeMaps_5 = EMFObservables.observeMaps(listContentProvider_5.getKnownElements(), new EStructuralFeature[]{Literals.EXPAND_CLASS__CLASS_ID, Literals.EXPAND_CLASS__CLASS_NAME, Literals.EXPAND_CLASS__CLASS_INTERFACE, Literals.EXPAND_CLASS__CLASS_IMPL, Literals.EXPAND_CLASS__REMARKS});
		tableViewer_5.setLabelProvider(new ObservableMapLabelProvider(observeMaps_5));
		tableViewer_5.setContentProvider(listContentProvider_5);
		//
		IObservableList fixFlowConfigExpandClassObserveList = EMFProperties.list(FeaturePath.fromList(Literals.FIX_FLOW_CONFIG__EXPAND_CLASS_CONFIG, Literals.EXPAND_CLASS_CONFIG__EXPAND_CLASS)).observe(fixFlowConfig);
		tableViewer_5.setInput(fixFlowConfigExpandClassObserveList);
		//
		IObservableValue text_3ObserveTextObserveWidget = SWTObservables.observeText(text_3, SWT.Modify);
		IObservableValue fixFlowConfigServerAddressObserveValue = EMFProperties.value(FeaturePath.fromList(Literals.FIX_FLOW_CONFIG__EVENT_SUBSCRIPTION_CONFIG, Literals.EVENT_SUBSCRIPTION_CONFIG__SERVER_ADDRESS)).observe(fixFlowConfig);
		bindingContext.bindValue(text_3ObserveTextObserveWidget, fixFlowConfigServerAddressObserveValue, null, null);
		//
		IObservableValue text_5ObserveTextObserveWidget = SWTObservables.observeText(text_5, SWT.Modify);
		IObservableValue fixFlowConfigMessageInfoObserveValue = EMFProperties.value(FeaturePath.fromList(Literals.FIX_FLOW_CONFIG__EVENT_SUBSCRIPTION_CONFIG, Literals.EVENT_SUBSCRIPTION_CONFIG__MESSAGE_INFO)).observe(fixFlowConfig);
		bindingContext.bindValue(text_5ObserveTextObserveWidget, fixFlowConfigMessageInfoObserveValue, null, null);
		//
		IObservableValue text_6ObserveTextObserveWidget = SWTObservables.observeText(text_6, SWT.Modify);
		IObservableValue fixFlowConfigSignalInfoObserveValue = EMFProperties.value(FeaturePath.fromList(Literals.FIX_FLOW_CONFIG__EVENT_SUBSCRIPTION_CONFIG, Literals.EVENT_SUBSCRIPTION_CONFIG__SIGNAL_INFO)).observe(fixFlowConfig);
		bindingContext.bindValue(text_6ObserveTextObserveWidget, fixFlowConfigSignalInfoObserveValue, null, null);
		//
		IObservableValue text_4ObserveTextObserveWidget = SWTObservables.observeText(text_4, SWT.Modify);
		IObservableValue fixFlowConfigServerPortObserveValue = EMFProperties.value(FeaturePath.fromList(Literals.FIX_FLOW_CONFIG__EVENT_SUBSCRIPTION_CONFIG, Literals.EVENT_SUBSCRIPTION_CONFIG__SERVER_PORT)).observe(fixFlowConfig);
		bindingContext.bindValue(text_4ObserveTextObserveWidget, fixFlowConfigServerPortObserveValue, null, null);
		//
		IObservableValue combo_3ObserveTextObserveWidget = SWTObservables.observeText(combo_3);
		IObservableValue fixFlowConfigDataBaseIdObserveValue = EMFProperties.value(FeaturePath.fromList(Literals.FIX_FLOW_CONFIG__QUARTZ_CONFIG, Literals.QUARTZ_CONFIG__DATA_BASE_ID)).observe(fixFlowConfig);
		bindingContext.bindValue(combo_3ObserveTextObserveWidget, fixFlowConfigDataBaseIdObserveValue, null, null);
		//
		ObservableListContentProvider listContentProvider_6 = new ObservableListContentProvider();
		IObservableMap[] observeMaps_6 = PojoObservables.observeMaps(listContentProvider_6.getKnownElements(), ScriptLanguage.class, new String[]{"id", "classImpl"});
		tableViewer_6.setLabelProvider(new ObservableMapLabelProvider(observeMaps_6));
		tableViewer_6.setContentProvider(listContentProvider_6);
		//
		IObservableList scriptLanguageFixFlowConfiggetScriptLanguageConfigObserveList = PojoProperties.list("scriptLanguage").observe(fixFlowConfig.getScriptLanguageConfig());
		tableViewer_6.setInput(scriptLanguageFixFlowConfiggetScriptLanguageConfigObserveList);
		//
		IObservableValue observeTextCombo_4ObserveWidget = WidgetProperties.text().observe(combo_4);
		IObservableValue fixFlowConfigSelectedObserveValue_2 = EMFProperties.value(FeaturePath.fromList(Literals.FIX_FLOW_CONFIG__SCRIPT_LANGUAGE_CONFIG, Literals.SCRIPT_LANGUAGE_CONFIG__SELECTED)).observe(fixFlowConfig);
		bindingContext.bindValue(observeTextCombo_4ObserveWidget, fixFlowConfigSelectedObserveValue_2, null, null);
		//
		IObservableValue text_7ObserveTextObserveWidget = SWTObservables.observeText(text_7, SWT.Modify);
		IObservableValue fixFlowConfigFolderPathObserveValue = EMFProperties.value(FeaturePath.fromList(Literals.FIX_FLOW_CONFIG__INTERNATIONALIZATION_CONFIG, Literals.INTERNATIONALIZATION_CONFIG__FOLDER_PATH)).observe(fixFlowConfig);
		bindingContext.bindValue(text_7ObserveTextObserveWidget, fixFlowConfigFolderPathObserveValue, null, null);
		//
		IObservableValue text_8ObserveTextObserveWidget = SWTObservables.observeText(serverPathTxt, SWT.Modify);
		IObservableValue fixFlowConfigServerPathObserveValue = EMFProperties.value(FeaturePath.fromList(Literals.FIX_FLOW_CONFIG__PIGEONHOLE_CONFIG, Literals.PIGEONHOLE_CONFIG__SERVER_PATH)).observe(fixFlowConfig);
		bindingContext.bindValue(text_8ObserveTextObserveWidget, fixFlowConfigServerPathObserveValue, null, null);
		//
		IObservableValue observeTextText_9ObserveWidget = WidgetProperties.text(SWT.Modify).observe(pdfPathTxt);
		IObservableValue fixFlowConfigPdfPathObserveValue = EMFProperties.value(FeaturePath.fromList(Literals.FIX_FLOW_CONFIG__PIGEONHOLE_CONFIG, Literals.PIGEONHOLE_CONFIG__PDF_PATH)).observe(fixFlowConfig);
		bindingContext.bindValue(observeTextText_9ObserveWidget, fixFlowConfigPdfPathObserveValue, null, null);
		//
		IObservableValue observeTextDayTxtObserveWidget = WidgetProperties.text(SWT.Modify).observe(dayTxt);
		IObservableValue fixFlowConfigMonthObserveValue = EMFProperties.value(FeaturePath.fromList(Literals.FIX_FLOW_CONFIG__PIGEONHOLE_CONFIG, Literals.PIGEONHOLE_CONFIG__MONTH)).observe(fixFlowConfig);
		bindingContext.bindValue(observeTextDayTxtObserveWidget, fixFlowConfigMonthObserveValue, null, null);
		//
		IObservableValue observeTextTimeTxtObserveWidget = WidgetProperties.text(SWT.Modify).observe(timeTxt);
		IObservableValue fixFlowConfigTimeObserveValue = EMFProperties.value(FeaturePath.fromList(Literals.FIX_FLOW_CONFIG__PIGEONHOLE_CONFIG, Literals.PIGEONHOLE_CONFIG__TIME)).observe(fixFlowConfig);
		bindingContext.bindValue(observeTextTimeTxtObserveWidget, fixFlowConfigTimeObserveValue, null, null);
		//
		ObservableListContentProvider listContentProvider_7 = new ObservableListContentProvider();
		IObservableMap[] observeMaps_7 = EMFObservables.observeMaps(listContentProvider_7.getKnownElements(), new EStructuralFeature[]{Literals.EXPAND_CMD__ID, Literals.EXPAND_CMD__NAME, Literals.EXPAND_CMD__CMD});
		excutertableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps_7));
		excutertableViewer.setContentProvider(listContentProvider_7);
		//
		IObservableList fixFlowConfigExpandCmdObserveList = EMFProperties.list(FeaturePath.fromList(Literals.FIX_FLOW_CONFIG__EXPAND_CMD_CONFIG, Literals.EXPAND_CMD_CONFIG__EXPAND_CMD)).observe(fixFlowConfig);
		excutertableViewer.setInput(fixFlowConfigExpandCmdObserveList);
		//
		ObservableListContentProvider listContentProvider_8 = new ObservableListContentProvider();
		IObservableMap[] observeMaps_8 = EMFObservables.observeMaps(listContentProvider_8.getKnownElements(), new EStructuralFeature[]{Literals.PRIORITY__ID, Literals.PRIORITY__NAME, Literals.PRIORITY__VALUE, Literals.PRIORITY__COLOR, Literals.PRIORITY__REMARK});
		prioritytableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps_8));
		prioritytableViewer.setContentProvider(listContentProvider_8);
		//
		IObservableList fixFlowConfigPriorityObserveList = EMFProperties.list(FeaturePath.fromList(Literals.FIX_FLOW_CONFIG__PRIORITY_CONFIG, Literals.PRIORITY_CONFIG__PRIORITY)).observe(fixFlowConfig);
		prioritytableViewer.setInput(fixFlowConfigPriorityObserveList);
		//
		ObservableListContentProvider listContentProvider_9 = new ObservableListContentProvider();
		IObservableMap[] observeMaps_9 = EMFObservables.observeMaps(listContentProvider_9.getKnownElements(), new EStructuralFeature[]{Literals.ASSIGN_POLICY__ID, Literals.ASSIGN_POLICY__NAME, Literals.ASSIGN_POLICY__CLASS_IMPL, Literals.ASSIGN_POLICY__REMARKS});
		assignPolicyConfigtableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps_9));
		assignPolicyConfigtableViewer.setContentProvider(listContentProvider_9);
		//
		IObservableList fixFlowConfigAssignPolicyObserveList = EMFProperties.list(FeaturePath.fromList(Literals.FIX_FLOW_CONFIG__ASSIGN_POLICY_CONFIG, Literals.ASSIGN_POLICY_CONFIG__ASSIGN_POLICY)).observe(fixFlowConfig);
		assignPolicyConfigtableViewer.setInput(fixFlowConfigAssignPolicyObserveList);
		//
		return bindingContext;
	}
}
