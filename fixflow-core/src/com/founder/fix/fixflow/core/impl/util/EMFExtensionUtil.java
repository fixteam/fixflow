package com.founder.fix.fixflow.core.impl.util;



import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.ecore.xml.type.AnyType;

import com.founder.fix.bpmn2extensions.fixflow.Expression;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowFactory;
import com.founder.fix.bpmn2extensions.fixflow.SkipAssignee;
import com.founder.fix.bpmn2extensions.fixflow.SkipComment;
import com.founder.fix.bpmn2extensions.fixflow.SkipStrategy;



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
	
	
	/**
	 * 获取跳过策略对象
	 * @param activity
	 * @return
	 */
	public static SkipStrategy getSkipStrategy(Activity activity){
		
		
		List<FeatureMap.Entry> entryList = getExtensionElements(activity, "skipStrategy");
		if(entryList.size()>0){
			
			SkipStrategy skipStrategy=FixFlowFactory.eINSTANCE.createSkipStrategy();
			Entry  entry=entryList.get(0);
			String isEnable=getExtensionElementAttributeValue(entry, "isEnable");
			String isCreateSkipProcess=getExtensionElementAttributeValue(entry, "isCreateSkipProcess");
			
			if(isEnable!=null){
				skipStrategy.setIsEnable(StringUtil.getBoolean(isEnable));
			}
			else{
				skipStrategy.setIsEnable(false);
			}
			
			if(isCreateSkipProcess!=null){
				skipStrategy.setIsCreateSkipProcess(StringUtil.getBoolean(isCreateSkipProcess));
			}
			else{
				skipStrategy.setIsCreateSkipProcess(true);
			}
			
			
			List<Entry> entriesSkipExpression=EMFExtensionUtil.getExtensionElementsInEntry(entry, "expression");
			if(entriesSkipExpression.size()>0){
				FeatureMap.Entry expressionEntry = entriesSkipExpression.get(0);
				String expressionValue = EMFExtensionUtil.getExtensionElementValue(expressionEntry);
				if(expressionValue!=null){
					Expression expression=FixFlowFactory.eINSTANCE.createExpression();
					expression.setValue(expressionValue);
					skipStrategy.setExpression(expression);
				}
				
			}
			
			String skipAssignee=null;
			String skipComment=null;
			
			
			List<Entry> entriesSkipAssignee=EMFExtensionUtil.getExtensionElementsInEntry(entry, "skipAssignee");
			
			if(entriesSkipAssignee.size()>0){
				List<Entry> entriesSkipAssigneeExpression=EMFExtensionUtil.getExtensionElementsInEntry(entriesSkipAssignee.get(0), "expression");
				if(entriesSkipAssigneeExpression.size()>0){
					FeatureMap.Entry expressionEntrySkipAssigneeExpression = entriesSkipAssigneeExpression.get(0);
					skipAssignee = EMFExtensionUtil.getExtensionElementValue(expressionEntrySkipAssigneeExpression);
					
				}
			}
			
			List<Entry> entriesSkipComment=EMFExtensionUtil.getExtensionElementsInEntry(entry, "skipComment");
			if(entriesSkipComment.size()>0){
				List<Entry> entriesSkipCommentExpression=EMFExtensionUtil.getExtensionElementsInEntry(entriesSkipComment.get(0), "expression");
				if(entriesSkipCommentExpression.size()>0){
					FeatureMap.Entry expressionEntrySkipCommentExpression = entriesSkipCommentExpression.get(0);
					skipComment = EMFExtensionUtil.getExtensionElementValue(expressionEntrySkipCommentExpression);
					
				
				}
			}
			
			
			if(skipAssignee!=null){
				SkipAssignee skipAssigneeObj=FixFlowFactory.eINSTANCE.createSkipAssignee();
				Expression expression=FixFlowFactory.eINSTANCE.createExpression();
				expression.setValue(skipAssignee);
				skipAssigneeObj.setExpression(expression);
				skipStrategy.setSkipAssignee(skipAssigneeObj);
			}
			if(skipComment!=null){
				SkipComment skipCommentObj=FixFlowFactory.eINSTANCE.createSkipComment();
				Expression expression=FixFlowFactory.eINSTANCE.createExpression();
				expression.setValue(skipComment);
				skipCommentObj.setExpression(expression);
				skipStrategy.setSkipComment(skipCommentObj);
			}
			
			
			return skipStrategy;
		}
		
		return null;
		
		
	}
	
	

}
