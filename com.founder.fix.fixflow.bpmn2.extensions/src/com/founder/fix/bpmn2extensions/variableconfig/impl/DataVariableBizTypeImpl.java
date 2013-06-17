/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.variableconfig.impl;

import com.founder.fix.bpmn2extensions.variableconfig.DataVariableBizType;
import com.founder.fix.bpmn2extensions.variableconfig.VariableconfigPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Variable Biz Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.variableconfig.impl.DataVariableBizTypeImpl#getTypeId <em>Type Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.variableconfig.impl.DataVariableBizTypeImpl#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.variableconfig.impl.DataVariableBizTypeImpl#getImg <em>Img</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataVariableBizTypeImpl extends EObjectImpl implements DataVariableBizType {
	/**
	 * The default value of the '{@link #getTypeId() <em>Type Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeId()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTypeId() <em>Type Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeId()
	 * @generated
	 * @ordered
	 */
	protected String typeId = TYPE_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getTypeName() <em>Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeName()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTypeName() <em>Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeName()
	 * @generated
	 * @ordered
	 */
	protected String typeName = TYPE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getImg() <em>Img</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImg()
	 * @generated
	 * @ordered
	 */
	protected static final String IMG_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getImg() <em>Img</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImg()
	 * @generated
	 * @ordered
	 */
	protected String img = IMG_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataVariableBizTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return VariableconfigPackage.Literals.DATA_VARIABLE_BIZ_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTypeId() {
		return typeId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeId(String newTypeId) {
		String oldTypeId = typeId;
		typeId = newTypeId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VariableconfigPackage.DATA_VARIABLE_BIZ_TYPE__TYPE_ID, oldTypeId, typeId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeName(String newTypeName) {
		String oldTypeName = typeName;
		typeName = newTypeName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VariableconfigPackage.DATA_VARIABLE_BIZ_TYPE__TYPE_NAME, oldTypeName, typeName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getImg() {
		return img;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImg(String newImg) {
		String oldImg = img;
		img = newImg;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VariableconfigPackage.DATA_VARIABLE_BIZ_TYPE__IMG, oldImg, img));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case VariableconfigPackage.DATA_VARIABLE_BIZ_TYPE__TYPE_ID:
				return getTypeId();
			case VariableconfigPackage.DATA_VARIABLE_BIZ_TYPE__TYPE_NAME:
				return getTypeName();
			case VariableconfigPackage.DATA_VARIABLE_BIZ_TYPE__IMG:
				return getImg();
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
			case VariableconfigPackage.DATA_VARIABLE_BIZ_TYPE__TYPE_ID:
				setTypeId((String)newValue);
				return;
			case VariableconfigPackage.DATA_VARIABLE_BIZ_TYPE__TYPE_NAME:
				setTypeName((String)newValue);
				return;
			case VariableconfigPackage.DATA_VARIABLE_BIZ_TYPE__IMG:
				setImg((String)newValue);
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
			case VariableconfigPackage.DATA_VARIABLE_BIZ_TYPE__TYPE_ID:
				setTypeId(TYPE_ID_EDEFAULT);
				return;
			case VariableconfigPackage.DATA_VARIABLE_BIZ_TYPE__TYPE_NAME:
				setTypeName(TYPE_NAME_EDEFAULT);
				return;
			case VariableconfigPackage.DATA_VARIABLE_BIZ_TYPE__IMG:
				setImg(IMG_EDEFAULT);
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
			case VariableconfigPackage.DATA_VARIABLE_BIZ_TYPE__TYPE_ID:
				return TYPE_ID_EDEFAULT == null ? typeId != null : !TYPE_ID_EDEFAULT.equals(typeId);
			case VariableconfigPackage.DATA_VARIABLE_BIZ_TYPE__TYPE_NAME:
				return TYPE_NAME_EDEFAULT == null ? typeName != null : !TYPE_NAME_EDEFAULT.equals(typeName);
			case VariableconfigPackage.DATA_VARIABLE_BIZ_TYPE__IMG:
				return IMG_EDEFAULT == null ? img != null : !IMG_EDEFAULT.equals(img);
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
		result.append(" (typeId: ");
		result.append(typeId);
		result.append(", typeName: ");
		result.append(typeName);
		result.append(", img: ");
		result.append(img);
		result.append(')');
		return result.toString();
	}

} //DataVariableBizTypeImpl
