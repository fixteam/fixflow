package com.founder.fix.fixflow.designer.modeler.ui.common;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class FixFlowInputCellEditor extends DialogCellEditor {
	private Shell shell;
	public String text;
	
	public FixFlowInputCellEditor(Composite parent, Shell shell) {
		super(parent);
		this.shell = shell;
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		// TODO Auto-generated method stub
		FixFlowInputCellDialog ffid = new FixFlowInputCellDialog(shell, text);
		ffid.setBlockOnOpen(true);
		if(ffid != null && ffid.open() == InputDialog.OK) {
			return ffid.getDatatext();
		}
		return null;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
