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

package org.eclipse.bpmn2.modeler.ui.property;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.modeler.core.features.AbstractBpmn2CreateConnectionFeature;
import org.eclipse.bpmn2.modeler.core.features.AbstractBpmn2CreateFeature;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.ui.diagram.BPMNFeatureProvider;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.bpmn2.modeler.ui.util.PropertyUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.internal.parts.ContainerShapeEditPart;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.graphics.Image;

/**
 * @author Bob Brodt
 *
 */
public class PropertyLabelProvider extends LabelProvider {

	@Override
	public Image getImage(Object element) {
		EObject be = BusinessObjectUtil.getBusinessObjectForSelection((ISelection)element);
        BPMN2Editor editor = BPMN2Editor.getEditor( be );
        
        if (editor!=null) {
		    BPMNFeatureProvider fp = (BPMNFeatureProvider)editor.getDiagramTypeProvider().getFeatureProvider();
			PictogramElement pe = BusinessObjectUtil.getPictogramElementForSelection((ISelection)element);
		    IFeature cf = fp.getCreateFeatureForPictogramElement(pe);
			if (cf instanceof AbstractBpmn2CreateFeature) {
				return GraphitiUi.getImageService().getImageForId(
						((AbstractBpmn2CreateFeature)cf).getCreateImageId());
			}
			if (cf instanceof AbstractBpmn2CreateConnectionFeature) {
				return GraphitiUi.getImageService().getImageForId(
						((AbstractBpmn2CreateConnectionFeature)cf).getCreateImageId());
			}
        }
		return super.getImage(element);
	}

	@Override
	public String getText(Object element) {
		EObject be = BusinessObjectUtil.getBusinessObjectForSelection((ISelection)element);
		if (be!=null) {
			if (be instanceof BPMNDiagram) {
				BaseElement bpmnElement = ((BPMNDiagram)be).getPlane().getBpmnElement();
				if (bpmnElement instanceof Process) {
					be = bpmnElement;
				}
			}
			return PropertyUtil.getText(be);
		}
		PictogramElement pe = BusinessObjectUtil.getPictogramElementForSelection((ISelection)element);
		if (pe!=null && pe.getGraphicsAlgorithm()!=null) {
			return ModelUtil.getLabel( pe.getGraphicsAlgorithm() );
		}
		return super.getText(element);
	}
}
