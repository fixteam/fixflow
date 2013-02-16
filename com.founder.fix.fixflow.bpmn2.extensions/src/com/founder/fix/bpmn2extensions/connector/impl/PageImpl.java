/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.connector.impl;

import com.founder.fix.bpmn2extensions.connector.ConnectorPackage;
import com.founder.fix.bpmn2extensions.connector.Documentation;
import com.founder.fix.bpmn2extensions.connector.InputParameter;
import com.founder.fix.bpmn2extensions.connector.Page;

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
 * An implementation of the model object '<em><b>Page</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.impl.PageImpl#getPageId <em>Page Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.impl.PageImpl#getPageTitle <em>Page Title</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.impl.PageImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.impl.PageImpl#getPageNote <em>Page Note</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.impl.PageImpl#getInputParameter <em>Input Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PageImpl extends EObjectImpl implements Page {
	/**
	 * The default value of the '{@link #getPageId() <em>Page Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageId()
	 * @generated
	 * @ordered
	 */
	protected static final String PAGE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPageId() <em>Page Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageId()
	 * @generated
	 * @ordered
	 */
	protected String pageId = PAGE_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getPageTitle() <em>Page Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageTitle()
	 * @generated
	 * @ordered
	 */
	protected static final String PAGE_TITLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPageTitle() <em>Page Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageTitle()
	 * @generated
	 * @ordered
	 */
	protected String pageTitle = PAGE_TITLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentation()
	 * @generated
	 * @ordered
	 */
	protected Documentation documentation;

	/**
	 * The default value of the '{@link #getPageNote() <em>Page Note</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageNote()
	 * @generated
	 * @ordered
	 */
	protected static final String PAGE_NOTE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPageNote() <em>Page Note</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageNote()
	 * @generated
	 * @ordered
	 */
	protected String pageNote = PAGE_NOTE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInputParameter() <em>Input Parameter</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputParameter()
	 * @generated
	 * @ordered
	 */
	protected EList<InputParameter> inputParameter;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConnectorPackage.Literals.PAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPageId() {
		return pageId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPageId(String newPageId) {
		String oldPageId = pageId;
		pageId = newPageId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorPackage.PAGE__PAGE_ID, oldPageId, pageId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPageTitle() {
		return pageTitle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPageTitle(String newPageTitle) {
		String oldPageTitle = pageTitle;
		pageTitle = newPageTitle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorPackage.PAGE__PAGE_TITLE, oldPageTitle, pageTitle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Documentation getDocumentation() {
		return documentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDocumentation(Documentation newDocumentation, NotificationChain msgs) {
		Documentation oldDocumentation = documentation;
		documentation = newDocumentation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConnectorPackage.PAGE__DOCUMENTATION, oldDocumentation, newDocumentation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDocumentation(Documentation newDocumentation) {
		if (newDocumentation != documentation) {
			NotificationChain msgs = null;
			if (documentation != null)
				msgs = ((InternalEObject)documentation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConnectorPackage.PAGE__DOCUMENTATION, null, msgs);
			if (newDocumentation != null)
				msgs = ((InternalEObject)newDocumentation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ConnectorPackage.PAGE__DOCUMENTATION, null, msgs);
			msgs = basicSetDocumentation(newDocumentation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorPackage.PAGE__DOCUMENTATION, newDocumentation, newDocumentation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPageNote() {
		return pageNote;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPageNote(String newPageNote) {
		String oldPageNote = pageNote;
		pageNote = newPageNote;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorPackage.PAGE__PAGE_NOTE, oldPageNote, pageNote));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InputParameter> getInputParameter() {
		if (inputParameter == null) {
			inputParameter = new EObjectContainmentEList<InputParameter>(InputParameter.class, this, ConnectorPackage.PAGE__INPUT_PARAMETER);
		}
		return inputParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ConnectorPackage.PAGE__DOCUMENTATION:
				return basicSetDocumentation(null, msgs);
			case ConnectorPackage.PAGE__INPUT_PARAMETER:
				return ((InternalEList<?>)getInputParameter()).basicRemove(otherEnd, msgs);
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
			case ConnectorPackage.PAGE__PAGE_ID:
				return getPageId();
			case ConnectorPackage.PAGE__PAGE_TITLE:
				return getPageTitle();
			case ConnectorPackage.PAGE__DOCUMENTATION:
				return getDocumentation();
			case ConnectorPackage.PAGE__PAGE_NOTE:
				return getPageNote();
			case ConnectorPackage.PAGE__INPUT_PARAMETER:
				return getInputParameter();
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
			case ConnectorPackage.PAGE__PAGE_ID:
				setPageId((String)newValue);
				return;
			case ConnectorPackage.PAGE__PAGE_TITLE:
				setPageTitle((String)newValue);
				return;
			case ConnectorPackage.PAGE__DOCUMENTATION:
				setDocumentation((Documentation)newValue);
				return;
			case ConnectorPackage.PAGE__PAGE_NOTE:
				setPageNote((String)newValue);
				return;
			case ConnectorPackage.PAGE__INPUT_PARAMETER:
				getInputParameter().clear();
				getInputParameter().addAll((Collection<? extends InputParameter>)newValue);
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
			case ConnectorPackage.PAGE__PAGE_ID:
				setPageId(PAGE_ID_EDEFAULT);
				return;
			case ConnectorPackage.PAGE__PAGE_TITLE:
				setPageTitle(PAGE_TITLE_EDEFAULT);
				return;
			case ConnectorPackage.PAGE__DOCUMENTATION:
				setDocumentation((Documentation)null);
				return;
			case ConnectorPackage.PAGE__PAGE_NOTE:
				setPageNote(PAGE_NOTE_EDEFAULT);
				return;
			case ConnectorPackage.PAGE__INPUT_PARAMETER:
				getInputParameter().clear();
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
			case ConnectorPackage.PAGE__PAGE_ID:
				return PAGE_ID_EDEFAULT == null ? pageId != null : !PAGE_ID_EDEFAULT.equals(pageId);
			case ConnectorPackage.PAGE__PAGE_TITLE:
				return PAGE_TITLE_EDEFAULT == null ? pageTitle != null : !PAGE_TITLE_EDEFAULT.equals(pageTitle);
			case ConnectorPackage.PAGE__DOCUMENTATION:
				return documentation != null;
			case ConnectorPackage.PAGE__PAGE_NOTE:
				return PAGE_NOTE_EDEFAULT == null ? pageNote != null : !PAGE_NOTE_EDEFAULT.equals(pageNote);
			case ConnectorPackage.PAGE__INPUT_PARAMETER:
				return inputParameter != null && !inputParameter.isEmpty();
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
		result.append(" (pageId: ");
		result.append(pageId);
		result.append(", pageTitle: ");
		result.append(pageTitle);
		result.append(", pageNote: ");
		result.append(pageNote);
		result.append(')');
		return result.toString();
	}

} //PageImpl
