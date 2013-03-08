/**
 * Copyright c FOUNDER CORPORATION 2011 All Rights Reserved.
 * TreeViewerContentProvider.java
 */
package com.founder.fix.fixflow.designer.modeler.ui.property.connectors.tree;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * [类名]<br>
 * TreeViewerContentProvider.java<br>
 * <br>
 * [功能概要]<br>
 *
 * <br>
 * [变更履历]<br>
 *
 * <br>
 * 2011-6-20 ver1.0 <br>
 * <br>
 *
 * @作者 wangzhiwei
 *
 */
public class TreeViewerContentProvider implements ITreeContentProvider {

	/**
	 * 
	 */
	public TreeViewerContentProvider() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		if(inputElement instanceof List) {
			List<ITreeElement> list = (List<ITreeElement>) inputElement;
			if(list != null && list.size() > 0) {
				return list.toArray();
			}
		} else {
			return new Object[0];
		}
		return new Object[0];
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	public Object[] getChildren(Object parentElement) {
		ITreeElement tElement = (ITreeElement) parentElement;
		List<ITreeElement> list = tElement.getChildren();
		if(list != null && list.size() > 0) {
			return list.toArray();
		}
		return new Object[0];
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object element) {
		ITreeElement tElement = (ITreeElement) element;
		List<ITreeElement> list = tElement.getChildren();
		if(list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

}
