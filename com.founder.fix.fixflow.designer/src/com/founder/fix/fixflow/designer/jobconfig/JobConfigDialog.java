package com.founder.fix.fixflow.designer.jobconfig;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
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
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.core.databinding.observable.list.WritableList;

public class JobConfigDialog extends TitleAreaDialog {
	private DataBindingContext m_bindingContext;
	private Table table;
	private TableViewer tableViewer;
	private List<JobTo> jobTos;
	private Button stopButton;
	private Button continueButton;
	private Button deleteButton;
	private SchedulerFactory schedulerFactory;
	private Scheduler scheduler;

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
		setTitle("定时任务管理");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Composite composite = new Composite(container, SWT.NONE);
		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.verticalSpacing = 10;
		gl_composite.marginRight = 25;
		gl_composite.marginLeft = 25;
		gl_composite.marginBottom = 15;
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 15;
		composite.setLayout(gl_composite);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setText("全局管理");
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
				tableViewer.refresh();
				updateButtons();
			}
		});
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("流程名称");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("流程唯一编号");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setText("流程编号");
		tableColumn.setWidth(100);
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setText("下次执行时间");
		tableColumn_4.setWidth(100);
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("表达式");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("当前状态");
		
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
				// TODO Auto-generated method stub
				if(!tableViewer.getSelection().isEmpty()){
					IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
					JobTo jobTo  = (JobTo) selection.getFirstElement();
					try {
						scheduler.pauseTrigger(scheduler.getTriggersOfJob(jobTo.getJobKey()).get(0).getKey());
						jobTo.setCurrentStatus("暂停");
					} catch (SchedulerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				tableViewer.refresh();
				updateButtons();
			}
		});
		
		continueButton = new Button(composite_1, SWT.NONE);
		continueButton.setBounds(0, 0, 72, 22);
		continueButton.setText("继续");
		continueButton.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if(!tableViewer.getSelection().isEmpty()){
					IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
					JobTo jobTo  = (JobTo) selection.getFirstElement();
					try {
						scheduler.resumeTrigger(scheduler.getTriggersOfJob(jobTo.getJobKey()).get(0).getKey());
						jobTo.setCurrentStatus("普通");
					} catch (SchedulerException e) {
						// TODO Auto-generated catch block
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
				// TODO Auto-generated method stub
				if(!tableViewer.getSelection().isEmpty()){
					IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
					JobTo jobTo  = (JobTo) selection.getFirstElement();
					try {
						scheduler.unscheduleJob(scheduler.getTriggersOfJob(jobTo.getJobKey()).get(0).getKey());
						((List<JobTo>)tableViewer.getInput()).remove(jobTo);
					} catch (SchedulerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				tableViewer.refresh();
				updateButtons();
			}
		});

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
		return new Point(603, 544);
	}
	
	/**
	 * 取到定时任务所有数据
	 */
	public void loadAllJobs() {
		jobTos = new ArrayList<JobTo>();
		
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
				driverDelegateClass = "org.quartz.impl.jdbcjobstore.MSSQLDelegate";
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
	               JobDataMap jobDataMap = jobDetail.getJobDataMap();
	               Trigger trigger = scheduler.getTriggersOfJob(jobKey).get(0);
	               
	               Date date = trigger.getNextFireTime();
	               SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	               
	               JobTo jobTo = new JobTo();
	               jobTo.setProcessName(jobDataMap.getString("processName"));
	               jobTo.setProcessUniqueKey(jobDataMap.getString("processUniqueKey"));
	               jobTo.setProcessId(jobDataMap.getString("processid"));
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
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableMap[] observeMaps = PojoObservables.observeMaps(listContentProvider.getKnownElements(), JobTo.class, new String[]{"processName", "processUniqueKey", "processId", "nextFireTime", "quartzExpression", "currentStatus"});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		//
		WritableList writableList = new WritableList(jobTos, JobTo.class);
		tableViewer.setInput(writableList);
		//
		return bindingContext;
	}
}
