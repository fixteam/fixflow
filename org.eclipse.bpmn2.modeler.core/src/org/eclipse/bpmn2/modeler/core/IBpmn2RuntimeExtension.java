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
package org.eclipse.bpmn2.modeler.core;

import org.eclipse.bpmn2.modeler.core.utils.ModelUtil.Bpmn2DiagramType;
import org.eclipse.core.resources.IFile;

public interface IBpmn2RuntimeExtension {

	/**
	 * Check if the given input file is specific to the runtime environment.
	 * The implementation should check for specific extensions and namespaces that identify
	 * the file for this runtime.
	 *  
	 * @param file
	 * @return true if the file is targeted for this runtime, false if the file is generic BPMN 2.0
	 */
	public boolean isContentForRuntime(IFile file);
	public String getTargetNamespace(Bpmn2DiagramType diagramType);
	public void initialize();
}
