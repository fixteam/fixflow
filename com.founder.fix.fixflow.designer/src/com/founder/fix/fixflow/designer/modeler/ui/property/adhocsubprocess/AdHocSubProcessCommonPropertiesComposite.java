package com.founder.fix.fixflow.designer.modeler.ui.property.adhocsubprocess;

import org.eclipse.bpmn2.AdHocOrdering;
import org.eclipse.bpmn2.AdHocSubProcess;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.founder.fix.fixflow.designer.modeler.ui.property.AbstractFixFlowBpmn2PropertiesComposite;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;

import com.founder.fix.fixflow.designer.usercontrol.ExpressionChangedEvent;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionComboViewer;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionTo;
import com.founder.fix.fixflow.designer.usercontrol.IExpressionChangedListener;


public class AdHocSubProcessCommonPropertiesComposite extends AbstractFixFlowBpmn2PropertiesComposite {

	private Text idtext;
	private Text nametext;
	private Text desctext;
	
	AdHocSubProcess adHocSubProcess;

	@SuppressWarnings("unused")
	private BPMNShape shape;
	private Button btnCheckButton;
	ExpressionComboViewer expressionComboViewer_CompletionCondition;
	Combo combo_AdHocOrdering;
	
	public AdHocSubProcessCommonPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}
	public AdHocSubProcessCommonPropertiesComposite(Composite parent, int style) {
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
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setText("取消实例");

		toolkit.adapt(label_2, true, true);

		
		btnCheckButton = new Button(this, SWT.CHECK);
		btnCheckButton.setText("(用于并行顺序)");

		toolkit.adapt(btnCheckButton, true, true);
		
		Label label_AdHocOrdering = new Label(this, SWT.NONE);
		label_AdHocOrdering.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_AdHocOrdering.setText("执行顺序");
		

		toolkit.adapt(label_AdHocOrdering, true, true);
		
		combo_AdHocOrdering = new Combo(this, SWT.READ_ONLY);
		GridData gd_combo_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_combo_1.widthHint = 140;
		combo_AdHocOrdering.setLayoutData(gd_combo_1);
		

		toolkit.adapt(combo_AdHocOrdering, true, true);
		
		Label label_CompletionCondition = new Label(this, SWT.NONE);
		label_CompletionCondition.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_CompletionCondition.setText("完成条件");

		toolkit.adapt(label_CompletionCondition, true, true);
		
		expressionComboViewer_CompletionCondition = new ExpressionComboViewer(this);
		Combo combo = expressionComboViewer_CompletionCondition.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		toolkit.adapt(combo, true, true);
		
		
		Label descLabel = new Label(this, SWT.NONE);
		GridData gd_descLabel = new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1);
		gd_descLabel.widthHint = 28;
		descLabel.setLayoutData(gd_descLabel);
		descLabel.setText("\u63CF\u8FF0");


		toolkit.adapt(descLabel, true, true);

		desctext = new Text(this, SWT.BORDER | SWT.WRAP);
		GridData gd_desctext = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_desctext.heightHint = 46;
		gd_desctext.widthHint = 240;
		desctext.setLayoutData(gd_desctext);


		toolkit.adapt(desctext, true, true);
		
		
		
	}

	@Override
	public void createUIBindings(EObject be) {


		EList<EAttribute> eAllAttributes = be.eClass().getEAllAttributes();
		
		
		
		adHocSubProcess=(AdHocSubProcess)be;
		
		if(adHocSubProcess.isCancelRemainingInstances()){
			btnCheckButton.setSelection(true);
			
		}
		else{
			btnCheckButton.setSelection(false);
		}
		
		combo_AdHocOrdering.removeAll();
		combo_AdHocOrdering.add("顺序", 0);
		combo_AdHocOrdering.add("平行", 1);
		
		if(adHocSubProcess.getOrdering()==AdHocOrdering.PARALLEL){
			combo_AdHocOrdering.select(1);
			btnCheckButton.setEnabled(true);
		}
		else{
			combo_AdHocOrdering.select(0);
			btnCheckButton.setEnabled(false);
			
		}
		
		if(adHocSubProcess.getCompletionCondition()!=null){
			ExpressionTo expressionTo = new ExpressionTo();
			
		
			expressionTo.setName(adHocSubProcess.getCompletionCondition().getId());
			expressionTo.setExpressionText(((FormalExpression)adHocSubProcess.getCompletionCondition()).getBody());
			expressionComboViewer_CompletionCondition.setDefaultExpressionInput(expressionTo);
		}
		
		combo_AdHocOrdering.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						if(combo_AdHocOrdering.getSelectionIndex()==0){
							btnCheckButton.setEnabled(false);
							adHocSubProcess.setOrdering(AdHocOrdering.SEQUENTIAL);
							
						}
						else{
							btnCheckButton.setEnabled(true);
							adHocSubProcess.setOrdering(AdHocOrdering.PARALLEL);
						}
					}
				});
				
			
				//combo_AdHocOrdering.
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		btnCheckButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				final boolean btnCheckButtonSelection=btnCheckButton.getSelection();
				
				
				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						if(btnCheckButtonSelection){
							adHocSubProcess.setCancelRemainingInstances(true);
						}
						else{
							adHocSubProcess.setCancelRemainingInstances(false);
						}
					}
				});
				
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		

		
		
		
		
		expressionComboViewer_CompletionCondition.addExpressionChangedListeners(new IExpressionChangedListener() {

			@Override
			public void expressionChanged(final ExpressionChangedEvent event) {
				// TODO Auto-generated method stub
				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {

						if(event.getExpressionTo()!=null){
							
							FormalExpression formalExpression=Bpmn2Factory.eINSTANCE.createFormalExpression();
							formalExpression.setId(event.getExpressionTo().getName());
							formalExpression.setBody(event.getExpressionTo().getExpressionText());
							adHocSubProcess.setCompletionCondition(formalExpression);
						}
						else{
							adHocSubProcess.setCompletionCondition(null);
						}

					}
				});

			}
		});
		
		
		
		
		

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



}
