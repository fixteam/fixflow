package com.founder.fix.fixflow.designer.connector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.founder.fix.bpmn2extensions.connector.Connector;
import com.founder.fix.fixflow.designer.util.ConnectorUtil;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class ChooseConnectorFileToEditWizardPage extends WizardPage {
	private CheckboxTreeViewer checkboxTreeViewer;
	private Connector connector;
	private Button wizardRadioButton;
	private Button fileRadioButton;
	private Text txtjava;
	
	/**
	 * Create the wizard.
	 */
	public ChooseConnectorFileToEditWizardPage(Connector connector) {
		super("wizardPage");
		setTitle("选择编辑方式");
		setDescription("请选择您想要编辑连接器的方式");
		this.connector = connector;
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		GridLayout gl_container = new GridLayout(1, false);
		gl_container.verticalSpacing = 0;
		gl_container.marginWidth = 0;
		gl_container.marginHeight = 0;
		gl_container.horizontalSpacing = 0;
		container.setLayout(gl_container);
		
		Composite composite = new Composite(container, SWT.NONE);
		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.marginRight = 15;
		gl_composite.marginBottom = 15;
		gl_composite.marginLeft = 15;
		gl_composite.marginHeight = 15;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite.setBounds(0, 0, 64, 64);
		
		wizardRadioButton = new Button(composite, SWT.RADIO);
		wizardRadioButton.setSelection(true);
		wizardRadioButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				checkboxTreeViewer.getTree().setEnabled(false);
			}
		});
		wizardRadioButton.setText("使用向导");
		
		txtjava = new Text(composite, SWT.READ_ONLY);
		txtjava.setForeground(SWTResourceManager.getColor(220, 20, 60));
		txtjava.setText("注意：使用此方式编辑会导致连接器java文件重写！");
		txtjava.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		fileRadioButton = new Button(composite, SWT.RADIO);
		fileRadioButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		fileRadioButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				checkboxTreeViewer.getTree().setEnabled(true);
			}
		});
		fileRadioButton.setText("修改文件");
		
		checkboxTreeViewer = new CheckboxTreeViewer(composite, SWT.BORDER);
		Tree tree = checkboxTreeViewer.getTree();
		tree.setEnabled(false);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		checkboxTreeViewer.setLabelProvider(new ViewerLabelProvider());
		checkboxTreeViewer.setContentProvider(new TreeContentProvider());
//		checkboxTreeViewer.setInput(getFilesInConnectorPath());
	}
	
	public List<String> getFilesInConnectorPath() {
		List<String> filenames = new ArrayList<String>();
		File d = new File(ConnectorUtil.getConnectorPathById(connector.getConnectorId()));// 建立当前目录中文件的File对象
		File[] fl = d.listFiles();// 取得目录中所有文件的File对象数组
		for (int i = 0; i < fl.length; i++) {
			 // 目录下的文件：
			 File e = fl[i];
			 if (e.isFile()) {
				 filenames.add(e.getName());
//				 System.out.println(e.getName());
			 }
		}
		return filenames;
	}
	
	private static class ViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			return super.getText(element);
		}
	}
	
	
	private static class TreeContentProvider implements ITreeContentProvider {
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
		public void dispose() {
		}
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof List) {
				@SuppressWarnings("rawtypes")
				List list = (List) inputElement;
				return list.toArray();
			} else {
				return new Object[0];
			}
		}
		public Object[] getChildren(Object parentElement) {
			return null;
		}
		public Object getParent(Object element) {
			return null;
		}
		public boolean hasChildren(Object element) {
			return false;
		}
	}


	public Connector getConnector() {
		return connector;
	}

	public void setConnector(Connector connector) {
		this.connector = connector;
	}

	public CheckboxTreeViewer getCheckboxTreeViewer() {
		return checkboxTreeViewer;
	}

	public void setCheckboxTreeViewer(CheckboxTreeViewer checkboxTreeViewer) {
		this.checkboxTreeViewer = checkboxTreeViewer;
	}

	public Button getWizardRadioButton() {
		return wizardRadioButton;
	}

	public Button getFileRadioButton() {
		return fileRadioButton;
	}
}
