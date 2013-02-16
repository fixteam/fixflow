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

package org.eclipse.bpmn2.modeler.ui.adapters;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.CallActivity;
import org.eclipse.bpmn2.DataAssociation;
import org.eclipse.bpmn2.Error;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.Interface;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.Message;
import org.eclipse.bpmn2.MessageFlow;
import org.eclipse.bpmn2.MultiInstanceLoopCharacteristics;
import org.eclipse.bpmn2.Property;
import org.eclipse.bpmn2.ResourceAssignmentExpression;
import org.eclipse.bpmn2.ResourceParameterBinding;
import org.eclipse.bpmn2.ResourceRole;
import org.eclipse.bpmn2.ScriptTask;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.core.adapters.ObjectDescriptor;
import org.eclipse.bpmn2.modeler.core.runtime.PropertyExtensionDescriptor;
import org.eclipse.bpmn2.modeler.core.runtime.TargetRuntime;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.ui.adapters.properties.ActivityPropertiesAdapter;
import org.eclipse.bpmn2.modeler.ui.adapters.properties.CallActivityPropertiesAdapter;
import org.eclipse.bpmn2.modeler.ui.adapters.properties.DataAssociationPropertiesAdapter;
import org.eclipse.bpmn2.modeler.ui.adapters.properties.ErrorPropertiesAdapter;
import org.eclipse.bpmn2.modeler.ui.adapters.properties.FormalExpressionPropertiesAdapter;
import org.eclipse.bpmn2.modeler.ui.adapters.properties.InterfacePropertiesAdapter;
import org.eclipse.bpmn2.modeler.ui.adapters.properties.ItemAwareElementPropertiesAdapter;
import org.eclipse.bpmn2.modeler.ui.adapters.properties.ItemDefinitionPropertiesAdapter;
import org.eclipse.bpmn2.modeler.ui.adapters.properties.MessageFlowPropertiesAdapter;
import org.eclipse.bpmn2.modeler.ui.adapters.properties.MessagePropertiesAdapter;
import org.eclipse.bpmn2.modeler.ui.adapters.properties.MultiInstanceLoopCharacteristicsPropertiesAdapter;
import org.eclipse.bpmn2.modeler.ui.adapters.properties.PropertyPropertiesAdapter;
import org.eclipse.bpmn2.modeler.ui.adapters.properties.ResourceAssignmentExpressionPropertiesAdapter;
import org.eclipse.bpmn2.modeler.ui.adapters.properties.ResourceParameterBindingPropertiesAdapter;
import org.eclipse.bpmn2.modeler.ui.adapters.properties.ResourceRolePropertiesAdapter;
import org.eclipse.bpmn2.modeler.ui.adapters.properties.ScriptTaskPropertiesAdapter;
import org.eclipse.bpmn2.modeler.ui.adapters.properties.SequenceFlowPropertiesAdapter;
import org.eclipse.bpmn2.modeler.ui.adapters.properties.TaskPropertiesAdapter;
import org.eclipse.bpmn2.provider.Bpmn2ItemProviderAdapterFactory;
import org.eclipse.bpmn2.util.Bpmn2Switch;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;

/**
 * This class adds a name-value map to the Bpmn2ItemProviderAdapterFactory.
 * This allows for additional customization of the UI.
 */
public class Bpmn2EditorItemProviderAdapterFactory extends Bpmn2ItemProviderAdapterFactory {

	public Bpmn2EditorItemProviderAdapterFactory() {
		super();
		supportedTypes.add(ExtendedPropertiesAdapter.class);
	}

	@Override
	public Adapter adaptNew(Notifier object, Object type) {
		if (type == ExtendedPropertiesAdapter.class && object instanceof EObject) {
			return bpmn2ModelSwitch.doSwitch((EObject) object);
		}
		return super.adaptNew(object, type);
	}
	
    protected Bpmn2Switch<ExtendedPropertiesAdapter> bpmn2ModelSwitch = new Bpmn2ExtendedPropertiesSwitch(this);
    
    public class Bpmn2ExtendedPropertiesSwitch extends Bpmn2Switch<ExtendedPropertiesAdapter> {

    	private AdapterFactory adapterFactory;
    	
    	public Bpmn2ExtendedPropertiesSwitch(AdapterFactory adapterFactory) {
    		super();
    		this.adapterFactory = adapterFactory;
    	}
    	
        @Override
		public ExtendedPropertiesAdapter defaultCase(EObject object) {
        	ExtendedPropertiesAdapter adapter = new ExtendedPropertiesAdapter(adapterFactory,object);
        	adapter.setObjectDescriptor(new ObjectDescriptor(adapterFactory, object) {
				@Override
				public String getLabel(Object context) {
					EObject object = this.object;
					if (context instanceof EObject)
						object = (EObject)context;
					if (ModelUtil.isStringWrapper(object)) {
						return "Item Type";
					}
					return super.getLabel(context);
				}

				@Override
				public String getText(Object context) {
					EObject object = this.object;
					if (context instanceof EObject)
						object = (EObject)context;
					if (ModelUtil.isStringWrapper(object)) {
						return ModelUtil.getStringWrapperValue(object);
					}
					return super.getText(context);
				}
        	});
        	return adapter;
		}

        private ExtendedPropertiesAdapter getTargetRuntimeAdapter(EObject object) {
			PropertyExtensionDescriptor ped = TargetRuntime.getCurrentRuntime().getPropertyExtension(object.getClass());
			if (ped!=null)
				return ped.getAdapter(adapterFactory,object);
			return null;
        }
        
		@Override
        public ExtendedPropertiesAdapter caseScriptTask(ScriptTask object) {
			ExtendedPropertiesAdapter adapter = getTargetRuntimeAdapter(object);
			if (adapter!=null)
				return adapter;
			return new ScriptTaskPropertiesAdapter(adapterFactory,object);
        }

        @Override
        public ExtendedPropertiesAdapter caseCallActivity(CallActivity object) {
			ExtendedPropertiesAdapter adapter = getTargetRuntimeAdapter(object);
			if (adapter!=null)
				return adapter;
        	return new CallActivityPropertiesAdapter(adapterFactory,object);
        }

		@Override
		public ExtendedPropertiesAdapter caseTask(Task object) {
			ExtendedPropertiesAdapter adapter = getTargetRuntimeAdapter(object);
			if (adapter!=null)
				return adapter;
			return new TaskPropertiesAdapter(adapterFactory,object);
		}

		@Override
		public ExtendedPropertiesAdapter caseActivity(Activity object) {
			ExtendedPropertiesAdapter adapter = getTargetRuntimeAdapter(object);
			if (adapter!=null)
				return adapter;
        	return new ActivityPropertiesAdapter(adapterFactory,object);
		}

		@Override
		public ExtendedPropertiesAdapter caseSequenceFlow(SequenceFlow object) {
			ExtendedPropertiesAdapter adapter = getTargetRuntimeAdapter(object);
			if (adapter!=null)
				return adapter;
        	return new SequenceFlowPropertiesAdapter(adapterFactory,object);
		}

		@Override
		public ExtendedPropertiesAdapter caseFormalExpression(FormalExpression object) {
			ExtendedPropertiesAdapter adapter = getTargetRuntimeAdapter(object);
			if (adapter!=null)
				return adapter;
	    	return new FormalExpressionPropertiesAdapter(adapterFactory,object);
		}

		@Override
		public ExtendedPropertiesAdapter caseItemDefinition(ItemDefinition object) {
			ExtendedPropertiesAdapter adapter = getTargetRuntimeAdapter(object);
			if (adapter!=null)
				return adapter;
        	return new ItemDefinitionPropertiesAdapter(adapterFactory,object);
		}

		@Override
		public ExtendedPropertiesAdapter caseItemAwareElement(ItemAwareElement object) {
			ExtendedPropertiesAdapter adapter = getTargetRuntimeAdapter(object);
			if (adapter!=null)
				return adapter;
        	return new ItemAwareElementPropertiesAdapter(adapterFactory,object);
		}

		@Override
		public ExtendedPropertiesAdapter caseResourceAssignmentExpression(ResourceAssignmentExpression object) {
			ExtendedPropertiesAdapter adapter = getTargetRuntimeAdapter(object);
			if (adapter!=null)
				return adapter;
        	return new ResourceAssignmentExpressionPropertiesAdapter(adapterFactory,object);
		}

		@Override
		public ExtendedPropertiesAdapter caseResourceRole(ResourceRole object) {
			ExtendedPropertiesAdapter adapter = getTargetRuntimeAdapter(object);
			if (adapter!=null)
				return adapter;
        	return new ResourceRolePropertiesAdapter(adapterFactory,object);
		}

		@Override
		public ExtendedPropertiesAdapter caseDataAssociation(DataAssociation object) {
			ExtendedPropertiesAdapter adapter = getTargetRuntimeAdapter(object);
			if (adapter!=null)
				return adapter;
        	return new DataAssociationPropertiesAdapter(adapterFactory,object);
		}

		@Override
		public ExtendedPropertiesAdapter caseError(Error object) {
			ExtendedPropertiesAdapter adapter = getTargetRuntimeAdapter(object);
			if (adapter!=null)
				return adapter;
        	return new ErrorPropertiesAdapter(adapterFactory,object);
		}

		@Override
		public ExtendedPropertiesAdapter caseResourceParameterBinding(ResourceParameterBinding object) {
			ExtendedPropertiesAdapter adapter = getTargetRuntimeAdapter(object);
			if (adapter!=null)
				return adapter;
        	return new ResourceParameterBindingPropertiesAdapter(adapterFactory,object);
		}
		
		@Override
		public ExtendedPropertiesAdapter caseMessageFlow(MessageFlow object) {
			ExtendedPropertiesAdapter adapter = getTargetRuntimeAdapter(object);
			if (adapter!=null)
				return adapter;
        	return new MessageFlowPropertiesAdapter(adapterFactory,object);
		}

		@Override
		public ExtendedPropertiesAdapter caseMessage(Message object) {
			ExtendedPropertiesAdapter adapter = getTargetRuntimeAdapter(object);
			if (adapter!=null)
				return adapter;
        	return new MessagePropertiesAdapter(adapterFactory,object);
		}

		@Override
		public ExtendedPropertiesAdapter caseInterface(Interface object) {
			ExtendedPropertiesAdapter adapter = getTargetRuntimeAdapter(object);
			if (adapter!=null)
				return adapter;
			return new InterfacePropertiesAdapter(adapterFactory,object);
		}

		@Override
		public ExtendedPropertiesAdapter caseProperty(Property object) {
			ExtendedPropertiesAdapter adapter = getTargetRuntimeAdapter(object);
			if (adapter!=null)
				return adapter;
			return new PropertyPropertiesAdapter(adapterFactory,object);
		}

		@Override
		public ExtendedPropertiesAdapter caseMultiInstanceLoopCharacteristics(MultiInstanceLoopCharacteristics object) {
			ExtendedPropertiesAdapter adapter = getTargetRuntimeAdapter(object);
			if (adapter!=null)
				return adapter;
			return new MultiInstanceLoopCharacteristicsPropertiesAdapter(adapterFactory,object);
		}


		// TODO: add remaining BPMN2 elements
    };
}
