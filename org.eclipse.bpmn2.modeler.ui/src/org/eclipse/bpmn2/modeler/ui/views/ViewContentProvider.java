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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.LaneSet;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.modeler.core.ModelHandler;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.ui.internal.parts.ContainerShapeEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IViewSite;

class ViewContentProvider implements IStructuredContentProvider, ITreeContentProvider {
	private TreeParent invisibleRoot;

	@Override
	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public Object[] getElements(Object parent) {
		if (parent instanceof IViewSite) {
			if (invisibleRoot == null) {
				initialize();
			}
			return getChildren(invisibleRoot);
		}
		return getChildren(parent);
	}

	@Override
	public Object getParent(Object child) {
		if (child instanceof TreeObject) {
			return ((TreeObject) child).getParent();
		}
		return null;
	}

	@Override
	public Object[] getChildren(Object parent) {
		if (parent instanceof TreeParent) {
			return ((TreeParent) parent).getChildren();
		}
		return new Object[0];
	}

	@Override
	public boolean hasChildren(Object parent) {
		if (parent instanceof TreeParent) {
			return ((TreeParent) parent).hasChildren();
		}
		return false;
	}

	/*
	 * We will set up a dummy model to initialize tree heararchy. In a real code, you will connect to a real model and
	 * expose its hierarchy.
	 */
	private void initialize() {
		invisibleRoot = new TreeParent("");
	}

	void updateModel(ModelHandler mh) {
		invisibleRoot.removeChildren();
		if (mh == null) {
			return;
		}

		Definitions definitions = mh.getDefinitions();

		List<RootElement> rootElements = definitions.getRootElements();
		for (RootElement element : rootElements) {

			if (element instanceof Process) {
				Process process = (Process) element;
				TreeParent proc = new TreeParent("Process");

				createLaneSets(proc, process.getLaneSets());
				createFlowElementTree(proc, process.getFlowElements());
				invisibleRoot.addChild(proc);
			}
		}

	}

	private void createLaneSets(TreeParent proc, List<LaneSet> laneSets) {
		for (LaneSet laneSet : laneSets) {
			createLaneSetTree(proc, laneSet);

		}
	}

	private void createLaneSetTree(TreeParent proc, LaneSet laneSet) {
		if (laneSet == null) {
			return;
		}
		for (Lane lane : laneSet.getLanes()) {
			TreeParent parent = new TreeParent(lane);
			proc.addChild(parent);
			createLaneSetTree(parent, lane.getChildLaneSet());
		}
	}

	private void createFlowElementTree(TreeParent proc, List<FlowElement> flowElements) {
		for (FlowElement f : flowElements) {
			if (!(f instanceof SequenceFlow)) {
				TreeObject treeObject = new TreeObject(f);
				proc.addChild(treeObject);
			}
		}
	}

	@SuppressWarnings("restriction")
	public Object[] getSelected(ISelection selection) {
		if (selection instanceof StructuredSelection) {
			StructuredSelection sel = (StructuredSelection) selection;
			List<Object> selected = Arrays.asList(sel.toArray());
			if (selected.size() == 0 || !(selected.get(0) instanceof ContainerShapeEditPart)) {
				return null;
			}

			PictogramLink link = ((ContainerShapeEditPart) selected.get(0)).getPictogramElement().getLink();
			if (link == null) {
				return null;
			}

			EList<EObject> businessObjects = link.getBusinessObjects();
			TreeObject[] children = invisibleRoot.getChildren();
			ArrayList<TreeObject> list = getSelectionFromList(businessObjects, children);
			return list.toArray();
		}
		return null;
	}

	private ArrayList<TreeObject> getSelectionFromList(EList<EObject> businessObjects, TreeObject[] children) {
		ArrayList<TreeObject> list = new ArrayList<TreeObject>();
		for (TreeObject treeObject : children) {
			if (treeObject instanceof TreeParent) {
				if (businessObjects.contains(treeObject.getBaseElement())) {
					list.add(treeObject);
				}
				list.addAll(getSelectionFromList(businessObjects, ((TreeParent) treeObject).getChildren()));
			} else if (businessObjects.contains(treeObject.getBaseElement())) {
				list.add(treeObject);
			}
		}
		return list;
	}
}