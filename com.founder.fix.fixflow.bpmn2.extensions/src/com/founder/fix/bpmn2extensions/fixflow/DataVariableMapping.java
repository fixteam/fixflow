/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.fixflow;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Variable Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DataVariableMapping#getDataSourceId <em>Data Source Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.DataVariableMapping#getSubProcesId <em>Sub Proces Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDataVariableMapping()
 * @model
 * @generated
 */
public interface DataVariableMapping extends EObject {
	/**
	 * Returns the value of the '<em><b>Data Source Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Source Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Source Id</em>' attribute.
	 * @see #setDataSourceId(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDataVariableMapping_DataSourceId()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getDataSourceId();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DataVariableMapping#getDataSourceId <em>Data Source Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Source Id</em>' attribute.
	 * @see #getDataSourceId()
	 * @generated
	 */
	void setDataSourceId(String value);

	/**
	 * Returns the value of the '<em><b>Sub Proces Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Proces Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Proces Id</em>' attribute.
	 * @see #setSubProcesId(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getDataVariableMapping_SubProcesId()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getSubProcesId();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.DataVariableMapping#getSubProcesId <em>Sub Proces Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sub Proces Id</em>' attribute.
	 * @see #getSubProcesId()
	 * @generated
	 */
	void setSubProcesId(String value);

} // DataVariableMapping
