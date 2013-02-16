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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.bpmn2.Bpmn2Package
 * @generated
 */
public interface Bpmn2Factory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    Bpmn2Factory eINSTANCE = org.eclipse.bpmn2.impl.Bpmn2FactoryImpl.init();

    /**
     * Returns a new object of class '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Document Root</em>'.
     * @generated
     */
    DocumentRoot createDocumentRoot();

    /**
     * Returns a new object of class '<em>Ad Hoc Sub Process</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Ad Hoc Sub Process</em>'.
     * @generated
     */
    AdHocSubProcess createAdHocSubProcess();

    /**
     * Returns a new object of class '<em>Assignment</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Assignment</em>'.
     * @generated
     */
    Assignment createAssignment();

    /**
     * Returns a new object of class '<em>Association</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Association</em>'.
     * @generated
     */
    Association createAssociation();

    /**
     * Returns a new object of class '<em>Auditing</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Auditing</em>'.
     * @generated
     */
    Auditing createAuditing();

    /**
     * Returns a new object of class '<em>Boundary Event</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Boundary Event</em>'.
     * @generated
     */
    BoundaryEvent createBoundaryEvent();

    /**
     * Returns a new object of class '<em>Business Rule Task</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Business Rule Task</em>'.
     * @generated
     */
    BusinessRuleTask createBusinessRuleTask();

    /**
     * Returns a new object of class '<em>Call Activity</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Call Activity</em>'.
     * @generated
     */
    CallActivity createCallActivity();

    /**
     * Returns a new object of class '<em>Call Choreography</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Call Choreography</em>'.
     * @generated
     */
    CallChoreography createCallChoreography();

    /**
     * Returns a new object of class '<em>Call Conversation</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Call Conversation</em>'.
     * @generated
     */
    CallConversation createCallConversation();

    /**
     * Returns a new object of class '<em>Cancel Event Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Cancel Event Definition</em>'.
     * @generated
     */
    CancelEventDefinition createCancelEventDefinition();

    /**
     * Returns a new object of class '<em>Category</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Category</em>'.
     * @generated
     */
    Category createCategory();

    /**
     * Returns a new object of class '<em>Category Value</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Category Value</em>'.
     * @generated
     */
    CategoryValue createCategoryValue();

    /**
     * Returns a new object of class '<em>Choreography</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Choreography</em>'.
     * @generated
     */
    Choreography createChoreography();

    /**
     * Returns a new object of class '<em>Choreography Task</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Choreography Task</em>'.
     * @generated
     */
    ChoreographyTask createChoreographyTask();

    /**
     * Returns a new object of class '<em>Collaboration</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Collaboration</em>'.
     * @generated
     */
    Collaboration createCollaboration();

    /**
     * Returns a new object of class '<em>Compensate Event Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Compensate Event Definition</em>'.
     * @generated
     */
    CompensateEventDefinition createCompensateEventDefinition();

    /**
     * Returns a new object of class '<em>Complex Behavior Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Complex Behavior Definition</em>'.
     * @generated
     */
    ComplexBehaviorDefinition createComplexBehaviorDefinition();

    /**
     * Returns a new object of class '<em>Complex Gateway</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Complex Gateway</em>'.
     * @generated
     */
    ComplexGateway createComplexGateway();

    /**
     * Returns a new object of class '<em>Conditional Event Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Conditional Event Definition</em>'.
     * @generated
     */
    ConditionalEventDefinition createConditionalEventDefinition();

    /**
     * Returns a new object of class '<em>Conversation</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Conversation</em>'.
     * @generated
     */
    Conversation createConversation();

    /**
     * Returns a new object of class '<em>Conversation Association</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Conversation Association</em>'.
     * @generated
     */
    ConversationAssociation createConversationAssociation();

    /**
     * Returns a new object of class '<em>Conversation Link</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Conversation Link</em>'.
     * @generated
     */
    ConversationLink createConversationLink();

    /**
     * Returns a new object of class '<em>Correlation Key</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Correlation Key</em>'.
     * @generated
     */
    CorrelationKey createCorrelationKey();

    /**
     * Returns a new object of class '<em>Correlation Property</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Correlation Property</em>'.
     * @generated
     */
    CorrelationProperty createCorrelationProperty();

    /**
     * Returns a new object of class '<em>Correlation Property Binding</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Correlation Property Binding</em>'.
     * @generated
     */
    CorrelationPropertyBinding createCorrelationPropertyBinding();

    /**
     * Returns a new object of class '<em>Correlation Property Retrieval Expression</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Correlation Property Retrieval Expression</em>'.
     * @generated
     */
    CorrelationPropertyRetrievalExpression createCorrelationPropertyRetrievalExpression();

    /**
     * Returns a new object of class '<em>Correlation Subscription</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Correlation Subscription</em>'.
     * @generated
     */
    CorrelationSubscription createCorrelationSubscription();

    /**
     * Returns a new object of class '<em>Data Association</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Association</em>'.
     * @generated
     */
    DataAssociation createDataAssociation();

    /**
     * Returns a new object of class '<em>Data Input</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Input</em>'.
     * @generated
     */
    DataInput createDataInput();

    /**
     * Returns a new object of class '<em>Data Input Association</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Input Association</em>'.
     * @generated
     */
    DataInputAssociation createDataInputAssociation();

    /**
     * Returns a new object of class '<em>Data Object</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Object</em>'.
     * @generated
     */
    DataObject createDataObject();

    /**
     * Returns a new object of class '<em>Data Object Reference</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Object Reference</em>'.
     * @generated
     */
    DataObjectReference createDataObjectReference();

    /**
     * Returns a new object of class '<em>Data Output</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Output</em>'.
     * @generated
     */
    DataOutput createDataOutput();

    /**
     * Returns a new object of class '<em>Data Output Association</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Output Association</em>'.
     * @generated
     */
    DataOutputAssociation createDataOutputAssociation();

    /**
     * Returns a new object of class '<em>Data State</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data State</em>'.
     * @generated
     */
    DataState createDataState();

    /**
     * Returns a new object of class '<em>Data Store</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Store</em>'.
     * @generated
     */
    DataStore createDataStore();

    /**
     * Returns a new object of class '<em>Data Store Reference</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Data Store Reference</em>'.
     * @generated
     */
    DataStoreReference createDataStoreReference();

    /**
     * Returns a new object of class '<em>Definitions</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Definitions</em>'.
     * @generated
     */
    Definitions createDefinitions();

    /**
     * Returns a new object of class '<em>Documentation</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Documentation</em>'.
     * @generated
     */
    Documentation createDocumentation();

    /**
     * Returns a new object of class '<em>End Event</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>End Event</em>'.
     * @generated
     */
    EndEvent createEndEvent();

    /**
     * Returns a new object of class '<em>End Point</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>End Point</em>'.
     * @generated
     */
    EndPoint createEndPoint();

    /**
     * Returns a new object of class '<em>Error</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Error</em>'.
     * @generated
     */
    Error createError();

    /**
     * Returns a new object of class '<em>Error Event Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Error Event Definition</em>'.
     * @generated
     */
    ErrorEventDefinition createErrorEventDefinition();

    /**
     * Returns a new object of class '<em>Escalation</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Escalation</em>'.
     * @generated
     */
    Escalation createEscalation();

    /**
     * Returns a new object of class '<em>Escalation Event Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Escalation Event Definition</em>'.
     * @generated
     */
    EscalationEventDefinition createEscalationEventDefinition();

    /**
     * Returns a new object of class '<em>Event Based Gateway</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Event Based Gateway</em>'.
     * @generated
     */
    EventBasedGateway createEventBasedGateway();

    /**
     * Returns a new object of class '<em>Exclusive Gateway</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Exclusive Gateway</em>'.
     * @generated
     */
    ExclusiveGateway createExclusiveGateway();

    /**
     * Returns a new object of class '<em>Expression</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Expression</em>'.
     * @generated
     */
    Expression createExpression();

    /**
     * Returns a new object of class '<em>Extension</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Extension</em>'.
     * @generated
     */
    Extension createExtension();

    /**
     * Returns a new object of class '<em>Extension Attribute Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Extension Attribute Definition</em>'.
     * @generated
     */
    ExtensionAttributeDefinition createExtensionAttributeDefinition();

    /**
     * Returns a new object of class '<em>Extension Attribute Value</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Extension Attribute Value</em>'.
     * @generated
     */
    ExtensionAttributeValue createExtensionAttributeValue();

    /**
     * Returns a new object of class '<em>Extension Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Extension Definition</em>'.
     * @generated
     */
    ExtensionDefinition createExtensionDefinition();

    /**
     * Returns a new object of class '<em>Formal Expression</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Formal Expression</em>'.
     * @generated
     */
    FormalExpression createFormalExpression();

    /**
     * Returns a new object of class '<em>Global Business Rule Task</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Global Business Rule Task</em>'.
     * @generated
     */
    GlobalBusinessRuleTask createGlobalBusinessRuleTask();

    /**
     * Returns a new object of class '<em>Global Choreography Task</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Global Choreography Task</em>'.
     * @generated
     */
    GlobalChoreographyTask createGlobalChoreographyTask();

    /**
     * Returns a new object of class '<em>Global Conversation</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Global Conversation</em>'.
     * @generated
     */
    GlobalConversation createGlobalConversation();

    /**
     * Returns a new object of class '<em>Global Manual Task</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Global Manual Task</em>'.
     * @generated
     */
    GlobalManualTask createGlobalManualTask();

    /**
     * Returns a new object of class '<em>Global Script Task</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Global Script Task</em>'.
     * @generated
     */
    GlobalScriptTask createGlobalScriptTask();

    /**
     * Returns a new object of class '<em>Global Task</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Global Task</em>'.
     * @generated
     */
    GlobalTask createGlobalTask();

    /**
     * Returns a new object of class '<em>Global User Task</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Global User Task</em>'.
     * @generated
     */
    GlobalUserTask createGlobalUserTask();

    /**
     * Returns a new object of class '<em>Group</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Group</em>'.
     * @generated
     */
    Group createGroup();

    /**
     * Returns a new object of class '<em>Human Performer</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Human Performer</em>'.
     * @generated
     */
    HumanPerformer createHumanPerformer();

    /**
     * Returns a new object of class '<em>Implicit Throw Event</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Implicit Throw Event</em>'.
     * @generated
     */
    ImplicitThrowEvent createImplicitThrowEvent();

    /**
     * Returns a new object of class '<em>Import</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Import</em>'.
     * @generated
     */
    Import createImport();

    /**
     * Returns a new object of class '<em>Inclusive Gateway</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Inclusive Gateway</em>'.
     * @generated
     */
    InclusiveGateway createInclusiveGateway();

    /**
     * Returns a new object of class '<em>Input Output Binding</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Input Output Binding</em>'.
     * @generated
     */
    InputOutputBinding createInputOutputBinding();

    /**
     * Returns a new object of class '<em>Input Output Specification</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Input Output Specification</em>'.
     * @generated
     */
    InputOutputSpecification createInputOutputSpecification();

    /**
     * Returns a new object of class '<em>Input Set</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Input Set</em>'.
     * @generated
     */
    InputSet createInputSet();

    /**
     * Returns a new object of class '<em>Interface</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Interface</em>'.
     * @generated
     */
    Interface createInterface();

    /**
     * Returns a new object of class '<em>Intermediate Catch Event</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Intermediate Catch Event</em>'.
     * @generated
     */
    IntermediateCatchEvent createIntermediateCatchEvent();

    /**
     * Returns a new object of class '<em>Intermediate Throw Event</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Intermediate Throw Event</em>'.
     * @generated
     */
    IntermediateThrowEvent createIntermediateThrowEvent();

    /**
     * Returns a new object of class '<em>Item Aware Element</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Item Aware Element</em>'.
     * @generated
     */
    ItemAwareElement createItemAwareElement();

    /**
     * Returns a new object of class '<em>Item Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Item Definition</em>'.
     * @generated
     */
    ItemDefinition createItemDefinition();

    /**
     * Returns a new object of class '<em>Lane</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Lane</em>'.
     * @generated
     */
    Lane createLane();

    /**
     * Returns a new object of class '<em>Lane Set</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Lane Set</em>'.
     * @generated
     */
    LaneSet createLaneSet();

    /**
     * Returns a new object of class '<em>Link Event Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Link Event Definition</em>'.
     * @generated
     */
    LinkEventDefinition createLinkEventDefinition();

    /**
     * Returns a new object of class '<em>Manual Task</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Manual Task</em>'.
     * @generated
     */
    ManualTask createManualTask();

    /**
     * Returns a new object of class '<em>Message</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Message</em>'.
     * @generated
     */
    Message createMessage();

    /**
     * Returns a new object of class '<em>Message Event Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Message Event Definition</em>'.
     * @generated
     */
    MessageEventDefinition createMessageEventDefinition();

    /**
     * Returns a new object of class '<em>Message Flow</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Message Flow</em>'.
     * @generated
     */
    MessageFlow createMessageFlow();

    /**
     * Returns a new object of class '<em>Message Flow Association</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Message Flow Association</em>'.
     * @generated
     */
    MessageFlowAssociation createMessageFlowAssociation();

    /**
     * Returns a new object of class '<em>Monitoring</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Monitoring</em>'.
     * @generated
     */
    Monitoring createMonitoring();

    /**
     * Returns a new object of class '<em>Multi Instance Loop Characteristics</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Multi Instance Loop Characteristics</em>'.
     * @generated
     */
    MultiInstanceLoopCharacteristics createMultiInstanceLoopCharacteristics();

    /**
     * Returns a new object of class '<em>Operation</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Operation</em>'.
     * @generated
     */
    Operation createOperation();

    /**
     * Returns a new object of class '<em>Output Set</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Output Set</em>'.
     * @generated
     */
    OutputSet createOutputSet();

    /**
     * Returns a new object of class '<em>Parallel Gateway</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Parallel Gateway</em>'.
     * @generated
     */
    ParallelGateway createParallelGateway();

    /**
     * Returns a new object of class '<em>Participant</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Participant</em>'.
     * @generated
     */
    Participant createParticipant();

    /**
     * Returns a new object of class '<em>Participant Association</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Participant Association</em>'.
     * @generated
     */
    ParticipantAssociation createParticipantAssociation();

    /**
     * Returns a new object of class '<em>Participant Multiplicity</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Participant Multiplicity</em>'.
     * @generated
     */
    ParticipantMultiplicity createParticipantMultiplicity();

    /**
     * Returns a new object of class '<em>Partner Entity</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Partner Entity</em>'.
     * @generated
     */
    PartnerEntity createPartnerEntity();

    /**
     * Returns a new object of class '<em>Partner Role</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Partner Role</em>'.
     * @generated
     */
    PartnerRole createPartnerRole();

    /**
     * Returns a new object of class '<em>Performer</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Performer</em>'.
     * @generated
     */
    Performer createPerformer();

    /**
     * Returns a new object of class '<em>Potential Owner</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Potential Owner</em>'.
     * @generated
     */
    PotentialOwner createPotentialOwner();

    /**
     * Returns a new object of class '<em>Process</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Process</em>'.
     * @generated
     */
    Process createProcess();

    /**
     * Returns a new object of class '<em>Property</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Property</em>'.
     * @generated
     */
    Property createProperty();

    /**
     * Returns a new object of class '<em>Receive Task</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Receive Task</em>'.
     * @generated
     */
    ReceiveTask createReceiveTask();

    /**
     * Returns a new object of class '<em>Relationship</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Relationship</em>'.
     * @generated
     */
    Relationship createRelationship();

    /**
     * Returns a new object of class '<em>Rendering</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Rendering</em>'.
     * @generated
     */
    Rendering createRendering();

    /**
     * Returns a new object of class '<em>Resource</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Resource</em>'.
     * @generated
     */
    Resource createResource();

    /**
     * Returns a new object of class '<em>Resource Assignment Expression</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Resource Assignment Expression</em>'.
     * @generated
     */
    ResourceAssignmentExpression createResourceAssignmentExpression();

    /**
     * Returns a new object of class '<em>Resource Parameter</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Resource Parameter</em>'.
     * @generated
     */
    ResourceParameter createResourceParameter();

    /**
     * Returns a new object of class '<em>Resource Parameter Binding</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Resource Parameter Binding</em>'.
     * @generated
     */
    ResourceParameterBinding createResourceParameterBinding();

    /**
     * Returns a new object of class '<em>Resource Role</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Resource Role</em>'.
     * @generated
     */
    ResourceRole createResourceRole();

    /**
     * Returns a new object of class '<em>Script Task</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Script Task</em>'.
     * @generated
     */
    ScriptTask createScriptTask();

    /**
     * Returns a new object of class '<em>Send Task</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Send Task</em>'.
     * @generated
     */
    SendTask createSendTask();

    /**
     * Returns a new object of class '<em>Sequence Flow</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Sequence Flow</em>'.
     * @generated
     */
    SequenceFlow createSequenceFlow();

    /**
     * Returns a new object of class '<em>Service Task</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Service Task</em>'.
     * @generated
     */
    ServiceTask createServiceTask();

    /**
     * Returns a new object of class '<em>Signal</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Signal</em>'.
     * @generated
     */
    Signal createSignal();

    /**
     * Returns a new object of class '<em>Signal Event Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Signal Event Definition</em>'.
     * @generated
     */
    SignalEventDefinition createSignalEventDefinition();

    /**
     * Returns a new object of class '<em>Standard Loop Characteristics</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Standard Loop Characteristics</em>'.
     * @generated
     */
    StandardLoopCharacteristics createStandardLoopCharacteristics();

    /**
     * Returns a new object of class '<em>Start Event</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Start Event</em>'.
     * @generated
     */
    StartEvent createStartEvent();

    /**
     * Returns a new object of class '<em>Sub Choreography</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Sub Choreography</em>'.
     * @generated
     */
    SubChoreography createSubChoreography();

    /**
     * Returns a new object of class '<em>Sub Conversation</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Sub Conversation</em>'.
     * @generated
     */
    SubConversation createSubConversation();

    /**
     * Returns a new object of class '<em>Sub Process</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Sub Process</em>'.
     * @generated
     */
    SubProcess createSubProcess();

    /**
     * Returns a new object of class '<em>Task</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Task</em>'.
     * @generated
     */
    Task createTask();

    /**
     * Returns a new object of class '<em>Terminate Event Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Terminate Event Definition</em>'.
     * @generated
     */
    TerminateEventDefinition createTerminateEventDefinition();

    /**
     * Returns a new object of class '<em>Text Annotation</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Text Annotation</em>'.
     * @generated
     */
    TextAnnotation createTextAnnotation();

    /**
     * Returns a new object of class '<em>Timer Event Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Timer Event Definition</em>'.
     * @generated
     */
    TimerEventDefinition createTimerEventDefinition();

    /**
     * Returns a new object of class '<em>Transaction</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Transaction</em>'.
     * @generated
     */
    Transaction createTransaction();

    /**
     * Returns a new object of class '<em>User Task</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>User Task</em>'.
     * @generated
     */
    UserTask createUserTask();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    Bpmn2Package getBpmn2Package();

} //Bpmn2Factory
