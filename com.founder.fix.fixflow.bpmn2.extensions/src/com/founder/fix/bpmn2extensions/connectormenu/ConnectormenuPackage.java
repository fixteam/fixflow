/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.connectormenu;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.founder.fix.bpmn2extensions.connectormenu.ConnectormenuFactory
 * @model kind="package"
 * @generated
 */
public interface ConnectormenuPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "connectormenu";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.founderfix.com/connectormenu";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "connectormenu";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ConnectormenuPackage eINSTANCE = com.founder.fix.bpmn2extensions.connectormenu.impl.ConnectormenuPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.connectormenu.impl.MenuImpl <em>Menu</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.connectormenu.impl.MenuImpl
	 * @see com.founder.fix.bpmn2extensions.connectormenu.impl.ConnectormenuPackageImpl#getMenu()
	 * @generated
	 */
	int MENU = 0;

	/**
	 * The feature id for the '<em><b>Node</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU__NODE = 0;

	/**
	 * The number of structural features of the '<em>Menu</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.connectormenu.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.connectormenu.impl.NodeImpl
	 * @see com.founder.fix.bpmn2extensions.connectormenu.impl.ConnectormenuPackageImpl#getNode()
	 * @generated
	 */
	int NODE = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Ico</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__ICO = 2;

	/**
	 * The feature id for the '<em><b>Menu Connector</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__MENU_CONNECTOR = 3;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.connectormenu.impl.MenuConnectorImpl <em>Menu Connector</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.connectormenu.impl.MenuConnectorImpl
	 * @see com.founder.fix.bpmn2extensions.connectormenu.impl.ConnectormenuPackageImpl#getMenuConnector()
	 * @generated
	 */
	int MENU_CONNECTOR = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_CONNECTOR__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_CONNECTOR__NAME = 1;

	/**
	 * The feature id for the '<em><b>Note</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_CONNECTOR__NOTE = 2;

	/**
	 * The feature id for the '<em><b>Ico</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_CONNECTOR__ICO = 3;

	/**
	 * The number of structural features of the '<em>Menu Connector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_CONNECTOR_FEATURE_COUNT = 4;


	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.connectormenu.Menu <em>Menu</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Menu</em>'.
	 * @see com.founder.fix.bpmn2extensions.connectormenu.Menu
	 * @generated
	 */
	EClass getMenu();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.connectormenu.Menu#getNode <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Node</em>'.
	 * @see com.founder.fix.bpmn2extensions.connectormenu.Menu#getNode()
	 * @see #getMenu()
	 * @generated
	 */
	EReference getMenu_Node();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.connectormenu.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see com.founder.fix.bpmn2extensions.connectormenu.Node
	 * @generated
	 */
	EClass getNode();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.connectormenu.Node#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.connectormenu.Node#getId()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.connectormenu.Node#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.connectormenu.Node#getName()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.connectormenu.Node#getIco <em>Ico</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ico</em>'.
	 * @see com.founder.fix.bpmn2extensions.connectormenu.Node#getIco()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_Ico();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.connectormenu.Node#getMenuConnector <em>Menu Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Menu Connector</em>'.
	 * @see com.founder.fix.bpmn2extensions.connectormenu.Node#getMenuConnector()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_MenuConnector();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.connectormenu.MenuConnector <em>Menu Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Menu Connector</em>'.
	 * @see com.founder.fix.bpmn2extensions.connectormenu.MenuConnector
	 * @generated
	 */
	EClass getMenuConnector();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.connectormenu.MenuConnector#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.connectormenu.MenuConnector#getId()
	 * @see #getMenuConnector()
	 * @generated
	 */
	EAttribute getMenuConnector_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.connectormenu.MenuConnector#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.connectormenu.MenuConnector#getName()
	 * @see #getMenuConnector()
	 * @generated
	 */
	EAttribute getMenuConnector_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.connectormenu.MenuConnector#getNote <em>Note</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Note</em>'.
	 * @see com.founder.fix.bpmn2extensions.connectormenu.MenuConnector#getNote()
	 * @see #getMenuConnector()
	 * @generated
	 */
	EAttribute getMenuConnector_Note();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.connectormenu.MenuConnector#getIco <em>Ico</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ico</em>'.
	 * @see com.founder.fix.bpmn2extensions.connectormenu.MenuConnector#getIco()
	 * @see #getMenuConnector()
	 * @generated
	 */
	EAttribute getMenuConnector_Ico();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ConnectormenuFactory getConnectormenuFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.connectormenu.impl.MenuImpl <em>Menu</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.connectormenu.impl.MenuImpl
		 * @see com.founder.fix.bpmn2extensions.connectormenu.impl.ConnectormenuPackageImpl#getMenu()
		 * @generated
		 */
		EClass MENU = eINSTANCE.getMenu();

		/**
		 * The meta object literal for the '<em><b>Node</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MENU__NODE = eINSTANCE.getMenu_Node();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.connectormenu.impl.NodeImpl <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.connectormenu.impl.NodeImpl
		 * @see com.founder.fix.bpmn2extensions.connectormenu.impl.ConnectormenuPackageImpl#getNode()
		 * @generated
		 */
		EClass NODE = eINSTANCE.getNode();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__ID = eINSTANCE.getNode_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__NAME = eINSTANCE.getNode_Name();

		/**
		 * The meta object literal for the '<em><b>Ico</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__ICO = eINSTANCE.getNode_Ico();

		/**
		 * The meta object literal for the '<em><b>Menu Connector</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__MENU_CONNECTOR = eINSTANCE.getNode_MenuConnector();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.connectormenu.impl.MenuConnectorImpl <em>Menu Connector</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.connectormenu.impl.MenuConnectorImpl
		 * @see com.founder.fix.bpmn2extensions.connectormenu.impl.ConnectormenuPackageImpl#getMenuConnector()
		 * @generated
		 */
		EClass MENU_CONNECTOR = eINSTANCE.getMenuConnector();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MENU_CONNECTOR__ID = eINSTANCE.getMenuConnector_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MENU_CONNECTOR__NAME = eINSTANCE.getMenuConnector_Name();

		/**
		 * The meta object literal for the '<em><b>Note</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MENU_CONNECTOR__NOTE = eINSTANCE.getMenuConnector_Note();

		/**
		 * The meta object literal for the '<em><b>Ico</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MENU_CONNECTOR__ICO = eINSTANCE.getMenuConnector_Ico();

	}

} //ConnectormenuPackage
