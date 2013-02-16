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
import org.eclipse.bpmn2.LoopCharacteristics;
import org.eclipse.bpmn2.MultiInstanceLoopCharacteristics;
import org.eclipse.bpmn2.StandardLoopCharacteristics;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil.Loop;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil.MultiInstance;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;

public class UpdateActivityLoopAndMultiInstanceMarkerFeature extends AbstractUpdateMarkerFeature<Activity> {

	public static String IS_LOOP_OR_MULTI_INSTANCE = "marker.loop.or.multi";

	enum LoopCharacteristicType {
		
		NULL("null"), 
		
		LOOP(StandardLoopCharacteristics.class.getSimpleName()), 
		
		MULTI_PARALLEL(MultiInstanceLoopCharacteristics.class.getSimpleName() + ":parallel"), 
		
		MULTI_SEQUENTIAL(MultiInstanceLoopCharacteristics.class.getSimpleName() + ":sequential");

		private String name;

		private LoopCharacteristicType(String name) {
			this.name = name;
		}

		String getName() {
			return name;
		}
	}

	public UpdateActivityLoopAndMultiInstanceMarkerFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	protected String getPropertyKey() {
		return IS_LOOP_OR_MULTI_INSTANCE;
	}

	@Override
	protected boolean isPropertyChanged(Activity activity, String propertyValue) {
		return !getLoopCharacteristicsValue(activity).getName().equals(propertyValue);
	}

	@Override
	protected void doUpdate(Activity activity, ContainerShape markerContainer) {
		GraphicsUtil.clearActivityMarker(markerContainer, GraphicsUtil.ACTIVITY_MARKER_LOOP_CHARACTERISTIC);
		switch (getLoopCharacteristicsValue(activity)) {
		case LOOP:
			Loop loop = GraphicsUtil.createActivityMarkerStandardLoop(markerContainer);
			loop.circle.setForeground(manageColor(StyleUtil.CLASS_FOREGROUND));
			loop.arrow.setForeground(manageColor(StyleUtil.CLASS_FOREGROUND));
			break;
		case MULTI_PARALLEL:
			MultiInstance multiParallel = GraphicsUtil.createActivityMarkerMultiParallel(markerContainer);
			multiParallel.line1.setForeground(manageColor(StyleUtil.CLASS_FOREGROUND));
			multiParallel.line2.setForeground(manageColor(StyleUtil.CLASS_FOREGROUND));
			multiParallel.line3.setForeground(manageColor(StyleUtil.CLASS_FOREGROUND));
			break;
		case MULTI_SEQUENTIAL:
			MultiInstance multiSeq = GraphicsUtil.createActivityMarkerMultiSequential(markerContainer);
			multiSeq.line1.setForeground(manageColor(StyleUtil.CLASS_FOREGROUND));
			multiSeq.line2.setForeground(manageColor(StyleUtil.CLASS_FOREGROUND));
			multiSeq.line3.setForeground(manageColor(StyleUtil.CLASS_FOREGROUND));
			break;
		}
	}

	@Override
	protected String convertPropertyToString(Activity activity) {
		return getLoopCharacteristicsValue(activity).getName();
	}

	public static LoopCharacteristicType getLoopCharacteristicsValue(Activity activity) {
		LoopCharacteristics loopCharacteristics = activity.getLoopCharacteristics();
		LoopCharacteristicType type = LoopCharacteristicType.NULL;

		if (loopCharacteristics != null) {
			if (loopCharacteristics instanceof MultiInstanceLoopCharacteristics) {
				MultiInstanceLoopCharacteristics multi = (MultiInstanceLoopCharacteristics) loopCharacteristics;
				type = multi.isIsSequential() ? LoopCharacteristicType.MULTI_SEQUENTIAL
				        : LoopCharacteristicType.MULTI_PARALLEL;
			} else {
				type = LoopCharacteristicType.LOOP;
			}
		}

		return type;
	}
}