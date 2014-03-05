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
import org.eclipse.bpmn2.CorrelationProperty;
import org.eclipse.bpmn2.CorrelationPropertyBinding;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Correlation Property Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.CorrelationPropertyBindingImpl#getDataPath <em>Data Path</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.CorrelationPropertyBindingImpl#getCorrelationPropertyRef <em>Correlation Property Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CorrelationPropertyBindingImpl extends BaseElementImpl implements
        CorrelationPropertyBinding {
    /**
     * The cached value of the '{@link #getDataPath() <em>Data Path</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDataPath()
     * @generated
     * @ordered
     */
    protected FormalExpression dataPath;

    /**
     * The cached value of the '{@link #getCorrelationPropertyRef() <em>Correlation Property Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCorrelationPropertyRef()
     * @generated
     * @ordered
     */
    protected CorrelationProperty correlationPropertyRef;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected CorrelationPropertyBindingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.CORRELATION_PROPERTY_BINDING;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FormalExpression getDataPath() {
        return dataPath;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDataPath(FormalExpression newDataPath, NotificationChain msgs) {
        FormalExpression oldDataPath = dataPath;
        dataPath = newDataPath;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.CORRELATION_PROPERTY_BINDING__DATA_PATH, oldDataPath, newDataPath);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDataPath(FormalExpression newDataPath) {
        if (newDataPath != dataPath) {
            NotificationChain msgs = null;
            if (dataPath != null)
                msgs = ((InternalEObject) dataPath).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                        - Bpmn2Package.CORRELATION_PROPERTY_BINDING__DATA_PATH, null, msgs);
            if (newDataPath != null)
                msgs = ((InternalEObject) newDataPath).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                        - Bpmn2Package.CORRELATION_PROPERTY_BINDING__DATA_PATH, null, msgs);
            msgs = basicSetDataPath(newDataPath, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.CORRELATION_PROPERTY_BINDING__DATA_PATH, newDataPath, newDataPath));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CorrelationProperty getCorrelationPropertyRef() {
        if (correlationPropertyRef != null && correlationPropertyRef.eIsProxy()) {
            InternalEObject oldCorrelationPropertyRef = (InternalEObject) correlationPropertyRef;
            correlationPropertyRef = (CorrelationProperty) eResolveProxy(oldCorrelationPropertyRef);
            if (correlationPropertyRef != oldCorrelationPropertyRef) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            Bpmn2Package.CORRELATION_PROPERTY_BINDING__CORRELATION_PROPERTY_REF,
                            oldCorrelationPropertyRef, correlationPropertyRef));
            }
        }
        return correlationPropertyRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CorrelationProperty basicGetCorrelationPropertyRef() {
        return correlationPropertyRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCorrelationPropertyRef(CorrelationProperty newCorrelationPropertyRef) {
        CorrelationProperty oldCorrelationPropertyRef = correlationPropertyRef;
        correlationPropertyRef = newCorrelationPropertyRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.CORRELATION_PROPERTY_BINDING__CORRELATION_PROPERTY_REF,
                    oldCorrelationPropertyRef, correlationPropertyRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID,
            NotificationChain msgs) {
        switch (featureID) {
        case Bpmn2Package.CORRELATION_PROPERTY_BINDING__DATA_PATH:
            return basicSetDataPath(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case Bpmn2Package.CORRELATION_PROPERTY_BINDING__DATA_PATH:
            return getDataPath();
        case Bpmn2Package.CORRELATION_PROPERTY_BINDING__CORRELATION_PROPERTY_REF:
            if (resolve)
                return getCorrelationPropertyRef();
            return basicGetCorrelationPropertyRef();
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
        case Bpmn2Package.CORRELATION_PROPERTY_BINDING__DATA_PATH:
            setDataPath((FormalExpression) newValue);
            return;
        case Bpmn2Package.CORRELATION_PROPERTY_BINDING__CORRELATION_PROPERTY_REF:
            setCorrelationPropertyRef((CorrelationProperty) newValue);
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
        case Bpmn2Package.CORRELATION_PROPERTY_BINDING__DATA_PATH:
            setDataPath((FormalExpression) null);
            return;
        case Bpmn2Package.CORRELATION_PROPERTY_BINDING__CORRELATION_PROPERTY_REF:
            setCorrelationPropertyRef((CorrelationProperty) null);
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
        case Bpmn2Package.CORRELATION_PROPERTY_BINDING__DATA_PATH:
            return dataPath != null;
        case Bpmn2Package.CORRELATION_PROPERTY_BINDING__CORRELATION_PROPERTY_REF:
            return correlationPropertyRef != null;
        }
        return super.eIsSet(featureID);
    }

} //CorrelationPropertyBindingImpl
