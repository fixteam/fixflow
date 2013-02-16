/**
 * <copyright>
 * 
 * Copyright (c) 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Reiner Hille-Doering (SAP AG) - initial API and implementation and/or initial documentation
 * 
 * </copyright>
 */
package org.eclipse.bpmn2.impl;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.ParticipantAssociation;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Participant Association</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.ParticipantAssociationImpl#getInnerParticipantRef <em>Inner Participant Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ParticipantAssociationImpl#getOuterParticipantRef <em>Outer Participant Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ParticipantAssociationImpl extends BaseElementImpl implements ParticipantAssociation {
    /**
     * The cached value of the '{@link #getInnerParticipantRef() <em>Inner Participant Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInnerParticipantRef()
     * @generated
     * @ordered
     */
    protected Participant innerParticipantRef;

    /**
     * The cached value of the '{@link #getOuterParticipantRef() <em>Outer Participant Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOuterParticipantRef()
     * @generated
     * @ordered
     */
    protected Participant outerParticipantRef;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ParticipantAssociationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.PARTICIPANT_ASSOCIATION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Participant getInnerParticipantRef() {
        if (innerParticipantRef != null && innerParticipantRef.eIsProxy()) {
            InternalEObject oldInnerParticipantRef = (InternalEObject) innerParticipantRef;
            innerParticipantRef = (Participant) eResolveProxy(oldInnerParticipantRef);
            if (innerParticipantRef != oldInnerParticipantRef) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            Bpmn2Package.PARTICIPANT_ASSOCIATION__INNER_PARTICIPANT_REF,
                            oldInnerParticipantRef, innerParticipantRef));
            }
        }
        return innerParticipantRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Participant basicGetInnerParticipantRef() {
        return innerParticipantRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInnerParticipantRef(Participant newInnerParticipantRef) {
        Participant oldInnerParticipantRef = innerParticipantRef;
        innerParticipantRef = newInnerParticipantRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.PARTICIPANT_ASSOCIATION__INNER_PARTICIPANT_REF,
                    oldInnerParticipantRef, innerParticipantRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Participant getOuterParticipantRef() {
        if (outerParticipantRef != null && outerParticipantRef.eIsProxy()) {
            InternalEObject oldOuterParticipantRef = (InternalEObject) outerParticipantRef;
            outerParticipantRef = (Participant) eResolveProxy(oldOuterParticipantRef);
            if (outerParticipantRef != oldOuterParticipantRef) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            Bpmn2Package.PARTICIPANT_ASSOCIATION__OUTER_PARTICIPANT_REF,
                            oldOuterParticipantRef, outerParticipantRef));
            }
        }
        return outerParticipantRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Participant basicGetOuterParticipantRef() {
        return outerParticipantRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOuterParticipantRef(Participant newOuterParticipantRef) {
        Participant oldOuterParticipantRef = outerParticipantRef;
        outerParticipantRef = newOuterParticipantRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.PARTICIPANT_ASSOCIATION__OUTER_PARTICIPANT_REF,
                    oldOuterParticipantRef, outerParticipantRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case Bpmn2Package.PARTICIPANT_ASSOCIATION__INNER_PARTICIPANT_REF:
            if (resolve)
                return getInnerParticipantRef();
            return basicGetInnerParticipantRef();
        case Bpmn2Package.PARTICIPANT_ASSOCIATION__OUTER_PARTICIPANT_REF:
            if (resolve)
                return getOuterParticipantRef();
            return basicGetOuterParticipantRef();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case Bpmn2Package.PARTICIPANT_ASSOCIATION__INNER_PARTICIPANT_REF:
            setInnerParticipantRef((Participant) newValue);
            return;
        case Bpmn2Package.PARTICIPANT_ASSOCIATION__OUTER_PARTICIPANT_REF:
            setOuterParticipantRef((Participant) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case Bpmn2Package.PARTICIPANT_ASSOCIATION__INNER_PARTICIPANT_REF:
            setInnerParticipantRef((Participant) null);
            return;
        case Bpmn2Package.PARTICIPANT_ASSOCIATION__OUTER_PARTICIPANT_REF:
            setOuterParticipantRef((Participant) null);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case Bpmn2Package.PARTICIPANT_ASSOCIATION__INNER_PARTICIPANT_REF:
            return innerParticipantRef != null;
        case Bpmn2Package.PARTICIPANT_ASSOCIATION__OUTER_PARTICIPANT_REF:
            return outerParticipantRef != null;
        }
        return super.eIsSet(featureID);
    }

} //ParticipantAssociationImpl
