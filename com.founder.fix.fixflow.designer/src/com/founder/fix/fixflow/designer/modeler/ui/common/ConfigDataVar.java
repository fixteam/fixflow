package com.founder.fix.fixflow.designer.modeler.ui.common;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class ConfigDataVar implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;

	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		DataVarConfigurationDialog dataVarConfigurationDialog = new DataVarConfigurationDialog(window == null ?
				null : window.getShell());
		dataVarConfigurationDialog.setBlockOnOpen(true);
		if(dataVarConfigurationDialog != null && dataVarConfigurationDialog.open() == InputDialog.OK){
			
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		this.window = window;
	}

}
