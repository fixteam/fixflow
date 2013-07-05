package com.founder.fix.fixflow.designer.modeler.ui.property.process;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.MessageEventDefinition;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.TimerEventDefinition;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.modeler.core.ModelHandlerLocator;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil.Bpmn2DiagramType;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;

import com.founder.fix.bpmn2extensions.coreconfig.DBType;
import com.founder.fix.bpmn2extensions.coreconfig.DataBase;
import com.founder.fix.bpmn2extensions.coreconfig.FixFlowConfig;
import com.founder.fix.bpmn2extensions.coreconfig.QuartzConfig;
import com.founder.fix.bpmn2extensions.fixflow.Expression;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowFactory;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.fixflow.FormUri;
import com.founder.fix.bpmn2extensions.fixflow.ReceiveMessage;
import com.founder.fix.bpmn2extensions.fixflow.TaskSubject;
import com.founder.fix.fixflow.core.impl.job.ProcessInstanceAutoStart;
import com.founder.fix.fixflow.designer.database.SqlCommand;
import com.founder.fix.fixflow.designer.modeler.ui.property.AbstractFixFlowBpmn2PropertiesComposite;
import com.founder.fix.fixflow.designer.modeler.ui.property.SectionBpmnElement;
import com.founder.fix.fixflow.designer.persistence.BpmnDeployer;
import com.founder.fix.fixflow.designer.persistence.EventSubscriptionEntity;
import com.founder.fix.fixflow.designer.persistence.EventSubscriptionPersistence;
import com.founder.fix.fixflow.designer.persistence.EventSubscriptionType;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionChangedEvent;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionComboViewer;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionTo;
import com.founder.fix.fixflow.designer.usercontrol.IExpressionChangedListener;
import com.founder.fix.fixflow.designer.util.FixBpmnUtil;
import com.founder.fix.fixflow.designer.util.FixFlowConfigUtil;
import com.founder.fix.fixflow.designer.util.GuidUtil;
import com.founder.fix.fixflow.designer.util.QuartzUtil;
import com.founder.fix.fixflow.designer.util.StringUtil;
import com.founder.fix.fixflow.designer.util.VerificationUtil;

public class ProcessCommonPropertiesComposite extends AbstractFixFlowBpmn2PropertiesComposite {
	@SuppressWarnings("unused")
	private DataBindingContext m_bindingContext;
	private Text idtext;
	private Text nametext;
	private Text desctext;
	@SuppressWarnings("unused")
	private BPMNShape shape;
	private Table table;
	private TableViewer tableViewer;
	Button publishButton;
	protected List<ProcessTo> processToList;
	private Button deletebutton;
	private Text dbidtext;
	ExpressionComboViewer expressionComboViewer;
	private Button verifyButton;
	private Button quartzButton;
	private Button uppreverButton;
	private Button getbutton;
	ExpressionComboViewer expressionComboViewer_formUri;
	private Text typetext;

	public ProcessCommonPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
		processToList = new ArrayList<ProcessTo>();
	}

	public ProcessCommonPropertiesComposite(Composite parent, int style) {
		super(parent, style);
		processToList = new ArrayList<ProcessTo>();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createUI() {
		GridLayout gridLayout = new GridLayout(6, false);
		gridLayout.marginRight = 10;
		gridLayout.verticalSpacing = 10;
		gridLayout.marginHeight = 10;
		gridLayout.marginLeft = 5;
		setLayout(gridLayout);

		Label idLabel = new Label(this, SWT.NONE);
		GridData gd_idLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_idLabel.widthHint = 28;
		idLabel.setLayoutData(gd_idLabel);
		idLabel.setText("编号");

		toolkit.adapt(idLabel, true, true);

		idtext = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		GridData gd_idtext = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_idtext.widthHint = 240;
		idtext.setLayoutData(gd_idtext);

		toolkit.adapt(idtext, true, true);

		Label label_1 = new Label(this, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		label_1.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label_1.setText("唯一编号");

		toolkit.adapt(label_1, true, true);

		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(255, 255, 255));
		GridLayout gl_composite_1 = new GridLayout(2, false);
		gl_composite_1.horizontalSpacing = 10;
		gl_composite_1.marginHeight = 0;
		gl_composite_1.verticalSpacing = 0;
		gl_composite_1.marginWidth = 0;
		composite_1.setLayout(gl_composite_1);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));

		toolkit.adapt(composite_1, true, true);

		dbidtext = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		dbidtext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		// dbidtext.setEnabled(false);

		quartzButton = new Button(composite_1, SWT.CHECK);
		quartzButton.setToolTipText("是否同时发布定时任务");
		quartzButton.setText("发布定时任务");
		quartzButton.setSelection(true);

		verifyButton = new Button(this, SWT.CHECK);
		verifyButton.setToolTipText("是否启用校验");
		verifyButton.setText("校验");
		verifyButton.setSelection(true);

		toolkit.adapt(verifyButton, true, true);

		Label typelabel = new Label(this, SWT.NONE);
		typelabel.setText("分类");

		toolkit.adapt(typelabel, true, true);

		typetext = new Text(this, SWT.BORDER);
		typetext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		toolkit.adapt(typetext, true, true);

		Label dbversionLabel = new Label(this, SWT.NONE);
		GridData gd_dbversionLabel = new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 5);
		gd_dbversionLabel.horizontalIndent = 10;
		dbversionLabel.setLayoutData(gd_dbversionLabel);
		dbversionLabel.setText("历史版本");

		toolkit.adapt(dbversionLabel, true, true);

		tableViewer = new TableViewer(this, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		TableColumn t = new TableColumn(table, SWT.NONE);
		t.setText("编号");
		t.setWidth(70);

		TableColumn t1 = new TableColumn(table, SWT.NONE);
		t1.setText("名称");
		t1.setWidth(140);

		TableColumn t2 = new TableColumn(table, SWT.CENTER);
		t2.setText("分类");
		t2.setWidth(80);

		TableColumn t3 = new TableColumn(table, SWT.CENTER);
		t3.setText("版本号");
		t3.setWidth(80);

		GridData gd_table = new GridData(SWT.FILL, SWT.FILL, true, false, 2, 5);
		gd_table.heightHint = 115;
		gd_table.widthHint = 100;
		table.setLayoutData(gd_table);

		toolkit.adapt(table, true, true);

		TableColumn t4 = new TableColumn(table, SWT.CENTER);
		t4.setWidth(200);
		t4.setText("唯一编号");

		Composite composite = new Composite(this, SWT.NONE);
		GridLayout gl_composite = new GridLayout(1, false);
		gl_composite.horizontalSpacing = 0;
		gl_composite.marginHeight = 0;
		gl_composite.marginWidth = 0;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, false, 1, 5));

		toolkit.adapt(composite, true, true);

		Button verifybuttondj = new Button(composite, SWT.NONE);
		
		if(FixFlowConfigUtil.getFixFlowConfig().getDataBaseConfig().getIsEnableDesCon().equals("true")){
			verifybuttondj.setEnabled(false);
		}else {
			verifybuttondj.setEnabled(true);
		}
		
		verifybuttondj.setToolTipText("验证流程是否正确(不发布)");
		verifybuttondj.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		verifybuttondj.setText("验证");

		verifybuttondj.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if (!VerificationUtil.verifyAll()) {
					MessageDialog.openInformation(getShell(), "提示", "流程验证成功");
				}
			}
		});

		toolkit.adapt(verifybuttondj, true, true);

		publishButton = new Button(composite, SWT.NONE);
		publishButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		publishButton.setText("发布");
		
		if(FixFlowConfigUtil.getFixFlowConfig().getDataBaseConfig().getIsEnableDesCon().equals("true")){
			publishButton.setEnabled(false);
		}else {
			publishButton.setEnabled(true);
		}

		uppreverButton = new Button(composite, SWT.NONE);
		uppreverButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		uppreverButton.setText("更新");

		deletebutton = new Button(composite, SWT.NONE);
		deletebutton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		deletebutton.setText("删除");

		getbutton = new Button(composite, SWT.NONE);
		getbutton.setEnabled(false);
		getbutton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		getbutton.setText("获取");
		// getbutton.setVisible(false);

		Label nameLabel = new Label(this, SWT.NONE);
		GridData gd_nameLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_nameLabel.widthHint = 28;
		nameLabel.setLayoutData(gd_nameLabel);
		nameLabel.setText("名称");

		toolkit.adapt(nameLabel, true, true);

		nametext = new Text(this, SWT.BORDER);
		GridData gd_nametext = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_nametext.widthHint = 240;
		nametext.setLayoutData(gd_nametext);

		toolkit.adapt(nametext, true, true);

		Label label_2 = new Label(this, SWT.NONE);
		label_2.setText("任务主题");

		toolkit.adapt(label_2, true, true);

		expressionComboViewer = new ExpressionComboViewer(this, SWT.READ_ONLY);
		Combo combo_1 = expressionComboViewer.getCombo();
		GridData gd_combo_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_combo_1.widthHint = 194;
		combo_1.setLayoutData(gd_combo_1);

		toolkit.adapt(combo_1, true, true);

		Label labelFormUri = new Label(this, SWT.NONE);
		labelFormUri.setText("默认表单");

		toolkit.adapt(labelFormUri, true, true);

		expressionComboViewer_formUri = new ExpressionComboViewer(this, SWT.READ_ONLY);
		Combo combo_formUri = expressionComboViewer_formUri.getCombo();
		combo_formUri.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		toolkit.adapt(combo_formUri, true, true);

		Label descLabel = new Label(this, SWT.NONE);
		GridData gd_descLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_descLabel.widthHint = 28;
		descLabel.setLayoutData(gd_descLabel);
		descLabel.setText("描述");

		toolkit.adapt(descLabel, true, true);

		desctext = new Text(this, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		GridData gd_desctext = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_desctext.heightHint = 24;
		gd_desctext.widthHint = 188;
		desctext.setLayoutData(gd_desctext);

		toolkit.adapt(desctext, true, true);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		//
	}

	@Override
	public void createUIBindings(EObject eObject) {

		final Process process = (Process) SectionBpmnElement.sectionElement;

		m_bindingContext = initDataBindings();

		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				updateButtons();
			}
		});

		verifyButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						if (verifyButton.getSelection()) {
							process.eSet(FixFlowPackage.Literals.DOCUMENT_ROOT__VERIFICATION, "true");
						} else {

							process.eSet(FixFlowPackage.Literals.DOCUMENT_ROOT__VERIFICATION, "false");
						}

					}
				});
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		if (process.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__VERIFICATION) != null) {

			boolean verifyObj = StringUtil.getBoolean(process.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__VERIFICATION));
			verifyButton.setSelection(verifyObj);
		} else {
			verifyButton.setSelection(true);
			@SuppressWarnings("restriction")
			TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
			editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					process.eSet(FixFlowPackage.Literals.DOCUMENT_ROOT__VERIFICATION, "true");
				}
			});
		}

		// 设置按钮可用性
		updateButtons();

		// expressionComboViewer_formUri

		if (process.getExtensionValues().size() > 0) {

			for (ExtensionAttributeValue extensionAttributeValue : process.getExtensionValues()) {

				FeatureMap extensionElements = extensionAttributeValue.getValue();

				for (Entry entry : extensionElements) {
					if (entry.getValue() instanceof FormUri) {
						FormUri formUri = (FormUri) entry.getValue();
						ExpressionTo expressionTo = new ExpressionTo();
						expressionTo.setName(formUri.getExpression().getName());
						expressionTo.setExpressionText(formUri.getExpression().getValue());
						expressionComboViewer_formUri.setDefaultExpressionInput(expressionTo);
						break;
					}

				}

			}

		}

		expressionComboViewer_formUri.addExpressionChangedListeners(new IExpressionChangedListener() {

			@Override
			public void expressionChanged(final ExpressionChangedEvent event) {
				// TODO Auto-generated method stub
				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {

						setFormUriExtensionExpression(process, event.getExpressionTo());
						// be.eSet(a, e.diff.getNewValue());
					}
				});

			}
		});

		publishButton.addListener(SWT.Selection, addlisListener);
		uppreverButton.addListener(SWT.Selection, updatelisListener);
		getbutton.addListener(SWT.Selection, getlisListener);
		deletebutton.addListener(SWT.Selection, deletelisListener);

		EList<EAttribute> eAllAttributes = process.eClass().getEAllAttributes();

		if (FixBpmnUtil.getBpmn2DiagramType(modelHandler.getDefinitions()) == Bpmn2DiagramType.COLLABORATION) {
			for (EAttribute eAttribute : SectionBpmnElement.participant.eClass().getEAllAttributes()) {
				if (eAttribute.getName().equals("name")) {
					// text_1.setText("");
					bind(eAttribute, nametext, SectionBpmnElement.participant);

				}

			}
		}

		for (EAttribute attrib : eAllAttributes) {

			// 绑定开始节点的编号属性
			if (attrib.getName().equals("id")) {
				// text.setText("");
				bind(attrib, idtext);

			}
			if (attrib.getName().equals("name")) {
				// text_1.setText("");
				bind(attrib, nametext);

			}

		}

		for (EReference e : process.eClass().getEAllReferences()) {

			if (e.getName().equals("documentation")) {

				// text_3.setText("");
				bindDocumentation(e, desctext);
			}
		}

		if (process.getExtensionValues().size() > 0) {

			for (ExtensionAttributeValue extensionAttributeValue : process.getExtensionValues()) {

				FeatureMap extensionElements = extensionAttributeValue.getValue();

				for (Entry entry : extensionElements) {
					if (entry.getValue() instanceof TaskSubject) {
						TaskSubject taskSubject = (TaskSubject) entry.getValue();
						ExpressionTo expressionTo = new ExpressionTo();
						expressionTo.setName(taskSubject.getExpression().getName());
						expressionTo.setExpressionText(taskSubject.getExpression().getValue());
						expressionComboViewer.setDefaultExpressionInput(expressionTo);
						break;
					}
				}

			}

		}

		expressionComboViewer.addExpressionChangedListeners(new IExpressionChangedListener() {

			@Override
			public void expressionChanged(final ExpressionChangedEvent event) {
				// TODO Auto-generated method stub
				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {

						setTaskSubjectExtensionExpression(process, event.getExpressionTo());

					}
				});
			}
		});

		// 绑定流程分类
		bindAttributeText(typetext, FixFlowPackage.Literals.DOCUMENT_ROOT__CATEGORY);
		// 绑定流程唯一编号
		bindAttributeText(dbidtext, FixFlowPackage.Literals.DOCUMENT_ROOT__DBID);

		if (process.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__DBID) != null) {
			bindProcessList();
			tableViewer.refresh();
			idtext.setEnabled(false);
		}

		changeTableItemColor();
	}

	private void setTaskSubjectExtensionExpression(BaseElement baseElement, ExpressionTo expressionTo) {

		for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {

			FeatureMap extensionElements = extensionAttributeValue.getValue();
			for (Entry entry : extensionElements) {
				if (entry.getValue() instanceof TaskSubject) {
					if (expressionTo == null) {
						extensionElements.remove(entry);
					} else {
						TaskSubject taskSubject = (TaskSubject) entry.getValue();
						taskSubject.getExpression().setName(expressionTo.getName());
						taskSubject.getExpression().setValue(expressionTo.getExpressionText());
					}

					return;
				}
			}

		}
		if (expressionTo != null) {
			TaskSubject taskSubject = FixFlowFactory.eINSTANCE.createTaskSubject();

			Expression expression = FixFlowFactory.eINSTANCE.createExpression();
			expression.setName(expressionTo.getName());
			expression.setValue(expressionTo.getExpressionText());
			taskSubject.setExpression(expression);

			FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
					(org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_SUBJECT, taskSubject);

			if (baseElement.getExtensionValues().size() > 0) {
				baseElement.getExtensionValues().get(0).getValue().add(extensionElementEntry);
			} else {
				ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
				extensionElement.getValue().add(extensionElementEntry);
				baseElement.getExtensionValues().add(extensionElement);
			}
		}

	}

	public static List<ProcessTo> getProcessList() {

		List<ProcessTo> processTos = new ArrayList<ProcessTo>();
		if(FixFlowConfigUtil.getFixFlowConfig().getDataBaseConfig().getIsEnableDesCon().equals("true"))
			return processTos;
		Connection connection = null;

		// try {

		// } catch (Exception e2) {
		// TODO Auto-generated catch block
		// e2.printStackTrace();
		// MessageDialog.openInformation(null, "流程历史列表信息",
		// "流程历史列表获取出错,原因是数据库链接配置错误。");
		// }

		try {

			connection = FixFlowConfigUtil.createConnection();

			if (connection != null) {
				String selectProcessDefinitionsByQueryCriteriaSql = " select  PD.PROCESS_KEY,MAX(PD.PROCESS_NAME) as PROCESS_NAME  from FIXFLOW_DEF_PROCESSDEFINITION PD  group by PD.PROCESS_KEY ";

				SqlCommand sqlCommand = new SqlCommand(connection);

				List<Map<String, Object>> dataObj = sqlCommand.queryForList(selectProcessDefinitionsByQueryCriteriaSql);

				for (Map<String, Object> dataMap : dataObj) {

					// String processId =
					// StringUtil.getString(dataMap.get("PROCESS_ID"));
					String processKey = StringUtil.getString(dataMap.get("PROCESS_KEY"));
					// String category =
					// StringUtil.getString(dataMap.get("CATEGORY"));
					String processName = StringUtil.getString(dataMap.get("PROCESS_NAME"));
					// int version = StringUtil.getInt(dataMap.get("VERSION"));

					ProcessTo processTo = new ProcessTo();
					// processTo.setProcessId(processId);
					processTo.setProcessKey(processKey);
					processTo.setProcessName(processName);
					// processTo.setCategory(category);
					// processTo.setVersion(version);

					processTos.add(processTo);

				}
			}

		} catch (Exception e) {
			MessageDialog.openInformation(null, "流程历史列表信息", "流程历史列表信息出错,原因是 " + e.toString());

		} finally {
			try {
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}

		return processTos;
	}

	public void bindProcessList() {

		processToList.clear();

		if(FixFlowConfigUtil.getFixFlowConfig().getDataBaseConfig().getIsEnableDesCon().equals("true"))
			return;
		
		Connection connection = null;

		// MessageDialog.openInformation(null, "流程历史列表信息",
		// "流程历史列表获取出错,原因是数据库链接配置错误。");

		try {

			connection = FixFlowConfigUtil.createConnection();
			List<Object> objectParamWhere = new ArrayList<Object>();

			String selectProcessDefinitionsByQueryCriteriaSql = " select PD.* from FIXFLOW_DEF_PROCESSDEFINITION PD ";

			selectProcessDefinitionsByQueryCriteriaSql = selectProcessDefinitionsByQueryCriteriaSql + " WHERE PROCESS_KEY=? order by VERSION DESC";
			objectParamWhere.add(((Process) SectionBpmnElement.process).getId());
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

				processToList.add(processTo);

			}

		} catch (Exception e) {
			MessageDialog.openInformation(null, "流程历史列表信息", "流程历史列表信息出错,原因是 " + e.toString());

		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}

	}

	public void bindVersion(final Text text) {

		Object eGet = SectionBpmnElement.sectionElement.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__VERSION);

		if (eGet != null) {
			text.setText(eGet.toString());
		}

		IObservableValue textObserver = SWTObservables.observeText(text, SWT.Modify);// (text,
		// SWT.Modify);
		textObserver.addValueChangeListener(new IValueChangeListener() {

			@SuppressWarnings("restriction")
			@Override
			public void handleValueChange(final ValueChangeEvent e) {

				if (!text.getText().equals(SectionBpmnElement.sectionElement.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__VERSION))) {
					TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							SectionBpmnElement.sectionElement.eSet(FixFlowPackage.Literals.DOCUMENT_ROOT__VERSION, e.diff.getNewValue());
						}
					});
					if (getDiagramEditor().getDiagnostics() != null) {
						// revert the change and display error status message.
						text.setText((String) SectionBpmnElement.sectionElement.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__VERSION));
						// bpmn2Editor.showErrorMessage(bpmn2Editor.getDiagnostics().getMessage());
					}
				}
			}
		});

	}

	public void bindDbid(final Text text) {

		Object eGet = SectionBpmnElement.process.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__DBID);

		if (eGet != null) {
			text.setText(eGet.toString());
		}

		IObservableValue textObserver = SWTObservables.observeText(text, SWT.Modify);// (text,
		// SWT.Modify);
		textObserver.addValueChangeListener(new IValueChangeListener() {

			@SuppressWarnings("restriction")
			@Override
			public void handleValueChange(final ValueChangeEvent e) {

				if (!text.getText().equals(SectionBpmnElement.sectionElement.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__DBID))) {
					TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							SectionBpmnElement.sectionElement.eSet(FixFlowPackage.Literals.DOCUMENT_ROOT__DBID, e.diff.getNewValue());
						}
					});
					if (getDiagramEditor().getDiagnostics() != null) {
						// revert the change and display error status message.
						text.setText((String) SectionBpmnElement.sectionElement.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__DBID));

					}
				}
			}
		});

	}

	Listener deletelisListener = new Listener() {

		@Override
		public void handleEvent(Event event) {
			// TODO Auto-generated method stub
			BpmnDeployer bpmnDeployer = new BpmnDeployer();

			ISelection sel = tableViewer.getSelection();
			if (sel == null)
				return;
			Object[] objs = ((IStructuredSelection) sel).toArray();
			if (objs == null || objs.length == 0)
				return;

			boolean b = MessageDialog.openConfirm(null, "流程定义删除警告", "您确认删除流程定义吗?");
			if (!b)
				return;

			for (int i = 0; i < objs.length; i++) {
				ProcessTo col = (ProcessTo) objs[i];
				try {
					bpmnDeployer.deleteDeploy(col.getProcessKey(), col.getVersion(), FixFlowConfigUtil.createConnection());

					Scheduler scheduler = QuartzUtil.getScheduler(QuartzUtil.createSchedulerFactory(getQuartzProps()));
					Set<JobKey> keys = scheduler.getJobKeys(GroupMatcher.jobGroupEquals(col.getProcessId()));
					List<JobKey> jobkeys = new ArrayList<JobKey>();
					jobkeys.addAll(keys);
					scheduler.deleteJobs(jobkeys);

				} catch (Exception e) {
					MessageDialog.openInformation(null, "流程定义删除信息", "流程删除出错。");
					e.printStackTrace();
				}
				// ((List<ProcessTo>)tableViewer.getInput()).remove(col);
			}

			MessageDialog.openInformation(null, "流程定义删除信息", "流程删除成功。");

			bindProcessList();
			tableViewer.refresh();
			changeTableItemColor();
		}

	};

	Listener getlisListener = new Listener() {

		@Override
		public void handleEvent(Event event) {

			/*
			 * if(1==1){ return; }
			 */

			ProcessTo processTo = (ProcessTo) ((IStructuredSelection) tableViewer.getSelection()).getFirstElement();
			String processGuid = processTo.getProcessId();
			Resource eResource = SectionBpmnElement.sectionElement.eResource();
			Definitions definitions = null;
			try {
				definitions = ModelHandlerLocator.getModelHandler(eResource).getDefinitions();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// throw new FixFlowException("流程发布出错",e);
				MessageDialog.openInformation(null, "流程定义发布信息", "流程发布出错,原因是没有获取到BPMN2文件。");
			}
			BpmnDeployer bpmnDeployer = new BpmnDeployer();

			// ModelHandlerLocator.getModelHandler(eResource).getResource().

			Definitions d = bpmnDeployer.getResources(getDiagramEditor(), processGuid);

			changeTableItemColor();

		}

	};

	Listener addlisListener = new Listener() {

		@Override
		public void handleEvent(Event event) {

			try {

				// 验证
				if (verifyButton.getSelection()) {
					if (VerificationUtil.verifyAll()) {
						return;
					}
				}

				BpmnDeployer bpmnDeployer = new BpmnDeployer();
				Resource eResource = SectionBpmnElement.sectionElement.eResource();
				Definitions definitions = null;
				try {
					definitions = ModelHandlerLocator.getModelHandler(eResource).getDefinitions();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					// throw new FixFlowException("流程发布出错",e);
					MessageDialog.openInformation(null, "流程定义发布信息", "流程发布出错,原因是没有获取到BPMN2文件。");
					return;
				}

				// MessageDialog.openInformation(null, "流程定义发布信息",
				// "流程发布出错,原因是数据库链接配置错误。请在设计器的属性配置里检查数据库链接。");

				String processIdString = null;

				processIdString = bpmnDeployer.deploy(getDiagramEditor().getModelFile(), "设计器发布", definitions, dbidtext, getDiagramEditor());

				// dbidtext.setText(processIdString);

				bindProcessList();

				tableViewer.refresh();

				getDiagramEditor().doSave(null);

				readyQuartz(processIdString);

				Connection connection = FixFlowConfigUtil.createConnectionWithCommit();
				readMessageListener(processIdString, connection);

				if (SectionBpmnElement.process.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__DBID) != null) {
					// tableViewer.refresh();
					// idtext.setEditable(false);
					// idtext.setEnabled(false);
				}

				changeTableItemColor();
			} catch (Exception e) {
				MessageDialog.openInformation(null, "流程定义发布信息", "流程发布出错,原因是 " + e.getMessage());
				return;
			}
			MessageDialog.openInformation(null, "流程定义发布信息", "流程发布成功!");
		}
	};

	// 更新数据库定义文件
	Listener updatelisListener = new Listener() {

		@Override
		public void handleEvent(Event event) {
			if (tableViewer.getSelection() != null) {
				ProcessTo processTo = (ProcessTo) ((IStructuredSelection) tableViewer.getSelection()).getFirstElement();
				// 蛋疼的bug这里

				String oldProcessGuid = dbidtext.getText();
				String newProcessGuid = processTo.getProcessId();
				// 验证
				if (verifyButton.getSelection()) {
					if (VerificationUtil.verifyAll()) {
						return;
					}
				}

				BpmnDeployer bpmnDeployer = new BpmnDeployer();
				Resource eResource = SectionBpmnElement.process.eResource();
				Definitions definitions = null;
				try {
					definitions = ModelHandlerLocator.getModelHandler(eResource).getDefinitions();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					// throw new FixFlowException("流程发布出错",e);
					MessageDialog.openInformation(null, "流程定义发布信息", "流程发布出错,原因是没有获取到BPMN2文件。");
					return;
				}

				bpmnDeployer.updateDeploy(getDiagramEditor(), SectionBpmnElement.process, newProcessGuid, oldProcessGuid, typetext.getText(), nametext.getText());
				dbidtext.setText(newProcessGuid);
				// MessageDialog.openInformation(null, "流程定义发布信息", "流程发布成功!");
				bindProcessList();
				tableViewer.refresh();

				getDiagramEditor().doSave(null);

				Scheduler scheduler = QuartzUtil.getScheduler(QuartzUtil.createSchedulerFactory(getQuartzProps()));
				try {
					List<StartEvent> startEvents = modelHandler.getAll(StartEvent.class);
					for (StartEvent startEvent : startEvents) {
						scheduler.deleteJob(JobKey.jobKey(startEvent.getId(), newProcessGuid));
					}
				} catch (SchedulerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				readyQuartz(newProcessGuid);

				if (SectionBpmnElement.process.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__DBID) != null) {
					// tableViewer.refresh();
					idtext.setEnabled(false);
				}
			}

			changeTableItemColor();
		}
	};

	/**
	 * 设置按钮可用性
	 */
	public void updateButtons() {
		Object selectedPage = ((IStructuredSelection) tableViewer.getSelection()).getFirstElement();
		int index = 0;
		while (selectedPage != null && tableViewer.getElementAt(index) != null && !selectedPage.equals(tableViewer.getElementAt(index))) {
			index++;
		}
		uppreverButton.setEnabled(selectedPage != null);
		getbutton.setEnabled(selectedPage != null);
		deletebutton.setEnabled(selectedPage != null);

	}

	/**
	 * 根据to的id返回状态
	 * 
	 * @param processId
	 */
	public String getProcessToStatus(String processId) {
		String cnStatus = "";
		if (processId.equals(dbidtext.getText())) {
			cnStatus = "当前";
		}
		return cnStatus;
	}

	/**
	 * 改变table行颜色
	 */
	public void changeTableItemColor() {
		if (table.isDisposed() || table == null)
			return;
		if (table.getItemCount() > 0) {
			table.getItems();

			for (int i = 0; i < table.getItemCount(); i++) {
				if (dbidtext.getText().equals(((ProcessTo) table.getItem(i).getData()).getProcessId())) {
					Color color = Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
					table.getItem(i).setBackground(color);
					table.redraw();
				}
			}
		}
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableMap[] observeMaps = PojoObservables.observeMaps(listContentProvider.getKnownElements(), ProcessTo.class, new String[] { "processKey", "processName", "category",
				"version", "processId" });
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		//
		WritableList writableList = new WritableList(processToList, ProcessTo.class);
		tableViewer.setInput(writableList);
		//
		return bindingContext;
	}

	private void setFormUriExtensionExpression(BaseElement baseElement, ExpressionTo expressionTo) {

		for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {

			FeatureMap extensionElements = extensionAttributeValue.getValue();
			for (Entry entry : extensionElements) {
				if (entry.getValue() instanceof FormUri) {
					if (expressionTo == null) {
						extensionElements.remove(entry);
					} else {
						FormUri formUri = (FormUri) entry.getValue();
						formUri.getExpression().setName(expressionTo.getName());
						formUri.getExpression().setValue(expressionTo.getExpressionText());
					}

					return;
				}
			}

		}
		if (expressionTo != null) {
			FormUri formUri = FixFlowFactory.eINSTANCE.createFormUri();

			Expression expression = FixFlowFactory.eINSTANCE.createExpression();
			expression.setName(expressionTo.getName());
			expression.setValue(expressionTo.getExpressionText());
			formUri.setExpression(expression);

			FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__FORM_URI,
					formUri);

			if (baseElement.getExtensionValues().size() > 0) {
				baseElement.getExtensionValues().get(0).getValue().add(extensionElementEntry);
			} else {
				ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
				extensionElement.getValue().add(extensionElementEntry);
				baseElement.getExtensionValues().add(extensionElement);
			}
		}

	}

	/**
	 * 定时任务准备工作
	 * 
	 * @param id
	 *            流程定义文件id
	 */
	private void readyQuartz(String id) {
		String nodeId = "";
		String nodeName = "";
		
		// 定时任务数据插入及启动(在勾选发布定时任务的情况下)
		QuartzConfig quartzConfig = FixFlowConfigUtil.getFixFlowConfig().getQuartzConfig();
		FixFlowConfig fixFlowConfig = FixFlowConfigUtil.getFixFlowConfig();

		if (quartzConfig.getIsEnable().equals("false"))
			return ;

		if (quartzButton.getSelection()) {
			TimerEventDefinition timerEventDefinition = null;

			Process process = SectionBpmnElement.process;
			for (FlowElement flowElement : process.getFlowElements()) {
				if (flowElement instanceof StartEvent) {
					StartEvent startEvent = (StartEvent) flowElement;
					if (startEvent.getEventDefinitions().size() > 0 && startEvent.getEventDefinitions().get(0) instanceof TimerEventDefinition) {
						timerEventDefinition = (TimerEventDefinition) startEvent.getEventDefinitions().get(0);
						break;
					}
				}
			}

			if (timerEventDefinition == null) {
				return ;
			}

			boolean hasTimerEvent = false;

			for (FlowElement flowElement : SectionBpmnElement.process.getFlowElements()) {
				if (flowElement instanceof StartEvent) {
					StartEvent startEvent = (StartEvent) flowElement;
					for (EventDefinition eventDefinition : startEvent.getEventDefinitions()) {
						if (eventDefinition instanceof TimerEventDefinition) {
							hasTimerEvent = true;
							nodeId = startEvent.getId();
							nodeName = startEvent.getName();
							break;
						}
					}
				}
			}

			if (hasTimerEvent) {
				SchedulerFactory schedulerFactory = QuartzUtil.createSchedulerFactory(getQuartzProps());
				JobDetail job = QuartzUtil.createJobDetail(ProcessInstanceAutoStart.class, nodeId, id);

				Trigger trigger = null;

				// 如果是简单的时间点定时任务则创建SimpleTrigger
				if (((FormalExpression) timerEventDefinition.getTimeDate()) != null && ((FormalExpression) timerEventDefinition.getTimeCycle()) == null) {
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date = null;
					try {
						date = simpleDateFormat.parse(((FormalExpression) timerEventDefinition.getTimeDate()).getBody());
					} catch (ParseException e1) {
						
						MessageDialog.openInformation(null, "流程定义发布信息", "流程发布出错,原因是 " + e1.getMessage());
						return ;
						// TODO Auto-generated catch block
						//System.out.println("时间格式有误");
					}
					trigger = (SimpleTrigger) QuartzUtil.createSimpleTrigger(GuidUtil.CreateGuid(), id, date);

					Scheduler scheduler = null;
					try {
						scheduler = schedulerFactory.getScheduler();

						job.getJobDataMap().put("tokenId", "");//令牌
						job.getJobDataMap().put("transientVariableId", "");//不管
						job.getJobDataMap().put("processInstanceId", "");//流程实例
						job.getJobDataMap().put("nodeId", nodeId);//节点编号
						job.getJobDataMap().put("nodeName", nodeName);//节点名称
						job.getJobDataMap().put("processUniqueKey", id);//流程key
						job.getJobDataMap().put("processid", process.getId());//流程编号
						job.getJobDataMap().put("processName", process.getName());//流程名称
						job.getJobDataMap().put("bizKey", "");//业务关联值
						job.getJobDataMap().put("jobType", "automateTask");//流程类型
						job.getJobDataMap().put("simpleExp", date);
						job.getJobDataMap().put("connectorId", "");//连接器编号
						job.getJobDataMap().put("connectorInstanceId", "");//连接器实例编号
						job.getJobDataMap().put("connectorInstanceName", "");//连接器实例名称


						scheduler.scheduleJob(job, trigger);

					} catch (SchedulerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						MessageDialog.openInformation(null, "流程定义发布信息", "流程发布出错,原因是 " + e.getMessage());
						return ;

					}
				}
				// 如果是复杂的定时任务则创建CronTrigger
				else if (((FormalExpression) timerEventDefinition.getTimeCycle()) != null && ((FormalExpression) timerEventDefinition.getTimeDate()) == null) {
					trigger = (CronTrigger) QuartzUtil.createCronTrigger(GuidUtil.CreateGuid(), id, ((FormalExpression) timerEventDefinition.getTimeCycle()).getBody());

					Scheduler scheduler = null;
					try {
						scheduler = schedulerFactory.getScheduler();

						job.getJobDataMap().put("tokenId", "");//令牌
						job.getJobDataMap().put("transientVariableId", "");//不管
						job.getJobDataMap().put("processInstanceId", "");//流程实例
						job.getJobDataMap().put("nodeId", nodeId);//节点编号
						job.getJobDataMap().put("nodeName", nodeName);//节点名称
						job.getJobDataMap().put("processUniqueKey", id);//流程key
						job.getJobDataMap().put("processid", process.getId());//流程编号
						job.getJobDataMap().put("processName", process.getName());//流程名称
						job.getJobDataMap().put("bizKey", "");//业务关联值
						job.getJobDataMap().put("jobType", "automateTask");//流程类型
						job.getJobDataMap().put("connectorId", "");//连接器编号
						job.getJobDataMap().put("connectorInstanceId", "");//连接器实例编号
						job.getJobDataMap().put("connectorInstanceName", "");//连接器实例名称

						scheduler.scheduleJob(job, trigger);

					} catch (SchedulerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						MessageDialog.openInformation(null, "流程定义发布信息", "流程发布出错,原因是 " + e.getMessage());
						return ;
					}
				} else {
					System.out.println("不允同时许存在时间点和表达式的定时方式");
					MessageDialog.openInformation(null, "流程定义发布信息", "不允同时许存在时间点和表达式的定时方式");
					return ;
				}
				
			}
		}
	}

	/**
	 * 消息监听准备
	 * 
	 * @param processdefinitionId
	 *            流程定义ID
	 * @param connection
	 *            连接
	 */
	private void readMessageListener(String processdefinitionId, Connection connection) {
		MessageEventDefinition messageEventDefinition = null;
		ReceiveMessage receiveMessage = null;
		String id = "";

		Process process = SectionBpmnElement.process;
		for (FlowElement flowElement : process.getFlowElements()) {
			if (flowElement instanceof StartEvent) {
				StartEvent startEvent = (StartEvent) flowElement;
				if (startEvent.getEventDefinitions().size() > 0 && startEvent.getEventDefinitions().get(0) instanceof MessageEventDefinition) {
					messageEventDefinition = (MessageEventDefinition) startEvent.getEventDefinitions().get(0);
					id = startEvent.getId();
					break;
				}
			}
		}

		if (messageEventDefinition == null)
			return;

		for (ExtensionAttributeValue extensionAttributeValue : messageEventDefinition.getExtensionValues()) {
			if (extensionAttributeValue.getValue().get(0).getValue() instanceof ReceiveMessage) {
				receiveMessage = (ReceiveMessage) extensionAttributeValue.getValue().get(0).getValue();
				break;
			}
		}

		EventSubscriptionEntity entity = new EventSubscriptionEntity();
		entity.setId(GuidUtil.CreateGuid());
		entity.setMessageId(receiveMessage.getMessageId());
		entity.setNodeId(id);
		entity.setProcessDefinitionId(processdefinitionId);
		entity.setSubscriptionType(EventSubscriptionType.MessageStartEvent);

		EventSubscriptionPersistence eventSubscriptionPersistence = new EventSubscriptionPersistence(connection);
		try {
			eventSubscriptionPersistence.saveEventSubscriptionEntity(entity);
			connection.commit();
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

	private Properties getQuartzProps() {
		QuartzConfig quartzConfig = FixFlowConfigUtil.getFixFlowConfig().getQuartzConfig();
		FixFlowConfig fixFlowConfig = FixFlowConfigUtil.getFixFlowConfig();

		String DBDRIVER = "";
		String DBURL = "";
		String DBUSER = "";
		String DBPASSWORD = "";
		String driverDelegateClass = "";

		if (quartzConfig.getIsDefaultConfig().equals("true")) {
			DBDRIVER = FixFlowConfigUtil.getSelectedDataBase().getDriverClassName();
			DBURL = FixFlowConfigUtil.getSelectedDataBase().getUrl();
			DBUSER = FixFlowConfigUtil.getSelectedDataBase().getUsername();
			DBPASSWORD = FixFlowConfigUtil.getSelectedDataBase().getPassword();
			if (FixFlowConfigUtil.getSelectedDataBase().getDbtype().equals(DBType.ORACLE)) {
				driverDelegateClass = "org.quartz.impl.jdbcjobstore.oracle.OracleDelegate";
			} else {
				driverDelegateClass = "org.quartz.impl.jdbcjobstore.MSSQLDelegate";
			}
		} else {
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

		return props;
	}

}
