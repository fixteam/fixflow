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
package org.eclipse.bpmn2.modeler.ui.wizards;

import org.eclipse.bpmn2.modeler.core.utils.ModelUtil.Bpmn2DiagramType;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.ui.part.FileEditorInput;

public final class Bpmn2DiagramEditorInput extends DiagramEditorInput {

	private final TransactionalEditingDomain domain;
	private Bpmn2DiagramType initialDiagramType = Bpmn2DiagramType.NONE;
	private String targetNamespace;

	Bpmn2DiagramEditorInput(URI diagramUri, TransactionalEditingDomain domain, String providerId) {
		super(diagramUri, domain, providerId);
		this.domain = domain;
	}

	public Bpmn2DiagramType getInitialDiagramType() {
		return initialDiagramType;
	}

	public void setInitialDiagramType(Bpmn2DiagramType initialDiagramType) {
		this.initialDiagramType = initialDiagramType;
	}

	public String getTargetNamespace() {
		return targetNamespace;
	}

	public void setTargetNamespace(String targetNamespace) {
		this.targetNamespace = targetNamespace;
	}

	@Override
	public boolean equals(Object obj) {
		boolean superEquals = super.equals(obj);
		if (superEquals) {
			return true;
		}

		// Eclipse makes FileEditorInputs for files to be opened. Here we check if the file is actually the same
		// as the DiagramEditorInput uses. This is for preventing opening new editors for the same file.
		if (obj instanceof FileEditorInput) {

			String path = ((FileEditorInput) obj).getFile().getFullPath().toString();
			URI platformUri = URI.createPlatformResourceURI(path, true);

			for (Resource resource : domain.getResourceSet().getResources()) {
				if (resource.getURI().equals(platformUri)) {
					return true;
				}
			}

		}
		return false;
	}

	@Override
	public Object getAdapter(Class adapter) {
//		if (adapter.equals(TransactionalEditingDomain.class)) {
//			return new Bpmn2TransactionalEditingDomain();
//		}
		return super.getAdapter(adapter);
	}

//	public class Bpmn2TransactionalEditingDomain implements TransactionalEditingDomain {
//		
//	}
}