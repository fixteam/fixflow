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
package org.eclipse.bpmn2.modeler.ui.features.activity.task;

import java.io.IOException;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.bpmn2.modeler.core.ModelHandler;
import org.eclipse.bpmn2.modeler.core.features.activity.task.AbstractCreateTaskFeature;
import org.eclipse.bpmn2.modeler.core.features.activity.task.AddTaskFeature;
import org.eclipse.bpmn2.modeler.core.model.Bpmn2ModelerFactory;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.bpmn2.modeler.ui.ImageProvider;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;

import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;


public class UserTaskFeatureContainer extends AbstractTaskFeatureContainer {

	@Override
	public boolean canApplyTo(Object o) {
		return super.canApplyTo(o) && o instanceof UserTask;
	}

	@Override
	public ICreateFeature getCreateFeature(IFeatureProvider fp) {
		return new CreateUserTaskFeature(fp);
	}

	@Override
	public IAddFeature getAddFeature(IFeatureProvider fp) {
		return new AddTaskFeature(fp) {
			@Override
			protected void decorateActivityRectangle(RoundedRectangle rect) {
				IGaService service = Graphiti.getGaService();
				Image img = service.createImage(rect, ImageProvider.IMG_16_USER_TASK);
				service.setLocationAndSize(img, 2, 2, GraphicsUtil.TASK_IMAGE_SIZE, GraphicsUtil.TASK_IMAGE_SIZE);
			}
		};
	}

	public static class CreateUserTaskFeature extends AbstractCreateTaskFeature {

		public CreateUserTaskFeature(IFeatureProvider fp) {
			super(fp, "人工任务",
					"A User Task is a typical \"workflow\" Task where a human"
					+" performer performs the Task with the assistance of a"
					+" software application and is scheduled through a modelObject"
					+" list manager of some sort.");
		}

		@Override
		protected Task createFlowElement(ICreateContext context) {
			UserTask userTask = Bpmn2ModelerFactory.create(UserTask.class);
			ModelHandler mh=null;
			try {
				mh = ModelHandler.getInstance(getDiagram());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			userTask.setName("人工任务");
			userTask.getResources().add(mh.createPotentialOwner());
			
			ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE
			        .createExtensionAttributeValue();
			userTask.getExtensionValues().add(extensionElement);
			FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
			        (org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_COMMAND, mh.createTaskViewCommand());
			extensionElement.getValue().add(extensionElementEntry);
			

			
			
			
			FeatureMap.Entry extensionElementEntry2 = new SimpleFeatureMapEntry(
			        (org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__ASSIGN_POLICY_TYPE, mh.createAssignPolicyType());
			extensionElement.getValue().add(extensionElementEntry2);
			
			FeatureMap.Entry extensionElementEntry3 = new SimpleFeatureMapEntry(
			        (org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__SKIP_STRATEGY,mh.createSkipStrategy());
			extensionElement.getValue().add(extensionElementEntry3);
			
			
			
			
			
			return userTask;
		}
		
	

		@Override
		protected String getStencilImageId() {
			return ImageProvider.IMG_16_USER_TASK;
		}


		@SuppressWarnings("rawtypes")
		@Override
		public Class getBusinessObjectClass() {
			return UserTask.class;
		}
	}
}