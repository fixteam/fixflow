package com.founder.fix.fixflow.expand.flowconnector.SecureWSConnector;


import java.io.StringReader;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.action.ConnectorHandler;

public class SecureWSConnector implements ConnectorHandler {

	private java.lang.String serviceNS;

	private java.lang.String serviceName;

	private java.lang.String portName;

	private java.lang.String request;

	private java.lang.String endPointAddress;

	private java.lang.String binding;

	private java.lang.String SOAPAction;
	
	private Source response;
	
	public void execute(ExecutionContext executionContext) throws Exception {
		 final QName serviceQName = new QName(serviceNS, serviceName);
		    final QName portQName = new QName(serviceNS, portName);
		    final Service service = Service.create(serviceQName);
		    service.addPort(portQName, binding, endPointAddress);
		    final Dispatch<Source> dispatch = service.createDispatch(portQName, Source.class, Service.Mode.MESSAGE);
		    
		    if (SOAPAction != null) {
		      dispatch.getRequestContext().put(BindingProvider.SOAPACTION_USE_PROPERTY, true);
		      dispatch.getRequestContext().put(BindingProvider.SOAPACTION_URI_PROPERTY, SOAPAction);
		    }
		    
		    this.response = dispatch.invoke(new StreamSource(new StringReader(request)));
	}

	public void  setServiceNS(java.lang.String serviceNS){
		this.serviceNS = serviceNS;
	}

	public void  setServiceName(java.lang.String serviceName){
		this.serviceName = serviceName;
	}

	public void  setPortName(java.lang.String portName){
		this.portName = portName;
	}

	public void  setRequest(java.lang.String request){
		this.request = request;
	}

	public void  setEndPointAddress(java.lang.String endPointAddress){
		this.endPointAddress = endPointAddress;
	}

	public void  setBinding(java.lang.String binding){
		this.binding = binding;
	}

	public void  setSOAPAction(java.lang.String SOAPAction){
		this.SOAPAction = SOAPAction;
	}

}