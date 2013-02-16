/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.fixflow.impl;

import com.founder.fix.bpmn2extensions.fixflow.FilterConditions;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.fixflow.ProcessInstanceVariable;
import com.founder.fix.bpmn2extensions.fixflow.ReceiveMessage;
import com.founder.fix.bpmn2extensions.fixflow.TokenVariable;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Receive Message</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.ReceiveMessageImpl#getMessageId <em>Message Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.ReceiveMessageImpl#getFilterConditions <em>Filter Conditions</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.ReceiveMessageImpl#getProcessInstanceVariable <em>Process Instance Variable</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.ReceiveMessageImpl#getTokenVariable <em>Token Variable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReceiveMessageImpl extends EObjectImpl implements ReceiveMessage {
	/**
	 * The default value of the '{@link #getMessageId() <em>Message Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageId()
	 * @generated
	 * @ordered
	 */
	protected static final String MESSAGE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMessageId() <em>Message Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageId()
	 * @generated
	 * @ordered
	 */
	protected String messageId = MESSAGE_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFilterConditions() <em>Filter Conditions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilterConditions()
	 * @generated
	 * @ordered
	 */
	protected FilterConditions filterConditions;

	/**
	 * The cached value of the '{@link #getProcessInstanceVariable() <em>Process Instance Variable</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessInstanceVariable()
	 * @generated
	 * @ordered
	 */
	protected ProcessInstanceVariable processInstanceVariable;

	/**
	 * The cached value of the '{@link #getTokenVariable() <em>Token Variable</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTokenVariable()
	 * @generated
	 * @ordered
	 */
	protected TokenVariable tokenVariable;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReceiveMessageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FixFlowPackage.Literals.RECEIVE_MESSAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMessageId(String newMessageId) {
		String oldMessageId = messageId;
		messageId = newMessageId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.RECEIVE_MESSAGE__MESSAGE_ID, oldMessageId, messageId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FilterConditions getFilterConditions() {
		return filterConditions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFilterConditions(FilterConditions newFilterConditions, NotificationChain msgs) {
		FilterConditions oldFilterConditions = filterConditions;
		filterConditions = newFilterConditions;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FixFlowPackage.RECEIVE_MESSAGE__FILTER_CONDITIONS, oldFilterConditions, newFilterConditions);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFilterConditions(FilterConditions newFilterConditions) {
		if (newFilterConditions != filterConditions) {
			NotificationChain msgs = null;
			if (filterConditions != null)
				msgs = ((InternalEObject)filterConditions).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.RECEIVE_MESSAGE__FILTER_CONDITIONS, null, msgs);
			if (newFilterConditions != null)
				msgs = ((InternalEObject)newFilterConditions).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.RECEIVE_MESSAGE__FILTER_CONDITIONS, null, msgs);
			msgs = basicSetFilterConditions(newFilterConditions, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.RECEIVE_MESSAGE__FILTER_CONDITIONS, newFilterConditions, newFilterConditions));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessInstanceVariable getProcessInstanceVariable() {
		return processInstanceVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProcessInstanceVariable(ProcessInstanceVariable newProcessInstanceVariable, NotificationChain msgs) {
		ProcessInstanceVariable oldProcessInstanceVariable = processInstanceVariable;
		processInstanceVariable = newProcessInstanceVariable;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FixFlowPackage.RECEIVE_MESSAGE__PROCESS_INSTANCE_VARIABLE, oldProcessInstanceVariable, newProcessInstanceVariable);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProcessInstanceVariable(ProcessInstanceVariable newProcessInstanceVariable) {
		if (newProcessInstanceVariable != processInstanceVariable) {
			NotificationChain msgs = null;
			if (processInstanceVariable != null)
				msgs = ((InternalEObject)processInstanceVariable).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.RECEIVE_MESSAGE__PROCESS_INSTANCE_VARIABLE, null, msgs);
			if (newProcessInstanceVariable != null)
				msgs = ((InternalEObject)newProcessInstanceVariable).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.RECEIVE_MESSAGE__PROCESS_INSTANCE_VARIABLE, null, msgs);
			msgs = basicSetProcessInstanceVariable(newProcessInstanceVariable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.RECEIVE_MESSAGE__PROCESS_INSTANCE_VARIABLE, newProcessInstanceVariable, newProcessInstanceVariable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TokenVariable getTokenVariable() {
		return tokenVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTokenVariable(TokenVariable newTokenVariable, NotificationChain msgs) {
		TokenVariable oldTokenVariable = tokenVariable;
		tokenVariable = newTokenVariable;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FixFlowPackage.RECEIVE_MESSAGE__TOKEN_VARIABLE, oldTokenVariable, newTokenVariable);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTokenVariable(TokenVariable newTokenVariable) {
		if (newTokenVariable != tokenVariable) {
			NotificationChain msgs = null;
			if (tokenVariable != null)
				msgs = ((InternalEObject)tokenVariable).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.RECEIVE_MESSAGE__TOKEN_VARIABLE, null, msgs);
			if (newTokenVariable != null)
				msgs = ((InternalEObject)newTokenVariable).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.RECEIVE_MESSAGE__TOKEN_VARIABLE, null, msgs);
			msgs = basicSetTokenVariable(newTokenVariable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.RECEIVE_MESSAGE__TOKEN_VARIABLE, newTokenVariable, newTokenVariable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FixFlowPackage.RECEIVE_MESSAGE__FILTER_CONDITIONS:
				return basicSetFilterConditions(null, msgs);
			case FixFlowPackage.RECEIVE_MESSAGE__PROCESS_INSTANCE_VARIABLE:
				return basicSetProcessInstanceVariable(null, msgs);
			case FixFlowPackage.RECEIVE_MESSAGE__TOKEN_VARIABLE:
				return basicSetTokenVariable(null, msgs);
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
			case FixFlowPackage.RECEIVE_MESSAGE__MESSAGE_ID:
				return getMessageId();
			case FixFlowPackage.RECEIVE_MESSAGE__FILTER_CONDITIONS:
				return getFilterConditions();
			case FixFlowPackage.RECEIVE_MESSAGE__PROCESS_INSTANCE_VARIABLE:
				return getProcessInstanceVariable();
			case FixFlowPackage.RECEIVE_MESSAGE__TOKEN_VARIABLE:
				return getTokenVariable();
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
			case FixFlowPackage.RECEIVE_MESSAGE__MESSAGE_ID:
				setMessageId((String)newValue);
				return;
			case FixFlowPackage.RECEIVE_MESSAGE__FILTER_CONDITIONS:
				setFilterConditions((FilterConditions)newValue);
				return;
			case FixFlowPackage.RECEIVE_MESSAGE__PROCESS_INSTANCE_VARIABLE:
				setProcessInstanceVariable((ProcessInstanceVariable)newValue);
				return;
			case FixFlowPackage.RECEIVE_MESSAGE__TOKEN_VARIABLE:
				setTokenVariable((TokenVariable)newValue);
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
			case FixFlowPackage.RECEIVE_MESSAGE__MESSAGE_ID:
				setMessageId(MESSAGE_ID_EDEFAULT);
				return;
			case FixFlowPackage.RECEIVE_MESSAGE__FILTER_CONDITIONS:
				setFilterConditions((FilterConditions)null);
				return;
			case FixFlowPackage.RECEIVE_MESSAGE__PROCESS_INSTANCE_VARIABLE:
				setProcessInstanceVariable((ProcessInstanceVariable)null);
				return;
			case FixFlowPackage.RECEIVE_MESSAGE__TOKEN_VARIABLE:
				setTokenVariable((TokenVariable)null);
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
			case FixFlowPackage.RECEIVE_MESSAGE__MESSAGE_ID:
				return MESSAGE_ID_EDEFAULT == null ? messageId != null : !MESSAGE_ID_EDEFAULT.equals(messageId);
			case FixFlowPackage.RECEIVE_MESSAGE__FILTER_CONDITIONS:
				return filterConditions != null;
			case FixFlowPackage.RECEIVE_MESSAGE__PROCESS_INSTANCE_VARIABLE:
				return processInstanceVariable != null;
			case FixFlowPackage.RECEIVE_MESSAGE__TOKEN_VARIABLE:
				return tokenVariable != null;
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
		result.append(" (messageId: ");
		result.append(messageId);
		result.append(')');
		return result.toString();
	}

} //ReceiveMessageImpl
