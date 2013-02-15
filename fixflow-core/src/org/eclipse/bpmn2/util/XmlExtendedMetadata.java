/**
 * <copyright>
 * 
 * Copyright (c) 2010 SAP AG
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Reiner Hille-Doering (SAP AG) - initial API and implementation and/or initial documentation
 * 
 * </copyright>
 *
 * $Id: //bpem/bpem.metamodels/dev/src/_org.eclipse.bpmn2.ecore/ecp/api/org/eclipse/bpmn2/ecore/XmlExtendedMetadata.java#1 $
 */
package org.eclipse.bpmn2.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.di.BpmnDiPackage;
import org.eclipse.dd.dc.DcPackage;
import org.eclipse.dd.di.DiPackage;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;

public class XmlExtendedMetadata extends BasicExtendedMetaData {

    /** Mapping between XMI and XML namespaces of packages of the BPMN specification */
    private static Map<String, String> xmiToXmlNamespaceMap;

    /**
     * Initializes the {@linkplain #xmiToXmlNamespaceMap map between XMI and XML namespaces} of BPMN packages.
     */
    private static void initXmiToXmlNamespaceMap() {
        xmiToXmlNamespaceMap = new HashMap<String, String>(6);
        String[] namespaces = { Bpmn2Package.eNS_URI, BpmnDiPackage.eNS_URI, DiPackage.eNS_URI,
                DcPackage.eNS_URI };
        for (String curNs : namespaces)
            xmiToXmlNamespaceMap.put(curNs, NamespaceHelper.xmiToXsdNamespaceUri(curNs));
    }

    /**
     * 
     */
    public XmlExtendedMetadata() {
    }

    @Override
    public String getNamespace(EPackage ePackage) {
        if (xmiToXmlNamespaceMap == null)
            initXmiToXmlNamespaceMap();

        String ns = super.getNamespace(ePackage);
        String xmlNs;
        if ((xmlNs = xmiToXmlNamespaceMap.get(ns)) != null)
            return xmlNs;
        return ns;
    }

    /**
     * The method handles classes or data types that exist in XSD, but not in the Ecore.
     * The corresponding type will be "redirected" to a substitution or standard type.
     * The code snippet is important if XSD2Ecore "accidently" loads the BPMN20.xsd
     * into memory as Ecore, which happens e.g. from files that have a referenced extension
     * XSD. The on-the-fly converted BPMN20.xsd must not infer with our Ecore.
     */
    @Override
    public EClassifier getType(EPackage ePackage, String name) {
        if (Bpmn2Package.eINSTANCE.equals(ePackage)) {
            if ("tBaseElementWithMixedContent".equals(name)) {
                return Bpmn2Package.Literals.BASE_ELEMENT;
            } else if ("tImplementation".equals(name)) {
                return EcorePackage.Literals.ESTRING;
            } else if ("tScript".equals(name)) {
                return EcorePackage.Literals.EOBJECT;

            } else if ("tText".equals(name)) {
                return EcorePackage.Literals.EOBJECT;

            } else if ("tTransactionMethod".equals(name)) {
                return EcorePackage.Literals.ESTRING;
            }
        }
        return super.getType(ePackage, name);
    }
}
