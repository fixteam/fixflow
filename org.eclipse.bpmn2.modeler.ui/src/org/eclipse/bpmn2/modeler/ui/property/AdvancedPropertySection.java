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
package org.eclipse.bpmn2.modeler.ui.property;

import java.io.IOException;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.di.impl.BPMNDiagramImpl;
import org.eclipse.bpmn2.modeler.core.ModelHandlerLocator;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.ui.Activator;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

public class AdvancedPropertySection extends AbstractBpmn2PropertySection {

	private AdvancedPropertiesComposite composite;

	/* (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection#createSectionRoot()
	 */
	@Override
	protected AbstractBpmn2PropertiesComposite createSectionRoot() {
		return new AdvancedPropertiesComposite(this);
	}
	
	@Override
	protected EObject getBusinessObjectForPictogramElement(PictogramElement pe) {
		EObject be = BusinessObjectUtil.getFirstElementOfType(pe, BaseElement.class,true);
		if (be==null) {
			// maybe it's the Diagram (editor canvas)?
			be = BusinessObjectUtil.getFirstElementOfType(pe, BPMNDiagram.class);
		}
		if (be instanceof BPMNDiagramImpl) {
			try {
				be = ModelHandlerLocator.getModelHandler(be.eResource()).getDefinitions();
			} catch (IOException e) {
				Activator.showErrorWithLogging(e);
			}
		}
		return be;
	}

	@Override
	public boolean shouldUseExtraSpace() {
		return false;
	}

	@Override
	public boolean appliesTo(IWorkbenchPart part, ISelection selection) {
		BPMN2Editor editor = (BPMN2Editor)part.getAdapter(BPMN2Editor.class);
		if (editor!=null)
			return editor.getPreferences().getShowAdvancedPropertiesTab();
		return false;
	}
}
