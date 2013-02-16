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
package org.eclipse.bpmn2.modeler.ui.util;

import org.eclipse.bpmn2.modeler.ui.Activator;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.PartSite;

public class ErrorUtils {
	public static void throwCoreException(String message) throws CoreException {
		IStatus status = new Status(IStatus.ERROR,
				Activator.PLUGIN_ID, IStatus.OK, message, null);
		Platform.getLog(Activator.getDefault().getBundle()).log(status);
		throw new CoreException(status);
	}
	

	public static void showErrorWithLogging(IStatus status){
		Platform.getLog(Activator.getDefault().getBundle()).log(status);
		ErrorDialog.openError(PlatformUI.getWorkbench().getDisplay().getActiveShell(), "An error occured", null, status);
	}


	public static void showErrorMessage(final String msg) {
		Display.getDefault().asyncExec( new Runnable() {
	
			@Override
			public void run() {
				IWorkbench wb = PlatformUI.getWorkbench();
				IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
				IWorkbenchPage page = win.getActivePage();
				IWorkbenchPart part = page.getActivePart();
				if (part==null)
					return;
				
				IActionBars actionBars = null;
				IWorkbenchPartSite site = part.getSite();
				if (site instanceof IViewSite)
					actionBars = ((IViewSite) site).getActionBars();
				else if (site instanceof IEditorSite)
					actionBars = ((IEditorSite) site).getActionBars();
				else if (site instanceof PartSite)
					actionBars = ((PartSite) site).getActionBars();
	
				if( actionBars == null )
					return;
	
				IStatusLineManager statusLineManager = actionBars.getStatusLineManager();
				if( statusLineManager == null )
					return;
				
				statusLineManager.setErrorMessage(msg);
				statusLineManager.markDirty();
				statusLineManager.update(true);
			}
		});
	}
	
}
