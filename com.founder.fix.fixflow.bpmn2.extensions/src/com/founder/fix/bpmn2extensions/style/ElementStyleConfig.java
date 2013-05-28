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
 * A representation of the model object '<em><b>Element Style Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.style.ElementStyleConfig#getElementStyle <em>Element Style</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.style.ElementStyleConfig#getCurrentStyle <em>Current Style</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.style.StylePackage#getElementStyleConfig()
 * @model extendedMetaData="name='elementStyleConfig'"
 * @generated
 */
public interface ElementStyleConfig extends EObject {
	/**
	 * Returns the value of the '<em><b>Element Style</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.style.ElementStyle}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element Style</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element Style</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.style.StylePackage#getElementStyleConfig_ElementStyle()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='elementStyle'"
	 * @generated
	 */
	EList<ElementStyle> getElementStyle();

	/**
	 * Returns the value of the '<em><b>Current Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 当前选中的颜色主题编号
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Current Style</em>' attribute.
	 * @see #setCurrentStyle(String)
	 * @see com.founder.fix.bpmn2extensions.style.StylePackage#getElementStyleConfig_CurrentStyle()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getCurrentStyle();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.style.ElementStyleConfig#getCurrentStyle <em>Current Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Current Style</em>' attribute.
	 * @see #getCurrentStyle()
	 * @generated
	 */
	void setCurrentStyle(String value);

} // ElementStyleConfig
