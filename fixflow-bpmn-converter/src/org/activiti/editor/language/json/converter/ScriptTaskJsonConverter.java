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

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.ScriptTask;

import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.fixflow.core.ProcessEngineConfiguration;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ScriptTaskBehavior;
import com.founder.fix.fixflow.core.impl.util.BpmnModelUtil;

/**
 * @author Tijs Rademakers
 */
public class ScriptTaskJsonConverter extends BaseBpmnJsonConverter {

  public static void fillTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap,
      Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
    
    fillJsonTypes(convertersToBpmnMap);
    fillBpmnTypes(convertersToJsonMap);
  }
  
  public static void fillJsonTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap) {
    convertersToBpmnMap.put(STENCIL_TASK_SCRIPT, ScriptTaskJsonConverter.class);
  }
  
  public static void fillBpmnTypes(Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
    convertersToJsonMap.put(ScriptTaskBehavior.class, ScriptTaskJsonConverter.class);
  }
  
  protected String getStencilId(FlowElement flowElement) {
    return STENCIL_TASK_SCRIPT;
  }
  
  protected void convertElementToJson(ObjectNode propertiesNode, FlowElement flowElement) {
  	ScriptTask scriptTask = (ScriptTask) flowElement;
  	propertiesNode.put(PROPERTY_SCRIPT_TEXT, scriptTask.getScript());
  }
  
  protected FlowElement convertJsonToElement(JsonNode elementNode, JsonNode modelNode, Map<String, JsonNode> shapeMap) {
    ScriptTask task = Bpmn2Factory.eINSTANCE.createScriptTask();// ScriptTask();
    //String scriptFormat = ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getScriptLanguageConfig().getSelected();
    String scriptFormat = "Groovy";
    task.setScriptFormat(scriptFormat);
    BpmnModelUtil.addExtensionAttribute(task, FixFlowPackage.Literals.DOCUMENT_ROOT__SCRIPT_NAME, getPropertyValueAsString(PROPERTY_SCRIPT_TEXT, elementNode));
    task.setScript(getPropertyValueAsString(PROPERTY_SCRIPT_TEXT, elementNode));
    return task;
  }
}
