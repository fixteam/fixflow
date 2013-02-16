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

package org.eclipse.bpmn2.modeler.ui.adapters.properties;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.ResourceAssignmentExpression;
import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.core.adapters.FeatureDescriptor;
import org.eclipse.bpmn2.modeler.core.adapters.ObjectDescriptor;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * @author Bob Brodt
 *
 */
public class ResourceAssignmentExpressionPropertiesAdapter extends ExtendedPropertiesAdapter {

	/**
	 * @param adapterFactory
	 * @param object
	 */
	public ResourceAssignmentExpressionPropertiesAdapter(AdapterFactory adapterFactory, EObject object) {
		super(adapterFactory, object);

    	final EStructuralFeature ref = Bpmn2Package.eINSTANCE.getResourceAssignmentExpression_Expression();
    	setFeatureDescriptor(ref,
			new FeatureDescriptor(adapterFactory,object,ref) {

				@Override
				public String getText(Object context) {
					EObject object = this.object;
					if (context instanceof ResourceAssignmentExpression)
						object = (EObject)context;
					ResourceAssignmentExpression rae = null;
					if (object instanceof ResourceAssignmentExpression)
						rae = (ResourceAssignmentExpression) object;
					if (rae!=null && rae.getExpression() instanceof FormalExpression) {
						return ((FormalExpression)rae.getExpression()).getBody();
					}
					return "";
				}

				@Override
				public void setValue(Object context, Object value) {
					ResourceAssignmentExpression rae = (ResourceAssignmentExpression)this.object;
					if (!(rae.getExpression() instanceof FormalExpression)) {
						final FormalExpression e = Bpmn2Factory.eINSTANCE.createFormalExpression();
						e.setBody((String) value);
						TransactionalEditingDomain editingDomain = getEditingDomain(object);
						if (editingDomain == null) {
							object.eSet(feature, value);
						} else {
							editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
								@Override
								protected void doExecute() {
									object.eSet(feature, e);
									ModelUtil.setID(e);
								}
							});
						}
					}
				}
    		}
    	);
    	setObjectDescriptor(new ObjectDescriptor(adapterFactory, object) {
			@Override
			public String getText(Object context) {
				return getFeatureDescriptor(ref).getText(context);
			}
    	});
	}

}
