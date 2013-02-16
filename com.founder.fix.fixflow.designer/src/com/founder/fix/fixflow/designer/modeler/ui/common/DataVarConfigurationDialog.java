package com.founder.fix.fixflow.designer.modeler.ui.common;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Label;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import com.founder.fix.bpmn2extensions.variableconfig.DataTypeDef;
import com.founder.fix.bpmn2extensions.variableconfig.DataVariableConfig;
import com.founder.fix.bpmn2extensions.variableconfig.FixFlowDataVariable;
import com.founder.fix.bpmn2extensions.variableconfig.Type;
import com.founder.fix.bpmn2extensions.variableconfig.VariableconfigFactory;

import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EStructuralFeature;
import com.founder.fix.bpmn2extensions.variableconfig.VariableconfigPackage.Literals;
import com.founder.fix.fixflow.designer.util.DataVarUtil;
import com.founder.fix.fixflow.designer.util.EMFUtil;

import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;

public class DataVarConfigurationDialog extends TitleAreaDialog {
	private DataBindingContext m_bindingContext;
	private Table table;
	private Table table_1;
	private DataVariableConfig dataVariableConfig;
	private TableViewer tableViewer;
	private TableViewer tableViewer_1;
	private Button removeButton;
	private Button removebutton;
	private Button editbutton;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public DataVarConfigurationDialog(Shell parentShell) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(SWT.CLOSE | SWT.RESIZE | SWT.TITLE | SWT.PRIMARY_MODAL);
		//读取Menu的xml
		
		
		//XMIResource resource = (XMIResource) new ResourceSetImpl().getResource(URI.createFileURI(DataVarUtil.getDataVariableXMLPath()), true);
		dataVariableConfig = EMFUtil.getFixFlowDataVariableConfig(DataVarUtil.getDataVariableXMLPath());
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("数据变量配置");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		GridLayout gl_container = new GridLayout(2, false);
		gl_container.marginRight = 15;
		gl_container.marginLeft = 15;
		gl_container.marginHeight = 15;
		gl_container.marginBottom = 15;
		container.setLayout(gl_container);
		GridData gd_container = new GridData(GridData.FILL_BOTH);
		gd_container.widthHint = 523;
		gd_container.grabExcessHorizontalSpace = false;
		container.setLayoutData(gd_container);
		
		Label dataVariableDataTypeLabel = new Label(container, SWT.NONE);
		dataVariableDataTypeLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		dataVariableDataTypeLabel.setText("数据类型维护");
		
		tableViewer = new TableViewer(container, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				updateButtons();
			}
		});
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		GridData gd_table = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_table.heightHint = 200;
		table.setLayoutData(gd_table);
		
		table.addListener(SWT.MeasureItem, new Listener() {
			public void handleEvent(Event event) {
				// 设置行高度
				event.height = (int) Math.floor(event.gc.getFontMetrics().getHeight() * 1.5);
			}
		});
		
		TableColumn datavardatatypecolumn = new TableColumn(table, SWT.NONE);
		datavardatatypecolumn.setWidth(120);
		datavardatatypecolumn.setText("类型名称");
		datavardatatypecolumn.setMoveable(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("显示名称");
		
		TableColumn datavartypevaluecolumn = new TableColumn(table, SWT.NONE);
		datavartypevaluecolumn.setWidth(250);
		datavartypevaluecolumn.setText("类型全称");
		datavartypevaluecolumn.setMoveable(true);
		
		Composite composite = new Composite(container, SWT.NONE);
		GridLayout gl_composite = new GridLayout(1, false);
		gl_composite.verticalSpacing = 0;
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		gl_composite.horizontalSpacing = 0;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		Button addButton = new Button(composite, SWT.NONE);
		addButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		addButton.setText("添加");
		addButton.addListener(SWT.Selection, new Listener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				DataTypeDef dataTypeDef = VariableconfigFactory.eINSTANCE.createDataTypeDef();
				dataTypeDef.setId("类型名称" + ((List<DataTypeDef>)tableViewer.getInput()).size());
				dataTypeDef.setName("显示名称" + ((List<DataTypeDef>)tableViewer.getInput()).size());
				dataTypeDef.setTypeValue("类型全称 " + ((List<DataTypeDef>)tableViewer.getInput()).size());
				((List<DataTypeDef>)tableViewer.getInput()).add(dataTypeDef);
				tableViewer.refresh();
			}
		});
		
		removeButton = new Button(composite, SWT.NONE);
		removeButton.setText("移除");
		removeButton.addListener(SWT.Selection, new Listener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
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
					DataTypeDef col = (DataTypeDef) objs[i];
					((List<DataTypeDef>)tableViewer.getInput()).remove(col);
				}
				tableViewer.refresh();
			}
		});
		
		Label dataVariableTypeLabel = new Label(container, SWT.NONE);
		dataVariableTypeLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		dataVariableTypeLabel.setText("数据变量类型");
		
		createCellModifier1();
		
		tableViewer_1 = new TableViewer(container, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		tableViewer_1.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				updateButtons();
			}
		});
		table_1 = tableViewer_1.getTable();
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		table_1.addListener(SWT.MeasureItem, new Listener() {
			public void handleEvent(Event event) {
				// 设置行高度
				event.height = (int) Math.floor(event.gc.getFontMetrics().getHeight() * 1.5);
			}
		});
		
		TableColumn datavartypecolumn = new TableColumn(table_1, SWT.NONE);
		datavartypecolumn.setWidth(500);
		datavartypecolumn.setText("类型名");
		datavartypecolumn.setMoveable(true);
		
		Composite composite_1 = new Composite(container, SWT.NONE);
		GridLayout gl_composite_1 = new GridLayout(1, false);
		gl_composite_1.verticalSpacing = 0;
		gl_composite_1.marginWidth = 0;
		gl_composite_1.marginHeight = 0;
		gl_composite_1.horizontalSpacing = 0;
		composite_1.setLayout(gl_composite_1);
		composite_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		Button addbutton = new Button(composite_1, SWT.NONE);
		addbutton.setText("添加");
		addbutton.addListener(SWT.Selection, new Listener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				Type type = VariableconfigFactory.eINSTANCE.createType();
				type.setId(java.util.UUID.randomUUID().toString());
				type.setName("类型名称" + ((List<Type>)tableViewer_1.getInput()).size());
				((List<Type>)tableViewer_1.getInput()).add(type);
				tableViewer_1.refresh();
			}
		});
		
		editbutton = new Button(composite_1, SWT.NONE);
		editbutton.setText("编辑");
		editbutton.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				IStructuredSelection iStructuredSelection = (IStructuredSelection) tableViewer_1.getSelection();
				
				if(iStructuredSelection != null) {
					Type type = (Type) iStructuredSelection.getFirstElement();
					
					FixFlowDataVariable fixFlowDataVariable = getFixFlowDataVariableByTypeId(type.getId());
					
					FixFlowDataVariableDialog ffdvd = new FixFlowDataVariableDialog(getShell(), fixFlowDataVariable, type.getId());
					ffdvd.setBlockOnOpen(true);
					if(ffdvd != null && ffdvd.open() == InputDialog.OK) {
						dataVariableConfig.getFixFlowDataVariable().remove(fixFlowDataVariable);
						dataVariableConfig.getFixFlowDataVariable().add(ffdvd.getFixFlowDataVariable());
					}
				}
				
			}
		});
		
		removebutton = new Button(composite_1, SWT.NONE);
		removebutton.setText("移除");
		removebutton.addListener(SWT.Selection, new Listener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
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
					Type col = (Type) objs[i];
					((List<Type>)tableViewer_1.getInput()).remove(col);
					dataVariableConfig.getFixFlowDataVariable().remove(getFixFlowDataVariableByTypeId(col.getId()));
				}
				tableViewer_1.refresh();
			}
		});

		createCellModifier2();
		setMessage("配置数据变量", IMessageProvider.INFORMATION);
		updateButtons();
		
		return area;
	}
	
	private void createCellModifier1() {
		CellEditor[] cellEditor = new CellEditor[table.getColumnCount()];
		cellEditor[0] = new TextCellEditor(table);
		cellEditor[1] = new TextCellEditor(table);
		cellEditor[2] = new TextCellEditor(table);

		tableViewer.setColumnProperties(new String[]{"DATAVARTYPEID", "DATAVARTYPENAME", "DATAVARTYPEVALUE"});
		tableViewer.setCellEditors(cellEditor);
		tableViewer.setCellModifier(new ICellModifier() {

			public void modify(Object element, String property, Object value) {
				// TODO Auto-generated method stub
				TableItem tableitem = (TableItem) element;
				DataTypeDef dataTypeDef = (DataTypeDef) tableitem.getData();
				if (property.equals("DATAVARTYPEID")) {
					dataTypeDef.setId((String) value);
				}
				if (property.equals("DATAVARTYPENAME")) {
					dataTypeDef.setName((String) value);
				}
				if (property.equals("DATAVARTYPEVALUE")) {
					dataTypeDef.setTypeValue((String) value);
				}
				tableViewer.refresh();
				//清空缓存
				DataVarUtil.dataVariableConfig = null;
				updateButtons();
			}

			public Object getValue(Object element, String property) {
				// TODO Auto-generated method stub
				DataTypeDef dataTypeDef = (DataTypeDef) element;

				if (property.equals("DATAVARTYPEID")) {
					return dataTypeDef.getId();
				}
				if (property.equals("DATAVARTYPENAME")) {
					return dataTypeDef.getName();
				}
				if (property.equals("DATAVARTYPEVALUE")) {
					return dataTypeDef.getTypeValue();
				}
				return null;
			}

			public boolean canModify(Object element, String property) {
				// TODO Auto-generated method stub
				return element instanceof DataTypeDef;
			}
		});
	}

	private void createCellModifier2() {
		CellEditor[] cellEditor = new CellEditor[table_1.getColumnCount()];
		cellEditor[0] = new TextCellEditor(table_1);

		tableViewer_1.setColumnProperties(new String[]{"DATAVARIABLETYPE"});
		tableViewer_1.setCellEditors(cellEditor);
		tableViewer_1.setCellModifier(new ICellModifier() {

			public void modify(Object element, String property, Object value) {
				// TODO Auto-generated method stub
				TableItem tableitem = (TableItem) element;
				Type type = (Type) tableitem.getData();
				if (property.equals("DATAVARIABLETYPE")) {
					type.setName((String) value);
				}
				tableViewer_1.refresh();
				updateButtons();
			}

			public Object getValue(Object element, String property) {
				// TODO Auto-generated method stub
				Type type = (Type)element;

				if (property.equals("DATAVARIABLETYPE")) {
					return type.getName();
				}
				return null;
			}

			public boolean canModify(Object element, String property) {
				// TODO Auto-generated method stub
				return element instanceof Type;
			}
		});
	}


	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		button.setText("确定");
		Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		button_1.setText("取消");
		m_bindingContext = initDataBindings();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(597, 657);
	}

	/**
	 * 设置按钮可用性
	 */
	public void updateButtons() {
		Object selectedPage = ((IStructuredSelection)tableViewer.getSelection()).getFirstElement();
		Object selectedPage1 = ((IStructuredSelection)tableViewer_1.getSelection()).getFirstElement();
		removeButton.setEnabled(selectedPage != null);
		removebutton.setEnabled(selectedPage1 != null);
		editbutton.setEnabled(selectedPage1 != null);
	}
	
	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("xml", new XMIResourceFactoryImpl());
		
		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();
		
		// Create a resource
		XMIResource resource = (XMIResource) resSet.createResource(URI.createFileURI(DataVarUtil.getDataVariableXMLPath()));
		resource.setEncoding("UTF-8");
		
		resource.getContents().add(dataVariableConfig);
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.okPressed();
	}

	public DataVariableConfig getDataVariableConfig() {
		return dataVariableConfig;
	}

	public void setDataVariableConfig(DataVariableConfig dataVariableConfig) {
		this.dataVariableConfig = dataVariableConfig;
	}
	
	public FixFlowDataVariable getFixFlowDataVariableByTypeId(String typeId) {
		for(FixFlowDataVariable fixFlowDataVariable : dataVariableConfig.getFixFlowDataVariable()) {
			if(fixFlowDataVariable.getType().equals(typeId)) {
				return fixFlowDataVariable;
			}
		}
		return null;
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableMap[] observeMaps = EMFObservables.observeMaps(listContentProvider.getKnownElements(), new EStructuralFeature[]{Literals.DATA_TYPE_DEF__ID, Literals.DATA_TYPE_DEF__NAME, Literals.DATA_TYPE_DEF__TYPE_VALUE});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		//
		IObservableList dataVariableConfigDataTypeDefObserveList = EMFProperties.list(FeaturePath.fromList(Literals.DATA_VARIABLE_CONFIG__DATA_VARIABLE_DATA_TYPE, Literals.DATA_VARIABLE_DATA_TYPE__DATA_TYPE_DEF)).observe(dataVariableConfig);
		tableViewer.setInput(dataVariableConfigDataTypeDefObserveList);
		//
		ObservableListContentProvider listContentProvider_1 = new ObservableListContentProvider();
		tableViewer_1.setContentProvider(listContentProvider_1);
		//
		IObservableMap[] observeMaps_1 = EMFObservables.observeMaps(listContentProvider_1.getKnownElements(), new EStructuralFeature[]{Literals.TYPE__NAME});
		tableViewer_1.setLabelProvider(new ObservableMapLabelProvider(observeMaps_1));
		//
		IObservableList dataVariableConfigTypeObserveList = EMFProperties.list(FeaturePath.fromList(Literals.DATA_VARIABLE_CONFIG__DATA_VARIABLE_TYPE, Literals.DATA_VARIABLE_TYPE__TYPE)).observe(dataVariableConfig);
		tableViewer_1.setInput(dataVariableConfigTypeObserveList);
		//
		return bindingContext;
	}
}
