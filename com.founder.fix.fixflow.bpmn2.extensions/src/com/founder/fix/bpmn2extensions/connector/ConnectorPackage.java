/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.connector;

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
 * @see com.founder.fix.bpmn2extensions.connector.ConnectorFactory
 * @model kind="package"
 * @generated
 */
public interface ConnectorPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "connector";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.founderfix.com/connector";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "connector";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ConnectorPackage eINSTANCE = com.founder.fix.bpmn2extensions.connector.impl.ConnectorPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.connector.impl.ConnectorImpl <em>Connector</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.connector.impl.ConnectorImpl
	 * @see com.founder.fix.bpmn2extensions.connector.impl.ConnectorPackageImpl#getConnector()
	 * @generated
	 */
	int CONNECTOR = 0;

	/**
	 * The feature id for the '<em><b>Connector Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__CONNECTOR_ID = 0;

	/**
	 * The feature id for the '<em><b>Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__ICON = 1;

	/**
	 * The feature id for the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__CLASS_NAME = 2;

	/**
	 * The feature id for the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__PACKAGE_NAME = 3;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__DOCUMENTATION = 4;

	/**
	 * The feature id for the '<em><b>Connector Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__CONNECTOR_NAME = 5;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__OUTPUTS = 6;

	/**
	 * The feature id for the '<em><b>Pages</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__PAGES = 7;

	/**
	 * The feature id for the '<em><b>Connector Note</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__CONNECTOR_NOTE = 8;

	/**
	 * The feature id for the '<em><b>Category</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__CATEGORY = 9;

	/**
	 * The number of structural features of the '<em>Connector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_FEATURE_COUNT = 10;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.connector.impl.DocumentationImpl <em>Documentation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.connector.impl.DocumentationImpl
	 * @see com.founder.fix.bpmn2extensions.connector.impl.ConnectorPackageImpl#getDocumentation()
	 * @generated
	 */
	int DOCUMENTATION = 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTATION__VALUE = 0;

	/**
	 * The number of structural features of the '<em>Documentation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTATION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.connector.impl.PageImpl <em>Page</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.connector.impl.PageImpl
	 * @see com.founder.fix.bpmn2extensions.connector.impl.ConnectorPackageImpl#getPage()
	 * @generated
	 */
	int PAGE = 2;

	/**
	 * The feature id for the '<em><b>Page Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__PAGE_ID = 0;

	/**
	 * The feature id for the '<em><b>Page Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__PAGE_TITLE = 1;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__DOCUMENTATION = 2;

	/**
	 * The feature id for the '<em><b>Page Note</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__PAGE_NOTE = 3;

	/**
	 * The feature id for the '<em><b>Input Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__INPUT_PARAMETER = 4;

	/**
	 * The number of structural features of the '<em>Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.connector.impl.OutputsImpl <em>Outputs</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.connector.impl.OutputsImpl
	 * @see com.founder.fix.bpmn2extensions.connector.impl.ConnectorPackageImpl#getOutputs()
	 * @generated
	 */
	int OUTPUTS = 3;

	/**
	 * The feature id for the '<em><b>Output Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUTS__OUTPUT_PARAMETER = 0;

	/**
	 * The number of structural features of the '<em>Outputs</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUTS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.connector.impl.PagesImpl <em>Pages</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.connector.impl.PagesImpl
	 * @see com.founder.fix.bpmn2extensions.connector.impl.ConnectorPackageImpl#getPages()
	 * @generated
	 */
	int PAGES = 4;

	/**
	 * The feature id for the '<em><b>Page</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGES__PAGE = 0;

	/**
	 * The number of structural features of the '<em>Pages</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGES_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.connector.impl.InputParameterImpl <em>Input Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.connector.impl.InputParameterImpl
	 * @see com.founder.fix.bpmn2extensions.connector.impl.ConnectorPackageImpl#getInputParameter()
	 * @generated
	 */
	int INPUT_PARAMETER = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PARAMETER__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PARAMETER__NAME = 1;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PARAMETER__DATA_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Control Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PARAMETER__CONTROL_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Is Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PARAMETER__IS_REQUIRED = 4;

	/**
	 * The number of structural features of the '<em>Input Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PARAMETER_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.connector.impl.OutputParameterImpl <em>Output Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.connector.impl.OutputParameterImpl
	 * @see com.founder.fix.bpmn2extensions.connector.impl.ConnectorPackageImpl#getOutputParameter()
	 * @generated
	 */
	int OUTPUT_PARAMETER = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PARAMETER__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PARAMETER__NAME = 1;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PARAMETER__DATA_TYPE = 2;

	/**
	 * The number of structural features of the '<em>Output Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PARAMETER_FEATURE_COUNT = 3;


	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.connector.Connector <em>Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connector</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.Connector
	 * @generated
	 */
	EClass getConnector();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.connector.Connector#getConnectorId <em>Connector Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connector Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.Connector#getConnectorId()
	 * @see #getConnector()
	 * @generated
	 */
	EAttribute getConnector_ConnectorId();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.connector.Connector#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Icon</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.Connector#getIcon()
	 * @see #getConnector()
	 * @generated
	 */
	EAttribute getConnector_Icon();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.connector.Connector#getClassName <em>Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.Connector#getClassName()
	 * @see #getConnector()
	 * @generated
	 */
	EAttribute getConnector_ClassName();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.connector.Connector#getPackageName <em>Package Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Package Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.Connector#getPackageName()
	 * @see #getConnector()
	 * @generated
	 */
	EAttribute getConnector_PackageName();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.connector.Connector#getDocumentation <em>Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Documentation</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.Connector#getDocumentation()
	 * @see #getConnector()
	 * @generated
	 */
	EReference getConnector_Documentation();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.connector.Connector#getConnectorName <em>Connector Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connector Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.Connector#getConnectorName()
	 * @see #getConnector()
	 * @generated
	 */
	EAttribute getConnector_ConnectorName();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.connector.Connector#getOutputs <em>Outputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Outputs</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.Connector#getOutputs()
	 * @see #getConnector()
	 * @generated
	 */
	EReference getConnector_Outputs();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.connector.Connector#getPages <em>Pages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Pages</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.Connector#getPages()
	 * @see #getConnector()
	 * @generated
	 */
	EReference getConnector_Pages();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.connector.Connector#getConnectorNote <em>Connector Note</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connector Note</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.Connector#getConnectorNote()
	 * @see #getConnector()
	 * @generated
	 */
	EAttribute getConnector_ConnectorNote();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.connector.Connector#getCategory <em>Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Category</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.Connector#getCategory()
	 * @see #getConnector()
	 * @generated
	 */
	EAttribute getConnector_Category();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.connector.Documentation <em>Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Documentation</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.Documentation
	 * @generated
	 */
	EClass getDocumentation();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.connector.Documentation#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.Documentation#getValue()
	 * @see #getDocumentation()
	 * @generated
	 */
	EAttribute getDocumentation_Value();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.connector.Page <em>Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Page</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.Page
	 * @generated
	 */
	EClass getPage();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.connector.Page#getPageId <em>Page Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.Page#getPageId()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_PageId();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.connector.Page#getPageTitle <em>Page Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page Title</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.Page#getPageTitle()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_PageTitle();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.connector.Page#getDocumentation <em>Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Documentation</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.Page#getDocumentation()
	 * @see #getPage()
	 * @generated
	 */
	EReference getPage_Documentation();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.connector.Page#getPageNote <em>Page Note</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page Note</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.Page#getPageNote()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_PageNote();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.connector.Page#getInputParameter <em>Input Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Input Parameter</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.Page#getInputParameter()
	 * @see #getPage()
	 * @generated
	 */
	EReference getPage_InputParameter();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.connector.Outputs <em>Outputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Outputs</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.Outputs
	 * @generated
	 */
	EClass getOutputs();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.connector.Outputs#getOutputParameter <em>Output Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Output Parameter</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.Outputs#getOutputParameter()
	 * @see #getOutputs()
	 * @generated
	 */
	EReference getOutputs_OutputParameter();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.connector.Pages <em>Pages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pages</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.Pages
	 * @generated
	 */
	EClass getPages();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.connector.Pages#getPage <em>Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Page</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.Pages#getPage()
	 * @see #getPages()
	 * @generated
	 */
	EReference getPages_Page();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.connector.InputParameter <em>Input Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Input Parameter</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.InputParameter
	 * @generated
	 */
	EClass getInputParameter();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.connector.InputParameter#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.InputParameter#getId()
	 * @see #getInputParameter()
	 * @generated
	 */
	EAttribute getInputParameter_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.connector.InputParameter#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.InputParameter#getName()
	 * @see #getInputParameter()
	 * @generated
	 */
	EAttribute getInputParameter_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.connector.InputParameter#getDataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Type</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.InputParameter#getDataType()
	 * @see #getInputParameter()
	 * @generated
	 */
	EAttribute getInputParameter_DataType();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.connector.InputParameter#getControlType <em>Control Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Control Type</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.InputParameter#getControlType()
	 * @see #getInputParameter()
	 * @generated
	 */
	EAttribute getInputParameter_ControlType();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.connector.InputParameter#isIsRequired <em>Is Required</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Required</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.InputParameter#isIsRequired()
	 * @see #getInputParameter()
	 * @generated
	 */
	EAttribute getInputParameter_IsRequired();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.connector.OutputParameter <em>Output Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Output Parameter</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.OutputParameter
	 * @generated
	 */
	EClass getOutputParameter();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.connector.OutputParameter#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.OutputParameter#getId()
	 * @see #getOutputParameter()
	 * @generated
	 */
	EAttribute getOutputParameter_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.connector.OutputParameter#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.OutputParameter#getName()
	 * @see #getOutputParameter()
	 * @generated
	 */
	EAttribute getOutputParameter_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.connector.OutputParameter#getDataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Type</em>'.
	 * @see com.founder.fix.bpmn2extensions.connector.OutputParameter#getDataType()
	 * @see #getOutputParameter()
	 * @generated
	 */
	EAttribute getOutputParameter_DataType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ConnectorFactory getConnectorFactory();

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
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.connector.impl.ConnectorImpl <em>Connector</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.connector.impl.ConnectorImpl
		 * @see com.founder.fix.bpmn2extensions.connector.impl.ConnectorPackageImpl#getConnector()
		 * @generated
		 */
		EClass CONNECTOR = eINSTANCE.getConnector();

		/**
		 * The meta object literal for the '<em><b>Connector Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR__CONNECTOR_ID = eINSTANCE.getConnector_ConnectorId();

		/**
		 * The meta object literal for the '<em><b>Icon</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR__ICON = eINSTANCE.getConnector_Icon();

		/**
		 * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR__CLASS_NAME = eINSTANCE.getConnector_ClassName();

		/**
		 * The meta object literal for the '<em><b>Package Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR__PACKAGE_NAME = eINSTANCE.getConnector_PackageName();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR__DOCUMENTATION = eINSTANCE.getConnector_Documentation();

		/**
		 * The meta object literal for the '<em><b>Connector Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR__CONNECTOR_NAME = eINSTANCE.getConnector_ConnectorName();

		/**
		 * The meta object literal for the '<em><b>Outputs</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR__OUTPUTS = eINSTANCE.getConnector_Outputs();

		/**
		 * The meta object literal for the '<em><b>Pages</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR__PAGES = eINSTANCE.getConnector_Pages();

		/**
		 * The meta object literal for the '<em><b>Connector Note</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR__CONNECTOR_NOTE = eINSTANCE.getConnector_ConnectorNote();

		/**
		 * The meta object literal for the '<em><b>Category</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR__CATEGORY = eINSTANCE.getConnector_Category();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.connector.impl.DocumentationImpl <em>Documentation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.connector.impl.DocumentationImpl
		 * @see com.founder.fix.bpmn2extensions.connector.impl.ConnectorPackageImpl#getDocumentation()
		 * @generated
		 */
		EClass DOCUMENTATION = eINSTANCE.getDocumentation();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENTATION__VALUE = eINSTANCE.getDocumentation_Value();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.connector.impl.PageImpl <em>Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.connector.impl.PageImpl
		 * @see com.founder.fix.bpmn2extensions.connector.impl.ConnectorPackageImpl#getPage()
		 * @generated
		 */
		EClass PAGE = eINSTANCE.getPage();

		/**
		 * The meta object literal for the '<em><b>Page Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__PAGE_ID = eINSTANCE.getPage_PageId();

		/**
		 * The meta object literal for the '<em><b>Page Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__PAGE_TITLE = eINSTANCE.getPage_PageTitle();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAGE__DOCUMENTATION = eINSTANCE.getPage_Documentation();

		/**
		 * The meta object literal for the '<em><b>Page Note</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__PAGE_NOTE = eINSTANCE.getPage_PageNote();

		/**
		 * The meta object literal for the '<em><b>Input Parameter</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAGE__INPUT_PARAMETER = eINSTANCE.getPage_InputParameter();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.connector.impl.OutputsImpl <em>Outputs</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.connector.impl.OutputsImpl
		 * @see com.founder.fix.bpmn2extensions.connector.impl.ConnectorPackageImpl#getOutputs()
		 * @generated
		 */
		EClass OUTPUTS = eINSTANCE.getOutputs();

		/**
		 * The meta object literal for the '<em><b>Output Parameter</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUTPUTS__OUTPUT_PARAMETER = eINSTANCE.getOutputs_OutputParameter();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.connector.impl.PagesImpl <em>Pages</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.connector.impl.PagesImpl
		 * @see com.founder.fix.bpmn2extensions.connector.impl.ConnectorPackageImpl#getPages()
		 * @generated
		 */
		EClass PAGES = eINSTANCE.getPages();

		/**
		 * The meta object literal for the '<em><b>Page</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAGES__PAGE = eINSTANCE.getPages_Page();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.connector.impl.InputParameterImpl <em>Input Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.connector.impl.InputParameterImpl
		 * @see com.founder.fix.bpmn2extensions.connector.impl.ConnectorPackageImpl#getInputParameter()
		 * @generated
		 */
		EClass INPUT_PARAMETER = eINSTANCE.getInputParameter();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INPUT_PARAMETER__ID = eINSTANCE.getInputParameter_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INPUT_PARAMETER__NAME = eINSTANCE.getInputParameter_Name();

		/**
		 * The meta object literal for the '<em><b>Data Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INPUT_PARAMETER__DATA_TYPE = eINSTANCE.getInputParameter_DataType();

		/**
		 * The meta object literal for the '<em><b>Control Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INPUT_PARAMETER__CONTROL_TYPE = eINSTANCE.getInputParameter_ControlType();

		/**
		 * The meta object literal for the '<em><b>Is Required</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INPUT_PARAMETER__IS_REQUIRED = eINSTANCE.getInputParameter_IsRequired();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.connector.impl.OutputParameterImpl <em>Output Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.connector.impl.OutputParameterImpl
		 * @see com.founder.fix.bpmn2extensions.connector.impl.ConnectorPackageImpl#getOutputParameter()
		 * @generated
		 */
		EClass OUTPUT_PARAMETER = eINSTANCE.getOutputParameter();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OUTPUT_PARAMETER__ID = eINSTANCE.getOutputParameter_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OUTPUT_PARAMETER__NAME = eINSTANCE.getOutputParameter_Name();

		/**
		 * The meta object literal for the '<em><b>Data Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OUTPUT_PARAMETER__DATA_TYPE = eINSTANCE.getOutputParameter_DataType();

	}

} //ConnectorPackage
