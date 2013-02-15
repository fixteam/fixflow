package com.founder.fix.fixflow.expand.connector.ExecuteFixService;


import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.action.ConnectorHandler;
import com.founder.fix.webcore.DataView;
import com.founder.fix.webcore.interfaceLayer.AdapterProxy;

public class ExecuteFixService implements ConnectorHandler {

	private java.lang.String serviceName;

	private java.util.HashMap<String, Object> paramMap;
	
	private Object outputValue;

	public void execute(ExecutionContext executionContext) throws Exception {


		outputValue=AdapterProxy.executeForNoPermission(serviceName,DataView.buildDataView(),paramMap);


	}

	public void  setServiceName(java.lang.String serviceName){
		this.serviceName = serviceName;
	}

	public void  setParamMap(java.util.HashMap<String, Object> paramMap){
		this.paramMap = paramMap;
	}

	public java.lang.Object  getOutputValue(){
		return outputValue ;
	}

}