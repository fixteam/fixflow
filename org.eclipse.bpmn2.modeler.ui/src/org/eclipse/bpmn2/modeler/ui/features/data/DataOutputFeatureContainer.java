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
package org.eclipse.bpmn2.modeler.ui.features.data;

import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.modeler.core.ModelHandler;
import org.eclipse.bpmn2.modeler.core.features.data.AbstractCreateDataInputOutputFeature;
import org.eclipse.bpmn2.modeler.core.features.data.AddDataFeature;
import org.eclipse.bpmn2.modeler.core.model.Bpmn2ModelerFactory;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.bpmn2.modeler.ui.ImageProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.algorithms.Polygon;

public class DataOutputFeatureContainer extends AbstractDataFeatureContainer {

	@Override
	public boolean canApplyTo(Object o) {
		return super.canApplyTo(o) && o instanceof DataOutput;
	}

	@Override
	public ICreateFeature getCreateFeature(IFeatureProvider fp) {
		return new CreateDataOutputFeature(fp);
	}

	@Override
	public IAddFeature getAddFeature(IFeatureProvider fp) {
		return new AddDataFeature<DataOutput>(fp) {
			@Override
			protected boolean isSupportCollectionMarkers() {
				return false;
			}

			@Override
			protected void decorate(Polygon p) {
				Polygon arrow = GraphicsUtil.createDataArrow(p);
				arrow.setFilled(true);
				arrow.setBackground(manageColor(StyleUtil.CLASS_FOREGROUND));
				arrow.setForeground(manageColor(StyleUtil.CLASS_FOREGROUND));
			}

			@Override
			public String getName(DataOutput t) {
				return t.getName();
			}
		};
	}

	public static class CreateDataOutputFeature extends AbstractCreateDataInputOutputFeature {

		public CreateDataOutputFeature(IFeatureProvider fp) {
			super(fp, "数据输出", "Declaration that a particular kind of data can be produced as output");
		}

		@SuppressWarnings("unchecked")
		@Override
		public DataOutput add(Object target, ModelHandler handler) {
			DataOutput dataOutput = Bpmn2ModelerFactory.create(DataOutput.class);
//			dataOutput.setId(EcoreUtil.generateUUID());
			dataOutput.setName("数据输出");
			handler.addDataOutput(target, dataOutput);
			ModelUtil.setID(dataOutput);
			return dataOutput;
		}

		@Override
		public String getStencilImageId() {
			return ImageProvider.IMG_16_DATA_OUTPUT;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.bpmn2.modeler.core.features.AbstractBpmn2CreateFeature#getBusinessObjectClass()
		 */
		@Override
		public Class getBusinessObjectClass() {
			return DataOutput.class;
		}

	}
}