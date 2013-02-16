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

package org.eclipse.bpmn2.modeler.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Bob Brodt
 *
 */
public class BPMN2ResourceChangeListener implements IResourceChangeListener {

	class ResourceDeltaVisitor implements IResourceDeltaVisitor {

		public boolean visit(final IResourceDelta delta) throws CoreException {
			IResource target = delta.getResource();
//			switch (delta.getKind()) {
//			case IResourceDelta.ADDED:
//				System.out.println(target.getFullPath()+" ADDED");
//				break;
//			case IResourceDelta.CHANGED:
//				System.out.println(target.getFullPath()+" CHANGED");
//				break;
//			case IResourceDelta.REMOVED:
//				System.out.println(target.getFullPath()+" REMOVED");
//				break;
//			case IResourceDelta.ADDED_PHANTOM:
//				System.out.println(target.getFullPath()+" ADDED_PHANTOM");
//				break;
//			}
			if (target.getType() == IResource.FILE) {
				handleFile(delta);
			}
			return true;
		}

		private void handleFile(final IResourceDelta delta)
			throws CoreException {
			IWorkspaceRunnable runnable = new IWorkspaceRunnable() {
				public void run(IProgressMonitor monitor)
					throws CoreException {
					IFile target = (IFile) delta.getResource();
					int flags = delta.getFlags();
					
					switch (delta.getKind()) {
					case IResourceDelta.ADDED:
						if ((flags & IResourceDelta.MOVED_FROM) != 0) {
							if (target.exists())
								fileMoved(delta.getMovedFromPath(), target.getFullPath());
						}
						break;
					case IResourceDelta.REMOVED:
						if ((flags & IResourceDelta.MOVED_TO) != 0) {
							if (target.exists())
								fileMoved(target.getFullPath(), delta.getMovedToPath());
						} else {
							fileDeleted(target.getFullPath(), monitor);
						}
						break;
					}
				}
			};
			ResourcesPlugin.getWorkspace().run(runnable, null);
		}
	}

	protected IResourceDeltaVisitor visitor;
	protected List<IFileChangeListener> listeners;

	public BPMN2ResourceChangeListener() {
		listeners = new ArrayList<IFileChangeListener>();
	}

	/**
	 * Objects like the BPELEditor can add listeners so they can be
	 * notified and react when BPEL files change.
	 */
	public void addListener(IFileChangeListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Removed the listener.
	 */
	public void removeListener(IFileChangeListener listener) {
		listeners.remove(listener);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
	 */
	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		try {
			event.getDelta().accept(getResourceDeltaVisitor());
		} catch (CoreException e) {
			Activator.logError(e);
		}
	}

	protected IResourceDeltaVisitor getResourceDeltaVisitor() {
		if (visitor == null) {
			visitor = new ResourceDeltaVisitor();
		}
		return visitor;
	}

	protected void fileMoved(IPath oldFilePath, IPath newFilePath) throws CoreException {
		// notify listeners
		// make a copy of listener list to avoid concurrent list modification
		List<IFileChangeListener> l = new ArrayList<IFileChangeListener>();
		l.addAll(listeners);
		for (IFileChangeListener listener : l) {
			listener.moved(oldFilePath, newFilePath);
		}
	}
	
	protected void fileDeleted(IPath filePath, IProgressMonitor monitor) throws CoreException {
		// notify listeners
		// make a copy of listener list to avoid concurrent list modification
		List<IFileChangeListener> l = new ArrayList<IFileChangeListener>();
		l.addAll(listeners);
		for (IFileChangeListener listener : l) {
			listener.deleted(filePath);
		}
	}
}
