/**
 * 
 */
package com.founder.fix.fixflow.designer.modeler.ui.property.connectors.dialog.modify;

import org.eclipse.bpmn2.UserTask;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance;
import com.founder.fix.fixflow.designer.modeler.ui.property.SectionBpmnElement;
import com.founder.fix.fixflow.designer.modeler.ui.property.connectors.dialog.LifeCycleWidget;

/**
 * @author ququsxc
 *
 */
public class ModifyRenameConnectorWizardPage extends WizardPage {

	private Label namelabel;
	
	private Text connectNameText;
	
	private Text connectDescriptionText;
	
//	private Combo connectEventCombo;
	
	private Combo connectExceptionCombo;
	
	private Text connectNameErrorText;
	
	private ConnectorInstance connectorInstance;
	
	private LifeCycleWidget lifeCycle;

	/**
	 * @param pageName
	 */
	public ModifyRenameConnectorWizardPage(String pageName) {
		super(pageName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param pageName
	 * @param title
	 * @param titleImage
	 */
	public ModifyRenameConnectorWizardPage(String pageName, String title,
			ImageDescriptor titleImage, ConnectorInstance connectorInstance) {
		super(pageName, title, titleImage);
		this.connectorInstance = connectorInstance;
		
		//title信息
		setTitle(title);
		
		//消息信息
		setMessage("指定连接器的参数", INFORMATION);
		
		//设置图片
		setImageDescriptor(titleImage);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		//创建一个底层的Composite，并使用GridLayout布局
		Composite newComposite = new Composite(parent, SWT.NULL);
		newComposite.setLayout(new FillLayout());
		
		//创建布局
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2; //grid分为2列
		
		//创建一个组
		Group proGroup = new Group(newComposite, SWT.NONE);
		proGroup.setLayout(gridLayout);
		proGroup.setText("连接器详细设置");
		
		//创建label
		Label label = new Label(proGroup, SWT.NONE);
		label.setText("名称");
		
		//创建文本框
		connectNameText = new Text(proGroup, SWT.BORDER);
		connectNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL)); //布局
		connectNameText.setText(connectorInstance.getConnectorInstanceName());
		connectNameText.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent e) {
				if(connectNameText.getText().equals("")) {
					namelabel.setText("(       )");
				} else {
					namelabel.setText("( " + connectNameText.getText() + " )");
				}
				
				//验证输入内容
				setPageComplete(isPageComplete());
			}
		});
		
		new Label(proGroup, SWT.NONE);
		
		namelabel = new Label(proGroup, SWT.NONE);
		namelabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL)); //布局
		namelabel.setText("(    " + connectorInstance.getConnectorInstanceName() + "    )");
		
		label = new Label(proGroup, SWT.NONE);
		label.setText("描述");
		
		//创建文本框
		connectDescriptionText = new Text(proGroup, SWT.BORDER | SWT.WRAP);
		connectDescriptionText.setLayoutData(new GridData(400, 60)); //布局
		connectDescriptionText.setText(connectorInstance.getDocumentation()
				.getValue() == null ? "" : connectorInstance.getDocumentation()
				.getValue());
		connectDescriptionText.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent e) {
				
			}
		});
		
		label = new Label(proGroup, SWT.NONE);
		label.setText("选择事件");
		
		/*//创建文本框
		connectEventCombo = new Combo(proGroup, SWT.BORDER | SWT.READ_ONLY);
		connectEventCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL)); //布局
		connectEventCombo.setItems(new String[] {"enter", "cancel", "abort", "finish"});
		connectEventCombo.setText(connectorInstance.getEventType() == null ? "" : connectorInstance.getEventType());
		connectEventCombo.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent e) {
				
			}
		});*/
		
		if(SectionBpmnElement.sectionElement instanceof org.eclipse.bpmn2.Process){
			lifeCycle = new LifeCycleWidget(proGroup, connectorInstance.getEventType(), LifeCycleWidget.PROCESS_LIFE_CYCLE,null);
		}else if(SectionBpmnElement.sectionElement instanceof UserTask){
			lifeCycle = new LifeCycleWidget(proGroup, connectorInstance.getEventType(), LifeCycleWidget.USERTASK_LIFE_CYCLE,null);
		}else{
			lifeCycle = new LifeCycleWidget(proGroup, connectorInstance.getEventType(), LifeCycleWidget.OTHER_LIFE_CYCLE,null);
		}
		
		GridData gd1 = new GridData(lifeCycle.getWidth(), lifeCycle.getHeight());
		gd1.horizontalIndent = 0 ;
		lifeCycle.setLayoutData(gd1);

		lifeCycle.addSelectionListener(new SelectionAdapter() {


			/*public void widgetSelected(SelectionEvent e) {
				lifeCycle.setEvent(connectorInstance.getEventType() == null ? "" : connectorInstance.getEventType());
			}*/
		});		
		
		label = new Label(proGroup, SWT.NONE);
		label.setText("如果连接器失效");
		
		//创建文本框
		connectExceptionCombo = new Combo(proGroup, SWT.BORDER | SWT.READ_ONLY);
		connectExceptionCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL)); //布局
		connectExceptionCombo.setItems(new String[] {"抛出异常", "忽略错误并继续该流程", "抛出错误事件"});
		connectExceptionCombo.setText(connectorInstance.getErrorHandling() == null ? "" : connectorInstance.getErrorHandling());
		connectExceptionCombo.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent e) {
				if(connectExceptionCombo.getSelectionIndex() == 2) {
					connectNameErrorText.setEnabled(true);
				} else {
					connectNameErrorText.setText("");
					connectNameErrorText.setEnabled(false);
				}
				setPageComplete(isPageComplete());
			}
		});
		
		label = new Label(proGroup, SWT.NONE);
		label.setText("命名错误");
		
		//创建文本框
		connectNameErrorText = new Text(proGroup, SWT.BORDER);
		connectNameErrorText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL)); //布局
		if(connectExceptionCombo.getSelectionIndex() == 2) {
			connectNameErrorText.setEnabled(true);
		} else {
			connectNameErrorText.setEnabled(false);
		}
		connectNameErrorText.setText(connectorInstance.getErrorCode() == null ? "" : connectorInstance.getErrorCode());
		connectNameErrorText.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent e) {
				setPageComplete(isPageComplete());
			}
		});

		//初始化的时候先使‘完成’按钮不可用
//		setPageComplete(false); 
		
		//必须要的，将新的Composite放入
		setControl(newComposite);
	}

	/**
	 * 验证输入内容
	 */
	/*private void dataChange() {
		if(connectNameText.getText().trim().equals("")) {
			setErrorMessage("名称输入不能空"); //提示错误信息
			setPageComplete(false); //使‘完成’和‘下一步’两个按钮不可用
			return;
		}
		if(connectExceptionCombo.getSelectionIndex() == 2
				&& connectNameErrorText.getText().equals("")) {
			setErrorMessage("'命名错误'不能空"); //提示错误信息
			setPageComplete(false); //使‘完成’和‘下一步’两个按钮不可用
			return;
		}
		
		setErrorMessage(null); //清空错误信息
		setPageComplete(true); //使‘完成’和‘下一步’两个按钮可用
	}*/
	
	/**
	 * 重写验证
	 */
	@Override
	public boolean isPageComplete() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		if(null == connectNameText.getText() || connectNameText.getText().trim().equals("")) {
			sb.append("名称为空");
		}
		if(connectExceptionCombo.getSelectionIndex() == 2
				&& (null == connectNameErrorText.getText() || connectNameErrorText.getText().equals(""))) {
			if(sb.length()>0)
				sb.append(",");
			sb.append("命名错误为空");
		}
		
		if(sb.length()>0){
			ModifyRenameConnectorWizardPage.this.setErrorMessage(sb.toString());
			return false;
		}else{
			ModifyRenameConnectorWizardPage.this.setErrorMessage(null);
			return true;
		}
	}
	
	@Override
	public IWizardPage getPreviousPage() {
		// TODO Auto-generated method stub
		return null;
	}

	public Text getConnectNameText() {
		return connectNameText;
	}

	public void setConnectNameText(Text connectNameText) {
		this.connectNameText = connectNameText;
	}

	public Text getConnectDescriptionText() {
		return connectDescriptionText;
	}

	public void setConnectDescriptionText(Text connectDescriptionText) {
		this.connectDescriptionText = connectDescriptionText;
	}

	/*public Combo getConnectEventCombo() {
		return connectEventCombo;
	}

	public void setConnectEventCombo(Combo connectEventCombo) {
		this.connectEventCombo = connectEventCombo;
	}*/

	public Combo getConnectExceptionCombo() {
		return connectExceptionCombo;
	}

	public void setConnectExceptionCombo(Combo connectExceptionCombo) {
		this.connectExceptionCombo = connectExceptionCombo;
	}

	public Text getConnectNameErrorText() {
		return connectNameErrorText;
	}

	public void setConnectNameErrorText(Text connectNameErrorText) {
		this.connectNameErrorText = connectNameErrorText;
	}

	public LifeCycleWidget getLifeCycle() {
		return lifeCycle;
	}

	public void setLifeCycle(LifeCycleWidget lifeCycle) {
		this.lifeCycle = lifeCycle;
	}

}
