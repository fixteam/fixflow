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
package com.founder.fix.fixflow.editor.language;

import java.io.IOException;
import java.io.InputStream;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.eclipse.bpmn2.Definitions;

import com.founder.fix.fixflow.editor.language.json.converter.BpmnJsonConverter;

public abstract class AbstractConverterTest {

  protected Definitions readJsonFile() throws Exception {
    InputStream jsonStream = this.getClass().getClassLoader().getResourceAsStream(getResource());
    JsonNode modelNode = new ObjectMapper().readTree(jsonStream);
    Definitions bpmnModel = new BpmnJsonConverter().convertToBpmnModel(modelNode);
    return bpmnModel;
  }
  
  protected Definitions convertToJsonAndBack(Definitions bpmnModel) throws InstantiationException, IllegalAccessException, JsonProcessingException, IOException {
    ObjectNode modelNode = new BpmnJsonConverter().convertToJson(bpmnModel);
    bpmnModel = new BpmnJsonConverter().convertToBpmnModel(modelNode);
    return bpmnModel;
  }
  
  protected abstract String getResource();
}
