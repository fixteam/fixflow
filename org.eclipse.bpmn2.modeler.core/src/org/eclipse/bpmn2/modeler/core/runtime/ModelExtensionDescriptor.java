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
import java.util.List;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Internal;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * @author Bob Brodt
 *
 */
public class ModelExtensionDescriptor extends BaseRuntimeDescriptor {

	// Container class for property values
	public static class Value {
		
		static int ID = 0;
		String id;
		public List<Object>values;
		
		public Value() {
			setDefaultId();
		}
		
		public Value(String id) {
			if (id==null || id.isEmpty())
				setDefaultId();
			else
				this.id = id;
		}
		
		public List<Object> getValues() {
			if (values==null) {
				values = new ArrayList<Object>();
			}
			return values;
		}
		
		private void setDefaultId() {
			id = "V-" + ID++;
		}
	}
	
	// name/value pairs constructed from Custom Task extension point
	public static class Property {
		public String name;
		public String description;
		public List<Object>values;
		public String ref;
		public String type;
		
		public Property() {
			this.name = "unknown";
		}
		
		public Property(String name, String description) {
			this.name = name;
			this.description = description;
		}
		
		public List<Object> getValues() {
			if (values==null) {
				values = new ArrayList<Object>();
			}
			return values;
		}

		public String getFirstStringValue() {

			if (!this.getValues().isEmpty()) {
				// simple attribute - find a String value for it
				for (Object propValue : this.getValues()) {
					if (propValue instanceof String) {
						return (String)propValue;
					}
					else if (propValue instanceof Property) {
						String s = ((Property)propValue).getFirstStringValue();
						if (s!=null)
							return s;
					}
				}
			}
			return null;
		}
	}
	
	protected String id;
	protected String name;
	protected String type;
	protected String description;
	protected List<Property> properties = new ArrayList<Property>();
	protected Resource containingResource;
	protected EObject modelObject;

	public ModelExtensionDescriptor(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public String getDescription() {
		return description;
	}
	
	public List<Property> getProperties() {
		return properties;
	}

	
	/**
	 * Creates a custom Task object from a definition in the currently selected
	 * Target Runtime plugin's "modelExtension" extension point.
	 * 
	 * @param container - the EObject which will eventually contain the new Task.
	 *                    No changes are made to this object, it is only used to
	 *                    locate the EMF Resource which will eventually contain
	 *                    the new Task object.
	 * @return an initialized Task object
	 */
	public EObject createObject(EObject container) {
		if (container!=null)
			containingResource = container.eResource();
		modelObject = createObject(getType());
		populateObject(modelObject);
		return modelObject;
	}
	
	/**
	 * Create and initialize an object of the given EClass name.
	 * The runtime's EPackage is searched first for the given ECLass; if not
	 * found, the Bpmn2Package is searched.
	 * 
	 * @param className
	 * @return an initialized EObject or null if the EClass name was not found
	 */
	private EObject createObject(String className) {
		// look in the extension model package for the class name first
		EClass eClass = (EClass) getEPackage().getEClassifier(className);
		if (eClass==null) {
			// if not found, look in BPMN2 package
			eClass = (EClass) Bpmn2Package.eINSTANCE.getEClassifier(className);
		}
		if (eClass!=null)
			return createObject(eClass);
		return null;
	}

	/**
	 * Create and initialize an object of the given EClass. Initialization consists
	 * of assigning an ID and setting a default name if the EClass has those features.
	 * 
	 * @param eClass - type of object to create
	 * @return an initialized EObject
	 */
	private EObject createObject(EClass eClass) {
		EObject eObject = eClass.getEPackage().getEFactoryInstance().create(eClass);
		
		// if the object has an "id", assign it now.
		String id = ModelUtil.setID(eObject,containingResource);
		// also set a default name
		EStructuralFeature feature = eObject.eClass().getEStructuralFeature("name");
		if (feature!=null) {
			if (id!=null)
				eObject.eSet(feature, ModelUtil.toDisplayName(id));
			else
				eObject.eSet(feature, "New "+ModelUtil.toDisplayName(eObject.eClass().getName()));
		}

		return eObject;
	}
	
	/**
	 * Search the Target Runtime's EPackage for a structural feature with the specified name.
	 * If the feature is not found in the runtime package, search the Bpmn2Package.
	 * 
	 * @param name - name of the feature that specifies both an EClass and an EStructuralFeature
	 *               in the form "EClassName.EStructuralFeatureName"
	 * @return
	 */
	private EStructuralFeature getFeature(String name) {
		String[] parts = name.split("\\.");
		EClass eClass = (EClass)getEPackage().getEClassifier(parts[0]);
		if (eClass==null) {
			eClass = (EClass)Bpmn2Package.eINSTANCE.getEClassifier(parts[0]);
		}
		if (eClass!=null) {
			EStructuralFeature feature = eClass.getEStructuralFeature(parts[1]);
			if (ExtendedMetaData.INSTANCE.getFeatureKind(feature) == ExtendedMetaData.UNSPECIFIED_FEATURE) {
				if (feature instanceof EAttribute) {
					ExtendedMetaData.INSTANCE.setFeatureKind(feature,ExtendedMetaData.ATTRIBUTE_FEATURE);
				}
				else {
					ExtendedMetaData.INSTANCE.setFeatureKind(feature,ExtendedMetaData.ELEMENT_FEATURE);
				}
				ExtendedMetaData.INSTANCE.setNamespace(feature, eClass.getEPackage().getNsURI());
				ExtendedMetaData.INSTANCE.setName(feature, feature.getName());
			}
			
			return feature;
		}
		return null;
	}
	
	/**
	 * Search the Target Runtime's EPackage for a structural feature with the specified name.
	 * If the feature is not found in the runtime package, search the Bpmn2Package.
	 * 
	 * @param type - the feature type, either EAttribute or EReference
	 * @param name - name of the feature
	 * @return an EStructuralFeature or null if not found
	 */
	private EStructuralFeature getFeature(Class type, String name) {
		EStructuralFeature feature = getFeature(getEPackage(), type, name);
		if (feature==null) {
			// try the bpmn2 package
			feature = getFeature(Bpmn2Package.eINSTANCE, type, name);
		}
		return feature;
	}
	
	/**
	 * Search the given EPackage for a structural feature with the specified name.
	 * 
	 * @param pkg - the EPackage to search
	 * @param type - the feature type, either EAttribute or EReference
	 * @param name - name of the feature
	 * @return an EStructuralFeature or null if not found
	 */
	private EStructuralFeature getFeature(EPackage pkg, Class type, String name) {
		TreeIterator<EObject> it = pkg.eAllContents();
		while (it.hasNext()) {
			EObject o = it.next();
			if (type.isInstance(o)) {
				EStructuralFeature fName = o.eClass().getEStructuralFeature("name");
				if (fName!=null && o.eGet(fName)!=null && o.eGet(fName).equals(name)) {
					return (EStructuralFeature)o;
				}
			}
		}
		return null;
	}
	
	/**
	 * Populate the given EObject with a list of values which must be Property objects.
	 * 
	 * @param object - the object to initialize
	 * @param values - list of Property values
	 */
	public void populateObjectFromValues(EObject object, List<Object> values) {
		
		for (Object value : values) {
			if (value instanceof Property)
				populateObject(object,(Property)value);
		}
	}
	
	public EObject populateObject(EObject object, Resource resource) {
		containingResource = resource;
		modelObject = object;
		populateObject(modelObject);
		return modelObject;
	}

	public void populateObject(EObject object) {
		populateObject(object, getProperties());
	}
	
	/**
	 * Populate the given EObject with a list of Property objects.
	 * 
	 * @param object - the object to initialize
	 * @param values - list of Property objects
	 */
	public void populateObject(EObject object, List<Property> properties) {
		
		for (Property prop : properties) {
			populateObject(object,prop);
		}
	}
	
	protected EPackage getEPackage(EStructuralFeature feature) {
		EObject o = feature;
		while ( o.eContainer()!=null ) {
			o = o.eContainer();
			if (o instanceof EPackage) {
				return (EPackage)o;
			}
		}
		return null;
	}
	
	/**
	 * Set the value of the structural feature. If the feature is a list,
	 * the value is added to the list.
	 * 
	 * @param object
	 * @param feature
	 * @param value
	 */
	private void setValue(EObject object, EStructuralFeature feature, Object value) {
		// should not set null value features
		if (value == null) {
			return;
		}
		
		if (feature.isMany()) {
			((EList)object.eGet(feature)).add(value);
		}
		else {
			if (value instanceof String) {
				EDataType eDataType = (EDataType)feature.getEType();
				try {
					// TODO: figure out why feature.eClass().getEPackage().getEFactoryInstance() doesn't
					// return the correct factory!
					EFactory factory = getEPackage(feature).getEFactoryInstance();
					value = factory.createFromString(eDataType, (String)value);
				}
				catch (Exception e)
				{
					EFactory factory = EcorePackage.eINSTANCE.getEFactoryInstance();
					value = factory.createFromString(eDataType, (String)value);
				}
			}
			
			object.eSet(feature, value);
		}
	}
	
	/**
	 * Return the value of the specified feature from the given EObject.
	 * If the feature is a list, return the indexed value.
	 *  
	 * @param object
	 * @param feature
	 * @param index
	 * @return the feature's value
	 */
	private Object getValue(EObject object, EStructuralFeature feature, int index) {
		if (feature.isMany()) {
			return ((EList)object.eGet(feature)).get(index<0 ? 0 : index);
		}
		return object.eGet(feature);
	}
	
	/**
	 * Populate the given EObject from the Property tree defined in this runtime
	 * plugin's "modelObject" extension point.
	 * 
	 * @param object
	 * @param property
	 */
	public void populateObject(EObject object, Property property) {

		EObject childObject = null;
		EStructuralFeature childFeature = null;
		EStructuralFeature feature = object.eClass().getEStructuralFeature(property.name);
		
		Object firstValue = property.getValues().isEmpty() ? null : property.getValues().get(0);
		
		if (feature==null) {
			// determine the type of feature, either an EAttribute or an EReference,
			// from the give Property's characteristics
			Class type = EAttribute.class;
			// if the Property has a "ref" or if its value is a Property
			// then this must be an EReference
			if (property.ref!=null || firstValue instanceof Property)
				type = EReference.class;
			// get the feature from the runtime package or from Bpmn2Package,
			// wherever it's found first
			feature = getFeature(type,property.name);
		}

		if (feature==null) {
			EPackage pkg = getEPackage();
			feature = ModelUtil.createDynamicAttribute(pkg, object, property.name, property.type);
		}
		
		if (feature instanceof EAttribute) {
			if ( feature.getEType().getInstanceClass() == FeatureMap.Entry.class ) {
				// special handling for FeatureMaps
				for (Object value : property.getValues()) {
					if (value instanceof Property) {
						Property p = (Property)value;
						childFeature = getFeature(p.name);
						if (childFeature instanceof EAttribute) {
							childObject = createObject(((EReference) childFeature));
							setValue(childObject, childFeature, firstValue);
						}
						else if (childFeature instanceof EReference) {
							childObject = createObject(((EReference) childFeature).getEReferenceType());
							FeatureMap.Entry entry = new SimpleFeatureMapEntry((Internal)childFeature, childObject);
							setValue(object, feature, entry);
							populateObjectFromValues(childObject,p.getValues());
						}
					}
				}
			}
			else
				setValue(object, feature, firstValue);
		}
		else if (feature instanceof EReference) {
			EReference ref = (EReference)feature;
			if (property.ref!=null) {
				// navigate down the newly created custom task to find the object reference
				childObject = modelObject;
				String[] segments = property.ref.split("/");
				for (String s : segments) {
					// is the feature an Elist?
					int index = s.indexOf('#');
					if (index>0) {
						index = Integer.parseInt(s.substring(index+1));
						s = s.split("#")[0];
					}
					childFeature = childObject.eClass().getEStructuralFeature(s);
					childObject = (EObject)getValue(childObject, childFeature, index);
				}
				setValue(object, feature, childObject);
			}
			else
			{
				childObject = createObject(ref.getEReferenceType());
				setValue(object, feature, childObject);
				populateObjectFromValues(childObject,property.getValues());
			}
		}
	}

	/**
	 * Return the value of the given root Property name.
	 * 
	 * @param name
	 * @return
	 */
	public Object getProperty(String name) {

		for (Property prop : getProperties()) {
			if (prop.name.equals(name)) {
				if (!prop.getValues().isEmpty()) {
					return prop.getValues().get(0);
				}
			}
		}
		return null;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
}
