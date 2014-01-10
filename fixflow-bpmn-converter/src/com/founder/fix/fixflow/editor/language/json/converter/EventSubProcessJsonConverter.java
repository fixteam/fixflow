/**
 *  Copyright 1996-2013 Founder International Co.,Ltd.
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
 * @author ych
 * @author kenshin
 */
package com.founder.fix.fixflow.editor.language.json.converter;

import java.util.Map;

import com.founder.fix.fixflow.editor.language.json.converter.BaseBpmnJsonConverter;
import com.founder.fix.fixflow.editor.language.json.converter.EventSubProcessJsonConverter;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.dd.dc.Bounds;

import com.founder.fix.fixflow.core.impl.util.BpmnModelUtil;


public class EventSubProcessJsonConverter extends BaseBpmnJsonConverter {
  
  public static void fillTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap,
      Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
    
    fillJsonTypes(convertersToBpmnMap);
    fillBpmnTypes(convertersToJsonMap);
  }
  
  public static void fillJsonTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap) {
    convertersToBpmnMap.put(STENCIL_EVENT_SUB_PROCESS, EventSubProcessJsonConverter.class);
  }
  
  public static void fillBpmnTypes(Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
    convertersToJsonMap.put(SubProcess.class, EventSubProcessJsonConverter.class);
  }
  
  protected String getStencilId(FlowElement flowElement) {
    return STENCIL_EVENT_SUB_PROCESS;
  }

  protected void convertElementToJson(ObjectNode propertiesNode, FlowElement flowElement) {
    SubProcess subProcess = (SubProcess) flowElement;
    propertiesNode.put("activitytype", "Event-Sub-Process");
    propertiesNode.put("subprocesstype", "Embedded");
    ArrayNode subProcessShapesArrayNode = objectMapper.createArrayNode();
    Bounds graphicInfo = BpmnModelUtil.getBpmnShape(model, flowElement.getId()).getBounds();
    processor.processFlowElements(subProcess.getFlowElements(), model, subProcessShapesArrayNode, 
        graphicInfo.getX() + subProcessX, graphicInfo.getY() + subProcessY,null);
    flowElementNode.put("childShapes", subProcessShapesArrayNode);
  }
  
  protected FlowElement convertJsonToElement(JsonNode elementNode, JsonNode modelNode, Map<String, JsonNode> shapeMap) {
    SubProcess subProcess = Bpmn2Factory.eINSTANCE.createSubProcess();

    JsonNode childShapesArray = elementNode.get(EDITOR_CHILD_SHAPES);
    //processor.processJsonElements(childShapesArray, modelNode, subProcess, shapeMap);
    return subProcess;
  }
}
