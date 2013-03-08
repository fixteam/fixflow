package com.founder.fix.fixflow.designer.fixflowconfig;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage.Literals;
import com.founder.fix.bpmn2extensions.coreconfig.MailInfo;
import com.founder.fix.fixflow.designer.util.MailUtil;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MailConfigDialog extends TitleAreaDialog {
	private DataBindingContext m_bindingContext;
	private MailInfo mailInfo;
	private Text nametext;
	private Text addresstext;
	private Text hosttext;
	private Text porttext;
	private Text usertext;
	private Text pwdtext;
	private Button button;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public MailConfigDialog(Shell parentShell) {
		super(parentShell);
	}
	
	/**
	 * 构造方法
	 * @param parentShell
	 * @wbp.parser.constructor
	 */
	public MailConfigDialog(Shell parentShell, MailInfo mailInfo) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(SWT.RESIZE | SWT.TITLE | SWT.PRIMARY_MODAL);
		this.mailInfo = EcoreUtil.copy(mailInfo);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("邮件配置");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		GridLayout gl_container = new GridLayout(1, false);
		gl_container.verticalSpacing = 0;
		gl_container.marginWidth = 0;
		gl_container.marginHeight = 0;
		gl_container.horizontalSpacing = 0;
		container.setLayout(gl_container);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite.setBounds(0, 0, 64, 64);
		GridLayout gl_composite = new GridLayout(4, false);
		gl_composite.verticalSpacing = 10;
		gl_composite.marginRight = 25;
		gl_composite.marginLeft = 25;
		gl_composite.marginBottom = 15;
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 15;
		composite.setLayout(gl_composite);
		
		Label nameLabel = new Label(composite, SWT.NONE);
		nameLabel.setText("邮件名称");

		nametext = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		nametext.setEditable(true);
		nametext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		nametext.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				verify();
			}
		});

		Label addressLabel = new Label(composite, SWT.NONE);
		addressLabel.setText("邮件地址");

		addresstext = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		addresstext.setEditable(true);
		addresstext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		addresstext.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				verify();
			}
		});

		Label hostLabel = new Label(composite, SWT.NONE);
		hostLabel.setText("发送服务器地址");
		
		hosttext = new Text(composite, SWT.BORDER);
		hosttext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		hosttext.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				verify();
			}
		});
		
		Label portLabel = new Label(composite, SWT.NONE);
		portLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		portLabel.setText("端口");
		
		porttext = new Text(composite, SWT.BORDER);
		GridData gd_porttext = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_porttext.widthHint = 30;
		porttext.setLayoutData(gd_porttext);
		porttext.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				verify();
			}
		});
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setText("用户名");
		
		usertext = new Text(composite, SWT.BORDER);
		usertext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		usertext.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				verify();
			}
		});
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setText("密码");
		
		pwdtext = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		pwdtext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		pwdtext.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				verify();
			}
		});
		
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setText("测试连接");
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if(!MessageDialog.openConfirm(getShell(), "提示", "请确认邮件地址是否正确，是否继续？")) {
						return;
					}
					if(MailUtil.verifyMailSendServer(hosttext.getText().trim(),
							Integer.parseInt(porttext.getText().trim()),
							usertext.getText().trim(),
							pwdtext.getText().trim())) {
						MessageDialog.openInformation(getShell(), "信息", "连接成功");
					} else {
						MessageDialog.openInformation(getShell(), "信息", "连接失败");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					MessageDialog.openError(getShell(), "错误", e.toString());
				}
			}
		});

		return area;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		button.setText("确定");
		Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
		button_1.setText("取消");
		m_bindingContext = initDataBindings();
		verify();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(500, 378);
	}

	public MailInfo getMailInfo() {
		return mailInfo;
	}

	public void setMailInfo(MailInfo mailInfo) {
		this.mailInfo = mailInfo;
	}
	
	/**
	 * 验证text是否为空
	 * @return
	 */
	public void verify() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		if(nametext.getText() == null || nametext.getText().equals(""))
			sb.append("邮件名称为空");
		
		if(addresstext.getText() == null || addresstext.getText().equals("")){
			if(sb.length()>0)
				sb.append(",");
			sb.append("邮件地址为空");
		}
		if(hosttext.getText() == null || hosttext.getText().equals("")){
			if(sb.length()>0)
				sb.append(",");
			sb.append("发送服务器地址为空");
		}
		if(porttext.getText() != null && !(porttext.getText().equals(""))) {
			Pattern pattern = Pattern.compile("^[0-9]+$");
			Matcher matcher = pattern.matcher(porttext.getText());
			if(!matcher.matches()){
				if(sb.length()>0)
					sb.append(",");
				sb.append("发送服务器端口为空或者输入值不符合要求");
			}
		}
		if(usertext.getText() == null || usertext.getText().equals("")){
			if(sb.length()>0)
				sb.append(",");
			sb.append("用户名为空");
		}
		if(pwdtext.getText() == null || pwdtext.getText().equals("")){
			if(sb.length()>0)
				sb.append(",");
			sb.append("密码为空");
		}
		if(sb.length()>0){
			MailConfigDialog.this.setErrorMessage(sb.toString());
			button.setEnabled(false);
		}else{
			MailConfigDialog.this.setErrorMessage(null);
			button.setEnabled(true);
		}
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue idtextObserveTextObserveWidget = SWTObservables.observeText(nametext, SWT.Modify);
		IObservableValue mailInfoMailNameObserveValue = EMFObservables.observeValue(mailInfo, Literals.MAIL_INFO__MAIL_NAME);
		bindingContext.bindValue(idtextObserveTextObserveWidget, mailInfoMailNameObserveValue, null, null);
		//
		IObservableValue nametextObserveTextObserveWidget = SWTObservables.observeText(addresstext, SWT.Modify);
		IObservableValue mailInfoMailAddressObserveValue = EMFObservables.observeValue(mailInfo, Literals.MAIL_INFO__MAIL_ADDRESS);
		bindingContext.bindValue(nametextObserveTextObserveWidget, mailInfoMailAddressObserveValue, null, null);
		//
		IObservableValue textObserveTextObserveWidget = SWTObservables.observeText(hosttext, SWT.Modify);
		IObservableValue mailInfoSmtpHostObserveValue = EMFObservables.observeValue(mailInfo, Literals.MAIL_INFO__SMTP_HOST);
		bindingContext.bindValue(textObserveTextObserveWidget, mailInfoSmtpHostObserveValue, null, null);
		//
		IObservableValue text_2ObserveTextObserveWidget = SWTObservables.observeText(usertext, SWT.Modify);
		IObservableValue mailInfoUserNameObserveValue = EMFObservables.observeValue(mailInfo, Literals.MAIL_INFO__USER_NAME);
		bindingContext.bindValue(text_2ObserveTextObserveWidget, mailInfoUserNameObserveValue, null, null);
		//
		IObservableValue text_3ObserveTextObserveWidget = SWTObservables.observeText(pwdtext, SWT.Modify);
		IObservableValue mailInfoPassWordObserveValue = EMFObservables.observeValue(mailInfo, Literals.MAIL_INFO__PASS_WORD);
		bindingContext.bindValue(text_3ObserveTextObserveWidget, mailInfoPassWordObserveValue, null, null);
		//
		IObservableValue porttextObserveTextObserveWidget = SWTObservables.observeText(porttext, SWT.Modify);
		IObservableValue mailInfoSmtpPortObserveValue = EMFObservables.observeValue(mailInfo, Literals.MAIL_INFO__SMTP_PORT);
		bindingContext.bindValue(porttextObserveTextObserveWidget, mailInfoSmtpPortObserveValue, null, null);
		//
		return bindingContext;
	}
}
