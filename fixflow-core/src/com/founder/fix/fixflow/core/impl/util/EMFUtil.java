package com.founder.fix.fixflow.core.impl.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

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
	
	

}
