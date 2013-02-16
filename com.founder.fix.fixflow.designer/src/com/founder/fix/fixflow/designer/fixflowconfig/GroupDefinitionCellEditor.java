package com.founder.fix.fixflow.designer.fixflowconfig;

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.internal.core.SourceType;
import org.eclipse.jdt.internal.ui.JavaUIMessages;
import org.eclipse.jdt.internal.ui.dialogs.OpenTypeSelectionDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class GroupDefinitionCellEditor extends DialogCellEditor {
	private Shell shell;

	public GroupDefinitionCellEditor(Composite parent, Shell shell) {
		super(parent);
		this.shell = shell;
	}
	
	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		// TODO Auto-generated method stub
		// 打开对话框
		StringBuffer sb = new StringBuffer();
		OpenTypeSelectionDialog dialog = new OpenTypeSelectionDialog(shell,
				true, PlatformUI.getWorkbench().getProgressService(), null,
				IJavaSearchConstants.TYPE);
		dialog.setTitle(JavaUIMessages.OpenTypeAction_dialogTitle);
		dialog.setMessage(JavaUIMessages.OpenTypeAction_dialogMessage);
		int result = dialog.open();
		
		//打开文件
		Object[] files = dialog.getResult();
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				IType it = (IType) files[i];
				sb.append(it.getFullyQualifiedName());
			}
		}
		
		if (result == IDialogConstants.OK_ID && sb.length()>0) {
			return sb.toString();
		}
		return null;
	}

}
