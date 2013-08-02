/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 */
package com.founder.fix.fixflow.core.impl.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.FeatureMap;



public class EMFUtil {
	
	
	public static BaseElement findElement(String id,EObject eObject) {
		if (id == null || id.isEmpty())
			return null;

		List<BaseElement> baseElements = getAll(BaseElement.class,eObject);

		for (BaseElement be : baseElements) {
			if (id.equals(be.getId())) {
				return be;
			}
		}

		return null;
	}
	
	public static FlowElement findFlowElement(String id,EObject eObject) {
		if (id == null || id.isEmpty())
			return null;

		List<FlowElement> baseElements = getAll(FlowElement.class,eObject);

		for (FlowElement be : baseElements) {
			if (id.equals(be.getId())) {
				return be;
			}
		}

		return null;
	}
	
	public static List<FlowElement> getAllFlowElement(EObject eObject) {
		
		List<FlowElement> baseElements = getAll(FlowElement.class,eObject);

	

		return baseElements;
	}


	@SuppressWarnings("unchecked")
	public static <T> List<T> getAll(final Class<T> class1,EObject eObject) {
		ArrayList<T> l = new ArrayList<T>();
		TreeIterator<EObject> contents =eObject.eResource().getAllContents();
		for (; contents.hasNext();) {
			Object t = contents.next();
			if (class1.isInstance(t)) {
				l.add((T) t);
			}
		}
		return l;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> getExtensionElementList( Class<T> t ,BaseElement baseElement,EReference eReference){
		
		
		if (baseElement.getExtensionValues().size() > 0) {
			for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {
				FeatureMap extensionElements = extensionAttributeValue.getValue();
				Object objectElement = extensionElements.get(eReference, true);
				if (objectElement != null) {

					List<T> tObjList = (List<T>) objectElement;
					return tObjList;
				

				}
			}
		}
		
		
		return (List<T>)null;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getExtensionElementOne(Class<T> t ,BaseElement baseElement,EReference eReference){
	
		
		if(baseElement==null){
			return null;
		}
		
		if (baseElement.getExtensionValues().size() > 0) {
			for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {
				FeatureMap extensionElements = extensionAttributeValue.getValue();
				
				Object objectElement = extensionElements.get(eReference, true);
				if (objectElement != null) {

					
					if(objectElement instanceof List){
						List<T> tObjList = (List<T>) objectElement;
						if(tObjList.size()>0){
							return tObjList.get(0);
						}
						
					}else{
						return (T)objectElement;
					}
					
				

				}
				/*
				for (Entry entry : extensionElements) {
					if (t.isInstance(entry.getValue())) {
						
						T objectT=(T)entry.getValue();
						return objectT;
					}

				}
				*/
				
			}
		}
		
		
		
		
		
		return (T)null;
	}
	

}
