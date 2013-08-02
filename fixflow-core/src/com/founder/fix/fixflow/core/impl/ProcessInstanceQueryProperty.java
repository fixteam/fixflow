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
public class ProcessInstanceQueryProperty  implements QueryProperty {
  
  private static final Map<String, ProcessInstanceQueryProperty> properties = new HashMap<String, ProcessInstanceQueryProperty>();

  public static final ProcessInstanceQueryProperty PROCESS_INSTANCE_ID = new ProcessInstanceQueryProperty("E.PROCESSINSTANCE_ID");
  public static final ProcessInstanceQueryProperty PROCESS_DEFINITION_KEY = new ProcessInstanceQueryProperty("E.PROCESSDEFINITION_KEY");
  public static final ProcessInstanceQueryProperty PROCESS_DEFINITION_ID = new ProcessInstanceQueryProperty("E.RROCESSDEFINITION_ID");
  public static final ProcessInstanceQueryProperty START_TIME = new ProcessInstanceQueryProperty("E.START_TIME");
  public static final ProcessInstanceQueryProperty UPDATE_TIME = new ProcessInstanceQueryProperty("E.UPDATE_TIME");
  private String name;

  public ProcessInstanceQueryProperty(String name) {
    this.name = name;
    properties.put(name, this);
  }

  public String getName() {
    return name;
  }
  
  public static ProcessInstanceQueryProperty findByName(String propertyName) {
    return properties.get(propertyName);
  }

}
