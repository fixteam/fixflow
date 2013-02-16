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
package org.eclipse.bpmn2.modeler.ui.features.participant;

import java.io.IOException;
import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.modeler.core.ModelHandler;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.ui.features.AbstractDefaultDeleteFeature;
import org.eclipse.bpmn2.modeler.ui.features.choreography.ChoreographyUtil;
import org.eclipse.dd.di.DiagramElement;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

public class DeleteParticipantFeature extends AbstractDefaultDeleteFeature {

	public DeleteParticipantFeature(IFeatureProvider fp) {
		
		super(fp);
	}
	
	@Override
	public void delete(IDeleteContext context) {
		//super.delete(context.getPictogramElement().);
		Participant participant=BusinessObjectUtil.getFirstElementOfType(context.getPictogramElement(), Participant.class);
		
		Process process=participant.getProcessRef();
		//super.deletePeEnvironment(pictogramElement);
		super.delete(context);
		
		try {
			Definitions definitions = ModelHandler.getInstance(getDiagram()).getDefinitions();
			definitions.getRootElements().remove(process);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean canDelete(IDeleteContext context) {
		// Participant bands in a ChoreographyTask only be "deleted" (from the model)
		// if there are no other references to the participant; but they can be "removed"
		// (from the ChoreographyTask's participantRef list) at any time.
		// @see RemoveChoreographyParticipantFeature
		PictogramElement pe = context.getPictogramElement();
		if (ChoreographyUtil.isChoreographyParticipantBand(pe)) {
			int referenceCount = 0;
			try {
				Participant participant = BusinessObjectUtil.getFirstElementOfType(pe, Participant.class);
				Definitions definitions = ModelHandler.getInstance(getDiagram()).getDefinitions();
				TreeIterator<EObject> iter = definitions.eAllContents();
				while (iter.hasNext()) {
					EObject o = iter.next();
					for (EReference reference : o.eClass().getEAllReferences()) {
						if (!reference.isContainment() && !(o instanceof DiagramElement)) {
							if (reference.isMany()) {
								List list = (List)o.eGet(reference);
								for (Object referencedObject : list) {
									if (referencedObject==participant)
										++referenceCount;
								}
							}
							else {
								Object referencedObject = o.eGet(reference);
								if (referencedObject == participant)
									++referenceCount;
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return referenceCount <= 1;
		}
		return true;
	}

}