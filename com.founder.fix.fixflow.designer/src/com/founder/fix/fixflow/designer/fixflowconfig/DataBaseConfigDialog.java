package com.founder.fix.fixflow.designer.fixflowconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.internal.core.BinaryType;
import org.eclipse.jdt.internal.core.SourceType;
import org.eclipse.jdt.internal.ui.JavaUIMessages;
import org.eclipse.jdt.internal.ui.dialogs.OpenTypeSelectionDialog;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage.Literals;
import com.founder.fix.bpmn2extensions.coreconfig.DBType;
import com.founder.fix.bpmn2extensions.coreconfig.DataBase;

import org.eclipse.ui.PlatformUI;

public class DataBaseConfigDialog extends TitleAreaDialog {
	private DataBindingContext m_bindingContext;
	private Text idtext;
	private Text nametext;
	private Text urltext;
	private Text usertext;
	private Text pwdtext;
	private Text maxacttext;
	private Text mxidletext;
	private Text mxwaittext;
	private Text impltext;

	private DataBase dataBase;
	private Combo drivercombo;
	private DataBase oldDataBase;
	private Combo combo;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public DataBaseConfigDialog(Shell parentShell) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(SWT.RESIZE | SWT.TITLE | SWT.PRIMARY_MODAL);
	}

	/**
	 * 构造方法
	 * 
	 * @param parentShell
	 * @wbp.parser.constructor
	 */
	public DataBaseConfigDialog(Shell parentShell, DataBase dataBase) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(SWT.RESIZE | SWT.TITLE | SWT.PRIMARY_MODAL);
		this.dataBase = EcoreUtil.copy(dataBase);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("数据库配置");
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
		GridLayout gl_composite = new GridLayout(3, false);
		gl_composite.verticalSpacing = 10;
		gl_composite.marginRight = 25;
		gl_composite.marginLeft = 25;
		gl_composite.marginBottom = 15;
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 15;
		composite.setLayout(gl_composite);

		Label IDLabel = new Label(composite, SWT.NONE);
		IDLabel.setText("编号");

		idtext = new Text(composite, SWT.BORDER);
		idtext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label nameLabel = new Label(composite, SWT.NONE);
		nameLabel.setText("名称");

		nametext = new Text(composite, SWT.BORDER);
		nametext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("数据库类型");
		
		combo = new Combo(composite, SWT.READ_ONLY);
		combo.setItems(new String[] {"other", "oracle", "sqlserver", "db2", "mysql"});
		combo.select(0);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label driverLabel = new Label(composite, SWT.NONE);
		driverLabel.setText("驱动名称");

		drivercombo = new Combo(composite, SWT.READ_ONLY);
		drivercombo.setItems(new String[] { "oracle.jdbc.driver.OracleDriver", "net.sourceforge.jtds.jdbc.Driver", "com.mysql.jdbc.Driver", "com.ibm.db2.jdbc.app.DB2Driver" });
		drivercombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label urlLabel = new Label(composite, SWT.NONE);
		urlLabel.setText("地址");

		urltext = new Text(composite, SWT.BORDER);
		urltext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label userLabel = new Label(composite, SWT.NONE);
		userLabel.setText("用户名");

		usertext = new Text(composite, SWT.BORDER);
		usertext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label pwdLabel = new Label(composite, SWT.NONE);
		pwdLabel.setText("密码");

		pwdtext = new Text(composite, SWT.BORDER);
		pwdtext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label mxactLabel = new Label(composite, SWT.NONE);
		mxactLabel.setText("最大活动连接数");

		maxacttext = new Text(composite, SWT.BORDER);
		maxacttext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label mxidleLabel = new Label(composite, SWT.NONE);
		mxidleLabel.setText("最大闲置连接数");

		mxidletext = new Text(composite, SWT.BORDER);
		mxidletext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label mxwaitLabel = new Label(composite, SWT.NONE);
		mxwaitLabel.setText("最大等待时间");

		mxwaittext = new Text(composite, SWT.BORDER);
		mxwaittext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label implLabel = new Label(composite, SWT.NONE);
		implLabel.setText("分页实现类");

		impltext = new Text(composite, SWT.BORDER);
		impltext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button selectButton = new Button(composite, SWT.NONE);
		selectButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		selectButton.setText("选择");
		
		selectButton.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				// 打开对话框
				StringBuffer sb = new StringBuffer();
				OpenTypeSelectionDialog dialog = new OpenTypeSelectionDialog(getShell(), true, PlatformUI.getWorkbench().getProgressService(), null, IJavaSearchConstants.TYPE);
				dialog.setTitle(JavaUIMessages.OpenTypeAction_dialogTitle);
				dialog.setMessage(JavaUIMessages.OpenTypeAction_dialogMessage);
				int result = dialog.open();

				// 打开文件
				Object[] files = dialog.getResult();
				if (files != null && files.length > 0) {
					for (int i = 0; i < files.length; i++) {
						IType it = (IType) files[i];
						sb.append(it.getFullyQualifiedName());
					}
				}

				if (result == IDialogConstants.OK_ID && sb.length() > 0) {
					impltext.setText(sb.toString());
				}
			}
		});
		
				Button btnNewButton = new Button(composite, SWT.NONE);
				btnNewButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 3, 1));
				btnNewButton.setText("测试连接");
				
				btnNewButton.addListener(SWT.Selection, new Listener() {

					@Override
					public void handleEvent(Event event) {
						// TODO Auto-generated method stub
						String dbdriver = drivercombo.getText();
						String dburl = urltext.getText();
						String username = usertext.getText();
						String password = pwdtext.getText();
						Connection connection = null;
						try {
							connection = createConnection(dbdriver, dburl, username, password);
							if (connection != null) {
								MessageDialog.openInformation(null, "提示", "连接成功！");
							} else {
								MessageDialog.openWarning(null, "提示", "连接失败，请检查配置！");
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							MessageDialog.openWarning(null, "提示", "连接失败，请检查配置！" + '\n' + "错误信息：" + '\n' + e.getMessage());
						} finally {
							try {
								if (connection != null)
									connection.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				});
		
		setMessage("配置数据库", IMessageProvider.INFORMATION);

		
		init();
		return area;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		button.setText("确定");
		Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
		button_1.setText("取消");
		m_bindingContext = initDataBindings();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(480, 550);
	}

	public DataBase getDataBase() {
		return dataBase;
	}

	private Connection createConnection(String dbdriver, String dburl, String username, String pwd) throws Exception {
		Connection connection = null;
		Class.forName(dbdriver);
		String url = dburl;
		String user = username;
		String password = pwd;
		connection = DriverManager.getConnection(url, user, password);
		return connection;
	}
	
	private DBType getDbTypeSelection(String dbtype) {
		if(dbtype.equals("oracle")) {
			return DBType.get(1);
		}
		if(dbtype.equals("sqlserver")) {
			return DBType.get(2);
		}
		if(dbtype.equals("db2")) {
			return DBType.get(3);
		}
		if(dbtype.equals("mysql")) {
			return DBType.get(4);
		}
		if(dbtype.equals("other")) {
			return DBType.get(0);
		}
		return null;
	}
	
	private void init() {
		if(dataBase.getDbtype() != null) {
			combo.select(dataBase.getDbtype().getValue());
		}
	}

	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		dataBase.setDbtype(DBType.get(combo.getText()==null ? "other" : combo.getText()));
		super.okPressed();
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue idtextObserveTextObserveWidget = SWTObservables.observeText(idtext, SWT.Modify);
		IObservableValue dataBaseIdObserveValue = EMFObservables.observeValue(dataBase, Literals.DATA_BASE__ID);
		bindingContext.bindValue(idtextObserveTextObserveWidget, dataBaseIdObserveValue, null, null);
		//
		IObservableValue nametextObserveTextObserveWidget = SWTObservables.observeText(nametext, SWT.Modify);
		IObservableValue dataBaseNameObserveValue = EMFObservables.observeValue(dataBase, Literals.DATA_BASE__NAME);
		bindingContext.bindValue(nametextObserveTextObserveWidget, dataBaseNameObserveValue, null, null);
		//
		IObservableValue drivercomboObserveTextObserveWidget = SWTObservables.observeText(drivercombo);
		IObservableValue dataBaseDriverClassNameObserveValue = EMFObservables.observeValue(dataBase, Literals.DATA_BASE__DRIVER_CLASS_NAME);
		bindingContext.bindValue(drivercomboObserveTextObserveWidget, dataBaseDriverClassNameObserveValue, null, null);
		//
		IObservableValue urltextObserveTextObserveWidget = SWTObservables.observeText(urltext, SWT.Modify);
		IObservableValue dataBaseUrlObserveValue = EMFObservables.observeValue(dataBase, Literals.DATA_BASE__URL);
		bindingContext.bindValue(urltextObserveTextObserveWidget, dataBaseUrlObserveValue, null, null);
		//
		IObservableValue usertextObserveTextObserveWidget = SWTObservables.observeText(usertext, SWT.Modify);
		IObservableValue dataBaseUsernameObserveValue = EMFObservables.observeValue(dataBase, Literals.DATA_BASE__USERNAME);
		bindingContext.bindValue(usertextObserveTextObserveWidget, dataBaseUsernameObserveValue, null, null);
		//
		IObservableValue pwdtextObserveTextObserveWidget = SWTObservables.observeText(pwdtext, SWT.Modify);
		IObservableValue dataBasePasswordObserveValue = EMFObservables.observeValue(dataBase, Literals.DATA_BASE__PASSWORD);
		bindingContext.bindValue(pwdtextObserveTextObserveWidget, dataBasePasswordObserveValue, null, null);
		//
		IObservableValue maxacttextObserveTextObserveWidget = SWTObservables.observeText(maxacttext, SWT.Modify);
		IObservableValue dataBaseMaxActiveObserveValue = EMFObservables.observeValue(dataBase, Literals.DATA_BASE__MAX_ACTIVE);
		bindingContext.bindValue(maxacttextObserveTextObserveWidget, dataBaseMaxActiveObserveValue, null, null);
		//
		IObservableValue mxidletextObserveTextObserveWidget = SWTObservables.observeText(mxidletext, SWT.Modify);
		IObservableValue dataBaseMaxIdleObserveValue = EMFObservables.observeValue(dataBase, Literals.DATA_BASE__MAX_IDLE);
		bindingContext.bindValue(mxidletextObserveTextObserveWidget, dataBaseMaxIdleObserveValue, null, null);
		//
		IObservableValue mxwaittextObserveTextObserveWidget = SWTObservables.observeText(mxwaittext, SWT.Modify);
		IObservableValue dataBaseMaxWaitObserveValue = EMFObservables.observeValue(dataBase, Literals.DATA_BASE__MAX_WAIT);
		bindingContext.bindValue(mxwaittextObserveTextObserveWidget, dataBaseMaxWaitObserveValue, null, null);
		//
		IObservableValue impltextObserveTextObserveWidget = SWTObservables.observeText(impltext, SWT.Modify);
		IObservableValue dataBasePaginationImplObserveValue = EMFObservables.observeValue(dataBase, Literals.DATA_BASE__PAGINATION_IMPL);
		bindingContext.bindValue(impltextObserveTextObserveWidget, dataBasePaginationImplObserveValue, null, null);
		//
		return bindingContext;
	}
}
