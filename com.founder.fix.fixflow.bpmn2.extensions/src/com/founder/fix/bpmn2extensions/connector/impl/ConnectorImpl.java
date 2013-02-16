/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.connector.impl;

import com.founder.fix.bpmn2extensions.connector.Connector;
import com.founder.fix.bpmn2extensions.connector.ConnectorPackage;
import com.founder.fix.bpmn2extensions.connector.Documentation;
import com.founder.fix.bpmn2extensions.connector.Outputs;
import com.founder.fix.bpmn2extensions.connector.Pages;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connector</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.impl.ConnectorImpl#getConnectorId <em>Connector Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.impl.ConnectorImpl#getIcon <em>Icon</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.impl.ConnectorImpl#getClassName <em>Class Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.impl.ConnectorImpl#getPackageName <em>Package Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.impl.ConnectorImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.impl.ConnectorImpl#getConnectorName <em>Connector Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.impl.ConnectorImpl#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.impl.ConnectorImpl#getPages <em>Pages</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.impl.ConnectorImpl#getConnectorNote <em>Connector Note</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.connector.impl.ConnectorImpl#getCategory <em>Category</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConnectorImpl extends EObjectImpl implements Connector {
	/**
	 * The default value of the '{@link #getConnectorId() <em>Connector Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectorId()
	 * @generated
	 * @ordered
	 */
	protected static final String CONNECTOR_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConnectorId() <em>Connector Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectorId()
	 * @generated
	 * @ordered
	 */
	protected String connectorId = CONNECTOR_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getIcon() <em>Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIcon()
	 * @generated
	 * @ordered
	 */
	protected static final String ICON_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIcon() <em>Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIcon()
	 * @generated
	 * @ordered
	 */
	protected String icon = ICON_EDEFAULT;

	/**
	 * The default value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
	protected String className = CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPackageName() <em>Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageName()
	 * @generated
	 * @ordered
	 */
	protected static final String PACKAGE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPackageName() <em>Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageName()
	 * @generated
	 * @ordered
	 */
	protected String packageName = PACKAGE_NAME_EDEFAULT;

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
	 * The default value of the '{@link #getConnectorName() <em>Connector Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectorName()
	 * @generated
	 * @ordered
	 */
	protected static final String CONNECTOR_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConnectorName() <em>Connector Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectorName()
	 * @generated
	 * @ordered
	 */
	protected String connectorName = CONNECTOR_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOutputs() <em>Outputs</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputs()
	 * @generated
	 * @ordered
	 */
	protected Outputs outputs;

	/**
	 * The cached value of the '{@link #getPages() <em>Pages</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPages()
	 * @generated
	 * @ordered
	 */
	protected Pages pages;

	/**
	 * The default value of the '{@link #getConnectorNote() <em>Connector Note</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectorNote()
	 * @generated
	 * @ordered
	 */
	protected static final String CONNECTOR_NOTE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConnectorNote() <em>Connector Note</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectorNote()
	 * @generated
	 * @ordered
	 */
	protected String connectorNote = CONNECTOR_NOTE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCategory() <em>Category</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCategory()
	 * @generated
	 * @ordered
	 */
	protected static final String CATEGORY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCategory() <em>Category</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCategory()
	 * @generated
	 * @ordered
	 */
	protected String category = CATEGORY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConnectorPackage.Literals.CONNECTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getConnectorId() {
		return connectorId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnectorId(String newConnectorId) {
		String oldConnectorId = connectorId;
		connectorId = newConnectorId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorPackage.CONNECTOR__CONNECTOR_ID, oldConnectorId, connectorId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIcon(String newIcon) {
		String oldIcon = icon;
		icon = newIcon;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorPackage.CONNECTOR__ICON, oldIcon, icon));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassName(String newClassName) {
		String oldClassName = className;
		className = newClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorPackage.CONNECTOR__CLASS_NAME, oldClassName, className));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackageName(String newPackageName) {
		String oldPackageName = packageName;
		packageName = newPackageName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorPackage.CONNECTOR__PACKAGE_NAME, oldPackageName, packageName));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConnectorPackage.CONNECTOR__DOCUMENTATION, oldDocumentation, newDocumentation);
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
				msgs = ((InternalEObject)documentation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConnectorPackage.CONNECTOR__DOCUMENTATION, null, msgs);
			if (newDocumentation != null)
				msgs = ((InternalEObject)newDocumentation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ConnectorPackage.CONNECTOR__DOCUMENTATION, null, msgs);
			msgs = basicSetDocumentation(newDocumentation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorPackage.CONNECTOR__DOCUMENTATION, newDocumentation, newDocumentation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getConnectorName() {
		return connectorName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnectorName(String newConnectorName) {
		String oldConnectorName = connectorName;
		connectorName = newConnectorName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorPackage.CONNECTOR__CONNECTOR_NAME, oldConnectorName, connectorName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Outputs getOutputs() {
		return outputs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOutputs(Outputs newOutputs, NotificationChain msgs) {
		Outputs oldOutputs = outputs;
		outputs = newOutputs;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConnectorPackage.CONNECTOR__OUTPUTS, oldOutputs, newOutputs);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutputs(Outputs newOutputs) {
		if (newOutputs != outputs) {
			NotificationChain msgs = null;
			if (outputs != null)
				msgs = ((InternalEObject)outputs).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConnectorPackage.CONNECTOR__OUTPUTS, null, msgs);
			if (newOutputs != null)
				msgs = ((InternalEObject)newOutputs).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ConnectorPackage.CONNECTOR__OUTPUTS, null, msgs);
			msgs = basicSetOutputs(newOutputs, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorPackage.CONNECTOR__OUTPUTS, newOutputs, newOutputs));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Pages getPages() {
		return pages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPages(Pages newPages, NotificationChain msgs) {
		Pages oldPages = pages;
		pages = newPages;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConnectorPackage.CONNECTOR__PAGES, oldPages, newPages);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPages(Pages newPages) {
		if (newPages != pages) {
			NotificationChain msgs = null;
			if (pages != null)
				msgs = ((InternalEObject)pages).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConnectorPackage.CONNECTOR__PAGES, null, msgs);
			if (newPages != null)
				msgs = ((InternalEObject)newPages).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ConnectorPackage.CONNECTOR__PAGES, null, msgs);
			msgs = basicSetPages(newPages, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorPackage.CONNECTOR__PAGES, newPages, newPages));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getConnectorNote() {
		return connectorNote;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnectorNote(String newConnectorNote) {
		String oldConnectorNote = connectorNote;
		connectorNote = newConnectorNote;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorPackage.CONNECTOR__CONNECTOR_NOTE, oldConnectorNote, connectorNote));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCategory(String newCategory) {
		String oldCategory = category;
		category = newCategory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorPackage.CONNECTOR__CATEGORY, oldCategory, category));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ConnectorPackage.CONNECTOR__DOCUMENTATION:
				return basicSetDocumentation(null, msgs);
			case ConnectorPackage.CONNECTOR__OUTPUTS:
				return basicSetOutputs(null, msgs);
			case ConnectorPackage.CONNECTOR__PAGES:
				return basicSetPages(null, msgs);
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
			case ConnectorPackage.CONNECTOR__CONNECTOR_ID:
				return getConnectorId();
			case ConnectorPackage.CONNECTOR__ICON:
				return getIcon();
			case ConnectorPackage.CONNECTOR__CLASS_NAME:
				return getClassName();
			case ConnectorPackage.CONNECTOR__PACKAGE_NAME:
				return getPackageName();
			case ConnectorPackage.CONNECTOR__DOCUMENTATION:
				return getDocumentation();
			case ConnectorPackage.CONNECTOR__CONNECTOR_NAME:
				return getConnectorName();
			case ConnectorPackage.CONNECTOR__OUTPUTS:
				return getOutputs();
			case ConnectorPackage.CONNECTOR__PAGES:
				return getPages();
			case ConnectorPackage.CONNECTOR__CONNECTOR_NOTE:
				return getConnectorNote();
			case ConnectorPackage.CONNECTOR__CATEGORY:
				return getCategory();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ConnectorPackage.CONNECTOR__CONNECTOR_ID:
				setConnectorId((String)newValue);
				return;
			case ConnectorPackage.CONNECTOR__ICON:
				setIcon((String)newValue);
				return;
			case ConnectorPackage.CONNECTOR__CLASS_NAME:
				setClassName((String)newValue);
				return;
			case ConnectorPackage.CONNECTOR__PACKAGE_NAME:
				setPackageName((String)newValue);
				return;
			case ConnectorPackage.CONNECTOR__DOCUMENTATION:
				setDocumentation((Documentation)newValue);
				return;
			case ConnectorPackage.CONNECTOR__CONNECTOR_NAME:
				setConnectorName((String)newValue);
				return;
			case ConnectorPackage.CONNECTOR__OUTPUTS:
				setOutputs((Outputs)newValue);
				return;
			case ConnectorPackage.CONNECTOR__PAGES:
				setPages((Pages)newValue);
				return;
			case ConnectorPackage.CONNECTOR__CONNECTOR_NOTE:
				setConnectorNote((String)newValue);
				return;
			case ConnectorPackage.CONNECTOR__CATEGORY:
				setCategory((String)newValue);
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
			case ConnectorPackage.CONNECTOR__CONNECTOR_ID:
				setConnectorId(CONNECTOR_ID_EDEFAULT);
				return;
			case ConnectorPackage.CONNECTOR__ICON:
				setIcon(ICON_EDEFAULT);
				return;
			case ConnectorPackage.CONNECTOR__CLASS_NAME:
				setClassName(CLASS_NAME_EDEFAULT);
				return;
			case ConnectorPackage.CONNECTOR__PACKAGE_NAME:
				setPackageName(PACKAGE_NAME_EDEFAULT);
				return;
			case ConnectorPackage.CONNECTOR__DOCUMENTATION:
				setDocumentation((Documentation)null);
				return;
			case ConnectorPackage.CONNECTOR__CONNECTOR_NAME:
				setConnectorName(CONNECTOR_NAME_EDEFAULT);
				return;
			case ConnectorPackage.CONNECTOR__OUTPUTS:
				setOutputs((Outputs)null);
				return;
			case ConnectorPackage.CONNECTOR__PAGES:
				setPages((Pages)null);
				return;
			case ConnectorPackage.CONNECTOR__CONNECTOR_NOTE:
				setConnectorNote(CONNECTOR_NOTE_EDEFAULT);
				return;
			case ConnectorPackage.CONNECTOR__CATEGORY:
				setCategory(CATEGORY_EDEFAULT);
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
			case ConnectorPackage.CONNECTOR__CONNECTOR_ID:
				return CONNECTOR_ID_EDEFAULT == null ? connectorId != null : !CONNECTOR_ID_EDEFAULT.equals(connectorId);
			case ConnectorPackage.CONNECTOR__ICON:
				return ICON_EDEFAULT == null ? icon != null : !ICON_EDEFAULT.equals(icon);
			case ConnectorPackage.CONNECTOR__CLASS_NAME:
				return CLASS_NAME_EDEFAULT == null ? className != null : !CLASS_NAME_EDEFAULT.equals(className);
			case ConnectorPackage.CONNECTOR__PACKAGE_NAME:
				return PACKAGE_NAME_EDEFAULT == null ? packageName != null : !PACKAGE_NAME_EDEFAULT.equals(packageName);
			case ConnectorPackage.CONNECTOR__DOCUMENTATION:
				return documentation != null;
			case ConnectorPackage.CONNECTOR__CONNECTOR_NAME:
				return CONNECTOR_NAME_EDEFAULT == null ? connectorName != null : !CONNECTOR_NAME_EDEFAULT.equals(connectorName);
			case ConnectorPackage.CONNECTOR__OUTPUTS:
				return outputs != null;
			case ConnectorPackage.CONNECTOR__PAGES:
				return pages != null;
			case ConnectorPackage.CONNECTOR__CONNECTOR_NOTE:
				return CONNECTOR_NOTE_EDEFAULT == null ? connectorNote != null : !CONNECTOR_NOTE_EDEFAULT.equals(connectorNote);
			case ConnectorPackage.CONNECTOR__CATEGORY:
				return CATEGORY_EDEFAULT == null ? category != null : !CATEGORY_EDEFAULT.equals(category);
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
		result.append(" (connectorId: ");
		result.append(connectorId);
		result.append(", icon: ");
		result.append(icon);
		result.append(", className: ");
		result.append(className);
		result.append(", packageName: ");
		result.append(packageName);
		result.append(", connectorName: ");
		result.append(connectorName);
		result.append(", connectorNote: ");
		result.append(connectorNote);
		result.append(", category: ");
		result.append(category);
		result.append(')');
		return result.toString();
	}

} //ConnectorImpl
