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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.impl.DeleteContext;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature;

public class DefaultDeleteBPMNShapeFeature extends DefaultDeleteFeature {

	public DefaultDeleteBPMNShapeFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	protected boolean getUserDecision(IDeleteContext context) {
		return true;
	}

	public boolean canDelete(IDeleteContext context) {
		//如果选择的是基本画布就不允许删除 2012-07-10 wy
		if(context.getPictogramElement() instanceof ConnectionDecorator) {
			return false;
		}
		if(BusinessObjectUtil.getBusinessObjectForPictogramElement(context.getPictogramElement()) instanceof BPMNDiagram) {
			return false;
		}
		return true;
	}
	
	@Override
	protected void deleteBusinessObject(Object bo) {
		List<PictogramElement> pictElements = Graphiti.getLinkService().getPictogramElements(getDiagram(), (EObject) bo);
		for (Iterator<PictogramElement> iterator = pictElements.iterator(); iterator.hasNext();) {
			PictogramElement pe = iterator.next();
			deletePeEnvironment(pe);
			Graphiti.getPeService().deletePictogramElement(pe);
		}
		super.deleteBusinessObject(bo);
	}
	
	protected void deletePeEnvironment(PictogramElement pictogramElement){
		if (pictogramElement instanceof ContainerShape) {
			ContainerShape cShape = (ContainerShape) pictogramElement;
			EList<Anchor> anchors = cShape.getAnchors();
			for (Anchor anchor : anchors) {
				deleteConnections(getFeatureProvider(), anchor.getIncomingConnections());
				deleteConnections(getFeatureProvider(), anchor.getOutgoingConnections());
			}
			deleteContainer(getFeatureProvider(), cShape);
		}
	}
	
	protected void deleteContainer(IFeatureProvider fp, ContainerShape cShape) {
		Object[] children = cShape.getChildren().toArray();
		for (Object shape : children) {
			if (shape instanceof ContainerShape) {
				DeleteContext context = new DeleteContext((PictogramElement) shape);
				fp.getDeleteFeature(context).delete(context);
			}
		}
	}

	protected void deleteConnections(IFeatureProvider fp, EList<Connection> connections) {
		List<Connection> con = new ArrayList<Connection>();
		con.addAll(connections);
		for (Connection connection : con) {
			IDeleteContext conDelete = new DeleteContext(connection);
			fp.getDeleteFeature(conDelete).delete(conDelete);
		}
	}
	
}
