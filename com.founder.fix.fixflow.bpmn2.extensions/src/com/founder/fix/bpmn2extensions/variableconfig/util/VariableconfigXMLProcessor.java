/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.variableconfig.util;

import com.founder.fix.bpmn2extensions.variableconfig.VariableconfigPackage;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class VariableconfigXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableconfigXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		VariableconfigPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the VariableconfigResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new VariableconfigResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new VariableconfigResourceFactoryImpl());
		}
		return registrations;
	}

} //VariableconfigXMLProcessor
