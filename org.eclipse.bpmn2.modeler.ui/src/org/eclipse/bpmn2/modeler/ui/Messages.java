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
 * @author Bob Brodt
 ******************************************************************************/

package org.eclipse.bpmn2.modeler.ui;

import org.eclipse.osgi.util.NLS;

/**
 * @author Bob Brodt
 *
 */
public class Messages extends NLS {


	private static String BUNDLE_NAME = "org.eclipse.bpmn2.modeler.ui.messages"; //$NON-NLS-1$
	
	public static String SchemaImportDialog_0;
	public static String SchemaImportDialog_1;
	public static String SchemaImportDialog_2;
	public static String SchemaImportDialog_3;
	public static String SchemaImportDialog_4;
	public static String SchemaImportDialog_5;
	public static String SchemaImportDialog_6;
	public static String SchemaImportDialog_7;
	public static String SchemaImportDialog_15;
	public static String SchemaImportDialog_8;
	public static String SchemaImportDialog_9;
	public static String SchemaImportDialog_10;
	public static String SchemaImportDialog_11;
	public static String SchemaImportDialog_12;
	public static String SchemaImportDialog_17;
	public static String SchemaImportDialog_13;
	public static String SchemaImportDialog_19;
	public static String SchemaImportDialog_18;
	public static String SchemaImportDialog_14;
	public static String SchemaImportDialog_16;

	public static String SchemaImportDialog_20;
	public static String SchemaImportDialog_21;
	public static String SchemaImportDialog_22;

	public static String XSDSchemaTreeNode_1;
	public static String XSDSchemaTreeNode_0;

	public static String ModelTreeLabelProvider_s1_s2;
	
	public static String InputMessageAdapter_0;
	public static String MessageAdapter_Message_1;
	public static String OperationAdapter_Operation_1;
	public static String OperationAdapter_0;
	public static String OutputMessageAdapter_0;
	public static String PartAdapter_Part_1;
	public static String PortTypeAdapter_Port_Type_2;
	public static String FaultAdapter_Fault_1;
	public static String XSDAttributeDeclarationAdapter_XSD_Attribute_1;
	public static String XSDComplexTypeDefinitionAdapter_0;
	public static String XSDElementDeclarationAdapter_XSD_Element_1;
	public static String XSDSchemaAdapter_0;
	public static String XSDSchemaAdapter_1;
	public static String XSDTypeDefinitionAdapter_XSD_Type_1;
	public static String XSDSimpleTypeDefinitionAdapter_0;

	public static String Bpmn2PreferencePage_HomePage_Description;
	public static String Bpmn2PreferencePage_EditorPage_Description;

	public static String UI_UnknownDiagram_long_description;
	public static String UI_ProcessDiagram_long_description;
	public static String UI_ChoreographyDiagram_long_description;
	public static String UI_CollaborationDiagram_long_description;
	
	public static String UI_SequenceFlow_long_description;
	public static String UI_MessageFlow_long_description;
	public static String UI_Association_long_description;
	public static String UI_Annotation_long_description;
	public static String UI_Task_long_description;
	public static String UI_ManualTask_long_description;
	public static String UI_UserTask_long_description;
	public static String UI_BusinessRuleTask_long_description;
	public static String UI_ServiceTask_long_description;
	public static String UI_SendTask_long_description;
	public static String UI_ReceiveTask_long_description;
	public static String UI_ChoreographyTask_long_description;
	public static String UI_ScriptTask_long_description;

	public static String UI_StartEvent_long_description;
	public static String UI_EndEvent_long_description;
	public static String UI_IntermediateThrowEvent_long_description;
	public static String UI_IntermediateCatchEvent_long_description;
	public static String UI_BoundaryEvent_long_description;
	public static String UI_DataStore_long_description;
	public static String UI_DataInput_long_description;
	public static String UI_DataOutput_long_description;
	public static String UI_Message_long_description;
	
	public static String UI_ExclusiveGateway_long_description;
	public static String UI_InclusiveGateway_long_description;
	public static String UI_ParallelGateway_long_description;
	public static String UI_EventBasedGateway_long_description;
	public static String UI_ComplexGateway_long_description;
	
	public static String UI_Participant_long_description;
	public static String UI_Lane_long_description;
	public static String UI_SubProcess_long_description;
	public static String UI_Group_long_description;
	public static String UI_SubChoreography_long_description;
	public static String UI_CallActivity_long_description;
	public static String UI_Conversation_long_description;
	public static String UI_CallConversation_long_description;
	public static String UI_SubConversation_long_description;
	
	
	
	public static String WSILPreferencePage_WSIL_1;
	public static String WSILPreferencePage_WSIL_2;
	public static String WSILPreferencePage_WSIL_Description;
	public static String WSILPreferencePage_WSIL_Add;
	public static String WSILPreferencePage_WSIL_EnterLocation;
	public static String WSILPreferencePage_WSIL_EnterDescription;
	public static String WSILPreferencePage_WSIL_Remove;
	public static String WSILPreferencePage_WSIL_MoveUp;
	public static String WSILPreferencePage_WSIL_MoveDown;
	public static String WSILPreferencePage_WSIL_OpenInBrowser;
	public static String WSILPreferencePage_WSIL_NameLimit;
	public static String WSILPreferencePage_WSIL_DocumentNotLoaded;
	public static String WSILPreferencePage_WSIL_Abstract;
	public static String WSILPreferencePage_WSIL_Location;
	public static String WSILPreferencePage_WSIL_Namespace;
	public static String WSILPreferencePage_WSIL_Index;

	private Messages() {
		// Do not instantiate
	}

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
