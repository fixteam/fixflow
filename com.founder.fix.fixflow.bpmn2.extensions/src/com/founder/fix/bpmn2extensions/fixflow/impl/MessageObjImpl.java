/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.fixflow.impl;

import com.founder.fix.bpmn2extensions.fixflow.DataVariable;
import com.founder.fix.bpmn2extensions.fixflow.Documentation;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.fixflow.MessageObj;
import com.founder.fix.bpmn2extensions.fixflow.ProcessInstanceVariable;
import com.founder.fix.bpmn2extensions.fixflow.TokenVariable;

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
 * An implementation of the model object '<em><b>Message Obj</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.MessageObjImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.MessageObjImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.MessageObjImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.MessageObjImpl#getDataVariable <em>Data Variable</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.MessageObjImpl#getTargetProcess <em>Target Process</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.MessageObjImpl#getTargetNode <em>Target Node</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.MessageObjImpl#getProcessInstanceVariable <em>Process Instance Variable</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.MessageObjImpl#getTokenVariable <em>Token Variable</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.impl.MessageObjImpl#getMessageType <em>Message Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MessageObjImpl extends EObjectImpl implements MessageObj {
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
	 * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentation()
	 * @generated
	 * @ordered
	 */
	protected Documentation documentation;

	/**
	 * The cached value of the '{@link #getDataVariable() <em>Data Variable</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataVariable()
	 * @generated
	 * @ordered
	 */
	protected EList<DataVariable> dataVariable;

	/**
	 * The default value of the '{@link #getTargetProcess() <em>Target Process</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetProcess()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_PROCESS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetProcess() <em>Target Process</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetProcess()
	 * @generated
	 * @ordered
	 */
	protected String targetProcess = TARGET_PROCESS_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetNode() <em>Target Node</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetNode()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_NODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetNode() <em>Target Node</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetNode()
	 * @generated
	 * @ordered
	 */
	protected String targetNode = TARGET_NODE_EDEFAULT;

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
	 * The default value of the '{@link #getMessageType() <em>Message Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageType()
	 * @generated
	 * @ordered
	 */
	protected static final String MESSAGE_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMessageType() <em>Message Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageType()
	 * @generated
	 * @ordered
	 */
	protected String messageType = MESSAGE_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MessageObjImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FixFlowPackage.Literals.MESSAGE_OBJ;
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
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.MESSAGE_OBJ__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.MESSAGE_OBJ__NAME, oldName, name));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FixFlowPackage.MESSAGE_OBJ__DOCUMENTATION, oldDocumentation, newDocumentation);
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
				msgs = ((InternalEObject)documentation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.MESSAGE_OBJ__DOCUMENTATION, null, msgs);
			if (newDocumentation != null)
				msgs = ((InternalEObject)newDocumentation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.MESSAGE_OBJ__DOCUMENTATION, null, msgs);
			msgs = basicSetDocumentation(newDocumentation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.MESSAGE_OBJ__DOCUMENTATION, newDocumentation, newDocumentation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DataVariable> getDataVariable() {
		if (dataVariable == null) {
			dataVariable = new EObjectContainmentEList<DataVariable>(DataVariable.class, this, FixFlowPackage.MESSAGE_OBJ__DATA_VARIABLE);
		}
		return dataVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTargetProcess() {
		return targetProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetProcess(String newTargetProcess) {
		String oldTargetProcess = targetProcess;
		targetProcess = newTargetProcess;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.MESSAGE_OBJ__TARGET_PROCESS, oldTargetProcess, targetProcess));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTargetNode() {
		return targetNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetNode(String newTargetNode) {
		String oldTargetNode = targetNode;
		targetNode = newTargetNode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.MESSAGE_OBJ__TARGET_NODE, oldTargetNode, targetNode));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FixFlowPackage.MESSAGE_OBJ__PROCESS_INSTANCE_VARIABLE, oldProcessInstanceVariable, newProcessInstanceVariable);
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
				msgs = ((InternalEObject)processInstanceVariable).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.MESSAGE_OBJ__PROCESS_INSTANCE_VARIABLE, null, msgs);
			if (newProcessInstanceVariable != null)
				msgs = ((InternalEObject)newProcessInstanceVariable).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.MESSAGE_OBJ__PROCESS_INSTANCE_VARIABLE, null, msgs);
			msgs = basicSetProcessInstanceVariable(newProcessInstanceVariable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.MESSAGE_OBJ__PROCESS_INSTANCE_VARIABLE, newProcessInstanceVariable, newProcessInstanceVariable));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FixFlowPackage.MESSAGE_OBJ__TOKEN_VARIABLE, oldTokenVariable, newTokenVariable);
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
				msgs = ((InternalEObject)tokenVariable).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.MESSAGE_OBJ__TOKEN_VARIABLE, null, msgs);
			if (newTokenVariable != null)
				msgs = ((InternalEObject)newTokenVariable).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FixFlowPackage.MESSAGE_OBJ__TOKEN_VARIABLE, null, msgs);
			msgs = basicSetTokenVariable(newTokenVariable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.MESSAGE_OBJ__TOKEN_VARIABLE, newTokenVariable, newTokenVariable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMessageType() {
		return messageType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMessageType(String newMessageType) {
		String oldMessageType = messageType;
		messageType = newMessageType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FixFlowPackage.MESSAGE_OBJ__MESSAGE_TYPE, oldMessageType, messageType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FixFlowPackage.MESSAGE_OBJ__DOCUMENTATION:
				return basicSetDocumentation(null, msgs);
			case FixFlowPackage.MESSAGE_OBJ__DATA_VARIABLE:
				return ((InternalEList<?>)getDataVariable()).basicRemove(otherEnd, msgs);
			case FixFlowPackage.MESSAGE_OBJ__PROCESS_INSTANCE_VARIABLE:
				return basicSetProcessInstanceVariable(null, msgs);
			case FixFlowPackage.MESSAGE_OBJ__TOKEN_VARIABLE:
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
			case FixFlowPackage.MESSAGE_OBJ__ID:
				return getId();
			case FixFlowPackage.MESSAGE_OBJ__NAME:
				return getName();
			case FixFlowPackage.MESSAGE_OBJ__DOCUMENTATION:
				return getDocumentation();
			case FixFlowPackage.MESSAGE_OBJ__DATA_VARIABLE:
				return getDataVariable();
			case FixFlowPackage.MESSAGE_OBJ__TARGET_PROCESS:
				return getTargetProcess();
			case FixFlowPackage.MESSAGE_OBJ__TARGET_NODE:
				return getTargetNode();
			case FixFlowPackage.MESSAGE_OBJ__PROCESS_INSTANCE_VARIABLE:
				return getProcessInstanceVariable();
			case FixFlowPackage.MESSAGE_OBJ__TOKEN_VARIABLE:
				return getTokenVariable();
			case FixFlowPackage.MESSAGE_OBJ__MESSAGE_TYPE:
				return getMessageType();
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
			case FixFlowPackage.MESSAGE_OBJ__ID:
				setId((String)newValue);
				return;
			case FixFlowPackage.MESSAGE_OBJ__NAME:
				setName((String)newValue);
				return;
			case FixFlowPackage.MESSAGE_OBJ__DOCUMENTATION:
				setDocumentation((Documentation)newValue);
				return;
			case FixFlowPackage.MESSAGE_OBJ__DATA_VARIABLE:
				getDataVariable().clear();
				getDataVariable().addAll((Collection<? extends DataVariable>)newValue);
				return;
			case FixFlowPackage.MESSAGE_OBJ__TARGET_PROCESS:
				setTargetProcess((String)newValue);
				return;
			case FixFlowPackage.MESSAGE_OBJ__TARGET_NODE:
				setTargetNode((String)newValue);
				return;
			case FixFlowPackage.MESSAGE_OBJ__PROCESS_INSTANCE_VARIABLE:
				setProcessInstanceVariable((ProcessInstanceVariable)newValue);
				return;
			case FixFlowPackage.MESSAGE_OBJ__TOKEN_VARIABLE:
				setTokenVariable((TokenVariable)newValue);
				return;
			case FixFlowPackage.MESSAGE_OBJ__MESSAGE_TYPE:
				setMessageType((String)newValue);
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
			case FixFlowPackage.MESSAGE_OBJ__ID:
				setId(ID_EDEFAULT);
				return;
			case FixFlowPackage.MESSAGE_OBJ__NAME:
				setName(NAME_EDEFAULT);
				return;
			case FixFlowPackage.MESSAGE_OBJ__DOCUMENTATION:
				setDocumentation((Documentation)null);
				return;
			case FixFlowPackage.MESSAGE_OBJ__DATA_VARIABLE:
				getDataVariable().clear();
				return;
			case FixFlowPackage.MESSAGE_OBJ__TARGET_PROCESS:
				setTargetProcess(TARGET_PROCESS_EDEFAULT);
				return;
			case FixFlowPackage.MESSAGE_OBJ__TARGET_NODE:
				setTargetNode(TARGET_NODE_EDEFAULT);
				return;
			case FixFlowPackage.MESSAGE_OBJ__PROCESS_INSTANCE_VARIABLE:
				setProcessInstanceVariable((ProcessInstanceVariable)null);
				return;
			case FixFlowPackage.MESSAGE_OBJ__TOKEN_VARIABLE:
				setTokenVariable((TokenVariable)null);
				return;
			case FixFlowPackage.MESSAGE_OBJ__MESSAGE_TYPE:
				setMessageType(MESSAGE_TYPE_EDEFAULT);
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
			case FixFlowPackage.MESSAGE_OBJ__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case FixFlowPackage.MESSAGE_OBJ__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case FixFlowPackage.MESSAGE_OBJ__DOCUMENTATION:
				return documentation != null;
			case FixFlowPackage.MESSAGE_OBJ__DATA_VARIABLE:
				return dataVariable != null && !dataVariable.isEmpty();
			case FixFlowPackage.MESSAGE_OBJ__TARGET_PROCESS:
				return TARGET_PROCESS_EDEFAULT == null ? targetProcess != null : !TARGET_PROCESS_EDEFAULT.equals(targetProcess);
			case FixFlowPackage.MESSAGE_OBJ__TARGET_NODE:
				return TARGET_NODE_EDEFAULT == null ? targetNode != null : !TARGET_NODE_EDEFAULT.equals(targetNode);
			case FixFlowPackage.MESSAGE_OBJ__PROCESS_INSTANCE_VARIABLE:
				return processInstanceVariable != null;
			case FixFlowPackage.MESSAGE_OBJ__TOKEN_VARIABLE:
				return tokenVariable != null;
			case FixFlowPackage.MESSAGE_OBJ__MESSAGE_TYPE:
				return MESSAGE_TYPE_EDEFAULT == null ? messageType != null : !MESSAGE_TYPE_EDEFAULT.equals(messageType);
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
		result.append(", targetProcess: ");
		result.append(targetProcess);
		result.append(", targetNode: ");
		result.append(targetNode);
		result.append(", messageType: ");
		result.append(messageType);
		result.append(')');
		return result.toString();
	}

} //MessageObjImpl
