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

package org.eclipse.bpmn2.modeler.core.runtime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.di.BpmnDiPackage;
import org.eclipse.bpmn2.modeler.core.preferences.Bpmn2Preferences;
import org.eclipse.bpmn2.modeler.core.preferences.ToolEnablementPreferences;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * @author Bob Brodt
 *
 */
public class ModelEnablementDescriptor extends BaseRuntimeDescriptor {

	private Hashtable<String, HashSet<String>> classes = new Hashtable<String, HashSet<String>>();
	private String type;
	
	// require a TargetRuntime!
	private ModelEnablementDescriptor() {
	}
	
	public ModelEnablementDescriptor(TargetRuntime rt) {
		super(rt);
		setEnabledAll(true);
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	private void setEnabledAll(boolean enabled) {
		if (enabled) {
			Bpmn2Package i = Bpmn2Package.eINSTANCE;
			final List<EClass> items = new ArrayList<EClass>();
			for (EClassifier eclassifier : i.getEClassifiers() ) {
				if (eclassifier instanceof EClass) {
					items.add((EClass)eclassifier);
				}
			}
			setEnabled(items,true);
//			setEnabled(getSubClasses(i.getFlowElement()), true);
//			setEnabled(getSubClasses(i.getDataAssociation()), true);
//			setEnabled(getSubClasses(i.getRootElement()), true);
//			setEnabled(getSubClasses(i.getEventDefinition()), true);
//			setEnabled(getSubClasses(i.getLoopCharacteristics()), true);
//			setEnabled(getSubClasses(i.getExpression()), true);
//			setEnabled(i.getDefinitions(), true);
//			setEnabled(i.getOperation(), true);
//			setEnabled(i.getLane(), true);
//			setEnabled(i.getEscalation(), true);
//			setEnabled(getSubClasses(i.getPotentialOwner()), true);
//			setEnabled(i.getResourceAssignmentExpression(), true);
//			setEnabled(i.getInputSet(), true);
//			setEnabled(i.getOutputSet(), true);
//			setEnabled(i.getAssignment(), true);
//			setEnabled(i.getAssociation(), true);
//			setEnabled(i.getTextAnnotation(), true);
//			setEnabled(i.getMessageFlow(), true);
//			setEnabled(i.getConversationLink(), true);
//			setEnabled(i.getGroup(), true);
//			setEnabled(i.getConversation(), true);
		}
		else {
			classes.clear();
		}
	}
	
	private void setEnabled(List<EClass> eClasses, boolean enabled) {
		for (EClass c : eClasses) {
			setEnabled(c,enabled);
		}
	}
	
	public void setEnabled(EClass eClass, boolean enabled) {
		if (eClass.getInstanceClass() != EObject.class) {
			if (enabled) {
				if (!classes.containsKey(eClass.getName()))
					setEnabled(eClass.getName(), true);
			}
			else {
				setEnabled(eClass.getName(), false);
			}
		}
	}
	
	private EClass getEClass(String className) {
		// try the runtime package first
		EClass eClass = (EClass)getEPackage().getEClassifier(className);
		// then all BPMN2 packages
		if (eClass==null)
			eClass = (EClass)Bpmn2Package.eINSTANCE.getEClassifier(className);
		if (eClass==null)
			eClass = (EClass)BpmnDiPackage.eINSTANCE.getEClassifier(className);
//		if (eClass==null)
//			eClass = (EClass)DcPackage.eINSTANCE.getEClassifier(className);
//		if (eClass==null)
//			eClass = (EClass)DiPackage.eINSTANCE.getEClassifier(className);
		return eClass;
	}
	
	public void setEnabled(String className, boolean enabled) {
		EClass eClass = getEClass(className);
		
		// add an entry for the class name by itself
		if (enabled) {
			if (classes.containsKey(className))
				return;
			HashSet<String> features = new HashSet<String>();
			classes.put(className, features);
			if (eClass!=null) {
				for (EAttribute a : eClass.getEAllAttributes()) {
					features.add(a.getName());
				}
				for (EReference a : eClass.getEAllContainments()) {
					features.add(a.getName());
					setEnabled(a.getEReferenceType(), true);
				}
				for (EReference a : eClass.getEAllReferences()) {
					features.add(a.getName());
					setEnabled(a.getEReferenceType(), true);
				}
			}
		}
		else {
			if (!classes.containsKey(className))
				return;
			classes.remove(className);

			// remove any reference or containment list features
			// of this type for other elements 
			List<String> removed = new ArrayList<String>();
			for (Entry<String, HashSet<String>> entry : classes.entrySet()) {
				EClass ec = getEClass(entry.getKey());
				if (ec!=null) {
					HashSet<String> features = entry.getValue();
	
					for (EReference a : ec.getEAllContainments()) {
						// if this feature is a reference to the
						// class being disabled, remove it
						if (a.getEReferenceType() == eClass)
							removed.add(a.getName());
					}
					for (EReference a : ec.getEAllReferences()) {
						if (a.getEReferenceType() == eClass)
							removed.add(a.getName());
					}
					features.removeAll(removed);
				}
			}
		}
	}
	
	public void setEnabled(String className, String featureName, boolean enabled) {
		if ("all".equals(className)) {
			// enable all model objects
			if (featureName==null)
				setEnabledAll(enabled);
			else {
				// enable feature for all classes
				for (Entry<String, HashSet<String>> entry : classes.entrySet()) {
					HashSet<String> features = entry.getValue();
					if (enabled)
						features.add(featureName);
					else
						features.remove(featureName);
				}
			}
		}
		else if ("default".equals(className)) {
			// select the set of enablements from the default runtime
			// an optional featureName is used to specify a ModelEnablement type
			TargetRuntime rt = TargetRuntime.getDefaultRuntime();
			String type = getType();
			if (featureName!=null)
				type = featureName;
			Set <Entry<String, HashSet<String>>> otherClasses = rt.getModelEnablements(ModelUtil.getDiagramType(type)).classes.entrySet(); 
			for (Entry<String, HashSet<String>> entry : otherClasses) {
				for (String feature : entry.getValue()) {
					setEnabled(entry.getKey(), feature, enabled);
				}
			}
		}
		else if (featureName!=null && !featureName.isEmpty()) {
			if ("all".equals(featureName)) {
				if (enabled) {
					setEnabled(className,true);
				}
				else
				{
					if (classes.containsKey(className)) {
						classes.get(className).clear();
					}
				}
			}
			else if (enabled) {
				HashSet<String> features;
				if (classes.containsKey(className)) {
					features = classes.get(className);
				}
				else {
					features = new HashSet<String>();
					classes.put(className, features);
				}
				features.add(featureName);
			}
			else {
				if (classes.containsKey(className)) {
					classes.get(className).remove(featureName);
				}
			}
		}
		else
			setEnabled(className, enabled);
	}

	
	private ToolEnablementPreferences getToolEnablementPreferences() {
		IProject project = Bpmn2Preferences.getActiveProject();
		return ToolEnablementPreferences.getPreferences(project);
	}


	public boolean isEnabled(String className, String featureName) {
		if (Bpmn2Preferences.getInstance().getOverrideModelEnablements()) {
			String name = className;
			if (featureName!=null && !featureName.isEmpty())
				name += "." + featureName;
			return getToolEnablementPreferences().isEnabled(name);
		}
		if (classes.containsKey(className)) {
			if (featureName!=null && !featureName.isEmpty()) {
				HashSet<String> features = classes.get(className);
				return features.contains(featureName);
			}
			return true;
		}
		return false;
	}
	
	public boolean isEnabled(EClass eClass, EStructuralFeature feature) {
		if (feature==null)
			return isEnabled(eClass);
		return isEnabled(eClass.getName(), feature.getName());
	}
	
	public boolean isEnabled(EClass eClass) {
		if (eClass==null)
			return false;
		return isEnabled(eClass.getName());
	}

	public boolean isEnabled(String className) {
		return isEnabled(className, null);
	}
	
	public Collection<String> getAllEnabled() {
		ArrayList<String> list = new ArrayList<String>();
		for (Entry<String, HashSet<String>> entry : classes.entrySet()) {
			String className = entry.getKey();
			list.add(className);
			HashSet<String> features = entry.getValue();
			for (String featureName : features) {
				list.add(className + "." + featureName);
			}
		}
		return list;
	}
	
	public Collection<String> getAllEnabled(String className) {
		if (classes.containsKey(className))
			return classes.get(className);
		return new ArrayList<String>();
	}
	
	public static List<EClass> getSubClasses(EClass parentClass) {

		List<EClass> classList = new ArrayList<EClass>();
		EList<EClassifier> classifiers = Bpmn2Package.eINSTANCE.getEClassifiers();

		for (EClassifier classifier : classifiers) {
			if (classifier instanceof EClass) {
				EClass clazz = (EClass) classifier;

				clazz.getEAllSuperTypes().contains(parentClass);
				if (parentClass.isSuperTypeOf(clazz) && !clazz.isAbstract()) {
					classList.add(clazz);
				}
			}
		}
		return classList;
	}
}
