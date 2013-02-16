package com.founder.fix.fixflow.designer.connector;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class CreateFixConnectorWizardDialog extends WizardDialog {

	public CreateFixConnectorWizardDialog(Shell parentShell, IWizard newWizard) {
		super(parentShell, newWizard);
		setHelpAvailable(false);
		// TODO Auto-generated constructor stub
	}

	public void showPage(IWizardPage page) {
		super.showPage(page);
	/*	if (page instanceof CreatePageWizardPage) {
			getButton(IDialogConstants.CANCEL_ID).setText(IDialogConstants.BACK_LABEL);
			getButton(IDialogConstants.FINISH_ID).setText(IDialogConstants.OK_LABEL);
		} else {
			getButton(IDialogConstants.CANCEL_ID).setText(IDialogConstants.CANCEL_LABEL);
			getButton(IDialogConstants.FINISH_ID).setText(IDialogConstants.FINISH_LABEL);
		}*/
	}
	
	@Override
	public void cancelPressed() {
		super.cancelPressed();
	/*	if (getCurrentPage() instanceof CreatePageWizardPage) {
			backPressed();
		} else {
			super.cancelPressed();
		}*/
	}
		
	/**
	 * Prevent the wizard to close accidentally by pressing escape (or the red cross)
	 * @see org.eclipse.jface.window.Window#canHandleShellCloseEvent()
	 */
	@Override
	protected boolean canHandleShellCloseEvent() {
		return true;
		/*Boolean close = MessageDialog.openQuestion(getShell(), "提示", "你确定想退出对话框吗？");
		if(close){
			return super.canHandleShellCloseEvent();
		} else {
			return false;
		}*/
	}

	@Override
	protected Control createContents(Composite parent) {
		// TODO Auto-generated method stub
		 Control c = super.createContents(parent);
		 getButton(IDialogConstants.FINISH_ID).setText("完成"); //完成按钮
	     getButton(IDialogConstants.CANCEL_ID).setText("取消"); //取消按钮
	     try {
	    	 getButton(IDialogConstants.BACK_ID).setText("上一步"); //上一步
	    	 getButton(IDialogConstants.BACK_ID).setEnabled(false);
			 getButton(IDialogConstants.NEXT_ID).setText("下一步"); //下一步
		} catch (Exception e) {}
	     return c;
	}
}
