package com.founder.fix.fixflow.designer.modeler.ui.property.process;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.LaneSet;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.modeler.core.ModelHandler;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;


import com.founder.fix.apputil.Const;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowFactory;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.fixflow.designer.modeler.ui.property.AbstractFixFlowBpmn2PropertiesComposite;
import com.founder.fix.fixflow.designer.modeler.ui.property.SectionBpmnElement;
import com.founder.fix.fixflow.designer.util.FixFlowConfigUtil;
import com.founder.fix.fixflow.designer.util.StringUtil;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;

public class ProcessInternationPropertiesComposite extends AbstractFixFlowBpmn2PropertiesComposite {

	public ProcessInternationPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
		// TODO Auto-generated constructor stub
	}

	public ProcessInternationPropertiesComposite(Composite parent, int style) {
		super(parent, style);
		
		
		// TODO Auto-generated constructor stub
	}
	Combo combo;
	Button btnNewButton;
	@Override
	public void createUI() {

		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(15, 42, 65, 15);
		lblNewLabel.setText("国际化文件");
		
		combo = new Combo(this, SWT.READ_ONLY);
		GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_combo.widthHint = 103;
		combo.setLayoutData(gd_combo);
		combo.setBounds(86, 42, 160, 23);
		
		btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.setBounds(212, 42, 48, 25);
		btnNewButton.setText("切换");
		
		
		toolkit.adapt(lblNewLabel, true, true);
		
		toolkit.adapt(combo, true, true);
		
		toolkit.adapt(btnNewButton, true, true);
	}

	@Override
	public void createUIBindings(EObject be) {
		// TODO Auto-generated method stub
		
		
		String pathString=FixFlowConfigUtil.getFixFlowInternationPath();
		
		
		
		File file = new File(pathString);
		final File[] childs =file.listFiles();
		
		for (File fileChild : childs) {
			if(fileChild.isDirectory()){
				if(fileChild.getName().indexOf(".")<0){
					combo.add(fileChild.getName());
				}
				
			}
		}
		combo.select(0);
		
		
		btnNewButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {

				Properties properties = new Properties();
				
				File selectFile=null;
				
				
				
				
				
				
				String itemString=combo.getItem(combo.getSelectionIndex());
				for (File fileChildObj : childs) {
					if(fileChildObj.isDirectory()&&fileChildObj.getName().equals(itemString)){
						
						selectFile=fileChildObj;
					}
				}
				
				
				if(selectFile==null){
					return;
				}
				
				File[] selectFiles=selectFile.listFiles();
				File processFile=null;
				for (File selectFileObj : selectFiles) {
					String processIdString=SectionBpmnElement.process.getId();
					if(selectFileObj.getName().equals(processIdString)){
						processFile=selectFileObj;
						
						Object dbIdObject=SectionBpmnElement.process.eGet(FixFlowPackage.Literals.DOCUMENT_ROOT__DBID);
						if(dbIdObject!=null&&!dbIdObject.equals("")){
							String dbIdString=StringUtil.getString(dbIdObject);
							dbIdString=dbIdString.replace(":", "_");
							File[] tempFiles=selectFileObj.listFiles();
							for (File file2 : tempFiles) {
								if(file2.getName().equals(dbIdString+".properties")){
									InputStream in=null;
									try {
										in = new BufferedInputStream(new FileInputStream(file2.getPath()));
									} catch (FileNotFoundException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
									try {
										properties.load(in);
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
								}
							}
							
						}
						
						
					}
				}
				
				
				if(properties!=null){
					updateFlowObjName(properties);
				}
				
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		

	}
	
	public void updateFlowObjName(final Properties properties){
		
		
		@SuppressWarnings("restriction")
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				
		
				ModelHandler modelHandler=null;
				try {
					modelHandler = ModelHandler.getInstance(be);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				List<BaseElement> baseElements = modelHandler.getAll(BaseElement.class);

				for (BaseElement baseElement : baseElements) {

					
					if(baseElement instanceof FlowElement){
						Object returnObject=properties.get(baseElement.getId());

						
						if(returnObject!=null&&!returnObject.equals("")){
							
							
							try {
								
								((FlowElement)baseElement).setName(new String(StringUtil.getString(returnObject).getBytes(Const.PageEncoding.ISO88591), Const.PageEncoding.UTF8));
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}else{
						if(baseElement instanceof Lane){
							Object returnObject=properties.get(baseElement.getId());

							
							if(returnObject!=null&&!returnObject.equals("")){
								
								
								try {
									
									((Lane)baseElement).setName(new String(StringUtil.getString(returnObject).getBytes(Const.PageEncoding.ISO88591), Const.PageEncoding.UTF8));
								} catch (UnsupportedEncodingException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						
					}
					
					
					
			

					
				}
			}
		});
		

		
	}
	

}
