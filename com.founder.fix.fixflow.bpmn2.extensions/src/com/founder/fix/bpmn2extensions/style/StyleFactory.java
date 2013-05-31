/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.style;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.founder.fix.bpmn2extensions.style.StylePackage
 * @generated
 */
public interface StyleFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StyleFactory eINSTANCE = com.founder.fix.bpmn2extensions.style.impl.StyleFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Fix Flow Style Config</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Fix Flow Style Config</em>'.
	 * @generated
	 */
	FixFlowStyleConfig createFixFlowStyleConfig();

	/**
	 * Returns a new object of class '<em>Element Style Config</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Element Style Config</em>'.
	 * @generated
	 */
	ElementStyleConfig createElementStyleConfig();

	/**
	 * Returns a new object of class '<em>Element Style</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Element Style</em>'.
	 * @generated
	 */
	ElementStyle createElementStyle();

	/**
	 * Returns a new object of class '<em>Style</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Style</em>'.
	 * @generated
	 */
	Style createStyle();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	StylePackage getStylePackage();

} //StyleFactory
