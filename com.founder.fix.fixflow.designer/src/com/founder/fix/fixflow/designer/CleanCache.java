package com.founder.fix.fixflow.designer;
import java.util.ArrayList;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import com.founder.fix.fixflow.designer.modeler.ui.common.UserInfoEntity;
import com.founder.fix.fixflow.designer.util.DataVarUtil;
import com.founder.fix.fixflow.designer.util.FixFlowConfigUtil;


public class CleanCache implements IWorkbenchWindowActionDelegate {

	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		DataVarUtil.dataVariableConfig = null;
		DataVarUtil.userInforeturnList = null;
		DataVarUtil.userInforeturnListMap=null;
		DataVarUtil.map = null;
		FixFlowConfigUtil.fixFlowConfig = null;
		MessageDialog.openInformation(null, "提示", "缓存已经清空！");
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

	}

}
