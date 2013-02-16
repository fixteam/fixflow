package org.eclipse.bpmn2.modeler.ui;

/**
 * @author Bob Brodt
 *
 */
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;

/**
 * Similar to IResourceChangeListener but with more specific events.
 */
public interface IFileChangeListener {
	/**
	 * Called after a file is moved or renamed.
	 * 
	 * @param source the previous file
	 * @param destination the new file
	 */
	void moved(IPath oldFilePath, IPath newFilePath);
	
	/**
	 * Called after a file is deleted.
	 */
	void deleted(IPath filePath);
}