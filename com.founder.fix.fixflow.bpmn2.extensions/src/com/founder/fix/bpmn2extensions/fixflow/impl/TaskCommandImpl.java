/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.fixflow.impl;

import com.founder.fix.bpmn2extensions.fixflow.Expression;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.fixflow.TaskCommand;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Task Command</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.TaskCommandImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.TaskCommandImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.TaskCommandImpl#getCommandType <em>Command Type</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.TaskCommandImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.TaskCommandImpl#getOrderId <em>Order Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.TaskCommandImpl#getIsVerification <em>Is Verification</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.TaskCommandImpl#getIsSaveData <em>Is Save Data</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.TaskCommandImpl#getIsSimulationRun <em>Is Simulation Run</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TaskCommandImpl extends EObjectImpl implements TaskCommand {
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
	 * The default value of the '{@link #getCommandType() <em>Command Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCommandType()
	 * @generated
	 * @ordered
	 */
	protected static final String COMMAND_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCommandType() <em>Command Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCommandType()
	 * @generated
	 * @ordered
	 */
	protected String commandType = COMMAND_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected Expression expression;

	/**
	 * The default value of the '{@link #getOrderId() <em>Order Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrderId()
	 * @generated
	 * @ordered
	 */
	protected static final int ORDER_ID_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getOrderId() <em>Order Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrderId()
	 * @generated
	 * @ordered
	 */
	protected int orderId = ORDER_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsVerification() <em>Is Verification</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsVerification()
	 * @generated
	 * @ordered
	 */
	protected static final String IS_VERIFICATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsVerification() <em>Is Verification</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsVerification()
	 * @generated
	 * @ordered
	 */
	protected String isVerification = IS_VERIFICATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsSaveData() <em>Is Save Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsSaveData()
	 * @generated
	 * @ordered
	 */
	protected static final String IS_SAVE_DATA_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsSaveData() <em>Is Save Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsSaveData()
	 * @generated
	 * @ordered
	 */
	protected String isSaveData = IS_SAVE_DATA_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsSimulationRun() <em>Is Simulation Run</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsSimulationRun()
	 * @generated
	 * @ordered
	 */
	protected static final String IS_SIMULATION_RUN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsSimulationRun() <em>Is Simulation Run</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsSimulationRun()
	 * @generated
	 * @ordered
	 */
	protected String isSimulationRun = IS_SIMULATION_RUN_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TaskCommandImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FixFlowPackage.Literals.TASK_COMMAND;
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
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.TASK_COMMAND__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.TASK_COMMAND__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCommandType() {
		return commandType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCommandType(String newCommandType) {
		String oldCommandType = commandType;
		commandType = newCommandType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.TASK_COMMAND__COMMAND_TYPE, oldCommandType, commandType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getExpression() {
		return expression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpression(Expression newExpression, NotificationChain msgs) {
		Expression oldExpression = expression;
		expression = newExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FixFlowPackage.TASK_COMMAND__EXPRESSION, oldExpression, newExpression);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpression(Expression newExpression) {
		if (newExpression != expression) {
			NotificationChain msgs = null;
			if (expression != null)
				msgs = ((InternalEObject)expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.TASK_COMMAND__EXPRESSION, null, msgs);
			if (newExpression != null)
				msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.TASK_COMMAND__EXPRESSION, null, msgs);
			msgs = basicSetExpression(newExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.TASK_COMMAND__EXPRESSION, newExpression, newExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrderId(int newOrderId) {
		int oldOrderId = orderId;
		orderId = newOrderId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.TASK_COMMAND__ORDER_ID, oldOrderId, orderId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIsVerification() {
		return isVerification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsVerification(String newIsVerification) {
		String oldIsVerification = isVerification;
		isVerification = newIsVerification;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.TASK_COMMAND__IS_VERIFICATION, oldIsVerification, isVerification));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIsSaveData() {
		return isSaveData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsSaveData(String newIsSaveData) {
		String oldIsSaveData = isSaveData;
		isSaveData = newIsSaveData;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.TASK_COMMAND__IS_SAVE_DATA, oldIsSaveData, isSaveData));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIsSimulationRun() {
		return isSimulationRun;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsSimulationRun(String newIsSimulationRun) {
		String oldIsSimulationRun = isSimulationRun;
		isSimulationRun = newIsSimulationRun;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.TASK_COMMAND__IS_SIMULATION_RUN, oldIsSimulationRun, isSimulationRun));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FixFlowPackage.TASK_COMMAND__EXPRESSION:
				return basicSetExpression(null, msgs);
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
			case FixFlowPackage.TASK_COMMAND__ID:
				return getId();
			case FixFlowPackage.TASK_COMMAND__NAME:
				return getName();
			case FixFlowPackage.TASK_COMMAND__COMMAND_TYPE:
				return getCommandType();
			case FixFlowPackage.TASK_COMMAND__EXPRESSION:
				return getExpression();
			case FixFlowPackage.TASK_COMMAND__ORDER_ID:
				return getOrderId();
			case FixFlowPackage.TASK_COMMAND__IS_VERIFICATION:
				return getIsVerification();
			case FixFlowPackage.TASK_COMMAND__IS_SAVE_DATA:
				return getIsSaveData();
			case FixFlowPackage.TASK_COMMAND__IS_SIMULATION_RUN:
				return getIsSimulationRun();
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
			case FixFlowPackage.TASK_COMMAND__ID:
				setId((String)newValue);
				return;
			case FixFlowPackage.TASK_COMMAND__NAME:
				setName((String)newValue);
				return;
			case FixFlowPackage.TASK_COMMAND__COMMAND_TYPE:
				setCommandType((String)newValue);
				return;
			case FixFlowPackage.TASK_COMMAND__EXPRESSION:
				setExpression((Expression)newValue);
				return;
			case FixFlowPackage.TASK_COMMAND__ORDER_ID:
				setOrderId((Integer)newValue);
				return;
			case FixFlowPackage.TASK_COMMAND__IS_VERIFICATION:
				setIsVerification((String)newValue);
				return;
			case FixFlowPackage.TASK_COMMAND__IS_SAVE_DATA:
				setIsSaveData((String)newValue);
				return;
			case FixFlowPackage.TASK_COMMAND__IS_SIMULATION_RUN:
				setIsSimulationRun((String)newValue);
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
			case FixFlowPackage.TASK_COMMAND__ID:
				setId(ID_EDEFAULT);
				return;
			case FixFlowPackage.TASK_COMMAND__NAME:
				setName(NAME_EDEFAULT);
				return;
			case FixFlowPackage.TASK_COMMAND__COMMAND_TYPE:
				setCommandType(COMMAND_TYPE_EDEFAULT);
				return;
			case FixFlowPackage.TASK_COMMAND__EXPRESSION:
				setExpression((Expression)null);
				return;
			case FixFlowPackage.TASK_COMMAND__ORDER_ID:
				setOrderId(ORDER_ID_EDEFAULT);
				return;
			case FixFlowPackage.TASK_COMMAND__IS_VERIFICATION:
				setIsVerification(IS_VERIFICATION_EDEFAULT);
				return;
			case FixFlowPackage.TASK_COMMAND__IS_SAVE_DATA:
				setIsSaveData(IS_SAVE_DATA_EDEFAULT);
				return;
			case FixFlowPackage.TASK_COMMAND__IS_SIMULATION_RUN:
				setIsSimulationRun(IS_SIMULATION_RUN_EDEFAULT);
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
			case FixFlowPackage.TASK_COMMAND__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case FixFlowPackage.TASK_COMMAND__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case FixFlowPackage.TASK_COMMAND__COMMAND_TYPE:
				return COMMAND_TYPE_EDEFAULT == null ? commandType != null : !COMMAND_TYPE_EDEFAULT.equals(commandType);
			case FixFlowPackage.TASK_COMMAND__EXPRESSION:
				return expression != null;
			case FixFlowPackage.TASK_COMMAND__ORDER_ID:
				return orderId != ORDER_ID_EDEFAULT;
			case FixFlowPackage.TASK_COMMAND__IS_VERIFICATION:
				return IS_VERIFICATION_EDEFAULT == null ? isVerification != null : !IS_VERIFICATION_EDEFAULT.equals(isVerification);
			case FixFlowPackage.TASK_COMMAND__IS_SAVE_DATA:
				return IS_SAVE_DATA_EDEFAULT == null ? isSaveData != null : !IS_SAVE_DATA_EDEFAULT.equals(isSaveData);
			case FixFlowPackage.TASK_COMMAND__IS_SIMULATION_RUN:
				return IS_SIMULATION_RUN_EDEFAULT == null ? isSimulationRun != null : !IS_SIMULATION_RUN_EDEFAULT.equals(isSimulationRun);
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
		result.append(", commandType: ");
		result.append(commandType);
		result.append(", orderId: ");
		result.append(orderId);
		result.append(", isVerification: ");
		result.append(isVerification);
		result.append(", isSaveData: ");
		result.append(isSaveData);
		result.append(", isSimulationRun: ");
		result.append(isSimulationRun);
		result.append(')');
		return result.toString();
	}

} //TaskCommandImpl
