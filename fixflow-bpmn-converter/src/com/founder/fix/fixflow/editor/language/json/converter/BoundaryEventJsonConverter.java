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
import com.founder.fix.fixflow.editor.language.json.converter.BoundaryEventJsonConverter;
import com.founder.fix.fixflow.editor.language.json.converter.BpmnJsonConverterUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.ErrorEventDefinition;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.SignalEventDefinition;
import org.eclipse.bpmn2.di.BPMNShape;

import com.founder.fix.fixflow.core.impl.util.BpmnModelUtil;


public class BoundaryEventJsonConverter extends BaseBpmnJsonConverter {
  
  public static void fillTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap,
      Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
    
    fillJsonTypes(convertersToBpmnMap);
    fillBpmnTypes(convertersToJsonMap);
  }
  
  public static void fillJsonTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap) {
    convertersToBpmnMap.put(STENCIL_EVENT_BOUNDARY_TIMER, BoundaryEventJsonConverter.class);
    convertersToBpmnMap.put(STENCIL_EVENT_BOUNDARY_ERROR, BoundaryEventJsonConverter.class);
    convertersToBpmnMap.put(STENCIL_EVENT_BOUNDARY_SIGNAL, BoundaryEventJsonConverter.class);
  }
  
  public static void fillBpmnTypes(Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
    convertersToJsonMap.put(BoundaryEvent.class, BoundaryEventJsonConverter.class);
  }
  
  protected String getStencilId(FlowElement flowElement) {
    BoundaryEvent boundaryEvent = (BoundaryEvent) flowElement;
    List<EventDefinition> eventDefinitions = boundaryEvent.getEventDefinitions();
    if (eventDefinitions.size() != 1) {
      // return timer event as default;
      return STENCIL_EVENT_BOUNDARY_TIMER;
    }
    
    EventDefinition eventDefinition = eventDefinitions.get(0);
    if (eventDefinition instanceof ErrorEventDefinition) {
      return STENCIL_EVENT_BOUNDARY_ERROR;
    } else if (eventDefinition instanceof SignalEventDefinition) {
      return STENCIL_EVENT_BOUNDARY_SIGNAL;
    } else {
      return STENCIL_EVENT_BOUNDARY_TIMER;
    }
  }

  protected void convertElementToJson(ObjectNode propertiesNode, FlowElement flowElement) {
    BoundaryEvent boundaryEvent = (BoundaryEvent) flowElement;
    ArrayNode dockersArrayNode = objectMapper.createArrayNode();
    ObjectNode dockNode = objectMapper.createObjectNode();
    
    
    BPMNShape graphicInfo = BpmnModelUtil.getBpmnShape(model, boundaryEvent.getId());;
    BPMNShape parentGraphicInfo = BpmnModelUtil.getBpmnShape(model,boundaryEvent.getAttachedToRef().getId());
    dockNode.put(EDITOR_BOUNDS_X, graphicInfo.getBounds().getX() + graphicInfo.getBounds().getWidth() - parentGraphicInfo.getBounds().getX());
    dockNode.put(EDITOR_BOUNDS_Y, graphicInfo.getBounds().getY() - parentGraphicInfo.getBounds().getY());
    dockersArrayNode.add(dockNode);
    flowElementNode.put("dockers", dockersArrayNode);
    
    if (boundaryEvent.isCancelActivity() == false) {
      propertiesNode.put(PROPERTY_CANCEL_ACTIVITY, PROPERTY_VALUE_NO);
    }
    
    addEventProperties(boundaryEvent, propertiesNode);
  }
  
  protected FlowElement convertJsonToElement(JsonNode elementNode, JsonNode modelNode, Map<String, JsonNode> shapeMap) {
    BoundaryEvent boundaryEvent = Bpmn2Factory.eINSTANCE.createBoundaryEvent();// BoundaryEvent();
    String stencilId = BpmnJsonConverterUtil.getStencilId(elementNode);
    if (STENCIL_EVENT_BOUNDARY_TIMER.equals(stencilId)) {
      boundaryEvent.setCancelActivity(getPropertyValueAsBoolean(PROPERTY_CANCEL_ACTIVITY, elementNode));
      convertJsonToTimerDefinition(elementNode, boundaryEvent);
    } else if (STENCIL_EVENT_BOUNDARY_ERROR.equals(stencilId)) {
      convertJsonToErrorDefinition(elementNode, boundaryEvent);
    } else if (STENCIL_EVENT_BOUNDARY_SIGNAL.equals(stencilId)) {
      boundaryEvent.setCancelActivity(getPropertyValueAsBoolean(PROPERTY_CANCEL_ACTIVITY, elementNode));
      convertJsonToSignalDefinition(elementNode, boundaryEvent);
    }
    
    Activity activityRef=BpmnModelUtil.getElement(model, lookForAttachedRef(elementNode.get(EDITOR_SHAPE_ID).asText(), modelNode.get(EDITOR_CHILD_SHAPES)),Activity.class);
    
    boundaryEvent.setAttachedToRef(activityRef);
    return boundaryEvent;
  }
  
  private String lookForAttachedRef(String boundaryEventId, JsonNode childShapesNode) {
    String attachedRefId = null;
    
    if (childShapesNode != null) {
      
      for (JsonNode childNode : childShapesNode) {
        ArrayNode outgoingNode = (ArrayNode) childNode.get("outgoing");
        if (outgoingNode != null && outgoingNode.size() > 0) {
          for (JsonNode outgoingChildNode : outgoingNode) {
            JsonNode resourceNode = outgoingChildNode.get(EDITOR_SHAPE_ID);
            if (resourceNode != null && boundaryEventId.equals(resourceNode.asText())) {
              attachedRefId = BpmnJsonConverterUtil.getElementId(childNode);
              break;
            }
          }
          
          if (attachedRefId != null) {
            break;
          }
        }
        
        attachedRefId = lookForAttachedRef(boundaryEventId, childNode.get(EDITOR_CHILD_SHAPES));
        
        if (attachedRefId != null) {
          break;
        }
      }
    }
    
    return attachedRefId;
  }
}
