package com.founder.fix.fixflow.core.impl.util;


import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.emf.ecore.util.FeatureMap;


import com.founder.fix.bpmn2extensions.fixflow.DataVariable;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;




/**
 * 获取EMF对象扩展元素的工具类
 * @author jiangnan
 *
 */
public class EMFExtensionUtil {

	
	
	

	
	
	/**
	 * 获取数据变量
	 * @param baseElement
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<DataVariable> getDataVariables(BaseElement baseElement) {

		
		if (baseElement.getExtensionValues().size() > 0) {
			for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {
				FeatureMap extensionElements = extensionAttributeValue.getValue();
				Object objectElement = extensionElements.get(FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_VARIABLE, true);
				if (objectElement != null) {

					return (List<DataVariable>) objectElement;
				

				}
			}
		}

		return null;
	}
	
	


}
