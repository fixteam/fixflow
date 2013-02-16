/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpmn2.modeler.core.adapters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.wst.wsdl.WSDLElement;
import org.eclipse.xsd.util.XSDConstants;
import org.w3c.dom.Element;

/**
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date Jul 23, 2007
 * 
 */
public class BasicEObjectAdapter extends AbstractStatefulAdapter

implements IProperty<String, Object>, INamespaceMap<String, String> {

	/**
	 * For generic properties associated with an EObject; this are transient and
	 * not saved
	 */
	Map<String, Object> fProperties = null;

	/** Forward, prefix to namespace map. */
	Map<String, String> fPrefix2Namespace = null;

	/** Reverse, Namespace to list of prefixes */
	Map<String, List<String>> fNamespace2Prefix = null;

	/**
	 * @param key
	 *            the key
	 * @return the value
	 * @see org.eclipse.bpel.model.adapters.IProperty#getProperty(java.lang.Object)
	 */

	public Object getProperty(String key) {
		return fProperties == null ? null : fProperties.get(key);
	}

	/**
	 * Set the value under the key key. Previous value is returned.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the new value
	 * @return the previous value, if set.
	 * @see org.eclipse.bpel.model.adapters.IProperty#setProperty(java.lang.Object,
	 *      java.lang.Object)
	 */

	public Object setProperty(String key, Object value) {
		if (fProperties == null) {
			fProperties = new HashMap<String, Object>(5);
		}
		return fProperties.put(key, value);
	}

	/**
	 * 
	 * @param key
	 *            the namespace to get the reverse mapping for
	 * @return The reverse mapping of the Namespace to namespace prefixes.
	 * @see org.eclipse.bpel.model.adapters.INamespaceMap#getReverse(java.lang.Object)
	 */

	public List<String> getReverse(String key) {
		return getReverse4(key);
	}

	/**
	 * @see java.util.Map#clear()
	 */
	public void clear() {
		if (fNamespace2Prefix != null) {
			fNamespace2Prefix.clear();
		}
		if (fPrefix2Namespace != null) {
			fPrefix2Namespace.clear();
		}
	}

	/**
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	public boolean containsKey(Object key) {
		return fPrefix2Namespace != null ? fPrefix2Namespace.containsKey(key)
				: false;
	}

	/**
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	public boolean containsValue(Object value) {
		return fPrefix2Namespace != null ? fPrefix2Namespace
				.containsValue(value) : false;
	}

	/**
	 * @see java.util.Map#entrySet()
	 */
	public Set<java.util.Map.Entry<String, String>> entrySet() {
		if (fPrefix2Namespace == null) {
			return Collections.emptySet();
		}
		return fPrefix2Namespace.entrySet();
	}

	/**
	 * @see java.util.Map#get(java.lang.Object)
	 */
	public String get(Object key) {
		return fPrefix2Namespace != null ? fPrefix2Namespace.get(key) : null;
	}

	/**
	 * @see java.util.Map#isEmpty()
	 */
	public boolean isEmpty() {
		return fPrefix2Namespace != null ? fPrefix2Namespace.isEmpty() : true;
	}

	/**
	 * @see java.util.Map#keySet()
	 */
	public Set<String> keySet() {
		if (fPrefix2Namespace == null) {
			return Collections.emptySet();
		}
		return fPrefix2Namespace.keySet();
	}

	/**
	 * @param key
	 *            the key to set
	 * @param value
	 *            the value to set.
	 * @return the old value, if set
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public String put(String key, String value) {
		fPrefix2Namespace = getPrefix2NamespaceMap();
		String oldValue = fPrefix2Namespace.put(key, value);

		// when we put something, the reverse value may not yet exist.
		List<String> ns2pfx = getReverse4(value);
		if (ns2pfx.contains(key) == false) {
			ns2pfx.add(key);
		}
		//
		if (getTarget() instanceof WSDLElement) {
			Element element = ((WSDLElement) getTarget()).getElement();
			// Element could be null (for instance, on load)
			if (element != null) {
				String attr = key.equals("") ? "xmlns" : "xmlns:" + key;
				// We only need to update attribute if something really has been changed
				// because it causes hard reconcile process
				if ((oldValue == null && value != null) || (oldValue != null && !oldValue.equals(value))) {
					String attribute = element.getAttribute(attr);
					if ((attribute == null && value != null) || (attribute != null && !attribute.equals(value))) {
						element.setAttribute(attr, value);
					}
				}
			}
		}
		return oldValue;
	}

	/**
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	public void putAll(Map<? extends String, ? extends String> t) {
		fPrefix2Namespace.putAll(t);
	}

	/**
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	public String remove(Object key) {
		if (fPrefix2Namespace == null) {
			return null;
		}

		String value = fPrefix2Namespace.remove(key);

		// the key did not exist.
		if (value == null) {
			return value;
		}
		// the key did exist, remove it from the reverse mapping as well.
		// the reverse mapping must exist in this case.
		fNamespace2Prefix.get(value).remove(key);

		if (getTarget() instanceof WSDLElement) {
			Element element = ((WSDLElement) getTarget()).getElement();
			// Element could be null (for instance, on load)
			if (element != null) {
				element.removeAttributeNS(XSDConstants.XMLNS_URI_2000, key.toString());
			}
		}
		
		return value;
	}

	/**
	 * @see java.util.Map#size()
	 */
	public int size() {
		return fPrefix2Namespace != null ? fPrefix2Namespace.size() : 0;
	}

	/**
	 * @see java.util.Map#values()
	 */
	public Collection<String> values() {
		if (fPrefix2Namespace == null) {
			return Collections.emptyList();
		}
		return fPrefix2Namespace.values();
	}

	Map<String, String> getPrefix2NamespaceMap() {
		if (fPrefix2Namespace == null) {
			fPrefix2Namespace = new HashMap<String, String>(5);
		}
		return fPrefix2Namespace;
	}

	List<String> getReverse4(String key) {
		if (fNamespace2Prefix == null) {
			fNamespace2Prefix = new HashMap<String, List<String>>(5);
		}
		List<String> prefixes = fNamespace2Prefix.get(key);
		if (prefixes == null) {
			prefixes = new ArrayList<String>();
			fNamespace2Prefix.put(key, prefixes);
		}
		return prefixes;
	}

}
