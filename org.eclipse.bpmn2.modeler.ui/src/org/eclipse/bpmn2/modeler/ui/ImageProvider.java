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

import org.eclipse.bpmn2.AdHocSubProcess;
import org.eclipse.bpmn2.Association;
import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.BusinessRuleTask;
import org.eclipse.bpmn2.CallActivity;
import org.eclipse.bpmn2.CancelEventDefinition;
import org.eclipse.bpmn2.ChoreographyTask;
import org.eclipse.bpmn2.CompensateEventDefinition;
import org.eclipse.bpmn2.ComplexGateway;
import org.eclipse.bpmn2.ConditionalEventDefinition;
import org.eclipse.bpmn2.Conversation;
import org.eclipse.bpmn2.ConversationLink;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataObject;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.DataStore;
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
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.TerminateEventDefinition;
import org.eclipse.bpmn2.TextAnnotation;
import org.eclipse.bpmn2.TimerEventDefinition;
import org.eclipse.bpmn2.Transaction;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

public class ImageProvider extends AbstractImageProvider {

	private static final String dot16 = ".16";
	private static final String dot20 = ".20";
	private static final String ICONS_16 = "icons/16/";
	private static final String ICONS_20 = "icons/20/";

	public static final String PREFIX = ImageProvider.class.getPackage().getName() + ".";

	public static final String IMG_16_START_EVENT = PREFIX + StartEvent.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_END_EVENT = PREFIX + EndEvent.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_TASK = PREFIX + Task.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_EXCLUSIVE_GATEWAY = PREFIX + ExclusiveGateway.class.getSimpleName().toLowerCase()
	        + dot16;
	public static final String IMG_16_SEQUENCE_FLOW = PREFIX + SequenceFlow.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_PARTICIPANT = PREFIX + Participant.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_LANE = PREFIX + Lane.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_TEXT_ANNOTATION = PREFIX + TextAnnotation.class.getSimpleName().toLowerCase()
	        + dot16;
	public static final String IMG_16_ASSOCIATION = PREFIX + Association.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_INCLUSIVE_GATEWAY = PREFIX + InclusiveGateway.class.getSimpleName().toLowerCase()
	        + dot16;
	public static final String IMG_16_PARALLEL_GATEWAY = PREFIX + ParallelGateway.class.getSimpleName().toLowerCase()
	        + dot16;
	public static final String IMG_16_EVENT_BASED_GATEWAY = PREFIX
	        + EventBasedGateway.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_MESSAGE_FLOW = PREFIX + MessageFlow.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_INTERMEDIATE_CATCH_EVENT = PREFIX
	        + IntermediateCatchEvent.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_INTERMEDIATE_THORW_EVENT = PREFIX
	        + IntermediateThrowEvent.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_COMPLEX_GATEWAY = PREFIX + ComplexGateway.class.getSimpleName().toLowerCase()
	        + dot16;
	public static final String IMG_16_USER_TASK = PREFIX + UserTask.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_MANUAL_TASK = PREFIX + ManualTask.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_SCRIPT_TASK = PREFIX + ScriptTask.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_BUSINESS_RULE_TASK = PREFIX
	        + BusinessRuleTask.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_SERVICE_TASK = PREFIX + ServiceTask.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_SEND_TASK = PREFIX + SendTask.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_RECEIVE_TASK = PREFIX + ReceiveTask.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_CONDITION = PREFIX
	        + ConditionalEventDefinition.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_TIMER = PREFIX + TimerEventDefinition.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_SIGNAL = PREFIX + SignalEventDefinition.class.getSimpleName().toLowerCase()
	        + dot16;
	public static final String IMG_16_MESSAGE = PREFIX + MessageEventDefinition.class.getSimpleName().toLowerCase()
	        + dot16;
	public static final String IMG_16_ESCAlATION = PREFIX
	        + EscalationEventDefinition.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_COMPENSATE = PREFIX
	        + CompensateEventDefinition.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_LINK = PREFIX + LinkEventDefinition.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_ERROR = PREFIX + ErrorEventDefinition.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_CANCEL = PREFIX + CancelEventDefinition.class.getSimpleName().toLowerCase()
	        + dot16;
	public static final String IMG_16_BOUNDARY_EVENT = PREFIX + BoundaryEvent.class.getSimpleName().toLowerCase()
	        + dot16;
	public static final String IMG_16_SUB_PROCESS = PREFIX + SubProcess.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_AD_HOC_SUB_PROCESS = PREFIX + AdHocSubProcess.class.getSimpleName().toLowerCase()
	        + dot16;
	public static final String IMG_16_TRANSACTION = PREFIX + Transaction.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_GROUP = PREFIX + Group.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_DATA_OBJECT = PREFIX + DataObject.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_TERMINATE = PREFIX + TerminateEventDefinition.class.getSimpleName().toLowerCase()
	        + dot16;
	public static final String IMG_16_DATA_STORE = PREFIX + DataStore.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_DATA_INPUT = PREFIX + DataInput.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_DATA_OUTPUT = PREFIX + DataOutput.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_CALL_ACTIVITY = PREFIX + CallActivity.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_CONVERSATION = PREFIX + Conversation.class.getSimpleName().toLowerCase() + dot16;
	public static final String IMG_16_CONVERSATION_LINK = PREFIX + ConversationLink.class.getSimpleName().toLowerCase()
	        + dot16;
	public static final String IMG_16_CHOREOGRAPHY_TASK = PREFIX + ChoreographyTask.class.getSimpleName().toLowerCase()
	        + dot16;

	public static final String IMG_20_CONDITION = PREFIX
	        + ConditionalEventDefinition.class.getSimpleName().toLowerCase() + dot20;
	public static final String IMG_20_TIMER = PREFIX + TimerEventDefinition.class.getSimpleName().toLowerCase() + dot20;
	public static final String IMG_20_MULTIPLE = PREFIX + "multipleeventdefinition" + dot20; // FIXME

	public static final String IMG_16_ACTION = "default_action";
	
	public static final String IMG_16_EXPAND = PREFIX + "expand" + dot16;
	public static final String IMG_16_COLLAPSE = PREFIX + "collapse" + dot16;
	public static final String IMG_16_CONFIGURE = PREFIX + "configure" + dot16;

	public static final String IMG_16_ADD_PARTICIPANT = PREFIX + "addparticipant" + dot16;
	public static final String IMG_16_ADD_MESSAGE = PREFIX + "addmessage" + dot16;
	public static final String IMG_16_ROTATE = PREFIX + "rotate" + dot16;

	@Override
	protected void addAvailableImages() {
		addImageFilePath(IMG_16_START_EVENT, ICONS_16 + "StartEvent.png");
		addImageFilePath(IMG_16_END_EVENT, ICONS_16 + "EndEvent.png");
		addImageFilePath(IMG_16_TASK, ICONS_16 + "Task.png");
		addImageFilePath(IMG_16_EXCLUSIVE_GATEWAY, ICONS_16 + "ExclusiveGateway.png");
		addImageFilePath(IMG_16_SEQUENCE_FLOW, ICONS_16 + "SequenceFlow.png");
		addImageFilePath(IMG_16_PARTICIPANT, ICONS_16 + "Participant.png");
		addImageFilePath(IMG_16_LANE, ICONS_16 + "Lane.png");
		addImageFilePath(IMG_16_TEXT_ANNOTATION, ICONS_16 + "TextAnnotation.png");
		addImageFilePath(IMG_16_ASSOCIATION, ICONS_16 + "Association.png");
		addImageFilePath(IMG_16_INCLUSIVE_GATEWAY, ICONS_16 + "InclusiveGateway.png");
		addImageFilePath(IMG_16_PARALLEL_GATEWAY, ICONS_16 + "ParallelGateway.png");
		addImageFilePath(IMG_16_EVENT_BASED_GATEWAY, ICONS_16 + "EventBasedGateway.png");
		addImageFilePath(IMG_16_MESSAGE_FLOW, ICONS_16 + "MessageFlow.png");
		addImageFilePath(IMG_16_INTERMEDIATE_CATCH_EVENT, ICONS_16 + "IntermediateThrowEvent.png");
		addImageFilePath(IMG_16_INTERMEDIATE_THORW_EVENT, ICONS_16 + "IntermediateThrowEvent.png");
		addImageFilePath(IMG_16_COMPLEX_GATEWAY, ICONS_16 + "ComplexGateway.png");
		addImageFilePath(IMG_16_USER_TASK, ICONS_16 + "UserTask.png");
		addImageFilePath(IMG_16_MANUAL_TASK, ICONS_16 + "ManualTask.png");
		addImageFilePath(IMG_16_SCRIPT_TASK, ICONS_16 + "ScriptTask.png");
		addImageFilePath(IMG_16_BUSINESS_RULE_TASK, ICONS_16 + "BusinessRuleTask.png");
		addImageFilePath(IMG_16_SERVICE_TASK, ICONS_16 + "ServiceTask.png");
		addImageFilePath(IMG_16_SEND_TASK, ICONS_16 + "SendTask.png");
		addImageFilePath(IMG_16_RECEIVE_TASK, ICONS_16 + "ReceiveTask.png");
		addImageFilePath(IMG_16_CONDITION, ICONS_16 + "Condition.png");
		addImageFilePath(IMG_16_TIMER, ICONS_16 + "Timer.png");
		addImageFilePath(IMG_16_SIGNAL, ICONS_16 + "Signal.png");
		addImageFilePath(IMG_16_MESSAGE, ICONS_16 + "Message.png");
		addImageFilePath(IMG_16_ESCAlATION, ICONS_16 + "Escalation.png");
		addImageFilePath(IMG_16_COMPENSATE, ICONS_16 + "Compensate.png");
		addImageFilePath(IMG_16_LINK, ICONS_16 + "Link.png");
		addImageFilePath(IMG_16_ERROR, ICONS_16 + "Error.png");
		addImageFilePath(IMG_16_CANCEL, ICONS_16 + "Cancel.png");
		addImageFilePath(IMG_16_BOUNDARY_EVENT, ICONS_16 + "BoundaryEvent.png");
		addImageFilePath(IMG_16_SUB_PROCESS, ICONS_16 + "SubProcess.png");
		addImageFilePath(IMG_16_AD_HOC_SUB_PROCESS, ICONS_16 + "AdHocSubProcess.png");
		addImageFilePath(IMG_16_TRANSACTION, ICONS_16 + "Transaction.png");
		addImageFilePath(IMG_16_GROUP, ICONS_16 + "Group.png");
		addImageFilePath(IMG_16_DATA_OBJECT, ICONS_16 + "DataObject.png");
		addImageFilePath(IMG_16_TERMINATE, ICONS_16 + "Terminate.png");
		addImageFilePath(IMG_16_DATA_STORE, ICONS_16 + "DataStore.png");
		addImageFilePath(IMG_16_DATA_INPUT, ICONS_16 + "DataInput.png");
		addImageFilePath(IMG_16_DATA_OUTPUT, ICONS_16 + "DataOutput.png");
		addImageFilePath(IMG_16_CALL_ACTIVITY, ICONS_16 + "CallActivity.png");
		addImageFilePath(IMG_16_CONVERSATION, ICONS_16 + "Conversation.png");
		addImageFilePath(IMG_16_CONVERSATION_LINK, ICONS_16 + "ConversationLink.png");
		addImageFilePath(IMG_16_CHOREOGRAPHY_TASK, ICONS_16 + "ChoreographyTask.png");

		addImageFilePath(IMG_20_CONDITION, ICONS_20 + "Condition.png");
		addImageFilePath(IMG_20_TIMER, ICONS_20 + "Timer.png");
		addImageFilePath(IMG_20_MULTIPLE, ICONS_20 + "Multiple.png");

		addImageFilePath(IMG_16_ACTION, ICONS_16 + "action.gif");
		addImageFilePath(IMG_16_EXPAND, ICONS_16 + "expand.png");
		addImageFilePath(IMG_16_COLLAPSE, ICONS_16 + "collapse.png");
		addImageFilePath(IMG_16_CONFIGURE, ICONS_16 + "configure.png");

		addImageFilePath(IMG_16_ADD_PARTICIPANT, ICONS_16 + "addparticipant.png");
		addImageFilePath(IMG_16_ADD_MESSAGE, ICONS_16 + "addmessage.png");
		addImageFilePath(IMG_16_ROTATE, ICONS_16 + "rotate.png");
	}

}
