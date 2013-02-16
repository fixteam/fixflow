/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpmn2.modeler.ui.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDAttributeGroupDefinition;
import org.eclipse.xsd.XSDAttributeUse;
import org.eclipse.xsd.XSDAttributeUseCategory;
import org.eclipse.xsd.XSDComplexTypeContent;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDConstraint;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDFacet;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDFeature;
import org.eclipse.xsd.XSDIdentityConstraintDefinition;
import org.eclipse.xsd.XSDImport;
import org.eclipse.xsd.XSDInclude;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDModelGroupDefinition;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDNotationDeclaration;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSchemaCompositor;
import org.eclipse.xsd.XSDSchemaContent;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.XSDWildcard;
import org.eclipse.xsd.XSDXPathDefinition;
import org.eclipse.xsd.util.XSDConstants;
import org.eclipse.xsd.util.XSDUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * Collection of utility methods for dealing with navigation of the XSD model
 */
public class XSDUtils {
	
	// singleton lists of XSD simple type definitions for supported primitives (see getPrimitives()) and
	// all xsd primitives (see getAdvancedPrimitives()) respectively
	private static List<XSDSimpleTypeDefinition> primitives;
	private static List<XSDTypeDefinition> advancedPrimitives;
	
	// XSD short list -- these are the types presented to the user by default, rather than inundating them with
	// all the available types
	private static List<String> xsdShortList = new ArrayList<String>();
	static
	{
		xsdShortList.add("string"); //$NON-NLS-1$
		xsdShortList.add("int"); //$NON-NLS-1$
		xsdShortList.add("double"); //$NON-NLS-1$
		xsdShortList.add("date"); //$NON-NLS-1$
		xsdShortList.add("time"); //$NON-NLS-1$
		xsdShortList.add("dateTime"); //$NON-NLS-1$
		xsdShortList.add("boolean"); //$NON-NLS-1$
		xsdShortList.add("hexBinary"); //$NON-NLS-1$
		xsdShortList.add("float"); //$NON-NLS-1$
	}
	
	
	// A list of all supported XSD types.  Usually the user will not be presented with the full list, but 
	// rather with the xsd short list
	private static List<String> supportedPrimitives = new ArrayList<String>();
	static {
		supportedPrimitives.add("anyType"); //$NON-NLS-1$
		supportedPrimitives.add("anyURI"); //$NON-NLS-1$
		supportedPrimitives.add("base64Binary"); //$NON-NLS-1$
		supportedPrimitives.add("boolean"); //$NON-NLS-1$
		supportedPrimitives.add("byte"); //$NON-NLS-1$
		supportedPrimitives.add("date"); //$NON-NLS-1$
		supportedPrimitives.add("dateTime"); //$NON-NLS-1$
		supportedPrimitives.add("decimal"); //$NON-NLS-1$
		supportedPrimitives.add("double"); //$NON-NLS-1$
		supportedPrimitives.add("duration"); //$NON-NLS-1$
		supportedPrimitives.add("ENTITIES"); //$NON-NLS-1$
		supportedPrimitives.add("ENTITY"); //$NON-NLS-1$
		supportedPrimitives.add("float"); //$NON-NLS-1$
		supportedPrimitives.add("gDay"); //$NON-NLS-1$
		supportedPrimitives.add("gMonth"); //$NON-NLS-1$
		supportedPrimitives.add("gMonthDay"); //$NON-NLS-1$
		supportedPrimitives.add("gYear"); //$NON-NLS-1$
		supportedPrimitives.add("gYearMonth"); //$NON-NLS-1$
		supportedPrimitives.add("hexBinary"); //$NON-NLS-1$
		supportedPrimitives.add("ID"); //$NON-NLS-1$
		supportedPrimitives.add("IDREF"); //$NON-NLS-1$
		supportedPrimitives.add("IDREFS"); //$NON-NLS-1$
		supportedPrimitives.add("int"); //$NON-NLS-1$
		supportedPrimitives.add("integer"); //$NON-NLS-1$
		supportedPrimitives.add("language"); //$NON-NLS-1$
		supportedPrimitives.add("long"); //$NON-NLS-1$
		supportedPrimitives.add("Name"); //$NON-NLS-1$
		supportedPrimitives.add("NCName"); //$NON-NLS-1$
		supportedPrimitives.add("negativeInteger"); //$NON-NLS-1$
		supportedPrimitives.add("NMTOKEN"); //$NON-NLS-1$
		supportedPrimitives.add("NMTOKENS"); //$NON-NLS-1$, "NMTOKENS"); 
		supportedPrimitives.add("nonNegativeInteger"); //$NON-NLS-1$
		supportedPrimitives.add("nonPositiveInteger"); //$NON-NLS-1$
		supportedPrimitives.add("normalizedString"); //$NON-NLS-1$
		supportedPrimitives.add("NOTATION"); //$NON-NLS-1$
		supportedPrimitives.add("positiveInteger"); //$NON-NLS-1$
		supportedPrimitives.add("QName"); //$NON-NLS-1$
		supportedPrimitives.add("short"); //$NON-NLS-1$
		supportedPrimitives.add("string"); //$NON-NLS-1$
		supportedPrimitives.add("time"); //$NON-NLS-1$
		supportedPrimitives.add("token"); //$NON-NLS-1$
		supportedPrimitives.add("unsignedByte"); //$NON-NLS-1$
		supportedPrimitives.add("unsignedInt"); //$NON-NLS-1$
		supportedPrimitives.add("unsignedLong"); //$NON-NLS-1$
		supportedPrimitives.add("unsignedShort"); //$NON-NLS-1$
	}
		
	/**
	 * Add a local annotation with userInfo to the given item.
	 * 
	 * <p>
	 * Note: We take an XSDConcreteComponent, however we must then cast it to
	 * one of the types that has a setAnnotation call defined, since it doesn't
	 * have a clear 'parent' interface for annotations.
	 * </p>
	 * 
	 * <p>
	 * Also note that UserInformation and ApplicationInformation objects can
	 * only be added <b>after </b> the parent of the annotation has been added
	 * to an XSDSchema object. This is because these objects are modeled in the
	 * concrete DOM layer only, and otherwise will throw a DOMException.
	 * <p>
	 * 
	 * @param component
	 *            to add annotation to; may be any kind of XSDConcreteComponent
	 *            object including an XSDSchema
	 * @param text
	 *            text to add as the userInformation (xsd:documentation) node to
	 *            the annotation
	 * @return the XSDAnnotation object created, after having been added to the
	 *         component; null if any error occoured
	 */
	public static XSDAnnotation addDocumentation(XSDConcreteComponent component, String text) {
		if (null == component) { 
			throw new IllegalArgumentException("addDocumentation called with null component"); //$NON-NLS-1$
		}
		try {
			
			// First get the factory from the component: this is
			// roundabout, but saves the user from having to
			// pass it in
			XSDFactory xsdFactory = XSDFactory.eINSTANCE;

			// Create an XSDAnnotation object to hold everything
			XSDAnnotation xsdAnnotation = xsdFactory.createXSDAnnotation();

			// Depending on the XSDConcreteComponent type, cast to
			// the appropriate type and add the annotation or, if component
			// already has an annotation, use that one instead.
			if (component instanceof XSDAttributeDeclaration) {
				if(((XSDAttributeDeclaration) component).getAnnotation() == null)
					((XSDAttributeDeclaration) component).setAnnotation(xsdAnnotation);
				else
					xsdAnnotation = ((XSDAttributeDeclaration) component).getAnnotation();
			} else if (component instanceof XSDAttributeGroupDefinition) {
				if(((XSDAttributeGroupDefinition) component).getAnnotation() == null)
					((XSDAttributeGroupDefinition) component).setAnnotation(xsdAnnotation);
				else
					xsdAnnotation = ((XSDAttributeGroupDefinition) component).getAnnotation();
			} else if (component instanceof XSDElementDeclaration) {
				if(((XSDElementDeclaration) component).getAnnotation() == null)
					((XSDElementDeclaration) component).setAnnotation(xsdAnnotation);
				else
					xsdAnnotation = ((XSDElementDeclaration) component).getAnnotation();
			} else if (component instanceof XSDFacet) {
				if(((XSDFacet) component).getAnnotation() == null)
					((XSDFacet) component).setAnnotation(xsdAnnotation);
				else
					xsdAnnotation = ((XSDFacet) component).getAnnotation();
			} else if (component instanceof XSDIdentityConstraintDefinition) {
				if(((XSDIdentityConstraintDefinition) component).getAnnotation() == null)
					((XSDIdentityConstraintDefinition) component).setAnnotation(xsdAnnotation);
				else
					xsdAnnotation = ((XSDIdentityConstraintDefinition) component).getAnnotation();
			} else if (component instanceof XSDImport) {
				if(((XSDImport) component).getAnnotation() == null)
					((XSDImport) component).setAnnotation(xsdAnnotation);
				else
					xsdAnnotation = ((XSDImport) component).getAnnotation();
			} else if (component instanceof XSDInclude) {
				if(((XSDInclude) component).getAnnotation() == null)
					((XSDInclude) component).setAnnotation(xsdAnnotation);
				else
					xsdAnnotation = ((XSDInclude) component).getAnnotation();
			} else if (component instanceof XSDModelGroup) {
				if(((XSDModelGroup) component).getAnnotation() == null)
					((XSDModelGroup) component).setAnnotation(xsdAnnotation);
				else
					xsdAnnotation = ((XSDModelGroup) component).getAnnotation();
			} else if (component instanceof XSDModelGroupDefinition) {
				if(((XSDModelGroupDefinition) component).getAnnotation() == null)
					((XSDModelGroupDefinition) component).setAnnotation(xsdAnnotation);
				else
					xsdAnnotation = ((XSDModelGroupDefinition) component).getAnnotation();
			} else if (component instanceof XSDNotationDeclaration) {
				if(((XSDNotationDeclaration) component).getAnnotation() == null)
					((XSDNotationDeclaration) component).setAnnotation(xsdAnnotation);
				else
					xsdAnnotation = ((XSDNotationDeclaration) component).getAnnotation();
			} else if (component instanceof XSDTypeDefinition) {
				if(((XSDTypeDefinition) component).getAnnotation() == null)
					((XSDTypeDefinition) component).setAnnotation(xsdAnnotation);
				else
					xsdAnnotation = ((XSDTypeDefinition) component).getAnnotation();
			} else if (component instanceof XSDWildcard) {
				if(((XSDWildcard) component).getAnnotation() == null)
					((XSDWildcard) component).setAnnotation(xsdAnnotation);
				else
					xsdAnnotation = ((XSDWildcard) component).getAnnotation();
			} else if (component instanceof XSDXPathDefinition) {
				if(((XSDXPathDefinition) component).getAnnotation() == null)
					((XSDXPathDefinition) component).setAnnotation(xsdAnnotation);
				else
					xsdAnnotation = ((XSDXPathDefinition) component).getAnnotation();
			} else if (component instanceof XSDSchema) {
				// Note that this adds a global annotation to the
				// schema itself, not to any subcomponent
				((XSDSchema) component).getContents().add(xsdAnnotation);
			} else {
				// Whoops, asked us to annotate an unannotateable item
				throw new IllegalArgumentException("Unable to addDocumentation onto type: " + component); //$NON-NLS-1$
			}

			// Remove any old documentation elements
			for(int i = 0; i < xsdAnnotation.getElement().getChildNodes().getLength(); i++) {
				Node node = xsdAnnotation.getElement().getChildNodes().item(i);
				if(node.getNodeName().endsWith(XSDConstants.DOCUMENTATION_ELEMENT_TAG))
					xsdAnnotation.getElement().removeChild(node);
			}
			xsdAnnotation.getUserInformation().clear();
			
			if (text!=null)
			{
				// Now that the xsdAnnotation is added to a parent
				// XSDConcreteComponent, go ahead and create the
				// UserInformation node (xsd:documentation) and
				// add a DOM textNode to it containing the information
				Element userInfo = xsdAnnotation.createUserInformation(null);
				userInfo.appendChild(userInfo.getOwnerDocument().createTextNode(text));
	
				// Add the finished userInfo object to the concrete
				// parameter of the xsdAnnotation
				xsdAnnotation.getElement().appendChild(userInfo);
				
				// Add the finished userInfo object to the user information
				// list
				xsdAnnotation.getUserInformation().add(userInfo);
			}
			
			return xsdAnnotation;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Adds the text to the component as a Documentation parameter (just like addDocumentation) and then adds the 
	 * Documentation properties to the Documentation parameter if they exist.
	 * If text is null then no documentation is set.
	 * If properties is null then no properties are added to the documentation parameter.
	 * @param component
	 * @param text
	 * @param properties
	 * @return
	 */
	public static XSDAnnotation addDocumentationAndAttributes(XSDConcreteComponent component, String text, Map attributes)
    {
        if (null == component)
        {
            throw new IllegalArgumentException("addDocumentationAndAttributes called with null component"); //$NON-NLS-1$
        }
        
        // nothing to add
        if (text==null && attributes==null)
            return null;
        
        // add documentation
        XSDAnnotation annotation = addDocumentation(component, text);
        
        if (annotation==null)
            return null;
        
        // no properties so return
        if (attributes==null || attributes.isEmpty())
            return annotation;
        
        // get annotation dom parameter, create if necessary
        Element userInfo = null;
        if (annotation.getUserInformation().isEmpty())
        {
            userInfo = annotation.createUserInformation(null);
            annotation.getElement().appendChild(userInfo);
        }
        else
        {
	        for (Iterator<Element> i = annotation.getUserInformation().iterator(); i.hasNext();)
	        {
	            userInfo = i.next();
	            if (userInfo.getTagName().endsWith(XSDConstants.DOCUMENTATION_ELEMENT_TAG))
	            {
	                break;
	            }
	        }
        }
        
        if (userInfo==null)
            return null;
        
        // add properties
        String key = null;
        for (Iterator<String> iter = attributes.keySet().iterator(); iter.hasNext();)
        {
            key = iter.next();
            userInfo.setAttribute(key, (String)attributes.get(key));
        }
        
        return annotation;
    }

		
	/**
	 * Returns a string which is not used by any parameter in 'elements'. The name
	 * is generated by appending numerical suffixes on to 'prefix' until a
	 * unique one is found. PRECONDITION: 'elements' is a list of
	 * XSDNamedComponent instances
	 * 
	 * @param prefix
	 * @param elements
	 * @return
	 */
	public static String createUniqueElementName(String prefix, List elements) {
		ArrayList<String> usedNames = new ArrayList<String>();
		for(Iterator i = elements.iterator(); i.hasNext(); ) {
			usedNames.add( getDisplayName((XSDNamedComponent) i.next()));
		}
		
		int i = 1;
		String testName = prefix + i++;
		while(usedNames.contains(testName)) {
			testName = prefix + i++;
		}
		return testName;
	}

	/**
	 * Like getPrimitives(), this returns a list of XSDTypeDefinitions.  However where getPrimitives() 
	 * returns the basic set supported by the editor, getAdvancedPrimitives returns every known XSD
	 * primitive type.
	 * @return
	 */
	public static List<XSDTypeDefinition> getAdvancedPrimitives() {
		advancedPrimitives = null;
		if(advancedPrimitives == null) {
			advancedPrimitives = new ArrayList<XSDTypeDefinition>();
		
			// Get the schema for schemas instance to use when resolving primitives
			XSDSchema schemaForSchemas = XSDUtil.getSchemaForSchema(XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001);
			
			// Start adding the simple types using the supportedPrimitives list
			for (String typeName : supportedPrimitives) {
				XSDTypeDefinition type = schemaForSchemas.resolveSimpleTypeDefinition(typeName);
				advancedPrimitives.add(type);
			}

			// Return primitives in alpha order
			Collections.sort(advancedPrimitives, new Comparator() {

				public int compare(Object o1, Object o2) {
					if(o1 == null || o2 == null || ((XSDTypeDefinition) o1).getName() == null)
						return 0;
					return ((XSDTypeDefinition) o1).getName().compareToIgnoreCase(((XSDTypeDefinition) o2).getName());
				}
			});
		}
		return advancedPrimitives;		
	}
	
	/**
	 * Retrieves all the root Data types defined in the schema including complex types, user-defined simple types and anonymous
	 * complex types.  If there's an anonymous complex type definition (from a root parameter declaration) then we return
	 * the parameter declaration's anonymous type.
	 * @param schema
	 * @return
	 */
	public static List<XSDTypeDefinition> getAllDataTypes(XSDSchema schema)
	{
		if (schema==null)
			return Collections.emptyList();
		
		List<XSDTypeDefinition> bos = new ArrayList<XSDTypeDefinition>();
		
        EList<XSDSchemaContent> contents = schema.getContents();
        // First try the easy approach -- if this XSD contains a type definition, that's our BO, 
        // return it.  This is the recommended path, and the way our tooling does things.
        for (XSDSchemaContent item : contents) {
			if (item instanceof XSDTypeDefinition)
				bos.add((XSDTypeDefinition)item);
		}
        
		// If we failed, we try a second pass, this time looking for an parameter
		// with an anonymous complex
		// type defined in line
        for (XSDSchemaContent item : contents) {
			if (item instanceof XSDElementDeclaration)
			{
				XSDElementDeclaration element = (XSDElementDeclaration) item;
				if (element.getAnonymousTypeDefinition() instanceof XSDComplexTypeDefinition)
				{
					bos.add(element.getAnonymousTypeDefinition());
				}
			}
		}
		return bos;
	}
	
	/**
	 * Given a BO (XSD Complex Type), return a list of the properties 
	 * within the complexType.
	 * @param bo
	 * @return List of XSDAttributeDeclaration
	 */
	public static List<XSDAttributeDeclaration> getChildAttributes(XSDComplexTypeDefinition bo)
	{
		EList attrContents = bo.getAttributeContents();
		List<XSDAttributeDeclaration> attrs = new ArrayList<XSDAttributeDeclaration>();
		for (int i=0; i< attrContents.size(); i++)
		{
			Object next = attrContents.get(i);
			
			// Attribute contents may include actual attribute delcarations (wrapped in XSDAttributeUses) or
			// attribute group definitions, containing bundles of properties
			if(next instanceof XSDAttributeUse) {
				attrs.add( ((XSDAttributeUse) next).getContent().getResolvedAttributeDeclaration() );
				
			} else if (next instanceof XSDAttributeGroupDefinition) {
				
				// Add these properties to the end of attrContents to be processed in turn
				XSDAttributeGroupDefinition attrGroup = (XSDAttributeGroupDefinition) next;
				if(attrGroup.getResolvedAttributeGroupDefinition() != null)
					attrContents.addAll(attrGroup.getResolvedAttributeGroupDefinition().getAttributeUses());
				
			}
		}
		return attrs;
	}
	
	/**
	 * Given a BO (XSD Complex Type), return a list of the XSDFeatures 
	 * within the complexType's modelgroup (sequence, choice, etc.)
	 * @param bo
	 * @return
	 */
	public static List<XSDFeature> getChildElements(XSDComplexTypeDefinition bo) 
	{
   		return XSDUtils.getChildElements( getModelGroup(bo) );
	}

	/**
	 * Given an XSDFeature, return a list of the XSDFeatures 
	 * within the complexType's modelgroup (sequence, choice, etc.)
	 * @param bo
	 * @return
	 */
	public static List<XSDFeature> getChildElements(XSDFeature elem) 
	{
		XSDComplexTypeDefinition cType = getResolvedComplexType(elem);
		return (cType != null) ? XSDUtils.getChildElements(cType) : Collections.EMPTY_LIST;
	}
	
	
	/**
	 * Given a Model group, return a list of the XSDFeatures 
	 * declared within.
	 * @param group
	 * @return 
	 */
	public static List<XSDFeature> getChildElements(XSDModelGroup group) 
	{
    	if(group == null)
    		return new ArrayList<XSDFeature>();
    	
		List<XSDFeature> children = new ArrayList<XSDFeature>();
    	for(Iterator<XSDParticle> i = group.getContents().iterator(); i.hasNext();) 
    	{
    		XSDParticle next = i.next();
    		if(next.getContent() instanceof XSDFeature)
    			children.add((XSDFeature) next.getContent());
    		else if (next.getTerm() instanceof XSDModelGroup)
    			children.addAll(getChildElements((XSDModelGroup) next.getTerm()));
    	}
    	return children;
	}
	
	/**
	 * Return the contents of the documentation parameter in type's annotation, if it has one.  
	 * Otherwise return null
	 * @param type
	 * @return
	 */
	public static String getDocumentation(XSDFeature element) {
		XSDAnnotation annotation = null;
	
		if (element instanceof XSDAttributeDeclaration) 
			annotation = ((XSDAttributeDeclaration)element).getAnnotation();
		else if (element instanceof XSDElementDeclaration)
			annotation = ((XSDElementDeclaration)element).getAnnotation();
		
		if(annotation != null) {
			for(Iterator i = annotation.getUserInformation().iterator(); i.hasNext(); ) {
				Element domElement = (Element) i.next();
				if(domElement.getTagName().endsWith(XSDConstants.DOCUMENTATION_ELEMENT_TAG) && 
						   domElement.getFirstChild() instanceof Text) {
					return ((Text) domElement.getFirstChild()).getData();
				}
			}
		}
		return null;
	}
	
	/**
	 * Returns the properties on the documentation parameter if they exist.  I.e. returns source and xml:lang
	 * Returns an empty parentMap if none.
	 * @param type
	 * @return
	 */
	public static Map<String, String> getDocumentationAttributes(XSDTypeDefinition type)
    {
		Map<String, String> attributes = new HashMap<String, String>();

        if (type.getAnnotation() != null)
        {
            XSDAnnotation annotation = type.getAnnotation();
            for (Iterator i = annotation.getUserInformation().iterator(); i
                    .hasNext();)
            {
                Element element = (Element) i.next();
                if (element.getTagName().endsWith(XSDConstants.DOCUMENTATION_ELEMENT_TAG))
                {
                    String source = element.getAttribute("source"); //$NON-NLS-1$
                    String lang = element.getAttribute("xml:lang"); //$NON-NLS-1$
                    if (lang==null || lang.length()<1)
                        lang = element.getAttribute("lang"); //$NON-NLS-1$
                    
                    if (source!=null && source.length()>0)
                        attributes.put("source", source); //$NON-NLS-1$
                    
                    if (lang!=null && lang.length()>0)
                        attributes.put("xml:lang", lang); //$NON-NLS-1$
                    
                    return attributes;
                }
            }
        }
        return null;
    }

	/**
	 * Returns the properties on the documentation parameter if they exist.  I.e. returns source and xml:lang
	 * Returns an empty parentMap if none.
	 * @param parameter
	 * @return
	 */
	public static Map<String, String> getDocumentationAttributes(XSDFeature element)
	{
		XSDAnnotation annotation = null;
		Map<String, String> attributes = new HashMap<String, String>();
		
		if (element instanceof XSDAttributeDeclaration) 
			annotation = ((XSDAttributeDeclaration)element).getAnnotation();
		else if (element instanceof XSDElementDeclaration)
			annotation = ((XSDElementDeclaration)element).getAnnotation();
		
		if (annotation != null)
        {
            for (Iterator i = annotation.getUserInformation().iterator(); i
                    .hasNext();)
            {
                Element domElement = (Element) i.next();
                if (domElement.getTagName().endsWith(XSDConstants.DOCUMENTATION_ELEMENT_TAG))
                {
                    String source = domElement.getAttribute("source"); //$NON-NLS-1$
                    String lang = domElement.getAttribute("xml:lang"); //$NON-NLS-1$
                    if (lang==null || lang.length()<1)
                        lang = domElement.getAttribute("lang"); //$NON-NLS-1$
                    
                    if (source!=null && source.length()>0)
                        attributes.put("source", source); //$NON-NLS-1$
                    
                    if (lang!=null && lang.length()>0)
                        attributes.put("xml:lang", lang); //$NON-NLS-1$
                    
                    return attributes;
                }
            }
        }
		return null;
	}

	/**
	 * Return the contents of the documentation parameter in type's annotation, if it has one.  
	 * Otherwise return null
	 * @param type
	 * @return
	 */
	public static String getDocumentation(XSDTypeDefinition type) {
		if(type.getAnnotation() != null) {
			XSDAnnotation annotation = type.getAnnotation();
			for(Iterator i = annotation.getUserInformation().iterator(); i.hasNext(); ) {
				Element element = (Element) i.next();
				if(element.getTagName().endsWith(XSDConstants.DOCUMENTATION_ELEMENT_TAG) && 
				   element.getFirstChild() instanceof Text) {
					return ((Text) element.getFirstChild()).getData();
				}
			}
		}
		return null;
	}

	/**
	 * Given an XSD Complex Type Definition, return the model group containing
	 * its child elements. 
	 * @param parameter
	 * @return
	 */
	public static XSDModelGroup getModelGroup(XSDComplexTypeDefinition cType) 
	{
		XSDParticle particle = cType.getComplexType();
		
		// In cases where cType doesn't have a model group AND cType has a parent with a modelgroup, the
		// call above will rather unexpectedly give us cType's PARENT's model group, rather than the null we 
		// might expect.  We don't want that here, if the model group returned is null or belongs to someone
		// other than us, return null
		if (particle==null || particle.eContainer() != cType) {
			return null;
		}
		
		// get the model group
		Object particleContent = particle.getContent();
		XSDModelGroup group = null;
		
		if (particleContent instanceof XSDModelGroupDefinition) {
		    group = ((XSDModelGroupDefinition)particleContent).getResolvedModelGroupDefinition().getModelGroup();
		} else if (particleContent instanceof XSDModelGroup) {
		    group = (XSDModelGroup)particleContent;
		}
		
		if (group == null) {					
		    return null;
		}

		// if the content of the complex type is empty then the content
		// must be in the complexContent, ie. we're extending another BO.
		// if the group and the type are not in the same resource then
		// we are extending another BO and we don't want to show inherited
		// properties.
		if ( group.getContents().isEmpty() || group.eResource() != cType.eResource())
		{
			// if we are extending another BO then get the elements
			// we are adding
			if (cType.getBaseType()!=null)
			{
				XSDComplexTypeContent content = cType.getContent();
				
				if (content instanceof XSDParticle) {
					particleContent = ((XSDParticle)content).getContent();
					if (particleContent instanceof XSDModelGroupDefinition) {
					    group = ((XSDModelGroupDefinition)particleContent).getResolvedModelGroupDefinition().getModelGroup();
					} else if (particleContent instanceof XSDModelGroup) {
					    group = (XSDModelGroup)particleContent;
					}								
				}
				
			}
		}		

		return group;
	}

	/**
	 * General utility method for finding a name for an xsd component.  This method will handle
	 * mapping of xsd primitive names to their human readable counterparts, as well as resolving
	 * parameter references for properties and walking up object hierarchies for anonymous types.  
	 * Basically this method should be used whenever a name is needed to minimize the risk of 
	 * having a name of 'null' -- though this method WILL return null if no name can be found.
	 * @param component
	 * @return
	 */
	public static String getDisplayName(XSDNamedComponent component) {
		
		if(component == null)
			return null;
		
		if(component instanceof XSDTypeDefinition)
			return getDisplayNameFromXSDType((XSDTypeDefinition) component);
		
		if (component instanceof XSDFeature) {
			XSDFeature feature = (XSDFeature) component;
			if(feature.getName() != null)
				return feature.getName();
			else if (feature.getResolvedFeature() != null && feature.getResolvedFeature().getName() != null)
				return feature.getResolvedFeature().getName();
		}
		
		return component.getName();
		
	}
	
	/**
	 * Try a variety of methods to get a human readable name for type.  In order, this method will
	 * - check whether type is null, and if so return null
	 * - check whether type is a restriction of a primitive type, if so return its parent's name
	 * - check whether type is a complex anonymous (un-named) inner type of a named parameter, and if so, return the parameter's name  
	 * - check whether type is a primitive type, and if so return a human-readable version of that type
	 * - check whether type is a named, non-primitive type, and if so, return its name
	 * @param xsdType
	 * @return
	 */
	public static String getDisplayNameFromXSDType(XSDTypeDefinition type) {
		return getDisplayNameFromXSDType(type, true);
	}
		
	/**
	 * Try a variety of methods to get a human readable name for type.  In order, this method will
	 * - check whether type is null, and if so return null
	 * - check whether type is a complex anonymous (un-named) inner type of a named parameter, and if so, return the parameter's name  
	 * - check whether type is a primitive type, and if so return a human-readable version of that type
	 * - check whether type is a named, non-primitive type, and if so, return its name
	 * - if returnPrimitiveParents is true, check whether type is a restriction of a primitive type, 
	 * if so return its parent's name
	 * @param xsdType
	 * @param returnPrimitiveParents if true, and if type is an anonymous restriction of an xsd primitive 
	 * type, this method will return the name of the parent primitive type.  If false, restrictions of 
	 * primitive types will not be treated differently from other types, and their container hierarchy will
	 * be walked, instead of their inheritance hierarchy.  
	 * @return
	 */
	public static String getDisplayNameFromXSDType(XSDTypeDefinition type, boolean returnPrimitiveParents) {		
		if(type == null)
			return null;
		
		
		
		// Does type have a name?  If not, walk up the container tree to try and find one
		if(type.getName() == null || type.getName().length() == 0) {
			
			// In the special case where type is a restriction on a primitive type, just return the parent's
			// name (which will either be a primitive itself, or a named simple type)
			if(returnPrimitiveParents && isRestrictedPrimitiveType(type)) {
				return getDisplayNameFromXSDType(type.getBaseType());
			}
			
			EObject container = type.eContainer();

			while(container != null) {
				if(container instanceof XSDNamedComponent && ((XSDNamedComponent) container).getName() != null) {
					return ((XSDNamedComponent) container).getName();
				}
				container = container.eContainer();
			}
			// Type doesn't have a name, or a container with a name, nothing useful
			return null;
		} else
			return type.getName();
	}
	
	/**
	 * Return the type definition for the primitive with name xsdName (note, this is not the human-readable 
	 * name, but the actual XSD type name.)  Return null if a type with this name is not in the list of 
	 * all primitives
	 * @param xsdName
	 * @return
	 */
	public static XSDSimpleTypeDefinition getPrimitive(String xsdName) {
		for(Iterator<XSDTypeDefinition> i = getAdvancedPrimitives().iterator(); i.hasNext();) {
			XSDSimpleTypeDefinition next = (XSDSimpleTypeDefinition) i.next();
			if(next.getName().equals(xsdName)) {
				return next; 
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @return Returns a list of XSDSimpleTypeDefinitions representing each of the supported primitives.
	 * These will have their XSD spec names (e.g. xsd:dateTime) so they will likely need to be fed to 
	 * getDisplayName() if they are going to be presented to humans 
	 */
	public static List<XSDSimpleTypeDefinition> getPrimitives() {
		if(primitives == null) {
			primitives = new ArrayList<XSDSimpleTypeDefinition>();
		
			// Get the schema for schemas instance to use when resolving primitives
			XSDSchema schemaForSchemas = XSDUtil.getSchemaForSchema(XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001);
			
			// Start adding the simple types from the XSD short list
			for(Iterator<String> i = xsdShortList.iterator(); i.hasNext(); ) {
				String typeName = i.next();
				
				XSDSimpleTypeDefinition type = schemaForSchemas.resolveSimpleTypeDefinition(typeName);
				primitives.add(type);
			}

			// Return primitives in alpha order
			Collections.sort(primitives, new Comparator() {

				public int compare(Object o1, Object o2) {
					if(o1 == null || o2 == null || getDisplayNameFromXSDType((XSDTypeDefinition) o1) == null)
						return 0;
					return getDisplayNameFromXSDType((XSDTypeDefinition) o1).compareTo(getDisplayNameFromXSDType((XSDTypeDefinition) o2));
				}
			});
		}
		return primitives;
	}
	
	/**
	 * Return a collection of all types referenced by the properties (XSDFeatures) of source.  This
	 * method is non-recursive (i.e. the list only contains direct references, not references-of-references)
	 * Will not return null, but may return an empty set.  This will not return a BO reference if the file
	 * has been deleted (or just doesn't exist).  
	 * @param source  The complex type to examine for references
	 * @return a Collection of XSDComplexTypeDefinition instances -- no duplicates
	 */
	public static Collection<XSDComplexTypeDefinition> getReferencedTypes(XSDComplexTypeDefinition source) {
		if(source == null)
			return Collections.emptySet();
		
		List<XSDComplexTypeDefinition> results = new ArrayList<XSDComplexTypeDefinition>();
		for(Iterator<XSDFeature> i = getChildElements(source).iterator(); i.hasNext(); ) {
			XSDFeature element = i.next();
			XSDComplexTypeDefinition elementType = getResolvedComplexType(element); 
			if(elementType != null && !results.contains(elementType) && !XSDConstants.isSchemaForSchemaNamespace(elementType.getTargetNamespace()))
				results.add(elementType);
		}
		return results;
	}

	/**
	 * Return a collection of all types referenced by the properties (XSDFeatures) of source.  This
	 * method is non-recursive (i.e. the list only contains direct references, not references-of-references)
	 * Will not return null, but may return an empty set.  This will return a BO reference if the file
	 * has been deleted (or just doesn't exist).  
	 * @param source  The complex type to examine for references
	 * @return a Collection of XSDTypeDefinition (could be complex or simple type) instances -- no duplicates
	 */
	public static Collection<XSDTypeDefinition> getAllReferencedTypes(XSDComplexTypeDefinition source)
	{
		return getAllReferencedTypes(source, false);
	}

	/**
	 * Return a collection of all types referenced by the properties (XSDFeatures) of source.  This
	 * method is non-recursive (i.e. the list only contains direct references, not references-of-references)
	 * Will not return null, but may return an empty set.  This will return a BO reference if the file
	 * has been deleted (or just doesn't exist).  
	 * @param source  The complex type to examine for references
	 * @param includeAnonymous if true, the returned list will include anonymous inlined types as well.  These
	 * are not technically "referenced", however it allows this method to be used as a way to get all non-primitive
	 * types used in any way by source 
	 * @return a Collection of XSDTypeDefinition (could be complex or simple type) instances -- no duplicates
	 */
	public static Collection<XSDTypeDefinition> getAllReferencedTypes(XSDComplexTypeDefinition source, boolean includeAnonymous)
	{
		if (source == null)
			return Collections.emptySet();

		List<XSDTypeDefinition> results = new ArrayList<XSDTypeDefinition>();
		XSDTypeDefinition elementType = null;
		for (Iterator<XSDFeature> i = getChildElements(source).iterator(); i.hasNext();)
		{
			XSDFeature next = i.next();
			elementType = getResolvedType(next);
			
			// Only add non-null, non-duplicate, non-primitive types.  If includeAnonymous is false, 
			// anonymous types should be filtered out as well
			if(	elementType != null && 
				!results.contains(elementType) && 
				!XSDConstants.isSchemaForSchemaNamespace(elementType.getTargetNamespace()) &&
				(includeAnonymous || elementType.eContainer() != next) )
				results.add(elementType);
		}
		return results;
	}

	/**
	 * Given an parameter, return its complex type, or null if it does not have a complex type.  This is 
	 * slightly more complicated than just calling getType() since an parameter may not have a type at all, it
	 * may reference another parameter.
	 * @param parameter
	 * @return
	 */
	public static XSDComplexTypeDefinition getResolvedComplexType(XSDFeature feature) {
		// The contents of this method have been adapted to the more general getResolvedType,
		// but this method is maintained for compatibility and convenience 
		XSDTypeDefinition resolvedType = getResolvedType(feature);
		if(resolvedType instanceof XSDComplexTypeDefinition)
			return (XSDComplexTypeDefinition) resolvedType;
		return null;
	}

	/**
	 * Given an parameter, return its type, or null if it does not have a type.  This is 
	 * slightly more complicated than just calling getType() since an parameter may not have 
	 * a type at all, it may reference another parameter.
	 * @param parameter
	 * @return
	 */
	public static XSDTypeDefinition getResolvedType(XSDFeature feature) {
						
		// Special case of elements referencing stale XSD complex types
		if (feature instanceof XSDElementDeclaration && ((XSDElementDeclaration) feature).getTypeDefinition() instanceof XSDComplexTypeDefinition) {
			
			XSDElementDeclaration element = (XSDElementDeclaration) feature;

			// We have a type, but types can be proxies, and proxies can become
			// stale if the referenced
			// type changes, so before we return it, re-resolve the proxy and
			// then return it
			XSDComplexTypeDefinition oldType = (XSDComplexTypeDefinition) element.getTypeDefinition();
			EObject newType = EcoreUtil.resolve(element.getTypeDefinition(), element);
			if (oldType != newType) {
			    // We only return the resolved type if the name and the namespace has not changed.  Changing the name
			    // and namespace is essentially an unresolved BO.
			    String oldName = oldType.getName();
			    String newName = ((XSDTypeDefinition)newType).getName();
			    String oldTNS = oldType.getTargetNamespace();
			    String newTNS = ((XSDTypeDefinition)newType).getTargetNamespace();
			    
			    if ( ((oldName==newName) || (oldName!=null && oldName.equals(newName))) &&
			          ((oldTNS==newTNS) || (oldTNS!=null && oldTNS.equals(newTNS)))  )
			        element.setTypeDefinition((XSDTypeDefinition) newType);
			}
			return element.getTypeDefinition();

		} else if (feature.getType() != null) {
			return feature.getType();
		} else if (feature.getResolvedFeature() != null && feature.getResolvedFeature().getType() != null) {
			// We reference another parameter
			return feature.getResolvedFeature().getType();
		} else {
			return null;
		}
	}
	
	/**
	 * Return the base type from which this type inherits - that is, all xsd types are
	 * either xsd:anyType or xsd:anySimpleType at the topmost level of inheritance, so return the second
	 * topmost level of type's inheritance.  The first specific type from which type inherits. 
	 * @param type
	 * @return
	 */
	public static XSDTypeDefinition getRootType(XSDTypeDefinition type) {
		if(type == null)
			return null;
		
		XSDTypeDefinition baseType = type.getBaseType();
		while(baseType != null && !XSDConstants.isAnySimpleType(baseType) && !XSDConstants.isAnyType(baseType)) {
			// walk one more step up the hierarchy
			type = baseType;
			baseType = type.getBaseType();
		}
		
		// Since baseType, type's immediate parent, broke the while condition, we know that type is now
		// as high up the tree as we want to be
		return type;
		
		
		
	}
	
	/**
	 * Given a schema, return the list of XSDSimpleTypeDefinitions found within it.  Will not return null,
	 * but may return an empty list
	 * @param schema
	 * @return
	 */
	public static List<XSDSimpleTypeDefinition> getUserDefinedSimpleTypes(XSDSchema schema) {
		if(schema == null)
			return Collections.emptyList();
		
		List<XSDSimpleTypeDefinition> result = new ArrayList<XSDSimpleTypeDefinition>();
		for (XSDSchemaContent next : schema.getContents()) {
			if(next instanceof XSDSimpleTypeDefinition)
				result.add((XSDSimpleTypeDefinition)next);
		}
		return result;
	}
	
	/**
	 * Given a display name for a BO attribute type, return the XSD type name.
	 * If the given input is not a display name, the input is returned.  
	 * @param displayName
	 * @return
	 */
	public static String getXSDTypeFromDisplayName(String displayName)
	{
		// Now a no-op since we no longer translate XSD type names to "human readable" ones
		return displayName;
	}
	
	/**
	 * Return true iff type is a descendant of a primitive xsd type.  Will not return true for primitives
	 * themselves.
	 * @param type
	 * @return
	 */
	public static boolean isRestrictedPrimitiveType(XSDTypeDefinition type) {
		if(type instanceof XSDComplexTypeDefinition)
			return false;
		
		XSDTypeDefinition baseType = getRootType(type);
		return getAdvancedPrimitives().contains(baseType);
	}
	
	/**
	 * Gets the "minOccurs" attribute value for the given XSDFeature, if
	 * there is none then it returns the default 1.
	 * @param xsdElem
	 * @return
	 */
	public static int getMinOccurs(XSDFeature xsdElem)
	{
		if (xsdElem.eContainer() instanceof XSDAttributeUse)
		{
			return (((XSDAttributeUse)xsdElem.eContainer()).getUse()==XSDAttributeUseCategory.REQUIRED_LITERAL?1:0);
		}
		
		XSDParticle particle = (XSDParticle)xsdElem.eContainer();
		int min = 1;
		if (particle.isSetMinOccurs())
			min = particle.getMinOccurs();
		
		return min;
	}
	
	/**
	 * Gets the "maxOccurs" attribute value for the given XSDFeature, if
	 * there is none then it returns the default 1.
	 * @param xsdElem
	 * @return
	 */
	public static int getMaxOccurs(XSDFeature xsdElem)
	{
		int max = 1;
		
		// not a particle means an attribute use.  properties are maxed at 1.
		if ( !(xsdElem.eContainer() instanceof XSDParticle) )
			return max;
			
		XSDParticle particle = (XSDParticle)xsdElem.eContainer();
		if (particle.isSetMaxOccurs())
			max = particle.getMaxOccurs();
		
		return max;
	}
	
	/**
	 * Return the Data Type from the given schema with the given name.  
	 * @param schema - The schema containing the type.
	 * @param boName - The local name of the type to retrieve -- if null method returns first type found.
	 * @return The XSDTypeDefinition if one exists, null otherwise
	 */
	public static XSDTypeDefinition getDataType(XSDSchema schema, String typeName) {
		if(schema == null)
			return null;
		
		boolean pickFirstFound = (typeName==null || typeName.length()<1);
		
        EList<XSDSchemaContent> contents = schema.getContents();
        
        // First try the easy approach -- if this XSD contains a type definition with the right name, that's 
        // our type, return it.  This is the recommended path, and the way our tooling does things.
        for(Iterator<XSDSchemaContent> i = contents.iterator(); i.hasNext(); ) {
        	Object item = i.next();
        	if(item instanceof XSDTypeDefinition)
        		if (pickFirstFound)
        			return (XSDTypeDefinition) item;
        		else if ( typeName.equals(getDisplayName((XSDTypeDefinition)item)))
        			return (XSDTypeDefinition) item;
        }
        
        // If we failed, we try a second pass, this time looking for an parameter with an anonymous 
        // type defined in line
        for(Iterator<XSDSchemaContent> i = contents.iterator(); i.hasNext(); ) {
        	Object item = i.next();
        	if(item instanceof XSDFeature) {
        		XSDFeature element = (XSDFeature) item;
        		XSDTypeDefinition resolvedType = getResolvedType(element); 
        		if(resolvedType != null && resolvedType.getSchema() == schema) {
        			if (pickFirstFound)
        				return resolvedType;
        			else if (typeName.equals(getDisplayName(element)))
        				return resolvedType;
        		}        		
        	}
        }        
        return null;
		
	}

	/**
	 * Gets the "default" attribute value for the given XSDFeature, if there
	 * is none then it returns an empty string.
	 * @param xsdElem
	 * @return
	 */
	public static String getDefaultValue(XSDFeature xsdElem)
	{
		XSDConstraint constraint = null;
		if (xsdElem instanceof XSDAttributeDeclaration)
		{
			// attribute declarations store their default values in
			// their containers (attribute uses)
			XSDAttributeUse use = (XSDAttributeUse)xsdElem.getContainer();
			if (use.isSetConstraint())
				constraint = use.getConstraint();
			
			if (constraint!=null && constraint.equals(XSDConstraint.DEFAULT_LITERAL))
				if (use.getLexicalValue()!=null)
					return use.getLexicalValue();
				else
					return ""; //$NON-NLS-1$
		}
		else if (xsdElem instanceof XSDElementDeclaration)
		{
			if (xsdElem.isSetConstraint())
				constraint = xsdElem.getConstraint();
			
			if (constraint!=null && constraint.equals(XSDConstraint.DEFAULT_LITERAL))
				if (xsdElem.getLexicalValue()!=null)
					return xsdElem.getLexicalValue();
				else
					return ""; //$NON-NLS-1$
			
		}
		return ""; //$NON-NLS-1$
	}
	
	/**
	 * Return the enclosing Complex Type definition.
	 * @param component
	 * @return
	 */
	public static XSDComplexTypeDefinition getEnclosingTypeDefinition(EObject component)
	{
		if (component == null)
			return null;
		
		if (component instanceof XSDComplexTypeDefinition)
			return (XSDComplexTypeDefinition)component;

		return getEnclosingTypeDefinition(component.eContainer());
	}
	
	/**
	 * Given an XSD schema with a new target namespace set, this method will cycle through the XSD schema directives
	 * (i.e. imports/includes/redefines) and remove as appropriate.
	 * 
	 * I.e. this method will remove all includes/redefines if the new TNS is different than the old TNS and it will remove
	 * all imports that have the same namespace as the new TNS (since these should now be includes).
	 * 
	 * @param schema - has the new target namespace set
	 * @param oldTNS
	 */
	public static void removeImportsAndIncludes(XSDSchema schema, String oldTNS)
	{
	    if (schema==null)
	        return;
	    
	    String newTNS = schema.getTargetNamespace();
	    
	    // namespace hasn't changed
	    if (newTNS==null && oldTNS==null)
	        return;
	    
	    // namespace hasn't changed
	    if (newTNS!=null && newTNS.equals(oldTNS))
	        return;
	    
	    // namespace has changed so remove all includes and remove any imports to the new namespace
		ArrayList schemaContents = new ArrayList(schema.getContents());
		for (Iterator i = schemaContents.iterator(); i.hasNext();)
        {
            Object next = i.next();
            if (next instanceof XSDImport)
            {
                XSDImport nextImport = (XSDImport) next;
                if (nextImport.getNamespace()==null && newTNS==null)
                    schema.getContents().remove(nextImport);
                else if (nextImport.getNamespace()!=null && nextImport.getNamespace().equals(newTNS))
                    schema.getContents().remove(nextImport);
            }
            else if (next instanceof XSDSchemaCompositor)
            {
                schema.getContents().remove(next);
            }
        }
		
	}
	
	/**
	 * Given an XSD complex type, return a list of the XSDFeatures (parameter
	 * and attribute declarations) within the complex type.
	 */
	public static List<XSDFeature> getXSDElementsAndAttributes(XSDComplexTypeDefinition complexType)
	{
		List<XSDFeature> result = getChildElements(complexType);
		result.addAll( getChildAttributes(complexType));
		
		return result;
	}	
}
