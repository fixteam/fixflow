/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.style.impl;

import com.founder.fix.bpmn2extensions.style.ElementStyle;
import com.founder.fix.bpmn2extensions.style.ElementStyleConfig;
import com.founder.fix.bpmn2extensions.style.StylePackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element Style Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.style.impl.ElementStyleConfigImpl#getElementStyle <em>Element Style</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.style.impl.ElementStyleConfigImpl#getCurrentStyle <em>Current Style</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ElementStyleConfigImpl extends EObjectImpl implements ElementStyleConfig {
	/**
	 * The cached value of the '{@link #getElementStyle() <em>Element Style</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementStyle()
	 * @generated
	 * @ordered
	 */
	protected EList<ElementStyle> elementStyle;

	/**
	 * The default value of the '{@link #getCurrentStyle() <em>Current Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurrentStyle()
	 * @generated
	 * @ordered
	 */
	protected static final String CURRENT_STYLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCurrentStyle() <em>Current Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurrentStyle()
	 * @generated
	 * @ordered
	 */
	protected String currentStyle = CURRENT_STYLE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElementStyleConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StylePackage.Literals.ELEMENT_STYLE_CONFIG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ElementStyle> getElementStyle() {
		if (elementStyle == null) {
			elementStyle = new EObjectContainmentEList<ElementStyle>(ElementStyle.class, this, StylePackage.ELEMENT_STYLE_CONFIG__ELEMENT_STYLE);
		}
		return elementStyle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCurrentStyle() {
		return currentStyle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCurrentStyle(String newCurrentStyle) {
		String oldCurrentStyle = currentStyle;
		currentStyle = newCurrentStyle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.ELEMENT_STYLE_CONFIG__CURRENT_STYLE, oldCurrentStyle, currentStyle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StylePackage.ELEMENT_STYLE_CONFIG__ELEMENT_STYLE:
				return ((InternalEList<?>)getElementStyle()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StylePackage.ELEMENT_STYLE_CONFIG__ELEMENT_STYLE:
				return getElementStyle();
			case StylePackage.ELEMENT_STYLE_CONFIG__CURRENT_STYLE:
				return getCurrentStyle();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case StylePackage.ELEMENT_STYLE_CONFIG__ELEMENT_STYLE:
				getElementStyle().clear();
				getElementStyle().addAll((Collection<? extends ElementStyle>)newValue);
				return;
			case StylePackage.ELEMENT_STYLE_CONFIG__CURRENT_STYLE:
				setCurrentStyle((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case StylePackage.ELEMENT_STYLE_CONFIG__ELEMENT_STYLE:
				getElementStyle().clear();
				return;
			case StylePackage.ELEMENT_STYLE_CONFIG__CURRENT_STYLE:
				setCurrentStyle(CURRENT_STYLE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case StylePackage.ELEMENT_STYLE_CONFIG__ELEMENT_STYLE:
				return elementStyle != null && !elementStyle.isEmpty();
			case StylePackage.ELEMENT_STYLE_CONFIG__CURRENT_STYLE:
				return CURRENT_STYLE_EDEFAULT == null ? currentStyle != null : !CURRENT_STYLE_EDEFAULT.equals(currentStyle);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (currentStyle: ");
		result.append(currentStyle);
		result.append(')');
		return result.toString();
	}

} //ElementStyleConfigImpl
