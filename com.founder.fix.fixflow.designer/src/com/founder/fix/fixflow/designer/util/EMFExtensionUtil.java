package com.founder.fix.fixflow.designer.util;



import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.ecore.xml.type.AnyType;


/**
 * 获取EMF对象扩展元素的工具类
 * @author jiangnan
 *
 */
public class EMFExtensionUtil {

	/**
	 * 获取EMF对象的扩展属性值。
	 * @param baseElement BPMN2元素
	 * @param AnyAttributeName 扩展属性的名称
	 * @return 扩展属性值
	 */
	public static String getAnyAttributeValue(BaseElement baseElement, String anyAttributeName) {
		for (Entry entry : baseElement.getAnyAttribute()) {
			if (entry.getEStructuralFeature().getName().toLowerCase().equals(anyAttributeName.toLowerCase())) {
				return entry.getValue().toString();
			}
		}
		return null;
	}
	
	
	/**
	 * 获取EMF对象的扩展元素
	 * @param baseElement BPMN2元素
	 * @param elementName 元素的名称
	 * @return 扩展元素列表
	 */
	public static List<FeatureMap.Entry> getExtensionElements(BaseElement baseElement,String elementName)
	{
		List<FeatureMap.Entry> eStructuralFeatureList=new ArrayList<FeatureMap.Entry>();
		
		for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {
			FeatureMap extensionElements = extensionAttributeValue.getValue();
			
			for (Entry entry : extensionElements) {
				if(entry.getEStructuralFeature().getName().toLowerCase().equals(elementName.toLowerCase())){
					eStructuralFeatureList.add(entry);
				}
			}
		}
		
		return eStructuralFeatureList;
	}
	
	/**
	 * 获取EMF对象扩展元素的属性值
	 * @param entry 扩展元素
	 * @param attributeName 属性名称
	 * @return 属性值
	 */
	public static String getExtensionElementAttributeValue(FeatureMap.Entry entry,String attributeName)
	{	
		AnyType extensionElement = (AnyType) entry.getValue();
		FeatureMap elementAttributes = extensionElement.getAnyAttribute();
		for (Entry entryAttributes : elementAttributes) {
			if(entryAttributes.getEStructuralFeature().getName().toLowerCase().equals(attributeName.toLowerCase())){
				return entryAttributes.getValue().toString();
			}
		}
		return null;
	}
	
	/**
	 * 获取EMF对象扩展元素里值
	 * @param entry 扩展元素
	 * @return 元素里值
	 */
	public static String getExtensionElementValue(FeatureMap.Entry entry)
	{
		AnyType extensionElement = (AnyType) entry.getValue();
		Object elementValue=null;
		if(extensionElement.getMixed().size()>0){
			elementValue=extensionElement.getMixed().get(0).getValue();
		}

		
		if(elementValue!=null)
		{
			return elementValue.toString();
		}
		else {
			return null;
		}
	}
	
	/**
	 * 获取扩展元素中的子元素
	 * @param entry 扩展元素
	 * @param elementName 元素名称
	 * @return 子元素集合
	 */
	public static List<FeatureMap.Entry> getExtensionElementsInEntry(FeatureMap.Entry entry,String elementName)
	{
		List<FeatureMap.Entry> eStructuralFeatureList=new ArrayList<FeatureMap.Entry>();
		
		AnyType extensionElement = (AnyType) entry.getValue();
		FeatureMap extensionElementMap = extensionElement.getAny();
		
		for (Entry element : extensionElementMap) {
			 if (element.getEStructuralFeature().getName().toLowerCase().equals(elementName.toLowerCase())) {
				 eStructuralFeatureList.add(element);
			 }
		}
		return eStructuralFeatureList;

	}
	
	

}
