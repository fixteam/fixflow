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


package org.eclipse.bpmn2.modeler.ui.property.tasks;

import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.ui.property.DefaultPropertiesComposite;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;

public class ReceiveTaskPropertiesComposite extends DefaultPropertiesComposite {

	public ReceiveTaskPropertiesComposite(Composite parent, int style) {
		super(parent, style);
	}

	/**
	 * @param section
	 */
	public ReceiveTaskPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite
	 * #createBindings(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public void createBindings(EObject be) {
		bindAttribute(be,"implementation");
		bindAttribute(be,"instantiate");
		bindReference(be,"messageRef");
		bindReference(be,"operationRef");
	}
}