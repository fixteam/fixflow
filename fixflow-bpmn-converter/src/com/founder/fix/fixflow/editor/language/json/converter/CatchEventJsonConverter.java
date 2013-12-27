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

import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.editor.language.json.converter.BaseBpmnJsonConverter;
import com.founder.fix.fixflow.editor.language.json.converter.BpmnJsonConverterUtil;
import com.founder.fix.fixflow.editor.language.json.converter.CatchEventJsonConverter;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.IntermediateCatchEvent;
import org.eclipse.bpmn2.MessageEventDefinition;
import org.eclipse.bpmn2.SignalEventDefinition;

public class CatchEventJsonConverter extends BaseBpmnJsonConverter {
  
  public static void fillTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap,
      Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
    
    fillJsonTypes(convertersToBpmnMap);
    fillBpmnTypes(convertersToJsonMap);
  }
  
  public static void fillJsonTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap) {
    convertersToBpmnMap.put(STENCIL_EVENT_CATCH_TIMER, CatchEventJsonConverter.class);
    convertersToBpmnMap.put(STENCIL_EVENT_CATCH_MESSAGE, CatchEventJsonConverter.class);
    convertersToBpmnMap.put(STENCIL_EVENT_CATCH_SIGNAL, CatchEventJsonConverter.class);
  }
  
  public static void fillBpmnTypes(Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
    convertersToJsonMap.put(IntermediateCatchEvent.class, CatchEventJsonConverter.class);
  }
  
  protected String getStencilId(FlowElement flowElement) {
    IntermediateCatchEvent catchEvent = (IntermediateCatchEvent) flowElement;
    List<EventDefinition> eventDefinitions = catchEvent.getEventDefinitions();
    if (eventDefinitions.size() != 1) {
      // return timer event as default;
      return STENCIL_EVENT_CATCH_TIMER;
    }
    
    EventDefinition eventDefinition = eventDefinitions.get(0);
    if (eventDefinition instanceof MessageEventDefinition) {
      return STENCIL_EVENT_CATCH_MESSAGE;
    } else if (eventDefinition instanceof SignalEventDefinition) {
      return STENCIL_EVENT_CATCH_SIGNAL;
    } else {
      return STENCIL_EVENT_CATCH_TIMER;
    }
  }

  protected void convertElementToJson(ObjectNode propertiesNode, FlowElement flowElement) {
    IntermediateCatchEvent catchEvent = (IntermediateCatchEvent) flowElement;
    addEventProperties(catchEvent, propertiesNode);
  }
  
  protected FlowElement convertJsonToElement(JsonNode elementNode, JsonNode modelNode, Map<String, JsonNode> shapeMap) {
    IntermediateCatchEvent catchEvent = Bpmn2Factory.eINSTANCE.createIntermediateCatchEvent();// IntermediateCatchEvent();
    String stencilId = BpmnJsonConverterUtil.getStencilId(elementNode);
    if (STENCIL_EVENT_CATCH_TIMER.equals(stencilId)) {
      convertJsonToTimerDefinition(elementNode, catchEvent);
    } else if (STENCIL_EVENT_CATCH_MESSAGE.equals(stencilId)) {
      convertJsonToMessageDefinition(elementNode, catchEvent);
    } else if (STENCIL_EVENT_CATCH_SIGNAL.equals(stencilId)) {
      convertJsonToSignalDefinition(elementNode, catchEvent);
    }
    return catchEvent;
  }
}
