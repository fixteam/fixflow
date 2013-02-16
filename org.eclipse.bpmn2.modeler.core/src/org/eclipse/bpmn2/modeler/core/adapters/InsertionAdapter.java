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

package org.eclipse.bpmn2.modeler.core.adapters;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;

/**
 * This adapter will insert an EObject into its container feature when the EObject's
 * content changes. This allows the UI to construct new objects without inserting
 * them into their container unless the user changes some feature in the new object.
 * Thus, an empty EObject is available for use by the UI for rendering only, without
 * creating an EMF transaction, and hence, a useless entry on the undo stack.
 */
public class InsertionAdapter extends EContentAdapter {
	
	protected Resource resource;
	protected EObject object;
	protected EStructuralFeature feature;
	protected EObject value;
	
	public InsertionAdapter(EObject object, EStructuralFeature feature, EObject value) {
		this(null,object,feature,value);
	}

	public InsertionAdapter(Resource resource, EObject object, EStructuralFeature feature, EObject value) {
		// in order for this to work, the object must be contained in a Resource,
		// the value must NOT YET be contained in a Resource,
		// and the value must be an instance of the feature EType.
//		assert(object.eResource()!=null);
//		assert(value.eResource()==null);
//		assert(feature.getEType().isInstance(value));
		if (resource==null)
			this.resource = object.eResource();
		else
			this.resource = resource;
		this.object = object;
		this.feature = feature;
		this.value = value;
	}
	
	public InsertionAdapter(EObject object, String featureName, EObject value) {
		this(object, object.eClass().getEStructuralFeature(featureName), value);
	}
	
	public static EObject add(EObject object, EStructuralFeature feature, EObject value) {
		value.eAdapters().add(
				new InsertionAdapter(object, feature, value));
		return value;
	}
	
	public static EObject add(EObject object, String featureName, EObject value) {
		value.eAdapters().add(
				new InsertionAdapter(object, featureName, value));
		return value;
	}

	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
		if (notification.getNotifier() == value) {
			execute();
		}
	}

	@SuppressWarnings("unchecked")
	public void execute() {
		// remove this adapter from the value - this adapter is a one-shot deal!
		value.eAdapters().remove(this);
		// set the value in the object
		boolean valueChanged = false;
		final EList<EObject> list = feature.isMany() ? (EList<EObject>)object.eGet(feature) : null;
		if (list==null)
			valueChanged = object.eGet(feature)!=value;
		else
			valueChanged = !list.contains(value);
		
		if (valueChanged) {
			TransactionalEditingDomain domain = getEditingDomain();
			if (domain==null) {
				if (list==null)
					object.eSet(feature, value);
				else
					list.add(value);
				// assign the value's ID if it has one:
				// because of changes made by cascading InsertionAdapters,
				// the object could now be contained in a resource and hence
				// the setID() will need to be executed on the command stack.
				domain = getEditingDomain();
				if (domain==null) {
					ModelUtil.setID(value);
				}
				else {
					domain.getCommandStack().execute(new RecordingCommand(domain) {
						@Override
						protected void doExecute() {
							ModelUtil.setID(value);
						}
					});
				}
			}
			else {
				domain.getCommandStack().execute(new RecordingCommand(domain) {
					@Override
					protected void doExecute() {
						if (list==null)
							object.eSet(feature, value);
						else
							list.add(value);
						// assign the value's ID if it has one
						ModelUtil.setID(value);
					}
				});
			}
		}
	}
	
	private TransactionalEditingDomain getEditingDomain() {
		if (resource==null) {
			return
				(object.eResource()==null) ?
				null : 
				TransactionUtil.getEditingDomain(object.eResource());
		}
		else
			return TransactionUtil.getEditingDomain(resource);
	}
	
	public static void executeIfNeeded(EObject value) {
		List<InsertionAdapter> allAdapters = new ArrayList<InsertionAdapter>();
		
		for (Adapter adapter : value.eAdapters()) {
			if (adapter instanceof InsertionAdapter) {
				allAdapters.add((InsertionAdapter)adapter);
			}
		}
		for (InsertionAdapter adapter : allAdapters)
			adapter.execute();
	}
	
	public Resource getResource() {
		return resource;
	}
}
