/******************************************************************************* 
 * Copyright (c) 2011 Red Hat, Inc. 
 *  All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *
 * @author Innar Made
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.bpmn2.AdHocSubProcess;
import org.eclipse.bpmn2.Association;
import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.BusinessRuleTask;
import org.eclipse.bpmn2.CallActivity;
import org.eclipse.bpmn2.CallChoreography;
import org.eclipse.bpmn2.CancelEventDefinition;
import org.eclipse.bpmn2.ChoreographyTask;
import org.eclipse.bpmn2.CompensateEventDefinition;
import org.eclipse.bpmn2.ComplexGateway;
import org.eclipse.bpmn2.ConditionalEventDefinition;
import org.eclipse.bpmn2.Conversation;
import org.eclipse.bpmn2.DataAssociation;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataObject;
import org.eclipse.bpmn2.DataObjectReference;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.DataStoreReference;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.ErrorEventDefinition;
import org.eclipse.bpmn2.EscalationEventDefinition;
import org.eclipse.bpmn2.EventBasedGateway;
import org.eclipse.bpmn2.ExclusiveGateway;
import org.eclipse.bpmn2.Group;
import org.eclipse.bpmn2.InclusiveGateway;
import org.eclipse.bpmn2.IntermediateCatchEvent;
import org.eclipse.bpmn2.IntermediateThrowEvent;
import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.LinkEventDefinition;
import org.eclipse.bpmn2.ManualTask;
import org.eclipse.bpmn2.Message;
import org.eclipse.bpmn2.MessageEventDefinition;
import org.eclipse.bpmn2.MessageFlow;
import org.eclipse.bpmn2.ParallelGateway;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.ReceiveTask;
import org.eclipse.bpmn2.ScriptTask;
import org.eclipse.bpmn2.SendTask;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.ServiceTask;
import org.eclipse.bpmn2.SignalEventDefinition;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubChoreography;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.TerminateEventDefinition;
import org.eclipse.bpmn2.TextAnnotation;
import org.eclipse.bpmn2.TimerEventDefinition;
import org.eclipse.bpmn2.Transaction;
import org.eclipse.bpmn2.UserTask;

public class FeatureMap {
	public static final List<Class> CONNECTORS;
	public static final List<Class> EVENT_DEFINITIONS;
	public static final List<Class> EVENTS;
	public static final List<Class> GATEWAYS;
	public static final List<Class> TASKS;
	public static final List<Class> DATA;
	public static final List<Class> OTHER;
	static {
		ArrayList<Class> features = new ArrayList<Class>();
		features.add(SequenceFlow.class);
		features.add(MessageFlow.class);
		features.add(Association.class);
		features.add(DataAssociation.class);
		CONNECTORS = Collections.unmodifiableList(features);

		features = new ArrayList<Class>();
		features.add(ConditionalEventDefinition.class);
		features.add(TimerEventDefinition.class);
		features.add(SignalEventDefinition.class);
		features.add(MessageEventDefinition.class);
		features.add(EscalationEventDefinition.class);
		features.add(CompensateEventDefinition.class);
		features.add(LinkEventDefinition.class);
		features.add(ErrorEventDefinition.class);
		features.add(CancelEventDefinition.class);
		features.add(TerminateEventDefinition.class);
		EVENT_DEFINITIONS = Collections.unmodifiableList(features);

		features = new ArrayList<Class>();
		features.add(StartEvent.class);
		features.add(EndEvent.class);
		features.add(IntermediateThrowEvent.class);
		features.add(IntermediateCatchEvent.class);
		features.add(BoundaryEvent.class);
		EVENTS = Collections.unmodifiableList(features);

		features = new ArrayList<Class>();
		features.add(InclusiveGateway.class);
		features.add(ExclusiveGateway.class);
		features.add(ParallelGateway.class);
		features.add(EventBasedGateway.class);
		features.add(ComplexGateway.class);

		GATEWAYS = Collections.unmodifiableList(features);

		features = new ArrayList<Class>();
		features.add(Task.class);
		features.add(ManualTask.class);
		features.add(UserTask.class);
		features.add(ScriptTask.class);
		features.add(BusinessRuleTask.class);
		features.add(ServiceTask.class);
		features.add(SendTask.class);
		features.add(ReceiveTask.class);
		features.add(ChoreographyTask.class);
		TASKS = Collections.unmodifiableList(features);

		features = new ArrayList<Class>();
		features.add(DataObject.class);
//		features.add(DataObjectReference.class);
		features.add(DataStoreReference.class);
		features.add(DataInput.class);
		features.add(DataOutput.class);
		DATA = Collections.unmodifiableList(features);

		features = new ArrayList<Class>();
		features.add(Lane.class);
		features.add(Participant.class);
		features.add(TextAnnotation.class);
		features.add(SubProcess.class);
		features.add(Transaction.class);
		features.add(Group.class);
		features.add(AdHocSubProcess.class);
		features.add(CallActivity.class);
		features.add(Message.class);
		features.add(Conversation.class);
		features.add(SubChoreography.class);
		features.add(CallChoreography.class);
		OTHER = Collections.unmodifiableList(features);

	}
}
