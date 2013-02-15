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
import org.eclipse.bpmn2.MessageFlow;
import org.eclipse.bpmn2.MessageFlowAssociation;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Message Flow Association</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.MessageFlowAssociationImpl#getInnerMessageFlowRef <em>Inner Message Flow Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.MessageFlowAssociationImpl#getOuterMessageFlowRef <em>Outer Message Flow Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MessageFlowAssociationImpl extends BaseElementImpl implements MessageFlowAssociation {
    /**
     * The cached value of the '{@link #getInnerMessageFlowRef() <em>Inner Message Flow Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInnerMessageFlowRef()
     * @generated
     * @ordered
     */
    protected MessageFlow innerMessageFlowRef;

    /**
     * The cached value of the '{@link #getOuterMessageFlowRef() <em>Outer Message Flow Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOuterMessageFlowRef()
     * @generated
     * @ordered
     */
    protected MessageFlow outerMessageFlowRef;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MessageFlowAssociationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.MESSAGE_FLOW_ASSOCIATION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MessageFlow getInnerMessageFlowRef() {
        if (innerMessageFlowRef != null && innerMessageFlowRef.eIsProxy()) {
            InternalEObject oldInnerMessageFlowRef = (InternalEObject) innerMessageFlowRef;
            innerMessageFlowRef = (MessageFlow) eResolveProxy(oldInnerMessageFlowRef);
            if (innerMessageFlowRef != oldInnerMessageFlowRef) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            Bpmn2Package.MESSAGE_FLOW_ASSOCIATION__INNER_MESSAGE_FLOW_REF,
                            oldInnerMessageFlowRef, innerMessageFlowRef));
            }
        }
        return innerMessageFlowRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MessageFlow basicGetInnerMessageFlowRef() {
        return innerMessageFlowRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInnerMessageFlowRef(MessageFlow newInnerMessageFlowRef) {
        MessageFlow oldInnerMessageFlowRef = innerMessageFlowRef;
        innerMessageFlowRef = newInnerMessageFlowRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.MESSAGE_FLOW_ASSOCIATION__INNER_MESSAGE_FLOW_REF,
                    oldInnerMessageFlowRef, innerMessageFlowRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MessageFlow getOuterMessageFlowRef() {
        if (outerMessageFlowRef != null && outerMessageFlowRef.eIsProxy()) {
            InternalEObject oldOuterMessageFlowRef = (InternalEObject) outerMessageFlowRef;
            outerMessageFlowRef = (MessageFlow) eResolveProxy(oldOuterMessageFlowRef);
            if (outerMessageFlowRef != oldOuterMessageFlowRef) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            Bpmn2Package.MESSAGE_FLOW_ASSOCIATION__OUTER_MESSAGE_FLOW_REF,
                            oldOuterMessageFlowRef, outerMessageFlowRef));
            }
        }
        return outerMessageFlowRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MessageFlow basicGetOuterMessageFlowRef() {
        return outerMessageFlowRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOuterMessageFlowRef(MessageFlow newOuterMessageFlowRef) {
        MessageFlow oldOuterMessageFlowRef = outerMessageFlowRef;
        outerMessageFlowRef = newOuterMessageFlowRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.MESSAGE_FLOW_ASSOCIATION__OUTER_MESSAGE_FLOW_REF,
                    oldOuterMessageFlowRef, outerMessageFlowRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case Bpmn2Package.MESSAGE_FLOW_ASSOCIATION__INNER_MESSAGE_FLOW_REF:
            if (resolve)
                return getInnerMessageFlowRef();
            return basicGetInnerMessageFlowRef();
        case Bpmn2Package.MESSAGE_FLOW_ASSOCIATION__OUTER_MESSAGE_FLOW_REF:
            if (resolve)
                return getOuterMessageFlowRef();
            return basicGetOuterMessageFlowRef();
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
        case Bpmn2Package.MESSAGE_FLOW_ASSOCIATION__INNER_MESSAGE_FLOW_REF:
            setInnerMessageFlowRef((MessageFlow) newValue);
            return;
        case Bpmn2Package.MESSAGE_FLOW_ASSOCIATION__OUTER_MESSAGE_FLOW_REF:
            setOuterMessageFlowRef((MessageFlow) newValue);
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
        case Bpmn2Package.MESSAGE_FLOW_ASSOCIATION__INNER_MESSAGE_FLOW_REF:
            setInnerMessageFlowRef((MessageFlow) null);
            return;
        case Bpmn2Package.MESSAGE_FLOW_ASSOCIATION__OUTER_MESSAGE_FLOW_REF:
            setOuterMessageFlowRef((MessageFlow) null);
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
        case Bpmn2Package.MESSAGE_FLOW_ASSOCIATION__INNER_MESSAGE_FLOW_REF:
            return innerMessageFlowRef != null;
        case Bpmn2Package.MESSAGE_FLOW_ASSOCIATION__OUTER_MESSAGE_FLOW_REF:
            return outerMessageFlowRef != null;
        }
        return super.eIsSet(featureID);
    }

} //MessageFlowAssociationImpl
