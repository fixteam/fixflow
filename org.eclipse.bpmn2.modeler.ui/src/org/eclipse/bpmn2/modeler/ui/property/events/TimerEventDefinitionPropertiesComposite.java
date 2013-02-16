/*******************************************************************************
 * Copyright (c) 2011 Red Hat, Inc.
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 *
 * @author Bob Brodt
 ******************************************************************************/

package org.eclipse.bpmn2.modeler.ui.property.events;

import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.TimerEventDefinition;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.ui.property.DefaultPropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.editors.TextObjectEditor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Bob Brodt
 *
 */
public class TimerEventDefinitionPropertiesComposite extends DefaultPropertiesComposite {

	private Button timeDateButton;
	private Button timeCycleButton;
	private Button timeDurationButton;

	enum TimerType {
		NONE,
		TIMEDATE,
		TIMECYCLE,
		TIMEDURATION
	};
	TimerType timerType = TimerType.NONE;
	
	/**
	 * @param parent
	 * @param style
	 */
	public TimerEventDefinitionPropertiesComposite(Composite parent, int style) {
		super(parent, style);
	}

	/**
	 * @param section
	 */
	public TimerEventDefinitionPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	@Override
	public void createBindings(EObject be) {
		final TimerEventDefinition event = (TimerEventDefinition)be;

		Composite composite = getAttributesParent();

		Label label = createLabel(composite, "Timer Type");
		Composite buttonComposite = toolkit.createComposite(composite);
		buttonComposite.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		FillLayout layout = new FillLayout();
		layout.marginWidth = 10;
		layout.spacing = 20;
		buttonComposite.setLayout(layout);
		
		FormalExpression exp = null;
		
		timeDateButton = toolkit.createButton(buttonComposite, "Time/Date", SWT.RADIO);
		timeCycleButton = toolkit.createButton(buttonComposite, "Interval", SWT.RADIO);
		timeDurationButton = toolkit.createButton(buttonComposite, "Duration", SWT.RADIO);
		
		if (!modelEnablement.isEnabled(event.eClass(), PACKAGE.getTimerEventDefinition_TimeDate()))
			timeDateButton.setVisible(false);
		if (!modelEnablement.isEnabled(event.eClass(), PACKAGE.getTimerEventDefinition_TimeCycle()))
			timeCycleButton.setVisible(false);
		if (!modelEnablement.isEnabled(event.eClass(), PACKAGE.getTimerEventDefinition_TimeDuration()))
			timeDurationButton.setVisible(false);
		
		if (event.getTimeDate()!=null) {
			exp = (FormalExpression)event.getTimeDate();
			timeDateButton.setSelection(true);
			timerType = TimerType.TIMEDATE;
		}
		else if (event.getTimeCycle()!=null) {
			exp = (FormalExpression)event.getTimeCycle();
			timeCycleButton.setSelection(true);
			timerType = TimerType.TIMECYCLE;
		}
		else if (event.getTimeDuration()!=null) {
			exp = (FormalExpression)event.getTimeDuration();
			timeDurationButton.setSelection(true);
			timerType = TimerType.TIMEDURATION;
		}
		else
			exp = FACTORY.createFormalExpression();
		if (exp.getBody()==null)
			exp.setBody("");
		
		final TextObjectEditor editor = new TextObjectEditor(this, exp, PACKAGE.getFormalExpression_Body());
		
		timeDateButton.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (timeDurationButton.getSelection() && timerType!=TimerType.TIMEDATE) {
					TransactionalEditingDomain domain = getDiagramEditor().getEditingDomain();
					domain.getCommandStack().execute(new RecordingCommand(domain) {
						@Override
						protected void doExecute() {
							FormalExpression exp = FACTORY.createFormalExpression();
							ModelUtil.setID(exp);
							event.eUnset(PACKAGE.getTimerEventDefinition_TimeCycle());
							event.eUnset(PACKAGE.getTimerEventDefinition_TimeDuration());
							event.setTimeDate(exp);
							exp.setBody("");
							editor.setObject(exp);
							timerType = TimerType.TIMEDATE;
						}
					});
				}
			}
		});
		
		timeCycleButton.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (timeCycleButton.getSelection() && timerType!=TimerType.TIMECYCLE) {
					TransactionalEditingDomain domain = getDiagramEditor().getEditingDomain();
					domain.getCommandStack().execute(new RecordingCommand(domain) {
						@Override
						protected void doExecute() {
							FormalExpression exp = FACTORY.createFormalExpression();
							ModelUtil.setID(exp);
							event.eUnset(PACKAGE.getTimerEventDefinition_TimeDate());
							event.eUnset(PACKAGE.getTimerEventDefinition_TimeDuration());
							event.setTimeCycle(exp);
							exp.setBody("");
							editor.setObject(exp);
							timerType = TimerType.TIMECYCLE;
						}
					});
				}
			}
		});
		
		timeDurationButton.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				if (timeDurationButton.getSelection() && timerType!=TimerType.TIMEDURATION) {
					TransactionalEditingDomain domain = getDiagramEditor().getEditingDomain();
					domain.getCommandStack().execute(new RecordingCommand(domain) {
						@Override
						protected void doExecute() {
							FormalExpression exp = FACTORY.createFormalExpression();
							ModelUtil.setID(exp);
							event.eUnset(PACKAGE.getTimerEventDefinition_TimeDate());
							event.eUnset(PACKAGE.getTimerEventDefinition_TimeCycle());
							event.setTimeDuration(exp);
							exp.setBody("");
							editor.setObject(exp);
							timerType = TimerType.TIMEDURATION;
						}
					});
				}
			}
		});
		
		editor.createControl(composite, "Value");
	}

}
