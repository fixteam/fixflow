/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Quartz Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.QuartzConfig#getIsEnable <em>Is Enable</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.QuartzConfig#getIsDefaultConfig <em>Is Default Config</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.QuartzConfig#getDataBaseId <em>Data Base Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getQuartzConfig()
 * @model extendedMetaData="name='quartzConfig'"
 * @generated
 */
public interface QuartzConfig extends EObject {
	/**
	 * Returns the value of the '<em><b>Is Enable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Enable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Enable</em>' attribute.
	 * @see #setIsEnable(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getQuartzConfig_IsEnable()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getIsEnable();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.QuartzConfig#getIsEnable <em>Is Enable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Enable</em>' attribute.
	 * @see #getIsEnable()
	 * @generated
	 */
	void setIsEnable(String value);

	/**
	 * Returns the value of the '<em><b>Is Default Config</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Default Config</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Default Config</em>' attribute.
	 * @see #setIsDefaultConfig(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getQuartzConfig_IsDefaultConfig()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getIsDefaultConfig();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.QuartzConfig#getIsDefaultConfig <em>Is Default Config</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Default Config</em>' attribute.
	 * @see #getIsDefaultConfig()
	 * @generated
	 */
	void setIsDefaultConfig(String value);

	/**
	 * Returns the value of the '<em><b>Data Base Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Base Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Base Id</em>' attribute.
	 * @see #setDataBaseId(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getQuartzConfig_DataBaseId()
	 * @model extendedMetaData="name='dataBaseId' kind='attribute'"
	 * @generated
	 */
	String getDataBaseId();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.QuartzConfig#getDataBaseId <em>Data Base Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Base Id</em>' attribute.
	 * @see #getDataBaseId()
	 * @generated
	 */
	void setDataBaseId(String value);

} // QuartzConfig
