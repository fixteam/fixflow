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
package org.eclipse.bpmn2.modeler.ui.property.providers;

import org.eclipse.jface.viewers.Viewer;


/**
 * A content provider for a tree of ITreeNode objects representing some expansion of
 * the underlying graph of model objects.  CachedTreeContentProvider keeps track of the
 * parent relationships so the ITreeNode objects don't have to; however, the ITreeNode
 * objects *must have unique parents*, so our model objects can't represent themselves
 * directly.  After all, our model is a graph, not a tree.  Another reason to wrap our
 * model objects in ITreeNode objects is that there may be different tree expansions
 * of a particular model object in different contexts (e.g. variable/property versus
 * variable/part/query).
 * 
 * To programatically expose a particular branch of the tree, you must first walk down
 * the content provider from the root to the ITreeNode you want to expose (causing the
 * necessary ITreeNode objects to be created).  Then, you call viewer.expandToLevel()
 * on this ITreeNode.  Using this content provider, the TreeViewer will walk up from
 * the given node to the deepest already-expanded item, then back down again expanding
 * the remaining items.
 */
public class ModelTreeContentProvider extends CachedTreeContentProvider {

	protected boolean isCondensed;

	public ModelTreeContentProvider(boolean isCondensed) {
		super();
		this.isCondensed = isCondensed;
	}

	public boolean isCondensed() { return isCondensed; }

	@Override
	protected Object[] primGetChildren(Object node) {
		if (!(node instanceof ITreeNode)) return EMPTY_ARRAY;
		return ((ITreeNode)node).getChildren();
	}

	@Override
	protected Object[] primGetElements(Object node) {
		return primGetChildren(node);
	}

	@Override
	protected boolean primHasChildren(Object node) {
		if (!(node instanceof ITreeNode)) return false;
		return ((ITreeNode)node).hasChildren();
	}

	/**
	 * Searches each of the nodes in the given array to find the node representing a
	 * particular modelObject.
	 * 
	 * If subtreeDepth > 0, the children of each node in the given array are recursively
	 * searched to a depth of subtreeDepth-1.
	 * 
	 * Values larger than 1 or 2 should be avoided whenever possible, because all of the
	 * nodes in the subtree up to subtreeDepth have to be created and cached.  The most
	 * efficient subtreeDepth is 0.
	 */
	public ITreeNode findModelNode(Object[] nodes, Object modelObject, int subtreeDepth) {
		for (int i = 0; i<nodes.length; i++)  {
			if (!(nodes[i] instanceof ITreeNode)) continue;
			ITreeNode treeNode = (ITreeNode)nodes[i];
			if (treeNode.getModelObject().equals(modelObject))  return treeNode;
		}
		ITreeNode result = null;
		if (subtreeDepth > 0) {
			for (int i = 0; result==null && i<nodes.length; i++)  {
				result = findModelNode(getChildren(nodes[i]), modelObject, subtreeDepth-1);
			}
		}
		return result;
	}

	/* IContentProvider */

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		super.inputChanged(viewer, oldInput, newInput);
		// TODO: hook model listener?
	}
}
