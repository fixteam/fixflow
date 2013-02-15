package com.founder.fix.fixflow.core.impl.event;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.BaseElement;

import com.founder.fix.fixflow.core.event.BaseElementEvent;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.connector.ConnectorDefinition;

public class BaseElementEventImpl implements BaseElementEvent {

	String id;
	protected String eventType;
	protected BaseElement baseElement;
	protected List<ConnectorDefinition> connectors;
	

	public BaseElementEventImpl() {
	}

	public BaseElementEventImpl(String eventType) {
		this.eventType = eventType;
	}

	public BaseElementEventImpl(BaseElement baseElement, String eventType) {
		this.baseElement = baseElement;
		this.eventType = eventType;
	}

	public List<ConnectorDefinition> getConnectors() {
		return this.connectors;
	}

	public boolean hasConnectors() {
		return ((connectors != null) && (connectors.size() > 0));
	}

	public ConnectorDefinition addConnector(ConnectorDefinition connector) {
		if (connector == null)
			throw new FixFlowException("不能在Event中添加一个null的action");
		if (connectors == null)
			connectors = new ArrayList<ConnectorDefinition>();

		connectors.add(connector);
		return connector;
	}

	public void removeConnector(ConnectorDefinition connector) {
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
