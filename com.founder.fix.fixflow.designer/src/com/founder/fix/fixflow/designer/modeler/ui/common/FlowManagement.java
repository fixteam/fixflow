package com.founder.fix.fixflow.designer.modeler.ui.common;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class FlowManagement implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;

	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		FixFlowManageDialog fixFlowManageDialog = new FixFlowManageDialog(window == null ? null : window.getShell());
		fixFlowManageDialog.setBlockOnOpen(true);
		if(fixFlowManageDialog != null && fixFlowManageDialog.open() == InputDialog.OK){
			
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
