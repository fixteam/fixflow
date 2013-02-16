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
package org.eclipse.bpmn2.modeler.core.features.participant;

import org.eclipse.bpmn2.Participant;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

public class DirectEditParticipantFeature extends AbstractDirectEditingFeature {

	public DirectEditParticipantFeature(IFeatureProvider fp) {
	    super(fp);
    }

	@Override
    public int getEditingType() {
		return TYPE_TEXT;
    }

	@Override
    public String getInitialValue(IDirectEditingContext context) {
		PictogramElement pe = context.getPictogramElement();
		Participant participant = (Participant) getBusinessObjectForPictogramElement(pe);
		return participant.getName();
    }

	@Override
    public void setValue(String value, IDirectEditingContext context) {
		PictogramElement pe = context.getPictogramElement();
		Participant participant = (Participant) getBusinessObjectForPictogramElement(pe);
		participant.setName(value);
		updatePictogramElement(((Shape) pe).getContainer());
    }
	
	@Override
	public String checkValueValid(String value, IDirectEditingContext context) {
		if (value.length() < 1) {
			return "Please enter any text as Pool name.";
		} else if (value.contains("\n")) {
			return "Line breakes are not allowed in Pool names.";
		}
		return null;
	}

	@Override
	public boolean canDirectEdit(IDirectEditingContext context) {
		PictogramElement pe = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pe);
		GraphicsAlgorithm ga = context.getGraphicsAlgorithm();
		return bo instanceof Participant && ga instanceof Text;
	}
}
