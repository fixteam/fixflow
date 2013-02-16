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
 * $Id: //bpem/bpem.metamodels/dev/src/_org.eclipse.bpmn2.ecore/ecp/api/org/eclipse/bpmn2/ecore/QNameURIHandler.java#2 $
 */
package org.eclipse.bpmn2.util;

import org.eclipse.bpmn2.util.Bpmn2ResourceImpl.BpmnXmlHelper;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.impl.URIHandlerImpl;

/**
 * This simple XMLResource URI Handler converts between the QName-based reference model in BPMN 2.0 and the URI based model in EMF.
 * 
 * The prefix can be resolved using the prefix declaration in the file, then going to the "imports" element, searching for a fitting import and taking this file as baseURI.
 * 
 * @author Reiner Hille (SAP)
 */
public class QNameURIHandler extends URIHandlerImpl {

    private final BpmnXmlHelper xmlHelper;

    /**
     * 
     */
    public QNameURIHandler(Bpmn2ResourceImpl.BpmnXmlHelper xmlHelper) {
        this.xmlHelper = xmlHelper;
    }

    /**
     * The method converts a QName, e.g. "ns:element1" to a URI string, e.g. file1.bpmn#element1.
     * The method is called during load.
     * @param qName
     * @return
     */
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
            // HACK: officially IDs can contain ".", but unfortunately XmlHandler calls resolve also for xsi:schemaLocation stuff and similar, that are
            // NO URIs. We must not process them.
            return qName;
        }

        if (!xmlHelper.isTargetNamespace(prefix))
            return xmlHelper.getPathForPrefix(prefix).appendFragment(fragment).toString();
        else
            return baseURI.appendFragment(fragment).toString();
    }

    /**
     * Called from the framework during load. We will resolve to an absolute URI after - necessarily creating
     * a relative URI from a QName.
     */
    @Override
    public URI resolve(URI uri) {
        return super.resolve(URI.createURI(convertQNameToUri(uri.toString())));
    }

    /**
     * Called from the framework during save. We deresolve absolute URIs to relative ones. Then we try to
     * convert to QName
     */
    @Override
    public URI deresolve(URI uri) {
        String fragment = uri.fragment();
        if (fragment != null && !fragment.startsWith("/")) // We better don't try to QName XPath references to e.g. XML or WSDL context for now.
        {
            String prefix = "";

            if (uri.hasPath()) {
                prefix = xmlHelper.getNsPrefix(uri.trimFragment());
            }
            if (prefix.length() > 0) {
                return URI.createURI(prefix + ":" + fragment);
            } else
                // no prefix, just fragment (i.e. without the '#')
                return URI.createURI(fragment);
        }
        return super.deresolve(uri);
    }
}
