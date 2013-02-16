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
package org.eclipse.bpmn2.modeler.core.features.event;

import java.util.Collection;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IPeService;

public abstract class AbstractBoundaryEventOperation {

	public void doWork(Activity activity, Diagram diagram) {
		IPeService peService = Graphiti.getPeService();
		Collection<PictogramElement> elements = peService.getAllContainedPictogramElements(diagram);
		for (PictogramElement e : elements) {
			BoundaryEvent boundaryEvent = BusinessObjectUtil.getFirstElementOfType(e, BoundaryEvent.class);
			if (boundaryEvent != null && activity.getBoundaryEventRefs().contains(boundaryEvent)) {
				ContainerShape container = (ContainerShape) e;
				doWorkInternal(container);
			}
		}
	}

	protected abstract void doWorkInternal(ContainerShape container);
}