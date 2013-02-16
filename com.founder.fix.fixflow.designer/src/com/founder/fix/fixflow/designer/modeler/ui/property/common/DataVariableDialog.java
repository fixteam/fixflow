package com.founder.fix.fixflow.designer.modeler.ui.property.common;


import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;

import org.eclipse.jface.dialogs.TitleAreaDialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.founder.fix.bpmn2extensions.fixflow.DataVariable;
import com.founder.fix.bpmn2extensions.fixflow.Documentation;
import com.founder.fix.bpmn2extensions.fixflow.Expression;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowFactory;
import com.founder.fix.bpmn2extensions.variableconfig.DataTypeDef;

import com.founder.fix.fixflow.designer.modeler.ui.common.DataVarTo;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionComboViewer;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionTo;
import com.founder.fix.fixflow.designer.util.DataVarUtil;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;


public class DataVariableDialog extends TitleAreaDialog {
	@SuppressWarnings("unused")
	private DataBindingContext m_bindingContext;
	private Text nametext;
	private Text desctext;
	private Button istransientCheckButton;
	private Button ismutiCheckButton;
	private DataVariable dataVariable;
	//private Expression expression;
	ExpressionComboViewer expressionComboViewer;
	private Combo typecombo;
	private ComboViewer typecomboViewer;
	private String openType;
	
	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 * @wbp.parser.constructor
	 */
	public DataVariableDialog(Shell parentShell) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(SWT.RESIZE | SWT.TITLE | SWT.PRIMARY_MODAL);
		dataVariable = FixFlowFactory.eINSTANCE.createDataVariable();
		this.openType = "open";
	}

	/**
	 * 修改时构造函数
	 * 
	 * @param parentShell
	 */
	public DataVariableDialog(Shell parentShell, DataVariable dataVariable) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(SWT.RESIZE | SWT.TITLE | SWT.PRIMARY_MODAL);
		this.dataVariable = dataVariable;
		this.openType = "modify";
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("创建数据变量");

		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		GridLayout gl_container = new GridLayout(4, false);
		gl_container.marginBottom = 20;
		gl_container.verticalSpacing = 10;
		gl_container.marginHeight = 20;
		gl_container.marginRight = 30;
		gl_container.marginLeft = 30;
		container.setLayout(gl_container);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label nameLabel = new Label(container, SWT.NONE);
		nameLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		nameLabel.setText("名称");

		nametext = new Text(container, SWT.BORDER);
		nametext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));

		Label typeLabel = new Label(container, SWT.NONE);
		typeLabel.setText("类型");
		
		typecomboViewer = new ComboViewer(container, SWT.READ_ONLY);
		typecomboViewer.setContentProvider(new ArrayContentProvider());
		typecomboViewer.setLabelProvider(new ViewerLabelProvider());
		typecomboViewer.setInput(DataVarUtil.getFixFlowDataVariableConfig().getDataVariableDataType().getDataTypeDef());
		
		typecombo = typecomboViewer.getCombo();
		typecombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		if((List<DataTypeDef>)(typecomboViewer.getInput()) != null && ((List<DataTypeDef>)(typecomboViewer.getInput())).size()>0) {
			typecombo.select(0);
		}

		Label istransientLabel = new Label(container, SWT.NONE);
		istransientLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		istransientLabel.setText("持久化");

		istransientCheckButton = new Button(container, SWT.CHECK);
		istransientCheckButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label ismutiLabel = new Label(container, SWT.NONE);
		ismutiLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		ismutiLabel.setText("多个");
		ismutiLabel.setVisible(false);

		ismutiCheckButton = new Button(container, SWT.CHECK);
		ismutiCheckButton.setEnabled(false);
		GridData gd_ismutiCheckButton = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_ismutiCheckButton.widthHint = 30;
		ismutiCheckButton.setLayoutData(gd_ismutiCheckButton);
		ismutiCheckButton.setVisible(false);

		Label expLabel = new Label(container, SWT.NONE);
		expLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		expLabel.setText("默认值");

		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));

		
		expressionComboViewer = new ExpressionComboViewer(composite);
		
		
		Label descLabel = new Label(container, SWT.NONE);
		descLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		descLabel.setText("描述");

		desctext = new Text(container, SWT.BORDER | SWT.WRAP);
		GridData gd_desctext = new GridData(SWT.FILL, SWT.FILL, false, true, 3, 1);
		gd_desctext.heightHint = 50;
		desctext.setLayoutData(gd_desctext);

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
		// TODO Auto-generated method stub
		Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		button.setText("确定");
		Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
		button_1.setText("取消");
		m_bindingContext = initDataBindings();
	}

	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		
		//做数据变量名称唯一性验证
		if(openType.equals("open")) {
			List<DataVarTo> dataVarTos = DataVarUtil.getDataVarTos();
			for(DataVarTo dataVarTo : dataVarTos) {
				if(nametext.getText() != null && dataVarTo.getId() != null) {
					if(dataVarTo.getId().equals(nametext.getText())) {
						MessageDialog.openWarning(null, "警告", "已存在的数据变量ID，请更换ID");
						return;
					}
				}
			}
		}
		
		if(openType.equals("modify") && !(nametext.getText().equals(dataVariable.getId()))) {
			List<DataVarTo> dataVarTos = DataVarUtil.getDataVarTos();
			for(DataVarTo dataVarTo : dataVarTos) {
				if(nametext.getText() != null) {
					if(dataVarTo.getId().equals(nametext.getText())) {
						MessageDialog.openWarning(null, "警告", "已存在的数据变量ID，请更换ID");
						return;
					}
				}
			}
		}
		
		dataVariable = FixFlowFactory.eINSTANCE.createDataVariable();
		dataVariable.setId(nametext.getText() == null ? "" : nametext.getText());
		dataVariable.setIsList(ismutiCheckButton.getSelection());
		dataVariable.setIsPersistence(istransientCheckButton.getSelection());
		dataVariable.setDataType(((DataTypeDef)((IStructuredSelection)typecomboViewer.getSelection()).getFirstElement()) == null ? "" : ((DataTypeDef)((IStructuredSelection)typecomboViewer.getSelection()).getFirstElement()).getTypeValue());
		
		ExpressionTo expressionTo=expressionComboViewer.getExpressionCombo().getExpressionTo();
		if(expressionTo!=null)
		{
			Expression expression=FixFlowFactory.eINSTANCE.createExpression();
			expression.setName(expressionTo.getName());
			expression.setValue(expressionTo.getExpressionText());
			dataVariable.setExpression(expression);
		}
		
		dataVariable.setDataType(((DataTypeDef)((IStructuredSelection)typecomboViewer.getSelection()).getFirstElement()) == null ? "" : ((DataTypeDef)((IStructuredSelection)typecomboViewer.getSelection()).getFirstElement()).getTypeValue());
		
		Documentation documentation = FixFlowFactory.eINSTANCE.createDocumentation();
		documentation.setValue(desctext.getText() == null ? "" : desctext.getText());
		dataVariable.getDocumentation().add(documentation);
		
		super.okPressed();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(430, 380);
	}

	public DataVariable getDataVariable() {
		return dataVariable;
	}

	public void setDataVariable(DataVariable dataVariable) {
		this.dataVariable = dataVariable;
	}

	public Text getDesctext() {
		return desctext;
	}

	public void setDesctext(Text desctext) {
		this.desctext = desctext;
	}
	
	private class ViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}

		public String getText(Object element) {
			DataTypeDef dataTypeDef = (DataTypeDef) element;
			return dataTypeDef.getName();
		}
	}
	
	/**
	 * 初始化
	 */
	public void init() {
		if (dataVariable != null) {

			nametext.setText(dataVariable.getId() == null ? "" : dataVariable.getId());
			
			istransientCheckButton.setSelection(dataVariable.isIsPersistence());
			
			ismutiCheckButton.setSelection(dataVariable.isIsList());
			
			DataTypeDef dataTypeDef = DataVarUtil.getDataTypeDefByDataVariableDataType(dataVariable.getDataType());
			
			typecomboViewer.setSelection(new StructuredSelection(dataTypeDef));
			
			if (dataVariable.getExpression() != null) {
				ExpressionTo expressionTo=new ExpressionTo();
				expressionTo.setName(dataVariable.getExpression().getName());
				expressionTo.setExpressionText(dataVariable.getExpression().getValue());
				expressionComboViewer.setDefaultExpressionInput(expressionTo);
			}

			desctext.setText(dataVariable.getDocumentation().size() > 0 ? dataVariable.getDocumentation().get(0).getValue() : "");

		}
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		return bindingContext;
	}
}
