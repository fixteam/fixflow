/*******************************************************************************
 * Copyright (c) 2011 Red Hat, Inc.
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 *
 * @author Bob Brodt
 ******************************************************************************/

package org.eclipse.bpmn2.modeler.ui.property.dialogs;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Import;
import org.eclipse.bpmn2.modeler.core.ModelHandler;
import org.eclipse.bpmn2.modeler.core.model.Bpmn2ModelerResourceSetImpl;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.ui.Activator;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.bpmn2.modeler.ui.property.providers.ModelTreeLabelProvider;
import org.eclipse.bpmn2.modeler.ui.property.providers.ServiceTreeContentProvider;
import org.eclipse.bpmn2.modeler.ui.property.providers.TreeNode;
import org.eclipse.bpmn2.modeler.ui.property.providers.VariableTypeTreeContentProvider;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.dialogs.SelectionStatusDialog;

public class SchemaSelectionDialog extends SelectionStatusDialog {

	protected BPMN2Editor bpmn2Editor;
	protected EObject modelObject;
	protected Object input; // an XSDSchema or WSDL Definition
	protected List importList;
	protected Tree tree;
	protected TreeViewer treeViewer;
	protected ITreeContentProvider treeContentProvider;
	protected Bpmn2ModelerResourceSetImpl hackedResourceSet;
	protected String importType;
	protected String importLocation;
	protected Job loaderJob;
	private String selectionPath;
	
	/**
	 * @param parent
	 * @param structureRefObjectEditor TODO
	 */
	public SchemaSelectionDialog(Shell parent, EObject object) {
		super(parent);
		setShellStyle(getShellStyle() | SWT.RESIZE | SWT.MAX);

		modelObject = object;
		bpmn2Editor = BPMN2Editor.getActiveEditor();
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Schema Selection");
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite contents = (Composite) super.createDialogArea(parent);

		GridLayout contentsGridLayout = (GridLayout) contents.getLayout();
		contentsGridLayout.numColumns = 2;
		contentsGridLayout.makeColumnsEqualWidth = false;

		createImportList(contents);
		createImportStructure(contents);

		return contents;
	}

	protected Object createImportList(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText("Imports");
		label.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
		
		Button addImport = new Button(parent, SWT.PUSH);
		addImport.setText("Add new Import");
		addImport.setLayoutData(new GridData(SWT.RIGHT,SWT.FILL,true,true,1,1));
		addImport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SchemaImportDialog dialog = new SchemaImportDialog(getShell(), modelObject);
				if (dialog.open() == Window.OK) {
					Object result[] = dialog.getResult();
					if (result.length == 1) {
						Import imp = ModelHandler.addImport(bpmn2Editor.getModelHandler().getResource(), result[0]);
						if (imp!=null) {
							int index = importList.getItemCount();
							importList.add(imp.getLocation());
							importList.setData(""+index,imp);
						}
					}
				}
			}
		});
		
		importList = new List(parent, SWT.SINGLE | SWT.BORDER);
		GridData data = new GridData(SWT.FILL,SWT.FILL,true,true,2,1);
		data.minimumHeight = 100;
		data.minimumWidth = 400;
		importList.setLayoutData(data);
		
		int index = 0;
		for (Import imp : getImports()) {
			importList.add(imp.getLocation());
			importList.setData(""+index++, imp);
		}
		importList.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = importList.getSelectionIndex();
				Import imp = (Import)importList.getData(""+index);
				importType = getImportType(imp);
				importLocation = imp.getLocation();
				
				attemptLoad();
				updateOK(false);
			}
		});
		return importList;
	}
	
	protected Object createImportStructure(Composite parent) {

		Label label = new Label(parent, SWT.NONE);
		label.setText("Structure");

		// Tree viewer for variable structure
		tree = new Tree(parent, SWT.BORDER | SWT.SINGLE);

		treeViewer = new TreeViewer(tree);
		treeViewer.setLabelProvider(new ModelTreeLabelProvider());
		treeViewer.setAutoExpandLevel(3);

		GridData data = new GridData(SWT.FILL,SWT.FILL,true,true,2,1);
		data.minimumHeight = 200;
		tree.setLayoutData(data);
		tree.getVerticalBar().setSelection(0);
		
		tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] sel = tree.getSelection();
				if (sel.length==1) {
					Object data = sel[0].getData();
					if (data instanceof TreeNode) {
						TreeNode tn = (TreeNode)data;
						Object[] result = new Object[] {tn.getModelObject()};
						setSelectionResult(result);
						setSelectionPath(sel[0]);
						updateOK(true);
					}
					else
						updateOK(false);
				}
			}
		});

		return tree;
	}
	
	protected void setSelectionPath(TreeItem sel) {
		selectionPath = buildSelectionPath(sel);
	}

	public String getSelectionPath() {
		return selectionPath;
	}
	
	private String buildSelectionPath(TreeItem sel) {
		String path = "";
		TreeItem parent = sel.getParentItem();
		if (parent!=null) {
			path += buildSelectionPath(parent);
		
			Object data = sel.getData();
			if (data instanceof TreeNode) {
				TreeNode tn = (TreeNode)data;
				path += "/" + tn.getLabel();
			}
		}
		else
			path = ""; // this is the tree root
		
		return path;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.dialogs.SelectionStatusDialog#computeResult()
	 */
	@Override
	protected void computeResult() {
	}

	void attemptLoad() {
		String path = null;
		if ("xsd".equals(importType)) {
			treeContentProvider = new VariableTypeTreeContentProvider(true, true);
			path = importLocation;
		} else if ("wsdl".equals(importType)) {
			treeContentProvider = new ServiceTreeContentProvider(true);
			path = importLocation;
		} else {
			treeContentProvider = null;
			input = null;
		}

		if (loaderJob != null) {
			if (loaderJob.getState() == Job.RUNNING) {
				loaderJob.cancel();
			}
		}

		// empty paths are ignored
		if (path.length() == 0) {
			return;
		}

		URI uri = URI.createURI(path);
		if (uri == null) {
			return ;
		}
		if (uri.isRelative())
			uri = URI.createFileURI( path );
		if (uri == null) {
			return ;
		}
		if (uri.isRelative()) {
			// construct absolute path
			String basePath = bpmn2Editor.getModelFile().getLocation().removeLastSegments(1).toString();
			uri = URI.createFileURI( basePath + "/" + path );
		}

		final URI loadUri = uri;
		loaderJob = new Job("") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					Thread.sleep(500);
					tree.getDisplay().asyncExec(new Runnable() {
						public void run() {
							input = attemptLoad(loadUri, importType);
							loadDone();
						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				return Status.OK_STATUS;
			}
		};

		loaderJob.schedule();
	}

	Object attemptLoad(URI uri, String kind) {

		Resource resource = null;
		try {
			hackedResourceSet = ModelUtil.slightlyHackedResourceSet(bpmn2Editor.getResourceSet());
			resource = hackedResourceSet.getResource(uri, true, kind);
		} catch (Exception e) {
			return e;
		}

		if (resource != null && resource.getErrors().isEmpty() && resource.isLoaded()) {
			return resource.getContents().get(0);
		}
		return null;
	}

	@SuppressWarnings("boxing")
	void loadDone() {

		if (input == null || input instanceof Exception) {
			updateStatus(new Status(IStatus.ERROR, Activator.getDefault().PLUGIN_ID, 0,
					"Cannot load "+importLocation, (Throwable)input));
//			treeViewer.setInput(null);
			input = null;

		} else {
			treeViewer.setContentProvider(treeContentProvider);
			treeViewer.setInput(input);
			tree.getVerticalBar().setSelection(0);
			updateStatus(new Status(IStatus.OK, Activator.getDefault().PLUGIN_ID, 0,
					"Loaded "+importLocation, null));
		}
	}

	public void updateOK(boolean state) {
		Button okButton = getOkButton();
		if (okButton != null && !okButton.isDisposed()) {
			okButton.setEnabled(state);
		}
	}

	public Definitions getDefinitions() {
		return bpmn2Editor.getModelHandler().getDefinitions();
	}
	
	public java.util.List<Import> getImports() {
		return getDefinitions().getImports();
	}

	String getImportType(Import imp) {
		if (imp != null) {
			String type = imp.getImportType();
			if ("http://schemas.xmlsoap.org/wsdl/".equals(type))
				return "wsdl";
			if ("http://www.w3.org/2001/XMLSchema".equals(type))
				return "xsd";
		}
		return null;
	}
}