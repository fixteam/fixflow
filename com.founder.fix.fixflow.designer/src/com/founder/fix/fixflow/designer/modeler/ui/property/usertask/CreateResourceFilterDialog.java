package com.founder.fix.fixflow.designer.modeler.ui.property.usertask;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.founder.fix.bpmn2extensions.fixflow.Documentation;
import com.founder.fix.bpmn2extensions.fixflow.Expression;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowFactory;
import com.founder.fix.bpmn2extensions.fixflow.ResourceFilter;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.emf.databinding.EMFObservables;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage.Literals;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionComboViewer;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionTo;

public class CreateResourceFilterDialog extends TitleAreaDialog {
	@SuppressWarnings("unused")
	private DataBindingContext m_bindingContext;
	private ResourceFilter resourceFilter;
	private Text idtext;
	private Text nametext;
	private Text desctext;
	private ExpressionComboViewer expressionComboViewer;

	/**
	 * Create the dialog.
	 * @param parentShell
	 * @wbp.parser.constructor
	 */
	public CreateResourceFilterDialog(Shell parentShell) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(SWT.RESIZE | SWT.TITLE|SWT.PRIMARY_MODAL);
		resourceFilter=FixFlowFactory.eINSTANCE.createResourceFilter();
	}
	
	/**
	 * 修改时构造函数
	 * @param parentShell
	 * @param resourceFilter
	 */
	public CreateResourceFilterDialog(Shell parentShell, ResourceFilter resourceFilter) {
		super(parentShell);
		this.resourceFilter = resourceFilter;
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("创建过滤器");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		GridLayout gl_container = new GridLayout(2, false);
		gl_container.verticalSpacing = 10;
		gl_container.marginRight = 30;
		gl_container.marginLeft = 30;
		gl_container.marginHeight = 15;
		container.setLayout(gl_container);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label idLabel = new Label(container, SWT.NONE);
		idLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		idLabel.setText("编号");
		
		idtext = new Text(container, SWT.BORDER);
		idtext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label nameLabel = new Label(container, SWT.NONE);
		nameLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		nameLabel.setText("名称");
		
		nametext = new Text(container, SWT.BORDER);
		nametext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label expLabel = new Label(container, SWT.NONE);
		expLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		expLabel.setText("表达式");
		
		expressionComboViewer = new ExpressionComboViewer(container, SWT.BORDER | SWT.READ_ONLY);
		Combo expcombo = expressionComboViewer.getCombo();
		expcombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label descLabel = new Label(container, SWT.NONE);
		descLabel.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		descLabel.setText("描述");
		
		desctext = new Text(container, SWT.BORDER);
		GridData gd_desctext = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_desctext.heightHint = 70;
		gd_desctext.widthHint = 240;
		desctext.setLayoutData(gd_desctext);

		init();
		
		return area;
	}
	
	/**
	 * 初始化
	 */
	public void init(){
		if(resourceFilter != null){
			ExpressionTo expressionTo = new ExpressionTo();
			expressionTo.setName(resourceFilter.getExpression() == null ? "" : resourceFilter.getExpression().getName());
			expressionTo.setExpressionText(resourceFilter.getExpression() == null ? "" : resourceFilter.getExpression().getValue());
			expressionComboViewer.setDefaultExpressionInput(expressionTo);
			desctext.setText(resourceFilter.getDocumentation().size() > 0 ? resourceFilter.getDocumentation().get(0).getValue() : "" );
		}
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		button.setText("确定");
		Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		button_1.setText("取消");
		m_bindingContext = initDataBindings();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 370);
	}

	public ResourceFilter getResourceFilter() {
		return resourceFilter;
	}

	public void setResourceFilter(ResourceFilter resourceFilter) {
		this.resourceFilter = resourceFilter;
	}

	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		resourceFilter = FixFlowFactory.eINSTANCE.createResourceFilter();
		resourceFilter.setId(idtext.getText() == null ? "" : idtext.getText());
		resourceFilter.setName(nametext.getText() == null ? "" : nametext.getText());
		/*Expression expression = FixFlowFactory.eINSTANCE.createExpression();
		expression.setValue(expcombo.getText() == null ? "" : expcombo.getText());
		resourceFilter.setExpression(expression);*/
		
		ExpressionTo expressionTo = expressionComboViewer.getExpressionCombo().getExpressionTo();
		if(expressionTo!=null)
		{
			Expression expression=FixFlowFactory.eINSTANCE.createExpression();
			expression.setName(expressionTo.getName());
			expression.setValue(expressionTo.getExpressionText());
			resourceFilter.setExpression(expression);
		}
		
		Documentation documentation = FixFlowFactory.eINSTANCE.createDocumentation();
		documentation.setValue(desctext.getText() == null ? "" : desctext.getText());
		resourceFilter.getDocumentation().add(documentation);
		super.okPressed();
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue idtextObserveTextObserveWidget = SWTObservables.observeText(idtext, SWT.Modify);
		IObservableValue resourceFilterIdObserveValue = EMFObservables.observeValue(resourceFilter, Literals.RESOURCE_FILTER__ID);
		bindingContext.bindValue(idtextObserveTextObserveWidget, resourceFilterIdObserveValue, null, null);
		//
		IObservableValue nametextObserveTextObserveWidget = SWTObservables.observeText(nametext, SWT.Modify);
		IObservableValue resourceFilterNameObserveValue = EMFObservables.observeValue(resourceFilter, Literals.RESOURCE_FILTER__NAME);
		bindingContext.bindValue(nametextObserveTextObserveWidget, resourceFilterNameObserveValue, null, null);
		//
		return bindingContext;
	}
}
