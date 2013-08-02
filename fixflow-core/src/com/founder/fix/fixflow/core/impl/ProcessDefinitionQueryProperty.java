/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
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
 * @author kenshin
 */
package com.founder.fix.fixflow.core.impl;
import java.util.HashMap;
import java.util.Map;

import com.founder.fix.fixflow.core.query.QueryProperty;




/**
 * 
 * @author kenshin
 */
public class ProcessDefinitionQueryProperty implements QueryProperty {
  
  private static final Map<String, ProcessDefinitionQueryProperty> properties = new HashMap<String, ProcessDefinitionQueryProperty>();
  
  public static final ProcessDefinitionQueryProperty PROCESS_DEFINITION_KEY = new ProcessDefinitionQueryProperty("PD.PROCESS_KEY");
  public static final ProcessDefinitionQueryProperty PROCESS_DEFINITION_CATEGORY = new ProcessDefinitionQueryProperty("PD.CATEGORY");
  public static final ProcessDefinitionQueryProperty PROCESS_DEFINITION_ID = new ProcessDefinitionQueryProperty("PD.PROCESS_ID");
  public static final ProcessDefinitionQueryProperty PROCESS_DEFINITION_VERSION = new ProcessDefinitionQueryProperty("PD.VERSION");
  public static final ProcessDefinitionQueryProperty PROCESS_DEFINITION_NAME = new ProcessDefinitionQueryProperty("PD.PROCESS_NAME");
  public static final ProcessDefinitionQueryProperty DEPLOYMENT_ID = new ProcessDefinitionQueryProperty("PD.DEPLOYMENT_ID");

  private String name;

  public ProcessDefinitionQueryProperty(String name) {
    this.name = name;
    properties.put(name, this);
  }

  public String getName() {
    return name;
  }
  
  public static ProcessDefinitionQueryProperty findByName(String propertyName) {
    return properties.get(propertyName);
  }

}
