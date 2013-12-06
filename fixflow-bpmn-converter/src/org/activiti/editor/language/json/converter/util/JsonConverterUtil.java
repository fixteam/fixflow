package org.activiti.editor.language.json.converter.util;

import groovyjarjarasm.asm.tree.IntInsnNode;

import java.util.ArrayList;
import java.util.List;

import org.activiti.editor.constants.EditorJsonConstants;
import org.activiti.editor.constants.StencilConstants;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

public class JsonConverterUtil implements EditorJsonConstants, StencilConstants {
  
  public static String getPropertyValueAsString(String name, JsonNode objectNode) {
    String propertyValue = null;
    JsonNode propertyNode = getProperty(name, objectNode);
    if (propertyNode != null && "null".equalsIgnoreCase(propertyNode.asText()) == false) {
      propertyValue = propertyNode.asText();
    }
    return propertyValue;
  }
  
  public static boolean getPropertyValueAsBoolean(String name, JsonNode objectNode) {
    boolean result = false;
    String stringValue = getPropertyValueAsString(name, objectNode);
    if (PROPERTY_VALUE_YES.equalsIgnoreCase(stringValue)) {
      result = true;
    }
    return result;
  }
  
  public static List<String> getPropertyValueAsList(String name, JsonNode objectNode) {
    List<String> resultList = new ArrayList<String>();
    JsonNode propertyNode = getProperty(name, objectNode);
    if (propertyNode != null && "null".equalsIgnoreCase(propertyNode.asText()) == false) {
      String propertyValue = propertyNode.asText();
      String[] valueList = propertyValue.split(",");
      for (String value : valueList) {
        resultList.add(value.trim());
      }
    }
    return resultList;
  }
  
  public static JsonNode getProperty(String name, JsonNode objectNode) {
    JsonNode propertyNode = null;
    if (objectNode.get(EDITOR_SHAPE_PROPERTIES) != null) {
      JsonNode propertiesNode = objectNode.get(EDITOR_SHAPE_PROPERTIES);
      propertyNode = propertiesNode.get(name);
    }
    return propertyNode;
  }
  
  /**
   * 查找ObjectNode下propertyName属性为name的json节点
   * @param name
   * @param propertyName
   * @param objectNode
   * @return
   */
  public static JsonNode getChildElementByProperty(String name,String propertyName,ArrayNode objectNode){
	  JsonNode jsonNode = null;
	  for( int i = 0; i< objectNode.size();i++){
		  JsonNode childNode = objectNode.get(i);
		  if(childNode.get(propertyName)!=null && name.equals(childNode.get(propertyName).asText())){
			  return childNode;
		  }
	  }
	  return jsonNode;
  }

}
