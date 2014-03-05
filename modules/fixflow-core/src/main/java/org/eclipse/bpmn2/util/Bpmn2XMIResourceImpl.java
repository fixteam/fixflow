/**
 * Copyright (c) 2010 Henning Heitkoetter.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Henning Heitkoetter - initial API and implementation
 */
package org.eclipse.bpmn2.util;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLSave;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMISaveImpl;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl
 * @generated
 */
public class Bpmn2XMIResourceImpl extends XMIResourceImpl implements Bpmn2Resource {

    protected Bpmn2OppositeReferenceAdapter oppositeReferenceAdapter = new Bpmn2OppositeReferenceAdapter();

    public Bpmn2OppositeReferenceAdapter getOppositeReferenceAdapter() {
        return oppositeReferenceAdapter;
    }

    /**
     * Creates an instance of the resource.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param uri the URI of the new resource.
     * @generated
     */
    public Bpmn2XMIResourceImpl(URI uri) {
        super(uri);

        this.eAdapters().add(oppositeReferenceAdapter);
    }

    @Override
    public NotificationChain basicSetResourceSet(ResourceSet resourceSet,
            NotificationChain notifications) {
        if (resourceSet != null)
            resourceSet.eAdapters().add(oppositeReferenceAdapter);
        return super.basicSetResourceSet(resourceSet, notifications);
    }

    @Override
    protected XMLSave createXMLSave() {
        return new XMISaveImpl(createXMLHelper()) {
            @Override
            protected boolean shouldSaveFeature(EObject o, EStructuralFeature f) {
                if (Bpmn2Package.eINSTANCE.getDocumentation_Mixed().equals(f))
                    return false;
                if (Bpmn2Package.eINSTANCE.getFormalExpression_Mixed().equals(f))
                    return false;
                return super.shouldSaveFeature(o, f);
            }
        };
    }

} //Bpmn2XMIResourceImpl
