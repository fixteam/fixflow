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
package org.eclipse.bpmn2.modeler.ui.views;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

/**
 * This sample class demonstrates how to plug-in a new workbench view. The view shows data obtained from the model. The
 * sample creates a dummy model on the fly, but a real implementation would connect to the model available either in
 * this or another plug-in (e.g. the workspace). The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model objects should be presented in the view. Each view can present the
 * same model objects using different labels and icons, if needed. Alternatively, a single label provider can be shared
 * between views in order to ensure that objects of the same description are presented in the same way everywhere.
 * <p>
 */

public class BPMN2ModelView extends ViewPart {

	private final class BpmnModelDragSourceLisener extends DragSourceAdapter {
		@Override
		public void dragStart(DragSourceEvent event) {
			if (((IStructuredSelection) viewer.getSelection()).getFirstElement() instanceof TreeObject) {

				IStructuredSelection iStructuredSelection = (IStructuredSelection) viewer.getSelection();
				TreeObject treeObject = (TreeObject) iStructuredSelection.getFirstElement();
				BaseElement baseElement = treeObject.getBaseElement();
				LocalSelectionTransfer.getTransfer().setSelection(new StructuredSelection(baseElement));
			}

		}
	}

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.eclipse.bpmn2.modeler.ui.views.BPMN2ModelView";

	private TreeViewer viewer;
	private ISelectionListener listener;

	/**
	 * The constructor.
	 */
	public BPMN2ModelView() {
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);

		final ViewContentProvider contentProvider = new ViewContentProvider();
		viewer.setContentProvider(contentProvider);
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setInput(getViewSite());

		viewer.addDragSupport(DND.DROP_MOVE | DND.DROP_COPY, new Transfer[] { LocalSelectionTransfer.getTransfer() },
				new BpmnModelDragSourceLisener());

		listener = new BpmnModelViewerSelectionListener(viewer);
		getSite().getPage().addSelectionListener(listener);

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "org.eclipse.bpmn2.modeler.ui.viewer");
	}

	@Override
	public void dispose() {
		getSite().getPage().removeSelectionListener(listener);
		super.dispose();
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}