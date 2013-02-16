package com.founder.fix.fixflow.designer.modeler.ui.property.startevent;

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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.fixflow.designer.modeler.ui.property.AbstractFixFlowBpmn2PropertiesComposite;
import com.founder.fix.fixflow.designer.util.StringUtil;

import org.eclipse.swt.widgets.Button;

public class StartEventCommonPropertiesComposite extends
		AbstractFixFlowBpmn2PropertiesComposite {
	private Text idtext;
	private Text nametext;
	private Text desctext;
	private Label lblNewLabel;
	private Button btnCheckButton;

	// private ExpressionComboViewer expressionComboViewer;

	public StartEventCommonPropertiesComposite(
			AbstractBpmn2PropertySection section) {
		super(section);
	}

	public StartEventCommonPropertiesComposite(Composite parent, int style) {
		super(parent, style);

	}

	@Override
	public void createUI() {
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginLeft = 5;
		gridLayout.marginHeight = 10;
		gridLayout.verticalSpacing = 10;
		setLayout(gridLayout);

		Label idLabel = new Label(this, SWT.NONE);
		GridData gd_idLabel = new GridData(SWT.FILL, SWT.CENTER, false, false,
				1, 1);
		gd_idLabel.widthHint = 28;
		idLabel.setLayoutData(gd_idLabel);
		idLabel.setText("\u7F16\u53F7");

		toolkit.adapt(idLabel, true, true);

		idtext = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		GridData gd_idtext = new GridData(SWT.FILL, SWT.CENTER, false, false,
				1, 1);
		gd_idtext.widthHint = 240;
		idtext.setLayoutData(gd_idtext);

		toolkit.adapt(idtext, true, true);

		Label nameLabel = new Label(this, SWT.NONE);
		nameLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1));
		nameLabel.setText("\u540D\u79F0");

		toolkit.adapt(nameLabel, true, true);

		nametext = new Text(this, SWT.BORDER);
		nametext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false,
				1, 1));

		toolkit.adapt(nametext, true, true);
		
		lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setText("持久化");
		
		toolkit.adapt(lblNewLabel, true, true);
		
		btnCheckButton = new Button(this, SWT.CHECK);
		btnCheckButton.setSelection(true);
		toolkit.adapt(btnCheckButton, true, true);

		Label descLabel = new Label(this, SWT.NONE);
		descLabel.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false,
				1, 1));
		descLabel.setText("\u63CF\u8FF0");

		toolkit.adapt(descLabel, true, true);

		desctext = new Text(this, SWT.BORDER | SWT.WRAP);
		GridData gd_desctext = new GridData(SWT.FILL, SWT.CENTER, false, false,
				1, 1);
		gd_desctext.widthHint = 240;
		gd_desctext.heightHint = 70;
		desctext.setLayoutData(gd_desctext);

		toolkit.adapt(desctext, true, true);
	}

	@Override
	public void createUIBindings(EObject eObject) {

		EList<EAttribute> eAllAttributes = be.eClass().getEAllAttributes();

		for (EAttribute attrib : eAllAttributes) {

			// 绑定开始节点的编号属性
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
		
		

		bindAttributeCheckButton(btnCheckButton, FixFlowPackage.Literals.DOCUMENT_ROOT__IS_PERSISTENCE);


	}
	
	public void bindAttributeCheckButton(final Button button,final EStructuralFeature eStructuralFeature) {

		Object eGet = be.eGet(eStructuralFeature);


			if (eGet != null && !eGet.toString().equals("")) {

				boolean isAsynsObj=StringUtil.getBoolean(eGet);
			
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
							be.eSet(eStructuralFeature, e.diff.getNewValue());
						}
					});
				}
			
		});
	}


}
