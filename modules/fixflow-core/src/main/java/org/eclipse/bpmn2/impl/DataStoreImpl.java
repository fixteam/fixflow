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
import org.eclipse.bpmn2.DataStore;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Store</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.DataStoreImpl#getCapacity <em>Capacity</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DataStoreImpl#isIsUnlimited <em>Is Unlimited</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DataStoreImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataStoreImpl extends ItemAwareElementImpl implements DataStore {
    /**
     * The default value of the '{@link #getCapacity() <em>Capacity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCapacity()
     * @generated
     * @ordered
     */
    protected static final int CAPACITY_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getCapacity() <em>Capacity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCapacity()
     * @generated
     * @ordered
     */
    protected int capacity = CAPACITY_EDEFAULT;

    /**
     * The default value of the '{@link #isIsUnlimited() <em>Is Unlimited</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsUnlimited()
     * @generated
     * @ordered
     */
    protected static final boolean IS_UNLIMITED_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isIsUnlimited() <em>Is Unlimited</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsUnlimited()
     * @generated
     * @ordered
     */
    protected boolean isUnlimited = IS_UNLIMITED_EDEFAULT;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DataStoreImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.DATA_STORE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCapacity(int newCapacity) {
        int oldCapacity = capacity;
        capacity = newCapacity;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.DATA_STORE__CAPACITY, oldCapacity, capacity));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isIsUnlimited() {
        return isUnlimited;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIsUnlimited(boolean newIsUnlimited) {
        boolean oldIsUnlimited = isUnlimited;
        isUnlimited = newIsUnlimited;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.DATA_STORE__IS_UNLIMITED, oldIsUnlimited, isUnlimited));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.DATA_STORE__NAME,
                    oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case Bpmn2Package.DATA_STORE__CAPACITY:
            return getCapacity();
        case Bpmn2Package.DATA_STORE__IS_UNLIMITED:
            return isIsUnlimited();
        case Bpmn2Package.DATA_STORE__NAME:
            return getName();
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
        case Bpmn2Package.DATA_STORE__CAPACITY:
            setCapacity((Integer) newValue);
            return;
        case Bpmn2Package.DATA_STORE__IS_UNLIMITED:
            setIsUnlimited((Boolean) newValue);
            return;
        case Bpmn2Package.DATA_STORE__NAME:
            setName((String) newValue);
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
        case Bpmn2Package.DATA_STORE__CAPACITY:
            setCapacity(CAPACITY_EDEFAULT);
            return;
        case Bpmn2Package.DATA_STORE__IS_UNLIMITED:
            setIsUnlimited(IS_UNLIMITED_EDEFAULT);
            return;
        case Bpmn2Package.DATA_STORE__NAME:
            setName(NAME_EDEFAULT);
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
        case Bpmn2Package.DATA_STORE__CAPACITY:
            return capacity != CAPACITY_EDEFAULT;
        case Bpmn2Package.DATA_STORE__IS_UNLIMITED:
            return isUnlimited != IS_UNLIMITED_EDEFAULT;
        case Bpmn2Package.DATA_STORE__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (capacity: ");
        result.append(capacity);
        result.append(", isUnlimited: ");
        result.append(isUnlimited);
        result.append(", name: ");
        result.append(name);
        result.append(')');
        return result.toString();
    }

} //DataStoreImpl
