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

package org.eclipse.bpmn2.modeler.core.utils;

import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;

/**
 * @author Bob Brodt
 *
 */
public class NamespaceUtil {

	public static Map<String,String> getXMLNSPrefixMap(Resource resource) {
		if (resource!=null) {
			EList<EObject> contents = resource.getContents();
			if (!contents.isEmpty() && contents.get(0) instanceof DocumentRoot) {
				return ((DocumentRoot)contents.get(0)).getXMLNSPrefixMap();
			}
		}
		return null;
	}

	public static String getNamespaceForPrefix(Resource resource, String prefix) {
		Map<String,String> map = getXMLNSPrefixMap(resource);
		if (map!=null)
			return map.get(prefix);
		return null;
	}
	
	public static String getPrefixForNamespace(Resource resource, String namespace) {
		Map<String,String> map = getXMLNSPrefixMap(resource);
		if (map!=null) {
			for (Entry<String, String> e : map.entrySet()) {
				String value = e.getValue();
				if (value!=null && value.equals(namespace))
					return e.getKey();
			}
		}
		return null;
	}
	
	public static boolean hasNamespace(Resource resource, String namespace) {
		Map<String,String> map = getXMLNSPrefixMap(resource);
		if (map!=null) {
			for (Entry<String, String> e : map.entrySet()) {
				String value = e.getValue();
				if (value!=null && value.equals(namespace))
					return true;
			}
		}
		return false;
	}
	
	public static boolean hasPrefix(Resource resource, String prefix) {
		Map<String,String> map = getXMLNSPrefixMap(resource);
		if (map!=null) {
			return map.containsKey(prefix);
		}
		return false;
	}
	
	public static String addNamespace(Resource resource, String namespace) {
		if (hasNamespace(resource,namespace))
			return null;
		// generate a prefix
		String prefix = null;
		Map<String,String> map = getXMLNSPrefixMap(resource);
		if (map!=null) {
			prefix = "ns";
			int index = 1;
			while (map.containsKey(prefix+index))
				++index;
			prefix = addNamespace(resource, prefix+index, namespace);
		}
		return prefix;
	}
	
	public static String addNamespace(final Resource resource, final String prefix, final String namespace) {
		final Map<String,String> map = getXMLNSPrefixMap(resource);
		if (map!=null) {
			TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(resource);
			if (domain != null) {
				domain.getCommandStack().execute(new RecordingCommand(domain) {
					@Override
					protected void doExecute() {
						map.put(prefix, namespace);
					}
				});
			}
			return prefix;
		}
		return null;
	}
	
	/**
	 * Remove the namespace prefix mapping for a given namespace.
	 * 
	 * @param object - any EObject in the BPMN2 Resource 
	 * @param namespace - the namespace to be removed
	 * @return the namespace prefix if the mapping was successfully removed
	 * or null otherwise
	 */
	public static String removeNamespace(final Resource resource, final String namespace) {
		final Map<String,String> map = getXMLNSPrefixMap(resource);
		if (map!=null) {
			String prefix = null;
			for (Entry<String, String> e : map.entrySet()) {
				String value = e.getValue();
				if (value!=null && value.equals(namespace)) {
					prefix = e.getKey();
					break;
				}
			}
			if (prefix!=null && map.containsKey(prefix)) {
				TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(resource);
				if (domain != null) {
					final String p = prefix;
					domain.getCommandStack().execute(new RecordingCommand(domain) {
						@Override
						protected void doExecute() {
							map.remove(p);
						}
					});
				}
				return prefix;
			}
		}
		return null;
	}
	
	/**
	 * Remove the namespace prefix mapping for a given prefix.
	 * 
	 * @param object - any EObject in the BPMN2 Resource 
	 * @param prefix - the namespace prefix to be removed
	 * @return the namespace if the mapping was successfully removed
	 * or null otherwise
	 */
	public static String removeNamespaceForPrefix(final Resource resource, final String prefix) {
		final Map<String,String> map = getXMLNSPrefixMap(resource);
		if (map!=null && map.containsKey(prefix)) {
			TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(resource);
			if (domain != null) {
				String ns = map.get(prefix);
				domain.getCommandStack().execute(new RecordingCommand(domain) {
					@Override
					protected void doExecute() {
						map.remove(prefix);
					}
				});
				return ns;
			}
		}
		return null;
	}
}
