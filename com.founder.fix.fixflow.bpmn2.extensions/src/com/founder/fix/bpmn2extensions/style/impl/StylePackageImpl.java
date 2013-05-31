/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.style.impl;

import com.founder.fix.bpmn2extensions.style.ElementStyle;
import com.founder.fix.bpmn2extensions.style.ElementStyleConfig;
import com.founder.fix.bpmn2extensions.style.FixFlowStyleConfig;
import com.founder.fix.bpmn2extensions.style.Style;
import com.founder.fix.bpmn2extensions.style.StyleFactory;
import com.founder.fix.bpmn2extensions.style.StylePackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StylePackageImpl extends EPackageImpl implements StylePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fixFlowStyleConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementStyleConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementStyleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass styleEClass = null;

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
	 * @see com.founder.fix.bpmn2extensions.style.StylePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private StylePackageImpl() {
		super(eNS_URI, StyleFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link StylePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static StylePackage init() {
		if (isInited) return (StylePackage)EPackage.Registry.INSTANCE.getEPackage(StylePackage.eNS_URI);

		// Obtain or create and register package
		StylePackageImpl theStylePackage = (StylePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof StylePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new StylePackageImpl());

		isInited = true;

		// Create package meta-data objects
		theStylePackage.createPackageContents();

		// Initialize created meta-data
		theStylePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theStylePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(StylePackage.eNS_URI, theStylePackage);
		return theStylePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFixFlowStyleConfig() {
		return fixFlowStyleConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFixFlowStyleConfig_ElementStyleConfig() {
		return (EReference)fixFlowStyleConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getElementStyleConfig() {
		return elementStyleConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElementStyleConfig_ElementStyle() {
		return (EReference)elementStyleConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getElementStyleConfig_CurrentStyle() {
		return (EAttribute)elementStyleConfigEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getElementStyle() {
		return elementStyleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElementStyle_Style() {
		return (EReference)elementStyleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getElementStyle_StyleId() {
		return (EAttribute)elementStyleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getElementStyle_StyleName() {
		return (EAttribute)elementStyleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStyle() {
		return styleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStyle_Object() {
		return (EAttribute)styleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStyle_Foreground() {
		return (EAttribute)styleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStyle_Background() {
		return (EAttribute)styleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStyle_TextColor() {
		return (EAttribute)styleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStyle_Font() {
		return (EAttribute)styleEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StyleFactory getStyleFactory() {
		return (StyleFactory)getEFactoryInstance();
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
		fixFlowStyleConfigEClass = createEClass(FIX_FLOW_STYLE_CONFIG);
		createEReference(fixFlowStyleConfigEClass, FIX_FLOW_STYLE_CONFIG__ELEMENT_STYLE_CONFIG);

		elementStyleConfigEClass = createEClass(ELEMENT_STYLE_CONFIG);
		createEReference(elementStyleConfigEClass, ELEMENT_STYLE_CONFIG__ELEMENT_STYLE);
		createEAttribute(elementStyleConfigEClass, ELEMENT_STYLE_CONFIG__CURRENT_STYLE);

		elementStyleEClass = createEClass(ELEMENT_STYLE);
		createEReference(elementStyleEClass, ELEMENT_STYLE__STYLE);
		createEAttribute(elementStyleEClass, ELEMENT_STYLE__STYLE_ID);
		createEAttribute(elementStyleEClass, ELEMENT_STYLE__STYLE_NAME);

		styleEClass = createEClass(STYLE);
		createEAttribute(styleEClass, STYLE__OBJECT);
		createEAttribute(styleEClass, STYLE__FOREGROUND);
		createEAttribute(styleEClass, STYLE__BACKGROUND);
		createEAttribute(styleEClass, STYLE__TEXT_COLOR);
		createEAttribute(styleEClass, STYLE__FONT);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(fixFlowStyleConfigEClass, FixFlowStyleConfig.class, "FixFlowStyleConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFixFlowStyleConfig_ElementStyleConfig(), this.getElementStyleConfig(), null, "elementStyleConfig", null, 0, 1, FixFlowStyleConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(elementStyleConfigEClass, ElementStyleConfig.class, "ElementStyleConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getElementStyleConfig_ElementStyle(), this.getElementStyle(), null, "elementStyle", null, 0, -1, ElementStyleConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getElementStyleConfig_CurrentStyle(), ecorePackage.getEString(), "currentStyle", null, 1, 1, ElementStyleConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(elementStyleEClass, ElementStyle.class, "ElementStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getElementStyle_Style(), this.getStyle(), null, "style", null, 0, -1, ElementStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getElementStyle_StyleId(), ecorePackage.getEString(), "styleId", null, 1, 1, ElementStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getElementStyle_StyleName(), ecorePackage.getEString(), "styleName", null, 0, 1, ElementStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(styleEClass, Style.class, "Style", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStyle_Object(), ecorePackage.getEString(), "object", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStyle_Foreground(), ecorePackage.getEString(), "foreground", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStyle_Background(), ecorePackage.getEString(), "background", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStyle_TextColor(), ecorePackage.getEString(), "textColor", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStyle_Font(), ecorePackage.getEString(), "font", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		  (fixFlowStyleConfigEClass, 
		   source, 
		   new String[] {
			 "name", "fixFlowStyleConfig"
		   });		
		addAnnotation
		  (getFixFlowStyleConfig_ElementStyleConfig(), 
		   source, 
		   new String[] {
			 "name", "elementStyleConfig",
			 "namespace", "",
			 "kind", "element"
		   });		
		addAnnotation
		  (elementStyleConfigEClass, 
		   source, 
		   new String[] {
			 "name", "elementStyleConfig"
		   });		
		addAnnotation
		  (getElementStyleConfig_ElementStyle(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "elementStyle"
		   });		
		addAnnotation
		  (getElementStyleConfig_CurrentStyle(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });			
		addAnnotation
		  (elementStyleEClass, 
		   source, 
		   new String[] {
			 "name", "elementStyle"
		   });		
		addAnnotation
		  (getElementStyle_Style(), 
		   source, 
		   new String[] {
			 "name", "style",
			 "kind", "element"
		   });		
		addAnnotation
		  (getElementStyle_StyleId(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "styleId"
		   });			
		addAnnotation
		  (getElementStyle_StyleName(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });			
		addAnnotation
		  (styleEClass, 
		   source, 
		   new String[] {
			 "name", "style"
		   });		
		addAnnotation
		  (getStyle_Object(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getStyle_Foreground(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getStyle_Background(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getStyle_TextColor(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getStyle_Font(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });
	}

} //StylePackageImpl
