package com.founder.fix.fixflow.designer.modeler.ui.common;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.founder.fix.fixflow.designer.database.SqlCommand;
import com.founder.fix.fixflow.designer.modeler.ui.property.process.ProcessTo;
import com.founder.fix.fixflow.designer.persistence.BpmnDeployer;
import com.founder.fix.fixflow.designer.util.FixFlowConfigUtil;
import com.founder.fix.fixflow.designer.util.StringUtil;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.swt.widgets.Group;

public class FixFlowManageDialog extends TitleAreaDialog {
	private DataBindingContext m_bindingContext;
	private Table table;
	private TableViewer tableViewer;
	private Button clearAllInstanceButton;
	private Button deleteInstanceButton;
	private Button deleteButton;
	private Button clearAllButton;
	private List<ProcessTo> processList;
	private BpmnDeployer bpmnDeployer;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public FixFlowManageDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.CLOSE | SWT.RESIZE | SWT.TITLE | SWT.PRIMARY_MODAL);
		// 取得所有流程定义
		processList = getDefinitions();
		bpmnDeployer = new BpmnDeployer();
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("流程实例管理");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		Composite composite = new Composite(container, SWT.NONE);
		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.verticalSpacing = 10;
		gl_composite.marginRight = 15;
		gl_composite.marginLeft = 25;
		gl_composite.marginBottom = 15;
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 15;
		composite.setLayout(gl_composite);

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setText("流程数据管理");
		new Label(composite, SWT.NONE);

		tableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
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

		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				updateButtons();
				setMessage("管理流程实例", IMessageProvider.INFORMATION);
				tableViewer.refresh();
			}
		});

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(150);
		tblclmnNewColumn.setText("流程编号");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(250);
		tableColumn_3.setText("流程名称");

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setText("版本号");
		tableColumn.setWidth(60);

		Composite composite_1 = new Composite(composite, SWT.NONE);
		GridLayout gl_composite_1 = new GridLayout(1, false);
		gl_composite_1.verticalSpacing = 1;
		gl_composite_1.marginWidth = 0;
		gl_composite_1.marginHeight = 0;
		gl_composite_1.horizontalSpacing = 0;
		composite_1.setLayout(gl_composite_1);

		Group group = new Group(composite_1, SWT.NONE);
		group.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1));
		group.setText("选中");
		GridLayout gl_group = new GridLayout(1, false);
		gl_group.verticalSpacing = 0;
		gl_group.marginWidth = 0;
		gl_group.marginHeight = 0;
		gl_group.horizontalSpacing = 0;
		group.setLayout(gl_group);

		deleteButton = new Button(group, SWT.NONE);
		deleteButton.setToolTipText("删除选中流程定义并清空该流程实例数据");
		deleteButton.setText("删除定义");
		deleteButton.addListener(SWT.Selection, new Listener() {

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
					ProcessTo col = (ProcessTo) objs[i];
					bpmnDeployer.deleteDeploy(col.getProcessKey(), col.getVersion(), FixFlowConfigUtil.createConnection());
					((List<ProcessTo>) tableViewer.getInput()).remove(col);
				}

				setMessage("流程定义删除成功", IMessageProvider.INFORMATION);
				updateButtons();
				tableViewer.refresh();
			}
		});

		deleteInstanceButton = new Button(group, SWT.NONE);
		deleteInstanceButton.setToolTipText("删除选中流程实例数据");
		deleteInstanceButton.setBounds(0, 0, 72, 22);
		deleteInstanceButton.setText("删除实例");
		deleteInstanceButton.addListener(SWT.Selection, new Listener() {

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
					ProcessTo col = (ProcessTo) objs[i];
					deleteFlowInstance(col.getProcessKey(), col.getVersion(), FixFlowConfigUtil.createConnection());
				}

				setMessage("流程实例删除成功", IMessageProvider.INFORMATION);
				updateButtons();
				tableViewer.refresh();
			}
		});

		Group group_1 = new Group(composite_1, SWT.NONE);
		GridLayout gl_group_1 = new GridLayout(1, false);
		gl_group_1.verticalSpacing = 0;
		gl_group_1.marginWidth = 0;
		gl_group_1.marginHeight = 0;
		gl_group_1.horizontalSpacing = 0;
		group_1.setLayout(gl_group_1);
		group_1.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1));
		group_1.setText("所有");

		clearAllInstanceButton = new Button(group_1, SWT.NONE);
		clearAllInstanceButton.setToolTipText("清空所有流程实例数据");
		clearAllInstanceButton.setText("清空实例");
		clearAllInstanceButton.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				boolean b = MessageDialog.openConfirm(null, "提示", "确定要全部清空流程实例数据吗？");
				Connection connection = null;
				if (b) {
					try {
						connection = FixFlowConfigUtil.createConnection();
						connection.setAutoCommit(false);
						SqlCommand sqlCommand = new SqlCommand(connection);
						sqlCommand.delete("FIXFLOW_RUN_PROCESSINSTANECE");
						sqlCommand.delete("FIXFLOW_RUN_TAKSINSTANECE");
						sqlCommand.delete("FIXFLOW_RUN_TOKEN");
						sqlCommand.delete("FIXFLOW_RUN_VARIABLE");
						sqlCommand.delete("FIXFLOW_RUN_TASKIDENTITYLINK");
						sqlCommand.delete("FIXFLOW_RUN_EVENT_SUBSCRIPTION");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						try {
							connection.rollback();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
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

				setMessage("流程实例数据删除成功", IMessageProvider.INFORMATION);
				tableViewer.refresh();
				updateButtons();
			}
		});

		clearAllButton = new Button(group_1, SWT.NONE);
		clearAllButton.setToolTipText("清空所有流程数据");
		clearAllButton.setText("清空所有");
		clearAllButton.addListener(SWT.Selection, new Listener() {

			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				boolean b = MessageDialog.openConfirm(null, "提示", "确定要全部清空流程数据吗？");
				if (b) {
					for (ProcessTo processTo : (List<ProcessTo>) tableViewer.getInput()) {
						bpmnDeployer.deleteDeploy(processTo.getProcessKey(), processTo.getVersion(), FixFlowConfigUtil.createConnection());
					}
				}

				((List<ProcessTo>) tableViewer.getInput()).clear();
				setMessage("流程数据删除成功", IMessageProvider.INFORMATION);
				tableViewer.refresh();
				updateButtons();
			}
		});

		setMessage("管理流程实例", IMessageProvider.INFORMATION);
		updateButtons();
		return area;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
		m_bindingContext = initDataBindings();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(600, 550);
	}

	/**
	 * 拿到所有流程定义文件
	 * @return
	 */
	private List<ProcessTo> getDefinitions() {
		Connection connection = null;

		List<ProcessTo> processTos = new ArrayList<ProcessTo>();

		try {

			connection = FixFlowConfigUtil.createConnection();
			List<Object> objectParamWhere = new ArrayList<Object>();

			String selectProcessDefinitionsByQueryCriteriaSql = " select PD.* from FIXFLOW_DEF_PROCESSDEFINITION PD order by PROCESS_KEY DESC,VERSION DESC";

			SqlCommand sqlCommand = new SqlCommand(connection);

			List<Map<String, Object>> dataObj = sqlCommand.queryForList(selectProcessDefinitionsByQueryCriteriaSql, objectParamWhere);

			for (Map<String, Object> dataMap : dataObj) {

				String processId = StringUtil.getString(dataMap.get("PROCESS_ID"));
				String processKey = StringUtil.getString(dataMap.get("PROCESS_KEY"));
				String category = StringUtil.getString(dataMap.get("CATEGORY"));
				String processName = StringUtil.getString(dataMap.get("PROCESS_NAME"));
				int version = StringUtil.getInt(dataMap.get("VERSION"));

				ProcessTo processTo = new ProcessTo();
				processTo.setProcessId(processId);
				processTo.setProcessKey(processKey);
				processTo.setProcessName(processName);
				processTo.setCategory(category);
				processTo.setVersion(version);

				processTos.add(processTo);

			}

		} catch (Exception e) {
			MessageDialog.openInformation(null, "提示", "出错了，错误原因是：" + e.toString());
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}

		return processTos;
	}
	
	/**
	 * 删除传入流程定义的实例数据
	 * @param processKey 流程编号
	 * @param processVersion 流程版本
	 * @param connection 数据库连接
	 */
	private void deleteFlowInstance(String processKey, int processVersion, Connection connection) {
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

			String sqlTextSl = "SELECT PROCESSINSTANCE_ID FROM FIXFLOW_RUN_PROCESSINSTANECE WHERE PROCESSDEFINITION_ID=?";
			// 执行查询流程是Sql语句,判断流程实例是否存在于数据库中.
			List<Object> objectParamWhereSl = new ArrayList<Object>();
			objectParamWhereSl.add(processId);
			List<Map<String, Object>> mapListSl = sqlCommand.queryForList(sqlTextSl, objectParamWhereSl);

			
			for (Map<String, Object> map : mapListSl) {
				bpmnDeployer.deleteIdentityLinkByProcessInstanceId(map.get("PROCESSINSTANCE_ID").toString(), sqlCommand);
				bpmnDeployer.deleteProcessInstanceByProcessInstanceId(map.get("PROCESSINSTANCE_ID").toString(), sqlCommand);
				bpmnDeployer.deleteTaskInstanceByProcessInstanceId(map.get("PROCESSINSTANCE_ID").toString(), sqlCommand);
				bpmnDeployer.deleteTokenByProcessInstanceId(map.get("PROCESSINSTANCE_ID").toString(), sqlCommand);
				bpmnDeployer.deleteVariableByProcessInstanceId(map.get("PROCESSINSTANCE_ID").toString(), sqlCommand);
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

	/**
	 * 设置按钮可用性
	 */
	public void updateButtons() {
		Object selectedPage = ((IStructuredSelection) tableViewer.getSelection()).getFirstElement();
		deleteButton.setEnabled(selectedPage != null);
		deleteInstanceButton.setEnabled(selectedPage != null);
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = PojoObservables.observeMaps(listContentProvider.getKnownElements(), ProcessTo.class, new String[] { "processKey", "processName", "version" });
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewer.setContentProvider(listContentProvider);
		//
		WritableList writableList = new WritableList(processList, ProcessTo.class);
		tableViewer.setInput(writableList);
		//
		return bindingContext;
	}
}
