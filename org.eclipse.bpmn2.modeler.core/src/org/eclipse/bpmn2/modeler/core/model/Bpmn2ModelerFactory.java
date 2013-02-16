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

package org.eclipse.bpmn2.modeler.core.model;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.impl.Bpmn2FactoryImpl;
import org.eclipse.bpmn2.modeler.core.runtime.ModelExtensionDescriptor;
import org.eclipse.bpmn2.modeler.core.runtime.TargetRuntime;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;

/**
 * This Factory will invoke the super factory to create a "bare bones"
 * model object, and then "decorate" it with model extensions defined
 * by the Target Runtime plugin extension.
 *   
 * @author Bob Brodt
 *
 */
public class Bpmn2ModelerFactory extends Bpmn2FactoryImpl {
	
	// Allows the XML loader for a particular target runtime to temporarily disable
	// model extensions. This prevents extensions being added multiple times by
	// ModelExtensionDescriptor.populateObject() every time a file is loaded.
	protected static boolean enableModelExtensions = true;

	@Override
    public EObject create(EClass eClass) {
    	EObject object = super.create(eClass);
    	if (enableModelExtensions)
    	{
	    	TargetRuntime rt = TargetRuntime.getCurrentRuntime();
	    	if (rt!=null) {
    			
	    		if (!eClass.getName().equals(Bpmn2Package.eINSTANCE.getDocumentRoot().getName()) && 
	    			rt.getModelDescriptor().getEPackage() != Bpmn2Package.eINSTANCE &&
	    			rt.getModelDescriptor().getEPackage().getEClassifier(eClass.getName()) != null ) {
    				EClass clazz = (EClass) rt.getModelDescriptor().getEPackage().getEClassifier(eClass.getName());
	    			object = rt.getModelDescriptor().getEFactory().create(clazz);
    			}
	    		
		    	for (ModelExtensionDescriptor med : rt.getModelExtensions()) {
		    		if (med.getType().equals(eClass.getName())) {
		    			med.populateObject(object, eResource());
		    			break;
		    		}
		    	}
	    	}
    	}
    	return object;
    }

    public static void setEnableModelExtensions(boolean enable) {
    	enableModelExtensions = enable;
    }

    public static boolean getEnableModelExtensions() {
    	return enableModelExtensions;
    }
	
	@SuppressWarnings("unchecked")
	public static <T extends EObject> T create(Class<T> clazz) {
		EObject newObject = null;
		EClassifier eClassifier = Bpmn2Package.eINSTANCE.getEClassifier(clazz.getSimpleName());
		if (eClassifier instanceof EClass) {
			newObject = Bpmn2ModelerFactory.eINSTANCE.create((EClass)eClassifier);
		}
		return (T)newObject;
	}
}
