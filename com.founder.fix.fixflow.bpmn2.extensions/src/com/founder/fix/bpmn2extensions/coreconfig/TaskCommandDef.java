/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Task Command Def</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getId <em>Id</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getName <em>Name</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getCommand <em>Command</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getCmd <em>Cmd</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getIsEnabled <em>Is Enabled</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getType <em>Type</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getFilter <em>Filter</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getIsVerification <em>Is Verification</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getIsSaveData <em>Is Save Data</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getIsSimulationRun <em>Is Simulation Run</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getTaskCommandDef()
 * @model extendedMetaData="name='taskCommandDef'"
 * @generated
 */
public interface TaskCommandDef extends EObject {
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
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getTaskCommandDef_Id()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getId <em>Id</em>}' attribute.
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
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getTaskCommandDef_Name()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Command</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Command</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Command</em>' attribute.
	 * @see #setCommand(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getTaskCommandDef_Command()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getCommand();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getCommand <em>Command</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Command</em>' attribute.
	 * @see #getCommand()
	 * @generated
	 */
	void setCommand(String value);

	/**
	 * Returns the value of the '<em><b>Cmd</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cmd</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cmd</em>' attribute.
	 * @see #setCmd(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getTaskCommandDef_Cmd()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getCmd();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getCmd <em>Cmd</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cmd</em>' attribute.
	 * @see #getCmd()
	 * @generated
	 */
	void setCmd(String value);

	/**
	 * Returns the value of the '<em><b>Is Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Enabled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Enabled</em>' attribute.
	 * @see #setIsEnabled(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getTaskCommandDef_IsEnabled()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getIsEnabled();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getIsEnabled <em>Is Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Enabled</em>' attribute.
	 * @see #getIsEnabled()
	 * @generated
	 */
	void setIsEnabled(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getTaskCommandDef_Type()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Filter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filter</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filter</em>' attribute.
	 * @see #setFilter(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getTaskCommandDef_Filter()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getFilter();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getFilter <em>Filter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filter</em>' attribute.
	 * @see #getFilter()
	 * @generated
	 */
	void setFilter(String value);

	/**
	 * Returns the value of the '<em><b>Is Verification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Verification</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Verification</em>' attribute.
	 * @see #setIsVerification(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getTaskCommandDef_IsVerification()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getIsVerification();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getIsVerification <em>Is Verification</em>}' attribute.
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
	 * <p>
	 * If the meaning of the '<em>Is Save Data</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Save Data</em>' attribute.
	 * @see #setIsSaveData(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getTaskCommandDef_IsSaveData()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getIsSaveData();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getIsSaveData <em>Is Save Data</em>}' attribute.
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
	 * <p>
	 * If the meaning of the '<em>Is Simulation Run</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Simulation Run</em>' attribute.
	 * @see #setIsSimulationRun(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getTaskCommandDef_IsSimulationRun()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getIsSimulationRun();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef#getIsSimulationRun <em>Is Simulation Run</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Simulation Run</em>' attribute.
	 * @see #getIsSimulationRun()
	 * @generated
	 */
	void setIsSimulationRun(String value);

} // TaskCommandDef
