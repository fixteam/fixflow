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
import org.eclipse.bpmn2.CatchEvent;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.ThrowEvent;
import org.eclipse.emf.ecore.EClass;

import com.founder.fix.fixflow.core.runtime.ExecutionContext;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Event Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class EventDefinitionImpl extends RootElementImpl implements EventDefinition {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EventDefinitionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Bpmn2Package.Literals.EVENT_DEFINITION;
    }

    
    
	
	public void  execute(ExecutionContext executionContext, Event event) {
		
		
		if(event instanceof CatchEvent){
			catchExecute(executionContext,(CatchEvent)event);
		}
		if(event instanceof ThrowEvent){
			throwExecute(executionContext,(ThrowEvent)event);
		}
		
		
		
	}
	
	//捕获型执行事件定义
	public void  catchExecute(ExecutionContext executionContext, CatchEvent event) {
		
		
		
	}
	
	//抛出型执行事件定义
	public void  throwExecute(ExecutionContext executionContext, ThrowEvent event) {
		
		
		
	}

} //EventDefinitionImpl
