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

package org.eclipse.bpmn2.modeler.ui.wizards;

import org.eclipse.bpmn2.modeler.core.utils.ModelUtil.Bpmn2DiagramType;
import org.eclipse.bpmn2.modeler.ui.Activator;
import org.eclipse.bpmn2.modeler.ui.IConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Bob Brodt
 *
 */
public class BPMN2DiagramWizardPage1 extends WizardPage implements IConstants {

	private Bpmn2DiagramType diagramType = Bpmn2DiagramType.NONE;
	private final ISelection selection;

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public BPMN2DiagramWizardPage1(ISelection selection) {
		super("wizardPage1");
		setTitle("业务流程类型");
		setDescription("请选择一个业务流程模型.");
		this.selection = selection;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout(2,false);
		container.setLayout(layout);

		Point sz = parent.getSize();
		int labelWidth = (int)(0.5 * sz.x); 
		GridData data;
		
		final Button processButton = new Button(container, SWT.RADIO | SWT.PUSH);
//		processButton.setText("Process");
		GridData gd_processButton = new GridData(SWT.FILL,SWT.FILL,false,false,1,1);
		gd_processButton.heightHint = 100;
		processButton.setLayoutData(gd_processButton);
		processButton.setImage(Activator.getDefault().getImage(IMAGE_PROCESS));
		
		Label processLabel = new Label(container, SWT.WRAP | SWT.NONE);
		data = new GridData(SWT.RIGHT,SWT.CENTER,false,false,1,1);
		data.widthHint = labelWidth;
		processLabel.setLayoutData(data);
		processLabel.setText("独立业务流程");

		final Button collaborationButton = new Button(container, SWT.RADIO | SWT.PUSH);
//		collaborationButton.setText("Collaboration");
		GridData gd_collaborationButton = new GridData(SWT.FILL,SWT.FILL,false,false,1,1);
		gd_collaborationButton.heightHint = 100;
		collaborationButton.setLayoutData(gd_collaborationButton);
		collaborationButton.setImage(Activator.getDefault().getImage(IMAGE_COLLABORATION));
		
		Label collaborationLabel = new Label(container, SWT.WRAP | SWT.NONE);
		data = new GridData(SWT.RIGHT,SWT.CENTER,false,false,1,1);
		data.widthHint = labelWidth;
		collaborationLabel.setLayoutData(data);
		collaborationLabel.setText("协作业务流程");
		
		final Button choreographyButton = new Button(container, SWT.RADIO | SWT.PUSH);
//		choreographyButton.setText("Choreography");
		GridData gd_choreographyButton = new GridData(SWT.FILL,SWT.FILL,false,false,1,1);
		gd_choreographyButton.heightHint = 100;
		choreographyButton.setLayoutData(gd_choreographyButton);
		choreographyButton.setImage(Activator.getDefault().getImage(IMAGE_CHOREOGRAPHY));

		Label choreographyLabel = new Label(container, SWT.WRAP | SWT.NONE);
		data = new GridData(SWT.RIGHT,SWT.CENTER,false,false,1,1);
		data.widthHint = labelWidth;
		choreographyLabel.setLayoutData(data);
		choreographyLabel.setText("编舞业务流程");
		
		SelectionAdapter buttonListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (e.widget == processButton) {
					diagramType = Bpmn2DiagramType.PROCESS;
					processButton.setImage(Activator.getDefault().getImage(IMAGE_PROCESS_PUSHED));
					collaborationButton.setImage(Activator.getDefault().getImage(IMAGE_COLLABORATION));
					choreographyButton.setImage(Activator.getDefault().getImage(IMAGE_CHOREOGRAPHY));
				}
				else if (e.widget == collaborationButton) {
					diagramType = Bpmn2DiagramType.COLLABORATION;
					processButton.setImage(Activator.getDefault().getImage(IMAGE_PROCESS));
					collaborationButton.setImage(Activator.getDefault().getImage(IMAGE_COLLABORATION_PUSHED));
					choreographyButton.setImage(Activator.getDefault().getImage(IMAGE_CHOREOGRAPHY));
				}
				else if (e.widget == choreographyButton) {
					diagramType = Bpmn2DiagramType.CHOREOGRAPHY;
					processButton.setImage(Activator.getDefault().getImage(IMAGE_PROCESS));
					collaborationButton.setImage(Activator.getDefault().getImage(IMAGE_COLLABORATION));
					choreographyButton.setImage(Activator.getDefault().getImage(IMAGE_CHOREOGRAPHY_PUSHED));
				}
				else {
					diagramType = Bpmn2DiagramType.NONE;
					processButton.setImage(Activator.getDefault().getImage(IMAGE_PROCESS));
					collaborationButton.setImage(Activator.getDefault().getImage(IMAGE_COLLABORATION));
					choreographyButton.setImage(Activator.getDefault().getImage(IMAGE_CHOREOGRAPHY));
				}
				setPageComplete(canFlipToNextPage());
			}
		};
		processButton.addSelectionListener(buttonListener);
		collaborationButton.addSelectionListener(buttonListener);
		choreographyButton.addSelectionListener(buttonListener);
		
		setControl(container);
	}

	@Override
	public boolean isPageComplete() {
		if(diagramType!=Bpmn2DiagramType.PROCESS){
			return false;
		}
		return diagramType != Bpmn2DiagramType.NONE;
	}

	@Override
	public boolean canFlipToNextPage() {
		if(diagramType!=Bpmn2DiagramType.PROCESS){
			return false;
		}
		return diagramType != Bpmn2DiagramType.NONE;
	}

	public Bpmn2DiagramType getDiagramType() {
		return diagramType;
	}
}
