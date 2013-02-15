package com.founder.fix.fixflow.expand.connector.FixService;


import java.util.HashMap;

import com.founder.fix.fixflow.core.action.ConnectorHandler;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.webcore.DataView;
import com.founder.fix.webcore.interfaceLayer.AdapterProxy;

public class FixService implements ConnectorHandler {

	private java.lang.String serviceID;

	private java.lang.Object params;

	private java.util.HashMap context;

	public void execute(ExecutionContext executionContext) throws Exception {
		DataView dv = DataView.buildDataView();
		Object obj  = AdapterProxy.executeForNoPermission(serviceID,dv,params);
		context.putAll(dv.getReturnData());
	}

	public void  setServiceID(java.lang.String serviceID){
		this.serviceID = serviceID;
	}

	public void  setParams(java.lang.Object params){
		this.params = params;
	}

	public void  setContext(java.util.HashMap context){
		this.context = context;
	}

	public HashMap  getOut(){
		return context ;
	}

}