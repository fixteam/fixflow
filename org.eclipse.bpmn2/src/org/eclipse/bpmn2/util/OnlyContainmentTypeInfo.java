/**
 * <copyright>
 * 
 * Copyright (c) 2010 SAP AG
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Reiner Hille-Doering (SAP AG) - initial API and implementation and/or initial documentation
 * 
 * </copyright>
 *
 * $Id: //bpem/bpem.metamodels/dev/src/_org.eclipse.bpmn2.ecore/ecp/api/org/eclipse/bpmn2/ecore/OnlyContainmentTypeInfo.java#1 $
 */
package org.eclipse.bpmn2.util;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.XMLSave.XMLTypeInfo;

public class OnlyContainmentTypeInfo implements XMLTypeInfo {

    /*
     * Ensure that we save type information only for containment - in this case using the substitution group magic. But don't save type
     * information for normal references. We anyway never create proxies.
     */

    // @Override // for implementing interface methods: only since Java 1.6
    public boolean shouldSaveType(EClass objectType, EClassifier featureType,
            EStructuralFeature feature) {
        return feature instanceof EReference && ((EReference) feature).isContainment();
    }

    // @Override
    public boolean shouldSaveType(EClass objectType, EClass featureType, EStructuralFeature feature) {
        return feature instanceof EReference && ((EReference) feature).isContainment();
    }

}
