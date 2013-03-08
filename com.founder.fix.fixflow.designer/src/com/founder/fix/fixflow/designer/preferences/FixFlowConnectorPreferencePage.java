package com.founder.fix.fixflow.designer.preferences;

import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.founder.fix.fixflow.designer.Activator;

public class FixFlowConnectorPreferencePage extends FixFlowPreferencePage
		implements IWorkbenchPreferencePage {

	public FixFlowConnectorPreferencePage() {
		// TODO Auto-generated constructor stub
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Fix 连接器配置");
	}

	@Override
	public void createFieldEditors() {
		// TODO Auto-generated method stub
		addField(
				new StringFieldEditor(PreferenceConstants.CONNECTORPATH, "连接器地址:", getFieldEditorParent()));
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub
		super.init(workbench);
	}

	@Override
	protected Control createContents(Composite parent) {
		// TODO Auto-generated method stub
		return super.createContents(parent);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
	}

	@Override
	protected void performDefaults() {
		// TODO Auto-generated method stub
		super.performDefaults();
	}

	@Override
	public boolean performOk() {
		// TODO Auto-generated method stub
		return super.performOk();
	}

	@Override
	protected void performApply() {
		// TODO Auto-generated method stub
		super.performApply();
	}

}
