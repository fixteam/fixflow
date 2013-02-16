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
import org.eclipse.bpmn2.modeler.core.IBpmn2RuntimeExtension;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil.Bpmn2DiagramType;
import org.eclipse.core.resources.IFile;


public class DefaultBpmn2RuntimeExtension implements IBpmn2RuntimeExtension {

	public DefaultBpmn2RuntimeExtension() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isContentForRuntime(IFile file) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getTargetNamespace(Bpmn2DiagramType diagramType){
		String type = "";
		switch (diagramType) {
		case PROCESS:
			type = "/process";
			break;
		case COLLABORATION:
			type = "/collaboration";
			break;
		case CHOREOGRAPHY:
			type = "/choreography";
			break;
		}
		return "http://www.founderfix.com/fixflow/sample" + type;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
	}		
}
