/**
 * 
 */
package com.founder.fix.fixflow.designer.modeler.ui.property.connectors.dialog.modify;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.founder.fix.bpmn2extensions.connector.InputParameter;
import com.founder.fix.bpmn2extensions.connector.Page;
import com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance;
import com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterInputs;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionCombo;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionComboViewer;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionTo;

/**
 * @author wangzhiwei
 *
 */
public class ModifyCommonConnectorWizardPage extends WizardPage {
	
	public static String[] types = new String[] {"text", "textarea", "password", "checkbox", "radio", "select"};
	
	private Page page;
	
	private List<Control> controls;
	
	private List<Control> isRequiredControl;
	
	private boolean commonIsRequired;
	
	private ConnectorInstance connectorInstance;
	
	private ExpressionComboViewer expressionComboViewer;

	/**
	 * @param pageName
	 */
	public ModifyCommonConnectorWizardPage(String pageName) {
		super(pageName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param pageName
	 * @param title
	 * @param titleImage
	 */
	public ModifyCommonConnectorWizardPage(String pageName, String title, String desc,
			ImageDescriptor titleImage, Page page, ConnectorInstance connectorInstance) {
		super(pageName, title, titleImage);
		this.page = page;
		this.connectorInstance = connectorInstance;
		
		//title信息
		setTitle(title);
		
		//消息信息
		setMessage(desc, INFORMATION);
		
		//设置图片
		setImageDescriptor(titleImage);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		controls = new ArrayList<Control>();
		isRequiredControl = new ArrayList<Control>();
		
		//创建一个底层的Composite，并使用GridLayout布局
		Composite newComposite = new Composite(parent, SWT.NULL);
		newComposite.setLayout(new FillLayout());
		
		//创建布局
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3; //grid分为2列
		
		//创建一个组
		Group proGroup = new Group(newComposite, SWT.NONE);
		proGroup.setLayout(gridLayout);
		proGroup.setText("详细设置");
		
		//根据page动态画出控件
		EList<InputParameter> widgets = page.getInputParameter();
		if(widgets != null && widgets.size() > 0) {
			for (Iterator iterator = widgets.iterator(); iterator.hasNext();) {
				InputParameter widget = (InputParameter) iterator.next();
				String id = widget.getId();
				String name = widget.getName();
				String type = widget.getControlType();
				final boolean isRequired = widget.isIsRequired();
				if(isRequired) {
					commonIsRequired = isRequired;
				}
				
				String expression = "";
				String expname = "";
				//循环遍历inputs，找到表达式值
				EList<ConnectorParameterInputs> inputs = connectorInstance.getConnectorParameterInputs();
				if(inputs != null && inputs.size() > 0) {
					for (Iterator iterator2 = inputs.iterator(); iterator2
							.hasNext();) {
						ConnectorParameterInputs connectorParameterInputs = (ConnectorParameterInputs) iterator2
								.next();
						if(connectorParameterInputs.getId().equals(id)) {
							expression = connectorParameterInputs.getExpression().getValue();
							expname = connectorParameterInputs.getExpression().getName();
						}
					}
				}
				
				//创建label
				Label label = new Label(proGroup, SWT.NONE);
				label.setText(name == null ? "" : name);
				label.setData(widget);
				if(isRequired){
					Label label1 = new Label(proGroup, SWT.NONE);
					label1.setText("*");
				}else{
					new Label(proGroup, SWT.NONE);
				}
				controls.add(label);
				
				if(type.equals(types[0])) {
					//创建文本框
					expressionComboViewer = new ExpressionComboViewer(proGroup);

					ExpressionCombo expressionCombo = expressionComboViewer.getExpressionCombo();
					
					ExpressionTo expressionTo = new ExpressionTo();
					expressionTo.setName(expname == null ? "" : expname);
					expressionTo.setExpressionText(expression == null ? "" : expression);
					
					expressionCombo.setExpressionTo(expressionTo);
					
					expressionCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
					
					if(isRequired) {
						isRequiredControl.add(expressionCombo);
					}
					
					expressionCombo.addModifyListener(new ModifyListener() {
						
						@Override
						public void modifyText(ModifyEvent e) {
							// TODO Auto-generated method stub
							//验证输入内容
							if(isRequired) {
								dataChange();
							}
						}
					});
					
					controls.add(expressionCombo);
				} 
				
				//暂时还是跟text一样，保留
				else if(type.equals(types[1])) {
					//创建文本框
					expressionComboViewer = new ExpressionComboViewer(proGroup);

					ExpressionCombo expressionCombo = expressionComboViewer.getExpressionCombo();
					
					ExpressionTo expressionTo = new ExpressionTo();
					expressionTo.setName(expname == null ? "" : expname);
					expressionTo.setExpressionText(expression == null ? "" : expression);
					
					expressionCombo.setExpressionTo(expressionTo);
					
					expressionCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
					
					if(isRequired) {
						isRequiredControl.add(expressionCombo);
					}
					
					expressionCombo.addModifyListener(new ModifyListener() {
						
						@Override
						public void modifyText(ModifyEvent e) {
							// TODO Auto-generated method stub
							//验证输入内容
							if(isRequired) {
								dataChange();
							}
						}
					});
					
					controls.add(expressionCombo);
				}
				
				/*if(type.equals(types[0])) {
					//创建文本框
					final Text connectNameText = new Text(proGroup, SWT.BORDER);
					connectNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL)); //布局
					connectNameText.setText(expression == null ? "" : expression);
					if(isRequired) {
						isRequiredControl.add(connectNameText);
					}
					
					connectNameText.addModifyListener(new ModifyListener() {
						
						public void modifyText(ModifyEvent e) {
							//验证输入内容
							if(isRequired) {
								dataChange();
							}
						}
					});
					controls.add(connectNameText);
				} 
				else if(type.equals(types[1])) {
					//创建文本框
					final Text connectNameText = new Text(proGroup, SWT.BORDER | SWT.WRAP);
//					修改了是textarea时的布局
					GridData gd_desctext = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
					gd_desctext.heightHint = 70;
					connectNameText.setLayoutData(gd_desctext);
//					connectNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL)); //布局
					connectNameText.setText(expression == null ? "" : expression);
					if(isRequired) {
						isRequiredControl.add(connectNameText);
					}
					
					connectNameText.addModifyListener(new ModifyListener() {
						
						public void modifyText(ModifyEvent e) {
							//验证输入内容
							if(isRequired) {
								dataChange();
							}
						}
					});
					controls.add(connectNameText);
				} */
				else if(type.equals(types[2])) {
					//创建文本框
					final Text connectNameText = new Text(proGroup, SWT.BORDER | SWT.PASSWORD);
					connectNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL)); //布局
					connectNameText.setText(expression == null ? "" : expression);
					if(isRequired) {
						isRequiredControl.add(connectNameText);
					}
					
					connectNameText.addModifyListener(new ModifyListener() {
						
						public void modifyText(ModifyEvent e) {
							//验证输入内容
							if(isRequired) {
								dataChange();
							}
						}
					});
					controls.add(connectNameText);
				} 
				else if(type.equals(types[3])) {
					//创建
					Button connectNameBtn = new Button(proGroup, SWT.BORDER | SWT.CHECK);
					connectNameBtn.setLayoutData(new GridData(GridData.FILL_HORIZONTAL)); //布局
					if(isRequired) {
						isRequiredControl.add(connectNameBtn);
					}
					controls.add(connectNameBtn);
				} 
				else if(type.equals(types[4])) {
					//创建
					Button connectNameBtn = new Button(proGroup, SWT.BORDER | SWT.RADIO);
					connectNameBtn.setLayoutData(new GridData(GridData.FILL_HORIZONTAL)); //布局
					if(isRequired) {
						isRequiredControl.add(connectNameBtn);
					}
					controls.add(connectNameBtn);
				} 
				else if(type.equals(types[5])) {
					//创建
					final Combo connectNameCombo = new Combo(proGroup, SWT.BORDER);
					connectNameCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL)); //布局
					connectNameCombo.setItems(new String[] {expression == null ? "" : expression});
					connectNameCombo.setText(expression == null ? "" : expression);
					if(isRequired) {
						isRequiredControl.add(connectNameCombo);
					}
					connectNameCombo.addModifyListener(new ModifyListener() {
						
						public void modifyText(ModifyEvent e) {
							//验证输入内容
							if(isRequired) {
								dataChange();
							}
						}
					});
					controls.add(connectNameCombo);
				}
			}
		}

		if(commonIsRequired) {
			//初始化的时候先使‘完成’按钮不可用
			setPageComplete(false); 
		}
		
		//必须要的，将新的Composite放入
		
		dataChange();
		setControl(newComposite);
	}
	
	/**
	 * 验证输入内容
	 */
	private void dataChange() {
		//循环需要验证控件列表
		for (Iterator iterator = isRequiredControl.iterator(); iterator.hasNext();) {
			Control valcontrol = (Control) iterator.next();
			if(valcontrol instanceof Text) {
				Text text = (Text) valcontrol;
				if(text.getText().trim().equals("")) {
					setErrorMessage("名称输入不能空"); //提示错误信息
					setPageComplete(false); //使‘完成’和‘下一步’两个按钮不可用
					return;
				}
			}
			else if(valcontrol instanceof Combo) {
				Combo combo = (Combo) valcontrol;
				if(combo.getText().trim().equals("")) {
					setErrorMessage("名称输入不能空"); //提示错误信息
					setPageComplete(false); //使‘完成’和‘下一步’两个按钮不可用
					return;
				}
			}
		}
		
		setErrorMessage(null); //清空错误信息
		setPageComplete(true); //使‘完成’和‘下一步’两个按钮可用
	}

	public List<Control> getControls() {
		return controls;
	}

	public void setControls(List<Control> controls) {
		this.controls = controls;
	}

}
