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

import java.util.Map;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.AdHocSubProcess;
import org.eclipse.bpmn2.Artifact;
import org.eclipse.bpmn2.Assignment;
import org.eclipse.bpmn2.Association;
import org.eclipse.bpmn2.Auditing;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.BusinessRuleTask;
import org.eclipse.bpmn2.CallActivity;
import org.eclipse.bpmn2.CallChoreography;
import org.eclipse.bpmn2.CallConversation;
import org.eclipse.bpmn2.CallableElement;
import org.eclipse.bpmn2.CancelEventDefinition;
import org.eclipse.bpmn2.CatchEvent;
import org.eclipse.bpmn2.Category;
import org.eclipse.bpmn2.CategoryValue;
import org.eclipse.bpmn2.Choreography;
import org.eclipse.bpmn2.ChoreographyActivity;
import org.eclipse.bpmn2.ChoreographyTask;
import org.eclipse.bpmn2.Collaboration;
import org.eclipse.bpmn2.CompensateEventDefinition;
import org.eclipse.bpmn2.ComplexBehaviorDefinition;
import org.eclipse.bpmn2.ComplexGateway;
import org.eclipse.bpmn2.ConditionalEventDefinition;
import org.eclipse.bpmn2.Conversation;
import org.eclipse.bpmn2.ConversationAssociation;
import org.eclipse.bpmn2.ConversationLink;
import org.eclipse.bpmn2.ConversationNode;
import org.eclipse.bpmn2.CorrelationKey;
import org.eclipse.bpmn2.CorrelationProperty;
import org.eclipse.bpmn2.CorrelationPropertyBinding;
import org.eclipse.bpmn2.CorrelationPropertyRetrievalExpression;
import org.eclipse.bpmn2.CorrelationSubscription;
import org.eclipse.bpmn2.DataAssociation;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataInputAssociation;
import org.eclipse.bpmn2.DataObject;
import org.eclipse.bpmn2.DataObjectReference;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.DataOutputAssociation;
import org.eclipse.bpmn2.DataState;
import org.eclipse.bpmn2.DataStore;
import org.eclipse.bpmn2.DataStoreReference;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.bpmn2.Documentation;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.EndPoint;
import org.eclipse.bpmn2.ErrorEventDefinition;
import org.eclipse.bpmn2.Escalation;
import org.eclipse.bpmn2.EscalationEventDefinition;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.EventBasedGateway;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.ExclusiveGateway;
import org.eclipse.bpmn2.Expression;
import org.eclipse.bpmn2.Extension;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.GlobalBusinessRuleTask;
import org.eclipse.bpmn2.GlobalChoreographyTask;
import org.eclipse.bpmn2.GlobalConversation;
import org.eclipse.bpmn2.GlobalManualTask;
import org.eclipse.bpmn2.GlobalScriptTask;
import org.eclipse.bpmn2.GlobalTask;
import org.eclipse.bpmn2.GlobalUserTask;
import org.eclipse.bpmn2.Group;
import org.eclipse.bpmn2.HumanPerformer;
import org.eclipse.bpmn2.ImplicitThrowEvent;
import org.eclipse.bpmn2.Import;
import org.eclipse.bpmn2.InclusiveGateway;
import org.eclipse.bpmn2.InputOutputBinding;
import org.eclipse.bpmn2.InputOutputSpecification;
import org.eclipse.bpmn2.InputSet;
import org.eclipse.bpmn2.Interface;
import org.eclipse.bpmn2.IntermediateCatchEvent;
import org.eclipse.bpmn2.IntermediateThrowEvent;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.LaneSet;
import org.eclipse.bpmn2.LinkEventDefinition;
import org.eclipse.bpmn2.LoopCharacteristics;
import org.eclipse.bpmn2.ManualTask;
import org.eclipse.bpmn2.Message;
import org.eclipse.bpmn2.MessageEventDefinition;
import org.eclipse.bpmn2.MessageFlow;
import org.eclipse.bpmn2.MessageFlowAssociation;
import org.eclipse.bpmn2.Monitoring;
import org.eclipse.bpmn2.MultiInstanceLoopCharacteristics;
import org.eclipse.bpmn2.Operation;
import org.eclipse.bpmn2.OutputSet;
import org.eclipse.bpmn2.ParallelGateway;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.ParticipantAssociation;
import org.eclipse.bpmn2.ParticipantMultiplicity;
import org.eclipse.bpmn2.PartnerEntity;
import org.eclipse.bpmn2.PartnerRole;
import org.eclipse.bpmn2.Performer;
import org.eclipse.bpmn2.PotentialOwner;
import org.eclipse.bpmn2.Property;
import org.eclipse.bpmn2.ReceiveTask;
import org.eclipse.bpmn2.Relationship;
import org.eclipse.bpmn2.Rendering;
import org.eclipse.bpmn2.Resource;
import org.eclipse.bpmn2.ResourceAssignmentExpression;
import org.eclipse.bpmn2.ResourceParameter;
import org.eclipse.bpmn2.ResourceParameterBinding;
import org.eclipse.bpmn2.ResourceRole;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.ScriptTask;
import org.eclipse.bpmn2.SendTask;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.ServiceTask;
import org.eclipse.bpmn2.Signal;
import org.eclipse.bpmn2.SignalEventDefinition;
import org.eclipse.bpmn2.StandardLoopCharacteristics;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubChoreography;
import org.eclipse.bpmn2.SubConversation;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.TerminateEventDefinition;
import org.eclipse.bpmn2.TextAnnotation;
import org.eclipse.bpmn2.ThrowEvent;
import org.eclipse.bpmn2.TimerEventDefinition;
import org.eclipse.bpmn2.Transaction;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getActivity <em>Activity</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getAdHocSubProcess <em>Ad Hoc Sub Process</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getFlowElement <em>Flow Element</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getArtifact <em>Artifact</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getAssignment <em>Assignment</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getAssociation <em>Association</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getAuditing <em>Auditing</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getBaseElement <em>Base Element</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getBaseElementWithMixedContent <em>Base Element With Mixed Content</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getBoundaryEvent <em>Boundary Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getBusinessRuleTask <em>Business Rule Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getCallableElement <em>Callable Element</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getCallActivity <em>Call Activity</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getCallChoreography <em>Call Choreography</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getCallConversation <em>Call Conversation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getConversationNode <em>Conversation Node</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getCancelEventDefinition <em>Cancel Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getEventDefinition <em>Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getRootElement <em>Root Element</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getCatchEvent <em>Catch Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getCategory <em>Category</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getCategoryValue <em>Category Value</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getChoreography <em>Choreography</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getCollaboration <em>Collaboration</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getChoreographyActivity <em>Choreography Activity</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getChoreographyTask <em>Choreography Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getCompensateEventDefinition <em>Compensate Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getComplexBehaviorDefinition <em>Complex Behavior Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getComplexGateway <em>Complex Gateway</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getConditionalEventDefinition <em>Conditional Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getConversation <em>Conversation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getConversationAssociation <em>Conversation Association</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getConversationLink <em>Conversation Link</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getCorrelationKey <em>Correlation Key</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getCorrelationProperty <em>Correlation Property</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getCorrelationPropertyBinding <em>Correlation Property Binding</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getCorrelationPropertyRetrievalExpression <em>Correlation Property Retrieval Expression</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getCorrelationSubscription <em>Correlation Subscription</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getDataAssociation <em>Data Association</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getDataInput <em>Data Input</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getDataInputAssociation <em>Data Input Association</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getDataObject <em>Data Object</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getDataObjectReference <em>Data Object Reference</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getDataOutput <em>Data Output</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getDataOutputAssociation <em>Data Output Association</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getDataState <em>Data State</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getDataStore <em>Data Store</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getDataStoreReference <em>Data Store Reference</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getDefinitions <em>Definitions</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getEndEvent <em>End Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getEndPoint <em>End Point</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getError <em>Error</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getErrorEventDefinition <em>Error Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getEscalation <em>Escalation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getEscalationEventDefinition <em>Escalation Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getEvent <em>Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getEventBasedGateway <em>Event Based Gateway</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getExclusiveGateway <em>Exclusive Gateway</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getExtension <em>Extension</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getExtensionElements <em>Extension Elements</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getFlowNode <em>Flow Node</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getFormalExpression <em>Formal Expression</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getGateway <em>Gateway</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getGlobalBusinessRuleTask <em>Global Business Rule Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getGlobalChoreographyTask <em>Global Choreography Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getGlobalConversation <em>Global Conversation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getGlobalManualTask <em>Global Manual Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getGlobalScriptTask <em>Global Script Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getGlobalTask <em>Global Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getGlobalUserTask <em>Global User Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getHumanPerformer <em>Human Performer</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getPerformer <em>Performer</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getResourceRole <em>Resource Role</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getImplicitThrowEvent <em>Implicit Throw Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getImport <em>Import</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getInclusiveGateway <em>Inclusive Gateway</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getInputSet <em>Input Set</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getInterface <em>Interface</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getIntermediateCatchEvent <em>Intermediate Catch Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getIntermediateThrowEvent <em>Intermediate Throw Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getIoBinding <em>Io Binding</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getIoSpecification <em>Io Specification</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getItemDefinition <em>Item Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getLane <em>Lane</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getLaneSet <em>Lane Set</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getLinkEventDefinition <em>Link Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getLoopCharacteristics <em>Loop Characteristics</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getManualTask <em>Manual Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getMessage <em>Message</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getMessageEventDefinition <em>Message Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getMessageFlow <em>Message Flow</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getMessageFlowAssociation <em>Message Flow Association</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getMonitoring <em>Monitoring</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getMultiInstanceLoopCharacteristics <em>Multi Instance Loop Characteristics</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getOperation <em>Operation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getOutputSet <em>Output Set</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getParallelGateway <em>Parallel Gateway</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getParticipant <em>Participant</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getParticipantAssociation <em>Participant Association</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getParticipantMultiplicity <em>Participant Multiplicity</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getPartnerEntity <em>Partner Entity</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getPartnerRole <em>Partner Role</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getPotentialOwner <em>Potential Owner</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getProcess <em>Process</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getReceiveTask <em>Receive Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getRelationship <em>Relationship</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getRendering <em>Rendering</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getResource <em>Resource</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getResourceAssignmentExpression <em>Resource Assignment Expression</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getResourceParameter <em>Resource Parameter</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getResourceParameterBinding <em>Resource Parameter Binding</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getScript <em>Script</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getScriptTask <em>Script Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getSendTask <em>Send Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getSequenceFlow <em>Sequence Flow</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getServiceTask <em>Service Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getSignal <em>Signal</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getSignalEventDefinition <em>Signal Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getStandardLoopCharacteristics <em>Standard Loop Characteristics</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getStartEvent <em>Start Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getSubChoreography <em>Sub Choreography</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getSubConversation <em>Sub Conversation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getSubProcess <em>Sub Process</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getTask <em>Task</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getTerminateEventDefinition <em>Terminate Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getText <em>Text</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getTextAnnotation <em>Text Annotation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getThrowEvent <em>Throw Event</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getTimerEventDefinition <em>Timer Event Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getTransaction <em>Transaction</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.DocumentRootImpl#getUserTask <em>User Task</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends EObjectImpl implements DocumentRoot {
    /**
     * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMixed()
     * @generated
     * @ordered
     */
    protected FeatureMap mixed;

    /**
     * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXMLNSPrefixMap()
     * @generated
     * @ordered
     */
    protected EMap<String, String> xMLNSPrefixMap;

    /**
     * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXSISchemaLocation()
     * @generated
     * @ordered
     */
    protected EMap<String, String> xSISchemaLocation;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DocumentRootImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.DOCUMENT_ROOT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, Bpmn2Package.DOCUMENT_ROOT__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Map<String, String> getXMLNSPrefixMap() {
        if (xMLNSPrefixMap == null) {
            xMLNSPrefixMap = new EcoreEMap<String, String>(
                    EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY,
                    EStringToStringMapEntryImpl.class, this,
                    Bpmn2Package.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
        }
        return xMLNSPrefixMap.map();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Map<String, String> getXSISchemaLocation() {
        if (xSISchemaLocation == null) {
            xSISchemaLocation = new EcoreEMap<String, String>(
                    EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY,
                    EStringToStringMapEntryImpl.class, this,
                    Bpmn2Package.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
        }
        return xSISchemaLocation.map();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Activity getActivity() {
        return (Activity) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__ACTIVITY, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetActivity(Activity newActivity, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__ACTIVITY, newActivity, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setActivity(Activity newActivity) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__ACTIVITY,
                newActivity);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AdHocSubProcess getAdHocSubProcess() {
        return (AdHocSubProcess) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__AD_HOC_SUB_PROCESS, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetAdHocSubProcess(AdHocSubProcess newAdHocSubProcess,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__AD_HOC_SUB_PROCESS, newAdHocSubProcess, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAdHocSubProcess(AdHocSubProcess newAdHocSubProcess) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__AD_HOC_SUB_PROCESS, newAdHocSubProcess);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FlowElement getFlowElement() {
        return (FlowElement) getMixed()
                .get(Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFlowElement(FlowElement newFlowElement, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT, newFlowElement, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFlowElement(FlowElement newFlowElement) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_ELEMENT,
                newFlowElement);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Artifact getArtifact() {
        return (Artifact) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__ARTIFACT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetArtifact(Artifact newArtifact, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__ARTIFACT, newArtifact, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setArtifact(Artifact newArtifact) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__ARTIFACT,
                newArtifact);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Assignment getAssignment() {
        return (Assignment) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__ASSIGNMENT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetAssignment(Assignment newAssignment, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__ASSIGNMENT, newAssignment, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAssignment(Assignment newAssignment) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__ASSIGNMENT,
                newAssignment);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Association getAssociation() {
        return (Association) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__ASSOCIATION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetAssociation(Association newAssociation, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__ASSOCIATION, newAssociation, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAssociation(Association newAssociation) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__ASSOCIATION,
                newAssociation);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Auditing getAuditing() {
        return (Auditing) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__AUDITING, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetAuditing(Auditing newAuditing, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__AUDITING, newAuditing, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAuditing(Auditing newAuditing) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__AUDITING,
                newAuditing);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BaseElement getBaseElement() {
        return (BaseElement) getMixed()
                .get(Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetBaseElement(BaseElement newBaseElement, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT, newBaseElement, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBaseElement(BaseElement newBaseElement) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT,
                newBaseElement);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BaseElement getBaseElementWithMixedContent() {
        return (BaseElement) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetBaseElementWithMixedContent(
            BaseElement newBaseElementWithMixedContent, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
                newBaseElementWithMixedContent, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBaseElementWithMixedContent(BaseElement newBaseElementWithMixedContent) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT,
                newBaseElementWithMixedContent);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BoundaryEvent getBoundaryEvent() {
        return (BoundaryEvent) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__BOUNDARY_EVENT,
                true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetBoundaryEvent(BoundaryEvent newBoundaryEvent,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__BOUNDARY_EVENT, newBoundaryEvent, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBoundaryEvent(BoundaryEvent newBoundaryEvent) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__BOUNDARY_EVENT,
                newBoundaryEvent);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BusinessRuleTask getBusinessRuleTask() {
        return (BusinessRuleTask) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__BUSINESS_RULE_TASK, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetBusinessRuleTask(BusinessRuleTask newBusinessRuleTask,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__BUSINESS_RULE_TASK, newBusinessRuleTask, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBusinessRuleTask(BusinessRuleTask newBusinessRuleTask) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__BUSINESS_RULE_TASK, newBusinessRuleTask);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CallableElement getCallableElement() {
        return (CallableElement) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCallableElement(CallableElement newCallableElement,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT, newCallableElement, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCallableElement(CallableElement newCallableElement) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT, newCallableElement);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CallActivity getCallActivity() {
        return (CallActivity) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__CALL_ACTIVITY,
                true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCallActivity(CallActivity newCallActivity,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CALL_ACTIVITY, newCallActivity, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCallActivity(CallActivity newCallActivity) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__CALL_ACTIVITY,
                newCallActivity);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CallChoreography getCallChoreography() {
        return (CallChoreography) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CALL_CHOREOGRAPHY, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCallChoreography(CallChoreography newCallChoreography,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CALL_CHOREOGRAPHY, newCallChoreography, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCallChoreography(CallChoreography newCallChoreography) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CALL_CHOREOGRAPHY, newCallChoreography);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CallConversation getCallConversation() {
        return (CallConversation) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CALL_CONVERSATION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCallConversation(CallConversation newCallConversation,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CALL_CONVERSATION, newCallConversation, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCallConversation(CallConversation newCallConversation) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CALL_CONVERSATION, newCallConversation);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ConversationNode getConversationNode() {
        return (ConversationNode) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CONVERSATION_NODE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetConversationNode(ConversationNode newConversationNode,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CONVERSATION_NODE, newConversationNode, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setConversationNode(ConversationNode newConversationNode) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CONVERSATION_NODE, newConversationNode);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CancelEventDefinition getCancelEventDefinition() {
        return (CancelEventDefinition) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CANCEL_EVENT_DEFINITION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCancelEventDefinition(
            CancelEventDefinition newCancelEventDefinition, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CANCEL_EVENT_DEFINITION,
                newCancelEventDefinition, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCancelEventDefinition(CancelEventDefinition newCancelEventDefinition) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CANCEL_EVENT_DEFINITION,
                newCancelEventDefinition);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EventDefinition getEventDefinition() {
        return (EventDefinition) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT_DEFINITION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEventDefinition(EventDefinition newEventDefinition,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT_DEFINITION, newEventDefinition, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEventDefinition(EventDefinition newEventDefinition) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT_DEFINITION, newEventDefinition);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RootElement getRootElement() {
        return (RootElement) getMixed()
                .get(Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRootElement(RootElement newRootElement, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT, newRootElement, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRootElement(RootElement newRootElement) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__ROOT_ELEMENT,
                newRootElement);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CatchEvent getCatchEvent() {
        return (CatchEvent) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__CATCH_EVENT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCatchEvent(CatchEvent newCatchEvent, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CATCH_EVENT, newCatchEvent, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCatchEvent(CatchEvent newCatchEvent) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__CATCH_EVENT,
                newCatchEvent);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Category getCategory() {
        return (Category) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__CATEGORY, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCategory(Category newCategory, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CATEGORY, newCategory, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCategory(Category newCategory) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__CATEGORY,
                newCategory);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CategoryValue getCategoryValue() {
        return (CategoryValue) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__CATEGORY_VALUE,
                true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCategoryValue(CategoryValue newCategoryValue,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CATEGORY_VALUE, newCategoryValue, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCategoryValue(CategoryValue newCategoryValue) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__CATEGORY_VALUE,
                newCategoryValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Choreography getChoreography() {
        return (Choreography) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__CHOREOGRAPHY,
                true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetChoreography(Choreography newChoreography,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CHOREOGRAPHY, newChoreography, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setChoreography(Choreography newChoreography) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__CHOREOGRAPHY,
                newChoreography);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Collaboration getCollaboration() {
        return (Collaboration) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__COLLABORATION,
                true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCollaboration(Collaboration newCollaboration,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__COLLABORATION, newCollaboration, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCollaboration(Collaboration newCollaboration) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__COLLABORATION,
                newCollaboration);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ChoreographyActivity getChoreographyActivity() {
        return (ChoreographyActivity) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CHOREOGRAPHY_ACTIVITY, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetChoreographyActivity(
            ChoreographyActivity newChoreographyActivity, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CHOREOGRAPHY_ACTIVITY,
                newChoreographyActivity, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setChoreographyActivity(ChoreographyActivity newChoreographyActivity) {
        ((FeatureMap.Internal) getMixed())
                .set(Bpmn2Package.Literals.DOCUMENT_ROOT__CHOREOGRAPHY_ACTIVITY,
                        newChoreographyActivity);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ChoreographyTask getChoreographyTask() {
        return (ChoreographyTask) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CHOREOGRAPHY_TASK, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetChoreographyTask(ChoreographyTask newChoreographyTask,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CHOREOGRAPHY_TASK, newChoreographyTask, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setChoreographyTask(ChoreographyTask newChoreographyTask) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CHOREOGRAPHY_TASK, newChoreographyTask);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CompensateEventDefinition getCompensateEventDefinition() {
        return (CompensateEventDefinition) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__COMPENSATE_EVENT_DEFINITION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCompensateEventDefinition(
            CompensateEventDefinition newCompensateEventDefinition, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__COMPENSATE_EVENT_DEFINITION,
                newCompensateEventDefinition, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCompensateEventDefinition(CompensateEventDefinition newCompensateEventDefinition) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__COMPENSATE_EVENT_DEFINITION,
                newCompensateEventDefinition);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComplexBehaviorDefinition getComplexBehaviorDefinition() {
        return (ComplexBehaviorDefinition) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__COMPLEX_BEHAVIOR_DEFINITION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetComplexBehaviorDefinition(
            ComplexBehaviorDefinition newComplexBehaviorDefinition, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__COMPLEX_BEHAVIOR_DEFINITION,
                newComplexBehaviorDefinition, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setComplexBehaviorDefinition(ComplexBehaviorDefinition newComplexBehaviorDefinition) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__COMPLEX_BEHAVIOR_DEFINITION,
                newComplexBehaviorDefinition);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComplexGateway getComplexGateway() {
        return (ComplexGateway) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__COMPLEX_GATEWAY, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetComplexGateway(ComplexGateway newComplexGateway,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__COMPLEX_GATEWAY, newComplexGateway, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setComplexGateway(ComplexGateway newComplexGateway) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__COMPLEX_GATEWAY, newComplexGateway);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ConditionalEventDefinition getConditionalEventDefinition() {
        return (ConditionalEventDefinition) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CONDITIONAL_EVENT_DEFINITION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetConditionalEventDefinition(
            ConditionalEventDefinition newConditionalEventDefinition, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CONDITIONAL_EVENT_DEFINITION,
                newConditionalEventDefinition, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setConditionalEventDefinition(
            ConditionalEventDefinition newConditionalEventDefinition) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CONDITIONAL_EVENT_DEFINITION,
                newConditionalEventDefinition);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Conversation getConversation() {
        return (Conversation) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__CONVERSATION,
                true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetConversation(Conversation newConversation,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CONVERSATION, newConversation, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setConversation(Conversation newConversation) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__CONVERSATION,
                newConversation);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ConversationAssociation getConversationAssociation() {
        return (ConversationAssociation) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CONVERSATION_ASSOCIATION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetConversationAssociation(
            ConversationAssociation newConversationAssociation, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CONVERSATION_ASSOCIATION,
                newConversationAssociation, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setConversationAssociation(ConversationAssociation newConversationAssociation) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CONVERSATION_ASSOCIATION,
                newConversationAssociation);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ConversationLink getConversationLink() {
        return (ConversationLink) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CONVERSATION_LINK, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetConversationLink(ConversationLink newConversationLink,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CONVERSATION_LINK, newConversationLink, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setConversationLink(ConversationLink newConversationLink) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CONVERSATION_LINK, newConversationLink);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CorrelationKey getCorrelationKey() {
        return (CorrelationKey) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_KEY, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCorrelationKey(CorrelationKey newCorrelationKey,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_KEY, newCorrelationKey, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCorrelationKey(CorrelationKey newCorrelationKey) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_KEY, newCorrelationKey);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CorrelationProperty getCorrelationProperty() {
        return (CorrelationProperty) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCorrelationProperty(
            CorrelationProperty newCorrelationProperty, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY, newCorrelationProperty,
                msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCorrelationProperty(CorrelationProperty newCorrelationProperty) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY, newCorrelationProperty);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CorrelationPropertyBinding getCorrelationPropertyBinding() {
        return (CorrelationPropertyBinding) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY_BINDING, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCorrelationPropertyBinding(
            CorrelationPropertyBinding newCorrelationPropertyBinding, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY_BINDING,
                newCorrelationPropertyBinding, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCorrelationPropertyBinding(
            CorrelationPropertyBinding newCorrelationPropertyBinding) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY_BINDING,
                newCorrelationPropertyBinding);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CorrelationPropertyRetrievalExpression getCorrelationPropertyRetrievalExpression() {
        return (CorrelationPropertyRetrievalExpression) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY_RETRIEVAL_EXPRESSION,
                true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCorrelationPropertyRetrievalExpression(
            CorrelationPropertyRetrievalExpression newCorrelationPropertyRetrievalExpression,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY_RETRIEVAL_EXPRESSION,
                newCorrelationPropertyRetrievalExpression, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCorrelationPropertyRetrievalExpression(
            CorrelationPropertyRetrievalExpression newCorrelationPropertyRetrievalExpression) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_PROPERTY_RETRIEVAL_EXPRESSION,
                newCorrelationPropertyRetrievalExpression);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CorrelationSubscription getCorrelationSubscription() {
        return (CorrelationSubscription) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_SUBSCRIPTION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCorrelationSubscription(
            CorrelationSubscription newCorrelationSubscription, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_SUBSCRIPTION,
                newCorrelationSubscription, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCorrelationSubscription(CorrelationSubscription newCorrelationSubscription) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__CORRELATION_SUBSCRIPTION,
                newCorrelationSubscription);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataAssociation getDataAssociation() {
        return (DataAssociation) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_ASSOCIATION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDataAssociation(DataAssociation newDataAssociation,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_ASSOCIATION, newDataAssociation, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDataAssociation(DataAssociation newDataAssociation) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_ASSOCIATION, newDataAssociation);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataInput getDataInput() {
        return (DataInput) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_INPUT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDataInput(DataInput newDataInput, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_INPUT, newDataInput, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDataInput(DataInput newDataInput) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_INPUT,
                newDataInput);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataInputAssociation getDataInputAssociation() {
        return (DataInputAssociation) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_INPUT_ASSOCIATION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDataInputAssociation(
            DataInputAssociation newDataInputAssociation, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_INPUT_ASSOCIATION,
                newDataInputAssociation, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDataInputAssociation(DataInputAssociation newDataInputAssociation) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_INPUT_ASSOCIATION,
                newDataInputAssociation);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataObject getDataObject() {
        return (DataObject) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_OBJECT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDataObject(DataObject newDataObject, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_OBJECT, newDataObject, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDataObject(DataObject newDataObject) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_OBJECT,
                newDataObject);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataObjectReference getDataObjectReference() {
        return (DataObjectReference) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_OBJECT_REFERENCE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDataObjectReference(
            DataObjectReference newDataObjectReference, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_OBJECT_REFERENCE, newDataObjectReference,
                msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDataObjectReference(DataObjectReference newDataObjectReference) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_OBJECT_REFERENCE, newDataObjectReference);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataOutput getDataOutput() {
        return (DataOutput) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_OUTPUT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDataOutput(DataOutput newDataOutput, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_OUTPUT, newDataOutput, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDataOutput(DataOutput newDataOutput) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_OUTPUT,
                newDataOutput);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataOutputAssociation getDataOutputAssociation() {
        return (DataOutputAssociation) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_OUTPUT_ASSOCIATION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDataOutputAssociation(
            DataOutputAssociation newDataOutputAssociation, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_OUTPUT_ASSOCIATION,
                newDataOutputAssociation, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDataOutputAssociation(DataOutputAssociation newDataOutputAssociation) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_OUTPUT_ASSOCIATION,
                newDataOutputAssociation);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataState getDataState() {
        return (DataState) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_STATE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDataState(DataState newDataState, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_STATE, newDataState, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDataState(DataState newDataState) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_STATE,
                newDataState);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataStore getDataStore() {
        return (DataStore) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_STORE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDataStore(DataStore newDataStore, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_STORE, newDataStore, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDataStore(DataStore newDataStore) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_STORE,
                newDataStore);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataStoreReference getDataStoreReference() {
        return (DataStoreReference) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_STORE_REFERENCE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDataStoreReference(DataStoreReference newDataStoreReference,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_STORE_REFERENCE, newDataStoreReference,
                msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDataStoreReference(DataStoreReference newDataStoreReference) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__DATA_STORE_REFERENCE, newDataStoreReference);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Definitions getDefinitions() {
        return (Definitions) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__DEFINITIONS, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDefinitions(Definitions newDefinitions, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__DEFINITIONS, newDefinitions, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDefinitions(Definitions newDefinitions) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__DEFINITIONS,
                newDefinitions);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Documentation getDocumentation() {
        return (Documentation) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__DOCUMENTATION,
                true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDocumentation(Documentation newDocumentation,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__DOCUMENTATION, newDocumentation, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDocumentation(Documentation newDocumentation) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__DOCUMENTATION,
                newDocumentation);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndEvent getEndEvent() {
        return (EndEvent) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__END_EVENT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEndEvent(EndEvent newEndEvent, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__END_EVENT, newEndEvent, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEndEvent(EndEvent newEndEvent) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__END_EVENT,
                newEndEvent);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndPoint getEndPoint() {
        return (EndPoint) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__END_POINT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEndPoint(EndPoint newEndPoint, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__END_POINT, newEndPoint, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEndPoint(EndPoint newEndPoint) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__END_POINT,
                newEndPoint);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public org.eclipse.bpmn2.Error getError() {
        return (org.eclipse.bpmn2.Error) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__ERROR,
                true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetError(org.eclipse.bpmn2.Error newError, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__ERROR, newError, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setError(org.eclipse.bpmn2.Error newError) {
        ((FeatureMap.Internal) getMixed())
                .set(Bpmn2Package.Literals.DOCUMENT_ROOT__ERROR, newError);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ErrorEventDefinition getErrorEventDefinition() {
        return (ErrorEventDefinition) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__ERROR_EVENT_DEFINITION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetErrorEventDefinition(
            ErrorEventDefinition newErrorEventDefinition, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__ERROR_EVENT_DEFINITION,
                newErrorEventDefinition, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setErrorEventDefinition(ErrorEventDefinition newErrorEventDefinition) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__ERROR_EVENT_DEFINITION,
                newErrorEventDefinition);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Escalation getEscalation() {
        return (Escalation) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__ESCALATION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEscalation(Escalation newEscalation, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__ESCALATION, newEscalation, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEscalation(Escalation newEscalation) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__ESCALATION,
                newEscalation);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EscalationEventDefinition getEscalationEventDefinition() {
        return (EscalationEventDefinition) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__ESCALATION_EVENT_DEFINITION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEscalationEventDefinition(
            EscalationEventDefinition newEscalationEventDefinition, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__ESCALATION_EVENT_DEFINITION,
                newEscalationEventDefinition, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEscalationEventDefinition(EscalationEventDefinition newEscalationEventDefinition) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__ESCALATION_EVENT_DEFINITION,
                newEscalationEventDefinition);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Event getEvent() {
        return (Event) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEvent(Event newEvent, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT, newEvent, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEvent(Event newEvent) {
        ((FeatureMap.Internal) getMixed())
                .set(Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT, newEvent);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EventBasedGateway getEventBasedGateway() {
        return (EventBasedGateway) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT_BASED_GATEWAY, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEventBasedGateway(EventBasedGateway newEventBasedGateway,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT_BASED_GATEWAY, newEventBasedGateway,
                msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEventBasedGateway(EventBasedGateway newEventBasedGateway) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__EVENT_BASED_GATEWAY, newEventBasedGateway);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExclusiveGateway getExclusiveGateway() {
        return (ExclusiveGateway) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__EXCLUSIVE_GATEWAY, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetExclusiveGateway(ExclusiveGateway newExclusiveGateway,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__EXCLUSIVE_GATEWAY, newExclusiveGateway, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setExclusiveGateway(ExclusiveGateway newExclusiveGateway) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__EXCLUSIVE_GATEWAY, newExclusiveGateway);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Expression getExpression() {
        return (Expression) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__EXPRESSION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetExpression(Expression newExpression, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__EXPRESSION, newExpression, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setExpression(Expression newExpression) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__EXPRESSION,
                newExpression);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Extension getExtension() {
        return (Extension) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetExtension(Extension newExtension, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION, newExtension, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setExtension(Extension newExtension) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION,
                newExtension);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ExtensionAttributeValue getExtensionElements() {
        return (ExtensionAttributeValue) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetExtensionElements(
            ExtensionAttributeValue newExtensionElements, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed())
                .basicAdd(Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS,
                        newExtensionElements, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setExtensionElements(ExtensionAttributeValue newExtensionElements) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__EXTENSION_ELEMENTS, newExtensionElements);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FlowNode getFlowNode() {
        return (FlowNode) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFlowNode(FlowNode newFlowNode, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE, newFlowNode, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFlowNode(FlowNode newFlowNode) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__FLOW_NODE,
                newFlowNode);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FormalExpression getFormalExpression() {
        return (FormalExpression) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__FORMAL_EXPRESSION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFormalExpression(FormalExpression newFormalExpression,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__FORMAL_EXPRESSION, newFormalExpression, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFormalExpression(FormalExpression newFormalExpression) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__FORMAL_EXPRESSION, newFormalExpression);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Gateway getGateway() {
        return (Gateway) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__GATEWAY, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetGateway(Gateway newGateway, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__GATEWAY, newGateway, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GlobalBusinessRuleTask getGlobalBusinessRuleTask() {
        return (GlobalBusinessRuleTask) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_BUSINESS_RULE_TASK, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetGlobalBusinessRuleTask(
            GlobalBusinessRuleTask newGlobalBusinessRuleTask, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_BUSINESS_RULE_TASK,
                newGlobalBusinessRuleTask, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGlobalBusinessRuleTask(GlobalBusinessRuleTask newGlobalBusinessRuleTask) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_BUSINESS_RULE_TASK,
                newGlobalBusinessRuleTask);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GlobalChoreographyTask getGlobalChoreographyTask() {
        return (GlobalChoreographyTask) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_CHOREOGRAPHY_TASK, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetGlobalChoreographyTask(
            GlobalChoreographyTask newGlobalChoreographyTask, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_CHOREOGRAPHY_TASK,
                newGlobalChoreographyTask, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGlobalChoreographyTask(GlobalChoreographyTask newGlobalChoreographyTask) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_CHOREOGRAPHY_TASK,
                newGlobalChoreographyTask);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GlobalConversation getGlobalConversation() {
        return (GlobalConversation) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_CONVERSATION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetGlobalConversation(GlobalConversation newGlobalConversation,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_CONVERSATION, newGlobalConversation,
                msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGlobalConversation(GlobalConversation newGlobalConversation) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_CONVERSATION, newGlobalConversation);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GlobalManualTask getGlobalManualTask() {
        return (GlobalManualTask) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_MANUAL_TASK, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetGlobalManualTask(GlobalManualTask newGlobalManualTask,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_MANUAL_TASK, newGlobalManualTask, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGlobalManualTask(GlobalManualTask newGlobalManualTask) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_MANUAL_TASK, newGlobalManualTask);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GlobalScriptTask getGlobalScriptTask() {
        return (GlobalScriptTask) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_SCRIPT_TASK, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetGlobalScriptTask(GlobalScriptTask newGlobalScriptTask,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_SCRIPT_TASK, newGlobalScriptTask, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGlobalScriptTask(GlobalScriptTask newGlobalScriptTask) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_SCRIPT_TASK, newGlobalScriptTask);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GlobalTask getGlobalTask() {
        return (GlobalTask) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_TASK, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetGlobalTask(GlobalTask newGlobalTask, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_TASK, newGlobalTask, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGlobalTask(GlobalTask newGlobalTask) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_TASK,
                newGlobalTask);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GlobalUserTask getGlobalUserTask() {
        return (GlobalUserTask) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_USER_TASK, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetGlobalUserTask(GlobalUserTask newGlobalUserTask,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_USER_TASK, newGlobalUserTask, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGlobalUserTask(GlobalUserTask newGlobalUserTask) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__GLOBAL_USER_TASK, newGlobalUserTask);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Group getGroup() {
        return (Group) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__GROUP, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetGroup(Group newGroup, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__GROUP, newGroup, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGroup(Group newGroup) {
        ((FeatureMap.Internal) getMixed())
                .set(Bpmn2Package.Literals.DOCUMENT_ROOT__GROUP, newGroup);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public HumanPerformer getHumanPerformer() {
        return (HumanPerformer) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__HUMAN_PERFORMER, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetHumanPerformer(HumanPerformer newHumanPerformer,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__HUMAN_PERFORMER, newHumanPerformer, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setHumanPerformer(HumanPerformer newHumanPerformer) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__HUMAN_PERFORMER, newHumanPerformer);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Performer getPerformer() {
        return (Performer) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__PERFORMER, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPerformer(Performer newPerformer, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__PERFORMER, newPerformer, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPerformer(Performer newPerformer) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__PERFORMER,
                newPerformer);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ResourceRole getResourceRole() {
        return (ResourceRole) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_ROLE,
                true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetResourceRole(ResourceRole newResourceRole,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_ROLE, newResourceRole, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setResourceRole(ResourceRole newResourceRole) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_ROLE,
                newResourceRole);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ImplicitThrowEvent getImplicitThrowEvent() {
        return (ImplicitThrowEvent) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__IMPLICIT_THROW_EVENT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetImplicitThrowEvent(ImplicitThrowEvent newImplicitThrowEvent,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__IMPLICIT_THROW_EVENT, newImplicitThrowEvent,
                msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setImplicitThrowEvent(ImplicitThrowEvent newImplicitThrowEvent) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__IMPLICIT_THROW_EVENT, newImplicitThrowEvent);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Import getImport() {
        return (Import) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__IMPORT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetImport(Import newImport, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__IMPORT, newImport, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setImport(Import newImport) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__IMPORT,
                newImport);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InclusiveGateway getInclusiveGateway() {
        return (InclusiveGateway) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__INCLUSIVE_GATEWAY, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetInclusiveGateway(InclusiveGateway newInclusiveGateway,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__INCLUSIVE_GATEWAY, newInclusiveGateway, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInclusiveGateway(InclusiveGateway newInclusiveGateway) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__INCLUSIVE_GATEWAY, newInclusiveGateway);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InputSet getInputSet() {
        return (InputSet) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__INPUT_SET, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetInputSet(InputSet newInputSet, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__INPUT_SET, newInputSet, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInputSet(InputSet newInputSet) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__INPUT_SET,
                newInputSet);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Interface getInterface() {
        return (Interface) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__INTERFACE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetInterface(Interface newInterface, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__INTERFACE, newInterface, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInterface(Interface newInterface) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__INTERFACE,
                newInterface);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntermediateCatchEvent getIntermediateCatchEvent() {
        return (IntermediateCatchEvent) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__INTERMEDIATE_CATCH_EVENT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetIntermediateCatchEvent(
            IntermediateCatchEvent newIntermediateCatchEvent, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__INTERMEDIATE_CATCH_EVENT,
                newIntermediateCatchEvent, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIntermediateCatchEvent(IntermediateCatchEvent newIntermediateCatchEvent) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__INTERMEDIATE_CATCH_EVENT,
                newIntermediateCatchEvent);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntermediateThrowEvent getIntermediateThrowEvent() {
        return (IntermediateThrowEvent) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__INTERMEDIATE_THROW_EVENT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetIntermediateThrowEvent(
            IntermediateThrowEvent newIntermediateThrowEvent, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__INTERMEDIATE_THROW_EVENT,
                newIntermediateThrowEvent, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIntermediateThrowEvent(IntermediateThrowEvent newIntermediateThrowEvent) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__INTERMEDIATE_THROW_EVENT,
                newIntermediateThrowEvent);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InputOutputBinding getIoBinding() {
        return (InputOutputBinding) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__IO_BINDING,
                true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetIoBinding(InputOutputBinding newIoBinding,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__IO_BINDING, newIoBinding, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIoBinding(InputOutputBinding newIoBinding) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__IO_BINDING,
                newIoBinding);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InputOutputSpecification getIoSpecification() {
        return (InputOutputSpecification) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__IO_SPECIFICATION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetIoSpecification(InputOutputSpecification newIoSpecification,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__IO_SPECIFICATION, newIoSpecification, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIoSpecification(InputOutputSpecification newIoSpecification) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__IO_SPECIFICATION, newIoSpecification);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ItemDefinition getItemDefinition() {
        return (ItemDefinition) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__ITEM_DEFINITION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetItemDefinition(ItemDefinition newItemDefinition,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__ITEM_DEFINITION, newItemDefinition, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setItemDefinition(ItemDefinition newItemDefinition) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__ITEM_DEFINITION, newItemDefinition);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Lane getLane() {
        return (Lane) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__LANE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLane(Lane newLane, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__LANE, newLane, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLane(Lane newLane) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__LANE, newLane);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LaneSet getLaneSet() {
        return (LaneSet) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__LANE_SET, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLaneSet(LaneSet newLaneSet, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__LANE_SET, newLaneSet, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLaneSet(LaneSet newLaneSet) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__LANE_SET,
                newLaneSet);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LinkEventDefinition getLinkEventDefinition() {
        return (LinkEventDefinition) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__LINK_EVENT_DEFINITION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLinkEventDefinition(
            LinkEventDefinition newLinkEventDefinition, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__LINK_EVENT_DEFINITION, newLinkEventDefinition,
                msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLinkEventDefinition(LinkEventDefinition newLinkEventDefinition) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__LINK_EVENT_DEFINITION, newLinkEventDefinition);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LoopCharacteristics getLoopCharacteristics() {
        return (LoopCharacteristics) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__LOOP_CHARACTERISTICS, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLoopCharacteristics(
            LoopCharacteristics newLoopCharacteristics, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__LOOP_CHARACTERISTICS, newLoopCharacteristics,
                msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLoopCharacteristics(LoopCharacteristics newLoopCharacteristics) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__LOOP_CHARACTERISTICS, newLoopCharacteristics);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ManualTask getManualTask() {
        return (ManualTask) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__MANUAL_TASK, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetManualTask(ManualTask newManualTask, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__MANUAL_TASK, newManualTask, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setManualTask(ManualTask newManualTask) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__MANUAL_TASK,
                newManualTask);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Message getMessage() {
        return (Message) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__MESSAGE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetMessage(Message newMessage, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__MESSAGE, newMessage, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMessage(Message newMessage) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__MESSAGE,
                newMessage);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MessageEventDefinition getMessageEventDefinition() {
        return (MessageEventDefinition) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__MESSAGE_EVENT_DEFINITION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetMessageEventDefinition(
            MessageEventDefinition newMessageEventDefinition, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__MESSAGE_EVENT_DEFINITION,
                newMessageEventDefinition, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMessageEventDefinition(MessageEventDefinition newMessageEventDefinition) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__MESSAGE_EVENT_DEFINITION,
                newMessageEventDefinition);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MessageFlow getMessageFlow() {
        return (MessageFlow) getMixed()
                .get(Bpmn2Package.Literals.DOCUMENT_ROOT__MESSAGE_FLOW, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetMessageFlow(MessageFlow newMessageFlow, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__MESSAGE_FLOW, newMessageFlow, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMessageFlow(MessageFlow newMessageFlow) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__MESSAGE_FLOW,
                newMessageFlow);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MessageFlowAssociation getMessageFlowAssociation() {
        return (MessageFlowAssociation) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__MESSAGE_FLOW_ASSOCIATION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetMessageFlowAssociation(
            MessageFlowAssociation newMessageFlowAssociation, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__MESSAGE_FLOW_ASSOCIATION,
                newMessageFlowAssociation, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMessageFlowAssociation(MessageFlowAssociation newMessageFlowAssociation) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__MESSAGE_FLOW_ASSOCIATION,
                newMessageFlowAssociation);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Monitoring getMonitoring() {
        return (Monitoring) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__MONITORING, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetMonitoring(Monitoring newMonitoring, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__MONITORING, newMonitoring, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMonitoring(Monitoring newMonitoring) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__MONITORING,
                newMonitoring);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MultiInstanceLoopCharacteristics getMultiInstanceLoopCharacteristics() {
        return (MultiInstanceLoopCharacteristics) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__MULTI_INSTANCE_LOOP_CHARACTERISTICS, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetMultiInstanceLoopCharacteristics(
            MultiInstanceLoopCharacteristics newMultiInstanceLoopCharacteristics,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__MULTI_INSTANCE_LOOP_CHARACTERISTICS,
                newMultiInstanceLoopCharacteristics, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMultiInstanceLoopCharacteristics(
            MultiInstanceLoopCharacteristics newMultiInstanceLoopCharacteristics) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__MULTI_INSTANCE_LOOP_CHARACTERISTICS,
                newMultiInstanceLoopCharacteristics);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Operation getOperation() {
        return (Operation) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__OPERATION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetOperation(Operation newOperation, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__OPERATION, newOperation, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOperation(Operation newOperation) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__OPERATION,
                newOperation);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OutputSet getOutputSet() {
        return (OutputSet) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__OUTPUT_SET, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetOutputSet(OutputSet newOutputSet, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__OUTPUT_SET, newOutputSet, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOutputSet(OutputSet newOutputSet) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__OUTPUT_SET,
                newOutputSet);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ParallelGateway getParallelGateway() {
        return (ParallelGateway) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__PARALLEL_GATEWAY, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetParallelGateway(ParallelGateway newParallelGateway,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__PARALLEL_GATEWAY, newParallelGateway, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParallelGateway(ParallelGateway newParallelGateway) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__PARALLEL_GATEWAY, newParallelGateway);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Participant getParticipant() {
        return (Participant) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__PARTICIPANT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetParticipant(Participant newParticipant, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__PARTICIPANT, newParticipant, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParticipant(Participant newParticipant) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__PARTICIPANT,
                newParticipant);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ParticipantAssociation getParticipantAssociation() {
        return (ParticipantAssociation) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__PARTICIPANT_ASSOCIATION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetParticipantAssociation(
            ParticipantAssociation newParticipantAssociation, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__PARTICIPANT_ASSOCIATION,
                newParticipantAssociation, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParticipantAssociation(ParticipantAssociation newParticipantAssociation) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__PARTICIPANT_ASSOCIATION,
                newParticipantAssociation);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ParticipantMultiplicity getParticipantMultiplicity() {
        return (ParticipantMultiplicity) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__PARTICIPANT_MULTIPLICITY, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetParticipantMultiplicity(
            ParticipantMultiplicity newParticipantMultiplicity, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__PARTICIPANT_MULTIPLICITY,
                newParticipantMultiplicity, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParticipantMultiplicity(ParticipantMultiplicity newParticipantMultiplicity) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__PARTICIPANT_MULTIPLICITY,
                newParticipantMultiplicity);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PartnerEntity getPartnerEntity() {
        return (PartnerEntity) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__PARTNER_ENTITY,
                true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPartnerEntity(PartnerEntity newPartnerEntity,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__PARTNER_ENTITY, newPartnerEntity, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPartnerEntity(PartnerEntity newPartnerEntity) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__PARTNER_ENTITY,
                newPartnerEntity);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PartnerRole getPartnerRole() {
        return (PartnerRole) getMixed()
                .get(Bpmn2Package.Literals.DOCUMENT_ROOT__PARTNER_ROLE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPartnerRole(PartnerRole newPartnerRole, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__PARTNER_ROLE, newPartnerRole, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPartnerRole(PartnerRole newPartnerRole) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__PARTNER_ROLE,
                newPartnerRole);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PotentialOwner getPotentialOwner() {
        return (PotentialOwner) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__POTENTIAL_OWNER, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPotentialOwner(PotentialOwner newPotentialOwner,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__POTENTIAL_OWNER, newPotentialOwner, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPotentialOwner(PotentialOwner newPotentialOwner) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__POTENTIAL_OWNER, newPotentialOwner);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public org.eclipse.bpmn2.Process getProcess() {
        return (org.eclipse.bpmn2.Process) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__PROCESS, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetProcess(org.eclipse.bpmn2.Process newProcess,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__PROCESS, newProcess, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setProcess(org.eclipse.bpmn2.Process newProcess) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__PROCESS,
                newProcess);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Property getProperty() {
        return (Property) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__PROPERTY, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetProperty(Property newProperty, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__PROPERTY, newProperty, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setProperty(Property newProperty) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__PROPERTY,
                newProperty);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ReceiveTask getReceiveTask() {
        return (ReceiveTask) getMixed()
                .get(Bpmn2Package.Literals.DOCUMENT_ROOT__RECEIVE_TASK, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetReceiveTask(ReceiveTask newReceiveTask, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__RECEIVE_TASK, newReceiveTask, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setReceiveTask(ReceiveTask newReceiveTask) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__RECEIVE_TASK,
                newReceiveTask);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Relationship getRelationship() {
        return (Relationship) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__RELATIONSHIP,
                true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRelationship(Relationship newRelationship,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__RELATIONSHIP, newRelationship, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRelationship(Relationship newRelationship) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__RELATIONSHIP,
                newRelationship);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Rendering getRendering() {
        return (Rendering) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__RENDERING, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRendering(Rendering newRendering, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__RENDERING, newRendering, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRendering(Rendering newRendering) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__RENDERING,
                newRendering);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Resource getResource() {
        return (Resource) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetResource(Resource newResource, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE, newResource, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setResource(Resource newResource) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE,
                newResource);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ResourceAssignmentExpression getResourceAssignmentExpression() {
        return (ResourceAssignmentExpression) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_ASSIGNMENT_EXPRESSION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetResourceAssignmentExpression(
            ResourceAssignmentExpression newResourceAssignmentExpression, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_ASSIGNMENT_EXPRESSION,
                newResourceAssignmentExpression, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setResourceAssignmentExpression(
            ResourceAssignmentExpression newResourceAssignmentExpression) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_ASSIGNMENT_EXPRESSION,
                newResourceAssignmentExpression);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ResourceParameter getResourceParameter() {
        return (ResourceParameter) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_PARAMETER, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetResourceParameter(ResourceParameter newResourceParameter,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed())
                .basicAdd(Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_PARAMETER,
                        newResourceParameter, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setResourceParameter(ResourceParameter newResourceParameter) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_PARAMETER, newResourceParameter);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ResourceParameterBinding getResourceParameterBinding() {
        return (ResourceParameterBinding) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_PARAMETER_BINDING, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetResourceParameterBinding(
            ResourceParameterBinding newResourceParameterBinding, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_PARAMETER_BINDING,
                newResourceParameterBinding, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setResourceParameterBinding(ResourceParameterBinding newResourceParameterBinding) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__RESOURCE_PARAMETER_BINDING,
                newResourceParameterBinding);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object getScript() {
        return (Object) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetScript(EObject newScript, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT, newScript, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setScript(Object newScript) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT,
                newScript);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ScriptTask getScriptTask() {
        return (ScriptTask) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT_TASK, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetScriptTask(ScriptTask newScriptTask, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT_TASK, newScriptTask, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setScriptTask(ScriptTask newScriptTask) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__SCRIPT_TASK,
                newScriptTask);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SendTask getSendTask() {
        return (SendTask) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__SEND_TASK, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSendTask(SendTask newSendTask, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__SEND_TASK, newSendTask, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSendTask(SendTask newSendTask) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__SEND_TASK,
                newSendTask);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SequenceFlow getSequenceFlow() {
        return (SequenceFlow) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__SEQUENCE_FLOW,
                true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSequenceFlow(SequenceFlow newSequenceFlow,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__SEQUENCE_FLOW, newSequenceFlow, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSequenceFlow(SequenceFlow newSequenceFlow) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__SEQUENCE_FLOW,
                newSequenceFlow);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ServiceTask getServiceTask() {
        return (ServiceTask) getMixed()
                .get(Bpmn2Package.Literals.DOCUMENT_ROOT__SERVICE_TASK, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetServiceTask(ServiceTask newServiceTask, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__SERVICE_TASK, newServiceTask, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setServiceTask(ServiceTask newServiceTask) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__SERVICE_TASK,
                newServiceTask);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Signal getSignal() {
        return (Signal) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__SIGNAL, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSignal(Signal newSignal, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__SIGNAL, newSignal, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSignal(Signal newSignal) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__SIGNAL,
                newSignal);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SignalEventDefinition getSignalEventDefinition() {
        return (SignalEventDefinition) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__SIGNAL_EVENT_DEFINITION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSignalEventDefinition(
            SignalEventDefinition newSignalEventDefinition, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__SIGNAL_EVENT_DEFINITION,
                newSignalEventDefinition, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSignalEventDefinition(SignalEventDefinition newSignalEventDefinition) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__SIGNAL_EVENT_DEFINITION,
                newSignalEventDefinition);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StandardLoopCharacteristics getStandardLoopCharacteristics() {
        return (StandardLoopCharacteristics) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__STANDARD_LOOP_CHARACTERISTICS, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetStandardLoopCharacteristics(
            StandardLoopCharacteristics newStandardLoopCharacteristics, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__STANDARD_LOOP_CHARACTERISTICS,
                newStandardLoopCharacteristics, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStandardLoopCharacteristics(
            StandardLoopCharacteristics newStandardLoopCharacteristics) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__STANDARD_LOOP_CHARACTERISTICS,
                newStandardLoopCharacteristics);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StartEvent getStartEvent() {
        return (StartEvent) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__START_EVENT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetStartEvent(StartEvent newStartEvent, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__START_EVENT, newStartEvent, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStartEvent(StartEvent newStartEvent) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__START_EVENT,
                newStartEvent);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SubChoreography getSubChoreography() {
        return (SubChoreography) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__SUB_CHOREOGRAPHY, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSubChoreography(SubChoreography newSubChoreography,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__SUB_CHOREOGRAPHY, newSubChoreography, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSubChoreography(SubChoreography newSubChoreography) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__SUB_CHOREOGRAPHY, newSubChoreography);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SubConversation getSubConversation() {
        return (SubConversation) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__SUB_CONVERSATION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSubConversation(SubConversation newSubConversation,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__SUB_CONVERSATION, newSubConversation, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSubConversation(SubConversation newSubConversation) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__SUB_CONVERSATION, newSubConversation);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SubProcess getSubProcess() {
        return (SubProcess) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__SUB_PROCESS, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSubProcess(SubProcess newSubProcess, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__SUB_PROCESS, newSubProcess, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSubProcess(SubProcess newSubProcess) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__SUB_PROCESS,
                newSubProcess);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Task getTask() {
        return (Task) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__TASK, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTask(Task newTask, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__TASK, newTask, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTask(Task newTask) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__TASK, newTask);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TerminateEventDefinition getTerminateEventDefinition() {
        return (TerminateEventDefinition) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__TERMINATE_EVENT_DEFINITION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTerminateEventDefinition(
            TerminateEventDefinition newTerminateEventDefinition, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__TERMINATE_EVENT_DEFINITION,
                newTerminateEventDefinition, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTerminateEventDefinition(TerminateEventDefinition newTerminateEventDefinition) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__TERMINATE_EVENT_DEFINITION,
                newTerminateEventDefinition);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object getText() {
        return (Object) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetText(EObject newText, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT, newText, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setText(Object newText) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT, newText);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TextAnnotation getTextAnnotation() {
        return (TextAnnotation) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT_ANNOTATION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTextAnnotation(TextAnnotation newTextAnnotation,
            NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT_ANNOTATION, newTextAnnotation, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTextAnnotation(TextAnnotation newTextAnnotation) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__TEXT_ANNOTATION, newTextAnnotation);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ThrowEvent getThrowEvent() {
        return (ThrowEvent) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__THROW_EVENT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetThrowEvent(ThrowEvent newThrowEvent, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__THROW_EVENT, newThrowEvent, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setThrowEvent(ThrowEvent newThrowEvent) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__THROW_EVENT,
                newThrowEvent);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TimerEventDefinition getTimerEventDefinition() {
        return (TimerEventDefinition) getMixed().get(
                Bpmn2Package.Literals.DOCUMENT_ROOT__TIMER_EVENT_DEFINITION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTimerEventDefinition(
            TimerEventDefinition newTimerEventDefinition, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__TIMER_EVENT_DEFINITION,
                newTimerEventDefinition, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTimerEventDefinition(TimerEventDefinition newTimerEventDefinition) {
        ((FeatureMap.Internal) getMixed()).set(
                Bpmn2Package.Literals.DOCUMENT_ROOT__TIMER_EVENT_DEFINITION,
                newTimerEventDefinition);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Transaction getTransaction() {
        return (Transaction) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__TRANSACTION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTransaction(Transaction newTransaction, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__TRANSACTION, newTransaction, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTransaction(Transaction newTransaction) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__TRANSACTION,
                newTransaction);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UserTask getUserTask() {
        return (UserTask) getMixed().get(Bpmn2Package.Literals.DOCUMENT_ROOT__USER_TASK, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetUserTask(UserTask newUserTask, NotificationChain msgs) {
        return ((FeatureMap.Internal) getMixed()).basicAdd(
                Bpmn2Package.Literals.DOCUMENT_ROOT__USER_TASK, newUserTask, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUserTask(UserTask newUserTask) {
        ((FeatureMap.Internal) getMixed()).set(Bpmn2Package.Literals.DOCUMENT_ROOT__USER_TASK,
                newUserTask);
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
        case Bpmn2Package.DOCUMENT_ROOT__MIXED:
            return ((InternalEList<?>) getMixed()).basicRemove(otherEnd, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
            return ((InternalEList<?>) ((EMap.InternalMapView<String, String>) getXMLNSPrefixMap())
                    .eMap()).basicRemove(otherEnd, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
            return ((InternalEList<?>) ((EMap.InternalMapView<String, String>) getXSISchemaLocation())
                    .eMap()).basicRemove(otherEnd, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__ACTIVITY:
            return basicSetActivity(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__AD_HOC_SUB_PROCESS:
            return basicSetAdHocSubProcess(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__FLOW_ELEMENT:
            return basicSetFlowElement(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__ARTIFACT:
            return basicSetArtifact(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__ASSIGNMENT:
            return basicSetAssignment(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__ASSOCIATION:
            return basicSetAssociation(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__AUDITING:
            return basicSetAuditing(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__BASE_ELEMENT:
            return basicSetBaseElement(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT:
            return basicSetBaseElementWithMixedContent(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__BOUNDARY_EVENT:
            return basicSetBoundaryEvent(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__BUSINESS_RULE_TASK:
            return basicSetBusinessRuleTask(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__CALLABLE_ELEMENT:
            return basicSetCallableElement(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__CALL_ACTIVITY:
            return basicSetCallActivity(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__CALL_CHOREOGRAPHY:
            return basicSetCallChoreography(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__CALL_CONVERSATION:
            return basicSetCallConversation(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__CONVERSATION_NODE:
            return basicSetConversationNode(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__CANCEL_EVENT_DEFINITION:
            return basicSetCancelEventDefinition(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__EVENT_DEFINITION:
            return basicSetEventDefinition(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__ROOT_ELEMENT:
            return basicSetRootElement(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__CATCH_EVENT:
            return basicSetCatchEvent(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__CATEGORY:
            return basicSetCategory(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__CATEGORY_VALUE:
            return basicSetCategoryValue(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__CHOREOGRAPHY:
            return basicSetChoreography(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__COLLABORATION:
            return basicSetCollaboration(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__CHOREOGRAPHY_ACTIVITY:
            return basicSetChoreographyActivity(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__CHOREOGRAPHY_TASK:
            return basicSetChoreographyTask(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__COMPENSATE_EVENT_DEFINITION:
            return basicSetCompensateEventDefinition(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__COMPLEX_BEHAVIOR_DEFINITION:
            return basicSetComplexBehaviorDefinition(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__COMPLEX_GATEWAY:
            return basicSetComplexGateway(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__CONDITIONAL_EVENT_DEFINITION:
            return basicSetConditionalEventDefinition(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__CONVERSATION:
            return basicSetConversation(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__CONVERSATION_ASSOCIATION:
            return basicSetConversationAssociation(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__CONVERSATION_LINK:
            return basicSetConversationLink(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_KEY:
            return basicSetCorrelationKey(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_PROPERTY:
            return basicSetCorrelationProperty(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_PROPERTY_BINDING:
            return basicSetCorrelationPropertyBinding(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_PROPERTY_RETRIEVAL_EXPRESSION:
            return basicSetCorrelationPropertyRetrievalExpression(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_SUBSCRIPTION:
            return basicSetCorrelationSubscription(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__DATA_ASSOCIATION:
            return basicSetDataAssociation(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__DATA_INPUT:
            return basicSetDataInput(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__DATA_INPUT_ASSOCIATION:
            return basicSetDataInputAssociation(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__DATA_OBJECT:
            return basicSetDataObject(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__DATA_OBJECT_REFERENCE:
            return basicSetDataObjectReference(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__DATA_OUTPUT:
            return basicSetDataOutput(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__DATA_OUTPUT_ASSOCIATION:
            return basicSetDataOutputAssociation(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__DATA_STATE:
            return basicSetDataState(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__DATA_STORE:
            return basicSetDataStore(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__DATA_STORE_REFERENCE:
            return basicSetDataStoreReference(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__DEFINITIONS:
            return basicSetDefinitions(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__DOCUMENTATION:
            return basicSetDocumentation(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__END_EVENT:
            return basicSetEndEvent(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__END_POINT:
            return basicSetEndPoint(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__ERROR:
            return basicSetError(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__ERROR_EVENT_DEFINITION:
            return basicSetErrorEventDefinition(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__ESCALATION:
            return basicSetEscalation(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__ESCALATION_EVENT_DEFINITION:
            return basicSetEscalationEventDefinition(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__EVENT:
            return basicSetEvent(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__EVENT_BASED_GATEWAY:
            return basicSetEventBasedGateway(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__EXCLUSIVE_GATEWAY:
            return basicSetExclusiveGateway(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__EXPRESSION:
            return basicSetExpression(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__EXTENSION:
            return basicSetExtension(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__EXTENSION_ELEMENTS:
            return basicSetExtensionElements(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__FLOW_NODE:
            return basicSetFlowNode(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__FORMAL_EXPRESSION:
            return basicSetFormalExpression(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__GATEWAY:
            return basicSetGateway(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_BUSINESS_RULE_TASK:
            return basicSetGlobalBusinessRuleTask(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_CHOREOGRAPHY_TASK:
            return basicSetGlobalChoreographyTask(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_CONVERSATION:
            return basicSetGlobalConversation(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_MANUAL_TASK:
            return basicSetGlobalManualTask(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_SCRIPT_TASK:
            return basicSetGlobalScriptTask(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_TASK:
            return basicSetGlobalTask(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_USER_TASK:
            return basicSetGlobalUserTask(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__GROUP:
            return basicSetGroup(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__HUMAN_PERFORMER:
            return basicSetHumanPerformer(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__PERFORMER:
            return basicSetPerformer(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__RESOURCE_ROLE:
            return basicSetResourceRole(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__IMPLICIT_THROW_EVENT:
            return basicSetImplicitThrowEvent(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__IMPORT:
            return basicSetImport(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__INCLUSIVE_GATEWAY:
            return basicSetInclusiveGateway(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__INPUT_SET:
            return basicSetInputSet(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__INTERFACE:
            return basicSetInterface(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__INTERMEDIATE_CATCH_EVENT:
            return basicSetIntermediateCatchEvent(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__INTERMEDIATE_THROW_EVENT:
            return basicSetIntermediateThrowEvent(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__IO_BINDING:
            return basicSetIoBinding(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__IO_SPECIFICATION:
            return basicSetIoSpecification(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__ITEM_DEFINITION:
            return basicSetItemDefinition(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__LANE:
            return basicSetLane(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__LANE_SET:
            return basicSetLaneSet(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__LINK_EVENT_DEFINITION:
            return basicSetLinkEventDefinition(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__LOOP_CHARACTERISTICS:
            return basicSetLoopCharacteristics(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__MANUAL_TASK:
            return basicSetManualTask(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__MESSAGE:
            return basicSetMessage(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__MESSAGE_EVENT_DEFINITION:
            return basicSetMessageEventDefinition(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__MESSAGE_FLOW:
            return basicSetMessageFlow(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__MESSAGE_FLOW_ASSOCIATION:
            return basicSetMessageFlowAssociation(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__MONITORING:
            return basicSetMonitoring(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__MULTI_INSTANCE_LOOP_CHARACTERISTICS:
            return basicSetMultiInstanceLoopCharacteristics(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__OPERATION:
            return basicSetOperation(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__OUTPUT_SET:
            return basicSetOutputSet(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__PARALLEL_GATEWAY:
            return basicSetParallelGateway(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__PARTICIPANT:
            return basicSetParticipant(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__PARTICIPANT_ASSOCIATION:
            return basicSetParticipantAssociation(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__PARTICIPANT_MULTIPLICITY:
            return basicSetParticipantMultiplicity(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__PARTNER_ENTITY:
            return basicSetPartnerEntity(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__PARTNER_ROLE:
            return basicSetPartnerRole(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__POTENTIAL_OWNER:
            return basicSetPotentialOwner(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__PROCESS:
            return basicSetProcess(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__PROPERTY:
            return basicSetProperty(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__RECEIVE_TASK:
            return basicSetReceiveTask(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__RELATIONSHIP:
            return basicSetRelationship(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__RENDERING:
            return basicSetRendering(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__RESOURCE:
            return basicSetResource(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__RESOURCE_ASSIGNMENT_EXPRESSION:
            return basicSetResourceAssignmentExpression(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__RESOURCE_PARAMETER:
            return basicSetResourceParameter(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__RESOURCE_PARAMETER_BINDING:
            return basicSetResourceParameterBinding(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__SCRIPT:
            return basicSetScript(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__SCRIPT_TASK:
            return basicSetScriptTask(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__SEND_TASK:
            return basicSetSendTask(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__SEQUENCE_FLOW:
            return basicSetSequenceFlow(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__SERVICE_TASK:
            return basicSetServiceTask(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__SIGNAL:
            return basicSetSignal(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__SIGNAL_EVENT_DEFINITION:
            return basicSetSignalEventDefinition(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__STANDARD_LOOP_CHARACTERISTICS:
            return basicSetStandardLoopCharacteristics(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__START_EVENT:
            return basicSetStartEvent(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__SUB_CHOREOGRAPHY:
            return basicSetSubChoreography(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__SUB_CONVERSATION:
            return basicSetSubConversation(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__SUB_PROCESS:
            return basicSetSubProcess(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__TASK:
            return basicSetTask(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__TERMINATE_EVENT_DEFINITION:
            return basicSetTerminateEventDefinition(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__TEXT:
            return basicSetText(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__TEXT_ANNOTATION:
            return basicSetTextAnnotation(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__THROW_EVENT:
            return basicSetThrowEvent(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__TIMER_EVENT_DEFINITION:
            return basicSetTimerEventDefinition(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__TRANSACTION:
            return basicSetTransaction(null, msgs);
        case Bpmn2Package.DOCUMENT_ROOT__USER_TASK:
            return basicSetUserTask(null, msgs);
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
        case Bpmn2Package.DOCUMENT_ROOT__MIXED:
            if (coreType)
                return getMixed();
            return ((FeatureMap.Internal) getMixed()).getWrapper();
        case Bpmn2Package.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
            if (coreType)
                return ((EMap.InternalMapView<String, String>) getXMLNSPrefixMap()).eMap();
            else
                return getXMLNSPrefixMap();
        case Bpmn2Package.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
            if (coreType)
                return ((EMap.InternalMapView<String, String>) getXSISchemaLocation()).eMap();
            else
                return getXSISchemaLocation();
        case Bpmn2Package.DOCUMENT_ROOT__ACTIVITY:
            return getActivity();
        case Bpmn2Package.DOCUMENT_ROOT__AD_HOC_SUB_PROCESS:
            return getAdHocSubProcess();
        case Bpmn2Package.DOCUMENT_ROOT__FLOW_ELEMENT:
            return getFlowElement();
        case Bpmn2Package.DOCUMENT_ROOT__ARTIFACT:
            return getArtifact();
        case Bpmn2Package.DOCUMENT_ROOT__ASSIGNMENT:
            return getAssignment();
        case Bpmn2Package.DOCUMENT_ROOT__ASSOCIATION:
            return getAssociation();
        case Bpmn2Package.DOCUMENT_ROOT__AUDITING:
            return getAuditing();
        case Bpmn2Package.DOCUMENT_ROOT__BASE_ELEMENT:
            return getBaseElement();
        case Bpmn2Package.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT:
            return getBaseElementWithMixedContent();
        case Bpmn2Package.DOCUMENT_ROOT__BOUNDARY_EVENT:
            return getBoundaryEvent();
        case Bpmn2Package.DOCUMENT_ROOT__BUSINESS_RULE_TASK:
            return getBusinessRuleTask();
        case Bpmn2Package.DOCUMENT_ROOT__CALLABLE_ELEMENT:
            return getCallableElement();
        case Bpmn2Package.DOCUMENT_ROOT__CALL_ACTIVITY:
            return getCallActivity();
        case Bpmn2Package.DOCUMENT_ROOT__CALL_CHOREOGRAPHY:
            return getCallChoreography();
        case Bpmn2Package.DOCUMENT_ROOT__CALL_CONVERSATION:
            return getCallConversation();
        case Bpmn2Package.DOCUMENT_ROOT__CONVERSATION_NODE:
            return getConversationNode();
        case Bpmn2Package.DOCUMENT_ROOT__CANCEL_EVENT_DEFINITION:
            return getCancelEventDefinition();
        case Bpmn2Package.DOCUMENT_ROOT__EVENT_DEFINITION:
            return getEventDefinition();
        case Bpmn2Package.DOCUMENT_ROOT__ROOT_ELEMENT:
            return getRootElement();
        case Bpmn2Package.DOCUMENT_ROOT__CATCH_EVENT:
            return getCatchEvent();
        case Bpmn2Package.DOCUMENT_ROOT__CATEGORY:
            return getCategory();
        case Bpmn2Package.DOCUMENT_ROOT__CATEGORY_VALUE:
            return getCategoryValue();
        case Bpmn2Package.DOCUMENT_ROOT__CHOREOGRAPHY:
            return getChoreography();
        case Bpmn2Package.DOCUMENT_ROOT__COLLABORATION:
            return getCollaboration();
        case Bpmn2Package.DOCUMENT_ROOT__CHOREOGRAPHY_ACTIVITY:
            return getChoreographyActivity();
        case Bpmn2Package.DOCUMENT_ROOT__CHOREOGRAPHY_TASK:
            return getChoreographyTask();
        case Bpmn2Package.DOCUMENT_ROOT__COMPENSATE_EVENT_DEFINITION:
            return getCompensateEventDefinition();
        case Bpmn2Package.DOCUMENT_ROOT__COMPLEX_BEHAVIOR_DEFINITION:
            return getComplexBehaviorDefinition();
        case Bpmn2Package.DOCUMENT_ROOT__COMPLEX_GATEWAY:
            return getComplexGateway();
        case Bpmn2Package.DOCUMENT_ROOT__CONDITIONAL_EVENT_DEFINITION:
            return getConditionalEventDefinition();
        case Bpmn2Package.DOCUMENT_ROOT__CONVERSATION:
            return getConversation();
        case Bpmn2Package.DOCUMENT_ROOT__CONVERSATION_ASSOCIATION:
            return getConversationAssociation();
        case Bpmn2Package.DOCUMENT_ROOT__CONVERSATION_LINK:
            return getConversationLink();
        case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_KEY:
            return getCorrelationKey();
        case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_PROPERTY:
            return getCorrelationProperty();
        case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_PROPERTY_BINDING:
            return getCorrelationPropertyBinding();
        case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_PROPERTY_RETRIEVAL_EXPRESSION:
            return getCorrelationPropertyRetrievalExpression();
        case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_SUBSCRIPTION:
            return getCorrelationSubscription();
        case Bpmn2Package.DOCUMENT_ROOT__DATA_ASSOCIATION:
            return getDataAssociation();
        case Bpmn2Package.DOCUMENT_ROOT__DATA_INPUT:
            return getDataInput();
        case Bpmn2Package.DOCUMENT_ROOT__DATA_INPUT_ASSOCIATION:
            return getDataInputAssociation();
        case Bpmn2Package.DOCUMENT_ROOT__DATA_OBJECT:
            return getDataObject();
        case Bpmn2Package.DOCUMENT_ROOT__DATA_OBJECT_REFERENCE:
            return getDataObjectReference();
        case Bpmn2Package.DOCUMENT_ROOT__DATA_OUTPUT:
            return getDataOutput();
        case Bpmn2Package.DOCUMENT_ROOT__DATA_OUTPUT_ASSOCIATION:
            return getDataOutputAssociation();
        case Bpmn2Package.DOCUMENT_ROOT__DATA_STATE:
            return getDataState();
        case Bpmn2Package.DOCUMENT_ROOT__DATA_STORE:
            return getDataStore();
        case Bpmn2Package.DOCUMENT_ROOT__DATA_STORE_REFERENCE:
            return getDataStoreReference();
        case Bpmn2Package.DOCUMENT_ROOT__DEFINITIONS:
            return getDefinitions();
        case Bpmn2Package.DOCUMENT_ROOT__DOCUMENTATION:
            return getDocumentation();
        case Bpmn2Package.DOCUMENT_ROOT__END_EVENT:
            return getEndEvent();
        case Bpmn2Package.DOCUMENT_ROOT__END_POINT:
            return getEndPoint();
        case Bpmn2Package.DOCUMENT_ROOT__ERROR:
            return getError();
        case Bpmn2Package.DOCUMENT_ROOT__ERROR_EVENT_DEFINITION:
            return getErrorEventDefinition();
        case Bpmn2Package.DOCUMENT_ROOT__ESCALATION:
            return getEscalation();
        case Bpmn2Package.DOCUMENT_ROOT__ESCALATION_EVENT_DEFINITION:
            return getEscalationEventDefinition();
        case Bpmn2Package.DOCUMENT_ROOT__EVENT:
            return getEvent();
        case Bpmn2Package.DOCUMENT_ROOT__EVENT_BASED_GATEWAY:
            return getEventBasedGateway();
        case Bpmn2Package.DOCUMENT_ROOT__EXCLUSIVE_GATEWAY:
            return getExclusiveGateway();
        case Bpmn2Package.DOCUMENT_ROOT__EXPRESSION:
            return getExpression();
        case Bpmn2Package.DOCUMENT_ROOT__EXTENSION:
            return getExtension();
        case Bpmn2Package.DOCUMENT_ROOT__EXTENSION_ELEMENTS:
            return getExtensionElements();
        case Bpmn2Package.DOCUMENT_ROOT__FLOW_NODE:
            return getFlowNode();
        case Bpmn2Package.DOCUMENT_ROOT__FORMAL_EXPRESSION:
            return getFormalExpression();
        case Bpmn2Package.DOCUMENT_ROOT__GATEWAY:
            return getGateway();
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_BUSINESS_RULE_TASK:
            return getGlobalBusinessRuleTask();
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_CHOREOGRAPHY_TASK:
            return getGlobalChoreographyTask();
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_CONVERSATION:
            return getGlobalConversation();
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_MANUAL_TASK:
            return getGlobalManualTask();
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_SCRIPT_TASK:
            return getGlobalScriptTask();
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_TASK:
            return getGlobalTask();
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_USER_TASK:
            return getGlobalUserTask();
        case Bpmn2Package.DOCUMENT_ROOT__GROUP:
            return getGroup();
        case Bpmn2Package.DOCUMENT_ROOT__HUMAN_PERFORMER:
            return getHumanPerformer();
        case Bpmn2Package.DOCUMENT_ROOT__PERFORMER:
            return getPerformer();
        case Bpmn2Package.DOCUMENT_ROOT__RESOURCE_ROLE:
            return getResourceRole();
        case Bpmn2Package.DOCUMENT_ROOT__IMPLICIT_THROW_EVENT:
            return getImplicitThrowEvent();
        case Bpmn2Package.DOCUMENT_ROOT__IMPORT:
            return getImport();
        case Bpmn2Package.DOCUMENT_ROOT__INCLUSIVE_GATEWAY:
            return getInclusiveGateway();
        case Bpmn2Package.DOCUMENT_ROOT__INPUT_SET:
            return getInputSet();
        case Bpmn2Package.DOCUMENT_ROOT__INTERFACE:
            return getInterface();
        case Bpmn2Package.DOCUMENT_ROOT__INTERMEDIATE_CATCH_EVENT:
            return getIntermediateCatchEvent();
        case Bpmn2Package.DOCUMENT_ROOT__INTERMEDIATE_THROW_EVENT:
            return getIntermediateThrowEvent();
        case Bpmn2Package.DOCUMENT_ROOT__IO_BINDING:
            return getIoBinding();
        case Bpmn2Package.DOCUMENT_ROOT__IO_SPECIFICATION:
            return getIoSpecification();
        case Bpmn2Package.DOCUMENT_ROOT__ITEM_DEFINITION:
            return getItemDefinition();
        case Bpmn2Package.DOCUMENT_ROOT__LANE:
            return getLane();
        case Bpmn2Package.DOCUMENT_ROOT__LANE_SET:
            return getLaneSet();
        case Bpmn2Package.DOCUMENT_ROOT__LINK_EVENT_DEFINITION:
            return getLinkEventDefinition();
        case Bpmn2Package.DOCUMENT_ROOT__LOOP_CHARACTERISTICS:
            return getLoopCharacteristics();
        case Bpmn2Package.DOCUMENT_ROOT__MANUAL_TASK:
            return getManualTask();
        case Bpmn2Package.DOCUMENT_ROOT__MESSAGE:
            return getMessage();
        case Bpmn2Package.DOCUMENT_ROOT__MESSAGE_EVENT_DEFINITION:
            return getMessageEventDefinition();
        case Bpmn2Package.DOCUMENT_ROOT__MESSAGE_FLOW:
            return getMessageFlow();
        case Bpmn2Package.DOCUMENT_ROOT__MESSAGE_FLOW_ASSOCIATION:
            return getMessageFlowAssociation();
        case Bpmn2Package.DOCUMENT_ROOT__MONITORING:
            return getMonitoring();
        case Bpmn2Package.DOCUMENT_ROOT__MULTI_INSTANCE_LOOP_CHARACTERISTICS:
            return getMultiInstanceLoopCharacteristics();
        case Bpmn2Package.DOCUMENT_ROOT__OPERATION:
            return getOperation();
        case Bpmn2Package.DOCUMENT_ROOT__OUTPUT_SET:
            return getOutputSet();
        case Bpmn2Package.DOCUMENT_ROOT__PARALLEL_GATEWAY:
            return getParallelGateway();
        case Bpmn2Package.DOCUMENT_ROOT__PARTICIPANT:
            return getParticipant();
        case Bpmn2Package.DOCUMENT_ROOT__PARTICIPANT_ASSOCIATION:
            return getParticipantAssociation();
        case Bpmn2Package.DOCUMENT_ROOT__PARTICIPANT_MULTIPLICITY:
            return getParticipantMultiplicity();
        case Bpmn2Package.DOCUMENT_ROOT__PARTNER_ENTITY:
            return getPartnerEntity();
        case Bpmn2Package.DOCUMENT_ROOT__PARTNER_ROLE:
            return getPartnerRole();
        case Bpmn2Package.DOCUMENT_ROOT__POTENTIAL_OWNER:
            return getPotentialOwner();
        case Bpmn2Package.DOCUMENT_ROOT__PROCESS:
            return getProcess();
        case Bpmn2Package.DOCUMENT_ROOT__PROPERTY:
            return getProperty();
        case Bpmn2Package.DOCUMENT_ROOT__RECEIVE_TASK:
            return getReceiveTask();
        case Bpmn2Package.DOCUMENT_ROOT__RELATIONSHIP:
            return getRelationship();
        case Bpmn2Package.DOCUMENT_ROOT__RENDERING:
            return getRendering();
        case Bpmn2Package.DOCUMENT_ROOT__RESOURCE:
            return getResource();
        case Bpmn2Package.DOCUMENT_ROOT__RESOURCE_ASSIGNMENT_EXPRESSION:
            return getResourceAssignmentExpression();
        case Bpmn2Package.DOCUMENT_ROOT__RESOURCE_PARAMETER:
            return getResourceParameter();
        case Bpmn2Package.DOCUMENT_ROOT__RESOURCE_PARAMETER_BINDING:
            return getResourceParameterBinding();
        case Bpmn2Package.DOCUMENT_ROOT__SCRIPT:
            return getScript();
        case Bpmn2Package.DOCUMENT_ROOT__SCRIPT_TASK:
            return getScriptTask();
        case Bpmn2Package.DOCUMENT_ROOT__SEND_TASK:
            return getSendTask();
        case Bpmn2Package.DOCUMENT_ROOT__SEQUENCE_FLOW:
            return getSequenceFlow();
        case Bpmn2Package.DOCUMENT_ROOT__SERVICE_TASK:
            return getServiceTask();
        case Bpmn2Package.DOCUMENT_ROOT__SIGNAL:
            return getSignal();
        case Bpmn2Package.DOCUMENT_ROOT__SIGNAL_EVENT_DEFINITION:
            return getSignalEventDefinition();
        case Bpmn2Package.DOCUMENT_ROOT__STANDARD_LOOP_CHARACTERISTICS:
            return getStandardLoopCharacteristics();
        case Bpmn2Package.DOCUMENT_ROOT__START_EVENT:
            return getStartEvent();
        case Bpmn2Package.DOCUMENT_ROOT__SUB_CHOREOGRAPHY:
            return getSubChoreography();
        case Bpmn2Package.DOCUMENT_ROOT__SUB_CONVERSATION:
            return getSubConversation();
        case Bpmn2Package.DOCUMENT_ROOT__SUB_PROCESS:
            return getSubProcess();
        case Bpmn2Package.DOCUMENT_ROOT__TASK:
            return getTask();
        case Bpmn2Package.DOCUMENT_ROOT__TERMINATE_EVENT_DEFINITION:
            return getTerminateEventDefinition();
        case Bpmn2Package.DOCUMENT_ROOT__TEXT:
            return getText();
        case Bpmn2Package.DOCUMENT_ROOT__TEXT_ANNOTATION:
            return getTextAnnotation();
        case Bpmn2Package.DOCUMENT_ROOT__THROW_EVENT:
            return getThrowEvent();
        case Bpmn2Package.DOCUMENT_ROOT__TIMER_EVENT_DEFINITION:
            return getTimerEventDefinition();
        case Bpmn2Package.DOCUMENT_ROOT__TRANSACTION:
            return getTransaction();
        case Bpmn2Package.DOCUMENT_ROOT__USER_TASK:
            return getUserTask();
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
        case Bpmn2Package.DOCUMENT_ROOT__MIXED:
            ((FeatureMap.Internal) getMixed()).set(newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
            ((EStructuralFeature.Setting) ((EMap.InternalMapView<String, String>) getXMLNSPrefixMap())
                    .eMap()).set(newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
            ((EStructuralFeature.Setting) ((EMap.InternalMapView<String, String>) getXSISchemaLocation())
                    .eMap()).set(newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__ACTIVITY:
            setActivity((Activity) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__AD_HOC_SUB_PROCESS:
            setAdHocSubProcess((AdHocSubProcess) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__FLOW_ELEMENT:
            setFlowElement((FlowElement) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__ARTIFACT:
            setArtifact((Artifact) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__ASSIGNMENT:
            setAssignment((Assignment) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__ASSOCIATION:
            setAssociation((Association) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__AUDITING:
            setAuditing((Auditing) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__BASE_ELEMENT:
            setBaseElement((BaseElement) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT:
            setBaseElementWithMixedContent((BaseElement) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__BOUNDARY_EVENT:
            setBoundaryEvent((BoundaryEvent) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__BUSINESS_RULE_TASK:
            setBusinessRuleTask((BusinessRuleTask) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CALLABLE_ELEMENT:
            setCallableElement((CallableElement) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CALL_ACTIVITY:
            setCallActivity((CallActivity) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CALL_CHOREOGRAPHY:
            setCallChoreography((CallChoreography) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CALL_CONVERSATION:
            setCallConversation((CallConversation) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CONVERSATION_NODE:
            setConversationNode((ConversationNode) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CANCEL_EVENT_DEFINITION:
            setCancelEventDefinition((CancelEventDefinition) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__EVENT_DEFINITION:
            setEventDefinition((EventDefinition) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__ROOT_ELEMENT:
            setRootElement((RootElement) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CATCH_EVENT:
            setCatchEvent((CatchEvent) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CATEGORY:
            setCategory((Category) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CATEGORY_VALUE:
            setCategoryValue((CategoryValue) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CHOREOGRAPHY:
            setChoreography((Choreography) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__COLLABORATION:
            setCollaboration((Collaboration) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CHOREOGRAPHY_ACTIVITY:
            setChoreographyActivity((ChoreographyActivity) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CHOREOGRAPHY_TASK:
            setChoreographyTask((ChoreographyTask) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__COMPENSATE_EVENT_DEFINITION:
            setCompensateEventDefinition((CompensateEventDefinition) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__COMPLEX_BEHAVIOR_DEFINITION:
            setComplexBehaviorDefinition((ComplexBehaviorDefinition) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__COMPLEX_GATEWAY:
            setComplexGateway((ComplexGateway) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CONDITIONAL_EVENT_DEFINITION:
            setConditionalEventDefinition((ConditionalEventDefinition) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CONVERSATION:
            setConversation((Conversation) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CONVERSATION_ASSOCIATION:
            setConversationAssociation((ConversationAssociation) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CONVERSATION_LINK:
            setConversationLink((ConversationLink) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_KEY:
            setCorrelationKey((CorrelationKey) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_PROPERTY:
            setCorrelationProperty((CorrelationProperty) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_PROPERTY_BINDING:
            setCorrelationPropertyBinding((CorrelationPropertyBinding) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_PROPERTY_RETRIEVAL_EXPRESSION:
            setCorrelationPropertyRetrievalExpression((CorrelationPropertyRetrievalExpression) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_SUBSCRIPTION:
            setCorrelationSubscription((CorrelationSubscription) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_ASSOCIATION:
            setDataAssociation((DataAssociation) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_INPUT:
            setDataInput((DataInput) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_INPUT_ASSOCIATION:
            setDataInputAssociation((DataInputAssociation) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_OBJECT:
            setDataObject((DataObject) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_OBJECT_REFERENCE:
            setDataObjectReference((DataObjectReference) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_OUTPUT:
            setDataOutput((DataOutput) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_OUTPUT_ASSOCIATION:
            setDataOutputAssociation((DataOutputAssociation) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_STATE:
            setDataState((DataState) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_STORE:
            setDataStore((DataStore) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_STORE_REFERENCE:
            setDataStoreReference((DataStoreReference) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__DEFINITIONS:
            setDefinitions((Definitions) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__DOCUMENTATION:
            setDocumentation((Documentation) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__END_EVENT:
            setEndEvent((EndEvent) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__END_POINT:
            setEndPoint((EndPoint) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__ERROR:
            setError((org.eclipse.bpmn2.Error) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__ERROR_EVENT_DEFINITION:
            setErrorEventDefinition((ErrorEventDefinition) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__ESCALATION:
            setEscalation((Escalation) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__ESCALATION_EVENT_DEFINITION:
            setEscalationEventDefinition((EscalationEventDefinition) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__EVENT:
            setEvent((Event) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__EVENT_BASED_GATEWAY:
            setEventBasedGateway((EventBasedGateway) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__EXCLUSIVE_GATEWAY:
            setExclusiveGateway((ExclusiveGateway) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__EXPRESSION:
            setExpression((Expression) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__EXTENSION:
            setExtension((Extension) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__EXTENSION_ELEMENTS:
            setExtensionElements((ExtensionAttributeValue) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__FLOW_NODE:
            setFlowNode((FlowNode) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__FORMAL_EXPRESSION:
            setFormalExpression((FormalExpression) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_BUSINESS_RULE_TASK:
            setGlobalBusinessRuleTask((GlobalBusinessRuleTask) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_CHOREOGRAPHY_TASK:
            setGlobalChoreographyTask((GlobalChoreographyTask) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_CONVERSATION:
            setGlobalConversation((GlobalConversation) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_MANUAL_TASK:
            setGlobalManualTask((GlobalManualTask) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_SCRIPT_TASK:
            setGlobalScriptTask((GlobalScriptTask) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_TASK:
            setGlobalTask((GlobalTask) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_USER_TASK:
            setGlobalUserTask((GlobalUserTask) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__GROUP:
            setGroup((Group) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__HUMAN_PERFORMER:
            setHumanPerformer((HumanPerformer) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__PERFORMER:
            setPerformer((Performer) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__RESOURCE_ROLE:
            setResourceRole((ResourceRole) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__IMPLICIT_THROW_EVENT:
            setImplicitThrowEvent((ImplicitThrowEvent) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__IMPORT:
            setImport((Import) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__INCLUSIVE_GATEWAY:
            setInclusiveGateway((InclusiveGateway) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__INPUT_SET:
            setInputSet((InputSet) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__INTERFACE:
            setInterface((Interface) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__INTERMEDIATE_CATCH_EVENT:
            setIntermediateCatchEvent((IntermediateCatchEvent) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__INTERMEDIATE_THROW_EVENT:
            setIntermediateThrowEvent((IntermediateThrowEvent) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__IO_BINDING:
            setIoBinding((InputOutputBinding) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__IO_SPECIFICATION:
            setIoSpecification((InputOutputSpecification) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__ITEM_DEFINITION:
            setItemDefinition((ItemDefinition) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__LANE:
            setLane((Lane) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__LANE_SET:
            setLaneSet((LaneSet) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__LINK_EVENT_DEFINITION:
            setLinkEventDefinition((LinkEventDefinition) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__LOOP_CHARACTERISTICS:
            setLoopCharacteristics((LoopCharacteristics) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__MANUAL_TASK:
            setManualTask((ManualTask) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__MESSAGE:
            setMessage((Message) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__MESSAGE_EVENT_DEFINITION:
            setMessageEventDefinition((MessageEventDefinition) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__MESSAGE_FLOW:
            setMessageFlow((MessageFlow) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__MESSAGE_FLOW_ASSOCIATION:
            setMessageFlowAssociation((MessageFlowAssociation) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__MONITORING:
            setMonitoring((Monitoring) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__MULTI_INSTANCE_LOOP_CHARACTERISTICS:
            setMultiInstanceLoopCharacteristics((MultiInstanceLoopCharacteristics) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__OPERATION:
            setOperation((Operation) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__OUTPUT_SET:
            setOutputSet((OutputSet) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__PARALLEL_GATEWAY:
            setParallelGateway((ParallelGateway) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__PARTICIPANT:
            setParticipant((Participant) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__PARTICIPANT_ASSOCIATION:
            setParticipantAssociation((ParticipantAssociation) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__PARTICIPANT_MULTIPLICITY:
            setParticipantMultiplicity((ParticipantMultiplicity) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__PARTNER_ENTITY:
            setPartnerEntity((PartnerEntity) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__PARTNER_ROLE:
            setPartnerRole((PartnerRole) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__POTENTIAL_OWNER:
            setPotentialOwner((PotentialOwner) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__PROCESS:
            setProcess((org.eclipse.bpmn2.Process) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__PROPERTY:
            setProperty((Property) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__RECEIVE_TASK:
            setReceiveTask((ReceiveTask) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__RELATIONSHIP:
            setRelationship((Relationship) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__RENDERING:
            setRendering((Rendering) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__RESOURCE:
            setResource((Resource) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__RESOURCE_ASSIGNMENT_EXPRESSION:
            setResourceAssignmentExpression((ResourceAssignmentExpression) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__RESOURCE_PARAMETER:
            setResourceParameter((ResourceParameter) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__RESOURCE_PARAMETER_BINDING:
            setResourceParameterBinding((ResourceParameterBinding) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__SCRIPT:
            setScript((Object) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__SCRIPT_TASK:
            setScriptTask((ScriptTask) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__SEND_TASK:
            setSendTask((SendTask) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__SEQUENCE_FLOW:
            setSequenceFlow((SequenceFlow) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__SERVICE_TASK:
            setServiceTask((ServiceTask) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__SIGNAL:
            setSignal((Signal) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__SIGNAL_EVENT_DEFINITION:
            setSignalEventDefinition((SignalEventDefinition) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__STANDARD_LOOP_CHARACTERISTICS:
            setStandardLoopCharacteristics((StandardLoopCharacteristics) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__START_EVENT:
            setStartEvent((StartEvent) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__SUB_CHOREOGRAPHY:
            setSubChoreography((SubChoreography) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__SUB_CONVERSATION:
            setSubConversation((SubConversation) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__SUB_PROCESS:
            setSubProcess((SubProcess) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__TASK:
            setTask((Task) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__TERMINATE_EVENT_DEFINITION:
            setTerminateEventDefinition((TerminateEventDefinition) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__TEXT:
            setText((Object) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__TEXT_ANNOTATION:
            setTextAnnotation((TextAnnotation) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__THROW_EVENT:
            setThrowEvent((ThrowEvent) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__TIMER_EVENT_DEFINITION:
            setTimerEventDefinition((TimerEventDefinition) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__TRANSACTION:
            setTransaction((Transaction) newValue);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__USER_TASK:
            setUserTask((UserTask) newValue);
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
        case Bpmn2Package.DOCUMENT_ROOT__MIXED:
            getMixed().clear();
            return;
        case Bpmn2Package.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
            getXMLNSPrefixMap().clear();
            return;
        case Bpmn2Package.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
            getXSISchemaLocation().clear();
            return;
        case Bpmn2Package.DOCUMENT_ROOT__ACTIVITY:
            setActivity((Activity) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__AD_HOC_SUB_PROCESS:
            setAdHocSubProcess((AdHocSubProcess) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__FLOW_ELEMENT:
            setFlowElement((FlowElement) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__ARTIFACT:
            setArtifact((Artifact) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__ASSIGNMENT:
            setAssignment((Assignment) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__ASSOCIATION:
            setAssociation((Association) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__AUDITING:
            setAuditing((Auditing) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__BASE_ELEMENT:
            setBaseElement((BaseElement) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT:
            setBaseElementWithMixedContent((BaseElement) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__BOUNDARY_EVENT:
            setBoundaryEvent((BoundaryEvent) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__BUSINESS_RULE_TASK:
            setBusinessRuleTask((BusinessRuleTask) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CALLABLE_ELEMENT:
            setCallableElement((CallableElement) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CALL_ACTIVITY:
            setCallActivity((CallActivity) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CALL_CHOREOGRAPHY:
            setCallChoreography((CallChoreography) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CALL_CONVERSATION:
            setCallConversation((CallConversation) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CONVERSATION_NODE:
            setConversationNode((ConversationNode) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CANCEL_EVENT_DEFINITION:
            setCancelEventDefinition((CancelEventDefinition) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__EVENT_DEFINITION:
            setEventDefinition((EventDefinition) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__ROOT_ELEMENT:
            setRootElement((RootElement) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CATCH_EVENT:
            setCatchEvent((CatchEvent) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CATEGORY:
            setCategory((Category) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CATEGORY_VALUE:
            setCategoryValue((CategoryValue) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CHOREOGRAPHY:
            setChoreography((Choreography) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__COLLABORATION:
            setCollaboration((Collaboration) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CHOREOGRAPHY_ACTIVITY:
            setChoreographyActivity((ChoreographyActivity) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CHOREOGRAPHY_TASK:
            setChoreographyTask((ChoreographyTask) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__COMPENSATE_EVENT_DEFINITION:
            setCompensateEventDefinition((CompensateEventDefinition) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__COMPLEX_BEHAVIOR_DEFINITION:
            setComplexBehaviorDefinition((ComplexBehaviorDefinition) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__COMPLEX_GATEWAY:
            setComplexGateway((ComplexGateway) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CONDITIONAL_EVENT_DEFINITION:
            setConditionalEventDefinition((ConditionalEventDefinition) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CONVERSATION:
            setConversation((Conversation) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CONVERSATION_ASSOCIATION:
            setConversationAssociation((ConversationAssociation) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CONVERSATION_LINK:
            setConversationLink((ConversationLink) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_KEY:
            setCorrelationKey((CorrelationKey) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_PROPERTY:
            setCorrelationProperty((CorrelationProperty) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_PROPERTY_BINDING:
            setCorrelationPropertyBinding((CorrelationPropertyBinding) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_PROPERTY_RETRIEVAL_EXPRESSION:
            setCorrelationPropertyRetrievalExpression((CorrelationPropertyRetrievalExpression) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_SUBSCRIPTION:
            setCorrelationSubscription((CorrelationSubscription) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_ASSOCIATION:
            setDataAssociation((DataAssociation) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_INPUT:
            setDataInput((DataInput) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_INPUT_ASSOCIATION:
            setDataInputAssociation((DataInputAssociation) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_OBJECT:
            setDataObject((DataObject) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_OBJECT_REFERENCE:
            setDataObjectReference((DataObjectReference) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_OUTPUT:
            setDataOutput((DataOutput) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_OUTPUT_ASSOCIATION:
            setDataOutputAssociation((DataOutputAssociation) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_STATE:
            setDataState((DataState) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_STORE:
            setDataStore((DataStore) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_STORE_REFERENCE:
            setDataStoreReference((DataStoreReference) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__DEFINITIONS:
            setDefinitions((Definitions) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__DOCUMENTATION:
            setDocumentation((Documentation) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__END_EVENT:
            setEndEvent((EndEvent) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__END_POINT:
            setEndPoint((EndPoint) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__ERROR:
            setError((org.eclipse.bpmn2.Error) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__ERROR_EVENT_DEFINITION:
            setErrorEventDefinition((ErrorEventDefinition) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__ESCALATION:
            setEscalation((Escalation) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__ESCALATION_EVENT_DEFINITION:
            setEscalationEventDefinition((EscalationEventDefinition) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__EVENT:
            setEvent((Event) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__EVENT_BASED_GATEWAY:
            setEventBasedGateway((EventBasedGateway) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__EXCLUSIVE_GATEWAY:
            setExclusiveGateway((ExclusiveGateway) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__EXPRESSION:
            setExpression((Expression) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__EXTENSION:
            setExtension((Extension) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__EXTENSION_ELEMENTS:
            setExtensionElements((ExtensionAttributeValue) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__FLOW_NODE:
            setFlowNode((FlowNode) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__FORMAL_EXPRESSION:
            setFormalExpression((FormalExpression) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_BUSINESS_RULE_TASK:
            setGlobalBusinessRuleTask((GlobalBusinessRuleTask) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_CHOREOGRAPHY_TASK:
            setGlobalChoreographyTask((GlobalChoreographyTask) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_CONVERSATION:
            setGlobalConversation((GlobalConversation) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_MANUAL_TASK:
            setGlobalManualTask((GlobalManualTask) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_SCRIPT_TASK:
            setGlobalScriptTask((GlobalScriptTask) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_TASK:
            setGlobalTask((GlobalTask) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_USER_TASK:
            setGlobalUserTask((GlobalUserTask) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__GROUP:
            setGroup((Group) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__HUMAN_PERFORMER:
            setHumanPerformer((HumanPerformer) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__PERFORMER:
            setPerformer((Performer) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__RESOURCE_ROLE:
            setResourceRole((ResourceRole) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__IMPLICIT_THROW_EVENT:
            setImplicitThrowEvent((ImplicitThrowEvent) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__IMPORT:
            setImport((Import) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__INCLUSIVE_GATEWAY:
            setInclusiveGateway((InclusiveGateway) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__INPUT_SET:
            setInputSet((InputSet) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__INTERFACE:
            setInterface((Interface) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__INTERMEDIATE_CATCH_EVENT:
            setIntermediateCatchEvent((IntermediateCatchEvent) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__INTERMEDIATE_THROW_EVENT:
            setIntermediateThrowEvent((IntermediateThrowEvent) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__IO_BINDING:
            setIoBinding((InputOutputBinding) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__IO_SPECIFICATION:
            setIoSpecification((InputOutputSpecification) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__ITEM_DEFINITION:
            setItemDefinition((ItemDefinition) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__LANE:
            setLane((Lane) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__LANE_SET:
            setLaneSet((LaneSet) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__LINK_EVENT_DEFINITION:
            setLinkEventDefinition((LinkEventDefinition) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__LOOP_CHARACTERISTICS:
            setLoopCharacteristics((LoopCharacteristics) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__MANUAL_TASK:
            setManualTask((ManualTask) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__MESSAGE:
            setMessage((Message) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__MESSAGE_EVENT_DEFINITION:
            setMessageEventDefinition((MessageEventDefinition) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__MESSAGE_FLOW:
            setMessageFlow((MessageFlow) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__MESSAGE_FLOW_ASSOCIATION:
            setMessageFlowAssociation((MessageFlowAssociation) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__MONITORING:
            setMonitoring((Monitoring) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__MULTI_INSTANCE_LOOP_CHARACTERISTICS:
            setMultiInstanceLoopCharacteristics((MultiInstanceLoopCharacteristics) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__OPERATION:
            setOperation((Operation) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__OUTPUT_SET:
            setOutputSet((OutputSet) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__PARALLEL_GATEWAY:
            setParallelGateway((ParallelGateway) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__PARTICIPANT:
            setParticipant((Participant) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__PARTICIPANT_ASSOCIATION:
            setParticipantAssociation((ParticipantAssociation) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__PARTICIPANT_MULTIPLICITY:
            setParticipantMultiplicity((ParticipantMultiplicity) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__PARTNER_ENTITY:
            setPartnerEntity((PartnerEntity) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__PARTNER_ROLE:
            setPartnerRole((PartnerRole) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__POTENTIAL_OWNER:
            setPotentialOwner((PotentialOwner) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__PROCESS:
            setProcess((org.eclipse.bpmn2.Process) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__PROPERTY:
            setProperty((Property) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__RECEIVE_TASK:
            setReceiveTask((ReceiveTask) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__RELATIONSHIP:
            setRelationship((Relationship) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__RENDERING:
            setRendering((Rendering) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__RESOURCE:
            setResource((Resource) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__RESOURCE_ASSIGNMENT_EXPRESSION:
            setResourceAssignmentExpression((ResourceAssignmentExpression) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__RESOURCE_PARAMETER:
            setResourceParameter((ResourceParameter) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__RESOURCE_PARAMETER_BINDING:
            setResourceParameterBinding((ResourceParameterBinding) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__SCRIPT:
            setScript((Object) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__SCRIPT_TASK:
            setScriptTask((ScriptTask) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__SEND_TASK:
            setSendTask((SendTask) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__SEQUENCE_FLOW:
            setSequenceFlow((SequenceFlow) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__SERVICE_TASK:
            setServiceTask((ServiceTask) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__SIGNAL:
            setSignal((Signal) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__SIGNAL_EVENT_DEFINITION:
            setSignalEventDefinition((SignalEventDefinition) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__STANDARD_LOOP_CHARACTERISTICS:
            setStandardLoopCharacteristics((StandardLoopCharacteristics) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__START_EVENT:
            setStartEvent((StartEvent) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__SUB_CHOREOGRAPHY:
            setSubChoreography((SubChoreography) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__SUB_CONVERSATION:
            setSubConversation((SubConversation) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__SUB_PROCESS:
            setSubProcess((SubProcess) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__TASK:
            setTask((Task) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__TERMINATE_EVENT_DEFINITION:
            setTerminateEventDefinition((TerminateEventDefinition) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__TEXT:
            setText((Object) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__TEXT_ANNOTATION:
            setTextAnnotation((TextAnnotation) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__THROW_EVENT:
            setThrowEvent((ThrowEvent) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__TIMER_EVENT_DEFINITION:
            setTimerEventDefinition((TimerEventDefinition) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__TRANSACTION:
            setTransaction((Transaction) null);
            return;
        case Bpmn2Package.DOCUMENT_ROOT__USER_TASK:
            setUserTask((UserTask) null);
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
        case Bpmn2Package.DOCUMENT_ROOT__MIXED:
            return mixed != null && !mixed.isEmpty();
        case Bpmn2Package.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
            return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
        case Bpmn2Package.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
            return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
        case Bpmn2Package.DOCUMENT_ROOT__ACTIVITY:
            return getActivity() != null;
        case Bpmn2Package.DOCUMENT_ROOT__AD_HOC_SUB_PROCESS:
            return getAdHocSubProcess() != null;
        case Bpmn2Package.DOCUMENT_ROOT__FLOW_ELEMENT:
            return getFlowElement() != null;
        case Bpmn2Package.DOCUMENT_ROOT__ARTIFACT:
            return getArtifact() != null;
        case Bpmn2Package.DOCUMENT_ROOT__ASSIGNMENT:
            return getAssignment() != null;
        case Bpmn2Package.DOCUMENT_ROOT__ASSOCIATION:
            return getAssociation() != null;
        case Bpmn2Package.DOCUMENT_ROOT__AUDITING:
            return getAuditing() != null;
        case Bpmn2Package.DOCUMENT_ROOT__BASE_ELEMENT:
            return getBaseElement() != null;
        case Bpmn2Package.DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT:
            return getBaseElementWithMixedContent() != null;
        case Bpmn2Package.DOCUMENT_ROOT__BOUNDARY_EVENT:
            return getBoundaryEvent() != null;
        case Bpmn2Package.DOCUMENT_ROOT__BUSINESS_RULE_TASK:
            return getBusinessRuleTask() != null;
        case Bpmn2Package.DOCUMENT_ROOT__CALLABLE_ELEMENT:
            return getCallableElement() != null;
        case Bpmn2Package.DOCUMENT_ROOT__CALL_ACTIVITY:
            return getCallActivity() != null;
        case Bpmn2Package.DOCUMENT_ROOT__CALL_CHOREOGRAPHY:
            return getCallChoreography() != null;
        case Bpmn2Package.DOCUMENT_ROOT__CALL_CONVERSATION:
            return getCallConversation() != null;
        case Bpmn2Package.DOCUMENT_ROOT__CONVERSATION_NODE:
            return getConversationNode() != null;
        case Bpmn2Package.DOCUMENT_ROOT__CANCEL_EVENT_DEFINITION:
            return getCancelEventDefinition() != null;
        case Bpmn2Package.DOCUMENT_ROOT__EVENT_DEFINITION:
            return getEventDefinition() != null;
        case Bpmn2Package.DOCUMENT_ROOT__ROOT_ELEMENT:
            return getRootElement() != null;
        case Bpmn2Package.DOCUMENT_ROOT__CATCH_EVENT:
            return getCatchEvent() != null;
        case Bpmn2Package.DOCUMENT_ROOT__CATEGORY:
            return getCategory() != null;
        case Bpmn2Package.DOCUMENT_ROOT__CATEGORY_VALUE:
            return getCategoryValue() != null;
        case Bpmn2Package.DOCUMENT_ROOT__CHOREOGRAPHY:
            return getChoreography() != null;
        case Bpmn2Package.DOCUMENT_ROOT__COLLABORATION:
            return getCollaboration() != null;
        case Bpmn2Package.DOCUMENT_ROOT__CHOREOGRAPHY_ACTIVITY:
            return getChoreographyActivity() != null;
        case Bpmn2Package.DOCUMENT_ROOT__CHOREOGRAPHY_TASK:
            return getChoreographyTask() != null;
        case Bpmn2Package.DOCUMENT_ROOT__COMPENSATE_EVENT_DEFINITION:
            return getCompensateEventDefinition() != null;
        case Bpmn2Package.DOCUMENT_ROOT__COMPLEX_BEHAVIOR_DEFINITION:
            return getComplexBehaviorDefinition() != null;
        case Bpmn2Package.DOCUMENT_ROOT__COMPLEX_GATEWAY:
            return getComplexGateway() != null;
        case Bpmn2Package.DOCUMENT_ROOT__CONDITIONAL_EVENT_DEFINITION:
            return getConditionalEventDefinition() != null;
        case Bpmn2Package.DOCUMENT_ROOT__CONVERSATION:
            return getConversation() != null;
        case Bpmn2Package.DOCUMENT_ROOT__CONVERSATION_ASSOCIATION:
            return getConversationAssociation() != null;
        case Bpmn2Package.DOCUMENT_ROOT__CONVERSATION_LINK:
            return getConversationLink() != null;
        case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_KEY:
            return getCorrelationKey() != null;
        case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_PROPERTY:
            return getCorrelationProperty() != null;
        case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_PROPERTY_BINDING:
            return getCorrelationPropertyBinding() != null;
        case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_PROPERTY_RETRIEVAL_EXPRESSION:
            return getCorrelationPropertyRetrievalExpression() != null;
        case Bpmn2Package.DOCUMENT_ROOT__CORRELATION_SUBSCRIPTION:
            return getCorrelationSubscription() != null;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_ASSOCIATION:
            return getDataAssociation() != null;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_INPUT:
            return getDataInput() != null;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_INPUT_ASSOCIATION:
            return getDataInputAssociation() != null;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_OBJECT:
            return getDataObject() != null;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_OBJECT_REFERENCE:
            return getDataObjectReference() != null;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_OUTPUT:
            return getDataOutput() != null;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_OUTPUT_ASSOCIATION:
            return getDataOutputAssociation() != null;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_STATE:
            return getDataState() != null;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_STORE:
            return getDataStore() != null;
        case Bpmn2Package.DOCUMENT_ROOT__DATA_STORE_REFERENCE:
            return getDataStoreReference() != null;
        case Bpmn2Package.DOCUMENT_ROOT__DEFINITIONS:
            return getDefinitions() != null;
        case Bpmn2Package.DOCUMENT_ROOT__DOCUMENTATION:
            return getDocumentation() != null;
        case Bpmn2Package.DOCUMENT_ROOT__END_EVENT:
            return getEndEvent() != null;
        case Bpmn2Package.DOCUMENT_ROOT__END_POINT:
            return getEndPoint() != null;
        case Bpmn2Package.DOCUMENT_ROOT__ERROR:
            return getError() != null;
        case Bpmn2Package.DOCUMENT_ROOT__ERROR_EVENT_DEFINITION:
            return getErrorEventDefinition() != null;
        case Bpmn2Package.DOCUMENT_ROOT__ESCALATION:
            return getEscalation() != null;
        case Bpmn2Package.DOCUMENT_ROOT__ESCALATION_EVENT_DEFINITION:
            return getEscalationEventDefinition() != null;
        case Bpmn2Package.DOCUMENT_ROOT__EVENT:
            return getEvent() != null;
        case Bpmn2Package.DOCUMENT_ROOT__EVENT_BASED_GATEWAY:
            return getEventBasedGateway() != null;
        case Bpmn2Package.DOCUMENT_ROOT__EXCLUSIVE_GATEWAY:
            return getExclusiveGateway() != null;
        case Bpmn2Package.DOCUMENT_ROOT__EXPRESSION:
            return getExpression() != null;
        case Bpmn2Package.DOCUMENT_ROOT__EXTENSION:
            return getExtension() != null;
        case Bpmn2Package.DOCUMENT_ROOT__EXTENSION_ELEMENTS:
            return getExtensionElements() != null;
        case Bpmn2Package.DOCUMENT_ROOT__FLOW_NODE:
            return getFlowNode() != null;
        case Bpmn2Package.DOCUMENT_ROOT__FORMAL_EXPRESSION:
            return getFormalExpression() != null;
        case Bpmn2Package.DOCUMENT_ROOT__GATEWAY:
            return getGateway() != null;
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_BUSINESS_RULE_TASK:
            return getGlobalBusinessRuleTask() != null;
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_CHOREOGRAPHY_TASK:
            return getGlobalChoreographyTask() != null;
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_CONVERSATION:
            return getGlobalConversation() != null;
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_MANUAL_TASK:
            return getGlobalManualTask() != null;
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_SCRIPT_TASK:
            return getGlobalScriptTask() != null;
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_TASK:
            return getGlobalTask() != null;
        case Bpmn2Package.DOCUMENT_ROOT__GLOBAL_USER_TASK:
            return getGlobalUserTask() != null;
        case Bpmn2Package.DOCUMENT_ROOT__GROUP:
            return getGroup() != null;
        case Bpmn2Package.DOCUMENT_ROOT__HUMAN_PERFORMER:
            return getHumanPerformer() != null;
        case Bpmn2Package.DOCUMENT_ROOT__PERFORMER:
            return getPerformer() != null;
        case Bpmn2Package.DOCUMENT_ROOT__RESOURCE_ROLE:
            return getResourceRole() != null;
        case Bpmn2Package.DOCUMENT_ROOT__IMPLICIT_THROW_EVENT:
            return getImplicitThrowEvent() != null;
        case Bpmn2Package.DOCUMENT_ROOT__IMPORT:
            return getImport() != null;
        case Bpmn2Package.DOCUMENT_ROOT__INCLUSIVE_GATEWAY:
            return getInclusiveGateway() != null;
        case Bpmn2Package.DOCUMENT_ROOT__INPUT_SET:
            return getInputSet() != null;
        case Bpmn2Package.DOCUMENT_ROOT__INTERFACE:
            return getInterface() != null;
        case Bpmn2Package.DOCUMENT_ROOT__INTERMEDIATE_CATCH_EVENT:
            return getIntermediateCatchEvent() != null;
        case Bpmn2Package.DOCUMENT_ROOT__INTERMEDIATE_THROW_EVENT:
            return getIntermediateThrowEvent() != null;
        case Bpmn2Package.DOCUMENT_ROOT__IO_BINDING:
            return getIoBinding() != null;
        case Bpmn2Package.DOCUMENT_ROOT__IO_SPECIFICATION:
            return getIoSpecification() != null;
        case Bpmn2Package.DOCUMENT_ROOT__ITEM_DEFINITION:
            return getItemDefinition() != null;
        case Bpmn2Package.DOCUMENT_ROOT__LANE:
            return getLane() != null;
        case Bpmn2Package.DOCUMENT_ROOT__LANE_SET:
            return getLaneSet() != null;
        case Bpmn2Package.DOCUMENT_ROOT__LINK_EVENT_DEFINITION:
            return getLinkEventDefinition() != null;
        case Bpmn2Package.DOCUMENT_ROOT__LOOP_CHARACTERISTICS:
            return getLoopCharacteristics() != null;
        case Bpmn2Package.DOCUMENT_ROOT__MANUAL_TASK:
            return getManualTask() != null;
        case Bpmn2Package.DOCUMENT_ROOT__MESSAGE:
            return getMessage() != null;
        case Bpmn2Package.DOCUMENT_ROOT__MESSAGE_EVENT_DEFINITION:
            return getMessageEventDefinition() != null;
        case Bpmn2Package.DOCUMENT_ROOT__MESSAGE_FLOW:
            return getMessageFlow() != null;
        case Bpmn2Package.DOCUMENT_ROOT__MESSAGE_FLOW_ASSOCIATION:
            return getMessageFlowAssociation() != null;
        case Bpmn2Package.DOCUMENT_ROOT__MONITORING:
            return getMonitoring() != null;
        case Bpmn2Package.DOCUMENT_ROOT__MULTI_INSTANCE_LOOP_CHARACTERISTICS:
            return getMultiInstanceLoopCharacteristics() != null;
        case Bpmn2Package.DOCUMENT_ROOT__OPERATION:
            return getOperation() != null;
        case Bpmn2Package.DOCUMENT_ROOT__OUTPUT_SET:
            return getOutputSet() != null;
        case Bpmn2Package.DOCUMENT_ROOT__PARALLEL_GATEWAY:
            return getParallelGateway() != null;
        case Bpmn2Package.DOCUMENT_ROOT__PARTICIPANT:
            return getParticipant() != null;
        case Bpmn2Package.DOCUMENT_ROOT__PARTICIPANT_ASSOCIATION:
            return getParticipantAssociation() != null;
        case Bpmn2Package.DOCUMENT_ROOT__PARTICIPANT_MULTIPLICITY:
            return getParticipantMultiplicity() != null;
        case Bpmn2Package.DOCUMENT_ROOT__PARTNER_ENTITY:
            return getPartnerEntity() != null;
        case Bpmn2Package.DOCUMENT_ROOT__PARTNER_ROLE:
            return getPartnerRole() != null;
        case Bpmn2Package.DOCUMENT_ROOT__POTENTIAL_OWNER:
            return getPotentialOwner() != null;
        case Bpmn2Package.DOCUMENT_ROOT__PROCESS:
            return getProcess() != null;
        case Bpmn2Package.DOCUMENT_ROOT__PROPERTY:
            return getProperty() != null;
        case Bpmn2Package.DOCUMENT_ROOT__RECEIVE_TASK:
            return getReceiveTask() != null;
        case Bpmn2Package.DOCUMENT_ROOT__RELATIONSHIP:
            return getRelationship() != null;
        case Bpmn2Package.DOCUMENT_ROOT__RENDERING:
            return getRendering() != null;
        case Bpmn2Package.DOCUMENT_ROOT__RESOURCE:
            return getResource() != null;
        case Bpmn2Package.DOCUMENT_ROOT__RESOURCE_ASSIGNMENT_EXPRESSION:
            return getResourceAssignmentExpression() != null;
        case Bpmn2Package.DOCUMENT_ROOT__RESOURCE_PARAMETER:
            return getResourceParameter() != null;
        case Bpmn2Package.DOCUMENT_ROOT__RESOURCE_PARAMETER_BINDING:
            return getResourceParameterBinding() != null;
        case Bpmn2Package.DOCUMENT_ROOT__SCRIPT:
            return getScript() != null;
        case Bpmn2Package.DOCUMENT_ROOT__SCRIPT_TASK:
            return getScriptTask() != null;
        case Bpmn2Package.DOCUMENT_ROOT__SEND_TASK:
            return getSendTask() != null;
        case Bpmn2Package.DOCUMENT_ROOT__SEQUENCE_FLOW:
            return getSequenceFlow() != null;
        case Bpmn2Package.DOCUMENT_ROOT__SERVICE_TASK:
            return getServiceTask() != null;
        case Bpmn2Package.DOCUMENT_ROOT__SIGNAL:
            return getSignal() != null;
        case Bpmn2Package.DOCUMENT_ROOT__SIGNAL_EVENT_DEFINITION:
            return getSignalEventDefinition() != null;
        case Bpmn2Package.DOCUMENT_ROOT__STANDARD_LOOP_CHARACTERISTICS:
            return getStandardLoopCharacteristics() != null;
        case Bpmn2Package.DOCUMENT_ROOT__START_EVENT:
            return getStartEvent() != null;
        case Bpmn2Package.DOCUMENT_ROOT__SUB_CHOREOGRAPHY:
            return getSubChoreography() != null;
        case Bpmn2Package.DOCUMENT_ROOT__SUB_CONVERSATION:
            return getSubConversation() != null;
        case Bpmn2Package.DOCUMENT_ROOT__SUB_PROCESS:
            return getSubProcess() != null;
        case Bpmn2Package.DOCUMENT_ROOT__TASK:
            return getTask() != null;
        case Bpmn2Package.DOCUMENT_ROOT__TERMINATE_EVENT_DEFINITION:
            return getTerminateEventDefinition() != null;
        case Bpmn2Package.DOCUMENT_ROOT__TEXT:
            return getText() != null;
        case Bpmn2Package.DOCUMENT_ROOT__TEXT_ANNOTATION:
            return getTextAnnotation() != null;
        case Bpmn2Package.DOCUMENT_ROOT__THROW_EVENT:
            return getThrowEvent() != null;
        case Bpmn2Package.DOCUMENT_ROOT__TIMER_EVENT_DEFINITION:
            return getTimerEventDefinition() != null;
        case Bpmn2Package.DOCUMENT_ROOT__TRANSACTION:
            return getTransaction() != null;
        case Bpmn2Package.DOCUMENT_ROOT__USER_TASK:
            return getUserTask() != null;
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
        result.append(" (mixed: ");
        result.append(mixed);
        result.append(')');
        return result.toString();
    }

} //DocumentRootImpl
