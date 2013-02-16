/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Group Definition Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.GroupDefinitionConfig#getGroupDefinition <em>Group Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getGroupDefinitionConfig()
 * @model extendedMetaData="name='groupDefinitionConfig'"
 * @generated
 */
public interface GroupDefinitionConfig extends EObject {
	/**
	 * Returns the value of the '<em><b>Group Definition</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.coreconfig.GroupDefinition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group Definition</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group Definition</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getGroupDefinitionConfig_GroupDefinition()
	 * @model containment="true" required="true"
	 *        extendedMetaData="name='groupDefinition' kind='element'"
	 * @generated
	 */
	EList<GroupDefinition> getGroupDefinition();

} // GroupDefinitionConfig
