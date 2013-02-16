/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Base Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.DataBaseConfig#getDataBase <em>Data Base</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.DataBaseConfig#getSelected <em>Selected</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.DataBaseConfig#getMode <em>Mode</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getDataBaseConfig()
 * @model extendedMetaData="name='dataBaseConfig'"
 * @generated
 */
public interface DataBaseConfig extends EObject {
	/**
	 * Returns the value of the '<em><b>Data Base</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.coreconfig.DataBase}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Base</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Base</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getDataBaseConfig_DataBase()
	 * @model containment="true" required="true"
	 *        extendedMetaData="name='dataBase' kind='element'"
	 * @generated
	 */
	EList<DataBase> getDataBase();

	/**
	 * Returns the value of the '<em><b>Selected</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Selected</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Selected</em>' attribute.
	 * @see #setSelected(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getDataBaseConfig_Selected()
	 * @model required="true"
	 *        extendedMetaData="name='selected' kind='attribute'"
	 * @generated
	 */
	String getSelected();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBaseConfig#getSelected <em>Selected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Selected</em>' attribute.
	 * @see #getSelected()
	 * @generated
	 */
	void setSelected(String value);

	/**
	 * Returns the value of the '<em><b>Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mode</em>' attribute.
	 * @see #setMode(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getDataBaseConfig_Mode()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getMode();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.DataBaseConfig#getMode <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mode</em>' attribute.
	 * @see #getMode()
	 * @generated
	 */
	void setMode(String value);

} // DataBaseConfig
