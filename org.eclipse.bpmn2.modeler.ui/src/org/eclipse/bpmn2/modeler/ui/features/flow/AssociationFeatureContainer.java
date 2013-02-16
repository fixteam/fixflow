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
package org.eclipse.bpmn2.modeler.ui.features.flow;

import org.eclipse.bpmn2.Association;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.modeler.core.ModelHandler;
import org.eclipse.bpmn2.modeler.core.features.BaseElementConnectionFeatureContainer;
import org.eclipse.bpmn2.modeler.core.features.flow.AbstractAddFlowFeature;
import org.eclipse.bpmn2.modeler.core.features.flow.AbstractCreateFlowFeature;
import org.eclipse.bpmn2.modeler.core.features.flow.AbstractReconnectFlowFeature;
import org.eclipse.bpmn2.modeler.core.utils.AnchorUtil;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.ui.ImageProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReconnectionFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.context.impl.CreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.ReconnectionContext;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;

public class AssociationFeatureContainer extends BaseElementConnectionFeatureContainer {

	protected CreateConnectionContext createContext;
	
	@Override
	public boolean canApplyTo(Object o) {
		return super.canApplyTo(o) && o instanceof Association;
	}

	@Override
	public IAddFeature getAddFeature(IFeatureProvider fp) {
		return new AbstractAddFlowFeature(fp) {

			@Override
			public PictogramElement add(IAddContext context) {
				AddConnectionContext ac = (AddConnectionContext)context;
				Anchor sourceAnchor = ac.getSourceAnchor();
				Anchor targetAnchor = ac.getTargetAnchor();
				PictogramElement source = sourceAnchor==null ? null : sourceAnchor.getParent();
				PictogramElement target = targetAnchor==null ? null : targetAnchor.getParent();
				boolean anchorChanged = false;
				
				if (createContext!=null) {
					if (source==null) {
						source = createContext.getSourcePictogramElement();
						sourceAnchor = createContext.getSourceAnchor();
					}
					if (target==null) {
						target = createContext.getTargetPictogramElement();
						targetAnchor = createContext.getTargetAnchor();
					}
				}
				
				if (sourceAnchor==null && source instanceof FreeFormConnection) {
					Shape connectionPointShape = AnchorUtil.createConnectionPoint(getFeatureProvider(),
							(FreeFormConnection)source,
							Graphiti.getPeLayoutService().getConnectionMidpoint((FreeFormConnection)source, 0.5));
					sourceAnchor = AnchorUtil.getConnectionPointAnchor(connectionPointShape);
					anchorChanged = true;
				}
				if (targetAnchor==null && target instanceof FreeFormConnection) {
					Shape connectionPointShape = AnchorUtil.createConnectionPoint(getFeatureProvider(),
							(FreeFormConnection)target,
							Graphiti.getPeLayoutService().getConnectionMidpoint((FreeFormConnection)target, 0.5));
					targetAnchor = AnchorUtil.getConnectionPointAnchor(connectionPointShape);
					anchorChanged = true;
				}
				
				// this is silly! why are there no setters for sourceAnchor and targetAnchor in AddConnectionContext???
				if (anchorChanged) {
					AddConnectionContext newContext = new AddConnectionContext(sourceAnchor, targetAnchor);
					newContext.setSize(ac.getHeight(), ac.getWidth());
					newContext.setLocation(ac.getX(), ac.getY());
					newContext.setNewObject(ac.getNewObject());
					newContext.setTargetConnection(ac.getTargetConnection());
					newContext.setTargetConnectionDecorator(ac.getTargetConnectionDecorator());
					newContext.setTargetContainer(ac.getTargetContainer());
					
					context = newContext;
				}
				// we're done with this
				createContext = null;
				
				return super.add(context);
			}
			
			@Override
			protected Polyline createConnectionLine(Connection connection) {
				Polyline connectionLine = super.createConnectionLine(connection);
				connectionLine.setLineWidth(2);
				connectionLine.setLineStyle(LineStyle.DOT);
				return connectionLine;
			}
			
			@Override
			protected Class<? extends BaseElement> getBoClass() {
				return Association.class;
			}

			@Override
			public int getHeight() {
				return 0;
			}

			@Override
			public int getWidth() {
				return 0;
			}
		};
	}

	@Override
	public ICreateConnectionFeature getCreateConnectionFeature(IFeatureProvider fp) {
		return new CreateAssociationFeature(fp);
	}

	@Override
	public IReconnectionFeature getReconnectionFeature(IFeatureProvider fp) {
		// TODO Auto-generated method stub
		return new ReconnectAssociationFeature(fp);
	}

	public class CreateAssociationFeature extends AbstractCreateFlowFeature<BaseElement, BaseElement> {

		public CreateAssociationFeature(IFeatureProvider fp) {
			super(fp, "关联线", "Associate information with artifacts and flow objects");
		}

		@Override
		public boolean canCreate(ICreateConnectionContext context) {
			if ( context.getTargetPictogramElement() instanceof FreeFormConnection ) {
				return true;
			}
			return super.canCreate(context);
		}

		@Override
		public Connection create(ICreateConnectionContext context) {
			// save the CreateContext because we'll need it in AddFeature
			createContext = (CreateConnectionContext)context;
			Anchor sourceAnchor = createContext.getSourceAnchor();
			Anchor targetAnchor = createContext.getTargetAnchor();
			PictogramElement source = createContext.getSourcePictogramElement();
			PictogramElement target = createContext.getTargetPictogramElement();
			
			if (sourceAnchor==null && source instanceof FreeFormConnection) {
				Shape connectionPointShape = AnchorUtil.createConnectionPoint(getFeatureProvider(),
						(FreeFormConnection)source,
						Graphiti.getPeLayoutService().getConnectionMidpoint((FreeFormConnection)source, 0.5));
				sourceAnchor = AnchorUtil.getConnectionPointAnchor(connectionPointShape);
				createContext.setSourceAnchor(sourceAnchor);
			}
			if (targetAnchor==null && target instanceof FreeFormConnection) {
				Shape connectionPointShape = AnchorUtil.createConnectionPoint(getFeatureProvider(),
						(FreeFormConnection)target,
						Graphiti.getPeLayoutService().getConnectionMidpoint((FreeFormConnection)target, 0.5));
				targetAnchor = AnchorUtil.getConnectionPointAnchor(connectionPointShape);
				createContext.setTargetAnchor(targetAnchor);
			}

			return super.create(context);
		}

		@Override
		protected String getStencilImageId() {
			return ImageProvider.IMG_16_ASSOCIATION;
		}

		@Override
		protected Class<BaseElement> getSourceClass() {
			return BaseElement.class;
		}

		@Override
		protected Class<BaseElement> getTargetClass() {
			return BaseElement.class;
		}

		@Override
		protected BaseElement createFlow(ModelHandler mh, BaseElement source, BaseElement target) {
			return mh.createAssociation(source, target);
		}

		@Override
		protected BaseElement getSourceBo(ICreateConnectionContext context) {
			Anchor anchor = context.getSourceAnchor();
			if (anchor != null && anchor.getParent() instanceof Shape) {
				Shape shape = (Shape) anchor.getParent();
				Connection conn = AnchorUtil.getConnectionPointOwner(shape);
				if (conn!=null) {
					return BusinessObjectUtil.getFirstElementOfType(conn, getTargetClass());
				}
				return BusinessObjectUtil.getFirstElementOfType(shape, getTargetClass());
			}
			return null;
		}

		@Override
		protected BaseElement getTargetBo(ICreateConnectionContext context) {
			Anchor anchor = context.getTargetAnchor();
			if (anchor != null && anchor.getParent() instanceof Shape) {
				Shape shape = (Shape) anchor.getParent();
				Connection conn = AnchorUtil.getConnectionPointOwner(shape);
				if (conn!=null) {
					return BusinessObjectUtil.getFirstElementOfType(conn, getTargetClass());
				}
				return BusinessObjectUtil.getFirstElementOfType(shape, getTargetClass());
			}
			return null;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.bpmn2.modeler.core.features.AbstractBpmn2CreateConnectionFeature#getBusinessObjectClass()
		 */
		@Override
		public Class getBusinessObjectClass() {
			return Association.class;
		}
	}
	
	
	public static class ReconnectAssociationFeature extends AbstractReconnectFlowFeature {

		public ReconnectAssociationFeature(IFeatureProvider fp) {
			super(fp);
		}

		@Override
		public boolean canReconnect(IReconnectionContext context) {
			BaseElement targetElement = BusinessObjectUtil.getFirstElementOfType(context.getTargetPictogramElement(), BaseElement.class);
			if (targetElement instanceof Association)
				return false;
			PictogramElement targetPictogramElement = context.getTargetPictogramElement();
			if (targetPictogramElement instanceof FreeFormConnection) {
				return true;
			}
			return super.canReconnect(context);
		}

		@Override
		protected Class<? extends EObject> getTargetClass() {
			return BaseElement.class;
		}

		@Override
		protected Class<? extends EObject> getSourceClass() {
			return BaseElement.class;
		}

		@Override
		public void preReconnect(IReconnectionContext context) {
			PictogramElement targetPictogramElement = context.getTargetPictogramElement();
			if (targetPictogramElement instanceof FreeFormConnection) {
				Shape connectionPointShape = AnchorUtil.createConnectionPoint(
						getFeatureProvider(),
						(FreeFormConnection)targetPictogramElement,
						context.getTargetLocation());
				
				if (context instanceof ReconnectionContext) {
					ReconnectionContext rc = (ReconnectionContext) context;
					rc.setNewAnchor(AnchorUtil.getConnectionPointAnchor(connectionPointShape));
					rc.setTargetPictogramElement(connectionPointShape);
				}
			}
			super.preReconnect(context);
		}

		@Override
		public void postReconnect(IReconnectionContext context) {
			Anchor oldAnchor = context.getOldAnchor();
			AnchorContainer oldAnchorContainer = oldAnchor.getParent();
			AnchorUtil.deleteConnectionPointIfPossible(getFeatureProvider(), (Shape) oldAnchorContainer);
			super.postReconnect(context);
		}
	} 
	
}