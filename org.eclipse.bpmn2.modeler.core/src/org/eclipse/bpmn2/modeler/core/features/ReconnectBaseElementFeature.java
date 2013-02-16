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
package org.eclipse.bpmn2.modeler.core.features;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.di.BPMNEdge;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.dd.di.DiagramElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.context.impl.ReconnectionContext;
import org.eclipse.graphiti.features.impl.DefaultReconnectionFeature;

public class ReconnectBaseElementFeature extends DefaultReconnectionFeature {

		public ReconnectBaseElementFeature(IFeatureProvider fp) {
			super(fp);
		}

		@Override
		public boolean canReconnect(IReconnectionContext context) {
			// TODO Auto-generated method stub
			return super.canReconnect(context);
		}

		@Override
		public void preReconnect(IReconnectionContext context) {
			// TODO Auto-generated method stub
			super.preReconnect(context);
		}

		@Override
		public void postReconnect(IReconnectionContext context) {
			super.postReconnect(context);

			BPMNEdge edge = BusinessObjectUtil.getFirstElementOfType(context.getConnection(), BPMNEdge.class);
			DiagramElement de = BusinessObjectUtil.getFirstElementOfType(context.getTargetPictogramElement(), DiagramElement.class);
			if (context.getReconnectType().equals(ReconnectionContext.RECONNECT_TARGET)) {
				edge.setTargetElement(de);
			}
			else {
				edge.setSourceElement(de);
			}
			
			BaseElement flow = BusinessObjectUtil.getFirstElementOfType(context.getConnection(), BaseElement.class);
			BaseElement be = BusinessObjectUtil.getFirstElementOfType(context.getTargetPictogramElement(), BaseElement.class);
			if (context.getReconnectType().equals(ReconnectionContext.RECONNECT_TARGET)) {
				EStructuralFeature feature = flow.eClass().getEStructuralFeature("targetRef");
				if (feature!=null)
					flow.eSet(feature, be);
			}
			else {
				EStructuralFeature feature = flow.eClass().getEStructuralFeature("sourceRef");
				if (feature!=null)
					flow.eSet(feature, be);
			}
		}
		
	}