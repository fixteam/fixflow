package com.founder.fix.fixflow.expand.connector.SendTask;


import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.action.ConnectorHandler;
import com.founder.fix.webcore.DataView;
import com.founder.fix.webcore.interfaceLayer.AdapterProxy;

public class SendTask implements ConnectorHandler {

	private java.lang.Object field0;

	public void execute(ExecutionContext executionContext) throws Exception {

		AdapterProxy.executeArgsForFront("WT_UP_TASK.dataCollect", DataView.buildDataView(), field0);

	}

	public void  setField0(java.lang.Object field0){
		this.field0 = field0;
	}

}