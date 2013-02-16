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
package org.eclipse.bpmn2;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Multi Instance Loop Characteristics</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.MultiInstanceLoopCharacteristics#getLoopCardinality <em>Loop Cardinality</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.MultiInstanceLoopCharacteristics#getLoopDataInputRef <em>Loop Data Input Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.MultiInstanceLoopCharacteristics#getLoopDataOutputRef <em>Loop Data Output Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.MultiInstanceLoopCharacteristics#getInputDataItem <em>Input Data Item</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.MultiInstanceLoopCharacteristics#getOutputDataItem <em>Output Data Item</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.MultiInstanceLoopCharacteristics#getComplexBehaviorDefinition <em>Complex Behavior Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.MultiInstanceLoopCharacteristics#getCompletionCondition <em>Completion Condition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.MultiInstanceLoopCharacteristics#getBehavior <em>Behavior</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.MultiInstanceLoopCharacteristics#isIsSequential <em>Is Sequential</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.MultiInstanceLoopCharacteristics#getNoneBehaviorEventRef <em>None Behavior Event Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.MultiInstanceLoopCharacteristics#getOneBehaviorEventRef <em>One Behavior Event Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getMultiInstanceLoopCharacteristics()
 * @model extendedMetaData="name='tMultiInstanceLoopCharacteristics' kind='elementOnly'"
 * @generated
 */
public interface MultiInstanceLoopCharacteristics extends LoopCharacteristics {
    /**
     * Returns the value of the '<em><b>Loop Cardinality</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Loop Cardinality</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Loop Cardinality</em>' containment reference.
     * @see #setLoopCardinality(Expression)
     * @see org.eclipse.bpmn2.Bpmn2Package#getMultiInstanceLoopCharacteristics_LoopCardinality()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='loopCardinality' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    Expression getLoopCardinality();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.MultiInstanceLoopCharacteristics#getLoopCardinality <em>Loop Cardinality</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Loop Cardinality</em>' containment reference.
     * @see #getLoopCardinality()
     * @generated
     */
    void setLoopCardinality(Expression value);

    /**
     * Returns the value of the '<em><b>Loop Data Input Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Loop Data Input Ref</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Loop Data Input Ref</em>' reference.
     * @see #setLoopDataInputRef(ItemAwareElement)
     * @see org.eclipse.bpmn2.Bpmn2Package#getMultiInstanceLoopCharacteristics_LoopDataInputRef()
     * @model ordered="false"
     *        extendedMetaData="kind='element' name='loopDataInputRef' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    ItemAwareElement getLoopDataInputRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.MultiInstanceLoopCharacteristics#getLoopDataInputRef <em>Loop Data Input Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Loop Data Input Ref</em>' reference.
     * @see #getLoopDataInputRef()
     * @generated
     */
    void setLoopDataInputRef(ItemAwareElement value);

    /**
     * Returns the value of the '<em><b>Loop Data Output Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Loop Data Output Ref</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Loop Data Output Ref</em>' reference.
     * @see #setLoopDataOutputRef(ItemAwareElement)
     * @see org.eclipse.bpmn2.Bpmn2Package#getMultiInstanceLoopCharacteristics_LoopDataOutputRef()
     * @model ordered="false"
     *        extendedMetaData="kind='element' name='loopDataOutputRef' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    ItemAwareElement getLoopDataOutputRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.MultiInstanceLoopCharacteristics#getLoopDataOutputRef <em>Loop Data Output Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Loop Data Output Ref</em>' reference.
     * @see #getLoopDataOutputRef()
     * @generated
     */
    void setLoopDataOutputRef(ItemAwareElement value);

    /**
     * Returns the value of the '<em><b>Input Data Item</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Input Data Item</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Input Data Item</em>' containment reference.
     * @see #setInputDataItem(DataInput)
     * @see org.eclipse.bpmn2.Bpmn2Package#getMultiInstanceLoopCharacteristics_InputDataItem()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='inputDataItem' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    DataInput getInputDataItem();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.MultiInstanceLoopCharacteristics#getInputDataItem <em>Input Data Item</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Input Data Item</em>' containment reference.
     * @see #getInputDataItem()
     * @generated
     */
    void setInputDataItem(DataInput value);

    /**
     * Returns the value of the '<em><b>Output Data Item</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Output Data Item</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Output Data Item</em>' containment reference.
     * @see #setOutputDataItem(DataOutput)
     * @see org.eclipse.bpmn2.Bpmn2Package#getMultiInstanceLoopCharacteristics_OutputDataItem()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='outputDataItem' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    DataOutput getOutputDataItem();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.MultiInstanceLoopCharacteristics#getOutputDataItem <em>Output Data Item</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Output Data Item</em>' containment reference.
     * @see #getOutputDataItem()
     * @generated
     */
    void setOutputDataItem(DataOutput value);

    /**
     * Returns the value of the '<em><b>Complex Behavior Definition</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.ComplexBehaviorDefinition}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Complex Behavior Definition</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Complex Behavior Definition</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getMultiInstanceLoopCharacteristics_ComplexBehaviorDefinition()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='complexBehaviorDefinition' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<ComplexBehaviorDefinition> getComplexBehaviorDefinition();

    /**
     * Returns the value of the '<em><b>Completion Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Completion Condition</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Completion Condition</em>' containment reference.
     * @see #setCompletionCondition(Expression)
     * @see org.eclipse.bpmn2.Bpmn2Package#getMultiInstanceLoopCharacteristics_CompletionCondition()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='completionCondition' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    Expression getCompletionCondition();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.MultiInstanceLoopCharacteristics#getCompletionCondition <em>Completion Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Completion Condition</em>' containment reference.
     * @see #getCompletionCondition()
     * @generated
     */
    void setCompletionCondition(Expression value);

    /**
     * Returns the value of the '<em><b>Behavior</b></em>' attribute.
     * The default value is <code>"All"</code>.
     * The literals are from the enumeration {@link org.eclipse.bpmn2.MultiInstanceBehavior}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Behavior</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Behavior</em>' attribute.
     * @see org.eclipse.bpmn2.MultiInstanceBehavior
     * @see #setBehavior(MultiInstanceBehavior)
     * @see org.eclipse.bpmn2.Bpmn2Package#getMultiInstanceLoopCharacteristics_Behavior()
     * @model default="All" ordered="false"
     *        extendedMetaData="kind='attribute' name='behavior'"
     * @generated
     */
    MultiInstanceBehavior getBehavior();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.MultiInstanceLoopCharacteristics#getBehavior <em>Behavior</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Behavior</em>' attribute.
     * @see org.eclipse.bpmn2.MultiInstanceBehavior
     * @see #getBehavior()
     * @generated
     */
    void setBehavior(MultiInstanceBehavior value);

    /**
     * Returns the value of the '<em><b>Is Sequential</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Sequential</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Sequential</em>' attribute.
     * @see #setIsSequential(boolean)
     * @see org.eclipse.bpmn2.Bpmn2Package#getMultiInstanceLoopCharacteristics_IsSequential()
     * @model default="false" ordered="false"
     *        extendedMetaData="kind='attribute' name='isSequential'"
     * @generated
     */
    boolean isIsSequential();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.MultiInstanceLoopCharacteristics#isIsSequential <em>Is Sequential</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Sequential</em>' attribute.
     * @see #isIsSequential()
     * @generated
     */
    void setIsSequential(boolean value);

    /**
     * Returns the value of the '<em><b>None Behavior Event Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>None Behavior Event Ref</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>None Behavior Event Ref</em>' reference.
     * @see #setNoneBehaviorEventRef(EventDefinition)
     * @see org.eclipse.bpmn2.Bpmn2Package#getMultiInstanceLoopCharacteristics_NoneBehaviorEventRef()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='noneBehaviorEventRef'"
     * @generated
     */
    EventDefinition getNoneBehaviorEventRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.MultiInstanceLoopCharacteristics#getNoneBehaviorEventRef <em>None Behavior Event Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>None Behavior Event Ref</em>' reference.
     * @see #getNoneBehaviorEventRef()
     * @generated
     */
    void setNoneBehaviorEventRef(EventDefinition value);

    /**
     * Returns the value of the '<em><b>One Behavior Event Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>One Behavior Event Ref</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>One Behavior Event Ref</em>' reference.
     * @see #setOneBehaviorEventRef(EventDefinition)
     * @see org.eclipse.bpmn2.Bpmn2Package#getMultiInstanceLoopCharacteristics_OneBehaviorEventRef()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='oneBehaviorEventRef'"
     * @generated
     */
    EventDefinition getOneBehaviorEventRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.MultiInstanceLoopCharacteristics#getOneBehaviorEventRef <em>One Behavior Event Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>One Behavior Event Ref</em>' reference.
     * @see #getOneBehaviorEventRef()
     * @generated
     */
    void setOneBehaviorEventRef(EventDefinition value);

} // MultiInstanceLoopCharacteristics
