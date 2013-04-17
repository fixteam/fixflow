package com.founder.fix.fixflow.designer.modeler.ui.property.boundaryevent;


import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.CatchEvent;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.TimerEventDefinition;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.widgets.Composite;

import com.founder.fix.fixflow.designer.modeler.ui.property.AbstractFixFlowBpmn2PropertiesComposite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;

import com.founder.fix.fixflow.designer.usercontrol.ExpressionChangedEvent;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionComboViewer;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionTo;
import com.founder.fix.fixflow.designer.usercontrol.IExpressionChangedListener;


public class BoundaryEventTimerEventDefinitionPropertiesComposite extends AbstractFixFlowBpmn2PropertiesComposite {
	public BoundaryEventTimerEventDefinitionPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}
	public BoundaryEventTimerEventDefinitionPropertiesComposite(Composite parent, int style) {
		super(parent, style);
		
		// TODO Auto-generated constructor stub
	}
	ExpressionComboViewer expressionComboViewer;
	@Override
	public void createUI() {
		
		setLayout(null);
		
		expressionComboViewer = new ExpressionComboViewer(this);
		Combo combo = expressionComboViewer.getCombo();
		combo.setBounds(89, 19, 244, 22);
		
		Label label = new Label(this, SWT.NONE);
		label.setText("执行器条件");
		label.setBounds(14, 22, 59, 14);
		toolkit.adapt(combo, true, true);
		
		toolkit.adapt(label, true, true);
		
		
	}

	@Override
	public void createUIBindings(EObject eObject) {
		
		
		CatchEvent boundaryEvent = (CatchEvent) be;

		// 绑定开始时间
		final TimerEventDefinition timerEventDefinition = (TimerEventDefinition) boundaryEvent.getEventDefinitions().get(0);

		if (timerEventDefinition.getTimeDate() != null) {
			FormalExpression formalExpression = (FormalExpression) timerEventDefinition.getTimeDate();
			if (formalExpression.getBody() != null && !formalExpression.getBody().equals("")) {
				ExpressionTo expressionTo = new ExpressionTo();
				expressionTo.setName(formalExpression.getId());
				expressionTo.setExpressionText(formalExpression.getBody());
				expressionComboViewer.setDefaultExpressionInput(expressionTo);
			}

		}

		expressionComboViewer.addExpressionChangedListeners(new IExpressionChangedListener() {

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


	
		
		
	}
}
