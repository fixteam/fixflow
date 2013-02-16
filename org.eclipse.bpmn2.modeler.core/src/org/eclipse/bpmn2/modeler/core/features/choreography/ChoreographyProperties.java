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
 * @author Ivar Meikas
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.core.features.choreography;

public interface ChoreographyProperties {

	final static int PARTICIPANT_BAND_HEIGHT = 20;
	final static int ENV_W = 30;
	final static int ENV_H = 18;
	final static int ENVELOPE_HEIGHT_MODIFIER = 30;
	final static int R = 10;
	final static int TEXT_H = 15;
	final static int MARKER_H = 20;

	final static String CHOREOGRAPHY_ACTIVITY_PROPERTY = "choreography.activity";
	final static String PARTICIPANT_REF = "choreography.activity.participant.ref";
	final static String PARTICIPANT_REF_ID = "choreography.activity.participant.ref.id";
	final static String PARTICIPANT_REF_IDS = "choreography.activity.participant.ref.ids";
	final static String INITIATING_PARTICIPANT_REF = "choreography.activity.initiating.participant.ref";
	final static String MESSAGE_VISIBLE = "choreography.activity.band.message.visible";
	final static String BAND = "choreography.activity.band";
	final static String MESSAGE_LINK = "choreography.messageLink";
	final static String MESSAGE_NAME = "choreography.messageName";
	final static String CHOREOGRAPHY_NAME = "choreography.name";
	final static String CALL_CHOREO_BORDER = "call.choreography.border";
	final static String CHOREOGRAPHY_MARKER = "choreography.marker";
	final static String CHOREOGRAPHY_MARKER_SHAPE = "choreography.marker.shape";
	final static String MESSAGE_REF_IDS = "choreography.message.ref.ids";
}