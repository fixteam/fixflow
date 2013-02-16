package com.founder.fix.fixflow.designer.modeler.ui.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
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
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Label;

import com.founder.fix.bpmn2extensions.variableconfig.DataTypeDef;
import com.founder.fix.bpmn2extensions.variableconfig.DataVariableDef;
import com.founder.fix.bpmn2extensions.variableconfig.FixFlowDataVariable;
import com.founder.fix.bpmn2extensions.variableconfig.VariableconfigFactory;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import com.founder.fix.fixflow.designer.util.DataVarUtil;

import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;

public class FixFlowDataVariableDialog extends TitleAreaDialog {
	private DataBindingContext m_bindingContext;
	private Table table;
	private FixFlowDataVariable fixFlowDataVariable;
	private TableViewer tableViewer;
	private List<DataVariableDef> dataVariableDefs;
	private Button button;
	private Button removeButton;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public FixFlowDataVariableDialog(Shell parentShell, FixFlowDataVariable fixFlowDataVariable, String typeId) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(SWT.RESIZE | SWT.TITLE|SWT.PRIMARY_MODAL);
		this.fixFlowDataVariable = VariableconfigFactory.eINSTANCE.createFixFlowDataVariable();
		this.fixFlowDataVariable.setType(typeId);
		
		dataVariableDefs = new ArrayList<DataVariableDef>();
		
		if(fixFlowDataVariable != null){
			for(DataVariableDef dataVariableDef : fixFlowDataVariable.getDataVariableDef()){
				dataVariableDefs.add(dataVariableDef);
			}
		}
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
		gl_container.marginBottom = 15;
		gl_container.marginRight = 15;
		gl_container.marginHeight = 15;
		gl_container.marginLeft = 15;
		container.setLayout(gl_container);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label datavarLabel = new Label(container, SWT.NONE);
		datavarLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		datavarLabel.setText("数据变量");
		
		tableViewer = new TableViewer(container, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				button.setEnabled(isRight());
			}
		});
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		table.addListener(SWT.MeasureItem, new Listener() {
			public void handleEvent(Event event) {
				// 设置行高度
				event.height = (int) Math.floor(event.gc.getFontMetrics().getHeight() * 1.5);
			}
		});

		TableColumn datavarnamecolumn = new TableColumn(table, SWT.NONE);
		datavarnamecolumn.setWidth(100);
		datavarnamecolumn.setText("名称");
		datavarnamecolumn.setMoveable(true);
		
		TableColumn datavartypecolumn = new TableColumn(table, SWT.NONE);
		datavartypecolumn.setWidth(100);
		datavartypecolumn.setText("类型");
		datavartypecolumn.setMoveable(true);
		
		TableColumn datavarexpColumn = new TableColumn(table, SWT.NONE);
		datavarexpColumn.setMoveable(true);
		datavarexpColumn.setWidth(150);
		datavarexpColumn.setText("表达式");
		
		TableColumn datavardescColumn = new TableColumn(table, SWT.NONE);
		datavardescColumn.setMoveable(true);
		datavardescColumn.setWidth(150);
		datavardescColumn.setText("描述");
		
		Composite composite = new Composite(container, SWT.NONE);
		GridLayout gl_composite = new GridLayout(1, false);
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		gl_composite.horizontalSpacing = 0;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		Button addButton = new Button(composite, SWT.NONE);
		addButton.setText("添加");
		addButton.addListener(SWT.Selection, new Listener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				DataVariableDef dataVariableDef = VariableconfigFactory.eINSTANCE.createDataVariableDef();
				dataVariableDef.setName("名称" + ((List<DataVariableDef>)tableViewer.getInput()).size());
				dataVariableDef.setDataType("类型" + ((List<DataVariableDef>)tableViewer.getInput()).size());
				dataVariableDef.setValue("表达式" + ((List<DataVariableDef>)tableViewer.getInput()).size());
				dataVariableDef.setDoc("描述" + ((List<DataVariableDef>)tableViewer.getInput()).size());
				((List<DataVariableDef>)tableViewer.getInput()).add(dataVariableDef);
				tableViewer.refresh();
				button.setEnabled(isRight());
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
					DataVariableDef col = (DataVariableDef) objs[i];
					((List<DataVariableDef>)tableViewer.getInput()).remove(col);
				}
				tableViewer.refresh();
				button.setEnabled(isRight());
			}
		});
		
		createCellModifier();
		
		return area;
	}
	
	private void createCellModifier() {
		final CellEditor[] cellEditor = new CellEditor[table.getColumnCount()];
		cellEditor[0] = new TextCellEditor(table);
		cellEditor[1] = new ComboBoxViewerCellEditor(table, SWT.READ_ONLY);
		((ComboBoxViewerCellEditor)cellEditor[1]).setContenProvider(new ArrayContentProvider());
		((ComboBoxViewerCellEditor)cellEditor[1]).setLabelProvider(new ViewerLabelProvider());
		((ComboBoxViewerCellEditor)cellEditor[1]).setInput(DataVarUtil.getFixFlowDataVariableConfig().getDataVariableDataType().getDataTypeDef());
		cellEditor[2] = new FixFlowInputCellEditor(table, getShell());
		cellEditor[3] = new FixFlowInputCellEditor(table, getShell());

		tableViewer.setColumnProperties(new String[]{"DATAVARNAME", "DATAVARTYPE", "DATAVAREXP", "DATAVARDOC"});
		tableViewer.setCellEditors(cellEditor);
		tableViewer.setCellModifier(new ICellModifier() {

			public void modify(Object element, String property, Object value) {
				// TODO Auto-generated method stub
				TableItem tableitem = (TableItem) element;
				DataVariableDef dataVariableDef = (DataVariableDef) tableitem.getData();
				if (property.equals("DATAVARNAME")) {
					dataVariableDef.setName((String) value);
				}
				if (property.equals("DATAVARTYPE")) {
					if(value == null){
						dataVariableDef.setDataType("");
					}else{
						dataVariableDef.setDataType(((DataTypeDef)value).getTypeValue());
					}
				}
				if (property.equals("DATAVAREXP")) {
					dataVariableDef.setValue((String) value);
				}
				if (property.equals("DATAVARDOC")) {
					dataVariableDef.setDoc((String) value);
				}
				tableViewer.refresh();
				button.setEnabled(isRight());
			}

			public Object getValue(Object element, String property) {
				// TODO Auto-generated method stub
				DataVariableDef dataVariableDef = (DataVariableDef) element;

				if (property.equals("DATAVARNAME")) {
					return dataVariableDef.getName();
				}
				if (property.equals("DATAVARTYPE")) {
					return DataVarUtil.getDataTypeDefByDataVariableDataType(dataVariableDef.getDataType());
				}
				if (property.equals("DATAVAREXP")) {
					((FixFlowInputCellEditor)cellEditor[2]).setText(dataVariableDef.getValue());
					return dataVariableDef.getValue();
				}
				if (property.equals("DATAVARDOC")) {
					((FixFlowInputCellEditor)cellEditor[3]).setText(dataVariableDef.getDoc());
					return dataVariableDef.getDoc();
				}
				return null;
			}

			public boolean canModify(Object element, String property) {
				// TODO Auto-generated method stub
				return element instanceof DataVariableDef;
			}
		});
	}
	
	private class ViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}

		public String getText(Object element) {
			DataTypeDef dataTypeDef = (DataTypeDef) element;
			return dataTypeDef.getName();
		}
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		button.setEnabled(false);
		button.setText("确定");
		Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		button_1.setText("取消");
		m_bindingContext = initDataBindings();
		button.setEnabled(isRight());
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(552, 471);
	}

	public FixFlowDataVariable getFixFlowDataVariable() {
		return fixFlowDataVariable;
	}

	public void setFixFlowDataVariable(FixFlowDataVariable fixFlowDataVariable) {
		this.fixFlowDataVariable = fixFlowDataVariable;
	}

	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		fixFlowDataVariable.getDataVariableDef().addAll(dataVariableDefs);
		super.okPressed();
	}
	
	/**
	 * 设置按钮可用性
	 * @return
	 */
	public boolean isRight(){
		Object selectedPage = ((IStructuredSelection)tableViewer.getSelection()).getFirstElement();
		removeButton.setEnabled(selectedPage != null);
		
		boolean allDataVariableNameUnique = isAllDataVariableNameUnique();
		
		StringBuffer sb = new StringBuffer();
		if(!allDataVariableNameUnique) {
			sb.append("数据变量名不能重复");
		}
				
		if(sb.length()>0){
			this.setErrorMessage(sb.toString());
			return false;
		}else{
			this.setErrorMessage(null);
			return true;
		}
	}
	
	/**
	 * 验证所有数据变量名称唯一性
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean isAllDataVariableNameUnique(){
		Set<String> dataVariableDefs = new HashSet<String>();
		if(tableViewer.getInput() != null){
			for (DataVariableDef dataVariableDef : ((List<DataVariableDef>)tableViewer.getInput())) {
				if (dataVariableDefs.contains(dataVariableDef.getName())) {
					return false;
				} else {
					dataVariableDefs.add(dataVariableDef.getName());
				}
			}
		}
		return true;
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableMap[] observeMaps = PojoObservables.observeMaps(listContentProvider.getKnownElements(), DataVariableDef.class, new String[]{"name", "dataType", "value", "doc"});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		//
		WritableList writableList = new WritableList(dataVariableDefs, DataVariableDef.class);
		tableViewer.setInput(writableList);
		//
		return bindingContext;
	}
}
