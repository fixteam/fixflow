package com.founder.fix.fixflow.designer.modeler.ui.property.erroreventdefinition;

import java.util.List;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.CatchEvent;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Documentation;
import org.eclipse.bpmn2.Error;
import org.eclipse.bpmn2.ErrorEventDefinition;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.fixflow.designer.modeler.ui.property.AbstractFixFlowBpmn2PropertiesComposite;
import com.founder.fix.fixflow.designer.modeler.ui.property.SectionBpmnElement;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.databinding.swt.SWTObservables;


public class CatchEventErrorEventDefinitionCommonPropertiesComposite extends AbstractFixFlowBpmn2PropertiesComposite {
	private Text idtext;
	private Text desctext;



	private Label label;
	private Combo combo;
	public CatchEventErrorEventDefinitionCommonPropertiesComposite(
			AbstractBpmn2PropertySection section) {
		super(section);
	}

	public CatchEventErrorEventDefinitionCommonPropertiesComposite(Composite parent, int style) {
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

		label = new Label(this, SWT.NONE);
		label.setText("捕获错误");
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		toolkit.adapt(label, true, true);

		combo = new Combo(this, SWT.READ_ONLY);
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
		gd_desctext.heightHint = 70;
		gd_desctext.widthHint = 240;
		desctext.setLayoutData(gd_desctext);

	
		toolkit.adapt(desctext, true, true);
	}

	@Override
	public void createUIBindings(EObject eObject) {

		CatchEvent throwEvent = (CatchEvent) be;
		final ErrorEventDefinition errorEventDefinition = (ErrorEventDefinition) throwEvent.getEventDefinitions().get(0);

		final Definitions definitions = SectionBpmnElement.definitions;

		combo.removeAll();
		combo.add("");
		for (RootElement rootElement : definitions.getRootElements()) {
			if (rootElement instanceof Error) {
				Error error = (Error) rootElement;

				combo.add(error.getErrorCode());

			}
		}
		
		if(errorEventDefinition.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__ERROR_CODE)!=null){
			
			combo.setText(errorEventDefinition.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__ERROR_CODE).toString());
			
			
			
		}

		combo.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				String selectedString = combo.getText();
				
				if(combo.getSelectionIndex()==0){
					@SuppressWarnings("restriction")
					TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							
							//
							errorEventDefinition.getAnyAttribute().clear();
							

						}
					});
					return ;
				}
				
				for (RootElement rootElement : definitions.getRootElements()) {
					if (rootElement instanceof Error) {
						final Error error = (Error) rootElement;
						if (error.getErrorCode().equals(selectedString)) {
							@SuppressWarnings("restriction")
							TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
							editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
								@Override
								protected void doExecute() {

									FeatureMap.Entry extensionAttributeEntry = new org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__ERROR_CODE, error.getErrorCode());
									errorEventDefinition.getAnyAttribute().clear();
									errorEventDefinition.getAnyAttribute().add(extensionAttributeEntry);

								}
							});
							return;
						}

					}
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		/*
		if(errorEventDefinition.getId()==null){
			@SuppressWarnings("restriction")
			TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
			editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					errorEventDefinition.setId(ModelUtil.generateID(errorEventDefinition));
				}
			});
		}*/

		if (errorEventDefinition != null) {
			idtext.setText(errorEventDefinition.getId());
		}

		IObservableValue textObserver = SWTObservables.observeText(idtext, SWT.Modify);
		textObserver.addValueChangeListener(new IValueChangeListener() {

			@SuppressWarnings("restriction")
			@Override
			public void handleValueChange(final ValueChangeEvent e) {

				if (!idtext.getText().equals(errorEventDefinition.getId())) {
					TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							errorEventDefinition.setId(e.diff.getNewValue().toString());
						
						}
					});
					
				}
			}
		});
		
		
	

		if (errorEventDefinition.getDocumentation().size() == 0 || errorEventDefinition.getDocumentation().get(0).getText() == null) {
			desctext.setText("");
		} else {
			desctext.setText(errorEventDefinition.getDocumentation().get(0).getText());
		}

		IObservableValue textObserverDoc = SWTObservables.observeText(desctext, SWT.Modify);
		textObserverDoc.addValueChangeListener(new IValueChangeListener() {

			@SuppressWarnings("restriction")
			@Override
			public void handleValueChange(final ValueChangeEvent e) {

				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {

						// List<Documentation> documentationList =
						// baseElement.getDocumentation();
						if (errorEventDefinition.getDocumentation().size() == 0) {
							final Documentation value = Bpmn2Factory.eINSTANCE.createDocumentation();
							ModelUtil.setID(value, be.eResource());
							errorEventDefinition.getDocumentation().add(value);
						}

						List<Documentation> documentationList = errorEventDefinition.getDocumentation();
						if (!desctext.getText().equals(documentationList.get(0).getText())) {

							documentationList.get(0).setText(e.diff.getNewValue().toString());
						}
					}
				});

			};

		});
	

	}

	
}
