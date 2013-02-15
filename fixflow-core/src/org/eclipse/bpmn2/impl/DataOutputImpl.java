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

import java.util.Collection;
import java.util.List;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.OutputSet;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Output</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.DataOutputImpl#getOutputSetWithOptional <em>Output Set With Optional</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DataOutputImpl#getOutputSetWithWhileExecuting <em>Output Set With While Executing</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DataOutputImpl#getOutputSetRefs <em>Output Set Refs</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DataOutputImpl#isIsCollection <em>Is Collection</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DataOutputImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataOutputImpl extends ItemAwareElementImpl implements DataOutput {
    /**
     * The cached value of the '{@link #getOutputSetWithOptional() <em>Output Set With Optional</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOutputSetWithOptional()
     * @generated
     * @ordered
     */
    protected EList<OutputSet> outputSetWithOptional;

    /**
     * The cached value of the '{@link #getOutputSetWithWhileExecuting() <em>Output Set With While Executing</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOutputSetWithWhileExecuting()
     * @generated
     * @ordered
     */
    protected EList<OutputSet> outputSetWithWhileExecuting;

    /**
     * The cached value of the '{@link #getOutputSetRefs() <em>Output Set Refs</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOutputSetRefs()
     * @generated
     * @ordered
     */
    protected EList<OutputSet> outputSetRefs;

    /**
     * The default value of the '{@link #isIsCollection() <em>Is Collection</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsCollection()
     * @generated
     * @ordered
     */
    protected static final boolean IS_COLLECTION_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIsCollection() <em>Is Collection</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsCollection()
     * @generated
     * @ordered
     */
    protected boolean isCollection = IS_COLLECTION_EDEFAULT;

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
    protected DataOutputImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.DATA_OUTPUT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<OutputSet> getOutputSetWithOptional() {
        if (outputSetWithOptional == null) {
            outputSetWithOptional = new EObjectWithInverseResolvingEList.ManyInverse<OutputSet>(
                    OutputSet.class, this, Bpmn2Package.DATA_OUTPUT__OUTPUT_SET_WITH_OPTIONAL,
                    Bpmn2Package.OUTPUT_SET__OPTIONAL_OUTPUT_REFS);
        }
        return outputSetWithOptional;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<OutputSet> getOutputSetWithWhileExecuting() {
        if (outputSetWithWhileExecuting == null) {
            outputSetWithWhileExecuting = new EObjectWithInverseResolvingEList.ManyInverse<OutputSet>(
                    OutputSet.class, this,
                    Bpmn2Package.DATA_OUTPUT__OUTPUT_SET_WITH_WHILE_EXECUTING,
                    Bpmn2Package.OUTPUT_SET__WHILE_EXECUTING_OUTPUT_REFS);
        }
        return outputSetWithWhileExecuting;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<OutputSet> getOutputSetRefs() {
        if (outputSetRefs == null) {
            outputSetRefs = new EObjectWithInverseResolvingEList.ManyInverse<OutputSet>(
                    OutputSet.class, this, Bpmn2Package.DATA_OUTPUT__OUTPUT_SET_REFS,
                    Bpmn2Package.OUTPUT_SET__DATA_OUTPUT_REFS);
        }
        return outputSetRefs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isIsCollection() {
        return isCollection;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIsCollection(boolean newIsCollection) {
        boolean oldIsCollection = isCollection;
        isCollection = newIsCollection;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.DATA_OUTPUT__IS_COLLECTION, oldIsCollection, isCollection));
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
            eNotify(new ENotificationImpl(this, Notification.SET, Bpmn2Package.DATA_OUTPUT__NAME,
                    oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID,
            NotificationChain msgs) {
        switch (featureID) {
        case Bpmn2Package.DATA_OUTPUT__OUTPUT_SET_WITH_OPTIONAL:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getOutputSetWithOptional())
                    .basicAdd(otherEnd, msgs);
        case Bpmn2Package.DATA_OUTPUT__OUTPUT_SET_WITH_WHILE_EXECUTING:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getOutputSetWithWhileExecuting())
                    .basicAdd(otherEnd, msgs);
        case Bpmn2Package.DATA_OUTPUT__OUTPUT_SET_REFS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getOutputSetRefs())
                    .basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
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
        case Bpmn2Package.DATA_OUTPUT__OUTPUT_SET_WITH_OPTIONAL:
            return ((InternalEList<?>) getOutputSetWithOptional()).basicRemove(otherEnd, msgs);
        case Bpmn2Package.DATA_OUTPUT__OUTPUT_SET_WITH_WHILE_EXECUTING:
            return ((InternalEList<?>) getOutputSetWithWhileExecuting())
                    .basicRemove(otherEnd, msgs);
        case Bpmn2Package.DATA_OUTPUT__OUTPUT_SET_REFS:
            return ((InternalEList<?>) getOutputSetRefs()).basicRemove(otherEnd, msgs);
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
        case Bpmn2Package.DATA_OUTPUT__OUTPUT_SET_WITH_OPTIONAL:
            return getOutputSetWithOptional();
        case Bpmn2Package.DATA_OUTPUT__OUTPUT_SET_WITH_WHILE_EXECUTING:
            return getOutputSetWithWhileExecuting();
        case Bpmn2Package.DATA_OUTPUT__OUTPUT_SET_REFS:
            return getOutputSetRefs();
        case Bpmn2Package.DATA_OUTPUT__IS_COLLECTION:
            return isIsCollection();
        case Bpmn2Package.DATA_OUTPUT__NAME:
            return getName();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case Bpmn2Package.DATA_OUTPUT__OUTPUT_SET_WITH_OPTIONAL:
            getOutputSetWithOptional().clear();
            getOutputSetWithOptional().addAll((Collection<? extends OutputSet>) newValue);
            return;
        case Bpmn2Package.DATA_OUTPUT__OUTPUT_SET_WITH_WHILE_EXECUTING:
            getOutputSetWithWhileExecuting().clear();
            getOutputSetWithWhileExecuting().addAll((Collection<? extends OutputSet>) newValue);
            return;
        case Bpmn2Package.DATA_OUTPUT__OUTPUT_SET_REFS:
            getOutputSetRefs().clear();
            getOutputSetRefs().addAll((Collection<? extends OutputSet>) newValue);
            return;
        case Bpmn2Package.DATA_OUTPUT__IS_COLLECTION:
            setIsCollection((Boolean) newValue);
            return;
        case Bpmn2Package.DATA_OUTPUT__NAME:
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
        case Bpmn2Package.DATA_OUTPUT__OUTPUT_SET_WITH_OPTIONAL:
            getOutputSetWithOptional().clear();
            return;
        case Bpmn2Package.DATA_OUTPUT__OUTPUT_SET_WITH_WHILE_EXECUTING:
            getOutputSetWithWhileExecuting().clear();
            return;
        case Bpmn2Package.DATA_OUTPUT__OUTPUT_SET_REFS:
            getOutputSetRefs().clear();
            return;
        case Bpmn2Package.DATA_OUTPUT__IS_COLLECTION:
            setIsCollection(IS_COLLECTION_EDEFAULT);
            return;
        case Bpmn2Package.DATA_OUTPUT__NAME:
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
        case Bpmn2Package.DATA_OUTPUT__OUTPUT_SET_WITH_OPTIONAL:
            return outputSetWithOptional != null && !outputSetWithOptional.isEmpty();
        case Bpmn2Package.DATA_OUTPUT__OUTPUT_SET_WITH_WHILE_EXECUTING:
            return outputSetWithWhileExecuting != null && !outputSetWithWhileExecuting.isEmpty();
        case Bpmn2Package.DATA_OUTPUT__OUTPUT_SET_REFS:
            return outputSetRefs != null && !outputSetRefs.isEmpty();
        case Bpmn2Package.DATA_OUTPUT__IS_COLLECTION:
            return isCollection != IS_COLLECTION_EDEFAULT;
        case Bpmn2Package.DATA_OUTPUT__NAME:
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
        result.append(" (isCollection: ");
        result.append(isCollection);
        result.append(", name: ");
        result.append(name);
        result.append(')');
        return result.toString();
    }

} //DataOutputImpl
