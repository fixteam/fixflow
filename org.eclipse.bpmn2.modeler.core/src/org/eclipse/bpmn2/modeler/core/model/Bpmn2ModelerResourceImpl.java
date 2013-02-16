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
/**
 * <copyright>
 * 
 * Copyright (c) 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Reiner Hille-Doering (SAP AG) - initial API and implementation and/or initial documentation
 * 
 * </copyright>
 */
package org.eclipse.bpmn2.modeler.core.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.di.BPMNEdge;
import org.eclipse.bpmn2.di.BPMNLabel;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.di.BpmnDiPackage;
import org.eclipse.bpmn2.modeler.core.preferences.Bpmn2Preferences;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.util.Bpmn2ResourceImpl;
import org.eclipse.bpmn2.util.ImportHelper;
import org.eclipse.bpmn2.util.QNameURIHandler;
import org.eclipse.dd.dc.Bounds;
import org.eclipse.dd.dc.DcPackage;
import org.eclipse.dd.dc.Point;
import org.eclipse.dd.di.DiPackage;
import org.eclipse.dd.di.DiagramElement;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.XMLSave;
import org.eclipse.emf.ecore.xmi.impl.XMLLoadImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLSaveImpl;
import org.xml.sax.helpers.DefaultHandler;

/**
 * <!-- begin-user-doc --> The <b>Resource </b> associated with the package.
 * 
 * @implements Bpmn2Resource <!-- end-user-doc -->
 * @see org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl
 */
public class Bpmn2ModelerResourceImpl extends Bpmn2ResourceImpl {

	public static final String BPMN2_CONTENT_TYPE_ID = "org.eclipse.bpmn2.content-type.xml";
	protected BpmnXmlHelper xmlHelper;
	protected QNameURIHandler uriHandler;

	/**
	 * Creates an instance of the resource.
	 * 
	 * @param uri
	 *            the URI of the new resource.
	 */
	public Bpmn2ModelerResourceImpl(URI uri) {
		super(uri);
		
		// overwrite helper and uri handler in options map
		this.xmlHelper = new Bpmn2ModelerXmlHelper(this);
        this.uriHandler = new FragmentQNameURIHandler(xmlHelper);
        this.getDefaultLoadOptions().put(XMLResource.OPTION_URI_HANDLER, uriHandler);
        this.getDefaultLoadOptions().put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, true);
        this.getDefaultSaveOptions().put(XMLResource.OPTION_URI_HANDLER, uriHandler);

        // only necessary if this resource will not be added to a ResourceSet instantly
        this.eAdapters().add(oppositeReferenceAdapter);
	}

    @Override
    protected XMLHelper createXMLHelper() {
        return this.xmlHelper;
    }

	/**
	 * Override this method to hook in our own XmlHandler
	 */
	@Override
	protected XMLLoad createXMLLoad() {
		return new XMLLoadImpl(createXMLHelper()) {
			@Override
			protected DefaultHandler makeDefaultHandler() {
				return new Bpmn2ModelerXmlHandler(resource, helper, options);
			}
		};
	}

	@Override
	protected XMLSave createXMLSave() {
		return new Bpmn2ModelerXMLSave(createXMLHelper()) {
            @Override
            protected boolean shouldSaveFeature(EObject o, EStructuralFeature f) {
                if (o instanceof BPMNShape && f==BpmnDiPackage.eINSTANCE.getBPMNShape_IsHorizontal()) {
                	BPMNShape s = (BPMNShape)o;
                	if (s.getBpmnElement() instanceof Lane || s.getBpmnElement() instanceof Participant)
                		return true;
                }
                return super.shouldSaveFeature(o, f);
            }
		};
	}

	@Override
	protected void prepareSave() {
		EObject cur;
		Definitions thisDefinitions = ImportHelper.getDefinitions(this);
		for (Iterator<EObject> iter = getAllContents(); iter.hasNext();) {
			cur = iter.next();

			setDefaultId(cur);

			for (EObject referenced : cur.eCrossReferences()) {
				setDefaultId(referenced);
				if (thisDefinitions != null) {
					Resource refResource = referenced.eResource();
					if (refResource != null && refResource != this) {
						createImportIfNecessary(thisDefinitions, refResource);
					}
				}
			}
		}
	}

	/**
	 * Set the ID attribute of cur to a generated ID, if it is not already set.
	 * 
	 * @param obj
	 *            The object whose ID should be set.
	 */
	private void setDefaultId(EObject obj) {
		if (obj.eClass() != null) {
			EStructuralFeature idAttr = obj.eClass().getEIDAttribute();
			if (idAttr != null && !obj.eIsSet(idAttr)) {
				ModelUtil.setID(obj);
			}
		}
	}

	/**
	 * We need extend the standard SAXXMLHandler to hook into the handling of
	 * attribute references which may be either simple ID Strings or QNames.
	 * We'll search through all of the objects' IDs first to find the one we're
	 * looking for. If not, we'll try a QName search.
	 */
	protected static class Bpmn2ModelerXmlHandler extends BpmnXmlHandler {

		Bpmn2Preferences prefs = null;
		
		public Bpmn2ModelerXmlHandler(XMLResource xmiResource, XMLHelper helper, Map<?, ?> options) {
			super(xmiResource, helper, options);
		}

		@Override
		protected void handleObjectAttribs(EObject obj) {
			super.handleObjectAttribs(obj);
			if (obj instanceof BPMNShape) {
				BPMNShape bpmnShape = (BPMNShape)obj;

				Hashtable<String,String> map = new Hashtable<String,String>();
				if (attribs != null) {
					for (int i = 0, size = attribs.getLength(); i < size; ++i) {
						String key = attribs.getQName(i);
						String value = attribs.getValue(i);
						map.put(key, value);
					}
					Bpmn2Preferences.getInstance(this.resourceURI).applyBPMNDIDefaults(bpmnShape, map);
				}
			}
		}

		/**
		 * Overridden to be able to convert ID references in attributes to URIs
		 * during load. If the reference can't be found by its ID, we'll try a
		 * QName search (done in the super class)
		 * 
		 * @param ids
		 *            In our case the parameter will contain exactly one ID that
		 *            we resolve to URI.
		 */
		@Override
		protected void setValueFromId(EObject object, EReference eReference, String ids) {

			for (EObject o : objects) {
				TreeIterator<EObject> iter = o.eAllContents();
				while (iter.hasNext()) {
					EObject obj = iter.next();
					EStructuralFeature feature = ((EObject) obj).eClass().getEIDAttribute();
					if (feature != null && obj.eGet(feature) != null) {
						Object id = obj.eGet(feature);
						if (id != null && id.equals(ids)) {
							try {
								object.eSet(eReference, obj);
							} catch (Exception e) {
								continue;
							}
							return;
						}
					}
				}
			}

			// hack to handle QNames and arbitrary strings in structureRefs
			if (eReference.getName().equals("structureRef")) {
				object.eSet(eReference, ModelUtil.createStringWrapper(ids));
				return;
			}

			super.setValueFromId(object, eReference, ids);
		}
	}
	
	public static class Bpmn2ModelerXMLSave extends XMLSaveImpl {
		protected float minX = Float.MAX_VALUE;
		protected float minY = Float.MAX_VALUE;

		public Bpmn2ModelerXMLSave(XMLHelper helper) {
			super(helper);
		}

		@Override
		protected void init(XMLResource resource, Map<?, ?> options) {
			super.init(resource, options);
			featureTable = new Bpmn2ModelerXMLSave.Bpmn2Lookup(map, extendedMetaData, elementHandler);
			
			final List<BPMNDiagram> diagrams = getAll(BPMNDiagram.class, resource);
			for (BPMNDiagram bpmnDiagram : diagrams) {
				findMinXY(bpmnDiagram);
			}
			
		}
		
		protected <T> List<T> getAll(Class<T> class1, Resource resource) {
			ArrayList<T> l = new ArrayList<T>();
			TreeIterator<EObject> contents = resource.getAllContents();
			for (; contents.hasNext();) {
				Object t = contents.next();
				if (class1.isInstance(t)) {
					l.add((T) t);
				}
			}
			return l;
		}
		
		protected void findMinXY(BPMNDiagram bpmnDiagram) {
			EList<DiagramElement> elements = (EList<DiagramElement>) bpmnDiagram.getPlane().getPlaneElement();
			for (DiagramElement e : elements) {
				if (e instanceof BPMNShape) {
					Bounds b = ((BPMNShape)e).getBounds();
					minX = Math.min(minX, b.getX());
					minY = Math.min(minY, b.getY());
				}
				else if (e instanceof BPMNEdge) {
					List<Point> points = ((BPMNEdge)e).getWaypoint();
					for (Point p : points) {
						minX = Math.min(minX, p.getX());
						minY = Math.min(minY, p.getY());
					}

				}
				else if (e instanceof BPMNLabel) {
					Bounds b = ((BPMNLabel)e).getBounds();
					minX = Math.min(minX, b.getX());
					minY = Math.min(minY, b.getY());
				}
			}
		}

		@Override
		protected void saveElement(EObject o, EStructuralFeature f) {
			float oldX = 0, oldY = 0;
			List<Point> oldPoints = null;
			
			if (o instanceof BPMNShape) {
				Bounds b = ((BPMNShape)o).getBounds();
				if (minX<0) {
					oldX = b.getX();
					b.setX(oldX - minX);
				}
				if (minY<0) {
					oldY = b.getY();
					b.setY(oldY - minY);
				}
			}
			else if (o instanceof BPMNEdge) {
				List<Point> points = ((BPMNEdge)o).getWaypoint();
				oldPoints = new ArrayList<Point>();
				oldPoints.addAll(points);
				for (Point p : points) {
					if (minX<0)
						p.setX( p.getX() - minX);
					if (minY<0)
						p.setY( p.getY() - minY);
				}
			}
			else if (o instanceof BPMNLabel) {
				Bounds b = ((BPMNLabel)o).getBounds();
				if (b!=null) {
					if (minX<0) {
						oldX = b.getX();
						b.setX(oldX - minX);
					}
					if (minY<0) {
						oldY = b.getY();
						b.setY(oldY - minY);
					}
				}
			}

			super.saveElement(o, f);
			
			if (o instanceof BPMNShape) {
				Bounds b = ((BPMNShape)o).getBounds();
				if (minX<0) {
					b.setX(oldX);
				}
				if (minY<0) {
					b.setY(oldY);
				}
			}
			else if (o instanceof BPMNEdge) {
				List<Point> points = ((BPMNEdge)o).getWaypoint();
				int index = 0;
				for (Point p : points) {
					if (minX<0)
						p.setX(oldPoints.get(index).getX());
					if (minY<0)
						p.setY(oldPoints.get(index).getY());
					++index;
				}
			}
			else if (o instanceof BPMNLabel) {
				Bounds b = ((BPMNLabel)o).getBounds();
				if (b!=null) {
					if (minX<0) {
						b.setX(oldX);
					}
					if (minY<0) {
						b.setY(oldY);
					}
				}
			}
			
		}

		@Override
		public void traverse(List<? extends EObject> contents) {
			for (EObject e : contents) {
				if (e instanceof Definitions) {
					List<RootElement> roots = ((Definitions) e).getRootElements();
					Process p = null;
					for (RootElement root : roots) {
						if (root instanceof Process) {
							p = (Process) root;
						}
					}
					if (p != null) {
						((Definitions) e).getRootElements().remove(p);
						((Definitions) e).getRootElements().add((RootElement) p);
					}
				}
			}
			super.traverse(contents);
		}

		public static class Bpmn2Lookup extends XMLSaveImpl.Lookup {
			public Bpmn2Lookup(XMLMap map, ExtendedMetaData extendedMetaData, ElementHandler elementHandler) {
				super(map, extendedMetaData, elementHandler);
			}

			@Override
			public EStructuralFeature[] getFeatures(EClass cls) {
				int index = getIndex(cls);
				EClass c = classes[index];

				if (c == cls) {
					return features[index];
				}

				EStructuralFeature[] featureList = listFeatures(cls);
				if (c == null) {
					classes[index] = cls;
					features[index] = featureList;
					featureKinds[index] = listKinds(featureList);
				}

				if (cls.getName().equalsIgnoreCase("Process")) {
					EStructuralFeature[] modifiedFeatureList = getModifiedProcessFeatureSet(featureList);
					if (c == null) {
						classes[index] = cls;
						features[index] = modifiedFeatureList;
						featureKinds[index] = listKinds(modifiedFeatureList);
					}
					return modifiedFeatureList;
				}
				return featureList;
			}
		}

		private static EStructuralFeature[] getModifiedProcessFeatureSet(EStructuralFeature[] processFeatureList) {
			/**
			 * Feature list for Process provided by eclipse.bpmn2: -
			 * extensionDefinitions (0) - id (1) - anyAttribute (2) - name (3) -
			 * definitionalCollaborationRef (4) - isClosed (5) - isExecutable
			 * (6) - processType (7) - extensionValues (8) - documentation (9) -
			 * supportedInterfaceRefs (10) - ioSpecification (11) - ioBinding
			 * (12) - laneSets (13) - flowElements (14) - auditing (15) -
			 * monitoring (16) - properties (17) - artifacts (18) - resources
			 * (19) - correlationSubscriptions (20) - supports (21) Semantic.xsd
			 * sequence definition for Process: <xsd:sequence> <xsd:element
			 * ref="auditing" minOccurs="0" maxOccurs="1"/> <xsd:element
			 * ref="monitoring" minOccurs="0" maxOccurs="1"/> <xsd:element
			 * ref="property" minOccurs="0" maxOccurs="unbounded"/> <xsd:element
			 * ref="laneSet" minOccurs="0" maxOccurs="unbounded"/> <xsd:element
			 * ref="flowElement" minOccurs="0" maxOccurs="unbounded"/>
			 * <xsd:element ref="artifact" minOccurs="0" maxOccurs="unbounded"/>
			 * <xsd:element ref="resourceRole" minOccurs="0"
			 * maxOccurs="unbounded"/> <xsd:element
			 * ref="correlationSubscription" minOccurs="0"
			 * maxOccurs="unbounded"/> <xsd:element name="supports"
			 * type="xsd:QName" minOccurs="0" maxOccurs="unbounded"/>
			 * </xsd:sequence>
			 * 
			 * Moving auditing, monitoring, property above flowElements...
			 */

			EStructuralFeature[] retArray = new EStructuralFeature[processFeatureList.length];
			for (int i = 0; i < 13; i++) {
				retArray[i] = processFeatureList[i];
			}
			retArray[13] = processFeatureList[15]; // auditing
			retArray[14] = processFeatureList[16]; // monitoring
			retArray[15] = processFeatureList[17]; // properties
			retArray[16] = processFeatureList[13]; // lanesets
			retArray[17] = processFeatureList[14]; // flow elements
			retArray[18] = processFeatureList[18]; // artifacts
			retArray[19] = processFeatureList[19]; // resources
			retArray[20] = processFeatureList[20]; // correlationSubscriptions
			retArray[21] = processFeatureList[21]; // supports

			return retArray;
		}
	}
	
	// TODO check this, is this the correct way to deal with this ID prefixes
	/**
	 * QName handler to make create URIs out of the fragment, which is the local part of the QName
	 * 
	 * Most other tools dont understand QNames in referencing attributes
	 * 
	 * @author drobisch
	 *
	 */
	public static class FragmentQNameURIHandler extends QNameURIHandler {

		protected BpmnXmlHelper xmlHelper;

		public FragmentQNameURIHandler(BpmnXmlHelper xmlHelper) {
			super(xmlHelper);
			this.xmlHelper = xmlHelper;
		}

		@Override
		public URI deresolve(URI uri) {
			String fragment = uri.fragment();
			if (fragment != null && !fragment.startsWith("/")) {
				// return just fragment (i.e. without the '#'), always assume
				// local reference
				return URI.createURI(fragment);
			}
			return super.deresolve(uri);
		}

		@Override
		public String convertQNameToUri(String qName) {
			if (qName.contains("#") || qName.contains("/")) {
				// We already have an URI and not QName, e.g. URL
				return qName;
			}

			// Split into prefix and local part (fragment)
			String[] parts = qName.split(":");
			String prefix, fragment;
			if (parts.length == 1) {
				prefix = null;
				fragment = qName;
			} else if (parts.length == 2) {
				prefix = parts[0];
				fragment = parts[1];
			} else
				throw new IllegalArgumentException("Illegal QName: " + qName);

			if (fragment.contains(".")) {
				// HACK: officially IDs can contain ".", but unfortunately
				// XmlHandler calls resolve also for xsi:schemaLocation stuff
				// and similar, that are
				// NO URIs. We must not process them.
				return qName;
			}

			boolean isTargetNamespacePrefix = false;
			try {
				isTargetNamespacePrefix = xmlHelper.isTargetNamespace(prefix);
			} catch (Exception e) {
			}
			if (!isTargetNamespacePrefix)
				return xmlHelper.getPathForPrefix(prefix).appendFragment(fragment).toString();
			else
				return baseURI.appendFragment(fragment).toString();
		}
	}
	
	protected class Bpmn2ModelerXmlHelper extends BpmnXmlHelper {
		
		public Bpmn2ModelerXmlHelper(Bpmn2ResourceImpl resource) {
			super(resource);
		}
		
    	@Override
		public EStructuralFeature getFeature(EClass eClass, String namespaceURI, String name, boolean isElement) {
    		// This fixes https://bugs.eclipse.org/bugs/show_bug.cgi?id=378296
    		// I'm still not convinced that getFeature() shouldn't simply return the feature
    		// from the given EClass instead of searching the EPackage of the Resource being
    		// loaded (if the EClass has a feature with that name of course).
    		EPackage pkg = eClass.getEPackage(); 
			if (pkg != Bpmn2Package.eINSTANCE &&
					pkg != BpmnDiPackage.eINSTANCE &&
					pkg != DcPackage.eINSTANCE &&
					pkg != DiPackage.eINSTANCE) {
				return eClass.getEStructuralFeature(name);
			}
			return super.getFeature(eClass, namespaceURI, name, isElement);
		}
	}
}
