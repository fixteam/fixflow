/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.connectormenu.impl;

import com.founder.fix.bpmn2extensions.connectormenu.ConnectormenuPackage;
import com.founder.fix.bpmn2extensions.connectormenu.MenuConnector;
import com.founder.fix.bpmn2extensions.connectormenu.Node;

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
 * An implementation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.connectormenu.impl.NodeImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connectormenu.impl.NodeImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connectormenu.impl.NodeImpl#getIco <em>Ico</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connectormenu.impl.NodeImpl#getMenuConnector <em>Menu Connector</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodeImpl extends EObjectImpl implements Node {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getIco() <em>Ico</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIco()
	 * @generated
	 * @ordered
	 */
	protected static final String ICO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIco() <em>Ico</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIco()
	 * @generated
	 * @ordered
	 */
	protected String ico = ICO_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMenuConnector() <em>Menu Connector</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMenuConnector()
	 * @generated
	 * @ordered
	 */
	protected EList<MenuConnector> menuConnector;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConnectormenuPackage.Literals.NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectormenuPackage.NODE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectormenuPackage.NODE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIco() {
		return ico;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIco(String newIco) {
		String oldIco = ico;
		ico = newIco;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectormenuPackage.NODE__ICO, oldIco, ico));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MenuConnector> getMenuConnector() {
		if (menuConnector == null) {
			menuConnector = new EObjectContainmentEList<MenuConnector>(MenuConnector.class, this, ConnectormenuPackage.NODE__MENU_CONNECTOR);
		}
		return menuConnector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ConnectormenuPackage.NODE__MENU_CONNECTOR:
				return ((InternalEList<?>)getMenuConnector()).basicRemove(otherEnd, msgs);
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
			case ConnectormenuPackage.NODE__ID:
				return getId();
			case ConnectormenuPackage.NODE__NAME:
				return getName();
			case ConnectormenuPackage.NODE__ICO:
				return getIco();
			case ConnectormenuPackage.NODE__MENU_CONNECTOR:
				return getMenuConnector();
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
			case ConnectormenuPackage.NODE__ID:
				setId((String)newValue);
				return;
			case ConnectormenuPackage.NODE__NAME:
				setName((String)newValue);
				return;
			case ConnectormenuPackage.NODE__ICO:
				setIco((String)newValue);
				return;
			case ConnectormenuPackage.NODE__MENU_CONNECTOR:
				getMenuConnector().clear();
				getMenuConnector().addAll((Collection<? extends MenuConnector>)newValue);
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
			case ConnectormenuPackage.NODE__ID:
				setId(ID_EDEFAULT);
				return;
			case ConnectormenuPackage.NODE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ConnectormenuPackage.NODE__ICO:
				setIco(ICO_EDEFAULT);
				return;
			case ConnectormenuPackage.NODE__MENU_CONNECTOR:
				getMenuConnector().clear();
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
			case ConnectormenuPackage.NODE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case ConnectormenuPackage.NODE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ConnectormenuPackage.NODE__ICO:
				return ICO_EDEFAULT == null ? ico != null : !ICO_EDEFAULT.equals(ico);
			case ConnectormenuPackage.NODE__MENU_CONNECTOR:
				return menuConnector != null && !menuConnector.isEmpty();
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
		result.append(" (id: ");
		result.append(id);
		result.append(", name: ");
		result.append(name);
		result.append(", ico: ");
		result.append(ico);
		result.append(')');
		return result.toString();
	}

} //NodeImpl
