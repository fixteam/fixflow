package com.founder.fix.fixflow.designer.connector;

import java.io.File;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import com.founder.fix.fixflow.designer.modeler.ui.property.connectors.dialog.OverrideWizard;
import com.founder.fix.fixflow.designer.util.ConnectorUtil;

public class EditFixConnector implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;

	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		File file = new File(ConnectorUtil.getConnectorPath());
		if(!file.exists()){
			MessageDialog.openWarning(window.getShell(), "提示", "找不到连接器存放路径，请设置");
			return;
		}else{
			OverrideWizard dialog = new OverrideWizard(window == null ? null : window.getShell(), 
					new EditConnectorWizard());
			dialog.setPageSize(-1, 400); // -1代表宽度自适应, 240为高度
			dialog.open();
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
