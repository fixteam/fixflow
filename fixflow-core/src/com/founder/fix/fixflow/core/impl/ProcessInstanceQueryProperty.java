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
