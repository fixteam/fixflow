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
import com.founder.fix.fixflow.editor.language.json.converter.BpmnJsonConverterUtil;
import com.founder.fix.fixflow.editor.language.json.converter.StartEventJsonConverter;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.CatchEvent;
import org.eclipse.bpmn2.ErrorEventDefinition;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.MessageEventDefinition;
import org.eclipse.bpmn2.SignalEventDefinition;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.TimerEventDefinition;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry;
import org.eclipse.emf.ecore.util.FeatureMap;

import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.StartEventBehavior;
import com.founder.fix.fixflow.core.impl.util.BpmnModelUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

/**
 * @author Tijs Rademakers
 */
public class StartEventJsonConverter extends BaseBpmnJsonConverter {
	public static void fillTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap,
			Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
    
		fillJsonTypes(convertersToBpmnMap);
		fillBpmnTypes(convertersToJsonMap);
	}
  
	public static void fillJsonTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap) {
		convertersToBpmnMap.put(STENCIL_EVENT_START_NONE, StartEventJsonConverter.class);
		convertersToBpmnMap.put(STENCIL_EVENT_START_TIMER, StartEventJsonConverter.class);
		convertersToBpmnMap.put(STENCIL_EVENT_START_ERROR, StartEventJsonConverter.class);
		convertersToBpmnMap.put(STENCIL_EVENT_START_MESSAGE, StartEventJsonConverter.class);
		convertersToBpmnMap.put(STENCIL_EVENT_START_SIGNAL, StartEventJsonConverter.class);
	}
  
	public static void fillBpmnTypes(Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
		convertersToJsonMap.put(StartEventBehavior.class, StartEventJsonConverter.class);
	}
  
	protected String getStencilId(FlowElement flowElement) {
		CatchEvent event = (CatchEvent) flowElement;
		if (event.getEventDefinitions().size() > 0) {
			EventDefinition eventDefinition = event.getEventDefinitions().get(0);
			if(eventDefinition instanceof TimerEventDefinition) {
				return STENCIL_EVENT_START_TIMER;
			}else if(eventDefinition instanceof ErrorEventDefinition) {
				return STENCIL_EVENT_START_ERROR;
			}else if(eventDefinition instanceof MessageEventDefinition) {
				return STENCIL_EVENT_START_MESSAGE;
			}else if(eventDefinition instanceof SignalEventDefinition) {
				return STENCIL_EVENT_START_SIGNAL;
			}
		}
		return STENCIL_EVENT_START_NONE;
	}
  
	protected void convertElementToJson(ObjectNode propertiesNode, FlowElement flowElement) {
		StartEventBehavior startEvent = (StartEventBehavior) flowElement;
		setPropertyValue(PROPERTY_START_EVENT_ISPERSISTENCE, StringUtil.getString(startEvent.isPersistence()), propertiesNode);
	}
  
	protected FlowElement convertJsonToElement(JsonNode elementNode,JsonNode modelNode, Map<String, JsonNode> shapeMap) {
		StartEvent startEvent = Bpmn2Factory.eINSTANCE.createStartEvent();
		String stencilId = BpmnJsonConverterUtil.getStencilId(elementNode);
		if (STENCIL_EVENT_START_NONE.equals(stencilId)) {
			boolean isPersistence = true;
			JsonNode isPersistenceNode = BpmnJsonConverterUtil.getProperty(PROPERTY_START_EVENT_ISPERSISTENCE, elementNode);
			if (isPersistenceNode != null) {
				isPersistence = isPersistenceNode.asBoolean();
			}
			if (!isPersistence) {
				BpmnModelUtil.addExtensionAttribute(startEvent, FixFlowPackage.Literals.DOCUMENT_ROOT__IS_PERSISTENCE, StringUtil.getString(isPersistence));
			}
		} else if (STENCIL_EVENT_START_TIMER.equals(stencilId)) {
			convertJsonToTimerDefinition(elementNode, startEvent);
		} else if (STENCIL_EVENT_START_ERROR.equals(stencilId)) {
			convertJsonToErrorDefinition(elementNode, startEvent);
		} else if (STENCIL_EVENT_START_MESSAGE.equals(stencilId)) {
			convertJsonToMessageDefinition(elementNode, startEvent);
		} else if (STENCIL_EVENT_START_SIGNAL.equals(stencilId)) {
			convertJsonToSignalDefinition(elementNode, startEvent);
		}
		return startEvent;
	}
}
