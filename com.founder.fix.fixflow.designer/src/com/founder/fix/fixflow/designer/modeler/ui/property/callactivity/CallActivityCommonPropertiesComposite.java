package com.founder.fix.fixflow.designer.modeler.ui.property.callactivity;



import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import org.eclipse.swt.widgets.Text;

import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.fixflow.designer.modeler.ui.property.AbstractFixFlowBpmn2PropertiesComposite;
import com.founder.fix.fixflow.designer.modeler.ui.property.SectionBpmnElement;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionChangedEvent;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionComboViewer;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionTo;
import com.founder.fix.fixflow.designer.usercontrol.IExpressionChangedListener;
import com.founder.fix.fixflow.designer.util.StringUtil;


import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;

public class CallActivityCommonPropertiesComposite extends AbstractFixFlowBpmn2PropertiesComposite {
	private Text idtext;
	private Text nametext;
	private Text desctext;

	private Button btnCheckButton;

	private ExpressionComboViewer ecvProcessKey;
	private ExpressionComboViewer ecvProcessVersion;
	private ExpressionComboViewer bizKeyExpressionComboViewer;
	
	public CallActivityCommonPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public CallActivityCommonPropertiesComposite(Composite parent, int style) {
		super(parent, style);


	}

	@Override
	public void createUI() {
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginHeight = 10;
		gridLayout.verticalSpacing = 10;
		gridLayout.marginLeft = 5;
		setLayout(gridLayout);

		Label idLabel = new Label(this, SWT.NONE);
		GridData gd_idLabel = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_idLabel.widthHint = 28;
		idLabel.setLayoutData(gd_idLabel);
		idLabel.setText("\u7F16\u53F7");

		
		toolkit.adapt(idLabel, true, true);

		idtext = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		GridData gd_idtext = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_idtext.widthHint = 240;
		idtext.setLayoutData(gd_idtext);

		
		toolkit.adapt(idtext, true, true);

		Label nameLabel = new Label(this, SWT.NONE);
		GridData gd_nameLabel = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_nameLabel.widthHint = 28;
		nameLabel.setLayoutData(gd_nameLabel);
		nameLabel.setText("\u540D\u79F0");


		toolkit.adapt(nameLabel, true, true);

		nametext = new Text(this, SWT.BORDER);
		GridData gd_nametext = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_nametext.widthHint = 240;
		nametext.setLayoutData(gd_nametext);

		toolkit.adapt(nametext, true, true);
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setText("流程");
	
		toolkit.adapt(lblNewLabel, true, true);
		
		
		
		
		ecvProcessKey = new ExpressionComboViewer(this);
		Combo combo = ecvProcessKey.getCombo();
		GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_combo.widthHint = 222;
		combo.setLayoutData(gd_combo);
	
		toolkit.adapt(combo, true, true);
		
		
		Label label = new Label(this, SWT.NONE);
		label.setText("版本");

		toolkit.adapt(label, true, true);
		
		ecvProcessVersion = new ExpressionComboViewer(this);
		Combo combo2 = ecvProcessVersion.getCombo();
		GridData gd_combo2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_combo2.widthHint = 222;
		combo2.setLayoutData(gd_combo2);
	
		toolkit.adapt(combo2, true, true);
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("关联值");
		
		toolkit.adapt(lblNewLabel_1, true, true);
		
		bizKeyExpressionComboViewer = new ExpressionComboViewer(this);
		Combo bizKeyCombo = bizKeyExpressionComboViewer.getCombo();
		GridData gd_bizKeyCombo = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_bizKeyCombo.widthHint = 222;
		bizKeyCombo.setLayoutData(gd_bizKeyCombo);
		
		toolkit.adapt(bizKeyCombo, true, true);
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setText("异步");
		
		toolkit.adapt(label_1, true, true);
		
		btnCheckButton = new Button(this, SWT.CHECK);
		toolkit.adapt(btnCheckButton, true, true);
		
		Label descLabel = new Label(this, SWT.NONE);
		GridData gd_descLabel = new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1);
		gd_descLabel.widthHint = 28;
		descLabel.setLayoutData(gd_descLabel);
		descLabel.setText("\u63CF\u8FF0");

		toolkit.adapt(descLabel, true, true);

		desctext = new Text(this, SWT.BORDER | SWT.WRAP);
		GridData gd_desctext = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_desctext.heightHint = 36;
		gd_desctext.widthHint = 236;
		desctext.setLayoutData(gd_desctext);


		toolkit.adapt(desctext, true, true);
	}

	@Override
	public void createUIBindings(EObject eObject) {

		
		Object elementId = be.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_ID);
		Object elementName = be.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_NAME);
		
		
		if ((elementId != null && !elementId.toString().equals(""))&&(elementName != null && !elementName.toString().equals(""))) {

			ExpressionTo expressionTo=new ExpressionTo();
			expressionTo.setName(elementName.toString());
			expressionTo.setExpressionText(elementId.toString());
			ecvProcessKey.setDefaultExpressionInput(expressionTo);

		}
		//Object isAsyns = be.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__IS_ASYNC);
		
	
	
		
		bindAttributeCheckButton(btnCheckButton, FixFlowPackage.Literals.DOCUMENT_ROOT__IS_ASYNC);


		
		
		
		Object elementVersion = be.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_VERSION);
		Object elementVersionName = be.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_VERSION_NAME);
		
		
		if ((elementVersion != null && !elementVersion.toString().equals(""))&&(elementVersionName != null && !elementVersionName.toString().equals(""))) {

			ExpressionTo expressionTo=new ExpressionTo();
			expressionTo.setName(elementVersionName.toString());
			expressionTo.setExpressionText(elementVersion.toString());
			ecvProcessVersion.setDefaultExpressionInput(expressionTo);

		}
		
		Object callableElementBizKey = be.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_BIZ_KEY);
		Object callableElementBizKeyName = be.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_BIZ_KEY_NAME);
		
		if ((callableElementBizKey != null && !callableElementBizKey.toString().equals(""))&&(callableElementBizKeyName != null && !callableElementBizKeyName.toString().equals(""))) {

			ExpressionTo expressionTo=new ExpressionTo();
			expressionTo.setName(callableElementBizKeyName.toString());
			expressionTo.setExpressionText(callableElementBizKey.toString());
			bizKeyExpressionComboViewer.setDefaultExpressionInput(expressionTo);

		}
		
		ecvProcessKey.addExpressionChangedListeners(new IExpressionChangedListener() {
			
			@Override
			public void expressionChanged(final ExpressionChangedEvent event) {
				// TODO Auto-generated method stub
				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						
						if(event.getExpressionTo()!=null){
							be.eSet(FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_ID, event.getExpressionTo().getExpressionText());
							be.eSet(FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_NAME, event.getExpressionTo().getName());
							
						}
						else{
							be.eSet(FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_ID, null);
							be.eSet(FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_NAME, null);
						}
						
					
					}
				});
			}
		});
		
		
		ecvProcessVersion.addExpressionChangedListeners(new IExpressionChangedListener() {
			
			@Override
			public void expressionChanged(final ExpressionChangedEvent event) {
				// TODO Auto-generated method stub
				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						if(event.getExpressionTo()!=null){
							be.eSet(FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_VERSION, event.getExpressionTo().getExpressionText());
							be.eSet(FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_VERSION_NAME, event.getExpressionTo().getName());
							
						}
						else{
							be.eSet(FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_VERSION, null);
							be.eSet(FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_VERSION_NAME, null);
						}
					}
				});
			}
		});
		
		
		bizKeyExpressionComboViewer.addExpressionChangedListeners(new IExpressionChangedListener() {
			
			@Override
			public void expressionChanged(final ExpressionChangedEvent event) {
				// TODO Auto-generated method stub
				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						if(event.getExpressionTo()!=null){
							be.eSet(FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_BIZ_KEY, event.getExpressionTo().getExpressionText());
							be.eSet(FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_BIZ_KEY_NAME, event.getExpressionTo().getName());
							
						}
						else{
							be.eSet(FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_BIZ_KEY, null);
							be.eSet(FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_BIZ_KEY_NAME, null);
						}
					}
				});
			}
		});
		
		

		EList<EAttribute> eAllAttributes = be.eClass().getEAllAttributes();
		
		for (EAttribute attrib : eAllAttributes) {

		

				if (attrib.getName().equals("id")) {
					bind(attrib, idtext);

				}
				if (attrib.getName().equals("name")) {
					bind(attrib, nametext);

				}
			
		}

		for (EReference e : be.eClass().getEAllReferences()) {

			if (e.getName().equals("documentation")) {
				
				bindDocumentation(e, desctext);	
			}

		}
	}
	
	
	public void bindAttributeCheckButton(final Button button,final EStructuralFeature eStructuralFeature) {

		Object eGet = SectionBpmnElement.sectionElement.eGet(eStructuralFeature);


			if (eGet != null && !eGet.toString().equals("")) {

				boolean isAsynsObj=StringUtil.getBoolean(eGet.toString());
			
				button.setSelection(isAsynsObj);
			}
	

		IObservableValue textObserver = SWTObservables.observeSelection(button);// (text,
		// SWT.Modify);
		textObserver.addValueChangeListener(new IValueChangeListener() {

			@SuppressWarnings("restriction")
			@Override
			public void handleValueChange(final ValueChangeEvent e) {

					TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							SectionBpmnElement.sectionElement.eSet(eStructuralFeature, e.diff.getNewValue().toString());
						}
					});
				}
			
		});
	}


}
