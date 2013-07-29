/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.fixflow;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Task Command</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 任务命令
 * 在UserTask上使用
 * 是用户对接收到的任务进行处理的入口点
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getId <em>Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getName <em>Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getCommandType <em>Command Type</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getExpression <em>Expression</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getOrderId <em>Order Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getIsVerification <em>Is Verification</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getIsSaveData <em>Is Save Data</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getIsSimulationRun <em>Is Simulation Run</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getParameterExpression <em>Parameter Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getTaskCommand()
 * @model
 * @generated
 */
public interface TaskCommand extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getTaskCommand_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getTaskCommand_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Command Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Command Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Command Type</em>' attribute.
	 * @see #setCommandType(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getTaskCommand_CommandType()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 * @generated
	 */
	String getCommandType();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getCommandType <em>Command Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Command Type</em>' attribute.
	 * @see #getCommandType()
	 * @generated
	 */
	void setCommandType(String value);

	/**
	 * Returns the value of the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' containment reference.
	 * @see #setExpression(Expression)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getTaskCommand_Expression()
	 * @model containment="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	Expression getExpression();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getExpression <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' containment reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(Expression value);

	/**
	 * Returns the value of the '<em><b>Order Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Order Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Order Id</em>' attribute.
	 * @see #setOrderId(int)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getTaskCommand_OrderId()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int"
	 *        extendedMetaData="namespace='' kind='attribute'"
	 * @generated
	 */
	int getOrderId();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getOrderId <em>Order Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Order Id</em>' attribute.
	 * @see #getOrderId()
	 * @generated
	 */
	void setOrderId(int value);

	/**
	 * Returns the value of the '<em><b>Is Verification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 是否验证
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Verification</em>' attribute.
	 * @see #setIsVerification(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getTaskCommand_IsVerification()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getIsVerification();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getIsVerification <em>Is Verification</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Verification</em>' attribute.
	 * @see #getIsVerification()
	 * @generated
	 */
	void setIsVerification(String value);

	/**
	 * Returns the value of the '<em><b>Is Save Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 是否保存表单数据
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Save Data</em>' attribute.
	 * @see #setIsSaveData(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getTaskCommand_IsSaveData()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getIsSaveData();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getIsSaveData <em>Is Save Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Save Data</em>' attribute.
	 * @see #getIsSaveData()
	 * @generated
	 */
	void setIsSaveData(String value);

	/**
	 * Returns the value of the '<em><b>Is Simulation Run</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 是否模拟运行
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Simulation Run</em>' attribute.
	 * @see #setIsSimulationRun(String)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getTaskCommand_IsSimulationRun()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getIsSimulationRun();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getIsSimulationRun <em>Is Simulation Run</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Simulation Run</em>' attribute.
	 * @see #getIsSimulationRun()
	 * @generated
	 */
	void setIsSimulationRun(String value);

	/**
	 * Returns the value of the '<em><b>Parameter Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Expression</em>' containment reference.
	 * @see #setParameterExpression(Expression)
	 * @see com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage#getTaskCommand_ParameterExpression()
	 * @model containment="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	Expression getParameterExpression();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.fixflow.TaskCommand#getParameterExpression <em>Parameter Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter Expression</em>' containment reference.
	 * @see #getParameterExpression()
	 * @generated
	 */
	void setParameterExpression(Expression value);

} // TaskCommand
