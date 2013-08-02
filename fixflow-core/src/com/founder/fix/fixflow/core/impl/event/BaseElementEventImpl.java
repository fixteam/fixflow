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
package com.founder.fix.fixflow.core.impl.event;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.BaseElement;

import com.founder.fix.fixflow.core.event.BaseElementEvent;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.connector.ConnectorInstanceBehavior;


public class BaseElementEventImpl implements BaseElementEvent {

	String id;
	protected String eventType;
	protected BaseElement baseElement;
	protected List<ConnectorInstanceBehavior> connectors;
	

	public BaseElementEventImpl() {
	}

	public BaseElementEventImpl(String eventType) {
		this.eventType = eventType;
	}

	public BaseElementEventImpl(BaseElement baseElement, String eventType) {
		this.baseElement = baseElement;
		this.eventType = eventType;
	}

	public List<ConnectorInstanceBehavior> getConnectors() {
		return this.connectors;
	}

	public boolean hasConnectors() {
		return ((connectors != null) && (connectors.size() > 0));
	}

	public ConnectorInstanceBehavior addConnector(ConnectorInstanceBehavior connector) {
		if (connector == null)
			throw new FixFlowException("不能在Event中添加一个null的action");
		if (connectors == null)
			connectors = new ArrayList<ConnectorInstanceBehavior>();

		connectors.add(connector);
		return connector;
	}

	public void removeConnector(ConnectorInstanceBehavior connector) {
		if (connector == null)
			throw new FixFlowException("不能在Event中移除一个null的action");
		if (connectors != null) {
			connectors.remove(connector);
		}
	}

	public String toString() {
		return this.eventType;
	}

	public String getEventType() {
		return this.eventType;
	}

	public BaseElement getBaseElement() {
		return this.baseElement;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String Id) {
		this.id = Id;
	}

	public void setBaseElement(BaseElement baseElement) {
		this.baseElement=baseElement;
	}


	
	
	

}
