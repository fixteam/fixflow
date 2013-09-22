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
package com.founder.fix.fixflow.core.event;

import java.util.List;

import org.eclipse.bpmn2.BaseElement;

import com.founder.fix.fixflow.core.impl.connector.ConnectorInstanceBehavior;


public interface BaseElementEvent {
	  public static final String EVENTTYPE_TRANSITION = "transition";
	  public static final String EVENTTYPE_BEFORE_SIGNAL = "before-signal";
	  public static final String EVENTTYPE_AFTER_SIGNAL = "after-signal";
	  public static final String EVENTTYPE_PROCESS_START = "process-start";
	  public static final String EVENTTYPE_PROCESS_END = "process-end";
	  public static final String EVENTTYPE_NODE_ENTER = "node-enter";
	  public static final String EVENTTYPE_NODE_LEAVE = "node-leave";
	  public static final String EVENTTYPE_TASK_ROLLBACK = "task-rollback";
	  public static final String EVENTTYPE_TASK_JUMP = "task-jump";
	  public static final String EVENTTYPE_SUPERSTATE_ENTER = "superstate-enter";
	  public static final String EVENTTYPE_SUPERSTATE_LEAVE = "superstate-leave";
	  public static final String EVENTTYPE_SUBPROCESS_CREATED = "subprocess-created";
	  public static final String EVENTTYPE_SUBPROCESS_END = "subprocess-end";
	  public static final String EVENTTYPE_TASK_CREATE = "task-create";
	  public static final String EVENTTYPE_TASK_ASSIGN = "task-assign";
	  public static final String EVENTTYPE_TASK_ASSIGN_CHANGE = "task-assign-change";
	  public static final String EVENTTYPE_TASK_START = "task-start";
	  public static final String EVENTTYPE_TASK_END = "task-end";
	  public static final String EVENTTYPE_TIMER = "timer";
	  
	  public static final String EVENTTYPE_PROCESS_ABORT = "process-abort";
	  
	  
	  
	  
	  
	  
	  List<ConnectorInstanceBehavior> getConnectors();
	  
	  boolean hasConnectors();
	  
	  ConnectorInstanceBehavior addConnector(ConnectorInstanceBehavior connector);
	  
	  void removeConnector(ConnectorInstanceBehavior connector);
	  
	  String getEventType();
	  
	  BaseElement getBaseElement();
	  
	  void setBaseElement(BaseElement baseElement);
	  
	  String getId();
	  
	  void setId(String Id);

}
