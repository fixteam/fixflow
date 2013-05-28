/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.style;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element Style</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.style.ElementStyle#getStyle <em>Style</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.style.ElementStyle#getStyleId <em>Style Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.style.ElementStyle#getStyleName <em>Style Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.style.StylePackage#getElementStyle()
 * @model extendedMetaData="name='elementStyle'"
 * @generated
 */
public interface ElementStyle extends EObject {
	/**
	 * Returns the value of the '<em><b>Style</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.style.Style}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Style</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Style</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.style.StylePackage#getElementStyle_Style()
	 * @model containment="true"
	 *        extendedMetaData="name='style' kind='element'"
	 * @generated
	 */
	EList<Style> getStyle();

	/**
	 * Returns the value of the '<em><b>Style Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 节点样式主题编号
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Style Id</em>' attribute.
	 * @see #setStyleId(String)
	 * @see com.founder.fix.bpmn2extensions.style.StylePackage#getElementStyle_StyleId()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute' name='styleId'"
	 * @generated
	 */
	String getStyleId();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.style.ElementStyle#getStyleId <em>Style Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Style Id</em>' attribute.
	 * @see #getStyleId()
	 * @generated
	 */
	void setStyleId(String value);

	/**
	 * Returns the value of the '<em><b>Style Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 节点样式主题名称
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Style Name</em>' attribute.
	 * @see #setStyleName(String)
	 * @see com.founder.fix.bpmn2extensions.style.StylePackage#getElementStyle_StyleName()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getStyleName();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.style.ElementStyle#getStyleName <em>Style Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Style Name</em>' attribute.
	 * @see #getStyleName()
	 * @generated
	 */
	void setStyleName(String value);

} // ElementStyle
