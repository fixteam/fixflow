package com.founder.fix.fixflow.designer.modeler.ui.property.parallelgateway;

import org.eclipse.bpmn2.GatewayDirection;
import org.eclipse.bpmn2.ParallelGateway;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;


import com.founder.fix.fixflow.designer.modeler.ui.property.AbstractFixFlowBpmn2PropertiesComposite;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class ParallelGatewayCommonPropertiesComposite extends AbstractFixFlowBpmn2PropertiesComposite {
	private Text idtext;
	private Text nametext;
	private Text desctext;



	private Label label;
	Combo combo;
	
	public ParallelGatewayCommonPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}
	

	public ParallelGatewayCommonPropertiesComposite(Composite parent, int style) {
		super(parent, style);
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
		GridData gd_idLabel = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_idLabel.widthHint = 56;
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
				
						label = new Label(this, SWT.NONE);
						label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
						label.setText("进出类型");
						
							
								toolkit.adapt(label, true, true);
		
				combo = new Combo(this, SWT.READ_ONLY);
				combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
				
						combo.setItems(new String[] { "分散", "合并" });
						
								combo.select(0);
								
								
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

		combo.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if (combo.getSelectionIndex() == combo.getItemCount() - 1) {
					
					

				}
			}
		});

		EList<EAttribute> eAllAttributes = be.eClass().getEAllAttributes();
	
		for (EAttribute attrib : eAllAttributes) {

		
				if (attrib.getName().equals("id")) {

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
		
		bindGatewayDirection(this.combo);

	}
	
	private void bindGatewayDirection(final Combo combo)
	{
		final ParallelGateway parallelGateway=(ParallelGateway)be;
		GatewayDirection gatewayDirection=parallelGateway.getGatewayDirection();
		//Unspecified
		//分
		if(gatewayDirection==GatewayDirection.DIVERGING)
		{
			combo.select(0);
		}
		//合
		if(gatewayDirection==GatewayDirection.CONVERGING)
		{
			combo.select(1);
		}
		
		
		IObservableValue textObserver = SWTObservables.observeText(combo);
		textObserver.addValueChangeListener(new IValueChangeListener() {

			@SuppressWarnings("restriction")
			@Override
			public void handleValueChange(final ValueChangeEvent e) {

					TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							if(combo.getSelectionIndex()==0)
							{
								parallelGateway.setGatewayDirection(GatewayDirection.DIVERGING);
							}
							else
							{
								parallelGateway.setGatewayDirection(GatewayDirection.CONVERGING);
							}

						}
					});
					
				
			}
		});
		
		
		
	}

	
}
