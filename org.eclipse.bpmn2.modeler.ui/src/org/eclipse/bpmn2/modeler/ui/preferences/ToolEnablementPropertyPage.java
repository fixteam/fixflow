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
 * @author Innar Made
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.ui.preferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.bpmn2.modeler.core.preferences.Bpmn2Preferences;
import org.eclipse.bpmn2.modeler.core.preferences.ToolEnablement;
import org.eclipse.bpmn2.modeler.core.preferences.ToolEnablementPreferences;
import org.eclipse.bpmn2.modeler.core.runtime.ModelEnablementDescriptor;
import org.eclipse.bpmn2.modeler.core.runtime.TargetRuntime;
import org.eclipse.bpmn2.modeler.ui.Activator;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.IElementComparer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PropertyPage;
import org.osgi.service.prefs.BackingStoreException;

public class ToolEnablementPropertyPage extends PropertyPage {

	private DataBindingContext m_bindingContext;

	private ToolEnablementPreferences toolEnablementPreferences;
	private Bpmn2Preferences bpmn2Preferences;
	private final List<ToolEnablement> tools = new ArrayList<ToolEnablement>();
	private Object[] toolsEnabled;
	private CheckboxTreeViewer checkboxTreeViewer;
	private Tree checkboxTree;

	private WritableList writableList;

	/**
	 * Create the property page.
	 */
	public ToolEnablementPropertyPage() {
		setTitle("Tool Enablement");
	}

	/**
	 * Create contents of the property page.
	 * 
	 * @param parent
	 */
	@Override
	public Control createContents(Composite parent) {
		initData();

		Composite container = new Composite(parent, SWT.NULL);
		container.setLayout(new GridLayout(3, false));
		container.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, true, false, 1, 1));
		
		final Button btnOverride = new Button(container,SWT.CHECK);
		btnOverride.setText("Override default tool enablements with these settings:");
		btnOverride.setSelection(bpmn2Preferences.getOverrideModelEnablements());
		btnOverride.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, false, 3, 1));

		checkboxTreeViewer = new CheckboxTreeViewer(container, SWT.BORDER);
		checkboxTree = checkboxTreeViewer.getTree();
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1);
		data.heightHint = 200;
		checkboxTree.setLayoutData(data);

		final Button btnCopy = new Button(container,SWT.FLAT);
		btnCopy.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		btnCopy.setText("Copy");

		final Label lblCopy = new Label(container, SWT.NONE);
		lblCopy.setText("all enablements from Target Runtime:");
		lblCopy.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));

		final Combo cboCopy = new Combo(container, SWT.READ_ONLY);
		cboCopy.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		TargetRuntime cr = bpmn2Preferences.getRuntime();
		int i = 0;
		for (TargetRuntime rt : TargetRuntime.getAllRuntimes()) {
			for (ModelEnablementDescriptor md : rt.getModelEnablements()) {
				String text = rt.getName();
				if (md.getType()!=null)
					text += " - " + md.getType();
				cboCopy.add(text);
				cboCopy.setData(Integer.toString(i), md);
				if (rt == cr)
					cboCopy.select(i);
				++i;
			}
		}

		Composite importExportButtons = new Composite(container, SWT.NONE);
		importExportButtons.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 3, 1));
		importExportButtons.setLayout(new FillLayout());

		Button btnImportProfile = new Button(importExportButtons, SWT.NONE);
		btnImportProfile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), SWT.NULL);
				String path = dialog.open();
				if (path != null) {
					try {
						tools.clear();
						toolEnablementPreferences.importPreferences(path);
						reloadPreferences();
						checkboxTreeViewer.refresh();
						restoreDefaults();
					} catch (Exception e1) {
						Activator.showErrorWithLogging(e1);
					}
				}
			}
		});
		btnImportProfile.setText("Import Profile ...");

		Button btnExportProfile = new Button(importExportButtons, SWT.NONE);
		btnExportProfile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), SWT.SAVE);
				String path = dialog.open();
				if (path != null) {
					try {
						toolEnablementPreferences.export(path);
					} catch (Exception e1) {
						Activator.showErrorWithLogging(e1);
					}
				}
			}
		});
		btnExportProfile.setText("Export Profile ...");

		checkboxTreeViewer.setComparer(new IElementComparer() {

			@Override
			public boolean equals(Object a, Object b) {
				return a == b;
			}

			@Override
			public int hashCode(Object element) {
				return System.identityHashCode(element);
			}
		});
		checkboxTreeViewer.setUseHashlookup(true);
		m_bindingContext = initDataBindings();
		
		boolean enable = btnOverride.getSelection();
		checkboxTree.setEnabled(enable);
		btnCopy.setEnabled(enable);
		lblCopy.setEnabled(enable);
		cboCopy.setEnabled(enable);

		btnOverride.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean enable = btnOverride.getSelection();
				checkboxTree.setEnabled(enable);
				bpmn2Preferences.setOverrideModelEnablements(enable);
				btnCopy.setEnabled(enable);
				lblCopy.setEnabled(enable);
				cboCopy.setEnabled(enable);
			}
		});
		
		btnCopy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = cboCopy.getSelectionIndex();
				ModelEnablementDescriptor md = (ModelEnablementDescriptor) cboCopy.getData(Integer.toString(i)); 
				toolEnablementPreferences.setEnablements(md);
				
				reloadPreferences();
				checkboxTreeViewer.refresh();
				restoreDefaults();
			}
		});


		restoreDefaults();

		return container;
	}

	private void restoreDefaults() {
		checkboxTreeViewer.setCheckedElements(toolsEnabled);
	}

	@Override
	protected void performDefaults() {
		super.performDefaults();
		restoreDefaults();
	}

	private void initData() {
		toolEnablementPreferences = ToolEnablementPreferences.getPreferences((IProject) getElement().getAdapter(IProject.class));
		IProject project = (IProject)getElement().getAdapter(IProject.class);
		bpmn2Preferences = Bpmn2Preferences.getInstance(project);

		reloadPreferences();
	}

	private void reloadPreferences() {
		tools.clear();
		tools.addAll(toolEnablementPreferences.getAllElements());
		ArrayList<ToolEnablement> tEnabled = new ArrayList<ToolEnablement>();
		for (ToolEnablement tool : tools) {
			if (tool.getEnabled()) {
				tEnabled.add(tool);
			}
			ArrayList<ToolEnablement> children = tool.getChildren();
			for (ToolEnablement t : children) {
				if (t.getEnabled()) {
					tEnabled.add(t);
				}
			}
		}
		toolsEnabled = tEnabled.toArray();
	}

	@Override
	public boolean performOk() {
		setErrorMessage(null);
		try {
			updateToolEnablement(tools, Arrays.asList(checkboxTreeViewer.getCheckedElements()));
			bpmn2Preferences.save();
		} catch (BackingStoreException e) {
			Activator.showErrorWithLogging(e);
		}
		return true;
	}

	private void updateToolEnablement(List<ToolEnablement> saveables, List<Object> enabled)
			throws BackingStoreException {
		for (ToolEnablement t : saveables) {
			toolEnablementPreferences.setEnabled(t, enabled.contains(t));
			for (ToolEnablement c : t.getChildren()) {
				toolEnablementPreferences.setEnabled(c, enabled.contains(c));
			}
		}
		toolEnablementPreferences.flush();
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		checkboxTreeViewer.setContentProvider(new ITreeContentProvider() {

			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			}

			@Override
			public void dispose() {
			}

			@Override
			public boolean hasChildren(Object element) {
				if (element instanceof ToolEnablement) {
					return !((ToolEnablement) element).getChildren().isEmpty();
				}
				return false;
			}

			@Override
			public Object getParent(Object element) {
				if (element instanceof ToolEnablement) {
					return ((ToolEnablement) element).getParent();
				}
				return null;
			}

			@Override
			public Object[] getElements(Object inputElement) {
				if (inputElement instanceof WritableList) {
					return ((WritableList) inputElement).toArray();
				}
				return null;
			}

			@Override
			public Object[] getChildren(Object parentElement) {
				if (parentElement instanceof ToolEnablement) {
					return ((ToolEnablement) parentElement).getChildren().toArray();
				}
				return null;
			}
		});

		checkboxTreeViewer.setLabelProvider(new ILabelProvider() {
			@Override
			public void removeListener(ILabelProviderListener listener) {
			}

			@Override
			public boolean isLabelProperty(Object element, String property) {
				return false;
			}

			@Override
			public void dispose() {

			}

			@Override
			public void addListener(ILabelProviderListener listener) {
			}

			@Override
			public Image getImage(Object element) {
				return null;
			}

			@Override
			public String getText(Object element) {
				if (element instanceof ToolEnablement) {
					return ((ToolEnablement) element).getName();
				}
				return null;
			}
		});
		writableList = new WritableList(tools, ToolEnablement.class);
		checkboxTreeViewer.setInput(writableList);
		//
		return bindingContext;
	}

}
