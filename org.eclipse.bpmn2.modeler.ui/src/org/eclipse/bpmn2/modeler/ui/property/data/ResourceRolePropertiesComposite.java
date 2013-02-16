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

package org.eclipse.bpmn2.modeler.ui.property.data;

import org.eclipse.bpmn2.modeler.core.adapters.InsertionAdapter;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.ui.property.DefaultPropertiesComposite;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Bob Brodt
 *
 */
public class ResourceRolePropertiesComposite extends DefaultPropertiesComposite {

	/**
	 * @param parent
	 * @param style
	 */
	public ResourceRolePropertiesComposite(Composite parent, int style) {
		super(parent, style);
	}

	/**
	 * @param section
	 */
	public ResourceRolePropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	@Override
	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
		if (propertiesProvider==null) {
			propertiesProvider = new AbstractPropertiesProvider(object) {
				String[] properties = new String[] {
						"name",
						"resourceRef",
						"resourceAssignmentExpression",
						"resourceParameterBindings",
				};
				
				@Override
				public String[] getProperties() {
					return properties; 
				}
			};
		}
		return propertiesProvider;
	}
	
	@Override
	protected void bindReference(Composite parent, EObject object, EReference reference) {
		if ("resourceAssignmentExpression".equals(reference.getName()) &&
				modelEnablement.isEnabled(object.eClass(), reference)) {
			
			ResourceAssignmentExpressionPropertiesComposite composite =
					new ResourceAssignmentExpressionPropertiesComposite(this, SWT.NONE);
			EObject value = (EObject)object.eGet(reference);
			if (value==null) {
				value = modelHandler.create((EClass)reference.getEType());
				InsertionAdapter.add(object, reference, value);
			}
			composite.setEObject(getDiagramEditor(), value);
		}
		else
			super.bindReference(parent, object, reference);
	}
}
