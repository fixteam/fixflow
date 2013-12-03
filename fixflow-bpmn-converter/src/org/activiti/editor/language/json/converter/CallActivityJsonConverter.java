/* Licensed under the Apache License, Version 2.0 (the "License");
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
 */
package org.activiti.editor.language.json.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.CallActivity;
import org.eclipse.bpmn2.FlowElement;

import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.CallActivityBehavior;
import com.founder.fix.fixflow.core.impl.util.BpmnModelUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

/**
 * @author Tijs Rademakers
 */
public class CallActivityJsonConverter extends BaseBpmnJsonConverter {

  public static void fillTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap,
      Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
    
    fillJsonTypes(convertersToBpmnMap);
    fillBpmnTypes(convertersToJsonMap);
  }
  
  public static void fillJsonTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap) {
    convertersToBpmnMap.put(STENCIL_CALL_ACTIVITY, CallActivityJsonConverter.class);
  }
  
  public static void fillBpmnTypes(Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
    convertersToJsonMap.put(CallActivityBehavior.class, CallActivityJsonConverter.class);
  }
  
  protected String getStencilId(FlowElement flowElement) {
    return STENCIL_CALL_ACTIVITY;
  }
  
  protected void convertElementToJson(ObjectNode propertiesNode, FlowElement flowElement) {
    CallActivityBehavior callActivity = (CallActivityBehavior) flowElement;
    String callElementId = callActivity.getCallableElementId();
    String callElementVersion = callActivity.getCallableElementVersion();
    String callElementBizKey = callActivity.getCallableElementBizKey();
    boolean isAsync = callActivity.isAsync();
    if(StringUtil.isNotEmpty(callElementId)){
    	propertiesNode.put(PROPERTY_CALLACTIVITY_CALLEDELEMENT,callElementId);
    }
    if(StringUtil.isNotEmpty(callElementVersion)){
    	propertiesNode.put(PROPERTY_CALLACTIVITY_ELEMENTVERSION,callElementVersion);
    }
    if(StringUtil.isNotEmpty(callElementBizKey)){
    	propertiesNode.put(PROPERTY_CALLACTIVITY_BIZKEY, callElementBizKey);
    }
    propertiesNode.put(PROPERTY_CALLACTIVITY_ISASYNC, isAsync);
  }
  
  protected FlowElement convertJsonToElement(JsonNode elementNode, JsonNode modelNode, Map<String, JsonNode> shapeMap) {
    CallActivity callActivity = Bpmn2Factory.eINSTANCE.createCallActivity();
    if(StringUtils.isNotEmpty(getPropertyValueAsString(PROPERTY_CALLACTIVITY_CALLEDELEMENT, elementNode))){
    	BpmnModelUtil.addExtensionAttribute(callActivity, FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_ID, getPropertyValueAsString(PROPERTY_CALLACTIVITY_CALLEDELEMENT, elementNode));
    	BpmnModelUtil.addExtensionAttribute(callActivity, FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_NAME, getPropertyValueAsString(PROPERTY_CALLACTIVITY_CALLEDELEMENT, elementNode));
    } 
    String callElementVersion = getPropertyValueAsString(PROPERTY_CALLACTIVITY_ELEMENTVERSION, elementNode);
    if(StringUtil.isNotEmpty(callElementVersion)){
    	BpmnModelUtil.addExtensionAttribute(callActivity, FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_VERSION, callElementVersion);
    	BpmnModelUtil.addExtensionAttribute(callActivity, FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_VERSION_NAME, callElementVersion);
    }
    String callElementBizKey = getPropertyValueAsString(PROPERTY_CALLACTIVITY_BIZKEY, elementNode);
    if(StringUtil.isNotEmpty(callElementBizKey)){
    	BpmnModelUtil.addExtensionAttribute(callActivity, FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_BIZ_KEY, callElementBizKey);
    	BpmnModelUtil.addExtensionAttribute(callActivity, FixFlowPackage.Literals.DOCUMENT_ROOT__CALLABLE_ELEMENT_BIZ_KEY_NAME, callElementBizKey);
    }
    boolean isAsync = getPropertyValueAsBoolean(PROPERTY_CALLACTIVITY_ISASYNC, elementNode);
    if(isAsync){
    	BpmnModelUtil.addExtensionAttribute(callActivity, FixFlowPackage.Literals.DOCUMENT_ROOT__IS_ASYNC, StringUtil.getString(isAsync));
    }
    return callActivity;
  }
}
