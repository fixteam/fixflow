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
import org.eclipse.bpmn2.ExtensionAttributeDefinition;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extension Attribute Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.ExtensionAttributeValueImpl#getValueRef <em>Value Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ExtensionAttributeValueImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.ExtensionAttributeValueImpl#getExtensionAttributeDefinition <em>Extension Attribute Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExtensionAttributeValueImpl extends EObjectImpl implements ExtensionAttributeValue {
    /**
     * The cached value of the '{@link #getValueRef() <em>Value Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValueRef()
     * @generated
     * @ordered
     */
    protected EObject valueRef;

    /**
     * The cached value of the '{@link #getValue() <em>Value</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected FeatureMap value;

    /**
     * The cached value of the '{@link #getExtensionAttributeDefinition() <em>Extension Attribute Definition</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExtensionAttributeDefinition()
     * @generated
     * @ordered
     */
    protected ExtensionAttributeDefinition extensionAttributeDefinition;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ExtensionAttributeValueImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.EXTENSION_ATTRIBUTE_VALUE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object getValueRef() {
        if (valueRef != null && valueRef.eIsProxy()) {
            InternalEObject oldValueRef = (InternalEObject) valueRef;
            valueRef = eResolveProxy(oldValueRef);
            if (valueRef != oldValueRef) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            Bpmn2Package.EXTENSION_ATTRIBUTE_VALUE__VALUE_REF, oldValueRef,
                            valueRef));
            }
        }
        return valueRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object basicGetValueRef() {
        return valueRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValueRef(Object newValueRef) {
        Object oldValueRef = valueRef;
        valueRef = (EObject) newValueRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.EXTENSION_ATTRIBUTE_VALUE__VALUE_REF, oldValueRef, valueRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getValue() {
        if (value == null) {
            value = new BasicFeatureMap(this, Bpmn2Package.EXTENSION_ATTRIBUTE_VALUE__VALUE);
        }
        return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExtensionAttributeDefinition getExtensionAttributeDefinition() {
        if (extensionAttributeDefinition != null && extensionAttributeDefinition.eIsProxy()) {
            InternalEObject oldExtensionAttributeDefinition = (InternalEObject) extensionAttributeDefinition;
            extensionAttributeDefinition = (ExtensionAttributeDefinition) eResolveProxy(oldExtensionAttributeDefinition);
            if (extensionAttributeDefinition != oldExtensionAttributeDefinition) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            Bpmn2Package.EXTENSION_ATTRIBUTE_VALUE__EXTENSION_ATTRIBUTE_DEFINITION,
                            oldExtensionAttributeDefinition, extensionAttributeDefinition));
            }
        }
        return extensionAttributeDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExtensionAttributeDefinition basicGetExtensionAttributeDefinition() {
        return extensionAttributeDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setExtensionAttributeDefinition(
            ExtensionAttributeDefinition newExtensionAttributeDefinition) {
        ExtensionAttributeDefinition oldExtensionAttributeDefinition = extensionAttributeDefinition;
        extensionAttributeDefinition = newExtensionAttributeDefinition;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.EXTENSION_ATTRIBUTE_VALUE__EXTENSION_ATTRIBUTE_DEFINITION,
                    oldExtensionAttributeDefinition, extensionAttributeDefinition));
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
        case Bpmn2Package.EXTENSION_ATTRIBUTE_VALUE__VALUE:
            return ((InternalEList<?>) getValue()).basicRemove(otherEnd, msgs);
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
        case Bpmn2Package.EXTENSION_ATTRIBUTE_VALUE__VALUE_REF:
            if (resolve)
                return getValueRef();
            return basicGetValueRef();
        case Bpmn2Package.EXTENSION_ATTRIBUTE_VALUE__VALUE:
            if (coreType)
                return getValue();
            return ((FeatureMap.Internal) getValue()).getWrapper();
        case Bpmn2Package.EXTENSION_ATTRIBUTE_VALUE__EXTENSION_ATTRIBUTE_DEFINITION:
            if (resolve)
                return getExtensionAttributeDefinition();
            return basicGetExtensionAttributeDefinition();
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
        case Bpmn2Package.EXTENSION_ATTRIBUTE_VALUE__VALUE_REF:
            setValueRef((Object) newValue);
            return;
        case Bpmn2Package.EXTENSION_ATTRIBUTE_VALUE__VALUE:
            ((FeatureMap.Internal) getValue()).set(newValue);
            return;
        case Bpmn2Package.EXTENSION_ATTRIBUTE_VALUE__EXTENSION_ATTRIBUTE_DEFINITION:
            setExtensionAttributeDefinition((ExtensionAttributeDefinition) newValue);
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
        case Bpmn2Package.EXTENSION_ATTRIBUTE_VALUE__VALUE_REF:
            setValueRef((Object) null);
            return;
        case Bpmn2Package.EXTENSION_ATTRIBUTE_VALUE__VALUE:
            getValue().clear();
            return;
        case Bpmn2Package.EXTENSION_ATTRIBUTE_VALUE__EXTENSION_ATTRIBUTE_DEFINITION:
            setExtensionAttributeDefinition((ExtensionAttributeDefinition) null);
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
        case Bpmn2Package.EXTENSION_ATTRIBUTE_VALUE__VALUE_REF:
            return valueRef != null;
        case Bpmn2Package.EXTENSION_ATTRIBUTE_VALUE__VALUE:
            return value != null && !value.isEmpty();
        case Bpmn2Package.EXTENSION_ATTRIBUTE_VALUE__EXTENSION_ATTRIBUTE_DEFINITION:
            return extensionAttributeDefinition != null;
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
        result.append(" (value: ");
        result.append(value);
        result.append(')');
        return result.toString();
    }

} //ExtensionAttributeValueImpl
