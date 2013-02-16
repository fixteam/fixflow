/**
 * 
 */
package com.founder.fix.fixflow.designer.modeler.ui.property.connectors.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

import com.founder.fix.bpmn2extensions.connector.OutputParameter;
import com.founder.fix.bpmn2extensions.fixflow.DataVariable;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.fixflow.designer.modeler.ui.common.ExpressionDialog;
import com.founder.fix.fixflow.designer.modeler.ui.property.SectionBpmnElement;

/**
 * @author wangzhiwei
 *
 */ 
public class OutputConnectorWizardPage extends WizardPage {
	
	private EList<OutputParameter> outputs;
	
	private ScrolledComposite scrolledComposite;
	
	private Composite gridComposite;
	
	private String[] commonOutputIds;

	/**
	 * @param pageName
	 */
	public OutputConnectorWizardPage(String pageName) {
		super(pageName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param pageName
	 * @param title
	 * @param titleImage
	 */
	public OutputConnectorWizardPage(String pageName, String title, String desc,
			ImageDescriptor titleImage, EList<OutputParameter> outputs) {
		super(pageName, title, titleImage);
		this.outputs = outputs;
		
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
		//创建一个底层的Composite，并使用GridLayout布局
		Composite newComposite = new Composite(parent, SWT.NONE);
		newComposite.setLayout(new FillLayout(SWT.VERTICAL));
		
		//创建滚动面板
		scrolledComposite = new ScrolledComposite(newComposite, SWT.V_SCROLL | SWT.H_SCROLL);
		
		//创建布局
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 5; //grid分为5列
		
		gridComposite = new Composite(scrolledComposite, SWT.NONE);
		gridComposite.setLayout(gridLayout);
		
		//这样设置才生效
		scrolledComposite.setContent(gridComposite);
		
		GridData gridData = new GridData(100, 20);
		gridData.horizontalSpan = 5;
		
		Button addBtn = new Button(gridComposite, SWT.NONE);
		addBtn.setLayoutData(gridData);
		addBtn.setText("添加输出参数");
		addBtn.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				//添加一行控件
				addRowControls();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		new Label(gridComposite, SWT.NONE);
		
		//创建label
		Label label = new Label(gridComposite, SWT.NONE);
		label.setText("连接器输出");
		
		new Label(gridComposite, SWT.NONE);
		
		label = new Label(gridComposite, SWT.NONE);
		label.setText("目标变量");
		
		new Label(gridComposite, SWT.NONE);
		
		if(outputs != null && outputs.size() > 0) {
			for (int i = 0; i < outputs.size(); i++) {
				OutputParameter output = outputs.get(i);
				
				String  rowId = UUID.randomUUID().toString();
				
				//创建label
				label = new Label(gridComposite, SWT.NONE);
				label.setText(output.getName());
				label.setData(rowId);
				
				//创建combo
				final Combo outputCombo = new Combo(gridComposite, SWT.BORDER | SWT.READ_ONLY);
				outputCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL)); //布局
				
				commonOutputIds = new String[outputs.size()];
				for (int j = 0; j < outputs.size(); j++) {
					OutputParameter parameter = outputs.get(j);
					commonOutputIds[j] = parameter.getId();
				}
				/*commonOutputIds[outputs.size()] = "编辑表达式...";*/
				outputCombo.setItems(commonOutputIds);
				
				outputCombo.select(0);
				outputCombo.setData(rowId);
				outputCombo.setData("type", "expression");
				outputCombo.addModifyListener(new ModifyListener() {
					
					public void modifyText(ModifyEvent e) {
						//验证输入内容
//						dataChange();
						if(outputCombo.getSelectionIndex() == commonOutputIds.length - 1) {
							//弹出表达式编辑器
							
						}
					}
				});
				
				outputCombo.addListener(SWT.Selection, new Listener() {
					
					@Override
					public void handleEvent(Event event) {
						// TODO Auto-generated method stub
						if(outputCombo.getSelectionIndex() == outputCombo.getItemCount()-1){
							ExpressionDialog expressionDialog = new ExpressionDialog(getShell());
							expressionDialog.setBlockOnOpen(true);
							if(expressionDialog !=null && expressionDialog.open() == InputDialog.OK){
								//outputCombo.setText(expressionDialog.getExpression());
							}
							else{
								//outputCombo.setText(expressionDialog.getExpression());
							}
						}
					}
				});
				
				label = new Label(gridComposite, SWT.NONE);
				label.setText("跳转到");
				label.setData(rowId);
				
				//创建combo
				Combo targetCombo = new Combo(gridComposite, SWT.BORDER );
				targetCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL)); //布局
				targetCombo.setData(rowId);
				targetCombo.setData("type", "target");
				targetCombo.setItems(getOutputTargets());
				targetCombo.addModifyListener(new ModifyListener() {
					
					public void modifyText(ModifyEvent e) {
						//验证输入内容
//						dataChange();
					}
				});
				
				final Button cancelBtn = new Button(gridComposite, SWT.NONE);
				cancelBtn.setText("删除");
				cancelBtn.setData(rowId);
				cancelBtn.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent e) {
						deleteRowControls(String.valueOf(cancelBtn.getData()));
						
						updateScrolledComposite();
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
			}
		}
		
		//必须要的，将新的Composite放入
		setControl(newComposite);
		
		updateScrolledComposite();
	}
	
	/**
	 * 添加一行
	 * @param scrolledComposite
	 * @param gridComposite
	 */
	private void addRowControls() {
		String  rowId = UUID.randomUUID().toString();
		
		//创建label
		Label label = new Label(gridComposite, SWT.NONE);
		label.setText("");
		label.setData(rowId);
		
		//创建combo
		final Combo outputCombo = new Combo(gridComposite, SWT.BORDER | SWT.READ_ONLY);
		outputCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL)); //布局
		if(commonOutputIds != null) {
			outputCombo.setItems(commonOutputIds);
		}
		outputCombo.select(0);
		outputCombo.setData(rowId);
		outputCombo.setData("type", "expression");
		outputCombo.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent e) {
				//验证输入内容
//				dataChange();
			}
		});
		
		outputCombo.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if(outputCombo.getSelectionIndex() == outputCombo.getItemCount()-1){
					ExpressionDialog expressionDialog = new ExpressionDialog(getShell());
					expressionDialog.setBlockOnOpen(true);
					if(expressionDialog !=null && expressionDialog.open() == InputDialog.OK){
						//outputCombo.setText(expressionDialog.getExpression());
					}
					else{
						//outputCombo.setText(expressionDialog.getExpression());
					}
				}
			}
		});
		
		label = new Label(gridComposite, SWT.NONE);
		label.setText("跳转到");
		label.setData(rowId);
		
		//创建combo
		Combo targetCombo = new Combo(gridComposite, SWT.BORDER );
		targetCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL)); //布局
		targetCombo.setData(rowId);
		targetCombo.setData("type", "target");
		targetCombo.setItems(getOutputTargets());
		targetCombo.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent e) {
				//验证输入内容
//				dataChange();
			}
		});
		
		final Button cancelBtn = new Button(gridComposite, SWT.NONE);
		cancelBtn.setText("删除");
		cancelBtn.setData(rowId);
		cancelBtn.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteRowControls(String.valueOf(cancelBtn.getData()));
				
				updateScrolledComposite();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		gridComposite.layout(true);
		
		updateScrolledComposite();
	}
	
	/**
	 * 删除一行
	 * @param buttonId
	 */
	private void deleteRowControls(String buttonId) {
		List<Control> headList = new ArrayList<Control>();
		List<Control> list = new ArrayList<Control>();
		
		//获取所有的gridComposite内的控件
		Control[] controls = gridComposite.getChildren();
		if(controls.length == 6) {
			return;
		}
		
		for (int i = 0; i < controls.length; i++) {
			Control control = controls[i];
			if(i > 5) {
				list.add(control);
			} else {
				headList.add(control);
			}
		}

		for (int i = 0; i < list.size(); i++) {
			Control control = list.get(i);
			if(control.getData().toString().equals(buttonId)) {
				control.setVisible(false);
				control.dispose();
			}
		}
	}

	/**
	 * 更新面板状态
	 */
	private void updateScrolledComposite() {
		scrolledComposite.setMinSize(gridComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite.setExpandHorizontal(true);
	    scrolledComposite.setExpandVertical(true);
	    scrolledComposite.setAlwaysShowScrollBars(false);
		gridComposite.layout();
	}
	
	/**
	 * 获取目标列表
	 */
	public String[] getOutputTargets() {
		List<String> dataVarList = new ArrayList<String>();

		for (ExtensionAttributeValue eav : SectionBpmnElement.process
				.getExtensionValues()) {
			org.eclipse.emf.ecore.util.FeatureMap extensionElements = eav
					.getValue();
			Object objectElement = extensionElements.get(
					FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_VARIABLE, true);
			if (objectElement != null) {

				@SuppressWarnings("unchecked")
				java.util.List<DataVariable> dataVariableList = (java.util.List<DataVariable>) objectElement;
				for (DataVariable dataVariable : dataVariableList) {
					dataVarList.add("${" + dataVariable.getId() + "}");
				}
			}
		}

		// 私有
		if (!(SectionBpmnElement.sectionElement instanceof org.eclipse.bpmn2.Process)
				|| !(SectionBpmnElement.sectionElement instanceof org.eclipse.bpmn2.impl.ProcessImpl)) {
			BaseElement baseElement = (BaseElement) SectionBpmnElement.sectionElement;
			if (baseElement.getExtensionValues().size() > 0) {
				for (ExtensionAttributeValue extensionAttributeValue : baseElement
						.getExtensionValues()) {
					org.eclipse.emf.ecore.util.FeatureMap extensionElements = extensionAttributeValue
							.getValue();
					Object objectElement = extensionElements
							.get(FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_VARIABLE,
									true);
					if (objectElement != null) {

						@SuppressWarnings("unchecked")
						java.util.List<DataVariable> dataVariableList = (java.util.List<DataVariable>) objectElement;
						for (DataVariable dataVariable : dataVariableList) {
							dataVarList.add("${" + dataVariable.getId() + "}");
						}

					}
				}
			}
		}
		
		String[] targets = new String[dataVarList.size()];
		for (int i = 0; i < dataVarList.size(); i++) {
			targets[i] = dataVarList.get(i);
		}
		
		return targets;
	}


	public Composite getGridComposite() {
		return gridComposite;
	}

	public void setGridComposite(Composite gridComposite) {
		this.gridComposite = gridComposite;
	}
	
}
