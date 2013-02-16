/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.variableconfig.impl;

import com.founder.fix.bpmn2extensions.variableconfig.DataTypeDef;
import com.founder.fix.bpmn2extensions.variableconfig.DataVariableConfig;
import com.founder.fix.bpmn2extensions.variableconfig.DataVariableDataType;
import com.founder.fix.bpmn2extensions.variableconfig.DataVariableDef;
import com.founder.fix.bpmn2extensions.variableconfig.DataVariableType;
import com.founder.fix.bpmn2extensions.variableconfig.DocumentRoot;
import com.founder.fix.bpmn2extensions.variableconfig.FixFlowDataVariable;
import com.founder.fix.bpmn2extensions.variableconfig.Type;
import com.founder.fix.bpmn2extensions.variableconfig.VariableconfigFactory;
import com.founder.fix.bpmn2extensions.variableconfig.VariableconfigPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class VariableconfigPackageImpl extends EPackageImpl implements VariableconfigPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentRootEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataVariableConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataVariableTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataVariableDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataTypeDefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fixFlowDataVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataVariableDefEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.founder.fix.bpmn2extensions.variableconfig.VariableconfigPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private VariableconfigPackageImpl() {
		super(eNS_URI, VariableconfigFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link VariableconfigPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static VariableconfigPackage init() {
		if (isInited) return (VariableconfigPackage)EPackage.Registry.INSTANCE.getEPackage(VariableconfigPackage.eNS_URI);

		// Obtain or create and register package
		VariableconfigPackageImpl theVariableconfigPackage = (VariableconfigPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof VariableconfigPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new VariableconfigPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theVariableconfigPackage.createPackageContents();

		// Initialize created meta-data
		theVariableconfigPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theVariableconfigPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(VariableconfigPackage.eNS_URI, theVariableconfigPackage);
		return theVariableconfigPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDocumentRoot() {
		return documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Mixed() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XMLNSPrefixMap() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XSISchemaLocation() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_DataVariableConfig() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataVariableConfig() {
		return dataVariableConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataVariableConfig_DataVariableType() {
		return (EReference)dataVariableConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataVariableConfig_FixFlowDataVariable() {
		return (EReference)dataVariableConfigEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataVariableConfig_DataVariableDataType() {
		return (EReference)dataVariableConfigEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataVariableType() {
		return dataVariableTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataVariableType_Type() {
		return (EReference)dataVariableTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getType() {
		return typeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getType_Name() {
		return (EAttribute)typeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getType_Id() {
		return (EAttribute)typeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataVariableDataType() {
		return dataVariableDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataVariableDataType_DataTypeDef() {
		return (EReference)dataVariableDataTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataTypeDef() {
		return dataTypeDefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataTypeDef_Name() {
		return (EAttribute)dataTypeDefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataTypeDef_TypeValue() {
		return (EAttribute)dataTypeDefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataTypeDef_Id() {
		return (EAttribute)dataTypeDefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFixFlowDataVariable() {
		return fixFlowDataVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFixFlowDataVariable_DataVariableDef() {
		return (EReference)fixFlowDataVariableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFixFlowDataVariable_Type() {
		return (EAttribute)fixFlowDataVariableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataVariableDef() {
		return dataVariableDefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataVariableDef_Name() {
		return (EAttribute)dataVariableDefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataVariableDef_DataType() {
		return (EAttribute)dataVariableDefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataVariableDef_Value() {
		return (EAttribute)dataVariableDefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataVariableDef_Doc() {
		return (EAttribute)dataVariableDefEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableconfigFactory getVariableconfigFactory() {
		return (VariableconfigFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__DATA_VARIABLE_CONFIG);

		dataVariableConfigEClass = createEClass(DATA_VARIABLE_CONFIG);
		createEReference(dataVariableConfigEClass, DATA_VARIABLE_CONFIG__DATA_VARIABLE_TYPE);
		createEReference(dataVariableConfigEClass, DATA_VARIABLE_CONFIG__FIX_FLOW_DATA_VARIABLE);
		createEReference(dataVariableConfigEClass, DATA_VARIABLE_CONFIG__DATA_VARIABLE_DATA_TYPE);

		dataVariableTypeEClass = createEClass(DATA_VARIABLE_TYPE);
		createEReference(dataVariableTypeEClass, DATA_VARIABLE_TYPE__TYPE);

		typeEClass = createEClass(TYPE);
		createEAttribute(typeEClass, TYPE__NAME);
		createEAttribute(typeEClass, TYPE__ID);

		dataVariableDataTypeEClass = createEClass(DATA_VARIABLE_DATA_TYPE);
		createEReference(dataVariableDataTypeEClass, DATA_VARIABLE_DATA_TYPE__DATA_TYPE_DEF);

		dataTypeDefEClass = createEClass(DATA_TYPE_DEF);
		createEAttribute(dataTypeDefEClass, DATA_TYPE_DEF__NAME);
		createEAttribute(dataTypeDefEClass, DATA_TYPE_DEF__TYPE_VALUE);
		createEAttribute(dataTypeDefEClass, DATA_TYPE_DEF__ID);

		fixFlowDataVariableEClass = createEClass(FIX_FLOW_DATA_VARIABLE);
		createEReference(fixFlowDataVariableEClass, FIX_FLOW_DATA_VARIABLE__DATA_VARIABLE_DEF);
		createEAttribute(fixFlowDataVariableEClass, FIX_FLOW_DATA_VARIABLE__TYPE);

		dataVariableDefEClass = createEClass(DATA_VARIABLE_DEF);
		createEAttribute(dataVariableDefEClass, DATA_VARIABLE_DEF__NAME);
		createEAttribute(dataVariableDefEClass, DATA_VARIABLE_DEF__DATA_TYPE);
		createEAttribute(dataVariableDefEClass, DATA_VARIABLE_DEF__VALUE);
		createEAttribute(dataVariableDefEClass, DATA_VARIABLE_DEF__DOC);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_DataVariableConfig(), this.getDataVariableConfig(), null, "dataVariableConfig", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dataVariableConfigEClass, DataVariableConfig.class, "DataVariableConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDataVariableConfig_DataVariableType(), this.getDataVariableType(), null, "dataVariableType", null, 1, 1, DataVariableConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDataVariableConfig_FixFlowDataVariable(), this.getFixFlowDataVariable(), null, "fixFlowDataVariable", null, 0, -1, DataVariableConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDataVariableConfig_DataVariableDataType(), this.getDataVariableDataType(), null, "dataVariableDataType", null, 1, 1, DataVariableConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dataVariableTypeEClass, DataVariableType.class, "DataVariableType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDataVariableType_Type(), this.getType(), null, "type", null, 0, -1, DataVariableType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(typeEClass, Type.class, "Type", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, Type.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getType_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, Type.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dataVariableDataTypeEClass, DataVariableDataType.class, "DataVariableDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDataVariableDataType_DataTypeDef(), this.getDataTypeDef(), null, "dataTypeDef", null, 0, -1, DataVariableDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dataTypeDefEClass, DataTypeDef.class, "DataTypeDef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDataTypeDef_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, DataTypeDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataTypeDef_TypeValue(), theXMLTypePackage.getString(), "typeValue", null, 1, 1, DataTypeDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataTypeDef_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, DataTypeDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fixFlowDataVariableEClass, FixFlowDataVariable.class, "FixFlowDataVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFixFlowDataVariable_DataVariableDef(), this.getDataVariableDef(), null, "dataVariableDef", null, 0, -1, FixFlowDataVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFixFlowDataVariable_Type(), theXMLTypePackage.getString(), "type", null, 1, 1, FixFlowDataVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dataVariableDefEClass, DataVariableDef.class, "DataVariableDef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDataVariableDef_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, DataVariableDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataVariableDef_DataType(), theXMLTypePackage.getString(), "dataType", null, 0, 1, DataVariableDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataVariableDef_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, DataVariableDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataVariableDef_Doc(), theXMLTypePackage.getString(), "doc", null, 0, 1, DataVariableDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";			
		addAnnotation
		  (documentRootEClass, 
		   source, 
		   new String[] {
			 "name", "",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_XMLNSPrefixMap(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xmlns:prefix"
		   });		
		addAnnotation
		  (getDocumentRoot_XSISchemaLocation(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xsi:schemaLocation"
		   });		
		addAnnotation
		  (getDocumentRoot_DataVariableConfig(), 
		   source, 
		   new String[] {
			 "name", "dataVariableConfig",
			 "kind", "element"
		   });		
		addAnnotation
		  (dataVariableConfigEClass, 
		   source, 
		   new String[] {
			 "name", "dataVariableConfig"
		   });		
		addAnnotation
		  (getDataVariableConfig_DataVariableType(), 
		   source, 
		   new String[] {
			 "name", "dataVariableType",
			 "kind", "element"
		   });		
		addAnnotation
		  (getDataVariableConfig_FixFlowDataVariable(), 
		   source, 
		   new String[] {
			 "name", "fixFlowDataVariable",
			 "kind", "element"
		   });		
		addAnnotation
		  (getDataVariableConfig_DataVariableDataType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "dataVariableDataType"
		   });		
		addAnnotation
		  (dataVariableTypeEClass, 
		   source, 
		   new String[] {
			 "name", "dataVariableType"
		   });		
		addAnnotation
		  (getDataVariableType_Type(), 
		   source, 
		   new String[] {
			 "name", "type",
			 "kind", "element"
		   });		
		addAnnotation
		  (typeEClass, 
		   source, 
		   new String[] {
			 "name", "type"
		   });		
		addAnnotation
		  (getType_Name(), 
		   source, 
		   new String[] {
			 "name", "name",
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (dataVariableDataTypeEClass, 
		   source, 
		   new String[] {
			 "name", "dataVariableDataType"
		   });		
		addAnnotation
		  (getDataVariableDataType_DataTypeDef(), 
		   source, 
		   new String[] {
			 "name", "dataTypeDef",
			 "kind", "element"
		   });		
		addAnnotation
		  (dataTypeDefEClass, 
		   source, 
		   new String[] {
			 "name", "dataTypeDef"
		   });		
		addAnnotation
		  (getDataTypeDef_Name(), 
		   source, 
		   new String[] {
			 "name", "name",
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getDataTypeDef_TypeValue(), 
		   source, 
		   new String[] {
			 "name", "typeValue",
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getDataTypeDef_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (fixFlowDataVariableEClass, 
		   source, 
		   new String[] {
			 "name", "fixFlowDataVariable"
		   });		
		addAnnotation
		  (getFixFlowDataVariable_DataVariableDef(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "dataVariableDef"
		   });		
		addAnnotation
		  (getFixFlowDataVariable_Type(), 
		   source, 
		   new String[] {
			 "name", "type",
			 "namespace", "",
			 "kind", "attribute"
		   });		
		addAnnotation
		  (dataVariableDefEClass, 
		   source, 
		   new String[] {
			 "name", "dataVariableDef"
		   });		
		addAnnotation
		  (getDataVariableDef_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getDataVariableDef_DataType(), 
		   source, 
		   new String[] {
			 "name", "dataType",
			 "namespace", "",
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getDataVariableDef_Value(), 
		   source, 
		   new String[] {
			 "kind", "simple"
		   });		
		addAnnotation
		  (getDataVariableDef_Doc(), 
		   source, 
		   new String[] {
			 "kind", "simple"
		   });
	}

} //VariableconfigPackageImpl
