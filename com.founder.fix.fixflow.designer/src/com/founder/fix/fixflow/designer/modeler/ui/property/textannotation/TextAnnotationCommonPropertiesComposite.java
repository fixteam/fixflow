package com.founder.fix.fixflow.designer.modeler.ui.property.textannotation;



import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.founder.fix.fixflow.designer.modeler.ui.property.AbstractFixFlowBpmn2PropertiesComposite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class TextAnnotationCommonPropertiesComposite extends AbstractFixFlowBpmn2PropertiesComposite {
	private Text idtext;
	private Text desctext;

	public TextAnnotationCommonPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public TextAnnotationCommonPropertiesComposite(Composite parent, int style) {
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
		gd_idLabel.widthHint = 28;
		idLabel.setLayoutData(gd_idLabel);
		idLabel.setText("\u7F16\u53F7");

		
		toolkit.adapt(idLabel, true, true);

		idtext = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		GridData gd_idtext = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_idtext.widthHint = 240;
		idtext.setLayoutData(gd_idtext);

		
		toolkit.adapt(idtext, true, true);


		Label descLabel = new Label(this, SWT.NONE);
		descLabel.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		descLabel.setText("注释");

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
		// TODO Auto-generated method stub
		// ItemProviderAdapter itemProviderAdapter = (ItemProviderAdapter) new
		// Bpmn2ItemProviderAdapterFactory().adapt(be,
		// ItemProviderAdapter.class);

		EList<EAttribute> eAllAttributes = be.eClass().getEAllAttributes();
		
		for (EAttribute attrib : eAllAttributes) {

				// 绑定开始节点的编号属性
				if (attrib.getName().equals("id")) {
					// text.setText("");
					bind(attrib, idtext);

				}
				if(attrib.getName().equals("text"))
				{
					bind(attrib,desctext);
				}

			
		}
		
		 


	}
	
	

	
}
