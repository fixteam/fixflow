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
package org.eclipse.bpmn2.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Extension;
import org.eclipse.bpmn2.Import;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.XMLSave;
import org.eclipse.emf.ecore.xmi.impl.SAXXMLHandler;
import org.eclipse.emf.ecore.xmi.impl.XMLHelperImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLLoadImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLSaveImpl;
import org.eclipse.xsd.ecore.XSDEcoreBuilder;
import org.xml.sax.helpers.DefaultHandler;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * @implements Bpmn2Resource
 * <!-- end-user-doc -->
 * @see org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl
 * @generated
 */
public class Bpmn2ResourceImpl extends XMLResourceImpl implements Bpmn2Resource {

    private QNameURIHandler uriHandler;
    private BpmnXmlHelper xmlHelper;

    // CHECK: make this optional (as it adds notification overhead) 
    // ... or lazy (also works if added later on, because it attaches itself to the whole tree at once)
    protected Bpmn2OppositeReferenceAdapter oppositeReferenceAdapter = new Bpmn2OppositeReferenceAdapter();

    public Bpmn2OppositeReferenceAdapter getOppositeReferenceAdapter() {
        return oppositeReferenceAdapter;
    }

    /**
     * Creates an instance of the resource.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param uri the URI of the new resource.
     * @generated NOT
     */
    public Bpmn2ResourceImpl(URI uri) {
        super(uri);
        this.xmlHelper = new BpmnXmlHelper(this);
        this.uriHandler = new QNameURIHandler(xmlHelper);
        this.getDefaultLoadOptions().put(XMLResource.OPTION_URI_HANDLER, uriHandler);
        this.getDefaultSaveOptions().put(XMLResource.OPTION_URI_HANDLER, uriHandler);

        // only necessary if this resource will not be added to a ResourceSet instantly
        this.eAdapters().add(oppositeReferenceAdapter);
    }

    @Override
    public NotificationChain basicSetResourceSet(ResourceSet resourceSet,
            NotificationChain notifications) {
        if (resourceSet != null)
            resourceSet.eAdapters().add(oppositeReferenceAdapter);
        return super.basicSetResourceSet(resourceSet, notifications);
    }

    // This method is called by all save methods - save(Document,...), doSave(Writer/OutputStream, ...) - in superclasses.
    @Override
    protected XMLSave createXMLSave() {
        prepareSave();
        return new XMLSaveImpl(createXMLHelper()) {
            @Override
            protected boolean shouldSaveFeature(EObject o, EStructuralFeature f) {
                if (Bpmn2Package.eINSTANCE.getDocumentation_Text().equals(f))
                    return false;
                if (Bpmn2Package.eINSTANCE.getFormalExpression_Body().equals(f))
                    return false;
                return super.shouldSaveFeature(o, f);
            }
        };
    }

    /**
     * Prepares this resource for saving.
     * 
     * Sets all ID attributes of contained and referenced objects 
     * that are not yet set, to a generated UUID.
     */
    protected void prepareSave() {
        EObject cur;
        Definitions thisDefinitions = ImportHelper.getDefinitions(this);
        for (Iterator<EObject> iter = getAllContents(); iter.hasNext();) {
            cur = iter.next();

            setIdIfNotSet(cur);

            for (EObject referenced : cur.eCrossReferences()) {
                setIdIfNotSet(referenced);
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
     * @param obj The object whose ID should be set.
     */
    protected static void setIdIfNotSet(EObject obj) {
        if (obj.eClass() != null) {
            EStructuralFeature idAttr = obj.eClass().getEIDAttribute();
            if (idAttr != null && !obj.eIsSet(idAttr)) {
                obj.eSet(idAttr, EcoreUtil.generateUUID());
            }
        }
    }

    /**
     * Looks for an import of the referenced resource from the given definitions object.
     * If none is found, the method creates a new import element.
     * @param definitions The model that references an object contained in <code>reference</code> 
     * and thus needs an import element to <code>reference</code>.
     * @param referenced The resource which needs to be imported into <code>definitions</code>.
     */
    protected void createImportIfNecessary(Definitions definitions, Resource referenced) {
        if (ImportHelper.findImportForLocation(definitions, referenced.getURI()) == null) {
            URI referencingURI = ImportHelper.makeURICanonical(definitions.eResource().getURI());
            URI referencedURI = ImportHelper.makeURICanonical(referenced.getURI());

            Definitions importedDef = ImportHelper.getDefinitions(referenced);
            // only handle BPMN imports (with declared target namespace)
            if (importedDef != null && importedDef.getTargetNamespace() != null) {
                Import newImport = Bpmn2Factory.eINSTANCE.createImport();
                newImport.setImportType(NamespaceHelper.xmiToXsdNamespaceUri(Bpmn2Package.eNS_URI));
                newImport.setNamespace(importedDef.getTargetNamespace());
                // Counterpart: location.resolve(referencingURI) == referencedURI !
                newImport.setLocation(referencedURI.deresolve(referencingURI).toString());
                definitions.getImports().add(newImport);
            }
        }
    }

    /**
     * We must override this method for having an own XMLHandler
     */
    @Override
    protected XMLLoad createXMLLoad() {
        return new XMLLoadImpl(createXMLHelper()) {
            @Override
            protected DefaultHandler makeDefaultHandler() {
                return new BpmnXmlHandler(resource, helper, options);
            }
        };
    }

    @Override
    protected XMLHelper createXMLHelper() {
        return this.xmlHelper;
    }

    /**
     * We need extend the standard SAXXMLHandler to hook into the handling of attribute references - which are no URIs but QNames.
     * @author Reiner Hille
     *
     */
    protected static class BpmnXmlHandler extends SAXXMLHandler {

        public BpmnXmlHandler(XMLResource xmiResource, XMLHelper helper, Map<?, ?> options) {
            super(xmiResource, helper, options);
        }

        /**
         * Overridden to be able to convert QName references in attributes to URIs during load.
         * @param ids
         *  In our case the parameter will contain exactly one QName that we resolve to URI.
         */
        @Override
        protected void setValueFromId(EObject object, EReference eReference, String ids) {
            super.setValueFromId(
                    object,
                    eReference,
                    eReference.isResolveProxies() ? ((QNameURIHandler) uriHandler)
                            .convertQNameToUri(ids) : ids);
        }

        /**
         * Used from the <extension><definition> tag to load referenced extension schemes.
         * The extension scheme will be loaded and converted to EMF Ecore on the fly.
         * 
         * @param id
         */
        private EObject loadExtensionSchema(QName xsdQname) {
            EPackage extensionPackage = extendedMetaData.getPackage(xsdQname.getNamespaceURI());
            if (extensionPackage == null) {

                try {
                    @SuppressWarnings("unchecked")
                    Class<XSDEcoreBuilder> theXSDEcoreBuilderClass = (Class<XSDEcoreBuilder>) CommonPlugin
                            .loadClass("org.eclipse.xsd", "org.eclipse.xsd.ecore.XSDEcoreBuilder");

                    Constructor<XSDEcoreBuilder> theXSDEcoreBuilderConstructor = theXSDEcoreBuilderClass
                            .getConstructor(new Class[] { ExtendedMetaData.class, Map.class });
                    Field theOptionField = theXSDEcoreBuilderClass
                            .getField("OPTION_REUSE_REGISTERED_PACKAGES");
                    Object theXsdOption = theOptionField.get(null);

                    URI location = urisToLocations.get(xsdQname.getNamespaceURI());
                    Map<Object, Object> options = new HashMap<Object, Object>();
                    options.put(theXsdOption, Boolean.TRUE);
                    XSDEcoreBuilder builder = theXSDEcoreBuilderConstructor.newInstance(
                            extendedMetaData, options);
                    builder.generate(location);
                } catch (Exception e) {
                }
            }

            return extendedMetaData.getElement(xsdQname.getNamespaceURI(), xsdQname.getLocalPart());
        }

        @Override
        public void endElement(String uri, String localName, String name) {
            // Detect Extension object
            EObject peekObject = objects.peek();
            if (peekObject instanceof Extension) {
                Extension extension = (Extension) peekObject;
                if (extension.isMustUnderstand() && null != extension.getXsdDefinition()) {
                    loadExtensionSchema(extension.getXsdDefinition());
                }
            }
            super.endElement(uri, localName, name);
        }
    }

    /**
     * Extend XML Helper to gain access to the different XSD namespace handling features.
     * @author Reiner Hille
     *
     */
    protected static class BpmnXmlHelper extends XMLHelperImpl {

        public BpmnXmlHelper(Bpmn2ResourceImpl resource) {
            super(resource);
        }

        private Definitions getDefinitions() {
            return ImportHelper.getDefinitions(getResource());
        }

        /**
         * Checks if the given prefix is pointing to the current target namespace and thus is optional.
         * The method is called during load.
         * @param prefix The prefix or null, if no prefix is given (interpreted as default namespace).
         * @return <code>true</code>, if the namespace associated with the prefix equals the target namespace 
         * of this Definitions.
         * If prefix is null or the empty string, then the default namespace is compared with the target namespace.
         * If prefix is null/empty and default namespace is not defined, <code>true</code> if the target namespace 
         * is not defined either.
         * 
         * <p>
         * The above rules describe a strict interpretation of the rules for QName resolution. 
         * This method relaxes these rules and additionally returns <code>true</code> in the following cases:
         * <ul>
         * <li>prefix is null/empty, no default namespace (regardless of target namespace)</li>
         * <li>prefix is null/empty, default namespace is not {@linkplain ImportHelper#findImportForNamespace(Definitions, String) mapped by an import element}.</li>
         * </ul>
         * </p>
         */
        public boolean isTargetNamespace(String prefix) {
            if (prefix == null)
                prefix = XMLConstants.DEFAULT_NS_PREFIX;
            final String prefixNs = this.getNamespaceURI(prefix);

            if (prefixNs == null) {
                if (XMLConstants.DEFAULT_NS_PREFIX.equals(prefix))
                    /*
                     * The (empty) prefix points to {no namespace}, because no default namespace is defined.
                     * This would be OK if target namespace is undefined as well (meaning {no namespace}).
                     * 
                     * However, we employ a relaxed interpretation and do not require that 
                     *   getDefinitions().getTargetNamespace() == null (i.e. target namespace == {no namespace})
                     * Every unprefixed QName is interpreted as local reference, if the default namespace is not defined.
                     */
                    return true;

                // the non-empty prefix is not mapped to a namespace
                throw new IllegalArgumentException(String.format("The prefix '%s' is not valid.",
                        prefix));
            }

            // result with strict evaluation: return prefixNs.equals(getDefinitions().getTargetNamespace())
            if (prefixNs.equals(getDefinitions().getTargetNamespace()))
                return true;
            else if (XMLConstants.DEFAULT_NS_PREFIX.equals(prefix)
                    && ImportHelper.findImportForNamespace(getDefinitions(), prefixNs) == null) {
                // The default namespace is not mapped to a location by an import element.
                // Guess that the unprefixed QName should point to a local element (relaxed interpretation)
                // TODO: emit warning
                return true;
            } else
                return false;
        }

        /**
         * Looks up the given prefix in the list of BPMN import elements and returns - if found - the corresponding file location.
         * The method is called during load.
         * @param prefix
         * @return
         */
        public URI getPathForPrefix(String prefix) {
            String ns = this.getNamespaceURI(prefix == null ? XMLConstants.DEFAULT_NS_PREFIX
                    : prefix);
            if (ns != null) {
                Import imp = ImportHelper.findImportForNamespace(getDefinitions(), ns);
                if (imp != null)
                    return URI.createURI(imp.getLocation()).resolve(
                            ImportHelper.makeURICanonical(getResource().getURI()));
                else {
                    return URI.createURI(ns);
                }
            }
            return URI.createURI("");
        }

        /**
         * Partly stolen from XmlHelperImpl.setPrefixToNamespaceMap().
         * Ensuring that namespace declaration is saved seems to be really tricky.
         * We will necessarily create a dummy package to ensure that later XmlSaveImpl.addNamespaceDeclarations() writes the ns declaration for us
         * @param namespace
         * @return
         */
        private String getPrefixDuringSave(String namespace) {
            if (urisToPrefixes.containsKey(namespace))
                return urisToPrefixes.get(namespace).get(0);

            EPackage ePackage = extendedMetaData.getPackage(namespace);
            if (ePackage == null) {
                ePackage = extendedMetaData.demandPackage(namespace);
                // This will internally create a nice prefix
            }

            String prefix;
            if (namespace.equals(getDefinitions().getTargetNamespace()))
                // try to use the default namespace (xmlns="...") for local references
                prefix = XMLConstants.DEFAULT_NS_PREFIX;
            else
                prefix = ePackage.getNsPrefix();

            // Make prefix unique
            String originalPrefix = prefix + "_";
            int discr = 0;
            while (prefixesToURIs.containsKey(prefix)
                    && !prefixesToURIs.get(prefix).equals(namespace))
                prefix = originalPrefix + discr++;

            // I'm not sure if the following code is needed, but I keep it to avoid inconsistencies
            if (!packages.containsKey(ePackage)) {
                packages.put(ePackage, prefix);
            }
            prefixesToURIs.put(prefix, namespace);
            return prefix;
        }

        /**
         * This is called on save to convert from a file-based URI to a namespace prefix.
         * It might be necessary to add a new namespace declaration to the file, if  the 
         * namespace was not known to far.
         * @param referenced Absolute or relative to current working directory.
         * @return
         */
        public String getNsPrefix(URI referenced) {
            String ns = null;
            String prefix = "";

            URI referencedAbs = ImportHelper.makeURICanonical(referenced);
            URI thisAbs = ImportHelper.makeURICanonical(getResource().getURI());
            URI relativeToThis = referencedAbs.deresolve(thisAbs);
            if (relativeToThis.isEmpty())
                // reference to local element
                ns = getDefinitions().getTargetNamespace();
            else {
                Import impForRef = ImportHelper.findImportForLocation(getDefinitions(), referenced);
                if (impForRef != null)
                    ns = impForRef.getNamespace();
            }
            if (ns != null) {
                prefix = getPrefixDuringSave(ns);
            }
            return prefix;
        }
    }

} //Bpmn2ResourceImpl
