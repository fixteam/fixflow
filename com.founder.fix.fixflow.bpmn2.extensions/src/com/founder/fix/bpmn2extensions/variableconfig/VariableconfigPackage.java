/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.variableconfig;

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
 * <!-- begin-model-doc -->
 * 此XML Schema定义的BPMN 2.0的扩展元素是Founder公司拓展的属性。
 * 		
 * <!-- end-model-doc -->
 * @see com.founder.fix.bpmn2extensions.variableconfig.VariableconfigFactory
 * @model kind="package"
 * @generated
 */
public interface VariableconfigPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "variableconfig";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.founderfix.com/variableconfig";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "variableconfig";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	VariableconfigPackage eINSTANCE = com.founder.fix.bpmn2extensions.variableconfig.impl.VariableconfigPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.variableconfig.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.DocumentRootImpl
	 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.VariableconfigPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 0;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Data Variable Config</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DATA_VARIABLE_CONFIG = 3;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.variableconfig.impl.DataVariableConfigImpl <em>Data Variable Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.DataVariableConfigImpl
	 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.VariableconfigPackageImpl#getDataVariableConfig()
	 * @generated
	 */
	int DATA_VARIABLE_CONFIG = 1;

	/**
	 * The feature id for the '<em><b>Data Variable Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_VARIABLE_CONFIG__DATA_VARIABLE_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Fix Flow Data Variable</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_VARIABLE_CONFIG__FIX_FLOW_DATA_VARIABLE = 1;

	/**
	 * The feature id for the '<em><b>Data Variable Data Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_VARIABLE_CONFIG__DATA_VARIABLE_DATA_TYPE = 2;

	/**
	 * The number of structural features of the '<em>Data Variable Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_VARIABLE_CONFIG_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.variableconfig.impl.DataVariableTypeImpl <em>Data Variable Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.DataVariableTypeImpl
	 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.VariableconfigPackageImpl#getDataVariableType()
	 * @generated
	 */
	int DATA_VARIABLE_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_VARIABLE_TYPE__TYPE = 0;

	/**
	 * The number of structural features of the '<em>Data Variable Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_VARIABLE_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.variableconfig.impl.TypeImpl <em>Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.TypeImpl
	 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.VariableconfigPackageImpl#getType()
	 * @generated
	 */
	int TYPE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE__ID = 1;

	/**
	 * The number of structural features of the '<em>Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.variableconfig.impl.DataVariableDataTypeImpl <em>Data Variable Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.DataVariableDataTypeImpl
	 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.VariableconfigPackageImpl#getDataVariableDataType()
	 * @generated
	 */
	int DATA_VARIABLE_DATA_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Data Type Def</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_VARIABLE_DATA_TYPE__DATA_TYPE_DEF = 0;

	/**
	 * The number of structural features of the '<em>Data Variable Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_VARIABLE_DATA_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.variableconfig.impl.DataTypeDefImpl <em>Data Type Def</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.DataTypeDefImpl
	 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.VariableconfigPackageImpl#getDataTypeDef()
	 * @generated
	 */
	int DATA_TYPE_DEF = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_DEF__NAME = 0;

	/**
	 * The feature id for the '<em><b>Type Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_DEF__TYPE_VALUE = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_DEF__ID = 2;

	/**
	 * The number of structural features of the '<em>Data Type Def</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_DEF_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.variableconfig.impl.FixFlowDataVariableImpl <em>Fix Flow Data Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.FixFlowDataVariableImpl
	 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.VariableconfigPackageImpl#getFixFlowDataVariable()
	 * @generated
	 */
	int FIX_FLOW_DATA_VARIABLE = 6;

	/**
	 * The feature id for the '<em><b>Data Variable Def</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_FLOW_DATA_VARIABLE__DATA_VARIABLE_DEF = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_FLOW_DATA_VARIABLE__TYPE = 1;

	/**
	 * The number of structural features of the '<em>Fix Flow Data Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIX_FLOW_DATA_VARIABLE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.founder.fix.bpmn2extensions.variableconfig.impl.DataVariableDefImpl <em>Data Variable Def</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.DataVariableDefImpl
	 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.VariableconfigPackageImpl#getDataVariableDef()
	 * @generated
	 */
	int DATA_VARIABLE_DEF = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_VARIABLE_DEF__NAME = 0;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_VARIABLE_DEF__DATA_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_VARIABLE_DEF__VALUE = 2;

	/**
	 * The feature id for the '<em><b>Doc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_VARIABLE_DEF__DOC = 3;

	/**
	 * The number of structural features of the '<em>Data Variable Def</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_VARIABLE_DEF_FEATURE_COUNT = 4;


	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.variableconfig.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link com.founder.fix.bpmn2extensions.variableconfig.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link com.founder.fix.bpmn2extensions.variableconfig.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link com.founder.fix.bpmn2extensions.variableconfig.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.variableconfig.DocumentRoot#getDataVariableConfig <em>Data Variable Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Variable Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DocumentRoot#getDataVariableConfig()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_DataVariableConfig();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableConfig <em>Data Variable Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Variable Config</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DataVariableConfig
	 * @generated
	 */
	EClass getDataVariableConfig();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableConfig#getDataVariableType <em>Data Variable Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data Variable Type</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DataVariableConfig#getDataVariableType()
	 * @see #getDataVariableConfig()
	 * @generated
	 */
	EReference getDataVariableConfig_DataVariableType();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableConfig#getFixFlowDataVariable <em>Fix Flow Data Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fix Flow Data Variable</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DataVariableConfig#getFixFlowDataVariable()
	 * @see #getDataVariableConfig()
	 * @generated
	 */
	EReference getDataVariableConfig_FixFlowDataVariable();

	/**
	 * Returns the meta object for the containment reference '{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableConfig#getDataVariableDataType <em>Data Variable Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data Variable Data Type</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DataVariableConfig#getDataVariableDataType()
	 * @see #getDataVariableConfig()
	 * @generated
	 */
	EReference getDataVariableConfig_DataVariableDataType();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableType <em>Data Variable Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Variable Type</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DataVariableType
	 * @generated
	 */
	EClass getDataVariableType();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Type</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DataVariableType#getType()
	 * @see #getDataVariableType()
	 * @generated
	 */
	EReference getDataVariableType_Type();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.variableconfig.Type <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.Type
	 * @generated
	 */
	EClass getType();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.variableconfig.Type#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.Type#getName()
	 * @see #getType()
	 * @generated
	 */
	EAttribute getType_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.variableconfig.Type#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.Type#getId()
	 * @see #getType()
	 * @generated
	 */
	EAttribute getType_Id();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableDataType <em>Data Variable Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Variable Data Type</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DataVariableDataType
	 * @generated
	 */
	EClass getDataVariableDataType();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableDataType#getDataTypeDef <em>Data Type Def</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Type Def</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DataVariableDataType#getDataTypeDef()
	 * @see #getDataVariableDataType()
	 * @generated
	 */
	EReference getDataVariableDataType_DataTypeDef();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.variableconfig.DataTypeDef <em>Data Type Def</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Type Def</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DataTypeDef
	 * @generated
	 */
	EClass getDataTypeDef();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.variableconfig.DataTypeDef#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DataTypeDef#getName()
	 * @see #getDataTypeDef()
	 * @generated
	 */
	EAttribute getDataTypeDef_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.variableconfig.DataTypeDef#getTypeValue <em>Type Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Value</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DataTypeDef#getTypeValue()
	 * @see #getDataTypeDef()
	 * @generated
	 */
	EAttribute getDataTypeDef_TypeValue();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.variableconfig.DataTypeDef#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DataTypeDef#getId()
	 * @see #getDataTypeDef()
	 * @generated
	 */
	EAttribute getDataTypeDef_Id();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.variableconfig.FixFlowDataVariable <em>Fix Flow Data Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fix Flow Data Variable</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.FixFlowDataVariable
	 * @generated
	 */
	EClass getFixFlowDataVariable();

	/**
	 * Returns the meta object for the containment reference list '{@link com.founder.fix.bpmn2extensions.variableconfig.FixFlowDataVariable#getDataVariableDef <em>Data Variable Def</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Variable Def</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.FixFlowDataVariable#getDataVariableDef()
	 * @see #getFixFlowDataVariable()
	 * @generated
	 */
	EReference getFixFlowDataVariable_DataVariableDef();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.variableconfig.FixFlowDataVariable#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.FixFlowDataVariable#getType()
	 * @see #getFixFlowDataVariable()
	 * @generated
	 */
	EAttribute getFixFlowDataVariable_Type();

	/**
	 * Returns the meta object for class '{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableDef <em>Data Variable Def</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Variable Def</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DataVariableDef
	 * @generated
	 */
	EClass getDataVariableDef();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableDef#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DataVariableDef#getName()
	 * @see #getDataVariableDef()
	 * @generated
	 */
	EAttribute getDataVariableDef_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableDef#getDataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Type</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DataVariableDef#getDataType()
	 * @see #getDataVariableDef()
	 * @generated
	 */
	EAttribute getDataVariableDef_DataType();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableDef#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DataVariableDef#getValue()
	 * @see #getDataVariableDef()
	 * @generated
	 */
	EAttribute getDataVariableDef_Value();

	/**
	 * Returns the meta object for the attribute '{@link com.founder.fix.bpmn2extensions.variableconfig.DataVariableDef#getDoc <em>Doc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Doc</em>'.
	 * @see com.founder.fix.bpmn2extensions.variableconfig.DataVariableDef#getDoc()
	 * @see #getDataVariableDef()
	 * @generated
	 */
	EAttribute getDataVariableDef_Doc();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	VariableconfigFactory getVariableconfigFactory();

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
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.variableconfig.impl.DocumentRootImpl <em>Document Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.DocumentRootImpl
		 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.VariableconfigPackageImpl#getDocumentRoot()
		 * @generated
		 */
		EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>Data Variable Config</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__DATA_VARIABLE_CONFIG = eINSTANCE.getDocumentRoot_DataVariableConfig();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.variableconfig.impl.DataVariableConfigImpl <em>Data Variable Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.DataVariableConfigImpl
		 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.VariableconfigPackageImpl#getDataVariableConfig()
		 * @generated
		 */
		EClass DATA_VARIABLE_CONFIG = eINSTANCE.getDataVariableConfig();

		/**
		 * The meta object literal for the '<em><b>Data Variable Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_VARIABLE_CONFIG__DATA_VARIABLE_TYPE = eINSTANCE.getDataVariableConfig_DataVariableType();

		/**
		 * The meta object literal for the '<em><b>Fix Flow Data Variable</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_VARIABLE_CONFIG__FIX_FLOW_DATA_VARIABLE = eINSTANCE.getDataVariableConfig_FixFlowDataVariable();

		/**
		 * The meta object literal for the '<em><b>Data Variable Data Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_VARIABLE_CONFIG__DATA_VARIABLE_DATA_TYPE = eINSTANCE.getDataVariableConfig_DataVariableDataType();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.variableconfig.impl.DataVariableTypeImpl <em>Data Variable Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.DataVariableTypeImpl
		 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.VariableconfigPackageImpl#getDataVariableType()
		 * @generated
		 */
		EClass DATA_VARIABLE_TYPE = eINSTANCE.getDataVariableType();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_VARIABLE_TYPE__TYPE = eINSTANCE.getDataVariableType_Type();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.variableconfig.impl.TypeImpl <em>Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.TypeImpl
		 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.VariableconfigPackageImpl#getType()
		 * @generated
		 */
		EClass TYPE = eINSTANCE.getType();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TYPE__NAME = eINSTANCE.getType_Name();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TYPE__ID = eINSTANCE.getType_Id();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.variableconfig.impl.DataVariableDataTypeImpl <em>Data Variable Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.DataVariableDataTypeImpl
		 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.VariableconfigPackageImpl#getDataVariableDataType()
		 * @generated
		 */
		EClass DATA_VARIABLE_DATA_TYPE = eINSTANCE.getDataVariableDataType();

		/**
		 * The meta object literal for the '<em><b>Data Type Def</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_VARIABLE_DATA_TYPE__DATA_TYPE_DEF = eINSTANCE.getDataVariableDataType_DataTypeDef();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.variableconfig.impl.DataTypeDefImpl <em>Data Type Def</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.DataTypeDefImpl
		 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.VariableconfigPackageImpl#getDataTypeDef()
		 * @generated
		 */
		EClass DATA_TYPE_DEF = eINSTANCE.getDataTypeDef();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_TYPE_DEF__NAME = eINSTANCE.getDataTypeDef_Name();

		/**
		 * The meta object literal for the '<em><b>Type Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_TYPE_DEF__TYPE_VALUE = eINSTANCE.getDataTypeDef_TypeValue();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_TYPE_DEF__ID = eINSTANCE.getDataTypeDef_Id();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.variableconfig.impl.FixFlowDataVariableImpl <em>Fix Flow Data Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.FixFlowDataVariableImpl
		 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.VariableconfigPackageImpl#getFixFlowDataVariable()
		 * @generated
		 */
		EClass FIX_FLOW_DATA_VARIABLE = eINSTANCE.getFixFlowDataVariable();

		/**
		 * The meta object literal for the '<em><b>Data Variable Def</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIX_FLOW_DATA_VARIABLE__DATA_VARIABLE_DEF = eINSTANCE.getFixFlowDataVariable_DataVariableDef();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIX_FLOW_DATA_VARIABLE__TYPE = eINSTANCE.getFixFlowDataVariable_Type();

		/**
		 * The meta object literal for the '{@link com.founder.fix.bpmn2extensions.variableconfig.impl.DataVariableDefImpl <em>Data Variable Def</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.DataVariableDefImpl
		 * @see com.founder.fix.bpmn2extensions.variableconfig.impl.VariableconfigPackageImpl#getDataVariableDef()
		 * @generated
		 */
		EClass DATA_VARIABLE_DEF = eINSTANCE.getDataVariableDef();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_VARIABLE_DEF__NAME = eINSTANCE.getDataVariableDef_Name();

		/**
		 * The meta object literal for the '<em><b>Data Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_VARIABLE_DEF__DATA_TYPE = eINSTANCE.getDataVariableDef_DataType();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_VARIABLE_DEF__VALUE = eINSTANCE.getDataVariableDef_Value();

		/**
		 * The meta object literal for the '<em><b>Doc</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_VARIABLE_DEF__DOC = eINSTANCE.getDataVariableDef_Doc();

	}

} //VariableconfigPackage
