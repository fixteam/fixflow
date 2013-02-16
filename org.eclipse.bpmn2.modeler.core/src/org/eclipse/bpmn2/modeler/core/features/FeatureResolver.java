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

import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IDirectEditingFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IMoveShapeFeature;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.IUpdateFeature;

public interface FeatureResolver {

	List<ICreateConnectionFeature> getCreateConnectionFeatures(IFeatureProvider fp);

	List<ICreateFeature> getCreateFeatures(IFeatureProvider fp);

	IAddFeature getAddFeature(IFeatureProvider fp, BaseElement e);

	IDirectEditingFeature getDirectEditingFeature(IFeatureProvider fp, BaseElement e);

	ILayoutFeature getLayoutFeature(IFeatureProvider fp, BaseElement e);

	IUpdateFeature getUpdateFeature(IFeatureProvider fp, BaseElement e);

	IMoveShapeFeature getMoveFeature(IFeatureProvider fp, BaseElement e);

	IResizeShapeFeature getResizeFeature(IFeatureProvider fp, BaseElement e);

	IDeleteFeature getDeleteFeature(IFeatureProvider fp, BaseElement e);
}