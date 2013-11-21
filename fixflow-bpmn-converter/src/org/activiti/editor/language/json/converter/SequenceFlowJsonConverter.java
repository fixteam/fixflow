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

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.di.BPMNEdge;
import org.eclipse.dd.dc.Point;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.SequenceFlowBehavior;
import com.founder.fix.fixflow.core.impl.util.BpmnModelUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

/**
 * @author Tijs Rademakers
 */
public class SequenceFlowJsonConverter extends BaseBpmnJsonConverter {

  public static void fillTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap,
      Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
    
    fillJsonTypes(convertersToBpmnMap);
    fillBpmnTypes(convertersToJsonMap);
  }
  
  public static void fillJsonTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap) {
    convertersToBpmnMap.put(STENCIL_SEQUENCE_FLOW, SequenceFlowJsonConverter.class);
  }
  
  public static void fillBpmnTypes(Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
    convertersToJsonMap.put(SequenceFlowBehavior.class, SequenceFlowJsonConverter.class);
  }
  
  @Override
  protected String getStencilId(FlowElement flowElement) {
    return STENCIL_SEQUENCE_FLOW;
  }
  
  @Override
  public void convertToJson(FlowElement flowElement, ActivityProcessor processor,
      Definitions model, ArrayNode shapesArrayNode, double subProcessX, double subProcessY) {
    
    SequenceFlowBehavior sequenceFlow = (SequenceFlowBehavior) flowElement;
    ObjectNode flowNode = BpmnJsonConverterUtil.createChildShape(sequenceFlow.getId(), STENCIL_SEQUENCE_FLOW, 172, 212, 128, 212);
    ArrayNode dockersArrayNode = objectMapper.createArrayNode();
    ObjectNode dockNode = objectMapper.createObjectNode();
    dockNode.put(EDITOR_BOUNDS_X, BpmnModelUtil.getBpmnShape(model, sequenceFlow.getSourceRef().getId()).getBounds().getWidth() / 2.0);
    dockNode.put(EDITOR_BOUNDS_Y, BpmnModelUtil.getBpmnShape(model,sequenceFlow.getSourceRef().getId()).getBounds().getHeight() / 2.0);
    dockersArrayNode.add(dockNode);
    
    BPMNEdge bpmnEdge=BpmnModelUtil.getBpmnEdge(model, sequenceFlow.getId());
    
    if (bpmnEdge.getWaypoint().size() > 2) {
      for (int i = 1; i < bpmnEdge.getWaypoint().size() - 1; i++) {
    	  Point graphicInfo =  (bpmnEdge.getWaypoint().get(i));
        dockNode = objectMapper.createObjectNode();
        dockNode.put(EDITOR_BOUNDS_X, graphicInfo.getX());
        dockNode.put(EDITOR_BOUNDS_Y, graphicInfo.getY());
        dockersArrayNode.add(dockNode);
      }
    }
    
    dockNode = objectMapper.createObjectNode();
    dockNode.put(EDITOR_BOUNDS_X, BpmnModelUtil.getBpmnShape(model, sequenceFlow.getTargetRef().getId()).getBounds().getWidth() / 2.0);
    dockNode.put(EDITOR_BOUNDS_Y, BpmnModelUtil.getBpmnShape(model, sequenceFlow.getTargetRef().getId()).getBounds().getHeight() / 2.0);
    dockersArrayNode.add(dockNode);
    flowNode.put("dockers", dockersArrayNode);
    ArrayNode outgoingArrayNode = objectMapper.createArrayNode();
    outgoingArrayNode.add(BpmnJsonConverterUtil.createResourceNode(sequenceFlow.getTargetRef().getId()));
    flowNode.put("outgoing", outgoingArrayNode);
    flowNode.put("target", BpmnJsonConverterUtil.createResourceNode(sequenceFlow.getTargetRef().getId()));
    
    ObjectNode propertiesNode = objectMapper.createObjectNode();
    propertiesNode.put(PROPERTY_OVERRIDE_ID, flowElement.getId());
    if (StringUtils.isNotEmpty(sequenceFlow.getName())) {
      propertiesNode.put(PROPERTY_NAME, sequenceFlow.getName());
    }
    
    if (StringUtils.isNotEmpty(BpmnModelUtil.getDocumentation(sequenceFlow))) {
      propertiesNode.put(PROPERTY_DOCUMENTATION, BpmnModelUtil.getDocumentation(sequenceFlow));
    }
    
    if (StringUtils.isNotEmpty(BpmnModelUtil.getExpression(sequenceFlow.getConditionExpression()))) {
      propertiesNode.put(PROPERTY_SEQUENCEFLOW_CONDITION, BpmnModelUtil.getExpression(sequenceFlow.getConditionExpression()));
    }
    propertiesNode.put(PROPERTY_SEQUENCEFLOW_ORDERID, sequenceFlow.getOrderId());
    
    flowNode.put(EDITOR_SHAPE_PROPERTIES, propertiesNode);
    
    shapesArrayNode.add(flowNode);
  }
  
  @Override
  protected void convertElementToJson(ObjectNode propertiesNode, FlowElement flowElement) {
    // nothing to do
  }
  
  @Override
  protected FlowElement convertJsonToElement(JsonNode elementNode, JsonNode modelNode, Map<String, JsonNode> shapeMap) {
    SequenceFlow flow = Bpmn2Factory.eINSTANCE.createSequenceFlow();// SequenceFlow();
    
    String sourceRef = lookForSourceRef(elementNode.get(EDITOR_SHAPE_ID).asText(), modelNode.get(EDITOR_CHILD_SHAPES));
    
    if (sourceRef != null) {
      flow.setSourceRef(BpmnModelUtil.getElement(model, sourceRef,FlowNode.class));
      String targetId = elementNode.get("target").get(EDITOR_SHAPE_ID).asText();
      flow.setTargetRef(BpmnModelUtil.getElement(model, BpmnJsonConverterUtil.getElementId(shapeMap.get(targetId)),FlowNode.class));
    }
    
    FormalExpression formalExpression=Bpmn2Factory.eINSTANCE.createFormalExpression();
    formalExpression.setBody(getPropertyValueAsString(PROPERTY_SEQUENCEFLOW_CONDITION, elementNode));
    
    flow.setConditionExpression(formalExpression);
    
    return flow;
  }
  
  private String lookForSourceRef(String flowId, JsonNode childShapesNode) {
    String sourceRef = null;
    
    if (childShapesNode != null) {
    
      for (JsonNode childNode : childShapesNode) {
        ArrayNode outgoingNode = (ArrayNode) childNode.get("outgoing");
        if (outgoingNode != null && outgoingNode.size() > 0) {
          for (JsonNode outgoingChildNode : outgoingNode) {
            JsonNode resourceNode = outgoingChildNode.get(EDITOR_SHAPE_ID);
            if (resourceNode != null && flowId.equals(resourceNode.asText())) {
              sourceRef = BpmnJsonConverterUtil.getElementId(childNode);
              break;
            }
          }
          
          if (sourceRef != null) {
            break;
          }
        }
        sourceRef = lookForSourceRef(flowId, childNode.get(EDITOR_CHILD_SHAPES));
        
        if (sourceRef != null) {
          break;
        }
      }
    }
    return sourceRef;
  }
}
