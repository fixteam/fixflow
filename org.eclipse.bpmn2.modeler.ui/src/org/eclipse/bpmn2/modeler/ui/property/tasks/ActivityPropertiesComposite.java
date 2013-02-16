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


package org.eclipse.bpmn2.modeler.ui.property.tasks;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.LoopCharacteristics;
import org.eclipse.bpmn2.MultiInstanceLoopCharacteristics;
import org.eclipse.bpmn2.StandardLoopCharacteristics;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.ui.property.DefaultPropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.PropertiesCompositeFactory;
import org.eclipse.bpmn2.modeler.ui.util.PropertyUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class ActivityPropertiesComposite extends DefaultPropertiesComposite {

	static {
		PropertiesCompositeFactory.register(StandardLoopCharacteristics.class, StandardLoopCharacteristicsPropertiesComposite.class);
		PropertiesCompositeFactory.register(MultiInstanceLoopCharacteristics.class, MultiInstanceLoopCharacteristicsPropertiesComposite.class);
	}

	private Button addStandardLoopButton;
	private Button addMultiLoopButton;
	private Button removeLoopButton;
	protected AbstractBpmn2PropertiesComposite loopCharacteristicsComposite;
	
	public ActivityPropertiesComposite(Composite parent, int style) {
		super(parent, style);
	}

	/**
	 * @param section
	 */
	public ActivityPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	@Override
	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
		if (propertiesProvider==null) {
			propertiesProvider = new AbstractPropertiesProvider(object) {
				String[] properties = new String[] {
						"anyAttribute",
						"calledElementRef", // only used in CallActivity
						"completionQuantity",
						"startQuantity",
						"isForCompensation",
						"boundaryEventDefs",
						"properties",
						"resources",
						"loopCharacteristics",
				};
				
				@Override
				public String[] getProperties() {
					return properties; 
				}
			};
		}
		return propertiesProvider;
	}

	protected void bindReference(Composite parent, EObject object, EReference reference) {
		if ("loopCharacteristics".equals(reference.getName())) {

			final Activity activity = (Activity) be;
			LoopCharacteristics loopCharacteristics = (LoopCharacteristics) activity.getLoopCharacteristics();
				
			if (loopCharacteristics != null) {
				loopCharacteristicsComposite = PropertiesCompositeFactory.createComposite(LoopCharacteristics.class, this, SWT.NONE);
				loopCharacteristicsComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

				loopCharacteristicsComposite.setEObject(getDiagramEditor(), loopCharacteristics);

				removeLoopButton = toolkit.createButton(loopCharacteristicsComposite.getAttributesParent(), "Remove Loop Characteristics", SWT.PUSH);
				removeLoopButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 3, 1));
				removeLoopButton.addSelectionListener(new SelectionAdapter() {
					
					public void widgetSelected(SelectionEvent e) {
						@SuppressWarnings("restriction")
						TransactionalEditingDomain domain = getDiagramEditor().getEditingDomain();
						domain.getCommandStack().execute(new RecordingCommand(domain) {
							@Override
							protected void doExecute() {
								if (activity.getLoopCharacteristics() !=null)
									activity.setLoopCharacteristics(null);
								setEObject(activity);
							}
						});
					}
				});
				
				loopCharacteristicsComposite.setTitle(loopCharacteristics instanceof StandardLoopCharacteristics ?
						"Standard Loop Characteristics" : "Multi-Instance Loop Characteristics");
			}
			else {
				Composite composite = getAttributesParent();

				createLabel(composite, "Loop Characteristics");
				
				Composite buttonComposite = toolkit.createComposite(composite);
				buttonComposite.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
				FillLayout layout = new FillLayout();
				layout.marginWidth = 20;
				buttonComposite.setLayout(layout);
				
				Button noneButton = toolkit.createButton(buttonComposite, "None", SWT.RADIO);
				noneButton.setSelection(true);
				
				addStandardLoopButton = toolkit.createButton(buttonComposite, "Standard", SWT.RADIO);
				if (!modelEnablement.isEnabled(PACKAGE.getStandardLoopCharacteristics()))
					addStandardLoopButton.setVisible(false);
				addStandardLoopButton.addSelectionListener(new SelectionAdapter() {
					
					public void widgetSelected(SelectionEvent e) {
						if (addStandardLoopButton.getSelection()) {
							@SuppressWarnings("restriction")
							TransactionalEditingDomain domain = getDiagramEditor().getEditingDomain();
							domain.getCommandStack().execute(new RecordingCommand(domain) {
								@Override
								protected void doExecute() {
									StandardLoopCharacteristics loopChar = FACTORY.createStandardLoopCharacteristics();
									activity.setLoopCharacteristics(loopChar);
									ModelUtil.setID(loopChar);
									setEObject(activity);
								}
							});
						}
					}
				});
	
				addMultiLoopButton = toolkit.createButton(buttonComposite, "Multi-Instance", SWT.RADIO);
				if (!modelEnablement.isEnabled(PACKAGE.getMultiInstanceLoopCharacteristics()))
					addMultiLoopButton.setVisible(false);
				addMultiLoopButton.addSelectionListener(new SelectionAdapter() {
					
					public void widgetSelected(SelectionEvent e) {
						if (addMultiLoopButton.getSelection()) {
							@SuppressWarnings("restriction")
							TransactionalEditingDomain domain = getDiagramEditor().getEditingDomain();
							domain.getCommandStack().execute(new RecordingCommand(domain) {
								@Override
								protected void doExecute() {
									MultiInstanceLoopCharacteristics loopChar = FACTORY.createMultiInstanceLoopCharacteristics();
									activity.setLoopCharacteristics(loopChar);
									ModelUtil.setID(loopChar);
									setEObject(activity);
								}
							});
						}
					}
				});
			}

			PropertyUtil.recursivelayout(this);
		}
		else
			super.bindReference(parent, object, reference);
	}
}