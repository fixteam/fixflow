package com.founder.fix.fixflow.designer.jobconfig;

import java.sql.Connection;
import java.sql.SQLException;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import com.founder.fix.fixflow.designer.util.FixFlowConfigUtil;

public class JobConfigAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;

	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		
		
		Connection connection = null;
		connection = FixFlowConfigUtil.createConnection();
		if (connection == null) {
			return;
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				MessageDialog.openWarning(null, "提示", "连接失败，请检查配置！" + '\n' + "错误信息：" + '\n' + e.getMessage());
			}
		}

		JobConfigDialog jobConfigDialog = new JobConfigDialog(window == null ? null : window.getShell());
		jobConfigDialog.setBlockOnOpen(true);
		jobConfigDialog.open();
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
