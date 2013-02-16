package com.founder.fix.fixflow.designer.fixflowconfig;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigFactory;
import com.founder.fix.bpmn2extensions.coreconfig.GroupInfo;
import com.founder.fix.bpmn2extensions.coreconfig.UserInfo;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.emf.databinding.EMFObservables;
import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage.Literals;

public class UserInfoConfigDialog extends TitleAreaDialog {
	private DataBindingContext m_bindingContext;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private GroupInfo groupInfo;
	private UserInfo userInfo;

	/**
	 * Create the dialog.
	 * @param parentShell
	 * @wbp.parser.constructor
	 */
	public UserInfoConfigDialog(Shell parentShell) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(SWT.RESIZE | SWT.TITLE | SWT.PRIMARY_MODAL);
	}
	
	/**
	 * 构造函数
	 * @param parentShell
	 * @param userInfo
	 */
	public UserInfoConfigDialog(Shell parentShell,GroupInfo groupInfo) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(SWT.RESIZE | SWT.TITLE | SWT.PRIMARY_MODAL);
		if(groupInfo.getUserInfo() == null) {
			UserInfo userInfo = CoreconfigFactory.eINSTANCE.createUserInfo();
			groupInfo.setUserInfo(userInfo);
			this.groupInfo = groupInfo;
			this.userInfo = groupInfo.getUserInfo();
		}
		else{
			this.groupInfo = groupInfo;
			this.userInfo = groupInfo.getUserInfo();
		}
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("用户信息配置");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.verticalSpacing = 15;
		gl_composite.marginBottom = 15;
		gl_composite.marginRight = 25;
		gl_composite.marginLeft = 25;
		gl_composite.marginHeight = 15;
		composite.setLayout(gl_composite);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setText("用户编号字段");
		
		text = new Text(composite, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setText("用户名称字段");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setText("组编号字段");
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_3 = new Label(composite, SWT.NONE);
		lblNewLabel_3.setText("查询语句");
		
		text_3 = new Text(composite, SWT.BORDER | SWT.WRAP);
		GridData gd_text_3 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_3.heightHint = 141;
		text_3.setLayoutData(gd_text_3);

		return area;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
		m_bindingContext = initDataBindings();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(470, 450);
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue textObserveTextObserveWidget = SWTObservables.observeText(text, SWT.Modify);
		IObservableValue userInfoUserIdFieldObserveValue = EMFObservables.observeValue(userInfo, Literals.USER_INFO__USER_ID_FIELD);
		bindingContext.bindValue(textObserveTextObserveWidget, userInfoUserIdFieldObserveValue, null, null);
		//
		IObservableValue text_1ObserveTextObserveWidget = SWTObservables.observeText(text_1, SWT.Modify);
		IObservableValue userInfoUserNameFieldObserveValue = EMFObservables.observeValue(userInfo, Literals.USER_INFO__USER_NAME_FIELD);
		bindingContext.bindValue(text_1ObserveTextObserveWidget, userInfoUserNameFieldObserveValue, null, null);
		//
		IObservableValue text_2ObserveTextObserveWidget = SWTObservables.observeText(text_2, SWT.Modify);
		IObservableValue userInfoGroupIdFieldObserveValue = EMFObservables.observeValue(userInfo, Literals.USER_INFO__GROUP_ID_FIELD);
		bindingContext.bindValue(text_2ObserveTextObserveWidget, userInfoGroupIdFieldObserveValue, null, null);
		//
		IObservableValue text_3ObserveTextObserveWidget = SWTObservables.observeText(text_3, SWT.Modify);
		IObservableValue userInfoSqlTextObserveValue = EMFObservables.observeValue(userInfo, Literals.USER_INFO__SQL_TEXT);
		bindingContext.bindValue(text_3ObserveTextObserveWidget, userInfoSqlTextObserveValue, null, null);
		//
		return bindingContext;
	}

	public GroupInfo getGroupInfo() {
		return groupInfo;
	}

	public void setGroupInfo(GroupInfo groupInfo) {
		this.groupInfo = groupInfo;
	}
}
