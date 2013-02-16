package com.founder.fix.fixflow.designer.preferences;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

/**
 * @author wangyu
 *
 */
public class PasswordFieldEditor extends StringFieldEditor {

    public PasswordFieldEditor(String userPassword, String userPasswordLabel,
			Composite fieldEditorParent) {
		super(userPassword, userPasswordLabel, fieldEditorParent);
	}

	/**
     * 重写getcontrol方法
     * @param parent the parent
     * @return the text control
     */
    public Text getTextControl(final Composite parent) {
    	/*Create a composite to contain the TextBox and the checkbox*/
    	final Composite textAndCheckboxcomposite = new Composite(parent, SWT.NONE);
    	textAndCheckboxcomposite.setLayout(GridLayoutFactory.fillDefaults().create());
    	textAndCheckboxcomposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
    	
    	/*Create Text and Checkbox*/
    	final Text textControl = super.getTextControl(textAndCheckboxcomposite);
    	textControl.setEchoChar('*');
    	final Button checkboxToShowPassword = new Button(textAndCheckboxcomposite, SWT.CHECK);
		checkboxToShowPassword.setText("显示密码");
		
		/*Add a listener to hide/show password when Checkbox is checked/unchecked*/
		checkboxToShowPassword.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				if(checkboxToShowPassword.getSelection()){
					textControl.setEchoChar('\0');//show password
				} else {
					textControl.setEchoChar('*');//hide password, replace all characters by *
				}
			}
		});
		
		final Button testbutton = new Button(parent, SWT.NONE);
		testbutton.setText("测试连接");
		
		testbutton.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				org.eclipse.swt.widgets.Control[] ctls = parent.getChildren();
				String dbdriver = ((Text)ctls[1]).getText();
				String dburl = ((Text)ctls[3]).getText();
				String username = ((Text)ctls[5]).getText();
				org.eclipse.swt.widgets.Control[] passwrodctl = ((Composite)ctls[7]).getChildren();
				String password = ((Text)passwrodctl[0]).getText();
				Connection connection = null;
				try {
					connection = createConnection(dbdriver, dburl, username, password);
					if(connection != null){
						MessageDialog.openInformation(null, "提示", "连接成功！");
					}else{
						MessageDialog.openWarning(null, "提示", "连接失败，请检查配置！");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					MessageDialog.openWarning(null, "提示", "连接失败，请检查配置！" + '\n'+ "错误信息：" + '\n' + e.getMessage());
				}finally{
					try {
						if(connection != null)
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		
		final Button sqlbutton = new Button(composite, SWT.NONE);
		sqlbutton.setText("SQL SERVER");
		GridData gd = new GridData();
		gd.widthHint = 90;
		sqlbutton.setLayoutData(gd);
		sqlbutton.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				org.eclipse.swt.widgets.Control[] ctls = parent.getChildren();
				((Text)ctls[1]).setText("net.sourceforge.jtds.jdbc.Driver");
				((Text)ctls[3]).setText("jdbc:jtds:sqlserver://127.0.0.1:1433/database");
			}
		});
		
		final Button oraclebutton = new Button(composite, SWT.NONE);
		oraclebutton.setText("ORACLE");
		oraclebutton.setLayoutData(gd);
		oraclebutton.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				org.eclipse.swt.widgets.Control[] ctls = parent.getChildren();
				((Text)ctls[1]).setText("oracle.jdbc.driver.OracleDriver");
				((Text)ctls[3]).setText("jdbc:oracle:thin:@127.0.0.1:1521:orcl");
			}
		});
		
		final Button mysqlbutton = new Button(composite, SWT.NONE);
		mysqlbutton.setText("MYSQL");
		mysqlbutton.setLayoutData(gd);
		mysqlbutton.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				org.eclipse.swt.widgets.Control[] ctls = parent.getChildren();
				((Text)ctls[1]).setText("com.mysql.jdbc.Driver");
				((Text)ctls[3]).setText("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useUnicode=true");
			}
		});
		
		return textControl;
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
}
