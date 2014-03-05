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
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.InputOutputSpecification;
import org.eclipse.bpmn2.InputSet;
import org.eclipse.bpmn2.OutputSet;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Input Output Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.InputOutputSpecificationImpl#getDataInputs <em>Data Inputs</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.InputOutputSpecificationImpl#getDataOutputs <em>Data Outputs</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.InputOutputSpecificationImpl#getInputSets <em>Input Sets</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.InputOutputSpecificationImpl#getOutputSets <em>Output Sets</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InputOutputSpecificationImpl extends BaseElementImpl implements
        InputOutputSpecification {
    /**
     * The cached value of the '{@link #getDataInputs() <em>Data Inputs</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDataInputs()
     * @generated
     * @ordered
     */
    protected EList<DataInput> dataInputs;

    /**
     * The cached value of the '{@link #getDataOutputs() <em>Data Outputs</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDataOutputs()
     * @generated
     * @ordered
     */
    protected EList<DataOutput> dataOutputs;

    /**
     * The cached value of the '{@link #getInputSets() <em>Input Sets</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInputSets()
     * @generated
     * @ordered
     */
    protected EList<InputSet> inputSets;

    /**
     * The cached value of the '{@link #getOutputSets() <em>Output Sets</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOutputSets()
     * @generated
     * @ordered
     */
    protected EList<OutputSet> outputSets;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected InputOutputSpecificationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.INPUT_OUTPUT_SPECIFICATION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<DataInput> getDataInputs() {
        if (dataInputs == null) {
            dataInputs = new EObjectContainmentEList<DataInput>(DataInput.class, this,
                    Bpmn2Package.INPUT_OUTPUT_SPECIFICATION__DATA_INPUTS);
        }
        return dataInputs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<DataOutput> getDataOutputs() {
        if (dataOutputs == null) {
            dataOutputs = new EObjectContainmentEList<DataOutput>(DataOutput.class, this,
                    Bpmn2Package.INPUT_OUTPUT_SPECIFICATION__DATA_OUTPUTS);
        }
        return dataOutputs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<InputSet> getInputSets() {
        if (inputSets == null) {
            inputSets = new EObjectContainmentEList<InputSet>(InputSet.class, this,
                    Bpmn2Package.INPUT_OUTPUT_SPECIFICATION__INPUT_SETS);
        }
        return inputSets;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<OutputSet> getOutputSets() {
        if (outputSets == null) {
            outputSets = new EObjectContainmentEList<OutputSet>(OutputSet.class, this,
                    Bpmn2Package.INPUT_OUTPUT_SPECIFICATION__OUTPUT_SETS);
        }
        return outputSets;
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
        case Bpmn2Package.INPUT_OUTPUT_SPECIFICATION__DATA_INPUTS:
            return ((InternalEList<?>) getDataInputs()).basicRemove(otherEnd, msgs);
        case Bpmn2Package.INPUT_OUTPUT_SPECIFICATION__DATA_OUTPUTS:
            return ((InternalEList<?>) getDataOutputs()).basicRemove(otherEnd, msgs);
        case Bpmn2Package.INPUT_OUTPUT_SPECIFICATION__INPUT_SETS:
            return ((InternalEList<?>) getInputSets()).basicRemove(otherEnd, msgs);
        case Bpmn2Package.INPUT_OUTPUT_SPECIFICATION__OUTPUT_SETS:
            return ((InternalEList<?>) getOutputSets()).basicRemove(otherEnd, msgs);
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
        case Bpmn2Package.INPUT_OUTPUT_SPECIFICATION__DATA_INPUTS:
            return getDataInputs();
        case Bpmn2Package.INPUT_OUTPUT_SPECIFICATION__DATA_OUTPUTS:
            return getDataOutputs();
        case Bpmn2Package.INPUT_OUTPUT_SPECIFICATION__INPUT_SETS:
            return getInputSets();
        case Bpmn2Package.INPUT_OUTPUT_SPECIFICATION__OUTPUT_SETS:
            return getOutputSets();
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
        case Bpmn2Package.INPUT_OUTPUT_SPECIFICATION__DATA_INPUTS:
            getDataInputs().clear();
            getDataInputs().addAll((Collection<? extends DataInput>) newValue);
            return;
        case Bpmn2Package.INPUT_OUTPUT_SPECIFICATION__DATA_OUTPUTS:
            getDataOutputs().clear();
            getDataOutputs().addAll((Collection<? extends DataOutput>) newValue);
            return;
        case Bpmn2Package.INPUT_OUTPUT_SPECIFICATION__INPUT_SETS:
            getInputSets().clear();
            getInputSets().addAll((Collection<? extends InputSet>) newValue);
            return;
        case Bpmn2Package.INPUT_OUTPUT_SPECIFICATION__OUTPUT_SETS:
            getOutputSets().clear();
            getOutputSets().addAll((Collection<? extends OutputSet>) newValue);
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
        case Bpmn2Package.INPUT_OUTPUT_SPECIFICATION__DATA_INPUTS:
            getDataInputs().clear();
            return;
        case Bpmn2Package.INPUT_OUTPUT_SPECIFICATION__DATA_OUTPUTS:
            getDataOutputs().clear();
            return;
        case Bpmn2Package.INPUT_OUTPUT_SPECIFICATION__INPUT_SETS:
            getInputSets().clear();
            return;
        case Bpmn2Package.INPUT_OUTPUT_SPECIFICATION__OUTPUT_SETS:
            getOutputSets().clear();
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
        case Bpmn2Package.INPUT_OUTPUT_SPECIFICATION__DATA_INPUTS:
            return dataInputs != null && !dataInputs.isEmpty();
        case Bpmn2Package.INPUT_OUTPUT_SPECIFICATION__DATA_OUTPUTS:
            return dataOutputs != null && !dataOutputs.isEmpty();
        case Bpmn2Package.INPUT_OUTPUT_SPECIFICATION__INPUT_SETS:
            return inputSets != null && !inputSets.isEmpty();
        case Bpmn2Package.INPUT_OUTPUT_SPECIFICATION__OUTPUT_SETS:
            return outputSets != null && !outputSets.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //InputOutputSpecificationImpl
