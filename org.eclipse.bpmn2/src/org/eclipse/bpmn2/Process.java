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
 * A representation of the model object '<em><b>Process</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.Process#getAuditing <em>Auditing</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Process#getMonitoring <em>Monitoring</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Process#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Process#getArtifacts <em>Artifacts</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Process#getResources <em>Resources</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Process#getCorrelationSubscriptions <em>Correlation Subscriptions</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Process#getSupports <em>Supports</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Process#getDefinitionalCollaborationRef <em>Definitional Collaboration Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Process#isIsClosed <em>Is Closed</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Process#isIsExecutable <em>Is Executable</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.Process#getProcessType <em>Process Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getProcess()
 * @model extendedMetaData="name='tProcess' kind='elementOnly'"
 * @generated
 */
public interface Process extends CallableElement, FlowElementsContainer {
    /**
     * Returns the value of the '<em><b>Auditing</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Auditing</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Auditing</em>' containment reference.
     * @see #setAuditing(Auditing)
     * @see org.eclipse.bpmn2.Bpmn2Package#getProcess_Auditing()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='auditing' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    Auditing getAuditing();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Process#getAuditing <em>Auditing</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Auditing</em>' containment reference.
     * @see #getAuditing()
     * @generated
     */
    void setAuditing(Auditing value);

    /**
     * Returns the value of the '<em><b>Monitoring</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Monitoring</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Monitoring</em>' containment reference.
     * @see #setMonitoring(Monitoring)
     * @see org.eclipse.bpmn2.Bpmn2Package#getProcess_Monitoring()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='monitoring' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    Monitoring getMonitoring();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Process#getMonitoring <em>Monitoring</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Monitoring</em>' containment reference.
     * @see #getMonitoring()
     * @generated
     */
    void setMonitoring(Monitoring value);

    /**
     * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.Property}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Properties</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getProcess_Properties()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='property' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<Property> getProperties();

    /**
     * Returns the value of the '<em><b>Artifacts</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.Artifact}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Artifacts</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Artifacts</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getProcess_Artifacts()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='artifact' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' group='http://www.omg.org/spec/BPMN/20100524/MODEL#artifact'"
     * @generated
     */
    List<Artifact> getArtifacts();

    /**
     * Returns the value of the '<em><b>Resources</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.ResourceRole}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resources</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Resources</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getProcess_Resources()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='resourceRole' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' group='http://www.omg.org/spec/BPMN/20100524/MODEL#resourceRole'"
     * @generated
     */
    List<ResourceRole> getResources();

    /**
     * Returns the value of the '<em><b>Correlation Subscriptions</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.CorrelationSubscription}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Correlation Subscriptions</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Correlation Subscriptions</em>' containment reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getProcess_CorrelationSubscriptions()
     * @model containment="true" ordered="false"
     *        extendedMetaData="kind='element' name='correlationSubscription' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<CorrelationSubscription> getCorrelationSubscriptions();

    /**
     * Returns the value of the '<em><b>Supports</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.bpmn2.Process}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Supports</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Supports</em>' reference list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getProcess_Supports()
     * @model ordered="false"
     *        extendedMetaData="kind='element' name='supports' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    List<Process> getSupports();

    /**
     * Returns the value of the '<em><b>Definitional Collaboration Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Definitional Collaboration Ref</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Definitional Collaboration Ref</em>' reference.
     * @see #setDefinitionalCollaborationRef(Collaboration)
     * @see org.eclipse.bpmn2.Bpmn2Package#getProcess_DefinitionalCollaborationRef()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='definitionalCollaborationRef'"
     * @generated
     */
    Collaboration getDefinitionalCollaborationRef();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Process#getDefinitionalCollaborationRef <em>Definitional Collaboration Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Definitional Collaboration Ref</em>' reference.
     * @see #getDefinitionalCollaborationRef()
     * @generated
     */
    void setDefinitionalCollaborationRef(Collaboration value);

    /**
     * Returns the value of the '<em><b>Is Closed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Closed</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Closed</em>' attribute.
     * @see #setIsClosed(boolean)
     * @see org.eclipse.bpmn2.Bpmn2Package#getProcess_IsClosed()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='isClosed'"
     * @generated
     */
    boolean isIsClosed();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Process#isIsClosed <em>Is Closed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Closed</em>' attribute.
     * @see #isIsClosed()
     * @generated
     */
    void setIsClosed(boolean value);

    /**
     * Returns the value of the '<em><b>Is Executable</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Executable</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Executable</em>' attribute.
     * @see #setIsExecutable(boolean)
     * @see org.eclipse.bpmn2.Bpmn2Package#getProcess_IsExecutable()
     * @model ordered="false"
     *        extendedMetaData="kind='attribute' name='isExecutable'"
     * @generated
     */
    boolean isIsExecutable();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Process#isIsExecutable <em>Is Executable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Executable</em>' attribute.
     * @see #isIsExecutable()
     * @generated
     */
    void setIsExecutable(boolean value);

    /**
     * Returns the value of the '<em><b>Process Type</b></em>' attribute.
     * The default value is <code>"None"</code>.
     * The literals are from the enumeration {@link org.eclipse.bpmn2.ProcessType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Process Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Process Type</em>' attribute.
     * @see org.eclipse.bpmn2.ProcessType
     * @see #setProcessType(ProcessType)
     * @see org.eclipse.bpmn2.Bpmn2Package#getProcess_ProcessType()
     * @model default="None" ordered="false"
     *        extendedMetaData="kind='attribute' name='processType'"
     * @generated
     */
    ProcessType getProcessType();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.Process#getProcessType <em>Process Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Process Type</em>' attribute.
     * @see org.eclipse.bpmn2.ProcessType
     * @see #getProcessType()
     * @generated
     */
    void setProcessType(ProcessType value);

} // Process
