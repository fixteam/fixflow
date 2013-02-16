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
import org.eclipse.bpmn2.InputOutputBinding;
import org.eclipse.bpmn2.InputSet;
import org.eclipse.bpmn2.Operation;
import org.eclipse.bpmn2.OutputSet;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Input Output Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.InputOutputBindingImpl#getInputDataRef <em>Input Data Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.InputOutputBindingImpl#getOperationRef <em>Operation Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.InputOutputBindingImpl#getOutputDataRef <em>Output Data Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InputOutputBindingImpl extends BaseElementImpl implements InputOutputBinding {
    /**
     * The cached value of the '{@link #getInputDataRef() <em>Input Data Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInputDataRef()
     * @generated
     * @ordered
     */
    protected InputSet inputDataRef;

    /**
     * The cached value of the '{@link #getOperationRef() <em>Operation Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOperationRef()
     * @generated
     * @ordered
     */
    protected Operation operationRef;

    /**
     * The cached value of the '{@link #getOutputDataRef() <em>Output Data Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOutputDataRef()
     * @generated
     * @ordered
     */
    protected OutputSet outputDataRef;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected InputOutputBindingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.INPUT_OUTPUT_BINDING;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InputSet getInputDataRef() {
        return inputDataRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInputDataRef(InputSet newInputDataRef) {
        InputSet oldInputDataRef = inputDataRef;
        inputDataRef = newInputDataRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.INPUT_OUTPUT_BINDING__INPUT_DATA_REF, oldInputDataRef,
                    inputDataRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Operation getOperationRef() {
        if (operationRef != null && operationRef.eIsProxy()) {
            InternalEObject oldOperationRef = (InternalEObject) operationRef;
            operationRef = (Operation) eResolveProxy(oldOperationRef);
            if (operationRef != oldOperationRef) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            Bpmn2Package.INPUT_OUTPUT_BINDING__OPERATION_REF, oldOperationRef,
                            operationRef));
            }
        }
        return operationRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Operation basicGetOperationRef() {
        return operationRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOperationRef(Operation newOperationRef) {
        Operation oldOperationRef = operationRef;
        operationRef = newOperationRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.INPUT_OUTPUT_BINDING__OPERATION_REF, oldOperationRef, operationRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OutputSet getOutputDataRef() {
        return outputDataRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOutputDataRef(OutputSet newOutputDataRef) {
        OutputSet oldOutputDataRef = outputDataRef;
        outputDataRef = newOutputDataRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.INPUT_OUTPUT_BINDING__OUTPUT_DATA_REF, oldOutputDataRef,
                    outputDataRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case Bpmn2Package.INPUT_OUTPUT_BINDING__INPUT_DATA_REF:
            return getInputDataRef();
        case Bpmn2Package.INPUT_OUTPUT_BINDING__OPERATION_REF:
            if (resolve)
                return getOperationRef();
            return basicGetOperationRef();
        case Bpmn2Package.INPUT_OUTPUT_BINDING__OUTPUT_DATA_REF:
            return getOutputDataRef();
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
        case Bpmn2Package.INPUT_OUTPUT_BINDING__INPUT_DATA_REF:
            setInputDataRef((InputSet) newValue);
            return;
        case Bpmn2Package.INPUT_OUTPUT_BINDING__OPERATION_REF:
            setOperationRef((Operation) newValue);
            return;
        case Bpmn2Package.INPUT_OUTPUT_BINDING__OUTPUT_DATA_REF:
            setOutputDataRef((OutputSet) newValue);
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
        case Bpmn2Package.INPUT_OUTPUT_BINDING__INPUT_DATA_REF:
            setInputDataRef((InputSet) null);
            return;
        case Bpmn2Package.INPUT_OUTPUT_BINDING__OPERATION_REF:
            setOperationRef((Operation) null);
            return;
        case Bpmn2Package.INPUT_OUTPUT_BINDING__OUTPUT_DATA_REF:
            setOutputDataRef((OutputSet) null);
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
        case Bpmn2Package.INPUT_OUTPUT_BINDING__INPUT_DATA_REF:
            return inputDataRef != null;
        case Bpmn2Package.INPUT_OUTPUT_BINDING__OPERATION_REF:
            return operationRef != null;
        case Bpmn2Package.INPUT_OUTPUT_BINDING__OUTPUT_DATA_REF:
            return outputDataRef != null;
        }
        return super.eIsSet(featureID);
    }

} //InputOutputBindingImpl
