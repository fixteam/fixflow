/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpmn2.modeler.ui.property.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.DrillDownComposite;

public class FileSelectionGroup extends Composite {

	// The listener to notify of events
	private Listener listener;

	private String message;

	// Last selection made by user
	private IResource selectedResource;

	// handle on parts
	private TreeViewer treeViewer;

	// Filters
	private ViewerFileFilter viewerFileFilter;
	//	private ViewerFileFilterMatcher viewerFileFilterMatcher
	private String filterPatterns;
	// sizing constants
	private static final int SIZING_SELECTION_PANE_HEIGHT = 175;
	private static final int SIZING_SELECTION_PANE_WIDTH = 200;
	/**
	 * Creates a new instance of the widget.
	 */
	public FileSelectionGroup(
		Composite parent,
		Listener listener,
		String message) {
		this(parent, listener, message, null);
	}
	public FileSelectionGroup(
		Composite parent,
		Listener listener,
		String message,
		String filterPatterns) {
		super(parent, SWT.NONE);

		this.filterPatterns = filterPatterns;
		this.listener = listener;
		this.message = message;
		this.setFont(parent.getFont());

		createViewerFileFilter();
		createContents();
	}
	public void setFileFilter(String filter) {
		filterPatterns = filter;
		createViewerFileFilter();
		treeViewer.setFilters(new ViewerFilter[] {viewerFileFilter});
		treeViewer.setInput(ResourcesPlugin.getWorkspace());
	}
	/**
	 * Creates ViewerFileFilter using array of objects.
	 */
	public void createViewerFileFilter() {
		if (filterPatterns != null) {
			viewerFileFilter = new ViewerFileFilter(filterPatterns);
		}
	}
	/**
	 * Creates the contents of the composite.
	 */
	public void createContents() {
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		setLayout(layout);
		setLayoutData(new GridData(GridData.FILL_BOTH));

		Label label = new Label(this, SWT.WRAP);
		label.setText(message != null ? message : ""); //$NON-NLS-1$
		label.setFont(this.getFont());

		createTreeViewer();
	}
	/**
	 * Returns a new drill down viewer for this dialog.
	 */
	protected void createTreeViewer() {

		// Create drill down.
		DrillDownComposite drillDown = new DrillDownComposite(this, SWT.BORDER);
		GridData spec =
			new GridData(
				GridData.VERTICAL_ALIGN_FILL
					| GridData.HORIZONTAL_ALIGN_FILL
					| GridData.GRAB_HORIZONTAL
					| GridData.GRAB_VERTICAL);
		spec.widthHint = SIZING_SELECTION_PANE_WIDTH;
		spec.heightHint = SIZING_SELECTION_PANE_HEIGHT;
		drillDown.setLayoutData(spec);

		// Create tree viewer inside drill down.
		treeViewer = new TreeViewer(drillDown, SWT.NONE);
		drillDown.setChildTree(treeViewer);
		treeViewer.setContentProvider(new WorkbenchContentProvider());
		treeViewer.setLabelProvider(new WorkbenchLabelProvider());
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				resourceSelectionChanged((IResource) selection.getFirstElement());
				// allow null
			}
		});
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				ISelection selection = event.getSelection();
				if (selection instanceof IStructuredSelection) {
					Object item = ((IStructuredSelection) selection).getFirstElement();
					if (treeViewer.getExpandedState(item))
						treeViewer.collapseToLevel(item, 1);
					else
						treeViewer.expandToLevel(item, 1);
				}
			}
		});

		if (viewerFileFilter != null)
			treeViewer.addFilter(viewerFileFilter);
		// This has to be done after the viewer has been laid out
		treeViewer.setInput(ResourcesPlugin.getWorkspace());
	}
	/**
	 * Returns the "full path" (i.e. first segment is project name) of the currently selected file.
	 */
	public IPath getResourceFullPath() {
		if (selectedResource == null) return null;
		return selectedResource.getFullPath();
	}
	
	
	public IResource getSelectedResource () {
		return selectedResource;
	}
	
	
	/**
	 * Returns the tree viewer.
	 */
	public TreeViewer getTreeViewer() {
		return treeViewer;
	}
	/**
	 * The file selection has changed in the
	 * tree view. Update the file name field
	 * value and notify all listeners.
	 */
	public void resourceSelectionChanged(IResource resource) {
		selectedResource = resource;

		// fire an event so the parent can update its controls
		if (listener != null) {
			Event changeEvent = new Event();
			changeEvent.type = SWT.Selection;
			changeEvent.widget = this;
			listener.handleEvent(changeEvent);
		}
	}
	/**
	 * Gives focus to one of the widgets in the group, as determined by the group.
	 */
	public void setInitialFocus() {
		treeViewer.getTree().setFocus();
	}
	/**
	 * Sets the selected existing file.
	 */
	public void setSelectedResource(IResource resource) {
		// https://issues.jboss.org/browse/JBIDE-8738
		if (resource==null)
			return;
		selectedResource = resource;

		//expand to and select the specified file
		List itemsToExpand = new ArrayList();
		IContainer parent = resource.getParent();
		while (parent != null) {
			itemsToExpand.add(0, parent);
			parent = parent.getParent();
		}
		treeViewer.setExpandedElements(itemsToExpand.toArray());
		treeViewer.setSelection(new StructuredSelection(resource), true);
	}
}
