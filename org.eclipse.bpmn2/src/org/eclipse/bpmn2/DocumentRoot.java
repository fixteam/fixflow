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

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getActivity <em>Activity</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getAdHocSubProcess <em>Ad Hoc Sub Process</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getFlowElement <em>Flow Element</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getArtifact <em>Artifact</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getAssignment <em>Assignment</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getAssociation <em>Association</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getAuditing <em>Auditing</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getBaseElement <em>Base Element</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getBaseElementWithMixedContent <em>Base Element With Mixed Content</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getBoundaryEvent <em>Boundary Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getBusinessRuleTask <em>Business Rule Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCallableElement <em>Callable Element</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCallActivity <em>Call Activity</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCallChoreography <em>Call Choreography</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCallConversation <em>Call Conversation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getConversationNode <em>Conversation Node</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCancelEventDefinition <em>Cancel Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getEventDefinition <em>Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getRootElement <em>Root Element</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCatchEvent <em>Catch Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCategory <em>Category</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCategoryValue <em>Category Value</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getChoreography <em>Choreography</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCollaboration <em>Collaboration</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getChoreographyActivity <em>Choreography Activity</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getChoreographyTask <em>Choreography Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCompensateEventDefinition <em>Compensate Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getComplexBehaviorDefinition <em>Complex Behavior Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getComplexGateway <em>Complex Gateway</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getConditionalEventDefinition <em>Conditional Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getConversation <em>Conversation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getConversationAssociation <em>Conversation Association</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getConversationLink <em>Conversation Link</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCorrelationKey <em>Correlation Key</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCorrelationProperty <em>Correlation Property</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCorrelationPropertyBinding <em>Correlation Property Binding</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCorrelationPropertyRetrievalExpression <em>Correlation Property Retrieval Expression</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getCorrelationSubscription <em>Correlation Subscription</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getDataAssociation <em>Data Association</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getDataInput <em>Data Input</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getDataInputAssociation <em>Data Input Association</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getDataObject <em>Data Object</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getDataObjectReference <em>Data Object Reference</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getDataOutput <em>Data Output</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getDataOutputAssociation <em>Data Output Association</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getDataState <em>Data State</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getDataStore <em>Data Store</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getDataStoreReference <em>Data Store Reference</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getDefinitions <em>Definitions</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getEndEvent <em>End Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getEndPoint <em>End Point</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getError <em>Error</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getErrorEventDefinition <em>Error Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getEscalation <em>Escalation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getEscalationEventDefinition <em>Escalation Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getEvent <em>Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getEventBasedGateway <em>Event Based Gateway</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getExclusiveGateway <em>Exclusive Gateway</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getExtension <em>Extension</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getExtensionElements <em>Extension Elements</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getFlowNode <em>Flow Node</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getFormalExpression <em>Formal Expression</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getGateway <em>Gateway</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getGlobalBusinessRuleTask <em>Global Business Rule Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getGlobalChoreographyTask <em>Global Choreography Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getGlobalConversation <em>Global Conversation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getGlobalManualTask <em>Global Manual Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getGlobalScriptTask <em>Global Script Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getGlobalTask <em>Global Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getGlobalUserTask <em>Global User Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getHumanPerformer <em>Human Performer</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getPerformer <em>Performer</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getResourceRole <em>Resource Role</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getImplicitThrowEvent <em>Implicit Throw Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getImport <em>Import</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getInclusiveGateway <em>Inclusive Gateway</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getInputSet <em>Input Set</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getInterface <em>Interface</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getIntermediateCatchEvent <em>Intermediate Catch Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getIntermediateThrowEvent <em>Intermediate Throw Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getIoBinding <em>Io Binding</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getIoSpecification <em>Io Specification</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getItemDefinition <em>Item Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getLane <em>Lane</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getLaneSet <em>Lane Set</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getLinkEventDefinition <em>Link Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getLoopCharacteristics <em>Loop Characteristics</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getManualTask <em>Manual Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getMessage <em>Message</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getMessageEventDefinition <em>Message Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getMessageFlow <em>Message Flow</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getMessageFlowAssociation <em>Message Flow Association</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getMonitoring <em>Monitoring</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getMultiInstanceLoopCharacteristics <em>Multi Instance Loop Characteristics</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getOperation <em>Operation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getOutputSet <em>Output Set</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getParallelGateway <em>Parallel Gateway</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getParticipant <em>Participant</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getParticipantAssociation <em>Participant Association</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getParticipantMultiplicity <em>Participant Multiplicity</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getPartnerEntity <em>Partner Entity</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getPartnerRole <em>Partner Role</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getPotentialOwner <em>Potential Owner</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getProcess <em>Process</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getReceiveTask <em>Receive Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getRelationship <em>Relationship</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getRendering <em>Rendering</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getResource <em>Resource</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getResourceAssignmentExpression <em>Resource Assignment Expression</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getResourceParameter <em>Resource Parameter</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getResourceParameterBinding <em>Resource Parameter Binding</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getScript <em>Script</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getScriptTask <em>Script Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getSendTask <em>Send Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getSequenceFlow <em>Sequence Flow</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getServiceTask <em>Service Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getSignal <em>Signal</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getSignalEventDefinition <em>Signal Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getStandardLoopCharacteristics <em>Standard Loop Characteristics</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getStartEvent <em>Start Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getSubChoreography <em>Sub Choreography</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getSubConversation <em>Sub Conversation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getSubProcess <em>Sub Process</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getTask <em>Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getTerminateEventDefinition <em>Terminate Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getText <em>Text</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getTextAnnotation <em>Text Annotation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getThrowEvent <em>Throw Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getTimerEventDefinition <em>Timer Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getTransaction <em>Transaction</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.DocumentRoot#getUserTask <em>User Task</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface DocumentRoot extends EObject {
    /**
     * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
     * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Mixed</em>' attribute list.
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Mixed()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
     *        extendedMetaData="kind='elementWildcard' name=':mixed'"
     * @generated
     */
    FeatureMap getMixed();

    /**
     * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link java.lang.String},
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>XMLNS Prefix Map</em>' map.
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_XMLNSPrefixMap()
     * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
     *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
     * @generated
     */
    Map<String, String> getXMLNSPrefixMap();

    /**
     * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link java.lang.String},
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>XSI Schema Location</em>' map.
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_XSISchemaLocation()
     * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
     *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
     * @generated
     */
    Map<String, String> getXSISchemaLocation();

    /**
     * Returns the value of the '<em><b>Activity</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Activity</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Activity</em>' containment reference.
     * @see #setActivity(Activity)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Activity()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='activity' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    Activity getActivity();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getActivity <em>Activity</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Activity</em>' containment reference.
     * @see #getActivity()
     * @generated
     */
    void setActivity(Activity value);

    /**
     * Returns the value of the '<em><b>Ad Hoc Sub Process</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ad Hoc Sub Process</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ad Hoc Sub Process</em>' containment reference.
     * @see #setAdHocSubProcess(AdHocSubProcess)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_AdHocSubProcess()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='adHocSubProcess' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    AdHocSubProcess getAdHocSubProcess();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getAdHocSubProcess <em>Ad Hoc Sub Process</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ad Hoc Sub Process</em>' containment reference.
     * @see #getAdHocSubProcess()
     * @generated
     */
    void setAdHocSubProcess(AdHocSubProcess value);

    /**
     * Returns the value of the '<em><b>Flow Element</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Flow Element</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Flow Element</em>' containment reference.
     * @see #setFlowElement(FlowElement)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_FlowElement()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='flowElement' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    FlowElement getFlowElement();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getFlowElement <em>Flow Element</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Flow Element</em>' containment reference.
     * @see #getFlowElement()
     * @generated
     */
    void setFlowElement(FlowElement value);

    /**
     * Returns the value of the '<em><b>Artifact</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Artifact</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Artifact</em>' containment reference.
     * @see #setArtifact(Artifact)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Artifact()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='artifact' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    Artifact getArtifact();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getArtifact <em>Artifact</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Artifact</em>' containment reference.
     * @see #getArtifact()
     * @generated
     */
    void setArtifact(Artifact value);

    /**
     * Returns the value of the '<em><b>Assignment</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Assignment</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Assignment</em>' containment reference.
     * @see #setAssignment(Assignment)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Assignment()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='assignment' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    Assignment getAssignment();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getAssignment <em>Assignment</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Assignment</em>' containment reference.
     * @see #getAssignment()
     * @generated
     */
    void setAssignment(Assignment value);

    /**
     * Returns the value of the '<em><b>Association</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Association</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Association</em>' containment reference.
     * @see #setAssociation(Association)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Association()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='association' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#artifact'"
     * @generated
     */
    Association getAssociation();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getAssociation <em>Association</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Association</em>' containment reference.
     * @see #getAssociation()
     * @generated
     */
    void setAssociation(Association value);

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
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Auditing()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='auditing' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    Auditing getAuditing();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getAuditing <em>Auditing</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Auditing</em>' containment reference.
     * @see #getAuditing()
     * @generated
     */
    void setAuditing(Auditing value);

    /**
     * Returns the value of the '<em><b>Base Element</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Base Element</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Base Element</em>' containment reference.
     * @see #setBaseElement(BaseElement)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_BaseElement()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='baseElement' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    BaseElement getBaseElement();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getBaseElement <em>Base Element</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Base Element</em>' containment reference.
     * @see #getBaseElement()
     * @generated
     */
    void setBaseElement(BaseElement value);

    /**
     * Returns the value of the '<em><b>Base Element With Mixed Content</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Base Element With Mixed Content</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Base Element With Mixed Content</em>' containment reference.
     * @see #setBaseElementWithMixedContent(BaseElement)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_BaseElementWithMixedContent()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='baseElementWithMixedContent' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    BaseElement getBaseElementWithMixedContent();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getBaseElementWithMixedContent <em>Base Element With Mixed Content</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Base Element With Mixed Content</em>' containment reference.
     * @see #getBaseElementWithMixedContent()
     * @generated
     */
    void setBaseElementWithMixedContent(BaseElement value);

    /**
     * Returns the value of the '<em><b>Boundary Event</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Boundary Event</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Boundary Event</em>' containment reference.
     * @see #setBoundaryEvent(BoundaryEvent)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_BoundaryEvent()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='boundaryEvent' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    BoundaryEvent getBoundaryEvent();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getBoundaryEvent <em>Boundary Event</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Boundary Event</em>' containment reference.
     * @see #getBoundaryEvent()
     * @generated
     */
    void setBoundaryEvent(BoundaryEvent value);

    /**
     * Returns the value of the '<em><b>Business Rule Task</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Business Rule Task</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Business Rule Task</em>' containment reference.
     * @see #setBusinessRuleTask(BusinessRuleTask)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_BusinessRuleTask()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='businessRuleTask' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    BusinessRuleTask getBusinessRuleTask();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getBusinessRuleTask <em>Business Rule Task</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Business Rule Task</em>' containment reference.
     * @see #getBusinessRuleTask()
     * @generated
     */
    void setBusinessRuleTask(BusinessRuleTask value);

    /**
     * Returns the value of the '<em><b>Callable Element</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Callable Element</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Callable Element</em>' containment reference.
     * @see #setCallableElement(CallableElement)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_CallableElement()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='callableElement' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    CallableElement getCallableElement();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getCallableElement <em>Callable Element</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Callable Element</em>' containment reference.
     * @see #getCallableElement()
     * @generated
     */
    void setCallableElement(CallableElement value);

    /**
     * Returns the value of the '<em><b>Call Activity</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Call Activity</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Call Activity</em>' containment reference.
     * @see #setCallActivity(CallActivity)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_CallActivity()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='callActivity' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    CallActivity getCallActivity();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getCallActivity <em>Call Activity</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Call Activity</em>' containment reference.
     * @see #getCallActivity()
     * @generated
     */
    void setCallActivity(CallActivity value);

    /**
     * Returns the value of the '<em><b>Call Choreography</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Call Choreography</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Call Choreography</em>' containment reference.
     * @see #setCallChoreography(CallChoreography)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_CallChoreography()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='callChoreography' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    CallChoreography getCallChoreography();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getCallChoreography <em>Call Choreography</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Call Choreography</em>' containment reference.
     * @see #getCallChoreography()
     * @generated
     */
    void setCallChoreography(CallChoreography value);

    /**
     * Returns the value of the '<em><b>Call Conversation</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Call Conversation</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Call Conversation</em>' containment reference.
     * @see #setCallConversation(CallConversation)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_CallConversation()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='callConversation' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#conversationNode'"
     * @generated
     */
    CallConversation getCallConversation();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getCallConversation <em>Call Conversation</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Call Conversation</em>' containment reference.
     * @see #getCallConversation()
     * @generated
     */
    void setCallConversation(CallConversation value);

    /**
     * Returns the value of the '<em><b>Conversation Node</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Conversation Node</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Conversation Node</em>' containment reference.
     * @see #setConversationNode(ConversationNode)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_ConversationNode()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='conversationNode' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    ConversationNode getConversationNode();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getConversationNode <em>Conversation Node</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Conversation Node</em>' containment reference.
     * @see #getConversationNode()
     * @generated
     */
    void setConversationNode(ConversationNode value);

    /**
     * Returns the value of the '<em><b>Cancel Event Definition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Cancel Event Definition</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Cancel Event Definition</em>' containment reference.
     * @see #setCancelEventDefinition(CancelEventDefinition)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_CancelEventDefinition()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='cancelEventDefinition' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#eventDefinition'"
     * @generated
     */
    CancelEventDefinition getCancelEventDefinition();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getCancelEventDefinition <em>Cancel Event Definition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Cancel Event Definition</em>' containment reference.
     * @see #getCancelEventDefinition()
     * @generated
     */
    void setCancelEventDefinition(CancelEventDefinition value);

    /**
     * Returns the value of the '<em><b>Event Definition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Event Definition</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Event Definition</em>' containment reference.
     * @see #setEventDefinition(EventDefinition)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_EventDefinition()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='eventDefinition' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#rootElement'"
     * @generated
     */
    EventDefinition getEventDefinition();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getEventDefinition <em>Event Definition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Event Definition</em>' containment reference.
     * @see #getEventDefinition()
     * @generated
     */
    void setEventDefinition(EventDefinition value);

    /**
     * Returns the value of the '<em><b>Root Element</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Root Element</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Root Element</em>' containment reference.
     * @see #setRootElement(RootElement)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_RootElement()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='rootElement' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    RootElement getRootElement();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getRootElement <em>Root Element</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Root Element</em>' containment reference.
     * @see #getRootElement()
     * @generated
     */
    void setRootElement(RootElement value);

    /**
     * Returns the value of the '<em><b>Catch Event</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Catch Event</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Catch Event</em>' containment reference.
     * @see #setCatchEvent(CatchEvent)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_CatchEvent()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='catchEvent' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    CatchEvent getCatchEvent();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getCatchEvent <em>Catch Event</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Catch Event</em>' containment reference.
     * @see #getCatchEvent()
     * @generated
     */
    void setCatchEvent(CatchEvent value);

    /**
     * Returns the value of the '<em><b>Category</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Category</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Category</em>' containment reference.
     * @see #setCategory(Category)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Category()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='category' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#rootElement'"
     * @generated
     */
    Category getCategory();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getCategory <em>Category</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Category</em>' containment reference.
     * @see #getCategory()
     * @generated
     */
    void setCategory(Category value);

    /**
     * Returns the value of the '<em><b>Category Value</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Category Value</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Category Value</em>' containment reference.
     * @see #setCategoryValue(CategoryValue)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_CategoryValue()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='categoryValue' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    CategoryValue getCategoryValue();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getCategoryValue <em>Category Value</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Category Value</em>' containment reference.
     * @see #getCategoryValue()
     * @generated
     */
    void setCategoryValue(CategoryValue value);

    /**
     * Returns the value of the '<em><b>Choreography</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Choreography</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Choreography</em>' containment reference.
     * @see #setChoreography(Choreography)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Choreography()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='choreography' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#collaboration'"
     * @generated
     */
    Choreography getChoreography();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getChoreography <em>Choreography</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Choreography</em>' containment reference.
     * @see #getChoreography()
     * @generated
     */
    void setChoreography(Choreography value);

    /**
     * Returns the value of the '<em><b>Collaboration</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Collaboration</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Collaboration</em>' containment reference.
     * @see #setCollaboration(Collaboration)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Collaboration()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='collaboration' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#rootElement'"
     * @generated
     */
    Collaboration getCollaboration();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getCollaboration <em>Collaboration</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Collaboration</em>' containment reference.
     * @see #getCollaboration()
     * @generated
     */
    void setCollaboration(Collaboration value);

    /**
     * Returns the value of the '<em><b>Choreography Activity</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Choreography Activity</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Choreography Activity</em>' containment reference.
     * @see #setChoreographyActivity(ChoreographyActivity)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_ChoreographyActivity()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='choreographyActivity' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    ChoreographyActivity getChoreographyActivity();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getChoreographyActivity <em>Choreography Activity</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Choreography Activity</em>' containment reference.
     * @see #getChoreographyActivity()
     * @generated
     */
    void setChoreographyActivity(ChoreographyActivity value);

    /**
     * Returns the value of the '<em><b>Choreography Task</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Choreography Task</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Choreography Task</em>' containment reference.
     * @see #setChoreographyTask(ChoreographyTask)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_ChoreographyTask()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='choreographyTask' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    ChoreographyTask getChoreographyTask();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getChoreographyTask <em>Choreography Task</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Choreography Task</em>' containment reference.
     * @see #getChoreographyTask()
     * @generated
     */
    void setChoreographyTask(ChoreographyTask value);

    /**
     * Returns the value of the '<em><b>Compensate Event Definition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Compensate Event Definition</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Compensate Event Definition</em>' containment reference.
     * @see #setCompensateEventDefinition(CompensateEventDefinition)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_CompensateEventDefinition()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='compensateEventDefinition' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#eventDefinition'"
     * @generated
     */
    CompensateEventDefinition getCompensateEventDefinition();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getCompensateEventDefinition <em>Compensate Event Definition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Compensate Event Definition</em>' containment reference.
     * @see #getCompensateEventDefinition()
     * @generated
     */
    void setCompensateEventDefinition(CompensateEventDefinition value);

    /**
     * Returns the value of the '<em><b>Complex Behavior Definition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Complex Behavior Definition</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Complex Behavior Definition</em>' containment reference.
     * @see #setComplexBehaviorDefinition(ComplexBehaviorDefinition)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_ComplexBehaviorDefinition()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='complexBehaviorDefinition' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    ComplexBehaviorDefinition getComplexBehaviorDefinition();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getComplexBehaviorDefinition <em>Complex Behavior Definition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Complex Behavior Definition</em>' containment reference.
     * @see #getComplexBehaviorDefinition()
     * @generated
     */
    void setComplexBehaviorDefinition(ComplexBehaviorDefinition value);

    /**
     * Returns the value of the '<em><b>Complex Gateway</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Complex Gateway</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Complex Gateway</em>' containment reference.
     * @see #setComplexGateway(ComplexGateway)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_ComplexGateway()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='complexGateway' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    ComplexGateway getComplexGateway();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getComplexGateway <em>Complex Gateway</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Complex Gateway</em>' containment reference.
     * @see #getComplexGateway()
     * @generated
     */
    void setComplexGateway(ComplexGateway value);

    /**
     * Returns the value of the '<em><b>Conditional Event Definition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Conditional Event Definition</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Conditional Event Definition</em>' containment reference.
     * @see #setConditionalEventDefinition(ConditionalEventDefinition)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_ConditionalEventDefinition()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='conditionalEventDefinition' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#eventDefinition'"
     * @generated
     */
    ConditionalEventDefinition getConditionalEventDefinition();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getConditionalEventDefinition <em>Conditional Event Definition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Conditional Event Definition</em>' containment reference.
     * @see #getConditionalEventDefinition()
     * @generated
     */
    void setConditionalEventDefinition(ConditionalEventDefinition value);

    /**
     * Returns the value of the '<em><b>Conversation</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Conversation</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Conversation</em>' containment reference.
     * @see #setConversation(Conversation)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Conversation()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='conversation' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#conversationNode'"
     * @generated
     */
    Conversation getConversation();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getConversation <em>Conversation</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Conversation</em>' containment reference.
     * @see #getConversation()
     * @generated
     */
    void setConversation(Conversation value);

    /**
     * Returns the value of the '<em><b>Conversation Association</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Conversation Association</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Conversation Association</em>' containment reference.
     * @see #setConversationAssociation(ConversationAssociation)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_ConversationAssociation()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='conversationAssociation' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    ConversationAssociation getConversationAssociation();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getConversationAssociation <em>Conversation Association</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Conversation Association</em>' containment reference.
     * @see #getConversationAssociation()
     * @generated
     */
    void setConversationAssociation(ConversationAssociation value);

    /**
     * Returns the value of the '<em><b>Conversation Link</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Conversation Link</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Conversation Link</em>' containment reference.
     * @see #setConversationLink(ConversationLink)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_ConversationLink()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='conversationLink' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    ConversationLink getConversationLink();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getConversationLink <em>Conversation Link</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Conversation Link</em>' containment reference.
     * @see #getConversationLink()
     * @generated
     */
    void setConversationLink(ConversationLink value);

    /**
     * Returns the value of the '<em><b>Correlation Key</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Correlation Key</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Correlation Key</em>' containment reference.
     * @see #setCorrelationKey(CorrelationKey)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_CorrelationKey()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='correlationKey' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    CorrelationKey getCorrelationKey();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getCorrelationKey <em>Correlation Key</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Correlation Key</em>' containment reference.
     * @see #getCorrelationKey()
     * @generated
     */
    void setCorrelationKey(CorrelationKey value);

    /**
     * Returns the value of the '<em><b>Correlation Property</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Correlation Property</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Correlation Property</em>' containment reference.
     * @see #setCorrelationProperty(CorrelationProperty)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_CorrelationProperty()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='correlationProperty' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#rootElement'"
     * @generated
     */
    CorrelationProperty getCorrelationProperty();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getCorrelationProperty <em>Correlation Property</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Correlation Property</em>' containment reference.
     * @see #getCorrelationProperty()
     * @generated
     */
    void setCorrelationProperty(CorrelationProperty value);

    /**
     * Returns the value of the '<em><b>Correlation Property Binding</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Correlation Property Binding</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Correlation Property Binding</em>' containment reference.
     * @see #setCorrelationPropertyBinding(CorrelationPropertyBinding)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_CorrelationPropertyBinding()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='correlationPropertyBinding' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    CorrelationPropertyBinding getCorrelationPropertyBinding();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getCorrelationPropertyBinding <em>Correlation Property Binding</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Correlation Property Binding</em>' containment reference.
     * @see #getCorrelationPropertyBinding()
     * @generated
     */
    void setCorrelationPropertyBinding(CorrelationPropertyBinding value);

    /**
     * Returns the value of the '<em><b>Correlation Property Retrieval Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Correlation Property Retrieval Expression</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Correlation Property Retrieval Expression</em>' containment reference.
     * @see #setCorrelationPropertyRetrievalExpression(CorrelationPropertyRetrievalExpression)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_CorrelationPropertyRetrievalExpression()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='correlationPropertyRetrievalExpression' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    CorrelationPropertyRetrievalExpression getCorrelationPropertyRetrievalExpression();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getCorrelationPropertyRetrievalExpression <em>Correlation Property Retrieval Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Correlation Property Retrieval Expression</em>' containment reference.
     * @see #getCorrelationPropertyRetrievalExpression()
     * @generated
     */
    void setCorrelationPropertyRetrievalExpression(CorrelationPropertyRetrievalExpression value);

    /**
     * Returns the value of the '<em><b>Correlation Subscription</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Correlation Subscription</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Correlation Subscription</em>' containment reference.
     * @see #setCorrelationSubscription(CorrelationSubscription)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_CorrelationSubscription()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='correlationSubscription' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    CorrelationSubscription getCorrelationSubscription();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getCorrelationSubscription <em>Correlation Subscription</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Correlation Subscription</em>' containment reference.
     * @see #getCorrelationSubscription()
     * @generated
     */
    void setCorrelationSubscription(CorrelationSubscription value);

    /**
     * Returns the value of the '<em><b>Data Association</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data Association</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Association</em>' containment reference.
     * @see #setDataAssociation(DataAssociation)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_DataAssociation()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='dataAssociation' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    DataAssociation getDataAssociation();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getDataAssociation <em>Data Association</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data Association</em>' containment reference.
     * @see #getDataAssociation()
     * @generated
     */
    void setDataAssociation(DataAssociation value);

    /**
     * Returns the value of the '<em><b>Data Input</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data Input</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Input</em>' containment reference.
     * @see #setDataInput(DataInput)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_DataInput()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='dataInput' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    DataInput getDataInput();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getDataInput <em>Data Input</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data Input</em>' containment reference.
     * @see #getDataInput()
     * @generated
     */
    void setDataInput(DataInput value);

    /**
     * Returns the value of the '<em><b>Data Input Association</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data Input Association</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Input Association</em>' containment reference.
     * @see #setDataInputAssociation(DataInputAssociation)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_DataInputAssociation()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='dataInputAssociation' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    DataInputAssociation getDataInputAssociation();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getDataInputAssociation <em>Data Input Association</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data Input Association</em>' containment reference.
     * @see #getDataInputAssociation()
     * @generated
     */
    void setDataInputAssociation(DataInputAssociation value);

    /**
     * Returns the value of the '<em><b>Data Object</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data Object</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Object</em>' containment reference.
     * @see #setDataObject(DataObject)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_DataObject()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='dataObject' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    DataObject getDataObject();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getDataObject <em>Data Object</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data Object</em>' containment reference.
     * @see #getDataObject()
     * @generated
     */
    void setDataObject(DataObject value);

    /**
     * Returns the value of the '<em><b>Data Object Reference</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data Object Reference</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Object Reference</em>' containment reference.
     * @see #setDataObjectReference(DataObjectReference)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_DataObjectReference()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='dataObjectReference' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    DataObjectReference getDataObjectReference();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getDataObjectReference <em>Data Object Reference</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data Object Reference</em>' containment reference.
     * @see #getDataObjectReference()
     * @generated
     */
    void setDataObjectReference(DataObjectReference value);

    /**
     * Returns the value of the '<em><b>Data Output</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data Output</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Output</em>' containment reference.
     * @see #setDataOutput(DataOutput)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_DataOutput()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='dataOutput' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    DataOutput getDataOutput();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getDataOutput <em>Data Output</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data Output</em>' containment reference.
     * @see #getDataOutput()
     * @generated
     */
    void setDataOutput(DataOutput value);

    /**
     * Returns the value of the '<em><b>Data Output Association</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data Output Association</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Output Association</em>' containment reference.
     * @see #setDataOutputAssociation(DataOutputAssociation)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_DataOutputAssociation()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='dataOutputAssociation' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    DataOutputAssociation getDataOutputAssociation();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getDataOutputAssociation <em>Data Output Association</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data Output Association</em>' containment reference.
     * @see #getDataOutputAssociation()
     * @generated
     */
    void setDataOutputAssociation(DataOutputAssociation value);

    /**
     * Returns the value of the '<em><b>Data State</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data State</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data State</em>' containment reference.
     * @see #setDataState(DataState)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_DataState()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='dataState' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    DataState getDataState();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getDataState <em>Data State</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data State</em>' containment reference.
     * @see #getDataState()
     * @generated
     */
    void setDataState(DataState value);

    /**
     * Returns the value of the '<em><b>Data Store</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data Store</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Store</em>' containment reference.
     * @see #setDataStore(DataStore)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_DataStore()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='dataStore' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#rootElement'"
     * @generated
     */
    DataStore getDataStore();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getDataStore <em>Data Store</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data Store</em>' containment reference.
     * @see #getDataStore()
     * @generated
     */
    void setDataStore(DataStore value);

    /**
     * Returns the value of the '<em><b>Data Store Reference</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data Store Reference</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Store Reference</em>' containment reference.
     * @see #setDataStoreReference(DataStoreReference)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_DataStoreReference()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='dataStoreReference' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    DataStoreReference getDataStoreReference();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getDataStoreReference <em>Data Store Reference</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data Store Reference</em>' containment reference.
     * @see #getDataStoreReference()
     * @generated
     */
    void setDataStoreReference(DataStoreReference value);

    /**
     * Returns the value of the '<em><b>Definitions</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Definitions</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Definitions</em>' containment reference.
     * @see #setDefinitions(Definitions)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Definitions()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='definitions' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    Definitions getDefinitions();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getDefinitions <em>Definitions</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Definitions</em>' containment reference.
     * @see #getDefinitions()
     * @generated
     */
    void setDefinitions(Definitions value);

    /**
     * Returns the value of the '<em><b>Documentation</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Documentation</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Documentation</em>' containment reference.
     * @see #setDocumentation(Documentation)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Documentation()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='documentation' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    Documentation getDocumentation();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getDocumentation <em>Documentation</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Documentation</em>' containment reference.
     * @see #getDocumentation()
     * @generated
     */
    void setDocumentation(Documentation value);

    /**
     * Returns the value of the '<em><b>End Event</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>End Event</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>End Event</em>' containment reference.
     * @see #setEndEvent(EndEvent)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_EndEvent()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='endEvent' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    EndEvent getEndEvent();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getEndEvent <em>End Event</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>End Event</em>' containment reference.
     * @see #getEndEvent()
     * @generated
     */
    void setEndEvent(EndEvent value);

    /**
     * Returns the value of the '<em><b>End Point</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>End Point</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>End Point</em>' containment reference.
     * @see #setEndPoint(EndPoint)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_EndPoint()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='endPoint' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#rootElement'"
     * @generated
     */
    EndPoint getEndPoint();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getEndPoint <em>End Point</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>End Point</em>' containment reference.
     * @see #getEndPoint()
     * @generated
     */
    void setEndPoint(EndPoint value);

    /**
     * Returns the value of the '<em><b>Error</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Error</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Error</em>' containment reference.
     * @see #setError(org.eclipse.bpmn2.Error)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Error()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='error' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#rootElement'"
     * @generated
     */
    org.eclipse.bpmn2.Error getError();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getError <em>Error</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Error</em>' containment reference.
     * @see #getError()
     * @generated
     */
    void setError(org.eclipse.bpmn2.Error value);

    /**
     * Returns the value of the '<em><b>Error Event Definition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Error Event Definition</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Error Event Definition</em>' containment reference.
     * @see #setErrorEventDefinition(ErrorEventDefinition)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_ErrorEventDefinition()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='errorEventDefinition' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#eventDefinition'"
     * @generated
     */
    ErrorEventDefinition getErrorEventDefinition();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getErrorEventDefinition <em>Error Event Definition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Error Event Definition</em>' containment reference.
     * @see #getErrorEventDefinition()
     * @generated
     */
    void setErrorEventDefinition(ErrorEventDefinition value);

    /**
     * Returns the value of the '<em><b>Escalation</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Escalation</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Escalation</em>' containment reference.
     * @see #setEscalation(Escalation)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Escalation()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='escalation' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#rootElement'"
     * @generated
     */
    Escalation getEscalation();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getEscalation <em>Escalation</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Escalation</em>' containment reference.
     * @see #getEscalation()
     * @generated
     */
    void setEscalation(Escalation value);

    /**
     * Returns the value of the '<em><b>Escalation Event Definition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Escalation Event Definition</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Escalation Event Definition</em>' containment reference.
     * @see #setEscalationEventDefinition(EscalationEventDefinition)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_EscalationEventDefinition()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='escalationEventDefinition' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#eventDefinition'"
     * @generated
     */
    EscalationEventDefinition getEscalationEventDefinition();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getEscalationEventDefinition <em>Escalation Event Definition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Escalation Event Definition</em>' containment reference.
     * @see #getEscalationEventDefinition()
     * @generated
     */
    void setEscalationEventDefinition(EscalationEventDefinition value);

    /**
     * Returns the value of the '<em><b>Event</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Event</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Event</em>' containment reference.
     * @see #setEvent(Event)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Event()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='event' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    Event getEvent();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getEvent <em>Event</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Event</em>' containment reference.
     * @see #getEvent()
     * @generated
     */
    void setEvent(Event value);

    /**
     * Returns the value of the '<em><b>Event Based Gateway</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Event Based Gateway</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Event Based Gateway</em>' containment reference.
     * @see #setEventBasedGateway(EventBasedGateway)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_EventBasedGateway()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='eventBasedGateway' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    EventBasedGateway getEventBasedGateway();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getEventBasedGateway <em>Event Based Gateway</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Event Based Gateway</em>' containment reference.
     * @see #getEventBasedGateway()
     * @generated
     */
    void setEventBasedGateway(EventBasedGateway value);

    /**
     * Returns the value of the '<em><b>Exclusive Gateway</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Exclusive Gateway</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Exclusive Gateway</em>' containment reference.
     * @see #setExclusiveGateway(ExclusiveGateway)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_ExclusiveGateway()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='exclusiveGateway' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    ExclusiveGateway getExclusiveGateway();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getExclusiveGateway <em>Exclusive Gateway</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Exclusive Gateway</em>' containment reference.
     * @see #getExclusiveGateway()
     * @generated
     */
    void setExclusiveGateway(ExclusiveGateway value);

    /**
     * Returns the value of the '<em><b>Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Expression</em>' containment reference.
     * @see #setExpression(Expression)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Expression()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='expression' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    Expression getExpression();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getExpression <em>Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Expression</em>' containment reference.
     * @see #getExpression()
     * @generated
     */
    void setExpression(Expression value);

    /**
     * Returns the value of the '<em><b>Extension</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extension</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Extension</em>' containment reference.
     * @see #setExtension(Extension)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Extension()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='extension' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    Extension getExtension();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getExtension <em>Extension</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Extension</em>' containment reference.
     * @see #getExtension()
     * @generated
     */
    void setExtension(Extension value);

    /**
     * Returns the value of the '<em><b>Extension Elements</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extension Elements</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Extension Elements</em>' containment reference.
     * @see #setExtensionElements(ExtensionAttributeValue)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_ExtensionElements()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='extensionElements' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    ExtensionAttributeValue getExtensionElements();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getExtensionElements <em>Extension Elements</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Extension Elements</em>' containment reference.
     * @see #getExtensionElements()
     * @generated
     */
    void setExtensionElements(ExtensionAttributeValue value);

    /**
     * Returns the value of the '<em><b>Flow Node</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Flow Node</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Flow Node</em>' containment reference.
     * @see #setFlowNode(FlowNode)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_FlowNode()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='flowNode' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    FlowNode getFlowNode();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getFlowNode <em>Flow Node</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Flow Node</em>' containment reference.
     * @see #getFlowNode()
     * @generated
     */
    void setFlowNode(FlowNode value);

    /**
     * Returns the value of the '<em><b>Formal Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Formal Expression</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Formal Expression</em>' containment reference.
     * @see #setFormalExpression(FormalExpression)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_FormalExpression()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='formalExpression' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#expression'"
     * @generated
     */
    FormalExpression getFormalExpression();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getFormalExpression <em>Formal Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Formal Expression</em>' containment reference.
     * @see #getFormalExpression()
     * @generated
     */
    void setFormalExpression(FormalExpression value);

    /**
     * Returns the value of the '<em><b>Gateway</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Gateway</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Gateway</em>' containment reference.
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Gateway()
     * @model containment="true" upper="-2" transient="true" changeable="false" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='gateway' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    Gateway getGateway();

    /**
     * Returns the value of the '<em><b>Global Business Rule Task</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Global Business Rule Task</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Global Business Rule Task</em>' containment reference.
     * @see #setGlobalBusinessRuleTask(GlobalBusinessRuleTask)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_GlobalBusinessRuleTask()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='globalBusinessRuleTask' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#rootElement'"
     * @generated
     */
    GlobalBusinessRuleTask getGlobalBusinessRuleTask();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getGlobalBusinessRuleTask <em>Global Business Rule Task</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Global Business Rule Task</em>' containment reference.
     * @see #getGlobalBusinessRuleTask()
     * @generated
     */
    void setGlobalBusinessRuleTask(GlobalBusinessRuleTask value);

    /**
     * Returns the value of the '<em><b>Global Choreography Task</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Global Choreography Task</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Global Choreography Task</em>' containment reference.
     * @see #setGlobalChoreographyTask(GlobalChoreographyTask)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_GlobalChoreographyTask()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='globalChoreographyTask' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#choreography'"
     * @generated
     */
    GlobalChoreographyTask getGlobalChoreographyTask();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getGlobalChoreographyTask <em>Global Choreography Task</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Global Choreography Task</em>' containment reference.
     * @see #getGlobalChoreographyTask()
     * @generated
     */
    void setGlobalChoreographyTask(GlobalChoreographyTask value);

    /**
     * Returns the value of the '<em><b>Global Conversation</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Global Conversation</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Global Conversation</em>' containment reference.
     * @see #setGlobalConversation(GlobalConversation)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_GlobalConversation()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='globalConversation' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#collaboration'"
     * @generated
     */
    GlobalConversation getGlobalConversation();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getGlobalConversation <em>Global Conversation</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Global Conversation</em>' containment reference.
     * @see #getGlobalConversation()
     * @generated
     */
    void setGlobalConversation(GlobalConversation value);

    /**
     * Returns the value of the '<em><b>Global Manual Task</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Global Manual Task</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Global Manual Task</em>' containment reference.
     * @see #setGlobalManualTask(GlobalManualTask)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_GlobalManualTask()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='globalManualTask' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#rootElement'"
     * @generated
     */
    GlobalManualTask getGlobalManualTask();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getGlobalManualTask <em>Global Manual Task</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Global Manual Task</em>' containment reference.
     * @see #getGlobalManualTask()
     * @generated
     */
    void setGlobalManualTask(GlobalManualTask value);

    /**
     * Returns the value of the '<em><b>Global Script Task</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Global Script Task</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Global Script Task</em>' containment reference.
     * @see #setGlobalScriptTask(GlobalScriptTask)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_GlobalScriptTask()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='globalScriptTask' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#rootElement'"
     * @generated
     */
    GlobalScriptTask getGlobalScriptTask();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getGlobalScriptTask <em>Global Script Task</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Global Script Task</em>' containment reference.
     * @see #getGlobalScriptTask()
     * @generated
     */
    void setGlobalScriptTask(GlobalScriptTask value);

    /**
     * Returns the value of the '<em><b>Global Task</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Global Task</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Global Task</em>' containment reference.
     * @see #setGlobalTask(GlobalTask)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_GlobalTask()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='globalTask' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#rootElement'"
     * @generated
     */
    GlobalTask getGlobalTask();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getGlobalTask <em>Global Task</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Global Task</em>' containment reference.
     * @see #getGlobalTask()
     * @generated
     */
    void setGlobalTask(GlobalTask value);

    /**
     * Returns the value of the '<em><b>Global User Task</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Global User Task</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Global User Task</em>' containment reference.
     * @see #setGlobalUserTask(GlobalUserTask)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_GlobalUserTask()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='globalUserTask' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#rootElement'"
     * @generated
     */
    GlobalUserTask getGlobalUserTask();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getGlobalUserTask <em>Global User Task</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Global User Task</em>' containment reference.
     * @see #getGlobalUserTask()
     * @generated
     */
    void setGlobalUserTask(GlobalUserTask value);

    /**
     * Returns the value of the '<em><b>Group</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Group</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Group</em>' containment reference.
     * @see #setGroup(Group)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Group()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='group' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#artifact'"
     * @generated
     */
    Group getGroup();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getGroup <em>Group</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Group</em>' containment reference.
     * @see #getGroup()
     * @generated
     */
    void setGroup(Group value);

    /**
     * Returns the value of the '<em><b>Human Performer</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Human Performer</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Human Performer</em>' containment reference.
     * @see #setHumanPerformer(HumanPerformer)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_HumanPerformer()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='humanPerformer' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#performer'"
     * @generated
     */
    HumanPerformer getHumanPerformer();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getHumanPerformer <em>Human Performer</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Human Performer</em>' containment reference.
     * @see #getHumanPerformer()
     * @generated
     */
    void setHumanPerformer(HumanPerformer value);

    /**
     * Returns the value of the '<em><b>Performer</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Performer</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Performer</em>' containment reference.
     * @see #setPerformer(Performer)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Performer()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='performer' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#resourceRole'"
     * @generated
     */
    Performer getPerformer();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getPerformer <em>Performer</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Performer</em>' containment reference.
     * @see #getPerformer()
     * @generated
     */
    void setPerformer(Performer value);

    /**
     * Returns the value of the '<em><b>Resource Role</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resource Role</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Resource Role</em>' containment reference.
     * @see #setResourceRole(ResourceRole)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_ResourceRole()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='resourceRole' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    ResourceRole getResourceRole();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getResourceRole <em>Resource Role</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Resource Role</em>' containment reference.
     * @see #getResourceRole()
     * @generated
     */
    void setResourceRole(ResourceRole value);

    /**
     * Returns the value of the '<em><b>Implicit Throw Event</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Implicit Throw Event</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Implicit Throw Event</em>' containment reference.
     * @see #setImplicitThrowEvent(ImplicitThrowEvent)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_ImplicitThrowEvent()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='implicitThrowEvent' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    ImplicitThrowEvent getImplicitThrowEvent();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getImplicitThrowEvent <em>Implicit Throw Event</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Implicit Throw Event</em>' containment reference.
     * @see #getImplicitThrowEvent()
     * @generated
     */
    void setImplicitThrowEvent(ImplicitThrowEvent value);

    /**
     * Returns the value of the '<em><b>Import</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Import</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Import</em>' containment reference.
     * @see #setImport(Import)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Import()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='import' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    Import getImport();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getImport <em>Import</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Import</em>' containment reference.
     * @see #getImport()
     * @generated
     */
    void setImport(Import value);

    /**
     * Returns the value of the '<em><b>Inclusive Gateway</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Inclusive Gateway</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Inclusive Gateway</em>' containment reference.
     * @see #setInclusiveGateway(InclusiveGateway)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_InclusiveGateway()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='inclusiveGateway' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    InclusiveGateway getInclusiveGateway();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getInclusiveGateway <em>Inclusive Gateway</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Inclusive Gateway</em>' containment reference.
     * @see #getInclusiveGateway()
     * @generated
     */
    void setInclusiveGateway(InclusiveGateway value);

    /**
     * Returns the value of the '<em><b>Input Set</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Input Set</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Input Set</em>' containment reference.
     * @see #setInputSet(InputSet)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_InputSet()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='inputSet' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    InputSet getInputSet();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getInputSet <em>Input Set</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Input Set</em>' containment reference.
     * @see #getInputSet()
     * @generated
     */
    void setInputSet(InputSet value);

    /**
     * Returns the value of the '<em><b>Interface</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Interface</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Interface</em>' containment reference.
     * @see #setInterface(Interface)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Interface()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='interface' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#rootElement'"
     * @generated
     */
    Interface getInterface();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getInterface <em>Interface</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Interface</em>' containment reference.
     * @see #getInterface()
     * @generated
     */
    void setInterface(Interface value);

    /**
     * Returns the value of the '<em><b>Intermediate Catch Event</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Intermediate Catch Event</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Intermediate Catch Event</em>' containment reference.
     * @see #setIntermediateCatchEvent(IntermediateCatchEvent)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_IntermediateCatchEvent()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='intermediateCatchEvent' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    IntermediateCatchEvent getIntermediateCatchEvent();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getIntermediateCatchEvent <em>Intermediate Catch Event</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Intermediate Catch Event</em>' containment reference.
     * @see #getIntermediateCatchEvent()
     * @generated
     */
    void setIntermediateCatchEvent(IntermediateCatchEvent value);

    /**
     * Returns the value of the '<em><b>Intermediate Throw Event</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Intermediate Throw Event</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Intermediate Throw Event</em>' containment reference.
     * @see #setIntermediateThrowEvent(IntermediateThrowEvent)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_IntermediateThrowEvent()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='intermediateThrowEvent' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    IntermediateThrowEvent getIntermediateThrowEvent();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getIntermediateThrowEvent <em>Intermediate Throw Event</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Intermediate Throw Event</em>' containment reference.
     * @see #getIntermediateThrowEvent()
     * @generated
     */
    void setIntermediateThrowEvent(IntermediateThrowEvent value);

    /**
     * Returns the value of the '<em><b>Io Binding</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Io Binding</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Io Binding</em>' containment reference.
     * @see #setIoBinding(InputOutputBinding)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_IoBinding()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='ioBinding' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    InputOutputBinding getIoBinding();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getIoBinding <em>Io Binding</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Io Binding</em>' containment reference.
     * @see #getIoBinding()
     * @generated
     */
    void setIoBinding(InputOutputBinding value);

    /**
     * Returns the value of the '<em><b>Io Specification</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Io Specification</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Io Specification</em>' containment reference.
     * @see #setIoSpecification(InputOutputSpecification)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_IoSpecification()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='ioSpecification' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    InputOutputSpecification getIoSpecification();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getIoSpecification <em>Io Specification</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Io Specification</em>' containment reference.
     * @see #getIoSpecification()
     * @generated
     */
    void setIoSpecification(InputOutputSpecification value);

    /**
     * Returns the value of the '<em><b>Item Definition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Item Definition</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Item Definition</em>' containment reference.
     * @see #setItemDefinition(ItemDefinition)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_ItemDefinition()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='itemDefinition' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#rootElement'"
     * @generated
     */
    ItemDefinition getItemDefinition();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getItemDefinition <em>Item Definition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Item Definition</em>' containment reference.
     * @see #getItemDefinition()
     * @generated
     */
    void setItemDefinition(ItemDefinition value);

    /**
     * Returns the value of the '<em><b>Lane</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Lane</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Lane</em>' containment reference.
     * @see #setLane(Lane)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Lane()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='lane' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    Lane getLane();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getLane <em>Lane</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Lane</em>' containment reference.
     * @see #getLane()
     * @generated
     */
    void setLane(Lane value);

    /**
     * Returns the value of the '<em><b>Lane Set</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Lane Set</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Lane Set</em>' containment reference.
     * @see #setLaneSet(LaneSet)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_LaneSet()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='laneSet' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    LaneSet getLaneSet();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getLaneSet <em>Lane Set</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Lane Set</em>' containment reference.
     * @see #getLaneSet()
     * @generated
     */
    void setLaneSet(LaneSet value);

    /**
     * Returns the value of the '<em><b>Link Event Definition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Link Event Definition</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Link Event Definition</em>' containment reference.
     * @see #setLinkEventDefinition(LinkEventDefinition)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_LinkEventDefinition()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='linkEventDefinition' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#eventDefinition'"
     * @generated
     */
    LinkEventDefinition getLinkEventDefinition();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getLinkEventDefinition <em>Link Event Definition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Link Event Definition</em>' containment reference.
     * @see #getLinkEventDefinition()
     * @generated
     */
    void setLinkEventDefinition(LinkEventDefinition value);

    /**
     * Returns the value of the '<em><b>Loop Characteristics</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Loop Characteristics</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Loop Characteristics</em>' containment reference.
     * @see #setLoopCharacteristics(LoopCharacteristics)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_LoopCharacteristics()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='loopCharacteristics' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    LoopCharacteristics getLoopCharacteristics();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getLoopCharacteristics <em>Loop Characteristics</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Loop Characteristics</em>' containment reference.
     * @see #getLoopCharacteristics()
     * @generated
     */
    void setLoopCharacteristics(LoopCharacteristics value);

    /**
     * Returns the value of the '<em><b>Manual Task</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Manual Task</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Manual Task</em>' containment reference.
     * @see #setManualTask(ManualTask)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_ManualTask()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='manualTask' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    ManualTask getManualTask();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getManualTask <em>Manual Task</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Manual Task</em>' containment reference.
     * @see #getManualTask()
     * @generated
     */
    void setManualTask(ManualTask value);

    /**
     * Returns the value of the '<em><b>Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Message</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Message</em>' containment reference.
     * @see #setMessage(Message)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Message()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='message' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#rootElement'"
     * @generated
     */
    Message getMessage();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getMessage <em>Message</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Message</em>' containment reference.
     * @see #getMessage()
     * @generated
     */
    void setMessage(Message value);

    /**
     * Returns the value of the '<em><b>Message Event Definition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Message Event Definition</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Message Event Definition</em>' containment reference.
     * @see #setMessageEventDefinition(MessageEventDefinition)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_MessageEventDefinition()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='messageEventDefinition' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#eventDefinition'"
     * @generated
     */
    MessageEventDefinition getMessageEventDefinition();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getMessageEventDefinition <em>Message Event Definition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Message Event Definition</em>' containment reference.
     * @see #getMessageEventDefinition()
     * @generated
     */
    void setMessageEventDefinition(MessageEventDefinition value);

    /**
     * Returns the value of the '<em><b>Message Flow</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Message Flow</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Message Flow</em>' containment reference.
     * @see #setMessageFlow(MessageFlow)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_MessageFlow()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='messageFlow' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    MessageFlow getMessageFlow();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getMessageFlow <em>Message Flow</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Message Flow</em>' containment reference.
     * @see #getMessageFlow()
     * @generated
     */
    void setMessageFlow(MessageFlow value);

    /**
     * Returns the value of the '<em><b>Message Flow Association</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Message Flow Association</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Message Flow Association</em>' containment reference.
     * @see #setMessageFlowAssociation(MessageFlowAssociation)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_MessageFlowAssociation()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='messageFlowAssociation' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    MessageFlowAssociation getMessageFlowAssociation();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getMessageFlowAssociation <em>Message Flow Association</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Message Flow Association</em>' containment reference.
     * @see #getMessageFlowAssociation()
     * @generated
     */
    void setMessageFlowAssociation(MessageFlowAssociation value);

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
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Monitoring()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='monitoring' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    Monitoring getMonitoring();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getMonitoring <em>Monitoring</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Monitoring</em>' containment reference.
     * @see #getMonitoring()
     * @generated
     */
    void setMonitoring(Monitoring value);

    /**
     * Returns the value of the '<em><b>Multi Instance Loop Characteristics</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Multi Instance Loop Characteristics</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Multi Instance Loop Characteristics</em>' containment reference.
     * @see #setMultiInstanceLoopCharacteristics(MultiInstanceLoopCharacteristics)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_MultiInstanceLoopCharacteristics()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='multiInstanceLoopCharacteristics' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#loopCharacteristics'"
     * @generated
     */
    MultiInstanceLoopCharacteristics getMultiInstanceLoopCharacteristics();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getMultiInstanceLoopCharacteristics <em>Multi Instance Loop Characteristics</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Multi Instance Loop Characteristics</em>' containment reference.
     * @see #getMultiInstanceLoopCharacteristics()
     * @generated
     */
    void setMultiInstanceLoopCharacteristics(MultiInstanceLoopCharacteristics value);

    /**
     * Returns the value of the '<em><b>Operation</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Operation</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Operation</em>' containment reference.
     * @see #setOperation(Operation)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Operation()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='operation' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    Operation getOperation();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getOperation <em>Operation</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Operation</em>' containment reference.
     * @see #getOperation()
     * @generated
     */
    void setOperation(Operation value);

    /**
     * Returns the value of the '<em><b>Output Set</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Output Set</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Output Set</em>' containment reference.
     * @see #setOutputSet(OutputSet)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_OutputSet()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='outputSet' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    OutputSet getOutputSet();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getOutputSet <em>Output Set</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Output Set</em>' containment reference.
     * @see #getOutputSet()
     * @generated
     */
    void setOutputSet(OutputSet value);

    /**
     * Returns the value of the '<em><b>Parallel Gateway</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parallel Gateway</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parallel Gateway</em>' containment reference.
     * @see #setParallelGateway(ParallelGateway)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_ParallelGateway()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='parallelGateway' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    ParallelGateway getParallelGateway();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getParallelGateway <em>Parallel Gateway</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parallel Gateway</em>' containment reference.
     * @see #getParallelGateway()
     * @generated
     */
    void setParallelGateway(ParallelGateway value);

    /**
     * Returns the value of the '<em><b>Participant</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Participant</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Participant</em>' containment reference.
     * @see #setParticipant(Participant)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Participant()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='participant' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    Participant getParticipant();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getParticipant <em>Participant</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Participant</em>' containment reference.
     * @see #getParticipant()
     * @generated
     */
    void setParticipant(Participant value);

    /**
     * Returns the value of the '<em><b>Participant Association</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Participant Association</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Participant Association</em>' containment reference.
     * @see #setParticipantAssociation(ParticipantAssociation)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_ParticipantAssociation()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='participantAssociation' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    ParticipantAssociation getParticipantAssociation();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getParticipantAssociation <em>Participant Association</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Participant Association</em>' containment reference.
     * @see #getParticipantAssociation()
     * @generated
     */
    void setParticipantAssociation(ParticipantAssociation value);

    /**
     * Returns the value of the '<em><b>Participant Multiplicity</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Participant Multiplicity</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Participant Multiplicity</em>' containment reference.
     * @see #setParticipantMultiplicity(ParticipantMultiplicity)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_ParticipantMultiplicity()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='participantMultiplicity' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    ParticipantMultiplicity getParticipantMultiplicity();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getParticipantMultiplicity <em>Participant Multiplicity</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Participant Multiplicity</em>' containment reference.
     * @see #getParticipantMultiplicity()
     * @generated
     */
    void setParticipantMultiplicity(ParticipantMultiplicity value);

    /**
     * Returns the value of the '<em><b>Partner Entity</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Partner Entity</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Partner Entity</em>' containment reference.
     * @see #setPartnerEntity(PartnerEntity)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_PartnerEntity()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='partnerEntity' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#rootElement'"
     * @generated
     */
    PartnerEntity getPartnerEntity();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getPartnerEntity <em>Partner Entity</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Partner Entity</em>' containment reference.
     * @see #getPartnerEntity()
     * @generated
     */
    void setPartnerEntity(PartnerEntity value);

    /**
     * Returns the value of the '<em><b>Partner Role</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Partner Role</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Partner Role</em>' containment reference.
     * @see #setPartnerRole(PartnerRole)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_PartnerRole()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='partnerRole' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#rootElement'"
     * @generated
     */
    PartnerRole getPartnerRole();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getPartnerRole <em>Partner Role</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Partner Role</em>' containment reference.
     * @see #getPartnerRole()
     * @generated
     */
    void setPartnerRole(PartnerRole value);

    /**
     * Returns the value of the '<em><b>Potential Owner</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Potential Owner</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Potential Owner</em>' containment reference.
     * @see #setPotentialOwner(PotentialOwner)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_PotentialOwner()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='potentialOwner' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#performer'"
     * @generated
     */
    PotentialOwner getPotentialOwner();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getPotentialOwner <em>Potential Owner</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Potential Owner</em>' containment reference.
     * @see #getPotentialOwner()
     * @generated
     */
    void setPotentialOwner(PotentialOwner value);

    /**
     * Returns the value of the '<em><b>Process</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Process</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Process</em>' containment reference.
     * @see #setProcess(org.eclipse.bpmn2.Process)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Process()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='process' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#rootElement'"
     * @generated
     */
    org.eclipse.bpmn2.Process getProcess();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getProcess <em>Process</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Process</em>' containment reference.
     * @see #getProcess()
     * @generated
     */
    void setProcess(org.eclipse.bpmn2.Process value);

    /**
     * Returns the value of the '<em><b>Property</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Property</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Property</em>' containment reference.
     * @see #setProperty(Property)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Property()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='property' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    Property getProperty();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getProperty <em>Property</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Property</em>' containment reference.
     * @see #getProperty()
     * @generated
     */
    void setProperty(Property value);

    /**
     * Returns the value of the '<em><b>Receive Task</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Receive Task</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Receive Task</em>' containment reference.
     * @see #setReceiveTask(ReceiveTask)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_ReceiveTask()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='receiveTask' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    ReceiveTask getReceiveTask();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getReceiveTask <em>Receive Task</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Receive Task</em>' containment reference.
     * @see #getReceiveTask()
     * @generated
     */
    void setReceiveTask(ReceiveTask value);

    /**
     * Returns the value of the '<em><b>Relationship</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Relationship</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Relationship</em>' containment reference.
     * @see #setRelationship(Relationship)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Relationship()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='relationship' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    Relationship getRelationship();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getRelationship <em>Relationship</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Relationship</em>' containment reference.
     * @see #getRelationship()
     * @generated
     */
    void setRelationship(Relationship value);

    /**
     * Returns the value of the '<em><b>Rendering</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Rendering</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Rendering</em>' containment reference.
     * @see #setRendering(Rendering)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Rendering()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='rendering' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    Rendering getRendering();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getRendering <em>Rendering</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Rendering</em>' containment reference.
     * @see #getRendering()
     * @generated
     */
    void setRendering(Rendering value);

    /**
     * Returns the value of the '<em><b>Resource</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resource</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Resource</em>' containment reference.
     * @see #setResource(Resource)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Resource()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='resource' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#rootElement'"
     * @generated
     */
    Resource getResource();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getResource <em>Resource</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Resource</em>' containment reference.
     * @see #getResource()
     * @generated
     */
    void setResource(Resource value);

    /**
     * Returns the value of the '<em><b>Resource Assignment Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resource Assignment Expression</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Resource Assignment Expression</em>' containment reference.
     * @see #setResourceAssignmentExpression(ResourceAssignmentExpression)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_ResourceAssignmentExpression()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='resourceAssignmentExpression' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    ResourceAssignmentExpression getResourceAssignmentExpression();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getResourceAssignmentExpression <em>Resource Assignment Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Resource Assignment Expression</em>' containment reference.
     * @see #getResourceAssignmentExpression()
     * @generated
     */
    void setResourceAssignmentExpression(ResourceAssignmentExpression value);

    /**
     * Returns the value of the '<em><b>Resource Parameter</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resource Parameter</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Resource Parameter</em>' containment reference.
     * @see #setResourceParameter(ResourceParameter)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_ResourceParameter()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='resourceParameter' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    ResourceParameter getResourceParameter();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getResourceParameter <em>Resource Parameter</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Resource Parameter</em>' containment reference.
     * @see #getResourceParameter()
     * @generated
     */
    void setResourceParameter(ResourceParameter value);

    /**
     * Returns the value of the '<em><b>Resource Parameter Binding</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resource Parameter Binding</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Resource Parameter Binding</em>' containment reference.
     * @see #setResourceParameterBinding(ResourceParameterBinding)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_ResourceParameterBinding()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='resourceParameterBinding' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    ResourceParameterBinding getResourceParameterBinding();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getResourceParameterBinding <em>Resource Parameter Binding</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Resource Parameter Binding</em>' containment reference.
     * @see #getResourceParameterBinding()
     * @generated
     */
    void setResourceParameterBinding(ResourceParameterBinding value);

    /**
     * Returns the value of the '<em><b>Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Script</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Script</em>' containment reference.
     * @see #setScript(Object)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Script()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='script' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    Object getScript();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getScript <em>Script</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Script</em>' containment reference.
     * @see #getScript()
     * @generated
     */
    void setScript(Object value);

    /**
     * Returns the value of the '<em><b>Script Task</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Script Task</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Script Task</em>' containment reference.
     * @see #setScriptTask(ScriptTask)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_ScriptTask()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='scriptTask' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    ScriptTask getScriptTask();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getScriptTask <em>Script Task</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Script Task</em>' containment reference.
     * @see #getScriptTask()
     * @generated
     */
    void setScriptTask(ScriptTask value);

    /**
     * Returns the value of the '<em><b>Send Task</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Send Task</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Send Task</em>' containment reference.
     * @see #setSendTask(SendTask)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_SendTask()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='sendTask' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    SendTask getSendTask();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getSendTask <em>Send Task</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Send Task</em>' containment reference.
     * @see #getSendTask()
     * @generated
     */
    void setSendTask(SendTask value);

    /**
     * Returns the value of the '<em><b>Sequence Flow</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sequence Flow</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Sequence Flow</em>' containment reference.
     * @see #setSequenceFlow(SequenceFlow)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_SequenceFlow()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='sequenceFlow' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    SequenceFlow getSequenceFlow();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getSequenceFlow <em>Sequence Flow</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Sequence Flow</em>' containment reference.
     * @see #getSequenceFlow()
     * @generated
     */
    void setSequenceFlow(SequenceFlow value);

    /**
     * Returns the value of the '<em><b>Service Task</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Service Task</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Service Task</em>' containment reference.
     * @see #setServiceTask(ServiceTask)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_ServiceTask()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='serviceTask' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    ServiceTask getServiceTask();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getServiceTask <em>Service Task</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Service Task</em>' containment reference.
     * @see #getServiceTask()
     * @generated
     */
    void setServiceTask(ServiceTask value);

    /**
     * Returns the value of the '<em><b>Signal</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Signal</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Signal</em>' containment reference.
     * @see #setSignal(Signal)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Signal()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='signal' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#rootElement'"
     * @generated
     */
    Signal getSignal();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getSignal <em>Signal</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Signal</em>' containment reference.
     * @see #getSignal()
     * @generated
     */
    void setSignal(Signal value);

    /**
     * Returns the value of the '<em><b>Signal Event Definition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Signal Event Definition</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Signal Event Definition</em>' containment reference.
     * @see #setSignalEventDefinition(SignalEventDefinition)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_SignalEventDefinition()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='signalEventDefinition' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#eventDefinition'"
     * @generated
     */
    SignalEventDefinition getSignalEventDefinition();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getSignalEventDefinition <em>Signal Event Definition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Signal Event Definition</em>' containment reference.
     * @see #getSignalEventDefinition()
     * @generated
     */
    void setSignalEventDefinition(SignalEventDefinition value);

    /**
     * Returns the value of the '<em><b>Standard Loop Characteristics</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Standard Loop Characteristics</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Standard Loop Characteristics</em>' containment reference.
     * @see #setStandardLoopCharacteristics(StandardLoopCharacteristics)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_StandardLoopCharacteristics()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='standardLoopCharacteristics' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#loopCharacteristics'"
     * @generated
     */
    StandardLoopCharacteristics getStandardLoopCharacteristics();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getStandardLoopCharacteristics <em>Standard Loop Characteristics</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Standard Loop Characteristics</em>' containment reference.
     * @see #getStandardLoopCharacteristics()
     * @generated
     */
    void setStandardLoopCharacteristics(StandardLoopCharacteristics value);

    /**
     * Returns the value of the '<em><b>Start Event</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Start Event</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Start Event</em>' containment reference.
     * @see #setStartEvent(StartEvent)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_StartEvent()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='startEvent' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    StartEvent getStartEvent();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getStartEvent <em>Start Event</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Start Event</em>' containment reference.
     * @see #getStartEvent()
     * @generated
     */
    void setStartEvent(StartEvent value);

    /**
     * Returns the value of the '<em><b>Sub Choreography</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sub Choreography</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Sub Choreography</em>' containment reference.
     * @see #setSubChoreography(SubChoreography)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_SubChoreography()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='subChoreography' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    SubChoreography getSubChoreography();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getSubChoreography <em>Sub Choreography</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Sub Choreography</em>' containment reference.
     * @see #getSubChoreography()
     * @generated
     */
    void setSubChoreography(SubChoreography value);

    /**
     * Returns the value of the '<em><b>Sub Conversation</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sub Conversation</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Sub Conversation</em>' containment reference.
     * @see #setSubConversation(SubConversation)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_SubConversation()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='subConversation' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#conversationNode'"
     * @generated
     */
    SubConversation getSubConversation();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getSubConversation <em>Sub Conversation</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Sub Conversation</em>' containment reference.
     * @see #getSubConversation()
     * @generated
     */
    void setSubConversation(SubConversation value);

    /**
     * Returns the value of the '<em><b>Sub Process</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sub Process</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Sub Process</em>' containment reference.
     * @see #setSubProcess(SubProcess)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_SubProcess()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='subProcess' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    SubProcess getSubProcess();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getSubProcess <em>Sub Process</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Sub Process</em>' containment reference.
     * @see #getSubProcess()
     * @generated
     */
    void setSubProcess(SubProcess value);

    /**
     * Returns the value of the '<em><b>Task</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Task</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Task</em>' containment reference.
     * @see #setTask(Task)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Task()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='task' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    Task getTask();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getTask <em>Task</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Task</em>' containment reference.
     * @see #getTask()
     * @generated
     */
    void setTask(Task value);

    /**
     * Returns the value of the '<em><b>Terminate Event Definition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Terminate Event Definition</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Terminate Event Definition</em>' containment reference.
     * @see #setTerminateEventDefinition(TerminateEventDefinition)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_TerminateEventDefinition()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='terminateEventDefinition' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#eventDefinition'"
     * @generated
     */
    TerminateEventDefinition getTerminateEventDefinition();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getTerminateEventDefinition <em>Terminate Event Definition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Terminate Event Definition</em>' containment reference.
     * @see #getTerminateEventDefinition()
     * @generated
     */
    void setTerminateEventDefinition(TerminateEventDefinition value);

    /**
     * Returns the value of the '<em><b>Text</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Text</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Text</em>' containment reference.
     * @see #setText(Object)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Text()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='text' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    Object getText();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getText <em>Text</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Text</em>' containment reference.
     * @see #getText()
     * @generated
     */
    void setText(Object value);

    /**
     * Returns the value of the '<em><b>Text Annotation</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Text Annotation</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Text Annotation</em>' containment reference.
     * @see #setTextAnnotation(TextAnnotation)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_TextAnnotation()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='textAnnotation' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#artifact'"
     * @generated
     */
    TextAnnotation getTextAnnotation();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getTextAnnotation <em>Text Annotation</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Text Annotation</em>' containment reference.
     * @see #getTextAnnotation()
     * @generated
     */
    void setTextAnnotation(TextAnnotation value);

    /**
     * Returns the value of the '<em><b>Throw Event</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Throw Event</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Throw Event</em>' containment reference.
     * @see #setThrowEvent(ThrowEvent)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_ThrowEvent()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='throwEvent' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL'"
     * @generated
     */
    ThrowEvent getThrowEvent();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getThrowEvent <em>Throw Event</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Throw Event</em>' containment reference.
     * @see #getThrowEvent()
     * @generated
     */
    void setThrowEvent(ThrowEvent value);

    /**
     * Returns the value of the '<em><b>Timer Event Definition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Timer Event Definition</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Timer Event Definition</em>' containment reference.
     * @see #setTimerEventDefinition(TimerEventDefinition)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_TimerEventDefinition()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='timerEventDefinition' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#eventDefinition'"
     * @generated
     */
    TimerEventDefinition getTimerEventDefinition();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getTimerEventDefinition <em>Timer Event Definition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Timer Event Definition</em>' containment reference.
     * @see #getTimerEventDefinition()
     * @generated
     */
    void setTimerEventDefinition(TimerEventDefinition value);

    /**
     * Returns the value of the '<em><b>Transaction</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Transaction</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Transaction</em>' containment reference.
     * @see #setTransaction(Transaction)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_Transaction()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='transaction' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    Transaction getTransaction();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getTransaction <em>Transaction</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Transaction</em>' containment reference.
     * @see #getTransaction()
     * @generated
     */
    void setTransaction(Transaction value);

    /**
     * Returns the value of the '<em><b>User Task</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>User Task</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>User Task</em>' containment reference.
     * @see #setUserTask(UserTask)
     * @see org.eclipse.bpmn2.Bpmn2Package#getDocumentRoot_UserTask()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='userTask' namespace='http://www.omg.org/spec/BPMN/20100524/MODEL' affiliation='http://www.omg.org/spec/BPMN/20100524/MODEL#flowElement'"
     * @generated
     */
    UserTask getUserTask();

    /**
     * Sets the value of the '{@link org.eclipse.bpmn2.DocumentRoot#getUserTask <em>User Task</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>User Task</em>' containment reference.
     * @see #getUserTask()
     * @generated
     */
    void setUserTask(UserTask value);

} // DocumentRoot
