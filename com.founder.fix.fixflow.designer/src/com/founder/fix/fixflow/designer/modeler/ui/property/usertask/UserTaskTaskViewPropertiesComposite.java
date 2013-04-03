package com.founder.fix.fixflow.designer.modeler.ui.property.usertask;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.founder.fix.bpmn2extensions.fixflow.ExpectedExecutionTime;
import com.founder.fix.bpmn2extensions.fixflow.Expression;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowFactory;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.fixflow.TaskSubject;
import com.founder.fix.fixflow.designer.modeler.ui.property.AbstractFixFlowBpmn2PropertiesComposite;
import com.founder.fix.fixflow.designer.modeler.ui.property.SectionBpmnElement;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionChangedEvent;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionComboViewer;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionTo;
import com.founder.fix.fixflow.designer.usercontrol.IExpressionChangedListener;
import com.founder.fix.fixflow.designer.util.StringUtil;

import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.emf.databinding.EMFObservables;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage.Literals;

public class UserTaskTaskViewPropertiesComposite extends
		AbstractFixFlowBpmn2PropertiesComposite {

	

	private ExpressionComboViewer expressionComboViewer1;
	private ExpressionComboViewer expressionComboViewer2;
	
	private ExpectedExecutionTime expectedExecutionTime;
	private Spinner spinner;
	private Spinner spinner_1;
	private Spinner spinner_2;
	private Spinner spinner_3;
	Combo combo;
	public UserTaskTaskViewPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}
	public UserTaskTaskViewPropertiesComposite(Composite parent, int style) {
		super(parent, style);

		// TODO Auto-generated constructor stub
	}

	@Override
	public void createUI() {
		GridLayout gridLayout = new GridLayout(1, false);
		gridLayout.verticalSpacing = 0;
		gridLayout.marginLeft = 10;
		gridLayout.marginHeight = 10;
		setLayout(gridLayout);

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1));
		composite.setLayout(new GridLayout(2, false));

	
		toolkit.adapt(composite, true, true);
		
		Label tasksubjectLabel = new Label(composite, SWT.NONE);
		tasksubjectLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		tasksubjectLabel.setText("动态主题");


		toolkit.adapt(tasksubjectLabel, true, true);
		
		expressionComboViewer1 = new ExpressionComboViewer(composite, SWT.BORDER | SWT.READ_ONLY);
		Combo combo_1 = expressionComboViewer1.getCombo();
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		

		toolkit.adapt(combo_1, true, true);
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_2.setText("任务类型");
		toolkit.adapt(lblNewLabel_2, true, true);
		
		combo = new Combo(composite, SWT.READ_ONLY);
		combo.setItems(new String[] {"FIXFLOWTASK", "FIXNOTICETASK"});
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo.select(0);
		toolkit.adapt(combo, true, true);
		
		Label label = new Label(composite, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1,
				1));
		label.setText("动态描述");
		label.setVisible(false);

		toolkit.adapt(label, true, true);
		
		expressionComboViewer2 = new ExpressionComboViewer(composite, SWT.BORDER | SWT.READ_ONLY);
		Combo combo_2 = expressionComboViewer2.getCombo();
		combo_2.setEnabled(false);
		combo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo_2.setVisible(false);

		toolkit.adapt(combo_2, true, true);

		Composite composite_1 = new Composite(this, SWT.NONE);
		GridData gd_composite_1 = new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1);
		gd_composite_1.heightHint = 34;
		composite_1.setLayoutData(gd_composite_1);
		composite_1.setLayout(new GridLayout(9, false));
		
		//composite_1.setVisible(false);
		

		toolkit.adapt(composite_1, true, true);

		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setText("预计执行时间");


		toolkit.adapt(lblNewLabel, true, true);

		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setText("天");

	
		toolkit.adapt(lblNewLabel_1, true, true);

		spinner = new Spinner(composite_1, SWT.BORDER);
		//spinner.setEnabled(false);
		

		toolkit.adapt(spinner, true, true);

		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setText("小时");

	
		toolkit.adapt(label_1, true, true);

		spinner_1 = new Spinner(composite_1, SWT.BORDER);
		//spinner_1.setEnabled(false);

		toolkit.adapt(spinner_1, true, true);

		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setText("分钟");

	
		toolkit.adapt(label_2, true, true);

		spinner_2 = new Spinner(composite_1, SWT.BORDER);
		//spinner_2.setEnabled(false);

	
		toolkit.adapt(spinner_2, true, true);

		Label label_3 = new Label(composite_1, SWT.NONE);
		label_3.setText("秒");


		toolkit.adapt(label_3, true, true);

		spinner_3 = new Spinner(composite_1, SWT.BORDER);
		//spinner_3.setEnabled(false);


		toolkit.adapt(spinner_3, true, true);
		expectedExecutionTime=null;

	}

	@Override
	public void createUIBindings(EObject eObject) {
		
		final UserTask userTask = (UserTask) be;
		
		Object taskTypeObject=userTask.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_TYPE);
		if(taskTypeObject==null||taskTypeObject.equals("")){
			
			@SuppressWarnings("restriction")
			TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
			editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {

					userTask.eSet(FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_TYPE, "FIXFLOWTASK");
					combo.select(0);
				}
			});
			
			
		}
		else{
			//userTask.eSet(FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_TYPE, arg1)
			if(taskTypeObject.equals("FIXFLOWTASK")){
				combo.select(0);
			}else{
				if(taskTypeObject.equals("FIXNOTICETASK")){
					combo.select(1);
				}else {
					combo.select(0);
				}
			}
			
			
		}
		
		combo.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				Combo comboEvent=(Combo)e.getSource();
				final String taskTypeString=comboEvent.getItem(comboEvent.getSelectionIndex());
				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {

						userTask.eSet(FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_TYPE,taskTypeString);

					}
				});
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		if (userTask.getExtensionValues().size() > 0) {

			for (ExtensionAttributeValue extensionAttributeValue : userTask.getExtensionValues()) {

				FeatureMap extensionElements = extensionAttributeValue.getValue();

				for (Entry entry : extensionElements) {
					if (entry.getValue() instanceof TaskSubject) {
						TaskSubject taskSubject = (TaskSubject) entry.getValue();
						ExpressionTo expressionTo = new ExpressionTo();
						expressionTo.setName(taskSubject.getExpression().getName());
						expressionTo.setExpressionText(taskSubject.getExpression().getValue());
						expressionComboViewer1.setDefaultExpressionInput(expressionTo);
						continue;
					}
					if(entry.getValue() instanceof ExpectedExecutionTime){
						expectedExecutionTime=(ExpectedExecutionTime) entry.getValue();
					}
				}
				if(expectedExecutionTime==null){
					expectedExecutionTime = FixFlowFactory.eINSTANCE.createExpectedExecutionTime();

					final FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__EXPECTED_EXECUTION_TIME, expectedExecutionTime);


					@SuppressWarnings("restriction")
					TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {

							if (userTask.getExtensionValues().size() > 0) {
								userTask.getExtensionValues().get(0).getValue().add(extensionElementEntry);
							} else {
								ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
								extensionElement.getValue().add(extensionElementEntry);
								userTask.getExtensionValues().add(extensionElement);
							}

						}
					});
					
					
					
				}

			}

		}
		
		spinner.setSelection(expectedExecutionTime.getDay());
		spinner_1.setSelection(expectedExecutionTime.getHour());
		spinner_2.setSelection(expectedExecutionTime.getMinute());
		spinner_3.setSelection(expectedExecutionTime.getSecond());
		
		

		IObservableValue textObserver = SWTObservables.observeSelection(spinner);// (text,
		// SWT.Modify);
		textObserver.addValueChangeListener(new IValueChangeListener() {

			@SuppressWarnings("restriction")
			@Override
			public void handleValueChange(final ValueChangeEvent e) {

			
					TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							expectedExecutionTime.setDay(StringUtil.getInt(e.diff.getNewValue()));
						}
					});
				}
			
		});
		
		
		IObservableValue textObserver1 = SWTObservables.observeSelection(spinner_1);// (text,
		// SWT.Modify);
		textObserver1.addValueChangeListener(new IValueChangeListener() {

			@SuppressWarnings("restriction")
			@Override
			public void handleValueChange(final ValueChangeEvent e) {

			
				if(Integer.parseInt(e.diff.getNewValue().toString())>23){
					spinner_1.setSelection(23);
					return;
				}
				
					TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							
							expectedExecutionTime.setHour(StringUtil.getInt(e.diff.getNewValue()));
						}
					});
				}
			
		});
		
		IObservableValue textObserver2 = SWTObservables.observeSelection(spinner_2);// (text,
		// SWT.Modify);
		textObserver2.addValueChangeListener(new IValueChangeListener() {

			@SuppressWarnings("restriction")
			@Override
			public void handleValueChange(final ValueChangeEvent e) {

				if(Integer.parseInt(e.diff.getNewValue().toString())>59){
					spinner_2.setSelection(59);
					return;
				}
				
			
					TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							expectedExecutionTime.setMinute(StringUtil.getInt(e.diff.getNewValue()));
						}
					});
				}
			
		});
		
		IObservableValue textObserver3 = SWTObservables.observeSelection(spinner_3);// (text,
		// SWT.Modify);
		textObserver3.addValueChangeListener(new IValueChangeListener() {

			@SuppressWarnings("restriction")
			@Override
			public void handleValueChange(final ValueChangeEvent e) {

				if(Integer.parseInt(e.diff.getNewValue().toString())>59){
					spinner_3.setSelection(59);
					return;
				}
				
					TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							expectedExecutionTime.setSecond(StringUtil.getInt(e.diff.getNewValue()));
						}
					});
				}
			
		});
		
		
		
		
		expressionComboViewer1.addExpressionChangedListeners(new IExpressionChangedListener() {
			
			@Override
			public void expressionChanged(final ExpressionChangedEvent event) {
				// TODO Auto-generated method stub
				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {

						setTaskSubjectExtensionExpression(userTask,event.getExpressionTo());

					}
				});
			}
		});

	}
	
	
	
	private void setTaskSubjectExtensionExpression(BaseElement baseElement, ExpressionTo expressionTo) {


		for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {

			FeatureMap extensionElements = extensionAttributeValue.getValue();
			for (Entry entry : extensionElements) {
				if (entry.getValue() instanceof TaskSubject) {
					if(expressionTo==null)
					{
						extensionElements.remove(entry);
					}
					else{
						TaskSubject taskSubject = (TaskSubject) entry.getValue();
						taskSubject.getExpression().setName(expressionTo.getName());
						taskSubject.getExpression().setValue(expressionTo.getExpressionText());
					}

					return;
				}
			}

		}
		if(expressionTo!=null)
		{
			TaskSubject taskSubject = FixFlowFactory.eINSTANCE.createTaskSubject();

			Expression expression = FixFlowFactory.eINSTANCE.createExpression();
			expression.setName(expressionTo.getName());
			expression.setValue(expressionTo.getExpressionText());
			taskSubject.setExpression(expression);

			FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_SUBJECT, taskSubject);

			if (baseElement.getExtensionValues().size() > 0) {
				baseElement.getExtensionValues().get(0).getValue().add(extensionElementEntry);
			} else {
				ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
				extensionElement.getValue().add(extensionElementEntry);
				baseElement.getExtensionValues().add(extensionElement);
			}
		}

		

	}

}
