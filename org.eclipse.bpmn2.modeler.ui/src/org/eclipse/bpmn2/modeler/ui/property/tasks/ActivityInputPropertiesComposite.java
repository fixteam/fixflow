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

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.InputOutputSpecification;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.widgets.Composite;

public class ActivityInputPropertiesComposite extends AbstractBpmn2PropertiesComposite {

	public ActivityInputPropertiesComposite(Composite parent, int style) {
		super(parent, style);
	}

	/**
	 * @param section
	 */
	public ActivityInputPropertiesComposite(AbstractBpmn2PropertySection section) {
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
		if (be instanceof Activity) {
			Activity abe = (Activity)be;
			InputOutputSpecification ioSpecification = abe.getIoSpecification();
			if (ioSpecification != null) {
				EStructuralFeature feature = getFeature(ioSpecification, "dataInputs");
				bindList(ioSpecification, feature);
			}
			bindList(abe, "dataInputAssociations");
		}
//		bindList(be, "dataInputAssociations");
//		bindList(be, "dataOutputAssociations");
//		bindReference(be,"ioSpecification");
	}
}