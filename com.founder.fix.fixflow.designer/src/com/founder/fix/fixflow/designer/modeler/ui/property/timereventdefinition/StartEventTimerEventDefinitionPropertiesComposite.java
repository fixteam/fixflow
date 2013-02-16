package com.founder.fix.fixflow.designer.modeler.ui.property.timereventdefinition;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.TimerEventDefinition;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import com.founder.fix.fixflow.designer.modeler.ui.property.AbstractFixFlowBpmn2PropertiesComposite;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionChangedEvent;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionComboViewer;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionTo;
import com.founder.fix.fixflow.designer.usercontrol.IExpressionChangedListener;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class StartEventTimerEventDefinitionPropertiesComposite extends AbstractFixFlowBpmn2PropertiesComposite {
	private DataBindingContext m_bindingContext;
	

	
	private Label lblNewLabel;
	private Label label;
//	private Label label_1;
	private ExpressionComboViewer expressionComboViewer1;
	private ExpressionComboViewer expressionComboViewer2;

	// private ExpressionComboViewer expressionComboViewer3;
	public StartEventTimerEventDefinitionPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}
	public StartEventTimerEventDefinitionPropertiesComposite(Composite parent, int style) {
		super(parent, style);

	}

	@Override
	public void createUI() {
		GridLayout gridLayout = new GridLayout(3, false);
		gridLayout.marginBottom = 10;
		gridLayout.marginRight = 10;
		gridLayout.marginLeft = 10;
		gridLayout.marginHeight = 10;
		gridLayout.verticalSpacing = 10;
		gridLayout.marginWidth = 10;
		setLayout(gridLayout);

		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setText("执行时间");

	
		toolkit.adapt(lblNewLabel_1, true, true);

		/*
		 * Label lblNewLabel_3 = new Label(this, SWT.NONE);
		 * lblNewLabel_3.setBounds(10, 76, 48, 14);
		 * lblNewLabel_3.setText("持续时间");
		 * 
		 * widgets.add(lblNewLabel_3); toolkit.adapt(lblNewLabel_3, true, true);
		 */

		expressionComboViewer1 = new ExpressionComboViewer(this, SWT.READ_ONLY | SWT.BORDER);
		Combo combo = expressionComboViewer1.getCombo();
		GridData gd_combo = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_combo.widthHint = 200;
		combo.setLayoutData(gd_combo);
		combo.setBounds(71, 15, 187, 20);
		
		toolkit.adapt(combo, true, true);

		lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		lblNewLabel.setText("(指定流程开始定时启动的时间)");
	
		toolkit.adapt(lblNewLabel, true, true);

		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setText("时间表达式");

	
		toolkit.adapt(lblNewLabel_2, true, true);

		expressionComboViewer2 = new ExpressionComboViewer(this, SWT.READ_ONLY | SWT.BORDER);
		Combo combo_1 = expressionComboViewer2.getCombo();
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		toolkit.adapt(combo_1, true, true);

		label = new Label(this, SWT.NONE);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		label.setText("(使用表达式指定启动的时间)");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		
		toolkit.adapt(label, true, true);

		/*
		 * label_1 = new Label(this, SWT.NONE);
		 * label_1.setText("(指定流程定时启动的持续时间)");
		 * label_1.setForeground(SWTResourceManager
		 * .getColor(SWT.COLOR_DARK_GRAY)); label_1.setBounds(264, 76, 227, 14);
		 * widgets.add(label_1); toolkit.adapt(label_1, true, true);
		 */

		/*
		 * expressionComboViewer3 = new ExpressionComboViewer(this,
		 * SWT.READ_ONLY | SWT.BORDER); Combo combo_2 =
		 * expressionComboViewer3.getCombo(); combo_2.setBounds(71, 76, 187,
		 * 20); widgets.add(combo_2); toolkit.adapt(combo_2, true, true);
		 */

	}

	@Override
	public void createUIBindings(EObject eObject) {

		StartEvent startEvent = (StartEvent) be;

		// 绑定开始时间
		final TimerEventDefinition timerEventDefinition = (TimerEventDefinition) startEvent.getEventDefinitions().get(0);

		if (timerEventDefinition.getTimeDate() != null) {
			FormalExpression formalExpression = (FormalExpression) timerEventDefinition.getTimeDate();
			if (formalExpression.getBody() != null && !formalExpression.getBody().equals("")) {
				ExpressionTo expressionTo = new ExpressionTo();
				expressionTo.setName(formalExpression.getId());
				expressionTo.setExpressionText(formalExpression.getBody());
				expressionComboViewer1.setDefaultExpressionInput(expressionTo);
			}

		}

		expressionComboViewer1.addExpressionChangedListeners(new IExpressionChangedListener() {

			@SuppressWarnings("restriction")
			@Override
			public void expressionChanged(final ExpressionChangedEvent event) {
				// TODO Auto-generated method stub
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {

						if (event.getExpressionTo() == null) {
							timerEventDefinition.setTimeDate(null);
						} else {
							FormalExpression formalExpression = Bpmn2Factory.eINSTANCE.createFormalExpression();
							formalExpression.setId(event.getExpressionTo().getName());
							formalExpression.setBody(event.getExpressionTo().getExpressionText());
							timerEventDefinition.setTimeDate(formalExpression);
						}

					}
				});
			}
		});

		// 绑定时间间隔
		if (timerEventDefinition.getTimeCycle() != null) {
			FormalExpression formalExpression = (FormalExpression) timerEventDefinition.getTimeCycle();
			if (formalExpression.getBody() != null && !formalExpression.getBody().equals("")) {
				ExpressionTo expressionTo = new ExpressionTo();
				expressionTo.setName(formalExpression.getId());
				expressionTo.setExpressionText(formalExpression.getBody());
				expressionComboViewer2.setDefaultExpressionInput(expressionTo);
			}

		}

		expressionComboViewer2.addExpressionChangedListeners(new IExpressionChangedListener() {

			@SuppressWarnings("restriction")
			@Override
			public void expressionChanged(final ExpressionChangedEvent event) {
				// TODO Auto-generated method stub
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {

						if (event.getExpressionTo() == null) {
							timerEventDefinition.setTimeCycle(null);
						} else {
							FormalExpression formalExpression = Bpmn2Factory.eINSTANCE.createFormalExpression();
							formalExpression.setId(event.getExpressionTo().getName());
							formalExpression.setBody(event.getExpressionTo().getExpressionText());
							timerEventDefinition.setTimeCycle(formalExpression);
						}
					}
				});
			}
		});

		/*
		 * //绑定持续时间 if(timerEventDefinition.getTimeDuration()!=null) {
		 * FormalExpression
		 * formalExpression=(FormalExpression)timerEventDefinition
		 * .getTimeDuration();
		 * if(formalExpression.getBody()!=null&&!formalExpression
		 * .getBody().equals("")) { ExpressionTo expressionTo = new
		 * ExpressionTo(); expressionTo.setName(formalExpression.getId());
		 * expressionTo.setExpressionText(formalExpression.getBody());
		 * expressionComboViewer3.setDefaultExpressionInput(expressionTo); }
		 * 
		 * }
		 */

		/*
		 * expressionComboViewer3.addExpressionChangedListeners(new
		 * IExpressionChangedListener() {
		 * 
		 * @Override public void expressionChanged(final ExpressionChangedEvent
		 * event) { // TODO Auto-generated method stub
		 * TransactionalEditingDomain editingDomain =
		 * bpmn2Editor.getEditingDomain();
		 * editingDomain.getCommandStack().execute(new
		 * RecordingCommand(editingDomain) {
		 * 
		 * @Override protected void doExecute() {
		 * 
		 * if(timerEventDefinition.getTimeDuration()==null) { FormalExpression
		 * formalExpression=ModelHandler.FACTORY.createFormalExpression();
		 * formalExpression.setId(event.getExpressionTo().getName());
		 * formalExpression
		 * .setBody(event.getExpressionTo().getExpressionText());
		 * timerEventDefinition.setTimeDuration(formalExpression); } else {
		 * FormalExpression
		 * formalExpression=(FormalExpression)timerEventDefinition
		 * .getTimeDuration();
		 * formalExpression.setId(event.getExpressionTo().getName());
		 * formalExpression
		 * .setBody(event.getExpressionTo().getExpressionText()); }
		 * 
		 * } }); } });
		 */

		/*
		 * IObservableValue textObserver =
		 * SWTObservables.observeSelection(dateTime);
		 * textObserver.addValueChangeListener(new IValueChangeListener() {
		 * 
		 * @SuppressWarnings("restriction")
		 * 
		 * @Override public void handleValueChange(final ValueChangeEvent e) {
		 * 
		 * if
		 * (!dateTime.toString().equals(((FormalExpression)timerEventDefinition
		 * .getTimeDate()).getBody())) { TransactionalEditingDomain
		 * editingDomain = bpmn2Editor.getEditingDomain();
		 * editingDomain.getCommandStack().execute(new
		 * RecordingCommand(editingDomain) {
		 * 
		 * @Override protected void doExecute() { FormalExpression
		 * formalExpression = Bpmn2Factory.eINSTANCE.createFormalExpression();
		 * formalExpression.setBody(e.diff.getNewValue().toString());
		 * timerEventDefinition.setTimeDate(formalExpression); } }); } } });
		 */

	}

	
}
