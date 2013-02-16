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
 * A representation of the model object '<em><b>Expand Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandClass#getClassId <em>Class Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandClass#getClassName <em>Class Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandClass#getClassInterface <em>Class Interface</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandClass#getClassImpl <em>Class Impl</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandClass#getRemarks <em>Remarks</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getExpandClass()
 * @model extendedMetaData="name='expandClass'"
 * @generated
 */
public interface ExpandClass extends EObject {
	/**
	 * Returns the value of the '<em><b>Class Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 类编号
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Class Id</em>' attribute.
	 * @see #setClassId(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getExpandClass_ClassId()
	 * @model required="true"
	 *        extendedMetaData="name='classId' kind='attribute'"
	 * @generated
	 */
	String getClassId();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandClass#getClassId <em>Class Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Id</em>' attribute.
	 * @see #getClassId()
	 * @generated
	 */
	void setClassId(String value);

	/**
	 * Returns the value of the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 类名称
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Class Name</em>' attribute.
	 * @see #setClassName(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getExpandClass_ClassName()
	 * @model required="true"
	 *        extendedMetaData="name='className' kind='attribute'"
	 * @generated
	 */
	String getClassName();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandClass#getClassName <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Name</em>' attribute.
	 * @see #getClassName()
	 * @generated
	 */
	void setClassName(String value);

	/**
	 * Returns the value of the '<em><b>Class Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 接口名称
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Class Interface</em>' attribute.
	 * @see #setClassInterface(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getExpandClass_ClassInterface()
	 * @model required="true"
	 *        extendedMetaData="name='classInterface' kind='attribute'"
	 * @generated
	 */
	String getClassInterface();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandClass#getClassInterface <em>Class Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Interface</em>' attribute.
	 * @see #getClassInterface()
	 * @generated
	 */
	void setClassInterface(String value);

	/**
	 * Returns the value of the '<em><b>Class Impl</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 实现类
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Class Impl</em>' attribute.
	 * @see #setClassImpl(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getExpandClass_ClassImpl()
	 * @model required="true"
	 *        extendedMetaData="name='classImpl' kind='attribute'"
	 * @generated
	 */
	String getClassImpl();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandClass#getClassImpl <em>Class Impl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Impl</em>' attribute.
	 * @see #getClassImpl()
	 * @generated
	 */
	void setClassImpl(String value);

	/**
	 * Returns the value of the '<em><b>Remarks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 备注
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Remarks</em>' attribute.
	 * @see #setRemarks(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getExpandClass_Remarks()
	 * @model required="true"
	 *        extendedMetaData="name='remarks' kind='attribute'"
	 * @generated
	 */
	String getRemarks();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.ExpandClass#getRemarks <em>Remarks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remarks</em>' attribute.
	 * @see #getRemarks()
	 * @generated
	 */
	void setRemarks(String value);

} // ExpandClass
