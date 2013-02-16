package com.founder.fix.fixflow.designer.modeler.ui.property.usertask;


import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.founder.fix.bpmn2extensions.fixflow.Expression;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowFactory;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.fixflow.FormUri;
import com.founder.fix.bpmn2extensions.fixflow.FormUriView;
import com.founder.fix.bpmn2extensions.fixflow.TaskPriority;
import com.founder.fix.fixflow.designer.modeler.ui.property.AbstractFixFlowBpmn2PropertiesComposite;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionChangedEvent;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionComboViewer;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionTo;
import com.founder.fix.fixflow.designer.usercontrol.IExpressionChangedListener;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class UserTaskCommonPropertiesComposite extends AbstractFixFlowBpmn2PropertiesComposite {
	private Text idtext;
	private Text nametext;
	private Text desctext;

	private ExpressionComboViewer expressionComboViewer;

	private ExpressionComboViewer expressionComboViewForm;
	
	private ExpressionComboViewer expressionComboViewer_yx;
	
	public UserTaskCommonPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}
	public UserTaskCommonPropertiesComposite(Composite parent, int style) {
		super(parent, style);
		((GridData) attributesSection.getLayoutData()).grabExcessHorizontalSpace = false;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createUI() {
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.verticalSpacing = 10;
		gridLayout.marginLeft = 5;
		gridLayout.marginHeight = 10;
		setLayout(gridLayout);

		Label idLabel = new Label(this, SWT.NONE);
		GridData gd_idLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_idLabel.widthHint = 28;
		idLabel.setLayoutData(gd_idLabel);
		idLabel.setText("\u7F16\u53F7");

	
		toolkit.adapt(idLabel, true, true);

		idtext = new Text(this, SWT.BORDER|SWT.READ_ONLY);
		GridData gd_idtext = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_idtext.widthHint = 240;
		idtext.setLayoutData(gd_idtext);


		toolkit.adapt(idtext, true, true);

		Label nameLabel = new Label(this, SWT.NONE);
		nameLabel.setText("\u540D\u79F0");

	
		toolkit.adapt(nameLabel, true, true);

		nametext = new Text(this, SWT.BORDER);
		nametext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));


		toolkit.adapt(nametext, true, true);

		Label formLabel = new Label(this, SWT.NONE);
		formLabel.setText("操作表单");


		toolkit.adapt(formLabel, true, true);

		expressionComboViewer = new ExpressionComboViewer(this);

		Combo combo = expressionComboViewer.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));


		toolkit.adapt(combo, true, true);


		toolkit.adapt(combo, true, true);
		
		Label label = new Label(this, SWT.NONE);
		label.setText("浏览表单");
		toolkit.adapt(label, true, true);
		expressionComboViewForm = new ExpressionComboViewer(this);
		Combo comboView = expressionComboViewForm.getCombo();
		comboView.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		toolkit.adapt(comboView, true, true);
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setText("优先级");
		
		toolkit.adapt(label_1, true, true);
		
		expressionComboViewer_yx = new ExpressionComboViewer(this);
		Combo combo_yx = expressionComboViewer_yx.getCombo();
		combo_yx.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		toolkit.adapt(combo_yx, true, true);
		
		
		Label descLabel = new Label(this, SWT.NONE);
		descLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		descLabel.setText("\u63CF\u8FF0");

	
		toolkit.adapt(descLabel, true, true);

		desctext = new Text(this, SWT.BORDER | SWT.WRAP);
		GridData gd_desctext = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_desctext.heightHint = 43;
		gd_desctext.widthHint = 240;
		desctext.setLayoutData(gd_desctext);


		toolkit.adapt(desctext, true, true);

	}

	@Override
	public void createUIBindings(EObject eObject) {

		final UserTask userTask = (UserTask) be;
		if (userTask.getExtensionValues().size() > 0) {

			for (ExtensionAttributeValue extensionAttributeValue : userTask.getExtensionValues()) {

				FeatureMap extensionElements = extensionAttributeValue.getValue();

				for (Entry entry : extensionElements) {
					if (entry.getValue() instanceof FormUri) {
						FormUri formUri = (FormUri) entry.getValue();
						ExpressionTo expressionTo = new ExpressionTo();
						expressionTo.setName(formUri.getExpression().getName());
						expressionTo.setExpressionText(formUri.getExpression().getValue());
						expressionComboViewer.setDefaultExpressionInput(expressionTo);
						continue;
					}
					if(entry.getValue() instanceof FormUriView){
						FormUriView formUriView = (FormUriView) entry.getValue();
						ExpressionTo expressionTo = new ExpressionTo();
						expressionTo.setName(formUriView.getExpression().getName());
						expressionTo.setExpressionText(formUriView.getExpression().getValue());
						expressionComboViewForm.setDefaultExpressionInput(expressionTo);
						continue;
					}
					
					if(entry.getValue() instanceof TaskPriority){
						TaskPriority taskPriority = (TaskPriority) entry.getValue();
						ExpressionTo expressionTo = new ExpressionTo();
						expressionTo.setName(taskPriority.getExpression().getName());
						expressionTo.setExpressionText(taskPriority.getExpression().getValue());
						expressionComboViewer_yx.setDefaultExpressionInput(expressionTo);
						continue;
					}

				}

			}

		}

		expressionComboViewer.addExpressionChangedListeners(new IExpressionChangedListener() {

			@Override
			public void expressionChanged(final ExpressionChangedEvent event) {
				// TODO Auto-generated method stub
				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {

						setFormUriExtensionExpression(userTask, event.getExpressionTo());
						// be.eSet(a, e.diff.getNewValue());
					}
				});

			}
		});
		
		
		
		expressionComboViewForm.addExpressionChangedListeners(new IExpressionChangedListener() {

			@Override
			public void expressionChanged(final ExpressionChangedEvent event) {
				// TODO Auto-generated method stub
				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {

						setFormUriViewExtensionExpression(userTask, event.getExpressionTo());
						// be.eSet(a, e.diff.getNewValue());
					}
				});

			}
		});

		expressionComboViewer_yx.addExpressionChangedListeners(new IExpressionChangedListener() {

			@Override
			public void expressionChanged(final ExpressionChangedEvent event) {
				// TODO Auto-generated method stub
				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {

						setTaskPriorityExtensionExpression(userTask, event.getExpressionTo());
						// be.eSet(a, e.diff.getNewValue());
					}
				});

			}
		});
		
		
		EList<EAttribute> eAllAttributes = be.eClass().getEAllAttributes();
		
		for (EAttribute attrib : eAllAttributes) {

			
				if (attrib.getName().equals("id")) {
					// text.setText("");
					bind(attrib, idtext);

				}
				if (attrib.getName().equals("name")) {
					// text_1.setText("");
					bind(attrib, nametext);

				}
				


			 
		}

		for (EReference e : be.eClass().getEAllReferences()) {

			if (e.getName().equals("documentation")) {

				// text_3.setText("");
				bindDocumentation(e, desctext);
			}
		}

	}

	private void setFormUriExtensionExpression(BaseElement baseElement, ExpressionTo expressionTo) {


		for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {

			FeatureMap extensionElements = extensionAttributeValue.getValue();
			for (Entry entry : extensionElements) {
				if (entry.getValue() instanceof FormUri) {
					if(expressionTo==null)
					{
						extensionElements.remove(entry);
					}
					else{
						FormUri formUri = (FormUri) entry.getValue();
						formUri.getExpression().setName(expressionTo.getName());
						formUri.getExpression().setValue(expressionTo.getExpressionText());
					}

					return;
				}
			}

		}
		if(expressionTo!=null)
		{
			FormUri formUri = FixFlowFactory.eINSTANCE.createFormUri();

			Expression expression = FixFlowFactory.eINSTANCE.createExpression();
			expression.setName(expressionTo.getName());
			expression.setValue(expressionTo.getExpressionText());
			formUri.setExpression(expression);

			FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__FORM_URI, formUri);

			if (baseElement.getExtensionValues().size() > 0) {
				baseElement.getExtensionValues().get(0).getValue().add(extensionElementEntry);
			} else {
				ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
				extensionElement.getValue().add(extensionElementEntry);
				baseElement.getExtensionValues().add(extensionElement);
			}
		}

		

	}
	
	
	private void setTaskPriorityExtensionExpression(BaseElement baseElement, ExpressionTo expressionTo) {

		
		for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {

			FeatureMap extensionElements = extensionAttributeValue.getValue();
			for (Entry entry : extensionElements) {
				if (entry.getValue() instanceof TaskPriority) {
					if(expressionTo==null)
					{
						extensionElements.remove(entry);
					}
					else{
						TaskPriority taskPriority = (TaskPriority) entry.getValue();
						taskPriority.getExpression().setName(expressionTo.getName());
						taskPriority.getExpression().setValue(expressionTo.getExpressionText());
					}

					return;
				}
			}

		}
		if(expressionTo!=null)
		{
			TaskPriority taskPriority = FixFlowFactory.eINSTANCE.createTaskPriority();

			Expression expression = FixFlowFactory.eINSTANCE.createExpression();
			expression.setName(expressionTo.getName());
			expression.setValue(expressionTo.getExpressionText());
			taskPriority.setExpression(expression);

			FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_PRIORITY, taskPriority);

			if (baseElement.getExtensionValues().size() > 0) {
				baseElement.getExtensionValues().get(0).getValue().add(extensionElementEntry);
			} else {
				ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
				extensionElement.getValue().add(extensionElementEntry);
				baseElement.getExtensionValues().add(extensionElement);
			}
		}

		

	}

	
	private void setFormUriViewExtensionExpression(BaseElement baseElement, ExpressionTo expressionTo) {


		for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {

			FeatureMap extensionElements = extensionAttributeValue.getValue();
			for (Entry entry : extensionElements) {
				if (entry.getValue() instanceof FormUriView) {
					if(expressionTo==null)
					{
						extensionElements.remove(entry);
					}
					else{
						FormUriView formUriView = (FormUriView) entry.getValue();
						formUriView.getExpression().setName(expressionTo.getName());
						formUriView.getExpression().setValue(expressionTo.getExpressionText());
					}

					return;
				}
			}

		}
		if(expressionTo!=null)
		{
			FormUriView formUriView = FixFlowFactory.eINSTANCE.createFormUriView();

			Expression expression = FixFlowFactory.eINSTANCE.createExpression();
			expression.setName(expressionTo.getName());
			expression.setValue(expressionTo.getExpressionText());
			formUriView.setExpression(expression);

			FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__FORM_URI_VIEW, formUriView);

			if (baseElement.getExtensionValues().size() > 0) {
				baseElement.getExtensionValues().get(0).getValue().add(extensionElementEntry);
			} else {
				ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
				extensionElement.getValue().add(extensionElementEntry);
				baseElement.getExtensionValues().add(extensionElement);
			}
		}

		

	}


}
