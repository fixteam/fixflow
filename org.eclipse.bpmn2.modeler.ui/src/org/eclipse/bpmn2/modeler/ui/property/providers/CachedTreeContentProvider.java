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

import java.util.HashMap;
import java.util.Vector;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * An abstract content provider that caches the tree nodes as they are discovered.
 * 
 * This is useful for two reasons: it guarantees that the same object will be returned
 * until the cache is cleared (which is convenient for trees that wrap a different set
 * of model objects), and it handles the parent relationship automatically.
 * 
 * Note that the parent of a node will only be known if the node appeared in an array
 * previously returned by primGetChildren()!  The value UNKNOWN_PARENT is returned
 * as the parent of a node whose parent has never had primGetChildren() called on it.
 * null is returned as the parent of the root nodes, that is, those returned by
 * primGetElements().
 */
public abstract class CachedTreeContentProvider implements ITreeContentProvider {

	protected static final Object[] EMPTY_ARRAY = new Object[0];

	public static final Object UNKNOWN_PARENT = new Object();

	// A null value in the treeNodeToParent parentMap means the node is a root parameter. 
	protected HashMap treeNodeToParent;

	// A null value in the treeNodeToChildren parentMap means the node has children, button
	// we don't yet know what they are.  (If it has no children, an empty array would
	// be present instead).
	protected HashMap treeNodeToChildren;

	Object[] rootChildren;

	public CachedTreeContentProvider() {
		super();
		treeNodeToParent = new HashMap();
		treeNodeToChildren = new HashMap();
	}

	protected abstract boolean primHasChildren(Object node);
	protected abstract Object[] primGetChildren(Object node);
	protected abstract Object[] primGetElements(Object node);

	/**
	 * Returns an array describing the path from a node up to the root.  The node will
	 * be the first parameter of the array, its parent will be the 2nd, and so on, with
	 * a top-level node (i.e. a root) as the last parameter of the array.  Note that the
	 * input object of the content provider is *NOT* included in this path, because the
	 * top-level nodes returned by getElements() have a parent value of null.
	 */
	public Object[] getPathToRoot(Object node) {
		Vector v = new Vector();
		while (node != null && node != UNKNOWN_PARENT) {
			v.add(node);
			node = getParent(node);
		}
		return v.toArray();
	}

	/* IStructuredContentProvider */

	public final Object[] getElements(Object node) {
		if (rootChildren == null)  {
			rootChildren = primGetElements(node);
			if (rootChildren == null)  rootChildren = EMPTY_ARRAY;
			for (int i = 0; i<rootChildren.length; i++) {
				treeNodeToParent.put(rootChildren[i], null);
			}
		}
		return rootChildren;
	}

	/* ITreeContentProvider */

	public final boolean hasChildren(Object node) {
		Object[] result = (Object[])treeNodeToChildren.get(node);
		if (result != null) return (result.length > 0);
		if (treeNodeToChildren.containsKey(node)) {
			// it has children, we just haven't seen them yet.
			return true; 
		}
		if (primHasChildren(node)) {
			treeNodeToChildren.put(node, null);
			return true;
		}

		treeNodeToChildren.put(node, EMPTY_ARRAY);
		return false;
	}

	public final Object[] getChildren(Object node) {
		Object[] result = (Object[])treeNodeToChildren.get(node);
		if (result == null) {
			result = primGetChildren(node);
			if (result == null)  result = EMPTY_ARRAY;
			treeNodeToChildren.put(node, result);
			for (int i = 0; i<result.length; i++) {
				treeNodeToParent.put(result[i], node);
			}
		}
		return result;
	}

	public final Object getParent(Object node) {
		// Prevent stack overflow in cases where a caller is walking up the
		// parent chain of a stale object and doesn't recognize UNKNOWN_PARENT.
		// (e.g. in a StructuredViewer, internalExpand() will keep asking for
		// the parent of UNKNOWN_PARENT until stack overflow).   
		if (node==UNKNOWN_PARENT) return null;

		Object result = treeNodeToParent.get(node);
		if (result != null || treeNodeToParent.containsKey(node))  return result;
		return UNKNOWN_PARENT;
	}

	/* IContentProvider */

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		rootChildren = null;
		treeNodeToParent.clear();
		treeNodeToChildren.clear();
	}

}
