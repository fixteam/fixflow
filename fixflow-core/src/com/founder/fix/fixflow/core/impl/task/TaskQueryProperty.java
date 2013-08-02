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
package com.founder.fix.fixflow.core.impl.task;

import java.util.HashMap;
import java.util.Map;

import com.founder.fix.fixflow.core.query.QueryProperty;





/**
 *
 * 
 * @author kenshin
 */
public class TaskQueryProperty implements QueryProperty {
  
  private static final Map<String, TaskQueryProperty> properties = new HashMap<String, TaskQueryProperty>();

  public static final TaskQueryProperty TASK_ID = new TaskQueryProperty("T.TASKINSTANCE_ID");
  public static final TaskQueryProperty NAME = new TaskQueryProperty("T.NAME");
  public static final TaskQueryProperty DESCRIPTION = new TaskQueryProperty("T.DESCRIPTION");
  public static final TaskQueryProperty PRIORITY = new TaskQueryProperty("T.PRIORITY");
  public static final TaskQueryProperty ASSIGNEE = new TaskQueryProperty("T.ASSIGNEE");
  public static final TaskQueryProperty CREATE_TIME = new TaskQueryProperty("T.CREATE_TIME");
  public static final TaskQueryProperty PROCESS_INSTANCE_ID = new TaskQueryProperty("T.PROCESSINSTANCE_ID");
  public static final TaskQueryProperty EXECUTION_ID = new TaskQueryProperty("T.EXECUTION_ID_");
  public static final TaskQueryProperty DUE_DATE = new TaskQueryProperty("T.DUEDATE");
  public static final TaskQueryProperty END_TIME = new TaskQueryProperty("T.END_TIME");
  private String name;

  public TaskQueryProperty(String name) {
    this.name = name;
    properties.put(name, this);
  }

  public String getName() {
    return name;
  }
  
  public static TaskQueryProperty findByName(String propertyName) {
    return properties.get(propertyName);
  }

}
