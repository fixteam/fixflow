package com.founder.fix.fixflow.designer.modeler.ui.property.sequenceflow;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.fixflow.designer.modeler.ui.property.AbstractFixFlowBpmn2PropertiesComposite;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionChangedEvent;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionComboViewer;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionTo;
import com.founder.fix.fixflow.designer.usercontrol.IExpressionChangedListener;


public class SequenceFlowCommonPropertiesComposite extends AbstractFixFlowBpmn2PropertiesComposite {
	private Text idtext;
	private Text nametext;
	private Text desctext;

	private Label expLabel;
	private Combo combo;
	private ExpressionComboViewer expressionComboViewer;
	private Label label;
	private Combo combo_1;
	
	public SequenceFlowCommonPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public SequenceFlowCommonPropertiesComposite(Composite parent, int style) {
		super(parent, style);
		((GridData) attributesSection.getLayoutData()).grabExcessHorizontalSpace = false;
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
		gd_idLabel.widthHint = 42;
		idLabel.setLayoutData(gd_idLabel);
		idLabel.setText("编号");
		

		toolkit.adapt(idLabel, true, true);
		
		idtext = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		GridData gd_idtext = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_idtext.widthHint = 240;
		idtext.setLayoutData(gd_idtext);
		

		toolkit.adapt(idtext, true, true);

		Label nameLabel = new Label(this, SWT.NONE);
		nameLabel.setText("名称");
		

		toolkit.adapt(nameLabel, true, true);

		nametext = new Text(this, SWT.BORDER);
		nametext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		

		toolkit.adapt(nametext, true, true);
		
		label = new Label(this, SWT.NONE);
		label.setText("排序");
		toolkit.adapt(label, true, true);
		
		String[] str = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
		combo_1 = new Combo(this, SWT.READ_ONLY);
		combo_1.setItems(str);
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		combo_1.select(0);
		
		toolkit.adapt(combo_1, true, true);
		
		for(int i=0;i<str.length;i++) {
			if(((SequenceFlow)be).eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__ORDER_ID).toString().equals(str[i])) {
				combo_1.select(i);
				break;
			}
		}
		
		toolkit.adapt(combo_1, true, true);
		
		expLabel = new Label(this, SWT.NONE);
		expLabel.setText("\u8868\u8FBE\u5F0F");

		toolkit.adapt(expLabel, true, true);
		
		expressionComboViewer = new ExpressionComboViewer(this);
		combo = expressionComboViewer.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		toolkit.adapt(combo, true, true);
		
		
		Label descLabel = new Label(this, SWT.NONE);
		descLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		descLabel.setText("描述");

		toolkit.adapt(descLabel, true, true);

		desctext = new Text(this, SWT.BORDER | SWT.WRAP);
		GridData gd_desctext = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_desctext.heightHint = 70;
		gd_desctext.widthHint = 240;
		desctext.setLayoutData(gd_desctext);
		
	
		toolkit.adapt(desctext, true, true);
		
		
	}





	@Override
	public void createUIBindings(EObject eObject) {
		
		final SequenceFlow sequenceFlow = (SequenceFlow) be;
		
		if (sequenceFlow.getConditionExpression() == null) {
			//expressionComboViewer.setDefaultExpressionInput(null);
		} else {
			ExpressionTo expressionTo = new ExpressionTo();
			expressionTo.setName(((FormalExpression) sequenceFlow.getConditionExpression()).getId());
			expressionTo.setExpressionText(((FormalExpression) sequenceFlow.getConditionExpression()).getBody());
			expressionComboViewer.setDefaultExpressionInput(expressionTo);
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
						
						
						if(event.getExpressionTo()==null)
						{
							sequenceFlow.setConditionExpression(null);
						}
						else{
							if (sequenceFlow.getConditionExpression() == null) {
								final FormalExpression value = Bpmn2Factory.eINSTANCE.createFormalExpression();
//								ModelUtil.setID(value, be.eResource());
								value.setId(event.getExpressionTo().getName());
								value.setBody(event.getExpressionTo().getExpressionText());
								sequenceFlow.setConditionExpression(value);
							}else{
								((FormalExpression) sequenceFlow.getConditionExpression()).setBody(event.getExpressionTo().getExpressionText());
								((FormalExpression) sequenceFlow.getConditionExpression()).setId(event.getExpressionTo().getName());
							}
						}

					}
				});
			}
		});
		
		combo_1.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						SequenceFlow sequenceFlow = (SequenceFlow) be;
						sequenceFlow.eSet(FixFlowPackage.Literals.DOCUMENT_ROOT__ORDER_ID, Integer.valueOf(combo_1.getText()));
					}
				});
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		EList<EAttribute> eAllAttributes = be.eClass().getEAllAttributes();
		
		for (EAttribute attrib : eAllAttributes) {

			
				
				if (attrib.getName().equals("id")) {
					//text.setText("");
					bind(attrib, idtext);
					
				}
				if (attrib.getName().equals("name")) {
					//text_1.setText("");
					bind(attrib, nametext);
					
				}
				

			
		}


		for (EReference e : be.eClass().getEAllReferences()) {

			if (e.getName().equals("documentation")) {

				//text_3.setText("");
				bindDocumentation(e,desctext);
			}
		
		}

		
	}

}
