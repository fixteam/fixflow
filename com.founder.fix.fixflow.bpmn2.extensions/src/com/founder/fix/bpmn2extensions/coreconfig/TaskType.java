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
 * A representation of the model object '<em><b>Task Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 任务类型
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.TaskType#getTypeId <em>Type Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.TaskType#getTypeName <em>Type Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getTaskType()
 * @model extendedMetaData="name='taskType'"
 * @generated
 */
public interface TaskType extends EObject {
	/**
	 * Returns the value of the '<em><b>Type Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 类型编号
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type Id</em>' attribute.
	 * @see #setTypeId(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getTaskType_TypeId()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getTypeId();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskType#getTypeId <em>Type Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Id</em>' attribute.
	 * @see #getTypeId()
	 * @generated
	 */
	void setTypeId(String value);

	/**
	 * Returns the value of the '<em><b>Type Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 类型名称
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type Name</em>' attribute.
	 * @see #setTypeName(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getTaskType_TypeName()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getTypeName();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskType#getTypeName <em>Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Name</em>' attribute.
	 * @see #getTypeName()
	 * @generated
	 */
	void setTypeName(String value);

} // TaskType
