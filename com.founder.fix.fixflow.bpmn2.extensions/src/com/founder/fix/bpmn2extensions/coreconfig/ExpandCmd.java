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
 * A representation of the model object '<em><b>Expand Cmd</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandCmd#getId <em>Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandCmd#getName <em>Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandCmd#getCmd <em>Cmd</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getExpandCmd()
 * @model extendedMetaData="name='expandCmd'"
 * @generated
 */
public interface ExpandCmd extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getExpandCmd_Id()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute' name='id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandCmd#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getExpandCmd_Name()
	 * @model required="true"
	 *        extendedMetaData="name='name' kind='attribute'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandCmd#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Cmd</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cmd</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cmd</em>' attribute.
	 * @see #setCmd(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getExpandCmd_Cmd()
	 * @model required="true"
	 *        extendedMetaData="name='cmd' kind='attribute'"
	 * @generated
	 */
	String getCmd();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandCmd#getCmd <em>Cmd</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cmd</em>' attribute.
	 * @see #getCmd()
	 * @generated
	 */
	void setCmd(String value);

} // ExpandCmd
