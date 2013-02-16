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
package org.eclipse.bpmn2.modeler.core.features.activity;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.modeler.core.features.MoveFlowNodeFeature;
import org.eclipse.bpmn2.modeler.core.features.event.AbstractBoundaryEventOperation;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IMoveShapeFeature;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.impl.MoveShapeContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IPeLayoutService;

public class MoveActivityFeature extends MoveFlowNodeFeature {

	public static final String ACTIVITY_MOVE_PROPERTY = "activity.move";

	public MoveActivityFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	protected void preMoveShape(IMoveShapeContext context) {
		MoveShapeContext msc = (MoveShapeContext)context;
		ContainerShape oldContainer = context.getSourceContainer();
		ContainerShape newContainer = context.getTargetContainer();
		IPeLayoutService peLayoutService = Graphiti.getPeLayoutService();
//		Shape shape = context.getShape();
//		ILocation loc = peService.getLocationRelativeToDiagram(shape);
		ILocation oldLoc = peLayoutService.getLocationRelativeToDiagram(oldContainer);
		ILocation newLoc = peLayoutService.getLocationRelativeToDiagram(newContainer);
//		System.out.println(
//				(oldContainer==newContainer ? "inside:\n" : "outside:\n")+
//				"oldContainer:\n" +
//				"  x="+oldLoc.getX()+"\n"+
//					"  y="+oldLoc.getY()+"\n"+
//				"newContainer:\n" +
//				"  x="+newLoc.getX()+"\n"+
//					"  y="+newLoc.getY()+"\n"+
//				"shape:\n" +
//				"  rel x="+shape.getGraphicsAlgorithm().getX()+"\n"+
//					"  rel y="+shape.getGraphicsAlgorithm().getY()+"\n"+
//				"  abs x="+loc.getX()+"\n"+
//					"  abs y="+loc.getY()+"\n"+
//				"context:\n" +
//				"  x="+msc.getX()+"\n"+
//					"  y="+msc.getY()+"\n"+
//				"  deltaX="+msc.getDeltaX()+"\n"+
//					"  deltaY="+msc.getDeltaY()+"\n"+
//				"\n"
//		);
		
		if (oldContainer!=newContainer) {
			int x = newLoc.getX() + msc.getX() - oldLoc.getX();
			int y = newLoc.getY() + msc.getY() - oldLoc.getY();
			int deltaX = newLoc.getX() + msc.getDeltaX() - oldLoc.getX();
			int deltaY = newLoc.getY() + msc.getDeltaY() - oldLoc.getY();
			
//			System.out.println(
//					"new context:\n"+
//					"  x="+( newLoc.getX() + msc.getX() - oldLoc.getX() )+"\n"+
//								"  y="+msc.getY()+"\n"+
//					"  deltaX="+( newLoc.getX() + msc.getDeltaX() - oldLoc.getX() )+"\n"+
//								"  deltaY="+msc.getDeltaY()+"\n"+
//					"\n"
//			);
			
//			msc.setX(x);
//			msc.setY(y);
//			msc.setDeltaX(deltaX);
//			msc.setDeltaY(deltaY);
//			msc.setTargetContainer(oldContainer);
		}

		super.preMoveShape(context);
	}

	@Override
	protected void postMoveShape(final IMoveShapeContext context) {
		super.postMoveShape(context);
		PictogramElement containerShape = context.getPictogramElement();
		Activity activity = BusinessObjectUtil.getFirstElementOfType(containerShape, Activity.class);
		new AbstractBoundaryEventOperation() {
			@Override
			protected void doWorkInternal(ContainerShape container) {
				GraphicsAlgorithm ga = container.getGraphicsAlgorithm();

				MoveShapeContext newContext = new MoveShapeContext(container);
				newContext.setDeltaX(context.getDeltaX());
				newContext.setDeltaY(context.getDeltaY());
				newContext.setSourceContainer(context.getSourceContainer());
				newContext.setTargetContainer(context.getTargetContainer());
				newContext.setTargetConnection(context.getTargetConnection());
				newContext.setLocation(ga.getX(), ga.getY());
				newContext.putProperty(ACTIVITY_MOVE_PROPERTY, true);

				IMoveShapeFeature moveFeature = getFeatureProvider().getMoveShapeFeature(newContext);
				Graphiti.getPeService().sendToFront(context.getShape());
				if (moveFeature.canMoveShape(newContext)) {
					moveFeature.moveShape(newContext);
				}
				
			}
		}.doWork(activity, getDiagram());
		
		if (containerShape.eContainer() instanceof ContainerShape) {
			PictogramElement pe = (PictogramElement) containerShape.eContainer();
			if (BusinessObjectUtil.containsElementOfType(pe, SubProcess.class)) {
				layoutPictogramElement(pe);
			}
		}
	}
}