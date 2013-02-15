package com.founder.fix.fixflow.expand.connector.ReciveTask;


import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.action.ConnectorHandler;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.webcore.DataView;
import com.founder.fix.webcore.interfaceLayer.AdapterProxy;

public class ReciveTask implements ConnectorHandler {

	private java.lang.Object field0;

	public void execute(ExecutionContext executionContext) throws Exception {
	
		AdapterProxy.executeArgsForFront("WT_DATA_FILL.subDataCollect", DataView.buildDataView(), field0);
		Object jiangnanObject=ExpressionMgmt.execute("newforminfo.getData(\"RWCD\")", executionContext);
		ExpressionMgmt.execute("${lingpai}=newtokenid", executionContext);
		ExpressionMgmt.execute("${pforminfo}=newforminfo", executionContext);
		ExpressionMgmt.execute("${pformdata}=newformdata", executionContext);
		executionContext.getProcessInstance().setBizKey(jiangnanObject.toString());
		
		
	}

	public void  setField0(java.lang.Object field0){
		this.field0 = field0;
	}

}