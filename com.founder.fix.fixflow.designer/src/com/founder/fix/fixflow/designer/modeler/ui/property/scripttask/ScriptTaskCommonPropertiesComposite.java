package com.founder.fix.fixflow.designer.modeler.ui.property.scripttask;


import org.eclipse.bpmn2.ScriptTask;
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

import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.fixflow.designer.modeler.ui.property.AbstractFixFlowBpmn2PropertiesComposite;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionChangedEvent;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionComboViewer;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionTo;
import com.founder.fix.fixflow.designer.usercontrol.IExpressionChangedListener;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;

public class ScriptTaskCommonPropertiesComposite extends AbstractFixFlowBpmn2PropertiesComposite {
	private Text idtext;
	private Text nametext;
	private Text desctext;

	public ScriptTaskCommonPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}
	
	ExpressionComboViewer scriptComboViewer;
	
	public ScriptTaskCommonPropertiesComposite(Composite parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createUI() {
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginLeft = 5;
		gridLayout.marginHeight = 10;
		gridLayout.verticalSpacing = 10;
		setLayout(gridLayout);

		Label idLabel = new Label(this, SWT.NONE);
		GridData gd_idLabel = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_idLabel.widthHint = 28;
		idLabel.setLayoutData(gd_idLabel);
		idLabel.setText("\u7F16\u53F7");

	
		toolkit.adapt(idLabel, true, true);

		idtext = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		GridData gd_idtext = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_idtext.widthHint = 240;
		idtext.setLayoutData(gd_idtext);

		
		toolkit.adapt(idtext, true, true);

		Label nameLabel = new Label(this, SWT.NONE);
		nameLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		nameLabel.setText("\u540D\u79F0");

		
		toolkit.adapt(nameLabel, true, true);

		nametext = new Text(this, SWT.BORDER);
		nametext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		
		toolkit.adapt(nametext, true, true);
		
		Label scriptLabel = new Label(this, SWT.NONE);
		scriptLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		scriptLabel.setText("脚本");
		
		toolkit.adapt(scriptLabel, true, true);
		
		scriptComboViewer = new ExpressionComboViewer(this);
		Combo combo = scriptComboViewer.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
	
		toolkit.adapt(combo, true, true);
		
		
		
		Label descLabel = new Label(this, SWT.NONE);
		descLabel.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		descLabel.setText("\u63CF\u8FF0");

		
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
		final ScriptTask scriptTask=(ScriptTask)be;
		
		if(scriptTask.getScript()!=null){
			ExpressionTo expressionTo = new ExpressionTo();
			
			
			Object eGet = be.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__SCRIPT_NAME);

			if (eGet != null) {
				expressionTo.setName(eGet.toString());
			}
			
			expressionTo.setExpressionText(scriptTask.getScript());
			scriptComboViewer.setDefaultExpressionInput(expressionTo);
		}
		
		
		scriptComboViewer.addExpressionChangedListeners(new IExpressionChangedListener() {

			@Override
			public void expressionChanged(final ExpressionChangedEvent event) {
				// TODO Auto-generated method stub
				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						
						
						
						if(event.getExpressionTo()!=null){
							be.eSet(FixFlowPackage.Literals.DOCUMENT_ROOT__SCRIPT_NAME, event.getExpressionTo().getName());

							scriptTask.setScript(event.getExpressionTo().getExpressionText());
						}
						else{
							be.eSet(FixFlowPackage.Literals.DOCUMENT_ROOT__SCRIPT_NAME,null);
							scriptTask.setScript(null);
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

	
}
