/**
 * <copyright>
 * 
 * Copyright (c) 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Reiner Hille-Doering (SAP AG) - initial API and implementation and/or initial documentation
 * 
 * </copyright>
 */
package org.eclipse.bpmn2.impl;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Formal Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.impl.FormalExpressionImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.FormalExpressionImpl#getBody <em>Body</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.FormalExpressionImpl#getEvaluatesToTypeRef <em>Evaluates To Type Ref</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.impl.FormalExpressionImpl#getLanguage <em>Language</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FormalExpressionImpl extends ExpressionImpl implements FormalExpression {
    /**
     * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMixed()
     * @generated
     * @ordered
     */
    protected FeatureMap mixed;

    /**
     * The default value of the '{@link #getBody() <em>Body</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBody()
     * @generated
     * @ordered
     */
    protected static final String BODY_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getEvaluatesToTypeRef() <em>Evaluates To Type Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEvaluatesToTypeRef()
     * @generated
     * @ordered
     */
    protected ItemDefinition evaluatesToTypeRef;

    /**
     * The default value of the '{@link #getLanguage() <em>Language</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLanguage()
     * @generated
     * @ordered
     */
    protected static final String LANGUAGE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLanguage() <em>Language</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLanguage()
     * @generated
     * @ordered
     */
    protected String language = LANGUAGE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FormalExpressionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.FORMAL_EXPRESSION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, Bpmn2Package.FORMAL_EXPRESSION__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String getBody() {
    	
    	
    	 if (mixed != null && !mixed.isEmpty()) {
             StringBuilder result = new StringBuilder();
             if(mixed.size()>0){
            	 FeatureMap.Entry cur = mixed.get(0);
                 result.append(cur.getValue());
                 
                 return result.toString();
             }
            
         }

         return null;
    	
    	/*
        if (mixed != null && !mixed.isEmpty()) {
            StringBuilder result = new StringBuilder();
            for (FeatureMap.Entry cur : mixed) {
                switch (cur.getEStructuralFeature().getFeatureID()) {
                case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__CDATA:
                case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__TEXT:
                    result.append(cur.getValue());
                    break;

                default:
                    break;
                }
            }
            return result.toString();
        }

        return null;
        */
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void setBody(String newBody) {
        getMixed().clear();
        getMixed().add(XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Text(), newBody);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ItemDefinition getEvaluatesToTypeRef() {
        if (evaluatesToTypeRef != null && evaluatesToTypeRef.eIsProxy()) {
            InternalEObject oldEvaluatesToTypeRef = (InternalEObject) evaluatesToTypeRef;
            evaluatesToTypeRef = (ItemDefinition) eResolveProxy(oldEvaluatesToTypeRef);
            if (evaluatesToTypeRef != oldEvaluatesToTypeRef) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            Bpmn2Package.FORMAL_EXPRESSION__EVALUATES_TO_TYPE_REF,
                            oldEvaluatesToTypeRef, evaluatesToTypeRef));
            }
        }
        return evaluatesToTypeRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ItemDefinition basicGetEvaluatesToTypeRef() {
        return evaluatesToTypeRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEvaluatesToTypeRef(ItemDefinition newEvaluatesToTypeRef) {
        ItemDefinition oldEvaluatesToTypeRef = evaluatesToTypeRef;
        evaluatesToTypeRef = newEvaluatesToTypeRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.FORMAL_EXPRESSION__EVALUATES_TO_TYPE_REF, oldEvaluatesToTypeRef,
                    evaluatesToTypeRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLanguage() {
        return language;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLanguage(String newLanguage) {
        String oldLanguage = language;
        language = newLanguage;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    Bpmn2Package.FORMAL_EXPRESSION__LANGUAGE, oldLanguage, language));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID,
            NotificationChain msgs) {
        switch (featureID) {
        case Bpmn2Package.FORMAL_EXPRESSION__MIXED:
            return ((InternalEList<?>) getMixed()).basicRemove(otherEnd, msgs);
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
        case Bpmn2Package.FORMAL_EXPRESSION__MIXED:
            if (coreType)
                return getMixed();
            return ((FeatureMap.Internal) getMixed()).getWrapper();
        case Bpmn2Package.FORMAL_EXPRESSION__BODY:
            return getBody();
        case Bpmn2Package.FORMAL_EXPRESSION__EVALUATES_TO_TYPE_REF:
            if (resolve)
                return getEvaluatesToTypeRef();
            return basicGetEvaluatesToTypeRef();
        case Bpmn2Package.FORMAL_EXPRESSION__LANGUAGE:
            return getLanguage();
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
        case Bpmn2Package.FORMAL_EXPRESSION__MIXED:
            ((FeatureMap.Internal) getMixed()).set(newValue);
            return;
        case Bpmn2Package.FORMAL_EXPRESSION__BODY:
            setBody((String) newValue);
            return;
        case Bpmn2Package.FORMAL_EXPRESSION__EVALUATES_TO_TYPE_REF:
            setEvaluatesToTypeRef((ItemDefinition) newValue);
            return;
        case Bpmn2Package.FORMAL_EXPRESSION__LANGUAGE:
            setLanguage((String) newValue);
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
        case Bpmn2Package.FORMAL_EXPRESSION__MIXED:
            getMixed().clear();
            return;
        case Bpmn2Package.FORMAL_EXPRESSION__BODY:
            setBody(BODY_EDEFAULT);
            return;
        case Bpmn2Package.FORMAL_EXPRESSION__EVALUATES_TO_TYPE_REF:
            setEvaluatesToTypeRef((ItemDefinition) null);
            return;
        case Bpmn2Package.FORMAL_EXPRESSION__LANGUAGE:
            setLanguage(LANGUAGE_EDEFAULT);
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
        case Bpmn2Package.FORMAL_EXPRESSION__MIXED:
            return mixed != null && !mixed.isEmpty();
        case Bpmn2Package.FORMAL_EXPRESSION__BODY:
            return BODY_EDEFAULT == null ? getBody() != null : !BODY_EDEFAULT.equals(getBody());
        case Bpmn2Package.FORMAL_EXPRESSION__EVALUATES_TO_TYPE_REF:
            return evaluatesToTypeRef != null;
        case Bpmn2Package.FORMAL_EXPRESSION__LANGUAGE:
            return LANGUAGE_EDEFAULT == null ? language != null : !LANGUAGE_EDEFAULT
                    .equals(language);
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
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (mixed: ");
        result.append(mixed);
        result.append(", language: ");
        result.append(language);
        result.append(')');
        return result.toString();
    }

} //FormalExpressionImpl
