package com.founder.fix.fixflow.designer.jobconfig;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.impl.matchers.GroupMatcher;

import com.founder.fix.bpmn2extensions.coreconfig.DBType;
import com.founder.fix.bpmn2extensions.coreconfig.DataBase;
import com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig;
import com.founder.fix.bpmn2extensions.coreconfig.QuartzConfig;
import com.founder.fix.fixflow.designer.util.FixFlowConfigUtil;
import com.founder.fix.fixflow.designer.util.QuartzUtil;

public class JobConfigDialog extends TitleAreaDialog {
	private DataBindingContext m_bindingContext;
	private Table table;
	private TableViewer tableViewer;
	private List<JobTo> jobTos;
	private List<JobDetail> jobDetails;
	private Button stopButton;
	private Button continueButton;
	private Button deleteButton;
	private SchedulerFactory schedulerFactory;
	private Scheduler scheduler;
	private Combo combo;
	private TimeTaskFilter filter;
	private Button editbutton;

	/**构造方法
	 * Create the dialog.
	 * @param parentShell
	 */
	public JobConfigDialog(Shell parentShell) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(SWT.CLOSE | SWT.RESIZE | SWT.TITLE | SWT.PRIMARY_MODAL);
		//加载所有任务
		loadAllJobs();
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("任务管理");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Composite composite = new Composite(container, SWT.NONE);
		GridLayout gl_composite = new GridLayout(3, false);
		gl_composite.verticalSpacing = 10;
		gl_composite.marginRight = 25;
		gl_composite.marginLeft = 25;
		gl_composite.marginBottom = 15;
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 15;
		composite.setLayout(gl_composite);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		lblNewLabel.setText("全局任务管理");
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("按流程名称查询任务");
		
		combo = new Combo(composite, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
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
				// 读取表格中的数据库ID
				List<JobTo> jobTos = (List<JobTo>) tableViewer.getInput();
				if (jobTos != null && jobTos.size() > 0) {
					combo.setItems(new String[0]);
					combo.add("");
					for (Iterator iterator = jobTos.iterator(); iterator.hasNext();) {
						JobTo jobTo = (JobTo) iterator.next();
						combo.add(jobTo.getProcessName());
					}
				}
				if (oldValue != null) {
					combo.setText(oldValue);
				}
			}
		});
		
		combo.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				filter.setSearchText(combo.getText());
				tableViewer.refresh();
			}

			@Override
			public void keyPressed(KeyEvent e) {
//				e.doit = false;
			}
		});
		
		combo.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				filter.setSearchText(combo.getText());
				tableViewer.refresh();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		new Label(composite, SWT.NONE);
		
		tableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
		filter = new TimeTaskFilter();
		tableViewer.addFilter(filter);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table.addListener(SWT.MeasureItem, new Listener() {
			public void handleEvent(Event event) {
				// 设置行高度
				event.height = (int) Math.floor(event.gc.getFontMetrics().getHeight() * 1.5);
			}
		});
		
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				updateButtons();
			}
		});
		
		tableViewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
				JobTo jobTo = (JobTo) selection.getFirstElement();
				TriggerConfigDialog triggerConfigDialog = new TriggerConfigDialog(getShell(), scheduler, jobTo);
				triggerConfigDialog.setBlockOnOpen(true);
				triggerConfigDialog.open();
			}
		});
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("名称");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("组名");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("流程名称");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("流程编号");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("流程key");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(100);
		tblclmnNewColumn_4.setText("节点");
		
		TableColumn tblclmnNewColumn_9 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_9.setWidth(100);
		tblclmnNewColumn_9.setText("节点名称");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_5.setWidth(100);
		tblclmnNewColumn_5.setText("流程实例");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("流程类型");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("业务关联值");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("令牌");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("当前状态");
		
		TableColumn tblclmnNewColumn_6 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_6.setWidth(100);
		tblclmnNewColumn_6.setText("连接器编号");
		
		TableColumn tblclmnNewColumn_7 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_7.setWidth(100);
		tblclmnNewColumn_7.setText("连接器实例编号");
		
		TableColumn tblclmnNewColumn_8 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_8.setWidth(100);
		tblclmnNewColumn_8.setText("连接器实例名称");
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		GridLayout gl_composite_1 = new GridLayout(1, false);
		gl_composite_1.verticalSpacing = 1;
		gl_composite_1.marginWidth = 0;
		gl_composite_1.marginHeight = 0;
		gl_composite_1.horizontalSpacing = 0;
		composite_1.setLayout(gl_composite_1);
		
		stopButton = new Button(composite_1, SWT.NONE);
		stopButton.setBounds(0, 0, 72, 22);
		stopButton.setText("暂停");
		stopButton.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				if(!tableViewer.getSelection().isEmpty()){
					IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
					JobTo jobTo  = (JobTo) selection.getFirstElement();
					try {
						scheduler.pauseTrigger(scheduler.getTriggersOfJob(jobTo.getJobKey()).get(0).getKey());
						jobTo.setCurrentStatus("暂停");
					} catch (SchedulerException e) {
						e.printStackTrace();
					}
				}
				
				tableViewer.refresh();
				updateButtons();
			}
		});
		
		editbutton = new Button(composite_1, SWT.NONE);
		editbutton.setText("编辑");
		editbutton.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
				JobTo jobTo = (JobTo) selection.getFirstElement();
				TriggerConfigDialog triggerConfigDialog = new TriggerConfigDialog(getShell(), scheduler, jobTo);
				triggerConfigDialog.setBlockOnOpen(true);
				triggerConfigDialog.open();
			}
		});
		
		continueButton = new Button(composite_1, SWT.NONE);
		continueButton.setBounds(0, 0, 72, 22);
		continueButton.setText("继续");
		continueButton.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				if(!tableViewer.getSelection().isEmpty()){
					IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
					JobTo jobTo  = (JobTo) selection.getFirstElement();
					try {
						scheduler.resumeTrigger(scheduler.getTriggersOfJob(jobTo.getJobKey()).get(0).getKey());
						jobTo.setCurrentStatus("普通");
					} catch (SchedulerException e) {
						e.printStackTrace();
					}
				}
				
				tableViewer.refresh();
				updateButtons();
			}
		});
		
		deleteButton = new Button(composite_1, SWT.NONE);
		deleteButton.setBounds(0, 0, 72, 22);
		deleteButton.setText("删除");
		deleteButton.addListener(SWT.Selection, new Listener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				if(!tableViewer.getSelection().isEmpty()){
					IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
					JobTo jobTo  = (JobTo) selection.getFirstElement();
					try {
						scheduler.deleteJob(jobTo.getJobKey());
						((List<JobTo>)tableViewer.getInput()).remove(jobTo);
					} catch (SchedulerException e) {
						e.printStackTrace();
					}
				}
				
				tableViewer.refresh();
				updateButtons();
			}
		});
		
		createCellModifier();
		updateButtons();
		setMessage("管理定时任务", IMessageProvider.INFORMATION);
		return area;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, "确定", true);
		createButton(parent, IDialogConstants.CANCEL_ID, "取消", false);
		m_bindingContext = initDataBindings();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(700, 544);
	}
	
	/**
	 * 取到定时任务所有数据
	 */
	public void loadAllJobs() {
		jobTos = new ArrayList<JobTo>();
		jobDetails = new ArrayList<JobDetail>();
		
		FixFlowConfig fixFlowConfig = FixFlowConfigUtil.getFixFlowConfig();
		QuartzConfig quartzConfig = FixFlowConfigUtil.getFixFlowConfig().getQuartzConfig();
		
		if(quartzConfig.getIsEnable().equals("false")||fixFlowConfig.getDataBaseConfig().getIsEnableDesCon().equals("true"))
			return;
		
		String DBDRIVER = "";
		String DBURL = "";
		String DBUSER = "";
		String DBPASSWORD = "";
		String driverDelegateClass = "";
		
		if(quartzConfig.getIsDefaultConfig().equals("true")) {
			DBDRIVER = FixFlowConfigUtil.getSelectedDataBase().getDriverClassName();
			DBURL = FixFlowConfigUtil.getSelectedDataBase().getUrl();
			DBUSER = FixFlowConfigUtil.getSelectedDataBase().getUsername();
			DBPASSWORD = FixFlowConfigUtil.getSelectedDataBase().getPassword();
			if (FixFlowConfigUtil.getSelectedDataBase().getDbtype().equals(DBType.ORACLE)) {
				driverDelegateClass = "org.quartz.impl.jdbcjobstore.oracle.OracleDelegate";
			} else {
				if(FixFlowConfigUtil.getSelectedDataBase().getDbtype().equals(DBType.SQLSERVER))
				{
					driverDelegateClass = "org.quartz.impl.jdbcjobstore.MSSQLDelegate";
				}
				else{
					driverDelegateClass = "org.quartz.impl.jdbcjobstore.StdJDBCDelegate";
				}
			}
		}else {
			for (DataBase dataBase : fixFlowConfig.getDataBaseConfig().getDataBase()) {
				if (dataBase.getId().equals(quartzConfig.getDataBaseId())) {
					DBDRIVER = dataBase.getDriverClassName();
					DBURL = dataBase.getUrl();
					DBUSER = dataBase.getUsername();
					DBPASSWORD = dataBase.getPassword();
					if (dataBase.getDbtype().equals(DBType.ORACLE)) {
						driverDelegateClass = "org.quartz.impl.jdbcjobstore.oracle.OracleDelegate";
					} else {
						driverDelegateClass = "org.quartz.impl.jdbcjobstore.MSSQLDelegate";
					}
				}
			}
		}
		
		Properties props = new Properties();
		props.put("org.quartz.scheduler.instanceName", "FixFlowQuartzScheduler");
		props.put("org.quartz.scheduler.instanceId", "AUTO");
		props.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
		props.put("org.quartz.threadPool.threadCount", "3");
		props.put("org.quartz.threadPool.threadPriority", "5");
		props.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
		props.put("org.quartz.jobStore.driverDelegateClass", driverDelegateClass);
		props.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
		props.put("org.quartz.jobStore.dataSource", "fixDS");
		props.put("org.quartz.jobStore.isClustered", "false");
		props.put("org.quartz.dataSource.fixDS.driver", DBDRIVER);
		props.put("org.quartz.dataSource.fixDS.URL", DBURL);
		props.put("org.quartz.dataSource.fixDS.user", DBUSER);
		props.put("org.quartz.dataSource.fixDS.password", DBPASSWORD);
		props.put("org.quartz.dataSource.fixDS.maxConnections", "5");

		
		
		
		
		 try
	      { 
			 schedulerFactory = QuartzUtil.createSchedulerFactory(props);
			 scheduler = QuartzUtil.getScheduler(schedulerFactory);
	         List<String> jobGroups = scheduler.getJobGroupNames();
	         for (int i = 0; i < jobGroups.size(); i++)
	         {
	            String name = (String) jobGroups.get(i);
	            Set<JobKey> keys = scheduler.getJobKeys(GroupMatcher.jobGroupEquals(name));
	            Iterator<JobKey> iter = keys.iterator();
	            while (iter.hasNext())
	            {
	               JobKey jobKey = (JobKey)iter.next();
	               JobDetail jobDetail = scheduler.getJobDetail(jobKey);
	               jobDetails.add(jobDetail);
	               
	               
	               JobDataMap jobDataMap = jobDetail.getJobDataMap();
	               Trigger trigger = scheduler.getTriggersOfJob(jobKey).get(0);
	               
	               Date date = trigger.getNextFireTime();
	               SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	               
	               JobTo jobTo = new JobTo();
	               jobTo.setJobName(jobKey.getName());
	               jobTo.setJobGroupName(jobKey.getGroup());
	               jobTo.setProcessName(jobDataMap.getString("processName"));
	               jobTo.setProcessUniqueKey(jobDataMap.getString("processUniqueKey"));
	               jobTo.setProcessId(jobDataMap.getString("processid"));
	               jobTo.setNodeId(jobDataMap.getString("nodeId"));
	               jobTo.setNodeName(jobDataMap.getString("nodeName"));
	               jobTo.setBizKey(jobDataMap.getString("bizKey"));
	               jobTo.setProcessInstanceId(jobDataMap.getString("processInstanceId"));
	               jobTo.setJobType(jobDataMap.getString("jobType"));
	               jobTo.setTokenId(jobDataMap.getString("tokenId"));
	               jobTo.setConnectorId(jobDataMap.getString("connectorId"));
	               jobTo.setConnectorInstanceId(jobDataMap.getString("connectorInstanceId"));
	               jobTo.setConnectorInstanceName(jobDataMap.getString("connectorInstanceName"));
	               if(trigger instanceof SimpleTrigger) {
	            	   
	            	   jobTo.setQuartzExpression(simpleDateFormat.format(jobDataMap.get("simpleExp")));
	               }
	               if(trigger instanceof CronTrigger) {
	            	   jobTo.setQuartzExpression(((CronTrigger)trigger).getCronExpression());
	               }
	               jobTo.setCurrentStatus(getTriggerStateByEmuType(scheduler.getTriggerState(trigger.getKey())));
	              
	               jobTo.setNextFireTime(simpleDateFormat.format(date).toString());
	               jobTo.setJobKey(jobKey);
	               
	               jobTos.add(jobTo);
	            }
	         }
	      }
	      catch (SchedulerException se)
	      {
	         se.printStackTrace();
	      }
	}
	
	/**
	 * 更改按钮状态
	 */
	public void updateButtons() {
		Object selectedPage = ((IStructuredSelection)tableViewer.getSelection()).getFirstElement();
		stopButton.setEnabled(selectedPage != null);
		continueButton.setEnabled(selectedPage != null);
		deleteButton.setEnabled(selectedPage != null);
	}
	
	/**
	 * 根据枚举的触发器类型转成中文
	 * @return
	 */
	public String getTriggerStateByEmuType(TriggerState triggerState) {
		String type = "";
		if(triggerState.toString().equals("BLOCKED")) {
			type = "锁定";
			return type;
		}
		if(triggerState.toString().equals("COMPLETE")) {
			type = "完成";
			return type;
		}
		if(triggerState.toString().equals("ERROR")) {
			type = "错误";
			return type;
		}
		if(triggerState.toString().equals("NONE")) {
			type = "无";
			return type;
		}
		if(triggerState.toString().equals("NORMAL")) {
			type = "普通";
			return type;
		}
		if(triggerState.toString().equals("PAUSED")) {
			type = "暂停";
			return type;
		}
		else {
			return type;
		}
	}
	
	private void createCellModifier() {
		final CellEditor[] cellEditor = new CellEditor[table.getColumnCount()];
		cellEditor[0] = new TextCellEditor(table, SWT.READ_ONLY);
		cellEditor[1] = new TextCellEditor(table, SWT.READ_ONLY);
		cellEditor[2] = new TextCellEditor(table, SWT.READ_ONLY);
		cellEditor[3] = new TextCellEditor(table, SWT.READ_ONLY);
		cellEditor[4] = new TextCellEditor(table, SWT.READ_ONLY);
		cellEditor[5] = new TextCellEditor(table, SWT.READ_ONLY);
		cellEditor[6] = new TextCellEditor(table, SWT.READ_ONLY);
		cellEditor[7] = new TextCellEditor(table, SWT.READ_ONLY);
		cellEditor[8] = new TextCellEditor(table, SWT.READ_ONLY);
		cellEditor[9] = new TextCellEditor(table, SWT.READ_ONLY);
		cellEditor[10] = new TextCellEditor(table, SWT.READ_ONLY);
		cellEditor[11] = null;
		cellEditor[12] = new TextCellEditor(table, SWT.READ_ONLY);
		cellEditor[13] = new TextCellEditor(table, SWT.READ_ONLY);
		cellEditor[14] = new TextCellEditor(table, SWT.READ_ONLY);

		tableViewer.setCellEditors(cellEditor);
		tableViewer.setColumnProperties(new String[]{"JOBTOJOBNAME","JOBTOJOBGROUPNAME","JOBTOPROCESSNAME","JOBTOPROCESSID","JOBTOPROCESSKEY","JOBTONODEID","JOBTONODENAME","JOBTOPROCESSINSTANCEID","JOBTOJOBTYPE","JOBTOBIZKEY","JOBTOTOKENID","JOBTOCURRENTSTATUS","JOBTOCONNECTORID","JOBTOCONNECTORINSTANCEID","JOBTOCONNECTORINSTANCENAME"});
		tableViewer.setCellModifier(new ICellModifier() {

			public void modify(Object element, String property, Object value) {

				TableItem tableitem = (TableItem) element;
				JobTo jobTo = (JobTo) tableitem.getData();
				if (property.equals("JOBTOJOBNAME")) {
					jobTo.setJobName(jobTo.getJobName());
				}
				if (property.equals("JOBTOJOBGROUPNAME")) {
					jobTo.setJobGroupName(jobTo.getJobGroupName());
				}
				if (property.equals("JOBTOPROCESSNAME")) {
					jobTo.setProcessName(jobTo.getProcessName());
				}
				if (property.equals("JOBTOPROCESSID")) {
					jobTo.setProcessId(jobTo.getProcessId());
				}
				if (property.equals("JOBTOPROCESSKEY")) {
					jobTo.setProcessUniqueKey(jobTo.getProcessUniqueKey());
				}
				if (property.equals("JOBTONODEID")) {
					jobTo.setNodeId(jobTo.getNodeId());
				}
				if (property.equals("JOBTONODENAME")) {
					jobTo.setNodeName(jobTo.getNodeName());
				}
				if (property.equals("JOBTOPROCESSINSTANCEID")) {
					jobTo.setProcessInstanceId(jobTo.getProcessInstanceId());
				}
				if (property.equals("JOBTOJOBTYPE")) {
					jobTo.setJobType(jobTo.getJobType());
				}
				if (property.equals("JOBTOBIZKEY")) {
					jobTo.setBizKey(jobTo.getBizKey());
				}
				if (property.equals("JOBTOTOKENID")) {
					jobTo.setTokenId(jobTo.getTokenId());
				}
				if (property.equals("JOBTOCONNECTORID")) {
					jobTo.setConnectorId(jobTo.getConnectorId());
				}
				if (property.equals("JOBTOCONNECTORINSTANCEID")) {
					jobTo.setConnectorInstanceId(jobTo.getConnectorInstanceId());
				}
				if (property.equals("JOBTOCONNECTORINSTANCENAME")) {
					jobTo.setConnectorInstanceName(jobTo.getConnectorInstanceName());
				}
				tableViewer.refresh();
				updateButtons();
			}

			public Object getValue(Object element, String property) {

				JobTo jobTo = (JobTo) element;

				if (property.equals("JOBTOJOBNAME")) {
					return jobTo.getJobName();
				}
				if (property.equals("JOBTOJOBGROUPNAME")) {
					return jobTo.getJobGroupName();
				}
				if (property.equals("JOBTOPROCESSNAME")) {
					return jobTo.getProcessName();
				}
				if (property.equals("JOBTOPROCESSID")) {
					return jobTo.getProcessId();
				}
				if (property.equals("JOBTOPROCESSKEY")) {
					return jobTo.getProcessUniqueKey();
				}
				if (property.equals("JOBTONODEID")) {
					return jobTo.getNodeId();
				}
				if (property.equals("JOBTONODENAME")) {
					return jobTo.getNodeName();
				}
				if (property.equals("JOBTOPROCESSINSTANCEID")) {
					return jobTo.getProcessInstanceId();
				}
				if (property.equals("JOBTOJOBTYPE")) {
					return jobTo.getJobType();
				}
				if (property.equals("JOBTOBIZKEY")) {
					return jobTo.getBizKey();
				}
				if (property.equals("JOBTOTOKENID")) {
					return jobTo.getTokenId();
				}
				if (property.equals("JOBTOCONNECTORID")) {
					return jobTo.getConnectorId();
				}
				if (property.equals("JOBTOCONNECTORINSTANCEID")) {
					return jobTo.getConnectorInstanceId();
				}
				if (property.equals("JOBTOCONNECTORINSTANCENAME")) {
					return jobTo.getConnectorInstanceName();
				}	
				return null;
			}

			public boolean canModify(Object element, String property) {

				return element instanceof JobTo;
			}
		});
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = PojoObservables.observeMaps(listContentProvider.getKnownElements(), JobTo.class, new String[]{"jobName", "jobGroupName", "processName", "processId", "processUniqueKey", "nodeId", "nodeName", "processInstanceId", "jobType", "bizKey", "tokenId", "currentStatus", "connectorId", "connectorInstanceId", "connectorInstanceName"});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableList selfList = org.eclipse.core.databinding.property.Properties.selfList(JobTo.class).observe(jobTos);
		tableViewer.setInput(selfList);
		//
		return bindingContext;
	}
}
