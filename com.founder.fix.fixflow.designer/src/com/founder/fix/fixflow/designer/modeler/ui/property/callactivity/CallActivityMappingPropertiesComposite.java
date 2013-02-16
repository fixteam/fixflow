package com.founder.fix.fixflow.designer.modeler.ui.property.callactivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.CallActivity;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.founder.fix.bpmn2extensions.fixflow.DataSourceToSubProcessMapping;
import com.founder.fix.bpmn2extensions.fixflow.DataVariable;
import com.founder.fix.bpmn2extensions.fixflow.DataVariableMapping;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowFactory;
import com.founder.fix.bpmn2extensions.fixflow.SubProcessToDataSourceMapping;
import com.founder.fix.fixflow.designer.modeler.ui.property.AbstractFixFlowBpmn2PropertiesComposite;
import com.founder.fix.fixflow.designer.modeler.ui.property.SectionBpmnElement;

public class CallActivityMappingPropertiesComposite extends AbstractFixFlowBpmn2PropertiesComposite {
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private TableViewer tableViewer1;
	private TableViewer tableViewer2;
	private List<DataVariableMapping> list1;
	private List<DataVariableMapping> list2;
	private Map<String, String> process;
	private Map<String, String> subProcess;
	private Map<String, String> processFan;
	private Map<String, String> subProcessFan;
	private String[] processArray;
	private String[] subProcessArray;
	private CellEditor[] cellEditors;
	private CellEditor[] cellEditors2;
	private DataSourceToSubProcessMapping dataSourceToSubProcessMapping;
	private SubProcessToDataSourceMapping subProcessToDataSourceMapping;
	
	public CallActivityMappingPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
		list1 = new ArrayList<DataVariableMapping>();
		list2 = new ArrayList<DataVariableMapping>();
		process = new HashMap<String, String>();
		subProcess = new HashMap<String, String>();
		processFan = new HashMap<String, String>();
		subProcessFan = new HashMap<String, String>();
	}

	public CallActivityMappingPropertiesComposite(Composite parent, int style) {
		super(parent, style);
		((GridData) attributesSection.getLayoutData()).grabExcessVerticalSpace = false;
	}
	
	/**
	  * initData(初始化数据)
	  * @Title: initData
	  * @Description: TODO
	  * @param @param be    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	private void initData(final EObject be) {
		//获取主流程数据
		List<DataVariable> process = DataVariableUtil.getProcessDataVariable(SectionBpmnElement.process);
		if(process != null && process.size() > 0) {
			processArray = new String[process.size()];
			for (int i = 0; i < process.size(); i++) {
				DataVariable dataVariable = process.get(i);
				this.process.put(i + "", dataVariable.getId());
				this.processFan.put(dataVariable.getId(), i + "");
				processArray[i] = dataVariable.getId();
			}
		}
		
		//获取子流程数据
		try {
			List<DataVariable> subProcess = DataVariableUtil.getSubProcessDataVariable((CallActivity) be);
			if(subProcess != null && subProcess.size() > 0) {
				subProcessArray = new String[subProcess.size()];
				for (int i = 0; i < subProcess.size(); i++) {
					DataVariable dataVariable = subProcess.get(i);
					this.subProcess.put(i + "", dataVariable.getId());
					this.subProcessFan.put(dataVariable.getId(), i + "");
					subProcessArray[i] = dataVariable.getId();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openError(getShell(), "异常", e.toString());
		}
		
		//赋值
		((ComboBoxCellEditor) cellEditors[0]).setItems(processArray == null ? new String[0] : processArray);
		((ComboBoxCellEditor) cellEditors[1]).setItems(subProcessArray == null ? new String[0] : subProcessArray);
		((ComboBoxCellEditor) cellEditors2[1]).setItems(processArray == null ? new String[0] : processArray);
		((ComboBoxCellEditor) cellEditors2[0]).setItems(subProcessArray == null ? new String[0] : subProcessArray);
		
		//获取emf数据
		
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				dataSourceToSubProcessMapping = DataVariableUtil.getDataSourceToSubProcessMapping((CallActivity) be);
				subProcessToDataSourceMapping = DataVariableUtil.getSubProcessToDataSourceMapping((CallActivity) be);
		}});
		
		
		
		//填充list
		list1 = dataSourceToSubProcessMapping.getDataVariableMapping();
		list2 = subProcessToDataSourceMapping.getDataVariableMapping();
		
		tableViewer1.setInput(list1);
		tableViewer2.setInput(list2);
	}

	@Override
	public void createUI() {
		setLayout(new GridLayout(3, false));
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayout(new GridLayout(2, false));
		GridData gd_composite_1 = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_composite_1.heightHint = 200;
		composite_1.setLayoutData(gd_composite_1);
		formToolkit.adapt(composite_1);
		formToolkit.paintBordersFor(composite_1);
		
		Composite composite = new Composite(composite_1, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
		formToolkit.adapt(composite);
		formToolkit.paintBordersFor(composite);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		formToolkit.adapt(btnNewButton, true, true);
		btnNewButton.setText("自动映射");
		btnNewButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						//删除列表数据
						if(list1 != null && list1.size() > 0) {
							for (int i = 0; i < list1.size();) {
								list1.remove(i);
							}
						}
						if(list2 != null && list2.size() > 0) {
							for (int i = 0; i < list2.size();) {
								list2.remove(i);
							}
						}
						
						for (int i = 0; i < process.keySet().size(); i++) {
							String data = process.get(i + "");
							for (int j = 0; j < subProcess.keySet().size(); j++) {
								String subData = subProcess.get(j + "");
								if(data.equals(subData) || ("sub_" + data).equals(subData) ) {
									DataVariableMapping dataVariable = FixFlowFactory.eINSTANCE.createDataVariableMapping();
									dataVariable.setDataSourceId(data);
									dataVariable.setSubProcesId(subData);
									list1.add(dataVariable);
									
									dataVariable = FixFlowFactory.eINSTANCE.createDataVariableMapping();
									dataVariable.setDataSourceId(data);
									dataVariable.setSubProcesId(subData);
									list2.add(dataVariable);
									break;
								}
							}
						}
						
						tableViewer1.setInput(list1);
						tableViewer2.setInput(list2);
						tableViewer1.refresh();
						tableViewer2.refresh();
					}
				});
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		formToolkit.adapt(lblNewLabel, true, true);
		lblNewLabel.setText("名字相同或前缀都为‘sub_’的变量");
				
		Section sctnNewSection = formToolkit.createSection(composite_1, Section.TITLE_BAR);
		sctnNewSection.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sctnNewSection.setText("主流程映射子流程");
		formToolkit.paintBordersFor(sctnNewSection);
		
		ToolBar toolBar = new ToolBar(sctnNewSection, SWT.FLAT | SWT.RIGHT);
//		formToolkit.adapt(toolBar);
		formToolkit.paintBordersFor(toolBar);
		sctnNewSection.setTextClient(toolBar);
		
		ToolItem titem = new ToolItem(toolBar, SWT.NONE);
		titem.setToolTipText("添加");
		titem.setImage(PlatformUI.getWorkbench().getSharedImages()
				.getImage(ISharedImages.IMG_OBJ_ADD));
		titem.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						DataVariableMapping ele = FixFlowFactory.eINSTANCE.createDataVariableMapping();
						ele.setDataSourceId("");
						ele.setSubProcesId("");
						list1.add(ele);
						refreshContent(tableViewer1, list1, ele);
					}
				});
			}
		});
		
		titem = new ToolItem(toolBar, SWT.NONE);
		titem.setToolTipText("删除");
		titem.setImage(PlatformUI.getWorkbench().getSharedImages()
				.getImage(ISharedImages.IMG_TOOL_DELETE));
		titem.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						ISelection sel = tableViewer1.getSelection();
						if (sel == null)
							return;
						Object[] objs = ((IStructuredSelection) sel).toArray();
						if (objs == null || objs.length == 0)
							return;
						boolean b = MessageDialog.openConfirm(null, "警告", "你确认要删除吗？");
						if (!b)
							return;
						int selected = tableViewer1.getTable().getSelectionIndex();
						for (int i = 0; i < objs.length; i++) {
							list1.remove(objs[i]);
						}
						int num = 0; //删除后选中第几行数据的行数
						if(selected == 0) {
							num = selected;
						} else {
							num = selected - 1;
						}
						refreshContent(tableViewer1, list1, list1.size() > 0 ? list1.get(num) : null);
					}
				});
			}
		});
		
		tableViewer1 = new TableViewer(sctnNewSection, SWT.BORDER | SWT.FULL_SELECTION);
		final Table table = tableViewer1.getTable();
		table.setHeaderVisible(true);
		formToolkit.paintBordersFor(table);
		sctnNewSection.setClient(table);
		
		Section sctnNewSection_1 = formToolkit.createSection(composite_1, Section.TITLE_BAR);
		sctnNewSection_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sctnNewSection_1.setSize(446, 112);
		sctnNewSection_1.setText("子流程映射主流程");
		formToolkit.paintBordersFor(sctnNewSection_1);
		
		ToolBar toolBar_1 = new ToolBar(sctnNewSection_1, SWT.FLAT | SWT.RIGHT);
//		formToolkit.adapt(toolBar_1);
		formToolkit.paintBordersFor(toolBar_1);
		sctnNewSection_1.setTextClient(toolBar_1);
		
		titem = new ToolItem(toolBar_1, SWT.NONE);
		titem.setToolTipText("添加");
		titem.setImage(PlatformUI.getWorkbench().getSharedImages()
				.getImage(ISharedImages.IMG_OBJ_ADD));
		titem.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						DataVariableMapping ele = FixFlowFactory.eINSTANCE.createDataVariableMapping();
						ele.setDataSourceId("");
						ele.setSubProcesId("");
						list2.add(ele);
						refreshContent(tableViewer2, list2, ele);
					}
				});
			}
		});
		
		titem = new ToolItem(toolBar_1, SWT.NONE);
		titem.setToolTipText("删除");
		titem.setImage(PlatformUI.getWorkbench().getSharedImages()
				.getImage(ISharedImages.IMG_TOOL_DELETE));
		titem.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						ISelection sel = tableViewer2.getSelection();
						if (sel == null)
							return;
						Object[] objs = ((IStructuredSelection) sel).toArray();
						if (objs == null || objs.length == 0)
							return;
						boolean b = MessageDialog.openConfirm(null, "警告", "你确认要删除吗？");
						if (!b)
							return;
						int selected = tableViewer2.getTable().getSelectionIndex();
						for (int i = 0; i < objs.length; i++) {
							list2.remove(objs[i]);
						}
						int num = 0; //删除后选中第几行数据的行数
						if(selected == 0) {
							num = selected;
						} else {
							num = selected - 1;
						}
						refreshContent(tableViewer2, list2, list2.size() > 0 ? list2.get(num) : null);
					}
				});
			}
		});
		
		tableViewer2 = new TableViewer(sctnNewSection_1, SWT.BORDER | SWT.FULL_SELECTION);
		final Table table2 = tableViewer2.getTable();
		table2.setHeaderVisible(true);
		formToolkit.paintBordersFor(table2);
		sctnNewSection_1.setClient(table2);
		
		createTableViewer1(sctnNewSection, table);
		createTableViewer2(sctnNewSection_1, table2);
	}

	@Override
	public void createUIBindings(EObject be) {
		initData(be);
	}
	
	/**
	 * 创建表格1
	 * @param sctnNewSection
	 * @param table
	 */
	private void createTableViewer1(Section sctnNewSection, final Table table) {
		createAttrColumns1(sctnNewSection, tableViewer1);
		table.setLinesVisible(true);
		GridData gd = new GridData(SWT.LEFT, SWT.TOP, false, true);
		gd.heightHint = 150;
//		gd.widthHint = 400;
		// gd.minimumHeight = 400;
		table.setLayoutData(gd);
		table.addListener(SWT.MeasureItem, new Listener() { // TODO 修改行高度
			public void handleEvent(Event event) {
				event.width = table.getGridLineWidth();
				// 设置宽度
				event.height = (int) Math.floor(event.gc
						.getFontMetrics().getHeight() * 1.5);
			}
		});
		
		tableViewer1.setContentProvider(new ArrayContentProvider());
		cellEditors = new CellEditor[tableViewer1.getTable().getColumnCount()];
  		cellEditors[0] = new ComboBoxCellEditor(table, processArray == null ? new String[0] : processArray,
  				SWT.READ_ONLY);
  		cellEditors[1] = new ComboBoxCellEditor(table, subProcessArray == null ? new String[0] : subProcessArray,
  				SWT.READ_ONLY);
		
  		tableViewer1.setCellEditors(cellEditors);
  		tableViewer1.setColumnProperties(new String[] {"source", "sonSource"});
  		tableViewer1.setCellModifier(new ICellModifier() {
			public boolean canModify(Object element, String property) {
				return element instanceof DataVariableMapping;
			}
			public Object getValue(Object element, String property) {
				DataVariableMapping ele = (DataVariableMapping) element;
				if ("source".equals(property)) {
					return processFan.get(ele.getDataSourceId()) == null ?
							-1 : Integer.parseInt(processFan.get(ele.getDataSourceId()));
				}
				if ("sonSource".equals(property)) {
					return subProcessFan.get(ele.getSubProcesId()) == null ?
							-1 : Integer.parseInt(subProcessFan.get(ele.getSubProcesId()));
				}
				return null;
			}
			public void modify(Object element, String property, Object value) {
				DataVariableMapping ele = (DataVariableMapping) ((Item) element).getData();
				Object oldValue = getValue(ele, property);
				if (value == null || value.equals(oldValue))
					return;
				if ("source".equals(property)) {
					Integer defaultValue = 0;
					if (value != null)
						defaultValue = (Integer) value;
					setDataSourceId(ele, process.get(defaultValue == -1 ? "" : defaultValue.toString()));
				}
				if ("sonSource".equals(property)) {
					Integer defaultValue = 0;
					if (value != null)
						defaultValue = (Integer) value;
					setSubProcesId(ele, subProcess.get(defaultValue == -1 ? "" : defaultValue.toString()));
				}
				refreshContent(tableViewer1, list1, ele);
			}
		});

	}
	
	/**
	 * 创建表格2
	 * @param sctnNewSection_1
	 * @param table2
	 */
	private void createTableViewer2(Section sctnNewSection_1, final Table table2) {
		createAttrColumns2(sctnNewSection_1, tableViewer2);
//		table2.setHeaderVisible(true);
		table2.setLinesVisible(true);
		GridData gd = new GridData(SWT.LEFT, SWT.TOP, false, true);
		gd.heightHint = 150;
//		gd.widthHint = 400;
		// gd.minimumHeight = 400;
		table2.setLayoutData(gd);
		table2.addListener(SWT.MeasureItem, new Listener() { // TODO 修改行高度
			public void handleEvent(Event event) {
				event.width = table2.getGridLineWidth();
				// 设置宽度
				event.height = (int) Math.floor(event.gc
						.getFontMetrics().getHeight() * 1.5);
			}
		});
		
		tableViewer2.setContentProvider(new ArrayContentProvider());
		cellEditors2 = new CellEditor[tableViewer2.getTable().getColumnCount()];
		cellEditors2[0] = new ComboBoxCellEditor(table2, subProcessArray == null ? new String[0] : subProcessArray,
  				SWT.READ_ONLY);
		cellEditors2[1] = new ComboBoxCellEditor(table2, processArray == null ? new String[0] : processArray,
  				SWT.READ_ONLY);
		
  		tableViewer2.setCellEditors(cellEditors2);
  		tableViewer2.setColumnProperties(new String[] {"sonSource", "source"});
  		tableViewer2.setCellModifier(new ICellModifier() {
			public boolean canModify(Object element, String property) {
				return element instanceof DataVariableMapping;
			}
			public Object getValue(Object element, String property) {
				DataVariableMapping ele = (DataVariableMapping) element;
				if ("source".equals(property)) {
					return processFan.get(ele.getDataSourceId()) == null ?
							-1 : Integer.parseInt(processFan.get(ele.getDataSourceId()));
				}
				if ("sonSource".equals(property)) {
					return subProcessFan.get(ele.getSubProcesId()) == null ?
							-1 : Integer.parseInt(subProcessFan.get(ele.getSubProcesId()));
				}
				return null;
			}
			public void modify(Object element, String property, Object value) {
				DataVariableMapping ele = (DataVariableMapping) ((Item) element).getData();
				Object oldValue = getValue(ele, property);
				if (value == null || value.equals(oldValue))
					return;
				if ("source".equals(property)) {
					Integer defaultValue = 0;
					if (value != null)
						defaultValue = (Integer) value;
					setDataSourceId(ele, process.get(defaultValue == -1 ? "" : defaultValue.toString()));
				}
				if ("sonSource".equals(property)) {
					Integer defaultValue = 0;
					if (value != null)
						defaultValue = (Integer) value;
					setSubProcesId(ele, subProcess.get(defaultValue == -1 ? "" : defaultValue.toString()));
				}
				refreshContent(tableViewer2, list2, ele);
			}
		});

	}
	
	/**
	 * emf赋值
	 * @param mapping
	 * @param dataSourceId
	 */
	private void setDataSourceId(final DataVariableMapping mapping, 
			final String dataSourceId) {
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				mapping.setDataSourceId(dataSourceId);
			}
		});
	}
	
	/**
	 * emf赋值
	 * @param mapping
	 * @param subProcesId
	 */
	private void setSubProcesId(final DataVariableMapping mapping, 
			final String subProcesId) {
		@SuppressWarnings("restriction")
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				mapping.setSubProcesId(subProcesId);
			}
		});
	}
	
	/**
	 * 刷新
	 * @param tableViewer
	 * @param list
	 * @param selection
	 */
	private void refreshContent(TableViewer tableViewer, List<DataVariableMapping> list,
			Object selection) {
		tableViewer.setInput(list);
		tableViewer.refresh();
		if (selection != null) {
			tableViewer.setSelection(new StructuredSelection(
					new Object[] { selection }), true);
		}
	}

	private static void createAttrColumns1(final Composite parent,
			final TableViewer viewer) {
		TableViewerColumn col = createTableViewerColumn(viewer,
				"源数据", 180, 0);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				DataVariableMapping p = (DataVariableMapping) element;
				return p.getDataSourceId();
			}
		});
		
		col = createTableViewerColumn(viewer,
				"子流程数据", 180, 0);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				DataVariableMapping p = (DataVariableMapping) element;
				return p.getSubProcesId();
			}
		});
	}
	
	private static void createAttrColumns2(final Composite parent,
			final TableViewer viewer) {
		TableViewerColumn col = createTableViewerColumn(viewer,
				"子流程数据", 180, 0);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				DataVariableMapping p = (DataVariableMapping) element;
				return p.getSubProcesId();
			}
		});
		
		col = createTableViewerColumn(viewer,
				"源数据", 180, 0);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				DataVariableMapping p = (DataVariableMapping) element;
				return p.getDataSourceId();
			}
		});
	}
	
	private static TableViewerColumn createTableViewerColumn(
			TableViewer viewer, String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
				SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}

}
