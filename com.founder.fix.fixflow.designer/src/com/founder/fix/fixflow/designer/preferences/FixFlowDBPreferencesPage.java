/**
 * 
 */
package com.founder.fix.fixflow.designer.preferences;


import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.founder.fix.fixflow.designer.Activator;

/**
 * @author Administrator
 *
 */
public class FixFlowDBPreferencesPage extends FixFlowPreferencePage implements
		IWorkbenchPreferencePage {

	/**
	 * 
	 */
	public FixFlowDBPreferencesPage() {
		// TODO Auto-generated constructor stub
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Fix 数据库配置");
	}

	@Override
	public void createFieldEditors() {
		// TODO Auto-generated method stub
		/*addField(new DirectoryFieldEditor(PreferenceConstants.P_PATH, 
				"&Directory preference:", getFieldEditorParent()));
		addField(
			new BooleanFieldEditor(
				PreferenceConstants.P_BOOLEAN,
				"&An example of a boolean preference",
				getFieldEditorParent()));

		addField(new RadioGroupFieldEditor(
				PreferenceConstants.P_CHOICE,
			"An example of a multiple-choice preference",
			1,
			new String[][] { { "&Choice 1", "choice1" }, {
				"C&hoice 2", "choice2" }
		}, getFieldEditorParent()));*/
		addField(
			new StringFieldEditor(PreferenceConstants.DBDRIVERNAME, "驱动:", getFieldEditorParent()));
		addField(
				new StringFieldEditor(PreferenceConstants.DBURL, "URL:", getFieldEditorParent()));
		addField(
				new StringFieldEditor(PreferenceConstants.DBUSER, "用户名:", getFieldEditorParent()));
		
		addField(new PasswordFieldEditor(PreferenceConstants.DBPASSWORD, "密码:", getFieldEditorParent()));
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
