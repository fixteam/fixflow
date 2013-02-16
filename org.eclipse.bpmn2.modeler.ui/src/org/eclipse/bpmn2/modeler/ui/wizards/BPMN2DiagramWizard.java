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
 * @author Innar Made
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.ui.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;

public class BPMN2DiagramWizard extends Wizard implements INewWizard {
	private BPMN2DiagramWizardPage1 page1;
	private BPMN2DiagramWizardPage2 page2;
	private ISelection selection;

	/**
	 * Constructor for BPMN2DiagramWizard.
	 */
	public BPMN2DiagramWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	/**
	 * Adding the page2 to the wizard.
	 */

	@Override
	public void addPages() {
		page1 = new BPMN2DiagramWizardPage1(selection);
		addPage(page1);
		page2 = new BPMN2DiagramWizardPage2(selection);
		addPage(page2);
	}

	/**
	 * This method is called when 'Finish' button is pressed in the wizard. We will create an operation and run it using
	 * wizard as execution context.
	 */
	@Override
	public boolean performFinish() {
		final String fileName = page2.getFileName();
		final IResource container = page2.getDiagramContainer();
		final String targetNamespace = page2.getTargetNamespace();

		IRunnableWithProgress op = new IRunnableWithProgress() {
			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					IPath path = container.getFullPath().append(fileName);
					IFolder folder = null;
					BPMN2DiagramCreator factory = new BPMN2DiagramCreator();

					folder = BPMN2DiagramCreator.getTempFolder(path);

					factory.setDiagramFile(BPMN2DiagramCreator.getTempFile(path,folder));

					factory.setDiagramFolder(folder);

					factory.createDiagram(page1.getDiagramType(), targetNamespace);

				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * We will accept the selection in the workbench to see if we can initialize from it.
	 * 
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
}