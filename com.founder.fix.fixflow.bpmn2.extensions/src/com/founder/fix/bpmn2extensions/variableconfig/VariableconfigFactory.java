/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.variableconfig;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.founder.fix.bpmn2extensions.variableconfig.VariableconfigPackage
 * @generated
 */
public interface VariableconfigFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	VariableconfigFactory eINSTANCE = com.founder.fix.bpmn2extensions.variableconfig.impl.VariableconfigFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Document Root</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Document Root</em>'.
	 * @generated
	 */
	DocumentRoot createDocumentRoot();

	/**
	 * Returns a new object of class '<em>Data Variable Config</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Variable Config</em>'.
	 * @generated
	 */
	DataVariableConfig createDataVariableConfig();

	/**
	 * Returns a new object of class '<em>Data Variable Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Variable Type</em>'.
	 * @generated
	 */
	DataVariableType createDataVariableType();

	/**
	 * Returns a new object of class '<em>Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Type</em>'.
	 * @generated
	 */
	Type createType();

	/**
	 * Returns a new object of class '<em>Data Variable Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Variable Data Type</em>'.
	 * @generated
	 */
	DataVariableDataType createDataVariableDataType();

	/**
	 * Returns a new object of class '<em>Data Type Def</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Type Def</em>'.
	 * @generated
	 */
	DataTypeDef createDataTypeDef();

	/**
	 * Returns a new object of class '<em>Fix Flow Data Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Fix Flow Data Variable</em>'.
	 * @generated
	 */
	FixFlowDataVariable createFixFlowDataVariable();

	/**
	 * Returns a new object of class '<em>Data Variable Def</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Variable Def</em>'.
	 * @generated
	 */
	DataVariableDef createDataVariableDef();

	/**
	 * Returns a new object of class '<em>Data Variable Biz Type Config</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Variable Biz Type Config</em>'.
	 * @generated
	 */
	DataVariableBizTypeConfig createDataVariableBizTypeConfig();

	/**
	 * Returns a new object of class '<em>Data Variable Biz Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Variable Biz Type</em>'.
	 * @generated
	 */
	DataVariableBizType createDataVariableBizType();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	VariableconfigPackage getVariableconfigPackage();

} //VariableconfigFactory
