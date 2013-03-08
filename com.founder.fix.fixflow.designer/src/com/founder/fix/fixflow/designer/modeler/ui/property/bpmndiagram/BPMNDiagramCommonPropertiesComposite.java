package com.founder.fix.fixflow.designer.modeler.ui.property.bpmndiagram;

import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.swt.widgets.Composite;

import com.founder.fix.fixflow.designer.modeler.ui.property.AbstractFixFlowBpmn2PropertiesComposite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;

public class BPMNDiagramCommonPropertiesComposite extends AbstractFixFlowBpmn2PropertiesComposite {
	private Text text;
	private Text text_1;
	private Text text_2;

	
	public BPMNDiagramCommonPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}
	public BPMNDiagramCommonPropertiesComposite(Composite parent, int style) {
		super(parent, style);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createUI() {
		
		setLayout(null);
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(10, 17, 33, 14);
		lblNewLabel.setText("编号");

		toolkit.adapt(lblNewLabel, true, true);
		
		text = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text.setBounds(48, 14, 194, 19);

		toolkit.adapt(text, true, true);
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBounds(10, 50, 33, 14);
		lblNewLabel_1.setText("名称");

		toolkit.adapt(lblNewLabel_1, true, true);
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(48, 47, 194, 19);

		toolkit.adapt(text_1, true, true);
		
		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setBounds(10, 80, 33, 14);
		lblNewLabel_2.setText("描述");

		toolkit.adapt(lblNewLabel_2, true, true);
		
		text_2 = new Text(this, SWT.BORDER | SWT.WRAP);
		text_2.setBounds(48, 80, 194, 77);

		toolkit.adapt(text_2, true, true);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.setBounds(438, 10, 94, 28);
		btnNewButton.setText("发布到数据库");
		
		List list = new List(this, SWT.BORDER);
		list.setBounds(317, 14, 115, 143);
		
		Label lblNewLabel_3 = new Label(this, SWT.NONE);
		lblNewLabel_3.setBounds(252, 17, 59, 47);
		lblNewLabel_3.setText("数据库版本");
		
		Button btnNewButton_1 = new Button(this, SWT.NONE);
		btnNewButton_1.setBounds(438, 36, 94, 28);
		btnNewButton_1.setText("更新历史版本");
		
	}

	@Override
	public void createUIBindings(EObject eObject) {
		

		EList<EAttribute> eAllAttributes = be.eClass().getEAllAttributes();
		
		for (EAttribute attrib : eAllAttributes) {

				// 绑定开始节点的编号属性
				if (attrib.getName().equals("id")) {
					//text.setText("");
					bind(attrib, text);
					
				}
				if (attrib.getName().equals("name")) {
					//text_1.setText("");
					bind(attrib, text_1);
					
				}
				

			
		}
		
		for (EReference e : be.eClass().getEAllReferences()) {

			if (e.getName().equals("documentation")) {

				//text_3.setText("");
				bindDocumentation(e,text_2);
			}
		}

		
		
	}

	
}
