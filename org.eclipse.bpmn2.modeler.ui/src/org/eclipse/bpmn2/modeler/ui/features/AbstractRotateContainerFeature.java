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

package org.eclipse.bpmn2.modeler.ui.features;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.ChoreographyTask;
import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.modeler.core.di.DIUtils;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.FeatureSupport;
import org.eclipse.bpmn2.modeler.ui.ImageProvider;
import org.eclipse.bpmn2.modeler.ui.features.choreography.ChoreographyUtil;
import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
/**
 * @author Bob Brodt
 *
 */
public abstract class AbstractRotateContainerFeature extends AbstractCustomFeature {

	/**
	 * @param fp
	 */
	public AbstractRotateContainerFeature(IFeatureProvider fp) {
		super(fp);
	}


	@Override
	public boolean isAvailable(IContext context) {
		return true;
	}

	@Override
	public String getImageId() {
		return ImageProvider.IMG_16_ROTATE;
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length == 1) {
			PictogramElement pe = pes[0];
			Object bo = getBusinessObjectForPictogramElement(pe);
			if (pe instanceof ContainerShape && (bo instanceof Participant || bo instanceof Lane)) {
				Object parent = getBusinessObjectForPictogramElement(((ContainerShape)pe).getContainer());
				if (parent instanceof BPMNDiagram) {
					// only the top-level Lane or Pool can be rotated - all other
					// child Lanes must have the same orientation as their ancestor.
					return true;
				}
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.features.custom.ICustomFeature#execute(org.eclipse.graphiti.features.context.ICustomContext)
	 */
	@Override
	public void execute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length == 1 && pes[0] instanceof ContainerShape) {
			ContainerShape container = (ContainerShape)pes[0];
			boolean horz = FeatureSupport.isHorizontal(container);
			changeOrientation(container, !horz);
			FeatureSupport.redraw(container);
		}
	}

	private void changeOrientation(ContainerShape container, boolean horz) {

		// Recursively change the orientation of Lanes and "Pools" (Participants).
		// Note that this does not apply to Participant bands contained in a ChoreographTask.
		if (ChoreographyUtil.isChoreographyParticipantBand(container))
			return;
		
		IGaService gaService = Graphiti.getGaService();
		GraphicsAlgorithm ga = container.getGraphicsAlgorithm();
		int x = ga.getX();
		int y = ga.getY();
		int width = ga.getWidth();
		int height = ga.getHeight();
		
		if (FeatureSupport.isParticipant(container) || FeatureSupport.isLane(container)) {
			gaService.setLocationAndSize(ga, x, y, height, width);
			FeatureSupport.setHorizontal(container, horz);
		}
		else {
			// Activities and other child figures only change location, not size
			// so simply swap x and y as a first cut.
			// TODO: replace this with auto layout algorithm, TBD later
			gaService.setLocationAndSize(ga, y, x, width, height);
			
			// TODO: also swap x and y of connection bendpoints: connection bendpoint
			// coordinates are relative to the diagram not the container figure
			// (i.e. the Lane in this case)
			ILocation loc = Graphiti.getLayoutService().getLocationRelativeToDiagram(container.getContainer());
			for (Anchor a : container.getAnchors()) {
				for (Connection c : a.getIncomingConnections()) {
					if (c instanceof FreeFormConnection) {
						FreeFormConnection fc = (FreeFormConnection)c;
						boolean update = false;
						for (Point p : fc.getBendpoints()) {
							int py = p.getX() - loc.getX() + loc.getY();
							int px = p.getY() - loc.getY() + loc.getX();
							p.setX(px);
							p.setY(py);
							update = true;
						}
						if (update)
							DIUtils.updateDIEdge(c);
					}
				}
			}

			layoutPictogramElement(container);
		}
		
		DIUtils.updateDIShape(container);

		for (PictogramElement pe : container.getChildren()) {
			if (pe instanceof ContainerShape) {
				changeOrientation((ContainerShape) pe, horz);
				if (BusinessObjectUtil.getBusinessObjectForPictogramElement(pe) instanceof ChoreographyTask)
					ChoreographyUtil.moveChoreographyMessageLinks((ContainerShape) pe);
			}
		}
	}

}
