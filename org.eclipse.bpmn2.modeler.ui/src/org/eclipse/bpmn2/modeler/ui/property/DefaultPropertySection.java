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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

public class DefaultPropertySection extends AbstractBpmn2PropertySection {

	// Register the DefaultPropertiesComposite as a fallback for rendering EObject
	static {
		PropertiesCompositeFactory.register(EObject.class, DefaultPropertiesComposite.class);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection#createSectionRoot()
	 */
	@Override
	protected AbstractBpmn2PropertiesComposite createSectionRoot() {
		return new DefaultPropertiesComposite(this);
	}

	@Override
	public boolean appliesTo(IWorkbenchPart part, ISelection selection) {
//		BPMN2Editor editor = (BPMN2Editor)part;
//		EObject object = getBusinessObjectForSelection();
//		DefaultPropertiesComposite composite = new DefaultPropertiesComposite();
//		AbstractPropertiesProvider provider = composite.getPropertiesProvider(object);
//		ModelEnablementDescriptor modelEnablement = editor.getTargetRuntime().getModelEnablements(object);
//		String className = object.eClass().getName();
//		if (modelEnablement.isEnabled(className)) {
//			String[] properties = provider.getProperties();
//			for (String featureName : properties) {
//				if (modelEnablement.isEnabled(className, featureName))
//					return true;
//			}
//		}
//		return false;
		return super.appliesTo(part, selection);
	}
}
