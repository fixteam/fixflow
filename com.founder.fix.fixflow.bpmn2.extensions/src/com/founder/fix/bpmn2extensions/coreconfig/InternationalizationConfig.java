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
 * A representation of the model object '<em><b>Internationalization Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.InternationalizationConfig#getIsEnable <em>Is Enable</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.InternationalizationConfig#getFolderPath <em>Folder Path</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getInternationalizationConfig()
 * @model extendedMetaData="name='internationalizationConfig'"
 * @generated
 */
public interface InternationalizationConfig extends EObject {
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
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getInternationalizationConfig_IsEnable()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getIsEnable();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.InternationalizationConfig#getIsEnable <em>Is Enable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Enable</em>' attribute.
	 * @see #getIsEnable()
	 * @generated
	 */
	void setIsEnable(String value);

	/**
	 * Returns the value of the '<em><b>Folder Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Folder Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Folder Path</em>' attribute.
	 * @see #setFolderPath(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getInternationalizationConfig_FolderPath()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getFolderPath();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.InternationalizationConfig#getFolderPath <em>Folder Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Folder Path</em>' attribute.
	 * @see #getFolderPath()
	 * @generated
	 */
	void setFolderPath(String value);

} // InternationalizationConfig
