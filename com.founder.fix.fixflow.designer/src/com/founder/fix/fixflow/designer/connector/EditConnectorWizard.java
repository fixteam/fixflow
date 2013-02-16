package com.founder.fix.fixflow.designer.connector;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.founder.fix.fixflow.designer.modeler.ui.property.connectors.dialog.DynamicPageWizard;
import com.founder.fix.fixflow.designer.util.ConnectorUtil;

public class EditConnectorWizard extends DynamicPageWizard {
	private SelectConnectorWizardPage selectConnectorWizardPage;
	private ChooseConnectorFileToEditWizardPage chooseConnectorFileToEditWizardPage;
	
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		if(chooseConnectorFileToEditWizardPage.getWizardRadioButton().getSelection()) {
			ConnectorWizardCreationWizard cwcp = new ConnectorWizardCreationWizard(selectConnectorWizardPage.getConnector());
			CreateFixConnectorWizardDialog cfwd = new CreateFixConnectorWizardDialog(new Shell(SWT.PRIMARY_MODAL), cwcp);
			cfwd.open();
			return true;
		}
		if(chooseConnectorFileToEditWizardPage.getFileRadioButton().getSelection()) {
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject("fixflow-expand");
			
			Object[] files = chooseConnectorFileToEditWizardPage.getCheckboxTreeViewer().getCheckedElements();
			
			for(Object object : files) {
				try {
					File file = new File(ConnectorUtil.getConnectorPathById(selectConnectorWizardPage.getConnector().getConnectorId()) + "/" + object.toString());
					String relativePath = file.toString().substring(project.getLocation().toString().length() + 1);
					IFile ifile = project.getFile(relativePath);
					// 打开编辑器
					ConnectorUtil.refreshProject("fixflow-expand");
					IDE.openEditor(page, ifile);
				} catch (PartInitException e) {
				}
			}
			
			return true;
		}
		return false;
	}

	@Override
	public void addPages() {
		// TODO Auto-generated method stub
		selectConnectorWizardPage = new SelectConnectorWizardPage(this);
		chooseConnectorFileToEditWizardPage = new ChooseConnectorFileToEditWizardPage(null);
		addPage(selectConnectorWizardPage);
		addPage(chooseConnectorFileToEditWizardPage);
		super.addPages();
	}

	public ChooseConnectorFileToEditWizardPage getChooseConnectorFileToEditWizardPage() {
		return chooseConnectorFileToEditWizardPage;
	}

	public void setChooseConnectorFileToEditWizardPage(ChooseConnectorFileToEditWizardPage chooseConnectorFileToEditWizardPage) {
		this.chooseConnectorFileToEditWizardPage = chooseConnectorFileToEditWizardPage;
	}

}
